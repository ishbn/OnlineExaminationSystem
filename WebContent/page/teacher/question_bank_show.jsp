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
<script type="text/javascript">
	function deleteQuestion(c_id){
		var del;
		alert(c_id);
		if(window.XMLHttpRequest){
			del = new XMLHttpRequest();
		}else{
			del = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		del.onreadystatechange = function(){
			if(del.readyState == 4 && del.status == 200){
				var resObj=del.responseText; //html用文本形式接收       
				window.location.href="<%=request.getContextPath() %>/ServletQuestionBank?option=find&subject_id=<%=request.getParameter("subject_id")%>";  
               // document.getElementById("ticket").innerHTML=resObj;
				}
		}
		
		var url = "ServletQuestionBank?option=delete_from_bank&subject_id=<%=request.getParameter("subject_id")%>&c_id="+c_id;
		del.open("GET",url,true);
		del.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		del.send();
		
	}
</script>
</head>
<body>
	
	<%=request.getAttribute("question") %>

</body>
</html>