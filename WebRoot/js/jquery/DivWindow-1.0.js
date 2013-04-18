// JavaScript Document
/*----------------------------------------------------*/
var DivWindow;

if (DivWindow == undefined) {
	DivWindow = function (setting,title,w,h,content,url) {
		if(jQuery.isPlainObject(setting)){
			this.initWindow(setting);
			this.open();
		}
		else{
			var h_=h+40;
			this.oldInitWindow(setting,title,w,h_,content,url)
		}
	};
}

//整合原来用法
DivWindow.prototype.oldInitWindow = function(id,title,w,h,content,url){	
	var setting={
		id:id,
		title:title,
		width:w,
		height:h,
		url:url
	};
	if(content!=""){
		if(content.indexOf("/>")>0||content.indexOf("</")>0){
			setting.html = content;
		}
		else{
			setting.text = content;
		}
	}	
	this.initWindow(setting);
}
DivWindow.prototype.addButton = function(name,fun){
	this.buttons[this.buttons.length]={name:name,click:fun};
}

DivWindow.prototype.initWindow = function(setting){
//--------------加载设置信息
if(setting.id==null){
	this.id="win_"+(new Date()).getTime();
}
else{
	this.id = setting.id;
}

this.height = setting.height==null?(setting.buttons==null?100:130):setting.height;
this.width = setting.width==null?200:setting.width;
this.title = setting.title==null?"系统信息":setting.title;
this.text = setting.text;
this.html = setting.html;
this.url = setting.url;
this.close = setting.close;
if(setting.buttons==null){
	this.buttons = [];
}
else{
	this.buttons = setting.buttons;
}

//--------------加载设置信息
}

DivWindow.prototype.open = function(){

var $win_bg = $("<div id=bg_"+this.id+" class='win_bg'></div>");	//创建背景DIV	

var $win_crust = $("<div id=win_"+this.id+" class='win_crust'></div>");
var $win_padding = $("<div class='win_padding'></div>");
var $win_internal = $("<div class='win_internal'></div>");
$win_padding.append($win_internal);
	
var $win_title = $("<div class='win_title'>"+this.title+"</div>");
if(this.buttons.length==0&&this.close!=false){
	//右上角关闭按钮
	var closeId = this.id;
	var $close_button = $("<span title='关闭窗口' class='close_button'></span>").click(function(){
		closeDivWindow(closeId);
	});
	$win_title.append($close_button);
}
var $win_content = $("<div class='win_content'></div>");

$("body").append($win_bg);



	//------------移动处理-----------------------------------------------
	$win_title.mousedown(function(e){
		var winY = parseInt($win_crust.css("top"));
		var winX = parseInt($win_crust.css("left"));
		var mouY = e.pageY;
		var mouX = e.pageX;
		$("body").mousemove(function(e){
			$win_crust.css({"top":winY+e.pageY-mouY+"px","left":winX+e.pageX-mouX+"px"});			
		});		
	});
	$win_title.mouseup(function(){
		$("body").unbind("mousemove");
	})
	
	//获取当前窗口高度 
	var h_top = (window.innerHeight || (window.document.documentElement.clientHeight || window.document.body.clientHeight));
	//计算弹窗距顶边距离
	if(h_top>this.height){
		h_top=(h_top-this.height)/3;
	}
	else{
		h_top=0;
	}
	
	//------------移动处理-----------------------------------------------
	
		//在外框内添加标题和内容
	$win_internal.append($win_title)
						.append($win_content)
						.width(this.width)
						.height(this.height);	//为弹窗内容设置宽高	
	this.height=this.height-30;											//设置标题 高度减小了30像素
	
	//-----------------------------------------设置按钮----------------------------------------------------------
	var buttons = this.buttons;
	if(buttons!=null&&buttons.length!=0){
		this.height=this.height-40;										//设置了按钮框 高度减小了40像素
		var $win_button_box = $("<div class='win_button_box'></div>");
		$win_internal.append($win_button_box);	//在外框内添加按钮框		
		for(var i=0;i<buttons.length;i++){
			$win_button = $("<button class='win_button_bg'>"+buttons[i].name+"</button>");
																//如果按钮有单击事件 则设置到按钮中
			
			if(buttons[i].name.length!=2){
				$win_button.removeClass("win_button_bg").addClass("win_button_bg_sp");
			}
			if(buttons[i].click!=null){
				$win_button.click(buttons[i].click);
			}
			else{
				$win_button.click(function(){
					closeDivWindow();
				});
			}
			$win_button_box.append($win_button);
		}
	}	
	//-----------------------------------------设置按钮----------------------------------------------------------
	 
	
	//设置嵌套页内容
	if(this.text!=null){
		$win_content.addClass("win_content_text").html(this.text);
		this.height = this.height-20;
	}else if(this.html!=null){
		$win_content.append(this.html);
	}else if(this.url!=null){
		$win_content.html("<iframe name=\"tab_iframe\" src=\""+this.url+"\" width=\"100%\" height=\"100%\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
	}
	
	$win_content.height(this.height);
	
		
	//将背景蒙板 和窗体添加到body中
		
	$("body").append($win_crust);
	
	
	$win_bg.height($(document).height())
	.animate({opacity:0.5},300);		//重新为背景蒙板设置宽高						运行淡出动画
	
	$win_crust.append($win_padding)
					.css("left",($(document).width()-this.width)/2)
					.css("top",$(document).scrollTop()+h_top);
					//.animate({"top":$(document).scrollTop()+h_top},300);	//为窗体设置位置动画

	

}

//隐藏该窗体
DivWindow.prototype.close=function(){
	$("#bg_"+this.id).remove();
	$("#win_"+this.id).remove();
}

//隐藏指定窗体
function closeDivWindow(id){
	if(id!=null){
		$("#bg_"+id).remove();
		$("#win_"+id).remove();
	}
	else{
		$(".win_bg").remove();
		$(".win_crust").remove();
	}
	with(document.selection.createRange())
	{
		//collapse();
		select();
	}
}