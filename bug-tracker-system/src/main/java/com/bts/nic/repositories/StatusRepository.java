package com.bts.nic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bts.nic.modal.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    
}
