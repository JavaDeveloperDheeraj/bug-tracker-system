package org.nic.bug_tracker_system.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "assign_to")
public class AssignTo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private Integer developerId;

    @Column(name = "developer_name", nullable = false)
    private String developerName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "created_date")
    private LocalDateTime createdDatetime;

    @Column(name = "updated_date")
    private LocalDateTime updatedDatetime;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDatetime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "flag")
    private Boolean flag;
}

