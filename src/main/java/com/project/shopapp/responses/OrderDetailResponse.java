package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.OrderDetailModel;
import com.project.shopapp.models.OrderModel;
import com.project.shopapp.models.ProductModel;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {
    private Long id;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("product_id")
    private Long productId;

    private Float price;

    @JsonProperty("number_of_products")
    private Long numberOfProducts;

    @JsonProperty("total_money")
    private Float totalMoney;

    private String color;

    public static OrderDetailResponse fromOrderDetailModel(OrderDetailModel orderDetailModel){
        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setId(orderDetailModel.getId());
        orderDetailResponse.setOrderId(orderDetailModel.getOrderModel().getId());
        orderDetailResponse.setProductId(orderDetailModel.getProductModel().getId());
        orderDetailResponse.setPrice(orderDetailModel.getPrice());
        orderDetailResponse.setNumberOfProducts(orderDetailModel.getNumberOfProducts());
        orderDetailResponse.setTotalMoney(orderDetailModel.getTotalMoney());
        orderDetailResponse.setColor(orderDetailModel.getColor());
        return orderDetailResponse;
    }
}
