<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="loadBg"></div>
<div id="loadInfo">
	<img id="loadImg" src="<%=request.getContextPath() %>/images/load.gif"/><br>
	页面载入中...
</div>
<script type="text/javascript">
function bodyLoad(){
	var sWidth,sHeight;         
	sWidth=document.documentElement.clientWidth;
	sHeight=document.documentElement.clientHeight;
	var loadObj = document.getElementById("loadBg");
		loadObj.style.width=sWidth+"px";
		loadObj.style.height=sHeight+"px";
	var loadImg = document.getElementById("loadImg");
	var loadInfo = document.getElementById("loadInfo");
	loadInfo.style.width=loadImg.width+"px";
	loadInfo.style.height=loadImg.width+"px";
	loadInfo.style.left=(sWidth/2-loadImg.width/2);
}
bodyLoad();
var loadEnd = function(){
	var loadBg = document.getElementById("loadBg");
	loadBg.style.display = "none";
	var loadInfo = document.getElementById("loadInfo");
	loadInfo.style.display = "none";
}
window.onload=loadEnd;
</script>
<div id="head">
	<div id="logo"></div>
    <ul>
    	<input type="hidden" id="user.userId" value="${user.userId}"/>
    	<li class="user">用户：<c:out value="${user.userId}" /></li>
        <li class="help"><a href="#">帮助</a></li>
        <li class="exit"><a href="<%=request.getContextPath() %>/index.do?method=logout">退出</a></li>
    </ul>
</div>
<div class="menucontainer">
<div id="menulist">
	<ul>
		<li><a href="#">个人中心</a>
        	<ul>
        		<li><a href="<%=request.getContextPath()%>/user.do?method=modify&editId=${user.id}"><img src="<%=request.getContextPath() %>/images/nav_psw_15.png" />个人信息</a></li>
        	</ul>
        </li>
       <c:if test="${user.permissionMap.isWatchDocument||user.permissionMap.isWatchPhoto||user.permissionMap.isInPrice}">
		<li><a href="#">内部信息库</a>
        	<ul>      						
		        <c:if test="${user.permissionMap.isWatchDocument}"><li><a href="<%=request.getContextPath()%>/document.do?method=showIndex">文档资料</a></li></c:if>
		        <c:if test="${user.permissionMap.isWatchPhoto}"><li><a href="<%=request.getContextPath()%>/picture.do?method=showIndex">图库搜索</a></li></c:if>
		        <c:if test="${user.permissionMap.isInPrice}"><li><a href="<%=request.getContextPath()%>/freight.do?method=showQuery">报价查询</a></li></c:if>
    		</ul>
        </li>
		</c:if>
		<c:if test="${user.permissionMap.isManagerPhoto||user.permissionMap.isManagerDocument||user.permissionMap.isAddPrice||user.permissionMap.isUpdatePrice||user.permissionMap.isUpdatePriceAll}">
  		<li><a href="#">资料管理</a>
        	<ul>
				<c:if test="${user.permissionMap.isManagerDocument}"><li><a href="<%=request.getContextPath()%>/document.do?method=index">文档管理</a></li></c:if> 
		        <c:if test="${user.permissionMap.isManagerPhoto}"><li><a href="<%=request.getContextPath()%>/picture.do?method=index">图库管理</a></li></c:if>
		        <c:if test="${user.permissionMap.isAddPrice||user.permissionMap.isUpdatePrice||user.permissionMap.isUpdatePriceAll}"><li><a href="<%=request.getContextPath()%>/freight.do?method=index">报价管理</a></li></c:if>
    		</ul>
        </li>
        </c:if>
        <c:if test="${user.permissionMap.company_view||user.permissionMap.department_view||user.permissionMap.employee_view}">
		<li><a href="#">基础信息</a>
        	<ul>
		       <c:if test="${user.permissionMap.company_view}"><li><a href="<%=request.getContextPath()%>/company.do?method=list">公司信息</a></li></c:if>
		       <c:if test="${user.permissionMap.department_view}"><li><a href="<%=request.getContextPath()%>/department.do?method=list">部门信息</a></li></c:if>
		       <c:if test="${user.permissionMap.employee_view}"><li><a href="<%=request.getContextPath()%>/employee.do?method=list">员工信息</a></li></c:if>
		    </ul>
        </li>
        </c:if>
        <c:if test="${user.permissionMap.sys_config}">
		<li><a href="#">系统设置</a>
        	<ul>
            	<li><a href="<%=request.getContextPath() %>/user.do?method=query"><img src="<%=request.getContextPath() %>/images/nav_account_15.png" />管理账户</a></li>
		        <li><a href="<%=request.getContextPath() %>/permission.do?method=getRole"><img src="<%=request.getContextPath() %>/images/nav_account_15.png" />角色管理</a></li>
				<li><a href="<%=request.getContextPath()%>/system.do?method=index"><img src="<%=request.getContextPath() %>/images/nav_system_15.png" />系统配置</a></li>
		        <c:if test="${user.permissionMap.sys_auto_code}"><li><a href="<%=request.getContextPath() %>/system.do?method=autoCodeIndex"><img src="<%=request.getContextPath() %>/images/nav_system_15.png" />自动单号</a></li></c:if>
			</ul>
        </li>
		</c:if>
	</ul>
</div>
</div>