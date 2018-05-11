
	
	function add(){
		var addForm;
		if(window.XMLHttpRequest){
			addForm = new XMLHttpRequest();
		}else{
			addForm = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var exam_id = $('#exam_id').val();
		var class_id = $('#class_id').val();
		var exam_date = $('#exam_date').val();
		var timeLength = $('#timeLength').val();
		
		
		addForm.onreadystatechange = function(){
			if(addForm.readyState == 4 && addForm.status == 200){
				var resObj=addForm.responseText; //html用文本形式接收           
	           // document.getElementById("result").innerHTML=resObj;
				//显示提交结果
				alert(resObj);
				window.location.href="./../../ServletExaminationArrangement?option=find";  
				
				}
		}
		var url = "./../../ServletExaminationArrangement?option=add"
				+"&exam_id="+exam_id
				+"&class_id="+class_id
				+"&exam_date="+exam_date
				+"&timeLength="+timeLength;
		alert(url);
		addForm.open("GET",url,true);
		addForm.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		addForm.send();
		
		}
