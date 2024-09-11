package com.bts.nic.service;

import com.bts.nic.modal.Severity;
import java.util.List;

public interface SeverityService {
    Severity saveSeverity(Severity severity);
    Severity getSeverityById(Integer severityId);
    List<Severity> getAllSeverities();
    void deleteSeverity(Integer severityId);
}
