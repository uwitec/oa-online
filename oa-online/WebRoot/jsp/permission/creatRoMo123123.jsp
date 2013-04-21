<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/general.css" />
<title></title>
</head>
<body>
<div id="layout">
<div id="mainpage">
<form action="" method="post" id="creatRM">
<input type="hidden" value="${role.id }" id="ltsRoPer" name="ltsRoPer">
<input type="hidden" value="${modify}" id="isModify" name="isModify">
	
	<h2>文档模块</h2>
	<div name="document" id="list_table">
	<ul class="list_tatle"><li>文件上传大小（单位：MB）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[0].roleCode" id="stocks[0].roleCode">
		<input type="hidden" value="${list[0].id }" name="stocks[0].moduleCode" id="stocks[0].moduleCode">
		<input type="hidden" value="${list[0].moduleName}" name="stocks[0].moduleName" id="stocks[0].moduleName" >
		<li><input type="text" value="${list[0].moduleLevel }" name="stocks[0].moduleLevel" id="stocks[0].moduleLevel"></li>
	</ul>
	<ul class="list_tatle"><li>文件上传类型（例如：*.rar，多类型可用;隔开）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[1].roleCode" id="stocks[1].roleCode">
		<input type="hidden" value="${list[1].id }" name="stocks[1].moduleCode" id="stocks[1].moduleCode">
		<input type="hidden" value="${list[1].moduleName}" name="stocks[1].moduleName" id="stocks[1].moduleName" >
		<li><input type="text" value="${list[1].moduleLevel }" name="stocks[1].moduleLevel" id="stocks[1].moduleLevel"></li>
	</ul>
	<ul class="list_tatle"><li>是否可以管理文档（只能管理当前用户发布的文档，此权限与4互斥）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[2].roleCode" id="stocks[2].roleCode">
		<input type="hidden" value="${list[2].id }" name="stocks[2].moduleCode" id="stocks[2].moduleCode">
		<input type="hidden" value="${list[2].moduleName}" name="stocks[2].moduleName" id="stocks[2].moduleName" >
		<li>可管理<input type="radio" value="true" name="stocks[2].moduleLevel" id="stocks[2].moduleLevel" <c:if test="${list[2].moduleLevel =='true'}">checked</c:if>></li>
		<li>不可管理<input type="radio" value="false" name="stocks[2].moduleLevel" id="stocks[2].moduleLevel" <c:if test="${list[2].moduleLevel =='false'}">checked</c:if>></li>
	</ul>
	<ul class="list_tatle"><li>是否可以管理全部文档   （此权限与3互斥）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[3].roleCode" id="stocks[3].roleCode">
		<input type="hidden" value="${list[3].id }" name="stocks[3].moduleCode" id="stocks[3].moduleCode">
		<input type="hidden" value="${list[3].moduleName}" name="stocks[3].moduleName" id="stocks[3].moduleName" >
		<li>可管理<input type="radio" value="true" name="stocks[3].moduleLevel" id="stocks[3].moduleLevel" <c:if test="${list[3].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可管理<input type="radio" value="false" name="stocks[3].moduleLevel" id="stocks[3].moduleLevel" <c:if test="${list[3].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	<ul class="list_tatle"><li>是否可以查看文档（只包含本角色可见文档，此权限与6互斥）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[4].roleCode" id="stocks[4].roleCode">
		<input type="hidden" value="${list[4].id }" name="stocks[4].moduleCode" id="stocks[4].moduleCode">
		<input type="hidden" value="${list[4].moduleName}" name="stocks[4].moduleName" id="stocks[4].moduleName" >
		<li>可查看<input type="radio" value="true" name="stocks[4].moduleLevel" id="stocks[4].moduleLevel" <c:if test="${list[4].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可查看<input type="radio" value="false" name="stocks[4].moduleLevel" id="stocks[4].moduleLevel" <c:if test="${list[4].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	<ul class="list_tatle"><li>是否可以查看所有文档（此权限与5互斥）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[5].roleCode" id="stocks[5].roleCode">
		<input type="hidden" value="${list[5].id }" name="stocks[5].moduleCode" id="stocks[5].moduleCode">
		<input type="hidden" value="${list[5].moduleName}" name="stocks[5].moduleName" id="stocks[5].moduleName" >
		<li>可查看<input type="radio" value="true" name="stocks[5].moduleLevel" id="stocks[5].moduleLevel" <c:if test="${list[5].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可查看<input type="radio" value="false" name="stocks[5].moduleLevel" id="stocks[5].moduleLevel" <c:if test="${list[5].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	<ul class="list_tatle"><li>是否可以下载文件</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[6].roleCode" id="stocks[6].roleCode">
		<input type="hidden" value="${list[6].id }" name="stocks[6].moduleCode" id="stocks[6].moduleCode">
		<input type="hidden" value="${list[6].moduleName}" name="stocks[6].moduleName" id="stocks[6].moduleName" >
		<li>可下载<input type="radio" value="true" name="stocks[6].moduleLevel" id="stocks[6].moduleLevel" <c:if test="${list[6].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可下载<input type="radio" value="false" name="stocks[6].moduleLevel" id="stocks[6].moduleLevel" <c:if test="${list[6].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	<ul class="list_tatle"><li>是否可以添加分类</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[7].roleCode" id="stocks[7].roleCode">
		<input type="hidden" value="${list[7].id }" name="stocks[7].moduleCode" id="stocks[7].moduleCode">
		<input type="hidden" value="${list[7].moduleName}" name="stocks[7].moduleName" id="stocks[7].moduleName" >
		<li>可添加<input type="radio" value="true" name="stocks[7].moduleLevel" id="stocks[7].moduleLevel" <c:if test="${list[7].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可添加<input type="radio" value="false" name="stocks[7].moduleLevel" id="stocks[7].moduleLevel" <c:if test="${list[7].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	</div>
	<BR />
	<h2>相册模块</h2>
	<div id="list_table" name="photo">
	<ul class="list_tatle"><li>文件上传大小	（单位：MB）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[8].roleCode" id="stocks[8].roleCode">
		<input type="hidden" value="${list[8].id }" name="stocks[8].moduleCode" id="stocks[8].moduleCode">
		<input type="hidden" value="${list[8].moduleName}" name="stocks[8].moduleName" id="stocks[8].moduleName" >
		<li><input type="text" value="${list[8].moduleLevel }" name="stocks[8].moduleLevel" id="stocks[8].moduleLevel"></li>
	</ul>
	
	<ul class="list_tatle"><li>文件上传类型（例如：*.jpg，多类型可用;隔开）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[9].roleCode" id="stocks[9].roleCode">
		<input type="hidden" value="${list[9].id }" name="stocks[9].moduleCode" id="stocks[9].moduleCode">
		<input type="hidden" value="${list[9].moduleName}" name="stocks[9].moduleName" id="stocks[9].moduleName" >
		<li><input type="text" value="${list[9].moduleLevel }" name="stocks[9].moduleLevel" id="stocks[9].moduleLevel"></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以管理相册（只能管理当前用户发布的相册，此权限与4互斥）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[10].roleCode" id="stocks[10].roleCode">
		<input type="hidden" value="${list[10].id }" name="stocks[10].moduleCode" id="stocks[10].moduleCode">
		<input type="hidden" value="${list[10].moduleName}" name="stocks[10].moduleName" id="stocks[10].moduleName" >
		<li>可管理<input type="radio" value="true" name="stocks[10].moduleLevel" id="stocks[10].moduleLevel" <c:if test="${list[10].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可管理<input type="radio" value="false" name="stocks[10].moduleLevel" id="stocks[10].moduleLevel" <c:if test="${list[10].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以管理全部相册（此权限与3互斥）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[11].roleCode" id="stocks[11].roleCode">
		<input type="hidden" value="${list[11].id }" name="stocks[11].moduleCode" id="stocks[11].moduleCode">
		<input type="hidden" value="${list[11].moduleName}" name="stocks[11].moduleName" id="stocks[11].moduleName" >
		<li>可管理<input type="radio" value="true" name="stocks[11].moduleLevel" id="stocks[11].moduleLevel" <c:if test="${list[11].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可管理<input type="radio" value="false" name="stocks[11].moduleLevel" id="stocks[11].moduleLevel" <c:if test="${list[11].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以查看相册（只包含本角色可见相册，此权限与6互斥）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[12].roleCode" id="stocks[12].roleCode">
		<input type="hidden" value="${list[12].id }" name="stocks[12].moduleCode" id="stocks[12].moduleCode">
		<input type="hidden" value="${list[12].moduleName}" name="stocks[12].moduleName" id="stocks[12].moduleName" >
		<li>可查看<input type="radio" value="true" name="stocks[12].moduleLevel" id="stocks[12].moduleLevel" <c:if test="${list[12].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可查看<input type="radio" value="false" name="stocks[12].moduleLevel" id="stocks[12].moduleLevel" <c:if test="${list[12].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以查看所有相册（此权限与5互斥）</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[13].roleCode" id="stocks[13].roleCode">
		<input type="hidden" value="${list[13].id }" name="stocks[13].moduleCode" id="stocks[13].moduleCode">
		<input type="hidden" value="${list[13].moduleName}" name="stocks[13].moduleName" id="stocks[13].moduleName" >
		<li>可查看<input type="radio" value="true" name="stocks[13].moduleLevel" id="stocks[13].moduleLevel" <c:if test="${list[13].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可查看<input type="radio" value="false" name="stocks[13].moduleLevel" id="stocks[13].moduleLevel" <c:if test="${list[13].moduleLevel  =='false'}">checked</c:if>></li>
	
	</ul>
	
	<ul class="list_tatle"><li>是否可以添加分类</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[14].roleCode" id="stocks[14].roleCode">
		<input type="hidden" value="${list[14].id }" name="stocks[14].moduleCode" id="stocks[14].moduleCode">
		<input type="hidden" value="${list[14].moduleName}" name="stocks[14].moduleName" id="stocks[14].moduleName" >
		<li>可添加<input type="radio" value="true" name="stocks[14].moduleLevel" id="stocks[14].moduleLevel" <c:if test="${list[14].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可添加<input type="radio" value="false" name="stocks[14].moduleLevel" id="stocks[14].moduleLevel" <c:if test="${list[14].moduleLevel  =='false'}">checked</c:if>></li>
	
	</ul>
	</div>
	<BR />
	
	<h2 id="album">报价模块</h2>
	<div id="list_table" name="album">
	<ul class="list_tatle"><li>是否可以进入报价查询模块</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[15].roleCode" id="stocks[15].roleCode">
		<input type="hidden" value="${list[15].id }" name="stocks[15].moduleCode" id="stocks[15].moduleCode">
		<input type="hidden" value="${list[15].moduleName}" name="stocks[15].moduleName" id="stocks[15].moduleName" >
		<li>可进入<input type="radio" value="true" name="stocks[15].moduleLevel" id="stocks[15].moduleLevel" <c:if test="${list[15].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可进入<input type="radio" value="false" name="stocks[15].moduleLevel" id="stocks[15].moduleLevel" <c:if test="${list[15].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以添加报价</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[16].roleCode" id="stocks[16].roleCode">
		<input type="hidden" value="${list[16].id }" name="stocks[16].moduleCode" id="stocks[16].moduleCode">
		<input type="hidden" value="${list[16].moduleName}" name="stocks[16].moduleName" id="stocks[16].moduleName" >
		<li>可添加<input type="radio" value="true" name="stocks[16].moduleLevel" id="stocks[16].moduleLevel" <c:if test="${list[16].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可添加<input type="radio" value="false" name="stocks[16].moduleLevel" id="stocks[16].moduleLevel" <c:if test="${list[16].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可管理自己的报价</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[17].roleCode" id="stocks[17].roleCode">
		<input type="hidden" value="${list[17].id }" name="stocks[17].moduleCode" id="stocks[17].moduleCode">
		<input type="hidden" value="${list[17].moduleName}" name="stocks[17].moduleName" id="stocks[17].moduleName" >
		<li>可管理<input type="radio" value="true" name="stocks[17].moduleLevel" id="stocks[17].moduleLevel" <c:if test="${list[17].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可管理<input type="radio" value="false" name="stocks[17].moduleLevel" id="stocks[17].moduleLevel" <c:if test="${list[17].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	<ul class="list_tatle"><li>是否可管理全部报价</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[35].roleCode" id="stocks[35].roleCode">
		<input type="hidden" value="${list[35].id }" name="stocks[35].moduleCode" id="stocks[35].moduleCode">
		<input type="hidden" value="${list[35].moduleName}" name="stocks[35].moduleName" id="stocks[35].moduleName" >
		<li>可管理<input type="radio" value="true" name="stocks[35].moduleLevel" id="stocks[35].moduleLevel" <c:if test="${list[35].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可管理<input type="radio" value="false" name="stocks[35].moduleLevel" id="stocks[35].moduleLevel" <c:if test="${list[35].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
		<BR />
	<h2 id="company">公司模块</h2>
	<ul class="list_tatle"><li>是否可以添加公司模块</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[18].roleCode" id="stocks[18].roleCode">
		<input type="hidden" value="${list[18].id }" name="stocks[18].moduleCode" id="stocks[18].moduleCode">
		<input type="hidden" value="${list[18].moduleName}" name="stocks[18].moduleName" id="stocks[18].moduleName" >
		<li>可添加公司模块<input type="radio" value="true" name="stocks[18].moduleLevel" id="stocks[18].moduleLevel" <c:if test="${list[18].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可添加公司模块<input type="radio" value="false" name="stocks[18].moduleLevel" id="stocks[18].moduleLevel" <c:if test="${list[18].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以删除公司模块</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[19].roleCode" id="stocks[19].roleCode">
		<input type="hidden" value="${list[19].id }" name="stocks[19].moduleCode" id="stocks[19].moduleCode">
		<input type="hidden" value="${list[19].moduleName}" name="stocks[19].moduleName" id="stocks[19].moduleName" >
		<li>可删除公司模块<input type="radio" value="true" name="stocks[19].moduleLevel" id="stocks[19].moduleLevel" <c:if test="${list[19].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可删除公司模块<input type="radio" value="false" name="stocks[19].moduleLevel" id="stocks[19].moduleLevel" <c:if test="${list[19].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以修改公司模块</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[20].roleCode" id="stocks[20].roleCode">
		<input type="hidden" value="${list[20].id }" name="stocks[20].moduleCode" id="stocks[20].moduleCode">
		<input type="hidden" value="${list[20].moduleName}" name="stocks[20].moduleName" id="stocks[20].moduleName" >
		<li>可修改公司模块<input type="radio" value="true" name="stocks[20].moduleLevel" id="stocks[20].moduleLevel" <c:if test="${list[20].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可修改公司模块<input type="radio" value="false" name="stocks[20].moduleLevel" id="stocks[20].moduleLevel" <c:if test="${list[20].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以查看公司模块</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[21].roleCode" id="stocks[21].roleCode">
		<input type="hidden" value="${list[21].id }" name="stocks[21].moduleCode" id="stocks[21].moduleCode">
		<input type="hidden" value="${list[21].moduleName}" name="stocks[21].moduleName" id="stocks[21].moduleName" >
		<li>可以查看公司模块<input type="radio" value="true" name="stocks[21].moduleLevel" id="stocks[21].moduleLevel" <c:if test="${list[21].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可以查看公司模块<input type="radio" value="false" name="stocks[21].moduleLevel" id="stocks[21].moduleLevel" <c:if test="${list[21].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	<BR />
	<h2 id="department">部门模块</h2>
	
	<ul class="list_tatle"><li>是否可以添加部门模块</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[22].roleCode" id="stocks[22].roleCode">
		<input type="hidden" value="${list[22].id }" name="stocks[22].moduleCode" id="stocks[22].moduleCode">
		<input type="hidden" value="${list[22].moduleName}" name="stocks[22].moduleName" id="stocks[22].moduleName" >
		<li>可添加部门模块<input type="radio" value="true" name="stocks[22].moduleLevel" id="stocks[22].moduleLevel" <c:if test="${list[22].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可添加部门模块<input type="radio" value="false" name="stocks[22].moduleLevel" id="stocks[22].moduleLevel" <c:if test="${list[22].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以删除部门模块</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[23].roleCode" id="stocks[23].roleCode">
		<input type="hidden" value="${list[23].id }" name="stocks[23].moduleCode" id="stocks[23].moduleCode">
		<input type="hidden" value="${list[23].moduleName}" name="stocks[23].moduleName" id="stocks[23].moduleName" >
		<li>可删除部门模块<input type="radio" value="true" name="stocks[23].moduleLevel" id="stocks[23].moduleLevel" <c:if test="${list[23].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可删除部门模块<input type="radio" value="false" name="stocks[23].moduleLevel" id="stocks[23].moduleLevel" <c:if test="${list[23].moduleLevel  =='false'}">checked</c:if>></li>
	
	</ul>
	
	<ul class="list_tatle"><li>是否可以修改部门模块</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[24].roleCode" id="stocks[24].roleCode">
		<input type="hidden" value="${list[24].id }" name="stocks[24].moduleCode" id="stocks[24].moduleCode">
		<input type="hidden" value="${list[24].moduleName}" name="stocks[24].moduleName" id="stocks[24].moduleName" >
		<li>可修改部门模块<input type="radio" value="true" name="stocks[24].moduleLevel" id="stocks[24].moduleLevel" <c:if test="${list[24].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可修改部门模块<input type="radio" value="false" name="stocks[24].moduleLevel" id="stocks[24].moduleLevel" <c:if test="${list[24].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以查看部门模块</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[25].roleCode" id="stocks[25].roleCode">
		<input type="hidden" value="${list[25].id }" name="stocks[25].moduleCode" id="stocks[25].moduleCode">
		<input type="hidden" value="${list[25].moduleName}" name="stocks[25].moduleName" id="stocks[25].moduleName" >
		<li>可查看部门模块<input type="radio" value="true" name="stocks[25].moduleLevel" id="stocks[25].moduleLevel" <c:if test="${list[25].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可查看部门模块<input type="radio" value="false" name="stocks[25].moduleLevel" id="stocks[25].moduleLevel" <c:if test="${list[25].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	<BR />
	<h2 id="employee">员工模块 </h2>
	
	<ul class="list_tatle"><li>是否可以添加员工</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[26].roleCode" id="stocks[26].roleCode">
		<input type="hidden" value="${list[26].id }" name="stocks[26].moduleCode" id="stocks[26].moduleCode">
		<input type="hidden" value="${list[26].moduleName}" name="stocks[26].moduleName" id="stocks[26].moduleName" >
		<li>可添加员工<input type="radio" value="true" name="stocks[26].moduleLevel" id="stocks[26].moduleLevel" <c:if test="${list[26].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可添加员工<input type="radio" value="false" name="stocks[26].moduleLevel" id="stocks[26].moduleLevel" <c:if test="${list[26].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以删除员工</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[27].roleCode" id="stocks[27].roleCode">
		<input type="hidden" value="${list[27].id }" name="stocks[27].moduleCode" id="stocks[27].moduleCode">
		<input type="hidden" value="${list[27].moduleName}" name="stocks[27].moduleName" id="stocks[27].moduleName" >
		<li>可删除员工<input type="radio" value="true" name="stocks[27].moduleLevel" id="stocks[27].moduleLevel" <c:if test="${list[27].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可删除员工<input type="radio" value="false" name="stocks[27].moduleLevel" id="stocks[27].moduleLevel" <c:if test="${list[27].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以修改员工信息</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[28].roleCode" id="stocks[28].roleCode">
		<input type="hidden" value="${list[28].id }" name="stocks[28].moduleCode" id="stocks[28].moduleCode">
		<input type="hidden" value="${list[28].moduleName}" name="stocks[28].moduleName" id="stocks[28].moduleName" >
		<li>可修改员工信息<input type="radio" value="true" name="stocks[28].moduleLevel" id="stocks[28].moduleLevel" <c:if test="${list[28].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可修改员工信息<input type="radio" value="false" name="stocks[28].moduleLevel" id="stocks[28].moduleLevel" <c:if test="${list[28].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	
	<ul class="list_tatle"><li>是否可以查看员工信息</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[29].roleCode" id="stocks[29].roleCode">
		<input type="hidden" value="${list[29].id }" name="stocks[29].moduleCode" id="stocks[29].moduleCode">
		<input type="hidden" value="${list[29].moduleName}" name="stocks[29].moduleName" id="stocks[29].moduleName" >
		<li>可查看员工信息<input type="radio" value="true" name="stocks[29].moduleLevel" id="stocks[29].moduleLevel" <c:if test="${list[29].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可查看员工信息<input type="radio" value="false" name="stocks[29].moduleLevel" id="stocks[29].moduleLevel" <c:if test="${list[29].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	<ul class="list_tatle"><li>是否可以查看员工信息</li></ul>
	<ul>
		<input type="hidden" value="${role.id }" name="stocks[30].roleCode" id="stocks[30].roleCode">
		<input type="hidden" value="${list[30].id }" name="stocks[30].moduleCode" id="stocks[30].moduleCode">
		<input type="hidden" value="${list[30].moduleName}" name="stocks[30].moduleName" id="stocks[30].moduleName" >
		<li>可创建账号<input type="radio" value="true" name="stocks[30].moduleLevel" id="stocks[30].moduleLevel" <c:if test="${list[30].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可创建账号<input type="radio" value="false" name="stocks[30].moduleLevel" id="stocks[30].moduleLevel" <c:if test="${list[30].moduleLevel  =='false'}">checked</c:if>></li>
	</ul>
	<BR />
	
	<h2 id="employee">接单表权限 </h2>
		<ul class="list_tatle"><li>是否可管理全部接单表</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[36].roleCode" id="stocks[36].roleCode">
		<input type="hidden" value="${list[36].id }" name="stocks[36].moduleCode" id="stocks[36].moduleCode">
		<input type="hidden" value="${list[36].moduleName}" name="stocks[36].moduleName" id="stocks[36].moduleName" >
		<li>可全部管理<input type="radio" value="true" name="stocks[36].moduleLevel" id="stocks[36].moduleLevel" <c:if test="${list[36].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可全部管理<input type="radio" value="false" name="stocks[36].moduleLevel" id="stocks[36].moduleLevel" <c:if test="${list[36].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
		<ul class="list_tatle"><li>是否可查看接单表</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[37].roleCode" id="stocks[37].roleCode">
		<input type="hidden" value="${list[37].id }" name="stocks[37].moduleCode" id="stocks[37].moduleCode">
		<input type="hidden" value="${list[37].moduleName}" name="stocks[37].moduleName" id="stocks[37].moduleName" >
		<li>可查看<input type="radio" value="true" name="stocks[37].moduleLevel" id="stocks[37].moduleLevel" <c:if test="${list[37].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可查看<input type="radio" value="false" name="stocks[37].moduleLevel" id="stocks[37].moduleLevel" <c:if test="${list[37].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
		<ul class="list_tatle"><li>是否可管理自己的接单表</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[38].roleCode" id="stocks[38].roleCode">
		<input type="hidden" value="${list[38].id }" name="stocks[38].moduleCode" id="stocks[38].moduleCode">
		<input type="hidden" value="${list[38].moduleName}" name="stocks[38].moduleName" id="stocks[38].moduleName" >
		<li>可管理<input type="radio" value="true" name="stocks[38].moduleLevel" id="stocks[38].moduleLevel" <c:if test="${list[38].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可管理<input type="radio" value="false" name="stocks[38].moduleLevel" id="stocks[38].moduleLevel" <c:if test="${list[38].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
		<ul class="list_tatle"><li>是否可以重置</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[38].roleCode" id="stocks[50].roleCode">
		<input type="hidden" value="${list[50].id }" name="stocks[50].moduleCode" id="stocks[50].moduleCode">
		<input type="hidden" value="${list[50].moduleName}" name="stocks[50].moduleName" id="stocks[50].moduleName" >
		<li>可管理<input type="radio" value="true" name="stocks[50].moduleLevel" id="stocks[50].moduleLevel" <c:if test="${list[50].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可管理<input type="radio" value="false" name="stocks[50].moduleLevel" id="stocks[50].moduleLevel" <c:if test="${list[50].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
	<BR />
	
	
	<h2 id="employee">客户管理 </h2>
		<ul class="list_tatle"><li>是否可以管理全部</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[44].roleCode" id="stocks[44].roleCode">
		<input type="hidden" value="${list[44].id }" name="stocks[44].moduleCode" id="stocks[44].moduleCode">
		<input type="hidden" value="${list[44].moduleName}" name="stocks[44].moduleName" id="stocks[44].moduleName" >
		<li>可以<input type="radio" value="true" name="stocks[44].moduleLevel" id="stocks[44].moduleLevel" <c:if test="${list[44].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可以<input type="radio" value="false" name="stocks[44].moduleLevel" id="stocks[44].moduleLevel" <c:if test="${list[44].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
		<ul class="list_tatle"><li>是否可以管理自己</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[45].roleCode" id="stocks[45].roleCode">
		<input type="hidden" value="${list[45].id }" name="stocks[45].moduleCode" id="stocks[45].moduleCode">
		<input type="hidden" value="${list[45].moduleName}" name="stocks[45].moduleName" id="stocks[45].moduleName" >
		<li>可以<input type="radio" value="true" name="stocks[45].moduleLevel" id="stocks[45].moduleLevel" <c:if test="${list[45].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可以<input type="radio" value="false" name="stocks[45].moduleLevel" id="stocks[45].moduleLevel" <c:if test="${list[45].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
		<ul class="list_tatle"><li>是否可以查询</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[46].roleCode" id="stocks[46].roleCode">
		<input type="hidden" value="${list[46].id }" name="stocks[46].moduleCode" id="stocks[46].moduleCode">
		<input type="hidden" value="${list[46].moduleName}" name="stocks[46].moduleName" id="stocks[46].moduleName" >
		<li>可以<input type="radio" value="true" name="stocks[46].moduleLevel" id="stocks[46].moduleLevel" <c:if test="${list[46].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可以<input type="radio" value="false" name="stocks[46].moduleLevel" id="stocks[46].moduleLevel" <c:if test="${list[46].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
	<BR />
	
	
	<h2 id="employee">业务报表</h2>
		<ul class="list_tatle"><li>是否可以查看</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[49].roleCode" id="stocks[49].roleCode">
		<input type="hidden" value="${list[49].id }" name="stocks[49].moduleCode" id="stocks[49].moduleCode">
		<input type="hidden" value="${list[49].moduleName}" name="stocks[49].moduleName" id="stocks[49].moduleName" >
		<li>可以<input type="radio" value="true" name="stocks[49].moduleLevel" id="stocks[49].moduleLevel" <c:if test="${list[49].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可以<input type="radio" value="false" name="stocks[49].moduleLevel" id="stocks[49].moduleLevel" <c:if test="${list[49].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
	<BR />
	
	<h2 id="employee">工作计划 </h2>
		<ul class="list_tatle"><li>是否可以使用工作计划模块</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[39].roleCode" id="stocks[39].roleCode">
		<input type="hidden" value="${list[39].id }" name="stocks[39].moduleCode" id="stocks[39].moduleCode">
		<input type="hidden" value="${list[39].moduleName}" name="stocks[39].moduleName" id="stocks[39].moduleName" >
		<li>可全部管理<input type="radio" value="true" name="stocks[39].moduleLevel" id="stocks[39].moduleLevel" <c:if test="${list[39].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可全部管理<input type="radio" value="false" name="stocks[39].moduleLevel" id="stocks[39].moduleLevel" <c:if test="${list[39].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
		<ul class="list_tatle"><li>是否可以查看所有工作计划</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[40].roleCode" id="stocks[40].roleCode">
		<input type="hidden" value="${list[40].id }" name="stocks[40].moduleCode" id="stocks[40].moduleCode">
		<input type="hidden" value="${list[40].moduleName}" name="stocks[40].moduleName" id="stocks[40].moduleName" >
		<li>可全部管理<input type="radio" value="true" name="stocks[40].moduleLevel" id="stocks[40].moduleLevel" <c:if test="${list[40].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可全部管理<input type="radio" value="false" name="stocks[40].moduleLevel" id="stocks[40].moduleLevel" <c:if test="${list[40].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
	<BR />
	
	<h2 id="employee">TYCO模块 </h2>
		<ul class="list_tatle"><li>是否可以操作全部</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[41].roleCode" id="stocks[41].roleCode">
		<input type="hidden" value="${list[41].id }" name="stocks[41].moduleCode" id="stocks[41].moduleCode">
		<input type="hidden" value="${list[41].moduleName}" name="stocks[41].moduleName" id="stocks[41].moduleName" >
		<li>可操作<input type="radio" value="true" name="stocks[41].moduleLevel" id="stocks[41].moduleLevel" <c:if test="${list[41].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可操作<input type="radio" value="false" name="stocks[41].moduleLevel" id="stocks[41].moduleLevel" <c:if test="${list[41].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
		<ul class="list_tatle"><li>查看</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[42].roleCode" id="stocks[42].roleCode">
		<input type="hidden" value="${list[42].id }" name="stocks[42].moduleCode" id="stocks[42].moduleCode">
		<input type="hidden" value="${list[42].moduleName}" name="stocks[42].moduleName" id="stocks[42].moduleName" >
		<li>可查看<input type="radio" value="true" name="stocks[42].moduleLevel" id="stocks[42].moduleLevel" <c:if test="${list[42].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可查看<input type="radio" value="false" name="stocks[42].moduleLevel" id="stocks[42].moduleLevel" <c:if test="${list[42].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
		<ul class="list_tatle"><li>可以操作财务字段</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[43].roleCode" id="stocks[43].roleCode">
		<input type="hidden" value="${list[43].id }" name="stocks[43].moduleCode" id="stocks[43].moduleCode">
		<input type="hidden" value="${list[43].moduleName}" name="stocks[43].moduleName" id="stocks[43].moduleName" >
		<li>可操作<input type="radio" value="true" name="stocks[43].moduleLevel" id="stocks[43].moduleLevel" <c:if test="${list[43].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可操作<input type="radio" value="false" name="stocks[43].moduleLevel" id="stocks[43].moduleLevel" <c:if test="${list[43].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
		<ul class="list_tatle"><li>可以编辑</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[48].roleCode" id="stocks[48].roleCode">
		<input type="hidden" value="${list[48].id }" name="stocks[48].moduleCode" id="stocks[48].moduleCode">
		<input type="hidden" value="${list[48].moduleName}" name="stocks[48].moduleName" id="stocks[48].moduleName" >
		<li>可操作<input type="radio" value="true" name="stocks[48].moduleLevel" id="stocks[48].moduleLevel" <c:if test="${list[48].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可操作<input type="radio" value="false" name="stocks[48].moduleLevel" id="stocks[48].moduleLevel" <c:if test="${list[48].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
		<ul class="list_tatle"><li>是否可以导出报表</li></ul>
		<ul>
		<input type="hidden" value="${role.id }" name="stocks[47].roleCode" id="stocks[47].roleCode">
		<input type="hidden" value="${list[47].id }" name="stocks[47].moduleCode" id="stocks[47].moduleCode">
		<input type="hidden" value="${list[47].moduleName}" name="stocks[47].moduleName" id="stocks[47].moduleName" >
		<li>可以<input type="radio" value="true" name="stocks[47].moduleLevel" id="stocks[47].moduleLevel" <c:if test="${list[47].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可以<input type="radio" value="false" name="stocks[47].moduleLevel" id="stocks[47].moduleLevel" <c:if test="${list[47].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
		
	<BR />
	
	<h2 id="role">系统配置 </h2>
		<input type="hidden" value="${role.id }" name="stocks[31].roleCode" id="stocks[31].roleCode">
		<input type="hidden" value="${list[31].id }" name="stocks[31].moduleCode" id="stocks[31].moduleCode">
		<input type="hidden" value="${list[31].moduleName}" name="stocks[31].moduleName" id="stocks[31].moduleName" >
		<ul class="list_tatle">
		<li>可管理<input type="radio" value="true" name="stocks[31].moduleLevel" id="stocks[31].moduleLevel" <c:if test="${list[31].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可管理<input type="radio" value="false" name="stocks[31].moduleLevel" id="stocks[31].moduleLevel" <c:if test="${list[31].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
	<BR />
	<h2 id="role">自动单号 </h2>
		<input type="hidden" value="${role.id }" name="stocks[32].roleCode" id="stocks[32].roleCode">
		<input type="hidden" value="${list[32].id }" name="stocks[32].moduleCode" id="stocks[32].moduleCode">
		<input type="hidden" value="${list[32].moduleName}" name="stocks[32].moduleName" id="stocks[32].moduleName" >
		<ul class="list_tatle">
		<li>可创建账号<input type="radio" value="true" name="stocks[32].moduleLevel" id="stocks[32].moduleLevel" <c:if test="${list[32].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可创建账号<input type="radio" value="false" name="stocks[32].moduleLevel" id="stocks[32].moduleLevel" <c:if test="${list[32].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
	<BR />
	
	<h2 id="role">角色与权限 </h2>
		<input type="hidden" value="${role.id }" name="stocks[33].roleCode" id="stocks[33].roleCode">
		<input type="hidden" value="${list[33].id }" name="stocks[33].moduleCode" id="stocks[33].moduleCode">
		<input type="hidden" value="${list[33].moduleName}" name="stocks[33].moduleName" id="stocks[33].moduleName" >
		<ul class="list_tatle">
		<li>可创建账号<input type="radio" value="true" name="stocks[33].moduleLevel" id="stocks[33].moduleLevel" <c:if test="${list[33].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可创建账号<input type="radio" value="false" name="stocks[33].moduleLevel" id="stocks[33].moduleLevel" <c:if test="${list[33].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
	<BR />
	<h2 id="user">用户模块 </h2>
		<input type="hidden" value="${role.id }" name="stocks[34].roleCode" id="stocks[34].roleCode">
		<input type="hidden" value="${list[34].id }" name="stocks[34].moduleCode" id="stocks[34].moduleCode">
		<input type="hidden" value="${list[34].moduleName}" name="stocks[34].moduleName" id="stocks[34].moduleName" >
		<ul class="list_tatle">
		<li>可管理用户<input type="radio" value="true" name="stocks[34].moduleLevel" id="stocks[34].moduleLevel" <c:if test="${list[34].moduleLevel  =='true'}">checked</c:if>></li>
		<li>不可管理用户<input type="radio" value="false" name="stocks[34].moduleLevel" id="stocks[34].moduleLevel" <c:if test="${list[34].moduleLevel  =='false'}">checked</c:if>></li>
		</ul>
	</div>

<li><a href="#" id="sub"><span><img src="<%=request.getContextPath() %>/images/save_icon.png" />保存</span></a></li>
<li><a href="#" id="cancel"><span><img src="<%=request.getContextPath() %>/images/cancel_icon.png" />取消</span></a></li>
</div>
</div>
<script type="text/javascript">
document.getElementById('album').onclick=function(){
	alert(1);
}

document.getElementById('sub').onclick=function(){
	document.forms['creatRM'].action="<%=request.getContextPath() %>/permission.do?method=addRM";
	document.forms['creatRM'].submit();
}
document.getElementById('cancel').onclick=function(){
	document.forms['creatRM'].action="<%=request.getContextPath() %>/permission.do?method=getRole";
	document.forms['creatRM'].submit();
}

</script>
</form>
</body>
</html>