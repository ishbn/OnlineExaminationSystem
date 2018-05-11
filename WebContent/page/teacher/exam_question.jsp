<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<frameset cols="50%,*" >  
	  <frame src="<%=request.getContextPath() %>/ServletQuestionBank?option=find_question_not_in_exam&exam_id=<%=request.getParameter("exam_id")%>&subject_id=<%=request.getParameter("subject_id") %>"  >  
	  <frame src="<%=request.getContextPath() %>/ServletQuestionBank?option=find_exam_question&exam_id=<%=request.getParameter("exam_id")%>" name="main" id="main" >  
	</frameset>  
</html>