package com.fallalarm.network.data.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PATIENT")
public class Patient implements Serializable {

	private static final long serialVersionUID = -5459992586810993932L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID", unique = true, nullable = false)
	private int id;
	
	@Column(name="FIRST_NAME", unique=false, nullable=false, length=50)
	private String firstName;

	@Column(name="LAST_NAME", unique=false, nullable=false, length=50)
	private String lastName;
	
	@Column(name="ADDRESS", unique=false, nullable=false, length=50)
	private String address;

	@Column(name="EMERGENCY_PHONE", unique=false, nullable=false, length=50)
	private String emergencyPhone;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NURSE_ID")
	private Nurse nurse;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DEVICE_ID")
	private Device device;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	

}