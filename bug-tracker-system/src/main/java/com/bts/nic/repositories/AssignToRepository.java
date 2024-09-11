package com.bts.nic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bts.nic.modal.AssignTo;

@Repository
public interface AssignToRepository extends JpaRepository<AssignTo, Integer> {

}
