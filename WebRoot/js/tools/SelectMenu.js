// JavaScript Document SelectMenu v1.0: chankyic@gmail.com
var SelectMenu;

if (SelectMenu == undefined) {
	SelectMenu = function (inputId) {
		this.initSelectMenu(inputId);
	};
}

SelectMenu.prototype.initSelectMenu = function(inputId) {
	this.inputObj =  document.getElementById(inputId);	
	this.menuDataList=[];
	this.selectMenuDiv;
	this.selectLiId=-1;
	this.selectLiIdMax=-1;
	this.dataSources=function(inStr,dataList){
		dataList[0] = inStr;
	}
	this.onSelected = function(dataObj,inputObj){
		inputObj.value=dataObj.innerHTML;
	};
	//设置输出格式 默认直接输出
	this.showValue=function(liObj,dataObj){
		liObj.innerHTML=dataObj;
	};
	if(this.inputObj==undefined){
			alert("文本框ID："+inputId +"不存在！");
		}
	
	
	if(this.selectMenuDiv!=undefined){
		document.body.removeChild(this.selectMenuDiv);
	}
	this.selectMenuDiv = document.createElement("div");	
	//设置初始隐藏
	this.selectMenuDiv.style.display = "none";

	//设置虚拟列表Div的ID
	this.selectMenuDiv.id=this.inputObj.id+".select";
	
	//设置下拉列表样式
	this.selectMenuDiv.className="SelectMenu";
	
	//设置虚拟列表相对位置
	this.selectMenuDiv.style.left=this.inputObj.getBoundingClientRect().left;
	this.selectMenuDiv.style.top=this.inputObj.getBoundingClientRect().top+18;
	document.body.appendChild(this.selectMenuDiv);
	
	//为DIV添加鼠标悬停确认
	this.selectMenuDiv.onmouseenter=function(){
		this.setAttribute("onmouse",true);
	};
	this.selectMenuDiv.onmouseleave=function(){
		this.setAttribute("onmouse",false);
	};	
	
	//键盘弹起动作

	var selectMenu = this; 
	this.inputObj.onkeyup=function(){		
		//alert(event.keyCode);
		//上
		if(event.keyCode==38&&selectMenu.selectLiIdMax>=0){
			for(var i=0;i<=selectMenu.selectLiIdMax;i++){
				document.getElementById(selectMenu.selectMenuDiv.id+".li"+i).style.backgroundColor="";
				//document.getElementById(selectMenu.selectMenuDiv.id+".li"+i).style.color="";
			}
			selectMenu.selectLiId--;
			if(selectMenu.selectLiId<0){
				selectMenu.selectLiId=selectMenu.selectLiIdMax;
			}
			document.getElementById(selectMenu.selectMenuDiv.id+".li"+selectMenu.selectLiId).style.backgroundColor="F2F5EF";
			//document.getElementById(selectMenu.selectMenuDiv.id+".li"+selectMenu.selectLiId).style.color="FFF";
			return;
		}
		//下
		if(event.keyCode==40&&selectMenu.selectLiIdMax>=0){
			for(var i=0;i<=selectMenu.selectLiIdMax;i++){
				document.getElementById(selectMenu.selectMenuDiv.id+".li"+i).style.backgroundColor="";
				//document.getElementById(selectMenu.selectMenuDiv.id+".li"+i).style.color="";
			}
			selectMenu.selectLiId++;
			if(selectMenu.selectLiId>selectMenu.selectLiIdMax){
				selectMenu.selectLiId=0;
			}
			document.getElementById(selectMenu.selectMenuDiv.id+".li"+selectMenu.selectLiId).style.backgroundColor="F2F5EF";
			//document.getElementById(selectMenu.selectMenuDiv.id+".li"+selectMenu.selectLiId).style.color="FFF";
			return;
		}
		//回车
		if(event.keyCode==13&&selectMenu.selectLiId>=0){
			document.getElementById(selectMenu.selectMenuDiv.id+".li"+selectMenu.selectLiId).click();
			this.select();
			return;
		}
		if(this.value==""){
			return;
		}
		selectMenu.showSelectMenu();
	};
	
	this.inputObj.onblur=function(){
		if(!selectMenu.selectMenuDiv.getAttribute("onmouse")){
			selectMenu.selectMenuDiv.style.display = "none";
		}
	}
	
};

	//设置输出格式 默认直接输出
SelectMenu.prototype.setShowValue=function(showValue){
		this.showValue=showValue;
	};

	//设置数据来源
SelectMenu.prototype.setDataSources = function(dataSources){	
		this.dataSources=dataSources;
	};
	
	
	//设置数据处理
SelectMenu.prototype.getDataList=function(){
		
	};
	
//设置选定动作
SelectMenu.prototype.setSelected = function(onSelected){
		this.onSelected=onSelected;
	};
	

	//显示列表  
SelectMenu.prototype.showSelectMenu = function(){
		//清除列表中所有元素
		for(var i=0;i<this.selectMenuDiv.children.length;i++){
			this.selectMenuDiv.removeChild(this.selectMenuDiv.children[i])
		}		
		var selectMenuUl = document.createElement("ul");
		var selectMenu = this;		
		this.dataSources(this.inputObj.value,function(data){
			selectMenu.menuDataList=data;

			//初始化所选项
			selectMenu.selectLiId=-1;
			selectMenu.selectLiIdMax=selectMenu.menuDataList.length-1;
			
			for(var i=0;i<data.length;i++){
				var selectMenuli = document.createElement("li");
				selectMenuli.id=selectMenu.selectMenuDiv.id+".li"+i;
				selectMenu.showValue(data[i],selectMenuli);
				
				selectMenuli.onclick=function(){
					selectMenu.onSelected(selectMenu.inputObj,this);
					selectMenu.selectMenuDiv.style.display = "none";
				}
				selectMenuUl.appendChild(selectMenuli);
			}
			selectMenu.selectMenuDiv.appendChild(selectMenuUl);
			//设置显示		
			if(selectMenu.menuDataList.length==0){
				selectMenu.selectMenuDiv.style.display = "none";
			}else{
				selectMenu.selectMenuDiv.style.display = "inline";
			}
		});		
};