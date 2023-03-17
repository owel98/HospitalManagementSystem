package com.hms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
public class Doctor {
    @Id
    @Column(nullable = false,length = 100)
    private String username;
    @Column(nullable = false)
    private long doctorId;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 100)
    private String doctorName;
    @Column(nullable = false,columnDefinition = "integer default 2")
    private Integer roleId;
    @Column(nullable = false, length = 10)
    private String doctorProfileStatus;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getDoctorProfileStatus() {
		return doctorProfileStatus;
	}
	public void setDoctorProfileStatus(String doctorProfileStatus) {
		this.doctorProfileStatus = doctorProfileStatus;
	}
	public Doctor(String username, long doctorId, String password, String doctorName, Integer roleId,
			String doctorProfileStatus) {
		super();
		this.username = username;
		this.doctorId = doctorId;
		this.password = password;
		this.doctorName = doctorName;
		this.roleId = roleId;
		this.doctorProfileStatus = doctorProfileStatus;
	}
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

}