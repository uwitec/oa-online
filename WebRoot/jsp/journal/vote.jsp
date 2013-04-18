<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/JournalDWR.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>


<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size: 12px;
}
.vote_button_interval{
	height:18px;
	width:1px;
	background-image:url(images/bg2_questionpage.png);
}
.vote_button_bg{
	width:250px;
	height:18px;
	background-image:url(images/vote_button_bg.gif);
	border:1px solid #CCCCCC;
	cursor:hand;
}
.vote_button_bg_over{
	color:#999;
	cursor:auto;
}
.vote_button_bg_over span{
	color:#555;
}
.lian_info{
	border-top: 1px dashed #000;
	padding:20px 0px 0px 50px;
}
.addVote{
	color:#FF0000;
	font-weight: bold;
	position:absolute;
	width:15px;
	height:15px;
	filter:alpha(opacity=0);
	font-size: 13px;
}
-->
</style>
</head>
<body>
    <div class="lian_info">
    	请您踊跃投票：
    </div>
	<div align="center">    
		<form action="">
			<input id="userId" type="hidden" value="<c:out value="${user.userId}" />" />
			<input id="code" type="hidden" value="${form.vote.code}" />
            <table border="0" class="vote_button_bg">
				  <tr>
                    <td id="good">好评：<span>${form.vote.good}</span></td>
                    <td class="vote_button_interval"></td>
                    <td id="ordinary">一般：<span>${form.vote.ordinary}</span></td>
                    <td class="vote_button_interval"></td>
                    <td id="noGood">差评：<span>${form.vote.noGood}</span></td>
                </tr>
            </table>
            <div class="addVote">+1</div>
	  </form>
	</div>
</body>
<script type="text/javascript">
var addVoteShow=function($obj){
	$obj.html(parseInt($obj.html())+1);
	$(".addVote").css({left:$obj.offset().left,top:"50px"})
	.animate({top:"40px",opacity:1},500)
	.animate({top:"20px",opacity:0},500);
}
function voteForm(vote){
	vote.code=$("#code").val();
	vote.creater=$("#userId").val();
	var take=function(ret){
		$(".vote_button_bg").addClass("vote_button_bg_over");
		$("#good").unbind("click");
		$("#ordinary").unbind("click");
		$("#noGood").unbind("click");
		if(ret==-1){		
			window.parent.document.location="<%=request.getContextPath() %>/index.do?method=logout";
		}
		else if(ret==1){
			if(vote.good==1){
				addVoteShow($("#good > span"));
			}
			else if(vote.ordinary==1){
				addVoteShow($("#ordinary > span"));
			}
			else if(vote.noGood==1){
				addVoteShow($("#noGood > span"));
			}
		}
	}
	JournalDWR.voteAction(vote,take);
}

$(function(){
	if($("#userId").val()==""){
		window.parent.document.location="<%=request.getContextPath() %>/index.do?method=logout";		
		return;
	}
	if($("#code").val()==""){
		$(".vote_button_bg").addClass("vote_button_bg_over");
		$("#good").unbind("click");
		$("#ordinary").unbind("click");
		$("#noGood").unbind("click");
	}
	else{
		$("#good").bind("click", function(){ voteForm({good:1}); }); 
		$("#ordinary").bind("click", function(){ voteForm({ordinary:1}); }); 
		$("#noGood").bind("click", function(){ voteForm({noGood:1}); }); 
	}
});

</script>
</html>