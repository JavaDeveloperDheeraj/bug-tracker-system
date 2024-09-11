package com.bts.nic.service.impl;

import com.bts.nic.modal.Severity;
import com.bts.nic.repositories.SeverityRepository;
import com.bts.nic.service.SeverityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeverityServiceImpl implements SeverityService {

    @Autowired
    private SeverityRepository severityRepository;

    @Override
    public Severity saveSeverity(Severity severity) {
        return severityRepository.save(severity);
    }

    @Override
    public Severity getSeverityById(Integer severityId) {
        Optional<Severity> severity = severityRepository.findById(severityId);
        return severity.orElse(null);
    }

    @Override
    public List<Severity> getAllSeverities() {
        return severityRepository.findAll();
    }

    @Override
    public void deleteSeverity(Integer severityId) {
        severityRepository.deleteById(severityId);
    }
}
