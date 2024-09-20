package org.nic.bug_tracker_system.serviceImpl;

import java.util.List;

import org.nic.bug_tracker_system.entity.TicketDirectory;
import org.nic.bug_tracker_system.repository.TicketDirectoryRepo;
import org.nic.bug_tracker_system.service.TicketDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service

public class TicketDirectoryServiceImpl implements TicketDirectoryService{

	@Autowired
	TicketDirectoryRepo ticRepo;
	
	@Override
	public TicketDirectory convertToDto(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.hasText(data)) {
			ObjectMapper obj=  new ObjectMapper();
			obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			try {
				//TicketDirectoryDTO tic=obj.readValue(data, TicketDirectoryDTO.class);
				TicketDirectory ticEntity=obj.readValue(data, TicketDirectory.class);
				return ticEntity;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}else throw new NullPointerException("Data can't be null or empty!");
		
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
