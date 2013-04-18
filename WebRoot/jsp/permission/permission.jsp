<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/purview_list.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
  	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/PermissionDwr.js"></script>
  	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成页面</title>
</head>
<body>
<form name="form1" method="post" action="">
<script language="javascript" type="text/javascript">
				var o = document.body;
				var div = document.createElement("div");
				var uls = document.createElement("ul");
				var lis = document.createElement("li");
				var divs = document.createElement("div");
				div.id = 'left_box'; 
			    div.className = 'list_table'; 
			    div.setAttribute('name ','document');
			    divs.className='content_ul';
			    uls.className='list_tatle';
				var dataPidModel;
				PermissionDwr.getModelByPid(function(dataPid){
		            PermissionDwr.getModel(function(data){
						for(var i=0;i<data.length;i++)
						{
							for(var j=0;j<1;j++){
								var lis = document.createElement("li");
								var	options = document.createElement("INPUT");
								lis.className='title_weight';
								options.setAttribute("type","text");
					        	options.setAttribute("name","sn");   
					        	options.setAttribute("id","sn");
					        	options.setAttribute("value",data[i][1]);
					        	lis.appendChild(options);
					        	for(var k=0;k<dataPid.length;k++){
									if(data[i][0]==dataPid[k][2]){
										var option = document.createElement("INPUT");
										option.setAttribute("type","text");
										option.setAttribute("name","sn1");
										option.setAttribute("id","sn1");
										option.setAttribute("value",dataPid[k][1]);
										lis.appendChild(option);
									}
						        }
							}
							uls.appendChild(lis);
							divs.appendChild(uls);
						}
						div.appendChild(divs);
						o.appendChild(div);
					});
				});
				
</script>
  <input type="submit" name="button" id="button" value="提交">
</form>
<ul>
<!--<c:forEach var="name" items="${list}">
	<li><c:out value="${name.moduleCode}" /></li>
</c:forEach>
--></ul>
<script type="text/javascript"><!--
$("#button").click(function(){
	document.forms['form1'].action="<%=request.getContextPath() %>/permission.do?method=getMod";
	document.forms['form1'].submit();
	}
);
--></script>
</body>
</html>