<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工作计划明细</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/workplan.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
	<div id="mainpage">
	<div id="location">
                  当前位置：<span class="fp"><a href="index.do?method=index">我的桌面</a></span><span><a href="#">个人中心</a></span><span><a href="workplan.do?method=index">工作计划管理</a></span><span><a href="#">工作计划信息</a></span>
		   </div>
            <div class="paper">工作计划信息：</div>
            <div id="inpageinfo"></div>
			<div id="now_program_content" class="system_configuration_box">
				<form action="<%=request.getContextPath()%>/workplan.do?method=save" name="pageForm" method="post">
				<input type="hidden" name="ucWorkPlan.id" value="${form.ucWorkPlan.id}"/>
				<div id="all_input_box">
					<ul>
						<li><span class="inpageinfo_f"><span class="mustRed">*</span>标题： </span><input type="text" name="ucWorkPlan.title" id="ucWorkPlan.title" value="${form.ucWorkPlan.title}"/>
						</li>
						<li><span class="inpageinfo_f2"><span class="mustRed">*</span>优先级：</span><select name="ucWorkPlan.priority">
								<option value="1" <c:if test="${form.ucWorkPlan.priority==1}">selected</c:if>>高</option>
								<option value="2" <c:if test="${form.ucWorkPlan.priority==2}">selected</c:if>>中</option>
								<option value="4" <c:if test="${form.ucWorkPlan.priority==4}">selected</c:if>>低</option>
							</select>							
						</li>
					</ul>
					<ul>
						<li class="time01">
							<span class="inpageinfo_f"><span class="mustRed">*</span>任务时间：</span><input name="ucWorkPlan.startingTimeStr" type="text" id="ucWorkPlan.startingTimeStr" class="text_time" readonly value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${form.ucWorkPlan.startingTime}" />" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
						</li>
						<li class="time02">
							<span class="inpageinfo_f">提醒时间：</span><input name="ucWorkPlan.remindTimeStr" type="text" id="ucWorkPlan.remindTimeStr" class="text_time" readonly value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${form.ucWorkPlan.remindTime}" />" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
						</li>
					</ul>
				</div>
				<div id="remark_box">
					<ul>
						<li>
							<span class="inpageinfo_f">内容：</span>
						    <textarea rows="2" name="ucWorkPlan.content" id="ucWorkPlan.content">${form.ucWorkPlan.content}</textarea>
						</li>
				        <li>
							<span class="inpageinfo_f">总结：</span>
						    <textarea rows="2" name="ucWorkPlan.workSummary">${form.ucWorkPlan.workSummary}</textarea>
						</li>
				    </ul>
				</div>
				<div id="sureorcancel">
          			 	<button onclick="saveSubmit()" class="button_bg">保存</button>
         				<button onclick="goback('<%=request.getContextPath()%>/workplan.do?method=index')" class="button_bg">退出</button>
        		</div>
        		</form>
			</div>	
		</div>
    </div>
<jsp:include page="/index.do?method=foot" flush="true"></jsp:include> 
</div>
</body>
<script type="text/javascript">
//表单保存
function saveSubmit(){
	if($ID("ucWorkPlan.title").value.isEmpty()){
		showMsgbox("标题不能为空！");
		return;
	}

	if($ID("ucWorkPlan.startingTimeStr").value.isEmpty()){
		showMsgbox("任务时间不能为空！");
		return;
	}

	if($ID("ucWorkPlan.content").value.isEmpty()){
		showMsgbox("任务内容不能为空！");
		return;
	}	
	document.forms['pageForm'].submit();
}
</script>
</html>
