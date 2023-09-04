package com.mypersonal.project.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mypersonal.project.springboot.entities.CustomerEntity;

@Repository
public interface CostumerRepository extends JpaRepository<CustomerEntity, Integer> {

}
