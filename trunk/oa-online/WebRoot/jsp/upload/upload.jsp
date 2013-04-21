<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
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
-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
<body >
<form name="fileForm" method="post"  ENCTYPE="multipart/form-data">	
	<table border="0" cellspacing="0" cellpadding="2" align="center" class="bgtable" width="98%">
		<tr id="fileShow">
			<td>
				<INPUT TYPE="file" id="fileInput" name="uploadfile">&nbsp;<button onClick="upLoad()">上传</button>
			</td>
		</tr>
		<tr id="uploading">
			<td>上传中，请稍后...</td>
		</tr>
	</table>
</form>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
function upLoad() {
	var fileInput = document.getElementById("fileInput");
	if(fileInput.value.length<=0){
		return;
	}	
	var fileShow = document.getElementById("fileShow");
	fileShow.style.display="none";
	var uploading = document.getElementById("uploading");
	uploading.style.display="block";
	
	document.forms[0].submit();
}

//回传上传成功文件路径
function upUrlInit(fileName,fileID){
	var uploading = document.getElementById("uploading");
	uploading.style.display="none";
	var fileNameObj = window.parent.document.getElementById("${param['fileObj']}");	
	if(fileNameObj==null){
		//控件不存在
		return;
	}
	if(fileName.length>4){
		//上传成功
		fileNameObj.onupfile(fileName,fileID);
	}
	else if(fileName=='1'){
		//文件过大
		fileNameObj.onupfile(fileName,fileID);
	}
	else if(fileName=='-1'){
		//上传失败
		fileNameObj.onupfile(fileName,fileID);
	}
}
upUrlInit('${fileName}','${fileID}');
</SCRIPT>