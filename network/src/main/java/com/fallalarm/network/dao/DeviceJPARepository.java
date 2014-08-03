package com.fallalarm.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fallalarm.network.data.entity.Device;


public interface DeviceJPARepository extends JpaRepository<Device,Integer>, JpaSpecificationExecutor<Device> {
	
	
}
 