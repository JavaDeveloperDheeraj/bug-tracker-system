package org.nic.bug_tracker_system.service;
import java.util.List;

import org.nic.bug_tracker_system.entity.Priority;

public interface PriorityService {
    Priority savePriority(Priority priority);
    Priority getPriorityById(Integer priorityId);
    List<Priority> getAllPriorities();
    void deletePriority(Integer priorityId);
}
