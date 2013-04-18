package com.fhi.permission.condition;

import com.fhi.framework.page.Condition;
import com.fhi.permission.form.PermissionForm;
import com.fhi.user.vo.FhiUser;

public class PermissionCondition extends Condition{
	
	public void setRegionQueryHql(String UserName){
		StringBuilder sql = new StringBuilder("from FhiRoleUser where 1=1 and userName='321'");
		this.setHqlString(sql.toString());
	}
	
	public void setRoleQueryHql(){
		StringBuilder sql = new StringBuilder("from FhiRole where 1=1 ");
		this.setHqlString(sql.toString());
	}
	
	public void setPermissionQueryHql(){
		StringBuilder sql = new StringBuilder("from FhiModule where 1=1 and isPid=1 ODER BY priority ASC");
		this.setHqlString(sql.toString());
	}
	
	
	
	public void setModQueryHql(PermissionForm form){
		StringBuilder sql = new StringBuilder("from FhiModule where 1=1 ");
		this.setPageNo(form.getPageNo());
		System.out.println(form.getPageNo());
		this.setHqlString(sql.toString());
	}

	public void setQueryRoleUser(String id) {
		StringBuilder sql = new StringBuilder("from FhiRoleUser where roleCode = '"+id+"'");
		this.setHqlString(sql.toString());
	}
	public void setQueryRoleUserByName(String name) {
		StringBuilder sql = new StringBuilder("from FhiRoleUser where userName = '"+name+"'");
		this.setHqlString(sql.toString());
	}
	
	public void setQuerRolePermission(String id) {
		StringBuilder sql = new StringBuilder("from FhiRolePermission where roleCode = '"+id+"'");
		this.setHqlString(sql.toString());
		
	}

	public void setRolePermissionQueryHql(FhiUser ws) {
		StringBuilder sql = new StringBuilder("from FhiRolePermission where roleCode = '"+ws+"'");
		this.setHqlString(sql.toString());
	}

	public void setHqlStringPermission(String id) {
		StringBuilder sql = new StringBuilder("from FhiRolePermission where roleCode = '"+id+"'");
		this.setHqlString(sql.toString());
	}
	
	public void setHqlStringPermissionMod() {
		StringBuilder sql = new StringBuilder("from FhiModule where 1=1 and isPid = 1");
		this.setHqlString(sql.toString());
	}

	public void setModelPidQueryHql() {
		StringBuilder sql = new StringBuilder("from FhiModule where 1=1 and isPid <> 1");
		this.setHqlString(sql.toString());
	}
}
