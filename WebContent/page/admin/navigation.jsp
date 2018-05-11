<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
	* {margin:0;}
	nav{
		position:fixed;
		width:100%;
		background-color:#333;
		height:70px;
	}
	
	nav ul{
		list-style:none;
		float:right;
	}
	
	nav ul li, nav .logo{
		display:inline-block;
		line-height:70px;
		margin-right:15px;
	}
	nav ul li a{
		text-decoration:none;
		line-height:70px;
		display:inline-block;
		height:inherit;
		color:white;
	}
	
	nav .logo{
		//background-color:#111;
		float:left;
		
	}
	.logo{
		font-size:20px;
		font-weight:700;
		margin-left:25px;
		letter-spacing:5px;
		
	}
	.logo img{vertical-align: bottom;}
	.logo a{
		display:inline-block;
		text-decoration:none;
		color:white;
	}

</style>

</head>
<body>
	
<nav>
	<div class="logo">
			
			<a href="clientHome.jsp" target="main">
			<img height='70' width='70' src="./../../images/airplane.png">
			OnlineExamination
			</a>
	</div>
		<ul>
			<li><a href="adminHome.jsp" target="main">首页</a></li>
			<li><a href="./../../ServletStudent?option=home" target="main">学生信息管理</a></li>
			<li><a href="./../../ServletDepartment?option=show" target="main">教师信息管理</a></li>
			<li><a href="./../../ServletSchedule?option=department" target="main">任课管理</a></li>
			<li><a href="./../../ServletAdmin?option=adminup" target="main">修改个人信息</a></li>
			<li><a href="./../../login.jsp"  target="_top">注销</a></li>
			                                                        
		</ul>
</nav>
	<br>
</body>
</html>