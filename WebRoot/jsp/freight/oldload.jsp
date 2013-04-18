<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报价信息</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/general.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DivWindow.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/System.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/FreightDWR.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
</head>
<body>
<div id="layout"><jsp:include page="/index.do?method=top" flush="true"></jsp:include>
<div id="mainpage">
<h2>报价信息<span id="inpageinfo"></span></h2>
<ul class="basicinfo">
	<form action="<%=request.getContextPath()%>/freight.do?method=save" name="pageForm" method="post">
		<input type="hidden" id="freight.id" name="freight.id" value="${form.freight.id}" />
		<input type="hidden" id="freight.air" name="freight.air" value="${form.freight.air}" />
		<input type="hidden" id="freight.land" name="freight.land" value="${form.freight.land}" />
		<input type="hidden" id="freight.sea" name="freight.sea" value="${form.freight.sea}" />
	<ul>
		<li class="li_title">报价编号：</li>
		<li><input <c:if test="${form.freight.autoCode||form.freight.id!=null}"> readonly</c:if>	class="text_input" type="text" name="freight.code" id="freight.code" value="${form.freight.code}" /></li>
	</ul>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">始发地</td>
				<td colspan="2">目的地</td>
			</tr>
			<tr>
				<td>始发地编码：</td>
				<td><input type="text" name="freight.fromCode" id="freight.fromCode" value="${form.freight.fromCode}" /></td>
				<td>目的地编码：</td>
				<td><input type="text" name="freight.toCode" id="freight.toCode" value="${form.freight.toCode}"/></td>
			</tr>
			<tr>
				<td>始发地名称：</td>
				<td><input type="text" name="freight.fromName" id="freight.fromName" value="${form.freight.fromName}"/></td>
				<td>目的地名称：</td>
				<td><input type="text" name="freight.toName" id="freight.toName" value="${form.freight.toName}"/></td>
			</tr>
			<tr>
				<td colspan="4"><hr/></td>
			</tr>			
			<tr>
				<td colspan="4">航运报价</td>
			</tr>
			<tr>
				<td>航班信息：</td>
				<td><textarea id="freight.airInfo" cols="40" rows="5"></textarea></td>
			</tr>
			<tr>
				<td>地面价格：</td>
				<td><textarea id="freight.airFromInfo" cols="40" rows="5"></textarea>
				</td>
				<td>地面价格：</td>
				<td><textarea id="freight.airToInfo" cols="40" rows="5"></textarea>
				</td>				
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" border="0">
					<tr>
						<td>FSC</td>
						<td>SSC</td>						
						<td>航程</td>
						<td>币种</td>
						<td>有效期</td>
						<td width="30%">&nbsp;</td>
					</tr>
					<tr>						
						<td><input type="text" class="text_num" id="freight.airFsc"/>/Kg</td>
						<td><input type="text" class="text_num" id="freight.airSsc"/>/Kg</td>
						<td>
							<select id="freight.airHc">
								<option value="">请选择</option>
								<option value="直航">直航</option>
								<option value="转航">转航</option>								
							</select>
						</td>
						<td><input type="text" class="text_num" id="freight.airCurrency"/></td>
						<td><input type="text" id="freight.airPeriodValidity" readonly onClick="WdatePicker()" /></td>
						<td width="30%">&nbsp;</td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td>Min</td>
						<td>-45kg</td>
						<td>+45kg</td>
						<td>+100kg</td>
						<td>+300kg</td>
						<td>+500kg</td>
						<td>+1000kg</td>
					</tr>
					<tr>
						<td><input type="text" class="text_num" id="freight.airMin"/>/SET</td>
						<td><input type="text" class="text_num" id="freight.airLess45"/>/Kg</td>
						<td><input type="text" class="text_num" id="freight.airMore45"/>/Kg</td>
						<td><input type="text" class="text_num" id="freight.airMore100"/>/Kg</td>
						<td><input type="text" class="text_num" id="freight.airMore300"/>/Kg</td>
						<td><input type="text" class="text_num" id="freight.airMore500"/>/Kg</td>
						<td><input type="text" class="text_num" id="freight.airMore1000"/>/Kg</td>
					</tr>
				</table>
				</td>
			</tr>			
			<tr>
				<td colspan="4"><hr/></td>
			</tr>
			<tr>
				<td colspan="4">陆运报价</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" border="0">
					<tr>
						<td>
						币种：<input type="text" class="text_num" id="freight.landCurrency"/>
						有效期：<input type="text" id="freight.landPeriodValidity" readonly onClick="WdatePicker()" />
						</td>						
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td colspan="3">拼车</td>
						<td>8T</td>
						<td>10T</td>
						<td>12T</td>
						<td><input type="text" class="text_num" id="freight.landOthName" />T</td>
					</tr>
					<tr>
						<td><input type="text" class="text_num" id="freight.landAssemblyKg" />/Kg</td>
						<td><input type="text" class="text_num" id="freight.landAssemblyT" />/T</td>
						<td><input type="text" class="text_num" id="freight.landAssemblyCbm" />/CBM</td>
						<td><input type="text" class="text_num" id="freight.land5T" /></td>
						<td><input type="text" class="text_num" id="freight.land8T" /></td>
						<td><input type="text" class="text_num" id="freight.land12_5T" /></td>
						<td><input type="text" class="text_num" id="freight.landOthValue" /></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>地面价格：</td>
				<td><textarea id="freight.landFromInfo" cols="40" rows="5"></textarea>
				</td>
				<td>地面价格：</td>
				<td><textarea id="freight.landToInfo" cols="40" rows="5"></textarea>
				</td>				
			</tr>
			<tr>
				<td colspan="4"><hr/></td>
			</tr>
			<tr>
				<td colspan="4">海运报价</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" border="0">
					<tr>
						<td>
						币种：<input type="text" class="text_num" id="freight.seaCurrency"/>
						有效期：<input type="text" id="freight.seaPeriodValidity" readonly onClick="WdatePicker()" />						
						</td>						
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td>拼仓：<input type="text" class="text_num" id="freight.seaAssemblyCbm"/>/CBM</td>
						<td><textarea id="freight.seaAssemblyCbmInfo" cols="40" rows="5"></textarea></td>
						<td>拼仓：<input type="text" class="text_num" id="freight.seaAssemblyT"/>/T</td>
						<td><textarea id="freight.seaAssemblyTInfo" cols="40" rows="5"></textarea></td>
					</tr>
					<tr>
						<td>20DR：<input type="text" class="text_num" id="freight.sea20Dr"/>/箱</td>
						<td><textarea id="freight.sea20DrInfo" cols="40" rows="5"></textarea></td>
						<td>40DR：<input type="text" class="text_num" id="freight.sea40Dr"/>/箱</td>
						<td><textarea id="freight.sea40DrInfo" cols="40" rows="5"></textarea></td>
					</tr>
					<tr>
						<td>40HQ：<input type="text" class="text_num" id="freight.sea40Hq"/>/箱</td>
						<td><textarea id="freight.sea40HqInfo" cols="40" rows="5"></textarea></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				</td>				
			</tr>
			</table>
	</form>
</ul>
<span class="line"></span>
<ul class="btnlist">
	<button onClick="goback()" class="Btn"><span>取消</span></button>
	<button onClick="saveSubmit()" class="Btn"><span>保存信息</span></button>
</ul>
</div>
</div>
</div>
</body>
<script type="text/javascript">
//检查以及拼装空运JSON
function getAirStr(){
	var airSsc = $ID("freight.airSsc").value;
	if(!airSsc.isNum()){
		$ID("inpageinfo").innerHTML="空运【SCC】必须为数值类型！";
		return false;
	}
	var airFsc = $ID("freight.airFsc").value;
	if(!airFsc.isNum()){
		$ID("inpageinfo").innerHTML="空运【FSC】必须为数值类型！";
		return false;
	}
	var airMin = $ID("freight.airMin").value;
	if(!airMin.isNum()){
		$ID("inpageinfo").innerHTML="空运【Min】必须为数值类型！";
		return false;
	}
	var airLess45 = $ID("freight.airLess45").value;
	if(!airLess45.isNum()){
		$ID("inpageinfo").innerHTML="空运【-45】必须为数值类型！";
		return false;
	}
	var airMore45 = $ID("freight.airMore45").value;
	if(!airMore45.isNum()){
		$ID("inpageinfo").innerHTML="空运【+45】必须为数值类型！";
		return false;
	}
	var airMore100 = $ID("freight.airMore100").value;
	if(!airMore100.isNum()){
		$ID("inpageinfo").innerHTML="空运【+100】必须为数值类型！";
		return false;
	}
	var airMore300 = $ID("freight.airMore300").value;
	if(!airMore300.isNum()){
		$ID("inpageinfo").innerHTML="空运【+300】必须为数值类型！";
		return false;
	}
	var airMore500 = $ID("freight.airMore500").value;
	if(!airMore500.isNum()){
		$ID("inpageinfo").innerHTML="空运【+500】必须为数值类型！";
		return false;
	}
	var airMore1000 = $ID("freight.airMore1000").value;
	if(!airMore1000.isNum()){
		$ID("inpageinfo").innerHTML="空运【+1000】必须为数值类型！";
		return false;
	}
	
	var air="{airMin:"+(airMin==""?0:airMin);
	air+=",airLess45:"+(airLess45==""?0:airLess45);
	air+=",airMore45:"+(airMore45==""?0:airMore45);
	air+=",airMore100:"+(airMore100==""?0:airMore100);
	air+=",airMore300:"+(airMore300==""?0:airMore300);
	air+=",airMore500:"+(airMore500==""?0:airMore500);
	air+=",airMore1000:"+(airMore1000==""?0:airMore1000);
	air+=",airFromInfo:'" + repstr($ID("freight.airFromInfo").innerHTML) + "'";	
	air+=",airToInfo:'" + repstr($ID("freight.airToInfo").innerHTML) + "'";
	air+=",airSsc:"+(airSsc==""?0:airSsc);
	air+=",airFsc:"+(airFsc==""?0:airFsc);
	air+=",airHc:'" +$ID("freight.airHc").value + "'";	
	air+=",airInfo:'" + repstr($ID("freight.airInfo").innerHTML) + "'";
	
	air+=",airCurrency:'" +$ID("freight.airCurrency").value + "'";
	air+=",airPeriodValidity:'" +$ID("freight.airPeriodValidity").value + "'";

	air+="}";
	
	$ID("freight.air").value=air;	
	return true;
}
//检查以及拼装陆运JSON
function getLandStr(){
	var landAssemblyKg = $ID("freight.landAssemblyKg").value;
	if(!landAssemblyKg.isNum()){
		$ID("inpageinfo").innerHTML="陆运【拼车】必须为数值类型！";
		return false;
	}
	var landAssemblyT = $ID("freight.landAssemblyT").value;
	if(!landAssemblyT.isNum()){
		$ID("inpageinfo").innerHTML="陆运【拼车】必须为数值类型！";
		return false;
	}
	var landAssemblyCbm = $ID("freight.landAssemblyCbm").value;
	if(!landAssemblyCbm.isNum()){
		$ID("inpageinfo").innerHTML="陆运【拼车】必须为数值类型！";
		return false;
	}
	var land5T = $ID("freight.land5T").value;
	if(!land5T.isNum()){
		$ID("inpageinfo").innerHTML="陆运【5T】必须为数值类型！";
		return false;
	}
	var land8T = $ID("freight.land8T").value;
	if(!land8T.isNum()){
		$ID("inpageinfo").innerHTML="陆运【8T】必须为数值类型！";
		return false;
	}
	var land12_5T = $ID("freight.land12_5T").value;
	if(!land12_5T.isNum()){
		$ID("inpageinfo").innerHTML="陆运【12.5T】必须为数值类型！";
		return false;
	}
	var landOthName = $ID("freight.landOthName").value;
/*	if(!landOthName.isNum()){
		$ID("inpageinfo").innerHTML="陆运【自定义车型】必须为数值类型！";
		return false;
	}*/
	var landOthValue = $ID("freight.landOthValue").value;
	if(!landOthValue.isNum()){
		$ID("inpageinfo").innerHTML="陆运【自定义车价钱】必须为数值类型！";
		return false;
	}

	var land="{landAssemblyKg:"+(landAssemblyKg==""?0:landAssemblyKg);
	land+=",landAssemblyT:"+(landAssemblyT==""?0:landAssemblyT);
	land+=",landAssemblyCbm:"+(landAssemblyCbm==""?0:landAssemblyCbm);
	land+=",land5T:"+(land5T==""?0:land5T);
	land+=",land8T:"+(land8T==""?0:land8T);
	land+=",land12_5T:"+(land12_5T==""?0:land12_5T);
	land+=",landOthName:'"+(landOthName==""?0:landOthName)+"'";
	land+=",landOthValue:"+(landOthValue==""?0:landOthValue);	


	land+=",landToInfo:'" + repstr($ID("freight.landToInfo").innerHTML) + "'";
	land+=",landFromInfo:'" + repstr($ID("freight.landFromInfo").innerHTML) + "'";

	land+=",landCurrency:'" +$ID("freight.landCurrency").value + "'";
	land+=",landPeriodValidity:'" +$ID("freight.landPeriodValidity").value + "'";
	
	land+="}";
	
	$ID("freight.land").value=land;
	return true;
}
//检查以及拼装海运JSON
function getSeaStr(){
	var seaAssemblyT = $ID("freight.seaAssemblyT").value;
	if(!seaAssemblyT.isNum()){
		$ID("inpageinfo").innerHTML="海运【拼箱】必须为数值类型！";
		return false;
	}
	var seaAssemblyCbm = $ID("freight.seaAssemblyCbm").value;
	if(!seaAssemblyCbm.isNum()){
		$ID("inpageinfo").innerHTML="海运【拼箱】必须为数值类型！";
		return false;
	}
	var sea20Dr = $ID("freight.sea20Dr").value;
	if(!sea20Dr.isNum()){
		$ID("inpageinfo").innerHTML="海运【20DR】必须为数值类型！";
		return false;
	}
	var sea40Dr = $ID("freight.sea40Dr").value;
	if(!sea40Dr.isNum()){
		$ID("inpageinfo").innerHTML="海运【40DR】必须为数值类型！";
		return false;
	}
	var sea40Hq = $ID("freight.sea40Hq").value;
	if(!sea40Hq.isNum()){
		$ID("inpageinfo").innerHTML="海运【40HQ】必须为数值类型！";
		return false;
	}
	
	var sea="{seaAssemblyT:"+(seaAssemblyT==""?0:seaAssemblyT);
	sea+=",seaAssemblyCbm:"+(seaAssemblyCbm==""?0:seaAssemblyCbm);
	sea+=",sea20Dr:"+(sea20Dr==""?0:sea20Dr);
	sea+=",sea40Dr:"+(sea40Dr==""?0:sea40Dr);
	sea+=",sea40Hq:"+(sea40Hq==""?0:sea40Hq);	

	sea+=",seaAssemblyTInfo:'"+repstr($ID("freight.seaAssemblyTInfo").value)+"'";
	sea+=",seaAssemblyCbmInfo:'"+repstr($ID("freight.seaAssemblyCbmInfo").value)+"'";
	sea+=",sea20DrInfo:'"+repstr($ID("freight.sea20DrInfo").value)+"'";
	sea+=",sea40DrInfo:'"+repstr($ID("freight.sea40DrInfo").value)+"'";
	sea+=",sea40HqInfo:'"+repstr($ID("freight.sea40HqInfo").value)+"'";
	
	sea+=",seaCurrency:'" +$ID("freight.seaCurrency").value + "'";
	sea+=",seaPeriodValidity:'" +$ID("freight.seaPeriodValidity").value + "'";
	sea+="}";
	
	$ID("freight.sea").value=sea;
	return true;
}

//保存运单
function saveSubmit(){
	if(!(getAirStr()&&getLandStr()&&getSeaStr())){
		return;
	}
	var code = $ID("freight.code");
	if(!code.readOnly&&$ID("freight.code").value.isEmpty()){
		$ID("inpageinfo").innerHTML = "编号不能为空！";
		return;
	}

	var fromName = $ID("freight.fromName").value;
	if(fromName.isEmpty()){
		$ID("inpageinfo").innerHTML = "始发地名称不能为空！";
		return;
	}
	
	var toName = $ID("freight.toName").value;
	if(toName.isEmpty()){
		$ID("inpageinfo").innerHTML = "目的地名称不能为空！";
		return;
	}
	
	if(!code.readOnly){
		FreightDWR.hasCode(code.value,function(data){
			if(data){
				$ID("inpageinfo").innerHTML = "编号不可重复！";
				return;
			}
			document.forms['pageForm'].submit();
		});
	}
	else if(code.readOnly&&$ID("freight.id").value.isEmpty()){
		var takeCode=function(data)
		{			
			if(data.length>0){
				var alertMsg = new DivWindow("alertMsg","系统提示",250,120,"系统自动分配的单号："+data+"有重号。<br>是否自动顺延？");
				alertMsg.addButton("确定",function(){
					document.forms['pageForm'].submit();
				});	
				alertMsg.addButton("取消");
				alertMsg.open();
				return;
			}
			document.forms['pageForm'].submit();
		}
		System.getCode('AutoCode_Freight','FhiFreight', 'code',takeCode);
	}
	else{
		document.forms['pageForm'].submit();
	}
}

//运输方式初始化
function initFreight(){
	
	var air = $ID("freight.air").value;
	
	if(air!=""){
		air = eval('(' + air + ')');
		$ID("freight.airMin").value=(air.airMin==0?"":air.airMin);
		$ID("freight.airLess45").value=(air.airLess45==0?"":air.airLess45);
		$ID("freight.airMore45").value=(air.airMore45==0?"":air.airMore45);
		$ID("freight.airMore100").value=(air.airMore100==0?"":air.airMore100);
		$ID("freight.airMore300").value=(air.airMore300==0?"":air.airMore300);
		$ID("freight.airMore500").value=(air.airMore500==0?"":air.airMore500);
		$ID("freight.airMore1000").value=(air.airMore1000==0?"":air.airMore1000);

		$ID("freight.airFromInfo").value=repstrOut(air.airFromInfo);
		$ID("freight.airToInfo").value=repstrOut(air.airToInfo);
		$ID("freight.airSsc").value=(air.airSsc==0?"":air.airSsc);
		$ID("freight.airFsc").value=(air.airFsc==0?"":air.airFsc);
		$ID("freight.airInfo").value=repstrOut(air.airInfo);
		
		$ID("freight.airCurrency").value=air.airCurrency;
		$ID("freight.airPeriodValidity").value=air.airPeriodValidity;

		$ID("freight.airHc").value=air.airHc;
		
	}
	
	var land = $ID("freight.land").value;
	if(land!=""){
		land = eval('(' + land + ')');
		$ID("freight.landAssemblyKg").value=(land.landAssemblyKg==0?"":land.landAssemblyKg);
		$ID("freight.landAssemblyT").value=(land.landAssemblyT==0?"":land.landAssemblyT);
		$ID("freight.landAssemblyCbm").value=(land.landAssemblyCbm==0?"":land.landAssemblyCbm);
		$ID("freight.land5T").value=(land.land5T==0?"":land.land5T);
		$ID("freight.land8T").value=(land.land8T==0?"":land.land8T);
		$ID("freight.land12_5T").value=(land.land12_5T==0?"":land.land12_5T);
		$ID("freight.landOthName").value=(land.landOthName==0?"":land.landOthName);
		$ID("freight.landOthValue").value=(land.landOthValue==0?"":land.landOthValue);
		$ID("freight.landCurrency").value=land.landCurrency;
		$ID("freight.landPeriodValidity").value=land.landPeriodValidity;
		$ID("freight.landFromInfo").value=repstrOut(land.landFromInfo);
		$ID("freight.landToInfo").value=repstrOut(land.landToInfo);
	}
	
	var sea = $ID("freight.sea").value;
	if(sea!=""){
		sea = eval('(' + sea + ')');
		$ID("freight.seaAssemblyT").value=(sea.seaAssemblyT==0?"":sea.seaAssemblyT);
		$ID("freight.seaAssemblyCbm").value=(sea.seaAssemblyCbm==0?"":sea.seaAssemblyCbm);
		$ID("freight.sea20Dr").value=(sea.sea20Dr==0?"":sea.sea20Dr);
		$ID("freight.sea40Dr").value=(sea.sea40Dr==0?"":sea.sea40Dr);
		$ID("freight.sea40Hq").value=(sea.sea40Hq==0?"":sea.sea40Hq);
		$ID("freight.seaCurrency").value=sea.seaCurrency;
		$ID("freight.seaPeriodValidity").value=sea.seaPeriodValidity;
		
		$ID("freight.seaAssemblyTInfo").value=repstrOut(sea.seaAssemblyTInfo);
		$ID("freight.seaAssemblyCbmInfo").value=repstrOut(sea.seaAssemblyCbmInfo);
		$ID("freight.sea20DrInfo").value=repstrOut(sea.sea20DrInfo);
		$ID("freight.sea40DrInfo").value=repstrOut(sea.sea40DrInfo);
		$ID("freight.sea40HqInfo").value=repstrOut(sea.sea40HqInfo);
	}
}
initFreight();

function  goback(){
	window.location = "<%=request.getContextPath()%>/freight.do?method=index";
}

function repstr(str)
{       
	if(str==null||str==undefined)
		return "";
	var reg=new RegExp("\r\n","g");
  	return str.replace(reg,"<br>");
}
function repstrOut(str)
{ 
	if(str==null||str==undefined)
		return "";
	var reg=new RegExp("<br>","g");
	return str.replace(reg,"\r\n");
}
</script>
</html>
