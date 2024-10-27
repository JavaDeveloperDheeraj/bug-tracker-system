package org.nic.bug_tracker_system.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.nic.bug_tracker_system.enums.CategoryEnum;
import org.nic.bug_tracker_system.enums.PriorityEnum;
import org.nic.bug_tracker_system.enums.ReproducibilityEnum;
import org.nic.bug_tracker_system.enums.SeverityEnum;
import org.nic.bug_tracker_system.enums.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import lombok.Data;

@Data
@Entity
public class TicketDirectory {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Id
	private String ticketNo="TASK"+id;
	
	@Enumerated(EnumType.STRING)
    private StatusEnum status;
	
	//@Enumerated(EnumType.STRING)
	//private CategoryEnum category;
	private String category;
	
	//@Enumerated(EnumType.STRING)
	//private SeverityEnum severity;
	private String severity;
	
	//@Enumerated(EnumType.STRING)
	//private ReproducibilityEnum reproducibility;
	private String reproducibility;
	
	//@Enumerated(EnumType.STRING)
	//private PriorityEnum priority;
	private String priority;

	private String assignedTo;
	
	private String assignedBy;

	private String ticketDescription;
	
    private String ticketSubject;

    
    private String attachments;
    
    private LocalDateTime assignedTime;
	    
	private LocalDateTime completionDatetime;
	    
	private Long estimatedTime;  

	private Boolean activeFlag=true;
	 @PostPersist
	    public void setTicketNo() {
	        this.ticketNo = "TASK" + this.id;
	    }
	
}
