<%@ page   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	    <!-- 时间控件js -->
		<script type='text/javascript' src='<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js'></script>
		
		<!-- left页面菜单控制js -->
		<script type='text/javascript' src='<%=request.getContextPath()%>/js/leftnav.js'></script>
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/TableListControls.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
		 
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
		<script type='text/javascript' src="<%=request.getContextPath()%>/js/jquery/TableListControls.js"></script>
		
		<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
		<script type='text/javascript' src="<%=request.getContextPath()%>/js/jquery/MyValidator.js"></script>
		<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/util.js'></script>
		<script type="text/javascript">
			function getCustomer(){ 
			  var selagent = new DivWindow("selagent","供应商选择窗口",490,470,"","<%=request.getContextPath()%>/jsp/officeSupply/selectCustomer.jsp") ; 
			  selagent.addButton("取消");
			  selagent.open();
			}
			//消息窗口
			function msgframe(msg){ 
			  var msgwin = new DivWindow("msgwin","消息窗口",200,100,msg); 	
			  msgwin.addButton("确定") ;
			  msgwin.open(); 
			}  
		</script> 
	</head>
	<body>
	</body>
</html>