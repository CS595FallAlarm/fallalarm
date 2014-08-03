package com.fallalarm.network.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.fallalarm"})
@Import({ DAOConfig.class })
public class AppConfig {

	
}
