package org.nic.bug_tracker_system.controller;

import org.nic.bug_tracker_system.service.CategoryEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryEnumController {

    @Autowired
    private CategoryEnumService categoryService;

    @GetMapping
    public ResponseEntity<List<String>> getCategoryEnums() {
        List<String> formattedCategories = categoryService.getFormattedCategoryEnums();
        return ResponseEntity.ok(formattedCategories);
    }
}
