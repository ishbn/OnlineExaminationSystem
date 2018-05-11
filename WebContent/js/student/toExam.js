function toExam(exam_id){
	var toRequest;
	if(window.XMLHttpRequest){
		toRequest = new XMLHttpRequest();
	}else{
		toRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	toRequest.onreadystatechange = function(){
		if(toRequest.readyState == 4 && toRequest.status == 200){
				var resObj=toRequest.responseText; //html用文本形式接收           
	           // document.getElementById("result").innerHTML=resObj;
				//显示提交结果
				//alert(resObj);
				if(resObj =="yes"){
					window.location.href="ServletExam?course="+exam_id;  
				}else{
					alert("不在考试时间段！");
				}
			}
	}
	var url ="ServletRequestExamState?option=ask&exam_id="+exam_id;

	toRequest.open("GET",url,true);
	toRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	toRequest.send();
	
}