<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/account_supervise.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/PermissionDwr.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>

<title>添加模块</title>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
  	<div id="mid">
   	<jsp:include page="/index.do?method=left" flush="true"></jsp:include>
   	<div id="mainpage">
   	<div id="location"> 当前位置：<span class="fp"><a href="#">我的桌面</a></span><span><a href="#">系统设置</a></span><span><a href="#">模块设置</a></span> </div>
      <div class="button_TOP">
        <h2 class="account_title_new">管理模块：</h2><div id="inpageinfo"></div>
      </div>
		<form name="pageForm" action="" id="addModel" method="POST" >
		<div>
		<ul>
			<li>隶属模块</li>
			<li>
				<select name="ltsMo.module" id="module">
		            <script language="javascript" type="text/javascript">
		            var select=document.getElementById("module");
		        	var	option = document.createElement("OPTION");
		        	var	options = document.createElement("OPTION");
		        	options.value = "";
		        	options.innerHTML="";
		        	select.appendChild(options);
		            PermissionDwr.getModel("1",function(data){
					for(var i=0;i<data.length;i++)
					{
						var	options = document.createElement("OPTION");
						options.value=data[i][0];
						options.innerHTML=data[i][1];
						select.appendChild(options);
						
					}
				});
					</script>
				</select>
			</li>
		</ul>
		<ul>
			<li>模块中文名称</li>
			<li><input name="ltsMo.moduleCode" type="text" id="moduleCode" /></li>
		</ul>
		<ul>
			<li>模块地址</li>
			<li><input name="ltsMo.moduleUrl" type="text" id="moduleUrl" /></li>
		</ul>
		<ul>
			<li>模块编码</li>
			<li><input name="ltsMo.moduleName" type="text" id="moduleName" /></li>
		</ul>
		<ul>
			<li>默认值</li>
			<li><input name="ltsMo.moduleLevel" type="text" id="moduleLevel" /></li>
		</ul>
		<ul>
			<li>模块类型</li>
			<li><select name="ltsMo.moduleType" id="moduleType">
				  <option value="boolean">布尔值</option>
				  <option value="text">文本值</option>
				  <option value="sel">下拉选项</option>
				</select>
			</li>
		</ul>
		<ul>
			<li>模块优先级</li>
			<li><input name="ltsMo.priority" type="text" id="priority" /></li>
		</ul>
	</div>
		<label>
		<input type="button" name="button" id="button" value="提交" />
		</label>
		</form>
		<script language="javascript" type="text/javascript">
		$("#button").click(function(){
			document.forms['addModel'].action="<%=request.getContextPath() %>/permission.do?method=newModule";
			document.forms['addModel'].submit();
		});
		</script>
		</div>
	</div>
	 <jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
</html>