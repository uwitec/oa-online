<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/company/comjs.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<title>公司列表页</title>
</head> 
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
    	<div id="mainpage">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a href="#">基础信息</a></span>
        	<span><a href="<%=request.getContextPath()%>/company.do?method=list">公司管理</a></span></div>
        	<form action="<%=request.getContextPath()%>/company.do" name="pageForm" method="post"> 
      		<input type="hidden" name="self.company.Token"  value="<%=session.getAttribute("self.company.Token")%>"/>
      		<input type="hidden" name="method" id="method" value="list"/>
     		<input type="hidden" name="id" id="id" value=""/>
      		<input type="hidden" name="orderByBame" id="orderByBame" value="${form.orderByBame}"/>
      		<input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/> 
            <div id="search">
				  <ul>
				   	<li>公司名称：</li>
				   	<li><input type="text" id="company.fullName" class="company_input" name="company.fullName" value="${form.company.fullName}"/></li>
				  </ul>
				  <ul>
				   	<li>公司地址：</li>
				   	<li><input type="text" id="company.address" class="company_input" name="company.address"  value="${form.company.address}"/></li>
				  </ul>
				  <ul>
				    <li>公司网址：</li>
				    <li><input type="text" id="company.homePage" class="company_input" name="company.homePage"  value="${form.company.homePage}"/></li>
				   	<li class="content_bg"><button onclick="document.forms['pageForm'].submit();" class="button_bg">搜索</button></li>
				  </ul>
            </div>
            <div class="index_pic">公司列表：</div> 
            <div id="inpageinfo">${comResult}</div>
            <script type="text/javascript">
              var comResult = "${comResult}" ;
              if(comResult!=null && comResult!="")
                 document.getElementById("inpageinfo").style.display= "block" ;
            </script>
           <div id="operat_title">   
              <% session.removeAttribute("comResult") ; %> 
				<c:if test="${user.permissionMap.company_append==true}">
				<button class="button_add" id="addShow" onclick="document.location.href='<%=request.getContextPath()%>/company.do?method=preAdd';">新建</button>
           		</c:if>
            </div>
            <div id="com_list"> 
                <ul class="list_title">
                    <li class="selall"></li>
                	<li class="comfullname" id="fullName" onclick="orderby(this)">全称</li>
                    <li class="comaddress" id="address" onclick="orderby(this)">地址</li>
                    <li class="comparent"  id="parentName" onclick="orderby(this)">所属公司</li>
                    <li class="comhomepage"  id="homePage" onclick="orderby(this)">公司网址</li>
                    <li class="createtime"  id="addDate" onclick="orderby(this)">录入时间</li>
                    <li class="sigleoperat">操作</li>
                </ul>
                <c:forEach var="com" items="${list}">
				<ul class="com_list_content">
				   <% session.removeAttribute("sessionFullName") ; %>
                    <li class="selall"><input  name="ids" type="checkbox" value="${com.id}"/></li>
                	<li class="comfullname"><a href="javascript:edit('${com.id}');" name="comfullname_substring">${com.fullName}</a></li>
                    <li class="comaddress"><a name="comaddress_substring">${com.address}</a></li>
                    <li class="comparent"><a name="comparent_substring">${com.parentName}</a></li>
                    <li class="comhomepage"><a href="http://${com.homePage}" target="_black" name="comhomepage_substring">${com.homePage}</a></li>
                    <li class="createtime"><fmt:formatDate value="${com.addDate}" pattern="yyyy-MM-dd"/></li>
                    <li class="sigleoperat">
                      <c:if test="${user.permissionMap.company_modify==true}">  
                      	<a title="编辑" href="javascript:edit('${com.id}');"><img src="<%=request.getContextPath()%>/images/edit_mini.png"/></a></c:if>   
                      <c:if test="${user.permissionMap.company_del==true}">      
                      	<a class="d_box" title="删除" href="javascript:delOne('${com.id}');"><img src="<%=request.getContextPath()%>/images/delete.png"/></a></c:if> 
                    </li>
                </ul>
                </c:forEach> 
            </div> 
			<div id="com_all_delete">
				<button id="chall" onclick="selAll('ids');" class="button_bg">全选</button>
				<c:if test="${user.permissionMap.company_del==true}">
				<button id="delAll" onclick="delSome();" class="button_bg">删除</button>
				</c:if> 
			</div>
        <div id="page"> 
		 <%@ include file="/inc/pagecontrol.inc"%>
    	</div>
    </form>  
    </div>
    </div>
    <jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
</html>
<script type="text/javascript">
orderByInit();  
titleInit('comfullname_substring',12) ;
titleInit('comaddress_substring',12) ;
titleInit('comparent_substring',12) ;
titleInit('comhomepage_substring',12) ; 
</script>

