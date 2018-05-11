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
<br>
	<h3>添加学生</h3>

<form  action="./../../ServletStudent?option=add" method="post">
  <div class='container'>
  <div class='row clearfix'>
  <div class='col-md-12 column'>
   <br>
   <div class="input-group col-md-12">
            <span class="input-group-addon">姓名</span>
            <input type="text" name="username"  class="form-control">
        </div>
      
	<br>
	<div class="input-group col-md-12">
            <span class="input-group-addon">性别</span>
            <input type="text" name="usersex"  class="form-control">
        </div>
      
	<br>
   <div class="input-group col-md-12">
            <span class="input-group-addon">身份证</span>
            <input type="text" name="usercard"  class="form-control">
        </div>
      
	<br>
	<div class="input-group col-md-12">
            <span class="input-group-addon">电话</span>
            <input type="text" name="userphone"  class="form-control">
        </div>
      
	<br>
	<div class="input-group col-md-12">
            <span class="input-group-addon">班级</span>
            <input type="text" name="userclass"  class="form-control">
        </div>
      
	<br>
    <input type="submit"  class="btn btn-primary" value="提交">
    </div></div></div>
   </form>
</body>
</html>