define(['jquery'], function($) {
return function(){

var APP_STATIC_FILE_BASIC_PATH='/MrZive/static/app_web_client/';

function init(op){
	bindEvent(op)}


function bindEvent(op){
	op.zzItem.left.on("click","[name]",function(){
		$.initApp({
			mainJQ:op.zzItem.app_container,
			appVer:function(){
				return new Date().getTime();
			},
			basicPath:APP_STATIC_FILE_BASIC_PATH+'user/',
			tplFile:'tpl.html',
			tplName:'user_list',
			js:'js/user_list.js',
			cover:true
		});
	});
}

return {init:init};};});