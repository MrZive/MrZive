(function(requirejs){
	requirejs.config({
	    "baseUrl": "/MrZive/static/common/js",
	    "paths": {
			"check_visit_time": "../../check_visit_time",
			"app_client_index": "../../app_client_index",
			"jquery.oldZZjqueryExtend":"jquery/oldZZjqueryExtend",
			"jquery.dialog":"jquery/dialog/dialog",
			"jquery.lhgcalendar":"jquery/lhgcalendar/lhgcalendar.min",
			"jquery.lightbox":"jquery/lightbox/lightbox.min",
			"baidu.echarts":"other/echarts.common.min",
			"zzapp.pageloader":"zz_app/page_loader.js?v=161201",
			"zzapp.datalist":"zz_app/data_list.js?v=161217",
			"zzapp.groupTree":"zz_app/group_treeV2",
			"zzapp.cateTree":"zz_app/categoryTree",
			"zzapp.html5FileUploader":"zz_app/html5FileUploader.js?v=161123",
			"zzapp.validator":"zz_app/zz_validator",
			"zzapp.pagebar":"zz_app/zz_pagebar",
			"sp_shenhua.selector":"other/sp_shenhua/selector.js?v=161118",
			"sp_shenhua.winCreator":"other/sp_shenhua/winCreator",
			"kindeditor":"other/kindeditor-4.1.10/kindeditor",
			"webuploder":"other/webupload/webuploader.min",
			"zh_CN":"other/kindeditor-4.1.10/lang/zh_CN",
			"none":"nonedir"
			
				
	    },
	    "shim": {
	    	'app_client_index':['jquery/jquery.min']
	    	,'zzapp.groupTree':['jquery/dialog/dialog']
	    	,'zzapp.cateTree':['jquery/dialog/dialog']
	    	,'zzapp.datalist':['zz_app/zz_pagebar']
	    }
	});
	
	requirejs(["check_visit_time","app_client_index"]);
	
})(requirejs);


