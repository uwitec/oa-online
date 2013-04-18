<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div id="loadBg"></div>
	<div id="loadInfo">
		<img id="loadImg" src="<%=request.getContextPath() %>/images/load.gif"/><br>
		<div id="loadInfoText">处理中请稍后...</div>
	</div>
	<script type="text/javascript">
	
	
	function bodyLoad(){		
		/*
		setInterval(function(){
			if(document.getElementById("loadBg").style.display!="none"){
			var sWidth,sHeight;         
			sWidth=document.documentElement.clientWidth;
			sHeight=document.documentElement.clientHeight;
			var loadObj = document.getElementById("loadBg");
				loadObj.style.width=sWidth+"px";
				loadObj.style.height=sHeight+"px";
			var loadImg = document.getElementById("loadImg");
			var loadInfo = document.getElementById("loadInfo");
			loadInfo.style.width="200px";
			loadInfo.style.height="100px";
			loadInfo.style.left=((sWidth-200)/2);
			}
		},500);
		*/
		if(document.getElementById("loadBg").style.display!="none"){
			var sWidth,sHeight;         
			sWidth=document.documentElement.clientWidth;
			sHeight=document.documentElement.clientHeight;
			var loadObj = document.getElementById("loadBg");
				loadObj.style.width=sWidth+"px";
				loadObj.style.height=sHeight+"px";
			var loadImg = document.getElementById("loadImg");
			var loadInfo = document.getElementById("loadInfo");
			loadInfo.style.width="200px";
			loadInfo.style.height="100px";
			loadInfo.style.left=((sWidth-200)/2);
		}
	}	
	bodyLoad();
	var loadEnd = function(){
		var loadBg = document.getElementById("loadBg");
		loadBg.style.display = "none";
		var loadInfo = document.getElementById("loadInfo");
		loadInfo.style.display = "none";
		//如果提示筐里有信息 直接显示
		if(document.getElementById("inpageinfo")){
				var inpageinfoInfo = document.getElementById("inpageinfo").innerHTML;
				if(!inpageinfoInfo.isEmpty()){
					showMsgbox(inpageinfoInfo);
				}
			}
		//alert("华展信息平台系统升级，将在5分钟后关闭。");
	}
	window.onload=loadEnd;
	</script>
	<div id="head">
    	<span id="logo"></span>
    	<ul>
    		<input type="hidden" id="user.userId" value="${user.userId}"/>
        	<li class="user">用户：<c:out value="${user.employeeClass.name}" /></li>
        	<li class="help"><a href="#">帮助</a></li>
        	<li class="exit"><a href="<%=request.getContextPath() %>/index.do?method=logout">退出</a></li>
    	</ul>
    </div>