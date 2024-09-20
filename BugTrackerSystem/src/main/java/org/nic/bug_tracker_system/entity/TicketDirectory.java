package org.nic.bug_tracker_system.entity;

import java.time.LocalDateTime;

import org.nic.bug_tracker_system.enums.CategoryEnum;
import org.nic.bug_tracker_system.enums.PriorityEnum;
import org.nic.bug_tracker_system.enums.ReproducibilityEnum;
import org.nic.bug_tracker_system.enums.SeverityEnum;
import org.nic.bug_tracker_system.enums.StatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TicketDirectory {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ticketNo="TASK"+id;
	
	@Enumerated(EnumType.STRING)
    private StatusEnum status;
	
	@Enumerated(EnumType.STRING)
	private CategoryEnum category;
	
	@Enumerated(EnumType.STRING)
	private SeverityEnum severity;
	
	@Enumerated(EnumType.STRING)
	private ReproducibilityEnum reproducibility;
	
	@Enumerated(EnumType.STRING)
	private PriorityEnum priority;

	private String assignedTo;
	
	private String assignedBy;

	private String ticketDescription;
	
    private String ticketSubject;

    private String attachments;
    
    private LocalDateTime assignedTime;
	    
	private LocalDateTime completionDatetime;
	    
	private Long estimatedTime;  

	private Boolean activeFlag=true;
}
