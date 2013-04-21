package com.fhi.user.form;

import org.apache.struts.action.ActionForm;

import com.fhi.user.vo.FhiUser;


public class AddUserForm extends ActionForm{
	
	private String permission;
	private String PermissionEdit;
	private Integer pageNo;
	private FhiUser wu = new FhiUser();
	
	private String queryUserId;
	private String queryUserName;
	
	
	public String getPermissionEdit() {
		return PermissionEdit;
	}
	
	public String[] getPermissionEditString() {
		return PermissionEdit.split("\\|");
	}
	
	public void setPermissionEdit(String permissionEdit) {
		PermissionEdit = permissionEdit;
	}
	public String getPermission() {
		return permission;
	}
	
	public String[] getPermissionString(){
		return permission.split("\\|");
	}
	
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Integer getPageNo() {
		if (pageNo == null || pageNo == 0) {
			pageNo = 1;
		}
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		if (pageNo == null || pageNo == 0) {
			pageNo = 1;
		}
		this.pageNo = pageNo;
	}
	
	public FhiUser getWu() {
		return wu;
	}
	public void setWu(FhiUser wu) {
		this.wu = wu;
	}

	public String getQueryUserId() {
		return queryUserId;
	}

	public void setQueryUserId(String queryUserId) {
		this.queryUserId = queryUserId;
	}

	public String getQueryUserName() {
		return queryUserName;
	}

	public void setQueryUserName(String queryUserName) {
		this.queryUserName = queryUserName;
	}
}
