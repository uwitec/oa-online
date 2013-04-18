package com.fhi.department.condition;

import org.apache.log4j.Logger;

import com.fhi.department.action.DepartmentForm;
import com.fhi.framework.page.Condition;

public class DepartmentCondition extends Condition {

	private static Logger logger = Logger.getLogger(DepartmentCondition.class);
	public DepartmentCondition setDepHql(DepartmentForm form){
		
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
		StringBuilder hql = new StringBuilder(" from FhiOaDepartment where (1=1) ") ;
		
		String name = form.getDepartment().getDepName();
		if(name!=null && !name.trim().equals("")){
			hql.append(" and depName like '%"+name.trim()+"%' ") ;
		}  
		
		String parentCom = form.getDepartment().getComName();
		if(parentCom!=null && !parentCom.trim().equals("")){
			hql.append(" and comName like '%"+parentCom.trim()+"%' ") ;
		}  
		
		String parentDep = form.getDepartment().getParentDepName();
		if(parentDep!=null && !parentDep.trim().equals("")){
			hql.append(" and parentDepName like '%"+parentDep.trim()+"%' ") ;
		}   
		//设置条件
		this.setHqlString(hql.toString()) ; 
		
		return this ; 
	}

}
