package com.fhi.client.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fhi.client.vo.FhiClientUser;
import com.fhi.framework.page.Condition;

public interface ClientIn {	
	public List query(Condition condition);

	public List query(String hqlString);

	public Object load(Class obj, String obj_id);
	
	public boolean update(Object obj);
	
	public boolean save(Object obj);
	
	/**
	 * 获取客户IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request);
	
	/**
	 * 客户用户登录查询
	 * @param userId
	 * @param password
	 * @return
	 */
	public FhiClientUser getClient(String userId,String password);
}
