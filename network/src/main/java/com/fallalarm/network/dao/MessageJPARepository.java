package com.fallalarm.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fallalarm.network.data.entity.Message;


public interface MessageJPARepository extends JpaRepository<Message,Integer>, JpaSpecificationExecutor<Message> {
	
	
}
 