package com.bts.nic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bts.nic.modal.Severity;

@Repository
public interface SeverityRepository extends JpaRepository<Severity, Integer> {
}
