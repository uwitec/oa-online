package com.fhi.client.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class ClientForm extends ActionForm{

	private Integer pageNo;
	private String orderByName;
	private String orderByOrder;
	private String inpageinfo;
	private String startTime ; 
	private String endTime ;
	private String startTimePod ; 
	private String endTimePod ;
	
	private String userName;
	private String passWord;
	
	
	private List list=null;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public List getList() {
		if(list==null){
			list = new ArrayList();
		}
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
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
	public String getStartTimePod() {
		return startTimePod;
	}
	public void setStartTimePod(String startTimePod) {
		this.startTimePod = startTimePod;
	}
	public String getEndTimePod() {
		return endTimePod;
	}
	public void setEndTimePod(String endTimePod) {
		this.endTimePod = endTimePod;
	}	
	
}
