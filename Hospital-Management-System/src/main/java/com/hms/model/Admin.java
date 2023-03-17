package com.hms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Entity
public class Admin {
    @Id
    @Column(nullable = false, length = 100)
    private String userName;
    @Column(nullable = false,length = 100)
    private String password;
    @Column(nullable = false,columnDefinition = "integer default 1")
    private Integer roleId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "Admin [userName=" + userName + ", password=" + password + ", roleId=" + roleId + "]";
	}
	public Admin(String userName, String password, Integer roleId) {
		super();
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
