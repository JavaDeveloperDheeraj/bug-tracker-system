package org.nic.bug_tracker_system.dto;

import java.time.LocalDateTime;

import org.nic.bug_tracker_system.enums.CategoryEnum;
import org.nic.bug_tracker_system.enums.PriorityEnum;
import org.nic.bug_tracker_system.enums.ReproducibilityEnum;
import org.nic.bug_tracker_system.enums.SeverityEnum;
import org.nic.bug_tracker_system.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDirectoryDTO {
	
    private StatusEnum status;
	
	private CategoryEnum category;

	private SeverityEnum severity;
	
	private ReproducibilityEnum reproducibility;
	
	private PriorityEnum priority;

	private String assignedTo;
	
	private String assignedBy;

	private String ticketDescription;
	
    private String ticketSubject;

    private String attachments;
    
    private LocalDateTime assignedTime;
	    
	private LocalDateTime completionDatetime;
	    
	private Long estimatedTime;
}
