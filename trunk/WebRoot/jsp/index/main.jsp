<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/home.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/SysMsgDWR.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<title>我的桌面</title>
</head>
<body>
<div id="layout">
  <jsp:include page="/index.do?method=head" flush="true"></jsp:include>
  <div id="mid">
    <jsp:include page="/index.do?method=left" flush="true"></jsp:include>
	<div id="inform_home">
	<form action="" name="pageForm" method="post">
		<div class="inform_home01">
		   
            <div id="inpageinfo">${rstMsg}</div>
            <script type="text/javascript">
              var rstMsg = "${rstMsg}" ;
              if(rstMsg!=null && rstMsg!="")
                 document.getElementById("inpageinfo").style.display= "block" ;
            </script>
			<span class="title_14px">通知(${form.informCount})<a title="查看所有提醒" href="sysmsg.do?method=index"><img src="images/more.gif" />更多</a></span>
			<div class="inform_home_content">
				<ul class="home_list_title">
					<li class="left_noborder"><img src="images/mailer.gif" /></li>
					<li class="list_trumpet"><img src="images/trumpet2.gif" /></li>
					<li class="home_list_time">接收时间</li>
					<li class="titlehome01"><span class="spacing">标题</span></li>
					<li class="right_noborder"><span class="spacing">类型</span></li>
				</ul>
				<div class="box">				
				<c:forEach var="sysMsg" items="${form.sysMsgList}"> 
				<ul onclick="showSysMsg('${sysMsg.id}')" class="home_list9<c:if test="${sysMsg.grade==1&&sysMsg.isRead==0}"> red</c:if> <c:if test="${sysMsg.grade==0&&sysMsg.isRead==0}"> blue</c:if>">
					<li class="left_noborder"><img <c:if test="${sysMsg.isRead==1}">src="images/mailer_open.png" title="已读取"</c:if><c:if test="${sysMsg.isRead==0}">src="images/mailer_close.png" title="未读取"</c:if>/></li>
					<li class="list_trumpet"><img <c:if test="${sysMsg.grade==1}">src="images/baoj.gif" title="高优先级"</c:if> <c:if test="${sysMsg.grade==0}"> src="images/YJ.gif" title="低优先级"</c:if>/></li>
					<li class="home_list_time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${sysMsg.createDate}" /></li>
					<li class="titlehome01 left"><a name="sysMsgTitle">${sysMsg.title}</a></li>
					<li class="right_noborder left"><a name="sysClassName">${sysMsg.sysClassName}</a></li>
				</ul>
				</c:forEach>
				</div>			
			</div>
	</div>
	<div class="inform_home01">
			<span class="title_14px">工作计划提醒(${form.planCount})<a title="查看所有工作计划提醒" href="sysmsg.do?method=index&sysMsg.sysType=0"><img src="images/more.gif" />更多</a></span>
			<div class="inform_home_content">
				<ul class="home_list_title">
					<li class="left_noborder"><img src="images/mailer.gif" /></li>
					<li class="list_trumpet"><img src="images/trumpet2.gif" /></li>
					<li class="home_list_time">接收时间</li>
					<li class="titlehome01"><span class="spacing">标题</span></li>
					<li class="right_noborder">优先级</li>
				</ul>
				<div id="box" class="box">
				<c:forEach var="sysMsg" items="${form.sysMsgListWork}"> 
				<ul onClick="showSysMsg('${sysMsg.id}')" class="home_list9<c:if test="${sysMsg.grade==1&&sysMsg.isRead==0}"> red</c:if> <c:if test="${sysMsg.grade==0&&sysMsg.isRead==0}"> blue</c:if>">
					<li class="left_noborder"><img <c:if test="${sysMsg.isRead==1}">src="images/mailer_open.png" title="已读取"</c:if><c:if test="${sysMsg.isRead==0}">src="images/mailer_close.png" title="未读取"</c:if>/></li>
					<li class="list_trumpet"><img <c:if test="${sysMsg.grade==1}">src="images/baoj.gif" title="高优先级"</c:if> <c:if test="${sysMsg.grade==0}"> src="images/YJ.gif" title="低优先级"</c:if>/></li>
					<li class="home_list_time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${sysMsg.createDate}" /></li>
					<li class="titlehome01 left"><a name="sysMsgTitle" class="title">${sysMsg.title}</a></li>
					<li class="right_noborder left">${fn:substring(sysMsg.sysClassName, 7,13)} </li>
				</ul>
				</c:forEach>
				</div>
			</div>
	</div>
	</form>
</div>
</div>
<jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
<script type="text/javascript">
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

function repstr(str)
{        
	var reg=new RegExp("\r\n","g");
  	return str.replace(reg,"<br>");
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
$(function(){
	titleInit("sysMsgTitle",14);
	titleInit("sysClassName",7);
})

</script>
</html>
