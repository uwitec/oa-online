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
 

function isSelSome(obj){
	var selobj = document.getElementsByName(obj) ; 
	   for(var i=0 ; i < selobj.length ; i++){
	      if(selobj[i].checked)
	          return true ;
	   }
	   return false ; 
}

function isSelOne(obj){
   var count = 0 ;
   var selobj = document.getElementsByName(obj) ;
   for(var i=0 ; i < selobj.length ; i++){
      if(selobj[i].checked)
    	  count++ ;
   } 
   if(count==1) 
	   return true ; 
   else   
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
 
 /*-----------department-----------*/
 
 function addDep(){   
       var depname = document.getElementById("department.depName").value.trim() ; 
       if(depname==""){  
          document.getElementById("inpageinfo").innerHTML = "部门不能为空" ; 
          $ID("inpageinfo").style.display = "block" ;
          return ;
       } 
       var comName = document.getElementById("department.comName").value.trim() ;
       if(comName==""){  
          document.getElementById("inpageinfo").innerHTML = "所属公司不能为空" ; 
          $ID("inpageinfo").style.display = "block" ;
          return ;
       }  
       var comId = document.getElementById("department.comId").value.trim() ; 
       DepartmentDWR.hasDep(comId,depname,"",function(data){ 
          if(data==false){ 
              $ID("inpageinfo").style.display = "none" ;
              document.getElementById("inpageinfo").innerHTML = "" ;
              document.forms["pageForm"].submit(); 
          }else{
              document.getElementById("inpageinfo").innerHTML = "部门名称已经存在" ;
              $ID("inpageinfo").style.display = "block" ;
              return ;
          } 
       });  
    }   

function removeOptions(selId,stay){
  var removeobj = document.getElementById(selId)
  var c = removeobj.options.length ;
  for(var j=c-1 ; j >= 0 ; j--){  
      if(j==stay)  continue ;
      removeobj.options[j] = null ;
  }
} 
function selctCom(){  
  var parentcom = $ID("parentcom").value.trim() ;  
  removeOptions("parentdep",0) ; 
  if(parentcom==null || parentcom==""){ 
	  $ID("department.comId").value = "" ;
	  $ID("department.comName").value = "" ;
	  return ;
  } 
  $ID("department.comId").value = parentcom.split("-")[0] ;
  $ID("department.comName").value = parentcom.split("-")[1] ;
  var comId = $ID("department.comId").value.trim() ;
  DepartmentDWR.getDeparts(comId,function(data){ 
    for(var i=0 ; i < data.length ; i++){  
      var depoption=document.createElement("OPTION"); 
      depoption.text=data[i].split("-")[1]; 
      depoption.value=data[i];   
      $ID("parentdep").options.add(depoption);   
    }});  
}
//2011-7-12  
function selctCom1(){  
 
  var parentcom = $ID("parentcom").value.trim() ;  
   
  removeOptions("parentdep",0) ; 
  DepartmentDWR.getDeparts1(parentcom,function(data){ 
    for(var i=0 ; i < data.length ; i++){  
      var depoption=document.createElement("OPTION"); 
      depoption.text=data[i].depName; 
      depoption.value=data[i].id;  
      $ID("parentdep").options.add(depoption);   
    }});  
}
function selDep(){ 
  var parentdep = $ID("parentdep").value.trim() ;  
  if(parentdep==null || parentdep==""){
      $ID("department.parentDepId").value = "" ;
      $ID("department.parentDepName").value = "" ;
  }else{
     $ID("department.parentDepId").value = parentdep.split("-")[0] ;
     $ID("department.parentDepName").value = parentdep.split("-")[1] ;  
  } 
}

function delDep(){ 
	var selone = isSelSome("ids")
	if(selone==false){ 
       $ID("inpageinfo").innerHTML = "请选择部门" ;  
       $ID("inpageinfo").style.display = "block" ;
       return ;
	}  
	var msg = new DivWindow("msg","消息窗口",200,100,"确定要删除吗"); 	
    msg.addButton("确定",function(){
    	$ID("method").value= "delete" ;
    	document.forms["pageForm"].action = "<%=request.getContextPath()%>/department.do" ;
    	document.forms["pageForm"].submit() ; 
     }) ;
    msg.addButton("取消") ;
    msg.open(); 
}

function  editDep(){ 
       var depname = document.getElementById("department.depName").value.trim() ; 
       if(depname==""){  
          document.getElementById("inpageinfo").innerHTML = "部门名称不能为空" ; 
          $ID("inpageinfo").style.display = "block" ;
          return ;
       } 
       var comName = document.getElementById("department.comName").value.trim() ; 
       if(comName==""){  
          document.getElementById("inpageinfo").innerHTML = "所属公司不能为空" ; 
          $ID("inpageinfo").style.display = "block" ;
          return ;
       } 
       var comId = document.getElementById("department.comId").value.trim() ; 
       var depId = document.getElementById("department.id").value.trim() ; 
       DepartmentDWR.hasDep(comId,depname,depId,function(data){ 
          if(data==false){ 
              $ID("inpageinfo").style.display = "none" ;
              document.getElementById("inpageinfo").innerHTML = "" ; 
       		  document.forms["pageForm"].submit();
          }else{
              document.getElementById("inpageinfo").innerHTML = "部门名称已经存在" ;
              $ID("inpageinfo").style.display = "block" ;
              return ;
          } 
       });  
    } 


