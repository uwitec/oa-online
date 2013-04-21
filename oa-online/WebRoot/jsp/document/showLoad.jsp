<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/new_general.css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/ClassDwr.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<title>文档展示页</title>
</head>
<body>
<div id="layout">
    <jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    	<div id="mainpage">
    		<form action="<%=request.getContextPath()%>/document.do?method=showIndex" name="pageForm" method="post">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a href="#">内部信息库</a></span></div>
            <h1>${form.docm.title}<c:if test="${form.docm.creater==user.userId}">【<a href="<%=request.getContextPath()%>/document.do?method=load&docm.id=${form.docm.id}">重新编辑</a>】</c:if> </h1>
            <h5><span>发布部门：${form.docm.depName}</span><span>发布时间：<fmt:formatDate pattern="yyyy-MM-dd" type="both" dateStyle="default" timeStyle="default" value="${form.docm.createDate}" /></span></h5>
            <input type="hidden" name="docm.classId" id="docm.classId" value="${form.docm.classId}"/>        	
			<input type="hidden" id="docm.id" name="docm.id" value="${form.docm.id}" />
			<input type="hidden" id="docm.files" name="docm.files" value="${form.docm.files}" />
			<input type="hidden" id="userisDownload" value="${user.permissionMap.isDownload}" />
            <div id="doc_text">
            	${form.docm.content}
            </div>
            <div id="attach"><span id="filesDiv">附件：</span></div>
            </form>
    		</div>
    	</div>
    <jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
<script type="text/javascript">
//文件列表初始化
var initFiles = function(){	
	if($ID("docm.files").value.length<4)
		return	
	var filesJson =eval('(' + $ID("docm.files").value + ')');
	for(var i=0;i<filesJson.length;i++){
		fileQueued(filesJson[i]);
	}	
}
//文件显示
var fileQueued = function(file){
	
	var fileObjA = document.createElement("a");	
	if($ID("userisDownload").value=="true"){
		fileObjA.href="<%=request.getContextPath()%>/upload.do?method=download&fileID="+file.id;
		fileObjA.target='_blank';
		fileObjA.title="点击下载:"+file.name;
	}
	fileObjA.innerHTML=file.name;
	$ID("filesDiv").appendChild(fileObjA);
}
//返回查询页
function backIndex(classId){	
	$ID("docm.classId").value=classId;
	document.forms['pageForm'].submit();
}
//分类初始化
var classInit = function(id){
	var take=function(list){
		var currentStr="";
		for(var i=0;i<list.length;i++){
			currentStr+="<span><a href=\"javascript:backIndex('"+list[i].id+"');\">"+list[i].name+"</a></span>";
		}
		$ID("location").innerHTML=$ID("location").innerHTML+currentStr;
	}
	ClassDwr.queryParent('Class_Document',id,take);
}
//***************************初始化*********************************
classInit('${form.docm.classId}');
initFiles();
</script>
</html>