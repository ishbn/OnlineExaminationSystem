var timstr; 
var _timer; 
  
	function timer(str){
		timstr = str;
		 timerfunc();//调用定时器 
		// document.getElementById('timSpan').innerHTML = timstr; //将目标时间输出 
		
	}

	
	//定时器方法 
	timerfunc = function(){ 
		 _timer = setInterval('tfunc()',1000);//生成定时器 
		} 
	  
//时间处理方法 
tfunc = function(){ 
	 var ntim = new Date(); //当前时间戳 
	 var _tm = timstr.replace(/-/g,'\/'); //目标时间字段串里的-替换成/,格式化的需要 
	 var ftim = new Date(_tm); //格式化目标时间 
	 var rs = timGap(ntim.getTime(),ftim.getTime()); //调用取时间差方法 
	 var _str = rs.d+'天'+rs.h+'小时'+rs.m+'分钟'+rs.s+'秒'; //将返回的数据拼接字符串 
	 document.getElementById('gap').innerHTML = _str; //输出 
	} 
  
//取时间差方法 
timGap = function(ntim,ftim){ 
	 var date3 = ftim - ntim; //时间差值毫秒数 
	  
	 var days = Math.floor(date3/(24*3600*1000)); //取天数 
	  
	  
	 var level1 = date3%(24*3600*1000);//取天数后剩下的毫秒数 
	 var hours = Math.floor(level1/(3600*1000)); //取小时数 
	  
	 var level2 = level1%(3600*1000);//取小时后剩下的毫秒数 
	 var minutes = Math.floor(level2/(60*1000));//取分钟 
	  
	 var level3 = level2%(60*1000);//取分钟后剩下的毫秒数 
	 var seconds = Math.floor(level3/1000);//取秒 
  
	 //定义对象 
	 var tim = {}; 
	 //赋值 
	 tim['d'] = days; 
	 tim['h'] = hours; 
	 tim['m'] = minutes; 
	 tim['s'] = seconds; 
	 //console.log(tim); 
	 return tim; //返回数据 
} 