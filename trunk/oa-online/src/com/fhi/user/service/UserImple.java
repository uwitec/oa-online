package com.fhi.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.directwebremoting.WebContextFactory;

import com.fhi.employee.service.EmployeeIn;
import com.fhi.employee.vo.FhiOaEmployeeBasic;
import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.dao.JdbcDbDaoImple;
import com.fhi.index.form.IndexForm;
import com.fhi.permission.service.PermissionImple;
import com.fhi.permission.vo.FhiRole;
import com.fhi.permission.vo.FhiRoleUser;
import com.fhi.user.MD5.MD5andKL;
import com.fhi.user.condition.UserCondition;
import com.fhi.user.form.AddUserForm;
import com.fhi.user.vo.FhiUser;

public class UserImple extends AbstractServiceImple implements UserIn {
	private static Logger logger = Logger.getLogger(UserImple.class);	
	private PermissionImple permissionImple;
	private UserCondition ucondition;
	private EmployeeIn employeeService;
	JdbcDbDaoImple jdbcDbDao;

	public JdbcDbDaoImple getJdbcDbDao() {
		return jdbcDbDao;
	}

	public void setJdbcDbDao(JdbcDbDaoImple jdbcDbDao) {
		this.jdbcDbDao = jdbcDbDao;
	}

	public UserCondition getUcondition() {
		return ucondition;
	}

	public void setUcondition(UserCondition ucondition) {
		this.ucondition = ucondition;
	}
	public PermissionImple getPermissionImple() {
		return permissionImple;
	}

	public void setPermissionImple(PermissionImple permissionImple) {
		this.permissionImple = permissionImple;
	}

	public List<FhiRole> getSelect1Role(List perlist, List userPerList) {
		List<FhiRole> Select1 = new ArrayList();
		boolean isPermission = true;
		FhiRoleUser wmsroleuser;
		FhiRole wmsrole;
		for(int i=0;i<perlist.size();i++){
			wmsrole = (FhiRole) perlist.get(i);
			for(int j=0;j<userPerList.size();j++){
				wmsroleuser = (FhiRoleUser) userPerList.get(j);
				if (wmsrole.getId().toString().equals(wmsroleuser.getRoleCode().toString())){
					isPermission = false;
					}
				}
			if(isPermission){
				Select1.add(wmsrole);
			}
			isPermission = true;
		}
		return Select1;
	}
	
	public List<FhiRole> getSelect2Role(List perlist, List userPerList) {
		List<FhiRole> list = new ArrayList();
		FhiRoleUser wmsroleuser;
		FhiRole wmsrole;
		for(int i=0;i<userPerList.size();i++){
			wmsroleuser = (FhiRoleUser) userPerList.get(i);
			for(int j=0;j<perlist.size();j++){
				wmsrole = (FhiRole) perlist.get(j);
				if(wmsroleuser.getRoleCode().toString().equals(wmsrole.getId().toString()))
				{
					list.add((FhiRole) perlist.get(j));
				}
			}
		}
		return list;
	}

	public List query() {
		this.ucondition.setRegionQueryHqlPermission();
		List list = null;
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
			if(list.size()<=0){
				return null;
			}
			return list;
		} catch (Exception e) {
			logger.error("权限列表错误！",e);
			return null;
		}
	}
	
	public List queryAllNode(){
		this.ucondition.setRegionQueryHqlPermissionWithFhiRole();
		List list = null;
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
			if(list.size()<=0){
				return null;
			}
			return list;
		} catch (Exception e) {
			logger.error("权限列表错误！",e);
			return null;
		}
	}
	
	public List queryPermission(String userName) {
		this.ucondition.setRegionQueryHqlUserPermission(userName);
		List list = null;
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("用户权限列表错误！",e);
			return null;
		}
		return list;
	}
	
	//查询登陆ID和密码是否正确
	public List<FhiUser> requery(IndexForm form) {
		try{
			this.ucondition.setRegionQueryHql(form.getWu().getUserId(),form.getWu().getPassWord());
		}catch(NullPointerException e){
			logger.error("非法登陆，参数错误！",e);
			return null;
		}
		List<FhiUser> list=null;
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("系统查询错误！");
			return null; 
		}
		return list;
	}
	 
	//查询是否已经有此登陆ID
	public List<FhiUser> requeryName(ActionForm form) {
		this.ucondition.setRegionQueryHqlid((AddUserForm)form);
		List<FhiUser> list=null;
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public FhiUser queryById(String id){
		FhiUser fuser = null;
		this.ucondition.setRegionQueryHqlByUserId(id);
		try {
			fuser = (FhiUser) this.dbDao.queryObjectById(FhiUser.class, id);
		} catch (Exception e) {
			logger.debug("未找到符合此id的用户'"+id+"'",e);
		}
		return fuser;
	}
	
	public List<FhiUser> query(ActionForm form) {
		this.ucondition.setRegionQueryHqlName((AddUserForm)form);
		List<FhiUser> list = null;
		try {
			list = this.dbDao.queryObjectsToPages(this.setPageInfo(ucondition));
			if (!(list.size()<=0)){
				return list;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("系统查询错误",e);
			return null;
		}
		return list;
	}

	public FhiUser load(String Id) {
		this.ucondition.setRegionQueryHql(Id);
		List<FhiUser> list = null;
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
			if (!(list.size()<=0)){
				return (FhiUser)list.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("系统查询错误");
			return null;
		}
		return null;
	}
	
	public boolean update(FhiUser obj) {
		// TODO Auto-generated method stub
		try {
			if (this.dbDao.updateObject(obj)){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("系统查询错误");
			return false;
		}
		return false;
	}
	
//
//	public void save(Test obj) {
//		try {
//			if (obj.getId() == 0) {
//				this.dbDao.addObject(obj);
//			} else {
//				this.dbDao.updateObject(obj);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void delete(Test obj) {
//		try {
//			dbDao.deleteObject(obj);
//		} catch (Exception e) {
//			// 
//			e.printStackTrace();
//		}
//
//	}
//
//	public void deleteAll(String[] ids) {
//		for (int i = 0; i < ids.length; i++) {
//			Test obj = this.load(ids[i]);
//			try {
//				this.delete(obj);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public Test load(String id) {
//
//		List list;
//		try {
//
//			return (Test) dbDao.queryObjectById(Test.class, id);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	public boolean resave(FhiUser obj) {
		try {
			if(obj.getId()==null||"".equals(obj.getId()))
			{
			 obj.setPassWord(MD5andKL.MD5(obj.getPassWord()));
			 dbDao.addObject(obj);
			}
			 return true;
		} catch (Exception e) {
			logger.error("系统保存数据错误！",e);
		}
		return false;
	}
	
	/**
	 * 返回所有角色下的用户.
	 * @param userName
	 * @return
	 */
	
	public List queryRoleUser(){
		List list = null;
		String sql = "";
		sql = "select * from fhi_oa_role_user";
		try {
			list = jdbcDbDao.queryJDBCforList(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//Dwr调用方法
	public boolean isExist(String userName){
		this.ucondition.setRegionQueryHqlIsExist(userName);
		System.out.println("1");
		List<FhiUser> list = null;
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
			System.out.println(list.get(0));
			//如果查询得到结果，则登陆ID已经存在
			if (!(list.size()<=0)){
				System.out.println(list.get(0));
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("系统查询错误");
			return false;
		}
		return false;
	}

	public List<FhiUser> load() {
		this.ucondition.setRegionQueryHql();
		List<FhiUser> list = null;
		try {
			list = this.dbDao.queryObjectsToPages(this.setPageInfo(ucondition));
			if (!(list.size()<=0)){
				return list;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("系统查询错误");
		}
		return null;
	}
	
	public boolean dwrequery(String userName,String passWord,HttpServletRequest request) {
		this.ucondition.dwrSetRegionQueryHql(userName,MD5andKL.MD5(passWord));
		List<FhiUser> list=null;
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
			if (list.size()>0){
				return true;
			}
			logger.info("用户登录失败   用户名:"+userName+",密码："+passWord + ",IP:"+this.getIpAddr(request));			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("无此用户",e);
			return false;
		}
		return false;
	}
	
	public boolean delUser(String[] delId){
		List<FhiUser> list = null;
		String delRoleSql = "";
		String userName = "";
		for(int i=0;i<delId.length;i++){
			this.ucondition.setRegionQueryHql(delId[i]);
			try {
				list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("系统查询错误");
				return false;
			}
			userName = list.get(0).getUserId();
			delRoleSql = "DELETE FROM fhi_oa_role_user where UserName = '"+userName+"'";
			try {
				jdbcDbDao.execute(delRoleSql);
			} catch (Exception e) {
				logger.error("清空权限错误",e);
				return false;
			}
		}
		FhiUser fhi = null;
		FhiOaEmployeeBasic fe = null;
		for(int i=0;i<delId.length;i++){
			try {
				fhi = (FhiUser)this.dbDao.queryObjectById(FhiUser.class, delId[i]);
				fe = this.employeeService.getEmployeeById(fhi.getEmployeeId());
				fe.setAccount(null);
				this.employeeService.updateEmployee(fe);
								
			} catch (Exception e) {
				logger.debug("查询错误",e);
			}			
		}
		
		try {
			if(this.dbDao.deleteObjects(FhiUser.class,delId)){
				HttpSession session = WebContextFactory.get().getSession();
				session.setAttribute("delSuccess", true);
				return true;
			}else{
				return false;
			}
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean isPassWord(String id,String password){
		FhiUser wms = this.load(id);
		if (wms.getPassWord().equals(MD5andKL.MD5(password))){
			return true;
		}else{
			return false;
		}
	}
	
	public void setEmployeeService(EmployeeIn employeeService) {
		this.employeeService = employeeService;
	}

	
	public List queryPermissionId(String userId) {
		
		return null;
	}

	public List queryById(String userName, String passWord) {
		this.ucondition.setRegionQueryHql(userName,passWord);
		List list = null;
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
/**
 * dwr
 */
	public String queryWebService(FhiUser fu) {
		List list = null;
		this.ucondition.setRegionQueryHqlWebService(fu.getUserId(),fu.getPassWord());
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list.size()<=0){
			return "false";
		}else{
			return "true";
		} 
	}
	/**
	 * 
	 * @return
	 */
	public String userSession(String userId){
		List list = null;
		HttpSession session = WebContextFactory.get().getSession();
		this.ucondition.setRegionQueryHqlWebServiceById(userId);
		try {
			list = this.dbDao.queryObjects(ucondition.getHquery().getQueryString());
			FhiUser fu = (FhiUser) list.get(0);
			session.setAttribute(FhiConfig.USER_NAME, fu);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "false";
	}
}
