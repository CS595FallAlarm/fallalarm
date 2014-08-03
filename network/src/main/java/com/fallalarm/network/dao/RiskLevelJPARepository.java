package com.fallalarm.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fallalarm.network.data.entity.RiskLevel;


public interface RiskLevelJPARepository extends JpaRepository<RiskLevel,Integer>, JpaSpecificationExecutor<RiskLevel> {
	
	
}
 