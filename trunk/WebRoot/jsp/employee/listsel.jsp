<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/employee/selcss.css"/> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/employee/employeejs.jsp"></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/tools/validate.jsp"></script> 
<script type="text/javascript">  
</script>
</head> 
<body>  
<div  id="allblock">
<form id="pageForm" name="pageForm" action="<%=request.getContextPath()%>/employee.do?method=listsel" method="post">  
    <input type="hidden" name="orderByBame" id="orderByBame" value="${form.orderByBame}"/>
    <input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/>  
    <input type="hidden" id="id" name="id" /> 
	<div id="searchcust"> 
		 <li class="cust_name">姓名：<input type="text" id="basic.name" name="basic.name" value="${form.basic.name}"/></li>
		 <li class="cust_code">部门：<input type="text" id="basic.depName"  name="basic.depName" value="${form.basic.depName}"/></li> 
		 <li class="button_test"><input type="button" class="button_test" value="搜 &nbsp;&nbsp;&nbsp; 索" onclick="document.forms['pageForm'].submit();"/></li>
	</div> 
	<div id="cust_content">
	 <ul id="cust_title">  
		 <li class="title_custname"    onClick="orderby(this)" id="employeeCode">员工编码</li>
         <li class="title_custcode"    onClick="orderby(this)" id="name">员工姓名</li>
         <li class="title_custphone"   onClick="orderby(this)" id="depName">所属部门</li>  
         <li class="title_com"   onClick="orderby(this)" id="companyName">所属公司</li>         
	 </ul>
	 <c:forEach var="one" items="${emlist}" >
		 <ul id="cust_one" onclick="selem('${one.account}','${one.name}')">
			 <li  class="title_custname">${one.employeeCode}</li>
			 <li  class="title_custcode">${one.name}</li>
			 <li  class="title_custphone">${one.depName}</li>
			 <li  class="title_com"><a name="short_comname">${one.companyName}</a></li>
		 </ul>
	 </c:forEach>
	 <%@ include file="/inc/pagecontrol.inc"%> 
	</div> 
	</form>
	</div> 
</body>
</html>
<script type="text/javascript">
orderByInit();  
function selem(account,name){
 /*
  var item = "${item}"
  if(item=="yewu"){
  */
     window.parent.document.getElementById("custom.workor").value = name  ;
     window.parent.document.getElementById("custom.workorId").value = account  ;
     /*
  }else if(item=="kefu"){ 
     window.parent.document.getElementById("custom.custService").value = name  ;
     window.parent.document.getElementById("custom.custServiceId").value = account  ; 
  } */
  window.parent.closeDivWindow("moneyfra") ; 
} 
titleInit('short_comname',12) ; 
</script>