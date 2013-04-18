package com.fhi.journal.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.framework.config.FhiConfig;
import com.fhi.journal.condition.JournalCondition;
import com.fhi.journal.form.JournalForm;
import com.fhi.journal.service.JournalIn;
import com.fhi.user.vo.FhiUser;

public class JournalAction extends DispatchAction {


	private static Logger logger = Logger.getLogger(JournalAction.class);
	private JournalForm journalForm;
	private JournalIn journalService;
	private FhiUser user;
	private JournalCondition journalCondition;
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}
		
		journalForm = (JournalForm) form;
		//禁止网页缓存
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 文档管理入口
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
		
		
		request.setAttribute("form", journalForm);
		return mapping.findForward("index");
	}
//	
//	/**
//	 * 期刊展示入口
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward show(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		String title = request.getParameter("title");
//		String folder = request.getParameter("folder");
//		request.setAttribute("title", new String(title.trim().getBytes("ISO-8859-1"), "UTF-8"));
//		request.setAttribute("folder", new String(folder.trim().getBytes("ISO-8859-1"), "UTF-8"));
//		return mapping.findForward("show");
//	}
	
	/**
	 * 投票
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward vote(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		journalForm.setVote(journalService.getCount(journalForm.getVote().getCode(),user.getUserId()));
		
		request.setAttribute("form", journalForm);
		return mapping.findForward("vote");
	}

	public void setJournalService(JournalIn journalService) {
		this.journalService = journalService;
	}

	public void setJournalCondition(JournalCondition journalCondition) {
		this.journalCondition = journalCondition;
	}
}
