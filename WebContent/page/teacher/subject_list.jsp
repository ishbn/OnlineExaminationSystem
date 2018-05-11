<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">  
<script src="<%=request.getContextPath() %>/js/popper.min.js"></script>

<script type="text/javascript">
	function findGrade(sub){
		
	
		
		window.location.href="ServletScore?option=showClassGrade&class_id=<%=request.getParameter("class_id")%>&subject_id="+sub;  
		
	}

</script>

</head>
<body style="text-align:center">
	<br><br>
	<h3>科目列表</h3>
	<br>
	<div class="container"  style="text-align:left">
	<%= request.getAttribute("sub_str") %>
	</div>
</body>
</html>