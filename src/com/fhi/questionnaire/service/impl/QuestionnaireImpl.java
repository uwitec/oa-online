package com.fhi.questionnaire.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.page.Condition;
import com.fhi.questionnaire.service.QuestionnaireIn;
import com.fhi.questionnaire.vo.Question;
import com.fhi.questionnaire.vo.QuestionAnswer;
import com.fhi.questionnaire.vo.QuestionnaireDetail;
import com.fhi.questionnaire.vo.QuestionnaireSetup;

public class QuestionnaireImpl extends AbstractServiceImple  implements QuestionnaireIn {
	private Logger logger = Logger.getLogger(QuestionnaireImpl.class) ;  
	@Override
	public boolean deleteQuestionnaireOneOrMore(String ids) throws Exception {
		this.jdbcDbDao.deleteObjectByIds("fhi_os_questionnairesetup", "'"+ids+"'");
		this.jdbcDbDao.execute("delete from fhi_os_question where questionnaireId in ( '"+ids+"')");
		this.jdbcDbDao.execute("delete from fhi_os_questionAnswer where questionnaireId in ('"+ids+"')");
		this.jdbcDbDao.execute("delete from fhi_os_questionnairedetial where questionnaireId in ('"+ids+"')");
		return true;
	}

	@Override
	public boolean saveQuestionnaire(QuestionnaireSetup questionnaire)
			throws Exception {
	    try {
			this.dbDao.addObject(questionnaire);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionnaireSetup> getQuestionnaireSetup() throws Exception {
		return (List<QuestionnaireSetup>)this.dbDao.queryObjects("from QuestionnaireSetup ");
	}

	@Override
	public QuestionnaireSetup loadQuestionnaire(String id) throws Exception {
		
		return (QuestionnaireSetup)this.dbDao.queryObjectById(QuestionnaireSetup.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionsById(String id) throws Exception {
		
		return  (List<Question>)this.dbDao.queryObjects("from Question where questionnaireId = '"+id+"' order by createDate ");
	}

	@Override
	public boolean saveQuestion(List<Question> question,List<QuestionAnswer> list, String questionnaireId) throws Exception {
		
		this.jdbcDbDao.execute("delete from fhi_os_question where questionnaireId = '"+questionnaireId+"'");
		this.jdbcDbDao.execute("delete from fhi_os_questionAnswer where questionnaireId = '"+questionnaireId+"'");
		this.dbDao.addObjects(question);
		this.dbDao.addObjects(list);
		return true;
	}

	@Override
	public boolean updateQuestionnaire(QuestionnaireSetup questionnaireSetup) throws Exception {
		this.dbDao.updateObject(questionnaireSetup);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionsById() throws Exception {
		return  (List<Question>)this.dbDao.queryObjects("from Question order by  createDate");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionAnswer> getQuestionAnswerById(String id)
			throws Exception {
		 
		return  (List<QuestionAnswer>)this.dbDao.queryObjects("from QuestionAnswer where questionId = '"+id+"'");
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionAnswer> getQuestionAnswerById()
			throws Exception {
		 
		return  (List<QuestionAnswer>)this.dbDao.queryObjects("from QuestionAnswer order by questionId");
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkUser(String id,String questionnaireId) throws Exception {
		List<QuestionnaireDetail>  questionDetialList = this.dbDao.queryObjects("from QuestionnaireDetail where questionnaireId = '"+questionnaireId+"' and questionAnswerUserId = '"+id+"'");
		if(questionDetialList != null && questionDetialList.size()>0){
			return  false;
		}
		return true;
	}

	@Override
	public boolean saveQuestionnaireDetail(List<QuestionnaireDetail> list)
			throws Exception {
		 
		return this.dbDao.addObjects(list);
	}

	@Override
	public int checkTimeAndStatus(String questionnaireId) throws Exception {
		String sql = " from fhi_os_questionnairesetup where questionnaireStatus = '启用'  and DATE_FORMAT(questionnaireStartTime,'%Y-%m-%d')<= date_format(now(),'%Y-%m-%d')     and DATE_FORMAT(questionnaireEndTime,'%Y-%m-%d')>= date_format(now(),'%Y-%m-%d')  ;";
		 
		return this.jdbcDbDao.count(sql);
		//return   this.dbDao.count("from QuestionnaireSetup where questionnaireStatus = '启用' and DATE_FORMAT(now(),'%Y-%m-%d') between  DATE_FORMAT(questionnaireStartTime,'%Y-%m-%d') and DATE_FORMAT(questionnaireEndTime,'%Y-%m-%d')  and id = '"+questionnaireId+"'");
	}

	@Override
	public Question loadQuestion(String id) throws Exception {
		return (Question)this.dbDao.queryObjectById(Question.class, id);
	}

	@Override
	public int countPop(String id) throws Exception {
		 String hql = "from QuestionnaireDetail where questionAnswerId = '"+id+"'";
		return this.dbDao.count(hql);
	}

	@Override
	public int employeeBasicCount() throws Exception {
		return this.dbDao.count("from FhiOaEmployeeBasic");
	}

	@Override
	public int answerCount(String id) throws Exception {
	 
		return this.dbDao.count("from QuestionAnswer where questionId = '"+id+"'");
	}

	@Override
	public int deptEmpCount(String id) throws Exception {
		 
		return this.dbDao.count("from FhiOaEmployeeBasic where depId = '"+id+"'");
	}

	 
	@Override
	public int deptVotePop(String id,String questionnaireId) throws Exception {
		 
		/**
		 * 
		 * select COUNT(DISTINCT name ) from ProductDTO   

		 * @Override
			/**
			 * SELECT DISTINCT  fhi_os_questionnairedetial.questionAnswerUserId FROM fhi_os_questionnairedetial WHERE fhi_os_questionnairedetial.deptId =  '531df229276ed240012774665df70011'
		 * 
		 * */
		
		String hql = "SELECT count(DISTINCT qd.questionAnswerUserId)  FROM QuestionnaireDetail qd WHERE qd.deptId =  '"+id+"' and qd.questionnaireId = '"+questionnaireId+"'";
		//List<QuestionnaireDetail> qdlList=	(List<QuestionnaireDetail>)this.dbDao.queryObjects(hql);
		int count = this.dbDao.count(hql);
	 
		//return this.dbDao.count("from QuestionnaireDetail where deptId = '"+id+"'","questionAnswerUserId");
		return count;
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List votedPopList(String questionnaireId) throws Exception {
		 //String hql = "from FhiOaEmployeeBasic a ,QuestionnaireDetail b where a.id = b.questionAnswerUserId";
		 //List<FhiOaEmployeeBasic> list = this.dbDao.queryObjects(hql);
	     List   list =	jdbcDbDao.queryJDBCforList("select * from fhi_oa_employee_basic  where id in (select questionAnswerUserId from fhi_os_questionnairedetial where questionnaireId = '"+questionnaireId+"') order by DepId");
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List unVotedPopList() throws Exception {
	 
		 List   list =	jdbcDbDao.queryJDBCforList("select * from fhi_oa_employee_basic  where id not in (select questionAnswerUserId from fhi_os_questionnairedetial) order by DepId");
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionAnswer> getQuestionAnswersByQustionnaireId(String id)
			throws Exception {
	 
		return  (List<QuestionAnswer>)this.dbDao.queryObjects("from QuestionAnswer where questionnaireId = '"+id+"' order by questionId");
	}

	@Override
	public QuestionAnswer loadQuestionAnswerById(String id) throws Exception {
		return (QuestionAnswer)this.dbDao.queryObjectById(QuestionAnswer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryQuestionnaire4PageList(Condition cond) {
		try{
			return (List)this.dbDao.queryObjectsToPages(this.setPageInfo(cond));
		}catch(Exception e){
			logger.error("查询单位对象失败:"+e.getMessage());
			return null ;
		}
	}

	@Override
	public boolean checkQuestionnaireName(String name ,String questionnaireId) {
		
		StringBuffer  hql = new StringBuffer(" from QuestionnaireSetup where questionnaireName = '"+name+"'");
		if(questionnaireId != null && !"".equals(questionnaireId.trim())){
			//修改
			hql.append(" and id <> '"+questionnaireId+"'");
		} 
		int count = this.dbDao.count(hql.toString());
		return count>=1?false:true;
	}

}
