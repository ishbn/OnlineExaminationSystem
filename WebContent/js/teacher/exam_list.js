
	function deleteExam(id){
		alert(id);
		var del;
		if(window.XMLHttpRequest){
			del = new XMLHttpRequest();
		}else{
			del = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		del.onreadystatechange = function(){
			if(del.readyState == 4 && del.status == 200){
				var resObj=del.responseText; //html用文本形式接收       
				alert(resObj);
				window.location.href="ServletExamination?option=find";  
               // document.getElementById("ticket").innerHTML=resObj;
				}
		}
		
		var url = "ServletExamination?option=examDelete&exam_id="+id;
		del.open("GET",url,true);
		del.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		del.send();
	};
	
	
	function releaseExam(str){
		var rel;
		alert("确认发布?");
		if(window.XMLHttpRequest){
			rel = new XMLHttpRequest();
		}else{
			rel = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		rel.onreadystatechange = function(){
			if(rel.readyState == 4 && rel.status == 200){
				var resObj=rel.responseText; //html用文本形式接收       
				alert(resObj);
				window.location.href="ServletExamination?option=find";  
               // document.getElementById("ticket").innerHTML=resObj;
				}
		}
		
		var url = "ServletExamination?option=release&exam_id="+str;
		rel.open("GET",url,true);
		rel.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		rel.send();
	}
