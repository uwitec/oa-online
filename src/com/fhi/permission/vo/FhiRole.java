package com.fhi.permission.vo;

public class FhiRole implements java.io.Serializable{
	private String id;
	private String roleName;
	private String roleCode;
	private String pid;
	private String isPid;
	
	public FhiRole(){
		
	}
	
	public FhiRole(String id,String roleName,String roleCode){
		this.id = id;
		this.roleName = roleName;
		this.roleCode = roleCode;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getIsPid() {
		return isPid;
	}

	public void setIsPid(String isPid) {
		this.isPid = isPid;
	}
	
	
}
