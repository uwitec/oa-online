<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/stream.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>

<script type='text/javascript' src="<%=request.getContextPath() %>/jsp/questionnaire/questionnaire.jsp"></script>

<script type="text/javascript">
function download(){
	var  questionnaireId = '';

    var  ids = document.getElementsByName("ids");
    var count = 0 ;
  
    for(var k = 0 ;k<ids.length;k++){
        if(ids[k].checked){
              count++;
              questionnaireId = ids[k].value;
         };
    }
    if(count>1){
      alert("不能多选，只能选择一条数据！");
      return;
    }
    if(count<=0){
    	 alert("请选择要导出的问卷!");
         return;

    }
	
	if(count==1){
		document.forms['pageForm'].action="<%=request.getContextPath()%>/questionnaire.do?method=questionaireDownload&questionnaireId="+questionnaireId;
		document.forms['pageForm'].submit();
	}
	
}
</script>
<title>问卷列表页</title>
</head> 
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
    	<div id="mainpage">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span>
        	<span><a>个人中心</a></span><span><a href="#">问卷管理</a></span></div>
        	<form action="<%=request.getContextPath()%>/questionnaire.do?method=questionnaireManagement&editId=${user.id}"" name="pageForm" method="post"> 
      		<input type="hidden" name="self.unit.Token"  value="<%=session.getAttribute("self.unit.Token")%>"/>
      		<input type="hidden" name="method" id="method" value="list"/>
     		<input type="hidden" name="id" id="id" value=""/>
            <div id="search" style="display:none"> 
   				<ul>
				<!--     <li>问卷名称：</li>
				    <li><input class="company_input" type="text"  name="unit.enName"  /></li> 
				    
				    <li class="content_bg">
				       <button onclick="document.pageForm.submit();" class="button_bg">搜索</button>
				    </li> -->
			    </ul>  
            </div>
            <div class="index_pic">问卷列表：</div> 
            <div id="inpageinfo">${unitResult}</div>
            <script type="text/javascript">
              var unitResult = "${unitResult}" ;
              if(unitResult!=null && unitResult!="")
                 document.getElementById("inpageinfo").style.display= "block" ;
            </script>
            <div id="operat_title"> 
	        	<% session.removeAttribute("unitResult") ; %> 
	         	<c:if test="${1==1}">
	             <ul>
            <li>  
             <button onclick="download()" class="button_bg_sp">导出报表</button>
	            <button class="button_add" id="addShow" onclick="create();">新建</button>
            </li>
            
          </ul>
             
	        	
		   		</c:if>
		   		
		   	</div>
            <div id="com_list"> 
                <ul class="list_title">
                    <li class="client_selall"></li>
                	<li class="comparent" >问卷名称</li>
                	<li class="comparent"  >开始时间</li> 
                    <li class="comparent"  id="creator">结束时间</li> 
                    <li class="comparent"  id="createTime">问卷状态</li>
                    <li class="comparent">操作</li>
                </ul>
                <c:forEach var="questionnaire" items="${questionnaireList}">
				<ul class="com_list_content">  
                    <li class="client_selall"> 
                    <input  name="ids" type="checkbox" value="${questionnaire.id}"/> 
                    </li>
                	<li class="comparent">${questionnaire.questionnaireName}</li>
                    <li class="comparent"><fmt:formatDate value="${questionnaire.questionnaireStartTime}" pattern="yyyy-MM-dd"/></li>
                    <li class="comparent"><fmt:formatDate value="${questionnaire.questionnaireEndTime}" pattern="yyyy-MM-dd"/></li> 
                 	<li class="comparent">${questionnaire.questionnaireStatus}</li>
                    <li class="comparent">  
	        		 <c:if test="${1==1}">
                      <a title="编辑" href="javascript:edit('${questionnaire.id}');"><img src="<%=request.getContextPath()%>/images/edit_mini.png"/></a> 
                     </c:if>
                      <c:if test="${1==1}">
                      <a title="编辑问卷" href="javascript:editQuestion('${questionnaire.id}');"><img src="<%=request.getContextPath()%>/images/edit_mini.png"/></a> 
                     </c:if>
	        		<c:if test="${1==1}">
                      <a class="d_box" title="删除" href="javascript:delOne('${questionnaire.id}','${questionnaire.questionnaireEndTime}');"><img src="<%=request.getContextPath()%>/images/delete.png"/></a> 
                   </c:if>
                    </li>
                 </ul>
                 </c:forEach> 
            </div> 
           <div id="com_all_delete">
	              <button id="chall" onclick="selAll('ids');" class="button_bg">全选</button> 
	              <c:if test="${user.permissionMap.FhiOa_deleteunit}">
	              <button id="delAll" onclick="delSome();" class="button_bg">删除</button></c:if> 
	         </div> 
             <div id="page">
					<%@ include file="/inc/pagecontrol.inc" %>
             </div>   
     </form>   
</div>
</div>
</div>
</body>
<jsp:include page="/index.do?method=foot" flush="true"/>  
 
</html> 