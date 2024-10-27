package org.nic.bug_tracker_system.service;

import java.util.List;

import org.nic.bug_tracker_system.entity.Category;

public interface CategoryService {
    Category saveCategory(Category category);
    Category getCategoryById(Integer categoryId);
    List<Category> getAllCategories();
    void deleteCategory(Integer categoryId);
}
