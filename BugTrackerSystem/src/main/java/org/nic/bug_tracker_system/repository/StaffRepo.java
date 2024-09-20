package org.nic.bug_tracker_system.repository;

import java.util.Optional;

import org.nic.bug_tracker_system.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepo extends JpaRepository<Staff, Long> {
	

    //Optional<Staff> findByUsername(String username);
	@Query("SELECT s FROM Staff s WHERE s.username = :username")
	Staff findByUsername(@Param("username") String username);

}

