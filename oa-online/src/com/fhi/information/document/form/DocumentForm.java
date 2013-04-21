package com.fhi.information.document.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.fhi.information.document.vo.FhiDocument;
import com.fhi.permission.vo.FhiRole;

public class DocumentForm extends ActionForm {
	//分页查询基本属性
	private Integer pageNo;
	private String orderByName;
	private String orderByOrder;
	private String queryType;
	private String queryText;	
	private String startTime ; 
	private String endTime ; 
	private FhiDocument docm;
	private List<FhiDocument> list;
	private List<FhiRole> roleList;
	
	
	
	
	public FhiDocument getDocm() {
		if(this.docm==null){
			this.docm=new FhiDocument();
		}
		return docm;
	}

	public void setDocm(FhiDocument docm) {
		this.docm = docm;
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

	public List<FhiDocument> getList() {
		if(this.list==null){
			this.list=new ArrayList<FhiDocument>();
		}
		return list;
	}

	public void setList(List<FhiDocument> list) {
		this.list = list;
	}

	public List<FhiRole> getRoleList() {
		if(this.roleList==null){
			this.roleList=new ArrayList<FhiRole>();
		}
		return roleList;
	}

	public void setRoleList(List<FhiRole> roleList) {
		this.roleList = roleList;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}	
}
