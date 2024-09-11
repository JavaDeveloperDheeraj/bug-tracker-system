package com.bts.nic.controller;

import com.bts.nic.modal.TaskManager;
import com.bts.nic.service.impl.TaskManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskManagerServiceImpl taskManagerService;

    @GetMapping("/assigner/{assignerId}")
    @Secured("ROLE_ASSIGNER")
    public ResponseEntity<List<TaskManager>> getTasksForAssigner(@PathVariable Integer assignerId) {
        List<TaskManager> tasks = taskManagerService.getTasksForAssigner(assignerId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/developer/{developerId}")
    @Secured("ROLE_DEVELOPER")
    public ResponseEntity<List<TaskManager>> getTasksForDeveloper(@PathVariable Integer developerId) {
        List<TaskManager> tasks = taskManagerService.getTasksForDeveloper(developerId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<TaskManager>> getAllTasksForAdmin() {
        List<TaskManager> tasks = taskManagerService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
}
