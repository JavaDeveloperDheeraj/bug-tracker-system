package com.bts.nic.service;

import com.bts.nic.modal.Priority;
import java.util.List;

public interface PriorityService {
    Priority savePriority(Priority priority);
    Priority getPriorityById(Integer priorityId);
    List<Priority> getAllPriorities();
    void deletePriority(Integer priorityId);
}
