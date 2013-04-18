<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/swfupload/swfupload.js"></script>
<head>
<title>文件上传</title>
<style type="text/css">
<!--
input,button{
	border:1px solid #a7a7a7; 
	height:20px;
}
input{
	width:200px;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

/*swf文件上传样式开始*/
.fileDiv {
	height:21px;
	width:202px;
	border:1px dashed #CCCCCC;
	position:relative;
	background-color: #FCD6FA;
	margin: 2px;
}
.filesDiv {
	width:206px;
	height:0px;
	border:1px dotted #999999;
	background-color: #A5B7FE;
	position:relative;
}
.fileDivBg{
	height:21px;
	width:0px;
	background-color: #D3F2D0;
}
.fileDivInfo{
	height:19px;
	width:175px;
	position:absolute;
	top:0;
	padding:3px;
	overflow:hidden;
}
a.fileCancel{
	position:absolute;
	top:5px;
	right:5px;
	height:14px;
	width:14px;
	background-image: url(cancelbutton.gif);
	background-repeat: no-repeat;
	background-position: -14px 0px;
}
a.fileCancel:hover {
	background-position: 0px 0px;
}
/*swf文件上传样式结束*/
-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
<body >
<form name="fileForm" method="post"  ENCTYPE="multipart/form-data">	
	<table border="0" cellspacing="0" cellpadding="2" align="center" class="bgtable" width="98%">
		<tr>
			<td><div id="spanButtonPlaceHolder"></div><button onClick="swfu.startUpload()">开始上传</button><button onClick="swfu.stopUpload()">暂停上传</button></td>
		</tr>
	</table>
</form>

<div class="filesDiv" id="filesDiv">
</div>
</body>
</html>
<script type="text/javascript">
var swfu;
window.onload = function () {
	swfu = new SWFUpload({
		// Backend Settings
		upload_url: "<%=request.getContextPath()%>/upload.do",
		post_params : {
			"method" : "swfupload"	//设置一同上传的参数
		},
		use_query_string:true,//post_params以GET方式上传
		// File Upload Settings
		file_size_limit : "0",	// 文件大小 B,KB,MB,GB
		file_types : "*.*",		//设置文件类型
		//file_types_description : "JPG Images",//显示在文件选择框的打开文件类型
		file_upload_limit : "0",//允许上传文件数量

		button_image_url : "<%=request.getContextPath()%>/js/swfuploadbutton.swf",
		button_placeholder_id: "spanButtonPlaceHolder",		
		button_width: 180,
		button_height: 18,
		button_text : '<span class="button">请选择文件</span>',
		button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; }',
		button_text_top_padding: 0,
		button_text_left_padding: 18,
		
		upload_success_handler:getServer,	//获取上传后返回信息
		upload_progress_handler:uploadProgress,		//设置上传时的状态信息
		upload_error_handler:uploadError,	//发生错误的回调函数
		file_queued_handler:fileQueued,		//选择文件之后调用
		// Flash Settings
		flash_url : "<%=request.getContextPath()%>/js/swfupload/swfupload.swf",

		// Debug Settings
		debug: true		//是否开启Degug信息
	});
};
var getServer=function(file,data){
	if(data.indexOf("$[L]")>=0){
		alert("未登录");
	}else if(data.indexOf("$[B]")>=0){
		alert("文件过大");
	}else if(data.indexOf("$[E]")>=0){
		alert("上传失败");
	}else if(data.indexOf("$[D]")>=0){
		alert("数据库添加失败");
	}else{
		alert("上传成功！："+data);
		swfu.startUpload();
	}
}

var uploadProgress=function(file, complete, bytes){	
	var fileBg = document.getElementById(file.id+"_bg");
	if(fileBg){
		fileBg.style.width=(complete/bytes)*200;
	}
}

var uploadError=function(file,code,msg){
	var fileBg = document.getElementById(file.id+"_bg");
	if(fileBg){
		fileBg.style.width=0;
	}
}

//终止文件上传 并从文件队列中删除
var cancelUpload=function(file_id){
	swfu.cancelUpload(file_id);
	var filesDiv = document.getElementById("filesDiv");
	var fileDiv = document.getElementById(file_id+"_div");
	filesDiv.removeChild(fileDiv);
	swfu.cancelUpload(file_id);	//uploadError事件不会触发
}

//file_queued_handler
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
</script>