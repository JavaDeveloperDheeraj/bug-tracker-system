package com.bts.nic.modal;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "priority")
public class Priority {

    @Id
    private Integer priorityId;
    private String priorityName;
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
