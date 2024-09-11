package com.bts.nic.modal;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_manager")
public class TaskManager {

    @Id
   // @Column(insertable=false, updatable=false)
    private String taskId;

    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "severity_id")
    private Severity severity;

    @ManyToOne
    @JoinColumn(name = "reproducibility_id")
    private Reproducibility reproducibility;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private AssignTo assignTo;
    
   
//    @ManyToOne
//    @JoinColumn(name = "assigner_id")
//    private TaskAssigners taskAssigners;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) 
    @JoinColumn(name = "assigner_id", nullable = false)
    private TaskAssigners taskAssigners;



    private String subject;
    private String subjectDetails;
    private String attachmentPath;
  //  private String assignedBy;
    private LocalDateTime assignedTime;
    
    @Column(name = "completion_time")
    private LocalDateTime completionDatetime;
    
    private Integer estimatedTime;
    private Integer actualTime;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    
   
}
