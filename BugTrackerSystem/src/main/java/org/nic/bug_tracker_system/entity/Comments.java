package org.nic.bug_tracker_system.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Comments {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ticketNo;
	
	private String commentedBy;
	
	private String comment;
	
	private LocalDateTime dateTime;
	
	private Boolean activeFlag;

}
