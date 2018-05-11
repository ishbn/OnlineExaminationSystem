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
      String did=request.getParameter("id");
	String oid = request.getParameter("name");
	String teachername=request.getParameter("tn");
	String teachersex=request.getParameter("ts");
	String teachercardnumber=request.getParameter("tc");
	String teachertitle=request.getParameter("tt");
	String teacherphone=request.getParameter("tp");
%>
<body>
	<div class="container" style="text-align:center">
		<form action="./../../ServletTeacher?option=update&o=<%=oid%>&id=<%=did %>"
			method="post">
			<br><br>
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">教师姓名</span>
	            <input type="text" name="tname" value="<%=teachername%>"  class="form-control" >
	        </div>
	        <br>
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">教师性别</span>
	            <input type="text" name="sex"  value="<%=teachersex%>" class="form-control" >
	        </div>
	        <br>
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">教师身份证号</span>
	            <input type="text" name="cardnumber" value="<%=teachercardnumber%>"  class="form-control" >
	        </div>
	        <br>
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">教师职称</span>
	            <input type="text" name="title" value="<%=teachertitle%>" class="form-control" >
	        </div>
	        <br>
			 <div class="input-group col-md-12">
	            <span class="input-group-addon">教师电话</span>
	            <input type="text" name="phone" value="<%=teacherphone%>" class="form-control" >
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