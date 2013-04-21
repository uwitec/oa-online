<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择问卷</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/chang password.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DivWindow.js"></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/UserDwr.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript">

  function submitSelect(){

	  var  questionnaireId = document.getElementById("questionnaireId").value;
	  if(questionnaireId == undefined || ""==questionnaireId){
			alert("请您选择要回答问卷！");
			return;
	   } 
	  document.forms['editForm'].submit();
  }


</script>
</head>
<body>
<div id="layout">
<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
<div id="mid">
     <jsp:include page="/index.do?method=left" flush="true"></jsp:include>
  <div id="mainpage">
    <div id="location"> 当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a href="#">个人信息</a></span><span><a>选择问卷</a></span>
	</div>
		<form action="<%=request.getContextPath() %>/questionnaire.do?method=questionnaireList" method="post" name="editForm">
    <div class="index_pic">选择问卷</div> 
    <div id="inpageinfo"></div>
    <div class="system_configuration_box">
      
        <div class="test" id="change_box">
          <ul>
            <li> 选择您要回答的问卷：
             <select name="questionnaireId">
               	<option value="">---请选择---</option>
	             <c:forEach var="setup" items="${questionnaireList}">
	             	 
	                   <option value="${setup.id }">${setup.questionnaireName }</option>
	             </c:forEach>
             </select>
            </li>
          </ul>
        </div>
       
     <div>
           <ul id="com_all_delete02">
              <button id="sub" class="button_bg" onclick="submitSelect();">确定</button>
               
           </ul>
         </div>   
      </div>
    </form>
  </div>

</div>
<jsp:include page="/index.do?method=foot" flush="true"/>
</div>
</body>
</html>