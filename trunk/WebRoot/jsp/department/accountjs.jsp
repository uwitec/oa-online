<%@ page language="java" pageEncoding="utf-8"%>
function addmen(){
		var d = document.getElementById('select1');
		var s = document.getElementById('select2');
		var p = document.getElementById('Permission');
		var select2length = document.getElementById("select2");
		//alert(d.options.length);
		if (select2length.length >=1){
			document.getElementById("error").innerHTML="只能为用户分配一个角色!";
			return false;
		}
		for(var i=0;i < d.options.length;i++){
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
		function returnmen(){
		var d = document.getElementById('select1');
		var r = document.getElementById('select2');
		var p = document.getElementById('Permission');
		for(i=0;i < r.options.length;i++){
			if(true==r.options[i].selected){
				//alert(d.options[i].text);
				var obj = new Option(r.options[i].text,r.options[i].value);
				r.remove(i);
				d.options.add(obj);
			}
		}
		p.value = "";
		for(i=0;i < r.options.length;i++){
			if(p.value == ""){
				p.value = p.value+r.options[i].value;
			}else{
				p.value = p.value+"|"+r.options[i].value;
			}
			}
	}
returnvalue = "" ;
function isExist(){
	var username = document.getElementById("userId").value; 
	if (isRegisterUserName(document.getElementById("userId").value)){ 
		return false;
	} 
	UserDwr.isExist(username,function(test){
	document.getElementById("error").innerHTML="";
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
userNameIsTrue = 0 ;
function isRegisterUserName(username)   
{   
	document.getElementById("error").innerHTML="";
	var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
	if(username.length==0){
		document.getElementById("error").innerHTML="用户名不能为空！";
		return true;
	}else if (username.length<5){
		document.getElementById("error").innerHTML="用户名位数不正确！";
		return true;
	}
	userNameIsTrue = 1; 
	return false  
}

function passWd(){
	var password = document.getElementById("passWord").value;
	var password1 = document.getElementById("passWord1").value;
	document.getElementById("error").innerHTML="";
	if(password.length>1){
		if(password.length<6){
			document.getElementById("error").innerHTML="密码位数不正确！";
			return false;
			}
	}
	if(password1.length>1){
		if(password != password1){
			document.getElementById("error").innerHTML="确认密码不一致！";
			document.getElementById("passWord1").focus();
			return false;
		}else{
			document.getElementById("error").innerHTML="";
		}
		if(password1.length<6){
			document.getElementById("error").innerHTML="密码位数不正确！";
			return false;
		}
	}
}
function isEmail(){}

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

function addUserSub(){
    var childdoc = document.frames[1].document ;  
	//var emailvalue = childdoc.getElementById("email").value; 
	var username = childdoc.getElementById("userId").value;
	var password = childdoc.getElementById("passWord").value; 
	var password1 = childdoc.getElementById("passWord1").value; 
	var name = childdoc.getElementById("employee").value; 
	var permission = childdoc.getElementById("Permission").value; 
	//var isEmailTrue = 0;
	childdoc.getElementById("error").innerHTML="";
	/*  
	function isRegisterEmail(userEmail)   
	{   
		var patrn=/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
		if (!patrn.exec(userEmail)){
			childdoc.getElementById("email").value = "";
			isEmailTrue = 0;
		 	return false;
		}
		childdoc.getElementById("error").innerHTML="";
		isEmailTrue = 1;
		return true;
	} 
	 isRegisterEmail(childdoc.getElementById("email").value);
	 */
	//isEmpty();
	if(username.length==0){
		childdoc.getElementById("error").innerHTML="用户名不能为空！";
		return false;
	}else if(username.length<5){
		childdoc.getElementById("error").innerHTML="用户名位数不正确！";
		return false;
	}else if(password.length<1){
		childdoc.getElementById("error").innerHTML="密码不能为空！";
		return false;
	}else if(password.length<6||password.length>20){
		childdoc.getElementById("error").innerHTML="密码位数不正确！";
		return false;
	}else if(password1.length<6||password1.length>20){
		childdoc.getElementById("error").innerHTML="确认密码位数不正确！";
		return false;
	}else if(password1.length<1){
		childdoc.getElementById("error").innerHTML="确认密码不能为空！";
		return false;
	}else if(password != password1){
		childdoc.getElementById("error").innerHTML="确认密码不一致！";
		return false;
	}else if(name.length<1){
		childdoc.getElementById("error").innerHTML="用户姓名不能为空！";
		return false;
	}else if(permission.length<1){
		childdoc.getElementById("error").innerHTML="请选择权限！";
		return false;
	}else if(returnvalue == 1){
		return false;
	}
	
	/*else if(isEmailTrue == 0){
		childdoc.getElementById("error").innerHTML="E-mail格式不正确！";
		return false;
	}else if(emailvalue.lenght<1){
		childdoc.getElementById("error").innerHTML="不能为空！";
		return false;
	}
	*/
	childdoc.forms['addUser'].submit();
}

function cancel(){
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

function addAccount(id){ 
	var selectLocation = new DivWindow("selectEmployee","账户创建窗口",350,400,"","<%=request.getContextPath()%>/user.do?method=preAddUser&id="+id);
	selectLocation.addButton("确定",addUserSub);	
	selectLocation.addButton("取消");
	selectLocation.open();
	}
