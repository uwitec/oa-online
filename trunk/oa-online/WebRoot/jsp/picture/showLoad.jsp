<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/new_general.css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/ClassDwr.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<title>图片展示页</title>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    	<div id="mainpage">
    		<form action="<%=request.getContextPath()%>/picture.do?method=showIndex" name="pageForm" method="post">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>内部信息库</a></span><span><a href="<%=request.getContextPath()%>/picture.do?method=showIndex">图库搜索</a></div>
        	<input type="hidden" id="pic.files" name="pic.files" value="${form.pic.files}" />
    		<input type="hidden" name="pic.classId" id="pic.classId" value="${form.pic.classId}"/>
            <h5 class="pic_title">
	            <p>相册名称:<a title="相册编号：${form.pic.code}&#13;所在分类：${form.pic.className}&#13;发布用户：${form.pic.creater}&#13;发布时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${form.pic.createDate}" />">${form.pic.title}</a> 图片名称：<a href="#" title="下载当前图片" id="pic_name_url"></a></p>
	            <p class="pic_uploaddate" id="pic_uploaddate"></p>
            </h5>
            <div id="mainpicarea">
            	<div class="picture">
                	<a href="#" title="点击浏览下一张图片" id="pic_next_url"><img id="pic_show_img"/></a>
                    <p>${form.pic.content}</p>
                </div>
                <ul class="picscroll" id="picscroll">
                </ul>
            </div>
            </form>
    	</div>
    </div>
   	<jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
<script type="text/javascript">
//文件ID转换日期
function fileIdtoDate(fileId){
	var upDate = new Date(parseInt(fileId.substring(0,fileId.length-7)));
	if(!upDate)
		return;	
	return upDate.format("yyyy-MM-dd");
}
//显示下一张图
function nextPicture(fileId){
	if($ID("pic.files").value.length<4)
		return	
	var filesJson =eval('(' + $ID("pic.files").value + ')');
	for(var i=0;i<filesJson.length;i++){
		if(fileId==filesJson[i].id){
			if((i+1)<filesJson.length){
				showPicture(filesJson[i+1].id,filesJson[i+1].name);
			}
			else{
				showPicture(filesJson[0].id,filesJson[0].name);
			}
			return;
		}
	}
}
//展示大图
function showPicture(fileId,fileName){
	$ID("pic_name_url").innerHTML=fileName;
	$ID("pic_name_url").href="<%=request.getContextPath()%>/upload.do?method=download&fileID="+fileId;
	$ID("pic_name_url").target="_blank";
	
	$ID("pic_uploaddate").innerHTML="上传时间："+fileIdtoDate(fileId);
	
	$ID("pic_next_url").href="javascript:nextPicture('"+fileId+"')";
	
	
	$ID("pic_show_img").src="<%=request.getContextPath()%>/upload.do?method=picDownload&w=580&h=395&fileID="+fileId;
	$ID("pic_show_img").onerror=function(){
		this.src='<%=request.getContextPath() %>/images/noPic.gif';
	}
}
//图片缩略图显示添加
function addShowPic(file){
		var picscroll=$ID("picscroll");
		//创建展示图
		var showLi = document.createElement("li");
			showLi.id=file.id+"_show";
		var showImg = document.createElement("img");
			showImg.src="<%=request.getContextPath()%>/upload.do?method=picDownload&w=150&h=120&fileID="+file.id;
			showImg.onerror=function(){
				this.src='<%=request.getContextPath() %>/images/noPic.gif';
				//$ID(id+"_link").style.visibility="hidden";
			}
			
		var showA = document.createElement("a");
			showA.href="javascript:showPicture('"+file.id+"','"+file.name+"')";
			showA.title="图片名称："+file.name+"\n上传时间："+fileIdtoDate(file.id);
	
		showA.appendChild(showImg);
		showLi.appendChild(showA);
		picscroll.appendChild(showLi);
}
//文件列表初始化
var initFiles = function(){
	if($ID("pic.files").value.length<4)
		return	
	var filesJson =eval('(' + $ID("pic.files").value + ')');
	for(var i=0;i<filesJson.length;i++){
		addShowPic(filesJson[i]);		
	}
	showPicture(filesJson[0].id,filesJson[0].name);
}
//返回查询页
function backIndex(classId){	
	$ID("pic.classId").value=classId;
	document.forms['pageForm'].submit();
}

//分类初始化
var classInit = function(id){
	var take=function(list){
		var currentStr="";
		for(var i=0;i<list.length;i++){
			currentStr=currentStr+"<span><a href=\"javascript:backIndex('"+list[i].id+"');\">"+list[i].name+"</a></span>";
		}
		$ID("location").innerHTML=$ID("location").innerHTML+currentStr;
	}
	ClassDwr.queryParent('Class_Picture',id,take);
}

classInit('${form.pic.classId}');

initFiles();
</script>
</html>