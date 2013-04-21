package com.fhi.accept.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.fhi.accept.vo.FhiOaAccept;
import com.fhi.accept.vo.FhiOaAcceptInspectionRecords;
import com.fhi.accept.vo.FhiOaRetmoney;
import com.fhi.system.sysmsg.vo.SysMsgSetup;

public class AcceptForm extends ActionForm {
	
	private Integer pageNo;
	private String orderByName;
	private String orderByOrder;
	private String startTime ; 
	private String endTime ;  
	
	private String endDate="" ; 			 //完毕时间
	private String eexpectMoneyDate="" ;    //应收款预计结算日期
	private String realMoneyDate="" ; 		 //应收款实际结算日期
	private String excpectMatMoneyDate="" ; //货款预计结算日期
	private String realMatMoneyDate="" ; 	 //货款实际结算日期
	private String payExchangeDate="" ; 	 //付汇日期
	private String expectTaxDate="" ; 		 //税款预计结算日期
	private String realTaxDate="" ; 		 //税款实际结算日期  
	private String getOrderDate="" ; 		 //税款实际结算日期  
	private String createDate="" ; 		     //创建日期 
	private String payTime = "" ;
	
	private int  emergnceItem   ;
	private int  emergnceType   ;
	
	private String EMstartTime ; 
	private String EMendTime ;
	private String times ;
	
	private String sendOrder_startTime ;
	private String sendOrder_endTime ;
	 
	
	
	private String []ids ;  
	private FhiOaAccept accept = new FhiOaAccept() ;
	private SysMsgSetup emergnce = new SysMsgSetup();
	private FhiOaRetmoney money = new FhiOaRetmoney() ;
	
	private FhiOaAcceptInspectionRecords inspection=null;
	private List list = null;
	
	 
	public List getList() {
		if(list==null){
			list=new ArrayList();
		}
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public FhiOaAccept getAccept() {
		return accept;
	}
	public void setAccept(FhiOaAccept accept) {
		this.accept = accept;
	} 
	public String getEexpectMoneyDate() {
		return eexpectMoneyDate;
	}
	public void setEexpectMoneyDate(String eexpectMoneyDate) {
		this.eexpectMoneyDate = eexpectMoneyDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getExcpectMatMoneyDate() {
		return excpectMatMoneyDate;
	}
	public void setExcpectMatMoneyDate(String excpectMatMoneyDate) {
		this.excpectMatMoneyDate = excpectMatMoneyDate;
	}
	public String getExpectTaxDate() {
		return expectTaxDate;
	}
	public void setExpectTaxDate(String expectTaxDate) {
		this.expectTaxDate = expectTaxDate;
	}
	public String getPayExchangeDate() {
		return payExchangeDate;
	}
	public void setPayExchangeDate(String payExchangeDate) {
		this.payExchangeDate = payExchangeDate;
	}
	public String getRealMatMoneyDate() {
		return realMatMoneyDate;
	}
	public void setRealMatMoneyDate(String realMatMoneyDate) {
		this.realMatMoneyDate = realMatMoneyDate;
	}
	public String getRealMoneyDate() {
		return realMoneyDate;
	}
	public void setRealMoneyDate(String realMoneyDate) {
		this.realMoneyDate = realMoneyDate;
	}
	public String getRealTaxDate() {
		return realTaxDate;
	}
	public void setRealTaxDate(String realTaxDate) {
		this.realTaxDate = realTaxDate;
	}
	public SysMsgSetup getEmergnce() {
		return emergnce;
	}
	public void setEmergnce(SysMsgSetup emergnce) {
		this.emergnce = emergnce;
	}
	public int getEmergnceItem() {
		return emergnceItem;
	}
	public void setEmergnceItem(int emergnceItem) {
		this.emergnceItem = emergnceItem;
	}
	public int getEmergnceType() {
		return emergnceType;
	}
	public void setEmergnceType(int emergnceType) {
		this.emergnceType = emergnceType;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public FhiOaRetmoney getMoney() {
		return money;
	}
	public void setMoney(FhiOaRetmoney money) {
		this.money = money;
	}
	public String getEMendTime() {
		return EMendTime;
	}
	public void setEMendTime(String mendTime) {
		EMendTime = mendTime;
	}
	public String getEMstartTime() {
		return EMstartTime;
	}
	public void setEMstartTime(String mstartTime) {
		EMstartTime = mstartTime;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getGetOrderDate() {
		return getOrderDate;
	}
	public void setGetOrderDate(String getOrderDate) {
		this.getOrderDate = getOrderDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public FhiOaAcceptInspectionRecords getInspection() {
		if(inspection==null){
			inspection=new FhiOaAcceptInspectionRecords();
		}
		return inspection;
	}
	public void setInspection(FhiOaAcceptInspectionRecords inspection) {
		this.inspection = inspection;
	}
	public String getSendOrder_endTime() {
		return sendOrder_endTime;
	}
	public void setSendOrder_endTime(String sendOrder_endTime) {
		this.sendOrder_endTime = sendOrder_endTime;
	}
	public String getSendOrder_startTime() {
		return sendOrder_startTime;
	}
	public void setSendOrder_startTime(String sendOrder_startTime) {
		this.sendOrder_startTime = sendOrder_startTime;
	} 
}
