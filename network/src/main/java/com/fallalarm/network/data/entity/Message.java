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
@Table(name = "MESSAGE")
public class Message implements Serializable {

	private static final long serialVersionUID = -5459992586810993932L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID", unique = true, nullable = false)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FROM_DEVICE_ID")
	private Device fromDevice;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TO_DEVICE_ID")
	private Device toDevice;
	
	@Column(name="CONTENT", unique=false, nullable=false, length=255)
	private String content;

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
	

}