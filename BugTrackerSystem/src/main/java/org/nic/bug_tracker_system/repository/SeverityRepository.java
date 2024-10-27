package org.nic.bug_tracker_system.repository;

import org.nic.bug_tracker_system.entity.Severity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeverityRepository extends JpaRepository<Severity, Integer> {
}
