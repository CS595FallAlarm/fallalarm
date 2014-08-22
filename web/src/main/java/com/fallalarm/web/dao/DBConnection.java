package com.fallalarm.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fallalarm.web.data.Patient;

public class DBConnection {
	public Connection dbConn;
	Patient patient;

	public Connection connectDB() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		String dbSourceUrl = "jdbc:mysql://localhost:3306/fallalarm";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		try {
			dbConn = DriverManager.getConnection(dbSourceUrl, "root", "admin");
			//dbConn = DriverManager.getConnection(dbSourceUrl, "fallalarm", "fallalarm");
			//getPatient(dbConn);
			// Action2(dbConn,"CS100","database", 3);
			//dbConn.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return dbConn;
	}

	public void getPatient(Connection dbConn) throws SQLException { // Action 1
		int id = 0;
		String first_name = "";
		String last_name = "";
		String address = "";
		String emergency_phone = "";
		int device_id = 0;

		String queryStr1 = "select * from patient";
		Statement queryStmt1 = dbConn.createStatement();

		ResultSet results1 = null;
		try {
			results1 = queryStmt1.executeQuery(queryStr1);
			while (results1.next()) {
				id = results1.getInt("id");
				first_name = results1.getString("first_name");
				last_name = results1.getString("last_name");
				address = results1.getString("address");
				emergency_phone = results1.getString("emergency_phone");
				device_id = results1.getInt("device_id");
				System.out.println("Patient ID        : " + id);
				System.out.println("Patient first name: " + first_name);
				System.out.println("Patient last name : " + last_name);
				System.out.println("Patient address   : " + address);
				System.out.println("Emergency phone   : " + emergency_phone);
				System.out.println("Device_id         : " + device_id);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		results1.close();
		queryStmt1.close();
	}

	public void setDevice(Connection dbConn, int id, String ipaddress,
			String phone) throws SQLException {

		String queryStr1 = "insert into device (id,ipaddress,phone_number)"
				+ "value ('" + id + "', '" + ipaddress + "','" + phone + "')";
		System.out.println(queryStr1);
		Statement queryStmt1 = dbConn.createStatement();

		ResultSet results1 = null;
		try {
			queryStmt1.executeUpdate(queryStr1);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		results1.close();
		queryStmt1.close();
	}

	// SetPatient Action 2
	public void setPatient(Connection dbConn, int id, String first_name,
			String last_name, String address, String emergency_phone,
			int device_id) throws SQLException {

		String queryStr1 = "insert into patient (coursename,coutsetitle,unit)"
				+ "value ('" + id + "', '" + first_name + "','" + last_name
				+ "','" + address + "','" + emergency_phone + "','" + device_id
				+ "')";
		System.out.println(queryStr1);
		Statement queryStmt1 = dbConn.createStatement();

		ResultSet results1 = null;
		try {
			queryStmt1.executeUpdate(queryStr1);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		results1.close();
		queryStmt1.close();
	}
	/*
	 * public static void dbError(){ try{
	 * System.out.println("DB access error-rollback changes");
	 * dbConn.rollback(); } catch(SQLException ex){
	 * System.out.println("Unable to rollback changes"); ex.printStackTrace(); }
	 * }
	 */
}
