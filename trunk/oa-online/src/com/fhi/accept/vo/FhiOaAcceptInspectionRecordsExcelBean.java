package com.fhi.accept.vo;

import java.util.Date;

public class FhiOaAcceptInspectionRecordsExcelBean {
	
	//内部号
	private String innerCode;
	//客户名称
	private String customer;
	//接单时间
	private Date getOrderDate;	
	//录入人姓名
	private String username;	
	//报检流水号
	private String inspectionNum;
	//报检号
	private String inspectionCode;
	//电子报检时间
	private Date declarationDate;
	//电子报检承办人
	private String inspectionPersonnel;
	
	//现场申报时间
	private Date submitDate;
	//现场申报承办人
	private String submitPersonnel;
	
	//收费金额
	private Float charges;	
	//收费时间
	private Date chargeDate;
	
	//查验时间
	private Date inspectionDate;
	//查验承办人
	private String checkPersonnel;
	
	//放行时间
	private Date releaseDate;
	
	//操作完毕时间
	private Date endDate;
	
	public FhiOaAcceptInspectionRecordsExcelBean(FhiOaAcceptInspectionRecords inspection) {

		this.innerCode = inspection.getInnerCode();
		this.customer = inspection.getCustomer();
		this.getOrderDate = inspection.getGetOrderDate();
		this.inspectionNum = inspection.getInspectionNum();
		this.inspectionCode = inspection.getInspectionCode();
		this.declarationDate = inspection.getDeclarationDate();
		this.submitDate = inspection.getSubmitDate();
		this.charges = inspection.getCharges();
		this.chargeDate = inspection.getChargeDate();
		this.inspectionDate =  inspection.getInspectionDate();
		this.releaseDate = inspection.getReleaseDate();
		this.endDate = inspection.getEndDate();
		
		//接单承办人
		this.username = inspection.getUsername();
		
		//申报承办人
		this.inspectionPersonnel = inspection.getInspectionPersonnel();
		//交接承办人
		this.submitPersonnel = inspection.getSubmitPersonnel();
		//查验承办人
		this.checkPersonnel = inspection.getCheckPersonnel();
		
	}
	public FhiOaAcceptInspectionRecordsExcelBean() {
		
	}
	public String getInnerCode() {
		return innerCode;
	}
	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Date getGetOrderDate() {
		return getOrderDate;
	}
	public void setGetOrderDate(Date getOrderDate) {
		this.getOrderDate = getOrderDate;
	}
	public String getInspectionCode() {
		return inspectionCode;
	}
	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}
	public Date getDeclarationDate() {
		return declarationDate;
	}
	public void setDeclarationDate(Date declarationDate) {
		this.declarationDate = declarationDate;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Date getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}
	public Date getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getInspectionNum() {
		return inspectionNum;
	}
	public void setInspectionNum(String inspectionNum) {
		this.inspectionNum = inspectionNum;
	}
	public Float getCharges() {
		return charges;
	}
	public void setCharges(Float charges) {
		this.charges = charges;
	}
	public String getInspectionPersonnel() {
		return inspectionPersonnel;
	}
	public void setInspectionPersonnel(String inspectionPersonnel) {
		this.inspectionPersonnel = inspectionPersonnel;
	}
	public String getSubmitPersonnel() {
		return submitPersonnel;
	}
	public void setSubmitPersonnel(String submitPersonnel) {
		this.submitPersonnel = submitPersonnel;
	}
	public String getCheckPersonnel() {
		return checkPersonnel;
	}
	public void setCheckPersonnel(String checkPersonnel) {
		this.checkPersonnel = checkPersonnel;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
