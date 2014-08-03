package com.fallalarm.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fallalarm.network.data.entity.Patient;


public interface PatientJPARepository extends JpaRepository<Patient,Integer>, JpaSpecificationExecutor<Patient> {
	
	
}
 