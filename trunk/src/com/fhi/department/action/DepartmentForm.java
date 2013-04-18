package com.fhi.department.action;

import org.apache.struts.action.ActionForm;

import com.fhi.department.vo.FhiOaDepartment;

public class DepartmentForm extends ActionForm {
	
	private Integer pageNo;
	private String orderByBame;
	private String orderByOrder;
	private String startTime ; 
	private String contractTime ; 
	private String endTime ; 
	private String []ids ; 
	private FhiOaDepartment department = new FhiOaDepartment() ;
	
	
	public FhiOaDepartment getDepartment() {
		return department;
	}
	public void setDepartment(FhiOaDepartment department) {
		this.department = department;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getOrderByBame() {
		return orderByBame;
	}
	public void setOrderByBame(String orderByBame) {
		this.orderByBame = orderByBame;
	}
	public String getOrderByOrder() {
		return orderByOrder;
	}
	public void setOrderByOrder(String orderByOrder) {
		this.orderByOrder = orderByOrder;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String getContractTime() {
		return contractTime;
	}
	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}

}
