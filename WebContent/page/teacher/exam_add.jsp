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
  <form action="ServletExamination?option=add" method="post">
  	<%=request.getAttribute("subject_list") %>
	 <div class="input-group col-md-6">
            <span class="input-group-addon">试卷名</span>
            <input type="text" name="exam_name" id="exam_name" class="form-control" placeholder="试卷名">
        </div>
      
	<br>
	&nbsp;&nbsp;
       <input type="submit"  class="btn btn-primary" value="提交">
    	<button type="reset" class="btn btn-primary ">重置</button>
	</form>

</body>

<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
</html>

