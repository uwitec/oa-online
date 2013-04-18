package com.fhi.client.action;

import java.io.OutputStream;
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

import com.fhi.client.condition.ClientCondition;
import com.fhi.client.form.ClientForm;
import com.fhi.client.service.ClientIn;
import com.fhi.client.vo.FhiClientUser;
import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.utils.ExportExcel;

public class ClientAction extends DispatchAction{

	private static Logger logger = Logger.getLogger(ClientAction.class);
	private ClientForm clientForm;
	private ClientCondition clientCondition;
	private ClientIn clientService;
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		clientForm = (ClientForm) form;
		
		//禁止网页缓存
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 客户用户登录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		String userName = clientForm.getUserName();
		String passWord = clientForm.getPassWord();
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute(FhiConfig.CLIENT_USER_NAME,null);
		httpSession.setAttribute(FhiConfig.AGENT_USER_NAME,null);
		httpSession.setAttribute(FhiConfig.USER_NAME,null);
		FhiClientUser cUser = null;
		if(userName!=null&&passWord!=null){
			cUser = clientService.getClient(userName, passWord);
			if(cUser!=null){				
				httpSession.setAttribute(FhiConfig.CLIENT_USER_NAME,cUser);
				request.setAttribute("url", cUser.getRightsUrl());
				cUser.setLoginDate(new Date());
				clientService.update(cUser);
				logger.debug("客户登录成功  用户名："+userName+"，密码："+passWord +",IP:"+clientService.getIpAddr(request));
				return mapping.findForward("redirect");
			}
			else{
				logger.debug("客户登录失败 用户名："+userName+"，密码："+passWord +",IP:"+clientService.getIpAddr(request));
				request.setAttribute("loginMsg", "用户名或密码错误！");
			}
		}
		return mapping.findForward("clientLogin");
	}

	public void setClientCondition(ClientCondition clientCondition) {
		this.clientCondition = clientCondition;
	}

	public void setClientService(ClientIn clientService) {
		this.clientService = clientService;
	}	
	
}
