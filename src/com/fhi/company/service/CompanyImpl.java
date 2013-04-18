package com.fhi.company.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.company.condition.CompanyCondition;
import com.fhi.company.vo.FhiOaCompany;
import com.fhi.department.service.DepartmentIn;
import com.fhi.framework.abstractservice.AbstractServiceImple;

public class CompanyImpl extends AbstractServiceImple implements CompanyIn {
	
	Logger logger = Logger.getLogger(CompanyImpl.class) ; 
	private DepartmentIn depService;
	
//  查询方法
	public FhiOaCompany getCompanyById(String id){ 
		try{
			return (FhiOaCompany)this.dbDao.queryObjectById(FhiOaCompany.class, id.trim()) ;
		}catch(Exception e){
			logger.error("公司对象查询失败",e) ;
		}
		return null ;
	}
	public FhiOaCompany getCompanyByCode(String code){
		String hql = " from FhiOaCompany where comCode='"+code.trim()+"' " ;
		try{
			List list = this.dbDao.queryObjects(hql) ;
			if(list!=null && list.size()>0){
				return (FhiOaCompany)list.get(0) ;
			}
		}catch(Exception e){
			logger.error("公司对象查询失败",e) ;
		}
		return null ; 
	}
	public List<FhiOaCompany> getCompanies(CompanyCondition con){
		try{
			return (List<FhiOaCompany>)this.dbDao.queryObjectsToPages(this.setPageInfo(con)); 
		}catch(Exception e){
			logger.error("公司对象查询失败",e) ;
		}
		return null ; 
	}
	

	public List<FhiOaCompany> getCompanies() {
		String hql = " from FhiOaCompany " ;
		try{
			return this.dbDao.queryObjects(hql) ; 
		}catch(Exception e){
			logger.error("公司对象查询失败",e) ;
			return null ;
		}
	}
//  添加方法
	public boolean addCompany(FhiOaCompany com){
		try{ 
			return this.dbDao.addObject(com) ;
		}catch(Exception e){
			logger.error("公司对象添加失败",e) ;
		}
		return false ; 
	}
//	修改方法
	public boolean updateCompany(FhiOaCompany com){
		try{
			 this.dbDao.updateObject(com) ;
			 String sql = " update fhi_oa_department set ComName='"+com.getFullName()+"' where ComId='"+com.getId()+"'" ;
			 this.jdbcDbDao.update(sql) ;
			 sql = " update fhi_oa_employee_basic set CompanyName='"+com.getFullName()+"' where CompanyId='"+com.getId()+"'" ;
			 this.jdbcDbDao.update(sql) ;
			 return true ;
		}catch(Exception e){
			logger.error("公司对象修改失败",e) ;
		}
		return false ; 
	}
//	删除方法
	public boolean deleteCompany(FhiOaCompany com){
		try{
			return this.dbDao.deleteObject(com) ;
		}catch(Exception e){
			logger.error("公司对象删除失败",e) ;
		}
		return false ;
	}
	public boolean deleteCompanyById(String id){
		try{
			return this.dbDao.deleteObject(this.getCompanyById(id.trim())) ;
		}catch(Exception e){
			logger.error("公司对象删除失败",e) ;
		}
		return false ;
	}
	public boolean deleteCompanyByCode(String code){
		try{
			return this.dbDao.deleteObject(this.getCompanyByCode(code.trim())) ;
		}catch(Exception e){
			logger.error("公司对象删除失败",e) ;
		}
		return false ;
	}
	public boolean deleteCompanies(String []ids)throws Exception{
        this.dbDao.deleteObjects(FhiOaCompany.class, ids) ;
        for(String id:ids){ 
        	logger.debug(" delete from fhi_oa_department where ComId='"+id+"' ") ;
        	depService.update(" delete from fhi_oa_department where ComId='"+id+"' ") ;
        }
        return true ;
	} 
	
	public boolean hasCompany(String name,String id){
		String hql = " from FhiOaCompany where fullName='"+name.trim()+"'" ;
		if(id!=null && !id.trim().equals(""))
			hql += " and id!='"+id.trim()+"' " ; 
		int c = this.dbDao.count(hql) ;
		if(c>0) return true ;
		else return false ;
		
	}
	public void setDepService(DepartmentIn depService) {
		this.depService = depService;
	}
}
