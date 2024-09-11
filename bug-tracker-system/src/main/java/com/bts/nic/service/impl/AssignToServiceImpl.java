package com.bts.nic.service.impl;

import com.bts.nic.modal.AssignTo;
import com.bts.nic.repositories.AssignToRepository;
import com.bts.nic.service.AssignToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignToServiceImpl implements AssignToService {

    @Autowired
    private AssignToRepository assignToRepository;

    @Override
    public AssignTo saveAssignTo(AssignTo assignTo) {
        return assignToRepository.save(assignTo);
    }

    @Override
    public AssignTo getAssignToById(Integer developerId) {
        Optional<AssignTo> assignTo = assignToRepository.findById(developerId);
        return assignTo.orElse(null);
    }

    @Override
    public List<AssignTo> getAllAssignTo() {
        return assignToRepository.findAll();
    }

    @Override
    public void deleteAssignTo(Integer developerId) {
        assignToRepository.deleteById(developerId);
    }
}
