package com.fhi.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.framework.config.FhiConfig;
import com.fhi.system.form.SystemForm;
import com.fhi.system.service.SystemIn;
import com.fhi.user.vo.FhiUser;

public class SystemAction extends DispatchAction {

//	private WmsUser user;
	private static Logger logger = Logger.getLogger(SystemAction.class);
	private SystemForm systemForm;
	private SystemIn systemService;
	private FhiUser user;
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}
		systemForm = (SystemForm) form;
		//禁止网页缓存
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 系统配置入口
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
		this.setInfo(request);
		systemForm.setConf(systemService.getSysConfig());
		request.setAttribute("form", systemForm);
		return mapping.findForward("index");
	}
	/**
	 * 配置信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int r = systemService.saveSysConfig(systemForm.getConf());
		if(r==1){
			request.getSession().setAttribute(FhiConfig.System_Info, "系统配置保存成功！");
		}
		else if(r==0){
			request.getSession().setAttribute(FhiConfig.System_Info, "未知错误！系统配置保存失败！");
		}		
		return mapping.findForward("main");
	}
	/**
	 * 自动编号配置入口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward autoCodeIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setInfo(request);
		
		systemForm.setCodeList(systemService.queryAutoCode());
		
		request.setAttribute("form", systemForm);
		return mapping.findForward("autoCodeIndex");
	}
	/**
	 * 自动单号加载
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward autoCodeLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		systemForm.setWac(systemService.loadAutoCodeById(systemForm.getWac().getCodeId()));
		request.setAttribute("form", systemForm);		
		return mapping.findForward("autoCodeLoad");
	}
	
	
	/**
	 * 自动单号保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward codeSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().setAttribute(FhiConfig.System_Info, "自动单号保存成功！");
		if(!systemService.saveAutoCode(systemForm.getWac())){
			request.getSession().setAttribute(FhiConfig.System_Info, "自动单号保存失败！可能由于单号已被删除！");
			request.setAttribute(FhiConfig.System_Save_Id, systemForm.getWac().getCodeId());
		}
		response.getWriter().print("<script type=''text/javascript'>window.parent.document.forms['pageForm'].submit()</script>");
		return null;
	}
	
	public void setSystemService(SystemIn systemService) {
		this.systemService = systemService;
	}
	
	private void setInfo(HttpServletRequest request){
		// 提示信息的接收 页面传值 后续处理
		String info = (String) request.getSession().getAttribute(
				FhiConfig.System_Info);
		String id = (String) request.getSession().getAttribute(
				FhiConfig.System_Save_Id);
		request.setAttribute(FhiConfig.System_Info, info);
		request.setAttribute(FhiConfig.System_Save_Id, id);
		request.getSession().removeAttribute(FhiConfig.System_Info);
		request.getSession().removeAttribute(FhiConfig.System_Save_Id);
	}
	
	/**
	 * 用于DWR判断自动单号是否存在
	 * @param markCode 单号标识
	 * @param tabName 实体名称
	 * @param codeMethod 单号属性名
	 * @return
	 */
	public String getCode(String markCode,String tabName,String codeMethod){
		String autoCode = systemService.getCode(markCode);
		if(!systemService.isCodeHave(autoCode, tabName, codeMethod)){
			return "";
		}
		return autoCode;
	}
	
}
