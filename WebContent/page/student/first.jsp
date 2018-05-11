<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="com.OE.Beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线考试系统</title>
</head>
<body>
<%
String  sname=(String)session.getAttribute("sname");
%>
<table>
<tr align="center">
        姓名：<%=sname%>
    </tr>
</table>
</body>
</html>