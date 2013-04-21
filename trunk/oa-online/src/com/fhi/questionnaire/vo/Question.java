package com.fhi.questionnaire.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question implements Serializable {

	private String id;

	private List<QuestionAnswer> questionAnswersList = new ArrayList<QuestionAnswer>();

	public String getId() {
		return id;
	}

	public List<QuestionAnswer> getQuestionAnswersList() {
		return questionAnswersList;
	}

	public void setQuestionAnswersList(List<QuestionAnswer> questionAnswersList) {
		this.questionAnswersList = questionAnswersList;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getIsMultiSelect() {
		return isMultiSelect;
	}

	public void setIsMultiSelect(String isMultiSelect) {
		this.isMultiSelect = isMultiSelect;
	}

	/**
	 * 问题描述
	 * **/
	private String questionContent;
	/**
	 * 是否多选
	 * **/
	private String isMultiSelect;

	/**
	 * 问卷id
	 * **/
	private String questionnaireId;

	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	private Date createDate;

}
