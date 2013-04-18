<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/accept/selcss.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/custom/comjs.jsp"></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/tools/validate.jsp"></script> 
<script type="text/javascript">  
</script>
</head> 
<body>  
<div  id="allblock">
<form action="<%=request.getContextPath()%>/custom.do" name="pageForm" method="post">  
     		 <input type="hidden" name="id" id="id"/>
     		 <input type="hidden" name="method" id="method" value="getCust"/> 
      		<input type="hidden" name="orderByBame" id="orderByBame" value="${form.orderByBame}"/>
      		<input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/> 
	<div id="searchcust"> 
		 <li class="cust_name">客户名：<input type="text"  name="custom.fullName" value="${form.custom.fullName}"/></li>
		 <li class="cust_code">客户简称：<input type="text"  name="custom.cutName" value="${form.custom.cutName}"/></li> 
		 <li class="button_test"><input type="button" class="button_test" value="搜 &nbsp;&nbsp;&nbsp; 索" onclick="document.forms['pageForm'].submit();"/></li>
	</div> 
	<div id="cust_content">
	 <ul id="cust_title">
		 <li  class="title_custname" id="fullName" onclick="orderby(this)">客户名</li>
		 <li  class="title_custcode" id="cutName" onclick="orderby(this)">客户简称</li>
		 <li  class="title_custphone" id="phone" onclick="orderby(this)">电话</li>
	 </ul>
	 <c:forEach var="one" items="${list}" >
		 <ul id="cust_one" onclick="selcust('${one.id}','${one.fullName}','${one.workor}','${one.workorId}')">
			 <li  class="title_custname"><a name="short_fullname">${one.fullName}</a></li>
			 <li  class="title_custcode"><a name="short_cutName">${one.cutName}</a></li>
			 <li  class="title_custphone"><a name="short_phone">${one.phone}</a></li>
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
function selcust(id,fullname,workor,workorId){
  window.parent.document.getElementById("accept.customer").value = fullname  ;
  window.parent.document.getElementById("accept.customerId").value = id  ;
  if(workor==null || workor==""){
  	  window.parent.document.getElementById("accept.manager").readOnly = false  ;
	  window.parent.document.getElementById("accept.manager").value = ""  ; 
	  window.parent.document.getElementById("accept.managerId").value = ""  ;
	  window.parent.yewuhidden() ;
  }else{
	  window.parent.document.getElementById("accept.manager").value = workor  ; 
	  window.parent.document.getElementById("accept.manager").readOnly = true  ;
	  window.parent.document.getElementById("accept.managerId").value = workorId  ;
	  window.parent.yewuhidden() ;
  } 
  window.parent.closeDivWindow("moneyfra") ; 
}
titleInit('short_fullname',15) ;
titleInit('short_cutName',8) ;
titleInit('short_phone',7) ;
</script>