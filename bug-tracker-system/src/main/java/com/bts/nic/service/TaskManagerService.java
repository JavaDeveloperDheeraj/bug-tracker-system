package com.bts.nic.service;

import com.bts.nic.modal.TaskManager;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface TaskManagerService {
    TaskManager saveTask(TaskManager task);
    TaskManager getTaskById(String taskId);
    List<TaskManager> getAllTasks();
    void deleteTask(String taskId);
    Optional<TaskManager> findTaskWithHighestId(String prefix);
    List<TaskManager> getTasksForDeveloper(Integer developerId); // New method
 // New methods for fetching tasks based on roles
    List<TaskManager> getTasksForAssigner(Integer assignerId);
}
