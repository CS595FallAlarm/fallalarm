package com.fallalarm.network.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "RISK_LEVEL")
public class RiskLevel implements Serializable {

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

	@Column(name="GYRO_X", unique=false, nullable=false)
	private double gyroX;

	@Column(name="GYRO_Y", unique=false, nullable=false)
	private double gyroY;

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

	public double getGyroX() {
		return gyroX;
	}

	public void setGyroX(double gyroX) {
		this.gyroX = gyroX;
	}

	public double getGyroY() {
		return gyroY;
	}

	public void setGyroY(double gyroY) {
		this.gyroY = gyroY;
	}

		
	

}