package com.project.shopapp.services;

import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.models.CategoryModel;

import java.util.List;

public interface ICategoryService {
    CategoryModel createCategory(CategoryDTO categoryDTO);

    CategoryModel getCategoryById(long id);

    List<CategoryModel> getCategories();

    CategoryModel updateCategory(long categoryId, CategoryDTO categoryDTO);

    void deleteCategory(long id);
}
