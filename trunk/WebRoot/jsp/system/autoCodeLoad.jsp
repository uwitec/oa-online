<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自动编号配置</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/popup.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
</head>
<body>
<form method="POST" action="<%=request.getContextPath() %>/system.do?method=codeSave" name="pageForm">
<input type="hidden" id="countSetup" value="<c:if test="${fn:contains(form.wac.codeConfig,'Counter')}">1</c:if>" >
<input type="hidden" name="wac.codeId" value="${form.wac.codeId}">
<table cellspacing="0" cellpadding="0">
  	<tr>
    	<th class="th_center" colspan="3"><input readonly name="wac.codeConfig" type="text" id="codeConfig" value="${form.wac.codeConfig}" size="36"/>&nbsp;<a href="#" id="codeConfigCle">清除</a></th>
    </tr>
    <tr>
    	<th>&nbsp;</th>
    </tr>
    <tr>
    	<th class="th_right">固定字符：</th>
    	<td><input type="text" id="codeString" value=""/></td>
        <td><a href="#" id="insertChar">插入字符</a></td>
  	</tr>
    <tr>
    	<th class="th_center" colspan="3">时间编码</th>
    </tr>
    <tr>
    	<th class="th_right">时间格式：</th>
    	<td><input type="text" id="dateCode" readonly value=""/></td>
        <td><a href="#" id="insertDate">插入时间</a></td>
  	</tr>
  	<tr>
    	<th class="th_center" colspan="3"><input type="checkbox" id="dateYY">二位年号 &nbsp;<input type="checkbox" id="dateYYYY">四位年号&nbsp;<input type="checkbox" id="dateMM">月号&nbsp;<input type="checkbox" id="dateDD">日号&nbsp;</th>
  	</tr>
    <tr>
    	<th class="th_center" colspan="3">计数器编码</th>
  	</tr>
    <tr>
    	<th class="th_right">当前计数：</th>
    	<td><input type="text" name="wac.count" id="count" value="${form.wac.count}"/></td>
    	<td><a href="#" id="insertCount">插入计数器</a></td>
  	</tr>
    <tr>
    	<th class="th_right">计数步长：</th>
    	<td colspan="2"><input type="text" name="wac.step" id="step" value="${form.wac.step}"/></td>
  	</tr>
    <tr>
    	<th class="th_right">计数位数：</th>
    	<td colspan="2"><input type="text" id="bit"/></td>
  	</tr>
    </tr>
        <tr>
    	<th class="th_right">重置周期：</th>
    	<td colspan="2">
        <select name="wac.reset" id="reset">
          <option value="">=请选择=</option>
          <option value="0"<c:if test="${form.wac.reset==0}"> selected</c:if>>每年重置</option>
          <option value="1"<c:if test="${form.wac.reset==1}"> selected</c:if>>每月重置</option>
          <option value="2"<c:if test="${form.wac.reset==2}"> selected</c:if>>每天重置</option>
          <option value="3"<c:if test="${form.wac.reset==3}"> selected</c:if>>每时重置</option>
        </select>
        </td>
  	</tr>
  	<tr>
  		<th>启用单号：</th>
  		<td colspan="2"><input type="checkbox" name="wac.start" <c:if test="${form.wac.start==1}">checked</c:if> value="1"></td>
  	</tr>
  	<tr><th colspan="3" id="errMsg"></th></tr>
</table>
</form>
</body>
<script type="text/javascript">
//清除编码格式
document.getElementById('codeConfigCle').onclick=function(){
	document.getElementById('countSetup').value="";
	document.getElementById('codeConfig').value="";	
}
//插入字符串
document.getElementById('insertChar').onclick=function(){
	var errMsg = document.getElementById('errMsg');
	errMsg.innerHTML="";
	var codeString = document.getElementById('codeString');
	if(codeString.value.isEmpty()){
		errMsg.innerHTML="请填写插入字符！";
		return;
		}
	var codeConfig = document.getElementById('codeConfig');
	if(codeConfig.value.length>0){
		codeConfig.value = codeConfig.value + "+";
		}
	codeConfig.value = codeConfig.value + "'" + codeString.value + "'";
	codeString.value="";
}

//时间拼装
document.getElementById('dateYY').onclick=function(){
	if(this.checked){
		var dateYYYY = document.getElementById('dateYYYY');
		dateYYYY.checked=false;
	}
	setDateCode();
}
document.getElementById('dateYYYY').onclick=function(){
	if(this.checked){
		var dateYY = document.getElementById('dateYY');
		dateYY.checked=false;
	}
	setDateCode();
}
document.getElementById('dateMM').onclick=function(){
	setDateCode();
}
document.getElementById('dateDD').onclick=function(){
	setDateCode();
}

//设置时间格式
function setDateCode(){
	var errMsg = document.getElementById('errMsg');
	var dateYYYY = document.getElementById('dateYYYY');
	var dateYY = document.getElementById('dateYY');
	var dateMM = document.getElementById('dateMM');
	var dateDD = document.getElementById('dateDD');
	var dateCode = document.getElementById('dateCode');
	errMsg.innerHTML="";
	
	dateCode.value="Date(";	
	if(dateYYYY.checked){
		dateCode.value=dateCode.value+"yyyy";
		}
	if(dateYY.checked){
		dateCode.value=dateCode.value+"yy";
		}
	if(dateMM.checked){
		dateCode.value=dateCode.value+"MM";
		}
	if(dateDD.checked){
		dateCode.value=dateCode.value+"dd";
		}
	dateCode.value=dateCode.value+")";
	if(!(dateYYYY.checked||dateYY.checked||dateMM.checked||dateDD.checked)){
		dateCode.value="";
		}
}

//插入时间编码
document.getElementById('insertDate').onclick=function(){
	var dateCode = document.getElementById('dateCode');
	var errMsg = document.getElementById('errMsg');
	if(dateCode.value==""){
			errMsg.innerHTML="请选择时间格式！";
			return;
		}
	var codeConfig = document.getElementById('codeConfig');
	if(codeConfig.value.length>0){
		codeConfig.value = codeConfig.value + "+";
		}
	codeConfig.value = codeConfig.value + dateCode.value;
	dateCode.value="";
	var dateYYYY = document.getElementById('dateYYYY');
	var dateYY = document.getElementById('dateYY');
	var dateMM = document.getElementById('dateMM');
	var dateDD = document.getElementById('dateDD');
	dateYYYY.checked=false;
	dateYY.checked=false;
	dateMM.checked=false;
	dateDD.checked=false;
}
//插入计数器

document.getElementById('insertCount').onclick=function(){
	var bit = document.getElementById('bit');
	var errMsg = document.getElementById('errMsg');
	var countSetup = document.getElementById('countSetup');
	errMsg.innerHTML="";
	if(countSetup.value=="1"){
		errMsg.innerHTML="已经插入过计数器了！";
		return;
	}
	if(!bit.value.isNum()){
			errMsg.innerHTML="请正确填写计数器位数！";
			return;
		}
	var codeConfig = document.getElementById('codeConfig');
	if(codeConfig.value.length>0){
		codeConfig.value = codeConfig.value + "+";
		}
	codeConfig.value = codeConfig.value + "Counter(";
	for(var i=0;i<parseInt(bit.value);i++){
		codeConfig.value = codeConfig.value + "0";
		}
	codeConfig.value = codeConfig.value + ")";
	bit.value="";	
	countSetup.value="1";
}
</script>
</html>
