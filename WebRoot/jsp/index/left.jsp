<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
    		function goToFlex(id){
        		window.location = "FHIOA-debug/AgencySelectSwf.html?id="+id;
    		}
</script>
		<div id="left_nav_bg">
        	<div id="left_nav">
        		<div class="left_nav_title_no1" onclick="window.location='<%=request.getContextPath()%>/index.do?method=index'"></div>
        		<!-- 个人中心 -->
        		<div class="left_nav_title" id="menu_a" onclick="showmenu('a') "><img src="<%=request.getContextPath() %>/images/pc/02.png"/>个人中心</div>
  				<div id="list_a" class="left_nav_content" style="display:none">
    				<ul>
    					<li><a href="<%=request.getContextPath()%>/sysmsg.do?method=index">通知管理</a></li>
    					<c:if test="${user.permissionMap.isUseWorkTask}"><li><a href="<%=request.getContextPath()%>/workplan.do?method=index">工作计划</a></li></c:if>
		        		<li><a href="<%=request.getContextPath()%>/user.do?method=modify&editId=${user.id}">密码修改</a></li>
		        		<li><a href="<%=request.getContextPath()%>/questionnaire.do?method=selectQuestionnaire&editId=${user.id}" >调查问卷</a></li>
		        		<li><a href="<%=request.getContextPath()%>/questionnaire.do?method=questionnaireManagement&editId=${user.id}">问卷管理</a></li>
    				</ul>
  				</div>
  				<!-- 电子期刊 -->
  				<div class="left_nav_title"><img src="<%=request.getContextPath() %>/images/pc/11.png"/><a href="<%=request.getContextPath()%>/journal.do?method=index">电子期刊</a></div>
  				<!-- 内部信息库 -->
        		<c:if test="${user.permissionMap.isWatchDocument||user.permissionMap.isWatchPhoto||user.permissionMap.isInPrice}">
        		<div class="left_nav_title" id="menu_b" onclick="showmenu('b')"><img src="<%=request.getContextPath() %>/images/pc/03.png"/>内部信息库</div>
  				<div id="list_b" class="left_nav_content" style="display:none">
    				<ul>      						
		        		<c:if test="${user.permissionMap.isWatchDocument}"><li><a href="<%=request.getContextPath()%>/document.do?method=showIndex">文档资料</a></li></c:if>
		        		<c:if test="${user.permissionMap.isWatchPhoto}"><li><a href="<%=request.getContextPath()%>/picture.do?method=showIndex">图库搜索</a></li></c:if>
		        		<c:if test="${user.permissionMap.isInPrice}"><li><a href="<%=request.getContextPath()%>/freight.do?method=showQuery">报价查询</a></li></c:if>
    				</ul>
  				</div>
  				</c:if>
  				<!-- 资料发布管理 -->
  				<c:if test="${user.permissionMap.isManagerPhoto||user.permissionMap.isManagerDocument||user.permissionMap.isAddPrice||user.permissionMap.isUpdatePrice||user.permissionMap.isUpdatePriceAll}">
  				<div class="left_nav_title" id="menu_c" onclick="showmenu('c')"><img src="<%=request.getContextPath() %>/images/pc/04.png"/>资料发布管理</div>
  				<div id="list_c" class="left_nav_content" style="display:none">
    				<ul>
						<c:if test="${user.permissionMap.isManagerDocument}"><li><a href="<%=request.getContextPath()%>/document.do?method=index">文档管理</a></li></c:if> 
		        		<c:if test="${user.permissionMap.isManagerPhoto}"><li><a href="<%=request.getContextPath()%>/picture.do?method=index">图库管理</a></li></c:if>
		        		<c:if test="${user.permissionMap.isAddPrice||user.permissionMap.isUpdatePrice||user.permissionMap.isUpdatePriceAll}"><li><a href="<%=request.getContextPath()%>/freight.do?method=index">报价管理</a></li></c:if>
    				</ul>
  				</div>
  				</c:if> 
  				<!-- 基础信息维护 --> 				
  				<c:if test="${user.permissionMap.FhiOa_searchunit||user.permissionMap.FhiOa_searchmoney||user.permissionMap.company_view||user.permissionMap.department_view||user.permissionMap.employee_view||user.permissionMap.custom_all || user.permissionMap.custom_select ||user.permissionMap.custom_self}">
  				<div class="left_nav_title" id="menu_d" onclick="showmenu('d')"><img src="<%=request.getContextPath() %>/images/pc/08.png"/>基础信息</div>
  				<div id="list_d" class="left_nav_content" style="display:none">
		        	<ul>
		        		<c:if test="${user.permissionMap.company_view}"><li><a href="<%=request.getContextPath()%>/company.do?method=list">公司信息</a></li></c:if>
		        		<c:if test="${user.permissionMap.department_view}"><li><a href="<%=request.getContextPath()%>/department.do?method=list">部门信息</a></li></c:if>
		        		<c:if test="${user.permissionMap.employee_view}"><li><a href="<%=request.getContextPath()%>/employee.do?method=list">员工信息</a></li></c:if>
		        		<c:if test="${user.permissionMap.custom_all || user.permissionMap.custom_select || user.permissionMap.custom_self}"><li><a href="<%=request.getContextPath()%>/custom.do?method=list">客户信息</a></li></c:if>
		        	</ul>
		        </div>
		        </c:if>
		        <!-- 系统设置管理 -->
		        <c:if test="${user.permissionMap.sys_config}">
			        <div class="left_nav_title" id="menu_z" onclick="showmenu('z')"><img src="<%=request.getContextPath() %>/images/pc/09.png"/>系统设置</div>
	  				<div id="list_z" class="left_nav_content" style="display:none">
			        	<ul>
			            	<li><a href="<%=request.getContextPath() %>/user.do?method=query">管理账户</a></li>
			            	<li><a href="<%=request.getContextPath() %>/permission.do?method=getRole">角色管理</a></li>
			            	<li><a href="<%=request.getContextPath() %>/permission.do?method=getModel">添加模块权限</a></li>
							<li><a href="<%=request.getContextPath()%>/system.do?method=index">系统配置</a></li>
							<li><a href="<%=request.getContextPath()%>/jsp/permission/creatRoMo.jsp">权限编辑</a></li>
			                <c:if test="${user.permissionMap.sys_auto_code}">
			                <li><a href="<%=request.getContextPath() %>/system.do?method=autoCodeIndex">自动单号</a></li>
			               	</c:if>
						</ul>
			        </div>
		        </c:if>
            </div>
    	</div>