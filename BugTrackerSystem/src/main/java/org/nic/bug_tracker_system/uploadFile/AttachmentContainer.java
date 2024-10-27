package org.nic.bug_tracker_system.uploadFile;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentContainer {
	
	private String ticketNo;
	private String attachment;
	
	public void resetAttachmentContainer() {
		this.ticketNo=null;
		this.attachment= null;
	}
	

}
