package com.fhi.custom.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.custom.condition.CustomCondition;
import com.fhi.custom.vo.FhiOaCustom;
import com.fhi.department.service.DepartmentIn;
import com.fhi.framework.abstractservice.AbstractServiceImple;

public class CustomImpl extends AbstractServiceImple implements CustomIn {
	
	Logger logger = Logger.getLogger(CustomImpl.class) ; 
	private DepartmentIn depService;
	
//  查询方法
	public FhiOaCustom getCustomById(String id){ 
		try{
			return (FhiOaCustom)this.dbDao.queryObjectById(FhiOaCustom.class, id.trim()) ;
		}catch(Exception e){
			logger.error("公司对象查询失败",e) ;
		}
		return null ;
	}
	public FhiOaCustom getCustomByCode(String code){
		String hql = " from FhiOaCustom where comCode='"+code.trim()+"' " ;
		try{
			List list = this.dbDao.queryObjects(hql) ;
			if(list!=null && list.size()>0){
				return (FhiOaCustom)list.get(0) ;
			}
		}catch(Exception e){
			logger.error("公司对象查询失败",e) ;
		}
		return null ; 
	}
	public List<FhiOaCustom> getCustoms(CustomCondition con){
		try{
			return (List<FhiOaCustom>)this.dbDao.queryObjectsToPages(this.setPageInfo(con)); 
		}catch(Exception e){
			logger.error("公司对象查询失败",e) ;
		}
		return null ; 
	}
	

	public List<FhiOaCustom> getCustoms() {
		String hql = " from FhiOaCustom " ;
		try{
			return this.dbDao.queryObjects(hql) ; 
		}catch(Exception e){
			logger.error("公司对象查询失败",e) ;
			return null ;
		}
	}
//  添加方法
	public boolean addCustom(FhiOaCustom com){
		try{
			return this.dbDao.addObject(com) ;
		}catch(Exception e){
			logger.error("公司对象添加失败",e) ;
		}
		return false ; 
	}
//	修改方法
	public boolean updateCustom(FhiOaCustom com){
		try{
			 this.dbDao.updateObject(com) ; 
			 return true ;
		}catch(Exception e){
			logger.error("公司对象修改失败",e) ;
		}
		return false ; 
	}
//	删除方法
	public boolean deleteCustom(FhiOaCustom com){
		try{
			return this.dbDao.deleteObject(com) ;
		}catch(Exception e){
			logger.error("公司对象删除失败",e) ;
		}
		return false ;
	}
	public boolean deleteCustomById(String id){
		try{
			return this.dbDao.deleteObject(this.getCustomById(id.trim())) ;
		}catch(Exception e){
			logger.error("公司对象删除失败",e) ;
		}
		return false ;
	}
	public boolean deleteCustomByCode(String code){
		try{
			return this.dbDao.deleteObject(this.getCustomByCode(code.trim())) ;
		}catch(Exception e){
			logger.error("公司对象删除失败",e) ;
		}
		return false ;
	}
	public boolean deleteCustoms(String []ids)throws Exception{
        this.dbDao.deleteObjects(FhiOaCustom.class, ids) ; 
        return true ;
	} 
	
	public boolean hasCustom(String name,String id){
		String hql = " from FhiOaCustom where fullName='"+name.trim()+"'" ;
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
