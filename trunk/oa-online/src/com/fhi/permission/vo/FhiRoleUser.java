package com.fhi.permission.vo;

public class FhiRoleUser implements java.io.Serializable{
	private String id;
	private String userName;
	private String roleCode;
	
	public FhiRoleUser(){
		
	}
	public FhiRoleUser(String id,String userName,String roleCode){
		this.id = id;
		this.userName = userName;
		this.roleCode = roleCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
