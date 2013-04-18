package com.fhi.usercenter.worktask.condition;

import com.fhi.framework.page.Condition;
import com.fhi.user.vo.FhiUser;
import com.fhi.usercenter.worktask.form.UcWorkPlanForm;
import com.fhi.usercenter.worktask.vo.FhiUcWorkPlan;

public class UcWorkPlanCondition extends Condition{
	
	public void queryList(UcWorkPlanForm form,FhiUser user){
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
		
		// 查询条件组装开始
		StringBuilder sql = new StringBuilder("from FhiUcWorkPlan where 1=1 ");
		
		FhiUcWorkPlan ucWockPlan = form.getUcWorkPlan();		
		if(!"true".equals(user.getPermissionStr("isQueryAll"))){
			sql.append(" and creater = '" + user.getUserId() + "' ");
		}
		//按标题查询
		if (ucWockPlan.getTitle() != null
				&& !"".equals(ucWockPlan.getTitle().trim())) {
			sql.append(" and title like '%" + ucWockPlan.getTitle().trim() + "%'");
		}
		
		//按时间范围查询
		if (form.getStartTime() != null && !"".equals(form.getStartTime())
				&& form.getEndTime() != null && !"".equals(form.getEndTime())
				&&("createDate".equals(form.getTimeType())||"startingTime".equals(form.getTimeType())||"remindTime".equals(form.getTimeType()))) {
			sql.append(" and "+form.getTimeType()+" BETWEEN '" + form.getStartTime()
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
