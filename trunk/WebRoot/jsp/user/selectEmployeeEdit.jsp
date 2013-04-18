<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DivWindow.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择员工1</title>
</head>
<body>
<c:forEach var="employee" items="${employeeList}">
<ul>
	<li><a href="javascript:clickEmployee('${employee.name}','${employee.id}');">${employee.name}</a></li>
</ul>
</c:forEach>
<script type="text/javascript">
function clickEmployee(name,id){
	var va = window.parent.document.getElementById("employeeEdit");
	var vahi = window.parent.document.getElementById("employeeEditHidden");
	vahi.value = id;
	va.value = name;
	window.parent.document.getElementById("employeeEdit").focus();
	window.parent.closeDivWindow("selectEmployeeEdit");
	}
</script>
</body>
</html>