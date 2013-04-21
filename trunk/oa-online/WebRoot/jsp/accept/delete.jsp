<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<form action="<%=request.getContextPath()%>/accept.do" name="pageForm" method="post">  
<input type="hidden" name="index"  value="${index}"/>
<input type="hidden" name="method"  value="deleteMoney"/> 
<span style="display:inline-block;font-size:13px;text-align:center;padding-left:60px;padding-top:5px;">确定要删除吗</span>  
</form>