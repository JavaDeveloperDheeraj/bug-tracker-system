package com.bts.nic.service;

import com.bts.nic.modal.AssignTo;
import java.util.List;

public interface AssignToService {
    AssignTo saveAssignTo(AssignTo assignTo);
    AssignTo getAssignToById(Integer developerId);
    List<AssignTo> getAllAssignTo();
    void deleteAssignTo(Integer developerId);
}
