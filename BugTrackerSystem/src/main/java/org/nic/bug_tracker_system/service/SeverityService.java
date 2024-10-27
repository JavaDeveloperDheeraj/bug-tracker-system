package org.nic.bug_tracker_system.service;
import java.util.List;

import org.nic.bug_tracker_system.entity.Severity;

public interface SeverityService {
    Severity saveSeverity(Severity severity);
    Severity getSeverityById(Integer severityId);
    List<Severity> getAllSeverities();
    void deleteSeverity(Integer severityId);
}
