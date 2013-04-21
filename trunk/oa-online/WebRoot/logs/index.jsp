<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.io.*,com.fhi.framework.utils.ListSomeLevelDirsFiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   ListSomeLevelDirsFiles files = new ListSomeLevelDirsFiles(application.getRealPath("/")+"\\logs");
   for (File file : files) {
     out.println("<a target='_blank' href='"+request.getContextPath()+"/logs/"+file.getName()+"'>"+file.getName()+"</a>");
     out.println("<br>");
   }  
%>
</body>
</html>