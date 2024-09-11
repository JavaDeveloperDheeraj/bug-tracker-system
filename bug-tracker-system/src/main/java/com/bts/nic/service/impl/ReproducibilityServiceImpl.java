package com.bts.nic.service.impl;

import com.bts.nic.modal.Reproducibility;
import com.bts.nic.repositories.ReproducibilityRepository;
import com.bts.nic.service.ReproducibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReproducibilityServiceImpl implements ReproducibilityService {

    @Autowired
    private ReproducibilityRepository reproducibilityRepository;

    @Override
    public Reproducibility saveReproducibility(Reproducibility reproducibility) {
        return reproducibilityRepository.save(reproducibility);
    }

    @Override
    public Reproducibility getReproducibilityById(Integer reproducibilityId) {
        Optional<Reproducibility> reproducibility = reproducibilityRepository.findById(reproducibilityId);
        return reproducibility.orElse(null);
    }

    @Override
    public List<Reproducibility> getAllReproducibilities() {
        return reproducibilityRepository.findAll();
    }

    @Override
    public void deleteReproducibility(Integer reproducibilityId) {
        reproducibilityRepository.deleteById(reproducibilityId);
    }
}
