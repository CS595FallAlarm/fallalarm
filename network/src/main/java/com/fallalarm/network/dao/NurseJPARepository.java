package com.fallalarm.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fallalarm.network.data.entity.Nurse;


public interface NurseJPARepository extends JpaRepository<Nurse,Integer>, JpaSpecificationExecutor<Nurse> {
	
	
}
 