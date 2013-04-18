package com.fhi.system.sysmsg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.framework.config.FhiConfig;
import com.fhi.system.service.SystemIn;
import com.fhi.system.sysmsg.condition.SysMsgCondition;
import com.fhi.system.sysmsg.form.SysMsgForm;
import com.fhi.system.sysmsg.service.SysMsgIn;
import com.fhi.user.vo.FhiUser;

public class SysMsgAction extends DispatchAction {

//	private WmsUser user;
	private static Logger logger = Logger.getLogger(SysMsgAction.class);

	private FhiUser user;
	private SysMsgForm sysMsgForm;
	private SysMsgCondition sysMsgCondition;
	private SysMsgIn sysMsgService;
	private SystemIn systemService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}
				
		sysMsgForm = (SysMsgForm) form;
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
				FhiConfig.Sys_Msg_Info);
		String id = (String) request.getSession().getAttribute(
				FhiConfig.Sys_Msg_Id);
		request.setAttribute(FhiConfig.Sys_Msg_Info, info);
		request.setAttribute(FhiConfig.Sys_Msg_Id, id);
		request.getSession().removeAttribute(FhiConfig.Sys_Msg_Info);
		request.getSession().removeAttribute(FhiConfig.Sys_Msg_Id);
		
		sysMsgCondition.queryList(sysMsgForm, user);
		sysMsgForm.setList(sysMsgService.query(sysMsgCondition));

		request.setAttribute(FhiConfig.PAGE_INFO_NAME, sysMsgCondition.getPageInfo());
		request.setAttribute("form", sysMsgForm);
		return mapping.findForward("index");
	}

	public void setSysMsgCondition(SysMsgCondition sysMsgCondition) {
		this.sysMsgCondition = sysMsgCondition;
	}

	public void setSysMsgService(SysMsgIn sysMsgService) {
		this.sysMsgService = sysMsgService;
	}

	public void setSystemService(SystemIn systemService) {
		this.systemService = systemService;
	}
}
