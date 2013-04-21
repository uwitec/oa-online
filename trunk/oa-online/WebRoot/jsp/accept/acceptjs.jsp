<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
//js  四舍五入 
function round(num){ 
return Math.round(num*100)/100 ;
}

//小数保留位数
function rounddot(c,n){  
	return Math.round(c*Math.pow(10,n))/Math.pow(10,n) ;
} 

 //浮点数加法运算   
 function floatAdd(arg1,arg2){   
   var r1=0,r2=0,m=1;   
   try{r1=arg1.toString().split(".")[1].length}catch(e){}   
   try{r2=arg2.toString().split(".")[1].length}catch(e){}   
   m=Math.pow(10,Math.max(r1,r2))   
   return (arg1*m+arg2*m)/m   
  }   
  
 //浮点数减法运算   
 function floatSub(arg1,arg2){   
	 var r1=0,r2=0,m=1;   
	 try{r1=arg1.toString().split(".")[1].length}catch(e){}   
	 try{r2=arg2.toString().split(".")[1].length}catch(e){}   
	 m=Math.pow(10,Math.max(r1,r2));  
	 return (arg1*m-arg2*m)/m ;   
 }   
    
 //浮点数乘法运算   
 function floatMul(arg1,arg2){    
  var m=0,s1=arg1.toString(),s2=arg2.toString();    
  try{m+=s1.split(".")[1].length}catch(e){}    
  try{m+=s2.split(".")[1].length}catch(e){}    
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)    
}    
  
  
//浮点数除法运算   
function floatDiv(arg1,arg2){    
	var t1=0,t2=0,r1=0,r2=1;    
	try{t1=arg1.toString().split(".")[1].length}catch(e){}    
	try{t2=arg2.toString().split(".")[1].length}catch(e){}    
	with(Math){    
		r1=Number(arg1.toString().replace(".",""))    
		r2=Number(arg2.toString().replace(".",""))  
		return floatMul((r1/r2),pow(10,t2-t1));    
	} 	   
}   
//排序操作表单提交
	function orderby(obj) {
		//状态更改以及排序设置
		var str=obj.innerHTML;
		if (obj.value == 1) {
			obj.value = 2;
			document.getElementById("orderByOrder").value=2;
		} else {
			obj.value = 1;
			document.getElementById("orderByOrder").value=1;
		}
		document.getElementById("orderByName").value=obj.id;
		document.forms['pageForm'].pageNo.value=1;
		document.forms['pageForm'].submit();
	}

	//初始化排序信息
	function orderByInit() { 
		var orderByName = document.getElementById("orderByName").value; 
		var orderByOrder = document.getElementById("orderByOrder").value; 
		if(orderByName.length<1) {
			return;
		} 
		var orderObj = document.getElementById(orderByName);
		orderObj.value=parseInt(orderByOrder);
		if (orderObj.value == 1) {
			orderObj.innerHTML=orderObj.innerHTML+"↓";
		} else if(orderObj.value == 2){
			orderObj.innerHTML=orderObj.innerHTML+"↑";
		}
	}


function selAll(obj){ 
    var selAllFlag = false ; 
	var goods = document.getElementsByName(obj); 
	if(goods[0].checked==false)
	  selAllFlag = true ;
	for ( var i = 0; i < goods.length; i++) {
		goods[i].checked = selAllFlag;
	} 
}

function isSelSome(obj){
	var selobj = document.getElementsByName(obj) ; 
	   for(var i=0 ; i < selobj.length ; i++){
	      if(selobj[i].checked)
	          return true ;
	   }
	   return false ; 
}


function setClassAtt(obj){ 
  var items = ['item_1','item_2'] ;  
  
    if(obj==items[0]){
       document.getElementById(items[0]).className = "button_back" ; 
       document.getElementById(items[1]).className = "button_back02" ;  
       document.getElementById("emergence").style.display = "block" ;
       document.getElementById("retmoney").style.display = "none" ;
    }else{  
       document.getElementById(items[0]).className = "button_back_reverse" ; 
       document.getElementById(items[1]).className = "button_back_reverse2" ;  
       document.getElementById("emergence").style.display = "none" ;
       document.getElementById("retmoney").style.display = "block" ;
    }  
} 

function $ID(id){
  return document.getElementById(id) ; 
} 
   
String.prototype.trim=function(){
   return this.replace(/(^\s*)|(\s*$)/g, "");
}

function isInt(obj){
  return /^[1-9]\d*$/.test(obj) 
}


function addAccept(id){
  var message = "red_cue" ;
  var innerCode = $ID("accept.innerCode")
  if(innerCode.value.trim()==""){ 
       showMsgbox("内部号不能为空","red_cue") ;
       return ; 
  }
  AcceptDWR.hasAccept(innerCode.value,id,function(data){
     if(data==true){ 
       showMsgbox("内部号已经存在，请重新输入","red_cue") ;
       return ;
     }else{
      
       var customer = $ID("accept.customer");
       if(customer.value.trim()==""){ 
         showMsgbox("客户名称不能为空","red_cue") ; 
         return ;
       }
      
       var materiaMoney = $ID("accept.materiaMoney");
       if(materiaMoney.value.trim()!="" && isNaN(materiaMoney.value.trim())){ 
         showMsgbox("货值只能为数字","red_cue") ;
         materiaMoney.select() ;
         return ;
       } 
       var mayMoney = $ID("accept.mayMoney");
       if(mayMoney.value.trim()!="" && isNaN(mayMoney.value.trim())){ 
         showMsgbox("应收结帐款只能为数字","red_cue") ;
         mayMoney.select() ;
         return ;
       }
       var cost = $ID("accept.cost");
       if(cost.value.trim()!="" && isNaN(cost.value.trim())){ 
         showMsgbox("成本只能为数字","red_cue") ;
         cost.select() ;
         return ;
       }
       var serviceMoney = $ID("accept.serviceMoney");
       if(serviceMoney.value.trim()!="" && isNaN(serviceMoney.value.trim())){ 
         showMsgbox("服务费只能为数字","red_cue") ;
         serviceMoney.select() ;
         return ;
       }
       var profit = $ID("accept.profit");
       if(profit.value.trim()!="" && isNaN(profit.value.trim())){ 
         showMsgbox("净利润只能为数字","red_cue") ;
         profit.select() ;
         return ;
       }
       var tiCheng = $ID("accept.tiCheng");
       if(tiCheng.value.trim()!="" && isNaN(tiCheng.value.trim())){ 
         showMsgbox("提成只能为数字","red_cue") ;
         tiCheng.select() ;
         return ;
       }
       var tiChengRate = $ID("accept.tiChengRate");
       if(tiChengRate.value.trim()!="" && isNaN(tiChengRate.value.trim())){ 
         showMsgbox("提成比只能为数字","red_cue") ;
         tiChengRate.select() ;
         return ;
       }
       /*
       var profitRate = $ID("accept.profitRate");
       if(profitRate.value.trim()!="" && isNaN(profitRate.value.trim())){
         showMsgbox("利润率只能为数字","red_cue") ;
         profitRate.select() ;
         return ;
       } */ 
       
       var materiaCost = $ID("accept.materiaCost");
       if(materiaCost.value.trim()!="" && isNaN(materiaCost.value.trim())){
         showMsgbox("货款金额只能为数字","red_cue") ;
         materiaCost.select() ;
         return ;
       } 
       var prePayMateriaMoney = $ID("accept.prePayMateriaMoney") ;
       if(prePayMateriaMoney.value.trim()!="" && isNaN(prePayMateriaMoney.value.trim())){
         showMsgbox("预付金额只能为数字","red_cue") ;
         prePayMateriaMoney.select() ;
         return ;
       } 
       var huiCha = $ID("accept.huiCha");
       if(huiCha.value.trim()!="" && isNaN(huiCha.value.trim())){
         showMsgbox("汇差只能为数字","red_cue") ;
         huiCha.select() ;
         return ;
       } 
       var dianFuMatMoney = $ID("accept.dianFuMatMoney");
       if(dianFuMatMoney.value.trim()!="" && isNaN(dianFuMatMoney.value.trim())){
         showMsgbox("垫付款金额只能为数字","red_cue") ;
         dianFuMatMoney.select() ;
         return ;
       } 
       var exchangeRate = $ID("accept.exchangeRate");
       if(exchangeRate.value.trim()!="" && isNaN(exchangeRate.value.trim())){
         showMsgbox("汇率只能为数字","red_cue") ;
         exchangeRate.select() ;
         return ;
       } 
       var taxMoney = $ID("accept.taxMoney");
       if(taxMoney.value.trim()!="" && isNaN(taxMoney.value.trim())){
         showMsgbox("税金只能为数字","red_cue") ;
         taxMoney.select() ;
         return ;
       } 
        
       var prePayTax = $ID("accept.prePayTax") ;
       if(prePayTax.value.trim()!="" && isNaN(prePayTax.value.trim())){
         showMsgbox("预付税金只能为数字","red_cue") ;
         prePayTax.select() ;
         return ;
       } 
       var shuiCha = $ID("accept.shuiCha");
       if(shuiCha.value.trim()!="" && isNaN(shuiCha.value.trim())){
         showMsgbox("税差只能为数字","red_cue") ;
         shuiCha.select() ;
         return ;
       } 
       var dianFuTax = $ID("accept.dianFuTax");  
       if(dianFuTax.value.trim()!="" && isNaN(dianFuTax.value.trim())){
         showMsgbox("垫付税金只能为数字","red_cue") ;
         dianFuTax.select() ;
         return ;
       }  
       
       var ids=["accept.materiaMoney","accept.mayMoney","accept.cost","accept.serviceMoney","accept.profit","accept.tiCheng",
                "accept.tiChengRate" ,"accept.prePayMateriaMoney","accept.huiCha","accept.materiaCost","accept.shuiCha",
                "accept.dianFuMatMoney","accept.exchangeRate","accept.taxMoney","accept.prePayTax","accept.dianFuTax"]
	 
	   for(var i=0;i < ids.length ; i++){
	      if($ID(ids[i]).value.trim()==""){
	          $ID(ids[i]).value = "0" ; 
	      }
          
	      else if(ids[i]=="accept.tiChengRate"||ids[i]=="accept.profitRate"||ids[i]=="accept.exchangeRate")
	         $ID(ids[i]).value = rounddot($ID(ids[i]).value,4) ;
	      else 
	         $ID(ids[i]).value = rounddot($ID(ids[i]).value,2) ; 
	          
	   } 
	    
       document.forms["pageForm"].submit(); 
     } 
 }); 
}  
function addMoney(index){
  var moneyfra = new DivWindow("moneyfra","分段还款窗口",410,290,"","<%=request.getContextPath()%>/accept.do?method=preAddMoney&index="+index) ;
  moneyfra.addButton("确定",function(){  
  var child = window.frames[1].document ;
  var paydate = child.getElementById("payTime").value.trim() ;
  if(paydate==""){
    child.getElementById("message").innerHTML = "还款日期不能为空" ; 
    return ;
  }
  var paycount = child.getElementById("money.retCount").value.trim() ;
  if(paycount==""){
    child.getElementById("message").innerHTML = "还款金额不能为空" ;
    child.getElementById("money.retCount").focus(); 
    return ;
  }
  if(isNaN(paycount.trim())){
    child.getElementById("message").innerHTML = "还款金额只能为数字" ;
    child.getElementById("money.retCount").focus(); 
    return ;
  } 
  child.forms["pageForm"].submit(); 
  });
  moneyfra.addButton("取消");
  moneyfra.open();
} 
function delMoney(index){
  var moneyfra = new DivWindow("moneyfra","确认窗口",200,100,"","<%=request.getContextPath()%>/accept.do?method=preDeleteMoney&index="+index) ;
  moneyfra.addButton("确定",function(){  
  var child = window.frames[1].document ; 
  child.forms["pageForm"].submit(); 
  });
  moneyfra.addButton("取消");
  moneyfra.open();  
}

function addEmergence(index){
  var moneyfra = new DivWindow("moneyfra","提醒窗口",440,300,"","<%=request.getContextPath()%>/accept.do?method=preAddEmergence&index="+index) ;
  moneyfra.addButton("确定",function(){  
  var child = window.frames[1].document ;
  var EMstartTime = child.getElementById("EMstartTime").value.trim() ;
  if(EMstartTime==""){
    child.getElementById("message").innerHTML = "提醒日期不能为空" ; 
    return ;
  }
  var times = child.getElementsByName("times"); 
  var EMendTime = child.getElementById("EMendTime").value.trim() ; 
  for(var i=0 ; i < times.length ; i++){  
     if(times[i].checked==true && times[i].value=="1"){  
        child.getElementById("EMendTime").value = "" ; 
     }
     if(times[i].checked==true && times[i].value=="days" && EMendTime==""){
        child.getElementById("message").innerHTML = "结束日期不能为空" ;
        return ;
     }
  }  
  child.forms["pageForm"].submit(); 
  });
  moneyfra.addButton("取消");
  moneyfra.open();
} 

function delEmergence(index){
  var moneyfra = new DivWindow("moneyfra","确认窗口",200,100,"","<%=request.getContextPath()%>/accept.do?method=preDeleteEmergence&index="+index) ;
  moneyfra.addButton("确定",function(){  
  var child = window.frames[1].document ; 
  child.forms["pageForm"].submit(); 
  });
  moneyfra.addButton("取消");
  moneyfra.open();  
}

function editAccept(id){
  document.getElementById("method").value = "preEditAccept" ; 
  document.getElementById("id").value = id ;
  document.forms["pageForm"].submit();  
} 

function delAccept(id){  
  if((id==null || id=="") && !isSelSome("ids")){  
    showMsgbox("请选择要删除的信息","red_cue") ;
    return ;
  }   
  var moneyfra = new DivWindow("moneyfra","确认窗口",200,100,"确定要删除") ;
  moneyfra.addButton("确定",function(){
      document.getElementById("method").value = "delAccept" ; 
	  document.getElementById("id").value = id ;
	  document.forms["pageForm"].submit();
  
  }); 
  moneyfra.addButton("取消");
  moneyfra.open();   
}

function getProfit(){ 
var mayMoney = document.getElementById("accept.mayMoney").value.trim();
var cost = document.getElementById("accept.cost").value.trim();
var serviceMoney = document.getElementById("accept.serviceMoney").value.trim();
if(isNaN(mayMoney) || isNaN(cost) || isNaN(serviceMoney))
  return ;
document.getElementById("accept.profit").value = round(floatSub(floatSub(mayMoney,cost),serviceMoney)) ; 
getTiCheng();
}

function getHuiCha(){
var materiaCost = document.getElementById("accept.materiaCost").value.trim();
var prePayMateriaMoney = document.getElementById("accept.prePayMateriaMoney").value.trim();
var exchangeRate = document.getElementById("accept.exchangeRate").value.trim();
/*
if(isNaN(materiaCost)){
   showMsgbox("货款金额只能为数字","red_cue") ;
   document.getElementById("accept.materiaCost").select() ;
   return ;
 } 
 if(isNaN(prePayMateriaMoney)){
   showMsgbox("预付金额只能为数字","red_cue") ;
   document.getElementById("accept.prePayMateriaMoney").select() ;
   return ;
 } 
 if(isNaN(exchangeRate)){
   showMsgbox("汇率只能为数字","red_cue") ;
   document.getElementById("accept.exchangeRate").select() ;
   return ;
 }  
 */
 if(!isNaN(exchangeRate) && !isNaN(materiaCost) && !isNaN(prePayMateriaMoney))
    document.getElementById("accept.huiCha").value = prePayMateriaMoney-materiaCost*exchangeRate ;
 }
 
function getShuiCha(){
var taxMoney = document.getElementById("accept.taxMoney").value.trim();
var prePayTax = document.getElementById("accept.prePayTax").value.trim(); 
if(!isNaN(prePayTax) && !isNaN(taxMoney))
    document.getElementById("accept.shuiCha").value = prePayTax-taxMoney ;
}



function getTiCheng(){
   var profit = document.getElementById("accept.profit") ;
   var tiChengRate = document.getElementById("accept.tiChengRate") ;
   if(profit.value.trim()=="" || isNaN(profit.value) || isNaN(tiChengRate.value) ||tiChengRate.value.trim()==""){
       document.getElementById("accept.tiCheng").value = "" ;
       return ; 
   } 
   
   document.getElementById("accept.tiCheng").value = round(floatMul(profit.value,tiChengRate.value)/100) ;  
}

function getCust(){  
  var moneyfra = new DivWindow("moneyfra","查找客户",425,353,"","<%=request.getContextPath()%>/custom.do?method=getCust") ;
  moneyfra.addButton("取消");
  moneyfra.open();  
}

function cancel(){
  var moneyfra = new DivWindow("moneyfra","确认窗口",200,100,"确定要退出吗?") ;
  moneyfra.addButton("确定",function(){
    document.location.href = "<%=request.getContextPath()%>/accept.do?method=list" ; 
  });
  moneyfra.addButton("取消");
  moneyfra.open();  
}

function copyAccept(id){
   document.getElementById("method").value = "copyAccept" ; 
   document.getElementById("id").value = id ;
   document.forms["pageForm"].submit(); 
}

 


 










