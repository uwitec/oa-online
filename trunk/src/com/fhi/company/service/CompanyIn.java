package com.fhi.company.service;

import java.util.List;

import com.fhi.company.condition.CompanyCondition;
import com.fhi.company.vo.FhiOaCompany;

public interface CompanyIn {
	
//  查询方法
	public FhiOaCompany getCompanyById(String id) ;
	public FhiOaCompany getCompanyByCode(String code) ;
	public List<FhiOaCompany> getCompanies(CompanyCondition con) ; 
	public List<FhiOaCompany> getCompanies() ; 
//  添加方法
	public boolean addCompany(FhiOaCompany com);
//	修改方法
	public boolean updateCompany(FhiOaCompany com);
//	删除方法
	public boolean deleteCompany(FhiOaCompany com);
	public boolean deleteCompanyById(String id);
	public boolean deleteCompanyByCode(String code);
	public boolean deleteCompanies(String []ids)throws Exception;
	
}
