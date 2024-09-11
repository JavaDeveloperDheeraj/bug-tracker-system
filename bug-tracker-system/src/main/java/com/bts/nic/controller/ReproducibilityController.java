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

import com.bts.nic.modal.Reproducibility;
import com.bts.nic.service.ReproducibilityService;

@RestController

public class ReproducibilityController {

    @Autowired
    private ReproducibilityService reproducibilityService;

 // API to get Reproducibility formatted for dropdown
    @GetMapping("/reproducibility")
    public ResponseEntity<List<Map<String, Object>>> getCategoriesForDropdown() {
        List<Reproducibility> reproducibilities = reproducibilityService.getAllReproducibilities();

        // Convert to the required format
        List<Map<String, Object>> reproducibilityData = reproducibilities.stream()
                .map(reproducibilityItem -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", reproducibilityItem.getReproducibilityId());
                    map.put("name", reproducibilityItem.getReproducibilityName());
                    return map;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(reproducibilityData, HttpStatus.OK);
    }

}
