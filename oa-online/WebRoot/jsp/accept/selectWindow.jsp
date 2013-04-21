<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>徕卡进口清关管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/TableListControls.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/jquery/TableListControls.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" ></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/AcceptDWR.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
</head>
<body>
</body>
<script type="text/javascript">
$(function(){
	var tableSettingCourier =  {
			settingMap:{
						dataSources:AcceptDWR.getAcceptMainList,
						displayBox:$("body")
						},
			searchData:{state:"noover"},
			searchSettings:[{title:"内部号",type:"text",name:"innerCode"}],
			theadSttings:[
			          		{title:"序号",width:25,type:"sequence"},
			          		{title:"内部号",width:130,name:"innerCode"},
			         		{title:"客户名称",width:120,name:"customer"},
			         		{title:"业务员",width:40,name:"manager"},
			         		{title:"客服名称",width:50,name:"username"},
			         		{title:"操作完毕日期",width:80,type:"date",format:"yyyy-MM-dd",name:"endDate"},
			         		{title:"操作",width:50,type:"custom",custom:function(obj){
								return "<a target='_parent' href='costMaster.do?method=newCostMasterMain&id="+obj.id+"'>创建成本单</a>";
			         		}}
	     				],
			pageInfo:{pageNo:1,rowsPerpage:10}
	}
	var t = new TableList(tableSettingCourier);
})
</script>
</html>