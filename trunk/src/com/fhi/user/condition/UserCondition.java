package com.fhi.user.condition;

import com.fhi.framework.page.Condition;
import com.fhi.user.MD5.MD5andKL;
import com.fhi.user.form.AddUserForm;


public class UserCondition extends Condition{
	
	MD5andKL md5 = new MD5andKL();
	
	public UserCondition(){
		
	}
	
	public void setRegionQueryHqlById(String id){
		StringBuilder sql = new StringBuilder("from FhiUser where 1=1");
		this.setHqlString(sql.toString());
	}
	
	public void setRegionQueryHqlUserPermission(String userName){
		StringBuilder sql = new StringBuilder("from FhiRoleUser where 1=1 and UserName='"+userName+"'");
		this.setHqlString(sql.toString());
	}
	
	public void setRegionQueryHqlPermission(){
		StringBuilder sql = new StringBuilder("from FhiRole where isPid = 1");
		this.setHqlString(sql.toString());
	}
	
	public void setRegionQueryHqlPermissionWithFhiRole(){
		StringBuilder sql = new StringBuilder("from FhiRole where 1=1");
		this.setHqlString(sql.toString());
	}
	
	public void setRegionQueryHql(){
		StringBuilder sql = new StringBuilder("from FhiUser where 1=1");
		this.setHqlString(sql.toString());
	}
	
	public void dwrSetRegionQueryHql(String userName,String passWord){
		StringBuilder sql = new StringBuilder("from FhiUser where 1=1 and userId='"+userName+"' and "+"passWord='"+passWord+"'");
		this.setHqlString(sql.toString());
	}
	
	public void setRegionQueryHqlIsExist(String userName){
		StringBuilder sql = new StringBuilder("from FhiUser where 1=1 and userId='"+userName+"'");
		this.setHqlString(sql.toString());
	}
	public void setRegionQueryHql(String id){
		StringBuilder sql = new StringBuilder("from FhiUser where 1=1 and id='"+id+"'");
		this.setHqlString(sql.toString());
	}
	
	public void setRegionQueryHql(String userId,String passWord){
		// 查询条件
		StringBuilder sql = new StringBuilder("from FhiUser where 1=1 and userId='"+userId+"'"+" and passWord='"+md5.MD5(passWord)+"'");
		this.setHqlString(sql.toString());
	}
	
	public void setRegionQueryHqlWebService(String userId,String passWord){
		// 查询条件
		StringBuilder sql = new StringBuilder("from FhiUser where 1=1 and userId='"+userId+"'"+" and passWord='"+passWord+"'");
		this.setHqlString(sql.toString());
	}
	public void setRegionQueryHqlWebServiceById(String userId){
		// 查询条件
		StringBuilder sql = new StringBuilder("from FhiUser where 1=1 and id='"+userId+"'");
		this.setHqlString(sql.toString());
	}
	
	public void setRegionQueryHqlid(AddUserForm af){
		// 查询条件
		StringBuilder sql = new StringBuilder("from FhiUser where 1=1 ");
		if (af.getWu().getUserId() != null
				&& !"".equals(af.getWu().getUserId())) {
			sql.append(" and userId = '" + af.getWu().getUserId() + "'");
		}
		this.setHqlString(sql.toString());
	}

	public void setRegionQueryHqlName(AddUserForm af) {
		StringBuilder sql = new StringBuilder("from FhiUser where 1=1 ");
		this.setOrderBy(" order by id desc");
		if(af.getQueryUserId()!= null && !"".equals(af.getQueryUserId())){
			sql.append("and UserId LIKE '%"+af.getQueryUserId()+"%'");
		}
		if(af.getQueryUserName()!=null && !"".equals(af.getQueryUserName())){
			sql.append("and employee LIKE '%"+af.getQueryUserName()+"%'");
		}
		this.setPageNo(af.getPageNo());
		this.setHqlString(sql.toString());
	}
	
	public void setRegionQueryHqlByUserId(String id){
		// 查询条件
		StringBuilder sql = new StringBuilder("from FhiUser where userId= '"+id+"' ");
		this.setHqlString(sql.toString());
	}
	
}