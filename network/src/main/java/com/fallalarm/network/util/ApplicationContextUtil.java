package com.fallalarm.network.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fallalarm.network.config.AppConfig;
import com.fallalarm.network.dao.MessageJPARepository;
import com.fallalarm.network.dao.PatientActivityJPARepository;

public class ApplicationContextUtil {

	static ApplicationContext context;
	
	public static void loadApplicationContext() {
		context = new AnnotationConfigApplicationContext(
				AppConfig.class);

	}
	
	public static PatientActivityJPARepository getPatientActivityDAO() {
		return context.getBean(PatientActivityJPARepository.class);
	}

	public static MessageJPARepository getMessageDAO() {
		return context.getBean(MessageJPARepository.class);
	}

}
