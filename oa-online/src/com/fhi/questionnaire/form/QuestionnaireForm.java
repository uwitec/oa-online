package com.fhi.questionnaire.form;


import org.apache.struts.action.ActionForm;

public class QuestionnaireForm extends ActionForm {
	/**
	 * 
	 */
 
	private String orderByName;
	private String orderByOrder;
	private static final long serialVersionUID = 1L;
	private String[] ids;// id数组
	private Integer pageNo;// 页码
	 
	 

	// 搜索用时间数据
	private String selTimeStart;
	private String selTimeEnd; 
	private String createDate ;
	private String flagSite ;
	
	private String questionnaireId;
	
	private String questionnaireStartTime;
	/**
	 * 页面参数 判断问题答案是哪一行
	 * */
	private String[] rowNum;
	
	 

	public String[] getRowNum() {
		return rowNum;
	}

	public void setRowNum(String[] rowNum) {
		this.rowNum = rowNum;
	}

	public String getQuestionnaireStartTime() {
		return questionnaireStartTime;
	}

	public void setQuestionnaireStartTime(String questionnaireStartTime) {
		this.questionnaireStartTime = questionnaireStartTime;
	}

	public String getQuestionnaireEndTime() {
		return questionnaireEndTime;
	}

	public void setQuestionnaireEndTime(String questionnaireEndTime) {
		this.questionnaireEndTime = questionnaireEndTime;
	}

	private String questionnaireEndTime;
	
	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	private  String[] questionContent;
	
	private  String[] questionAnswers;
	
	
	private String[]  isMultSelect;
	
	
	


	// 用于实体传值以及查询传值


	 
	public String[] getIsMultSelect() {
		return isMultSelect;
	}

	public void setIsMultSelect(String[] isMultSelect) {
		this.isMultSelect = isMultSelect;
	}

	public String[] getQuestionAnswers() {
		return questionAnswers;
	}

	public String[] getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String[] questionContent) {
		this.questionContent = questionContent;
	}

	public void setQuestionAnswers(String[] questionAnswers) {
		this.questionAnswers = questionAnswers;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = (pageNo == null || pageNo == 0) ? 1 : pageNo;
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


	public String getSelTimeStart() {
		return selTimeStart;
	}

	public void setSelTimeStart(String selTimeStart) {
		this.selTimeStart = selTimeStart;
	}

	public String getSelTimeEnd() {
		return selTimeEnd;
	}

	public void setSelTimeEnd(String selTimeEnd) {
		this.selTimeEnd = selTimeEnd;
	}
	
	public String getCreateDate() {
		return createDate;
	} 
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getFlagSite() {
		return flagSite;
	}

	public void setFlagSite(String flagSite) {
		this.flagSite = flagSite;
	}

	
}
