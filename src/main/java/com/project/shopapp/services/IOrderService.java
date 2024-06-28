package com.project.shopapp.services;

import com.project.shopapp.dtos.OrderDTO;
import com.project.shopapp.models.OrderModel;
import com.project.shopapp.responses.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws Exception;

    OrderResponse getOrder(Long orderId) throws Exception;

    OrderModel updateOrder(Long orderId, OrderDTO orderDTO) throws Exception;

    void deleteOrder(Long orderId) throws Exception;

    List<OrderResponse> getOrders(Long userId);
}
