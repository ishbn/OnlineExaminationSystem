<%@page import="com.OE.Beans.Teacher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">  

</head>
<body style="text-align:center">
	<br><br>
	<h3>教师个人信息</h3>

	<% Teacher teacher = (Teacher)request.getAttribute("teacher");%>
	<div class="container" style="text-align:center">
		<form action="<%=request.getContextPath() %>/ServletTeacher?option=updatebyTeacher"
			method="post">
			<br>
			<div class="input-group col-md-12">
	            <span class="input-group-addon">教师账号</span>
	            <input type="text" name="o" value="<%=teacher.getTid()%>"  class="form-control" readonly>
	        </div>
			<br>
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">教师姓名</span>
	            <input type="text" name="tname" value="<%=teacher.getTname()%>"  class="form-control" >
	        </div>
	        <br>
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">教师性别</span>
	            <input type="text" name="sex"  value="<%=teacher.getSex()%>" class="form-control" >
	        </div>
	        <br>
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">教师身份证号</span>
	            <input type="text" name="cardnumber" value="<%=teacher.getCardNumber()%>"  class="form-control" >
	        </div>
	        <br>
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">教师职称</span>
	            <input type="text" name="title" value="<%=teacher.getTitle()%>" class="form-control" >
	        </div>
	        <br>
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">教师电话</span>
	            <input type="text" name="phone" value="<%=teacher.getPhone()%>" class="form-control" >
	        </div>
	        <br>
	         <input type="submit"  class="btn btn-primary" value="保存">
			<!-- &nbsp;&nbsp; <input
				type="button" class="bt" value="返回"
				onclick="javascrtpt:window.location.href='teacherHome.jsp' " /> -->
		</form>
	</div>


</body>
</html>