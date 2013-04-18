package com.fhi.information.freight.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.framework.config.FhiConfig;
import com.fhi.information.freight.condition.FreightCondition;
import com.fhi.information.freight.form.FreightForm;
import com.fhi.information.freight.service.FreightIn;
import com.fhi.information.freight.vo.FhiFreight;
import com.fhi.system.service.SystemIn;
import com.fhi.user.vo.FhiUser;

public class FreightAction extends DispatchAction {

//	private WmsUser user;
	private static Logger logger = Logger.getLogger(FreightAction.class);

	private FhiUser user;
	private FreightForm freightForm;
	private FreightCondition freightCondition;
	private FreightIn freightService;
	private SystemIn systemService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}
		
//		user=new FhiUser();
//		user.setName("管理员");
//		user.setUserId("Admin");
//		Map<String, FhiRole>  map=new HashMap<String, FhiRole> ();
//		FhiRole role=new FhiRole();
//		role.setId("402881a9260671b6012606723ecc0003");
//		role.setPid("4028818d2602da21012602daa7350001");
//		role.setRoleName("软件开发员");		
//		map.put(role.getId(), role);		
//		user.setRole(map);		
//		logger.debug("手动设置角色ID："+role.getId());		
//		request.getSession().setAttribute(FhiConfig.USER_NAME, user);
		
		
		freightForm = (FreightForm) form;
		//禁止网页缓存
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 报价管理入口
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
				FhiConfig.Freight_Info);
		String id = (String) request.getSession().getAttribute(
				FhiConfig.Freight_Alter_Id);
		request.setAttribute(FhiConfig.Freight_Info, info);
		request.setAttribute(FhiConfig.Freight_Alter_Id, id);
		request.getSession().removeAttribute(FhiConfig.Freight_Info);
		request.getSession().removeAttribute(FhiConfig.Freight_Alter_Id);
		
		freightCondition.queryManage(freightForm, user);
		freightForm.setList(freightService.query(freightCondition));

		request.setAttribute(FhiConfig.PAGE_INFO_NAME, freightCondition.getPageInfo());
		request.setAttribute("form", freightForm);
		return mapping.findForward("index");
	}
	
	/**
	 * 展示入口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FhiFreight freight = freightForm.getFreight();
		
		if(freight.getFromName()!=null&&!"".equals(freight.getFromName())&&freight.getToName()!=null&&!"".equals(freight.getToName())){
			freightCondition.queryList(freightForm, user);
			freightForm.setList(freightService.query(freightCondition));
		}

		request.setAttribute(FhiConfig.PAGE_INFO_NAME, freightCondition.getPageInfo());
		request.setAttribute("form", freightForm);
		return mapping.findForward("showQuery");
	}
	
	/**
	 * 报价载入
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
		FhiFreight freight = freightForm.getFreight();
		
		if(freight.getId()==null||"".equals(freight.getId())){
			freight.setAutoCode(systemService.isAutoCode(FhiConfig.AutoCode_Freight));
		}
		else{
			freightForm.setFreight((FhiFreight) freightService.load(FhiFreight.class, freight.getId()));
		}
		
		request.setAttribute("form", freightForm);
		return mapping.findForward("load");
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
		FhiFreight freight = freightForm.getFreight();
		
		if(freight.getId()==null||"".equals(freight.getId())){
			freight.setId(java.util.UUID.randomUUID().toString());
			freight.setCode(systemService.getCode(FhiConfig.AutoCode_Freight, "FhiFreight", "code"));
			freight.setCreater(user.getUserId());
			freight.setCreatorName(user.getEmployeeClass().getName());
			freight.setCreateDate(new Date());
			logger.info("添加新报价，用户："+user.getUserId()+",报价编号："+freight.getCode());			
			freightService.save(freight);
		}
		else{
			freight.setCreatorName(user.getEmployeeClass().getName());
			freight.setCreater(user.getUserId());
			freight.setCreateDate(new Date());
			logger.info("修改报价，用户："+user.getUserId()+",报价编号："+freight.getCode());			
			freightService.update(freight);
		}
		request.getSession().setAttribute(FhiConfig.Freight_Alter_Id,freight.getId());
		request.getSession().setAttribute(FhiConfig.Freight_Info,"报价信息保存成功！");
		
		request.setAttribute("form", freightForm);
		return mapping.findForward("main");
	}

	public void setFreightCondition(FreightCondition freightCondition) {
		this.freightCondition = freightCondition;
	}

	public void setFreightService(FreightIn freightService) {
		this.freightService = freightService;
	}

	public void setSystemService(SystemIn systemService) {
		this.systemService = systemService;
	}	
	
}
