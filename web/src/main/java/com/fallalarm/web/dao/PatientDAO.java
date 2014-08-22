package com.fallalarm.web.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fallalarm.web.data.Patient;
import com.fallalarm.web.data.PatientActivity;
import com.fallalarm.web.data.PatientMedication;

public class PatientDAO {
	
	public void updatePatientMedication(PatientMedication pm) {
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
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO PATIENT_MEDICATION(patient_id,medication,date) VALUES(");
			sql.append(pm.getPatientId() + ",");
			sql.append(pm.isMedication() + ",");
			sql.append(" now() )");
			Statement stat;
			try {
				stat = conn.createStatement();
				stat.execute(sql.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public PatientMedication getPatientMedication(int patientId) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PatientMedication patientMed = new PatientMedication();
		try {
			conn = dbConn.connectDB();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(conn!=null) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM PATIENT_MEDICATION WHERE patient_id = "+ patientId+ " and DATE_FORMAT(date, '%m-%d-%Y') = DATE_FORMAT(now(), '%m-%d-%Y')");
			Statement stat = null;
			ResultSet resultSet = null;
			
			try {
				stat = conn.createStatement();
				resultSet = stat.executeQuery(sql.toString());
				if(resultSet.next()) {
					patientMed.setId(resultSet.getInt("id"));
					patientMed.setPatientId(resultSet.getInt("patient_id"));
					patientMed.setMedication(resultSet.getBoolean("medication"));
					patientMed.setDate(resultSet.getDate("date"));
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
		
		return patientMed;
	}


	public List<PatientActivity> getPatientActivities(int patientId) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		List<PatientActivity> activities = new ArrayList<PatientActivity>();
		try {
			conn = dbConn.connectDB();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(conn!=null) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT pa.id as id, pa.patient_id as patient_id, p.nurse_id as nurse_id, p.first_name as fname," +
					"p.last_name as lname, pa.timestamp as date, pa.risk_level as risklevel FROM PATIENT_ACTIVITY pa, PATIENT p WHERE p.id = pa.patient_id and DATE_FORMAT(timestamp, '%m-%d-%Y') = DATE_FORMAT(now(), '%m-%d-%Y')");
			Statement stat = null;
			ResultSet resultSet = null;
			
			try {
				stat = conn.createStatement();
				resultSet = stat.executeQuery(sql.toString());
				while(resultSet.next()) {
					PatientActivity pa = new PatientActivity();
					pa.setId(resultSet.getInt("id"));
					Patient p = new Patient();
					p.setId(resultSet.getInt("patient_id"));
					p.setNurse_id(resultSet.getInt("nurse_id"));
					p.setFirst_name(resultSet.getString("fname"));
					p.setLast_name(resultSet.getString("lname"));
					pa.setPatient(p);
					pa.setTimestamp(resultSet.getDate("date"));
					pa.setRiskLevel(resultSet.getInt("risklevel"));
					activities.add(pa);
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
		
		return activities;
	}
}
