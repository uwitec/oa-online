package com.fhi.index.webservice;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;

import com.fhi.employee.service.EmployeeIn;
import com.fhi.employee.vo.FhiOaEmployeeBasic;
import com.fhi.permission.service.PermissionImple;
import com.fhi.system.service.SystemImple;
import com.fhi.user.MD5.MD5andKL;
import com.fhi.user.service.UserIn;
import com.fhi.user.vo.FhiUser;

@WebService
public class userWebServiceTest implements userWebServiceTestIn {
	@Resource 
	private WebServiceContext context;
	private UserIn userServiceImple;
	private PermissionImple permissionImple;
	private EmployeeIn employeeService;
	private static Logger logger = Logger.getLogger(SystemImple.class);
	
	
	public void setUserServiceImple(UserIn userServiceImple) {
		this.userServiceImple = userServiceImple;
	}
	public UserIn getUserServiceImple() {
		return userServiceImple;
	}
	
	/***
	 * 远程用户登陆
	 */
	
	public FhiUser sayHello(String userName,String passWord) {
		List list = null;
		FhiUser fhiUserser = new FhiUser();
		fhiUserser = null;
		try{
			list = this.userServiceImple.queryById(userName,passWord);
			if(list.size()<=0){
				logger.info("WMS用户远程登陆失败"+"\n"+"用户ID为: "+userName+"\n密码错误或用户不存在");
				fhiUserser = null;
				return fhiUserser;
			}
			fhiUserser = (FhiUser)list.get(0);
			//查找用户角度和权限,组装进FhiUser用户类中.
			fhiUserser.setRole(this.permissionImple.findRole(fhiUserser));
			fhiUserser.setPermissionMap(permissionImple.findPermission(fhiUserser,"webService"));
			if("".equals(fhiUserser.getPermissionMap().get("WMS_SITE"))||fhiUserser.getPermissionMap().get("WMS_SITE") == null){
				logger.info("WMS用户远程登陆失败"+"\n"+"用户ID为: "+userName+"\n用户无WMS站点的权限");
				//返回无权限错误.
				fhiUserser.setId("errorSITE");
				return fhiUserser;
			}
			try{
				fhiUserser.setEmployeeClass(this.employeeService.getEmployeeById(fhiUserser.getEmployeeId()));
			}catch(Exception e){
				logger.info("ID: "+userName+"\n为无员工用户.");
			}
			//将用户放入FhiUser类中,如无用户则放入空用户.
			if(fhiUserser.getEmployeeClass()==null){
				fhiUserser.setEmployeeClass(new FhiOaEmployeeBasic());
			}
			FhiOaEmployeeBasic fe = null;
			fe = fhiUserser.getEmployeeClass();
			
		} catch (Exception e) {
			logger.error("WMS用户远程登陆失败", e);
			return fhiUserser;
		}
		return fhiUserser;
	}
	
	
	/**
	 * 远程修改密码
	 */
	public String changePassword(String userId,String passWord,String OldPassword) {
		FhiUser fu = userServiceImple.queryById(userId);
		if (!(fu.getPassWord().toString().equals(MD5andKL.MD5(OldPassword).toString()))){
			System.out.println(fu.getPassWord());
			return "0";
		}else{
			fu.setPassWord(MD5andKL.MD5(passWord));
			System.out.println(fu.getPassWord());
			userServiceImple.update(fu);
			return "1";
		}
	}
	
	public String queryPermission(FhiUser fu) {
		MessageContext ctx = context.getMessageContext();
		HttpServletRequest request = (HttpServletRequest)ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
        HttpSession session = request.getSession();
		String str = "";
		str = userServiceImple.queryWebService(fu);
		if ("true".equals(str.toString())){
			session.setAttribute("wmsRFhiUser", fu);
			return "true";
		}else{
			return "false";
		}
	}
	
	public PermissionImple getPermissionImple() {
		return permissionImple;
	}
	public void setPermissionImple(PermissionImple permissionImple) {
		this.permissionImple = permissionImple;
	}
	public EmployeeIn getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeIn employeeService) {
		this.employeeService = employeeService;
	}
}
