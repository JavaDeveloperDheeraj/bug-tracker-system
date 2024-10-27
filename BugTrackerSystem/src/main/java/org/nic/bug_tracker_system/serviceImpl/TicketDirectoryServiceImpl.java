package org.nic.bug_tracker_system.serviceImpl;

import org.nic.bug_tracker_system.entity.TicketDirectory;
import org.nic.bug_tracker_system.repository.TicketDirectoryRepo;
import org.nic.bug_tracker_system.service.TicketDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service

public class TicketDirectoryServiceImpl implements TicketDirectoryService{

	@Autowired
	TicketDirectoryRepo ticRepo;
	
//	@Override
//	public TicketDirectory convertToDto(String data) {
//		if(StringUtils.hasText(data)) {
//			ObjectMapper obj=  new ObjectMapper();
//			obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//			try {
//				//TicketDirectoryDTO tic=obj.readValue(data, TicketDirectoryDTO.class);
//				TicketDirectory ticEntity=obj.readValue(data, TicketDirectory.class);
//				return ticEntity;
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//				return null;
//			}
//			
//		}else throw new NullPointerException("Data can't be null or empty!");
//		
//	}
	 public TicketDirectory convertToDto(String data) {
	        if (StringUtils.hasText(data)) {
	            ObjectMapper obj = new ObjectMapper();
	            obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
	            try {
	                
	                JsonNode jsonNode = obj.readTree(data);
	                if (jsonNode.has("attachments")) {
	                    StringBuilder attachmentsBuilder = new StringBuilder();
	                    for (JsonNode attachmentNode : jsonNode.get("attachments")) {
	                        if (attachmentNode.has("fileName")) {
	                            attachmentsBuilder.append(attachmentNode.get("fileName").asText()).append(",");
	                        }
	                    }
	                   
	                    if (attachmentsBuilder.length() > 0) {
	                        attachmentsBuilder.setLength(attachmentsBuilder.length() - 1);
	                    }
	                   ((ObjectNode) jsonNode).put("attachments", attachmentsBuilder.toString());
	                }
	                TicketDirectory ticEntity = obj.treeToValue(jsonNode, TicketDirectory.class);
	                return ticEntity;
	            } catch (JsonProcessingException e) {
	                e.printStackTrace();
	                return null;
	            }

	        } else {
	            throw new NullPointerException("Data can't be null or empty!");
	        }
	    }

	@Override
	public String saveTicketDetails(TicketDirectory ticDir) {
		try {
		TicketDirectory savedData=ticRepo.save(ticDir);
		return savedData.getTicketNo()+" - Task created successfully.";
		}catch (Exception e) {
			return "Some error occured..";
		}
	}
	
	
	
}
