package org.nic.bug_tracker_system.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    private Integer categoryId;
    private String categoryName;
    
    @Column(name = "created_date")
    private LocalDateTime createdDatetime;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDatetime;
    
    @Column(name = "deleted_date")
    private LocalDateTime deletedDatetime;
    
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private Boolean flag;
}
