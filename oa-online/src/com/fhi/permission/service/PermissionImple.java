package com.fhi.permission.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.dao.JdbcDbDaoImple;
import com.fhi.permission.condition.PermissionCondition;
import com.fhi.permission.form.PermissionForm;
import com.fhi.permission.vo.FhiModule;
import com.fhi.permission.vo.FhiRole;
import com.fhi.permission.vo.FhiRolePermission;
import com.fhi.permission.vo.FhiRoleUser;
import com.fhi.user.vo.FhiUser;

public class PermissionImple extends AbstractServiceImple implements PermissionIn{

	private static Logger logger = Logger.getLogger(PermissionImple.class);
	PermissionCondition pcondition;
	FhiRolePermission wmsRoPe;
	JdbcDbDaoImple jdbcDbDao;
	
	public JdbcDbDaoImple getJdbcDbDao() {
		return jdbcDbDao;
	}
	public void setJdbcDbDao(JdbcDbDaoImple jdbcDbDao) {
		this.jdbcDbDao = jdbcDbDao;
	}
	
	public boolean addModule(FhiModule obj) {
		try {
			dbDao.addObject(obj);
			return true;
		} catch (Exception e) {
			logger.error("模块添加失败",e);
			return false;
		}
	}
	
	public boolean addRM(FhiRolePermission obj) {
		try {
			dbDao.addObject(obj);
			return true;
		} catch (Exception e) {
			logger.error("模块添加失败",e);
			return false;
		}
	}
	
	public boolean addRole(FhiRole obj) {
		// TODO Auto-generated method stub
		try {
			dbDao.addObject(obj);
			return true;
		} catch (Exception e) {
			logger.error("用户组添加失败",e);
			return false;
		}
	}
	
	public List getAllRole(){
		List list = null;
		this.pcondition.setRoleQueryHql();
		try {
			list = this.dbDao.queryObjects(pcondition.getHquery().getQueryString());
		} catch (Exception e) {
			logger.error("用户查询失败",e);
		}
		return list;
	}

	/*
	 * 返回用户权限
	 */
	public Map findPermission(FhiUser ws) {
		List list = null;
		Map<String,String> resultList = new HashMap();
		Map<String,String> result = null;
		//this.pcondition.setRolePermissionQueryHql(ws);
		String sql = "select * from fhi_oa_role_user ,fhi_oa_role_permission where fhi_oa_role_user.RoleCode = fhi_oa_role_permission.RoleCode and fhi_oa_role_user.UserName ='"+ws.getUserId()+"'";
		try {
			list = jdbcDbDao.queryJDBCforList(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询权限失败",e);
			return null;
		}
		Iterator it=list.iterator();
	    while(it.hasNext()){
	    	result =(Map)it.next();
	    	resultList.put(result.get("PermissionCode"), result.get("ModuleLevel"));
	    }
		return resultList;
	}
	
	public Map findPermission(FhiUser ws,String webService) {
		List list = null;
		Map<String,String> resultList = new HashMap();
		Map<String,String> result = null;
		//this.pcondition.setRolePermissionQueryHql(ws);
		String sql = "select * from fhi_oa_role_user ,fhi_oa_role_permission where fhi_oa_role_user.RoleCode = fhi_oa_role_permission.RoleCode and fhi_oa_role_user.UserName ='"+ws.getUserId()+"'";
		try {
			list = jdbcDbDao.queryJDBCforList(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询权限失败",e);
			return null;
		}
		Iterator it=list.iterator();
	    while(it.hasNext()){
	    	result =(Map)it.next();
	    		resultList.put(result.get("PermissionCode"), result.get("ModuleLevel"));
	    }
		return resultList;
	}
	
	public Map findPermission(String userId) {
		List list = null;
		Map<String,String> resultList = new HashMap();
		Map<String,String> result = null;
		String sql = "select * from fhi_oa_role_user ,fhi_oa_role_permission where fhi_oa_role_user.RoleCode = fhi_oa_role_permission.RoleCode and fhi_oa_role_user.UserName ='"+userId+"'";
		try {
			list = jdbcDbDao.queryJDBCforList(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询权限失败",e);
			return null;
		}
		Iterator it=list.iterator();
	    while(it.hasNext()){
	    	result =(Map)it.next();
	    	resultList.put(result.get("ModuleName"), result.get("ModuleLevel"));
	    }
		return resultList;
	}
	
	public boolean savePermission(String userName,String[] permission){
		UUID uuid = null;
		String sql= "";
		for(int i=0;i<permission.length;i++){
			uuid = UUID.randomUUID();
			sql = "INSERT INTO fhi_oa_role_user (id,UserName,RoleCode) VALUES ('"+uuid+"','"+userName+"','"+permission[i]+"')";
			try {
				jdbcDbDao.execute(sql);
			} catch (Exception e) {
				logger.error("用户权限保存错误",e);
			}
		}
		return true;
	}
	
	public boolean updatePermission(String userName,String[] permission){
		String delRoleSql = "";
		String updateSql = "";
		UUID uuid = null;
		delRoleSql = "DELETE FROM fhi_oa_role_user where UserName = '"+userName+"'";
		try {
			jdbcDbDao.execute(delRoleSql);
		} catch (Exception e) {
			logger.error("清空权限错误",e);
			return false;
		}
		for(int i=0;i<permission.length;i++){
			uuid = UUID.randomUUID();
			updateSql = "INSERT INTO fhi_oa_role_user (id,UserName,RoleCode) VALUES ('"+uuid+"','"+userName+"','"+permission[i]+"')";
			try {
				jdbcDbDao.execute(updateSql);
			} catch (Exception e) {
				logger.error("用户权限保存错误",e);
			}
		}
		return true;
	}
	
	
	
	public List getAllPermission() {
		List list = null;
		this.pcondition.setPermissionQueryHql();
		try {
			list = this.dbDao.queryObjects(this.pcondition.getHquery().getQueryString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("查询失败",e);
		}
		return list;
		
	}
	
	/**
	 * 返回所有父角色ID
	 * @param FhiRole role
	 * @return
	 */
	public List<String> queryRoleParentID(FhiRole role){
		List<String> list = new ArrayList<String>();
//		list.add(role.getId());
		try {
			FhiRole role_ = (FhiRole) this.dbDao.queryObjectById(FhiRole.class, role.getPid());
				if(role_!=null){
					list.add(role_.getId());
					while (role_.getPid()!=null&&!"-1".equals(role_.getPid())){						
						role_ = (FhiRole) this.dbDao.queryObjectById(FhiRole.class, role_.getPid());
						list.add(role_.getId());
					}
				}
		} catch (Exception e) {
			logger.error("返回父角色错误！ID:"+role.getId()+",name:"+role.getRoleName(), e);
		}
		return list;
	}
	
	/**
	 *
	 * @return
	 */
	
	public List<String> queryRoleParentID(String roleId){
		List<String> list = new ArrayList<String>();
//		list.add(role.getId());
		FhiRole role_ById = null;
		try{
			
			role_ById = (FhiRole) this.dbDao.queryObjectById(FhiRole.class, roleId);
			
		}catch(Exception e){
			logger.error("返回父角色错误！ID:"+roleId+",name:"+roleId, e);
		}
		try {
			FhiRole role_ = (FhiRole) this.dbDao.queryObjectById(FhiRole.class, role_ById.getPid());
				if(role_!=null){
					list.add(role_.getId());
					while (role_.getPid()!=null&&!"-1".equals(role_.getPid())){						
						role_ = (FhiRole) this.dbDao.queryObjectById(FhiRole.class, role_.getPid());
						list.add(role_.getId());
					}
				}
		} catch (Exception e) {
			logger.error("返回父角色错误！ID:"+roleId+",name:"+roleId, e);
		}
		return list;
	}
	
	public PermissionCondition getPcondition() {
		return pcondition;
	}
	public void setPcondition(PermissionCondition pcondition) {
		this.pcondition = pcondition;
	}

	public void updatePid(String pid) {
		FhiRole fr = null;
		try {
			fr = (FhiRole)this.dbDao.queryObjectById(FhiRole.class, pid);
		} catch (Exception e1) {
			logger.debug("未找到父角色");
		}
		fr.setIsPid("0");
		try {
			this.dbDao.updateObject(fr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("角色更新失败");
		}
	}
		
	public boolean queryRoleUser(String id){
		List list = null;
		this.pcondition.setQueryRoleUser(id);
		try {
			list = this.dbDao.queryObjects(this.pcondition.getHquery().getQueryString());
			if(list.size()<=0){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("没有用户使用这个权限");
		}
		return false;
	}
	
	public boolean delThisRole(String id,String isPid,String pid){
		FhiRole pidfr = null;
		FhiRole fr = null;
		FhiRolePermission frp = null;
		List list = null;
		if ("1".equals(isPid)){
			try {
				pidfr = (FhiRole)this.dbDao.queryObjectById(FhiRole.class, pid);
				pidfr.setIsPid("1");
				this.update(pidfr);
				fr = (FhiRole)this.dbDao.queryObjectById(FhiRole.class, id);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("未找到相应的角色",e);
			}
			
			this.pcondition.setQuerRolePermission(id);
			try {
				list = this.dbDao.queryObjects(this.pcondition.getHquery().getQueryString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("角色未分配权限",e);
			}
		}
		try {
			this.dbDao.deleteObject(fr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("删除角色错误",e);
		}
		for(int i=0;i<list.size();i++){
			try {
				this.dbDao.deleteObject((FhiRolePermission)list.get(i));
			} catch (Exception e) {
				logger.debug("删除角色权限时错误",e);
			}
		}
		return true;
	}
	
	public Map findRole(FhiUser ws){
		List<FhiRoleUser> list = null;
		Map<String,FhiRole> result = new HashMap<String,FhiRole>();
		this.pcondition.setQueryRoleUserByName(ws.getUserId());
		try {
			list = this.dbDao.queryObjects(this.pcondition.getHquery().getQueryString());
		} catch (Exception e) {
			logger.debug("未找到相应角色",e);
		}
		try {
		for(int i=0;i<list.size();i++){
			FhiRole fr;
				fr = (FhiRole) this.dbDao.queryObjectById(FhiRole.class, list.get(i).getRoleCode());
			
			result.put(list.get(i).getRoleCode(),fr);
			}
		}
		 catch (Exception e) {
				logger.debug("查询角色错误",e);
			}
		return result;
	}

	public FhiRole getRoleName(String id) {
		FhiRole fr = null;
		try {
			fr = (FhiRole)this.dbDao.queryObjectById(FhiRole.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fr;
	}
	
	public void updateRole(FhiRole fr){
		try {
			this.dbDao.updateObject(fr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List getAllPermissionById(String id) {
		this.pcondition.setHqlStringPermission(id);
		List list = null;
		try {
			list = this.dbDao.queryObjects(this.pcondition.getHquery().getQueryString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pcondition.setPermissionQueryHql();
		List list1 = null;
		/*try {
			list1 = this.dbDao.queryObjects(this.pcondition.getHquery().getQueryString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return list;
	}

	public void delRolePermissionByCode(List stocks) {
		for(int i=0;i<stocks.size();i++){
			FhiRolePermission stock = (FhiRolePermission)stocks.get(i);
			String sql = "delete from fhi_oa_role_permission where fhi_oa_role_permission.RoleCode = '"+stock.getRoleCode()+"'";
			try {
				this.jdbcDbDao.execute(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("删除失败"+i);
			}
		}
	} 
	
	public List getAllMod(ActionForm form) {
		List<FhiModule> list = null;
		this.pcondition.setModQueryHql((PermissionForm)form);
		try {
			list = this.dbDao.queryObjectsToPages(this.setPageInfo(pcondition));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 返回为主目录的模块
	 */
	
	public List<String> getModel(String isModify){
		List<String> list = null;
		if("1".equals(isModify)){//等于1为修改时的查询
			String hql = " select s.id,s.moduleCode from FhiModule s where isPid = 1 order by id asc" ;
			try {
				list = (List<String>)this.dbDao.queryObjects(hql) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			String hql = " select s.id,s.moduleCode from FhiModule s where isPid = 1 order by id asc" ;
			try {
				list = (List<String>)this.dbDao.queryObjects(hql) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List getModel(){
		List list = null;
		this.pcondition.setHqlStringPermissionMod();
			try {
				list = this.dbDao.queryObjects(this.pcondition.getHquery().getQueryString()) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	/*
	 * 返回主目录下的权限
	 */
	
	
	public List getModelByPid(String isModify,String roleId){
		List list = null;
		List<String[]> tempList=new ArrayList<String[]>();
		List moduleList = null;
		FhiRolePermission fhi = null;
		FhiModule fhiMo = null;
		if ("1".equals(isModify)){//等于1为修改时的查询
			//String hql = " select s.id,s.moduleName,s.modulePid,s.moduleCode,s.moduleType,s.moduleLevel from FhiRolePermission s where roleCode = '"+roleId+"' order by modulePid asc" ;
			//String hql = " select new com.fhi.permission.vo.FhiRolePermission(fhiRo) from FhiRolePermission fhiRo  where roleCode = '"+roleId+"' order by modulePid asc" ;
			this.pcondition.setHqlStringPermission(roleId);
			try {
				list = this.dbDao.queryObjects(this.pcondition.getHquery().getQueryString()) ;
					for(int i=0;i<list.size();i++){
						String[] str = new String[6];
						fhi = (FhiRolePermission) list.get(i);
						str[0] = fhi.getId();str[1] = fhi.getModuleName();str[2] = fhi.getModulePid();str[3] = fhi.getPermissionCode();str[4] = fhi.getModuleType();str[5] = fhi.getModuleLevel();
						tempList.add(str);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.pcondition.setModelPidQueryHql();
			try {
				boolean flag = false;
				moduleList = this.dbDao.queryObjects(this.pcondition.getHquery().getQueryString());
				for(int i=0;i<moduleList.size();i++){
					fhiMo = (FhiModule) moduleList.get(i);
					for(int j=0;j<list.size();j++){
						fhi = (FhiRolePermission) list.get(j);
						if(fhiMo.getModuleCode().equals(fhi.getModuleName())){
							flag = true;
							break;
						}else{
							flag = false;
						}
					}
					if (!flag){
						String[] str = new String[6];
						str[0] = fhiMo.getId();str[1] = fhiMo.getModuleCode();str[2] = fhiMo.getPid();str[3] = fhiMo.getModuleName();str[4] = fhiMo.getModuleType();str[5] = fhiMo.getModuleLevel();
						tempList.add(str);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.clear();
			list = tempList;
		}else{
			String hql = " select s.id,s.moduleCode,s.pid,s.moduleName,s.moduleType,s.moduleLevel from FhiModule s where isPid <> 1 order by pid asc" ;
			try {
				list = (List<String>)this.dbDao.queryObjects(hql) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public List query() {
		return null;
	}
}
