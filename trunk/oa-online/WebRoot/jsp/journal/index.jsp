<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子期刊</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/new_general.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MyDivWindow.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/DivWindow-1.0.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/leftnav.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools/validate.jsp"></script>
</head>
<body>
<div id="layout">
<jsp:include page="/index.do?method=head" flush="true"></jsp:include>
<div id="mid">
  <jsp:include page="/index.do?method=left" flush="true"></jsp:include>
  <div id="mainpage">
    <div id="location"> 当前位置：<span class="fp"><a href="<%=request.getContextPath()%>/index.do?method=index">我的桌面</a></span><span><a href="<%=request.getContextPath()%>/journal.do?method=index">电子期刊</a></span></div>
    <div id="inpageinfo"></div>
    <div class="index_button_box">

    </div>
  </div>  
</div>
<jsp:include page="/index.do?method=foot" flush="true"></jsp:include>
</body>
<script type="text/javascript">
var data=[	
			{photoUrl:'images/index.jpg',text:'2011年3月 第三十九期',url:'201103',title:'电子期刊第三十九期'},
			{photoUrl:'images/index.jpg',text:'2011年3月 第三十九期',url:'201103',title:'电子期刊第三十九期'},
			{photoUrl:'images/index.jpg',width:1000,text:'2010年度年终会议专刊',url:'201102',title:'2010年度年终会议专刊'},
          	{photoUrl:'images/index.jpg',text:'2011年1月 第三十八期',url:'201101',title:'电子期刊第三十八期'},
			{photoUrl:'images/index.jpg',text:'2010年12月 第三十七期',url:'201012',title:'电子期刊第三十七期'},			
			{photoUrl:'images/index.jpg',text:'2010年11月 第三十六期',url:'201011',title:'电子期刊第三十六期'},
			{photoUrl:'images/index.jpg',text:'2010年10月 第三十五期',url:'201010',title:'电子期刊第三十五期'},
			{photoUrl:'images/index.jpg',text:'2010年9月 第三十四期',url:'201009',title:'电子期刊第三十四期'},
			{photoUrl:'index.jpg',text:'2010年度年中会议专刊',url:'20100723',title:'2010年度年中会议专刊',height:600,width:$(document).width()-20},
			{photoUrl:'images/index.jpg',text:'2010年7月 第三十三期',url:'201007',title:'电子期刊第三十三期'},
          	{photoUrl:'images/index.jpg',text:'2010年6月 第三十二期',url:'201006',title:'电子期刊第三十二期'},
			{photoUrl:'images/index.jpg',text:'2010年5月 第三十一期',url:'201005',title:'电子期刊第三十一期'}

          ]
$(function(){
		$indexUl = $("<ul></ul>");
		for(var i=0;i<data.length;i++){
			$indexLi = $("<li></li>");
			$indexLi
					.append($("<img />").width(150).height(200).attr("src","<%=request.getContextPath()%>/upload/"+data[i].url+"/"+data[i].photoUrl))
					.append($("<span>"+data[i].text+"</span>"))
					.attr("url",data[i].url)
					.attr("title",data[i].title);
			if(data[i].width)
				$indexLi.attr("openWidth",data[i].width);
			if(data[i].height)
				$indexLi.attr("openHeight",data[i].height);

			$indexLi.click(function(){							
						var setting={
								width:890,
								height:1650,
								title:$(this).attr("title"),
								url:'upload/'+$(this).attr("url")
							}
						if($(this).attr("openWidth")!=null){
							setting.width=$(this).attr("openWidth");
						}
						if($(this).attr("openHeight")!=null){
							setting.height=$(this).attr("openHeight");
						}
						var showJournal = new DivWindow(setting);
						//弹出页面方式浏览期刊
						//window.location="<%=request.getContextPath() %>/journal.do?method=show&folder="+$(this).attr("url")+"&title="+$(this).attr("title");

						})
			$indexUl.append($indexLi);
		}
		$(".index_button_box").append($indexUl);
	})

//期刊中调用虚拟弹窗
function openFhiWindow(winSet){
	/*
	var setting={
			width:890,
			height:1650,
			title:$(this).attr("title"),
			url:'upload/'+$(this).attr("url")
		}
	*/
	var showJournal = new DivWindow(winSet);
}
</script>
</html>
