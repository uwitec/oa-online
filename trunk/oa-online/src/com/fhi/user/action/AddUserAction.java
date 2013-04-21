package com.fhi.user.action;

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

import com.fhi.employee.service.EmployeeIn;
import com.fhi.employee.vo.FhiOaEmployeeBasic;
import com.fhi.framework.config.FhiConfig;
import com.fhi.permission.service.PermissionImple;
import com.fhi.permission.vo.FhiRole;
import com.fhi.system.service.SystemIn;
import com.fhi.user.MD5.MD5andKL;
import com.fhi.user.condition.UserCondition;
import com.fhi.user.form.AddUserForm;
import com.fhi.user.service.UserIn;
import com.fhi.user.vo.FhiUser;

public class AddUserAction extends DispatchAction implements FhiConfig{

	private static Logger logger = Logger.getLogger(AddUserAction.class);	
	private UserIn userServiceImple;
	private UserCondition ucondition;
	private FhiUser user;
	private PermissionImple permissionImple;
	private SystemIn systemService;
	private EmployeeIn employeeService;
	public SystemIn getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemIn systemService) {
		this.systemService = systemService;
	}

	public PermissionImple getPermissionImple() {
		return permissionImple;
	}

	public void setPermissionImple(PermissionImple permissionImple) {
		this.permissionImple = permissionImple;
	}

	public UserCondition getUcondition() {
		return ucondition;
	}

	public void setUcondition(UserCondition ucondition) {
		this.ucondition = ucondition;
	}


	public void setUserServiceImple(UserIn userServiceImple) {
		this.userServiceImple = userServiceImple;
	}
	
	public void queryUserList(ActionForm form,HttpServletRequest request,ActionMapping mapping){
		List<FhiUser> queryList = userServiceImple.query(form);
		//存入request准备输出
		request.setAttribute("list", queryList);
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String path = "index" ;
		
		String emAddUser = request.getParameter("emAddUser") ;  
		if(emAddUser!=null && emAddUser.trim().equals("yes"))
			path = "midpage" ; 
		 
		HttpSession httpSession = request.getSession(true);
		//从Form拿到数据，设置创建时间
		AddUserForm auf = (AddUserForm) form;
		FhiUser wsu = auf.getWu();
		wsu.setRegDate(new Date());
		//使用service方法，查询是否已存在此用户
		List<FhiUser> list = userServiceImple.requeryName(form);
		if (list.size()==0){
			wsu.setFlag(wsu.getFlag());
			wsu.setEmployeeId(wsu.getEmployeeHidden());
			System.out.println(wsu.getEmployeeHidden());
			if (userServiceImple.resave(wsu) && permissionImple.savePermission(wsu.getUserId(), auf.getPermissionString())) {
				
				FhiOaEmployeeBasic fe = this.employeeService.getEmployeeById(wsu.getEmployeeHidden());
				fe.setAccount(wsu.getUserId());
				this.employeeService.updateEmployee(fe);
				
				queryUserList(form,request,mapping);
				httpSession.removeAttribute("editSuccess");
				httpSession.removeAttribute("addSuccess");
				httpSession.removeAttribute("delSuccess");
				httpSession.setAttribute("addSuccess",true);
				
				return mapping.findForward(path);
				
			}
			else{
				logger.info("未成功添加用户");
				return mapping.findForward(path);
			}
		} 
		 
			return null ;
		
	}
	
	public ActionForward edit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(true);

		//获取Session中的用户
		FhiUser wmsuser = (FhiUser)this.userServiceImple.load(request.getParameter("editId"));
		List permission = userServiceImple.query();
		List userPermission = userServiceImple.queryPermission(wmsuser.getUserId());
	    List<FhiRole> select1Role = userServiceImple.getSelect1Role(permission,userPermission);
		List<FhiRole> select2Role = userServiceImple.getSelect2Role(permission,userPermission);
		request.setAttribute("select1Role", select1Role);
		request.setAttribute("select2Role", select2Role);
		request.setAttribute("permissionsession", permission);
		request.setAttribute("editUser",wmsuser);
		request.setAttribute("Edit", true);
		httpSession.removeAttribute("editSuccess");
		httpSession.removeAttribute("addSuccess");
		httpSession.removeAttribute("delSuccess");
		queryUserList(form,request,mapping);
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, ucondition.getPageInfo());
		return mapping.findForward("userManager");
	}
	
	public ActionForward delUser(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{

		//获取ID存入数组
		String[] delId = null;
		delId = request.getParameterValues("delObj");
		if(userServiceImple.delUser(delId)){
			//设置信息，返回页面
			queryUserList(form,request,mapping);
			return mapping.findForward("index");
		}else{
			logger.info("删除用户未成功");
			return mapping.findForward("index");
		}
	}
	
	public ActionForward resEdit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
			HttpSession httpSession = request.getSession(true);
			queryUserList(form,request,mapping);
			request.removeAttribute("Edit");
			httpSession.removeAttribute("addSuccess");
			httpSession.removeAttribute("editSuccess");
			httpSession.removeAttribute("delSuccess");
			return mapping.findForward("index");
	}
	public ActionForward update(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(true);
		
		//获取页面数据
		AddUserForm auf = (AddUserForm)form;
		FhiUser wu = auf.getWu();
		System.out.println(request.getParameter("changePassword"));
		if(!("0".equals(request.getParameter("changePassword")))){
			wu.setPassWord(MD5andKL.MD5(wu.getPassWord()));
		}
		String oldId = this.userServiceImple.queryById(wu.getId()).getEmployeeId();
		FhiUser wuEmployee = this.userServiceImple.queryById(wu.getId());
		wu.setEmployeeId(wu.getEmployeeEditHidden());
		if (userServiceImple.update(wu)){
			permissionImple.updatePermission(wu.getUserId(),auf.getPermissionEditString());
			queryUserList(form,request,mapping);
			FhiOaEmployeeBasic fe = this.employeeService.getEmployeeById(wu.getEmployeeEditHidden());
			fe.setAccount(wu.getUserId());
			this.employeeService.updateEmployee(fe);
			System.out.println(fe.getAccount());
			fe = this.employeeService.getEmployeeById(oldId);
			System.out.println(wuEmployee.getEmployeeId().equals(wu.getEmployeeId()));
			if(!(wuEmployee.getEmployeeId().equals(wu.getEmployeeId()))){
				fe.setAccount(null);
			}
			this.employeeService.updateEmployee(fe);
			
			request.removeAttribute("Edit");
			httpSession.removeAttribute("addSuccess");
			httpSession.removeAttribute("delSuccess");
			httpSession.setAttribute("editSuccess",true);
			return mapping.findForward("index");
		}
		logger.info("更新用户信息不成功");
		return mapping.findForward("index");
	}
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(true);
		//执行查询语句
		request.setAttribute("addSuccess", httpSession.getAttribute("addSuccess"));
		request.setAttribute("editSuccess", httpSession.getAttribute("editSuccess"));
		request.setAttribute("delSuccess", httpSession.getAttribute("delSuccess"));
		httpSession.removeAttribute("addSuccess");
		httpSession.removeAttribute("editSuccess");
		httpSession.removeAttribute("delSuccess");
		List<FhiUser> queryList = userServiceImple.query(form);
		List permission = userServiceImple.query();
		request.setAttribute("permissionsession", permission);
		request.setAttribute("form",form);
		request.setAttribute("list", queryList);
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, ucondition.getPageInfo());
		return mapping.findForward("userManager");
	}
	
	public ActionForward preAddUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
		String employeeid = request.getParameter("id") ;
		List permission = userServiceImple.query();
		request.setAttribute("permissionsession", permission);
		request.setAttribute("employee", employeeService.getEmployeeById(employeeid)); 
		return mapping.findForward("emAddUser");
	}
	
	
	/**
	 * 修改密码的跳转方法，获取用户
	 * @param permission
	 * @return
	 */
	public ActionForward modify(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if(user==null){
			return mapping.findForward("login");
		}
		request.setAttribute("editUser",this.userServiceImple.load(request.getParameter("editId")));
		return mapping.findForward("changepassword");
	}
	
	public ActionForward updatePassword(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(true);
		//获取页面数据,对密码进行加密
		AddUserForm auf = (AddUserForm)form;
		FhiUser wu = auf.getWu();
		wu.setPassWord(MD5andKL.MD5(wu.getPassWord()));
		if (userServiceImple.update(wu)){
			queryUserList(form,request,mapping);
			request.setAttribute("modifySuccess", true);
			request.setAttribute("editUser",this.userServiceImple.load(auf.getWu().getId()));
			return mapping.findForward("changepassword");
		}
		logger.info("未成功修改密码");
		request.setAttribute("modifySuccess", false);
		return mapping.findForward("changepassword");
	}
	
	public ActionForward main(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}
		return mapping.findForward("main");
	}
	
	
	public ActionForward returnEmployee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<FhiOaEmployeeBasic> list = this.employeeService.getNoAccountEmployees();
		request.setAttribute("employeeList", list);
		return mapping.findForward("selectEmployee");
	}
	
	public ActionForward returnEmployeeEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<FhiOaEmployeeBasic> list = this.employeeService.getNoAccountEmployees();
		request.setAttribute("employeeList", list);
		return mapping.findForward("selectEmployeeEdit");
	}

	public EmployeeIn getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeIn employeeService) {
		this.employeeService = employeeService;
	}
}