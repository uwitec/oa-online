package com.fhi.custom.service;

import java.util.List;

import com.fhi.custom.condition.CustomCondition;
import com.fhi.custom.vo.FhiOaCustom;

public interface CustomIn {
	
//  查询方法
	public FhiOaCustom getCustomById(String id) ;
	public FhiOaCustom getCustomByCode(String code) ;
	public List<FhiOaCustom> getCustoms(CustomCondition con) ; 
	public List<FhiOaCustom> getCustoms() ; 
//  添加方法
	public boolean addCustom(FhiOaCustom com);
//	修改方法
	public boolean updateCustom(FhiOaCustom com);
//	删除方法
	public boolean deleteCustom(FhiOaCustom com);
	public boolean deleteCustomById(String id);
	public boolean deleteCustomByCode(String code);
	public boolean deleteCustoms(String []ids)throws Exception;
	
}
