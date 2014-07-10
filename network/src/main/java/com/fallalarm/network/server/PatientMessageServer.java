package com.fallalarm.network.server;

import java.net.Socket;

public class PatientMessageServer extends ThreadPooledServer{

	public PatientMessageServer(String serverName, int port, int threadPoolSize) {
		super(serverName, port, threadPoolSize);
	}


	@Override
	protected void executeWorker(Socket clientSocket) {
		threadPool.execute(new PatientMessageWorker(clientSocket));
	}

}
