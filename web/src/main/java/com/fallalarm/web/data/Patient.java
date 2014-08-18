package com.fallalarm.web.data;

public class Patient {
	private int id;
	private String first_name;
	private String last_name;
	private String address;
	private String emergency_phone;
	private int device_id;
	private int nurse_id;

	public Patient(int id, String first_name, String last_name, String address,
			String emergency_phone, int device_id) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.emergency_phone = emergency_phone;
		this.device_id = device_id;

	}

	public void setId(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setLastName(String lname) {
		this.last_name = lname;
	}

	public String getLastName() {
		return last_name;
	}

	public void setFirstName(String fname) {
		this.first_name = fname;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setPhone(String phone) {
		this.emergency_phone = phone;
	}

	public String getPhone() {
		return emergency_phone;
	}

	public void setDeviceId(int deviceid) {
		this.device_id = deviceid;
	}

	public int getDeviceID() {
		return device_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmergency_phone() {
		return emergency_phone;
	}

	public void setEmergency_phone(String emergency_phone) {
		this.emergency_phone = emergency_phone;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public int getNurse_id() {
		return nurse_id;
	}

	public void setNurse_id(int nurse_id) {
		this.nurse_id = nurse_id;
	}

	public int getId() {
		return id;
	}
	
	
}
