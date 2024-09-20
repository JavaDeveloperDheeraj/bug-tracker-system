package org.nic.bug_tracker_system.repository;

import java.util.List;

import org.nic.bug_tracker_system.entity.TicketDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDirectoryRepo extends JpaRepository< TicketDirectory,Long> {

	List<TicketDirectory> findByAssignedTo(String username);

	List<TicketDirectory> findByAssignedBy(String username);

}
