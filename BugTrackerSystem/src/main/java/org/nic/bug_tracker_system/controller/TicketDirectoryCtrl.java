package org.nic.bug_tracker_system.controller;

import org.nic.bug_tracker_system.entity.TicketDirectory;
import org.nic.bug_tracker_system.service.TicketDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
@RequestMapping("/api/ticket")
public class TicketDirectoryCtrl {
	@Autowired
	TicketDirectoryService ticService;

	@PostMapping
	public ResponseEntity<String> saveTicketDetails(@RequestParam("data") String data,
			@RequestParam("files") MultipartFile[] files) {
		try {
			TicketDirectory ticDto = ticService.convertToDto(data);
			String res = ticService.saveTicketDetails(ticDto);
			return new ResponseEntity<String>(res, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
