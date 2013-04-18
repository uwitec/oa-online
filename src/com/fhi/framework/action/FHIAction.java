package com.fhi.framework.action;



import java.rmi.AccessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.form.FHIForm;
import com.fhi.framework.utils.FHIUtils;
import com.fhi.user.vo.FhiUser;


/**
 * 
 * @author sunh
 * 
 */
public abstract class FHIAction extends DispatchActionSupport {
	 
	 
	protected Log logger = LogFactory.getLog(this.getClass());
	private FhiUser user; 

	public FhiUser getUser() {
		return user;
	}

	public void setUser(FhiUser user) {
		this.user = user;
	}

	/**
	 * 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // prevents caching at the proxy
		response.setHeader("Cache-Control", "no-store"); // HTTP 1.1
		
		FHIForm fhiForm = (FHIForm) form;
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}   
		if(fhiForm.getMethod()==null){
			return mapping.findForward("login");
		}
		/**
		 * 可能会涉及到权限的内容
		 * */
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	protected Object getBean(String id) {
		return getWebApplicationContext().getBean(id);
	}

	/**
	 * 将form 中的值设置到对应的VO中   避免循环取出form中的值设置到vo中
	 * @param vo
	 * @param form
	 * @param request
	 * @throws NoUserException
	 */
	protected void initCreateVO(Object vo, ActionForm form,
			HttpServletRequest request) throws AccessException {
		if (form instanceof FHIForm) {
			FHIUtils.setVOByForm(vo, (FHIForm) form);
		} else {
			throw new ClassCastException("form must extends FHIForm");
		}
		 
	}

	 
	/**
	 * @param vo 将数据中的对象取出  然后将vo的值设置到form中传到前台，再使用struts标签  页面将自动赋值
	 */
	protected void initForm(Object vo, ActionForm form) {
		if (form instanceof FHIForm) {
			FHIUtils.setFormByVO(vo, (FHIForm) form);
		} else {
			throw new ClassCastException("form must extends FHIForm");
		}
	}
}
