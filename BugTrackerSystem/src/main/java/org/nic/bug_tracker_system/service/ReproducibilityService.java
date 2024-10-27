package org.nic.bug_tracker_system.service;

import java.util.List;

import org.nic.bug_tracker_system.entity.Reproducibility;

public interface ReproducibilityService {
    Reproducibility saveReproducibility(Reproducibility reproducibility);
    Reproducibility getReproducibilityById(Integer reproducibilityId);
    List<Reproducibility> getAllReproducibilities();
    void deleteReproducibility(Integer reproducibilityId);
}
