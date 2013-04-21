package com.fhi.system.sysmsg.condition;

import com.fhi.framework.page.Condition;
import com.fhi.system.sysmsg.form.SysMsgForm;
import com.fhi.system.sysmsg.vo.SysMsg;
import com.fhi.user.vo.FhiUser;

public class SysMsgCondition extends Condition{
	
	public void queryList(SysMsgForm form,FhiUser user){
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
				
		// 查询条件组装开始
		StringBuilder sql = new StringBuilder("from SysMsg where userId = '" + user.getUserId() + "' ");
		
		SysMsg sysMsg = form.getSysMsg();
		
		//标题查询
		if (sysMsg.getTitle() != null&&!"".equals(sysMsg.getTitle())) {
			sql.append(" and title like '%" + sysMsg.getTitle() + "%' ") ;
		}
		
		//类型查询
		if (sysMsg.getSysType() != null&&sysMsg.getSysType().intValue()!=100) {
			sql.append(" and sysType = " + sysMsg.getSysType() + " ") ;
		}
		
		//分类查询
		if (sysMsg.getSysClass() != null) {
			sql.append(" and sysClass = " + sysMsg.getSysClass() + " ") ;
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
			this.setOrderBy(" Order By createDate Desc");
		}
		// 查询条件组装结束
		this.setHqlString(sql.toString());
	}	
	
	public void mainQuerySys(FhiUser user){
		StringBuilder sql = new StringBuilder("from SysMsg where userId = '" + user.getUserId() + "' ");
		sql.append(" and sysType > 0 ");
		this.setOrderBy(" Order By isRead , sysClass%2 DESC");
		this.setHqlString(sql.toString());
	}
	
	public void mainQueryWork(FhiUser user){
		StringBuilder sql = new StringBuilder("from SysMsg where userId = '" + user.getUserId() + "' ");
		sql.append(" and sysType = 0 ");
		this.setOrderBy(" Order By isRead , sysClass%2 DESC");
		this.setHqlString(sql.toString());
	}
}
