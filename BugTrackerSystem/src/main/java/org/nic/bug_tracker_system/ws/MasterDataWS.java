package org.nic.bug_tracker_system.ws;


import java.util.List;

import org.nic.bug_tracker_system.entity.AssignTo;
import org.nic.bug_tracker_system.entity.Category;
import org.nic.bug_tracker_system.entity.Priority;
import org.nic.bug_tracker_system.entity.Reproducibility;
import org.nic.bug_tracker_system.entity.Severity;
import org.nic.bug_tracker_system.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/default")
public class MasterDataWS {
	
	

	@Autowired
	AppConfigService appServices;
	
	@Autowired
	ApplicationContext appContext;
	
	@GetMapping("/categories")
	public List<?> categories() {
		try {
			
			return (List<Category>)appContext.getBean("categories");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/priorities")
	public List<?> priorities() {
		try {
			return (List<Priority>) appContext.getBean("priorities");
		}catch (Exception e) {
			e.printStackTrace();
			return null;			
		}
    }
	
	@GetMapping("/reproducibilites")
	public List<?> reproducibilites() {
		try {
			return (List<Reproducibility>) appContext.getBean("reproducibilites");
		}catch (Exception e) {
			e.printStackTrace();
			return null;			
		}
    }
	
	@GetMapping("/severities")
	public List<?> severities() {
		try {
			return (List<Severity>) appContext.getBean("severities");
		}catch (Exception e) {
			e.printStackTrace();
			return null;			
		}
    }
	
	@GetMapping("/assignTo")
	public List<?> assignTo(){
		try {
			return (List<AssignTo>) appContext.getBean("assignTo");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
