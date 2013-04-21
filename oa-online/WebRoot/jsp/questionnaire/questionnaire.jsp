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
		document.getElementById("orderByName").value=obj.id;
		document.forms['pageForm'].pageNo.value=1;
		document.forms['pageForm'].submit();
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
 
 
function add(id){ 
  var enName = document.getElementById("unit.enName").value.trim(); 
  var type = document.getElementById("unit.type").value.trim();  
  if(enName==""){
     showMsgbox("单位名称不能为空","","unit.enName");
     return ;
  }  
  UnitDWR.hasUnit(enName,type,id,function(data){
     if(data==true){
     	showMsgbox("单位名称已经存在","","unit.enName");
     	return ; 
     }
     var type = document.getElementById("unit.type").value.trim(); 
	  if(type==""){
	     showMsgbox("单位分类不能为空","","");
	     return ;
	  }
  var  useOrder = document.getElementById("unit.useOrder").value.trim();  
  if(useOrder!="" && !(/^\d*$/.test(useOrder))){
     showMsgbox("单位顺序只能为整数","","unit.useOrder");
     return ;
  } 
     if(id!="")
    	 document.getElementById("method").value = "editUnit" ;
  	 document.forms["pageForm"].submit() ; 
  }); 
}

function edit(id){ 
   location.href="<%=request.getContextPath()%>/questionnaire.do?method=createOrEdit&id="+id; 
}
function create(){ 
 
 	 
   document.location.href='<%=request.getContextPath()%>/questionnaire.do?method=createOrEdit';
}


function editQuestion(id){ 
   location.href="<%=request.getContextPath()%>/questionnaire.do?method=createOrEditQuestion&id="+id; 
}
function delOne(id,endTime){  

var date = new Date();
  
var str   =   endTime.split(" ")[0].replace(/-/g, '/ '); 
var   da1=new   Date(str);
var   today = new Date(); 
 var  app  = "";
if(today.valueOf()< da1.valueOf()){
   app = "问卷还有到结束时间，";
}
 var msgwin = new DivWindow("msgwin","消息窗口",200,100,app+"确定要删除吗"); 	
    msgwin.addButton("确定",function(){
    	location.href="<%=request.getContextPath()%>/questionnaire.do?method=questionnaireManagement&id="+id; 
    }) ;
    msgwin.addButton("取消") ;
    msgwin.open();   
}

 
function isSelSome(obj){
	var selobj = document.getElementsByName(obj) ; 
	   for(var i=0 ; i < selobj.length ; i++){
	      if(selobj[i].checked)
	          return true ;
	   }
	   return false ; 
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
    	 location.href="<%=request.getContextPath()%>/questionnaire.do?method=questionnaireManagement&id="+id; 
    }) ;
    msgwin.addButton("取消") ;
    msgwin.open();   
}

function  saveQuestionnaire(){

     var  questionnaireStartTime = document.getElementById("questionnaireStartTime").value;
     var  questionnaireEndTime = document.getElementById("questionnaireEndTime").value;
     var  questionnaireName = document.getElementById("questionnaireName").value;
     if(questionnaireStartTime == undefined || questionnaireStartTime.replace(" ",'') == ""){
     	alert("问卷开始时间不能为空！");
     	return;
     }
     if(questionnaireEndTime == undefined || questionnaireEndTime == ""){
     	alert("问卷结束时间不能为空！");
     	return;
     }
     if(questionnaireName == undefined || questionnaireName == ""){
     	alert("问卷名称不能为空！");
     	return;
     }
     
     
    var  startTimeStr = questionnaireStartTime.split(" ")[0].replace(/-/g, '/ ');
    var  endStr = questionnaireEndTime.split(" ")[0].replace(/-/g, '/ ');
    var   start=new   Date(startTimeStr);
	var   end = new Date(endStr); 
  
	if(start.valueOf()> end.valueOf()){
   		alert("开始时间不能大于结束时间！");
   		return;
	}
	document.forms['pageForm'].submit();
	
}

function  saveAll(){
    var  ids = document.getElementsByName('ids');
    var th = 0;
    var bool = true;
    var c = 0;
    for(var i = 0;i< ids.length;i++){
         c = 0;
         var id = ids[i].value;
         var check = document.getElementsByName(id) ;
         for(var k = 0;k< check.length;k++){
           var s =  check[k].checked;
          
           if(s){
              c++;
               
           } 
         }
         if(c>0){
           
         }else{
           alert("第"+(i+1)+"题的选项不能为空，请选择！");
            return;
         }
        
    }
   //location.href="<%=request.getContextPath()%>/questionnaire.do?method=saveAll";
    //document.getElementById("operat_title").style.display="none";
     document.getElementById("sub").disabled=true;
	 document.forms['pageForm'].submit();
	 document.forms['pageForm'].action="<%=request.getContextPath()%>/index.do?method=index";
	 
	
	
	
}
function  saveQuestions(){
   // 题目的内容不能为空
   //var mainTable = document.getElementById("table");
   // var mainTableRownumber = table.rows;
   
   var questionContents = document.getElementsByName("questionContent");
    
   if(questionContents.length<=0){
       alert("至少有一行数据！");
		  return;
   }
   for(var i = 0 ;i< questionContents.length ;i++){
       //alert(questionContents[i].value);
       if(questionContents[i].value == undefined || questionContents[i].value == ""){
          alert("问题内容不能有空！");
          return ;
       }
   	}
   	
   var rowNums = document.getElementsByName("rowNum");
   
   for(var i = 0 ;i< rowNums.length ;i++){
       var answers = document.getElementsByName("questionContent"+rowNums[i].value);
       for(var j = 0 ;j< answers.length ;j++){
       	//alert(answers[j].value);
       		if(answers[j].value=='' || answers[j].value == undefined){
       			 alert("问题的答案不能为空，请输入！");
       			 return;
       		}
   	    }
   	}
	document.forms['pageForm'].submit();
	
} 
function addSelectItems(vTable){


  
    var num = vTable.substring(16,vTable.length);
    var  rowsCount = 'itemsTableRowsCount'+num;
    
    var itemsTableRowsCount = document.getElementsByName(rowsCount);
    //获得答案的最大个数
    var  answerCount = parseFloat(itemsTableRowsCount[itemsTableRowsCount.length-1].value)+1;
    var  itemsTableRowsCountValue = 'itemsTableRowsCount'+num;
	var table = document.getElementById(vTable);
	var newRow = table.insertRow(-1);
	 
	newCell = newRow.insertCell(-1);
	newCell.align="center";
	newCell.innerHTML = "<input type='checkbox' name='checkbox'/><input type='text' name='questionContent"+num+"'><input type='hidden' name='questionAnswerId"+answerCount+"' value=''/><input type='hidden' name= 'itemsTableRowsCount"+num+"' value='"+answerCount+"'/>";
	newCell = newRow.insertCell(-1);
}
function insert(){
    //firefox 中不能使用name 代替 id   IE中能使用
    var itemTableValue = document.getElementById("itemTableValue").value;
    var num = parseFloat(itemTableValue)+1;
    document.getElementById("itemTableValue").value = num;
	var table = document.getElementById("table");
	var newRow = table.insertRow(-1);
	newCell = newRow.insertCell(-1);
	newCell.className = "";
	
	
	newCell.innerHTML = "<input type='checkbox' name='checkbox' value="+num+"  /><input type='hidden' name='rowNum' value="+num+"  />";
	newCell = newRow.insertCell(-1);
	 
	newCell.align="center";
	newCell.innerHTML = "<input type='hidden' name='ids'><input type='text' name='questionContent'>";
	newCell = newRow.insertCell(-1);
	
	newCell.align="center";
	newCell.innerHTML = "<select name='isMultSelect'><option value='否'>否</option><option value='是'>是</option> </select>";
	newCell = newRow.insertCell(-1);
	
	newCell.align="center";
	newCell.innerHTML = "<table border='0' id='selectItemsTable"+num+"'><tr align='center'><td align='center'><input name='' type='checkbox'/><input type='text' name='questionContent"+num+"'/><input type='hidden' name='questionAnswerId"+num+"' value=''/><input type='hidden' name= 'itemsTableRowsCount"+num+"' value=''/></td></tr></table>";
	newCell = newRow.insertCell(-1);
	var insertStr = 
	
	newCell.align="center"; 
	newCell.innerHTML = "<a href='javascript:toDelete(&quot;selectItemsTable"+num+"&quot;);'>删除备选项</a>  <a href='javascript:addSelectItems(&quot;selectItemsTable"+num+"&quot;);'>添加备选项</a>";
	newCell = newRow.insertCell(-1);
	
	
	

}
function toDelete(vTable){
	var table = document.getElementById(vTable);
	var rownumber = table.rows;

	for(var i=rownumber.length-1;i>=0;i--){	
	    var afterDeleteTable = document.getElementById(vTable);
		var afterRownumber = afterDeleteTable.rows;
		
		if(afterRownumber.length <=1){
		  alert("至少有一个选择项");
		  return;
		}
		if(rownumber[i].childNodes[0].childNodes[0].checked){
			table.deleteRow(rownumber[i].rowIndex);
		}
	}
	 
}

function toDeleteMainTable(){
	var table = document.getElementById("table");
	var rownumber = table.rows;
	 
	for(var i=rownumber.length-1;i>0;i--){		
		var afterDeleteTable = document.getElementById("table");
		var afterRownumber = afterDeleteTable.rows;
		if(afterRownumber.length <=2){
		  alert("至少有一行数据！");
		  return;
		}
		if(rownumber[i].childNodes[0].childNodes[0].checked){
			table.deleteRow(rownumber[i].rowIndex);
		} 
	}
	 
}
 

