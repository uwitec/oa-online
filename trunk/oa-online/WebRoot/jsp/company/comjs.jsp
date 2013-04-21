<%@ page language="java" pageEncoding="utf-8"%> 
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
		document.getElementById("orderByBame").value=obj.id;
		document.forms['pageForm'].pageNo.value=1;
		document.forms['pageForm'].submit();
	}

	//初始化排序信息
	function orderByInit() {
		var orderByBame = document.getElementById("orderByBame").value;
		var orderByOrder = document.getElementById("orderByOrder").value;
		if(orderByBame.length<1) {
			return;
		}
		var orderObj = document.getElementById(orderByBame);
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

function addcom(){ 
var fullname = $ID("company.fullName") ;  
if(fullname.value.trim()==""){   
  $ID("inpageinfo").innerHTML = "公司全称不能为空" ;
  $ID("inpageinfo").style.display = "block";
  fullname.select();
  return ;
}

   
CompanyDWR.hasCompany(fullname.value.trim(),"",function(data){
 if(data==true){ 
    $ID("inpageinfo").innerHTML = "公司全称已经存在" ;
  $ID("inpageinfo").style.display = "block";
    return ; 
 }else{
var address = $ID("company.address") ;
if(address.value.trim()==""){ 
  $ID("inpageinfo").innerHTML = "公司地址不能为空" ;
  $ID("inpageinfo").style.display = "block";
  address.select();
  return ;
}
var postcode = $ID("company.postcode") ;
if(postcode.value.trim()!="" && !isPostCode(postcode.value.trim())){ 
  $ID("inpageinfo").innerHTML = "邮编格式错误" ;
  $ID("inpageinfo").style.display = "block";
  postcode.select();
  return ;
}
/*
var phone = $ID("company.phone") ;
if(phone.value.trim()!="" && !isPhone(phone.value.trim())){ 
  $ID("inpageinfo").innerHTML = "电话格式错误" ;
  $ID("inpageinfo").style.display = "block";
  phone.select();
  return ;
}
var mobile = $ID("company.mobile") ;
if(mobile.value.trim()!="" && !isMobile(mobile.value.trim())){ 
  $ID("inpageinfo").innerHTML = "手机号格式错误" ;
  $ID("inpageinfo").style.display = "block";
  mobile.select();
  return ;
}*/
var email = $ID("company.email") ;
if(email.value.trim()!="" && !isEmail(email.value.trim())){ 
  $ID("inpageinfo").innerHTML = "email格式错误" ;
  $ID("inpageinfo").style.display = "block";
  email.select();
  return ;
}
var businessNo = $ID("company.businessNo") ;
if(businessNo.value.trim()!="" && hasChinese(businessNo.value.trim())){ 
  $ID("inpageinfo").innerHTML = "营业执照不能有中文" ;
  $ID("inpageinfo").style.display = "block";
  businessNo.select();
  return ;
}
var nationTaxNo = $ID("company.nationTaxNo") ;
if(nationTaxNo.value.trim()!="" && hasChinese(nationTaxNo.value.trim())){ 
  $ID("inpageinfo").innerHTML = "国税证不能有中文" ;
  $ID("inpageinfo").style.display = "block";
  nationTaxNo.select();
  return ;
}
var landTaxNo = $ID("company.landTaxNo") ;
if(landTaxNo.value.trim()!="" && hasChinese(landTaxNo.value.trim())){ 
  $ID("inpageinfo").innerHTML = "地税证不能有中文" ;
  $ID("inpageinfo").style.display = "block";
  landTaxNo.select();
  return ;
}
var cnnumber = $ID("company.cnnumber") ;
if(cnnumber.value.trim()!="" && hasChinese(cnnumber.value.trim())){ 
  $ID("inpageinfo").innerHTML = "海关编码不能有中文" ;
  $ID("inpageinfo").style.display = "block";
  cnnumber.select();
  return ;
}
var checkNo = $ID("company.checkNo") ;
if(checkNo.value.trim()!="" && hasChinese(checkNo.value.trim())){ 
  $ID("inpageinfo").innerHTML = "报检代码不能有中文" ;
  $ID("inpageinfo").style.display = "block";
  checkNo.select();
  return ;
}

$ID("inpageinfo").style.display = "none";
document.forms["pageForm"].submit() ;

} });}

function editcom(id){  
var fullname = $ID("company.fullName") ; 
if(fullname.value.trim()==""){  
  $ID("inpageinfo").innerHTML = "公司全称不能为空" ;
  $ID("inpageinfo").style.display = "block";
  fullname.select();
  return ;
}
CompanyDWR.hasCompany(fullname.value.trim(),id,function(data){
 if(data==true){ 
    $ID("inpageinfo").innerHTML = "公司全称已经存在" ;
  $ID("inpageinfo").style.display = "block";
    return ; 
 }else{
var address = $ID("company.address") ;
if(address.value.trim()==""){ 
  $ID("inpageinfo").innerHTML = "公司地址不能为空" ;
  $ID("inpageinfo").style.display = "block";
  address.select();
  return ;
}
var postcode = $ID("company.postcode") ;
if(postcode.value.trim()!="" && !isPostCode(postcode.value.trim())){ 
  $ID("inpageinfo").innerHTML = "邮编格式错误" ;
  $ID("inpageinfo").style.display = "block";
  postcode.select();
  return ;
}
/*
var phone = $ID("company.phone") ;
if(phone.value.trim()!="" && !isPhone(phone.value.trim())){ 
  $ID("inpageinfo").innerHTML = "电话格式错误" ;
  $ID("inpageinfo").style.display = "block";
  phone.select();
  return ;
}
var mobile = $ID("company.mobile") ;
if(mobile.value.trim()!="" && !isMobile(mobile.value.trim())){ 
  $ID("inpageinfo").innerHTML = "手机号格式错误" ;
  $ID("inpageinfo").style.display = "block";
  mobile.select();
  return ;
}
*/
var email = $ID("company.email") ;
if(email.value.trim()!="" && !isEmail(email.value.trim())){ 
  $ID("inpageinfo").innerHTML = "email格式错误" ;
  $ID("inpageinfo").style.display = "block";
  email.select();
  return ;
}
var businessNo = $ID("company.businessNo") ;
if(businessNo.value.trim()!="" && hasChinese(businessNo.value.trim())){ 
  $ID("inpageinfo").innerHTML = "营业执照不能有中文" ;
  $ID("inpageinfo").style.display = "block";
  businessNo.select();
  return ;
}
var nationTaxNo = $ID("company.nationTaxNo") ;
if(nationTaxNo.value.trim()!="" && hasChinese(nationTaxNo.value.trim())){ 
  $ID("inpageinfo").innerHTML = "国税证不能有中文" ;
  $ID("inpageinfo").style.display = "block";
  nationTaxNo.select();
  return ;
}
var landTaxNo = $ID("company.landTaxNo") ;
if(landTaxNo.value.trim()!="" && hasChinese(landTaxNo.value.trim())){ 
  $ID("inpageinfo").innerHTML = "地税证不能有中文" ;
  $ID("inpageinfo").style.display = "block";
  landTaxNo.select();
  return ;
}
var cnnumber = $ID("company.cnnumber") ;
if(cnnumber.value.trim()!="" && hasChinese(cnnumber.value.trim())){ 
  $ID("inpageinfo").innerHTML = "海关编码不能有中文" ;
  $ID("inpageinfo").style.display = "block";
  cnnumber.select();
  return ;
}
var checkNo = $ID("company.checkNo") ;
if(checkNo.value.trim()!="" && hasChinese(checkNo.value.trim())){ 
  $ID("inpageinfo").innerHTML = "报检代码不能有中文" ;
  $ID("inpageinfo").style.display = "block";
  checkNo.select();
  return ;
}

$ID("inpageinfo").style.display = "none";
document.forms["pageForm"].submit() ;

} });}

function selParentCom(){
  var parent = $ID("parentCom").value.trim() ;
  if(parent!=""){
     $ID("company.parentId").value = parent.split("-")[0] ;
     $ID("company.parentName").value = parent.split("-")[1] ; 
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

function clearSel(obj){
	var selobj = document.getElementsByName(obj) ; 
	   for(var i=0 ; i < selobj.length ; i++){
	      selobj[i].checked = false ; 
	   } 
}

function delSome(){ 
 if(!isSelSome("ids")){  
    $ID("inpageinfo").innerHTML = "请选择要删除的信息" ; 
    $ID("inpageinfo").style.display = "block" ;
    return ;
 }  
 $ID("inpageinfo").innerHTML = "" ; 
 var msgwin = new DivWindow("msgwin","消息窗口",200,100,"确定要删除吗"); 	
    msgwin.addButton("确定",function(){
    	$ID("method").value= "delete" ; 
    	document.forms["pageForm"].submit() ;
    }) ;
    msgwin.addButton("取消") ;
    msgwin.open();   
}


function delOne(id){    
 $ID("id").value= id ; 
 clearSel("ids")
 var msgwin = new DivWindow("msgwin","消息窗口",200,100,"确定要删除吗"); 	
    msgwin.addButton("确定",function(){
    	$ID("method").value= "delete" ; 
    	document.forms["pageForm"].submit() ;
    }) ;
    msgwin.addButton("取消") ;
    msgwin.open();   
}

function edit(id){    
    $ID("id").value= id ;
    $ID("method").value= "preEdit" ; 
    document.forms["pageForm"].submit() ;
 }
