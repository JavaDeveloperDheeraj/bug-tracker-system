package com.bts.nic.service.impl;

import com.bts.nic.modal.Priority;
import com.bts.nic.repositories.PriorityRepository;
import com.bts.nic.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    private PriorityRepository priorityRepository;

    @Override
    public Priority savePriority(Priority priority) {
        return priorityRepository.save(priority);
    }

    @Override
    public Priority getPriorityById(Integer priorityId) {
        Optional<Priority> priority = priorityRepository.findById(priorityId);
        return priority.orElse(null);
    }

    @Override
    public List<Priority> getAllPriorities() {
        return priorityRepository.findAll();
    }

    @Override
    public void deletePriority(Integer priorityId) {
        priorityRepository.deleteById(priorityId);
    }
}
