package com.bts.nic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bts.nic.modal.AssignTo;
import com.bts.nic.modal.TaskAssigners;

@Repository
public interface TaskAssignerRepository extends JpaRepository<TaskAssigners, Integer> {

}
