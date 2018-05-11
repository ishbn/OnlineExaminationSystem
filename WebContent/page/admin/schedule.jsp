<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align:center">
<br/>
<form action="./../../ServletSchedule?option=schedule" method="post">
	<select name="tt">
	<%=session.getAttribute("tea").toString() %>
	</select>
	<select name="ss">
	<%=session.getAttribute("sub").toString() %>
	</select>
	<select name="cc">
	<%=session.getAttribute("cla").toString() %>
	</select>
<input type = "submit" value = "提交"/>
</form>
</body>
</html>