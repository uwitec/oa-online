<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
</body>
<script type="text/javascript">	
if (self.location!=top.location){
		//alert(window.parent.document.forms['login_form']);
		window.parent.document.location="<%=request.getContextPath() %>/${url}";
	}
	else{
		window.location.href="<%=request.getContextPath()%>/${url}";
	}
</script>
</html>