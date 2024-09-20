package org.nic.bug_tracker_system.service;

import org.nic.bug_tracker_system.entity.TicketDirectory;
import org.springframework.stereotype.Service;

@Service
public interface TicketDirectoryService {

	TicketDirectory convertToDto(String data);

	String saveTicketDetails(TicketDirectory ticDto);

}
