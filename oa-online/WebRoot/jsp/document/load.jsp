<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文档新建页</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/dtree.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/new_general.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/doc_management.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/MyDivWindow.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/dtreeUTF-8.js"></script>
<script type='text/javascript'
	src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jsonFile.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/swfupload/swfupload.js"></script>
<script type='text/javascript'
	src='<%=request.getContextPath()%>/dwr/interface/DocumentDWR.js'></script>
<script type='text/javascript'
	src='<%=request.getContextPath()%>/dwr/interface/ClassDwr.js'></script>
<script type='text/javascript'
	src='<%=request.getContextPath()%>/dwr/interface/System.js'></script>
<script type='text/javascript'
	src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor-4.1.6/kindeditor.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor-4.1.6/lang/zh_CN.js"></script>
</head>
<body>
<script>
        KindEditor.ready(function(K) {
                window.editor = K.create('#editor_id');
        });
</script>
	<div id="layout">
		<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
		<div id="mid">
			<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
			<div id="mainpage">
				<div id="location">
					当前位置：<span class="fp"><a
						href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a>
					</span><span><a>资料管理</a> </span><span><a
						href="<%=request.getContextPath()%>/document.do?method=index">文档管理</a>
					</span><span><a>文档更新</a> </span>
				</div>
				<div id="inpageinfo"></div>
				<div class="index_pic">文档编辑：</div>
				<div class="info">
					<div class="classify">
						<h2>选择分类</h2>
						<div id="show_Class_Box" class="class_select">
							<div id="class_select_box" class="class_select_box"></div>
						</div>
						<c:if test="${user.permissionMap.isAddNewClassDocument}">
          	新建分类：<button class="button_add" onClick="addClass()">新建</button>
						</c:if>
					</div>
					<div class="upload_area">
						<div class="pic_classify">
							<form
								action="<%=request.getContextPath()%>/document.do?method=save"
								name="pageForm" method="post">
								<input type="hidden" id="docm.id" name="docm.id"
									value="${form.docm.id}" /> <input type="hidden"
									id="docm.classId" name="docm.classId"
									value="${form.docm.classId}" /> <input type="hidden"
									id="docm.className" name="docm.className"
									value="${form.docm.className}" /> <input type="hidden"
									id="docm.files" name="docm.files" value="${form.docm.files}" />
								<input type="hidden" id="docm.visible" name="docm.visible"
									value="${form.docm.visible}" />
								<div class="pic_input">
									<ul>
										<li>文档编号： <input
											<c:if test="${form.docm.autoCode||form.docm.id!=null}">readonly</c:if>
											class="document_code_text" type="text" name="docm.code"
											id="docm.code" value="${form.docm.code}" /></li>
										<li>文档标题： <input class="document_title_text" type="text"
											name="docm.title" id="docm.title" value="${form.docm.title}" />
										</li>
									</ul>
									<!-- fckeditor 编辑器所在位置 -->
									<div id="pictitle_bottom">           
							                <FCK:editor instanceName="docm.content" toolbarSet="FHI" height="300" width="750" >
												<jsp:attribute name="value">${form.docm.content}</jsp:attribute>
											</FCK:editor> 
										</div>
									<!-- 配置kindEditor -->
									<%-- <div id="pictitle_bottom">
										<textarea id="editor_id" name="content" 
											style="width: 750px; height: 300px;">
											<%=${form.docm.content}%>
									</textarea>
									</div> --%>
								</div>
								<h2>公开范围</h2>
								<div id="roleTree"></div>
								<div class="pic_file">
									<div class="upload_button">
										<span>上传文件：</span>
										<div class="selectUploadButton">
											<span id="spanButtonPlaceHolder"></span>
										</div>
										<button class="button_bg_sp" onClick="swfu.startUpload()">开始上传</button>
										<button class="button_bg_sp" onClick="swfu.stopUpload()">暂停上传</button>
									</div>
									<ul class="pic_show_list" id="pic_show_list">
									</ul>
								</div>
						</div>
						</form>
					</div>
					<div class="button_bottom_box">
						<button id="submit_button" onClick="saveSubmit()"
							class="button_bg">保存</button>
						<button
							onClick="goback('<%=request.getContextPath()%>/document.do?method=index')"
							class="button_bg">退出</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</body>
<script type="text/javascript">
//禁止提交
function prohibitSubmit(){
	var submit_button = $ID("submit_button");
	submit_button.className="button_gray";
	submit_button.onclick=function(){
		showMsgbox("文件上传中，不可保存！");
	}	
}
//允许提交
function permitSubmit(){
	var submit_button = $ID("submit_button");
	submit_button.className="button_bg";
	submit_button.onclick=function(){
		saveSubmit();
	}	
}

//添加分类
function addClass(){
	var addClass = new DivWindow("addClass","分类添加",200,120,"<div class='addClassWin'>分类名称：<input class='text_input' type='text' id='className' /></div>");
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
					classInit();					
				}else{
					showMsgbox("分类添加失败！可能有重复分类。");
				}
				closeDivWindow("addClass");
			})		
		});	
	addClass.addButton("取消");	
	addClass.open();
}

//分类初始化
var classInit = function(id){
	var class_select_box = $ID("class_select_box");
	class_select_box.innerHTML="";	
	var classId=$ID("docm.classId").value;
	var classTake=function(list){
		if(list.length==1){
			$ID("docm.classId").value=list[0].id;
			$ID("docm.className").value=list[0].name;
		}
		for(var i=0;i<list.length;i++){
			if(i+1!=list.length){
				showClassBox(list[i],list[i+1].id);				
			}
			else{
				showClassBox(list[i]);
			}			
		}
	};
	ClassDwr.queryTree('Class_Document',classId,classTake);
}
//分类单击事件
var classClick=function(obj){
	//获取父节点
	var classParent = obj.parentElement;
	//获取分类容器
	var class_select_box = $ID("class_select_box");

	
	for(var i=0;i<classParent.children.length;i++){
		classParent.children[i].className="";
	}	
	
	obj.className="onclick";
	$ID("docm.classId").value=obj.getAttribute("classId");
	$ID("docm.className").value=obj.getAttribute("claName");
	
	//获取当先分类索引值
	var boxIndex=classParent.getAttribute("boxIndex");
	//计算索引值删除之后所有对象
	boxIndex=boxIndex*2;				
	var childrenLength = class_select_box.children.length;
	for(var i=boxIndex+1;i<childrenLength;i++){					
		class_select_box.removeChild(class_select_box.children[boxIndex+1]);
	}
	//展示当前所选分类的子项
	var classTake=function(list){
		showClassBox(list);
	};
	ClassDwr.queryChild('Class_Document',obj.getAttribute("classId"),classTake);
}
//初始化分类框
function showClassBox(classObj,clickId){
	var class_select_box = $ID("class_select_box");
	var classUl = document.createElement("ul");
	
	
	var childList = classObj.childList;
	//如果是单击事件查询的是子类classObj就是list
	if(childList==undefined){
		childList=classObj;
	}
	
	for(var i=0;i<childList.length;i++){
		var classLi = document.createElement("li");	
			classLi.innerHTML=childList[i].name;
			classLi.setAttribute("classId",childList[i].id);
			classLi.setAttribute("claName",childList[i].name);
			classLi.setAttribute("claPid",childList[i].pid);
			//判断是否为选中状态
				if(clickId==childList[i].id){
					classLi.className="onclick";
				}
				
			classLi.onclick=function(){
				if(this.className=="onclick"){
					$ID("docm.classId").value=this.getAttribute("claPid");
					classInit();
				}else{
					classClick(this);
				}
			}
		classUl.appendChild(classLi);
	}
	if(classUl.children.length>0){
		if(class_select_box.children.length>0){
			var classSpan = document.createElement("span");		
			classUl.setAttribute("boxIndex",(class_select_box.children.length+1)/2);
			class_select_box.appendChild(classSpan);
		}
		else{
			classUl.setAttribute("boxIndex",0);
		}
		class_select_box.appendChild(classUl);
		//将可见区域像右拽
		$ID("show_Class_Box").scrollLeft=class_select_box.scrollWidth;
	}	
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
//角色初始化
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

//表单提交
function saveSubmit(){	
	var code = $ID("docm.code");

	if($ID("docm.title").value.isEmpty()){
		showMsgbox("标题不能为空！","","docm.title");
		return;
	}
	if(!code.readOnly&&$ID("docm.code").value.isEmpty()){
		showMsgbox("编号不能为空！");
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
		showMsgbox("请选择公开范围！");
		return;
	}
	
	if(!code.readOnly){
		DocumentDWR.hasCode(code.value,function(data){
			if(data){
				showMsgbox("编号不可重复！");
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
//******************SWF文件上传部分************************
var file_upload_limit=20;
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
		file_types_description : "File",//显示在文件选择框的打开文件类型
		file_upload_limit : file_upload_limit,//允许上传文件数量

		
		//button_image_url : "<%=request.getContextPath()%>/js/swfupload/buttonN0.png",
		button_placeholder_id: "spanButtonPlaceHolder",
		button_width: "87",
		button_height: "28",
		button_text : '<span class="button">选择文件</span>',
		button_text_style : '.button {color:#FFFFFF;text-align: center;}',
		button_text_left_padding: 3,
		button_text_top_padding: 3,
		
		button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
		button_cursor: SWFUpload.CURSOR.HAND,
		
		
		upload_success_handler:getServer,	//获取上传后返回信息
		upload_progress_handler:uploadProgress,		//设置上传时的状态信息
		upload_error_handler:uploadError,	//发生错误的回调函数
		file_queued_handler:fileQueued,		//选择文件成功之后调用
		file_queue_error_handler:fileQueueError,//选择文件错误之后调用
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
	permitSubmit();
	if(data.errMsg=="$[L]"){
		$ID(file.id+"_bg").style.width=0;
		showMsgbox("上传失败！用户登陆超时，请重新登录。");
	}else if(data.errMsg=="$[B]"){
		$ID(file.id+"_bg").style.width=0;
		showMsgbox("上传失败！文件大小超出限制。");
	}else if(data.errMsg=="$[E]"){
		$ID(file.id+"_bg").style.width=0;
		showMsgbox("上传失败！未知错误。");
	}else if(data.errMsg=="$[D]"){
		$ID(file.id+"_bg").style.width=0;
		showMsgbox("上传失败！数据库访问错误，请联系管理员。");
	}else{
		//showMsgbox("文件"+data.name+"上传成功！");
		//alert("上传成功！："+data.id+","+data.name);
		var fileLi = document.getElementById(file.id+"_li");
		fileLi.setAttribute("FileId",data.id);
		addFileJson(data,$ID("docm.files"));
		swfu.startUpload();
	}	
}

//实现文件上传百分比效果
var uploadProgress=function(file, complete, bytes){
	prohibitSubmit();
	var fileBg = document.getElementById(file.id+"_bg");
	if(fileBg){
		fileBg.style.width=(complete/bytes)*350;
	}
}

//上传错误返回信息
var uploadError=function(file,code,msg){
	if(code==-240){
		showMsgbox("您已达到可上传文件最大数量！");
	}
	else{
		var fileBg = document.getElementById(file.id+"_bg");
		if(fileBg){
			fileBg.style.width=0;
		}
	}
	permitSubmit();
}

//终止文件上传 并从文件队列中删除
var cancelUpload=function(file_id){
	var filesList = document.getElementById("pic_show_list");
	var fileLi = document.getElementById(file_id+"_li");
	if(fileLi.getAttribute("FileId")!=null){
		swfu.setFileUploadLimit(++file_upload_limit);
		removeFileJson(fileLi.getAttribute("FileId"),$ID("docm.files"));
	}
	filesList.removeChild(fileLi);	
	swfu.cancelUpload(file_id,false);	//uploadError事件不会触发
	permitSubmit();
}


//文件选择成功
var fileQueued = function(file){	


	
	var spans = $("#pic_show_list span:contains('"+file.name+"')");
	if(spans.length>0){
		showMsgbox("重名文件："+file.name+"！");
		swfu.cancelUpload(file.id,false);	//uploadError事件不会触发
		return true;
	}
	
	var fileLi = document.createElement("li");
	fileLi.id = file.id + "_li";	
	var fileDiv = document.createElement("div");
	fileDiv.id = file.id + "_bg";
	fileDiv.className="fileDivBg";
	var fileTitle = document.createElement("span");	
	fileTitle.innerHTML = file.name;
	fileDiv.appendChild(fileTitle);
	var fileDel = document.createElement("button");
	fileDel.innerHTML="x";
	fileDel.className="file_list_del";
	fileDel.onclick=function(){
		cancelUpload(file.id);
		};
	fileDiv.appendChild(fileDel);
	fileLi.appendChild(fileDiv);
	$ID("pic_show_list").appendChild(fileLi);
};

var fileQueueError = function(file , code, message){
	if(code==-100){
		showMsgbox("选择文件数量已超出可上传数量！");
	}
	permitSubmit();
};

//******************SWF文件上传部分结束************************
//文件列表初始化
var initFiles = function(){	
	if($ID("docm.files").value.length<4)
		return	
	var filesJson =eval('(' + $ID("docm.files").value + ')');
	for(var i=0;i<filesJson.length;i++){
		fileQueued(filesJson[i]);
		var fileDiv = document.getElementById(filesJson[i].id+"_li");
		fileDiv.setAttribute("FileId",filesJson[i].id);	
		var fileBg = document.getElementById(filesJson[i].id+"_bg");
		fileBg.style.width=350;
	}	
}
//***************************初始化*********************************

initFiles();
classInit();
swfUploadInit();

//角色初始化
showRoleTree();
selectRoleInit();
</script>
</html>
