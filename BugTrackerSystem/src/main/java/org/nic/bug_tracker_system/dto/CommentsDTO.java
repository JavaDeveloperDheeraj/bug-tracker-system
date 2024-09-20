package org.nic.bug_tracker_system.dto;

import lombok.Data;

@Data
public class CommentsDTO {
	
	private String ticketNo;
	
	private String commentedBy;
	
	private String comment;
}
