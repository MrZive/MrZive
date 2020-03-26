define(["jquery","zzapp.pageloader"], function($) {

$(function(){
	console.log("zive");
	$("#left").find("li[name]").click(function(){
		var name = $(this).attr("name");
		var options={
				mainJQ:$("#content"),
				basicPath:'/MrZive/view/ftl/',
				tplFile:'tpl.html',
				tplName:'apps_main_frame',
				js:'tpl.js',
				cover:true,
				initParams:{
					name:name
				}
			};
		$.initApp(options);
	});
});
});