package org.nic.bug_tracker_system.controller;

import java.util.HashMap;
import java.util.Map;

import org.nic.bug_tracker_system.entity.TicketDirectory;
import org.nic.bug_tracker_system.service.TicketDirectoryService;
import org.nic.bug_tracker_system.uploadFile.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
//@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
@RequestMapping("/api/ticket")
public class TicketDirectoryCtrl {
	@Autowired
	TicketDirectoryService ticService;
	@Autowired
	FileUploadHelper fileUploadHelper;


	@PostMapping
	public ResponseEntity<String> saveTicketDetails(@RequestParam("data") String data,
	                                                @RequestParam(value = "files", required = false) MultipartFile[] files) {
	    String res = "";
	    try {
	       
	        TicketDirectory ticDto = ticService.convertToDto(data);

	        
	        if (files != null && files.length > 0) {
	            StringBuilder attachments = new StringBuilder();
	            Map<String, MultipartFile> fileDetails = new HashMap<>();

	            for (MultipartFile file : files) {
	                String uniqueFileName = UUID.randomUUID().toString().substring(0, 4) + "_" + file.getOriginalFilename();
	                
	                if (attachments.length() > 0) {
	                    attachments.append(",");
	                }
	                attachments.append(uniqueFileName);

	                fileDetails.put(uniqueFileName, file);
	            }
	            ticDto.setAttachments(attachments.toString());

	            Map<String, Object> validatedFiles = fileUploadHelper.fileValidator(fileDetails);

	            if (!validatedFiles.isEmpty() && !validatedFiles.containsKey("error")) {
	                String uploadedFiles = fileUploadHelper.uploadFile(validatedFiles); 
	                res = ticService.saveTicketDetails(ticDto); 
	            } else {
	             
	                String errorMessage = validatedFiles.containsKey("error") ? validatedFiles.get("error").toString() : "Unknown validation error";
	                System.err.println("File validation failed: " + errorMessage); 
	                return new ResponseEntity<>("File validation failed: " + errorMessage, HttpStatus.BAD_REQUEST); 
	            }
	        } else {
	            
	            res = ticService.saveTicketDetails(ticDto);
	        }

	       
	        return new ResponseEntity<>(res, HttpStatus.CREATED);
	    } catch (Exception e) {
	      
	        System.err.println("Error occurred while saving ticket details: " + e.getMessage());
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Return error response
	    }
	}


}
