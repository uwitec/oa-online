<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>华展鑫荣信息共享平台 v1.0</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
</head>
<script type='text/javascript' id="dwrUrl" src='<%=request.getContextPath() %>/dwr/interface/UserDwr.js'></script>
<script type='text/javascript' id="dwrUrlB" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<body onLoad="show();">
<script type="text/javascript">
function show(){
	if (self.location!=top.location){
		//alert(window.parent.document.forms['login_form']);
		window.parent.document.location="<%=request.getContextPath() %>";
	}
}
</script>
<div id="login">
    <form class="adminlogin" action="<%=request.getContextPath() %>/index.do?method=login" method="post" name="login_form" id="login_form" onSubmit="return check();">
    	<ul>
        	<li>用户名：<input name="wu.userId" id="userId" type="text" class="text_input" value="admin" onblur="value=value.replace(/[^\da-zA-Z]/g,'')"/></li>
            <li class="fontAA">密码：<input name="wu.passWord" id="passWord" type="password" class="text_input" value="888888"/></li>
            <li><input type="submit" value="" id="login_button" /></li>
            <input type="hidden" name="test" value="sd6511sdf651sdf651"/>
        </ul>        
 		<label><p class="errorinfo" id="error"><c:if test="${noLogin==true}">您尚未登陆！</c:if></p></label>
    </form>
</div>
</body>
<script type="text/javascript">
function check(){
	var username = document.getElementById("userId").value;
	var password = document.getElementById("passWord").value;
	var returnvalue;
	document.getElementById("error").innerHTML="";
	if (username.length<1){
		document.getElementById("error").innerHTML="请输入用户名！";
		return false;
	}else if(password.length<1){
		document.getElementById("error").innerHTML="请输入密码！";
		return false;
	}
		dwr.engine.setAsync(false); 
		UserDwr.dwrequery(username,password,function(take){
			if(take){
				returnvalue =1;
			}else{
				returnvalue = 2;
			}
			});
	if(returnvalue == 1){
		return true;
	}else{
		document.getElementById("error").innerHTML="用户名或密码错误！";
		return false;
	}
}

function loginSel(obj){
	if(obj.value=="")return;
	window.location = "/" + obj.value + "/index.do?method=logout&site="+obj.value;
	}
</script>
</html>
