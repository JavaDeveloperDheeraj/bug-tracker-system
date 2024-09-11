package com.bts.nic.service;

import com.bts.nic.modal.Category;
import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    Category getCategoryById(Integer categoryId);
    List<Category> getAllCategories();
    void deleteCategory(Integer categoryId);
}
