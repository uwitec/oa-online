package com.fhi.employee.service;

import java.util.List;

import com.fhi.employee.condition.EmployeeCondition;
import com.fhi.employee.vo.FhiOaEmployeeBasic;
import com.fhi.employee.vo.FhiOaEmployeeEducation;
import com.fhi.employee.vo.FhiOaEmployeeFamily;
import com.fhi.employee.vo.FhiOaEmployeeWork;

public interface EmployeeIn {
	
//  查询方法
	public FhiOaEmployeeBasic getEmployeeById(String id) ;
	public FhiOaEmployeeBasic getEmployeeByCode(String code) ;
	public List<FhiOaEmployeeBasic> getEmployees(EmployeeCondition con) ;  
	public List<FhiOaEmployeeBasic> getEmployees(String str) ; 
	public List<FhiOaEmployeeBasic> getNoAccountEmployees() ; 
	public List<FhiOaEmployeeEducation> getEducations(String comid) ; 
	public List<FhiOaEmployeeFamily> getFamilys(String comid) ; 
	public List<FhiOaEmployeeWork> getWorks(String comid) ; 
//  添加方法
	public boolean addEmployee(FhiOaEmployeeBasic com);
	public boolean addFullEmployee(FhiOaEmployeeBasic basic,List<FhiOaEmployeeEducation> edu,List<FhiOaEmployeeFamily> family,List<FhiOaEmployeeWork> work) throws Exception  ;
	public boolean addEmployee_edu(FhiOaEmployeeEducation edu);
	public boolean addEmployee_family(FhiOaEmployeeFamily family);
	public boolean addEmployee_work(FhiOaEmployeeWork work);
//	修改方法
	public boolean updateEmployee(FhiOaEmployeeBasic com);
	public boolean updateEmployee_edu(FhiOaEmployeeEducation edu);
	public boolean updateEmployee_family(FhiOaEmployeeFamily family);
	public boolean updateEmployee_work(FhiOaEmployeeWork work);
	public boolean editFullEmployee(FhiOaEmployeeBasic basic,List<FhiOaEmployeeEducation> edu,List<FhiOaEmployeeFamily> family,List<FhiOaEmployeeWork> work) throws Exception  ;
	
//	删除方法
	public boolean deleteEmployee(FhiOaEmployeeBasic com);
	public boolean deleteEmployee_edu(String id);
	public boolean deleteEmployee_work(String id);
	public boolean deleteEmployee_family(String id);
	public boolean deleteEmployeeById(String id,String path);
	public boolean deleteEmployeeByCode(String code);
	public boolean deleteEmployees(String []ids,String path)throws Exception; 
	public boolean deleteObjects(List  list)throws Exception; 
	
}
