package com.fallalarm.network.server;

import java.net.Socket;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.Calendar;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;

import com.fallalarm.network.dao.MessageJPARepository;
import com.fallalarm.network.dao.PatientActivityJPARepository;
import com.fallalarm.network.dao.PatientJPARepository;
import com.fallalarm.network.data.entity.Message;
import com.fallalarm.network.data.entity.Nurse;
import com.fallalarm.network.data.entity.Patient;
import com.fallalarm.network.data.entity.PatientActivity;
import com.fallalarm.network.notification.EmailNotifier;
import com.fallalarm.network.util.ApplicationContextUtil;


public class PatientActivityWorker extends WorkerThread {

	public PatientActivityWorker(Socket clientSocket) {
		super(clientSocket);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String readMessage(byte[] buff) {
		System.out.println("Patient activity worker, reading message");
		String message = null;
		message = StringUtils.toEncodedString(buff, Charset.forName("UTF-8"));
		message = message.trim();
		message = message.substring(1);
		System.out.println("Message received :"+message);
		String[] parts = message.split(",");
		String msgId = parts[0];
		System.out.println("MsgId :"+msgId);
		//msgId = msgId.replace("/", "");
		int id = Integer.parseInt(msgId);
		System.out.println("Message id :"+id);
		String patientId = null;
		String accelX = null;
		String accelY = null;
		String accelZ = null;
		String magX = null;
		String magY = null;
		String magZ = null;
		String risk = null;
		if(id == 101) {
			patientId = parts[1];
			accelX = parts[2];
			accelY = parts[3];
			accelZ = parts[4];
			magX = parts[5];
			magY = parts[6];
			magZ = parts[7];
			risk = parts[8];
			System.out.println("Patient ID :"+patientId);
			System.out.println("Accel X :"+accelX);
			System.out.println("Accel Y :"+accelY);
			System.out.println("Accel Z :"+accelZ);
			System.out.println("Mag X :"+magX);
			System.out.println("Mag Y :"+magY);
			System.out.println("Mag Z :"+magZ);
			System.out.println("risk :"+risk);
			if(StringUtils.isNotBlank(risk)) {
				int riskLevel = Integer.parseInt(risk);
				if(riskLevel > 4) {
					//get the patient details
					if(StringUtils.isNotBlank(patientId)) {
						int patientID = Integer.parseInt(patientId);
						PatientJPARepository patientDAO = ApplicationContextUtil.getPatientDAO();
						Patient patient = patientDAO.findOne(patientID);
						Nurse nurse = patient.getNurse();
						String nurseEmail = nurse.getEmail();
						String patientName = String.format("%s %s", patient.getFirstName(), patient.getLastName()); 
						//send email notification to nurse.
						try {
							EmailNotifier.generateAndSendEmail(patientId, patientName, risk, nurseEmail);
						} catch (MessagingException e) {
							e.printStackTrace();
							System.err.println("Error while sending email notification");
						}
						//send an instant message to nurse
						MessageJPARepository messageDAO =  ApplicationContextUtil.getMessageDAO();
						Message msg = new Message();
						msg.setContent(String.format("EMERGENCY ALERT - PATIENT %s HAS AN EMERGENCY - Ward # 100", patientName));
						msg.setFromDevice(patient.getDevice());
						msg.setToDevice(nurse.getDevice());
						messageDAO.save(msg);
					}	
			    }
			}
			
		}
		System.out.println("Patient activity worker , read message -"+message);
		PatientActivityJPARepository patientActivityDao =  ApplicationContextUtil.getPatientActivityDAO();
		PatientActivity pact = new PatientActivity();
		pact.setId(Integer.parseInt(patientId));
		pact.setAccelX(Double.parseDouble(accelX));
		pact.setAccelY(Double.parseDouble(accelY));
		pact.setAccelZ(Double.parseDouble(accelZ));
		pact.setMagX(Double.parseDouble(magX));
		pact.setMagY(Double.parseDouble(magY));
		pact.setMagZ(Double.parseDouble(magZ));
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		pact.setTimestamp(date);
		pact.setRiskLevel(Integer.parseInt(risk));
		patientActivityDao.save(pact);
		return message;
	}

}
