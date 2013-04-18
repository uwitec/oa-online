package com.fhi.information.freight.condition;

import com.fhi.framework.page.Condition;
import com.fhi.information.freight.form.FreightForm;
import com.fhi.information.freight.vo.FhiFreight;
import com.fhi.user.vo.FhiUser;
public class FreightCondition extends Condition{
	
	public void queryList(FreightForm form,FhiUser user){
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
		
		// 查询条件组装开始
		StringBuilder sql = new StringBuilder("from FhiFreight where 1=1");
		
		FhiFreight freight = form.getFreight();
		if (freight.getCode() != null
				&& !"".equals(freight.getCode())) {
			sql.append(" and code like '%" + freight.getCode() + "%'");
		}		
		
		if (freight.getToName() != null
				&& !"".equals(freight.getToName())) {
			sql.append(" and (toCode = '" + freight.getToName() + "'");
			sql.append(" or toName = '" + freight.getToName() + "')");
		}
		
		if (freight.getFromName() != null
				&& !"".equals(freight.getFromName())) {			
			sql.append(" and (fromCode = '" + freight.getFromName() + "'");
			sql.append(" or fromName = '" + freight.getFromName() + "')");
		}
		
		//按时间范围查询
		if (form.getStartTime() != null && !"".equals(form.getStartTime())
				&& form.getEndTime() != null && !"".equals(form.getEndTime())) {
			sql.append(" and createDate BETWEEN '" + form.getStartTime()
					+ "' AND '" + form.getEndTime() + "'");
		}		
		
		// 排序传接值设置
		String orderByName = form.getOrderByName();
		String orderByOrder = form.getOrderByOrder();		
		if (!"".equals(orderByName) && orderByName != null) {
			this.setOrderBy(" Order By " + orderByName + " "
					+ ("1".equals(orderByOrder) ? "ASC" : "DESC"));
		}
		else{
			this.setOrderBy(" Order by createDate Desc");
		}
		// 查询条件组装结束
		this.setHqlString(sql.toString());
	}
	
	public void queryManage(FreightForm form,FhiUser user){
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
		
		// 查询条件组装开始
		StringBuilder sql = new StringBuilder("from FhiFreight where 1=1");
		
		FhiFreight freight = form.getFreight();
		
		if(!"true".equals(user.getPermissionStr("isUpdatePriceAll"))){
			sql.append(" and creater like '" + user.getUserId() + "'");
		}
		
		if (freight.getCode() != null
				&& !"".equals(freight.getCode())) {
			sql.append(" and code like '%" + freight.getCode() + "%'");
		}		
		
		if (freight.getToName() != null
				&& !"".equals(freight.getToName())) {
			sql.append(" and (toCode = '" + freight.getToName() + "'");
			sql.append(" or toName = '" + freight.getToName() + "')");
		}
		
		if (freight.getFromName() != null
				&& !"".equals(freight.getFromName())) {			
			sql.append(" and (fromCode = '" + freight.getFromName() + "'");
			sql.append(" or fromName = '" + freight.getFromName() + "')");
		}
		
		
		//按时间范围查询
		if (form.getStartTime() != null && !"".equals(form.getStartTime())
				&& form.getEndTime() != null && !"".equals(form.getEndTime())) {
			sql.append(" and createDate BETWEEN '" + form.getStartTime()
					+ "' AND '" + form.getEndTime() + "'");
		}		
		
		// 排序传接值设置
		String orderByName = form.getOrderByName();
		String orderByOrder = form.getOrderByOrder();		
		if (!"".equals(orderByName) && orderByName != null) {
			this.setOrderBy(" Order By " + orderByName + " "
					+ ("1".equals(orderByOrder) ? "ASC" : "DESC"));
		}
		else{
			this.setOrderBy(" Order by createDate Desc");
		}
		// 查询条件组装结束
		this.setHqlString(sql.toString());
	}
}
