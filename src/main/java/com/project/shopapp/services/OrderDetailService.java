package com.project.shopapp.services;

import com.project.shopapp.dtos.OrderDetailDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.OrderDetailModel;
import com.project.shopapp.models.OrderModel;
import com.project.shopapp.models.ProductModel;
import com.project.shopapp.repositories.OrderDetailRepository;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.responses.OrderDetailResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public OrderDetailResponse createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        // kiểm tra xem order có tồn tại hay ko
        OrderModel existingOrder = orderRepository
                .findById(orderDetailDTO.getOrderId())
                .orElseThrow(() ->
                        new DataNotFoundException("Cannot find order with id: " + orderDetailDTO.getOrderId()));
        // kiểm tra xem product có tồn tại hay ko
        ProductModel existingProduct = productRepository
                .findById(orderDetailDTO.getProductId())
                .orElseThrow(() ->
                        new DataNotFoundException("Cannot find product with id: " + orderDetailDTO.getProductId()));

        OrderDetailModel newOrderDetail = OrderDetailModel.builder()
                .orderModel(existingOrder)
                .productModel(existingProduct)
                .numberOfProducts(orderDetailDTO.getNumberOfProducts())
                .price(orderDetailDTO.getPrice())
                .totalMoney(orderDetailDTO.getTotalMoney())
                .color(orderDetailDTO.getColor())
                .build();
        orderDetailRepository.save(newOrderDetail);

        return OrderDetailResponse.fromOrderDetailModel(newOrderDetail);
    }

    @Override
    public OrderDetailModel getOrderDetail(Long orderDetailId) throws Exception {
        return orderDetailRepository
                .findById(orderDetailId)
                .orElseThrow(() ->
                        new DataNotFoundException("Cannot find order detail with id: " + orderDetailId));
    }

    @Override
    @Transactional
    public OrderDetailModel updateOrderDetail(
            Long orderDetailId,
            OrderDetailDTO orderDetailDTO
    ) throws Exception {
        // kiểm tra xem order-detail có tồn tại hay ko
        OrderDetailModel existingOrderDetail = orderDetailRepository
                .findById(orderDetailId)
                .orElseThrow(() ->
                        new DataNotFoundException("Cannot find order detail with id: " + orderDetailId));
        // kiểm tra xem order có tồn tại hay ko
        OrderModel existingOrder = orderRepository
                .findById(orderDetailDTO.getOrderId())
                .orElseThrow(() ->
                        new DataNotFoundException("Cannot find order with id: " + orderDetailDTO.getOrderId()));
        // kiểm tra xem product có tồn tại hay ko
        ProductModel existingProduct = productRepository
                .findById(orderDetailDTO.getProductId())
                .orElseThrow(() ->
                        new DataNotFoundException("Cannot find product with id: " + orderDetailDTO.getProductId()));

        existingOrderDetail.setPrice(orderDetailDTO.getPrice());
        existingOrderDetail.setColor(orderDetailDTO.getColor());
        existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        existingOrderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
        existingOrderDetail.setOrderModel(existingOrder);
        existingOrderDetail.setProductModel(existingProduct);

        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    @Transactional
    public void deleteOrderDetail(Long orderDetailId) throws Exception {
        orderDetailRepository.deleteById(orderDetailId);
    }

    @Override
    public List<OrderDetailModel> getOrderDetails(Long orderId) throws Exception {
        return orderDetailRepository.findAllByOrderModel_id(orderId);
    }
}
