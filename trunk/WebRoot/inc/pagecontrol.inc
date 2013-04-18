<%@ page import="com.fhi.framework.page.PageInfo,com.fhi.framework.config.FhiConfig"%>

<%
	PageInfo pageInfo = (PageInfo) request.getAttribute(FhiConfig.PAGE_INFO_NAME);

	long pageNo = pageInfo.getPageNo();
	pageInfo.setPageNo(1);
	long crossPages = pageInfo.getCrossPages();
	long rowCounts = pageInfo.getRowCounts();
	long currentRow = pageInfo.getCurrentRow();

%>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"/>
<%
	if(pageInfo != null&&pageInfo.getCrossPages()>1){
%>
	   	<ul id="paging">
	   		<%	if(pageNo>1){
	   		%><li class="previous"><a href="javascript:gotoPage(<%=pageNo-1%>)" title="上一页"></a></li>
	   		<%	}
	   			else{
	   		%><li class="previous_off"></li>
	   		<%	}
	   			if(pageNo>3){
	   		%><li><a href="javascript:gotoPage(1)">1</a></li>
	   		<%	}
	   			if(pageNo>4){
	   		%><li><a href="javascript:gotoPage(2)">2</a></li>
	   		<%	}
	   			if(pageNo>5){
	   		%><li>...</li>
    		<%	}
    		for(int i=1;i<=crossPages;i++)
    		{
    			if(i>=pageNo-2&&i<=pageNo+2){
    				if(i==pageNo){
    		%><li class="active"><%=i %></li>
			<%		}
    				else{
    		%><li><a href="javascript:gotoPage(<%=i %>)"><%=i %></a></li>			
			<%		}
    			}
    		}
    			if(pageNo<crossPages-4){
		   	%><li>...</li>
		   	<%	}
		   		if(pageNo<crossPages-3){
	   		%><li><a href="javascript:gotoPage(<%=crossPages-1%>)"><%=crossPages-1%></a></li>
	   		<%	}
	   			if(pageNo<crossPages-2){
	   		%><li><a href="javascript:gotoPage(<%=crossPages%>)"><%=crossPages%></a></li>
	   		<%	}
	   			if(pageNo<crossPages){
	   		%><li class="next"><a href="javascript:gotoPage(<%=pageNo+1%>)" title="下一页"></a></li>
	   		<%	}
	   			else{
	   		%><li class="next_off"></li>
	   		<%	}
	   		%><li class="total">共<%=rowCounts%>条/<%=crossPages%>页</li>
    	</ul>
		<script language="javascript">
		<!--
		function Jumping(){
		  var pageNo = document.forms['pageForm'].jumpPage.options[document.forms['pageForm'].jumpPage.selectedIndex].value;
		  formSubmit(pageNo);
		}
		function gotoPage(pageNo){
			formSubmit(pageNo);
		}
		function formSubmit(pageNo){
			document.forms['pageForm'].pageNo.value = pageNo;
			document.forms['pageForm'].submit();
			return ;
		}
		//-->
		</script>
<%		pageInfo.init();	}%>