//************************文件JSON处理部分***********************************
//从files中删除一个文件
function removeFileJson(id,obj){
	var fileJsonStr = obj.value;
	var filesJson =eval('(' + fileJsonStr + ')');
	for(var i=0;i<filesJson.length;i++){
		if(filesJson[i].id==id){
			filesJson[i]=null;
		}
	}
	fileJsonStr = filesJsonToString(filesJson);
	obj.value=fileJsonStr;
}
//向files中加入一个文件
function addFileJson(fileJson,obj){
	var fileJsonStr = obj.value;
	var filesJson;
	if(fileJsonStr.isEmpty()){
		fileJsonStr="[{id:'"+fileJson.id+"',name:'"+fileJson.name+"'}]";		
	}
	else{
		filesJson = eval('(' + fileJsonStr + ')');
		filesJson[filesJson.length]=fileJson;
		fileJsonStr = filesJsonToString(filesJson);
	}
	obj.value=fileJsonStr;
}

//将文件数组转换成字符串
function filesJsonToString(filesJson){
	if(filesJson.length==0){
		return "[]";
	}
	else{
		var retStr="[";
		for(var i=0;i<filesJson.length;i++){
			if(filesJson[i]!=null){
				if(retStr.length>1)retStr+=",";				
				retStr+="{id:'"+filesJson[i].id+"',name:'"+filesJson[i].name+"'}";
			}
		}
		retStr+="]";
		return retStr;
	}	
}