package org.nic.bug_tracker_system.entity;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class compositeConversationId implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String ticketNo;
	private Integer conversationNo;

}
