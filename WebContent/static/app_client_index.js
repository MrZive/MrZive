define(["jquery","jquery.oldZZjqueryExtend","zzapp.pageloader"], function($) {

$(function(){
	var APP_STATIC_FILE_BASIC_PATH='/MrZive/static/';
	
	$.autoHeight=function(){};
	$.ajaxSetup({
		contentType: "application/x-www-form-urlencoded; charset=utf-8"		//手动设置所有的ajax请求为ut8（防止乱码）
	 	,error: function(jqXHR, textStatus, errorThrown){  
            switch (jqXHR.status){
            	case(401):  
                    alert('未登录或登陆状态已失效！');
                     window.location.href= window.location.href;
                    break;  
                case(403):  
                    alert('无权限执行此操作');  
                    break; 
                case(500):  
                    alert("服务器系统内部错误");  
                    break;
                case(404):  
                    break;
                default:
                	if(textStatus!='abort'){
                		alert("未知错误");  
                	}
            }  
        }   
	 });
	var flag=false;
	$('#login').click(function(){
		startApp({username:$("#userSerialNo").val,password:$("#password").val()});
	});
	
	function startApp(params){
		var options={
			mainJQ:$("#main"),
			basicPath:APP_STATIC_FILE_BASIC_PATH+'apps_main_frame/',
			tplFile:'tpl.html',
			tplName:'apps_main_frame',
			js:'frame.js',
			cover:true,
			initParams:{
				loginType:params.username,
				password:params.password
			}
		};
		$.initApp(options);
	}
});


});