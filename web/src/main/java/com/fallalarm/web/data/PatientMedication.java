package com.fallalarm.web.data;

import java.io.Serializable;
import java.util.Date;

public class PatientMedication implements Serializable {

	private static final long serialVersionUID = -5459992586810993932L;

	private int id;
	
	private int patientId;

	private boolean medication;
	
	private Date date;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public boolean isMedication() {
		return medication;
	}

	public void setMedication(boolean medication) {
		this.medication = medication;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	
}