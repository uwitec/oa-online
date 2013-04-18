package com.fhi.user.vo;

import java.util.Date;
import java.util.Map;

import com.fhi.employee.vo.FhiOaEmployeeBasic;
import com.fhi.permission.vo.FhiRole;

/**
 * WmsLocationRegion generated by MyEclipse Persistence Tools
 */

public class FhiUser implements java.io.Serializable {
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String userId;
 
	private String name;
	
	private String passWord;

	private Date regDate;
	
	private Map permissionMap;
	
	private Map<String,FhiRole> role;
	
	private String flag;
	
	private String employee;
	
	private String employeeHidden;
	
	private String employeeEditHidden;
	
	private String employeeId;
	
	private FhiOaEmployeeBasic employeeClass;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public boolean Permission(String ModuleCode){
		boolean isPermission = false;
		if(permissionMap.containsKey(ModuleCode)){
			isPermission = true;
		}
		return isPermission;
	}

	public Map getPermissionMap() {
		return permissionMap;
	}
	
	/**
	 * 权限查询
	 * @param keyStr
	 * @return
	 */
	public String getPermissionStr(String keyStr) {
		return (String) this.getPermissionMap().get(keyStr);
	}

	public void setPermissionMap(Map permissionMap) {
		this.permissionMap = permissionMap;
	}

	public FhiUser(){
	}
	
	public FhiUser(String id,String userId,String name,String passWord,Date regDate){
		
		this.id = id;
		this.userId = userId;
		this.passWord = passWord;
		this.regDate = regDate;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String,FhiRole> getRole() {
		return role;
	}

	public void setRole(Map<String,FhiRole> role) {
		this.role = role;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getEmployeeHidden() {
		return employeeHidden;
	}

	public void setEmployeeHidden(String employeeHidden) {
		this.employeeHidden = employeeHidden;
	}

	public String getEmployeeEditHidden() {
		return employeeEditHidden;
	}

	public void setEmployeeEditHidden(String employeeEditHidden) {
		this.employeeEditHidden = employeeEditHidden;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public FhiOaEmployeeBasic getEmployeeClass() {
		return employeeClass;
	}

	public void setEmployeeClass(FhiOaEmployeeBasic employeeClass) {
		this.employeeClass = employeeClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}