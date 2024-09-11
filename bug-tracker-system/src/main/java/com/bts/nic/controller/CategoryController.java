package com.bts.nic.controller;

import com.bts.nic.modal.Category;
import com.bts.nic.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

@RestController

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // API to get categories formatted for dropdown
  //  @CrossOrigin(origins = "*")
    @GetMapping("/categories")
    public ResponseEntity<List<Map<String, Object>>> getCategoriesForDropdown() {
        List<Category> categories = categoryService.getAllCategories();

        // Convert to the required format
        List<Map<String, Object>> categoryData = categories.stream()
                .map(category -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", category.getCategoryId());
                    map.put("name", category.getCategoryName());
                    return map;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(categoryData, HttpStatus.OK);
    }
}
