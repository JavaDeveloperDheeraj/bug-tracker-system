package org.nic.bug_tracker_system.serviceImpl;

import java.util.List;

import org.nic.bug_tracker_system.repository.AssignToRepository;
import org.nic.bug_tracker_system.repository.CategoryRepository;
import org.nic.bug_tracker_system.repository.PriorityRepository;
import org.nic.bug_tracker_system.repository.ReproducibilityRepository;
import org.nic.bug_tracker_system.repository.SeverityRepository;
import org.nic.bug_tracker_system.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AppConfigServiceImpl implements AppConfigService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7216241466478300560L;
	
	
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	PriorityRepository priorityRepo;
	
	@Autowired
	SeverityRepository severityRepo;
	@Autowired
	ReproducibilityRepository reproducibilityRepo;
	
	@Autowired
	AssignToRepository assignToRepo;
	
	
	
	@Override
	public List<?> categories() {
		return categoryRepo.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
	}
	
	@Override
	public List<?> priorities() {
		return priorityRepo.findAll(Sort.by(Sort.Direction.ASC, "priorityName"));
	}


	@Override
	public List<?> severities() {
		return severityRepo.findAll(Sort.by(Sort.Direction.ASC, "severityName"));
	}
	
	@Override
	public List<?> reproducibilites() {
		return reproducibilityRepo.findAll(Sort.by(Sort.Direction.ASC, "reproducibilityName"));
	}

	public List<?> assignTo() {
		return assignToRepo.findAll(Sort.by(Sort.Direction.ASC,"developerName"));
	}

}
