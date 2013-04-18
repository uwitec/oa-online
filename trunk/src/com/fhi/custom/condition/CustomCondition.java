package com.fhi.custom.condition;

import org.apache.log4j.Logger;

import com.fhi.custom.action.CustomForm;
import com.fhi.framework.page.Condition;
import com.fhi.user.vo.FhiUser;

public class CustomCondition extends Condition {

	private static Logger logger = Logger.getLogger(CustomCondition.class);	
	public CustomCondition setCustomHql(CustomForm form,FhiUser user){
		
		//设置分页
		Integer pageNo = form.getPageNo();
		if (pageNo != null) { 
			this.setPageNo(pageNo);
		}
		
		//设置排序
		String orderByName = form.getOrderByBame() ;
		String orderByOrder = form.getOrderByOrder() ;
		if(orderByName!=null && !orderByName.trim().equals("")){
			this.setOrderBy(" Order By "+orderByName.trim()+ " "+(orderByOrder.trim().equals("1") ? "ASC" : "DESC")+" ") ;
		}else{
			this.setOrderBy(" Order By addDate DESC ") ;
		}
		
		//条件组装
		StringBuilder hql = new StringBuilder(" from FhiOaCustom where (1=1) ") ;
		String name = form.getCustom().getFullName() ;
		if(name!=null && !name.trim().equals("")){
			hql.append(" and (comCode like '%"+name.trim()+"%' or fullName like '%"+name.trim()
					+"%' or cutName like '%"+name.trim()+"%' or enName like '%"+name.trim()+"%') ") ;
		}
		String address = form.getCustom().getAddress() ;
		if(address!=null && !address.trim().equals("")){
			hql.append(" and (address like '%"+address.trim()+"%' or enAddress like '%"+address.trim()+"%') ") ;
		}
		String homepage = form.getCustom().getHomePage() ;
		if(homepage!=null && !homepage.trim().equals("")){
			hql.append(" and (homePage like '%"+homepage.trim()+"%') ") ;
		} 
		
		if(!"true".equals(user.getPermissionMap().get("custom_all")) && !"true".equals(user.getPermissionMap().get("custom_select"))){ 
			hql.append(" and (creator='"+user.getUserId()+"' or workorId='"+user.getUserId()
					+"' or custServiceId='"+user.getUserId()+"' or updatePerson='"+user.getUserId()+" ') ") ;
		}	 
 
		//设置条件
		this.setHqlString(hql.toString()) ; 
		
		return this ; 
	}

}
