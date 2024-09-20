package com.bts.nic.service.impl;

import com.bts.nic.modal.Login;
import com.bts.nic.modal.TaskManager;
import com.bts.nic.repositories.LoginRepository;
import com.bts.nic.service.TaskManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired TaskManagerService taskManagerService;

    public Optional<Login> findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    public void saveUser(Login login) {
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        loginRepository.save(login);
    }
    
    public void handleDeveloperLogin(Integer developerId) {
        List<TaskManager> tasks = taskManagerService.getTasksForDeveloper(developerId);
       
       
    }

    public void handleAssignerLogin(Integer assignerId) {
        List<TaskManager> tasks = taskManagerService.getTasksForAssigner(assignerId);
        
    }

    public void handleAdminLogin() {
        List<TaskManager> tasks = taskManagerService.getAllTasks();
       
    }
}
