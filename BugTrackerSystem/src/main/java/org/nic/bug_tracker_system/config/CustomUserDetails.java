package org.nic.bug_tracker_system.config;

import java.util.Collection;
import java.util.List;

import org.nic.bug_tracker_system.entity.Staff;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private Staff staff;

    public CustomUserDetails(Staff staff) {
        super();
        this.staff = staff;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(staff.getRole());
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return staff.getPassword();
    }

    public String getMobile() {
    	return staff.getMobile();
    }
    
    public String getName() {
    	return staff.getName();
    }
    
    public String getRole() {
    	return staff.getRole();
    }
    @Override
    public String getUsername() {
        return staff.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Adjust if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Adjust if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Adjust if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Adjust if needed
    }

    // Override toString to log actual details
    @Override
    public String toString() {
        return "Staff(id=" + staff.getId() + 
               ", username=" + staff.getUsername() + 
               ", role=" + staff.getRole() + 
               ", password=" + staff.getPassword() + 
               ", name=" + staff.getName() + 
               ", designation=" + staff.getDesignation() + 
               ", mobile=" + staff.getMobile() + 
               ", createdDatetime=" + staff.getCreatedDatetime() + ")";
    }
}
