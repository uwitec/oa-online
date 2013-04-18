package com.fhi.information.document.service;

import java.util.List;

import com.fhi.framework.page.Condition;

public interface DocumentIn {	
	public List query(Condition condition);

	public List query(String hqlString);

	public Object load(Class obj, String obj_id);
	
	public boolean update(Object obj);
	
	public boolean save(Object obj);
}
