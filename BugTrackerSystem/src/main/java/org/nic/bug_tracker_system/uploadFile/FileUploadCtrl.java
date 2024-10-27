package org.nic.bug_tracker_system.uploadFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.nic.bug_tracker_system.enums.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class FileUploadCtrl {
	
	@Autowired FileUploadDetails fileUploadDetails;
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@Autowired
	private AttachmentContainer attachmentContainer;
	
	 @PostMapping("/upload-files")
	 public ResponseEntity<String> uploadFiles(@ModelAttribute FileUploadDto data){
		try { 
			Map<String, Object> finalDetails= fileUploadHelper.fileValidator(data.getObject());
			 if(!finalDetails.isEmpty()) {
				 if(finalDetails.containsKey("error")) {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((String)finalDetails.get("error"));
				 }
				 String newAttachments= fileUploadHelper.uploadFile(finalDetails);
				 attachmentContainer.setAttachment(newAttachments);
				 attachmentContainer.setTicketNo(data.getTicketNo().replaceAll(AppConstants.REF_ID_MODIFIER,"/"));
				 data.resetFileUploadDto();
				 return ResponseEntity.ok("File uploaded successfully");
			 }
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file is empty Try again");

		}catch (Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went worng! Try again");
		}
	 }
	 
//	 @GetMapping("/get-files/{filename}")
//	    public ResponseEntity<InputStreamResource> getFile(@PathVariable String filename) throws IOException {
//	        File file = new File(AppConstants.UPLOAD_DIR + "/" + filename);
//	        if (!file.exists()) {
//	          
//	        	 throw new org.nic.bug_tracker_system.uploadFile.FileNotFoundException("File not found");
//	        }
//	        Resource resource = new UrlResource(file.toURI());
//
//	        HttpHeaders httpHeaders = new HttpHeaders();
//			httpHeaders.add("Content-Disposition", "inline;file=lcwd.pdf");
//	        return ResponseEntity.ok()
//					.headers(httpHeaders)
//					.contentType(FileUploadHelper.getContentType(file))
//					.body(new InputStreamResource(resource.getInputStream()));
//	        
//	    }
	 
	 @GetMapping("/get-files/{filename}")
	 public ResponseEntity<?> getFile(@PathVariable String filename) throws IOException {
	     File file = new File(AppConstants.UPLOAD_DIR + "/" + filename);

	     // If file doesn't exist, return an HTML response with a 404 image
	     if (!file.exists()) {
	    	 String errorMessage = "<html><body style='display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0;'>"
	    		        + "<div style='text-align: center; margin: 20px;'>"
	    		        + "<img src='/BTS/images/filenotfound404.jpg' alt='404 error' style='max-width: 100%; height: auto;'/>"
	    		        + "</div>"
	    		        + "</body></html>";

	         return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                 .contentType(MediaType.TEXT_HTML)
	                 .body(errorMessage);  // Returning HTML as String
	     }

	     Resource resource = new UrlResource(file.toURI());
	     HttpHeaders httpHeaders = new HttpHeaders();
	     httpHeaders.add("Content-Disposition", "inline;file=" + filename);

	     return ResponseEntity.ok()
	             .headers(httpHeaders)
	             .contentType(FileUploadHelper.getContentType(file))
	             .body(new InputStreamResource(resource.getInputStream()));  // Returning InputStreamResource for the file
	 }

	 
	 @PostMapping("/deleteFileFromMemory")
	 public ResponseEntity<String> DeleteFiles(@RequestParam String attachmentName){
		 try {
			 JsonObject fileDetails= new Gson().fromJson(attachmentContainer.getAttachment(), JsonObject.class);
			 if(fileDetails.has(attachmentName)) {
				 String fileName=fileDetails.get(attachmentName).getAsString();
				 File file= new File(AppConstants.UPLOAD_DIR+File.separator+fileName);
				 if(file.exists()) {
					 file.delete();
					 fileDetails.remove(attachmentName);
					 attachmentContainer.setAttachment(fileDetails.toString());
					 return ResponseEntity.status(HttpStatus.OK).body("File Deleted Successfully");
				 }else {
					 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No File Found!");
				 }
				 
			 }else {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Attachment Found!");
			 }
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(414).body("Some Error Occured");
		}
	 }
	 
	 @PostMapping("/upload-single-file")
	 public ResponseEntity<String> uploadSingleFile(@RequestPart("file") MultipartFile file, @RequestParam String name, @RequestParam String ticketNo){
		 try {
			 
			 Map<String, String> validMap = fileUploadHelper.validateFile(name, file, attachmentContainer.getAttachment(), ticketNo);
			 if(validMap.containsKey("error")) {
				 return ResponseEntity.status(Http.EXCEPTION).body(validMap.get("error"));
			 }
			 
			 String attachmentString =  fileUploadHelper.upload(file, name, ticketNo, attachmentContainer.getAttachment());
			 System.out.println(attachmentString);
			 if(attachmentContainer.getTicketNo() == null) {
				 attachmentContainer.setTicketNo(ticketNo);
			 }
			 attachmentContainer.setAttachment(attachmentString);
			 
			 return ResponseEntity.status(Http.OK).body("File Uploaded Successfully");
					
		 } catch (Exception e) {
			 e.printStackTrace();
			 return ResponseEntity.status(Http.EXCEPTION).body("Some Error Occoured!");
		 }
		 
	 }
	 
	 @GetMapping("/clear-attachmemt-container")
	 public ResponseEntity<String> clearAttachmetContainer(){
		 fileUploadHelper.deleteUnusedAttachments(attachmentContainer.getAttachment());
		 attachmentContainer.resetAttachmentContainer();
		 return ResponseEntity.ok("container reset success");
	 }
	 
}
