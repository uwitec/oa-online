package com.fhi.department.service;

import java.util.List;

import com.fhi.department.condition.DepartmentCondition;
import com.fhi.department.vo.FhiOaDepartment;

public interface DepartmentIn {
	
//  查询方法
	public FhiOaDepartment getDepartmentById(String id) ; 
	public List<FhiOaDepartment> getDepartments(DepartmentCondition con) ; 
	public List<FhiOaDepartment> getDepartments() ; 
	public List<FhiOaDepartment> getDepartmentsByComId(String comId) ;
	public boolean hasDep(String comid,String depname,String depid);
//  添加方法
	public boolean addDepartment(FhiOaDepartment dep);
//	修改方法
	public boolean updateDepartment(FhiOaDepartment dep);
//	删除方法
	public boolean deleteDepartment(FhiOaDepartment dep);
	public boolean deleteDepartmentById(String id); 
	public boolean update(String sql) throws Exception ;
	public boolean deleteDepartments(String []ids)throws Exception; 
}
