package com.fhi.employee.condition;

import org.apache.log4j.Logger;

import com.fhi.employee.action.EmployeeForm;
import com.fhi.framework.page.Condition;

public class EmployeeCondition extends Condition {
	
	private static Logger logger = Logger.getLogger(EmployeeCondition.class);	
	
	public EmployeeCondition setEmployeeHql(EmployeeForm form){
		
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
		StringBuilder hql = new StringBuilder(" from FhiOaEmployeeBasic where (1=1) ") ;
		String name = form.getBasic().getName() ;
		if(name!=null && !name.trim().equals("")){
			hql.append(" and name like '%"+name.trim()+"%' ") ;
		}
		
		String company = form.getBasic().getCompanyName();
		if(company!=null && !company.trim().equals("")){
			hql.append(" and companyName like '%"+company.trim()+"%' ") ;
		}
		
		String dep = form.getBasic().getDepName();
		if(dep!=null && !dep.trim().equals("")){
			hql.append(" and depName like '%"+dep.trim()+"%' ") ;
		}
		
		String state = form.getBasic().getEmployeeState() ; 
		if(state!=null && !state.trim().equals("")){
			hql.append(" and employeeState='"+state.trim()+"' ") ;
		} 
		
		String degree = form.getBasic().getDegree(); 
		if(degree!=null && !degree.trim().equals("")){
			hql.append(" and degree like '%"+degree.trim()+"%' ") ;
		} 
		
		String politics = form.getBasic().getPolitics(); 
		if(politics!=null && !politics.trim().equals("")){
			hql.append(" and politics like '%"+politics.trim()+"%' ") ;
		} 
		
		String marry = form.getBasic().getMarry(); 
		if(marry!=null && !marry.trim().equals("")){
			hql.append(" and marry='"+marry.trim()+"' ") ;
		}
		
		String starttime = form.getStartTime() ;
		String endtime = form.getEndTime() ; 
		String timeitem = form.getTimeitem();  
		if(timeitem==null || timeitem.trim().equals("") ||timeitem.equals("addDate")){
			if(starttime!=null && !starttime.trim().equals("")){ 
				hql.append(" and addDate>='"+starttime.trim()+"' ") ;
			}
			if(endtime!=null && !endtime.trim().equals("")){ 
				hql.append(" and addDate<='"+endtime.trim()+"' ") ;
			} 
		}else if(timeitem.equals("inWorkDate")){
			if(starttime!=null && !starttime.trim().equals("")){ 
				hql.append(" and inWorkDate>='"+starttime.trim()+"' ") ;
			}
			if(endtime!=null && !endtime.trim().equals("")){ 
				hql.append(" and inWorkDate<='"+endtime.trim()+"' ") ;
			}
		}else if(timeitem.equals("realWorkDate")){
			if(starttime!=null && !starttime.trim().equals("")){ 
				hql.append(" and realWorkDate>='"+starttime.trim()+"' ") ;
			}
			if(endtime!=null && !endtime.trim().equals("")){ 
				hql.append(" and realWorkDate<='"+endtime.trim()+"' ") ;
			}
		}else if(timeitem.equals("endDate")){
			if(starttime!=null && !starttime.trim().equals("")){ 
				hql.append(" and endDate>='"+starttime.trim()+"' ") ;
			}
			if(endtime!=null && !endtime.trim().equals("")){ 
				hql.append(" and endDate<='"+endtime.trim()+"' ") ;
			}
		}else if(timeitem.equals("birthday")){
			if(starttime!=null && !starttime.trim().equals("")){ 
				hql.append(" and birthday>='"+starttime.trim()+"' ") ;
			}
			if(endtime!=null && !endtime.trim().equals("")){ 
				hql.append(" and birthday<='"+endtime.trim()+"' ") ;
			}
		}else if(timeitem.equals("offWorkDate")){
			if(starttime!=null && !starttime.trim().equals("")){ 
				hql.append(" and offWorkDate>='"+starttime.trim()+"' ") ;
			}
			if(endtime!=null && !endtime.trim().equals("")){ 
				hql.append(" and offWorkDate<='"+endtime.trim()+"' ") ;
			}
		}else if(timeitem.equals("nextCheck")){
			if(starttime!=null && !starttime.trim().equals("")){ 
				hql.append(" and nextCheck>='"+starttime.trim()+"' ") ;
			}
			if(endtime!=null && !endtime.trim().equals("")){ 
				hql.append(" and nextCheck<='"+endtime.trim()+"' ") ;
			}
		}else if(timeitem.equals("startWrokDate")){
			if(starttime!=null && !starttime.trim().equals("")){ 
				hql.append(" and startWrokDate>='"+starttime.trim()+"' ") ;
			}
			if(endtime!=null && !endtime.trim().equals("")){ 
				hql.append(" and startWrokDate<='"+endtime.trim()+"' ") ;
			}
		}  
		
		//设置条件
		this.setHqlString(hql.toString()) ; 
		
		return this ; 
	}

}
