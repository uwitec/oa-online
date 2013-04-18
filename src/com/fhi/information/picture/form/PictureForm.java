package com.fhi.information.picture.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.fhi.information.picture.vo.FhiPicture;
import com.fhi.permission.vo.FhiRole;

public class PictureForm extends ActionForm {
	//分页查询基本属性
	private Integer pageNo;
	private String orderByName;
	private String orderByOrder;
	
	private String queryText;
	private String queryType;
	
	private String startTime ; 
	private String endTime ; 
	private FhiPicture pic;
	private List<FhiPicture> list;
	private List<FhiRole> roleList;	


	public FhiPicture getPic() {
		if(this.pic==null){
			this.pic=new FhiPicture();
		}
		return pic;
	}

	public void setPic(FhiPicture pic) {
		this.pic = pic;
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

	public List<FhiPicture> getList() {
		if(this.list==null){
			this.list=new ArrayList<FhiPicture>();
		}
		return list;
	}

	public void setList(List<FhiPicture> list) {
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

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}	
	
}
