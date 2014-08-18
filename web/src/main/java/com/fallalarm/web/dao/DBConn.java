package com.fallalarm.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fallalarm.web.data.Admin;
import com.fallalarm.web.data.Patient;

public class DBConn {

	public Connection dbConn;
	public String CourseName;
	public String CourseTitle;
	public int unit;

	public void getCourseName(String C) {
		CourseName = C;
	}

	public void getCourseTitle(String T) {
		CourseTitle = T;
	}

	public void getUnit(int n) {
		unit = n;
	}

	ArrayList<Patient> patientArrayList;

	public Admin AdminLogin(String fname, String password)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		DBConnection conn = new DBConnection();
		dbConn = conn.connectDB();
		Admin faculty = new Admin();
		try {
			faculty = getAdmin(dbConn, fname, password);
			dbConn.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return faculty;
	}

	public Admin getAdmin(Connection dbConn, String fname, String password)
			throws SQLException {
		int id = 0;
		String f = "";
		String l = "";
		String r = "";
		String p = "";
		String username = null;
		String queryStr = "select * from admin  where username ='" + fname
				+ "'" + "and password= '" + password + "'";
		System.out.println(queryStr);
		Statement queryStmt = dbConn.createStatement();

		ResultSet results = null;
		try {
			results = queryStmt.executeQuery(queryStr);
			if (results.next()) {
				// id=results.getInt("id");
				username = results.getString("username");
				f = results.getString("fname");
				l = results.getString("lname");
				// p=results.getString("password");
				r = results.getString("role");
				// System.out.println(id);
				System.out.println(f);
				System.out.println(l);
				System.out.println(r);
				System.out.println(p);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		Admin admin = new Admin(username, f, l, p, r);
		queryStmt.close();

		return admin;

	}

	public ArrayList<Patient> ListPatient() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		DBConnection conn = new DBConnection();
		dbConn = conn.connectDB();
		try {
			patientArrayList = new ArrayList<Patient>();
			patientList(dbConn);
			dbConn.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return patientArrayList;
	}

	public void patientList(Connection dbConn) throws SQLException {
		int id = 0;
		String first_name = "";
		String last_name = "";
		String address = "";
		String emergency_phone = "";
		int device_id;
		int nurse_id;
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
				emergency_phone = results1.getString("emergency_phone");
				device_id = results1.getInt("device_id");
				nurse_id = results1.getInt("nurse_id");
				System.out.println("Patient ID: " + id);
				System.out.println("First Name: " + first_name);
				System.out.println("Last Name: " + last_name);
				Patient newPatient = new Patient(id, first_name, last_name,
						address, emergency_phone, device_id);
				newPatient.setNurse_id(nurse_id);
				patientArrayList.add(newPatient);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		results1.close();
		queryStmt1.close();
	}

	public void insertNewPatient(int pid, String fName, String lName,
			String address, String phone, int deviceid, String nfName,
			String nlName) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		DBConnection conn = new DBConnection();
		dbConn = conn.connectDB();
		try {
			String queryStr2 = "insert into fallalarm.device (id, ipaddress, phone_number)"
					+ "value ("
					+ deviceid
					+ ",'111.111.111.1',"
					+ "'"
					+ phone
					+ "')";
			System.out.println(queryStr2);
			String queryStr3 = "insert into fallalarm.nurse (first_name, last_name, device_id)"
					+ "value ('"
					+ nfName
					+ "','"
					+ nlName
					+ "',"
					+ deviceid
					+ ")";
			System.out.println(queryStr3);
			String queryStr1 = "insert into fallalarm.patient (id,first_name, last_name, address,emergency_phone, device_id)"
					+ "value("
					+ pid
					+ ", '"
					+ fName
					+ "','"
					+ lName
					+ "','"
					+ address + "','" + phone + "'," + deviceid + ")";
			System.out.println(queryStr1);
			Statement queryStmt2 = dbConn.createStatement();
			Statement queryStmt3 = dbConn.createStatement();
			Statement queryStmt1 = dbConn.createStatement();
			queryStmt1.executeUpdate(queryStr2);
			queryStmt1.executeUpdate(queryStr3);
			queryStmt1.executeUpdate(queryStr1);
			dbConn.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
