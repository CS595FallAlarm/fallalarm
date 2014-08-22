package com.fallalarm.web.data;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	private static final long serialVersionUID = -5459992586810993932L;

	private int id;

	private int patientId;
	
	private int nurseId;
	
	private Device fromDevice;

	private Device toDevice;
	
	private String content;
	
	private String patientName;
	
	private Date date;
 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Device getFromDevice() {
		return fromDevice;
	}

	public void setFromDevice(Device fromDevice) {
		this.fromDevice = fromDevice;
	}

	public Device getToDevice() {
		return toDevice;
	}

	public void setToDevice(Device toDevice) {
		this.toDevice = toDevice;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}