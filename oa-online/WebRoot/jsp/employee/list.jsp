<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/employee.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/department/accountjs.jsp"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/employee/employeejs.jsp"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script> 
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/DepartmentDWR.js'></script>  
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/util.js'></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<title>员工列表页</title>
</head> 
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
        	<form id="pageForm" name="pageForm" action="<%=request.getContextPath()%>/employee.do" method="post">  
        	<input type="hidden" id="method" name="method" value="list"/> 
        	<input type="hidden" name="self.department.Token"  value="<%=session.getAttribute("self.department.Token")%>"/> 
        	<input type="hidden" name="self.employee.Token"  value="<%=session.getAttribute("self.employee.Token")%>"/> 
        	<input type="hidden" name="orderByBame" id="orderByBame" value="${form.orderByBame}"/>
        	<input type="hidden" name="orderByOrder" id="orderByOrder" value="${form.orderByOrder}"/>  
        	<input type="hidden" id="id" name="id" /> 
    	<div id="mainpage">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span>
        	<span><a>基础信息</a></span><span><a href="<%=request.getContextPath()%>/employee.do?method=list">员工管理</a></span></div>
            <div id="search">
	             <ul>
		             <li><span>姓名：</span><input type="text" id="basic.name" name="basic.name" value="${form.basic.name}"/></li> 
		             <li><span>公司：</span><input type="text" id="basic.companyName"  name="basic.companyName" value="${form.basic.companyName}" /></li>  
		             <li><span>部门：</span><input type="text" id="basic.depName"  name="basic.depName" value="${form.basic.depName}"/></li>  
		             <li><span>学位：</span><input type="text" name="basic.degree" id="basic.degree" value="${form.basic.degree}" /></li>
	             </ul>
	             <ul>
	                  <li><span>状态：</span><select id="basic.employeeState"  name="basic.employeeState">
		                <option value=""          <c:if test="${form.basic.employeeState=='' || form.basic.employeeState==null}">selected</c:if>>--全部--</option>
		                <option value="normal"    <c:if test="${form.basic.employeeState=='normal'}">selected</c:if>>正式员工</option>
		                <option value="try"       <c:if test="${form.basic.employeeState=='try'}">selected</c:if>>试用员工</option>
		                <option value="multipart" <c:if test="${form.basic.employeeState=='multipart'}">selected</c:if>>兼职员工</option>
		                <option value="off"       <c:if test="${form.basic.employeeState=='off'}">selected</c:if>>离职员工</option>
		                <option value="other"     <c:if test="${form.basic.employeeState=='other'}">selected</c:if>>其它员工</option>
		                </select>
		              </li>
		              <li><span>婚姻状况：</span><select type="text" name="basic.marry" id="basic.marry">
							     <option value="" <c:if test="${form.basic.marry=='' || form.basic.marry==null}">selected</c:if>>---全部---</option>
							     <option value="未婚" <c:if test="${form.basic.marry=='未婚'}">selected</c:if>>未婚</option>
							     <option value="已婚" <c:if test="${form.basic.marry=='已婚'}">selected</c:if>>已婚</option> 
						  </select></li>
					<li><span>政治面貌：</span><input type="text" name="basic.politics" id="basic.politics"  value="${form.basic.politics}"/></li>
				 </ul>
				 <ul>
					<li><span>时间：</span><select type="text" name="timeitem" >
							 <option value="addDate" <c:if test="${form.timeitem=='addDate' || form.timeitem=='' || form.timeitem==null}">selected</c:if>>创建时间</option>
							 <option value="inWorkDate" <c:if test="${form.timeitem=='inWorkDate'}">selected</c:if>>入职时间</option>
							 <option value="realWorkDate" <c:if test="${form.timeitem=='realWorkDate'}">selected</c:if>>转正时间</option> 
							 <option value="endDate" <c:if test="${form.timeitem=='endDate'}">selected</c:if>>到期时间</option> 
							 <option value="birthday" <c:if test="${form.timeitem=='birthday'}">selected</c:if>>出生时间</option> 
							 <option value="offWorkDate" <c:if test="${form.timeitem=='offWorkDate'}">selected</c:if>>离职时间</option> 
							 <option value="nextCheck" <c:if test="${form.timeitem=='nextCheck'}">selected</c:if>>下次验本时间</option>
							 <option value="startWrokDate" <c:if test="${form.timeitem=='startWrokDate'}">selected</c:if>>参加工作时间</option>
						 </select></li>
					  <li><span>从：</span><input type="text" name="startTime" value="${form.startTime}"  readonly onclick="WdatePicker()"/></li>
					  <li><span>到：</span><input type="text" name="endTime" value="${form.endTime}"  readonly onclick="WdatePicker()"/></li>
		              <li><button onclick="search();" class="button_bg">搜索</button></li>
		          </ul>
              </div>
              <div class="index_pic">员工列表：</div> 
            <div id="operat_title"> 
              <div class="inpageinfo" id="inpageinfo">${employeeret}</div>
              <script type="text/javascript">
	              var employeeret = "${employeeret}" ;
	              if(employeeret!=null && employeeret!="")
	                 document.getElementById("inpageinfo").style.display= "block" ;
              </script>
               <%session.removeAttribute("employeeret");%>
               <c:if test="${user.permissionMap.employee_append==true}"><button class="button_add" id="addShow" onclick="document.location.href='<%=request.getContextPath()%>/employee.do?method=preAdd';">新建</button></c:if>
            </div>
            <div id="extend_content"> 
                <ul class="list_title">
                    <li class="selall"></li>
                	<li class="employee_employeecode"    onClick="orderby(this)" id="employeeCode">员工编码</li>
                    <li class="employee_employeename"    onClick="orderby(this)" id="name">员工姓名</li>
                    <li class="employee_comparent"       onClick="orderby(this)" id="companyName">所属公司</li>
                    <li class="employee_depparent"  		onClick="orderby(this)" id="depName">所属部门</li>
                    <li class="employee_employeestate"  		onClick="orderby(this)" id="marry">婚姻状况</li>
                    <li class="employee_depparent"  		onClick="orderby(this)" id="politics">政治面貌</li>
                    <li class="employee_employeestate"   onClick="orderby(this)" id="degree">学位</li>
                    <li class="employee_depparent"   onClick="orderby(this)" id="employeeState">状态</li> 
                    <c:if test="${form.timeitem=='addDate' || form.timeitem=='' || form.timeitem==null}"> 
                    <li class="employee_createtime"      onClick="orderby(this)" id="addDate">录入时间</li></c:if> 
                    <c:if test="${form.timeitem=='inWorkDate'}"> 
                    <li class="employee_createtime"      onClick="orderby(this)" id="inWorkDate">入职时间</li></c:if> 
                    <c:if test="${form.timeitem=='realWorkDate'}"> 
                    <li class="employee_createtime"      onClick="orderby(this)" id="realWorkDate">转正时间</li></c:if> 
                    <c:if test="${form.timeitem=='endDate'}"> 
                    <li class="employee_createtime"      onClick="orderby(this)" id="endDate">到期时间</li></c:if> 
                    <c:if test="${form.timeitem=='birthday'}"> 
                    <li class="employee_createtime"      onClick="orderby(this)" id="birthday">出生时间</li></c:if> 
                    <c:if test="${form.timeitem=='offWorkDate'}"> 
                    <li class="employee_createtime"      onClick="orderby(this)" id="offWorkDate">离职时间</li></c:if> 
                    <c:if test="${form.timeitem=='nextCheck'}"> 
                    <li class="employee_createtime"      onClick="orderby(this)" id="nextCheck">下次验本时间</li></c:if> 
                    <c:if test="${form.timeitem=='startWrokDate'}"> 
                    <li class="employee_createtime"      onClick="orderby(this)" id="startWrokDate">参加工作时间</li></c:if>  
                    <li class="employee_sigleoperat">操作</li>
                </ul>
    			<c:forEach var="employee" items="${emlist}">
				<ul class="com_list_content" <c:if test="${user.permissionMap.employee_modify==true}">ondblclick="editEmployee('${employee.id}');"</c:if>  >
                    <li class="selall"> 
	                   <c:if test="${!(user.permissionMap.employee_del && (employee.account==null || employee.account==''))}"><img   src="<%=request.getContextPath()%>/images/checkbox_gray.gif" /></c:if>
					   <c:if test="${user.permissionMap.employee_del && (employee.account==null || employee.account=='')}"><input name="emids"  type="checkbox" value="${employee.id}" /></c:if> 
		  		    </li>
                	<li class="employee_employeecode"><a   href="<%=request.getContextPath()%>/employee.do?method=detail&id=${employee.id}">${employee.employeeCode}</a></li>
                    <li class="employee_employeename">${employee.name}</li>
                    <li class="employee_comparent"><a id="prea" name="comparent_substring">${employee.companyName}</a></li>
                    <li class="employee_depparent">${employee.depName}</li>
                    <li class="employee_employeestate">${employee.marry}</li>
                    <li class="employee_depparent">${employee.politics}</li>
                    <li class="employee_employeestate">${employee.degree}</li>
                    <li class="employee_depparent center">
                      <c:if test="${employee.employeeState=='normal'}">正式员工</c:if>
                  	  <c:if test="${employee.employeeState=='try'}">试用员工</c:if>
                 	  <c:if test="${employee.employeeState=='off'}">离职员工</c:if>
                 	  <c:if test="${employee.employeeState=='multipart'}">兼职员工</c:if>
                  	  <c:if test="${employee.employeeState=='other'}">其它员工</c:if>
                    </li>
                    <li class="employee_createtime"> 
                       <c:if test="${form.timeitem=='addDate' || form.timeitem=='' || form.timeitem==null}">
                       <fmt:formatDate value="${employee.addDate}" pattern="yyyy-MM-dd"/></c:if>
                       <c:if test="${form.timeitem=='inWorkDate'}"><fmt:formatDate value="${employee.inWorkDate}" pattern="yyyy-MM-dd"/></c:if>
                       <c:if test="${form.timeitem=='realWorkDate'}"><fmt:formatDate value="${employee.realWorkDate}" pattern="yyyy-MM-dd"/></c:if>
                       <c:if test="${form.timeitem=='endDate'}"><fmt:formatDate value="${employee.endDate}" pattern="yyyy-MM-dd"/></c:if>
                       <c:if test="${form.timeitem=='birthday'}"><fmt:formatDate value="${employee.birthday}" pattern="yyyy-MM-dd"/></c:if>
                       <c:if test="${form.timeitem=='offWorkDate'}"><fmt:formatDate value="${employee.offWorkDate}" pattern="yyyy-MM-dd"/></c:if>
                       <c:if test="${form.timeitem=='nextCheck'}"><fmt:formatDate value="${employee.nextCheck}" pattern="yyyy-MM-dd"/></c:if>
                       <c:if test="${form.timeitem=='startWrokDate'}"><fmt:formatDate value="${employee.startWrokDate}" pattern="yyyy-MM-dd"/></c:if>
                    </li>
                    <li class="employee_sigleoperat">
                       <c:if test="${user.permissionMap.employee_modify==true}"> 
                       <a title="编辑" href="javascript:editEmployee('${employee.id}');"><img src="<%=request.getContextPath()%>/images/edit_mini.png"/></a></c:if> 
                       <c:if test="${user.permissionMap.employee_del==true && (employee.account==null || employee.account=='')}"> 
                       <a class="d_box" title="删除" href="javascript:delEmployee('${employee.id}');"><img src="<%=request.getContextPath()%>/images/delete.png"/></a></c:if> 
                       <c:if test="${(employee.account==null || employee.account=='') && (user.permissionMap.employee_createAccount==true) && (employee.employeeState!='off')}">
                       <a class="d_box" title="创建账号" href="javascript:addAccount('${employee.id}');"><img src="<%=request.getContextPath()%>/images/employee.png"/></a></c:if>
                  	</li>
                </ul>
                </c:forEach>
            </div> 
			<div id="com_all_delete">
				<button id="chall" onclick="selAll('emids');" class="button_bg">全选</button>
				<c:if test="${user.permissionMap.employee_del==true}">
				<button id="delAll" onclick="delSomeEm();" class="button_bg">删除</button>
				</c:if> 
			</div>
			<div id="page">
		<%@include file="/inc/pagecontrol.inc" %>
		</div>
        </div>
    </div>
    <jsp:include page="/index.do?method=foot" flush="true"/> 
</div>
</body>
</html>

<script type="text/javascript">
orderByInit();   
function selOption(obj,val,tv){
  var options = document.getElementById(obj).options ; 
  for(var i=0 ; i < options.length ; i++){
    if(tv=="text" && val==options[i].text){ 
        document.getElementById(obj).selectedIndex = i ;
        return true ; 
    }
    if(tv=="value" && val==options[i].value){
        document.getElementById(obj).selectedIndex = i ;
        return true ; 
    } 
  } 
  return false ;
}
selOption("basic.employeeState",'${form.basic.employeeState}',"value") ;  


titleInit('comparent_substring',12) ;
</script> 

