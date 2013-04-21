<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/employee.css" />
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<title>员工展示页</title>
</head> 
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
    	<div id="mainpage"> 
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>基础信息</a></span><span><a href="<%=request.getContextPath()%>/employee.do?method=list">员工管理</a></span><span><a>员工展示</a></span></div>
        	<div id="empreview">
        	  <ul id="pre_title"><li>员工履历表</li></ul>
        	  <ul id="pre_code"><li>编号：${basic.employeeCode}&nbsp;&nbsp;&nbsp;&nbsp;填表日期：<fmt:formatDate value="${basic.addDate}"  pattern="yyyy-MM-dd"/></li></ul>
        	</div>
        	<div id="emmaininfo">
        	   <div id="pre_head_pic"><img src="<%=request.getContextPath() %>/upload/${basic.headPic}"/></div> 
        	   <div class="pre_head_pic_right">
                 <ul>
	                 <li><span>姓名：</span>${basic.name}</li>
	                 <li><span>性别：</span>${basic.sex}</li>
	                 <li><span>出生日期：</span><fmt:formatDate value="${basic.birthday}"  pattern="yyyy-MM-dd"/></li>
                 </ul>
                 <ul>
	                 <li><span>身份证：</span>${basic.idcard}</li>
	                 <li><span>参加工作时间：</span><fmt:formatDate value="${basic.startWrokDate}"  pattern="yyyy-MM-dd"/></li>
	                 <li><span>婚姻状况：</span>${basic.marry}</li>
                 </ul>
                 <ul>
	                 <li><span>政治面貌：</span>${basic.politics}</li>
	                 <li><span>入司时间：</span><fmt:formatDate value="${basic.inWorkDate}"  pattern="yyyy-MM-dd"/></li>
	                 <li><span>转正时间：</span><fmt:formatDate value="${basic.realWorkDate}"  pattern="yyyy-MM-dd"/></li>
                 </ul>
                 <ul>
                 <li><span>毕业院校：</span>${basic.university}</li>
                 <li><span>学历：</span>${basic.degree}</li>
                 <li><span>民族：</span>${basic.nomen}</li></ul>
                 
                 <ul>
	                 <li><span>合同期限：</span>${basic.contractLength}</li>
	                 <li><span>到期时间：</span><fmt:formatDate value="${basic.endDate}"  pattern="yyyy-MM-dd"/></li> 
                 </ul>
                 <ul>
	                 <li><span>紧急联系人：</span>${basic.emergencyPerson}</li>
	                 <li><span>与本人关系：</span>${basic.relation}</li>
	                 <li><span>电话：</span>${basic.emergencyPhone}</li>
                 </ul> 
                 <ul id="longaddress1">
	                 <li><span>现居住地：</span>${basic.livePlace}</li>
	                 <li><span>户口所在地：</span>${basic.identityPlace}</li>
                 </ul>
               </div>
               <div class="pretitle">教育背景（从中学填起）</div>
                 <ul  class="listtitle"><li>起始时间</li><li>结束时间</li><li>院校名称</li><li>证明人</li><li>联系电话</li><li>专业</li></ul>
                  <hr class="line_location"/>
                 <c:forEach var="eduone" items="${edu}">
			        <ul  class="listtitle">
				         <li><fmt:formatDate value="${eduone.startDate}"  pattern="yyyy-MM-dd"/></li> 
				         <li><fmt:formatDate value="${eduone.endDate}"  pattern="yyyy-MM-dd"/></li> 
				         <li>${eduone.university}</li> 
				         <li>${eduone.phone}</li> 
				         <li>${eduone.provePerson}</li> 
				         <li>${eduone.profession}</li> 
			        </ul>
			     </c:forEach>
                 <div class="pretitle">工作经历</div>
                 <ul  class="listtitle"><li>起始时间</li><li>结束时间</li><li>公司名称</li><li>职位</li><li>证明人</li><li>联系电话</li></ul>
                 <hr class="line_location"/>
                 <c:forEach var="eduone" items="${work}">
			        <ul  class="listtitle">
				         <li><fmt:formatDate value="${eduone.startDate}"  pattern="yyyy-MM-dd"/></li> 
				         <li><fmt:formatDate value="${eduone.endDate}"  pattern="yyyy-MM-dd"/></li> 
				         <li>${eduone.company}</li> 
				         <li>${eduone.phone}</li> 
				         <li>${eduone.provePerson}</li> 
				         <li>${eduone.job}</li> 
			       </ul>
			     </c:forEach>  
                 <div class="pretitle">家庭关系</div>
                 <ul  class="listtitle"><li>家庭成员姓名</li><li>年龄</li><li>工作单位</li><li>与本人关系</li><li>联系电话</li></ul>
                  <hr class="line_location"/>
                  <c:forEach var="eduone" items="${family}">
			        <ul  class="listtitle">
				         <li>${eduone.name}</li> 
				         <li>${eduone.relationship}</li> 
				         <li>${eduone.age}</li> 
				         <li>${eduone.workCompany}</li> 
				         <li>${eduone.phone}</li>  
			       </ul> 
			     </c:forEach> 
                 <div id="manypics">
	                 <ul  class="listtitle">
	                 <c:if test="${basic.idcardPic!=null }"><li class="credentials">身份证：</li></c:if>
	                 <c:if test="${basic.graduatePic!=null}"><li class="credentials">毕业证：</li></c:if>
	                 <c:if test="${basic.degreePic!=null}"><li class="credentials">学位证：</li></c:if>
	                 <c:if test="${basic.accountPic!=null || basic.drivePic!=null ||basic.gatewayPic!=null || basic.checkPic!=null}"><li class="credentials2">专业证书：</li></c:if>
	                 </ul>
	                 <ul  class="pic">
		                 <c:if test="${basic.idcardPic!=null}"><li class="credentials"><img src="<%=request.getContextPath() %>/upload/${basic.idcardPic}"/></li></c:if>
		                 <c:if test="${basic.graduatePic!=null}"><li class="credentials"><img src="<%=request.getContextPath() %>/upload/${basic.graduatePic}"/></li></c:if>
		                 <c:if test="${basic.degreePic!=null}"><li class="credentials"><img src="<%=request.getContextPath() %>/upload/${basic.degreePic}"/></li></c:if>
		                 <c:if test="${basic.accountPic!=null}"><li class="credentials2"><img src="<%=request.getContextPath() %>/upload/${basic.accountPic}"/></li></c:if>
		                 <c:if test="${basic.drivePic!=null}"><li class="credentials2"><img src="<%=request.getContextPath() %>/upload/${basic.drivePic}"/></li></c:if>
		                 <c:if test="${basic.gatewayPic!=null}"><li class="credentials2"><img src="<%=request.getContextPath() %>/upload/${basic.gatewayPic}"/></li></c:if>
		                 <c:if test="${basic.checkPic!=null}"><li class="credentials2"><img src="<%=request.getContextPath() %>/upload/${basic.checkPic}"/></li></c:if>  
	                 </ul>
                 </div>
            </div>
			<div id="emmaininfo_area">
				<button id="delAll" onclick="history.go(-1);" class="button_bg">退回</button>
		    </div>
    	</div>
    </div>    
<jsp:include page="/index.do?method=foot" flush="true"/>
</div>
</body>
</html>

