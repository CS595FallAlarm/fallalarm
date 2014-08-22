package com.fallalarm.web.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fallalarm.web.data.Message;
import com.fallalarm.web.data.Patient;
import com.fallalarm.web.data.PatientActivity;

public class MessageDAO {
	
	public void saveMessage(Message message) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		try {
			conn = dbConn.connectDB();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(conn!=null) {
			StringBuilder patientSql = new StringBuilder();
			patientSql.append("SELECT p.NURSE_ID,p.DEVICE_ID AS PATIENT_DEVICE_ID, n.DEVICE_ID AS NURSE_DEVICE_ID FROM PATIENT p, NURSE n WHERE p.NURSE_ID = n.ID and p.ID = "+message.getPatientId());
			Statement stat = null;
			ResultSet results = null;
			int nurseId = 0;
			int nurseDeviceId = 0;
			int patientDeviceId = 0;
			try {
				stat = conn.createStatement();
				results = stat.executeQuery(patientSql.toString());
				if(results.next()) {
					nurseId = results.getInt("NURSE_ID");
					nurseDeviceId = results.getInt("NURSE_DEVICE_ID");
					patientDeviceId = results.getInt("PATIENT_DEVICE_ID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					results.close();
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO MESSAGE(content,from_device_id,to_device_id,patient_id,nurse_id,date) VALUES(");
			sql.append(message.getContent()+",");
			sql.append(patientDeviceId + ",");
			sql.append(nurseDeviceId + ",");
			sql.append(message.getPatientId() + ",");
			sql.append(nurseId + ",");
			sql.append(" now())");
			try {
				stat = conn.createStatement();
				stat.execute(sql.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public List<Message> getMessages(int nurseId) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		List<Message> messages = new ArrayList<Message>();
		try {
			conn = dbConn.connectDB();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(conn!=null) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT m.id as id, m.patient_id as patient_id, m.nurse_id as nurse_id, m.content as content, p.first_name as fname," +
					"p.last_name as lname, m.date as date FROM MESSAGE m, PATIENT p WHERE m.nurse_id = "+ nurseId+ " and m.patient_id = p.id and DATE_FORMAT(m.date,'%m-%d-%Y') = DATE_FORMAT(now(), '%m-%d-%Y')");
			Statement stat = null;
			ResultSet resultSet = null;
			
			try {
				stat = conn.createStatement();
				resultSet = stat.executeQuery(sql.toString());
				while(resultSet.next()) {
					Message m = new Message();
					m.setId(resultSet.getInt("id"));
					m.setPatientId(resultSet.getInt("patient_id"));
					m.setNurseId(resultSet.getInt("nurse_id"));
					m.setContent(resultSet.getString("content"));
					m.setPatientName(resultSet.getString("fname") + " " + resultSet.getString("lname"));
					m.setDate(resultSet.getDate("date"));
					messages.add(m);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					resultSet.close();
					stat.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return messages;
	}

	

}
