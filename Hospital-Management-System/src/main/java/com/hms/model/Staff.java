package com.hms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
public class Staff {
    @Id
    @Column(nullable = false,length = 100)
    private String username;
    @Column(nullable = false)
    private long staffId;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 100)
    private String staffName;
    @Column(nullable = false, columnDefinition = "integer default 3")
    private Integer roleId;
    @Column(nullable = false, length = 10)
    private String staffProfileStatus;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getStaffProfileStatus() {
		return staffProfileStatus;
	}
	public void setStaffProfileStatus(String staffProfileStatus) {
		this.staffProfileStatus = staffProfileStatus;
	}
	public Staff(String username, long staffId, String password, String staffName, Integer roleId,
			String staffProfileStatus) {
		super();
		this.username = username;
		this.staffId = staffId;
		this.password = password;
		this.staffName = staffName;
		this.roleId = roleId;
		this.staffProfileStatus = staffProfileStatus;
	}
	
	@Override
	public String toString() {
		return "Staff [username=" + username + ", staffId=" + staffId + ", password=" + password + ", staffName="
				+ staffName + ", roleId=" + roleId + ", staffProfileStatus=" + staffProfileStatus + "]";
	}
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

}

