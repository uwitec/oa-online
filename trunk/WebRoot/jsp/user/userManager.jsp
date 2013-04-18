<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/account_supervise.css" />
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/UserDwr.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>

<title>UserManager</title>
</head>
<body>
<div id="layout">
  <jsp:include page="/index.do?method=head" flush="true"></jsp:include>
  <div id="mid">
   <jsp:include page="/index.do?method=left" flush="true"></jsp:include>
    <div id="mainpage">
      <div id="location"> 当前位置：<span class="fp"><a href="#">我的桌面</a></span><span><a href="#">系统设置</a></span><span><a href="#">管理账户</a></span> </div>
      <div class="button_TOP">
        <h2 class="account_title_new">管理账户：</h2><div id="inpageinfo"></div>
        <c:if test="${editSuccess==true}"><label class="errorinfo" id="EditSuccess">用户信息修改成功！</label></c:if>
		<c:if test="${editSuccess!=true}"><label class="errorinfo" id="EditSuccess"></label></c:if>
		<c:if test="${addSuccess==true}"><label class="errorinfo" id="AddSuccess">添加用户成功！</label></c:if>
		<c:if test="${addSuccess!=true}"><label class="errorinfo" id="AddSuccess"></label></c:if>
		<c:if test="${delSuccess==true}"><label class="errorinfo" id="DelSuccess">删除用户成功！</label></c:if>
		<c:if test="${delSuccess!=true}"><label class="errorinfo" id="DelSuccess"></label></c:if></h2>
        
      </div>
<form name="pageForm" action="" method="POST" >
		<div id="search">
		<ul>
			<li>用户ID</li>
			<li><input type="text" id="queryUserId" name="queryUserId" value="${form.queryUserId}"/></li>
		</ul>
		<ul>
			<li>用户姓名</li>
			<li><input type="text" id="queryUserName" name="queryUserName" value="${form.queryUserName}" /></li>
		</ul>
			<button id="subSearch" class="button_add left_button">搜索</button>
		</div>
		<button id="selectAll" class="button_bg">全选</button>
        <button id="delSelect"class="button_bg">删除</button>
        <button id="addNewUser" class="button_add left_button">新建</button>
      <div id="list_table">
        <ul class="list_title">
          <li class="list_tab_checkbox"></li>
          <li class="list_tab_userid">用户ID</li>
          <li class="list_tab_username">用户名</li>
          <li class="list_tab_operate">操作</li>
        </ul>
        <div class="list_content_box">
        <c:forEach var="name" items="${list}">
		<ul class="doc_list">
			<li class="list_tab_checkbox"><input type="checkbox" name="checkname" value="<c:out value="${name.id}" />" /></li>
			<li class="list_tab_userid"><span class="center"><c:out value="${name.userId}" /></span></li>
			<li class="list_tab_username"><span class="center"><c:out value="${name.employee}" /></span></li>
			<li class="list_tab_operate"><span class="center">
			<a id="editUser" href="<%=request.getContextPath()%>/user.do?method=edit&editId=<c:out value="${name.id}" />"><img src="<%=request.getContextPath() %>/images/edit_mini.png" /></a>
			<a href="#" onClick="del('<c:out value="${name.id}" />','<c:out value="${editUser.id}" />','<c:out value="${name.userId}" />');" /><img src="<%=request.getContextPath() %>/images/delete.png" /></a></span>
			</li>
		</ul>
		</c:forEach>
        </div>
        </div>
        <div><%@ include file="/inc/pagecontrol.inc"%></div>
    	</form>
    	<jsp:include page="addUserForm.jsp" flush="true"></jsp:include>
    	<jsp:include page="editUserForm.jsp" flush="true"></jsp:include>
      </div>
    </div>
    <jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
    <c:if test="${Edit}">
	<script type=text/javascript language=JavaScript>
	document.getElementById("editForm").style.display="block";
	</script>
	</c:if>
  </div>
  
  <script type=text/javascript language=JavaScript>
	 	function addmenEdit(){
			var d = document.getElementById('select1Edit');
			var s = document.getElementById('select2Edit');
			var p = document.getElementById('permissionEdit');
			//alert(d.options.length);
			if (s.length >=1){
				document.getElementById("editError").innerHTML="只能为用户分配一个角色!";
				return false;
			}
			for(var i=0;i<d.options.length;i++){
				if(d.options[i].selected){
					//alert(d.options[i].text);
					var obj = new Option(d.options[i].text,d.options[i].value);
					s.options.add(obj);
					if(p.value == ""){
						p.value = p.value+d.options[i].value;
					}else{
						p.value = p.value+"|"+d.options[i].value;
					}
					d.remove(i);
				}
			}
		}
		function returnmenEdit(){
			document.getElementById("editError").innerHTML="";
			var d = document.getElementById('select1Edit');
			var r = document.getElementById('select2Edit');
			var p = document.getElementById('permissionEdit');
			for(i=0;i<r.options.length;i++){
				if(true==r.options[i].selected){
					//alert(d.options[i].text);
					var obj = new Option(r.options[i].text,r.options[i].value);
					r.remove(i);
					d.options.add(obj);
				}
			}
			p.value = "";
			for(i=0;i<r.options.length;i++){
				if(p.value == ""){
					p.value = p.value+r.options[i].value;
				}else{
					p.value = p.value+"|"+r.options[i].value;
				}
				}
		}


		document.getElementById('delSelect').onclick=function(){
			//DWR验证
			var ids = document.getElementsByName('checkname');
			//将Values制作成数组
			var idsValue = [];
			var idsValuelength=0;
			for( var i = 0; i < ids.length; i++ )
			{
				if ( ids[i].checked ){
					idsValue[idsValuelength] = ids[i].value;
					idsValuelength++;
				}
			}
			if(idsValuelength==0){
				showMsgbox("请选择要删除的用户");
				return;
			}
			var take=function(data)
			{
				var isSucceed = 0;
				if(data){
						document.forms['pageForm'].submit();
						isSucceed = 1;
				}
				else{
					document.getElementById("delfailure").innerHTML="用户删除未成功！";
				}
			}

			var delale = new DivWindow("delale","系统提示",200,90,"确定要删除所选账号？");
			delale.addButton("确定",function(){
				UserDwr.delUser(idsValue,take);
				window.parent.closeDivWindow("delale");
				});	
			delale.addButton("取消");
			delale.open();	
		}
				
</script>
  
<script type="text/javascript">
$("#addNewUser").click(function(){
	$("#editForm").hide();
	$("#addUser").show();
	});

$("#selectAll").click(function(){
		$("input[name='checkname']").attr("checked",$(this).attr("checked",true));
});

$("#subSearch").click(function(){
	var queryUserId=$("#queryUserId")[0].value;
	var queryUserName=$("#queryUserName")[0].value;
	
	document.forms['pageForm'].pageNo.value=1;
	document.forms['pageForm'].submit();
});



document.getElementById('passWordEdit').onchange=function(){
	document.getElementById("changePassword").value="1";
}


/*
 * 单行删除
 */
 function del(id,editId,userName){
		var chinpageinfo = document.getElementById("inpageinfo");
		var delId =  new   Array();
		delId[0] = id;
		if(userName == "admin"){
			showMsgbox("不能删除系统内置用户！");
			return false;
		}
		if (delId[0] == editId){
			showMsgbox("无法删除正在编辑的用户！");
			return false;
		}else{ 
			var take=function(data)
			{
				var isSucceed = 0;
				if(data){
						document.forms['pageForm'].submit();
						isSucceed = 1;
				}
				else{
					showMsgbox("用户删除未成功！");
				}
			}

			var delale = new DivWindow("delale","系统提示",200,90,"确定要删除所选账号？");
			delale.addButton("确定",function(){
				UserDwr.delUser(delId,take);
				closeDivWindow("delale");
				});	
			delale.addButton("取消");
			delale.open();
		}
	}


 function isEmpty(){
		var name = document.getElementById("employee").value;
		var username = document.getElementById("userId").value;
		var password = document.getElementById("passWord").value;
		var password1 = document.getElementById("passWord1").value;
		document.getElementById("error").innerHTML="";
		if(username.length>1 && password.length>1 && password1.length>1){
			if(name.length>1){
				document.getElementById("error").innerHTML="";
			}else{
				document.getElementById("error").innerHTML="用户姓名不能为空！";
			}
		}
	}

	var userNameIsTrue = 1;
	var returnvalue;
	function isExist(){
		var username = document.getElementById("userId").value;
		if (isRegisterUserName(document.getElementById("userId").value)){
			return false;
		}
		UserDwr.isExist(username,function(test){
			if(test)
			{
				document.getElementById("error").innerHTML="用户名已存在！";
				returnvalue = 1;
			}
			else{
				document.getElementById("error").innerHTML="";
				returnvalue = 0;
			}
			});
	}

 function isRegisterUserName(username)   
 {   
 	document.getElementById("error").innerHTML="";
 	var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
 	if(username.length==0){
 		document.getElementById("error").innerHTML="用户名不能为空！";
 		return true;
 	}if (username.length<5){
 		document.getElementById("error").innerHTML="用户名位数不正确！";
 		return true;
 	}
 	userNameIsTrue = 1;
 	return false;
 }

 

 document.getElementById('sub').onclick=function(){
		var username = document.getElementById("userId").value;
		var password = document.getElementById("passWord").value;
		var password1 = document.getElementById("passWord1").value;
		var name = document.getElementById("employee").value;
		var permission = document.getElementById("Permission").value;
		document.getElementById("error").innerHTML="";
		
		isEmpty();
		if(username.length==0){
			document.getElementById("error").innerHTML="用户名不能为空！";
			return false;
		}else if(username.length<5){
			document.getElementById("error").innerHTML="用户名位数不正确！";
			return false;
		}else if(password.length<1){
			document.getElementById("error").innerHTML="密码不能为空！";
			return false;
		}else if(password.length<6||password.length>20){
			document.getElementById("error").innerHTML="密码位数不正确！";
			return false;
		}else if(password1.length<6||password1.length>20){
			document.getElementById("error").innerHTML="确认密码位数不正确！";
			return false;
		}else if(password1.length<1){
			document.getElementById("error").innerHTML="确认密码不能为空！";
			return false;
		}else if(password != password1){
			document.getElementById("error").innerHTML="确认密码不一致！";
			return false;
		}else if(name.length<1){
			document.getElementById("error").innerHTML="用户姓名不能为空！";
			return false;
		}else if(permission.length<1){
			document.getElementById("error").innerHTML="请选择权限！";
			return false;
		}else if(returnvalue == 1){
			return false;
		}else if(userNameIsTrue == 0){
			document.getElementById("error").innerHTML="用户名位数不正确！";
			return false;
		}

		document.forms['addUser'].submit();
}

 document.getElementById('res').onclick=function(){
		var delale = new DivWindow("delale","系统提示",300,100,"取消后当前所填数据将不被保存，是否确认取消？");
		delale.addButton("确定",function(){
			document.forms['addUser'].action="<%=request.getContextPath() %>/user.do?method=resEdit";
			document.forms['addUser'].submit();
			document.getElementById("addUser").style.display="none"
			closeDivWindow("delale");
			});	
		delale.addButton("取消");
		delale.open();	
		
}


 document.getElementById('subEdit').onclick=function(){
		var isEmail = 1;
		
		document.getElementById("editError").innerHTML="";

		var nameEdit = document.getElementById("employeeEdit").value;
		var permissionedit = document.getElementById("permissionEdit");
		var password = document.getElementById("passWordEdit").value;
		var r = document.getElementById('select2Edit');
		permissionedit.value = "";
		
		for(i=0;i < r.options.length;i++){
			if(permissionedit.value == ""){
				permissionedit.value = permissionedit.value+r.options[i].value;
			}else{
				permissionedit.value = permissionedit.value+"|"+r.options[i].value;
			}
		}
		if(nameEdit.length<1){
			document.getElementById("editError").innerHTML="用户名不能为空！";
			return false;
		}else if(password.length<6){
			document.getElementById("editError").innerHTML="密码位数不足6位！";
			return false;
		}else if(isEmail != 1){
			document.getElementById("editError").innerHTML="E-mail格式不正确！";
			return false;
		}else if(permissionedit.length<1){
			document.getElementById("editError").innerHTML="权限不能为空！";
			return false;
		}
		if (document.getElementById("changePassword").value == "1"){
			if (document.getElementById("CoPassWrord").value != document.getElementById("passWordEdit").value){
				document.getElementById("editError").innerHTML="权限不能为空！";
				return false;
			}
		}
		if (document.getElementById("changePassword").value == "1"){
			document.forms['editForm'].action="<%=request.getContextPath() %>/user.do?method=update&status='changepassword'";
		}else{
			document.forms['editForm'].action="<%=request.getContextPath() %>/user.do?method=update";
		}
		document.forms['editForm'].submit();
	}
	document.getElementById('resEdit').onclick=function(){
		document.forms['editForm'].action="<%=request.getContextPath() %>/user.do?method=resEdit";
		document.forms['editForm'].submit();
		document.getElementById("editForm").style.display="none";
	}
</script>
</body>
</html>