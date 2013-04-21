package com.fhi.department.action ;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.company.service.CompanyIn;
import com.fhi.department.condition.DepartmentCondition;
import com.fhi.department.service.DepartmentIn;
import com.fhi.department.vo.FhiOaDepartment;
import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.tools.SelfToken;
import com.fhi.framework.utils.date.DataUtils;
import com.fhi.user.vo.FhiUser;


public class DepartmentAction extends DispatchAction  { 
	
	private static Logger logger = Logger.getLogger(DepartmentAction.class);	
	private DepartmentIn depService;
	private CompanyIn companyService; 
	private DepartmentCondition depCondition ;
	private FhiUser user; 
	private HttpSession session ;
	DepartmentForm depForm = new DepartmentForm() ;
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		depForm = (DepartmentForm)form;
		session = request.getSession(true) ; 
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}   
		return super.execute(mapping, form, request, response);
	}  
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 
		 
		List deplist =null ; 
		try{  
			deplist = depService.getDepartments(depCondition.setDepHql(depForm)); 
		}catch(Exception e){
			logger.error("部门信息查询错误",e) ;
		}  
		request.setAttribute("deplist", deplist) ; 
		request.setAttribute("form", depForm) ;
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, depCondition.getPageInfo()) ;
		session.setAttribute("self.department.Token",java.util.UUID.randomUUID().toString()); 
		return mapping.findForward("list") ;
	}  

	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		session.setAttribute("self.department.Token",java.util.UUID.randomUUID().toString());
		request.setAttribute("comlist", companyService.getCompanies()) ; 
		return mapping.findForward("add") ;
	}
	
	public ActionForward preEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id") ; 
		FhiOaDepartment dep = depService.getDepartmentById(id) ;
		List deplist = depService.getDepartmentsByComId(dep.getComId()); 
		request.setAttribute("deplist", deplist) ; 
		request.setAttribute("department", dep) ; 
		request.setAttribute("comlist", companyService.getCompanies()) ; 
		session.setAttribute("self.department.Token",java.util.UUID.randomUUID().toString()); 
		return mapping.findForward("edit") ;
	}
	
	public ActionForward addDep(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(!SelfToken.isValid(request, "self.department.Token")){
			return mapping.findForward("relist") ;
		} 
		depForm.getDepartment().setAddDate(new Date()) ;
		depForm.getDepartment().setCreator(user.getUserId()) ;
		depForm.getDepartment().setContractTime(DataUtils.getDateByString(depForm.getContractTime())) ;
		String id = java.util.UUID.randomUUID().toString() ;
		depForm.getDepartment().setId(id) ;
		try{
			boolean ret = depService.addDepartment(depForm.getDepartment()) ;
			if(ret==true){
				session.setAttribute("retDep", "添加成功") ;
				session.setAttribute("sessionDepId", id) ;
			} 
		}catch(Exception e){
			logger.error("部门信息添加错误",e) ;
			session.setAttribute("retDep", "添加失败") ;
		}   
		return mapping.findForward("relist") ;
	}
	
	
	public ActionForward editDep(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(!SelfToken.isValid(request, "self.department.Token")){
			return mapping.findForward("relist") ;
		} 
		depForm.getDepartment().setAddDate(new Date()) ;
		depForm.getDepartment().setCreator(user.getUserId()) ;
		depForm.getDepartment().setContractTime(DataUtils.getDateByString(depForm.getContractTime())) ;
		try{
			boolean ret = depService.updateDepartment(depForm.getDepartment()) ;
			if(ret==true){
				session.setAttribute("retDep", "修改成功") ;
				session.setAttribute("sessionDepId", depForm.getDepartment().getId()) ; 
			} 
		}catch(Exception e){
			logger.error("部门信息修改错误",e) ;
			session.setAttribute("retDep", "修改失败") ;
		}   
		return mapping.findForward("relist") ;
	}
	
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(!SelfToken.isValid(request, "self.department.Token")){
			return mapping.findForward("relist") ;
		}  
		try{ 
			String []ids = depForm.getIds() ;
			if(ids==null || ids.length<=0){
				ids = new String[1] ;
				ids[0] = request.getParameter("id") ;
			}  
			boolean ret = depService.deleteDepartments(ids);
			if(ret==true)
				session.setAttribute("retDep", "删除成功") ;
		}catch(Exception e){
			logger.error("部门信息删除错误",e) ;
			session.setAttribute("retDep", "删除失败") ;
		}   
		return mapping.findForward("relist") ;
	}
	 

	public void setDepService(DepartmentIn depService) {
		this.depService = depService;
	}


	public void setCompanyService(CompanyIn companyService) {
	    this.companyService = companyService;
	}

	public void setDepCondition(DepartmentCondition depCondition) {
		this.depCondition = depCondition;
	} 
}