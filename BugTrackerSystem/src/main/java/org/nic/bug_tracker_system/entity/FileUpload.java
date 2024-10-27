package org.nic.bug_tracker_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Table (name="file_upload")
@Entity
public class FileUpload {
	
	@Id
	@Column(name="file_details")
	private String fileDetails;

	
}
