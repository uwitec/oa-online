package com.fhi.system.classification.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.framework.config.FhiConfig;
import com.fhi.system.classification.service.ClassIn;
import com.fhi.user.vo.FhiUser;

public class SysClassAction extends DispatchAction {

//	private WmsUser user;
	private static Logger logger = Logger.getLogger(SysClassAction.class);
	private FhiUser user;
	private ClassIn classService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}		

		//禁止网页缓存
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 分类管理入口
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
		
		return mapping.findForward("index");
	}
	
	public void setClassService(ClassIn classService) {
		this.classService = classService;
	}	
}
