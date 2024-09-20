package org.nic.bug_tracker_system.controller;

import java.util.List;
import org.nic.bug_tracker_system.service.ReproducibilityEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reproducibility-enums")
public class ReproducibilityEnumController {

    @Autowired
    private ReproducibilityEnumService reproducibilityEnumService;

    @GetMapping
    public ResponseEntity<List<String>> getFormattedReproducibilityEnums() {
        List<String> formattedEnums = reproducibilityEnumService.getFormattedReproducibilityEnums();
        return ResponseEntity.ok(formattedEnums);
    }
}
