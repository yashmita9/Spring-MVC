package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "st_customer")
public class CustomerDTO {
	
	@Id
	@GeneratedValue(generator = "ncsPk")
	@GenericGenerator(name = "ncsPk", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	
	@Column(name= "client_name" , length = 20)
	private String clientName;
	
	@Column(name="location" , length = 50)
	private String location;
	
	@Column(name = "contact_no", length = 10)
	private String contactNo;
	
	@Column(name="importance" , length = 50)
	private String importance;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	
	
	
	
	

}
