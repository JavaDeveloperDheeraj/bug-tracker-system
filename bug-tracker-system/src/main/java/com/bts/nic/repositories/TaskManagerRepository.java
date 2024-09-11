package com.bts.nic.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bts.nic.modal.AssignTo;
import com.bts.nic.modal.TaskAssigners;
import com.bts.nic.modal.TaskManager;

@Repository
public interface TaskManagerRepository extends JpaRepository<TaskManager, String> {
    
	// method to find the task with the highest taskId
    Optional<TaskManager> findTopByTaskIdStartingWithOrderByTaskIdDesc(String prefix);
    
 // Find all tasks by assignedBy
   // List<TaskManager> findByAssignerId(Integer assignerId);
    List<TaskManager> findBytaskAssigners(TaskAssigners assignerId);
    //List<TaskManager> findByassingerId(TaskAssigners assignerId);
    List<TaskManager> findByassignTo(AssignTo developerId);
    List<TaskManager> findByAssignToDeveloperId(Integer developerId);
	/*
	 * @Query("SELECT tm FROM TaskManager tm WHERE tm.taskAssigners.assingerId = :assingerId"
	 * ) List<TaskManager> findByAssingerId(@Param("assingerId") Integer
	 * assingerId);
	 */
    // Query to find tasks by developer ID
   
}
