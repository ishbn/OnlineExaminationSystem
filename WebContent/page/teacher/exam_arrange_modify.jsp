<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.OE.Beans.ExaminationArrangement"%>
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
<% ExaminationArrangement ea = (ExaminationArrangement)request.getAttribute("examinationArrangement");
%>
	
	<br>
	 <div class="input-group col-md-6">
            <span class="input-group-addon">安排编号</span>
            <input type="text" name="arrangement_id" id="arrangement_id" class="form-control"  value="<%=ea.getArrangement_id()%>" readonly>
      </div>
      
	<br>
	<br>
	<%
	String select_exam = request.getAttribute("select_exam").toString();
	out.print(select_exam);
	%>
	
	<%
	String select_class = request.getAttribute("select_class").toString();
	out.print(select_class);
	%>
	
	 <div class="input-group date form_datetime col-md-6" data-date="2018-01-01 T05:25:07Z" data-date-format="yyyy-mm-dd  hh:ii" data-link-field="exam_date">
               <label for="dtp_input1" class="col-md-2 control-label input-group-addon">考试日期</label>
                <input class="form-control" size="16" type="text" value="<%=ea.toString(ea.getExam_date()) %>" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
            </div>
			<input type="hidden" id="exam_date" value="<%=ea.toString(ea.getExam_date()) %>" />
         <br>
	 <div class="input-group col-md-6">
            <span class="input-group-addon">考试时长</span>
            <input type="text" name="timeLength" id="timeLength" class="form-control" placeholder="考试时长" value="<%=ea.getTimeLength()%>">
        </div>
      
	<br>
	
	&nbsp;&nbsp;
        <button  class="btn btn-primary " onclick="change()">提交</button>
    	<button type="reset" class="btn btn-primary ">重置</button>
</body>

<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script  src="<%=request.getContextPath() %>/js/teacher/update_exam_arrange.js" type="text/javascript"></script>
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