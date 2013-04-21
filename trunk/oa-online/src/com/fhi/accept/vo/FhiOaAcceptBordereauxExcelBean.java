package com.fhi.accept.vo;

import java.math.BigDecimal;


public class FhiOaAcceptBordereauxExcelBean {
	
	//序号
	private String serial;
	//客户公司名称（全称）
	private String customer;
	//内部号
	private String innerCode;
	//合同号/订单号
	private String contractCode;
	//货值
	private BigDecimal materiaMoney;	
	//入账金额
	private BigDecimal mayMoney;	
	//成本
	private BigDecimal cost;
	//服务费
	private BigDecimal serviceMoney;
	//利润
	private BigDecimal profit;	
	//预计提成
	private BigDecimal tiCheng;	
	//备注
	private String comment;
	
	
	public FhiOaAcceptBordereauxExcelBean(){
		
	}
	
	public FhiOaAcceptBordereauxExcelBean(FhiOaAccept accept){
		
		this.contractCode = accept.getContractCode();
		this.cost = new BigDecimal(accept.getCost()==null?"0":accept.getCost().toString());
		this.customer = accept.getCustomer();
		this.innerCode = accept.getInnerCode();
		this.materiaMoney = new BigDecimal(accept.getMateriaMoney()==null?"0":accept.getMateriaMoney().toString());
		this.mayMoney = new BigDecimal(accept.getMayMoney()==null?"0":accept.getMayMoney().toString());
		this.profit = new BigDecimal(accept.getProfit()==null?"0":accept.getProfit().toString());
		this.serviceMoney = new BigDecimal(accept.getServiceMoney()==null?"0":accept.getServiceMoney().toString());
		this.tiCheng = new BigDecimal(accept.getTiCheng()==null?"0":accept.getTiCheng().toString());
		this.comment = accept.getComment();
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public BigDecimal getMateriaMoney() {
		return materiaMoney;
	}

	public void setMateriaMoney(BigDecimal materiaMoney) {
		this.materiaMoney = materiaMoney;
	}

	public BigDecimal getMayMoney() {
		return mayMoney;
	}

	public void setMayMoney(BigDecimal mayMoney) {
		this.mayMoney = mayMoney;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getServiceMoney() {
		return serviceMoney;
	}

	public void setServiceMoney(BigDecimal serviceMoney) {
		this.serviceMoney = serviceMoney;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getTiCheng() {
		return tiCheng;
	}

	public void setTiCheng(BigDecimal tiCheng) {
		this.tiCheng = tiCheng;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
