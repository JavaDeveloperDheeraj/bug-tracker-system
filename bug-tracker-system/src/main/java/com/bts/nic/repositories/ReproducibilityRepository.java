package com.bts.nic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bts.nic.modal.Reproducibility;

@Repository
public interface ReproducibilityRepository extends JpaRepository<Reproducibility, Integer> {
    
	
}
