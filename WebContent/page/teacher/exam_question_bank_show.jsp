<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">  

<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/popper.min.js"></script>

<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>

<script>
	function reflashExam(){ 
	//leftTree是左边Frame的id 
	//重新加载这个页面 
	window.parent.frames["main"].location.href="<%=request.getContextPath() %>/ServletQuestionBank?option=find_exam_question&exam_id=<%=request.getParameter("exam_id")%>";  
	//window.parent.frames["main"].location.reload(); 
	} 

	function addtoExam(c_id){
		var ate;
		
		if(window.XMLHttpRequest){
			ate = new XMLHttpRequest();
		}else{
			ate = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		ate.onreadystatechange = function(){
			if(ate.readyState == 4 && ate.status == 200){
				var resObj=ate.responseText; //html用文本形式接收       
				window.location.href="<%=request.getContextPath() %>/ServletQuestionBank?option=find_question_not_in_exam&exam_id=<%=request.getParameter("exam_id")%>&subject_id=<%=request.getParameter("subject_id") %>";  
               // document.getElementById("ticket").innerHTML=resObj;
				}
		}
		
		var url = "ServletQuestionBank?option=add_to_exam&exam_id=<%=request.getParameter("exam_id")%>&c_id="+c_id;
		ate.open("GET",url,true);
		ate.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		ate.send();
	}
	
	function fullingAddtoExam(f_id){
		var ate;
		
		if(window.XMLHttpRequest){
			ate = new XMLHttpRequest();
		}else{
			ate = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		ate.onreadystatechange = function(){
			if(ate.readyState == 4 && ate.status == 200){
				var resObj=ate.responseText; //html用文本形式接收       
				window.location.href="<%=request.getContextPath() %>/ServletQuestionBank?option=find_question_not_in_exam&exam_id=<%=request.getParameter("exam_id")%>&subject_id=<%=request.getParameter("subject_id") %>";  
               // document.getElementById("ticket").innerHTML=resObj;
				}
		}
		
		var url = "ServletFullingBank?option=add_to_exam&exam_id=<%=request.getParameter("exam_id")%>&f_id="+f_id;
		ate.open("GET",url,true);
		ate.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		ate.send();
	}
</script>
</head>
<body onload="reflashExam()">
	<h2 style="text-align:center">题库</h2>
	
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
				<%=request.getAttribute("fString") %>	
			</div>
		</div>
	</div>
	</div>
	
	
	
</body>
</html>