package com.project.shopapp.repositories;

import com.project.shopapp.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    boolean existsByName(String name);

    // phân trang
    Page<ProductModel> findAll(Pageable pageable);
}
