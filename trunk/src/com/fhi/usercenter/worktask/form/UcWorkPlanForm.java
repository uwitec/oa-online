package com.fhi.usercenter.worktask.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.fhi.usercenter.worktask.vo.FhiUcWorkPlan;

public class UcWorkPlanForm extends ActionForm {
	//分页查询基本属性
	private Integer pageNo;
	private String orderByName;
	private String orderByOrder;
	private String startTime ; 
	private String endTime ;
	private String timeType;
	private String queryText;
	
	
	private FhiUcWorkPlan ucWorkPlan;
	
	private List<FhiUcWorkPlan> list=null;
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public String getOrderByName() {
		return orderByName;
	}
	public void setOrderByName(String orderByName) {
		this.orderByName = orderByName;
	}
	public String getOrderByOrder() {
		return orderByOrder;
	}
	public void setOrderByOrder(String orderByOrder) {
		this.orderByOrder = orderByOrder;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public FhiUcWorkPlan getUcWorkPlan() {		
		ucWorkPlan=ucWorkPlan==null?new FhiUcWorkPlan():ucWorkPlan;		
		return ucWorkPlan;
	}
	public void setUcWorkPlan(FhiUcWorkPlan ucWorkPlan) {
		this.ucWorkPlan = ucWorkPlan;
	}
	public List<FhiUcWorkPlan> getList() {
		return list==null?(new ArrayList<FhiUcWorkPlan>()):list;
	}
	public void setList(List<FhiUcWorkPlan> list) {
		this.list = list;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getQueryText() {
		return queryText;
	}
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	
}
