<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面重定向中...</title>
</head>
<body>
<script type="text/javascript">
document.body.onload=function(){
	window.location = "<%=request.getContextPath()%>/index.do?method=logout";
	}
</script>
</body>
</html>