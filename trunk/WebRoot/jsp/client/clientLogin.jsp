<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>华展鑫荣客户信息查询平台 v1.0</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/client/login.css" />
</head>
<body>
<div id="login">
    <form class="adminlogin" action="<%=request.getContextPath() %>/client.do?method=login" method="post" name="login_form" id="login_form" onSubmit="return check();">
    	<ul>
        	<li>用户名：<input name="userName" id="userId" type="text" class="text_input"/></li>
            <li class="fontAA">密码：<input name="passWord" id="passWord" type="password" class="text_input"/></li>
            <li><input name="" type="submit" value="" id="login_button" /></li>
        </ul>        
 		<label><p class="errorinfo" id="error">${loginMsg}</p></label>
    </form>
</div>
</body>
<script type="text/javascript">
function check(){
	var username = document.getElementById("userId").value;
	var password = document.getElementById("passWord").value;
	document.getElementById("error").innerHTML="";
	if (username.length<1){
		document.getElementById("error").innerHTML="请输入用户名！";
		return false;
	}else if(password.length<1){
		document.getElementById("error").innerHTML="请输入密码！";
		return false;
	}
}
</script>
</html>
