<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/employee.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" ></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/DepartmentDWR.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/EmployeeDWR.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath()%>/jsp/employee/employeejs.jsp'></script>  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script> 
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script> 
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
<title>添加员工基础信息</title>
</head> 
<body>
<div id="layout">
	<jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/> 
    	<div id="mainpage">
        	<div id="location">当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a>基础信息</a></span><span><a href="<%=request.getContextPath()%>/employee.do?method=list">员工管理</a></span><span><a>新建员工</a></span></div>
        	<div id="emmessage">
	        	<div class="index_pic">填写人个信息：</div>
	        	<div id="inpageinfo">${employeeret}</div>
	        	<script type="text/javascript">
	              var employeeret = "${employeeret}" ;
	              if(employeeret!=null && employeeret!="")
	                 document.getElementById("inpageinfo").style.display= "block" ;
                </script>
        	</div> 
        	<%session.removeAttribute("employeeret");%>
             <div>
	            <div id="emtitle">
		            <li   	id="item_1" <c:if test="${tempBasic!=null}">onclick="setClassAtt('item_1','eminfo');"</c:if>>个人信息</li>
		            <li><img src="<%=request.getContextPath()%>/images/seperat.png"/></li>
		            <li	  	id="item_2" <c:if test="${tempBasic!=null}">onclick="setClassAtt('item_2','eminfo_edu');"</c:if>>教育经历</li>
		            <li><img src="<%=request.getContextPath()%>/images/seperat.png"/></li>
		            <li	  	id="item_3" <c:if test="${tempBasic!=null}">onclick="setClassAtt('item_3','eminfo_work');"</c:if>>工作经验</li>
		            <li><img src="<%=request.getContextPath()%>/images/seperat.png"/></li>
		            <li	 class="before"	id="item_4" <c:if test="${tempBasic!=null}">onclick="setClassAtt('item_4','eminfo_family');"</c:if>>家庭成员</li>
	            </div>
	        </div>
	        <form action="<%=request.getContextPath()%>/employee.do?method=addBasic" name="pageForm" method="post" ENCTYPE="multipart/form-data" >
	        <input type="hidden" name="self.employee.Token" id="self.employee.Token" value="<%=session.getAttribute("self.employee.Token")%>"/>
			<input type="hidden" name="basic.id" id="basic.id" value="${tempBasic.id}"/>
			<input type="hidden" name="basic.companyName" id="basic.companyName" value="${tempBasic.companyName}"/>
			<input type="hidden" name="basic.companyId" id="basic.companyId" value="${tempBasic.companyId}"/>
			<input type="hidden" name="basic.depName" id="basic.depName" value="${tempBasic.depName}"/>
			<input type="hidden" name="basic.depId" id="basic.depId" value="${tempBasic.depId}"/>  
			<input type="hidden" name="basic.account" id="basic.account" value="${tempBasic.account}"/>  
			<input type="hidden" name="headpicpath" id="headpicpath" value="${tempBasic.headPic}"/> 
            <div id="eminfo">
               <div id="eminfo_basic_title">基本信息</div> 
               <div id="eminfo_basic_head">
                  <span class="tital_pic"><img src="<%=request.getContextPath()%>/images/point.jpg"/>员工图片</span>
                  <div id="showheadpic" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);"></div>
                  <input type="file" name="headpic" id="headpic"  hidefocus  size="1" 
                      onchange="previwe('headpic','showheadpic','inpageinfo');" style="position:absolute;filter:alpha(opacity=0);"  />
                  <button onmousemove="headpic.style.pixelLeft=event.x-100;headpic.style.pixelTop=this.offsetTop;" 
                       onclick="document.getElementById('headpic').click();" class="button_bg_sp">选择图像</button>
               </div> 
               <div id="eminfo_basic_headright"> 
                  <ul>
	                  <li>
		                  <span><img src="<%=request.getContextPath()%>/images/star.jpg"/>员工编码：</span>
		                  <input type="text" name="basic.employeeCode" id="basic.employeeCode" value="${tempBasic.employeeCode}"
		    	          <c:if test="${editEmployee=='yes'}"> readonly </c:if>/>
	    	          </li>
	                  <li>
		                  <span><img src="<%=request.getContextPath()%>/images/star.jpg"/>员工姓名：</span>
		                  <input type="text" name="basic.name" id="basic.name" value="${tempBasic.name}" />
	                  </li>
                  </ul>
                  <ul>
	                    <li>
		                    <span>英文名称：</span>
		                    <input type="text" name="basic.enName" id="basic.enName" value="${tempBasic.enName}" />
		                </li>
	                    <li>
		                     <span>性别：</span>
		                     <select type="text" name="basic.sex" id="basic.sex" class="slt_height"> 
						       <option value="男" selected>男</option>
						       <option value="女">女</option>
						     </select>
					    </li>
				  </ul>
                  <ul>
	                  <li>
		                  <span>出生日期：</span>
		                  <input type="text" name="birthday" id="birthday" onclick="WdatePicker()"  readonly 
		                  value="<fmt:formatDate value="${tempBasic.birthday}"  pattern="yyyy-MM-dd"/>" />
	                  </li>
                  	  <li>
	                  	  <span>民族：</span>
	                  	  <input type="text" name="basic.nomen" id="basic.nomen" value="${tempBasic.nomen}" />
                  	  </li>
                  </ul>
                  <ul>
	                  <li><span>政治面貌：</span>
	                  		<input type="text" name="basic.politics" id="basic.politics"  value="${tempBasic.politics}"/>
	                  </li>
                      <li>
                          <span>婚姻状况：</span>
                          <select type="text" name="basic.marry" id="basic.marry" class="slt_height">
					          <option value="未婚" selected>未婚</option>
					          <option value="已婚">已婚</option> 
					      </select>
                      </li>
                  </ul>
                  <ul>
	                  <li>
	                     <span>学历：</span>
	                     <input type="text" name="basic.degree" id="basic.degree" value="${tempBasic.degree}" />
	                  </li>
                      <li>
                         <span>毕业院校：</span>
                         <input type="text" name="basic.university" id="basic.university" value="${tempBasic.university}"/>
                      </li>
                  </ul>
                  <ul>
                     <li>
                        <span>身份证号：</span>
                     	<input type="text" name="basic.idcard" id="basic.idcard" value="${tempBasic.idcard}" class="inputstylebasic" onchange="writeBirthday();"/>
                     </li>
                  </ul>
                  <ul><span>居住地地址：</span>
                     <input class="addressinput" type="text" name="basic.livePlace" id="basic.livePlace"  value="${tempBasic.livePlace}" />
                  </ul>
                  <ul><span>户口地地址：</span>
                      <input class="addressinput" type="text" name="basic.identityPlace" id="basic.identityPlace"  value="${tempBasic.identityPlace}" />
                  </ul>
               </div>
               <div id="connect">
                  <ul><li><span>手机号码：</span><input type="text" name="basic.mobile" id="basic.mobile" value="${tempBasic.mobile}" /></li>
                      <li><span>家庭电话—座机：</span><input type="text" name="basic.phone" id="basic.phone" value="${tempBasic.phone}" /></li>
                      <li><span>MSN：</span><input type="text"  name="basic.msn" id="basic.msn" value="${tempBasic.msn}" class="inputstyle"/></li>
                  </ul>
                  <ul>
                      <li><span>QQ：</span><input type="text"  name="basic.qq" id="basic.qq" value="${tempBasic.qq}" /></li>
                      <li><span>E-mail：</span><input type="text" name="basic.email" id="basic.email" value="${tempBasic.email}" /></li> 
                      <li><span>分机号：</span><input type="text" name="basic.identityPhone" id="basic.identityPhone"  value="${tempBasic.identityPhone}"/></li>
                  </ul>
                  <ul>
                  	  <li><span>紧急联系人：</span><input type="text" name="basic.emergencyPerson" id="basic.emergencyPerson"  value="${tempBasic.emergencyPerson}"/></li>
                      <li><span>与本人关系：</span><input type="text" name="basic.relation" id="basic.relation"  value="${tempBasic.relation}"/></li>
                      <li><span>联系人电话：</span><input type="text" name="basic.emergencyPhone" id="basic.emergencyPhone"  value="${tempBasic.emergencyPhone}"/></li>
                  </ul> 
               </div>
               <div id="oneandcom">
                  <ul>
                  <li><span>所属公司：</span><select type="text" name="parentcom" id="parentcom" onchange="selctEmCom();" class="slt_height">
				          <option>---请选择公司---</option>
				          <c:forEach var="com" items="${comlist}"> 
				              <option value="${com.id}---${com.fullName}">${com.fullName}</option>
				          </c:forEach>
				     </select>
                  </li>
                 <li><span>所属部门：</span><select " type="text" name="parentdep" id="parentdep" onchange="selEmDep();" class="slt_height">
				          <option>---请选择部门---</option>
				        </select>
                 </li>
                      <li><span class="font_area">职位：</span><input type="text" name="basic.job" id="basic.job" value="${tempBasic.job}"/></li>
                  </ul>
                  <ul>
                    <li><span>状态：</span><select type="text" name="basic.employeeState" id="basic.employeeState" class="slt_height">
				          <option value="">---请选择状态---</option>
				          <option value="normal">正式员工</option>
				          <option value="try">试用员工</option>
				          <option value="multipart">兼职员工</option>
				          <option value="off">离职员工</option>
				          <option value="other">其它员工</option>
				        </select></li>
                      <li><span>职位等级：</span><input type="text" name="basic.jobLevel" id="basic.jobLevel" value="${tempBasic.jobLevel}"/></li> 
                      <li><span class="font_area">入职时间：</span><input type="text" name="inWorkDate" id="inWorkDate" onclick="WdatePicker()" 
                         readonly value="<fmt:formatDate value="${tempBasic.inWorkDate}"  pattern="yyyy-MM-dd"/>"/></li> 
                  </ul>
                  <ul><li><span>离职时间：</span><input type="text" name="offWorkDate" id="offWorkDate" onclick="WdatePicker()"  
                       readonly  value="<fmt:formatDate value="${tempBasic.offWorkDate}"  pattern="yyyy-MM-dd"/>"/></li>
                      <li><span>转正时间：</span><input type="text" name="realWorkDate" id="realWorkDate" onclick="WdatePicker()" 
                           readonly value="<fmt:formatDate value="${tempBasic.realWorkDate}"  pattern="yyyy-MM-dd"/>"/></li>
                      <li><span class="font_area">参加工作时间：</span><input type="text" name="startWrokDate" id="startWrokDate" onclick="WdatePicker()"  
                      readonly  value="<fmt:formatDate value="${tempBasic.startWrokDate}"  pattern="yyyy-MM-dd"/>"/></li>
                  </ul> 
               </div>
               <div id="eminfo_extend_title">
                 <ul>
                    <li>扩展信息</li>
                   <!--  <li><div><img src="<%=request.getContextPath()%>/images/-.jpg"/></div></li>  -->
                 </ul> 
               </div> 
               <div id="extend_driver">
	                  <ul><li><span>合同期限：</span><input type="text" name="basic.contractLength" id="basic.contractLength" value="${tempBasic.contractLength}" onchange="writeEndDate();"/></li>
	                      <li><span class="font_area02">到期时间：</span><input type="text" name="endDate" id="endDate" onclick="WdatePicker()" 
	                          readonly value="<fmt:formatDate value="${tempBasic.endDate}"  pattern="yyyy-MM-dd"/>"/></li>
	                      <li><span class="font_area">社保卡：</span><input type="text" name="basic.insureCard" id="basic.insureCard" value="${tempBasic.insureCard}"/></li>
	                  </ul>
	                  <ul>
	                      <li><span>是否是司机：</span><select type="text" name="basic.driver" id="basic.driver" class="slt_height"><option value="N" selected >不是司机</option><option value="Y">是司机</option></select></li>
	                      <li><span class="font_area02">车队：</span><input type="text" name="basic.busGroup" id="basic.busGroup"  value="${tempBasic.busGroup}"/></li> 
	                      <li><span class="font_area">驾驶证：</span><input type="text" name="basic.driverCard" id="basic.driverCard"  value="${tempBasic.driverCard}"/></li> 
	                  </ul>
	                  <ul><li><span>驾驶证级别：</span><input type="text" name="basic.driveLevel" id="basic.driveLevel"  value="${tempBasic.driveLevel}"/></li>
	                      <li><span class="font_area02">有效期：</span><input type="text" name="basic.dcvalidTime" id="basic.dcvalidTime"  value="${tempBasic.dcvalidTime}"/></li>
	                      <li><span class="font_area">下次验本时间：</span><input type="text" name="nextCheck" id="nextCheck" onclick="WdatePicker()"  
	                               readonly  value="<fmt:formatDate value="${tempBasic.nextCheck}"  pattern="yyyy-MM-dd"/>"/></li>
		              </ul> 
		               <ul><li><span>外语语种：</span><input type="text" name="basic.language" id="basic.language"  value="${tempBasic.language}"/></li>
	                      <li><span class="font_area02">等级：</span><input type="text" name="basic.langLevel" id="basic.langLevel"  value="${tempBasic.langLevel}"/></li> 
		              </ul> 
	                </div> 
	                <div id="extend_upload">
	                  <ul><li><span>身份证：</span><input type="file" name="idcardPic" /></li>
	                      <li><span>会计证：</span><input type="file" name="accountPic" /></li>
	                  </ul>
	                  <ul>
	                      <li><span>学位证：</span><input type="file" name="degreePic" /></li>
	                      <li><span>驾驶证：</span><input type="file" name="drivePic" /></li>
	                   </ul>
	                  <ul>
	                      <li><span>毕业证：</span><input type="file" name="graduatePic" /></li> 
	                      <li><span>报关证：</span><input type="file" name="gatewayPic" /></li> 
	                  </ul>
	                  <ul>
	                      <li><span>报检证：</span><input type="file" name="checkPic" /></li> 
		              </ul> 
	                </div> 
             <div>
	           <ul id="sureorcancel">
	              <li>
					<button id="chall" onclick="addCheck(<c:if test="${tempBasic.id!=null && tempBasic.id!=''}">'${tempBasic.id}'</c:if>);" class="button_bg">保存</button>
	              </li>
		          <li>
		              <button id="delAll" onclick="cancel();" class="button_bg">退出</button>
	              </li> 
	           </ul>
			</div>   
    	</div></form>

   	  	
    	
<!-- 教育经历/education infomation  -------------------------------------------->
    	   <div id="eminfo_edu" class="eminfo_work"> 
    	      <form action="<%=request.getContextPath()%>/employee.do" name="pageForm_edu" method="post">  
				<input type="hidden" name="method" id="method" value="addEducation"/> 
				<input type="hidden" name="submethod" id="submethod" value="${submethod}"/> 
				<input type="hidden" name="self.employee.Token"  value="<%=session.getAttribute("self.employee.Token")%>"/> 
				<input type="hidden" name="index" id="index" value="${index}"/>  
				<input type="hidden" name="education.id" id="education.id" value="${education.id}"/>  
				<input type="hidden" name="education.employeeId" id="education.employeeId" value="${education.employeeId}"/>
               <ul class="list_title">
               <span>
	               <li>起始时间</li>
	               <li>结束时间</li>
	               <li>院校名称</li>
	               <li>专业</li>
	               <li>证明人</li>
	               <li>证明人电话</li>
	               <li class="take_out_line">操作</li>
	           </span>
               </ul>
	           <c:forEach var="edu" items="${tempEducationList}" varStatus="order">
				    <ul id="work_info">
				    	<li><fmt:formatDate value="${edu.startDate}"  pattern="yyyy-MM-dd"/> </li>
				        <li><fmt:formatDate value="${edu.endDate}"  pattern="yyyy-MM-dd"/> </li>
				        <li><a name="edu_school">${edu.university}</a></li>  
				        <li>${edu.profession}</li> 
				        <li>${edu.provePerson}</li>  
				        <li>${edu.phone}</li>  
				        <li><a href="javascript:delTempEducation('${order.index}','${edu.id}');">删除</a> 
				        <a href="javascript:editTempEducation('${order.index}');">编辑</a></li>   
				    </ul>  
			  </c:forEach> 
	           <div id="middle_line">教育经历<hr/></div> 
				<div id="work_input"> 
			         <ul>
						 <li><span>起始时间：</span><input type="text" name="startTime" id="startTime" onclick="WdatePicker()" readonly value="<fmt:formatDate value="${education.startDate}"  pattern="yyyy-MM-dd"/>"/></li>
					     <li><span>结束时间：</span><input type="text" name="endTime" id="endTime" onclick="WdatePicker()" readonly value="<fmt:formatDate value="${education.endDate}"  pattern="yyyy-MM-dd"/>"/></li>
					     <li><span>院校名称：</span><input type="text" name="education.university" id="education.university" value="${education.university}"/></li>
				     </ul> 
			         <ul>
				        <li><span>专业：</span><input type="text" name="education.profession" id="education.profession" value="${education.profession}"/></li> 
				        <li><span>证明人：</span><input type="text" name="education.provePerson" id="education.provePerson" value="${education.provePerson}"/></li>  
				        <li><span>联系电话：</span><input type="text" name="education.phone" id="education.phone" value="${education.phone}"/></li> 
			         </ul>  
                </div>
	            <ul id="com_all_delete02">
	              <li>
					<button id="chall" onclick="addEducation();" class="button_bg">保存</button>
	              </li>
		          <li>
		              <button id="delAll" onclick="cancel();" class="button_bg">退出</button>
	              </li>
	           </ul>
	        </form>
    	</div> 
<!-- education infomation  -->
    	

<!-- 工作经历/work infomation   -------------------------------------------->
    	    <div id="eminfo_work" class="eminfo_work">  
    	       <form action="<%=request.getContextPath()%>/employee.do" name="pageForm_work" method="post"> 
				<input type="hidden" name="method" id="method" value="addWork"/> 
				<input type="hidden" name="submethod" id="submethod" value="${submethod}"/> 
				<input type="hidden" name="self.employee.Token"  value="<%=session.getAttribute("self.employee.Token")%>"/> 
				<input type="hidden" name="index" id="index" value="${index}"/> 
				<input type="hidden" name="work.id" id="work.id" value="${work.id}"/>  
				<input type="hidden" name="work.employeeId" id="work.employeeId" value="${work.employeeId}"/> 
               <ul class="list_title">
               <span>
	               <li>起始时间</li>
	               <li>结束时间</li>
	               <li>公司名称</li>
	               <li>职位</li>
	               <li>证明人</li>
	               <li>证明人电话</li>
	               <li class="take_out_line">操作</li>
	           </span>
               </ul>
               <c:forEach var="work" items="${tempWorkList}" varStatus="order">
				    <ul id="work_info">
				    	<li><fmt:formatDate value="${work.startDate}"  pattern="yyyy-MM-dd"/> </li>
				        <li><fmt:formatDate value="${work.endDate}"  pattern="yyyy-MM-dd"/> </li>
				        <li><a name="work_com">${work.company}</a></li>  
				        <li>${work.job}</li> 
				        <li>${work.provePerson}</li>  
				        <li>${work.phone}</li>  
				        <li><a href="javascript:delTempWork('${order.index}','${work.id}');">删除</a> 
				        <a href="javascript:editTempWork('${order.index}');">编辑</a></li>   
				   </ul>   
			  </c:forEach> 
	           <div id="middle_line">工作经历<hr/></div>
		       <div id="work_input">  
	            <ul>
		            <li><span>起始时间：</span><input type="text" name="startTime" id="startTime" onclick="WdatePicker()" readonly value="<fmt:formatDate value="${work.startDate}"  pattern="yyyy-MM-dd"/>"/></li>
			        <li><span>结束时间：</span><input type="text" name="endTime" id="endTime" onclick="WdatePicker()" readonly value="<fmt:formatDate value="${work.startDate}"  pattern="yyyy-MM-dd"/>"/></li>
			        <li><span>公司名称：</span><input type="text" name="work.company" id="work.company" value="${work.company}"/></li>
		        </ul> 
		        <ul>
			        <li><span>职位：</span><input type="text" name="work.job" id="work.job" value="${work.job}"/></li> 
			        <li><span>证明人：</span><input type="text" name="work.provePerson" id="work.provePerson" value="${work.provePerson}"/></li>  
			        <li><span>联系电话：</span><input type="text" name="work.phone" id="work.phone" value="${work.phone}"/></li>
		        </ul>  
	           </div>
					<ul id="com_all_delete02">
		              <li>
						<button id="chall" onclick="addWork();" class="button_bg">保存</button>
		              </li>
			          <li>
			              <button id="delAll" onclick="cancel();" class="button_bg">退出</button>
		              </li>
		           </ul> 
	         </form> 
    	</div>
    	
<!-- work infomation  -->
    	

<!-- 教育信息/family infomation   -------------------------------------------->
    	   <div id="eminfo_family" class="eminfo_work">  
    	       <form action="<%=request.getContextPath()%>/employee.do" name="pageForm_family" method="post">
				<input type="hidden" name="method" id="method" value="addFamily"/> 
				<input type="hidden" name="submethod" id="submethod" value="${submethod}"/> 
				<input type="hidden" name="self.employee.Token"  value="<%=session.getAttribute("self.employee.Token")%>"/> 
				<input type="hidden" name="index" id="index" value="${index}"/> 
				<input type="hidden" name="family.id" id="family.id" value="${family.id}"/>  
				<input type="hidden" name="family.employeeId" id="family.employeeId" value="${family.employeeId}"/> 
               <ul class="list_title">
               <span>
	               <li>家庭成员姓名</li>
	               <li>出生日期</li>
	               <li>工作单位</li>
	               <li>与本人关系</li>
	               <li>联系电话</li>
	               <li class="take_out_line">操作</li>
	           </span>
               </ul>
	           <c:forEach var="family" items="${tempFamilyList}" varStatus="order">
				    <ul id="work_info">
				    	<li>${family.name}</li>
				        <li><fmt:formatDate value="${family.birthday}"  pattern="yyyy-MM-dd"/></li>  
				        <li><a name="family_com">${family.workCompany}</a></li> 
				        <li>${family.relationship}</li>  
				        <li>${family.phone}</li>   
				       <li><a href="javascript:delTempFamily('${order.index}','${family.id}');">删除</a> 
				       <a href="javascript:editTempFamily('${order.index}');">编辑</a></li>   
				   </ul>   
			   </c:forEach> 
	           <div id="middle_line">教育信息<hr/></div>
		       <div id="work_input">
		           <ul>
			            <li><span>成员姓名：</span><input type="text" name="family.name" id="family.name" value="${family.name}"/></li>
				        <li><span>出生日期：</span><input type="text" name="family.birthdayStr" id="family.birthdayStr"    onclick="WdatePicker()"
				             readonly value="<fmt:formatDate value="${family.birthday}"  pattern="yyyy-MM-dd"/>"/></li>
				        <li><span>工作单位：</span><input type="text" name="family.workCompany" id="family.workCompany" value="${family.workCompany}"/></li>
			        </ul>
			        <ul>
				        <li><span>与本人关系：</span><input type="text" name="family.relationship" id="family.relationship" value="${family.relationship}"/></li>  
				        <li><span>联系电话：</span><input type="text" name="family.phone" id="family.phone" value="${family.phone}"/></li>
		            </ul>
	           </div>
					<ul id="com_all_delete02">
		              <li>
						<button id="chall" onclick="addFamily();" class="button_bg">保存</button>
		              </li>
			          <li>
			              <button id="delAll" onclick="cancel();" class="button_bg">退出</button>
		              </li>
		           </ul>     
	         </form> 
    	</div>	
<!-- family infomation  -->
    	
    </div>
</div>
</body>
<jsp:include page="/index.do?method=foot" flush="true"/>
</html>
<script type="text/javascript">  
	 var dFile = document.getElementById("headpic");  
	 var dImg = document.getElementById("showheadpic");   
	 if('${tempBasic.headPic}'!=null && '${tempBasic.headPic}'!="")
	 	dImg.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = "<%=request.getContextPath()%>/upload/${tempBasic.headPic}";
	 else
	 	dImg.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = "<%=request.getContextPath()%>/images/default.png"; 
</script> 			           

<script type="text/javascript"> 
selOption("parentcom",'${tempBasic.companyName}',"text") ; 
$ID("parentcom").fireEvent("onchange");    
selOption("basic.employeeState",'${tempBasic.employeeState}',"value") ; 
selOption("basic.sex",'${tempBasic.sex}',"value") ;  
selOption("basic.marry",'${tempBasic.marry}',"value") ; 
selOption("basic.driver",'${tempBasic.driver}',"value") ;  
setTimeout('selOption("parentdep","${tempBasic.depName}","text")',2)  

var operatItem = "${operateItem}" ;  
if(operatItem=='basic'|| operatItem=='' ||operatItem=='null')
	 setClassAtt('item_1','eminfo'); 
 else if(operatItem=='work')
	 setClassAtt('item_3','eminfo_work');
 else if(operatItem=='family')
	 setClassAtt('item_4','eminfo_family'); 
 else if(operatItem=='education'){ 
	 setClassAtt('item_2','eminfo_edu');
 }  
titleInit('edu_school',8) ; 
titleInit('work_com',8) ;
titleInit('family_com',8) ; 
</script> 

