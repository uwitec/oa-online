package com.fhi.system.classification.vo;


import java.util.Date;
import java.util.List;


/**
 * WmsAutoCode generated by MyEclipse Persistence Tools
 */

public class SysClassification {

     private String id;
     //名称
     private String name;
     //父ID
     private String pid;
     //类型0 文档 1 图片
     private String type;
     //顺序
     private Integer orderNum;
     //发布人ID
     private String creater;
     //发布时间
     private Date createDate;
     
    private List<SysClassification> childList;
     
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name.trim();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreateDate() {
		if(createDate==null){
			createDate=new Date();
		}
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List getChildList() {
		return childList;
	}
	public void setChildList(List childList) {
		this.childList = childList;
	}	
}