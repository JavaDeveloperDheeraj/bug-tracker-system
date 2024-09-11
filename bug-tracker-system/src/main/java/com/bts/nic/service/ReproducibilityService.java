package com.bts.nic.service;

import com.bts.nic.modal.Reproducibility;
import java.util.List;

public interface ReproducibilityService {
    Reproducibility saveReproducibility(Reproducibility reproducibility);
    Reproducibility getReproducibilityById(Integer reproducibilityId);
    List<Reproducibility> getAllReproducibilities();
    void deleteReproducibility(Integer reproducibilityId);
}
