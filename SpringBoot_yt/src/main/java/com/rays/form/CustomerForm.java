package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.dto.CustomerDTO;


public class CustomerForm {
	
	private long id;
	
	@NotEmpty(message = "client name is required")
	private String clientName;
	
	@NotEmpty(message = "Location is required")
	private String location;
	
	@NotEmpty(message = "importance is required")
	private String importance;
	
	@NotEmpty(message = "contact number is required")
	private String contactNo;

	public CustomerForm() {
		
	}

	public long getId() {
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

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	
	public CustomerDTO getDto() {
		CustomerDTO dto = (CustomerDTO) new CustomerDTO();
		dto.setClientName(clientName);;
		dto.setLocation(location);;
		dto.setImportance(importance);;
		dto.setContactNo(contactNo);;
		return dto;
	}
	
	
	

}
