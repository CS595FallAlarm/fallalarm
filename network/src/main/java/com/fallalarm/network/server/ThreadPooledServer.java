package com.fallalarm.network.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ThreadPooledServer implements Runnable {

	protected ServerSocket serverSocket;
	protected int port = 8080;
	protected boolean isRunning = true;
	protected ExecutorService threadPool;
	protected String serverName;

	public ThreadPooledServer(String serverName, int port, int threadPoolSize) {
		this.port = port;
		threadPool = Executors.newFixedThreadPool(threadPoolSize);
		this.serverName = serverName;
	}

	public void run() {
		openServerSocket();
		while (isRunning()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
				System.out.println("Server socket accepted client socket connection");
			} catch (IOException e) {
				if (!isRunning()) {
					System.out.println("Socket Server Stopped.");
					return;
				}
				throw new RuntimeException(
						"Error while accepting client connection", e);
			}
			System.out.println("Server socket executing worker");
			executeWorker(clientSocket);
		}
		threadPool.shutdown();
		System.out.println("Socket Server Stopped.");
	}
	
	/**
	 * To be implemented by implementing servers
	 */
	protected abstract void executeWorker(Socket clientSocket);

	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(port);
			System.out.println("Server socket on port "+port+" opened");
		} catch (IOException e) {
			throw new RuntimeException(
					"Error while opening server socket - port " + port, e);
		}
	}

	private synchronized boolean isRunning() {
		System.out.println("Server socket is running "+isRunning);
		return isRunning;
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Error while closing server socket", e);
		}
	}

}