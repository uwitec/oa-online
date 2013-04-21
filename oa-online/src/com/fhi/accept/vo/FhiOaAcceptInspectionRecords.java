package com.fhi.accept.vo;

import java.util.Date;

import com.fhi.framework.utils.date.DataUtils;

public class FhiOaAcceptInspectionRecords {
	
	private String id;
	//内部号
	private String innerCode;
	//客户名称
	private String customer;
	//客户ID
	private String customerId;
	//接单时间
	private Date getOrderDate;
	//报检流水号
	private String inspectionNum;
	//报检号
	private String inspectionCode;
	//申报时间
	private Date declarationDate;	
	//申报承办人
	private String inspectionPersonnel;
	
	
	//现场交接时间
	private Date submitDate;
	//交接承办人
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
	//更新时间
	private Date  updateDate ;
	//更改人账户
	private String  updatePerson ;
	//录入人账号
	private String creator;
	//录入时间
	private Date createDate;
	//录入人姓名
	private String username;
	
	private int valid = 0 ;
	
	public FhiOaAcceptInspectionRecords(FhiOaAccept accept) {
		this.id = accept.getId();
		this.innerCode = accept.getInnerCode();
		this.customer = accept.getCustomer();
		this.customerId = accept.getCustomerId();
		this.getOrderDate = accept.getGetOrderDate();
		this.endDate = accept.getEndDate();
		this.creator = accept.getCreator();
		this.createDate = accept.getCreateDate();
		this.username = accept.getUsername();
	}
	public FhiOaAcceptInspectionRecords() {
		
	}
	public void update(FhiOaAcceptInspectionRecords inspection){
		//报检流水号
		this.inspectionNum = inspection.getInspectionNum();
		//报检号
		this.inspectionCode = inspection.getInspectionCode();
		//申报时间
		this.declarationDate = inspection.getDeclarationDate();		
		//现场交接时间
		this.submitDate = inspection.getSubmitDate();
		//收费金额
		this.charges = inspection.getCharges();
		//收费时间
		this.chargeDate = inspection.getChargeDate();
		//查验时间
		this.inspectionDate = inspection.getInspectionDate();
		//放行时间
		this.releaseDate = inspection.getReleaseDate();	
		
		//申报承办人
		this.inspectionPersonnel = inspection.getInspectionPersonnel();
		//交接承办人
		this.submitPersonnel = inspection.getSubmitPersonnel();
		//查验承办人
		this.checkPersonnel = inspection.getCheckPersonnel();
	}
	
	/**
	 * 检查接单表信息是否改变
	 * @param accept
	 * @return
	 */
	public boolean update(FhiOaAccept accept) {
		boolean ret = false;
		if(!this.innerCode.equals(accept.getInnerCode())){
			this.innerCode = accept.getInnerCode();
			ret=true;
		}
		if(!this.customerId.equals(accept.getCustomerId())){
			this.customer = accept.getCustomer();
			this.customerId = accept.getCustomerId();
			ret=true;
		}
		if(accept.getGetOrderDate()!=null&&!accept.getGetOrderDate().equals(this.getOrderDate)||this.getOrderDate!=null&&this.getOrderDate.equals(accept.getGetOrderDate())){
			this.getOrderDate = accept.getGetOrderDate();
			ret=true;
		}
		if(accept.getEndDate()!=null&&!accept.getEndDate().equals(this.endDate)||this.endDate!=null&&this.endDate.equals(accept.getEndDate())){
			this.endDate = accept.getEndDate();		
			ret=true;
		}
		return ret;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	public void setDeclarationDateStr(String declarationDate) {
		this.declarationDate = DataUtils.getDateByString(declarationDate);
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public void setSubmitDateStr(String submitDate) {
		this.submitDate = DataUtils.getDateByString(submitDate);
	}
	public Date getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}
	public void setChargeDateStr(String chargeDate) {
		this.chargeDate = DataUtils.getDateByString(chargeDate);
	}
	public Date getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	public void setInspectionDateStr(String inspectionDate) {
		this.inspectionDate = DataUtils.getDateByString(inspectionDate);
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public void setReleaseDateStr(String releaseDate) {
		this.releaseDate = DataUtils.getDateByString(releaseDate);
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdatePerson() {
		return updatePerson;
	}
	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
}
