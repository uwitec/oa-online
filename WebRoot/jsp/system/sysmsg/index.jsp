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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/SysMsgDWR.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<title>通知管理</title>
</head>
<body>
<div id="layout">
<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    	<div id="mainpage">
    	<form action="sysmsg.do?method=index" name="pageForm" method="post">
    		<input type="hidden" name="orderByName" id="orderByName" value="${form.orderByName}"/>
		    <input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/>
			<div id="location">当前位置：<span class="fp"><a href="index.do?method=index">我的桌面</a></span><span><a href="#">个人中心</a></span><span><a href="#">通知管理</a></span>
		   </div>
            <div id="search" class="search_b">
            	<ul>
                	<li class="tital">标题：</li>
                    <li><input name="sysMsg.title" type="text" class="search_input" value="${form.sysMsg.title}"/>
                    </li>
                </ul>
				<ul>
                	<li class="tital">类型：</li>
                  	<li>
	                  	<select name="sysMsg.sysType">
	                  		<option value="100">全部提醒</option>
		                  	<option value="0"<c:if test="${form.sysMsg.sysType==0}"> selected</c:if>>工作计划提醒</option>
		                  	<option value="1"<c:if test="${form.sysMsg.sysType==1}"> selected</c:if>>接单表提醒</option>
	                  	</select>
                  	</li>
                </ul>
				<ul class="receipt_time">
	                <li class="tital">接收时间：</li> 
	                <li>
	          			<input name="startTime" type="text" id="startTime" class="text_time" readonly  value="${form.startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
			          	-
			        	<input name="endTime" type="text" id="endTime" class="text_time" readonly value="${form.endTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
	       			</li>
       			</ul>
                <ul class="search_button"><button onclick="renovate()" class="button_bg">搜索</button>
                </ul>
            </div>
            <div class="index_pic">通知管理：</div> 
            <div id="inform_content">
			<div id="inpageinfo"></div>
			<div id="trend02">
					<div class="button_order_form">
          		 		<button id="chall" class="button_bg">全选</button>
         				<button id="delAll" class="button_bg">删除</button>
						<button class="button_other">其它操作</button>
					</div>
       		</div>       		
			<ul class="list_title mainpage_list_top">
	            	<li class="doc_code"></li>
					<li class="mailer" onClick="orderby(this)" id="isRead"><img src="images/mailer.gif" /></li>
					<li class="baoj"><img src="images/trumpet.gif" /></li>
	                <li class="inform_caption" onClick="orderby(this)" id="title">标题</li>
	                <li class="inform_time" onClick="orderby(this)" id="createDate">接收时间</li>             
	                <li class="inform_type" onClick="orderby(this)" id="sysClass">类型</li>
					<li class="inform_operate" id="operate01">操作</li>
            </ul>
            <div class="box">
            <c:forEach varStatus="idxStatus" var="sysMsg" items="${form.list}"> 
            <ul class="doc_list">
				<li class="inform_checkbox"><input name="ids" type="checkbox" value="${sysMsg.id}"/></li>
            	<li class="doc_code center">${idxStatus.index+1}</li>
				<li class="mailer center"><img <c:if test="${sysMsg.isRead==1}">src="images/mailer_open.png" title="已读取"</c:if><c:if test="${sysMsg.isRead==0}">src="images/mailer_close.png" title="未读取"</c:if> class="mailer_ico"/></li>
				<li class="baoj center"><img <c:if test="${sysMsg.grade==1}">src="images/baoj.gif" title="高优先级"</c:if> <c:if test="${sysMsg.grade==0}"> src="images/YJ.gif" title="低优先级"</c:if> class="mailer_ico"/></li>                
                <span class="<c:if test="${sysMsg.grade==1&&sysMsg.isRead==0}"> red_01</c:if> <c:if test="${sysMsg.grade==0&&sysMsg.isRead==0}"> blue_01</c:if>">
				<li class="inform_caption left"><a name="sysMsgTitle">${sysMsg.title}</a></li>
                <li class="inform_time center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${sysMsg.createDate}" /></li>
				<li class="inform_type center">${sysMsg.sysClassName}</li>
				</span>
				
               	<li class="inform_operate">
            		<span class="center" id="icoright">
						<a title="查看" href="javascript:showSysMsg('${sysMsg.id}');"><img src="images/look.png"/></a>  
						<a class="d_box" title="删除" href="javascript:delOnly('${sysMsg.id}');"><img src="images/delete.png"/></a>
					</span>
            	</li>
            </ul>
            </c:forEach>			
			</div>
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
		SysMsgDWR.delAll(idsValue,take);
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
			SysMsgDWR.delAll(idsValue,take);
			closeDivWindow("delale");
			});	
		delale.addButton("取消");	
		delale.open();
	}
}

//展示系统信息
function showSysMsg(id){
	
	SysMsgDWR.showLoad(id,function(obj){
		if(obj==undefined)return;		
		var showSysMsg = new DivWindow("showSysMsg",obj.title,350,200,"<div class='showSysMsgWin'>"+repstr(obj.content)+"</div>");		
		showSysMsg.addButton("标记未读",function(){
				var idsValue = [];
				idsValue[0] = id;
				SysMsgDWR.editRead(idsValue,function(){
					document.forms['pageForm'].submit();
					});
			});
		showSysMsg.addButton("删除消息",function(){
			delOnly(id);
			});
		
		if(!obj.url.isEmpty()){
			showSysMsg.addButton("查看详细",function(){
				window.location = "<%=request.getContextPath()%>/"+obj.url;
			});
			
		}

		
		showSysMsg.addButton("关闭消息",function(){
			document.forms['pageForm'].submit();
			});
		showSysMsg.open();
		
	});
}
function repstr(str)
{        
	var reg=new RegExp("\r\n","g");
  	return str.replace(reg,"<br>");
}
titleInit("sysMsgTitle",17);
</script>
</html>