package com.bts.nic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bts.nic.modal.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
