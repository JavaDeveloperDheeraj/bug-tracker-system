package com.bts.nic.service.impl;

import com.bts.nic.modal.Status;
import com.bts.nic.repositories.StatusRepository;
import com.bts.nic.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status getStatusById(Integer statusId) {
        Optional<Status> status = statusRepository.findById(statusId);
        return status.orElse(null);
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public void deleteStatus(Integer statusId) {
        statusRepository.deleteById(statusId);
    }
}
