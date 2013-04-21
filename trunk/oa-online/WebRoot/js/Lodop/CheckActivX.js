//检测是否安装打印控件
function CheckLodop(){
   var oldVersion=LODOP.Version;
       newVerion="5.0.6.1";	
   var msgStr = "";
   if (oldVersion==null){
	   msgStr="打印控件未安装!点击这里<a href='js/Lodop/install_lodop.exe'>执行安装</a>,安装后请刷新页面。";
	   if(navigator.appName=="Netscape")
		   msgStr+="<br>（Firefox浏览器用户需先点击这里<a href='js/Lodop/npActiveXFirefox4x.xpi'>安装运行环境</a>）";
	   var setting={
				width:400,
				height:100,
				title:"系统提示",
				text:msgStr			
		};
		var printMsg = new DivWindow(setting);
	   return false;
   } else if (oldVersion<newVerion){
	   msgStr="打印控件需要升级!点击这里<a href='js/Lodop/install_lodop.exe'>执行升级</a>,升级后请重新进入。";
	   var setting={
				width:400,
				height:100,
				title:"系统提示",
				text:msgStr
		};
		var printMsg = new DivWindow(setting);
	   return false;
   }
   return true;
}

//进位取整
var parseAdd = function(i){
		return i>parseInt(i)?parseInt(i)+1:parseInt(i);
	};
 
//毫米转像素
var mmToXp=function(mm){
	return parseInt(parseAdd(mm)*0.039370078740157*96);
}

//打印函数 参数为数组
function CreatePrintPage(codes) {	
	var paperObj={};
		paperObj.everyCount = 8;	//每页张数
		paperObj.width=210/2;			//标签宽度（含塑料纸）单位：毫米
		paperObj.height=297/4;		//标签高度（塑料纸撕折线）单位：毫米


	if(codes==null||codes.length==0){
		return;
	}

	var pageCount = parseAdd(codes.length/paperObj.everyCount);//总页数		

	LODOP.SET_PRINT_PAGESIZE(1,paperObj.width*10*2,paperObj.height*4*10,"A4"); //纸张大小	单位像素
	LODOP.SET_PREVIEW_WINDOW(1,2,1,850,600,"标签打印预览.开始打印");
	var paperTextIndex = 0;
	for(var i=0;i<pageCount;i++){
		LODOP.NewPage();
		for(var j=0;j<paperObj.everyCount;j++){
			var code = codes[i*paperObj.everyCount+j];
			if(code!=null){
				var leftBack = 0;
				var topBack = 0;
				if(j%2==1){
					leftBack = mmToXp(paperObj.width);					
				}
				topBack = parseInt(j/2);
				if(code.indexOf("_")>0){	
					LODOP.ADD_PRINT_TEXT(mmToXp(paperObj.height*0.15)+topBack*mmToXp(paperObj.height),200+leftBack,300,27,code.split("_")[0]);
					paperTextIndex++;
					LODOP.SET_PRINT_STYLEA(paperTextIndex,"FontName","Arial Black");
					LODOP.SET_PRINT_STYLEA(paperTextIndex,"FontSize",23);
					
					LODOP.ADD_PRINT_TEXT(mmToXp(paperObj.height*0.4)+topBack*mmToXp(paperObj.height),25+leftBack,300,27,code.split("_")[1]);
					paperTextIndex++;
					LODOP.SET_PRINT_STYLEA(paperTextIndex,"FontName","Arial Black");
					LODOP.SET_PRINT_STYLEA(paperTextIndex,"FontSize",23);
					
					LODOP.ADD_PRINT_TEXT(mmToXp(paperObj.height*0.55)+topBack*mmToXp(paperObj.height),20+leftBack,300,100,code.split("_")[2]);					
					paperTextIndex++;
					LODOP.SET_PRINT_STYLEA(paperTextIndex,"FontName","Arial");
					LODOP.SET_PRINT_STYLEA(paperTextIndex,"FontSize",12.5);
				}
				else{
					LODOP.ADD_PRINT_TEXT(mmToXp(paperObj.height*0.4)+topBack*mmToXp(paperObj.height),25+leftBack,300,30,code);
					paperTextIndex++;
					LODOP.SET_PRINT_STYLEA(paperTextIndex,"FontName","Arial Black");
					LODOP.SET_PRINT_STYLEA(paperTextIndex,"FontSize",36);
				}
			}
		}
	}	
	LODOP.PREVIEW();	
};	



