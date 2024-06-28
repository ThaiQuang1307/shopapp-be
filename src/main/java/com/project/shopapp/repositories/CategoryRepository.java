package com.project.shopapp.repositories;

import com.project.shopapp.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
//    CategoryModel findById(long id);
}
