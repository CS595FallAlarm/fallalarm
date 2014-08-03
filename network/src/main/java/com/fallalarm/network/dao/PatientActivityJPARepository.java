package com.fallalarm.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fallalarm.network.data.entity.PatientActivity;


public interface PatientActivityJPARepository extends JpaRepository<PatientActivity,Integer>, JpaSpecificationExecutor<PatientActivity> {
	
	
}
 