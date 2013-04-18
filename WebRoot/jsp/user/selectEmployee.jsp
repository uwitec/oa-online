<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择员工</title>
</head>
<body>
			<div id="addDep">
			<ul>
				<li>用户姓名</li>
			</ul>
			<c:forEach var="employee" items="${employeeList}">
			<ul>
				<li><a href="javascript:clickEmployee('${employee.name}','${employee.id}');">${employee.name}</a></li>
			</ul>
			</c:forEach>
			</div>
			
<script type="text/javascript">
function clickEmployee(name,id){
	var va = window.parent.document.getElementById("employee");
	var vahi = window.parent.document.getElementById("employeeHidden");
	vahi.value = id;
	va.value = name;
	window.parent.document.getElementById("passWord").focus();
	window.parent.closeDivWindow("selectEmployee");
	}
</script>
</body>
</html>