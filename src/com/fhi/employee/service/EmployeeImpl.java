package com.fhi.employee.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.employee.condition.EmployeeCondition;
import com.fhi.employee.vo.FhiOaEmployeeBasic;
import com.fhi.employee.vo.FhiOaEmployeeEducation;
import com.fhi.employee.vo.FhiOaEmployeeFamily;
import com.fhi.employee.vo.FhiOaEmployeeWork;
import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.tools.SelfFileUpload;

public class EmployeeImpl extends AbstractServiceImple implements EmployeeIn {
	
	Logger logger = Logger.getLogger(EmployeeImpl.class) ;
	
    //  查询方法
	public FhiOaEmployeeBasic getEmployeeById(String id){ 
		if(id == null||"".equals(id)){return null;}
		try{
			return (FhiOaEmployeeBasic)this.dbDao.queryObjectById(FhiOaEmployeeBasic.class, id.trim()) ;
		}catch(Exception e){
			logger.error("员工对象查询失败'"+id+"'") ;
		}
		return null ;
	}

	public List<FhiOaEmployeeBasic> getEmployees(String str) {
		
		if(str==null || str.trim().equals(""))
			return null ;
		
		String hql = " from FhiOaEmployeeBasic where name like '%"+str.trim()+"%' or account like '"+str.trim()+"%' or email like '%"+str.trim()+"%'" ;
		try{
			return  this.dbDao.queryObjects(hql) ;  
		}catch(Exception e){
			logger.error("员工对象查询失败",e) ;
		}
		
		return null ;
	}
	public FhiOaEmployeeBasic getEmployeeByCode(String code){
		String hql = " from FhiOaEmployeeBasic where employeeCode='"+code.trim()+"' " ;
		try{
			List list = this.dbDao.queryObjects(hql) ;
			if(list!=null && list.size()>0){
				return (FhiOaEmployeeBasic)list.get(0) ;
			}
		}catch(Exception e){
			logger.error("员工对象查询失败",e) ;
		}
		return null ; 
	}
	 
	
	public List<FhiOaEmployeeBasic> getEmployees(EmployeeCondition con){
		try{
			return (List<FhiOaEmployeeBasic>)this.dbDao.queryObjectsToPages(this.setPageInfo(con)); 
		}catch(Exception e){
			logger.error("员工对象查询失败",e) ;
		}
		return null ; 
	}
	
	public List<FhiOaEmployeeBasic> getNoAccountEmployees(){
		String hql = " from FhiOaEmployeeBasic where (account is null) or (account='') or (lower(account)='null') ";
		try{
			return this.dbDao.queryObjects(hql) ;
		}catch(Exception e){
			logger.error("员工对象查询失败",e) ;
		}
		return null ;
	}
	 
//  添加方法
	public boolean addEmployee(FhiOaEmployeeBasic com){
		try{
			return this.dbDao.addObject(com) ;
		}catch(Exception e){
			logger.error("员工对象添加失败",e) ;
		}
		return false ; 
	}
	

	public boolean addFullEmployee(FhiOaEmployeeBasic basic,List<FhiOaEmployeeEducation> edu,List<FhiOaEmployeeFamily> family,List<FhiOaEmployeeWork> work) throws Exception{
		this.dbDao.addObject(basic) ;
		
		if(edu!=null){
		for(FhiOaEmployeeEducation one:edu){
			one.setEmployeeId(basic.getId()) ;
			this.dbDao.addObject(one) ;
		}}
		if(family!=null){
		for(FhiOaEmployeeFamily one:family){
			one.setEmployeeId(basic.getId()) ;
			this.dbDao.addObject(one) ;
		}}
		if(work!=null){
		for(FhiOaEmployeeWork one:work){
			one.setEmployeeId(basic.getId()) ;
			this.dbDao.addObject(one) ;
		}}
		return true ;
	}
	
	public boolean editFullEmployee(FhiOaEmployeeBasic basic,List<FhiOaEmployeeEducation> edu,List<FhiOaEmployeeFamily> family,List<FhiOaEmployeeWork> work) throws Exception {
		
		this.dbDao.updateObject(basic) ;
		
		List<FhiOaEmployeeEducation> existedu = this.getEducations(basic.getId()) ;
		List<FhiOaEmployeeFamily> existfamily = this.getFamilys(basic.getId()) ;
		List<FhiOaEmployeeWork> existwork = this.getWorks(basic.getId()) ;
		this.deleteObjects(existedu) ;
		this.deleteObjects(existfamily) ;
		this.deleteObjects(existwork) ;

		if(edu!=null){
		for(FhiOaEmployeeEducation one:edu){
			one.setEmployeeId(basic.getId()) ;
			this.dbDao.addObject(one) ;
		}}

		if(family!=null){
		for(FhiOaEmployeeFamily one:family){
			one.setEmployeeId(basic.getId()) ;
			this.dbDao.addObject(one) ;
		}}

		if(work!=null){
		for(FhiOaEmployeeWork one:work){
			one.setEmployeeId(basic.getId()) ;
			this.dbDao.addObject(one) ;
		} }
		return true ;
	}
	
//	修改方法
	public boolean updateEmployee(FhiOaEmployeeBasic com){
		try{
			return this.dbDao.updateObject(com) ;
		}catch(Exception e){
			logger.error("员工对象修改失败",e) ;
		}
		return false ; 
	}
//	删除方法
	public boolean deleteEmployee(FhiOaEmployeeBasic com){
		try{
			return this.dbDao.deleteObject(com) ;
		}catch(Exception e){
			logger.error("员工对象删除失败",e) ;
		}
		return false ;
	}
	public boolean deleteEmployeeById(String id,String path){
		try{
			this.deleteObjects(this.getEducations(id)) ;
			this.deleteObjects(this.getFamilys(id)) ;
			this.deleteObjects(this.getWorks(id)) ;
			FhiOaEmployeeBasic basic = this.getEmployeeById(id.trim()) ;
			SelfFileUpload  del = new SelfFileUpload() ;
			del.delete(path+basic.getHeadPic()) ;
			if(basic.getAccountPic()!=null && !basic.getAccountPic().trim().equals(""))
				del.delete(path+basic.getAccountPic()) ;
			if(basic.getCheckPic()!=null && !basic.getCheckPic().trim().equals(""))
				del.delete(path+basic.getCheckPic()) ;
			if(basic.getDegreePic()!=null && !basic.getDegreePic().trim().equals(""))
				del.delete(path+basic.getDegreePic()) ;
			if(basic.getGatewayPic()!=null && !basic.getGatewayPic().trim().equals(""))
				del.delete(path+basic.getGatewayPic()) ;
			if(basic.getGraduatePic()!=null && !basic.getGraduatePic().trim().equals(""))
				del.delete(path+basic.getGraduatePic()) ;
			if(basic.getDrivePic()!=null && !basic.getDrivePic().trim().equals(""))
				del.delete(path+basic.getDrivePic()) ;
			if(basic.getIdcardPic()!=null && !basic.getIdcardPic().trim().equals(""))
				del.delete(path+basic.getIdcardPic()) ;
		    this.dbDao.deleteObject(basic) ;
		    
		    return true ;
		}catch(Exception e){
			logger.error("员工对象删除失败",e) ;
		}
		return false ;
	}
	public boolean deleteEmployeeByCode(String code){
		try{
			return this.dbDao.deleteObject(this.getEmployeeByCode(code.trim())) ;
		}catch(Exception e){
			logger.error("员工对象删除失败",e) ;
		}
		return false ;
	}
	public boolean deleteEmployees(String []ids,String path)throws Exception{
		for(String id:ids){
			this.deleteEmployeeById(id,path) ;
		}
		return  true  ;
	} 
	
	public boolean hasEmployee(String code,String id){
		String hql = " from FhiOaEmployeeBasic where employeeCode='"+code.trim()+"'" ;
		if(id!=null && !id.trim().equals(""))
			hql += " and id!='"+id.trim()+"' " ; 
		int c = this.dbDao.count(hql) ;
		if(c>0) return true ;
		else return false ; 
	}
	
	

	public List<FhiOaEmployeeEducation> getEducations(String employeeId){
		String hql = " from FhiOaEmployeeEducation where employeeId='"+employeeId+"'" ; 
		try{
			return this.dbDao.queryObjects(hql) ;
		}catch(Exception e){
			logger.error("员工教育经历查询失败",e) ;
		}
		return  null   ;
	}
	public List<FhiOaEmployeeFamily> getFamilys(String employeeId) { 
		String hql = " from FhiOaEmployeeFamily where employeeId='"+employeeId+"'" ; 
		try{
			return this.dbDao.queryObjects(hql) ;
		}catch(Exception e){
			logger.error("员工家庭关系查询失败",e) ;
		}
		return  null   ;
	}
	public List<FhiOaEmployeeWork> getWorks(String employeeId) {
		String hql = " from FhiOaEmployeeWork where employeeId='"+employeeId+"'" ; 
		try{
			return this.dbDao.queryObjects(hql) ;
		}catch(Exception e){
			logger.error("员工工作经历查询失败",e) ;
		}
		return  null   ;
	}
	

	public boolean deleteObjects(List  list)throws Exception{
		for(Object one:list){
			this.dbDao.deleteObject(one)  ;
		}
		return true ;
	}
	
	public boolean addEmployee_edu(FhiOaEmployeeEducation edu){
		try{
			return this.dbDao.addObject(edu) ;
		}catch(Exception e){
			logger.error("添加教育信息失败", e) ;
			return false ;
		} 
	}
	public boolean addEmployee_family(FhiOaEmployeeFamily family){
		try{
			return this.dbDao.addObject(family) ;
		}catch(Exception e){
			logger.error("添加家庭信息失败", e) ;
			return false ;
		} 
	}
	public boolean addEmployee_work(FhiOaEmployeeWork work){
		try{
			return this.dbDao.addObject(work) ;
		}catch(Exception e){
			logger.error("添加工作信息失败", e) ;
			return false ;
		} 
	}
  
	public boolean updateEmployee_edu(FhiOaEmployeeEducation edu){
		try{
			return this.dbDao.updateObject(edu) ;
		}catch(Exception e){
			logger.error("修改教育信息失败", e) ;
			return false ;
		} 
	}
	public boolean updateEmployee_family(FhiOaEmployeeFamily family){
		try{
			return this.dbDao.updateObject(family) ;
		}catch(Exception e){
			logger.error("修改家庭信息失败", e) ;
			return false ;
		} 
	}
	public boolean updateEmployee_work(FhiOaEmployeeWork work){
		try{
			return this.dbDao.updateObject(work) ;
		}catch(Exception e){
			logger.error("修改工作信息失败", e) ;
			return false ;
		} 
	}
	

	public boolean deleteEmployee_edu(String id){
		try{
			String ids[] = new String[1] ;
			ids[0] =id ;
			return this.dbDao.deleteObjects(FhiOaEmployeeEducation.class, ids) ;
		}catch(Exception e){
			logger.error("删除教育信息失败", e) ;
			return false ;
		}
	}
	public boolean deleteEmployee_work(String id){
		try{
			String ids[] = new String[1] ;
			ids[0] =id ;
			return this.dbDao.deleteObjects(FhiOaEmployeeWork.class, ids) ;
		}catch(Exception e){
			logger.error("删除工作信息失败", e) ;
			return false ;
		}
	}
	public boolean deleteEmployee_family(String id){
		try{
			String ids[] = new String[1] ;
			ids[0] =id ;
			return this.dbDao.deleteObjects(FhiOaEmployeeFamily.class, ids) ;
		}catch(Exception e){
			logger.error("删除家庭信息失败", e) ;
			return false ;
		}
	}
	
	
}
