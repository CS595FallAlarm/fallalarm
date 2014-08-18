package com.fallalarm.web.data;

import java.util.ArrayList;

public class PatientArray {

	private String name;
	private ArrayList<String> patientList;
	
	public PatientArray(String name) {
		this.name = name;
		patientList = new ArrayList<String>();
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getStudentList() {
		return patientList;
	}
	
	public void setPatientList(ArrayList<String> patientList) {
		this.patientList = patientList;
	}
	
	public void addPatient(String patientName) {
		patientList.add(patientName);
	}
}
