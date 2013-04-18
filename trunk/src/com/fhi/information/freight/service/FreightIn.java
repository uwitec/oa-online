package com.fhi.information.freight.service;

import java.util.List;

import com.fhi.framework.page.Condition;

public interface FreightIn {	
	public List query(Condition condition);
	
	/**
	 * 分页查询 手动设置每页条数
	 * @param condition
	 * @return
	 */
	public List query(Condition condition,int rowsPerpage);

	public List query(String hqlString);

	public Object load(Class obj, String obj_id);
	
	public boolean update(Object obj);
	
	public boolean save(Object obj);
}
