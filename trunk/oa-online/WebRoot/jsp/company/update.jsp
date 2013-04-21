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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DivWindow.js"></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/CompanyDWR.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
<title>编辑公司</title>
</head>   
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
    	<div id="mainpage" class="add_company">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a href="#">基础信息</a></span>
        	<span><a href="<%=request.getContextPath()%>/company.do?method=list">公司管理</a></span><span><a>公司信息</a></span></div>
	               <div class="index_pic">编辑公司：</div>
	               <div id="inpageinfo"></div>
               <form action="<%=request.getContextPath()%>/company.do?method=editCom" name="pageForm" method="post">
               <input type="hidden" name="self.company.Token"  value="<%=session.getAttribute("self.company.Token")%>"/> 
               <input id="company.id" name="company.id" type="hidden" value="${comOne.id}"/>
               <input id="company.parentId" name="company.parentId" type="hidden" value="${comOne.parentId}"/>
               <input id="company.parentName" name="company.parentName" type="hidden" value="${comOne.parentName}"/>
               <div id="addcominfo" class="addcominfo_company"> 
                 <ul><li><span>简称：</span><input type="text" id="company.cutName"  name="company.cutName"  value="${comOne.cutName}"/></li>
                 <li><span>全称：</span><input id="company.fullName" type="text" name="company.fullName" value="${comOne.fullName}"/></li>
                 <li><span>所属公司：</span><select id="parentCom" onchange="selParentCom();">
                 <option value="">--请选择所属公司--</option>
                 <c:forEach var="com" items="${comlist}">
                 <c:if test="${com.fullName!=comOne.fullName}">
                 <option value="${com.id}-${com.fullName}" <c:if test="${com.fullName==comOne.parentName}"> selected </c:if> >${com.fullName}</option>
                 </c:if></c:forEach>
                 </select></li></ul>
                  <ul>
                 	<li><span>公司地址：</span><textarea cols="40" id="company.address" name="company.address">${comOne.address}</textarea>
                 	</li>
                 </ul>
                 <ul>
		            <li><span>英文名称：</span><input id="company.enName" type="text" name="company.enName" value="${comOne.enName}"/></li>
		            <li><span>国家：</span><input  id="company.country" type="text" name="company.country" value="${comOne.country}"/></li>
		         </ul>        
	             <ul>
                 	<li><span>英文地址：</span><textarea cols="40" id="company.enAddress"   name="company.enAddress">${comOne.enAddress}</textarea></li>
                 </ul>
                 <ul>
                   <li><span>省份：</span><input id="company.province" type="text" name="company.province" class="someinput" value="${comOne.province}"/></li>
                   <li><span>城市：</span><input id="company.city" type="text" name="company.city" class="someinput" value="${comOne.city}"/></li> 
                   <li><span>邮编：</span><input id="company.postCode" type="text" name="company.postCode" class="someinput" value="${comOne.postCode}"/></li>
                   <li><span>法人：</span><input id="company.legalPerson" type="text" name="company.legalPerson" class="someinput" value="${comOne.legalPerson}"/></li>
                 </ul>
                 <ul>
                   <li><span>联系人：</span><input id="company.contactPerson" type="text" name="company.contactPerson" class="someinput" value="${comOne.contactPerson}"/></li>
                   <li><span>电话：</span><input id="company.phone" type="text" name="company.phone" class="someinput" value="${comOne.phone}"/></li> 
                   <li><span>手机：</span><input id="company.mobile" type="text" name="company.mobile" class="someinput" value="${comOne.mobile}"/></li>
                   <li><span>传真：</span><input id="company.fax" type="text" name="company.fax" class="someinput" value="${comOne.fax}"/></li>
                 </ul>
                 <ul>
                   <li><span>email：</span><input id="company.email" type="text" name="company.email" class="someinput" value="${comOne.email}"/></li>
                   <li><span>公司网站：</span><input id="company.homePage" type="text" name="company.homePage" class="someinput" value="${comOne.homePage}"/></li> 
                   <li><span>营业执照：</span><input id="company.businessNo" type="text" name="company.businessNo" class="someinput" value="${comOne.businessNo}"/></li>
                   <li><span>国税证：</span><input id="company.nationTaxNo" type="text" name="company.nationTaxNo" class="someinput" value="${comOne.nationTaxNo}"/></li>
                 </ul>
                 <ul>
                   <li><span>地税证：</span><input id="company.landTaxNo" type="text" name="company.landTaxNo" class="someinput" value="${comOne.landTaxNo}"/></li>
                   <li><span>海关代码：</span><input id="company.cnnumber" type="text" name="company.cnnumber" class="someinput" value="${comOne.cnnumber}"/></li> 
                   <li><span>报检代码：</span><input id="company.checkNo" type="text" name="company.checkNo" class="someinput" value="${comOne.checkNo}"/></li> 
                 </ul>
                 <ul>
                   <li><span>租赁合同：</span><input id="company.contract" type="text" name="company.contract" class="someinput" value="${comOne.contract}"/></li>
                   <li><span>起始时间：</span><input id="contractTime" type="text" name="contractTime" class="someinput" value="<fmt:formatDate value="${comOne.contractTime}" pattern="yyyy-MM-dd"/>" readonly onclick="WdatePicker()"/></li> 
                   <li><span>合同期限：</span><input id="company.contractTerm" type="text" name="company.contractTerm" class="someinput" value="${comOne.contractTerm}"/></li> 
                 </ul>
                 <ul>
                   <li id="remark_box"><span>备注：</span><textarea cols="40" id="company.comment" name="company.comment">${comOne.comment}</textarea></li>
                 </ul> 
            </div>
            </form>
        <div id="com_all_delete02">
        	<button id="submit_button" onClick="editcom('${comOne.id}');" class="button_bg">保存</button>
        	<button onClick="document.location.href='<%=request.getContextPath()%>/company.do?method=list';" class="button_bg">退出</button>      
    	</div>
    </div>
</div>
<jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</div>
</body>
</html>