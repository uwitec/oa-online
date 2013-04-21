<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/workplan.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/workplan.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/WorkPlanDWR.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<title>工作计划管理</title>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    	<div id="mainpage">
    	<form action="" name="pageForm" method="post">
			<div id="location">
                  	当前位置：<span class="fp"><a href="index.do?method=index">我的桌面</a></span><span><a href="#">个人中心</a></span>
                  	<span><a href="workplan.do?method=index">工作计划管理</a></span>
		   	</div>     
            <div id="search">
            	<ul>
                	<li>工作计划标题：</li>
                    <li><input name="ucWorkPlan.title" id="ucWorkPlan.title" type="text" class="search_input" value="${form.ucWorkPlan.title}"/>
                    </li>
                </ul>
				<ul class="receipt_time">
	                <li class="tital">
						<select name="timeType">
							<option>按时间查询</option>
							<option value="startingTime" <c:if test="${form.timeType=='startingTime'}">selected</c:if>>任务时间</option>
							<option value="remindTime" <c:if test="${form.timeType=='remindTime'}">selected</c:if>>提醒时间</option>
							<option value="createDate" <c:if test="${form.timeType=='createDate'}">selected</c:if>>创建时间</option></select>：</li> 
	                <li>
	          			<input name="startTime" type="text" id="startTime" class="text_time" readonly  value="${form.startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
			          	-
			        	<input name="endTime" type="text" id="endTime" class="text_time" readonly value="${form.endTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
	       			</li>
	       			<li class="content_jh"><button onclick="renovate()" class="button_bg">搜索</button></li>
       			</ul>
            </div>
            <div class="index_pic">工作计划管理：</div>
            <input type="hidden" name="orderByName" id="orderByName" value="${form.orderByName}"/>
		    <input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/>
            <div id="doc_content">
				<div id="inpageinfo"></div>			
				<div id="trend02">
						<div class="button_order_form">
	          		 		<button id="chall" class="button_bg">全选</button>
	         				<button id="delAll" class="button_bg">删除</button>
							<!-- <button class="button_other" onclick="showMenu()">其它操作</button> -->
						</div>
						<button class="button_add" onclick="add()" >新建</button>
				</div>
	            <ul class="list_title">
	            	<li class="manag_checkbox"></li>
	            	<li class="doc_code_work"></li>
	                <li class="doc_name" onClick="orderby(this)" id="title">工作计划标题</li>
	                <li class="people" onClick="orderby(this)" id="startingTime">任务时间</li>             
	                <li class="predict_time" onClick="orderby(this)" id="priority">优先级</li>
	                <li class="people" onClick="orderby(this)" id="creatorName">提醒时间</li>
					<li class="time" onClick="orderby(this)" id="mode">状态</li>
					<li class="operate_work">操作</li>
	            </ul>
	            <c:forEach varStatus="idxStatus" var="wp" items="${form.list}"> 
	            <ul class="doc_list">
					<li class="manag_checkbox"><c:if test="${wp.creater==user.userId}"><input name="ids" type="checkbox" value="${wp.id}"/></c:if></li>
	            	<li class="doc_code_work"><span class="center">${idxStatus.index+1}</span></li>
	                <li class="doc_name left"><a name="wpTitle"><c:if test="${wp.creater!=user.userId}">【${wp.creatorName}】</c:if>${wp.title}</a></li>
	                <li class="people center"><span class="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${wp.startingTime}" /></span></li>
					<li class="predict_time"><span class="center">${wp.priorityStr}</span></li>
	                <li class="people center"><span class="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${wp.remindTime}" /></span></li>
	                <li class="time center" <c:if test="${wp.creater==user.userId}">onclick="editModeOnly('${wp.id}')"</c:if>>${wp.modeStr}</li>
					<li class="operate_work">
               			<span class="left">
							<a title="查看" class="a_box" href="workplan.do?method=show&ucWorkPlan.id=${wp.id}"><img src="images/look.png"/></a>
							<c:if test="${wp.creater==user.userId}">
           					<a class="d_box" title="编辑" href="workplan.do?method=load&ucWorkPlan.id=${wp.id}"><img src="<%=request.getContextPath()%>/images/edit_mini.png"/></a> 
							<a class="d_box" title="删除" href="javascript:delOnly('${wp.id}');"><img src="images/delete.png"/></a>
							</c:if>
           				</span>
	            	</li>
	            </ul>
	            </c:forEach>
			</div>
            <div id="page"><%@ include file="/inc/pagecontrol.inc"%></div>
			</form>
    	</div>
    </div>
<jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
<script type="text/javascript">
//排序操作表单提交
function orderby(obj) {
	//状态更改以及排序设置
	var str = obj.innerHTML;
	if (obj.value == 1) {
		obj.value = 2;
		document.getElementById("orderByOrder").value = 2;
	} else {
		obj.value = 1;
		document.getElementById("orderByOrder").value = 1;
	}
	document.getElementById("orderByName").value = obj.id;
	document.forms['pageForm'].pageNo.value = 1;
	document.forms['pageForm'].submit();
}
orderByInit();
//初始化排序信息
function orderByInit() {
	var orderByName = document.getElementById("orderByName").value;
	var orderByOrder = document.getElementById("orderByOrder").value;
	if (orderByName.length < 1) {
		return;
	}
	var orderObj = document.getElementById(orderByName);
	orderObj.value = parseInt(orderByOrder);
	if (orderObj.value == 1) {
		orderObj.innerHTML = orderObj.innerHTML + "↓";
	} else if (orderObj.value == 2) {
		orderObj.innerHTML = orderObj.innerHTML + "↑";
	}
}
//全选事件
document.getElementById('chall').onclick=function(){
	var ids = document.getElementsByName('ids');
	if(this.name==""||this.name=="2")
		this.name="1";
	else
		this.name="2"
	for ( var i = 0; i < ids.length; i++) {
		ids[i].checked = (this.name=="1"?true:false);
	}
}
//搜索函数
function renovate(type){
	if(type==0){
		document.forms['pageForm'].reset();
		}
	else{
		var storageInStartTime = document.getElementById("startTime");
		var storageInEndTime = document.getElementById("endTime");
		if(!datecompare(storageInStartTime.value, storageInEndTime.value)){
			showMsgbox("日期查询错误！须左侧日期小于右侧日期！");
			return;
			}
		document.forms['pageForm'].pageNo.value=1;
	}
	document.forms['pageForm'].submit();
}
//删除一条信息
function delOnly(objId){
	var idsValue = [];
	idsValue[0] = objId;
	var take=function(data)
	{
		if(data){
			document.forms['pageForm'].submit();
		}
		else{
			showMsgbox("当前项目不可删除！");
		}
	}
	var delale = new DivWindow("delale","系统提示",200,90,"确定要删除所选项目？");
	delale.addButton("确定",function(){
		WorkPlanDWR.delAll(idsValue,take);
		closeDivWindow("delale");
		});	
	delale.addButton("取消");	
	delale.open();
}

//批量删除
if(document.getElementById('delAll')){
	document.getElementById('delAll').onclick=function(){
		//DWR验证
		var ids = document.getElementsByName('ids');
		//将Values制作成数组
		var idsValue = [];
		var idsValuelength=0;
		for( var i = 0; i < ids.length; i++ )
		{
			if ( ids[i].checked ){
				idsValue[idsValuelength] = ids[i].value;
				idsValuelength++;
			}
		}
		if(idsValuelength==0){
			showMsgbox("请选择要删除的项目！");
			return;
			}
		var take=function(data)
		{
			
			document.forms['pageForm'].submit();
				
		}
	
		var delale = new DivWindow("delale","系统提示",200,90,"确定要删除所选项目？");
		delale.addButton("确定",function(){
			WorkPlanDWR.delAll(idsValue,take);
			closeDivWindow("delale");
			});	
		delale.addButton("取消");	
		delale.open();
	}
}
//添加工作计划
function add(){
	window.location = "<%=request.getContextPath()%>/workplan.do?method=load";
}

//更改一条信息状态
function editModeOnly(objId){
	var idsValue = [];
	idsValue[0] = objId;
	var take=function(data)
	{
		if(data){
			document.forms['pageForm'].submit();
		}
		else{
			showMsgbox("当前项目不可更改！");
		}
	}
	var editType = new DivWindow("editType","系统提示",200,90,"确定要更改所选项目的状态？");
	editType.addButton("确定",function(){
		WorkPlanDWR.editModeAll(idsValue,null,take);
		closeDivWindow("editType");
		});	
	editType.addButton("取消");	
	editType.open();
}

titleInit("wpTitle",13);
</script>
</html>