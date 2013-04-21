<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form action="<%=request.getContextPath() %>/user.do?method=update" method="post" id="editForm" name="editForm" class="hiddenuserform">
<input type="hidden" name="wu.id" id="id" value="${editUser.id}">
<input type="hidden" name="wu.userId" id="userNameEdit"  value="<c:out value="${editUser.userId}" />">
<input type="hidden" name="permissionEdit" id="permissionEdit">
	<h2>修改用户信息</h2>
    <table class="new_user">
    	<tr>
    	<th class="th_left">用户ID</th>
    	<th class="th_left">用户姓名</th>
    	<th class="th_left">用户密码</th>
    	<th class="th_left">修改密码确认</th>
    	</tr>
    	<tr>
    	<td><span class="li_left"><c:out value="${editUser.userId}" /></span></td>
    	<td><input type="text" name="wu.employee" id="employeeEdit" onKeyUp="value=value.replace(/[^\da-zA-Z-~\u4E00-\u9FA5]/g,'')" value="<c:out value="${editUser.employee}" />"><a href="javascript:showEmployeeEdit();"><img src="images/folder_add.png" /></a></td>
    		<input type="text" name="wu.employeeEditHidden" id="employeeEditHidden" value="${editUser.employeeId}" />
    	<td><input name="wu.passWord" maxlength="20" type="password" id="passWordEdit" value="<c:out value="${editUser.passWord}"/>" ></td>
    		<input type="hidden" name="changePassword" id="changePassword" value="0" />
    	<td><input name="CoPassWrord" maxlength="20" type="password" id="CoPassWrord" /></td>
    	</tr>
    	<tr>
        	<th colspan="4" class="th_left">权限:</th>
    	</tr>
    	<tr>
			<td><select id="select1Edit" name="select1Edit" size="4" multiple>
			<c:forEach var="permissionRole" items="${select1Role}">
				<option value="<c:out value="${permissionRole.id}"></c:out>"><c:out value="${permissionRole.roleName}"></c:out>
				</option>
        	</c:forEach>
				</select></td>
			<td class="td_center">
	      		<input class="arrowbutton" name="d" type="button" id="d" value="&lt;&lt;" onClick="returnmenEdit();">
	        	<input class="arrowbutton" name="d" type="button" id="d" value="&gt;&gt;" onClick="addmenEdit();">
	        </td>
	        <td>
	        <select id="select2Edit" name="select2Edit" size="4" multiple name="wu.select2" id="select2" >
	        <c:forEach var="permission" items="${select2Role}">
				<option value="<c:out value="${permission.id}"></c:out>"><c:out value="${permission.roleName}"></c:out>
				</option>
        	</c:forEach>
	        </select>
	        </td>
	     </tr>
	     <tr>
			<td>
				<c:if test="${user.userId=='admin'}"><input type="text" name="wu.flag" id="flag" value="${user.flag}"></c:if>
				<c:if test="${user.userId!='admin'}"><input type="hidden" name="wu.flag" id="flag" value="${user.flag}"></c:if>
			</td>
		</tr>	
    </table>
    <label><p class="errorinfo" id="editError"></p></label>
     <ul class="buttonlist">
    		<li class="button_bottom"><a href="#" id="subEdit"><span><img src="<%=request.getContextPath() %>/images/save_icon.png" />保存</span></a></li>
        	<li class="button_bottom"><a href="#" id="resEdit"><span><img src="<%=request.getContextPath() %>/images/cancel_icon.png" />取消</span></a></li>
   	    </ul>
</form>
<script type="text/javascript">
function showEmployeeEdit(){
	var selectLocation = new DivWindow("selectEmployeeEdit","员工选择窗口",530,460,"","<%=request.getContextPath() %>/user.do?method=returnEmployeeEdit");
	selectLocation.addButton("取消");	
	selectLocation.open();
	}
</script>