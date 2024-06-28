package com.project.shopapp.services;

import com.project.shopapp.dtos.ProductDTO;
import com.project.shopapp.dtos.ProductImageDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.ProductImageModel;
import com.project.shopapp.models.ProductModel;
import com.project.shopapp.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {
    ProductModel createProduct(ProductDTO productDTO) throws DataNotFoundException;

    ProductModel getProductById(long id) throws Exception;

    Page<ProductResponse> getProducts(PageRequest pageRequest);

    ProductModel updateProduct(long id, ProductDTO productDTO) throws Exception;

    void deleteProduct(long id) throws Exception;

    boolean existsByName(String name);

    ProductImageModel createProductImage(Long productId, ProductImageDTO productImageDT) throws Exception;
}
