<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/workplan.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<title>工作计划展示</title>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    	<div id="mainpage" class="show_detail">
			<div id="location">
                  	当前位置：<span class="fp"><a href="index.do?method=index">我的桌面</a></span><span><a href="#">个人中心</a></span><span><a href="workplan.do?method=index">工作计划管理</a></span><span><a href="#">${form.ucWorkPlan.title}</a></span>
		  	</div>
            <div class="paper">工作计划：</div>
            <div class="system_configuration_box" id="height_show">
			<div id="now_program_content">
			<div class="content_headbox"><a>${form.ucWorkPlan.title}</a><span class="time_box01">更新时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${form.ucWorkPlan.createDate}" /></span></div>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${form.ucWorkPlan.contentShow}</p>
			<p class="gray02">总结：<c:if test="${form.ucWorkPlan.workSummary==''}">/</c:if> ${form.ucWorkPlan.workSummaryShow}</p>
			<ul class="detail_inform01">
				<li>优先级：${form.ucWorkPlan.priorityStr}</li> 
				<li>状态：${form.ucWorkPlan.modeStr}</li>
				<li>任务时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${form.ucWorkPlan.startingTime}" /></li>
				<li>提醒时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${form.ucWorkPlan.remindTime}" /></li>
			</ul>                                                                 
            <div id="sureorcancel">
        		<button onclick="goback()" class="button_bg">关闭</button>
       		</div>
			</div>	
		</div>
    </div>   
</div>
<jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
<script type="text/javascript">
//返回列表
function goback(){
	window.location = "<%=request.getContextPath()%>/workplan.do?method=index";
}
</script>
</html>