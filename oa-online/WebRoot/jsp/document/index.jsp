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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/doc_management.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/DocumentDWR.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<title>文档管理列表页</title>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    	<div id="mainpage">
    		<form action="" name="pageForm" method="post">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>资料管理</a></span>
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
            	<ul>
                	<li>模糊搜索：</li>
                    <li><input name="queryText" id="queryText" type="text" class="search_input" value="${form.queryText}"/></li>
                    <input name="queryType" type="hidden" value="${form.queryType}"/>
                </ul>
				<ul>
                	<li><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '0')>=0||form.queryType==null||form.queryType==''}">checked</c:if> value="0"/></li>
                    <li>文档编号</li>
                </ul>
                <ul>
                	<li><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '1')>=0||form.queryType==null||form.queryType==''}">checked</c:if> value="1"/></li>
                    <li>文档标题</li>
                </ul>
                <ul>
                	<li><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '2')>=0}">checked</c:if> value="2"/></li>
                    <li>文档描述</li>
                </ul>
                <ul>
                	<li><input name="queryTypeCheck" type="checkbox" <c:if test="${fn:indexOf(form.queryType, '3')>=0}">checked</c:if> value="3"/></li>
                    <li>文件名称</li>
                </ul>
                <ul>
	                <li >发布日期：</li> 
	                <li>
	          			<input name="startTime" class="search_input" type="text" id="startTime" readonly  value="${form.startTime}" onclick="WdatePicker()" />
	          			-
	          			<input name="endTime" class="search_input" type="text" id="endTime" readonly value="${form.endTime}" onclick="WdatePicker()" />
	       			</li>
       			</ul>
                <ul>
                	<li>
                    	<button onclick="selectSubmit()" class="button_bg">搜索</button>
                    </li>
                </ul>
            </div>
            <div class="index_pic">文档管理列表：</div>
            <div id="inpageinfo"></div>
           	<div id="operat_title_bottom">
           	<button class="button_add" id="addShow" >新建</button>  
           	</div>
            <input type="hidden" name="orderByName" id="orderByName" value="${form.orderByName}"/>
		    <input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/>
		    <input type="hidden" name="docm.classId" id="docm.classId" value="${form.docm.classId}"/>
            <div id="doc_content">
            <ul class="list_title">
            	<li class="manag_checkbox"></li>
            	<li class="doc_code" onClick="orderby(this)" id="code">编号</li>
                <li class="doc_name" onClick="orderby(this)" id="title">文档名称</li>
                <li class="doc_manag_sort" onClick="orderby(this)" id="className">所属分类</li>             
                <li class="manag_uploaddept" onClick="orderby(this)" id="depName">上传部门</li>
                <li class="doc_manag_sort" onClick="orderby(this)" id="creatorName">上传人员</li>
                <li class="doc_manag_sort" onClick="orderby(this)" id="createDate">上传时间</li>
                <li class="uploaddate">操作</li>
            </ul>
            <c:forEach var="docm" items="${form.list}">
            <ul class="doc_manag_list">
	          	<li class="manag_checkbox">
	           		<input name="ids" type="checkbox" value="${docm.id}"/>
	          	</li>
            	<li class="doc_code"><span class="center">${docm.code}</span></li>
                <li class="doc_name"><span class="left"><a class="retract" name="docTitle">${docm.title}</a></span></li>
                <li class="doc_manag_sort"><span class="center"><a class="retract" name="docClass">${docm.className}</a></span></li>                
                <li class="manag_uploaddept"><span class="center">${docm.depName}</span></li>
                <li class="doc_manag_sort center"><span class="center">${docm.creatorName}</span></li>
                <li class="doc_manag_sort center"><span class="center"><fmt:formatDate pattern="yyyy-MM-dd" type="both" dateStyle="default" timeStyle="default" value="${docm.createDate}" /></span></li>
                <li class="uploaddate">
                	<span class="center">
            			<a title="阅览" href="<%=request.getContextPath()%>/document.do?method=showLoad&docm.id=${docm.id}" target="_blank"><img src="<%=request.getContextPath()%>/images/look.png"/></a> 
            			<a title="编辑" class="d_box" href="<%=request.getContextPath()%>/document.do?method=load&docm.id=${docm.id}"><img src="<%=request.getContextPath()%>/images/edit_mini.png"/></a>
            			<a title="删除" class="d_box" href="javascript:delOnly('${docm.id}');"><img src="<%=request.getContextPath()%>/images/delete.png"/></a>
            		</span>
            	</li>
            </ul>
             </c:forEach>
             </div>
            <div id="page"><%@ include file="/inc/pagecontrol.inc"%></div>
	            <button id="chall" class="button_bg">全选</button>
		        <button id="delAll" class="button_bg">删除</button>
          </form>
    	</div>
    </div>
    <jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
<script type="text/javascript">
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
//删除一条信息
function delOnly(objId){
	var chinpageinfo = document.getElementById("inpageinfo");
	var idsValue = [];
	idsValue[0] = objId;
	var take=function(data)
	{
		if(data){
			document.forms['pageForm'].submit();
		}
		else{
			chinpageinfo.innerHTML="当前项目不可删除！";
		}
	}
	var delale = new DivWindow("delale","系统提示",200,90,"确定要删除所选项目？");
	delale.addButton("确定",function(){
		DocumentDWR.delAll(idsValue,take);
		closeDivWindow("delale");
		});	
	delale.addButton("取消");	
	delale.open();
}

//批量删除
if(document.getElementById('delAll')){
	document.getElementById('delAll').onclick=function(){
		var chinpageinfo = document.getElementById("inpageinfo");
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
			chinpageinfo.innerHTML="请选择要删除的项目！";
			return;
			}
		var take=function(data)
		{
			
			document.forms['pageForm'].submit();
				
		}
	
		var delale = new DivWindow("delale","系统提示",200,90,"确定要删除所选项目？");
		delale.addButton("确定",function(){
			DocumentDWR.delAll(idsValue,take);
			closeDivWindow("delale");
			});	
		delale.addButton("取消");	
		delale.open();
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
//添加跳转
if($ID("addShow")){
	$ID("addShow").onclick=function(){
		window.location = "<%=request.getContextPath()%>/document.do?method=load";
	}
}
titleInit('docTitle',17);
titleInit('docClass',6);
</script>
</html>