package com.fhi.system.sysmsg.service;

import java.util.List;

import com.fhi.framework.page.Condition;
import com.fhi.user.vo.FhiUser;

public interface SysMsgIn {
	/**
	 * 分页查询 手动设置每页条数
	 * @param condition
	 * @return
	 */
	public List query(Condition condition,int rowsPerpage);
	
	public List query(Condition condition);

	public List query(String hqlString);

	public Object load(Class obj, String obj_id);
	
	public boolean update(Object obj);
	
	public boolean save(Object obj);
	
	
	/**
	 * 获取未读系统信息数量
	 * @return
	 */
	public Integer getInformCount(FhiUser user);
	
	/**
	 * 获取未读工作计划提醒数量
	 * @return
	 */
	public Integer getPlanCount(FhiUser user);
	
	
}
