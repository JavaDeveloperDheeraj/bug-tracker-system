package org.nic.bug_tracker_system.uploadFile;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.impl.SizeException;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;


@Component
public class FileUploadHelper {

	//public final String UPLOAD_DIR = "D:\\sts workspace nov22\\BiharGrievancePortal\\src\\main\\resources\\static\\image";
	//E:\STS MAR 2023 Workspace\BiharGrievancePortal\src\main\resources\static\image
	
	public String uploadFile(Map<String, Object> fileDetails) throws Exception {

	    // A JSON object to hold file mappings (original file name -> original file name)
	    JSONObject fileDir = new JSONObject();

	    try {
	        for (Map.Entry<String, Object> fileDetail : fileDetails.entrySet()) {
	            MultipartFile multipartFile = (MultipartFile) fileDetail.getValue();

	            if (!multipartFile.isEmpty()) {
	                // Get the original file name directly from the key (without any modifications)
	                String originalFileName = fileDetail.getKey();

	                // Save the file using the original file name in the desired directory
	                Files.copy(multipartFile.getInputStream(),
	                           Paths.get(AppConstants.UPLOAD_DIR + File.separator + originalFileName),
	                           StandardCopyOption.REPLACE_EXISTING);

	                // Store the mapping of original file name in the JSON object
	                fileDir.put(originalFileName, originalFileName); // Same original file name
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception(e.getMessage());
	    }

	    return fileDir.toJSONString();
	}

	
	
// new file validator withoout ticket no:
	public Map<String, Object> fileValidator(Map<String, MultipartFile> fileDetails) {
	    Map<String, Object> finalDetails = new HashMap<>();
	    String fileName;

	    for (Map.Entry<String, MultipartFile> fileDetail : fileDetails.entrySet()) {
	        MultipartFile file = fileDetail.getValue();
	        String fileType = file.getContentType();
	        long fileSize = file.getSize();

	        try {
	            // File type validation
	            if (!fileType.equals("application/pdf") && !fileType.equals("image/jpeg") && !fileType.equals("image/png")) {
	                finalDetails.clear();
	                finalDetails.put("error", fileDetail.getKey() + " File type must be PDF or image (JPG, PNG)");
	                return finalDetails;
	            }

	            // File size validation
	            if (fileType.equals("application/pdf") && (fileSize < 50000 || fileSize > 500000)) {
	                finalDetails.clear();
	                finalDetails.put("error", fileDetail.getKey() + " File size for PDF files must be between 50 KB and 500 KB");
	                return finalDetails;
	            }

	            if ((fileType.equals("image/jpeg") || fileType.equals("image/png")) && (fileSize < 20000 || fileSize > 100000)) {
	                finalDetails.clear();
	                finalDetails.put("error", fileDetail.getKey() + " File size for image files must be between 20 KB and 100 KB");
	                return finalDetails;
	            }

	            // Use the original file name without any changes
	            fileName = file.getOriginalFilename();
	            finalDetails.put(fileDetail.getKey(), file); // No need for @@@@ or anything else

	        } catch (Exception ex) {
	            finalDetails.clear();
	            finalDetails.put("error", "An error occurred while processing the file.");
	            return finalDetails;
	        }
	    }

	    return finalDetails;
	}

//	public String appendFileInList(JSONObject docList, String newDoc) {
//		 JSONObject newDocList =new JSONObject(newDoc); 
//		if (!docList.isEmpty() &&!newDocList.isEmpty()) {
//			 for (String keyStr : newDocList.keySet()) {
//				 docList.put(keyStr,newDocList.get(keyStr));
//		      } 	
//			 return new Gson().toJson(docList);
//		}
//		return newDoc;
//	}
	
	
	public static MediaType getContentType(File file) {
		MediaType content=null;
		
		String fileName=file.getName();
		String fileType=fileName.substring(fileName.lastIndexOf('.'),fileName.length());
		if(fileType.equalsIgnoreCase(".pdf"))content=MediaType.APPLICATION_PDF;
		else if(fileType.equalsIgnoreCase(".png"))content=MediaType.IMAGE_PNG;
		else if(fileType.equalsIgnoreCase(".jpeg")||fileType.equalsIgnoreCase(".jpg"))content=MediaType.IMAGE_JPEG;
				return content;
		
	}
	
	
	/**
	 * New Code For Single File Upload
	 */
	public Map<String, String> validateFile(String name, MultipartFile file, String attachmentStr, String ticketNo) throws Exception{
		Map<String, String> res = new HashMap<>();
		String fileType = file.getContentType();
        long fileSize = file.getSize();
        
        if(isAlreadyUploaded(ticketNo, file.getOriginalFilename(), attachmentStr)) {
        	res.put("error", "File "+file.getOriginalFilename()+" is Already Uploaded");
        }
        
        if(name == null || name.equals("undefined") || name.isEmpty()) {
        	res.put("error", "Please enter the file name");
        }
        
        if (!fileType.equals("application/pdf") && !fileType.equals("image/jpeg") && !fileType.equals("image/png")) {
        	res.put("error", name + " File type must be PDF or image (JPG, PNG)");
        }

        if (fileType.equals("application/pdf") && (fileSize < 50000 || fileSize > 500000)) {
            res.put("error", name + " File size for PDF files must be between 50 KB and 500 KB");
        } 
        else if ((fileType.equals("image/jpeg") || fileType.equals("image/png")) && (fileSize < 20000 || fileSize > 100000)) {
            res.put("error", name + " File size for image files must be between 20 KB and 100 KB");
        }
            
        return res;
	}
	
	private String generateFileName(String refId, String fileName) {
		return refId.replaceAll("/", AppConstants.REF_ID_MODIFIER) + AppConstants.FILE_NAME_SEPARATOR + fileName;
	}

	public String upload(MultipartFile file, String name, String refId, String attachmentStr) throws Exception {
		//Map<String,String> res = new HashMap<>();
		try {
			if(file.isEmpty()) 
				throw new Exception("Error : Empty File !");
				
			final String fileName = generateFileName(refId, file.getOriginalFilename());
			
			File directory = new File(AppConstants.UPLOAD_DIR);
            if (!directory.exists()){
            	directory.canWrite();
                directory.mkdirs();
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path filePath = Paths.get(AppConstants.UPLOAD_DIR).resolve(fileName);
			
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			
			//res.put(name, fileName);
			return getAttachmentString(attachmentStr, name, fileName);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String getAttachmentString(String attachmentStr, String name, String fileName) {
		
		if(attachmentStr!=null && attachmentStr.equals("{}")) attachmentStr = null;
		
		StringBuilder sb = new StringBuilder();
		if(attachmentStr != null) {
			sb.append(attachmentStr);
			sb.setCharAt(sb.length()-1, ',');
			sb.append("\"" +name+"\"" +":"+"\"" +fileName+"\"" +"}");
		}
		else {
			sb.append("{"+"\"" + name +"\"" +":"+ "\"" +fileName +"\"" +"}");
		}
		return sb.toString();
	}

	public boolean isAlreadyUploaded(String refId, String fileName, String attachmentString) {
		if(attachmentString == null) return false;
		try {
			Gson gson = new Gson();
			JSONObject json = gson.fromJson(attachmentString, JSONObject.class);
			return json.containsValue(generateFileName(refId, fileName));
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteUnusedAttachments(String attachmentStr) {
		if(!StringUtils.hasText(attachmentStr)) return;
		com.google.gson.JsonObject json = new Gson().fromJson(attachmentStr, com.google.gson.JsonObject.class);
		for(String key : json.keySet()) {
			if(json.has(key)) {
				 String fileName= json.get(key).getAsString();
				 File file= new File(AppConstants.UPLOAD_DIR+File.separator+fileName);
				 if(file.exists()) {
					 file.delete();
				 } 
			 }
		}
	}

	
//public static void main(String[] args) {
	//String str="{\"acknowledgement\":\"BICCO#2021#3912879-1679773228-DOC3-imresizer-1679681548036.jpg-imresizer-1679681548036.jpg\",\"land receipt 2\":\"BICCO#2021#3912879-1679773228-DOC1-eng sign 6 point 46 kb.jpg-eng sign 6 point 46 kb.jpg\",\"land receipt1\":\"BICCO#2021#3912879-1679773228-DOC2-imresizer-1679681548036.jpg-imresizer-1679681548036.jpg\"}";
//	 org.json.JSONObject jb =new  org.json.JSONObject(str); 
//	 for (String keyStr : jb.keySet()) {
//		 System.out.println(jb.get(keyStr));
//	 }
	//new FileUploadHelper().deleteUnusedAttachments(str);
	// System.out.println("Bharat".substring(3,"Bharat".length()));
//}
}
