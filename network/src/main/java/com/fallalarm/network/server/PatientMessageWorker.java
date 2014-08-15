package com.fallalarm.network.server;

import java.net.Socket;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;

import com.fallalarm.network.dao.MessageJPARepository;
import com.fallalarm.network.dao.PatientJPARepository;
import com.fallalarm.network.data.entity.Message;
import com.fallalarm.network.data.entity.Nurse;
import com.fallalarm.network.data.entity.Patient;
import com.fallalarm.network.util.ApplicationContextUtil;

public class PatientMessageWorker extends WorkerThread{

	public PatientMessageWorker(Socket clientSocket) {
		super(clientSocket);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String readMessage(byte[] buff) {
		System.out.println("Patient message worker, reading message");
		String message = null;
		message = StringUtils.toEncodedString(buff, Charset.forName("UTF-8"));
		message = message.trim();
		System.out.println("Received Message  :"+message);
		String[] parts = message.split(",");
		String msgId = parts[0];
		int patientId = Integer.parseInt(parts[1]);
		String content = parts[2];
		msgId = msgId.replace(".", "");
		int id = Integer.parseInt(msgId);
		System.out.println("Message id :"+id);
		System.out.println("Patient Id :"+patientId);
		System.out.println("Patient message worker , read message -"+content);
		MessageJPARepository messageDao =  ApplicationContextUtil.getMessageDAO();
		Message msg = new Message();
		msg.setContent(content);
		PatientJPARepository patientDao = ApplicationContextUtil.getPatientDAO();
		Patient patient = patientDao.findById(patientId);
		Nurse nurse = patient.getNurse();
		msg.setFromDevice(patient.getDevice());
		msg.setToDevice(nurse.getDevice());
		messageDao.save(msg);
		return message;
	}

}
