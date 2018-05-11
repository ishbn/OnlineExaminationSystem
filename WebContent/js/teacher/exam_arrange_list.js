
	function deleteExam_arrangement(id){
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
				window.location.href="ServletExaminationArrangement?option=find";  
               // document.getElementById("ticket").innerHTML=resObj;
				}
		}
		
		var url = "ServletExaminationArrangement?option=examDelete&arrangement_id="+id;
		del.open("GET",url,true);
		del.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		del.send();
	};
	
	/*function modifyExam(str){
		var modifyArrange;
		alert("确认发布?");
		if(window.XMLHttpRequest){
			modifyArrange = new XMLHttpRequest();
		}else{
			modifyArrange = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		modifyArrange.onreadystatechange = function(){
			if(modifyArrange.readyState == 4 && modifyArrange.status == 200){
				var resObj=modifyArrange.responseText; //html用文本形式接收       
				alert(resObj);
				window.location.href="ServletExaminationArrangement?option=find";  
               // document.getElementById("ticket").innerHTML=resObj;
				}
		}
		
		var url = "ServletExaminationArrangement?option=modify&arrangement_id="+str;
		modifyArrange.open("GET",url,true);
		modifyArrange.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		modifyArrange.send();
	};*/
	function releaseExam_arrangement(str){
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
				window.location.href="ServletExaminationArrangement?option=find";  
               // document.getElementById("ticket").innerHTML=resObj;
				}
		}
		
		var url = "ServletExaminationArrangement?option=release&arrangement_id="+str;
		rel.open("GET",url,true);
		rel.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		rel.send();
	}
