<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加用户组</title>
</head>
<body>
<div id="addDep">  
<form action="#" method="post" id="addUser">

<ul> 
	<li><font style="font-weight:bold">添加角色</font><font color="red"> <span id="message"></span></font></li> 
</ul> 
<ul>
	<li><th>角色名称</th><input name="ltsRo.roleName" type="text" id="RoleName" value="${roleId.roleName }" /></li>
</ul>
<ul>
	<li><th>角色代码</th><input name="ltsRo.roleCode" type="text" id="RoleCode" value="${roleId.roleName }" /></li>
</ul>


<c:if test="${new}"><input name="ltsRo.pid" type="hidden" id="pid" value=${roleId.id } /></c:if>
<c:if test="${new==false}"><input name="ltsRo.pid" type="hidden" id="pid" value=${roleId.pid } /></c:if>
<input name="ltsRo.isPid" type="hidden" id="isPid" value="${roleId.isPid }"/>
<input name="ltsRo.id" type="hidden" id="id" value="${roleId.id }"/>

</form>
</div>
</body>
</html>