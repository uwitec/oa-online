<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>password</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/chang password.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DivWindow.js"></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/UserDwr.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<title>修改密码页</title>
</head>
<body>
<div id="layout">
<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
<div id="mid">
     <jsp:include page="/index.do?method=left" flush="true"></jsp:include>
  <div id="mainpage">
    <div id="location"> 当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a href="#">个人信息</a></span><span><a>修改密码</a></span>
	</div>
		<form action="<%=request.getContextPath() %>/user.do?method=updatePassword" method="post" name="editForm">
		<input type="hidden" name="wu.id" id="id" value="${editUser.id}">
		<input type="hidden" name="wu.userId" id="userId" value="<c:out value="${editUser.userId}" />">
		<input type="hidden" name="wu.name" id="name" value="<c:out value="${editUser.name}" />">
		<input type="hidden" name="wu.employee" id="employee" value="<c:out value="${editUser.employee}" />">
		<input type="hidden" name="wu.employeeId" id="employeeId" value="<c:out value="${editUser.employeeId}" />">
		<c:if test="${modifySuccess==true}"><label class="redletter" id="ModifySuccess">修改用户密码成功！</label></c:if>
		<c:if test="${modifySuccess!=true}"><label class="redletter" id="ModifySuccess"></label></c:if>
		<c:if test="${modifyfailure==true}"><label class="redletter" id="ModifySuccess">修改用户密码不成功！</label></c:if>
		<c:if test="${modifyfailure!=true}"><label class="redletter" id="ModifyFailure"></label></c:if></h2>
    <div class="index_pic">修改密码：</div> 
    <div id="inpageinfo"></div>
    <div class="system_configuration_box">
      <form>
        <div class="test" id="change_box">
          <ul>
            <li> 原 密 码：
              <input name="orpassWord" type="password" class="input_80" id="orpassword" maxlength="20">
            </li>
            <li> 新 密 码：
              <input name="wu.passWord" type="password" class="input_80" id="newpassword" maxlength="20">
            </li>
            <li> 确认密码：
              <input name="cpassword" type="password" class="input_80" id="cpassword" maxlength="20" onfocusout="passWd()">
            </li>
          </ul>
        </div>
      </form>
     <div>
           <ul id="com_all_delete02">
              <button id="sub" class="button_bg">保存</button>
              <button id="res" class="button_bg">重置</button>
           </ul>
         </div>   
      </div>
    </form>
  </div>
  <script type="text/javascript">
function passWd(){
	
}
	document.getElementById('sub').onclick=function(){
		
		var returnvalue;
		var password = document.getElementById("newpassword").value;
		var password1 = document.getElementById("cpassword").value;
		var orpassword = document.getElementById("orpassword").value;
		var id = document.getElementById("id").value;
		dwr.engine.setAsync(false); 
		UserDwr.isPassWord(id,orpassword,function(test){
			if(test)
			{
				returnvalue = 1;
			}
			else{
				showMsgbox("原密码不一致！");
				returnvalue = 0;
			}
			});
		if (password == "" || password1== ""){
			showMsgbox("密码不能为空！");
			return false;
		}else if(password.length<6){
			showMsgbox("密码位数不足！");
			return false;			
		}else if(password != password1){
			showMsgbox("确认密码不一致！");
			return false;
		}else if(returnvalue == 0){
			return false;
		}
		document.forms['editForm'].submit();
}
	document.getElementById('res').onclick=function(){
		document.forms['editForm'].action="";
		document.forms['editForm'].submit();
}
</script>
</div>
<jsp:include page="/index.do?method=foot" flush="true"/>
</div>
</body>
</html>