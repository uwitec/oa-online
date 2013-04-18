<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/dtree.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/border_detail_box.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/dtreeUTF-8.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
</head>
<body>
<div id="layout">
  <jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    <jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    <div id="mainpage">
    <div id="location"> 当前位置：<span class="fp"><a href="index.do?method=index">我的桌面</a></span><span><a href="#">系统设置</a></span><span><a href="#">角色管理</a></span></div>
    	<div class="index_pic">分配权限：</div>
    	<div id="inpageinfo"></div>
    	<div class="content_box_border">
    		<div id="trend02">
				<div class="button_order_form">
	          		<button id="chall" class="button_bg" >展开</button><!-- <a href="javascript: d.openAll();">open all</a> -->
	         		<button id="delAll" class="button_bg">收起</button><!-- <a href="javascript: d.closeAll();">close all</a> -->
				</div>
			</div>
			<form action="" id="Role" method="post">
		  <div class="bintype">
    		<ul class="depart">
				<script type="text/javascript">
					d = new dTree('d',"<%=request.getContextPath() %>");
					<c:forEach items="${Role}" var="name"> 
					d.add('${name.id}','${name.pid}','${name.roleName} <a href="javascript: add(\'${name.id}\',\'${name.isPid}\');">add</a>  <a href="javascript: modify(\'${name.id}\');">mod</a>  <a href="javascript: del(\'${name.id}\',\'${name.isPid}\',\'${name.pid}\');">del</a>','a','','','',''); 
					</c:forEach>
					document.write(d);
				</script>
	
				<script type="text/javascript">
					function addTest(id,isPid){
						document.forms['Role'].action="<%=request.getContextPath() %>/jsp/permission/addRole.jsp?id="+id+"&isPid="+isPid+"&isNew='1'";
						document.forms['Role'].submit();
					}
			
					function modify(id){
						var modifyRole = new DivWindow("modifyRole","权限修改窗口",255,260,"","<%=request.getContextPath() %>/permission.do?method=getRoleId&id="+id+""); 	
						modifyRole.addButton("确定",function(){  
					    	var childdoc =  document.frames[0].document ; 
					    	childdoc.forms['addUser'].action = "<%=request.getContextPath() %>/permission.do?method=newRole&isModify=1";
					    	childdoc.forms["addUser"].submit();
					    }) ;
						modifyRole.addButton("取消",function(){ 
					    window.parent.location.href = "<%=request.getContextPath() %>/permission.do?method=getRole" ;
					    }) ;
						modifyRole.open();  
					}
					
					function del(id,isPid,pid){
						document.forms['Role'].action="<%=request.getContextPath() %>/permission.do?method=delRole&id="+id+"&isPid="+isPid+"&pid="+pid+"";
						document.forms['Role'].submit();
					}
				</script>
			</ul>
			<ul class="link_button">
			</ul>
		  </div>
        </div>
	</div>
				<script type="text/javascript">
				function add(id,isPid){ 
			    var addRole = new DivWindow("addRole","权限添加窗口",280,280,"","<%=request.getContextPath() %>/permission.do?method=getRoleId&id="+id+"&isPid="+isPid+"&isNew=1"); 	
			    addRole.addButton("确定",function(){  
			    	var childdoc =  document.frames[0].document ; 
			    	childdoc.forms['addUser'].action = "<%=request.getContextPath() %>/permission.do?method=newRole" ;
			    	childdoc.forms["addUser"].submit();
			    }) ;
			    addRole.addButton("取消",function(){ 
			    window.parent.location.href = "<%=request.getContextPath() %>/permission.do?method=getRole" ;
			    }) ;
			    addRole.open();
			}	</script>
			</form>
	</div>
<jsp:include page="/index.do?method=foot" flush="true"/> 
</div>
</body>
</html>