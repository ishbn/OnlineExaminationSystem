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
		if(window.XMLHttpRequest){
			del = new XMLHttpRequest();
		}else{
			del = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		del.onreadystatechange = function(){
			if(del.readyState == 4 && del.status == 200){
				var resObj=del.responseText; //html用文本形式接收       
				window.location.href="<%=request.getContextPath() %>/ServletQuestionBank?option=find_exam_question&exam_id=<%=request.getParameter("exam_id")%>";  
               // document.getElementById("ticket").innerHTML=resObj;
				}
		}
		
		var url = "ServletQuestionBank?option=delete_exam_question&exam_id=<%=request.getParameter("exam_id")%>&c_id="+c_id;
		del.open("GET",url,true);
		del.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		del.send();
	}


function deleteFulling(f_id){
		var del;
		if(window.XMLHttpRequest){
			del = new XMLHttpRequest();
		}else{
			del = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		del.onreadystatechange = function(){
			if(del.readyState == 4 && del.status == 200){
				var resObj=del.responseText; //html用文本形式接收       
				window.location.href="<%=request.getContextPath() %>/ServletQuestionBank?option=find_exam_question&exam_id=<%=request.getParameter("exam_id")%>";  
               // document.getElementById("ticket").innerHTML=resObj;
				}
		}
		
		var url = "ServletFullingBank?option=delete_exam_question&exam_id=<%=request.getParameter("exam_id")%>&f_id="+f_id;
		del.open("GET",url,true);
		del.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		del.send();
	}
	
</script>
</head>
<body >
	<h3 style="text-align:center">试卷</h3>
	
	
	
	
	
		<div class="panel-group" id="accordion">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" 
				   href="#collapseOne">
					选择题
				</a>
			</h4>
		</div>
		<div id="collapseOne" class="panel-collapse collapse in">
			<div class="panel-body">
			<%=request.getAttribute("question") %>
			</div>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" 
				   href="#collapseTwo">
					填空题
				</a>
			</h4>
		</div>
		<div id="collapseTwo" class="panel-collapse collapse">
			<div class="panel-body">
				<%=request.getAttribute("fullingBank") %>
			</div>
		</div>
	</div>
	</div>
	
</body>
</html>