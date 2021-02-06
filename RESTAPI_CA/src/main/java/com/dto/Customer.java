package com.dto;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"services"})
@XmlRootElement
@Entity
public class Customer {

	@Id@GeneratedValue
	private int customerId;
	private String customerName;
	private String password;
	private String emailId;
	private String mobNum;
	private String landmark;
	private String district;
	private String state;
	
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	List<Services> services= new ArrayList<Services>();

	public Customer() {
		// TODO Auto-generated constructor stub
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getMobNum() {
		return mobNum;
	}


	public void setMobNum(String mobNum) {
		this.mobNum = mobNum;
	}


	public String getLandmark() {
		return landmark;
	}


	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public List<Services> getServices() {
		return services;
	}


	public void setServices(List<Services> services) {
		this.services = services;
	}




	
	
}
