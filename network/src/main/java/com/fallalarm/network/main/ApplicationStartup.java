package com.fallalarm.network.main;

import com.fallalarm.network.server.PatientActivityMonitorServer;
import com.fallalarm.network.util.ApplicationContextUtil;

public class ApplicationStartup {
	public static void main(String[] args) {
		
		PatientActivityMonitorServer activityMonitor = new PatientActivityMonitorServer("ActivityMonitor", 8080, 10);
		Thread activityThread = new Thread(activityMonitor);
		activityThread.start();
		System.out.println("Fall alarm server socket started");
		
		ApplicationContextUtil.loadApplicationContext();
	}
}
