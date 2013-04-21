<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/SelectMenu.css" />
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/FreightDWR.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/js/tools/SelectMenu.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<title>报价查询页</title>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
    <div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    	<div id="mainpage">
    		<form action="" name="pageForm" method="post">
	       	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>内部信息库</a></span><span><a href="<%=request.getContextPath()%>/freight.do?method=showQuery">报价查询</a></span></div>
            <div id="search">
            	<ul>
                	<li>始发地：</li>
                    <li><input name="freight.fromName" id="freight.fromName" type="text" class="search_input" value="${form.freight.fromName}" /></li>
                </ul>
                <ul>
                    <li>目的地：</li>
                    <li><input name="freight.toName" id="freight.toName" type="text" class="search_input" value="${form.freight.toName}" /></li>
                </ul>
                <ul>
                    <li>运输方式：</li>
                    <li>
                    	<select id="waySelect" name="waySelect">
                    		<option value="air" <c:if test="${form.waySelect=='air'||form.waySelect==null}">selected</c:if>>空运</option>
                    		<option value="land" <c:if test="${form.waySelect=='land'}">selected</c:if>>路运</option>
                    		<option value="sea" <c:if test="${form.waySelect=='sea'}">selected</c:if>>海运</option>
                    	</select>
                    </li>
                </ul>                
                <ul>
                	<li>
                    	<button onclick="selectSubmit()" class="button_bg pic_bt">搜索</button>
                    </li>
                </ul>
            </div>
            <div id="inpageinfo"></div>
            <div class="titalpic">报价列表：</div> 
            <div id="search_resultlist">
				<c:forEach var="freight" items="${form.list}">
            	<div class="search_resultcontent" id="${freight.id}.box">
	            	<div class="search_resulttitle" onclick="showInfo('${freight.id}')">
		            	<ul>
			            	<li class="freight">${freight.code}</li>		            	
							<li id="freight">${freight.fromName} &rArr;&nbsp;&nbsp;${freight.toName}</li>
							<li id="${freight.id}.title"></li>
							<li id="${freight.id}.currency"></li>
							<li>录入时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="both" dateStyle="default" timeStyle="default" value="${freight.createDate}" /></li>
							<li id="${freight.id}.periodValidity"></li>
						</ul>
						<img id="${freight.id}.img" src="<%=request.getContextPath()%>/images/+.png"/>
					</div>
					<input type="hidden" name="freightPriceInfo" id="${freight.id}" air="${freight.air}" land="${freight.land}" sea="${freight.sea}" />
					<div id="${freight.id}.info" class="search_resultdetail">					

	  				</div>
  				</div>
  				</c:forEach>  				
            </div>
            <div id="page"><%@ include file="/inc/pagecontrol.inc"%></div>
            </form>
    	</div>
    </div>
    <jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
<script type="text/javascript">
//虚拟下拉列表开始***************************************************


var queryFrom = new SelectMenu("freight.fromName");
//自定义数据来源
queryFrom.setDataSources(FreightDWR.queryFrom);
//自定义数据处理
var showValue=function(dataObj,liObj){
	liObj.innerHTML=dataObj.fromCode + "  " + dataObj.fromName;
	liObj.setAttribute("fromName",dataObj.fromName);
}
queryFrom.setShowValue(showValue);
//自定义选中处理
var selected = function(inputObj,dataObj){
	inputObj.value=dataObj.getAttribute("fromName");;
}
queryFrom.setSelected(selected);


var queryTo = new SelectMenu("freight.toName");
//自定义数据来源
queryTo.setDataSources(FreightDWR.queryTo);
//自定义数据处理
var toShowValue=function(dataObj,liObj){
	liObj.innerHTML=dataObj.toCode + "  " + dataObj.toName;
	liObj.setAttribute("toName",dataObj.toName);
}
queryTo.setShowValue(toShowValue);
//自定义选中处理
var toSelected = function(inputObj,dataObj){
	inputObj.value=dataObj.getAttribute("toName");;
}
queryTo.setSelected(toSelected);
//虚拟下拉列表结束*************************************************

//地面信息显示隐藏
function showInfo(id){
	var infoDiv = $ID(id+".info");
	if(infoDiv.style.display=="none"||infoDiv.style.display==""){
		infoDiv.style.display = "block";
		$ID(id+".img").src="<%=request.getContextPath()%>/images/-.png";
	}
	else{
		infoDiv.style.display = "none";
		$ID(id+".img").src="<%=request.getContextPath()%>/images/+.png";
	}	
}
//表单提交查询
function selectSubmit(){
	if($ID('freight.fromName').value.isEmpty()){
		showMsgbox("请填写始发地");		
		return;
	}
	if($ID('freight.toName').value.isEmpty()){
		showMsgbox("请填写目的地");
		return;
	}	
	document.forms['pageForm'].submit();	
}

//空运报价获取
function airPrice(airStr,id){
	var air = eval('(' + airStr + ')');

	if(air.airPeriodValidity==""&&air.airCurrency==""){
		removeIds+=id+";";
		return;
	}
		
	var infoObj = $ID(id+".info");
	var titleObj = $ID(id+".title");

	//地面价格组装
	var airInfoUl = document.createElement("ul");
	var airFromLi = document.createElement("li");
		airFromLi.className="search_result_rightborder";
		airFromLi.innerHTML="地面价格：<br/>"+air.airFromInfo;
	var airToLi = document.createElement("li");
		airToLi.innerHTML="地面价格：<br/>"+air.airToInfo;
		airInfoUl.appendChild(airFromLi);
		airInfoUl.appendChild(airToLi);
	infoObj.appendChild(airInfoUl);

	//明细价格组装
	var airListUl = document.createElement("ul");
		airListUl.className="list";
	var airListLi = document.createElement("li");
		airListLi.innerHTML="SSC："+air.airSsc;
		airListUl.appendChild(airListLi);
		
		airListLi = document.createElement("li");
		airListLi.innerHTML="FSC："+air.airFsc;
		airListUl.appendChild(airListLi);

		airListLi = document.createElement("li");
		airListLi.innerHTML="MIN："+air.airMin+"/SET";
		airListUl.appendChild(airListLi);

		airListLi = document.createElement("li");
		airListLi.innerHTML="小于45Kg："+air.airLess45+"/Kg";
		airListUl.appendChild(airListLi);

		airListLi = document.createElement("li");
		airListLi.innerHTML="大于45Kg："+air.airMore45+"/Kg";
		airListUl.appendChild(airListLi);

		airListLi = document.createElement("li");
		airListLi.innerHTML="大于100Kg："+air.airMore100+"/Kg";
		airListUl.appendChild(airListLi);

		airListLi = document.createElement("li");
		airListLi.innerHTML="大于300Kg："+air.airMore300+"/Kg";
		airListUl.appendChild(airListLi);

		airListLi = document.createElement("li");
		airListLi.innerHTML="大于500Kg："+air.airMore500+"/Kg";
		airListUl.appendChild(airListLi);

		airListLi = document.createElement("li");
		airListLi.innerHTML="大于1000Kg："+air.airMore1000+"/Kg";
		airListUl.appendChild(airListLi);
		
	infoObj.appendChild(airListUl);

	//航班信息组装
	var airInfoDiv = document.createElement("div");
	airInfoDiv.style.width="808px";
		airInfoDiv.innerHTML="航班信息：<br/>"+air.airInfo;
	infoObj.appendChild(airInfoDiv);

	//标题文字
	titleObj.innerHTML="航空 "+air.airHc;

	$ID(id+".currency").innerHTML="币种："+air.airCurrency;
	$ID(id+".periodValidity").innerHTML="有效期至："+air.airPeriodValidity;
}

//路运报价获取
function landPrice(landStr,id){
	var land = eval('(' + landStr + ')');
	var infoObj = $ID(id+".info");
	var titleObj = $ID(id+".title");

	if(land.landPeriodValidity==""&&land.landCurrency==""){
		removeIds+=id+";";
		return;
	}
	
	//地面价格组装
	var landInfoUl = document.createElement("ul");
	var landFromLi = document.createElement("li");
		landFromLi.className="search_result_rightborder";
		landFromLi.innerHTML="地面价格：<br/>"+land.landFromInfo;
	var landToLi = document.createElement("li");
		landToLi.innerHTML="地面价格：<br/>"+land.landToInfo;
		landInfoUl.appendChild(landFromLi);
		landInfoUl.appendChild(landToLi);
	infoObj.appendChild(landInfoUl);

	//明细价格组装
	var landListUl = document.createElement("ul");
		landListUl.className="list";
	var landListLi = document.createElement("li");
		landListLi.innerHTML="拼车："+land.landAssemblyKg+"/Kg";
		landListUl.appendChild(landListLi);
		
		landListLi = document.createElement("li");
		landListLi.innerHTML="拼车："+land.landAssemblyT+"/T";
		landListUl.appendChild(landListLi);
		
		landListLi = document.createElement("li");
		landListLi.innerHTML="拼车："+land.landAssemblyCbm+"/CBM";
		landListUl.appendChild(landListLi);

		landListLi = document.createElement("li");
		landListLi.innerHTML="8T车："+land.land5T;
		landListUl.appendChild(landListLi);

		landListLi = document.createElement("li");
		landListLi.innerHTML="10T车："+land.land5T;
		landListUl.appendChild(landListLi);

		landListLi = document.createElement("li");
		landListLi.innerHTML="12T车："+land.land12_5T;
		landListUl.appendChild(landListLi);

		if(land.landOthName!=""&&land.landOthValue!=0){
			landListLi = document.createElement("li");
			if(land.landOthName.isNum()){
				landListLi.innerHTML=land.landOthName+"T车："+land.landOthValue;
			}
			else{
				landListLi.innerHTML=land.landOthName+"车："+land.landOthValue;
			}
			landListUl.appendChild(landListLi);
		}
		

		infoObj.appendChild(landListUl);

		//标题文字
		titleObj.innerHTML="陆运";

		$ID(id+".currency").innerHTML="币种："+land.landCurrency;
		$ID(id+".periodValidity").innerHTML="有效期至："+land.landPeriodValidity;
}

//海运报价获取
function seaPrice(seaStr,id){
	var sea = eval('(' + seaStr + ')');	
	var infoObj = $ID(id+".info");
	var titleObj = $ID(id+".title");

	if(sea.seaPeriodValidity==""&&sea.seaCurrency==""){
		removeIds+=id+";";
		return;
	}
	
		//船务信息组装
		var seaInfoDiv = document.createElement("div");
			seaInfoDiv.innerHTML="拼仓："+sea.seaAssemblyT+"/T<br/>"+sea.seaAssemblyTInfo;
			infoObj.appendChild(seaInfoDiv);

			seaInfoDiv = document.createElement("div");
			seaInfoDiv.innerHTML="拼仓："+sea.seaAssemblyCbm+"/CBM<br/>"+sea.seaAssemblyCbmInfo;
			infoObj.appendChild(seaInfoDiv);
			
			seaInfoDiv = document.createElement("div");
			seaInfoDiv.innerHTML="20DR："+sea.sea20Dr+"/箱<br/>"+sea.sea20DrInfo;
			infoObj.appendChild(seaInfoDiv);

			seaInfoDiv = document.createElement("div");
			seaInfoDiv.innerHTML="40DR："+sea.sea40Dr+"/箱<br/>"+sea.sea40DrInfo;
			seaInfoDiv.style.borderBottom="0px";
			infoObj.appendChild(seaInfoDiv);
			
			seaInfoDiv = document.createElement("div");
			seaInfoDiv.style.borderBottom="0px";
			seaInfoDiv.innerHTML="40HQ："+sea.sea40Hq+"/箱<br/>"+sea.sea40HqInfo;
			infoObj.appendChild(seaInfoDiv);
			

		//标题文字
		titleObj.innerHTML="海运";
		
		$ID(id+".currency").innerHTML="币种："+sea.seaCurrency;
		$ID(id+".periodValidity").innerHTML="有效期至："+sea.seaPeriodValidity;
}

//初始化标题内容
var removeIds="";
function initTitle(){
	var freightPriceInfo = $N("freightPriceInfo");
	var waySelect = $ID("waySelect").value;
	for(var i=0;i<freightPriceInfo.length;i++){
		var freight = freightPriceInfo[i];
		if(waySelect=='air'){			
			airPrice(freight.getAttribute("air"),freight.id);
		}
		else if(waySelect=='land'){			
			landPrice(freight.getAttribute("land"),freight.id);
		}
		else if(waySelect=='sea'){			
			seaPrice(freight.getAttribute("sea"),freight.id);
		}		
	}
	
	if(removeIds!=""){
		removeIds = removeIds.substring(0,removeIds.length-1);
		var removeId = removeIds.split(";");
		for(var i=0;i<removeId.length;i++){
			$ID("search_resultlist").removeChild($ID(removeId[i]+".box"));			
		}
	}
}
initTitle();
</script>
</html>