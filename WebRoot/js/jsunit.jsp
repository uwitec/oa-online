<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

//地址选择框
function selmoney(obj){
  var selmoney = new DivWindow("selmoney","地址选择窗口",400,300,"","<%=request.getContextPath() %>/moneyType.do?method=selList&selobj="+obj); 	
  selmoney.addButton("取消") ;
  selmoney.open();   
}   

//消息窗口
function messageWindow(msg,obj){ 
  var msgwin = new DivWindow("proEdit","消息窗口",200,100,msg); 	
  msgwin.addButton("确定","",obj.id) ;
  msgwin.open(); 
} 

//消息窗口
function msgframe(msg){ 
  var msgwin = new DivWindow("msgwin","消息窗口",200,100,msg); 	
  msgwin.addButton("确定") ;
  msgwin.open(); 
}  

//客户选择框
function selcustmoer(obj){
  var wincustmoer = new DivWindow("wincustmoer","客户选择窗口",517,430,"","<%=request.getContextPath() %>/customer.do?method=list&sel=yes&obj="+obj); 	
  wincustmoer.addButton("取消",function(data){ 
  if(document.getElementById("stockInInfo.partNum")!=null)
      document.getElementById("stockInInfo.partNum").focus();
  if(document.getElementById("pnpInInfo.goodsCode")!=null)
      document.getElementById("pnpInInfo.goodsCode").focus();
  closeDivWindow("wincustmoer") ; 
  }) ;
  wincustmoer.open();   
}  

//客户添加窗口
function  addcustom(){ 
  var addcustom = new DivWindow("addcustom","客户添加窗口",400,200,"","<%=request.getContextPath()%>/customer.do?method=load"); 	
  addcustom.open();  
} 

function hasMoney(money,id){  
  if(money=="" || money==null)  return ;
  MoneyType.validate(money,money,"",function(data){
		   if(data!=true)
		      document.getElementById(id).style.color ="red" ; 
		   }) ;  
} 

function hasCustomer(name,id){  
  if(name=="" || name==null)  return ;
  CustomerValidate.hasCustomer(name,function(data){
		   if(data!=true)
		      document.getElementById(id).style.color ="red" ; 
		   }) ;  
}


  













