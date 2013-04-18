package com.fhi.information.freight.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.fhi.information.freight.vo.FhiFreight;

public class FreightForm extends ActionForm {
	//分页查询基本属性
	private Integer pageNo;
	private String orderByName;
	private String orderByOrder;
	private String waySelect;
	private String selectWeight;
	private String startTime ; 
	private String endTime ;
	private List<FhiFreight> list;
	private FhiFreight freight;
	
	public List<FhiFreight> getList() {
		if(list==null){
			list = new ArrayList<FhiFreight>();
		}
		return list;
	}
	public void setList(List<FhiFreight> list) {
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
	public FhiFreight getFreight() {
		if(freight==null){
			freight=new FhiFreight();
		}
		return freight;
	}
	public void setFreight(FhiFreight freight) {
		this.freight = freight;
	}
	public String getWaySelect() {
		return waySelect;
	}
	public void setWaySelect(String waySelect) {
		this.waySelect = waySelect;
	}
	public String getSelectWeight() {
		return selectWeight;
	}
	public void setSelectWeight(String selectWeight) {
		this.selectWeight = selectWeight;
	}	
}
