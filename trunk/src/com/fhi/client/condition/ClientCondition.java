package com.fhi.client.condition;

import org.apache.log4j.Logger;

import com.fhi.client.form.ClientForm;
import com.fhi.framework.page.Condition;
public class ClientCondition extends Condition{
	
	private Logger logger = Logger.getLogger(ClientCondition.class) ;
	public void hkSendQuery(ClientForm form){
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
				
		// 查询条件组装开始
		StringBuilder sql = new StringBuilder(" from FhiOaTykeAllinfo where (1=1) ");  
		String orderByName = form.getOrderByName();
		String orderByOrder = form.getOrderByOrder();		
		if (!"".equals(orderByName) && orderByName != null) {
			this.setOrderBy(" Order By " + orderByName + " "
					+ ("1".equals(orderByOrder) ? "ASC" : "DESC"));
		}
		else{
			this.setOrderBy(" Order By createDate Desc");
		}
		
		String starttime = form.getStartTime();
		if(starttime!=null && !starttime.trim().equals(""))
			sql.append(" and deliveryDate >= '"+starttime.trim()+"'") ;
		String endtime = form.getEndTime();
		if(endtime!=null && !endtime.trim().equals(""))
			sql.append(" and deliveryDate <= '"+endtime.trim()+"'") ; 
		
		String starttimePod = form.getStartTimePod();
		if(starttimePod!=null && !starttimePod.trim().equals(""))
			sql.append(" and pod >= '"+starttimePod.trim()+"'") ;
		String endtimePod = form.getEndTimePod();
		if(endtimePod!=null && !endtimePod.trim().equals(""))
			sql.append(" and pod <= '"+endtimePod.trim()+"'") ; 
		
		// 查询条件组装结束
		this.setHqlString(sql.toString());
	}
}
