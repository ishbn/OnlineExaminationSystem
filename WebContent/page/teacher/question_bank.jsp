<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<frameset cols="30%,*" >  
	  <frame src="question_bank_add.jsp?subject_id=<%=request.getParameter("subject_id") %>"  >  
	  <frame src="<%=request.getContextPath() %>/page/teacher/question_bank_show.jsp?subject_id=<%=request.getParameter("subject_id") %>" name="main" id="main" >  
	</frameset>  
</html>