package com.bts.nic.controller;

import com.bts.nic.modal.Login;
import com.bts.nic.modal.TaskManager;
import com.bts.nic.service.TaskManagerService;
import com.bts.nic.service.impl.LoginService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired TaskManagerService taskManagerService;
    
   


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Login login) {
        loginService.saveUser(login);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

//    @PostMapping("/user/login")
//    public ResponseEntity<String> loginUser(@RequestBody Login login) {
//        var user = loginService.findByUsername(login.getUsername());
//        if (user.isPresent() && passwordEncoder.matches(login.getPassword(), user.get().getPassword())) {
//            return ResponseEntity.ok("Login successful");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }
//    @PostMapping("/user/login")
//    public ResponseEntity<String> loginUser(@RequestBody Login login) {
//        var user = loginService.findByUsername(login.getUsername());
//        if (user.isPresent() && passwordEncoder.matches(login.getPassword(), user.get().getPassword())) {
//            // Check if the user role is 'DEVELOPER'
//            if ("DEVELOPER".equalsIgnoreCase(user.get().getRole())) {
//            	System.err.println("Hit 1");
//                Integer developerId = user.get().getDeveloper().getDeveloperId();
//
//                // Fetch tasks assigned to this developer
//                List<TaskManager> tasks = taskManagerService.getTasksForDeveloper(developerId);
//
//                // Print tasks to the console
//                System.out.println("Tasks for Developer ID " + developerId + ":");
//                for (TaskManager task : tasks) {
//                    System.out.println("Task ID: " + task.getTaskId() +
//                                       ", Subject: " + task.getSubject() +
//                                       ", Status: " + (task.getStatus() != null ? task.getStatus().getStatusName() : "N/A"));
//                }
//            }
//
//            return ResponseEntity.ok("Login successful");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }
    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody Login login) {
        var user = loginService.findByUsername(login.getUsername());
        if (user.isPresent() && passwordEncoder.matches(login.getPassword(), user.get().getPassword())) {
            String role = user.get().getRole().toUpperCase();

            switch (role) {
                case "DEVELOPER":
                	loginService.handleDeveloperLogin(user.get().getDeveloper().getDeveloperId());
                    break;

                case "ASSIGNER":
                    Integer assignerId = user.get().getUserId(); 
                    loginService.handleAssignerLogin(assignerId);
                    break;

                case "ADMIN":
                	loginService.handleAdminLogin();
                    break;

                default:
                    System.out.println("Unknown role: " + role);
            }

            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

   
    
}
