package com.fallalarm.network.server;

import java.net.Socket;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import com.fallalarm.network.dao.PatientActivityJPARepository;
import com.fallalarm.network.data.entity.PatientActivity;
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
		//int id = buff[0] << 24 | (buff[1] & 0xFF) << 16 | (buff[2] & 0xFF) << 8 | (buff[3] & 0xFF);
		message = StringUtils.toEncodedString(buff, Charset.forName("UTF-8"));
		message = message.trim();
		String[] parts = message.split(",");
		String msgId = parts[0];
		msgId = msgId.replace(".", "");
		int id = Integer.parseInt(msgId);
		System.out.println("Message id :"+id);
		String patientId = null;
		String accelX = null;
		String accelY = null;
		String accelZ = null;
		String gyroX = null;
		String gyroY = null;
		String gyroZ = null;
		String risk = null;
		if(id == 101) {
			patientId = parts[1];
			accelX = parts[2];
			accelY = parts[3];
			accelZ = parts[4];
			gyroX = parts[5];
			gyroY = parts[6];
			gyroZ = parts[7];
			risk = parts[8];
			System.out.println("Patient ID :"+patientId);
			System.out.println("Accel X :"+accelX);
			System.out.println("Accel Y :"+accelY);
			System.out.println("Accel Z :"+accelZ);
			System.out.println("Gyro X :"+gyroX);
			System.out.println("Gyro Y :"+gyroY);
			System.out.println("Gyro Z :"+gyroZ);
			System.out.println("risk :"+risk);
			
		}
		System.out.println("Patient activity worker , read message -"+message);
		PatientActivityJPARepository patientActivityDao =  ApplicationContextUtil.getPatientActivityDAO();
		PatientActivity pact = new PatientActivity();
		pact.setAccelX(Double.parseDouble(accelX));
		pact.setAccelY(Double.parseDouble(accelY));
		pact.setAccelZ(Double.parseDouble(accelZ));
		pact.setGyroX(Double.parseDouble(gyroX));
		pact.setGyroY(Double.parseDouble(gyroY));
		pact.setGyroZ(Double.parseDouble(gyroZ));
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		pact.setTimestamp(date);
		patientActivityDao.save(pact);
		return message;
	}

}
