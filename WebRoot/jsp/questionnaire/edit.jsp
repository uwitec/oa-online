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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/workplan.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>


<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/questionnaire/questionnaire.jsp" ></script>
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
        	   <div id="inpageinfo">${rstMsg}</div>
            <script type="text/javascript">
              var rstMsg = "${rstMsg}" ;
              if(rstMsg!=null && rstMsg!="")
                 document.getElementById("inpageinfo").style.display= "block" ;
            </script>
        	<form action="<%=request.getContextPath()%>/questionnaire.do?method=createOrUpdateQuestionnaires" name="pageForm" method="post">
        	
        	<input type="hidden" name="id" value="${questionnaireSetup.id }"/> 
      		<input type="hidden" name="self.custom.Token"  value="<%=session.getAttribute("self.questionnaire.Token")%>"/>
      	    <div class="index_pic">问卷管理</div>
      	  
			 <div class="">
			       <div>
			            <ul>
				    		<li>问卷开始时间：<input name="questionnaireStartTime" type="text" id="startTime" class="text_time" value="<fmt:formatDate value="${questionnaireSetup.questionnaireStartTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				    		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    		 问卷结束时间：<input name="questionnaireEndTime" type="text" id="endTime" class="text_time" value="<fmt:formatDate value="${questionnaireSetup.questionnaireEndTime}" pattern="yyyy-MM-dd"/>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				    		 </li>
			    		</ul>
			    		<ul> 
			    		   <li>问卷名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<input name="questionnaireName" value="${questionnaireSetup.questionnaireName }"/> 
			    		  	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 启用状态：
				    		      <select name="questionnaireStatus">
				    		         <option value="启用">启用</option>
				    		         <option value="停用" <c:if test="${questionnaireSetup.questionnaireStatus eq '停用' }">selected='selected'</c:if>>停用</option>
				    		      </select></li>
			    		</ul>
			            
			        </div>
		      </div>
     </form>
      <div>
           <ul id="com_all_delete02">
              <button id="sub" class="button_bg"  onclick="saveQuestionnaire();">保存</button>
           </ul>
      </div>   
        
</div>
</div>
</div>
</body>
<jsp:include page="/index.do?method=foot" flush="true"/> 
</html>
