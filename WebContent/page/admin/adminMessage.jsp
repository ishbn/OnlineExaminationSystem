<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">  

</head>
<body>
	<div class="container" style="text-align:center">
	<form  action="ServletAdmin?option=adminupdate&p=${username}"  method="post"  >
    <br><br>
		 <div class="input-group col-md-12">
            <span class="input-group-addon">账号</span>
            <input type="text" name="adminid" value="${username}"  class="form-control" readonly >
        </div>
    <br>
		 <div class="input-group col-md-12">
            <span class="input-group-addon">姓名</span>
            <input type="text" name="adminname" value="${q1}"  class="form-control" >
        </div>
        <br>
		 <div class="input-group col-md-12">
            <span class="input-group-addon">性别</span>
            <input type="text" name="adminsex" value="${q2}"  class="form-control" >
        </div>
        <br>
		 <div class="input-group col-md-12">
            <span class="input-group-addon">身份证号</span>
            <input type="text" name="adminnumber" value="${q3}"  class="form-control" >
        </div>
        <br>
		 <div class="input-group col-md-12">
            <span class="input-group-addon">电话号码</span>
            <input type="text" name="adminphone" value="${q4}"  class="form-control" >
        </div>
         <br>
   		 <input type="submit"  class="btn btn-primary" value="保存">&nbsp;&nbsp; 
			
    </form>
    </div>
</body>
</html>