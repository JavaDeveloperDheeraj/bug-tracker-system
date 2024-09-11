package com.bts.nic.service;

import com.bts.nic.modal.TaskAssigners; 
import java.util.List;

public interface TaskAssignersService {
    TaskAssigners saveAssignTo(TaskAssigners taskAssigners); 
    TaskAssigners getAssignToById(Integer assignerId);
    List<TaskAssigners> getAllAssignTo(); 
    void deleteAssignTo(Integer assignerId); 
}
