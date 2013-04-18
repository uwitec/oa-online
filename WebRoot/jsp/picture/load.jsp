<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>新建相册</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/dtree.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/pic_management.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/dtreeUTF-8.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jsonFile.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
	<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/swfupload/swfupload.js"></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/PictureDWR.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/ClassDwr.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/System.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
</head>
<body>
<div id="layout">
  <jsp:include page="/index.do?method=head" flush="true"></jsp:include>
  <div id="mid">
    <jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    <div id="mainpage">
      <div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>资料管理</a></span><span><a href="<%=request.getContextPath()%>/picture.do?method=index">图库管理</a></span><span><a>相册更新</a></span></div>
      <div id="inpageinfo"></div>
      <div class="index_pic">编辑相册：</div> 
      <div class="info">     
        <div class="classify">
          <h2>选择分类</h2>
          <div id="show_Class_Box" class="class_select">
	          <div id="class_select_box" class="class_select_box">
	          </div>
          </div>
          <span>
          <c:if test="${user.permissionMap.isAddNewClassPhoto}">
          	新建分类：<button class="button_add" onClick="addClass()" >新建</button>
          </c:if>
          </span>
        </div>
        <div class="upload_area">
          <div class="pic_classify">
			<form action="<%=request.getContextPath() %>/picture.do?method=save" name="pageForm" method="post">
			<input type="hidden" id="pic.id" name="pic.id" value="${form.pic.id}" />
			<input type="hidden" id="pic.classId" name="pic.classId" value="${form.pic.classId}" />
			<input type="hidden" id="pic.className" name="pic.className" value="${form.pic.className}" />
			<input type="hidden" id="pic.files" name="pic.files" value="${form.pic.files}" />
        	<input type="hidden" id="pic.visible" name="pic.visible" value="${form.pic.visible}" />
        	<input type="hidden" id="pic.cover" name="pic.cover" value="${form.pic.cover}" />        	
              <div class="pic_input">
                <ul>
                  <li> 文档编号：
                   	<input <c:if test="${form.pic.autoCode||form.pic.id!=null}">readonly</c:if> class="document_code_text02" type="text" name="pic.code" id="pic.code" value="${form.pic.code}"/>
                  </li>
                  <li> 相册标题：
                    <input class="document_title_text" type="text" name="pic.title" id="pic.title" value="${form.pic.title}"/>
                  </li>
                  <li> 相册叙述：
                    <input type="text"  class="document_info_text" name="pic.content" value="${form.pic.content}"/>
                  </li>
                </ul>
              </div>            
            <h2>公开范围</h2>
            <div id="roleTree"></div>
            </form>
          </div>
          <div class="pic_file">
            <div class="upload_button"><p>上传图片：</p>
            <div class="selectUploadButton"><span id="spanButtonPlaceHolder"></span></div>
           	<button class="button_bg_sp" onClick="swfu.startUpload()">开始上传</button>
           	<button class="button_bg_sp" onClick="swfu.stopUpload()">暂停上传</button>
            </div>
            <ul class="pic_show_list" id="pic_show_list">              
            </ul>
          </div>
        </div>
      </div>
              <div id="com_all_delete02" class="left_push">
          <button onclick="saveSubmit()" class="button_bg">保存</button>
          <button onclick="goback('<%=request.getContextPath() %>/picture.do?method=index')" class="button_bg">退出</button>
        </div>
    </div>
  </div>
  <jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
<script type="text/javascript">
//添加分类
function addClass(){
	var addClass = new DivWindow("addClass","分类添加",200,120,"<div class='addClassWin'>分类名称：<input class='text_input' type='text' id='className' /></div>");
	addClass.addButton("确定",function(){
		var classification={};
		classification.name=$ID("className").value;
		if($ID("className").value.isEmpty()){
			return;
		}
		classification.pid=$ID("pic.classId").value;
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
//展示图片
function addShowPic(id,name,urlId){
		var showLi = $ID(id+"_li");
		if(showLi==undefined){
			var file={};
			file.id=urlId;
			file.urlId=urlId;
			file.name=name;
			fileQueued(file);
		}
		var showLabel = document.getElementById(id+"_label");
		showLabel.style.visibility="inherit";
		var cover = $ID("pic.cover").value;
		var showInput = document.getElementById(id+"_input");
		showInput.value=urlId;
		if((urlId+"_tag")==cover){
			showInput.checked=true;
			showInput.value=cover;
		}


		var showDiv = document.getElementById(id+"_div");
		showDiv.className="pic_show";
		showDiv.innerHTML="";
		//展示图片
		var showImg = document.createElement("img");
		showImg.id=id+"_img";
		showImg.onerror=function(){
			this.src='<%=request.getContextPath() %>/images/noPic.gif';
		}
		showImg.src="<%=request.getContextPath()%>/upload.do?method=picDownload&fileID="+urlId+"&w=90&h=68";
		showDiv.appendChild(showImg);
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
	var visibles=$ID("pic.visible").value.split(",");
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
	if($ID("pic.files").value.length<4)
		return	
	var filesJson =eval('(' + $ID("pic.files").value + ')');
	for(var i=0;i<filesJson.length;i++){
		fileQueued(filesJson[i]);
		var fileLi = document.getElementById(filesJson[i].id+"_li");
		fileLi.setAttribute("FileId",filesJson[i].id);	
		addShowPic(filesJson[i].id,filesJson[i].name,filesJson[i].id);
	}	
}

//表单提交
function saveSubmit(){	
	var code = $ID("pic.code");

	if($ID("pic.title").value.isEmpty()){
		showMsgbox("标题不能为空！");
		return;
	}
	if(!code.readOnly&&$ID("pic.code").value.isEmpty()){
		showMsgbox("编号不能为空！");
		return;
	}
	
	$ID("pic.visible").value="";
	var roles=$N("roles");
	for(var i=0;i<roles.length;i++){
		if(roles[i].checked){
			if($ID("pic.visible").value.isEmpty()){
				$ID("pic.visible").value=roles[i].value;
			}
			else{
				$ID("pic.visible").value+=","+roles[i].value;
			}
		}
	}
	if($ID("pic.visible").value.isEmpty()){
		showMsgbox("请选择可见范围！");
		return;
	}
	
	//如果没设置封面自动设置第一个为封面	
	if($ID("pic.files").value==""||$ID("pic.files").value=="[]"){
		showMsgbox("您还没有上传图片！");
		return;
	}
	
	var cover = $N("picCover");
	var hasCover=false;
	for(var i=0;i<cover.length;i++){
		hasCover=cover[i].checked||hasCover;
		if(cover[i].checked){
			$ID("pic.cover").value=cover[i].value;
		}
	}
	if(!hasCover){
		cover[0].checked=true;
		$ID("pic.cover").value=cover[0].value;
	}
	//编号判断
	if(!code.readOnly){
		PictureDWR.hasCode(code.value,function(data){
			if(data){
				showMsgbox("编号不可重复！");
				return;
			}
			document.forms['pageForm'].submit();
		});
	}
	else if(code.readOnly&&$ID("pic.id").value.isEmpty()){
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
		System.getCode('AutoCode_Picture','FhiPicture', 'code',takeCode);
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
			"maxPostSize" : "${user.permissionMap.uploadFileSizePhoto}"
		},
		use_query_string:true,//post_params以GET方式上传
		// File Upload Settings
		file_size_limit : "${user.permissionMap.uploadFileSizePhoto} MB",	// 文件大小 B,KB,MB,GB
		file_types : "${user.permissionMap.uploadFileSizeTypePhoto}",		//设置文件类型
		file_types_description : "Images",//显示在文件选择框的打开文件类型
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
	
	if(data.errMsg=="$[L]"){
		showMsgbox("用户登陆超时，上传失败！");
	}else if(data.errMsg=="$[B]"){
		showMsgbox("文件大小超出限制，上传失败！");
	}else if(data.errMsg=="$[E]"){
		showMsgbox("未知错误，上传失败！");
	}else if(data.errMsg=="$[D]"){
		showMsgbox("数据库访问错误，上传失败！");
	}else{
		showMsgbox("文件"+data.name+"上传成功！");
		//alert("上传成功！："+data.id+","+data.name);
		var fileLi = document.getElementById(file.id+"_li");
		fileLi.setAttribute("FileId",data.id);
		addFileJson(data,$ID("pic.files"));
		addShowPic(file.id,data.name,data.id);
		swfu.startUpload();
	}
}

//实现文件上传百分比效果
var uploadProgress=function(file, complete, bytes){
	var fileUpSpan = document.getElementById(file.id+"_span");
	if(fileUpSpan==undefined){
		fileUpSpan = document.createElement("span");
		fileUpSpan.id=file.id+"_span";
		var fileDiv = document.getElementById(file.id+"_div");
		fileDiv.className="pic_uploading";
		fileDiv.appendChild(fileUpSpan);
	}
	fileUpSpan.innerHTML=parseInt((complete/bytes)*100)+"%";
}

//上传错误返回信息
var uploadError=function(file,code,msg){
	if(code==-240){
		showMsgbox("您已达到可上传文件最大数量！");
	}
	else{
		var fileDiv = document.getElementById(file.id+"_div");
		fileDiv.className="pic_no_show";
		fileDiv.innerHTML="";
	}	
}

//终止文件上传 并从文件队列中删除
var cancelUpload=function(file_id){
	var filesList = document.getElementById("pic_show_list");
	var fileLi = document.getElementById(file_id+"_li");
	var fileImg = document.getElementById(file_id+"_img");
	if(fileImg){		
		swfu.setFileUploadLimit(++file_upload_limit);
	}
	if(fileLi.getAttribute("FileId")!=null){
		removeFileJson(fileLi.getAttribute("FileId"),$ID("pic.files"));
	}
	filesList.removeChild(fileLi);	
	swfu.cancelUpload(file_id,false);	//uploadError事件不会触发
}


//文件选择成功
var fileQueued = function(file){
	
	var spans = $("#pic_show_list a[title="+file.name+"]");
	if(spans.length>0){
		showMsgbox("重名文件："+file.name+"！");
		swfu.cancelUpload(file.id,false);	//uploadError事件不会触发
		return true;
	}

	
  	var fileli = document.createElement("li");
  	fileli.id=file.id+"_li";
  	if(file.urlId!=null){
  		fileLi.setAttribute("FileId",file.urlId);
  	}
  	var showLabel = document.createElement("label");
  	showLabel.id=file.id+"_label";
  	showLabel.style.visibility="hidden";
  	showLabel.innerHTML="<input type='radio' id='"+file.id+"_input' name='picCover'>设为封面";
	
	var fileDiv = document.createElement("div");
	fileDiv.className="pic_no_show";
	fileDiv.id=file.id+"_div";
	
	fileli.appendChild(showLabel);
	fileli.appendChild(fileDiv);
	

	fileli.innerHTML+="<a name='fileName' title='"+file.name+"'>"+omissionStr(file.name,7)+"</a>";

	var fileDelButton = document.createElement("button");
	fileDelButton.innerHTML="x";
	fileDelButton.onclick=function(){
		cancelUpload(file.id);
	};
	fileli.appendChild(fileDelButton);
	$ID("pic_show_list").appendChild(fileli);
};

var fileQueueError = function(file , code, message){
	if(code==-100){
		showMsgbox("文件加载失败！选择文件数量已超出可上传数量。");
	}else if(code=-110)
		showMsgbox("文件加载失败！您选择的文件大小超出限制。");
	else{
		showMsgbox("未知错误！文件加载失败，请联系管理员。");
	}
};

//******************SWF文件上传部分结束************************
//分类初始化
var classInit = function(){
	var class_select_box = $ID("class_select_box");
	class_select_box.innerHTML="";
	var classId=$ID("pic.classId").value;
	var classTake=function(list){
		if(list.length==1){
			$ID("pic.classId").value=list[0].id;
			$ID("pic.className").value=list[0].name;
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
	ClassDwr.queryTree('Class_Picture',classId,classTake);
}

var classClick=function(obj){
	//获取父节点
	var classParent = obj.parentElement;
	//获取分类容器
	var class_select_box = $ID("class_select_box");

	
	for(var i=0;i<classParent.children.length;i++){
		classParent.children[i].className="";
	}	
	
	obj.className="onclick";
	$ID("pic.classId").value=obj.getAttribute("classId");
	$ID("pic.className").value=obj.getAttribute("claName");
	
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
	ClassDwr.queryChild('Class_Picture',obj.getAttribute("classId"),classTake);
}

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
					$ID("pic.classId").value=this.getAttribute("claPid");
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
//***************************初始化*********************************
//swf文件上传初始化
swfUploadInit();
//显示已存在文件列表
initFiles();
//角色树初始化
showRoleTree();

selectRoleInit();

classInit();
</script>
</html>
