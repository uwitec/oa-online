package com.fhi.system.sysmsg.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.fhi.system.sysmsg.vo.SysMsg;

public class SysMsgForm extends ActionForm {
	//分页查询基本属性
	private Integer pageNo;
	private String orderByName;
	private String orderByOrder;
	private String startTime ; 
	private String endTime ;
	private SysMsg sysMsg;
	private List<SysMsg> list=null;
	
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
	public SysMsg getSysMsg() {
		if(sysMsg==null){
			sysMsg = new SysMsg();
		}
		return sysMsg;
	}
	public void setSysMsg(SysMsg sysMsg) {
		this.sysMsg = sysMsg;
	}
	public List<SysMsg> getList() {
		return list==null?(new ArrayList<SysMsg>()):list;
	}
	public void setList(List<SysMsg> list) {
		this.list = list;
	}
}
