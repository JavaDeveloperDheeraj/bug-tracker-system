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

import com.bts.nic.modal.Severity;
import com.bts.nic.service.SeverityService;

@RestController

public class SeverityController {

    @Autowired
    private SeverityService severityService;

 // API to get Severity formatted for dropdown
    @GetMapping("/severity")
    public ResponseEntity<List<Map<String, Object>>> getSeverityForDropdown() {
        List<Severity> severities = severityService.getAllSeverities();

        // Convert to the required format
        List<Map<String, Object>> severityData = severities.stream()
                .map(severityItem -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", severityItem.getSeverityId());
                    map.put("name", severityItem.getSeverityName());
                    return map;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(severityData, HttpStatus.OK);
    }

}
