package org.nic.bug_tracker_system.controller;

import java.util.List;
import org.nic.bug_tracker_system.service.PriorityEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/priority-enums")
public class PriorityEnumController {

    @Autowired
    private PriorityEnumService priorityEnumService;

    @GetMapping
    public ResponseEntity<List<String>> getFormattedPriorityEnums() {
        List<String> formattedEnums = priorityEnumService.getFormattedPriorityEnums();
        return ResponseEntity.ok(formattedEnums);
    }
}
