package com.project.shopapp.services;

import com.project.shopapp.dtos.OrderDetailDTO;
import com.project.shopapp.models.OrderDetailModel;
import com.project.shopapp.responses.OrderDetailResponse;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailResponse createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    OrderDetailModel getOrderDetail(Long orderDetailId) throws Exception;

    OrderDetailModel updateOrderDetail(Long orderDetailId, OrderDetailDTO orderDetailDTO) throws Exception;

    void deleteOrderDetail(Long orderDetailId) throws Exception;

    List<OrderDetailModel> getOrderDetails(Long orderId) throws Exception;
}
