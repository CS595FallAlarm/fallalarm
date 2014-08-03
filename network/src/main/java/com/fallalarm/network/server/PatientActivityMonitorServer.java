package com.fallalarm.network.server;

import java.net.Socket;

public class PatientActivityMonitorServer extends ThreadPooledServer {

	public PatientActivityMonitorServer(String serverName, int port,
			int threadPoolSize) {
		super(serverName, port, threadPoolSize);
	}

	@Override
	protected void executeWorker(Socket clientSocket) {
		System.out.println("Patient activity monitor server executing worker");
		threadPool.execute(new PatientActivityWorker(clientSocket));
	}

}
