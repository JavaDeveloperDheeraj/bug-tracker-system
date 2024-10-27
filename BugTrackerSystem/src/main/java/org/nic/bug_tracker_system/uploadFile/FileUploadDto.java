package org.nic.bug_tracker_system.uploadFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import org.nic.bug_tracker_system.enums.Http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDto {
	

	private String[] names;
	private MultipartFile[] files;
	private String ticketNo;
	public final Map<String, MultipartFile> getObject() {
		Map<String, MultipartFile> finalFileDetails = new HashMap<>();
		if(files.length>0 && names.length>0) {
			for (int i = 0; i < files.length; i++) {
					finalFileDetails.put(names[i], files[i]);
				}
			}
		return finalFileDetails;
	}
	public String getRefId() {
		return ticketNo.replaceAll("/", AppConstants.REF_ID_MODIFIER);
	}
	
	public void resetFileUploadDto() {
		this.names=null;
		this.files=null;
	}

}
