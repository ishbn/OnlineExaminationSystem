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
	 <br>
	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column"> 
<form action="<%=request.getContextPath() %>/ServletAnswer?option=fulling" method="post">
<%   
int i=(int)session.getAttribute("i");
List<FullingBlank> list2=(List)request.getAttribute("list2");

%>
 <h1>二、填空题。</h1>
  <%

  if(list2.isEmpty()){
		out.println("<center><strong>该科目填空题还在路上^-^</strong></center>");	
	}
  else{
  %>
 <%
for(FullingBlank f:list2){
%>
   
			<h4>
			
  			  <%=i %>、<%=f.getF_question()%> (<%=f.getF_score()%>分)
    		</h4>
    
    	
        
	  	<div class="input-group col-md-12">
	            <span class="input-group-addon">请填写答案：</span>
	           <input class="form-control " type="text" name="fulling<%=f.getF_id() %>" id="fAnswer" />
  
	        </div>
		
		<br>
  
 <%
		i++;
 }
}
   %> 
    <center><input type="submit" class="button white"  value="交卷" /></center>
</form>
</div>
	</div>
</div>
</body>
</html>