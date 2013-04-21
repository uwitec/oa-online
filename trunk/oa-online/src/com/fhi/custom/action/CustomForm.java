package com.fhi.custom.action;

import org.apache.struts.action.ActionForm;

import com.fhi.custom.vo.FhiOaCustom;

public class CustomForm extends ActionForm {
	
	private Integer pageNo;
	private String orderByBame;
	private String orderByOrder;
	private String startTime ; 
	private String contractTime ;
	private String endTime ;  
	private String addTime ; 
	private String []ids ; 
	private FhiOaCustom custom = new FhiOaCustom() ;
	
	
	public FhiOaCustom getCustom() {
		return custom;
	}
	public void setCustom(FhiOaCustom custom) {
		this.custom = custom;
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
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}
