package com.fhi.user.service;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.fhi.index.form.IndexForm;
import com.fhi.permission.vo.FhiRole;
import com.fhi.user.vo.FhiUser;

public interface UserIn {
	
	public FhiUser queryById(String id);
	
	public List<FhiUser> query(ActionForm form);
	
	/**
	 * 只返回叶子节点
	 * @return
	 */
	public List query();
	
	/**
	 * 返回所有角色:包括叶子和父节点.
	 * @return
	 */
	public List queryAllNode();
	
	public List<FhiRole> getSelect1Role(List perlist,List userPerList);
	
	public List<FhiRole> getSelect2Role(List perlist,List userPerList);
	
	public List queryPermission(String userName);
	
	public List queryPermissionId(String userId);
	
	public FhiUser load(String Id);
	
	public boolean resave(FhiUser obj);

	public List<FhiUser> requery(IndexForm form);
	
	public List<FhiUser> requeryName(ActionForm form);
	
	public boolean update(FhiUser form);

	public List<FhiUser> load();
	
	public boolean delUser(String[] id);

	public List queryById(String userName, String passWord);

	public String queryWebService(FhiUser fu);
	
}
