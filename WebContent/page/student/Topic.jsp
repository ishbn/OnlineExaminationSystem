<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.*" %>
    <%@ page import="com.OE.Beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">  

<script type="text/javascript" src="<%=request.getContextPath() %>/js/student/timer.js"></script>

</head>
<body onload="timer('<%= session.getAttribute("examTime")%>')">
	 <div>距离考试结束还有
	 <span id="gap"></span>
	 </div>
<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
<form action="<%=request.getContextPath() %>/ServletAnswer?option=choice" method="post">
	<% 
	List<Choice> list1 =(List)request.getAttribute("list1");
	//List<FullingBlank> list2=(List)request.getAttribute("list2");
	String subject_name=(String)request.getAttribute("subject_name");
	%>
	<h1 style="text-align:center"><%=subject_name %>考试</h1>
	<h2>一、选择题。</h2>
<% 
int i=1;
if(list1.isEmpty()){
	out.println("<center><strong>该科目选择题还在路上^-^</strong></center>");	
}
else{
%>
<%
for(Choice c:list1){
%>
		
					
				
   <ul class="list-group">
  	
    <li class="list-group-item"> <h3><%=i %>、<%=c.getQuestion()%> (<%=c.getC_score()%>分) </h3></li>
    
    <li class="list-group-item">A、<%=c.getC_choiceA() %></li>
    <li class="list-group-item">B、<%=c.getC_choiceB() %></li>
    <li class="list-group-item">C、<%=c.getC_choiceC() %></li>
    <li class="list-group-item">D、<%=c.getC_choiceD() %></li>
    <li class="list-group-item">
    请选择答案：
        <input type="radio" name="choice<%=c.getC_id()%>" id="cAnswerA" value="A" > A
        <input type="radio" name="choice<%=c.getC_id()%>" id="cAnswerB" value="B" > B
        <input type="radio" name="choice<%=c.getC_id()%>" id="cAnswerC" value="C" > C
        <input type="radio" name="choice<%=c.getC_id()%>" id="cAnswerD" value="D" > D
    </li>
</ul>
	<br><br>
	
 <%
		i++;
 session.setAttribute("i", i);
 }
}
   %>
 
 <center><input type="submit" class="button white"  value="交选择题" /></center>
	<br>
</form>

		</div>
	</div>
</div>
</body>
</html>