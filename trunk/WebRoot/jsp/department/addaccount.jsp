<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建账户</title> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/jsp/department/acc.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/department/accountjs.jsp"></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/UserDwr.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>
</head>
<body>
<form action="<%=request.getContextPath()%>/user.do?method=add" method="post" id="addUser"  name="addUser">
	<input type="hidden" name="permission" id="Permission"/> 
    <input type="hidden" name="wu.employeeHidden" id="employeeHidden"  value="${employee.id}" />
    <input type="hidden" name="emAddUser" id="emAddUser"  value="yes" />
	<div id="account_em">
	  <ul id="operattitle">新建用户<span id="error"></span></ul> 
	  <ul>
	    <li>用户ID:(母开头,长度5~20)<input name="wu.userId" type="text" id="userId" onKeyDown="value=value.replace(/[^\da-zA-Z]/g,'')" onfocusout="return isExist();"/></li>
	    <li>密码:(密码长度6~20)<input name="wu.passWord" type="password" id="passWord"   onfocusout="passWd()"/></li>
	   </ul> 
	  <ul>
	    <li>确认密码:<input name="wu.passWord1" type="password" id="passWord1"   onfocusout="passWd()"/></li>
	    <li>用户姓名:<input type="text" name="wu.employee" id="employee" value="${employee.name}"  readonly /></li>
	   </ul> 
	   <!--  
	   <ul>
	    <li>E-mail:(请使用公司邮箱)<input type="text" name="wu.email" id="email" onfocusout="return isEmail();" value="${employee.email}" /></li> 
	  </ul> -->
	</div>
	<div id="account_right">
	  <ul>
	  <li>
	     可选权限:
	    <select name="select1" size="4" multiple>
		  <c:forEach var="permission" items="${permissionsession}">
			 <option value="<c:out value="${permission.id}"></c:out>"><c:out value="${permission.roleName}"></c:out></option> 
		  </c:forEach>			
        </select>
       </li>
       <li id="lroperat">
          <input   name="d" type="button" id="d" value="&lt;&lt;" onClick="returnmen();"/>
	      <input   name="d" type="button" id="d" value="&gt;&gt;" onClick="addmen();"/>
	   </li>
       <li>被选权限:<select name="select2" size="4" multiple></select></li> 
      </ul>  
      <!-- 
      <ul>
      <li> 
		 <c:if test="${user.userId!='admin'}"><input type="hidden" name="wu.flag" id="flag" value="${user.flag}" /></c:if>
	  </li></ul> -->
	</div> 
</form>
</body>
</html>