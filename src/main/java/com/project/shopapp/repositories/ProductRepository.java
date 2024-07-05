package com.project.shopapp.repositories;

import com.project.shopapp.models.ProductModel;
import com.project.shopapp.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    boolean existsByName(String name);

    // phân trang
    Page<ProductModel> findAll(Pageable pageable);

    @Query(
            "SELECT p from ProductModel p where " +
                    "(:categoryId is null or :categoryId = 0 or p.categoryModel.id = :categoryId) " +
                    "and (:keyword is null or :keyword = '' or p.name like %:keyword% or p.description like %:keyword%)"
    )
    Page<ProductModel> searchProducts(
            @Param("categoryId") Long categoryId,
            @Param("keyword") String keyword,
            Pageable pageable
    );

    @Query("select p from ProductModel p where p.id in :productIds")
    List<ProductModel> findProductsByIds(@Param("productIds")List<Long> productIds);
}
