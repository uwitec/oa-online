package com.fhi.company.action;

import org.apache.struts.action.ActionForm;

import com.fhi.company.vo.FhiOaCompany;

public class CompanyForm extends ActionForm {
	
	private Integer pageNo;
	private String orderByBame;
	private String orderByOrder;
	private String startTime ; 
	private String contractTime ;
	private String endTime ; 
	private String []ids ; 
	private FhiOaCompany company = new FhiOaCompany() ;
	
	
	public FhiOaCompany getCompany() {
		return company;
	}
	public void setCompany(FhiOaCompany company) {
		this.company = company;
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
