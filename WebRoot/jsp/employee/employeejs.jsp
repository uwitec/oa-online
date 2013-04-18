<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

function selOption(obj,val,tv){
  var options = document.getElementById(obj).options ; 
  for(var i=0 ; i < options.length ; i++){
    if(tv=="text" && val==options[i].text){ 
        document.getElementById(obj).selectedIndex = i ;
        return true ; 
    }
    if(tv=="value" && val==options[i].value){
        document.getElementById(obj).selectedIndex = i ;
        return true ; 
    } 
  } 
  return false ;
}


function setClassAtt(obj,showdiv){
  var items = ['item_1','item_2','item_3','item_4'] ;
  var divs = ['eminfo','eminfo_edu','eminfo_work','eminfo_family'] ;
  for(var i=0 ;i<4 ;i++){ 
    
    if(obj==items[i]){
       document.getElementById(items[i]).className = "after" ;  
    }else
      document.getElementById(items[i]).className = "before" ;   
  }  
  for(var i=0 ;i<4 ;i++){    
    if(showdiv==divs[i]){   
      document.getElementById(divs[i]).style.display = "block" ;  
    }
    else
      document.getElementById(divs[i]).style.display = "none" ;
  }  
}



function search(){
document.forms['pageForm'].action = "<%=request.getContextPath()%>/employee.do" ; 
document.forms['pageForm'].submit();
} 

function removeOptions(selId,stay){
  var removeobj = document.getElementById(selId)
  var c = removeobj.options.length ;
  for(var j=c-1 ; j >= 0 ; j--){  
      if(j==stay)  continue ;
      removeobj.options[j] = null ;
  }
}

function selctEmCom(){
  var parentcom = $ID("parentcom").value.trim() ; 
  removeOptions("parentdep",0) ; 
  if(parentcom==null || parentcom=="")
     return ;
  $ID("basic.companyId").value = parentcom.split("---")[0] ;
  $ID("basic.companyName").value = parentcom.split("---")[1] ;
  var comId = $ID("basic.companyId").value.trim() ;
  DepartmentDWR.getDeparts(comId,function(data){ 
    for(var i=0 ; i < data.length ; i++){  
      var depoption=document.createElement("OPTION"); 
      depoption.text=data[i].split("---")[1]; 
      depoption.value=data[i];   
      $ID("parentdep").options.add(depoption);   
    }}); 
}

function selEmDep(){ 
  var parentdep = $ID("parentdep").value.trim() ; 
  if(parentdep==null || parentdep==""){
      $ID("basic.depId").value = "" ;
      $ID("basic.depName").value = "" ;
  }else{
     $ID("basic.depId").value = parentdep.split("---")[0] ;
     $ID("basic.depName").value = parentdep.split("---")[1] ;  
  } 
}

//判断浏览器版本 
function getExplorer(){ 
        var ua = navigator.userAgent.toLowerCase();
        if (window.ActiveXObject) 
            return  "ie" ;
        else if (document.getBoxObjectFor)
           return "firefox"  ;
        else if (window.MessageEvent && !document.getBoxObjectFor)
            return "chrome" ;
        else if (window.opera)
            return "opera" ;
        else if (window.openDatabase)
            return "safari" ; 
}

//图片预览  
function previwe(myfile,mypic,msg){  
var dFile = document.getElementById(myfile); 
var dImg = document.getElementById(mypic);     
 if(!(/(.JPG|.jpg|.GIF.gif|.PNG|.png|.BMP|.bmp)$/.test(dFile.value))) {  
   document.getElementById(msg).innerHTML = "请选择图片文件" ; 
   dImg.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src ="<%=request.getContextPath()%>/images/default.bmp"; 
   dImg.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dFile.value;  
   return;
 }   
 document.getElementById(msg).innerHTML = "" ;
 document.getElementById("headpicpath").value = dFile.value;  
 if(getExplorer()=="ie"){   
     dImg.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dFile.value;  
 }else if(dFile.files){ 
     dImg.src = dFile.files[0].getAsDataURL();
 }
}

function cancel(){
    var msgwin = new DivWindow("msgwin","消息窗口",200,100,"确定要退出吗"); 	
    msgwin.addButton("确定",function(){ 
      document.location.href = "<%=request.getContextPath()%>/employee.do?method=cancel" ;
    }) ;
    msgwin.addButton("取消") ;
    msgwin.open();    
}

function addCheck(id){
 var code = $ID("basic.employeeCode") ; 
 if(code.value.trim()==""){
   $ID("inpageinfo").innerHTML = "员工编码不能为空" ;
   $ID("inpageinfo").style.display = "block" ;
   code.select() ;
   return ;
 }
 if(/[\u4e00-\u9fa5]/.test(code.value.trim())){
   $ID("inpageinfo").innerHTML = "员工编码不能含有中文" ;
   $ID("inpageinfo").style.display = "block" ;
   code.select() ;
   return ; 
 }
 EmployeeDWR.hasEmployee(code.value.trim(),id,function(data){ 
    if(data==true){
       $ID("inpageinfo").innerHTML = "员工编码已经存在" ;
       $ID("inpageinfo").style.display = "block" ;
       code.select() ;
       return ; 
    }else{
       var name = $ID("basic.name") ; 
       if(name.value.trim()==""){
          $ID("inpageinfo").innerHTML = "员工姓名不能为空" ;
          $ID("inpageinfo").style.display = "block" ;
          name.select() ;
          return ;
       } 
       
       /*
       var enName = $ID("basic.enName") ; 
       if(enName.value.trim()!="" && hasChinese(enName.value.trim())){
          $ID("inpageinfo").innerHTML = "英文名不能含有中文" ;
          enName.select() ;
          return ;
       } 
       var company = $ID("parentcom") ; 
       if(company.value.trim()==""){
          $ID("inpageinfo").innerHTML = "所属公司不能为空" ; 
          return ;
       }
       var depName = $ID("parentdep") ; 
       if(depName.value.trim()==""){
          $ID("inpageinfo").innerHTML = "所属部门不能为空" ; 
          return ;
       }
       var state = $ID("basic.employeeState") ; 
       if(state.value.trim()==""){
          $ID("inpageinfo").innerHTML = "状态不能为空" ; 
          return ;
       } 
       
       var InWorkDate = $ID("InWorkDate") ; 
       if(InWorkDate.value.trim()==""){
          $ID("inpageinfo").innerHTML = "入职时间不能为空" ; 
          InWorkDate.select() ;
          return ;
       } 
       var realWorkDate = $ID("realWorkDate") ; 
       if(realWorkDate.value.trim()==""){
          $ID("inpageinfo").innerHTML = "转正时间不能为空" ; 
          realWorkDate.select() ;
          return ;
       } 
       var startWrokDate = $ID("startWrokDate") ; 
       if(startWrokDate.value.trim()==""){
          $ID("inpageinfo").innerHTML = "参加工作时间不能为空" ; 
          startWrokDate.select() ;
          return ;
       } 
       var degree = $ID("basic.degree") ; 
       if(degree.value.trim()==""){
          $ID("inpageinfo").innerHTML = "学历不能为空" ; 
          degree.select() ;
          return ;
       }
       var university = $ID("basic.university") ; 
       if(university.value.trim()==""){
          $ID("inpageinfo").innerHTML = "毕业院校不能为空" ; 
          university.select() ;
          return ;
       }
       var contractLength = $ID("basic.contractLength") ; 
       if(contractLength.value.trim()==""){
          $ID("inpageinfo").innerHTML = "合同期限不能为空" ; 
          contractLength.select() ;
          return ;
       }
       var insureCard = $ID("basic.insureCard") ; 
       if(insureCard.value.trim()!="" && hasChinese(insureCard.value.trim())){
          $ID("inpageinfo").innerHTML = "社保卡不能含有中文" ;
          insureCard.select() ;
          return ;
       } 
       var mobile = $ID("basic.mobile") ; 
       if(mobile.value.trim()!="" && hasChinese(mobile.value.trim())){
          $ID("inpageinfo").innerHTML = "手机不能含有中文" ;
          mobile.select() ;
          return ;
       } 
       var phone = $ID("basic.phone") ; 
       if(phone.value.trim()!="" && hasChinese(phone.value.trim())){
          $ID("inpageinfo").innerHTML = "电话不能含有中文" ;
          phone.select() ;
          return ;
       } 
       var fax = $ID("basic.fax") ; 
       if(fax.value.trim()!="" && hasChinese(fax.value.trim())){
          $ID("inpageinfo").innerHTML = "传真不能含有中文" ;
          fax.select() ;
          return ;
       } 
       var email = $ID("basic.email") ; 
       if(email.value.trim()!="" && hasChinese(email.value.trim())){
          $ID("inpageinfo").innerHTML = "Email不能含有中文" ;
          email.select() ;
          return ;
       } 
       var msn = $ID("basic.msn") ; 
       if(msn.value.trim()!="" && hasChinese(msn.value.trim())){
          $ID("inpageinfo").innerHTML = "MSN不能含有中文" ;
          msn.select() ;
          return ;
       } 
       var qq = $ID("basic.qq") ; 
       if(qq.value.trim()!="" && hasChinese(qq.value.trim())){
          $ID("inpageinfo").innerHTML = "QQ不能含有中文" ;
          qq.select() ;
          return ;
       } 
       var endDate = $ID("endDate") ; 
       if(endDate.value.trim()==""){
          $ID("inpageinfo").innerHTML = "合同到期时间不能为空" ; 
          endDate.select() ;
          return ;
       }
       var birthday = $ID("birthday") ; 
       if(birthday.value.trim()==""){
          $ID("inpageinfo").innerHTML = "生日不能为空" ; 
          birthday.select() ;
          return ;
       }
       var nomen = $ID("basic.nomen") ; 
       if(nomen.value.trim()==""){
          $ID("inpageinfo").innerHTML = "名族不能为空" ;  
          nomen.select() ;
          return ;
       }
       var politics = $ID("basic.politics") ; 
       if(politics.value.trim()==""){
          $ID("inpageinfo").innerHTML = "政治面貌不能为空" ;  
          politics.select() ;
          return ;
       }
       var idcard = $ID("basic.idcard") ; 
       if(idcard.value.trim()==""){
          $ID("inpageinfo").innerHTML = "身份证不能为空" ;  
          idcard.select() ;
          return ;
       } 
       if(!/^\d\d+$/.test(idcard.value.trim()) && !/^\d\d+[a-zA-Z]+$/.test(idcard.value.trim()) ){
          $ID("inpageinfo").innerHTML = "身份证只能为数字或字母" ;  
          idcard.select() ;
          return ;
       }  
       var livePlace = $ID("basic.livePlace") ; 
       if(livePlace.value.trim()==""){
          $ID("inpageinfo").innerHTML = "居住地地址不能为空" ;  
          livePlace.select() ;
          return ;
       }
       var livePhone = $ID("basic.livePhone") ; 
       if(livePhone.value.trim()==""){
          $ID("inpageinfo").innerHTML = "居住地电话不能为空" ;  
          livePhone.select() ;
          return ;
       }
       if(!isPhone(livePhone.value.trim()) && !isMobile(livePhone.value.trim())){
          $ID("inpageinfo").innerHTML = "居住地电话格式错误" ;  
          livePhone.select() ;
          return ;
       }
       
        var identityPlace = $ID("basic.identityPlace") ; 
       if(identityPlace.value.trim()==""){
          $ID("inpageinfo").innerHTML = "户口所在地址不能为空" ;  
          identityPlace.select() ;
          return ;
       }
       var identityPhone = $ID("basic.identityPhone") ; 
       if(identityPhone.value.trim()==""){
          $ID("inpageinfo").innerHTML = "户口所在地电话不能为空" ;  
          identityPhone.select() ;
          return ;
       }
       if(!isPhone(identityPhone.value.trim())&&!isMobile(identityPhone.value.trim())){
          $ID("inpageinfo").innerHTML = "户口所在地电话格式错误" ;  
          identityPhone.select() ;
          return ;
       }
       var headpic = $ID("headpic") ; 
       if($ID("headpicpath").value=="" && headpic.value.trim()==""){
          $ID("inpageinfo").innerHTML = "头像不能为空" ;  
          headpic.select() ;
          return ;
       }
        
        
       if(headpic.value.trim()!="" && !/(.jpg|.bmp|.gif|.png)$/.test(headpic.value.trim()) ){
          $ID("inpageinfo").innerHTML = "头像必须为图片文件" ;  
          headpic.select() ;
          return ;
       }  
       
       var emergencyPerson = $ID("basic.emergencyPerson") ; 
       if(emergencyPerson.value.trim()==""){
          $ID("inpageinfo").innerHTML = "紧急联系人不能为空" ;  
          emergencyPerson.select() ;
          return ;
       }
       var emergencyPhone = $ID("basic.emergencyPhone") ; 
       if(emergencyPhone.value.trim()==""){
          $ID("inpageinfo").innerHTML = "紧急联系人电话不能为空" ;  
          emergencyPhone.select() ;
          return ;
       }
       var relation = $ID("basic.relation") ; 
       if(relation.value.trim()==""){
          $ID("inpageinfo").innerHTML = "紧急联系人与本人关系不能为空" ;  
          relation.select() ;
          return ;
       }
       */
       
       $ID("inpageinfo").style.display = "none" ;
       document.forms["pageForm"].submit() ;
    } 
 }); 
}
 
function addEducation(){
   var startTime = document.forms["pageForm_edu"].startTime  ; 
   if(startTime.value.trim()==""){
      $ID("inpageinfo").innerHTML = "起始时间不能为空" ;  
       $ID("inpageinfo").style.display = "block" ;
      startTime.select() ;
      return ;
   }
   var endTime = document.forms["pageForm_edu"].endTime  ; 
   if(endTime.value.trim()==""){
      $ID("inpageinfo").innerHTML = "结束时间不能为空" ;  
       $ID("inpageinfo").style.display = "block" ;
      endTime.select() ;
      return ;
   }
   var university = $ID("education.university") ; 
   if(university.value.trim()==""){
      $ID("inpageinfo").innerHTML = "院校名称不能为空" ;  
       $ID("inpageinfo").style.display = "block" ;
      university.select() ;
      return ;
   }
   var profession = $ID("education.profession") ; 
   if(profession.value.trim()==""){
      $ID("inpageinfo").innerHTML = "专业不能为空" ;  
       $ID("inpageinfo").style.display = "block" ;
      profession.select() ;
      return ;
   }
    
   
   if($ID("submethod").value!="edit")
       $ID("submethod").value = "" ;  
       $ID("inpageinfo").style.display = "none" ;
   document.forms["pageForm_edu"].submit() ;
} 

function delTempEducation(index,id){
    var msgwin = new DivWindow("msgwin","消息窗口",200,100,"确定要删除吗"); 	
    msgwin.addButton("确定",function(){  
        $ID("index").value = index ;
        $ID("method").value = "delTempEducation" ; 
        $ID("education.id").value = id ; 
        document.forms["pageForm_edu"].submit() ;
    }) ;
    msgwin.addButton("取消") ;
    msgwin.open();  
} 

function editTempEducation(index){ 
   $ID("index").value = index ;
   $ID("method").value = "preEditTempEducation" ;   
   document.forms["pageForm_edu"].submit() ; 
}

function eduPoint(point){ 
   var startTime = $ID("startTime").value.trim() ;  
   var endTime = $ID("endTime").value.trim() ;  
   var university = $ID("education.university").value.trim() ;  
   var profession = $ID("education.profession").value.trim() ;  
   var provePerson = $ID("education.provePerson").value.trim() ;  
   var phone = $ID("education.phone").value.trim() ; 
   if(startTime!="" ||endTime!="" ||university!="" ||profession!="" ||provePerson!="" ||phone!=""){
      $ID("inpageinfo").innerHTML = "请将信息补充完整并保存" ; 
       $ID("inpageinfo").style.display = "block" ;  
      return ;
   }
   $ID("submethod").value = point ;   
   document.forms["pageForm"].submit() ;  
} 


function workPoint(point){ 
   var startTime = $ID("startTime").value.trim() ;  
   var endTime = $ID("endTime").value.trim() ;  
   var company = $ID("work.company").value.trim() ;  
   var job = $ID("work.job").value.trim() ;  
   var provePerson = $ID("work.provePerson").value.trim() ;  
   var phone = $ID("work.phone").value.trim() ;  
   if(startTime!="" ||endTime!="" ||company!="" ||job!="" ||provePerson!="" ||phone!=""){
      $ID("inpageinfo").innerHTML = "请将信息补充完整并保存" ;  
       $ID("inpageinfo").style.display = "block" ; 
      return ;
   }
   $ID("submethod").value = point ;   
   document.forms["pageForm"].submit() ;  
}


function familyPoint(point){ 
   var name = $ID("family.name").value.trim() ;  
   var age = $ID("family.age").value.trim() ;   
   var workCompany = $ID("family.workCompany").value.trim() ;  
   var relationship = $ID("family.relationship").value.trim() ;  
   var phone = $ID("family.phone").value.trim() ;    
   if(name!="" ||age!="" ||workCompany!="" ||relationship!="" ||phone!=""){
      $ID("inpageinfo").innerHTML = "请将信息补充完整并保存" ; 
       $ID("inpageinfo").style.display = "block" ;  
      return ;
   }
   $ID("submethod").value = point ;   
   document.forms["pageForm"].submit() ;  
}


function addWork(){ 
   var startTime = document.forms["pageForm_work"].startTime; 
   if(startTime.value.trim()==""){
      $ID("inpageinfo").innerHTML = "起始时间不能为空" ;  
       $ID("inpageinfo").style.display = "block" ;
      startTime.select() ;
      return ;
   }
   var endTime =document.forms["pageForm_work"].endTime  ; 
   if(endTime.value.trim()==""){
      $ID("inpageinfo").innerHTML = "结束时间不能为空" ;  
       $ID("inpageinfo").style.display = "block" ;
      endTime.select() ;
      return ;
   }
   var company =$ID('work.company')   ; 
   if(company.value.trim()==""){
      $ID("inpageinfo").innerHTML = "公司名称不能为空" ;  
       $ID("inpageinfo").style.display = "block" ;
      company.select() ;
      return ;
   }
   var job =$ID('work.job')    ; 
   if(job.value.trim()==""){
      $ID("inpageinfo").innerHTML = "职位不能为空" ;  
       $ID("inpageinfo").style.display = "block" ;
      job.select() ;
      return ;
   } 
   
   if(document.forms["pageForm_work"].submethod.value!="edit")
      document.forms["pageForm_work"].submethod.value = "" ; 
   
   $ID("inpageinfo").style.display = "none" ;
   document.forms["pageForm_work"].submit() ;
}


function delTempWork(index,id){
    var msgwin = new DivWindow("msgwin","消息窗口",200,100,"确定要删除吗"); 	
    msgwin.addButton("确定",function(){  
        document.forms["pageForm_work"].index.value = index ;
        document.forms["pageForm_work"].method.value = "delTempWork" ; 
        document.forms["pageForm_work"].submit() ;
    }) ;
    msgwin.addButton("取消") ;
    msgwin.open();  
} 

function editTempWork(index){   
   document.forms["pageForm_work"].index.value = index ;
   document.forms["pageForm_work"].method.value = "preEditTempWork" ; 
   document.forms["pageForm_work"].submit() ;
}

function workNext(){ 
   $ID("submethod").value = "next" ;   
   document.forms["pageForm"].submit() ;  
}

//-------------------------------------------------------

function addFamily(){ 
   var name = $ID("family.name") ; 
   if(name.value.trim()==""){
      $ID("inpageinfo").innerHTML = "家庭成员姓名不能为空" ;  
       $ID("inpageinfo").style.display = "block" ;
      name.select() ;
      return ;
   }
   
    /*
   var birthday = $ID("family.birthday") ; 
   if(birthday.value.trim()==""){
      $ID("inpageinfo").innerHTML = "出生日期不能为空" ;  
       $ID("inpageinfo").style.display = "block" ; 
      return ;
   } */
   
   var workCompany = $ID("family.workCompany") ; 
   if(workCompany.value.trim()==""){
      $ID("inpageinfo").innerHTML = "工作单位不能为空" ;  
       $ID("inpageinfo").style.display = "block" ;
      workCompany.select() ;
      return ;
   }
   var relationship = $ID("family.relationship") ; 
   if(relationship.value.trim()==""){
      $ID("inpageinfo").innerHTML = "与本人关系不能为空" ; 
       $ID("inpageinfo").style.display = "block" ; 
      relationship.select() ;
      return ;
   }
    
   if($ID("submethod").value!="edit")
       $ID("submethod").value = "" ; 
       
       $ID("inpageinfo").style.display = "none" ;
   document.forms["pageForm_family"].submit() ;
}

function delTempFamily(index){
    var msgwin = new DivWindow("msgwin","消息窗口",200,100,"确定要删除吗"); 	
    msgwin.addButton("确定",function(){  
        document.forms["pageForm_family"].index.value = index ;
        document.forms["pageForm_family"].method.value = "delTempFamily" ; 
        document.forms["pageForm_family"].submit() ;
    }) ;
    msgwin.addButton("取消") ;
    msgwin.open();  
} 

function editTempFamily(index){   
   document.forms["pageForm_family"].index.value = index ;
   document.forms["pageForm_family"].method.value = "preEditTempFamily" ; 
   document.forms["pageForm_family"].submit() ;
}

function complete(){
   var name = $ID("family.name").value.trim() ;  
   var age = $ID("family.age").value.trim() ;   
   var workCompany = $ID("family.workCompany").value.trim() ;  
   var relationship = $ID("family.relationship").value.trim() ;  
   var phone = $ID("family.phone").value.trim() ;    
   if(name!="" ||age!="" ||workCompany!="" ||relationship!="" ||phone!=""){
      $ID("inpageinfo").innerHTML = "请将信息补充完整并保存" ;   
       $ID("inpageinfo").style.display = "block" ;
      return ;
   }
   $ID("method").value = "complete" ;   
   $ID("inpageinfo").style.display = "none" ;
   document.forms["pageForm"].submit() ;  
}

function editEmployee(id){
  $ID("id").value = id ; 
  $ID("method").value = "preEditEmployee" ;
  document.forms["pageForm"].submit(); 
}

function delEmployee(id){
    var msg = new DivWindow("msg","消息窗口",200,100,"确定要删除吗"); 	
    msg.addButton("确定",function(){
    	$ID("id").value = id ; 
        $ID("method").value = "delEmployee" ;
        document.forms["pageForm"].submit();  
     }) ;
    msg.addButton("取消") ;
    msg.open();  
    
}

function delSomeEm(){
    var selone = isSelSome("emids") 
	if(selone==false){ 
       $ID("inpageinfo").innerHTML = "请选择员工" ;  
       $ID("inpageinfo").style.display = "block" ;
       return ;
	}  
	$ID("inpageinfo").style.display = "none" ;
	var msg = new DivWindow("msg","消息窗口",200,100,"确定要删除吗"); 	
    msg.addButton("确定",function(){
    	$ID("method").value= "delEmployee" ;
    	document.forms["pageForm"].action = "<%=request.getContextPath()%>/employee.do" ;  
    	document.forms["pageForm"].submit() ; 
     }) ;
    msg.addButton("取消") ;
    msg.open();  
}
 
function writeBirthday(){
   var idcard = $ID("basic.idcard").value.trim() ;  
   if(idcard==null || idcard=="" || idcard.length<15) return  ;
   if(idcard.length==15)
       $ID("birthday").value =  "19"+idcard.substring(6,8)+"-"+idcard.substring(8,10)+"-"+idcard.substring(10,12);  
   else if(idcard.length==18)
       $ID("birthday").value =  idcard.substring(6,10)+"-"+idcard.substring(10,12)+"-"+idcard.substring(12,14); 
}

function writeEndDate(){ 
   var contractLength = $ID("basic.contractLength").value.trim() ; 
   if(contractLength==null || contractLength=="")
      return  ;
   var inWorkDate = $ID("inWorkDate").value.trim() ;  
   if(isNaN(contractLength)) 
      return ;
   else{
       $ID("endDate").value  = (1*inWorkDate.substring(0,4)+Math.floor(1*contractLength))+inWorkDate.substring(4) 
   } 
}












