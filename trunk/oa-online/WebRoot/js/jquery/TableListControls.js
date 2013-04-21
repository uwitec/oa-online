// JavaScript Document
/*----------------------------------------------------*/
var TableList=null;
if (TableList == undefined) {
	TableList = function(courier) {
		this.init(courier);
		this.search();
	};
}

TableList.prototype.init=function(courier){
	//设置初始查询参数
	this.searchData = courier.searchData;
	
	this.settingMap = courier.settingMap;
	//设置数据源
	this.dataSources = courier.settingMap.dataSources;
	//右键菜单
	this.menuButtons = courier.menuButtons;
	//列表配置
	this.theadSttings = courier.theadSttings;
	//搜索框设置列表
	this.searchSettings = courier.searchSettings;
	//按钮设置列表
	this.buttonSettings = courier.buttonSettings;
	
	this.listClick = courier.listClick;
	
	if(!this.buttonSettings)
		this.buttonSettings=[];
	
	this.pageInfo = courier.pageInfo;
	
	//列表主体
	var $tab_main = $("<div class='tab_main'></div>");
	//设置宽度
	if(this.settingMap.width!=null){
		$tab_main.width(this.settingMap.width);
	}
	//初始化搜索框
	if(this.searchSettings!=null&&this.searchSettings.length>0){
		this.initSearch(this.searchSettings);
		$tab_main.append(this.searchBox);
	}	
	//信息提示框
	$tab_main.append("<div id='inpageinfo' class='tab_inpageinfo'></div>");
	//判断按钮初始化
	this.initButton(this.buttonSettings);
	if(this.top_button_box.get().length!=0){
		$tab_main.append(this.top_button_box);
	}
	//表格体初始化
	var $tab_border = $("<div class='tab_border'></div>");
	if(this.settingMap.height!=null){
		$tab_border.css("height",this.settingMap.height);
	}
	$tab_main.append($tab_border);
	
	//初始化下部工具条
	if(this.bottom_button_box.children().get().length!=0||this.pageInfo!=null){
		var $tab_bottom = $("<div class='tab_bottom'></div>");	
		$tab_bottom.append(this.bottom_button_box);
		$tab_main.append($tab_bottom);
	}
	
	//将整个列表控件加入页面中
	if($.isPlainObject(this.settingMap.displayBox)){
		$(this.settingMap.displayBox).empty().append($tab_main);
	}
	else{
		$("#"+this.settingMap.displayBoxId).empty().append($tab_main);
	}
}
//初始化按钮
TableList.prototype.initButton=function(buttonSettings){
	this.top_button_box = $("<div class='tab_top_button_box'></div>");
	this.bottom_button_box = $("<div class='tab_bottom_button_box'></div>");
	for(var i=0;i<buttonSettings.length;i++){
		var $button = $("<button class='tab_button_bg' id=''>"+buttonSettings[i].title+"</button>");
		
		if(buttonSettings[i].title.length>2){
			$button.addClass("tab_button_bg_sp");
		}
		if(buttonSettings[i].name)
			$button.attr("name",buttonSettings[i].name);
		if(buttonSettings[i].id)
			$button.attr("id",buttonSettings[i].id);
		if($.isFunction(buttonSettings[i].click))
			$button.click(buttonSettings[i].click);
		if(buttonSettings[i].type=="top"){
			this.top_button_box.append($button);
		}
		else if(buttonSettings[i].type=="bottom"){
			this.bottom_button_box.append($button);
		}
		else if(buttonSettings[i].type=="add"){
			$button.addClass("tab_button_add");
			if(buttonSettings.length==1){
				this.top_button_box.height(28);
			}
			this.top_button_box.append($button);
		}
	}
}
//初始化搜索框
//{type,rowId}
TableList.prototype.initSearch=function(searchSettings){
	//搜索框
	var $tab_search_box = $("<div class='tab_search_box'></div>");
	
	//获取有几种行
	var rowIdObj={};
	for(var i=0;i<searchSettings.length;i++){
		if(searchSettings[i].rowId==null){
			searchSettings[i].rowId="DefaultRowId";
		}
		rowIdObj[searchSettings[i].rowId]+=1;
	}	
	//分别取出每行的设置 生成html
	for(var rowId in rowIdObj){
		var $search_box_ul = $("<ul></ul>");
		for(var j=0;j<searchSettings.length;j++){
			if(searchSettings[j].rowId==rowId){
				if(searchSettings[j].type!="hidden"){
					var $search_box_li = $("<li></li>").html(this.getSearchBean(searchSettings[j]));				
					$search_box_ul.append($search_box_li);
				}else{
					$search_box_ul.append(this.getSearchBean(searchSettings[j]));
				}
			}
		}
		$tab_search_box.append($search_box_ul);
	}
	var $searchButton = $("<button onclick='$(document).attr(\"tab_\").search()' class='tab_button_bg selectButton'>搜索</button>");
	$tab_search_box.append($searchButton);
	this.searchBox = $tab_search_box;	
}
//根据配置文件 生成查询控件
TableList.prototype.getSearchBean=function(searchSetting){
	var retStr="";
	if(searchSetting.title){
		retStr+="<span>"+searchSetting.title+"：</span>";
	}
	switch(searchSetting.type){
		case "hidden":			
			retStr += "<input type='hidden'";
			if(searchSetting.id)
				retStr+=" id='"+searchSetting.id+"'";
			if(searchSetting.name)
				retStr+=" name='"+searchSetting.name+"'";
			if(searchSetting.value)
				retStr+=" value='"+searchSetting.value+"'";			
			retStr+="/>";
			break;
		case "text":			
			retStr += "<input type='text' class='tab_text_input'";
			if(searchSetting.id)
				retStr+=" id='"+searchSetting.id+"'";
			if(searchSetting.name)
				retStr+=" name='"+searchSetting.name+"'";
			if(searchSetting.value)
				retStr+=" value='"+searchSetting.value+"'";			
			retStr+="/>";
			break;
		case "fun":				
			$(document).attr("fun_input_"+searchSetting.name,{fun:searchSetting.clickFun});
			retStr += "<input type='text' readonly class='tab_fun_input' onclick='$(document).attr(\"fun_input_"+searchSetting.name+"\").fun()'"
			if(searchSetting.id)
				retStr+=" id='"+searchSetting.id+"'";
			if(searchSetting.name)
				retStr+=" name='"+searchSetting.name+"'";
			if(searchSetting.value)
				retStr+=" value='"+searchSetting.value+"'";			
			retStr+="/>";
			break;
		case "date":			
			retStr += "<input type='text' readonly class='tab_date_input' ";			
			if(searchSetting.startName)
				retStr += " name='"+searchSetting.startName+"'";
			if(searchSetting.startId)
				retStr += " id='"+searchSetting.startId+"'";
			if(searchSetting.startValue)
				retStr += " value='"+searchSetting.startValue+"'";
			retStr += " onclick='WdatePicker()'/> - <input type='text' readonly class='tab_date_input'";			
			if(searchSetting.endName)
				retStr += " name='"+searchSetting.endName+"'";
			if(searchSetting.endId)
				retStr += " id='"+searchSetting.endId+"'";
			if(searchSetting.endValue)
				retStr += " value='"+searchSetting.endValue+"'";
			retStr += " onclick='WdatePicker()'/>";			
			break;
		case "time":			
			retStr += "<input type='text' readonly class='tab_time_input' ";			
			if(searchSetting.startName)
				retStr += " name='"+searchSetting.startName+"'";
			if(searchSetting.startId)
				retStr += " id='"+searchSetting.startId+"'";
			if(searchSetting.startValue)
				retStr += " value='"+searchSetting.startValue+"'";
			retStr += " onclick='WdatePicker({dateFmt:\"yyyy-MM-dd HH:mm\"})'/> - <input type='text' readonly class='tab_time_input'";			
			if(searchSetting.endName)
				retStr += " name='"+searchSetting.endName+"'";
			if(searchSetting.endId)
				retStr += " id='"+searchSetting.endId+"'";
			if(searchSetting.endValue)
				retStr += " value='"+searchSetting.endValue+"'";
			retStr += " onclick='WdatePicker({dateFmt:\"yyyy-MM-dd HH:mm\"})'/>";			
			break;

	}
	return retStr;
}


//显示列表
TableList.prototype.showTable=function(dataBaseList){
	var theadSttings = this.theadSttings;
	//创建数据表格
	var $dataTable = $("<table class='tab_table' border='0' cellpadding='0' cellspacing='0'></table>");
	//数据表格标题
	var $dataTableThead =$("<thead></thead>");
	
	var $theadTr=$("<tr></tr>");
	//标题数据
	
	
	//循环创建标题栏
	for(var i=0;i<theadSttings.length;i++){	
		var theadTh = theadSttings[i];
		if(theadTh.title==null){
			theadTh.title="";
		}
		var $theadTh=$("<th width='"+theadTh.width+"'></th>");
		if(theadTh.type=="check"){
			/*
			var $checkAll = $("<input type='checkbox' id='checkAll'/>");
			$checkAll.click(function(){				
				if(this.checked){
					$(":checkbox[name='"+theadTh.name+"']").removeAttr("checked");
					$(":checkbox[name='"+theadTh.name+"']").click();
				}
				else
				{					
					$(":checkbox[name='"+theadTh.name+"']").attr("checked","true");
					$(":checkbox[name='"+theadTh.name+"']").click();
				}
				
			});
			*/
			$theadTh.append(theadTh.title);
		}
		else{
			$theadTh.append(theadTh.title);
		}
		$theadTr.append($theadTh);
	}
	//将标题插入表格
	$dataTableThead.append($theadTr);	
	$dataTable.append($dataTableThead);


	var $dataTableTbody = $("<tbody></tbody>"); 
	//数据处理
	for(var i=0;i<dataBaseList.length;i++){
		var obj = dataBaseList[i];		
		var $tbodyTr = $("<tr></tr>");
		if(obj.id!=null)$tbodyTr.attr("id",obj.id);
		if(i%2==1){
			$tbodyTr.addClass("even");
		}
		//添加行单击事件
		if(jQuery.isFunction(this.listClick)){
			$tbodyTr.css("cursor","hand")
					.bind("click", {obj: obj},this.listClick);
		}
		
		if(this.menuButtons!=null&&this.menuButtons.length!=0){
			var menuButtons = this.menuButtons;
			
			$tbodyTr.bind("contextmenu", {obj: obj,buttons:this.menuButtons},function(e){				
				circularMenu(e);
				$(this).addClass("menuSelected");
				return false;
				});
		}
		
		
		for(var j=0;j<theadSttings.length;j++){
			var tdInfo = theadSttings[j];
			
			var $tbodyTd=null;
			if(tdInfo.type=="sequence"){
				$tbodyTd = $("<td>" + parseInt(i+1) + "</td>");
			}
			else if(tdInfo.type=="check"){
				$tbodyTd = $("<td>" + parseInt(i+1) + "</td>");				
				var $check = $("<input type='checkbox' name='"+eval("obj." + tdInfo.name)+"' value='"+eval("obj." + tdInfo.valueName)+"' />")
				if($.isFunction(tdInfo.click))
					$check.bind("click", {obj: obj},tdInfo.click);				
				$tbodyTd.append($check);
			}
			else if(tdInfo.type=="custom"){	
				var retObj = tdInfo.custom(obj,parseInt(i));
				$tbodyTd = $("<td></td>");
				if(jQuery.isPlainObject(retObj)){
					$tbodyTd.attr("MyTitle",retObj.title).html(retObj.html);
				}
				else{
					$tbodyTd.append(retObj);
				}				
			}
			else if(tdInfo.type=="date"){
				if(eval("obj." + tdInfo.name)!=null){
					$tbodyTd = $("<td>" + this.dateFormat(eval("obj." + tdInfo.name),tdInfo.format) + "</td>");
				}
				else{
					$tbodyTd = $("<td>-</td>");
				}
			}
			else{
				if(eval("obj." + tdInfo.name)!=null){
					$tbodyTd = $("<td>" + eval("obj." + tdInfo.name) + "</td>");
				}
				else{
					$tbodyTd = $("<td>-</td>");
				}				
			}
			$tbodyTr.append($tbodyTd);
		}		
		$dataTableTbody.append($tbodyTr);
		//一条数据加载成功后的回调函数
		
		if(this.settingMap.onRowLoad!=null&&jQuery.isFunction(this.settingMap.onRowLoad)){
			this.settingMap.onRowLoad(obj,$tbodyTr);
		}		
		
	}
	$dataTable.append($dataTableTbody);

	$(".tab_border").append($dataTable);
	this.tableFormat();
}


//列表初始化
TableList.prototype.tableFormat=function(){
	//全选按钮动作
	$("#checkAll").click(function(){
		if($(this).attr("check")==undefined||!$(this).attr("check")){
			$(this).attr("check",true);
			$("input[name='ids']").attr("checked",true);
		}else{
			$(this).attr("check",false);
			$("input[name='ids']").attr("checked",false);
		}
	});

	//添加调整宽度控件
	$(".tab_table > thead > tr > th").after($("<td class='width_set'>&nbsp;</td>"));
	$(".tab_table > tbody > tr > td").after($("<td></td>"));

	//初始化列宽度
	var all_ths = $(".tab_table > thead > tr > th").get();
	for(var i=0;i<all_ths.length;i++){
		var $th_ = $(all_ths[i]);
		$th_.width($th_.width());
	}
	//列宽调整============================
	$(".width_set").mousedown(function(e){
			var mouX = e.pageX;
			var prev_elem = $(this).prev();
			var prev_elem_width = prev_elem.width();
			$("body").mousemove(function(e){
				prev_elem.width((e.pageX-mouX)+prev_elem_width);
			});	
			$("body").mouseup(function(){
				$("body").unbind("mousemove");
				//移动光标取消鼠标的选择
				with(document.selection.createRange())
				{
					collapse();
					select();
				}  
				$("body").unbind("mouseup");
				adjustTable();
			});
	}).css("cursor","w-resize");
	
	//取消列宽调整
	$(".width_set").unbind("mousedown").css("cursor","auto");
	
	//列宽调整============================
	
	
	
	
	//给所有超出显示范围的字段添加title 设置省略符号
	var adjustTable = function(){
		var $allTds = $(".tab_table > tbody > tr > td").get();
		for(var i=0;i<$allTds.length;i++){
			var $td = $($allTds[i]);
			if($td.attr("scrollWidth")>$td.width()+10){
				$td.attr("title",$td.html());
			}
			else{
				$td.removeAttr("title");
			}
			if($td.attr("MyTitle")!=null){
				$td.attr("title",$td.attr("MyTitle"));
			}
		}
	}
	adjustTable();
}

//显示分页控件
TableList.prototype.showPaging=function(pageInfo){
		if(pageInfo==null)
			return;
		$(".paging").remove();		
		var $pagingUl = $("<ul class='paging'></ul>");
		if(pageInfo.pageNo>1){
			$pagingUl.append("<li class='previous'><a href='javascript:$(document).attr(\"tab_\").gotoPage(" + (pageInfo.pageNo-1) + ")' title='上一页'></a></li>");
		}
		else{
			$pagingUl.append("<li class='previous_off'></li>");
		}
		if(pageInfo.pageNo>3){
			$pagingUl.append("<li><a href='javascript:$(document).attr(\"tab_\").gotoPage(1)'>1</a></li>");
		}
		if(pageInfo.pageNo>4){
			$pagingUl.append("<li><a href='javascript:$(document).attr(\"tab_\").gotoPage(2)'>2</a></li>");
		}
		if(pageInfo.pageNo>5){
			$pagingUl.append("<li>...</li>");
		}
		for(var i=1;i<=pageInfo.crossPages;i++){
			if(i>=pageInfo.pageNo-2&&i<=pageInfo.pageNo+2){
				if(i==pageInfo.pageNo){
					$pagingUl.append("<li class='active'>"+i+"</li>");
				}
				else{
					$pagingUl.append("<li><a href='javascript:$(document).attr(\"tab_\").gotoPage("+i+")'>"+i+"</a></li>");		
				}
			}
		}
		
		if(pageInfo.pageNo<(pageInfo.crossPages-4)){
   			$pagingUl.append("<li>...</li>");
   		}
		if(pageInfo.pageNo<(pageInfo.crossPages-3)){
   			$pagingUl.append("<li><a href='javascript:$(document).attr(\"tab_\").gotoPage("+(pageInfo.crossPages-1)+")'>"+(pageInfo.crossPages-1)+"</a></li>");
  			}
		if(pageInfo.pageNo<(pageInfo.crossPages-2)){
  			$pagingUl.append("<li><a href='javascript:$(document).attr(\"tab_\").gotoPage("+pageInfo.crossPages+")'>"+pageInfo.crossPages+"</a></li>");
		}
		if(pageInfo.pageNo<pageInfo.crossPages){
  			$pagingUl.append("<li class='next'><a href='javascript:$(document).attr(\"tab_\").gotoPage("+(pageInfo.pageNo+1)+")' title='下一页'></a></li>");
		}
		else{
			$pagingUl.append("<li class='next_off'></li>");
  			}

		$pagingUl.append("<li title='填写跳转页号'><input id='pageGoInput' /></li>");
		
		$pagingUl.append("<li title='跳转到指定页面'><a href='javascript:$(document).attr(\"tab_\").gotoPage($(\"#pageGoInput\").val())'>GO</a></li>");
	      
		$pagingUl.append("<li class='total'>共"+pageInfo.rowCounts+"条/"+pageInfo.crossPages+"页</li>");

		$(".tab_bottom").append($pagingUl);
}
//查询函数
TableList.prototype.search = function(pageNo){
	if(pageNo==null){
		pageNo=1;
	}
	$(".tab_border").empty().addClass("tab_border_loading");
	//拼装查询条件	
	if(this.searchData==null)
		this.searchData = {};
	var $searchDatas = $(".tab_search_box input[name]").get();
	for(var i=0;i<$searchDatas.length;i++){
		var $input = $($searchDatas[i]);
		this.searchData[$input.attr("name")] = $input.val();
	}
	var courier={};
	courier.searchData=this.searchData;
	//设置分页
	if(this.pageInfo){
		this.pageInfo.pageNo=pageNo;
		courier.pageInfo=this.pageInfo;
	}
	courier.settingMap = this.settingMap;
	var thisTable = this;
	this.dataSources(courier,function(courier){
		thisTable.dataBaseList = courier.dataBaseList;
		thisTable.pageInfo = courier.pageInfo;
		thisTable.showTable(thisTable.dataBaseList);
		thisTable.showPaging(thisTable.pageInfo);
		if(courier.message!=""&&courier.message!=null){
			showMsgbox(courier.message);
		}else if(thisTable.dataBaseList.length==0&&courier.settingMap.initMsg==undefined){
			showMsgbox("查询范围内没有符合条件的信息！");
		}
		$(".tab_border").removeClass("tab_border_loading");
	})
	$(document).attr("tab_",this);
}

TableList.prototype.dateFormat = function(date,format) {   
    /*  
     * eg:format="YYYY-MM-dd hh:mm:ss";  
     */  
    var o = {   
        "M+" :date.getMonth() + 1, // month   
        "d+" :date.getDate(), // day   
        "h+" :date.getHours(), // hour   
        "m+" :date.getMinutes(), // minute   
        "s+" :date.getSeconds(), // second   
        "q+" :Math.floor((date.getMonth() + 3) / 3), // quarter   
        "S" :date.getMilliseconds()   
    // millisecond   
    }   
  
    if (/(y+)/.test(format)) {   
        format = format.replace(RegExp.$1, (date.getFullYear() + "")   
                .substr(4 - RegExp.$1.length));   
    }   
  
    for ( var k in o) {   
        if (new RegExp("(" + k + ")").test(format)) {   
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]   
                    : ("00" + o[k]).substr(("" + o[k]).length));   
        }   
    }   
    return format;   
}  

//查询函数
TableList.prototype.gotoPage = function(pageNo){
	this.search(pageNo);
}

TableList.prototype.docSearch = function(){
	this.search();
}

var circularMenu = function(ev){
	
	$(".circularMenuDiv").remove();
	$(".menuSelected").removeClass("menuSelected");
		var x = ev.pageX;
		var y = ev.pageY;
		var obj = ev.data.obj;
		var buttons = ev.data.buttons;
		
		var $div = $("<div class='circularMenuDiv'></div>");
		$div.bind("contextmenu",function(){return false;});
		$("body").append($div);
		$div.css({top:(y-($div.height()/2)),left:(x-($div.width()/2))});
		
		$("body").mousemove(function(ev){
			var x_ = ev.pageX;
			var y_ = ev.pageY;
			var top = (y-($div.height()/2));
			var left = (x-($div.width()/2));
			var width = $div.width();
			var height = $div.height();
			if((x_>(left+width)||x_<left)||(y_>(top+height)||y_<top)){
				$div.remove();
				$("body").unbind("mousemove");
				$(".menuSelected").removeClass("menuSelected");
			}			
		});		
		
		var r=$div.width()/2-20;
		var MenuGetX = function(n,i,r,w){
			return Math.cos(360/n*(i+1)*Math.PI/180)*r+w/2-43/4;
		}
		var MenuGetY = function(n,i,r,h){
			return -Math.sin(360/n*(i+1)*Math.PI/180)*r+h/2-43*3/4;
		}
		for(var i=0;i<buttons.length;i++){
				var $button = $("<button>"+buttons[i].title+"</button>");
				$div.append($button);
				
				$button.bind('click', {obj: obj},buttons[i].click)
						.bind("contextmenu",function(){return false;});
				
				$button.css("top",$div.height()/2)
						.css("left",$div.width()/2);
						
				
				$button.animate({top:MenuGetY(buttons.length,i,r+5,$div.width()),left:MenuGetX(buttons.length,i,r+5,$div.height())},250)
					.animate({top:MenuGetY(buttons.length,i,r-3,$div.width()),left:MenuGetX(buttons.length,i,r-3,$div.height())},80)
					.animate({top:MenuGetY(buttons.length,i,r+2,$div.width()),left:MenuGetX(buttons.length,i,r+2,$div.height())},80)
					.animate({top:MenuGetY(buttons.length,i,r,$div.width()),left:MenuGetX(buttons.length,i,r,$div.height())},80)
			}
		
}
