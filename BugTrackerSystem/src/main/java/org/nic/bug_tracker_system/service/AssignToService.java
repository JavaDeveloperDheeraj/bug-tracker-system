package org.nic.bug_tracker_system.service;

import java.util.List;

import org.nic.bug_tracker_system.entity.AssignTo;

public interface AssignToService {
    AssignTo saveAssignTo(AssignTo assignTo);
    AssignTo getAssignToById(Integer developerId);
    List<AssignTo> getAllAssignTo();
    void deleteAssignTo(Integer developerId);
}
