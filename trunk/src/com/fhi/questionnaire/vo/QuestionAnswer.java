package com.fhi.questionnaire.vo;


public class QuestionAnswer {
	private String id;

	private String questionId;
	private String questionContent;
	/**
	 * 
	 * 备选项
	 * */
	private String questionSelectAnswer;
	
	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	private  int  totalSelected;
	
	private  String  selectedPercent;
	
	private  String  answerCount;
	
	private  String questionnaireId;
	
	
	

	 

	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public String getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(String answerCount) {
		this.answerCount = answerCount;
	}

	public int getTotalSelected() {
		return totalSelected;
	}

	public void setTotalSelected(int totalSelected) {
		this.totalSelected = totalSelected;
	}

	 

	public String getSelectedPercent() {
		return selectedPercent;
	}

	public void setSelectedPercent(String selectedPercent) {
		this.selectedPercent = selectedPercent;
	}

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

	public String getQuestionSelectAnswer() {
		return questionSelectAnswer;
	}

	public void setQuestionSelectAnswer(String questionSelectAnswer) {
		this.questionSelectAnswer = questionSelectAnswer;
	}

	 

}
