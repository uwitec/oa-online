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
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DivWindow.js"></script>
<title>文档列表页</title>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    	<div id="mainpage">
    		<form action="" name="pageForm" method="post">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>内部信息库</a></span>
        	<c:if test="${currentList!=null}">
		      <c:forEach var="class_" items="${currentList}">      	
		      	<span><a href="javascript:showClass('${class_.id}');">${class_.name}</a></span>
		      </c:forEach>
		    </c:if>
		    </div>
            <div id="sort">
	            <ul>
	            <c:forEach var="class_" items="${classList}">      	
		      		<li><a href="javascript:showClass('${class_.id}');">${class_.name}</a></li>
		      	</c:forEach>
		      	</ul>
            </div>
            <div id="search">
            	<ul class="search_location">
                	<li>模糊搜索：</li>
                    <input name="queryText" id="queryText" type="text" class="search_input" value="${form.queryText}"/>
                    <input name="queryType" type="hidden" value="${form.queryType}"/>
                </ul>
				<ul>
                	<li class="ch_box"><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '0')>=0||form.queryType==null||form.queryType==''}">checked</c:if> value="0"/></li>
                    <li>文档编号</li>
                </ul>
                <ul>
                	<li class="ch_box"><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '1')>=0||form.queryType==null||form.queryType==''}">checked</c:if> value="1"/></li>
                    <li>文档标题</li>
                </ul>
                <ul>
                	<li class="ch_box"><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '2')>=0}">checked</c:if> value="2"/></li>
                    <li>文档描述</li>
                </ul>
                <ul>
                	<li class="ch_box"><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '3')>=0}">checked</c:if> value="3"/></li>
                    <li>文件名称</li>
                </ul>
                <ul class="search_location">
                	<li>
                    	<button onclick="selectSubmit()" class="button_bg search_button">搜索</button>
                    </li>
                </ul>
            </div>
            <div class="titalpic">文档列表：</div> 
            <div id="inpageinfo"></div>
            <input type="hidden" name="orderByName" id="orderByName" value="${form.orderByName}"/>
		    <input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/>
		    <input type="hidden" name="docm.classId" id="docm.classId" value="${form.docm.classId}"/>
            <div id="doc_content">
            <ul class="list_title">
            	<li class="doc_code01" onClick="orderby(this)" id="code">编号</li>
                <li class="doc_name" onClick="orderby(this)" id="title">文档名称</li>
                <li class="doc_sort" onClick="orderby(this)" id="className">所属分类</li>             
                <li class="uploaddept" onClick="orderby(this)" id="depName">上传部门</li>
                <li class="userid" onClick="orderby(this)" id="creatorName">上传人员</li>
                <li class="uploaddate" onClick="orderby(this)" id="createDate">上传时间</li>
            </ul>
            <c:forEach var="docm" items="${form.list}">
            <ul class="doc_list" onClick="showInfo('${docm.id}')">
            	<li class="doc_code01"><span class="center">${docm.code}</span></li>
                <li class="doc_name"><span class="left"><a class="retract" name="docTitle">${docm.title}</a></span></li>
                <li class="doc_sort"><span class="center">${docm.className}</span></li>                
                <li class="uploaddept"><span class="center">${docm.depName}</span></li>
                <li class="userid"><span class="center">${docm.creatorName}</span></li>
                <li class="uploaddate"><span class="center"><fmt:formatDate pattern="yyyy-MM-dd" type="both" dateStyle="default" timeStyle="default" value="${docm.createDate}" /></span></li>
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
//搜索函数
function selectSubmit(type){
	//var chinpageinfo = document.getElementById("inpageinfo");
	if(type==0){
		document.forms['pageForm'].reset();
		}
	else{
		var queryTypeCheck = $N("queryTypeCheck");
		var queryType = $ID("queryType");
		queryType.value="";
		for(var i=0;i<queryTypeCheck.length;i++){
			if(queryTypeCheck[i].checked){
				queryType.value+=queryTypeCheck[i].value;
			}
		}
		if($ID("queryType").value.isEmpty()){
			showMsgbox("请选择查询方式！");
			return;
		}
		//var storageInStartTime = document.getElementById("startTime");
		//var storageInEndTime = document.getElementById("endTime");
		//if(!datecompare(storageInStartTime.value, storageInEndTime.value)){
			//document.getElementById("inpageinfo").innerHTML="日期查询错误！须左侧日期小于右侧日期！";
		//	return;
		//	}
		document.forms['pageForm'].pageNo.value=1;
	}
	document.forms['pageForm'].submit();
}
//分类更改
function showClass(id){
	$ID("docm.classId").value=id;
	document.forms['pageForm'].pageNo.value=1;
	document.forms['pageForm'].submit();
}

//展示文档
function showInfo(id){
	window.location = "<%=request.getContextPath()%>/document.do?method=showLoad&docm.id="+id;
}
titleInit('docTitle',17);
</script>
</html>