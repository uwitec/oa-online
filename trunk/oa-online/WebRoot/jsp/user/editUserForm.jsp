<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form action="<%=request.getContextPath() %>/user.do?method=update" method="post" id="editForm" name="editForm" class="hiddenuserform">
<input type="hidden" name="wu.id" id="id" value="${editUser.id}">
<input type="hidden" name="wu.userId" id="userNameEdit"  value="<c:out value="${editUser.userId}" />">
<input type="hidden" name="permissionEdit" id="permissionEdit">

<input type="hidden" name="permission" id="Permission">
<div id="new_user_box">
        <h2 class="account_title02_new">编辑账户：</h2>
        <ul>
          <li><span>用户ID：</span>
            <c:out value="${editUser.userId}" />
          </li>
          <li><span>密码：</span>
            <input name="wu.passWord" maxlength="20" type="password" id="passWordEdit" value="<c:out value="${editUser.passWord}"/>" class="right" >
            <input type="hidden" name="changePassword" id="changePassword" value="0" />
          </li>
          <li><span>确认密码：</span>
            <input name="CoPassWrord" maxlength="20" type="password" id="CoPassWrord" class="right" />
          </li>
          <li><span>员工姓名：</span>
           <input type="text" name="wu.employee" id="employeeEdit" onKeyUp="value=value.replace(/[^\da-zA-Z-~\u4E00-\u9FA5]/g,'')" value="<c:out value="${editUser.employee}" />"><a href="javascript:showEmployeeEdit();"><img src="images/looking.png" /></a></td>
    		<input type="hidden" name="wu.employeeEditHidden" id="employeeEditHidden" value="${editUser.employeeId}" /></a></li>
            <input type="hidden" name="wu.employeeHidden" id="employeeHidden" value="" />
        </ul>
      </div>
      <table>
        <tr>
          <th colspan="4" class="th_left">权限:</th>
        </tr>
        <tr>
          	<td>
          		<select id="select1Edit" name="select1Edit" size="4" multiple>
					<c:forEach var="permissionRole" items="${select1Role}">
						<option value="<c:out value="${permissionRole.id}"></c:out>"><c:out value="${permissionRole.roleName}"></c:out>
						</option>
        			</c:forEach>
				</select>
      		</td>
          	<td class="td_center">
          		<a name="d" type="button" id="d" value="&lt;&lt;" onClick="returnmenEdit();"><img src="<%=request.getContextPath() %>/images/left-acc.gif"/></a>
          		<a name="d" type="button" id="d" value="&gt;&gt;" onClick="addmenEdit();"><img src="<%=request.getContextPath() %>/images/right-acc.gif" /></a>
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
      </table>
      <label><p class="errorinfo" id="editError"></p></label>
	  <label><p class="errorinfo" id="error"></p></label>
<div class="button_order_form">
        <button id="subEdit" class="button_bg">保存</button>
        <button id="resEdit" class="button_bg">退出</button>
</div>
</form>
<script type="text/javascript">
	function showEmployeeEdit(){
		var selectLocation = new DivWindow("selectEmployeeEdit","员工选择窗口",530,460,"","<%=request.getContextPath() %>/user.do?method=returnEmployeeEdit");
		selectLocation.addButton("取消");	
		selectLocation.open();
	}
</script>