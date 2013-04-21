<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>AddUser1</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/user.do?method=add" method="post" id="addUser" class="hiddenuserform">
<input type="hidden" name="permission" id="Permission">
<div id="new_user_box">
        <div class="index_pic">新建账户：</div>
        <ul>
          <li><span>用户ID：</span>
            <input name="wu.userId" type="text" id="userId" onKeyDown="value=value.replace(/[^\da-zA-Z]/g,'')" maxlength="20" onfocusout="return isExist();" class="right" />
            <a>用户名长度为5~20个字符,且以字母开头。</a></li>
          <li><span>密码：</span>
            <input name="wu.passWord" type="password" id="passWord" maxlength="20" onfocusout="passWd()" class="right" />
            <a>密码长度为6~20位。</a></li>
          <li><span>确认密码：</span>
            <input name="wu.passWord1" type="password" id="passWord1" maxlength="20" onfocusout="passWd()" class="right" />
          </li>
          <li><span>选择员工：</span>
            <input type="text" name="wu.employee" id="employee" onKeyDown="value=value.replace(/[^\da-zA-Z-~\u4E00-\u9FA5]/g,'')" class="right" />
            <a href="javascript:showEmployee();"><img src="images/looking.png" /></a></li>
            <input type="hidden" name="wu.employeeHidden" id="employeeHidden" value="" />
        </ul>
      </div>
      <table>
        <tr>
          <th colspan="4" class="th_left">权限：</th>
        </tr>
        <tr>
          	<td>
          		<select name="select1" size="4" multiple>
					<c:forEach var="permission" items="${permissionsession}">
					<option value="<c:out value="${permission.id}"></c:out>"><c:out value="${permission.roleName}"></c:out>
					</option>
	        		</c:forEach>
      			</select>
      		</td>
          	<td class="td_center">
          		<a name="d" type="button" id="d" value="&lt;&lt;" onclick="returnmen();"><img src="<%=request.getContextPath() %>/images/left-acc.gif"/></a>
          		<a name="d" type="button" id="d" value="&gt;&gt;" onclick="addmen();"><img src="<%=request.getContextPath() %>/images/right-acc.gif" /></a>
          	</td>
          	<td>
          		<select name="select2" size="4" multiple>
            	</select>
          	</td>
        </tr>
      </table>
<label><p class="errorinfo" id="error"></p></label>
<div class="button_adduser">
        <button id="sub" class="button_bg">保存</button>
        <button id="res" class="button_bg">退出</button>
</div>
</form>
<script type="text/javascript">
function showEmployee(){
	var selectLocation = new DivWindow("selectEmployee","员工选择窗口",530,460,"","<%=request.getContextPath() %>/user.do?method=returnEmployee");
	selectLocation.addButton("取消");	
	selectLocation.open();
	}
</script>

<script type="text/javascript">

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
function allmen(){
	var d = document.getElementById('select1');
	var s = document.getElementById('select2');
	//alert(d.options.length);
	for(var i=0;i<d.options.length;i++){
			//alert(d.options[i].text);
			var obj = new Option(d.options[i].text,d.options[i].value);
			s.options.add(obj);
			d.remove(i);
	}
}
function returnmen(){
	var d = document.getElementById('select1');
	var r = document.getElementById('select2');
	var p = document.getElementById('Permission');
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
</script>

</body>
</html>