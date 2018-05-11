
	function change(){
		var changeForm;
		
		if(window.XMLHttpRequest){
			changeForm = new XMLHttpRequest();
		}else{
			changeForm = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var arrangement_id = $('#arrangement_id').val();
 		var exam_id = $('#exam_id').val();
		var class_id = $('#class_id').val();
		var exam_date = $('#exam_date').val();
		var timeLength = $('#timeLength').val();
		
		
		changeForm.onreadystatechange = function(){
			if(changeForm.readyState == 4 && changeForm.status == 200){
				var resObj=changeForm.responseText; //html用文本形式接收           
	           // document.getElementById("result").innerHTML=resObj;
				//显示提交结果
				alert(resObj);
				window.location.href="ServletExaminationArrangement?option=find";  
				
				}
		}
		var url = "ServletExaminationArrangement?option=update&arrangement_id="+arrangement_id+"&exam_id="+exam_id+"&class_id="+class_id+"&exam_date="+exam_date+"&timeLength="+timeLength;
		
		changeForm.open("GET",url,true);
		changeForm.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		changeForm.send();
		
		}
