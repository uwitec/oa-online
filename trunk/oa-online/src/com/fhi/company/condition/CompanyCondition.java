package com.fhi.company.condition;

import com.fhi.company.action.CompanyForm;
import com.fhi.framework.page.Condition;

public class CompanyCondition extends Condition {
	
	public CompanyCondition setCompanyHql(CompanyForm form){
		
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
		StringBuilder hql = new StringBuilder(" from FhiOaCompany where (1=1) ") ;
		String name = form.getCompany().getFullName() ;
		if(name!=null && !name.trim().equals("")){
			hql.append(" and (comCode like '%"+name.trim()+"%' or fullName like '%"+name.trim()
					+"%' or cutName like '%"+name.trim()+"%' or enName like '%"+name.trim()+"%') ") ;
		}
		String address = form.getCompany().getAddress() ;
		if(address!=null && !address.trim().equals("")){
			hql.append(" and (address like '%"+address.trim()+"%' or enAddress like '%"+address.trim()+"%') ") ;
		}
		String homepage = form.getCompany().getHomePage() ;
		if(homepage!=null && !homepage.trim().equals("")){
			hql.append(" and (homePage like '%"+homepage.trim()+"%') ") ;
		}
		
		//设置条件
		this.setHqlString(hql.toString()) ; 
		
		return this ; 
	}

}
