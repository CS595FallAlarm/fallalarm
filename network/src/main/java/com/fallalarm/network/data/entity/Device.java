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
@Table(name = "DEVICE")
public class Device implements Serializable {

	private static final long serialVersionUID = -5459992586810993932L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID", unique = true, nullable = false)
	private int id;
	
	@Column(name="IPADDRESS", unique=true, nullable=false, length=15)
	private String IPaddress;	

	@Column(name="PHONE_NUMBER", unique=false, nullable=false, length=10)
	private String phoneNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIPaddress() {
		return IPaddress;
	}

	public void setIPaddress(String iPaddress) {
		IPaddress = iPaddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	
}

