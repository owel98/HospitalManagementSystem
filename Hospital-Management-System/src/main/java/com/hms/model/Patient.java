package com.hms.model;

import jakarta.persistence.Column;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Patient {
    @Id
    @Column(nullable = false)
    private long patientId;
    @Column(nullable = false, length = 100)
    private String patientName;
    @Column(nullable = false)
    private Integer patientAge;
    @Column(nullable = false)
    private long patientPhoneNumber;
    @Column(nullable = false, length = 100)
    private String patientProfileStatus;
    @Column(nullable = false, columnDefinition = "integer default 4")
    private Integer roleId;
    @Column(nullable = false,length = 100)
    private String patientHealthDesc;
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Integer getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}
	public long getPatientPhoneNumber() {
		return patientPhoneNumber;
	}
	public void setPatientPhoneNumber(long patientPhoneNumber) {
		this.patientPhoneNumber = patientPhoneNumber;
	}
	public String getPatientProfileStatus() {
		return patientProfileStatus;
	}
	public void setPatientProfileStatus(String patientProfileStatus) {
		this.patientProfileStatus = patientProfileStatus;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getPatientHealthDesc() {
		return patientHealthDesc;
	}
	public void setPatientHealthDesc(String patientHealthDesc) {
		this.patientHealthDesc = patientHealthDesc;
	}
	public Patient(long patientId, String patientName, Integer patientAge, long patientPhoneNumber,
			String patientProfileStatus, Integer roleId, String patientHealthDesc) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientPhoneNumber = patientPhoneNumber;
		this.patientProfileStatus = patientProfileStatus;
		this.roleId = roleId;
		this.patientHealthDesc = patientHealthDesc;
	}
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}



}