package com.fhi.department.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fhi.department.condition.DepartmentCondition;
import com.fhi.department.vo.FhiOaDepartment;
import com.fhi.framework.abstractservice.AbstractServiceImple;

public class DepartmentImpl extends AbstractServiceImple implements DepartmentIn {
	
	Logger logger = Logger.getLogger(DepartmentImpl.class) ;  
	
//  查询方法
	public FhiOaDepartment getDepartmentById(String id){ 
		try{
			return (FhiOaDepartment)this.dbDao.queryObjectById(FhiOaDepartment.class, id.trim()) ;
		}catch(Exception e){
			logger.error("部门对象查询失败",e) ;
		}
		return null ;
	} 
	public List<FhiOaDepartment> getDepartments(DepartmentCondition con){
		try{
			return (List<FhiOaDepartment>)this.dbDao.queryObjectsToPages(this.setPageInfo(con)); 
		}catch(Exception e){
			logger.error("部门对象查询失败",e) ;
		}
		return null ; 
	}
	

	public List<FhiOaDepartment> getDepartmentsByComId(String comId){
		String hql = " from FhiOaDepartment where comId='"+comId.trim()+"'" ;
		try{
			return this.dbDao.queryObjects(hql) ; 
		}catch(Exception e){
			logger.error("部门对象查询失败",e) ;
			return null ;
		}
	}
	

	public List<FhiOaDepartment> getDepartments() {
		String hql = " from FhiOaDepartment " ;
		try{
			return this.dbDao.queryObjects(hql) ; 
		}catch(Exception e){
			logger.error("部门对象查询失败",e) ;
			return null ;
		}
	}
	
	public boolean hasDep(String comid,String depname,String depid){
		String hql = "  from FhiOaDepartment where comId='"+comid.trim()+"' and depName='"+depname.trim()+"'" ;
		if(depid!=null && !depid.trim().equals("")){
			hql += " and id!='" +depid.trim()+"' " ;
		} 
		if(this.dbDao.count(hql)>0) 
		    return true ;
		else
			return false ; 
	}
//  添加方法
	public boolean addDepartment(FhiOaDepartment dep){
		try{
			return this.dbDao.addObject(dep) ;
		}catch(Exception e){
			logger.error("部门对象添加失败",e) ;
		}
		return false ; 
	}
//	修改方法
	public boolean updateDepartment(FhiOaDepartment dep){
		try{
			 this.dbDao.updateObject(dep) ;
			 String sql = " update fhi_oa_employee_basic set DepName='"+dep.getDepName()+"' where DepId='"+dep.getId()+"'" ;
			 this.jdbcDbDao.update(sql) ;
			 return true ;
		}catch(Exception e){
			logger.error("部门对象修改失败",e) ;
		}
		return false ; 
	}
//	删除方法
	public boolean deleteDepartment(FhiOaDepartment dep){
		try{
			return this.dbDao.deleteObject(dep) ;
		}catch(Exception e){
			logger.error("部门对象删除失败",e) ;
		}
		return false ;
	}
	
	
   //	jdbc批量操作方法
	public boolean update(String sql) throws Exception {
		try{
			this.jdbcDbDao.update(sql) ;
		    return true ; 
		}catch(Exception e){
			logger.error("部门对象批量操作失败",e) ;
			throw new Exception("部门对象批量操作失败") ;
		}  
	}
	
	
	public boolean deleteDepartmentById(String id){
		try{
			return this.dbDao.deleteObject(this.getDepartmentById(id.trim())) ;
		}catch(Exception e){
			logger.error("部门对象删除失败",e) ;
		}
		return false ;
	} 
	public boolean deleteDepartments(String []ids)throws Exception{
		return this.dbDao.deleteObjects(FhiOaDepartment.class, ids) ;
	} 
	
	public boolean hasDepartment(String name,String depId,String comId){
		String hql = " from FhiOaDepartment where depName='"+name.trim()+"' and comId='"+comId.trim()+"'" ;
		if(depId!=null && !depId.trim().equals(""))
			hql += " and id!='"+depId.trim()+"' " ; 
		int c = this.dbDao.count(hql) ;
		if(c>0) return true ;
		else return false ; 
	}
	public List<String> getDeparts(String comId){
		String hql = " from FhiOaDepartment where comId='"+comId.trim()+"' " ;
		List<String> ret = new ArrayList<String>() ;
		try{
			List<FhiOaDepartment> list = this.dbDao.queryObjects(hql) ;
			if(list!=null && list.size()>0){ 
				for(FhiOaDepartment dep:list){
					ret.add(dep.getId()+"|"+dep.getDepName()) ;
				}
			}
			return ret ;
		}catch(Exception e){
			logger.error("部门对象查询失败",e) ;
			return null ;
		} 
	} 
	/**
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public List<FhiOaDepartment> getDeparts1(String comId){
		StringBuffer  hql = new StringBuffer("from FhiOaDepartment where 1=1");
		if(StringUtils.isNotBlank(comId)){
			hql.append(" and comId='"+comId.trim()+"'");
		}
		 
		try{
			List<FhiOaDepartment> list = this.dbDao.queryObjects(hql.toString()) ;
			 
			return list ;
		}catch(Exception e){
			logger.error("部门对象查询失败",e) ;
			return null ;
		} 
	} 
}
