package com.fhi.information.freight.vo;

import java.util.Date;

public class FhiFreight {
	//报价ID
	private String id;	
	//报价Code
	private String code;
	
	private boolean autoCode;
	//来源编码
	private String fromCode;
	//来源名称
	private String fromName;	
	//到达编码
	private String toCode;
	//到达名称
	private String toName;	
	//航空报价
	private String air;
	//陆运报价
	private String land;
	//海运报价
	private String sea;
    //发布人
    private String creater;
    //发布人 姓名
    private String creatorName;
    //发布时间
    private Date createDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFromName() {
		return fromName!=null?fromName.trim():null;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		if(toName!=null){
			toName = toName.trim();
		}
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getAir() {
		return air;
	}
	public void setAir(String air) {
		this.air = air;
	}
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
	}
	public String getSea() {
		return sea;
	}
	public void setSea(String sea) {
		this.sea = sea;
	}
	public String getFromCode() {
		if(fromCode!=null){
			fromCode = fromCode.trim();
		}
		return fromCode;
	}
	public void setFromCode(String fromCode) {
		this.fromCode = fromCode;
	}
	public String getToCode() {
		if(toCode!=null){
			toCode = toCode.trim();
		}
		return toCode;
	}
	public void setToCode(String toCode) {
		this.toCode = toCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isAutoCode() {
		return autoCode;
	}
	public void setAutoCode(boolean autoCode) {
		this.autoCode = autoCode;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}	
}
