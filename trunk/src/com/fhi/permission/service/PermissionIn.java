package com.fhi.permission.service;

import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import com.fhi.permission.vo.FhiModule;
import com.fhi.permission.vo.FhiRole;
import com.fhi.permission.vo.FhiRolePermission;
import com.fhi.user.vo.FhiUser;

public interface PermissionIn {
	public boolean addModule(FhiModule obj);
	public boolean addRM(FhiRolePermission obj);
	public boolean addRole(FhiRole obj);
	public Map findPermission(FhiUser ws);
	public Map findPermission(String userId);
	public List getAllRole();
	public List getAllPermission();
	public List getAllMod(ActionForm from);
	public List query();
	public boolean queryRoleUser(String id);
	public boolean delThisRole(String id,String isPid,String pid);
	public List<String> getModel(String isModify);
	public List getModel();
	/**
	 * 返回所有父角色ID
	 * @param FhiRole roleId
	 * @return
	 */
	public List<String> queryRoleParentID(String roleId);
	
	/**
	 * 返回所有父角色ID
	 * @param FhiRole role
	 * @return
	 */
	public List<String> queryRoleParentID(FhiRole role);
	public void updatePid(String pid);
	public FhiRole getRoleName(String id);
	public void updateRole(FhiRole fr);
	public List getAllPermissionById(String id);
	public void delRolePermissionByCode(List stocks);
}