package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageModel {
    public static final int MAXIMUM_IMAGES_PER_PRODUCT = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel productModel;

    @Column(name = "image_url", length = 300)
    private String imageUrl;
}
