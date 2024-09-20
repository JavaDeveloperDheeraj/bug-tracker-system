package org.nic.bug_tracker_system.controller;

import java.util.List;

import org.nic.bug_tracker_system.entity.TicketDirectory;
import org.nic.bug_tracker_system.repository.TicketDirectoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
	
	@Autowired
	TicketDirectoryRepo ticRepo;
	
	
		@PostMapping("/app/login")
	    public ModelAndView login(Authentication authentication) {
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        String role = authentication.getAuthorities().iterator().next().getAuthority();
	        String username = userDetails.getUsername();  // Get authenticated username

	        // Redirect based on role and include the username as a parameter
	        if (role.equals("ROLE_ADMIN")) {
	            return new ModelAndView("redirect:/all");
	        } else if (role.equals("ROLE_DEVELOPER")) {
	            return new ModelAndView("redirect:/developer?username=" + username);
	        } else if (role.equals("ROLE_ASSIGNER")) {
	            return new ModelAndView("redirect:/assigner?username=" + username);
	        } else {
	            return new ModelAndView("redirect:/error"); // In case of unknown role
	        }
	    }


	//its me
	@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<TicketDirectory> getAllTickets() {
		 List<TicketDirectory> tickets = ticRepo.findAll();

		    System.err.println("Tickets: " + tickets);

		    return tickets;
    }


	
//    @PreAuthorize("hasRole('DEVELOPER')")
//    @GetMapping("/developer")
//    public List<TicketDirectory> getDeveloperTickets(Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String username = userDetails.getUsername();
//        return ticRepo.findByAssignedTo(username);
//    }
    
    @PreAuthorize("hasRole('DEVELOPER')")
    @GetMapping("/developer")
    public List<TicketDirectory> getDeveloperTickets(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        
        List<TicketDirectory> tickets = ticRepo.findByAssignedTo(username);
        
        System.out.println("Developer Username: " + username);
        System.out.println("Assigned Tickets: " + tickets);
        
        return tickets;
    }


    @PreAuthorize("hasRole('ASSIGNER')")
    @GetMapping("/assigner")
    public List<TicketDirectory> getAssignerTickets(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return ticRepo.findByAssignedBy(username);
    }

    

}
