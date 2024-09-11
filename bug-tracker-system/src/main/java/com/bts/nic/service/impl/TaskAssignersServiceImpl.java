package com.bts.nic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bts.nic.modal.TaskAssigners; 
import com.bts.nic.repositories.TaskAssignerRepository; 
import com.bts.nic.service.TaskAssignersService;

@Service
public class TaskAssignersServiceImpl implements TaskAssignersService {

    @Autowired
    private TaskAssignerRepository taskAssignerRepository; 

    @Override
    public TaskAssigners saveAssignTo(TaskAssigners taskAssigners) {
        return taskAssignerRepository.save(taskAssigners);
    }

    @Override
    public TaskAssigners getAssignToById(Integer assignerId) { 
        Optional<TaskAssigners> taskAssigners = taskAssignerRepository.findById(assignerId);
        return taskAssigners.orElse(null);
    }

    @Override
    public List<TaskAssigners> getAllAssignTo() { 
        return taskAssignerRepository.findAll();
    }

    @Override
    public void deleteAssignTo(Integer assignerId) { 
        taskAssignerRepository.deleteById(assignerId);
    }
}
