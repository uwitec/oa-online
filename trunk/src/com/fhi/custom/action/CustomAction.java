package com.fhi.custom.action ;

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

import com.fhi.custom.condition.CustomCondition;
import com.fhi.custom.service.CustomIn;
import com.fhi.custom.vo.FhiOaCustom;
import com.fhi.custom.vo.PoiInData;
import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.tools.SelfToken;
import com.fhi.framework.utils.date.DataUtils;
import com.fhi.user.vo.FhiUser;

public class CustomAction extends DispatchAction  { 
	
	private static Logger logger = Logger.getLogger(CustomAction.class);	
	private CustomIn customService;
	private CustomCondition customCondition;
	private FhiUser user; 
	private HttpSession session ;
	CustomForm comForm = new CustomForm() ;
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		comForm = (CustomForm)form;
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
		    list = customService.getCustoms(customCondition.setCustomHql(comForm,user)) ;
		}catch(Exception e){
			logger.error("公司信息查询错误",e) ;
		} 
		request.setAttribute("list", list) ;
		request.setAttribute("form", comForm) ;
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, customCondition.getPageInfo()) ;
		session.setAttribute("self.custom.Token",java.util.UUID.randomUUID().toString());
		return mapping.findForward("list") ;
	}
	
	public ActionForward getCust(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List list =null ;
		try{
		    list = customService.getCustoms(customCondition.setCustomHql(comForm,user)) ;
		}catch(Exception e){
			logger.error("公司信息查询错误",e) ;
		} 
		request.setAttribute("list", list) ;
		request.setAttribute("form", comForm) ;
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, customCondition.getPageInfo()) ; 
		return mapping.findForward("getcust") ;
	}
	
	

	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		session.setAttribute("self.custom.Token",java.util.UUID.randomUUID().toString());
		request.setAttribute("comlist",customService.getCustoms()) ;
		return mapping.findForward("addcom") ;
	}
	
	
	public ActionForward addCom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 

		if(!SelfToken.isValid(request, "self.custom.Token")){
			return mapping.findForward("relist") ;
		}
		try{
			comForm.getCustom().setAddDate(new Date()) ;
			comForm.getCustom().setCreator(user.getUserId()) ;
			comForm.getCustom().setContractTime(DataUtils.getDateByString(comForm.getContractTime())) ;
			customService.addCustom(comForm.getCustom()) ;
			session.setAttribute("comResult","添加成功"); 
		}catch(Exception e){
			logger.error("添加公司信息失败",e) ;
			session.setAttribute("comResult","添加失败"); 
		}  
		session.setAttribute("self.custom.Token",java.util.UUID.randomUUID().toString()); 
		session.setAttribute("sessionFullName",comForm.getCustom().getFullName());  
		return mapping.findForward("relist") ;
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 

		if(!SelfToken.isValid(request, "self.custom.Token")){
			return mapping.findForward("relist") ;
		}
		try{  
			String []ids = comForm.getIds() ;
			if(ids==null){
				ids = new String[1] ;
				ids[0] = request.getParameter("id") ;
			}
			boolean ret = customService.deleteCustoms(ids) ;
			if(ret==true)
				session.setAttribute("comResult", "删除成功") ;
			else
				session.setAttribute("comResult", "删除失败") ;
		}catch(Exception e){
			logger.error("删除公司信息失败",e) ;
			session.setAttribute("comResult", "删除失败") ;
		}  
		session.setAttribute("self.custom.Token",java.util.UUID.randomUUID().toString());   
		return mapping.findForward("relist") ;
	} 

	public ActionForward preEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id") ; 
		request.setAttribute("comOne",customService.getCustomById(id)) ;
		request.setAttribute("comlist",customService.getCustoms()) ;
		session.setAttribute("self.custom.Token",java.util.UUID.randomUUID().toString()); 
		return mapping.findForward("updateCom") ;
	} 
	
	public ActionForward editCom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 

		if(!SelfToken.isValid(request, "self.custom.Token")){
			return mapping.findForward("relist") ;
		}
		try{
			comForm.getCustom().setAddDate(DataUtils.getDateByString(comForm.getAddTime())) ; 
			comForm.getCustom().setUpdateDate(new Date()) ;
			comForm.getCustom().setUpdatePerson(user.getUserId()) ;
			comForm.getCustom().setContractTime(DataUtils.getDateByString(comForm.getContractTime())) ;
			boolean ret = customService.updateCustom(comForm.getCustom()) ; 
			if(ret==true)
				session.setAttribute("comResult", "修改成功") ;
			else
				session.setAttribute("comResult", "修改失败") ;
		}catch(Exception e){
			logger.error("修改公司信息失败",e) ;
			session.setAttribute("comResult", "修改失败") ;
		}  
		session.setAttribute("self.custom.Token",java.util.UUID.randomUUID().toString()); 
		session.setAttribute("sessionFullName",comForm.getCustom().getFullName());  
		return mapping.findForward("relist") ;
	}
	
	public ActionForward poi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 

		PoiInData poiInData = new PoiInData();  
		
		List<FhiOaCustom> list = poiInData.getData("huangweiwei");
		
		for (FhiOaCustom  com : list) {
			
			customService.addCustom(com);
			
		}
		
		
		return mapping.findForward("a") ;
	}
	
	public void setCustomCondition(CustomCondition CustomCondition) {
		this.customCondition = CustomCondition;
	}


	public void setCustomService(CustomIn CustomService) {
		this.customService = CustomService;
	}
	
}