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
<title>新建部门</title>
</head>   
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
    	<div id="mainpage">
	           <div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>基础信息</a></span>
	           <span><a href="<%=request.getContextPath()%>/department.do?method=list">部门管理</a></span><span><a>新建部门</a></span></div>
	               <div class="index_pic">新建部门：</div>
                   <div id="inpageinfo"></div>
               <form name="pageForm" method="post" action="<%=request.getContextPath()%>/department.do?method=addDep">
               <input type="hidden" name="self.department.Token"  value="<%=session.getAttribute("self.department.Token")%>"/> 
               <input type="hidden" name="department.comId" id="department.comId" />
               <input type="hidden" name="department.comName" id="department.comName" />
               <input type="hidden" name="department.parentDepId" id="department.parentDepId" />
               <input type="hidden" name="department.parentDepName" id="department.parentDepName" />
                <div id="addcominfo"> 
                 <ul>
	                 <li><span>部门名称：</span><input type="text" name="department.depName" id="department.depName" class="someinput"/></li> 
	                 <li><span>所属公司：</span>
		                 <select type="text" name="parentcom" id="parentcom" onchange="selctCom();">
		        			 <option value="">---请选择公司---</option>
		       			 	 <c:forEach var="com" items="${comlist}"><option value="${com.id}-${com.fullName}">${com.fullName}</option></c:forEach>
     			 	 	 </select>
     			 	 </li>
	     			 <li><span>所属部门：</span>
	     			 	 <select type="text" name="parentdep" id="parentdep" onchange="selDep();"><option value="">---请选择部门---</option></select>
	                 </li>
     			 </ul>
                 <ul>
	                 <li>
		                 <span>部门地址：</span><textarea cols="40" id="department.address" name="department.address"></textarea>
	                 </li>
                 </ul> 
                 <ul>
                   <li><span>部门主管：</span><input type="text" name="department.leader" id="department.leader" class="someinput"/></li> 
                   <li><span>租赁合同：</span><input id="department.contract" type="text" name="department.contract" class="someinput"/></li>
                   <li><span>起始时间：</span><input id="contractTime" type="text" name="contractTime" class="someinput" readonly onclick="WdatePicker()"/></li> 
                   <li><span>合同期限：</span><input id="department.contractTerm" type="text" name="department.contractTerm" class="someinput"/></li> 
                 </ul>
                 <ul>
                   <li id="remark_box"><span>备注：</span><textarea cols="40" name="department.description" id="department.description"></textarea></li>
                 </ul> 
            </div>
            </form>
         </div>
         <div id=""com_all_delete02"">
              <button id="submit_button" onClick="addDep()" class="button_bg">保存</button>
              <button onClick="document.location.href='<%=request.getContextPath()%>/department.do?method=list';" class="button_bg">退出</button>
         </div>   
    	</div>
    </div>   
<jsp:include page="/index.do?method=foot" flush="true"/>
</div>
</body>
</html>

