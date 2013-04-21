package com.fhi.questionnaire.action ;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.department.service.DepartmentIn;
import com.fhi.department.vo.FhiOaDepartment;
import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.utils.ExportExcel;
import com.fhi.framework.utils.date.DataUtils;
import com.fhi.questionnaire.condition.QuestionnaireCondition;
import com.fhi.questionnaire.form.QuestionnaireForm;
import com.fhi.questionnaire.service.QuestionnaireIn;
import com.fhi.questionnaire.vo.DeptExcelBean;
import com.fhi.questionnaire.vo.Question;
import com.fhi.questionnaire.vo.QuestionAnswer;
import com.fhi.questionnaire.vo.QuestionnaireDetail;
import com.fhi.questionnaire.vo.QuestionnaireSetup;
import com.fhi.questionnaire.vo.VoteListExcelBean;
import com.fhi.user.vo.FhiUser;

public class QuestionnaireAction extends DispatchAction  { 
	
	private static Logger logger = Logger.getLogger(QuestionnaireAction.class);	
	 
	 
	private FhiUser user; 
	private HttpSession session ;

	private QuestionnaireIn  questionnaireInService;
	
	private QuestionnaireCondition questionnaireCondition;
	
	private DepartmentIn depService;
	 
	public QuestionnaireCondition getQuestionnaireCondition() {
		return questionnaireCondition;
	}


	public void setQuestionnaireCondition(
			QuestionnaireCondition questionnaireCondition) {
		this.questionnaireCondition = questionnaireCondition;
	}


	
	public DepartmentIn getDepService() {
		return depService;
	}


	public void setDepService(DepartmentIn depService) {
		this.depService = depService;
	}


	QuestionnaireForm questionnaireForm = new QuestionnaireForm() ;
	public QuestionnaireIn getQuestionnaireInService() {
		return questionnaireInService;
	}


	public void setQuestionnaireInService(QuestionnaireIn questionnaireInService) {
		this.questionnaireInService = questionnaireInService;
	}


	
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		questionnaireForm = (QuestionnaireForm)form;
		session = request.getSession(true) ; 
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}   
		List<QuestionnaireSetup>  list = questionnaireInService.getQuestionnaireSetup();
		request.setAttribute("questionnaireList", list);
		return super.execute(mapping, form, request, response);
	} 
	
	
	
	 
	
	@SuppressWarnings("unchecked")
	public ActionForward questionnaire(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//List<QuestionnaireSetup>  questionnaireList = questionnaireInService.getQuestionnaireSetup(); 
		
		List questionnaireList = questionnaireInService.queryQuestionnaire4PageList(questionnaireCondition.setHql(questionnaireForm)); 
		request.setAttribute("questionnaireList", questionnaireList);
		request.setAttribute("form", form);
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, questionnaireCondition.getPageInfo()) ;
		return mapping.findForward("questionnaire") ;
	}
	public ActionForward selectQuestionnaire(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if(user==null){
			return mapping.findForward("login");
		}
		
		 
		return mapping.findForward("selectQuestionnaire");
	}
	
	/**
	 * 
	 * 调查   不能重复;  时间范围内
	 * */
	public ActionForward questionnaireList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//不能重复投票在规定的时间内投票
		//String  qustionnaireId = "4c31af9e-59c0-4afb-8d21-2f4d83a1b538";
		String  qustionnaireId = request.getParameter("questionnaireId");
		FhiUser user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		
		
	   
	    if(questionnaireInService.checkUser(user.getEmployeeClass().getId(),qustionnaireId)){
	    	
	    	if(questionnaireInService.checkTimeAndStatus(qustionnaireId)>0){
	    		List<Question>  questionList = questionnaireInService.getQuestionsById(qustionnaireId);
				for (Question question : questionList) {
					List<QuestionAnswer>  answerList = questionnaireInService.getQuestionAnswerById(question.getId());
				   question.setQuestionAnswersList(answerList);
				}
				session.setAttribute("questionList", questionList);
				request.setAttribute("form", form);
				request.setAttribute("qustionnaireId", qustionnaireId);
				return mapping.findForward("questionnaireQuestions") ;
	    		
	    	}else {
	    		request.setAttribute("rstMsg", "问卷调查已结束或者已被停用！");
	    		return  mapping.findForward("index");
			}
	    	
			
	    } else {
	    	request.setAttribute("rstMsg", "您已经投过票了,不能重复投票！");
	    	return  mapping.findForward("result");
		}
	    
	    
	}
	@SuppressWarnings("unchecked")
	public ActionForward saveAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		FhiUser user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		String qustionnaireId = request.getParameter("id");
		List<Question>  questionList = (List<Question>)session.getAttribute("questionList"); 
		
		List<QuestionnaireDetail>  questionnaireDetailList  = new ArrayList<QuestionnaireDetail>();
		//向详情表中添加信息
		for (int i = 0; i < questionList.size(); i++) {
			
			String[]  answers =	request.getParameterValues(questionList.get(i).getId());
		    if(answers==null || answers.length==0){
		    	
		    	return mapping.findForward("questionnaireQuestions");
		    }
			if(answers!=null && answers.length!=0){
			for (int j = 0; j < answers.length; j++) {
				QuestionnaireDetail  questionnaireDetail = new QuestionnaireDetail();
				                     questionnaireDetail.setId(java.util.UUID.randomUUID().toString());
				                     questionnaireDetail.setDeptId(user.getEmployeeClass().getDepId());
				                     questionnaireDetail.setCompany(user.getEmployeeClass().getCompanyId());
				                     questionnaireDetail.setQuestionAnswerCode(user.getEmployeeClass().getName());
				                    // questionnaireDetail.setQuestionAnswerCode(user.getName());
				                     questionnaireDetail.setQuestionAnswerId(answers[j]);
				                     questionnaireDetail.setQuestionAnswerUserId(user.getEmployeeClass().getId());
				                     questionnaireDetail.setQuestionId(questionList.get(i).getId());
				                     questionnaireDetail.setQuestionnaireId(qustionnaireId);
				        questionnaireDetailList.add(questionnaireDetail);
				}
			}
		}
		if (questionnaireInService.checkUser(user.getEmployeeClass().getId(),qustionnaireId)) {
			request.setAttribute("rstMsg", "您提交问卷已保存成功！谢谢您的参与，祝您工作愉快！");
			questionnaireInService.saveQuestionnaireDetail(questionnaireDetailList);
		}else {
			request.setAttribute("rstMsg","您已经投过票了！不能重复投票");
		}
		
		//request.setAttribute("rstMsg", "您提交问卷已保存成功！谢谢你的参与，祝您工作愉快！");
		return mapping.findForward("result") ;
	}

	@SuppressWarnings("unchecked")
	public ActionForward questionnaireManagement(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//删除
		String id = request.getParameter("id");
		if(id!=null && !"".equals(id.trim())){
			questionnaireInService.deleteQuestionnaireOneOrMore(id);
			
		}
		List questionnaireList = questionnaireInService.queryQuestionnaire4PageList(questionnaireCondition.setHql(questionnaireForm)); 
		request.setAttribute("questionnaireList", questionnaireList);
		request.setAttribute("form", form);
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, questionnaireCondition.getPageInfo()) ;
		return mapping.findForward("questionnaireManagement") ;
	}
	
	public ActionForward createOrEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			String id = request.getParameter("id");//问卷id
			if (id!=null && !"".equals(id.trim())) {
				//修改问卷管理
				QuestionnaireSetup questionnaireSetup =	questionnaireInService.loadQuestionnaire(id);
				request.setAttribute("questionnaireSetup", questionnaireSetup);
				//问卷问题列表
				//List<Question>  questionsList = questionnaireInService.getQuestionsById(id);
				//request.setAttribute("questionnaireList", questionsList);
			}
			
		
		    return mapping.findForward("edit") ;
	}
	public ActionForward createOrEditQuestion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
			String id = request.getParameter("id");
			//取出问题和相关答案
			
			List<Question>  questionList = questionnaireInService.getQuestionsById(id);
			for (Question question : questionList) {
				List<QuestionAnswer>  answerList = questionnaireInService.getQuestionAnswerById(question.getId());
			   question.setQuestionAnswersList(answerList);
			}
			request.setAttribute("editQuestionList", questionList);
			request.setAttribute("questionListSize", questionList.size());
			
			request.setAttribute("questinnaireId",id);
		    return mapping.findForward("editQuestion") ;
	}
	public ActionForward savaOrUpdateQuestions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		    questionnaireForm = (QuestionnaireForm)form;
		    //保存题目时保存答案
		    Question question = new Question();
		    		 question.setId(java.util.UUID.randomUUID().toString());
		    		 question.setIsMultiSelect(request.getParameter("isMultiSelect")==null?"否":request.getParameter("isMultiSelect"));
		    		 question.setQuestionContent(request.getParameter("questionContent"));
		    		 question.setQuestionnaireId(request.getParameter("id"));
		    		 question.setCreateDate(new Date());
		    		 String[] answers = questionnaireForm.getQuestionAnswers();
		    		 List<QuestionAnswer> questionAnswerList = new ArrayList<QuestionAnswer>();
		    		 
					   for (int i = 0; i < answers.length; i++) {
						    QuestionAnswer  questionAnswer = new  QuestionAnswer();
						    				questionAnswer.setId(java.util.UUID.randomUUID().toString());
						    				questionAnswer.setQuestionId(question.getId());
						    				questionAnswer.setQuestionSelectAnswer(answers[i]);
						    			    questionAnswer.setQuestionnaireId(question.getQuestionnaireId());
						    questionAnswerList.add(questionAnswer);
					   }
			//保存题目  答案选项
			//questionnaireInService.saveQuestion(question, questionAnswerList);
			String id = request.getParameter("id");
			request.setAttribute("questinnaireId",id);
		    return mapping.findForward("editQuestion") ;
	}
	
	public ActionForward savaOrUpdateQuestionsLast(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		    questionnaireForm = (QuestionnaireForm)form;
		    List<Question>  questionList = new ArrayList<Question>();
		    List<QuestionAnswer> questionAnswerList = new ArrayList<QuestionAnswer>();
		    //多个题目循环
		    Question question = null;
		    QuestionAnswer  questionAnswer = null;
		    for (int i = 0; i < questionnaireForm.getQuestionContent().length; i++) {
		    	//根据题目判读 修改或者新建
		    	/*String  questionId = questionnaireForm.getIds()[i];
		    	if(questionId != null && !"".equals(questionId)){
		    		*//**
		    		 * 	问卷问题的修改
		    			(1)问题本身的修改
		    		 	(2)问卷答案修改和新增
		    		 * *//*
		    		question = questionnaireInService.loadQuestion(questionId);
		    		question.setCreateDate(new Date());
		    		question.setIsMultiSelect(request.getParameter("isMultiSelect")==null?question.getIsMultiSelect():request.getParameter("isMultiSelect"));//是否多选
		    		question.setQuestionContent(questionnaireForm.getQuestionContent()[i]);//问卷内容
		    		
		    		//问卷答案数组
		    		String[] answers = request.getParameterValues("questionContent"+questionnaireForm.getRowNum()[i]);
		    		for (int j = 0; j< answers.length; j++) {
		    			//每行中答案对应的值
		    			 String[] questionAnswerIds = request.getParameterValues("itemsTableRowsCount"+questionnaireForm.getRowNum()[i]);
		    		 
							String  answerId = request.getParameter("questionAnswerId"+questionAnswerIds[j]);
							if(answerId != null && !"".equals(answerId)){
								//更新答案
								questionAnswer = questionnaireInService.loadQuestionAnswerById(answerId);
								questionAnswer.setQuestionId(question.getId());
								questionAnswer.setQuestionSelectAnswer(answers[j]);
								questionAnswer.setQuestionnaireId(question.getQuestionnaireId());
								questionAnswerList.add(questionAnswer);
								
							}else {
								//新增答案
								questionAnswer = new  QuestionAnswer();
								questionAnswer.setId(java.util.UUID.randomUUID().toString());
								questionAnswer.setQuestionId(question.getId());
								questionAnswer.setQuestionSelectAnswer(answers[j]);
								questionAnswer.setQuestionnaireId(question.getQuestionnaireId());
								questionAnswerList.add(questionAnswer);
								
							//}
							
						 
				    }
		    		question.setQuestionAnswersList(questionAnswerList);
		    		questionList.add(question);
		    		
		    		
		    	}else {*/
					//新加的问题
		    		question = new Question();
		    		question.setId(java.util.UUID.randomUUID().toString());
		    		question.setIsMultiSelect(questionnaireForm.getIsMultSelect()[i]==null?"否":questionnaireForm.getIsMultSelect()[i]);//是否多选
		    		question.setQuestionContent(questionnaireForm.getQuestionContent()[i]);//问卷内容
		    		question.setQuestionnaireId(questionnaireForm.getQuestionnaireId());//问卷Id
		    		question.setCreateDate(new Date());//创建时间
		    		
		    		//问卷答案数组
		    		  List<QuestionAnswer> qsl = new ArrayList<QuestionAnswer>();
		    		String[] answers = request.getParameterValues("questionContent"+questionnaireForm.getRowNum()[i]);
		    		 for (int j = 0; j< answers.length; j++) {
						      questionAnswer = new  QuestionAnswer();
						    				questionAnswer.setId(java.util.UUID.randomUUID().toString().toUpperCase());
						    				questionAnswer.setQuestionId(question.getId());
						    				questionAnswer.setQuestionSelectAnswer(answers[j]);
						    			    questionAnswer.setQuestionnaireId(question.getQuestionnaireId());
						    questionAnswerList.add(questionAnswer);
						    qsl.add(questionAnswer);
						  
					 }
		    		 question.setQuestionAnswersList(qsl);
		    		 questionList.add(question);
				// }
		    	
				   
			}
		    //保存题目时保存答案
		     
			//保存题目  答案选项
			questionnaireInService.saveQuestion(questionList, questionAnswerList,questionnaireForm.getQuestionnaireId());
			 
			request.setAttribute("editQuestionList", questionList);
			request.setAttribute("questionListSize", questionList.size());
			request.setAttribute("rstMsg", "数据保存成功！");
		    return mapping.findForward("editQuestion") ;
	}
	/**
	 * 报检记录单报表导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward questionaireDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			  String fname = "questionnaireReport";//Excel文件名
		      OutputStream os = response.getOutputStream();//取得输出流
		      response.reset();//清空输出流
		    
		      response.setHeader("Content-disposition", "attachment; filename=" + fname + ".xls");//设定输出文件头
		      response.setContentType("application/msexcel");//定义输出类型
		      response.setCharacterEncoding("utf-8");
		      
			 
		      //questionaireDownload
		      
		      String  questionaireId = request.getParameter("questionnaireId");
		      
		      DecimalFormat df=new DecimalFormat("#.####");
			  int total = questionnaireInService.employeeBasicCount();
		      List<QuestionAnswer> dataset = questionnaireInService.getQuestionAnswersByQustionnaireId(questionaireId);
		      //部门统计表  start
		      List<FhiOaDepartment> dataset1 = depService.getDepartments();
		      List<DeptExcelBean>  deptExcelList = new ArrayList<DeptExcelBean>();
		      
		    
		      int  totalDeptVote = 0;
		      int  deptVote = 0;
		      double votePercent = 0.0000;
		      int  deptPopCount = 0;
		      for (int i = 0; i <= dataset1.size(); i++) {
		    	  DeptExcelBean  deptExcelBean = new DeptExcelBean();
		    	  if(i<dataset1.size()){
		    		  deptExcelBean.setDeptName(dataset1.get(i).getDepName());//部门名称
		    		  //
		    		  deptPopCount = questionnaireInService.deptEmpCount(dataset1.get(i).getId());//部门人数
		    		  deptExcelBean.setDeptPopCount(deptPopCount);
		    		  deptVote = questionnaireInService.deptVotePop(dataset1.get(i).getId(),questionaireId);//部门投票人数
		    		  totalDeptVote = totalDeptVote+deptVote;
		    		  deptExcelBean.setDeptVoteCount(deptVote);
		    		  //部门投票率
		    		  votePercent =((double)deptVote/ (double)deptPopCount)*100.0000;
		    		   String st=df.format(votePercent);
		    		  deptExcelBean.setDeptPercent(st+"%");
		    		 
		    	  }else if (i==dataset1.size()){
		    		  deptExcelBean.setDeptName("总计");
		    		  deptExcelBean.setDeptPopCount(total);
		    		  deptExcelBean.setDeptVoteCount(totalDeptVote);
		    		  votePercent =((double)totalDeptVote/ (double)total)*100.0000;
		    		  String st=df.format(votePercent);
		    		  deptExcelBean.setDeptPercent(st+"%");
		    		  
		    		  
				  }
		    	  //部门人数
		    	  if(deptExcelBean.getDeptPopCount()>0){
		    		  deptExcelList.add(deptExcelBean);
		    	  }
		    	 
		    	  
				
			  }
		      //部门统计表  end
		      
		      
		      //投票名单  start
		      //  获得投票人数
		      List  listEb = questionnaireInService.votedPopList(questionaireId);
		      List<VoteListExcelBean>  votedList = new ArrayList<VoteListExcelBean>();
		     for (int i = 0; i < listEb.size(); i++) {
		    	 //返回回来的是ListOrderMap
		    	 ListOrderedMap  ldm =(ListOrderedMap) listEb.get(i);
		    	 
		    	 VoteListExcelBean  vleb = new VoteListExcelBean();
		    	 					vleb.setDeptName((String)ldm.get("DepName"));
		    	 					vleb.setUserName((String)ldm.get("Name"));
		    	 					votedList.add(vleb);
			 } 
		      
		      //投票名单  end
		     
		     /**
		      * 未投票人数  start
		      * */
		     List  unVotePopList = questionnaireInService.unVotedPopList();
		      List<VoteListExcelBean>  unVotedList = new ArrayList<VoteListExcelBean>();
		     for (int i = 0; i < unVotePopList.size(); i++) {
		    	 //返回回来的是ListOrderMap
		    	 ListOrderedMap  ldm =(ListOrderedMap) unVotePopList.get(i);
		    	 VoteListExcelBean  vleb = new VoteListExcelBean();
		    	 					vleb.setDeptName((String)ldm.get("DepName"));
		    	 					vleb.setUserName((String)ldm.get("Name"));
		    	 					unVotedList.add(vleb);
			 } 
		     /**
		      * 未投票人数  end
		      * */
		     //答案个数
		     Map<String ,Integer> answerMap  = new HashMap<String ,Integer>();
		     List<Integer>  answerCount = new ArrayList<Integer>();
		      for (QuestionAnswer questionAnswer : dataset) {
		    	   questionAnswer.setQuestionContent(questionnaireInService.loadQuestion(questionAnswer.getQuestionId()).getQuestionContent());
		    	   questionAnswer.setTotalSelected(questionnaireInService.countPop(questionAnswer.getId()));
		    	   double p = ((double)questionAnswer.getTotalSelected() /total)*100.0000;
		    	   String st=df.format(p);
		    	   questionAnswer.setSelectedPercent(st+"%");
		    	   int  count  = questionnaireInService.answerCount(questionAnswer.getQuestionId());
		    	   questionAnswer.setAnswerCount(String.valueOf(count));
		    	   if(!answerMap.containsKey(questionAnswer.getQuestionId())){
		    		   answerCount.add(Integer.parseInt(questionAnswer.getAnswerCount()));
		    	   }
		    	  
			   }
		      
			  logger.debug("查询报检记录单报表数据条数："+dataset.size());
			  
		      ExportExcel<QuestionAnswer> ee=new ExportExcel<QuestionAnswer>();

		      
		      String[] headers={"问卷内容","选项","投票人数(总人数:"+total+")","所占百分比"};
		      Short[] widths={45,40,20,20};
		      //答案个数
		  	//List<QuestionAnswer>  answerList = questionnaireInService.getQuestionAnswerById(question.getId());
		      ee.myExportExcel("问卷情况表", headers, widths, dataset,deptExcelList,votedList,unVotedList,answerCount ,os);
		     
		      
		    }catch(Exception e){
		    	logger.error("生成报检记录单报表错误！", e);
		    } 
		    return mapping.findForward("print") ;
	}
	
	
	public ActionForward createOrUpdateQuestionnaires(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 
		try{
			 questionnaireForm = (QuestionnaireForm)form;
			String  questionnaireStartTime = request.getParameter("questionnaireStartTime");//问卷开始时间
			
			String questionnaireEndTime = request.getParameter("questionnaireEndTime");//问卷结束时间
			
			String questionnaireStatus = request.getParameter("questionnaireStatus");//问卷状态
			
			String questionnaireName = request.getParameter("questionnaireName");//问卷名称
			
			String id = request.getParameter("id");//问卷id
			QuestionnaireSetup questionnaireSetup = null;
			if(id !=null && !"".equals(id)){
				questionnaireSetup = questionnaireInService.loadQuestionnaire(id);
				
			}else {
				questionnaireSetup = new QuestionnaireSetup();
				questionnaireSetup.setId(java.util.UUID.randomUUID().toString());
			}
		
			   questionnaireSetup.setQuestionnaireName(questionnaireName);
			   questionnaireSetup.setQuestionnaireEndTime(DataUtils.getDateByString(questionnaireEndTime));
			   questionnaireSetup.setQuestionnaireStartTime(DataUtils.getDateByString(questionnaireStartTime));
			   questionnaireSetup.setQuestionnaireStatus(questionnaireStatus);
							   
			   if(id !=null && !"".equals(id)){
				   if(questionnaireInService.checkQuestionnaireName(questionnaireSetup.getQuestionnaireName(),questionnaireSetup.getId())){
					   questionnaireInService.updateQuestionnaire(questionnaireSetup);
					}else {
						request.setAttribute("questionnaireSetup", questionnaireSetup);
						request.setAttribute("rstMsg", "问卷名称重复，保存失败");
						return mapping.findForward("edit") ;
					}
					
				}else {
					if(questionnaireInService.checkQuestionnaireName(questionnaireSetup.getQuestionnaireName(),"")){
						 questionnaireInService.saveQuestionnaire(questionnaireSetup);
					}else {
						questionnaireSetup.setId("");
						request.setAttribute("questionnaireSetup", questionnaireSetup);
						request.setAttribute("rstMsg", "问卷名称重复，保存失败");
						return mapping.findForward("edit") ;
					}
					
				} 
			 
		}catch(Exception e){
			logger.error("添加信息失败",e) ;
		}  
		
		
		
		List<QuestionnaireSetup>  questionnaireList = questionnaireInService.getQuestionnaireSetup(); 
		request.setAttribute("questionnaireList", questionnaireList);
		return mapping.findForward("relist") ;
	}
}