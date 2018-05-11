<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.OE.Beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">  

</head>
<body style="text-align:center">
	<%
	  Student student=(Student)session.getAttribute("student");
	%>
	<br/><br/>
	<h3>信息确认</h3>
	
	<form action="<%=request.getContextPath() %>/ServletSubject?option=subjectlist" method="post">

		<div class="container">
		<br/>
		<div class="input-group col-md-12">
	            <span class="input-group-addon">学号</span>
	            <input type="text" class="form-control" value="<%=student.getSid()%>" readonly>
	        </div>
		<br>
		<div class="input-group col-md-12">
	            <span class="input-group-addon">用户名</span>
	            <input type="text" class="form-control" value="<%=student.getSname()%>" readonly>
	        </div>
		<br>
		<div class="input-group col-md-12">
	            <span class="input-group-addon">性别</span>
	            <input type="text" class="form-control" value="<%=student.getSex()%>" readonly>
	        </div>
		<br>
	    <div class="input-group col-md-12">
	            <span class="input-group-addon">身份证</span>
	            <input type="text" class="form-control" value="<%=student.getCardNumber()%>" readonly>
	        </div>
		<br>
	    <div class="input-group col-md-12">
	         <span class="input-group-addon">电话</span>
	         <input type="text" class="form-control" value="<%=student.getPhone()%>" readonly>
	     </div>
		<br> 
		</div>
	
 		<input type="submit"  class="btn btn-primary" value="确认">
</form>

</body>
</html>