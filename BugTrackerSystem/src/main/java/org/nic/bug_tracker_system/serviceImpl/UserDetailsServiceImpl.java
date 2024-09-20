package org.nic.bug_tracker_system.serviceImpl;

import org.nic.bug_tracker_system.config.CustomUserDetails;
import org.nic.bug_tracker_system.entity.Staff;
import org.nic.bug_tracker_system.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StaffRepo staffRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Staff staff = staffRepo.findByUsername(username);
		
		if(staff == null) {
			throw new UsernameNotFoundException("User not found!!");
		}
		
		CustomUserDetails customUserDetails = new CustomUserDetails(staff);
		
		return customUserDetails;
	}

   
}