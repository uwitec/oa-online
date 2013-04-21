package com.fhi.questionnaire.vo;  
import java.util.Date;
public class Qusestionnaire implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String comCode;

	private String fullName;

	private String cutName;

	private String address;

	private String enName;

	private String enAddress;

	private String country;

	private String province;

	private String city;

	private String postCode;

	private String legalPerson;

	private String contactPerson;

	private String phone;

	private String mobile;

	private String fax;

	private String homePage;

	private String email;

	private String businessNo;

	private String nationTaxNo;

	private String landTaxNo;

	private String cnnumber;

	private String checkNo;

	private String comment;

	private String parentId;

	private String parentName;

	private String creator;

	private Date addDate;
	
	private String updatePerson;

	private Date updateDate; 

	private String workor; 
	
	private String workorId;
	
	private String custServiceId;

	private String custService;
	
	private Date contractTime ;
	
	private String contractTerm ;
	
	private String contract;

	// Constructors

	/** default constructor */
	public Qusestionnaire() {
	}

	/** full constructor */
	public Qusestionnaire(String comCode, String fullName, String cutName,
			String address, String enName, String enAddress, String country,
			String province, String city, String postCode, String legalPerson,
			String contactPerson, String phone, String mobile, String fax,
			String homePage, String email, String businessNo,
			String nationTaxNo, String landTaxNo, String cnnumber,
			String checkNo, String comment, String parentId, String parentName,
			String creator, Date addDate) {
		this.comCode = comCode;
		this.fullName = fullName;
		this.cutName = cutName;
		this.address = address;
		this.enName = enName;
		this.enAddress = enAddress;
		this.country = country;
		this.province = province;
		this.city = city;
		this.postCode = postCode;
		this.legalPerson = legalPerson;
		this.contactPerson = contactPerson;
		this.phone = phone;
		this.mobile = mobile;
		this.fax = fax;
		this.homePage = homePage;
		this.email = email;
		this.businessNo = businessNo;
		this.nationTaxNo = nationTaxNo;
		this.landTaxNo = landTaxNo;
		this.cnnumber = cnnumber;
		this.checkNo = checkNo;
		this.comment = comment;
		this.parentId = parentId;
		this.parentName = parentName;
		this.creator = creator;
		this.addDate = addDate;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComCode() {
		return this.comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCutName() {
		return this.cutName;
	}

	public void setCutName(String cutName) {
		this.cutName = cutName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getEnAddress() {
		return this.enAddress;
	}

	public void setEnAddress(String enAddress) {
		this.enAddress = enAddress;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getLegalPerson() {
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHomePage() {
		return this.homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBusinessNo() {
		return this.businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getNationTaxNo() {
		return this.nationTaxNo;
	}

	public void setNationTaxNo(String nationTaxNo) {
		this.nationTaxNo = nationTaxNo;
	}

	public String getLandTaxNo() {
		return this.landTaxNo;
	}

	public void setLandTaxNo(String landTaxNo) {
		this.landTaxNo = landTaxNo;
	}

	public String getCnnumber() {
		return this.cnnumber;
	}

	public void setCnnumber(String cnnumber) {
		this.cnnumber = cnnumber;
	}

	public String getCheckNo() {
		return this.checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getContractTerm() {
		return contractTerm;
	}

	public void setContractTerm(String contractTerm) {
		this.contractTerm = contractTerm;
	}

	public Date getContractTime() {
		return contractTime;
	}

	public void setContractTime(Date contractTime) {
		this.contractTime = contractTime;
	}

	public String getCustService() {
		return custService;
	}

	public void setCustService(String custService) {
		this.custService = custService;
	}

	public String getCustServiceId() {
		return custServiceId;
	}

	public void setCustServiceId(String custServiceId) {
		this.custServiceId = custServiceId;
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

	public String getWorkor() {
		return workor;
	}

	public void setWorkor(String workor) {
		this.workor = workor;
	}

	public String getWorkorId() {
		return workorId;
	}

	public void setWorkorId(String workorId) {
		this.workorId = workorId;
	}

}