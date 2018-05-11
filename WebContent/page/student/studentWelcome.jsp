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
	<br>
	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="jumbotron">
				<h1>
					欢迎使用在线考试系统!
				</h1>
				<p>
					Welcome to use the online test system
.
				</p>
				<p>
					 <a class="btn btn-primary btn-large" href="<%=request.getContextPath() %>/ServletStudent?option=showExamList">进入考试</a>
				</p>
			</div>
		</div>
	</div>
</div>
</body>
</html>