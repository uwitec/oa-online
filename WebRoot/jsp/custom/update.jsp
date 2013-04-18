<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/custom/comjs.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/CustomDWR.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
<title>编辑客户</title>
</head>   
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
    	<div id="mainpage">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>基础信息</a></span>
        	<span><a href="<%=request.getContextPath()%>/custom.do?method=list">客户管理</a></span><span><a>编辑客户</a></span></div>
               <div class="index_pic">编辑客户：</div>  
               <div id="inpageinfo"></div>
               <form action="<%=request.getContextPath()%>/custom.do?method=editCom" name="pageForm" method="post">
               <input type="hidden" name="self.custom.Token"  value="<%=session.getAttribute("self.custom.Token")%>"/> 
               <input id="custom.id" name="custom.id" type="hidden" value="${comOne.id}"/>
               <input id="custom.creator" name="custom.creator" type="hidden" value="${comOne.creator}"/>
               <input id="addTime" name="addTime" type="hidden" value="<fmt:formatDate value='${comOne.addDate}' pattern='yyyy-MM-dd'/>"/>
               <input id="custom.parentId" name="custom.parentId" type="hidden" value="${comOne.parentId}"/>
               <input id="custom.parentName" name="custom.parentName" type="hidden" value="${comOne.parentName}"/>
                <div id="addcominfo"> 
                 <ul>
	                 <li><span>简称：</span><input type="text" id="custom.cutName"  name="custom.cutName"  value="${comOne.cutName}"/></li>
	                 <li><span>客户全称：</span><input id="custom.fullName" type="text" name="custom.fullName" value="${comOne.fullName}"/></li>
	                 <li><span>所属公司：</span>
	                     <select id="parentCom" onchange="selParentCom();">
		                 <option value="">--请选择所属公司--</option>
		                 <c:forEach var="com" items="${comlist}">
		                 <c:if test="${com.fullName!=comOne.fullName}">
		                 <option value="${com.id}-${com.fullName}" <c:if test="${com.fullName==comOne.parentName}"> selected </c:if> >${com.fullName}</option>
		                 </c:if></c:forEach>
		                 </select>
	                 </li>
                 </ul>
                 <ul>
	                 <li><span>客户地址：</span><input id="custom.address" type="text" name="custom.address" value="${comOne.address}"/></li>
	                 <li><span></span><input id="checkworker" <c:if test="${comOne.workor==null || comOne.workor==''}">checked</c:if> 
	                               type="checkbox" onclick="slecetWorker(this);" />无业务员
	                 </li>
	                 <li><div id="workerdis"><span>业务员：</span>
		                 <input type="text" class="text" name="custom.workor" id="custom.workor" readonly  onclick="getworker();" value="${comOne.workor}"/>
						</div> 
		                 <input id="custom.workorId" type="hidden" name="custom.workorId" value="${comOne.workorId}"/>
	                 </li>
                 </ul>
                 <script type="text/javascript">
                   var worker = "${comOne.workor}" ;
                   if(worker==null || worker.trim()=="")
                      document.getElementById("workerdis").style.display = "none" ;
                 </script>  
                 <ul>
                 <li><div id="ennamecountry">
                    <ul>
                       <li><span>英文名称：</span><input id="custom.enName" type="text" name="custom.enName"  value="${comOne.enName}"/></li>
                       <li><span>国家：</span><input  id="custom.country" type="text" name="custom.country" value="${comOne.country}"/></li>
                       <!--  <li><span>客服：</span><input  id="custom.custService" type="text" name="custom.custService" value="${comOne.custService}"  readonly  onclick="getworker('kefu');"/>
                       <input  id="custom.custServiceId" type="hidden" name="custom.custServiceId" value="${comOne.custServiceId}"/></li>-->
                    </ul></div>
                 </li>
                 <li><span>英文地址：</span><textarea cols="40" id="custom.enAddress"   name="custom.enAddress" >${comOne.enAddress}</textarea></li>
                 
                 </ul>
                 <ul>
                   <li><span>省份：</span><input id="custom.province" type="text" name="custom.province" class="someinput" value="${comOne.province}"/></li>
                   <li><span>城市：</span><input id="custom.city" type="text" name="custom.city" class="someinput" value="${comOne.city}"/></li> 
                   <li><span>邮编：</span><input id="custom.postCode" type="text" name="custom.postCode" class="someinput" value="${comOne.postCode}"/></li>
                   <li><span>法人：</span><input id="custom.legalPerson" type="text" name="custom.legalPerson" class="someinput" value="${comOne.legalPerson}"/></li>
                 </ul>
                 <ul>
                   <li><span>联系人：</span><input id="custom.contactPerson" type="text" name="custom.contactPerson" class="someinput" value="${comOne.contactPerson}"/></li>
                   <li><span>电话：</span><input id="custom.phone" type="text" name="custom.phone" class="someinput" value="${comOne.phone}"/></li> 
                   <li><span>手机：</span><input id="custom.mobile" type="text" name="custom.mobile" class="someinput" value="${comOne.mobile}"/></li>
                   <li><span>传真：</span><input id="custom.fax" type="text" name="custom.fax" class="someinput" value="${comOne.fax}"/></li>
                 </ul>
                 <ul>
                   <li><span>email：</span><input id="custom.email" type="text" name="custom.email" class="someinput" value="${comOne.email}"/></li>
                   <li><span>客户网站：</span><input id="custom.homePage" type="text" name="custom.homePage" class="someinput" value="${comOne.homePage}"/></li> 
                   <li><span>营业执照：</span><input id="custom.businessNo" type="text" name="custom.businessNo" class="someinput" value="${comOne.businessNo}"/></li>
                   <li><span>国税证：</span><input id="custom.nationTaxNo" type="text" name="custom.nationTaxNo" class="someinput" value="${comOne.nationTaxNo}"/></li>
                 </ul>
                 <ul>
                   <li><span>地税证：</span><input id="custom.landTaxNo" type="text" name="custom.landTaxNo" class="someinput" value="${comOne.landTaxNo}"/></li>
                   <li><span>海关代码：</span><input id="custom.cnnumber" type="text" name="custom.cnnumber" class="someinput" value="${comOne.cnnumber}"/></li> 
                   <li><span>报检代码：</span><input id="custom.checkNo" type="text" name="custom.checkNo" class="someinput" value="${comOne.checkNo}"/></li> 
                 </ul>
                 <ul>
                   <li><span>租赁合同：</span><input id="custom.contract" type="text" name="custom.contract" class="someinput" value="${comOne.contract}"/></li>
                   <li><span>起始时间：</span><input id="contractTime" type="text" name="contractTime" class="someinput" value="<fmt:formatDate value="${comOne.contractTime}" pattern="yyyy-MM-dd"/>" readonly onclick="WdatePicker()"/></li> 
                   <li><span>合同期限：</span><input id="custom.contractTerm" type="text" name="custom.contractTerm" class="someinput" value="${comOne.contractTerm}"/></li> 
                 </ul>
                 <ul>
                   <li><span>备注：</span><textarea cols="40" id="custom.comment" name="custom.comment">${comOne.comment}</textarea></li>
                 </ul> 
            </div>
            </form>  
    	</div>
    	<div id="com_all_delete02">
        	<button id="submit_button" onClick="editcom('${comOne.id}');" class="button_bg">保存</button>
        	<button onClick="document.location.href='<%=request.getContextPath()%>/custom.do?method=list';" class="button_bg">退出</button>
        </div>  
    	 
    </div> 
<jsp:include page="/index.do?method=foot" flush="true"/>
</div>
</body>
</html>