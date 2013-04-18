<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/DepartmentDWR.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath()%>/jsp/department/departjs.jsp'></script>  
<script type='text/javascript' src='<%=request.getContextPath()%>/js/tools/validate.jsp'></script>  
<script type='text/javascript' src='<%=request.getContextPath()%>/js/leftnav.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js'></script>
<title>编辑部门</title>
</head>   
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
    	<div id="mainpage">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>基础信息</a></span><span>
        	<a href="<%=request.getContextPath()%>/department.do?method=list">部门管理</a></span><span><a>部门信息</a></span></div>
               <div class="index_pic">编辑部门：</div>
                   <div id="inpageinfo"></div>
               <form name="pageForm" method="post" action="<%=request.getContextPath()%>/department.do?method=editDep">
					<input type="hidden" name="self.department.Token"  value="<%=session.getAttribute("self.department.Token")%>"/> 
					<input type="hidden" name="department.id" id="department.id" value="${department.id}"/>
					<input type="hidden" name="department.comId" id="department.comId" value="${department.comId}"/>
					<input type="hidden" name="department.comName" id="department.comName"  value="${department.comName}"/>
					<input type="hidden" name="department.parentDepId" id="department.parentDepId"  value="${department.parentDepId}"/>
					<input type="hidden" name="department.parentDepName" id="department.parentDepName"  value="${department.parentDepName}"/>
                <div id="addcominfo"> 
                 <ul><li><span>部门名称：</span><input type="text" name="department.depName" id="department.depName"  value="${department.depName}"/></li> 
                 <li><span>所属公司：</span><select type="text" name="parentcom" id="parentcom" onchange="selctCom();">
        <option value="">---请选择公司---</option>
        <c:forEach var="com" items="${comlist}"> 
           <option value="${com.id}-${com.fullName}"  <c:if test="${department.comName==com.fullName}">selected</c:if> >${com.fullName}</option>
        </c:forEach>
      </select></li>
     			 <li><span>所属部门：</span><select type="text" name="parentdep" id="parentdep" onchange="selDep();">
        <option value="">---请选择部门---</option>
        <c:forEach var="dep" items="${deplist}"> 
           <option value="${dep.id}-${dep.depName}"  <c:if test="${department.parentDepName==dep.depName}">selected</c:if> >${dep.depName}</option>
        </c:forEach>
      </select></li>
     			 </ul>
                 <ul><li id="address_cn"><span>部门地址：</span><input id="department.address" type="text" name="department.address" value="${department.address}"/></li></ul> 
                 <ul>
                   <li><span>部门主管：</span><input type="text" name="department.leader" id="department.leader"  value="${department.leader}" class="someinput"/></li> 
                   <li><span>租赁合同：</span><input id="department.contract" type="text" name="department.contract"  value="${department.contract}" class="someinput"/></li>
                   <li><span>起始时间：</span><input id="contractTime" type="text" name="contractTime" class="someinput" 
                    value="<fmt:formatDate value="${department.contractTime}" pattern="yyyy-MM-dd"/>"
                   readonly onclick="WdatePicker()"/></li> 
                   <li><span>合同期限：</span><input id="department.contractTerm" type="text" name="department.contractTerm" value="${department.contractTerm}"  class="someinput"/></li> 
                  </ul>
                 <ul>
                   <li><span>备注：</span><textarea cols="40" name="department.description" id="department.description">${department.description}</textarea></li>
                 </ul> 
            </div>
            </form> 
         </div>
         <div id="com_all_delete02">
              <button id="submit_button" onClick="editDep();" class="button_bg">保存</button>
              <button onClick="document.location.href='<%=request.getContextPath()%>/department.do?method=list';" class="button_bg">退出</button>
         </div>
    </div>  
<jsp:include page="/index.do?method=foot" flush="true"/>
</div>
</body>
</html>
<script type="text/javascript"> 
document.getElementById("department.depName").select(); 
</script>

