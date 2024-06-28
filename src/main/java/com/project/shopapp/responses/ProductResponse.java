package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.ProductModel;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse extends BaseResponse {
    private Long id;

    private String name;

    private Float price;

    private String thumbnail;

    private String description;

    @JsonProperty("category_id")
    private Long categoryId;

    public static ProductResponse fromProductModel(ProductModel productModel) {
        ProductResponse productResponse = ProductResponse.builder()
                .id(productModel.getId())
                .name(productModel.getName())
                .price(productModel.getPrice())
                .thumbnail(productModel.getThumbnail())
                .description(productModel.getDescription())
                .categoryId(productModel.getCategoryModel().getId())
                .build();
        productResponse.setCreatedAt(productModel.getCreatedAt());
        productResponse.setUpdatedAt(productModel.getUpdatedAt());
        return productResponse;
    }
}
