<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/purview_list.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
  	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/PermissionDwr.js"></script>
  	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<title>权限管理</title>
</head>
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
		<jsp:include page="/index.do?method=left" flush="true" />
    	<div id="mainpage">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span>
				<span><a>系统设置</a></span><span><a>角色管理</a></span><span><a>权限管理</a></span>
			</div>
			<form action="" method="post" id="creatRM">
			<input type="hidden" value="${role.id }" id="ltsRoPer" name="ltsRoPer">
			<input type="hidden" value="${modify}" id="isModify" name="isModify">
			
	<div class="index_pic">权限管理：</div>
	<div id="fhi_doc_pic">
	<script language="javascript" type="text/javascript">
				var o = document.body;
				var totalDiv = document.getElementById("fhi_doc_pic");
				var div = document.createElement("div");
				var uls = document.createElement("ul");
				var ul = document.createElement("ul");
				var lis = document.createElement("li");
				var divs = document.createElement("div");
				var roleId = document.getElementById("ltsRoPer");
				var isModify = document.getElementById("isModify");
				var countPer = 0;
				PermissionDwr.getModelByPid(isModify.value,roleId.value,function(dataPid){
		            PermissionDwr.getModel(isModify.value,function(data){
							for(var i=0;i<data.length;i++)
							{
								for(var j=0;j<1;j++){
									var div = document.createElement("div");
									var divs = document.createElement("div");
									var lis = document.createElement("li");
									var	options = document.createElement("INPUT");
									var h2= document.createElement("h2");
									if(i%2==0){
										div.id = 'left_box'; 
								    	div.className = 'list_table';
								    	div.name='document';
								    }else{
								    	div.className = 'list_table';
								    	div.name='photo';
									}
									divs.className='content_ul';
									options.setAttribute("type","text");
						        	options.setAttribute("name","sn");   
						        	options.setAttribute("id","sn");
						        	options.setAttribute("value",data[i][1]);
						        	var h2Text = document.createTextNode(data[i][1]);
						        	h2.appendChild(h2Text);
						        	div.appendChild(h2);
						        	for(var k=0;k<dataPid.length;k++){
										if(data[i][0]==dataPid[k][2]){
											var option = document.createElement("INPUT");
											var optionName = document.createElement("INPUT");
											var optionValue = document.createElement("INPUT");
											var lab = document.createElement("Lable");
											var lis = document.createElement("li");
											var lisvalue = document.createElement("li");
											var uls = document.createElement("ul");
											var optionId = document.createElement("INPUT");
											var optionPid = document.createElement("INPUT");
											var optionType = document.createElement("INPUT");
											var optionPermission = document.createElement("INPUT");
											
											optionId.setAttribute("type","hidden");
											optionId.setAttribute("name","stocks["+countPer+"].roleCode");
											optionId.setAttribute("id","stocks["+countPer+"].roleCode");
											optionId.setAttribute("value",roleId.value);
	
											optionPid.setAttribute("type","hidden");
											optionPid.setAttribute("name","stocks["+countPer+"].modulePid");
											optionPid.setAttribute("id","stocks["+countPer+"].modulePid");
											optionPid.setAttribute("value",dataPid[k][2]);

											optionType.setAttribute("type","hidden");
											optionType.setAttribute("name","stocks["+countPer+"].moduleType");
											optionType.setAttribute("id","stocks["+countPer+"].moduleType");
											optionType.setAttribute("value",dataPid[k][4]);
											
											lis.className='title_weight';
											uls.className='list_tatle';
											//
											option.setAttribute("type","hidden");
											option.setAttribute("name","stocks["+countPer+"].moduleCode");
											option.setAttribute("id","stocks["+countPer+"].moduleCode");
											option.setAttribute("value",dataPid[k][0]);
											//
											optionName.setAttribute("type","hidden");
											optionName.setAttribute("name","stocks["+countPer+"].moduleName");
											optionName.setAttribute("id","stocks["+countPer+"].moduleName");
											optionName.setAttribute("value",dataPid[k][1]);
											//
											optionPermission.setAttribute("type","hidden");
											optionPermission.setAttribute("name","stocks["+countPer+"].permissionCode");
											optionPermission.setAttribute("id","stocks["+countPer+"].permissionCode");
											optionPermission.setAttribute("value",dataPid[k][3]);
											//
											if(dataPid[k][4]=="text"){
											optionValue.setAttribute("type","text");
											optionValue.setAttribute("name","stocks["+countPer+"].moduleLevel");
											optionValue.setAttribute("id","stocks["+countPer+"].moduleLevel");
											optionValue.setAttribute("className","input_location");
											optionValue.setAttribute("value",dataPid[k][5]);
											lab.innerHTML=dataPid[k][1];
											lis.appendChild(lab);
											lis.appendChild(optionId);
											lis.appendChild(optionPid);
											lis.appendChild(optionType);
											lis.appendChild(option);
											lis.appendChild(optionName);
											lis.appendChild(optionPermission);
											uls.appendChild(lis);
											lisvalue.appendChild(optionValue);
											uls.appendChild(lisvalue);
											}else if(dataPid[k][4]=="boolean"){
												if(dataPid[k][5] == "true"){
													var _radio = document.createElement("<input type='radio' value='true' name='stocks["+countPer+"].moduleLevel' id='stocks["+countPer+"].moduleLevel' checked>");
													var _radio_lab = document.createElement("Lable");
													_radio_lab.innerHTML = "可以";
													lisvalue.appendChild(_radio_lab);
													lisvalue.appendChild(_radio);
													_radio = document.createElement("<input type='radio' value='false' name='stocks["+countPer+"].moduleLevel' id='stocks["+countPer+"].moduleLevel'>");
													var _radio_lab = document.createElement("Lable");
													_radio_lab.innerHTML = "不可以";
													lisvalue.appendChild(_radio_lab);
													lisvalue.appendChild(_radio);
													lab.innerHTML=dataPid[k][1];
													lis.appendChild(lab);
													lis.appendChild(optionId);
													lis.appendChild(optionPid);
													lis.appendChild(optionType);
													lis.appendChild(option);
													lis.appendChild(optionName);
													lis.appendChild(optionPermission);
													uls.appendChild(lis);
													uls.appendChild(lisvalue);
												}else{
													var _radio = document.createElement("<input type='radio' value='true' name='stocks["+countPer+"].moduleLevel' id='stocks["+countPer+"].moduleLevel'>");
													var _radio_lab = document.createElement("Lable");
													_radio_lab.innerHTML = "可以";
													lisvalue.appendChild(_radio_lab);
													lisvalue.appendChild(_radio);
													_radio = document.createElement("<input type='radio' value='false' name='stocks["+countPer+"].moduleLevel' id='stocks["+countPer+"].moduleLevel' checked>");
													var _radio_lab = document.createElement("Lable");
													_radio_lab.innerHTML = "不可以";
													lisvalue.appendChild(_radio_lab);
													lisvalue.appendChild(_radio);
													lab.innerHTML=dataPid[k][1];
													lis.appendChild(lab);
													lis.appendChild(optionId);
													lis.appendChild(optionPid);
													lis.appendChild(optionType);
													lis.appendChild(option);
													lis.appendChild(optionName);
													lis.appendChild(optionPermission);
													uls.appendChild(lis);
													uls.appendChild(lisvalue);
												}
											}
											//
												
											divs.appendChild(uls);
											div.appendChild(divs);
											++countPer;
										}
							        }
						        	totalDiv.appendChild(div);
								}
							}
						});
				});
				
</script>
	</div>
  	<div class="left_box">
		<ul id="sureorcancel">
		<button id="sub" class="button_bg">保存</button>
		<button id="cancel" class="button_bg">退出</button>
		</ul>
    </div>
	</form>
</div>
</div>
<jsp:include page="/index.do?method=foot" flush="true"/> 
</div>
<script type="text/javascript">
document.getElementById('sub').onclick=function(){
	document.forms['creatRM'].action="<%=request.getContextPath() %>/permission.do?method=addRM";
	document.forms['creatRM'].submit();
}
document.getElementById('cancel').onclick=function(){
	document.forms['creatRM'].action="<%=request.getContextPath() %>/permission.do?method=getRole";
	document.forms['creatRM'].submit();
}
</script>
</body>
</html>