package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.ProductImageModel;
import com.project.shopapp.models.ProductModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @JsonProperty("product_images")
    private List<ProductImageResponse> productImages = new ArrayList<>();

    public static ProductResponse fromProductModel(ProductModel productModel) {
        List<ProductImageResponse> productImageResponses = productModel.getProductImages().stream().map(item -> {
            return ProductImageResponse.builder()
                    .id(item.getId())
                    .imageUrl(item.getImageUrl())
                    .build();
        }).toList();
        ProductResponse productResponse = ProductResponse.builder()
                .id(productModel.getId())
                .name(productModel.getName())
                .price(productModel.getPrice())
                .thumbnail(productModel.getThumbnail())
                .description(productModel.getDescription())
                .categoryId(productModel.getCategoryModel().getId())
                .productImages(productImageResponses)
                .build();
        productResponse.setCreatedAt(productModel.getCreatedAt());
        productResponse.setUpdatedAt(productModel.getUpdatedAt());
        return productResponse;
    }
}
