package com.fallalarm.network.server;

import java.net.Socket;

public class PatientActivityWorker extends WorkerThread {

	public PatientActivityWorker(Socket clientSocket) {
		super(clientSocket);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String readMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
