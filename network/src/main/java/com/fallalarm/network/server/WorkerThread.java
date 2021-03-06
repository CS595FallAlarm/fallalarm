package com.fallalarm.network.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public abstract class WorkerThread implements Runnable{

    private Socket clientSocket;
    
    public WorkerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            long time = System.currentTimeMillis();
            System.out.println("Message received at: " + time + "," + clientSocket.getReceiveBufferSize());
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            byte[] buff = new byte[1024];
            IOUtils.read(input, buff);
            System.out.println("Reading input bytes: " + buff);
            readMessage(buff);
            System.out.println("Completed reading input bytes: " + buff);
            input.close();
            System.out.println("Message received at: " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To be implemented by sub classes - look at the first 3 integer value to determine if the message is for activity monitor
     * or Patient message.
     * 101 - Activity monitor message
     * 102 - Patient message.
     * @return
     */
    protected abstract String readMessage(byte[] buff);
}
