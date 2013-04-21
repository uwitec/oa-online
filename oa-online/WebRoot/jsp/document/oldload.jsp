<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文档信息</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/dtree.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dtreeUTF-8.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DivWindow.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/swfupload/swfupload.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/DocumentDWR.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/ClassDwr.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/System.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>

</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=top" flush="true"></jsp:include>
	<div id="mainpage">
		<h2>文档信息<span id="inpageinfo"></span></h2>
		<ul class="basicinfo">
			<h3 id="current"></h3>
			<h3><c:if test="${user.permissionMap.isAddNewClassDocument}">
				<a title="添加分类" href="javascript:addClass();">
					<img src="<%=request.getContextPath()%>/images/add.png" />
				</a>&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				<span id="classChild"></span>
			</h3>
			<form action="<%=request.getContextPath() %>/document.do?method=save" name="pageForm" method="post">
			<input type="hidden" id="docm.id" name="docm.id" value="${form.docm.id}" />
			<input type="hidden" id="docm.classId" name="docm.classId" value="${form.docm.classId}" />
			<input type="hidden" id="docm.className" name="docm.className" value="${form.docm.className}" />
			<input type="hidden" id="docm.files" name="docm.files" value="${form.docm.files}" />
        	<input type="hidden" id="docm.visible" name="docm.visible" value="${form.docm.visible}" />        	
        	<ul>
            	<li class="li_title">文档编号：</li>
            	<li><input <c:if test="${form.docm.autoCode||form.docm.id!=null}">readonly</c:if> class="text_input" type="text" name="docm.code" id="docm.code" value="${form.docm.code}"/></li>
            </ul>
            <ul>
            	<li class="li_title">标题：</li>
            	<li><input class="text_input" type="text" name="docm.title" id="docm.title" value="${form.docm.title}"/></li>
            </ul>
            <FCK:editor instanceName="docm.content" toolbarSet="FHI" height="300" width="700" >
				<jsp:attribute name="value">${form.docm.content}</jsp:attribute>
			</FCK:editor>
            </form>
        </ul>
        <div class="roleTree"><h3>可见角色</h3><div id="roleTree"></div></div>
       <div class="swfUpLoad">
       		<h3>文件上传</h3>
           	<div class="selectUploadButton"><span id="spanButtonPlaceHolder"></span></div>
           	<button onClick="swfu.startUpload()">开始上传</button>
           	<button onClick="swfu.stopUpload()">暂停上传</button>
           	<div class="filesDiv" id="filesDiv"></div>
        </div>
        <span class="line"></span>
        <ul class="btnlist">
        	<button onclick="goback()" class="Btn"><span>取消</span></button>
        	<button onclick="saveSubmit()" class="Btn"><span>发布信息</span></button>
        </ul>
		</div>        
	</div>
</div>
</body>
<script type="text/javascript">
//添加分类
function addClass(){
	var addClass = new DivWindow("addClass","分类添加",200,120,"分类名称：<input class='text_input' type='text' id='className' />");
	addClass.addButton("确定",function(){
		var classification={};
		classification.name=$ID("className").value;
		if($ID("className").value.isEmpty()){
			return;
		}
		classification.pid=$ID("docm.classId").value;
		classification.creater=$ID("user.userId").value;
		ClassDwr.saveClass(classification,function(ret){
				if(ret){
					classInit($ID("docm.classId").value);
					closeDivWindow("addClass");
				}
			})		
		});	
	addClass.addButton("取消");	
	addClass.open();
}
//取消编辑返回列表
function goback(){
	window.location="<%=request.getContextPath() %>/document.do?method=index";
}

//选角色方法
function selectRole(obj){	
	var roles=$N("roles");
	for(var i=0;i<roles.length;i++){
		if(roles[i].getAttribute("pid")==obj.value){
			roles[i].checked=false;
			roles[i].disabled=obj.checked||obj.disabled;
			selectRole(roles[i]);
		}		
	}	
}
var roleDtree;
//角色叔初始化
function showRoleTree(){
	roleDtree=new dTree('roleDtree','<%=request.getContextPath()%>');
	<c:forEach items="${form.roleList}" var="role">
	roleDtree.add('${role.id}','${role.pid}','<input onclick="selectRole(this)" type="checkbox" name="roles" pid="${role.pid}" value="${role.id}"/>${role.roleName}');
	</c:forEach>
	$ID("roleTree").innerHTML=roleDtree.toString();
}
//选择角色初始化
function selectRoleInit(){
	var visibles=$ID("docm.visible").value.split(",");
	var roles=$N("roles");
	for(var j=0;j<visibles.length;j++){
		for(var i=0;i<roles.length;i++){
			if(roles[i].value==visibles[j]){
				roles[i].checked=true;
				selectRole(roles[i]);
			}		
		}
	}
}

//文件列表初始化
var initFiles = function(){	
	if($ID("docm.files").value.length<4)
		return	
	var filesJson =eval('(' + $ID("docm.files").value + ')');
	for(var i=0;i<filesJson.length;i++){
		fileQueued(filesJson[i]);
		var fileDiv = document.getElementById(filesJson[i].id+"_div");
		fileDiv.setAttribute("FileId",filesJson[i].id);	
		var fileBg = document.getElementById(filesJson[i].id+"_bg");
		fileBg.style.width=200;
	}	
}
//分类初始化
var classInit = function(id){
	var take=function(list){
		var currentStr="当前分类：";
		for(var i=0;i<list.length;i++){
			currentStr+="<a href=\"javascript:classInit('"+list[i].id+"');\">"+list[i].name+"</a> >>";
			if(i==list.length-1){
				$ID("docm.classId").value=list[i].id;
				$ID("docm.className").value=list[i].name;
			}
		}
		$ID("current").innerHTML=currentStr;
	}
	ClassDwr.queryParent('Class_Document',id,take);
	
	var c_take=function(list){
		$ID("classChild").innerHTML="";
		for(var i=0;i<list.length;i++){
			$ID("classChild").innerHTML+="<a href=\"javascript:classInit('"+list[i].id+"');\">"+list[i].name+"</a>&nbsp;";
		}
	}
	ClassDwr.queryChild('Class_Document',id,c_take);
}

//表单提交
function saveSubmit(){	
	var code = $ID("docm.code");

	if($ID("docm.title").value.isEmpty()){
		$ID("inpageinfo").innerHTML = "标题不能为空！";
		return;
	}
	if(!code.readOnly&&$ID("docm.code").value.isEmpty()){
		$ID("inpageinfo").innerHTML = "编号不能为空！";
		return;
	}
	
	$ID("docm.visible").value="";
	var roles=$N("roles");
	for(var i=0;i<roles.length;i++){
		if(roles[i].checked){
			if($ID("docm.visible").value.isEmpty()){
				$ID("docm.visible").value=roles[i].value;
			}
			else{
				$ID("docm.visible").value+=","+roles[i].value;
			}
		}
	}
	if($ID("docm.visible").value.isEmpty()){
		$ID("inpageinfo").innerHTML = "请选择可见范围！";
		return;
	}
	
	if(!code.readOnly){
		DocumentDWR.hasCode(code.value,function(data){
			if(data){
				$ID("inpageinfo").innerHTML = "编号不可重复！";
				return;
			}
			document.forms['pageForm'].submit();
		});
	}
	else if(code.readOnly&&$ID("docm.id").value.isEmpty()){
		var takeCode=function(data)
		{			
			if(data.length>0){
				var alertMsg = new DivWindow("alertMsg","系统提示",250,120,"系统自动分配的单号："+data+"有重号。<br>是否自动顺延？");
				alertMsg.addButton("确定",function(){
					document.forms['pageForm'].submit();
				});	
				alertMsg.addButton("取消");
				alertMsg.open();
				return;
			}
			document.forms['pageForm'].submit();
		}
		System.getCode('AutoCode_Document','FhiDocument', 'code',takeCode);
	}
	else{
		document.forms['pageForm'].submit();
	}
}
//************************文件JSON处理部分***********************************
//从files中删除一个文件
function removeFileJson(id,obj){
	var fileJsonStr = obj.value;
	var filesJson =eval('(' + fileJsonStr + ')');
	for(var i=0;i<filesJson.length;i++){
		if(filesJson[i].id==id){
			filesJson[i]=null;
		}
	}
	fileJsonStr = filesJsonToString(filesJson);
	obj.value=fileJsonStr;	
}
//向files中加入一个文件
function addFileJson(fileJson,obj){
	var fileJsonStr = obj.value;
	var filesJson;
	if(fileJsonStr.isEmpty()){
		fileJsonStr="[{id:'"+fileJson.id+"',name:'"+fileJson.name+"'}]";		
	}
	else{
		filesJson = eval('(' + fileJsonStr + ')');
		filesJson[filesJson.length]=fileJson;
		fileJsonStr = filesJsonToString(filesJson);
	}
	obj.value=fileJsonStr;
}

//将文件数组转换成字符串
function filesJsonToString(filesJson){
	if(filesJson.length==0){
		return "[]";
	}
	else{
		var retStr="[";
		for(var i=0;i<filesJson.length;i++){
			if(filesJson[i]!=null){
				if(retStr.length>1)retStr+=",";				
				retStr+="{id:'"+filesJson[i].id+"',name:'"+filesJson[i].name+"'}";
			}
		}
		retStr+="]";
		return retStr;
	}	
}
//******************SWF文件上传部分************************
//swf文件上传对象
var swfu;
//初始化swf文件上传控件
function swfUploadInit(){
		var swfUploadSetting={
		// Backend Settings
		upload_url: "<%=request.getContextPath()%>/upload.do",
		post_params : {
			"method" : "swfupload",	//设置一同上传的参数
			"maxPostSize" : "${user.permissionMap.uploadFileSizeDocument}"
		},
		use_query_string:true,//post_params以GET方式上传
		// File Upload Settings
		file_size_limit : "${user.permissionMap.uploadFileSizeDocument} MB",	// 文件大小 B,KB,MB,GB
		file_types : "${user.permissionMap.uploadFileTypeDocument}",		//设置文件类型
		//file_types_description : "JPG Images",//显示在文件选择框的打开文件类型
		file_upload_limit : "30",//允许上传文件数量

		//button_image_url : "<%=request.getContextPath()%>/js/swfupload/SmallSpyGlassWithTransperancy_17x18.png",
		button_placeholder_id: "spanButtonPlaceHolder",
		button_width: "60",
		button_height: "20",
		button_text : '<span class="button">选择文件</span>',
		button_text_style : '.button {color:#FFFFFF;}',
		button_text_top_padding: 0,
		button_text_left_padding: 5,
		button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
		button_cursor: SWFUpload.CURSOR.HAND,
		
		
		upload_success_handler:getServer,	//获取上传后返回信息
		upload_progress_handler:uploadProgress,		//设置上传时的状态信息
		upload_error_handler:uploadError,	//发生错误的回调函数
		file_queued_handler:fileQueued,		//选择文件之后调用
		// Flash Settings
		flash_url : "<%=request.getContextPath()%>/js/swfupload/swfupload.swf",

		// Debug Settings
		debug: false		//是否开启Degug信息
	}

	swfu = new SWFUpload(swfUploadSetting);
}


//处理每个文件上传成功之后服务器返回数据
var getServer=function(file,data){
	//将字符串转换为json
	data=eval('(' + data + ')');
	
	if(data.errMsg=="$[L]"){
		$ID("inpageinfo").innerHTML="用户登陆超时，上传失败！";
		uploadError(file);
	}else if(data.errMsg=="$[B]"){
		$ID("inpageinfo").innerHTML="文件大小超出限制，上传失败！";
		uploadError(file);
	}else if(data.errMsg=="$[E]"){
		$ID("inpageinfo").innerHTML="未知错误，上传失败！";
		uploadError(file);
	}else if(data.errMsg=="$[D]"){
		$ID("inpageinfo").innerHTML="数据库访问错误，上传失败！";
		uploadError(file);
	}else{
		$ID("inpageinfo").innerHTML="文件"+data.name+"上传成功！";
		//alert("上传成功！："+data.id+","+data.name);
		var fileDiv = document.getElementById(file.id+"_div");
		fileDiv.setAttribute("FileId",data.id);		
		addFileJson(data,$ID("docm.files"));
		swfu.startUpload();
	}
}

//实现文件上传百分比效果
var uploadProgress=function(file, complete, bytes){	
	var fileBg = document.getElementById(file.id+"_bg");
	if(fileBg){
		fileBg.style.width=(complete/bytes)*200;
	}
}

//上传错误返回信息
var uploadError=function(file,code,msg){
	var fileBg = document.getElementById(file.id+"_bg");
	if(fileBg){
		fileBg.style.width=0;
	}
}

//终止文件上传 并从文件队列中删除
var cancelUpload=function(file_id){
	var filesDiv = document.getElementById("filesDiv");
	var fileDiv = document.getElementById(file_id+"_div");
	if(fileDiv.getAttribute("FileId")!=null){
		removeFileJson(fileDiv.getAttribute("FileId"),$ID("docm.files"));
	}
	filesDiv.removeChild(fileDiv);
	swfu.cancelUpload(file_id);	//uploadError事件不会触发
}


//文件选择成功
var fileQueued = function(file){
	var fileDiv = document.createElement("div");
	fileDiv.className="fileDiv";
	fileDiv.id=file.id+"_div";
	
	var progressCancel = document.createElement("a");
	progressCancel.className="fileCancel";
	progressCancel.href="javascript:cancelUpload('"+file.id+"');";

	var fileDivBg = document.createElement("div");
	fileDivBg.className="fileDivBg";
	fileDivBg.id=file.id+"_bg";

	var fileDivInfo = document.createElement("span");
	fileDivInfo.className="fileDivInfo";
	fileDivInfo.innerHTML=file.name;

	fileDiv.appendChild(progressCancel);
	fileDiv.appendChild(fileDivBg);
	fileDiv.appendChild(fileDivInfo);

	var filesDiv = document.getElementById("filesDiv");
	filesDiv.appendChild(fileDiv);	
}
//******************SWF文件上传部分结束************************
//***************************初始化*********************************
//swf文件上传初始化
swfUploadInit();
//显示已存在文件列表
initFiles();
//角色树初始化
showRoleTree();
//分类信息初始化
classInit('${form.docm.classId}');

selectRoleInit();
</script>
</html>
