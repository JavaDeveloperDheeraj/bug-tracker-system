package com.bts.nic.service.impl;

import com.bts.nic.modal.AssignTo;
import com.bts.nic.modal.TaskAssigners;
import com.bts.nic.modal.TaskManager;
import com.bts.nic.repositories.TaskManagerRepository;
import com.bts.nic.service.TaskManagerService;
import com.bts.nic.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    private TaskManagerRepository taskManagerRepository;
    
   
   @Autowired
   TaskAssigners taskasr;
    
   @Autowired
   AssignTo asignto;
    

    @Override
    public TaskManager saveTask(TaskManager task) {
        validateTask(task); // Validate task
        
        // Set the assigned time if not already set
        task.setAssignedTime(Optional.ofNullable(task.getAssignedTime()).orElse(LocalDateTime.now()));
        task.setTaskId(generateTaskId()); // Generate and set Task ID
        
        return taskManagerRepository.save(task);
    }

    @Override
    public TaskManager getTaskById(String taskId) {
        return taskManagerRepository.findById(taskId).orElse(null);
    }

    @Override
    public List<TaskManager> getAllTasks() {
        return taskManagerRepository.findAll();
    }

    @Override
    public void deleteTask(String taskId) {
        taskManagerRepository.deleteById(taskId);
    }

    @Override
    public Optional<TaskManager> findTaskWithHighestId(String prefix) {
        return taskManagerRepository.findTopByTaskIdStartingWithOrderByTaskIdDesc(prefix);
    }
    
    public List<TaskManager> getTasksForAssigner(Integer assignerId) {
    	 
    	taskasr.setAssingerId(assignerId);
        return taskManagerRepository.findBytaskAssigners(taskasr);
    }

    public List<TaskManager> getTasksForDeveloper(Integer developerId) {
    	
    	asignto.setDeveloperId(developerId);
        return taskManagerRepository.findByassignTo(asignto);
    }

    
    private void validateTask(TaskManager task) {
        // Validation logic for TaskManager
        if (task.getCategory() == null || task.getSeverity() == null ||
            task.getReproducibility() == null || task.getPriority() == null ||
            task.getAssignTo() == null || task.getStatus() == null || task.getTaskAssigners() == null) {
            throw new ValidationException("All required fields must not be null.");
        }

        if (task.getTaskAssigners() == null) {
            throw new ValidationException("Task Assigner must not be null.");
        }

        if (task.getTaskAssigners().getAssingerId() == null) {
            throw new ValidationException("Assigner ID must not be null.");
        }

        if (task.getTaskAssigners().getAssingerId() <= 0) {
            throw new ValidationException("Assigner ID must be a positive integer.");
        }

        if (task.getSubject() == null || task.getSubject().trim().isEmpty()) {
            throw new ValidationException("Subject must not be empty.");
        }

        if (task.getSubjectDetails() == null || task.getSubjectDetails().trim().isEmpty()) {
            throw new ValidationException("Subject details must not be empty.");
        }

        if (task.getAttachmentPath() == null || task.getAttachmentPath().trim().isEmpty()) {
            throw new ValidationException("Attachment path must not be empty.");
        }

        if (task.getEstimatedTime() == null || task.getEstimatedTime() <= 0) {
            throw new ValidationException("Estimated time must be a positive integer.");
        }

        if (task.getActualTime() != null && task.getActualTime() < 0) {
            throw new ValidationException("Actual time must be a non-negative integer.");
        }
    }


    private String generateTaskId() {
        // Generate task ID in the format "TasYYYYMMDD0XX"
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String prefix = "Tas" + currentDate + "0";

        // Fetch the latest task with a taskId starting with the current prefix
        int incrementNumber = findTaskWithHighestId(prefix)
                .map(task -> Integer.parseInt(task.getTaskId().substring(prefix.length())) + 1)
                .orElse(1);  // Default to 1 if no task exists for the current date

        return prefix + String.format("%02d", incrementNumber);
    }
    
    // Fetch tasks by developer ID
//    public List<TaskManager> getTasksForDeveloper(Integer developerId) {
//        return taskManagerRepository.findByAssignToDeveloperId(developerId);
//    }
}
