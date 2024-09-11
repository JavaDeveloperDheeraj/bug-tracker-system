package com.bts.nic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bts.nic.modal.Priority;
import com.bts.nic.service.PriorityService;

@RestController

public class PriorityController {

    @Autowired
    private PriorityService priorityService;

 // API to get priority formatted for dropdown
    @GetMapping("/priority")
    public ResponseEntity<List<Map<String, Object>>> getCategoriesForDropdown() {
        List<Priority> priorities = priorityService.getAllPriorities();

        // Convert to the required format
        List<Map<String, Object>> priorityData = priorities.stream()
                .map(priorityItem -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", priorityItem.getPriorityId());
                    map.put("name", priorityItem.getPriorityName());
                    return map;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(priorityData, HttpStatus.OK);
    }

}
