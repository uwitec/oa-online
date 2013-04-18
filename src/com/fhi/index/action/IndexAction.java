package com.fhi.index.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.employee.service.EmployeeIn;
import com.fhi.employee.vo.FhiOaEmployeeBasic;
import com.fhi.framework.config.FhiConfig;
import com.fhi.index.form.IndexForm;
import com.fhi.permission.service.PermissionImple;
import com.fhi.system.service.SystemIn;
import com.fhi.system.sysmsg.condition.SysMsgCondition;
import com.fhi.system.sysmsg.service.SysMsgIn;
import com.fhi.user.condition.UserCondition;
import com.fhi.user.service.UserIn;
import com.fhi.user.vo.FhiUser;

public class IndexAction extends DispatchAction{
	
	private static Logger logger = Logger.getLogger(IndexAction.class);	
	private UserCondition ucondition;
	private IndexForm indexForm;
	private UserIn userServiceImple;
	
	private PermissionImple permissionImple;
	
	private SystemIn systemService;
	
	private EmployeeIn employeeService;
	
	private SysMsgIn sysMsgService;
	private SysMsgCondition sysMsgCondition;
	
	
	
	
	//控制返回主页左侧边栏，并判断权限
	public ActionForward left(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("left");
	}
	//控制返回主页头部
	public ActionForward head(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("head");
	}
	
	//控制返回主页顶部
	public ActionForward top(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			return mapping.findForward("top");
		}
	
	//控制返回主页底部
	public ActionForward foot(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			return mapping.findForward("foot");
		}
	
	public ActionForward center(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			return mapping.findForward("center");
		}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//查询是否有此用户
		IndexForm indForm = (IndexForm)form;
		List<FhiUser> list = userServiceImple.requery(indForm);
		HttpSession httpSession = request.getSession(true);
		try{
				if (list==null||list.size()==0){
					FhiUser ws = null;
					ws = (FhiUser)httpSession.getAttribute(FhiConfig.USER_NAME);
					if (ws != null){
						ws.setRole(this.permissionImple.findRole(ws));
						ws.setPermissionMap(permissionImple.findPermission(ws));
						ws.setEmployeeClass(this.employeeService.getEmployeeById(ws.getEmployeeId()));
						if(ws.getEmployeeClass()==null){
							ws.setEmployeeClass(new FhiOaEmployeeBasic());
						}
						FhiOaEmployeeBasic fe = null;
						fe = ws.getEmployeeClass();
						httpSession.setAttribute(FhiConfig.USER_NAME, ws);
						System.out.println(httpSession.toString());
						httpSession.setMaxInactiveInterval(systemService.getSysConfig().getSessionInactiveInterval());
						logger.info("用户登录成功   用户名:"+ws.getUserId()+",IP:"+systemService.getIpAddr(request));
						return mapping.findForward("main");
					}else{
						request.setAttribute("noLogin", true);
						logger.info("用户登录失败   用户名:"+indForm.getWu().getUserId()+",密码："+indForm.getWu().getPassWord()+",IP:"+systemService.getIpAddr(request));
						return mapping.findForward("login");
					}
				}else{
					//将用户写入session
					FhiUser ws = (FhiUser)list.get(0);
					ws.setRole(this.permissionImple.findRole(ws));
					ws.setPermissionMap(permissionImple.findPermission(ws));
					ws.setEmployeeClass(this.employeeService.getEmployeeById(ws.getEmployeeId()));
					if(ws.getEmployeeClass()==null){
						ws.setEmployeeClass(new FhiOaEmployeeBasic());
					}
					FhiOaEmployeeBasic fe = null;
					fe = ws.getEmployeeClass();
					httpSession.setAttribute(FhiConfig.USER_NAME, ws);
					System.out.println(httpSession.toString());
					httpSession.setMaxInactiveInterval(systemService.getSysConfig().getSessionInactiveInterval());
					logger.info("用户登录成功   用户名:"+ws.getUserId()+",IP:"+systemService.getIpAddr(request));
					return mapping.findForward("main");
				}
		}catch(Exception e){
			logger.info("用户登陆错误！",e);
			request.setAttribute("noLogin", true);
			return mapping.findForward("login");
		}
	}
	
	
	//个人中心首页
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		
		indexForm = (IndexForm) form;
		FhiUser user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}
		//获取通知列表
		sysMsgCondition.mainQuerySys(user);
		
		indexForm.setSysMsgList(sysMsgService.query(sysMsgCondition, 5));
		
		//获取工作计划提醒列表
		sysMsgCondition.mainQueryWork(user);
		
		indexForm.setSysMsgListWork(sysMsgService.query(sysMsgCondition, 5));
		
		//获取通知数量
		indexForm.setInformCount(sysMsgService.getInformCount(user));
		
		//获取工作计划提醒数量
		indexForm.setPlanCount(sysMsgService.getPlanCount(user));
		
		request.setAttribute("form", indexForm);
		sysMsgCondition.getPageInfo().init();
		return mapping.findForward("index");
	}
	
	
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(true);
		//httpSession.removeAttribute(FhiConfig.USER_NAME);
		httpSession.setAttribute(FhiConfig.USER_NAME, null);
		return mapping.findForward("login");
	}

	public UserCondition getUcondition() {
		return ucondition;
	}

	public void setUcondition(UserCondition ucondition) {
		this.ucondition = ucondition;
	}

	public UserIn getUserServiceImple() {
		return userServiceImple;
	}

	public void setUserServiceImple(UserIn userServiceImple) {
		this.userServiceImple = userServiceImple;
	}

	public PermissionImple getPermissionImple() {
		return permissionImple;
	}

	public void setPermissionImple(PermissionImple permissionImple) {
		this.permissionImple = permissionImple;
	}

	public SystemIn getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemIn systemService) {
		this.systemService = systemService;
	}

	public EmployeeIn getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeIn employeeService) {
		this.employeeService = employeeService;
	}
	
	public void setSysMsgService(SysMsgIn sysMsgService) {
		this.sysMsgService = sysMsgService;
	}
	public void setSysMsgCondition(SysMsgCondition sysMsgCondition) {
		this.sysMsgCondition = sysMsgCondition;
	}
	
}
