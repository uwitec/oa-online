<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子期刊_${title}</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/new_general.css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
</head>
<body>
<div id="layout">
<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
<div id="mid">
  <jsp:include page="/index.do?method=left" flush="true"></jsp:include>
  <div id="mainpage">
    <div id="location"> 当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a href="<%=request.getContextPath()%>/journal.do?method=index">电子期刊</a></span><span><a href="#">${title}</a></span></div>
    <div id="inpageinfo"></div>
    <div class="journal_info">
    	<iframe src="<%=request.getContextPath()%>/upload/${folder}/" width="100%" height="100%" scrolling="auto" frameborder="0" marginheight="0" marginwidth="0"></iframe>
    </div>
  </div>  
</div>
<jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</body>
</html>
