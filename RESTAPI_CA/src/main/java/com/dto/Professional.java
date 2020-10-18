package com.dto;

import java.util.Arrays;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Professional {

	@Id
	@GeneratedValue
	private int professionalId;
	private String professionalName;
	private String password;
	private String emailId;
	private String mobNum;
	private String landmark;
	private String district;
	private String state;
	private String expertise;
	private String specialization;
	
	
	public int getProfessionalId() {
		return professionalId;
	}
	public void setProfessionalId(int professionalId) {
		this.professionalId = professionalId;
	}
	public String getProfessionalName() {
		return professionalName;
	}
	public void setProfessionalName(String professionalName) {
		this.professionalName = professionalName;
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
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	@Override
	public String toString() {
		return "Professional [professionalId=" + professionalId + ", professionalName=" + professionalName
				+ ", password=" + password + ", emailId=" + emailId + ", mobNum=" + mobNum + ", landmark=" + landmark
				+ ", district=" + district + ", state=" + state + ", expertise=" + expertise + ", specialization="
				+ specialization + "]";
	}
	
	
}
