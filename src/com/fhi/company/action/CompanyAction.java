package com.fhi.company.action ;

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

import com.fhi.company.condition.CompanyCondition;
import com.fhi.company.service.CompanyIn;
import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.tools.SelfToken;
import com.fhi.framework.utils.date.DataUtils;
import com.fhi.user.vo.FhiUser;

public class CompanyAction extends DispatchAction  { 
	
	private static Logger logger = Logger.getLogger(CompanyAction.class);	
	private CompanyIn companyService;
	private CompanyCondition companyCondition;
	private FhiUser user; 
	private HttpSession session ;
	CompanyForm comForm = new CompanyForm() ;
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		comForm = (CompanyForm)form;
		session = request.getSession(true) ; 
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}   
		return super.execute(mapping, form, request, response);
	} 
	
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List list =null ;
		try{
		    list = companyService.getCompanies(companyCondition.setCompanyHql(comForm)) ;
		}catch(Exception e){
			logger.error("公司信息查询错误",e) ;
		} 
		request.setAttribute("list", list) ;
		request.setAttribute("form", comForm) ;
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, companyCondition.getPageInfo()) ;
		session.setAttribute("self.company.Token",java.util.UUID.randomUUID().toString());
		return mapping.findForward("list") ;
	}
	
	

	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		session.setAttribute("self.company.Token",java.util.UUID.randomUUID().toString());
		request.setAttribute("comlist",companyService.getCompanies()) ;
		return mapping.findForward("addcom") ;
	}
	
	
	public ActionForward addCom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 

		if(!SelfToken.isValid(request, "self.company.Token")){
			return mapping.findForward("relist") ;
		}
		try{
			comForm.getCompany().setAddDate(new Date()) ;
			comForm.getCompany().setCreator(user.getUserId()) ;
			comForm.getCompany().setContractTime(DataUtils.getDateByString(comForm.getContractTime())) ;
			companyService.addCompany(comForm.getCompany()) ;
			session.setAttribute("comResult","添加成功"); 
		}catch(Exception e){
			logger.error("添加公司信息失败",e) ;
			session.setAttribute("comResult","添加失败"); 
		}  
		session.setAttribute("self.company.Token",java.util.UUID.randomUUID().toString()); 
		session.setAttribute("sessionFullName",comForm.getCompany().getFullName());  
		return mapping.findForward("relist") ;
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 

		if(!SelfToken.isValid(request, "self.company.Token")){
			return mapping.findForward("relist") ;
		}
		try{  
			String []ids = comForm.getIds() ;
			if(ids==null){
				ids = new String[1] ;
				ids[0] = request.getParameter("id") ;
			}
			boolean ret = companyService.deleteCompanies(ids) ;
			if(ret==true)
				session.setAttribute("comResult", "删除成功") ;
			else
				session.setAttribute("comResult", "删除失败") ;
		}catch(Exception e){
			logger.error("删除公司信息失败",e) ;
			session.setAttribute("comResult", "删除失败") ;
		}  
		session.setAttribute("self.company.Token",java.util.UUID.randomUUID().toString());   
		return mapping.findForward("relist") ;
	} 

	public ActionForward preEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id") ; 
		request.setAttribute("comOne",companyService.getCompanyById(id)) ;
		request.setAttribute("comlist",companyService.getCompanies()) ;
		session.setAttribute("self.company.Token",java.util.UUID.randomUUID().toString()); 
		return mapping.findForward("updateCom") ;
	} 
	
	public ActionForward editCom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 

		if(!SelfToken.isValid(request, "self.company.Token")){
			return mapping.findForward("relist") ;
		}
		try{
			comForm.getCompany().setAddDate(new Date()) ;
			comForm.getCompany().setCreator(user.getUserId()) ;
			comForm.getCompany().setContractTime(DataUtils.getDateByString(comForm.getContractTime())) ;
			boolean ret = companyService.updateCompany(comForm.getCompany()) ; 
			if(ret==true)
				session.setAttribute("comResult", "修改成功") ;
			else
				session.setAttribute("comResult", "修改失败") ;
		}catch(Exception e){
			logger.error("修改公司信息失败",e) ;
			session.setAttribute("comResult", "修改失败") ;
		}  
		session.setAttribute("self.company.Token",java.util.UUID.randomUUID().toString()); 
		session.setAttribute("sessionFullName",comForm.getCompany().getFullName());  
		return mapping.findForward("relist") ;
	}
	
	public void setCompanyCondition(CompanyCondition companyCondition) {
		this.companyCondition = companyCondition;
	}


	public void setCompanyService(CompanyIn companyService) {
		this.companyService = companyService;
	}
	
}