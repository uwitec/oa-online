<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/leftnav.js"></script>

<title>系统配置</title>
</head>
<body>
<div id="layout">
  <jsp:include page="/index.do?method=head" flush="true"></jsp:include>
	<div id="mid">
    <jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    <div id="mainpage">
    <div id="location"> 当前位置：<span class="fp"><a href="index.do?method=index">我的桌面</a></span><span><a href="#">系统设置</a></span><span><a href="#">系统配置</a></span></div>
		<form action="<%=request.getContextPath() %>/system.do?method=saveConfig" name="pageForm" method="post">
    	<div class="index_pic">系统配置管理：</div>
    	<div id="inpageinfo"></div>
    	<div class="system_configuration_box">
	      <div id="system">
	      <table cellspacing="0" cellpadding="0">
	        <tr>
	          <td>每页显示条数：</td>
	          <td>
	            <input type="text" name="conf.rowsPerpage" id="conf.rowsPerpage" value="${form.conf.rowsPerpage}"/>
	            </td>
	          <td>&nbsp;</td>
	        </tr>     
	        <tr>
	 		  <td>登陆超时：</td>
	          <td>
	          <select name="conf.sessionInactiveInterval">
		          <option value="600" <c:if test="${form.conf.sessionInactiveInterval==600}">selected</c:if>>10分钟</option>
		          <option value="900" <c:if test="${form.conf.sessionInactiveInterval==900||form.conf.sessionInactiveInterval==null}">selected</c:if>>15分钟</option>
		          <option value="1800" <c:if test="${form.conf.sessionInactiveInterval==1800}">selected</c:if>>30分钟</option>
		          <option value="3600" <c:if test="${form.conf.sessionInactiveInterval==3600}">selected</c:if>>60分钟</option>
	          </select>
			  </td>
	          <td>&nbsp;</td>
	        </tr>
	      </table>
	      </div>
           <div id="com_all_delete02">
              <button id="saveConfig" onClick="" class="button_bg">保存</button><!-- <a href="#" id="saveConfig"><span>保存</span></a> -->
              <button onClick="" class="button_bg">退出</button>
           </ul>
         </div>
         </div>
	   </form>
  </div>
</div>
<jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
<script type="text/javascript">
document.getElementById("saveConfig").onclick=function(){
	var rowsPerpage = document.getElementById("conf.rowsPerpage");
	var inpageinfo = document.getElementById("inpageinfo");
	if(!rowsPerpage.value.isNum()||parseInt(rowsPerpage.value)==0){
		inpageinfo.innerHTML="每页条数只能为大于零的数值类型！";
		return;
		}
	document.forms['pageForm'].submit();
}
</script>
</html>
