package com.bts.nic.controller;

import com.bts.nic.modal.TaskManager;
import com.bts.nic.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskManagerController {

    @Autowired
    private TaskManagerService taskManagerService;

    @PostMapping("/task")
    public ResponseEntity<String> createTask(@RequestBody TaskManager task) {
        taskManagerService.saveTask(task);
        return new ResponseEntity<>("Task created successfully", HttpStatus.CREATED);
    }
}
