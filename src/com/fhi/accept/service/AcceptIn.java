package com.fhi.accept.service;

import java.util.List;

import com.fhi.accept.vo.FhiOaAccept;
import com.fhi.accept.vo.FhiOaRetmoney;
import com.fhi.framework.page.Condition;
import com.fhi.system.sysmsg.vo.SysMsgSetup;

public interface AcceptIn {
	
//  查询方法
	public FhiOaAccept getAcceptById(String id) ;
	public List<SysMsgSetup> getEmergenceByAcceptId(String acceptId) ;
	public List<FhiOaRetmoney> getRetMoneyByAcceptId(String acceptId) ;
	public FhiOaAccept getAcceptByCode(String code) ;
	public List<FhiOaAccept> getAccepts(Condition con) ;  
	public List query(Condition condition);
//  添加方法
	public boolean addAccept(FhiOaAccept accept,List<SysMsgSetup> emergences, List<FhiOaRetmoney> moneys)throws Exception;
	
//	修改方法
	public boolean updateAccept(FhiOaAccept accept,List<SysMsgSetup> emergences, 
			 List<FhiOaRetmoney> moneys,List<SysMsgSetup> emergences_del, List<FhiOaRetmoney> moneys_del)throws Exception;
	public boolean setState(SysMsgSetup state);
//	删除方法
//	public boolean deleteAccept(FhiOaAccept accept)throws Exception;
	public boolean deleteAcceptById(String id)throws Exception; 
	public boolean deleteAccepts(String []ids)throws Exception;
	public List query(String hqlString);
	public Object load(Class obj, String obj_id);
	public boolean update(Object obj);
	public boolean save(Object obj);
	
}
