<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
//判断是否为字符类型(数字,字母,"-")
String.prototype.isChar=function(){
	return this.match(/^[\w|-]+$/);
}
//判断是否为正数类型包含小数
String.prototype.isNum=function(){
	return this.match(/^(\d+(\.\d+)?)?$/);
}
//判断是否为正数类型包含小数
String.prototype.isDouble=function(){
	return this.match(/^[-\+]?\d+(\.\d+)?$/);
}
//判断是否为空字符串
String.prototype.isEmpty=function(){
	return this.trim()=="";
}
// 去除字符串的首尾的空格   
String.prototype.trim=function(){
   return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.toHtml=function(){
	var reg=new RegExp("\r\n","g");
  	return this.replace(reg,"<br>");
}
String.prototype.toTitle=function(){
	var retStr = this.replace(/<[BR|br]+\/?>/g,"\n");
		//retStr = retStr.replace(/<\/?.+?>/g, " ");
  	return retStr;
}
String.prototype.clHtml=function(){
	return this.replace(/<\/?.+?>/g, " ");
}
// 将字符串转换成JSON对象
String.prototype.toJson=function(){
   return eval('(' + this + ')');
}
// 获取整数类型  
String.prototype.getInt=function(){
   return parseInt(this);
}
//判断是否为Email类型
String.prototype.isEmail=function(){
   return this.match(/^\w+([-+\.]\w+)*@\w+([-\.]\w+)*\.\w+([-\.]\w+)*$/);
}
/*
//字符串转换日期
String.prototype.toDate=function(){
	var arr = null);
	var dateArr = null;	
	var timeArr = null;
	var nowDate = null;
	try{
		arr=this.split(" ");
		dateArr=arr[0].split("-");	
		if(arr[1]==null){
			timeArr="0:0:0";
		}
		else{
			timeArr=arr[1].split(":");
		}
		if(timeArr[2]==null){
			timeArr[2]=0;
		}
		else if(timeArr[1]==null){
			timeArr[1]=0;
		}
		else if(timeArr[0]==null){
			timeArr[0]=0;
		}	
		nowDate = new Date(dateArr[0],dateArr[1],dateArr[2],timeArr[0],timeArr[1],timeArr[2]);
	}
	catch{
		alert("字符串转换日期出错，Str:"+this);
	}
	return nowDate;
}
*/
//格式化货币类型
function formatCurrency(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	return (((sign)?'':'-') + num + '.' + cents);
}

//等同于document.getElementById()
function $ID(element) {
		return document.getElementById(element);		
	}
//等同于document.getElementsByName()
function $N(element) {
	return document.getElementsByName(element);		
}
//阻止事件冒泡
function stopPropagation(evt){
	var e=(evt)?evt:window.event; //判断浏览器的类型，在基于ie内核的浏览器中的使用cancelBubble
	if (window.event) { 
		e.cancelBubble=true; 
	} else { 
	//e.preventDefault(); //在基于firefox内核的浏览器中支持做法stopPropagation
		e.stopPropagation(); 
	} 
}


//文字缩进
var omissionStr=function(titleStr,charNum){
	var jishu=2790/4571;
	var charLength=0.0;
	var charShowNum=0;
	var returnStr=titleStr;
	for(var j=0;j < titleStr.length;j++){
		charShowNum++;
		if(titleStr.charAt(j)<='z'){
			charLength=charLength+jishu;
		}
		else{
			charLength = charLength+1;
		}			
		if(charLength>=charNum){
			returnStr=titleStr.substring(0,charShowNum-1)+"…";
			j=titleStr.length;
		}
	}
	return returnStr;
}
//用于超长标题缩减
var titleInit = function(titleName,charNum){
	var titles = $N(titleName); 
	if(titles==null)return;
	for(var i=0;i < titles.length;i++){
		
		if(titles[i].title!=""){
			titles[i].title=titles[i].innerHTML+"\n"+titles[i].title;
		}
		else{
			titles[i].title=titles[i].innerHTML;
		}
		titles[i].innerHTML=omissionStr(titles[i].innerHTML,charNum);
	}
}


//日期格式化
Date.prototype.format = function(format){
    var o ={
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)){
		format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
    for(var k in o){
		if(new RegExp("("+ k +")").test(format)){
			format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	}
    return format;
}

//日期比较函数  date1 大于等于date2 返回true
function datecompare(date1, date2){ 
	var d1 = new Date(date1.replace(/\-/g, "\/ ")); 
	var d2 = new Date(date2.replace(/\-/g, "\/ ")); 
	
	var flag = true; 
	
	//只填写了一端日期
	if((d1 == "NaN" && d2 != "NaN")||(d1 != "NaN" && d2 == "NaN")){	
		flag = false;
	}	
	if(flag && d1.getFullYear() > d2.getFullYear()){ 
		flag = false; 
	} 
	if(flag && d1.getFullYear() == d2.getFullYear() && d1.getMonth() > d2.getMonth()){ 
		flag = false; 
	} 
	if(flag && d1.getFullYear() == d2.getFullYear() && d1.getMonth() == d2.getMonth() && d1.getDate() > d2.getDate()){ 
		flag = false; 
	}
	return flag; 
}

//信息提示 10秒后隐藏
var inpageinfoBox=undefined;
function showMsgbox(msgStr,objId,errInput){
    if(msgStr=="" || msgStr==null )
    return ;    
	var infoBox = null;
	if(objId!=null&&objId!=""){
		infoBox = $ID(objId);
	}else{
		infoBox = $ID("inpageinfo");	
	}
	if(!infoBox){
		alert("没找到消息提示用的DIV！");
		return;
	}
	if(inpageinfoBox!=undefined){
		clearTimeout(inpageinfoBox);
	}
	//infoBox.scrollTop=-100;
	if($ID("head"))
		$ID("head").scrollIntoView();
	//infoBox.scrollTop=0;
	//alert(0);
		
	infoBox.innerHTML=msgStr;
	infoBox.style.display = "block";
	//为错误输入框添加css
	if(jQuery.isPlainObject(errInput)){
		$(errInput).addClass("error_input");
	}else{
		$("input[id=" + errInput + "]").addClass("error_input");
	}
	
	inpageinfoBox = setTimeout(function(){	
		infoBox.style.display = "none";		
		//为错误输入框取消css
		if(jQuery.isPlainObject(errInput)){
			$(errInput).removeClass("error_input");
		}else{
			$("input[id=" + errInput + "]").removeClass("error_input");
		}
		inpageinfoBox=undefined;
	},10000);
}

//信息提示 10秒后隐藏
var XyMsgboxObj=undefined;
var XyMsgbox = function(msgStr,okMsg){
	$("#Xy_MsgBox").remove();
    var $infoBox = $("<div id='Xy_MsgBox'>"+msgStr+"</div>");    
    $infoBox.css({'font-weight':'bold',
    				'padding':'4px 45px 2px 35px',
    				'top':'3px',
					'left':'450px',
					'position':'fixed',
					'color':'#ff2b2a',
					'background-color':'#ffd9da',
					'border':'1px solid #ff4800'});    
	if(okMsg==true){
		$infoBox.css({'color':'#3d80ab',
					'background-color':'#e9f2f9',
					'border':'1px solid #3d80ab'});
	}	
	$("body").append($infoBox);
	XyMsgboxObj = setTimeout(function(){	
		XyMsgboxObj=undefined;		
		$infoBox.remove();
	},10000);
}

//小数保留位数
function rounddot(c,n){ 
	return Math.round(c*Math.pow(10,n))/Math.pow(10,n) ;
} 

//取消编辑返回列表
function goback(urlStr,msgText){
	var setting={text:"退出后信息将无法保存，您确定要退出？",
				width:250,
				height:120,
				title:"系统提示",
				buttons:[{name:"确定",click:function(){
					window.location=urlStr;
					}},{name:"取消"}]}
					
	if(msgText!=null&&!msgText.isEmpty()){
		setting.text=msgText;
	}
	var gobackMsg = new DivWindow(setting);
}

function characterReduce(obj,num){
	var str = obj.innerHTML;
	alert(str);
	if(str.length>num){
		obj.innerHTML=str.substring(0,num);
		obj.title=str;
	}	
}
function isEmail(email){
	return /^\w+([-+\.]\w+)*@\w+([-\.]\w+)*\.\w+([-\.]\w+)*$/.test(email) ; }
	
function isPostCode(code){
	return /^\d{6}$/.test(code) ; }
	
function isPhone(phone){
	return /^\d{3,4}\-*\d{5,10}$/.test(phone) ; } 
	
function isMobile(mob){
	return /1\d{10,12}$/.test(mob) ; } 
	
function hasChinese(str){
	return /[\u4e00-\u9fa5]/.test(str) ; } 

//锁定表单元素，feilds 为非锁定元素字符创id，多个id之间可用任意空白符号隔开
function lockFeilds(feilds){  
    function loopLock(tags){
        for(var i=0 ; i < tags.length ; i++){ 
               if(tags[i].id!="" && feilds.indexOf(tags[i].id)!=-1)
                  continue ; 
               if(tags[i].tagName=="SELECT"){   
                    tags[i].outerHTML = '<span onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();">' 
                      +tags[i].outerHTML+'</span>' ;  
                  tags[i].style.backgroundColor = "#EEF0EF" ;
               }else{
                  tags[i].readOnly = true ; 
                  tags[i].onclick = "" ;
                  tags[i].style.backgroundColor = "#EEF0EF" ;
               } 
        }
    }
    var inputs = document.getElementsByTagName("input") ; 
    var selects = document.getElementsByTagName("select") ;
    var textareas = document.getElementsByTagName("textarea") ; 
    var imgs = document.getElementsByTagName("img") ; 
    loopLock(inputs) ; 
    loopLock(selects) ; 
    loopLock(textareas) ; 
    loopLock(imgs) ; 
} 

//数值转换为大写汉字
var DX = function (num) {
  var strOutput = "";
  var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';
  num += "00";
  var intPos = num.indexOf('.');
  if (intPos >= 0)
    num = num.substring(0, intPos) + num.substr(intPos + 1, 2);
  strUnit = strUnit.substr(strUnit.length - num.length);
  for (var i=0; i < num.length; i++)
    strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);
    return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");
};

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
	