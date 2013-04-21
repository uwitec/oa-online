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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/pic_management.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/PictureDWR.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<title>相册管理列表页</title>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    	<div id="mainpage">
    		<form action="" name="pageForm" method="post">
            <input type="hidden" name="orderByName" id="orderByName" value="${form.orderByName}"/>
		    <input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/>
		    <input type="hidden" name="pic.classId" id="pic.classId" value="${form.pic.classId}"/>
        	<div id="location">
        			当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>资料管理</a></span>
        	<c:forEach var="class_" items="${currentList}">
      			<span><a href="javascript:showClass('${class_.id}');">${class_.name}</a></span>
		    </c:forEach>
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
            <div class="index_pic">图库管理列表：</div>
            <div id="inpageinfo"></div>
            <div id="operat_title_pic">
           		<button class="button_add" id="addShow" >新建</button>
           	</div>   
            <div id="albums_management_list">
        	<c:forEach var="pic" items="${form.list}">
            	<ul id="${pic.id}_bg">
            		<li class="album_management_checkbox"><input onclick="clickColor(this)" type="checkbox" name="ids" value="${pic.id}"/></li>
                	<li class="album_management_bg">
                		<a href="<%=request.getContextPath() %>/picture.do?method=load&pic.id=${pic.id}" title="相册编号：${pic.code}&#13;所在分类：${pic.className}&#13;发布用户：${pic.creatorName}&#13;发布时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${pic.createDate}" />">
							<img src="<%=request.getContextPath() %>/upload.do?method=picDownload&fileID=${pic.cover}&w=80&h=80" onerror="this.src='<%=request.getContextPath() %>/images/noCover.gif'"/>
	                	</a>
                	</li>
                    <li class="album_management_title">
						<a name="picTitle" href="<%=request.getContextPath() %>/picture.do?method=load&pic.id=${pic.id}" title="相册编号：${pic.code}&#13;所在分类：${pic.className}&#13;发布用户：${pic.creatorName}&#13;发布时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${pic.createDate}" />">${pic.title}</a>
						<button title="删除" onclick="delOnly('${pic.id}')"></button>
					</li>
                </ul>
            </c:forEach>
            </div>
            <div id="page"><%@ include file="/inc/pagecontrol.inc"%></div>
            </form>
            <div class="button_order_form_pic">
	          		 		<button id="chall" class="button_bg">全选</button>
	         				<button id="delAll" class="button_bg">删除</button>
			</div>
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

//全选事件
if($ID("chall")){
	document.getElementById('chall').onclick=function(){
		var ids = document.getElementsByName('ids');
		if(this.name==""||this.name=="2")
			this.name="1";
		else
			this.name="2"
		for ( var i = 0; i < ids.length; i++) {
			ids[i].checked = (this.name=="1"?true:false);
			clickColor(ids[i]);
		}
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
		PictureDWR.delAll(idsValue,take);
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
			PictureDWR.delAll(idsValue,take);
			closeDivWindow("delale");
			});	
		delale.addButton("取消");	
		delale.open();
	}
}
//添加跳转
if($ID("addShow")){
	$ID("addShow").onclick=function(){
		window.location = "<%=request.getContextPath()%>/picture.do?method=load";
	}
}
//根据选中情况，更换背景色
function clickColor(clickObj){
	if(clickObj.checked){
		$ID(clickObj.value+"_bg").style.backgroundColor="lightslategray";
	}
	else{
		$ID(clickObj.value+"_bg").style.backgroundColor="gainsboro";
	}
}

titleInit('picTitle',7);
</script>
</html>