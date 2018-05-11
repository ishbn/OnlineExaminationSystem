<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">  

<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/popper.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script>
	function reflesh(){ 
	//leftTree是左边Frame的id 
	//重新加载这个页面 
	window.parent.frames["main"].location.href="<%=request.getContextPath() %>/ServletFullingBank?option=find_fulling&subject_id=<%=request.getParameter("subject_id")%>";  
	//window.parent.frames["main"].location.reload(); 
	} 

</script>
</head>
<body onload="reflesh()">
	 <br>
  <form action="<%=request.getContextPath() %>/ServletFullingBank?option=question_fulling_add" method="post">
  <div class="input-group col-md-6">
            <span class="input-group-addon">编号</span>
            <input type="text" name="subject_id" class="form-control" value="<%=request.getParameter("subject_id")%>" readonly>
        </div>
  <br>
	 <div class="input-group col-md-6">
            <span class="input-group-addon">题目</span>
            <input type="text" name="f_question" class="form-control">
        </div>
	<br>
	<div class="input-group col-md-6">
            <span class="input-group-addon">答案</span>
            <input type="text" name="f_answer" class="form-control">
        </div>
	<br>
	<div class="input-group col-md-6">
            <span class="input-group-addon">分值</span>
            <input type="text" name="f_score" class="form-control" value="2" >
        </div>
	<br>
	
	&nbsp;&nbsp;
       <input type="submit"  class="btn btn-primary" value="提交" target="main">
    	<button type="reset" class="btn btn-primary ">重置</button>
	</form>
	
</body>
</html>