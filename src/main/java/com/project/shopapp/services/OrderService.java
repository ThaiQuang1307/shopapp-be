package com.project.shopapp.services;

import com.project.shopapp.dtos.OrderDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.OrderModel;
import com.project.shopapp.models.OrderStatus;
import com.project.shopapp.models.UserModel;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.repositories.UserRepository;
import com.project.shopapp.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderDTO orderDTO) throws Exception {
        // kiểm tra user_id có tồn tại không
        UserModel user = userRepository
                .findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: " + orderDTO.getUserId()));
        // convert OrderDTO -> OrderModel
        // dùng thư viện Model Mapper
        // tạo 1 luồng bằng ánh xạ riêng để kiểm soát việc ánh xạ
        modelMapper.typeMap(OrderDTO.class, OrderModel.class)
                .addMappings(mapper -> mapper.skip(OrderModel::setId));
        // cập nhật các trường của order từ orderDTO
        OrderModel orderModel = new OrderModel();
        modelMapper.map(orderDTO, orderModel);
        orderModel.setUserModel(user);
        orderModel.setOrderDate(new Date());
        orderModel.setStatus(OrderStatus.PENDING);

        // kiểm tra shipping date >= ngày hôm nay
        LocalDate shippingDate = orderDTO.getShippingDate() == null
                ? LocalDate.now()
                : orderDTO.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException("Data must be ay least today!");
        }
        orderModel.setShippingDate(shippingDate);
        orderModel.setActive(true);
        // lưu vào DB
        orderRepository.save(orderModel);
        // map sang response
        modelMapper.typeMap(OrderModel.class, OrderResponse.class);
        OrderResponse orderResponse = new OrderResponse();
        modelMapper.map(orderModel, orderResponse);

        return orderResponse;
    }

    @Override
    public OrderResponse getOrder(Long orderId) throws Exception {
        OrderModel orderModel = orderRepository.findById(orderId).orElse(null);
        if (orderModel == null) {
            throw new DataNotFoundException("Cannot find product with id: " + orderId);
        }
        modelMapper.typeMap(OrderModel.class, OrderResponse.class);
        OrderResponse orderResponse = new OrderResponse();
        modelMapper.map(orderModel, orderResponse);
        return orderResponse;
    }

    @Override
    @Transactional
    public OrderModel updateOrder(Long orderId, OrderDTO orderDTO) throws Exception {
        OrderModel existingOrder = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order with id: " + orderId));
        UserModel existingUser = userRepository
                .findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: " + orderDTO.getUserId()));

        modelMapper.typeMap(OrderDTO.class, OrderModel.class)
                .addMappings(mapper -> mapper.skip(OrderModel::setId));

        modelMapper.map(orderDTO, existingOrder);
        existingOrder.setUserModel(existingUser);
        return orderRepository.save(existingOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) throws Exception {
        OrderModel existingOrder = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order with id: " + orderId));
        existingOrder.setActive(false);
        orderRepository.save(existingOrder);
    }

    @Override
    public List<OrderResponse> getOrders(Long userId) {
        modelMapper.typeMap(OrderModel.class, OrderResponse.class);
        List<OrderModel> orderModels = orderRepository.findAllByUserModel_id(userId);
        return orderModels.stream()
                .map(item -> modelMapper.map(item, OrderResponse.class))
                .toList();
    }
}
