package com.fhi.framework.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author sunh
 * 
 */
public abstract class FHIForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * action中跳转的方法
	 * */
	private String method;
	
	
	private String[] ids;
	
	private Integer pageNo;
	
	private String orderByName;
	
	private String orderByOrder;
	
	private String inpageinfo;
	
	private String startTime;
	
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

	private String endTime;
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

	public String getInpageinfo() {
		return inpageinfo;
	}

	public void setInpageinfo(String inpageinfo) {
		this.inpageinfo = inpageinfo;
	}

	
	 
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}



}
