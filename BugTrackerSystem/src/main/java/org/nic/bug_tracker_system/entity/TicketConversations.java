package org.nic.bug_tracker_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@IdClass(compositeConversationId.class)

public class TicketConversations {
	
//	 @Id
//	 @ManyToOne
//	 @JoinColumn(name = "ticket_no", referencedColumnName = "ticketNo", nullable = false)
//	 private TicketDirectory ticketDirectory;
	
	@Id
	private Integer conversationNo;
	
	@Id
	private String ticketNo;

	private String conversationDesc;
	
	 private String attachments;
}
