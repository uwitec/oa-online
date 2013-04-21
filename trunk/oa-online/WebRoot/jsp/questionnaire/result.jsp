<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/new_general.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/stream.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/MyDivWindow.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript'
	src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type='text/javascript'
	src="<%=request.getContextPath()%>/jsp/questionnaire/questionnaire.jsp"></script>
<title>问卷列表页</title>
</head>
<body>
	<div id="layout">
		<jsp:include page="/index.do?method=head" flush="true" />
		<div id="mid">
			<jsp:include page="/index.do?method=left" flush="true" />
			<div id="mainpage">
				<div id="location">
					当前位置：<span class="fp"><a
						href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a>
					</span> <span><a>个人中心</a> </span><span><a href="#">调查问卷</a> </span>
				</div>
				<form action="<%=request.getContextPath()%>/unit.do" name="pageForm"
					method="post">
					<input type="hidden" name="self.unit.Token"
						value="<%=session.getAttribute("self.unit.Token")%>" /> <input
						type="hidden" name="method" id="method" value="list" /> <input
						type="hidden" name="id" id="id" value="" />
					<div id="search" style="display: none">
						<ul>
							<li>问卷名称：</li>
							<li><input class="company_input" type="text"
								name="unit.enName" />
							</li>

							<li class="content_bg">
								<button onclick="document.pageForm.submit();" class="button_bg">搜索</button>
							</li>
						</ul>
					</div>
					<div id="inpageinfo" align="center">${rstMsg}</div>
					<script type="text/javascript">
						var rstMsg = "${rstMsg}";
						if (rstMsg != null && rstMsg != "")
							document.getElementById("inpageinfo").style.display = "block";
					</script>
				</form>
			</div>
		</div>
	</div>
</body>
<jsp:include page="/index.do?method=foot" flush="true" />
</html>
