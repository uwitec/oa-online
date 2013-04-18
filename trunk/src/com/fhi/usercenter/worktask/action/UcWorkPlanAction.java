package com.fhi.usercenter.worktask.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.framework.config.FhiConfig;
import com.fhi.system.service.SystemIn;
import com.fhi.user.vo.FhiUser;
import com.fhi.usercenter.worktask.condition.UcWorkPlanCondition;
import com.fhi.usercenter.worktask.form.UcWorkPlanForm;
import com.fhi.usercenter.worktask.service.UcWorkPlanIn;
import com.fhi.usercenter.worktask.vo.FhiUcWorkPlan;

public class UcWorkPlanAction extends DispatchAction {

//	private WmsUser user;
	private static Logger logger = Logger.getLogger(UcWorkPlanAction.class);

	private FhiUser user;
	private UcWorkPlanForm ucWorkPlanForm;
	private UcWorkPlanCondition workPlanCondition;
	private UcWorkPlanIn workPlanService;
	private SystemIn systemService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}
				
		ucWorkPlanForm = (UcWorkPlanForm) form;
		//禁止网页缓存
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 管理入口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 提示信息的接收 页面传值 后续处理
		String info = (String) request.getSession().getAttribute(
				FhiConfig.UC_WorkPlan_Info);
		String id = (String) request.getSession().getAttribute(
				FhiConfig.UC_WorkPlan_Id);
		request.setAttribute(FhiConfig.UC_WorkPlan_Info, info);
		request.setAttribute(FhiConfig.UC_WorkPlan_Id, id);
		request.getSession().removeAttribute(FhiConfig.UC_WorkPlan_Info);
		request.getSession().removeAttribute(FhiConfig.UC_WorkPlan_Id);
		
		workPlanCondition.queryList(ucWorkPlanForm, user);
		ucWorkPlanForm.setList(workPlanService.query(workPlanCondition));

		request.setAttribute(FhiConfig.PAGE_INFO_NAME, workPlanCondition.getPageInfo());
		request.setAttribute("form", ucWorkPlanForm);
		return mapping.findForward("index");
	}
	
	/**
	 * 编辑载入
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FhiUcWorkPlan workPlan = ucWorkPlanForm.getUcWorkPlan();
		
		if(workPlan.getId()==null||"".equals(workPlan.getId())){
			workPlan.setAutoCode(systemService.isAutoCode(FhiConfig.AutoCode_WorkPlan));
		}
		else{
			ucWorkPlanForm.setUcWorkPlan((FhiUcWorkPlan) workPlanService.load(FhiUcWorkPlan.class, workPlan.getId()));
		}
		
		request.setAttribute("form", ucWorkPlanForm);
		return mapping.findForward("load");
	}
	
	/**
	 * 工作计划展示载入
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FhiUcWorkPlan workPlan = ucWorkPlanForm.getUcWorkPlan();
		
		if(workPlan.getId()!=null&&!"".equals(workPlan.getId())){
			ucWorkPlanForm.setUcWorkPlan((FhiUcWorkPlan) workPlanService.load(FhiUcWorkPlan.class, workPlan.getId()));
		}		
		request.setAttribute("form", ucWorkPlanForm);
		return mapping.findForward("show");
	}
	
	/**
	 * 报价保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FhiUcWorkPlan workPlan = ucWorkPlanForm.getUcWorkPlan();
		
		if(workPlan.getId()==null||"".equals(workPlan.getId())){
			workPlan.setId(java.util.UUID.randomUUID().toString());
			//workPlan.setCode(systemService.getCode(FhiConfig.AutoCode_WorkPlan, "FhiUcWorkPlan", "code"));
			workPlan.setCreater(user.getUserId());
			workPlan.setCreatorName(user.getEmployeeClass().getName());
			workPlan.setCreateDate(new Date());
			logger.info("添加新工作计划，用户："+user.getUserId()+",计划标题："+workPlan.getTitle());			
			workPlanService.save(workPlan);
		}
		else{
			workPlan.setCreatorName(user.getEmployeeClass().getName());
			workPlan.setCreater(user.getUserId());
			workPlan.setCreateDate(new Date());
			logger.info("修改工作计划，用户："+user.getUserId()+",计划标题："+workPlan.getTitle());			
			workPlanService.update(workPlan);
		}
		request.getSession().setAttribute(FhiConfig.UC_WorkPlan_Id,workPlan.getId());
		request.getSession().setAttribute(FhiConfig.UC_WorkPlan_Info,"工作计划保存成功！");
		
		request.setAttribute("form", ucWorkPlanForm);
		return mapping.findForward("main");
	}

	public void setWorkPlanCondition(UcWorkPlanCondition workPlanCondition) {
		this.workPlanCondition = workPlanCondition;
	}

	public void setWorkPlanService(UcWorkPlanIn workPlanService) {
		this.workPlanService = workPlanService;
	}

	public void setSystemService(SystemIn systemService) {
		this.systemService = systemService;
	}
}
