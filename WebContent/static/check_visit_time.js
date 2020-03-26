
//检查访问时间，如果url里没有t参数或者访问时间超过设定时间值，则在url里更新t参数，防止页面缓存
(function(){
	function handleDate(date_num){
		if(!date_num){
			return '';
		}
		function fix(num){
			return num<10?('0'+num):num;
		}
		var d = new Date(date_num);
		var month=fix(d.getMonth()+1);
		var date=fix(d.getDate());
		var h=fix(d.getHours());
		var m=fix(d.getMinutes());
		return [d.getFullYear(),month,date].join('-');
	}
	var s=location.search.substring(1).split('&');
	var t=0;
	for(var i=0;i<s.length;i++){
		var ss=s[i].split('=');
		if(ss[0]=='t'){
			t=ss[1];
			break;
		}
	}
	//以日期yyyy-MM-dd为格式做判断，日期不相等则重新刷新页面
	if(t==0||handleDate(new Date().getTime())!=handleDate(parseInt(t))){
		location.href=location.pathname+'?t='+new Date().getTime();
	}
})();
