package org.nic.bug_tracker_system.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    

    private String password;

    private String role;
    
    private String name;

    private String designation;

    private LocalDateTime createdDatetime;

    private LocalDateTime updatedDatetime;

    private LocalDateTime deletedDatetime;

    private String createdBy;

    private String updatedBy;

    private String deletedBy;

    private Boolean flag;
    
    private String mobile;
  
}
