package com.fhi.questionnaire.vo;



public class QuestionnaireDetail {
	
	private String id;
	/**
	 * 问题id
	 * */
	private String questionId;//
	/**
	 * 问题选项
	 * */
	private String questionAnswerId;
	/**
	 * 回答者id
	 * */
	private String questionAnswerUserId;
	/**
	 * 部门id
	 * */
	private String deptId;
	
	/**
	 * 公司
	 * */
	private String company;
	
	/**
	 * questionnaireId 问卷Id
	 * */
	
				   
	private String questionnaireId;
	 
	public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 *   调查人账号
	 * */
	private String questionAnswerCode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionAnswerId() {
		return questionAnswerId;
	}
	public void setQuestionAnswerId(String questionAnswerId) {
		this.questionAnswerId = questionAnswerId;
	}
	public String getQuestionAnswerUserId() {
		return questionAnswerUserId;
	}
	public void setQuestionAnswerUserId(String questionAnswerUserId) {
		this.questionAnswerUserId = questionAnswerUserId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getQuestionAnswerCode() {
		return questionAnswerCode;
	}
	public void setQuestionAnswerCode(String questionAnswerCode) {
		this.questionAnswerCode = questionAnswerCode;
	}
	
 
}
