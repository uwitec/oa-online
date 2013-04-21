package com.fhi.questionnaire.service;

import java.util.List;

import com.fhi.framework.page.Condition;
import com.fhi.questionnaire.vo.Question;
import com.fhi.questionnaire.vo.QuestionAnswer;
import com.fhi.questionnaire.vo.QuestionnaireDetail;
import com.fhi.questionnaire.vo.QuestionnaireSetup;


public interface QuestionnaireIn {
 
	public boolean deleteQuestionnaireOneOrMore(String ids)throws Exception;
	
	public boolean updateQuestionnaire(QuestionnaireSetup questionnaireSetup) throws Exception;
	
	public boolean saveQuestionnaire(QuestionnaireSetup questionnaire)throws Exception;
	
	
	
	/**
	 * 问卷列表
	 * */
	public List<QuestionnaireSetup> getQuestionnaireSetup() throws Exception;

	/**
	 * 问卷列表
	 * */
	public QuestionnaireSetup loadQuestionnaire(String id) throws Exception;
	
	/**
	 * 问卷问题列表
	 * */
	public List<Question> getQuestionsById(String id) throws Exception;
	
	/**
	 * 问卷问题列表
	 * */
	public List<Question> getQuestionsById() throws Exception;
	
	
	/**
	 * 问卷问题答案
	 * */
	public List<QuestionAnswer> getQuestionAnswerById(String id) throws Exception;
	
	
	/**
	 * 问卷问题
	 * */
	public List<QuestionAnswer> getQuestionAnswersByQustionnaireId(String id) throws Exception;
	
	/**
	 * 问卷问题答案
	 * */
	public List<QuestionAnswer> getQuestionAnswerById() throws Exception;
	
	/**
	 * 是否投票  return  true :可以投票   false  不能投票
	 * */
	public boolean checkUser(String id,String questionnaireId) throws Exception;
	
	
	
	/**
	 * 保存问题
	 * */
	public boolean saveQuestion(List<Question> question,List<QuestionAnswer> list ,String questionnaireId) throws Exception;
	
	/**
	 * 保存问卷明细
	 * */
	public boolean saveQuestionnaireDetail(List<QuestionnaireDetail> list) throws Exception;
	
	
	/**
	 * checkTime
	 * 
	 * 不能晚于系统设置的时间  也不能早  和检查是否停用
	 * */
	public int checkTimeAndStatus(String questionnaireId) throws Exception;
	
	
	/**
	 *问题名称
	 * */
	public Question loadQuestion(String id) throws Exception;
	
	/**
	 *问题名称
	 * */
	public int countPop(String id) throws Exception;
	
	/**
	 *员工总数
	 * */
	public int employeeBasicCount() throws Exception;
	
	
	/**
	 *员工总数
	 * */
	public int answerCount(String id) throws Exception;
	
	
	/**
	 * 部门人数
	 * */
	public int deptEmpCount(String id) throws Exception;
	
	
	/**
	 * 部门投票人数
	 * */
	public int deptVotePop(String id,String questionnaireId) throws Exception;
	
	
	/**
	 * 未投票人数
	 * */
	@SuppressWarnings("unchecked")
	public List votedPopList(String questionnaireId) throws Exception;
	
	
	/**
	 * 以投票人数
	 * */
	@SuppressWarnings("unchecked")
	public List unVotedPopList() throws Exception;
	
	
	/**
	 * 以投票人数
	 * */
	@SuppressWarnings("unchecked")
	public QuestionAnswer loadQuestionAnswerById(String id) throws Exception;
	
	
	/**
	 * 
	 * @param cond
	 * @return
	 */
	public List queryQuestionnaire4PageList(Condition cond);
	
	
	/**
	 * 
	 * @param String  name  问卷名称  questinnaireId 问卷的id 当修改的时候不验证该条数据 
	 * @return  true 可以插入  false  不能插入
	 */
	public boolean checkQuestionnaireName(String name,String questinnaireId);
	
}
