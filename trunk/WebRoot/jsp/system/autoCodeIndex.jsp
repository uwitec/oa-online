<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>自动编号列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/general.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DivWindow.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
</head>

<body>
<div id="layout">
<jsp:include page="/index.do?method=top" flush="true"></jsp:include>
<div id="mainpage">
	<form action="" name="pageForm" method="post">
	<h2><img src="<%=request.getContextPath() %>/images/nav_putaway.png" />自动单号管理<span class="inpageinfo" id="errorInfo">${SystemInfo}</span></h2>
    <div id="list_table">
    	<ul class="list_tatle">
        	<li class="list_tab_autocodename">单号名称</li>
        	<li class="list_tab_autocodemark">单号标识</li>
        	<li class="list_tab_autocodetime">最后使用时间</li>
        	<li class="list_tab_autocodeformat">单号格式</li>
        	<li class="list_tab_status">状态</li>
        	<li class="list_tab_operate">操作</li>
    	</ul>
    	<c:forEach var="wac" items="${form.codeList}">
    	<ul<c:if test="${SystemSaveId==wac.codeId}"> class="list_save"</c:if>>
    		<li class="list_tab_autocodename"><span class="li_center">${wac.codeName}</span></li>
    		<li class="list_tab_autocodemark"><span class="li_left">${wac.markCode}</span></li>  	
        	<li class="list_tab_autocodetime"><span class="li_center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${wac.createDate}" /></span></li>
    		<li class="list_tab_autocodeformat"><span class="li_left">${wac.codeConfig}</span></li>
			<li class="list_tab_status"><span class="li_center"><c:if test="${wac.start==1}">启用</c:if><c:if test="${wac.start==0}">未启用</c:if></span></li>
			<li class="list_tab_operate">
    			<span class="li_center">
    				<a href="javascript:showAutoCode('${wac.codeId}');">编辑</a>	
    			</span>
    		</li>
    	</ul>
    	</c:forEach>        
    </div>
	</form>
</div>
</div>
<jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</body>
<script type="text/javascript">
function showAutoCode(id){
	var codeLoad = new DivWindow("codeLoad","自动单号编辑窗口",300,420,"","<%=request.getContextPath() %>/system.do?method=autoCodeLoad&wac.codeId="+id);
	codeLoad.addButton("确定",function(){
		var childdoc=document.frames[0].document;
		var codeConfig = childdoc.getElementById("codeConfig");
		var errMsg = childdoc.getElementById("errMsg");		
		errMsg.innerHTML="";
		if(codeConfig.value==""){
			errMsg.innerHTML="请编写自动单号生成格式！";
			return;
			}
		var reset = childdoc.getElementById("reset");
		if(reset.value==""){
			errMsg.innerHTML="请选择计数器重置规则！";
			return;
			}
		var count = childdoc.getElementById("count");
		if(!count.value.isNum()){
			errMsg.innerHTML="当前计数只能填写数值类型！";
			return;
			}
		if(parseInt(count.value)<=0){
			errMsg.innerHTML="当前计数只能填写大于零的数值类型！";
			return;
			}
		var step = childdoc.getElementById("step");
		if(!step.value.isNum()){
			errMsg.innerHTML="计数步长只能填写数值类型！";
			return;
			}
		if(parseInt(step.value)<=0){
			errMsg.innerHTML="计数步长只能填写大于零的数值类型！";
			return;
			}
		childdoc.forms['pageForm'].submit();		
		});
	codeLoad.addButton("取消",function(){
		document.forms['pageForm'].submit();
		});
	codeLoad.open();
}
</script>
</html>
	