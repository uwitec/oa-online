package com.fhi.utils.portmanage.service;

import java.util.List;

import com.fhi.framework.page.Condition;
import com.fhi.utils.portmanage.vo.FhiOsUtilsPort;

public interface PortManageIn {
	public List query(Condition condition);

	public List query(String hqlString);
		
	public boolean update(Object obj);
	
	public boolean save(Object obj);
	
	public FhiOsUtilsPort load(String id);

}
