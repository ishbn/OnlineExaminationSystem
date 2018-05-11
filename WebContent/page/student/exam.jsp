<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.OE.Beans.Subject" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">  

</head>
<body style="text-align:center">
	<%
			List<Subject> list =(List)session.getAttribute("list");
			StringBuffer sb= new StringBuffer();	
				if(!list.isEmpty()){
						
				for(int i=0;i<list.size();i++)
				{
				
				sb.append("<tr><td>");
				sb.append(""+list.get(i).getSubject_id()+" ");
				sb.append("</td><td>");
				sb.append(" "+list.get(i).getSubject_name()+" ");
				sb.append("</td><td>");
				sb.append("<a href='"+request.getContextPath()+"/ServletExam?course="+list.get(i).getSubject_id()+"'>进入考试</a><br/>");
				sb.append("</td></tr>");
				}
			}
			else{
				sb.append("<center><strong>教师还没发布考试科目</strong></center>");
				//out.println("<center><strong>教师还没发布考试科目</strong></center>");
			}
			%>

	<br/><br/>
	<h3>考试列表</h3>
	<br/>
	<div class="container" style="text-align:left">
		<div class='row clearfix'>
			<div class='col-md-12 column'>
				<table class='table table-striped table-bordered'>
					<thead><tr><th>考试编号</th><th>科目</th><th>操作</th></tr></thead>
					<tbody>
						<%=sb %>
					</tbody>
				</table>	
			</div>
		</div>
	</div>


</body>
</html>