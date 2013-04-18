<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>接单表展示</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/order_form_detail.css"/> 
<script type='text/javascript' src="<%=request.getContextPath() %>/js/leftnav.js"></script>
</head>
<body>
<div id="layout">
  <jsp:include page="/index.do?method=head" flush="true"/>
	<div id="mid">
    	<jsp:include page="/index.do?method=left" flush="true"/>
    <div id="mainpage">
      <div id="location"> 当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span>
      <span><a href="<%=request.getContextPath()%>/accept.do?method=list">接单表管理</a></span><span><a>接单表信息</a></span></div>
      <div class="paper">接单表信息：</div>
      <div class="basic_info_detail">
        <h3 class="h_detail">基本信息:</h3>
        <div class="info_detailbox">
          <ul>
            <li class="title_d"> 内部号：</li>
            <li>${detailone.innerCode}</li>
            <li class="title_d"> 客户名称：</li>
            <li>${detailone.customer}</li>
            <li class="title_d"> 业务员：</li>
            <li>${detailone.manager}</li>
            <li class="title_d"> 合同号：</li>
            <li>${detailone.contractCode}</li>
            <li class="title_d"> 货值：</li>
            <li>${detailone.materiaMoney}${detailone.moneyUnit}</li>
            <li class="title_d"> 主单号：</li>
            <li>${detailone.mainOrderCode}</li>
            <li class="title_d"> 分单号：</li>
            <li>${detailone.subOrderCode}</li>
            <li class="title_d"> 起运港/目的港：</li>
            <li>${detailone.startOrEndPort}</li>
            <li class="title_d"> 件数：</li>
            <li>${detailone.piece}</li>
            <li class="title_d"> 重量(cw/cw)：</li>
            <li>${detailone.weight}</li>
            <li class="title_d"> 产品名称：</li>
            <li>${detailone.productName}</li>
            <li class="title_d"> 完毕日期：</li>
            <li><fmt:formatDate value="${detailone.endDate}" pattern="yyyy-MM-dd" /></li>
            <li class="title_d"> 接单日期：</li>
            <li><fmt:formatDate value="${detailone.getOrderDate}" pattern="yyyy-MM-dd" /></li>
          </ul>
        </div>
      </div>
      <div class="importance_info">
        <div class="info_detailbox">
          <ul>
            <li class="title_d"> 应收结帐款：</li>
            <li>${detailone.mayMoney}</li>
            <li class="title_d"> 预计结算日期：</li>
            <li><fmt:formatDate value="${detailone.eexpectMoneyDate}" pattern="yyyy-MM-dd" /></li>
            <li class="title_d"> 实际结回日期：</li>
            <li><fmt:formatDate value="${detailone.realMoneyDate}" pattern="yyyy-MM-dd" /></li>
            <li class="title_d"> 成本：</li>
            <li>${detailone.cost}</li>
            <li class="title_d">服务费：</li>
            <li>${detailone.serviceMoney}</li>
            <li class="title_d"> 利润：</li>
            <li>${detailone.profit}</li>
            <li class="title_d"> 利润率：</li>
            <li>${detailone.profitRate}%</li>
          </ul>
        </div>
      </div>
      <div id="fund_info">
        <div class="info_detailbox">
          <ul>
            <li class="title_d">货款金额：</li>
            <li>${detailone.materiaCost}${detailone.matCostUnit}</li>
            <li class="title_d">预付金额：</li>
            <li>${detailone.prePayMateriaMoney}</li>
            <li class="title_d">汇差：</li>
            <li>${detailone.huiCha}</li>
            <li class="title_d">垫付款金额：</li>
            <li>${detailone.dianFuMatMoney}</li>
            <li class="title_d">预计结算日期：</li>
            <li><fmt:formatDate value="${detailone.excpectMatMoneyDate}" pattern="yyyy-MM-dd" /></li>
            <li class="title_d">实际结回日期：</li>
            <li><fmt:formatDate value="${detailone.realMatMoneyDate}" pattern="yyyy-MM-dd" /></li>
            <li class="title_d">付汇日期：</li>
            <li><fmt:formatDate value="${detailone.payExchangeDate}" pattern="yyyy-MM-dd" /></li>
            <li class="title_d">汇率：</li>
            <li>${detailone.exchangeRate}</li>
            <li class="title_d"></li>
            <li></li>
            <li class="title_d"></li>
            <li></li>
            <li class="title_d">税金RMB：</li>
            <li>${detailone.taxMoney}</li>
            <li class="title_d">预付税金：</li>
            <li>${detailone.prePayTax}</li>
            <li class="title_d">税差：</li>
            <li>${detailone.shuiCha}</li>
            <li class="title_d">垫付税金：</li>
            <li>${detailone.dianFuTax}</li>
            <li class="title_d">预计结算日期：</li>
            <li><fmt:formatDate value="${detailone.expectTaxDate}" pattern="yyyy-MM-dd" /></li>
            <li class="title_d">实际结算日期：</li>
            <li><fmt:formatDate value="${detailone.realTaxDate}" pattern="yyyy-MM-dd" /></li>
			</ul>
        </div>
		<div id="remark_box">
			<span class="remarkfont">备注：</span>${detailone.comment}</div>
		</div>
        <h3 class="h_detail">分段还款信息:</h3>
       			<div id="list_box03">
					<div id="total">（总计：0rmb）</div> 
            		<c:set var="allmoney" value="0"/>
					<ul class="list_title_money">
            			<li class="money_type">款型类型</li>
                		<li class="money_number">还款金额(RMB)</li>
                		<li class="time_back">录入时间</li>             
                		<li class="time_back">还款时间</li>
                		<li class="remark_title">备注</li>
            		</ul>
            		<c:forEach var="one" items="${moneylist}">
						<c:set var="allmoney" value="${allmoney+one.retCount}"/>
            		<ul class="doc_manag_list center">
                		<li class="money_type center">
                		<a class="retract" name="docTitle">
                		<c:if test="${one.moneyItem=='mayMoney'}">应收结帐款</c:if>
							<c:if test="${one.moneyItem=='matMoney'}">货款</c:if>
							<c:if test="${one.moneyItem=='taxMoney'}">税金</c:if>
							<c:if test="${one.moneyItem=='otherMoney'}">其它</c:if>
                		</a></li>
                		<li class="money_number center">${one.retCount}</li>                
                		<li class="time_back center"><fmt:formatDate value="${one.createDate}" pattern="yyyy-MM-dd" /></li>
                		<li class="time_back center"><fmt:formatDate value="${one.retDate}" pattern="yyyy-MM-dd" /></li>
                		<li class="remark_title center">${one.comment}</li>
            		</ul>  
            		</c:forEach>
	 			</div>
      </div>
      <div id="com_all_delete02" class="button_no01">
      	<button onclick="history.go(-1);" class="button_bg">关闭</button>
      </div>
</div>
<jsp:include page="/index.do?method=foot" flush="true"/>
</div> 
</body> 
</html>

<script type="text/javascript">  
  var allmoney = '<fmt:formatNumber value="${allmoney}" pattern="#,#00.0#"/>';
  document.getElementById("total").innerHTML = "（总计："+allmoney + "rmb）" ;  
</script>
