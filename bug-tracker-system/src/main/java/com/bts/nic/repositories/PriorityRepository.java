package com.bts.nic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bts.nic.modal.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Integer> {
    
	
}
