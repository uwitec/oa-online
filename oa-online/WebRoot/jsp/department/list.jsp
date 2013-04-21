<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/department_list.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/department/departjs.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<title>部门列表页</title>
</head> 
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
    	<div id="mainpage">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span>
        	<span><a>基础信息</a></span><span><a href="<%=request.getContextPath()%>/department.do?method=list">部门管理</a></span></div>
        	<form action="<%=request.getContextPath()%>/department.do" name="pageForm" method="post"> 
      		<input type="hidden" name="self.department.Token"  value="<%=session.getAttribute("self.department.Token")%>"/>
      		<input type="hidden" name="method" id="method" value="list"/>
     		<input type="hidden" name="id" id="id" value=""/>
      		<input type="hidden" name="orderByBame" id="orderByBame" value="${form.orderByBame}"/>
      		<input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/>
            <div id="search">
            	<ul>
				    <li>部门名称：</li>
				    <li><input class="company_input" name="department.depName" type="text" value="${form.department.depName}"/></li>
			    </ul>
			    <ul>
				    <li>所属公司：</li>
				    <li><input class="company_input" name="department.comName" type="text" value="${form.department.comName}"/></li>
				</ul>
				<ul>    
				    <li>所属部门：</li>
				    <li><input class="company_input" name="department.parentDepName" type="text" value="${form.department.parentDepName}"/></li>
				     <li class="content_jh"><button onclick="document.forms['pageForm'].submit();" class="button_bg">搜索</button></li>
			    </ul>
            </div>
            <div class="index_pic">部门列表：</div> 
            <div id="inpageinfo">${retDep}</div> 
            <script type="text/javascript">
              var retDep = "${retDep}" ;
              if(retDep!=null && retDep!="")
                 document.getElementById("inpageinfo").style.display= "block" ;
            </script>
            <div id="operat_title"> 
              	<% session.removeAttribute("retDep");%> 
            	<button class="button_add" id="addShow" onclick="document.location.href='<%=request.getContextPath()%>/department.do?method=preAdd';">新建</button>
            </div>
            <div id="com_list"> 
                <ul class="list_title">
                    <li class="department_selall"></li>
                	<li class="department_comfullname"  id="depName" onclick="orderby(this)">部门名称</li>
                    <li class="department_comaddress"  id="comName" onclick="orderby(this)">所属公司</li>
                    <li class="department_comparent"  id="parentDepName" onclick="orderby(this)">所属部门</li>
                    <li class="department_comhomepage"  id="leader" onclick="orderby(this)">部门主管</li>
                    <li class="department_createtime"  id="addDate" onclick="orderby(this)">录入时间</li>
                    <li class="department_sigleoperat">操作</li>
                </ul>
				 <c:forEach var="dep" items="${deplist}">
				<ul class="com_list_content">
				   <%session.removeAttribute("sessionDepId");%>
                    <li class="department_selall"><input  name="ids" type="checkbox" value="${dep.id}"/></li>
                	<li class="department_comfullname">${dep.depName}</li>
                    <li class="department_comaddress"><a name="comaddresssubstring">${dep.comName}</a></li>
                    <li class="department_comparent">${dep.parentDepName}</li>
                    <li class="department_comhomepage">${dep.leader}</li>
                    <li class="department_createtime"><fmt:formatDate value="${dep.addDate}" pattern="yyyy-MM-dd"/></li>
                    <li class="department_sigleoperat">
                      <c:if test="${user.permissionMap.department_modify==true}">  
                      <a title="编辑" href="<%=request.getContextPath()%>/department.do?method=preEdit&id=${dep.id}"><img src="<%=request.getContextPath()%>/images/edit_mini.png"/></a>   
                      </c:if>
                      <c:if test="${user.permissionMap.department_del==true}"><a class="d_box" title="删除" href="javascript:delOne('${dep.id}');"><img src="<%=request.getContextPath()%>/images/delete.png"/></a></c:if> 
                    </li>
                </ul></c:forEach> 
            </div>
	         <div id="com_all_delete">
	              <button id="chall" onclick="selAll('ids');" class="button_bg">全选</button>
	              <c:if test="${user.permissionMap.company_del==true}">
	              <button id="delAll" onclick="delSome();" class="button_bg">删除</button>
	              </c:if> 
	         </div>
            <div id="page">
              <%@ include file="/inc/pagecontrol.inc"%>
			</div></form>
    	</div>
    </div> 
<jsp:include page="/index.do?method=foot" flush="true"/>
</div>
</body>
</html> 
<script type="text/javascript">
orderByInit(); 
titleInit('comaddresssubstring',12) ;
</script>

