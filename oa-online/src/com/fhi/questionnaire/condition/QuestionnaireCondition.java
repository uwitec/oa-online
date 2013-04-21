package com.fhi.questionnaire.condition;

import org.apache.log4j.Logger;

import com.fhi.framework.page.Condition;
import com.fhi.questionnaire.form.QuestionnaireForm;

public class QuestionnaireCondition extends Condition {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(QuestionnaireCondition.class) ;
	
	public QuestionnaireCondition setHql(QuestionnaireForm form){
		//		设置分页
		Integer pageNo = form.getPageNo();
		if (pageNo != null) { 
			this.setPageNo(pageNo);
		}
		
		//设置排序
		String orderByName = form.getOrderByName() ;
		String orderByOrder = form.getOrderByOrder() ;
		if(orderByName!=null && !orderByName.trim().equals("")){
			this.setOrderBy(" Order By "+orderByName.trim()+ " "+(orderByOrder.trim().equals("1") ? "ASC" : "DESC")+" ") ;
		}else{
			this.setOrderBy(" Order By questionnaireStartTime ") ;
		}
		
		//条件组装
		StringBuilder hql = new StringBuilder(" from QuestionnaireSetup where (1=1) ") ;
		
		 
		
		 
		
		 
		 
		 
		 
		 
		 
		
		  
		//设置条件
		this.setHqlString(hql.toString()) ;  
		return this ; 
	}
}
