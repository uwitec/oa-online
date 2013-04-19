package com.fhi.accept.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fhi.framework.utils.date.DataUtils;

/**
 * FhiOaAccept generated by MyEclipse Persistence Tools
 */

public class FhiOaAccept implements java.io.Serializable { 
	 
	Logger  logger = Logger.getLogger(FhiOaAccept.class) ;

	private String id;

	private String innerCode;

	private String customer;

	private String customerId;

	private String manager;

	private String contractCode;

	private BigDecimal materiaMoney = new BigDecimal("0");

	private String moneyUnit;

	private String mainOrderCode;

	private String subOrderCode;

	private String startOrEndPort;

	private String piece;

	private String weight;

	private String productName;

	private Date endDate;

	private BigDecimal mayMoney = new BigDecimal("0");

	private Date eexpectMoneyDate;

	private Date realMoneyDate;

	private BigDecimal cost = new BigDecimal("0");

	private BigDecimal serviceMoney = new BigDecimal("0");

	private BigDecimal profit = new BigDecimal("0");

	private BigDecimal profitRate = new BigDecimal("0");

	private BigDecimal materiaCost = new BigDecimal("0");

	private String matCostUnit;

	private BigDecimal prePayMateriaMoney = new BigDecimal("0");

	private BigDecimal huiCha = new BigDecimal("0");

	private BigDecimal dianFuMatMoney = new BigDecimal("0");

	private Date excpectMatMoneyDate;

	private Date realMatMoneyDate;

	private Date payExchangeDate;

	private BigDecimal exchangeRate;

	private BigDecimal prePayTax = new BigDecimal("0");

	private BigDecimal taxMoney = new BigDecimal("0");

	private BigDecimal shuiCha = new BigDecimal("0");

	private BigDecimal dianFuTax = new BigDecimal("0");

	private Date expectTaxDate;

	private Date realTaxDate;

	private String comment; 

	private String state;

	private String creator;

	private Date createDate;

	private String username;

	private BigDecimal taxCount = new BigDecimal("0");

	private BigDecimal mayCount = new BigDecimal("0");

	private BigDecimal matCount = new BigDecimal("0");
	
	private BigDecimal otherCount = new BigDecimal("0");
	
	private Date getOrderDate ;
	
	private Date  updateDate ;
	private Date  sendOrderDate ;
	private String  sendOrderDateStr ;
	
	private String  updatePerson ;
	private BigDecimal  tiCheng  = new BigDecimal("0");
	private BigDecimal  tiChengRate  = new BigDecimal("0");
	private String  payStyle ; 
	private String  managerId ;
	private String  moneyComment ;
	
	

	// Constructors

	/** default constructor */
	public FhiOaAccept() {
	}

	/** minimal constructor */
	public FhiOaAccept(String id) {
		this.id = id;
	}

	  
	public FhiOaAccept(String id, String innerCode, String customer,
			String customerId, String manager, String contractCode,
			BigDecimal materiaMoney, String moneyUnit, String mainOrderCode,
			String subOrderCode, String startOrEndPort, String piece,
			String weight, String productName, Date endDate, BigDecimal mayMoney,
			Date eexpectMoneyDate, Date realMoneyDate, BigDecimal cost,
			BigDecimal serviceMoney, BigDecimal profit, BigDecimal profitRate,
			BigDecimal materiaCost, String matCostUnit, BigDecimal prePayMateriaMoney,
			BigDecimal huiCha, BigDecimal dianFuMatMoney, Date excpectMatMoneyDate,
			Date realMatMoneyDate, Date payExchangeDate, BigDecimal exchangeRate,
			BigDecimal prePayTax, BigDecimal taxMoney, BigDecimal shuiCha, BigDecimal dianFuTax,
			Date expectTaxDate, Date realTaxDate, String comment, String state,
			String creator, Date createDate, String username, BigDecimal taxCount,
			BigDecimal mayCount, BigDecimal matCount, BigDecimal otherCount) {
		this.id = id;
		this.innerCode = innerCode;
		this.customer = customer;
		this.customerId = customerId;
		this.manager = manager;
		this.contractCode = contractCode;
		this.materiaMoney = materiaMoney;
		this.moneyUnit = moneyUnit;
		this.mainOrderCode = mainOrderCode;
		this.subOrderCode = subOrderCode;
		this.startOrEndPort = startOrEndPort;
		this.piece = piece;
		this.weight = weight;
		this.productName = productName;
		this.endDate = endDate;
		this.mayMoney = mayMoney;
		this.eexpectMoneyDate = eexpectMoneyDate;
		this.realMoneyDate = realMoneyDate;
		this.cost = cost;
		this.serviceMoney = serviceMoney;
		this.profit = profit;
		this.profitRate = profitRate;
		this.materiaCost = materiaCost;
		this.matCostUnit = matCostUnit;
		this.prePayMateriaMoney = prePayMateriaMoney;
		this.huiCha = huiCha;
		this.dianFuMatMoney = dianFuMatMoney;
		this.excpectMatMoneyDate = excpectMatMoneyDate;
		this.realMatMoneyDate = realMatMoneyDate;
		this.payExchangeDate = payExchangeDate;
		this.exchangeRate = exchangeRate;
		this.prePayTax = prePayTax;
		this.taxMoney = taxMoney;
		this.shuiCha = shuiCha;
		this.dianFuTax = dianFuTax;
		this.expectTaxDate = expectTaxDate;
		this.realTaxDate = realTaxDate;
		this.comment = comment;
		this.state = state;
		this.creator = creator;
		this.createDate = createDate;
		this.username = username;
		this.taxCount = taxCount;
		this.mayCount = mayCount;
		this.matCount = matCount;
		this.otherCount = otherCount;
		
	}
 
	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInnerCode() {
		return this.innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getContractCode() {
		return this.contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public BigDecimal getMateriaMoney() {
        if(this.materiaMoney!=null)
        	this.materiaMoney = this.materiaMoney.setScale(2);
		return this.materiaMoney ;  
	}

	public void setMateriaMoney(BigDecimal materiaMoney) {  
			 this.materiaMoney = materiaMoney; 
	}

	public String getMoneyUnit() {
		return this.moneyUnit;
	}

	public void setMoneyUnit(String moneyUnit) {
		this.moneyUnit = moneyUnit;
	}

	public String getMainOrderCode() {
		return this.mainOrderCode;
	}

	public void setMainOrderCode(String mainOrderCode) {
		this.mainOrderCode = mainOrderCode;
	}

	public String getSubOrderCode() {
		return this.subOrderCode;
	}

	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}

	public String getStartOrEndPort() {
		return this.startOrEndPort;
	}

	public void setStartOrEndPort(String startOrEndPort) {
		this.startOrEndPort = startOrEndPort;
	}

	public String getPiece() {
		return this.piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getMayMoney() {
		if(this.mayMoney!=null) 
			this.mayMoney = this.mayMoney.setScale(2);
		return this.mayMoney ;
	}
	/**
	 * 如果应收结帐款为0 则应收既是成本
	 * @return
	 */
	public BigDecimal getMayMoneyShow() {	
		if(this.cost!=null) 
			this.cost = this.cost.setScale(2);
		if(this.mayMoney!=null) 
			this.mayMoney = this.mayMoney.setScale(2);
		return this.mayMoney.compareTo(new BigDecimal("0"))==0?this.cost:this.mayMoney;
	}

	public void setMayMoney(BigDecimal mayMoney) {
		 this.mayMoney = mayMoney;
	}

	public Date getEexpectMoneyDate() {
		return this.eexpectMoneyDate;
	}

	public void setEexpectMoneyDate(Date eexpectMoneyDate) {
		this.eexpectMoneyDate = eexpectMoneyDate;
	}

	public Date getRealMoneyDate() {
		return this.realMoneyDate;
	}

	public void setRealMoneyDate(Date realMoneyDate) {
		this.realMoneyDate = realMoneyDate;
	}

	public BigDecimal getCost() {
		if(this.cost!=null) 
			this.cost = this.cost.setScale(2);
		return this.cost ;
	}

	public void setCost(BigDecimal cost) { 
		this.cost = cost;
	}

	public BigDecimal getServiceMoney() {

		if(this.serviceMoney!=null) 
			this.serviceMoney = this.serviceMoney.setScale(2);
		return this.serviceMoney ;
	}

	public void setServiceMoney(BigDecimal serviceMoney) { 
		this.serviceMoney = serviceMoney;
	}

	public BigDecimal getProfit() {

		if(this.profit!=null) 
			this.profit = this.profit.setScale(2);
		return this.profit ;
	}

	public void setProfit(BigDecimal profit) { 
		this.profit = profit;
	}

	public BigDecimal getProfitRate() {

		if(this.profitRate!=null) 
			this.profitRate = this.profitRate.setScale(2);
		return this.profitRate ;
	}

	public void setProfitRate(BigDecimal profitRate) { 
		this.profitRate = profitRate;
	}

	public BigDecimal getMateriaCost() {

		if(this.materiaCost!=null) 
			this.materiaCost = this.materiaCost.setScale(2);
		return this.materiaCost ;
	}

	public void setMateriaCost(BigDecimal materiaCost) {  
		this.materiaCost = materiaCost;
	}

	public String getMatCostUnit() {
		return this.matCostUnit;
	}

	public void setMatCostUnit(String matCostUnit) {
		this.matCostUnit = matCostUnit;
	}

	public BigDecimal getPrePayMateriaMoney() {

		if(this.prePayMateriaMoney!=null) 
			this.prePayMateriaMoney = this.prePayMateriaMoney.setScale(2); 
		return this.prePayMateriaMoney ;
	}

	public void setPrePayMateriaMoney(BigDecimal prePayMateriaMoney) { 
		this.prePayMateriaMoney = prePayMateriaMoney;
	}

	public BigDecimal getHuiCha() {

		if(this.huiCha!=null) 
			this.huiCha = this.huiCha.setScale(2);
		return this.huiCha ;
	}

	public void setHuiCha(BigDecimal huiCha) { 
		this.huiCha = huiCha;
	}

	public BigDecimal getDianFuMatMoney() {

		if(this.dianFuMatMoney!=null) 
			this.dianFuMatMoney = this.dianFuMatMoney.setScale(2);
		return this.dianFuMatMoney ;
	}

	public void setDianFuMatMoney(BigDecimal dianFuMatMoney) {  
		this.dianFuMatMoney = dianFuMatMoney;
	}

	public Date getExcpectMatMoneyDate() {
		return this.excpectMatMoneyDate;
	}

	public void setExcpectMatMoneyDate(Date excpectMatMoneyDate) {
		this.excpectMatMoneyDate = excpectMatMoneyDate;
	}

	public Date getRealMatMoneyDate() {
		return this.realMatMoneyDate;
	}

	public void setRealMatMoneyDate(Date realMatMoneyDate) {
		this.realMatMoneyDate = realMatMoneyDate;
	}

	public Date getPayExchangeDate() {
		return this.payExchangeDate;
	}

	public void setPayExchangeDate(Date payExchangeDate) {
		this.payExchangeDate = payExchangeDate;
	}

	public BigDecimal getExchangeRate() {

		if(this.exchangeRate!=null) 
			this.exchangeRate = this.exchangeRate.setScale(4);
		return this.exchangeRate ;
	}

	public void setExchangeRate(BigDecimal exchangeRate) { 
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getPrePayTax() {

		if(this.prePayTax!=null) 
			this.prePayTax = this.prePayTax.setScale(2);
		return this.prePayTax ;
	}

	public void setPrePayTax(BigDecimal prePayTax) { 
		this.prePayTax = prePayTax;
	}

	public BigDecimal getTaxMoney() {

		if(this.taxMoney!=null) 
			this.taxMoney = this.taxMoney.setScale(2);
		return this.taxMoney;
	}

	public void setTaxMoney(BigDecimal taxMoney) { 
		this.taxMoney = taxMoney; 
	}

	public BigDecimal getShuiCha() {

		if(this.shuiCha!=null) 
			this.shuiCha = this.shuiCha.setScale(2);
		return this.shuiCha ;
	}

	public void setShuiCha(BigDecimal shuiCha) { 
		this.shuiCha = shuiCha;
	}

	public BigDecimal getDianFuTax() {

		if(this.dianFuTax!=null) 
			this.dianFuTax = this.dianFuTax.setScale(2);
		return this.dianFuTax ;
	}

	public void setDianFuTax(BigDecimal dianFuTax) { 
		this.dianFuTax = dianFuTax;
	}

	public Date getExpectTaxDate() {
		return this.expectTaxDate;
	}

	public void setExpectTaxDate(Date expectTaxDate) {
		this.expectTaxDate = expectTaxDate;
	}

	public Date getRealTaxDate() {
		return this.realTaxDate;
	}

	public void setRealTaxDate(Date realTaxDate) {
		this.realTaxDate = realTaxDate;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getTaxCount() {

		if(this.taxCount!=null) 
			this.taxCount = this.taxCount.setScale(2);
		return this.taxCount ;
	}

	public void setTaxCount(BigDecimal taxCount) { 
		this.taxCount = taxCount;
	}

	public BigDecimal getMayCount() {
		if(this.mayCount!=null) 
			this.mayCount = this.mayCount.setScale(2);
		return this.mayCount ;
	}

	public void setMayCount(BigDecimal mayCount) { 
		this.mayCount = mayCount;
	}

	public BigDecimal getMatCount() {
		if(this.matCount!=null) 
			this.matCount = this.matCount.setScale(2);
		return this.matCount ;
	}

	public void setMatCount(BigDecimal matCount) { 
		this.matCount = matCount;
	}

	public BigDecimal getOtherCount() {
		if(this.otherCount!=null) 
			this.otherCount = this.otherCount.setScale(2);
		return this.otherCount ;
	}

	public void setOtherCount(BigDecimal otherCount) { 
		this.otherCount = otherCount;
	}

	public Date getGetOrderDate() {
		return getOrderDate;
	}

	public void setGetOrderDate(Date getOrderDate) {
		this.getOrderDate = getOrderDate;
	}

	public String getPayStyle() {
		return payStyle;
	}
	
	public String getPayStyleStr() {
		if("nopay".equals(payStyle)){
			return "不付汇";
		}
		else if("prett".equals(payStyle)){
			return "先TT";
		}
		else if("lasttt".equals(payStyle)){
			return "后TT";
				}
		else if("lc".equals(payStyle)){
			return "L/C";
		}
		return payStyle;
	}

	public void setPayStyle(String payStyle) {
		this.payStyle = payStyle;
	}

	public BigDecimal getTiCheng() {
		if(this.tiCheng!=null) 
			this.tiCheng = this.tiCheng.setScale(2);
		return this.tiCheng ;
	}

	public void setTiCheng(BigDecimal tiCheng) { 
		this.tiCheng = tiCheng;
	}

	public BigDecimal getTiChengRate() {
		if(this.tiChengRate!=null) 
			this.tiChengRate = this.tiChengRate.setScale(4);
		return this.tiChengRate ;
	}

	public void setTiChengRate(BigDecimal tiChengRate) {  
		this.tiChengRate = tiChengRate;
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

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getMoneyComment() {
		return moneyComment;
	}

	public void setMoneyComment(String moneyComment) {
		this.moneyComment = moneyComment;
	}

	public Date getSendOrderDate() {
		return sendOrderDate;
	} 
	public void setSendOrderDateStr(String sendOrderDateStr) {
		this.sendOrderDate = DataUtils.getDateByString(sendOrderDateStr);
	}

	public void setSendOrderDate(Date sendOrderDate) {
		this.sendOrderDate = sendOrderDate;
	} 
}