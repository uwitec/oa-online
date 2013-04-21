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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/pic_management.css"/>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<title>相册列表页</title>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    	<form action="" name="pageForm" method="post">
    	<div id="mainpage">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>内部信息库</a></span><c:forEach var="class_" items="${currentList}"><span><a href="javascript:showClass('${class_.id}');">${class_.name}</a></span></c:forEach></div>
        	<div id="inpageinfo"></div>
            <div id="sort">
            	<ul>
	            <c:forEach var="class_" items="${classList}">      	
		      		<li><a href="javascript:showClass('${class_.id}');">${class_.name}</a></li>
		      	</c:forEach>
		      	</ul>
            </div>
			<div id="search">
            	<ul>
                	<li class="indent">模糊搜索：</li>
                    <li><input name="queryText" id="queryText" type="text" class="search_input" value="${form.queryText}"/></li>
                    <input name="queryType" type="hidden" value="${form.queryType}"/>
                </ul>
				<ul>
                	<li><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '0')>=0||form.queryType==null||form.queryType==''}">checked</c:if> value="0"/></li>
                    <li>相册编号</li>
                </ul>
                <ul>
                	<li class="indent"><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '1')>=0||form.queryType==null||form.queryType==''}">checked</c:if> value="1"/></li>
                    <li>相册标题</li>
                </ul>
                <ul>
                	<li class="indent"><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '2')>=0}">checked</c:if> value="2"/></li>
                    <li>相册描述</li>
                </ul>
                <ul>
                	<li class="indent"><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '3')>=0}">checked</c:if> value="3"/></li>
                    <li>图片名称</li>
                </ul>
                <ul>
                	<li class="indent">
                    	<button onclick="selectSubmit()" class="button_bg">搜索</button>
                    </li>
                </ul>
            </div>
            <div class="titalpic">图库列表：</div> 
            <input type="hidden" name="orderByName" id="orderByName" value="${form.orderByName}"/>
		    <input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/>
		    <input type="hidden" name="pic.classId" id="pic.classId" value="${form.pic.classId}"/>
            <div id="inpageinfo"></div>
            <div class="albums01">
	            <div id="albumslist">		    
	        	<c:forEach var="pic" items="${form.list}">
	            	<ul>
	                	<li class="album_bg">
	                		<a target="_blank" href="<%=request.getContextPath() %>/picture.do?method=showLoad&pic.id=${pic.id}" title="相册编号：${pic.code}&#13;所在分类：${pic.className}&#13;发布用户：${pic.creatorName}&#13;发布时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${pic.createDate}" />">
								<img src="<%=request.getContextPath() %>/upload.do?method=picDownload&fileID=${pic.cover}&w=90&h=90" onerror="this.src='<%=request.getContextPath() %>/images/noCover.gif'"/>
		                	</a>
	                	</li>
	                    <li class="album_title">
							<a name="picTitle" target="_blank" href="<%=request.getContextPath() %>/picture.do?method=showLoad&pic.id=${pic.id}" title="相册编号：${pic.code}&#13;所在分类：${pic.className}&#13;发布用户：${pic.creater}&#13;发布时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${pic.createDate}" />">${pic.title}</a>
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

//搜索函数
function selectSubmit(type){
	//var chinpageinfo = document.getElementById("inpageinfo");
	if(type==0){
		document.forms['pageForm'].reset();
		}
	else{
		if($ID("queryText").value.isEmpty()){
			showMsgbox("请填写模糊查询关键字！");
			return;
		}
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
	$ID("pic.classId").value=id;
	document.forms['pageForm'].pageNo.value=1;
	document.forms['pageForm'].submit();
}

titleInit('picTitle',7);
</script>
</html>