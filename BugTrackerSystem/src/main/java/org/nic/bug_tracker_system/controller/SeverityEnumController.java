package org.nic.bug_tracker_system.controller;

import java.util.List;
import org.nic.bug_tracker_system.service.SeverityEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/severity-enums")
public class SeverityEnumController {

    @Autowired
    private SeverityEnumService severityEnumService;

    @GetMapping
    public ResponseEntity<List<String>> getFormattedSeverityEnums() {
        List<String> formattedEnums = severityEnumService.getFormattedSeverityEnums();
        return ResponseEntity.ok(formattedEnums);
    }
}
