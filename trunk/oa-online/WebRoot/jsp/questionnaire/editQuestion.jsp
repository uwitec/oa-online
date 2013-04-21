<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/stream.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/custom/comjs.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>


<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/questionnaire/questionnaire.jsp" ></script>

<style type="text/css">
 
</style>
<title>问卷列表页</title>
</head> 
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
    	<div id="mainpage">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span>
        	 <span><a href="#">个人中心</a></span></div>
		   	 <div id="inpageinfo" align="center">${rstMsg}</div>
		   	 <script type="text/javascript">
              var rstMsg = "${rstMsg}" ;
              if(rstMsg!=null && rstMsg!="")
                 document.getElementById("inpageinfo").style.display= "block" ;
            </script>
        	<form action="<%=request.getContextPath()%>/questionnaire.do?method=savaOrUpdateQuestionsLast" name="pageForm" method="post">
        	
        	<input name="itemTableValue" id ="itemTableValue" type="hidden" value="${questionListSize }" />
         	<input type="hidden" name="questionnaireId" value="${questinnaireId }"/>
      		<input type="hidden" name="self.custom.Token"  value="<%=session.getAttribute("self.questionnaire.Token")%>"/>
      		    
      	       <div class="splipbar" >
                <table  width="770"  id="table" border="1" cellspacing="0" cellpadding="0" bordercolor="#000000" bordercolordark="#FFFFFF" bgcolor="#FFFFFF" >
                   <thead class="list_title">
                     
					 <tr>
                         <th align="center" width="20"></th>
                         <th align="center" width="100">题目内容</th>
                         <th align="center">是否多选</th>
                         <th align="center">备选项</th>
                         <th align="center">操作</th>
                      </tr>
                   </thead>
                   <c:forEach var="question" items="${editQuestionList}" varStatus="status">
                   <tr >
                      <td>
                      		<input type='checkbox' name='checkbox' value="${status.count }"  />
                      		<input type="hidden" name="rowNum" value = "${status.count }"/>
                      </td>
                      <td align="center">
                                <input type="hidden" name="ids" value="${question.id }"/>										   
                                
                          		<input type="text" name="questionContent" value = "${question.questionContent }"/>
                          		
                      </td >
                      <td align="center">
                           		 <select name="isMultSelect">
				    		         <option value="否">否</option>
				    		         <option value="是" <c:if test="${question.isMultiSelect eq '是' }">selected='selected'</c:if>>是</option>
				    		      </select>
                      </td>
                      <td  id = "selectItem" align="center">
                          <table border="0" id="selectItemsTable${status.count }">
                          
                             <c:forEach var="questionAnswer" items="${question.questionAnswersList}" varStatus="innerStatus">
                                	<tr align="center">
                                   		<td align="center">
                                   		        <input type="checkbox" name="${questionAnswer.questionId }"/>
                                 				<input type="text" name="questionContent${status.count }" value="${questionAnswer.questionSelectAnswer }"/>
                                 				<input type="hidden" name="questionAnswerId${innerStatus.count }" value="${questionAnswer.id }"/>
                                 				<input type="hidden" name= "itemsTableRowsCount${status.count }" value="${innerStatus.count }"/><!-- 取最后一条信息 -->
                                 	    </td>
                              		</tr> 
                                  
                            </c:forEach>
                          </table>
                      </td>
                      <td align="center"><a href="javascript:toDelete('selectItemsTable${status.count}');">删除备选项</a>   <a href="javascript:addSelectItems('selectItemsTable${status.count}');">添加备选项</a> </td>
                   </tr>
                    
                   </c:forEach>
                </table>
            
		    </div>
           </form>
      <div>
         <br />
         <br />
        <div align="center" >
           <ul >
             <li >
              <button  id="sub" class="button_bg"  onclick="saveQuestions();">保存</button>
              <button id="res" class="button_bg"  onclick="insert();">插入行</button>
              <button id="res" class="button_bg"  onclick="toDeleteMainTable();">删除行</button>
              </li>
           </ul>
           </div>
         </div>   
        
</div>
</div>
</div>
</body>
<jsp:include page="/index.do?method=foot" flush="true"/> 
</html>
