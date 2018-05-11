<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.OE.Beans.Examination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">  
<link href="<%=request.getContextPath() %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link href="<%=request.getContextPath() %>/css/bootstrap-datetimepicker.css" rel="stylesheet" media="screen">
	
</head>
<body>
<% Examination ea = (Examination)request.getAttribute("ea");
	
	%>
	<form action="ServletExamination?option=update" method="post">
	
	<br>
	 <div class="input-group col-md-6">
            <span class="input-group-addon">试卷编号</span>
            <input type="text" name="exam_id" id="exam_id" class="form-control"  value="<%=ea.getExam_id()%>" readonly>
        </div>
      
  <br>
	 <div class="input-group col-md-6">
            <span class="input-group-addon">试卷名</span>
            <input type="text" name="exam_name" id="exam_name" class="form-control" placeholder="试卷名" value="<%=ea.getExam_name()%>">
        </div>
      
	<br>
	 
	<%
	String select_subject = request.getAttribute("select_subject").toString();
	out.print(select_subject);
	%>
	
	&nbsp;&nbsp;
        <input type="submit"  class="btn btn-primary" value="提交">
    	<button type="reset" class="btn btn-primary ">重置</button>
</form>
</body>

<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script  src="<%=request.getContextPath() %>/js/teacher/update_exam.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	
</script>

</html>