package com.fhi.questionnaire.vo;

import java.util.Date;

public class QuestionnaireSetup {
	
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 问卷名称
	 * **/
	private  String questionnaireName;
	/**
	 * 问卷结束时间
	 * **/
	private  Date  questionnaireEndTime;
	/**
	 * 问卷开始时间
	 * **/
	private  Date  questionnaireStartTime;
	
	public String getQuestionnaireStatus() {
		return questionnaireStatus;
	}

	public void setQuestionnaireStatus(String questionnaireStatus) {
		this.questionnaireStatus = questionnaireStatus;
	}

	/**
	 * 问卷状态
	 * **/
	private String questionnaireStatus;
	/**
	 * 获得问卷名称
	 * **/
	public String getQuestionnaireName() {
		return questionnaireName;
	}

	public void setQuestionnaireName(String questionnaireName) {
		this.questionnaireName = questionnaireName;
	}
	/**
	 * 问卷结束时间
	 * **/
	public Date getQuestionnaireEndTime() {
		return questionnaireEndTime;
	}

	public void setQuestionnaireEndTime(Date questionnaireEndTime) {
		this.questionnaireEndTime = questionnaireEndTime;
	}
	/**
	 * 问卷开始时间
	 * **/
	public Date getQuestionnaireStartTime() {
		return questionnaireStartTime;
	}

	public void setQuestionnaireStartTime(Date questionnaireStartTime) {
		this.questionnaireStartTime = questionnaireStartTime;
	}
	
	
}
