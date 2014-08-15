package com.fallalarm.network.data.entity;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "PATIENT_ACTIVITY")
public class PatientActivity implements Serializable {

	private static final long serialVersionUID = -5459992586810993932L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID", unique = true, nullable = false)
	private int id;
	
	@Column(name="ACCEL_X", unique=false, nullable=false)
	private double accelX;

	@Column(name="ACCEL_Y", unique=false, nullable=false)
	private double accelY;
	
	@Column(name="ACCEL_Z", unique=false, nullable=false)
	private double accelZ;

	@Column(name="MAG_X", unique=false, nullable=false)
	private double magX;

	@Column(name="MAG_Y", unique=false, nullable=false)
	private double magY;
	
	@Column(name="MAG_Z", unique=false, nullable=false)
	private double magZ;
	
	@Column(name="GEO_LAT", unique=false, nullable=false)
	private double geoLat;
	
	@Column(name="GEO_LONG", unique=false, nullable=false)
	private double geoLong;

	@Column(name="TIMESTAMP", unique=false, nullable=false)
	private Date timestamp;
	
	@Column(name="RISK_LEVEL", unique=false, nullable=false)
	private int riskLevel;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PATIENT_ID")
	private Patient patient;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAccelX() {
		return accelX;
	}

	public void setAccelX(double accelX) {
		this.accelX = accelX;
	}

	public double getAccelY() {
		return accelY;
	}

	public void setAccelY(double accelY) {
		this.accelY = accelY;
	}

	public double getAccelZ() {
		return accelZ;
	}

	public void setAccelZ(double accelZ) {
		this.accelZ = accelZ;
	}

	public double getMagX() {
		return magX;
	}

	public void setMagX(double magX) {
		this.magX = magX;
	}

	public double getMagY() {
		return magY;
	}

	public void setMagY(double magY) {
		this.magY = magY;
	}

	public double getMagZ() {
		return magZ;
	}

	public void setMagZ(double magZ) {
		this.magZ = magZ;
	}

	public double getGeoLat() {
		return geoLat;
	}

	public void setGeoLat(double geoLat) {
		this.geoLat = geoLat;
	}
	
	public double getGeoLong() {
		return geoLong;
	}

	public void setGeoLong(double geoLong) {
		this.geoLong = geoLong;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(int riskLevel) {
		this.riskLevel = riskLevel;
	}

	
}