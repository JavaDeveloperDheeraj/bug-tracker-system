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

import com.bts.nic.modal.AssignTo;
import com.bts.nic.service.AssignToService;

@RestController

public class AssignToController {

    @Autowired
    private AssignToService assignToService;

 // API to get Severity formatted for dropdown
    @GetMapping("/assignTo")
    public ResponseEntity<List<Map<String, Object>>> getAssignToForDropdown() {
        List<AssignTo> assignTos = assignToService.getAllAssignTo();

        // Convert to the required format
        List<Map<String, Object>> assignToData = assignTos.stream()
                .map(assignToItem -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", assignToItem.getDeveloperId());
                    map.put("name", assignToItem.getDeveloperName());
                    return map;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(assignToData, HttpStatus.OK);
    }

}
