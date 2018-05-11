<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../css/admin.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">  

</head>
<body style="text-align:center">
<br>
	<h3>
	<%=session.getAttribute("departmentname")%>
	</h3>	
		<br>	
		<div style="text-align:left">
			<%
				StringBuffer sb = (StringBuffer) session.getAttribute("result");
				out.print(sb.toString());
			%>
		</div>
		<br>
		<a href="page/admin/addTeacher.jsp">增加</a>


</body>
</html>