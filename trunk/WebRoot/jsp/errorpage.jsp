<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统提示</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
</head>
<body>

</body>
<script type="text/javascript">
	var forward = "${url}";
	var message = "${message}";
	var windowMsg = "程序可能出错啦！请重新登录系统。<br>如还有问题请及时联系管理员！";
	if(message!=""){
		windowMsg = message;
	}
	var error = new DivWindow("error","系统提示",250,100,windowMsg);
	error.addButton("确定",function(){
			if(forward!=""){
				window.location.href = "<%=request.getContextPath()%>/"+forward;
			}
			else{
				window.location.href = "<%=request.getContextPath()%>/index.do?method=logout";
			}
		});
	error.open();
</script>
</html>