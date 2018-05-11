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
<%
	String id = request.getParameter("id");
	String departmentname=request.getParameter("name"); 
%>
<body style="text-align:center">
<br/><br/><br/>
	<div class="container">
		<form action="./../../ServletDepartment?option=update&p=<%=id%>"
			method="post">
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">学院名称</span>
	            <input type="text" name="department_name"  class="form-control" value="<%=departmentname %>">
	        </div>
			<br/><br/>
			 <input type="submit"  class="btn btn-primary" value="修改">&nbsp;&nbsp; 
			 <input type="button"  class="btn btn-primary" value="返回" onclick="javascrtpt:window.location.href='departmentHome.jsp' ">
			
			</form>
	</div>
</body>
</html>
