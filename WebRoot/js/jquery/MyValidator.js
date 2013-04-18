/**
 * MyValidator v1.0
 * 
 * code by 鑫缘
 * 
 * wangxin6302@gmail.com
 * 
 */
var XyValidator = null;
if (XyValidator == undefined) {
	XyValidator = function(courier,isInit) {
		this.validator = {
				All : /^[[\w|-]|[\u0391-\uFFE5]]*$/,
				Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
				Phone : /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/,
				Mobile : /^((\(\d{3}\))|(\d{3}\-))?13\d{9}$/,
				Url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
				IdCard : /^\d{15}(\d{2}[A-Za-z0-9])?$/,
				Currency : /^\d+(\.\d+)?$/,
				Number : /^\d+$/,
				Zip : /^[1-9]\d{5}$/,
				QQ : /^[1-9]\d{4,8}$/,
				Integer : /^[-\+]?\d+$/,
				Double : /^[-\+]?\d+(\.\d+)?$/,
				English : /^[A-Za-z]+$/,
				Chinese : /^[\u0391-\uFFE5]+$/,
				UnSafe : /^[\w|-]*$/,				
				//UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
				Trim:function(str){// 去除字符串的首尾的空格
					return str.replace(/(^\s*)|(\s*$)/g, "");
				}
		}
		
		if(jQuery.isPlainObject(courier)){
			
		}
		else {
			this.jQuerySelect(courier);
		}
		if(isInit||isInit==null){
			this.pass=this.manage();
		}
	};
}
//jQuery初始化
XyValidator.prototype.jQuerySelect=function(courier){
	this.v_Inputs = $(courier).filter("[v_Sub]").get();
	
	for(var i=0;i<this.v_Inputs.length;i++){
		var $inputObj = $(this.v_Inputs[i]);
		//初始化只读数据
		/*
		if($inputObj.attr("readonly")){
			$inputObj.css("background-color","#ececec")
					.css("border","1px solid #9a9a9a");
		}
		*/
	}
}

//信息提示 10秒后隐藏
var XyValidatorMsgbox=undefined;
XyValidator.prototype.Msgbox=function(msgStr){
	$("#XyValidator_MsgBox").remove();
    var $infoBox = $("<div id='XyValidator_MsgBox'>"+msgStr+"</div>");    
    $infoBox.css({'font-weight':'bold',
    				'padding':'4px 45px 2px 35px',
    				'top':'3px',
					'left':'450px',
					'position':'fixed',
					'color':'#ff2b2a',
					'background-color':'#ffd9da',
					'border':'1px solid #ff4800'});    
	$("body").append($infoBox);
	XyValidatorMsgbox = setTimeout(function(){	
		XyValidatorMsgbox=undefined;		
		$infoBox.remove();
	},10000);
}

XyValidator.prototype.manage=function(){
	for(var i=0;i<this.v_Inputs.length;i++){
		//文本框错误变色处理
		var inputError=function($inputObj){
			var borderColor = $inputObj.css("border-color");
			var backgroundColor = $inputObj.css("background-color");
			$inputObj.css({"border-color":"#fd3535","background-color":"#ffc9c9"})
					.bind("blur",function(){
						$(this).css({"border-color":borderColor,"background-color":backgroundColor});
					});
		}
		
		var $inputObj = $(this.v_Inputs[i]);
		//去除两边空格处理
		var val = this.validator.Trim($inputObj.val());
		$inputObj.val(val);
		
		/*
		//检查只读数据
		if($inputObj.attr("readonly")!=null){
			if($inputObj.val()!=$inputObj.attr("defaultValue")){
				this.Msgbox("数据为只读，不可更改！");
				inputError($inputObj);
				return false;
			}
		}
		*/
		//如果输入为空并且不允许为空时提示
		if(val==""&&$inputObj.attr("v_NotNull")!=null&&$inputObj.attr("v_NotNull").toUpperCase()=="TRUE"){
			this.Msgbox("数据不能为空！");
			inputError($inputObj);
			return false;
		}		
		//录入不为空并且不符合规则 提示
		else if(val!=""&&!val.match(this.validator[$inputObj.attr("v_Sub")])){
			this.Msgbox("录入数据类型错误！");
			inputError($inputObj);
			return false;
		}
		//Double类型精度判断
		var v_Precision = $inputObj.attr("v_Precision");
		if(v_Precision!=null&&$inputObj.attr("v_Sub")=="Double"&&v_Precision.match(this.validator.Number)){
			var precision = parseInt(v_Precision);
			if(val.split(".")[1]!=null&&val.split(".")[1].length>precision){
				this.Msgbox("数据精度错误！请保留"+$inputObj.attr("v_Precision")+"位");
				inputError($inputObj);
				return false;
			}
		}
		
	}
	return true;
}
//清除表单验证时所有提示性信息
XyValidator.prototype.clearMsg=function(){
	$("#XyValidator_MsgBox").remove();
	for(var i=0;i<this.v_Inputs.length;i++){
		var $inputObj = $(this.v_Inputs[i]);
		$inputObj.blur();
	}
}
