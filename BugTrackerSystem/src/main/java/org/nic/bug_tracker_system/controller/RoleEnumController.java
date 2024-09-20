package org.nic.bug_tracker_system.controller;

import java.util.List;
import org.nic.bug_tracker_system.service.RoleEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role-enums")
public class RoleEnumController {

    @Autowired
    private RoleEnumService roleEnumService;

    @GetMapping
    public ResponseEntity<List<String>> getFormattedRoleEnums() {
        List<String> formattedEnums = roleEnumService.getFormattedRoleEnums();
        return ResponseEntity.ok(formattedEnums);
    }
}
