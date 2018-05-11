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
	<% String password=(String)session.getAttribute("password");%>
	<br/><br/>
	<h3>修改密码</h3>
	<div class="container">
	<form action="<%=request.getContextPath() %>/ServletPassword" method="post">
		 <br/>
		<div class="input-group col-md-12">
	        <span class="input-group-addon">原密码</span>
	        <input name="oldpwd" type="password" class="form-control" >
	    </div>
	    <br/>
		<div class="input-group col-md-12">
	        <span class="input-group-addon">新密码</span>
	        <input name="newpwd" type="password" class="form-control" >
	    </div>
	   <br/>
		<div class="input-group col-md-12">
	        <span class="input-group-addon">确认新密码</span>
	        <input name="newpwdrepeat" type="password" class="form-control" >
	    </div>
	    <br/><br/>
	    <input type="submit"  class="btn btn-primary" value="提交">
	
	</form>
	</div>
</body>
</html>