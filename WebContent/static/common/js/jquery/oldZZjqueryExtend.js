//从旧的jquery.js文件中抽出的非 jquery主文件里 的方法
jQuery.extend({
//	trim: function( text ) {
//		// 去除空白字符
//		return (text || "").replace(/^(\s|\u00A0)+|(\s|\u00A0)+$/g, "" );
//	},
	
	//重写的高度调整方法，非旧蜘蛛网的autoHeight方法
	autoHeight:function(frm){
		var ifr=top.document.getElementById("sub_main");
		if(!ifr){
			return;
		}
		var h=$(document.body).height();
		h=(h<500?500:h)+100;
		ifr.style.height = h+"px";//调整iframe高度
		var subDiv=$(parent.document.body).find(".main-area").find(">div");
		var h2=0;
		for(var i=0;i<subDiv.length;i++){
			var sh=subDiv.eq(i).height();
			if(h2<sh){
				h2=sh;
			}
		}
		parent.document.body.style.height=50+h2+"px";   
	},
	
	/**
 	* 根据参数名称获取参数值
 	* eg:?p1=123&p2=456
 	* 当name为p1时返回123，为p2时返回456
	* @param name 参数名称，不区分大小写
 	* @return 返回参数名称对应的值
	* @author chenxiang
 	*/
	getParam:function(name) {
    	var r = (location.search+location.hash).match(new RegExp("[\?\&\#]" + name + "=([^\&|\#]*)(\&|\#?)", "i"));
    	return r?decodeURI(r[1]):null;
	},

	/**
 	* 根据参数名称, 窗口地址获取参数值
	* 当name为p1时返回123，为p2时返回456
 	* @param name 参数名称，不区分大小写
 	* @param location 窗口的地址对象，不区分大小写
 	* @return 返回参数名称对应的值
 	*/
	getParamByLoc:function(name, loc) {
   	 	var r = (loc.search+loc.hash).match(new RegExp("[\?\&\#]" + name + "=([^\&|\#]*)(\&|\#?)", "i"));
    	return r?decodeURI(r[1]):null;
	},
	
	put:function(url, data, callback, type, async){
		if ( jQuery.isFunction( data ) ) {
			type = type || callback;
			callback = data;
			data = {};
		}
		
		if(typeof(type)=="boolean"){
			async=type;
			type="json";
		}

		return jQuery.ajax({
			type: "PUT",
			url: url,
			data: data,
			success: callback,
			dataType: type||"json",
			async: async===false?false:true
		});
	},
	
	del:function(url, data, callback, type, async){
		if ( jQuery.isFunction( data ) ) {
			type = type || callback;
			callback = data;
			data = {};
		}
		
		if(typeof(type)=="boolean"){
			async=type;
			type="json";
		}

		return jQuery.ajax({
			type: "DELETE",
			url: url,
			data: data,
			success: callback,
			dataType: type||"json",
			async: async===false?false:true
		});
	},
	/**
 	* 显示日期控件，调用方法如下：
 	* $.calendar('start_time',{format:'H-M-S'})  显示年月日时分秒 yyyy-mm-dd HH:MM:SS
 	* $.calendar('start_time')  显示年月日 yyyy-mm-dd
 	* 注：调用此方面的页面一定要引用'calendar.css'样式。
 	* @param id 容器对象或容器ID
 	* @param options 日期格式，如：'H-M-S'显示年月日时分秒 2008-09-08 19:47:45
 	* options={
 	* 		minDate:Number|Date|String,		//当传入的是字符串时，格式必须为:yyyy-mm-dd|yyyy-mm-dd HH:MM:SS.MS
	* 		maxDate:Number|Date|String,		
 	* 		format:'H-M-S'|""|null,			//'H-M-S'-显示年月日时分秒 2008-09-08 19:47:45，其他显示年月日 2008-09-08
 	* 		yearRange: '-10:+10'			//显示年的区域，与minDate，maxDate有一定的互斥，如：当minDate存在时-10无效
 	* }
 	* 依赖文件
 	* <script type="text/javascript" src="/js/ext/jquery.calendar.js"></script>
 	* <link href="/css/calendar.css" type="text/css" rel="stylesheet" />
 	*/
	calendar: function(id,options){
		var minDate=options.minDate?typeof(options.minDate)=="object"?options.minDate:new Date(Date.parse((""+options.minDate).replace(/-/g,"/"))):null;
		var maxDate=options.maxDate?typeof(options.maxDate)=="object"?options.maxDate:new Date(Date.parse((""+options.maxDate).replace(/-/g,"/"))):null;
		var format=options.format;
		popUpCal.regional['zh-cn'] = {
			minDate:minDate,
			maxDate:maxDate,
			clearText: '清除', 
			closeText: '关闭',
			prevText: '<上月', 
			nextText: '下月>', 
			currentText: '今天',
			dayNames: ['日','一','二','三','四','五','六'],
			monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			dateFormat: 'YMD-'
		};
			
		if(options.yearRange)popUpCal.regional['zh-cn'].yearRange=options.yearRange;
			
		popUpCal.setDefaults(popUpCal.regional['zh-cn']);
		var obj= typeof id =="string"? "#"+id:id;
		if(format){
			$(obj).calendar("",format);
		}else{
		    $(obj).calendar();
		}
		$(obj).focus();
	},
	
/**
 * 分页工具条
 * @param ID DIV标识ID或DIV对象
 * @param total	记录总数
 * @param pagesize	每页显示的记录数（默认为20条记录）
 * @param curpage 当前页（默认为第一页）
 * @param action	点击页码action（回调函数）
 * 依赖文件
 * <script type="text/javascript" src="/js/ext/jquery.pagebar.js"></script>
 * <link href="/css/pagebar.css" type="text/css" rel="stylesheet" />
 */
	pageBar: function(ID,total,pagesize,curpage,action,simple){
		var defPagesize=20,defCurpage=1;
		simple=action===true||curpage===true||simple===true;
			
		if ( jQuery.isFunction( pagesize ) ) {
			action = pagesize;
			pagesize = defPagesize;
			curpage = defCurpage;
		} else if (jQuery.isFunction( curpage )){
			action = curpage;
			curpage = defCurpage;
		}
			
		var callback=function(pageIndex){
			$.fn.setCurrentPage(this,pageIndex);
			action(pageIndex)
		}
		var render=typeof(ID)=="string"?$("#"+ID):$(ID);
		var setting={
			//pagebar 对象div
			renderTo: render,
            //总页码
			totalpage: Math.ceil(total/pagesize),
			//当前页码
			currentPage: curpage,
			//是否显示简单形式,简单形式：“首页、上一页、下一页、尾页”
			simple:simple,
			//点击页码action
			onClickPage : callback
		}
		$.fn.jpagebar(setting);
		$.autoHeight(window);
	},
	
	//==================弹出框==================
	//依赖文件<script type='text/javascript' src='/common/dialog/dialog.js'></script>
	/**
	 * dialog
	 * @param option dialog参数，请注意参数的大小写，参照setting
	 * @param callback 确定回调函数，替换option中的OKEvent
	 * @author chenxiang
	 */	 			
	dialog:function(option,callback){
		var diag=new Dialog(option);
		
		if ( jQuery.isFunction( callback ) ) 
			diag.OKEvent = callback;//点击确定后调用的方法
		
		diag.show();
		return diag;
	},
	/**
 	* alert
 	* @param msg 提示消息
	 * @param option dialog参数，请注意参数的大小写，参照setting
	 * @param callback 回调函数
	 * @author chenxiang
	 */
	alert:function(msg,option,callback){
		return Dialog.alert(msg,option,callback);
	},
	/**
 	* 确定正确的信息
 	* @param msg 提示消息
 	* @param option dialog参数，请注意参数的大小写，参照setting
 	* @param callback 回调函数
 	* @author chenxiang
 	*/
	ok:function(msg,option,callback){
		return Dialog.ok(msg,option,callback);
	},
	/**
 	* 严重错误的信息
 	* @param msg 提示消息
 	* @param option dialog参数，请注意参数的大小写，参照setting
 	* @param callback 回调函数
 	* @author chenxiang
 	*/
	error:function(msg,option,callback){
		return Dialog.error(msg,option,callback);
	},
	/**
 	* confirm
 	* @param msg 提示消息
 	* @param option dialog参数，请注意参数的大小写，参照setting
 	* @param callback 回调函数
 	* @author chenxiang
 	*/  
	confirm:function(msg,option,callback){
		return Dialog.confirm(msg,option,callback);
	},
	/**
 	* prompt
 	* @param content 默认内容
 	* @param option dialog参数，请注意参数的大小写，参照setting
 	* @param callback 回调函数
 	* @author chenxiang
 	*/ 
	prompt:function(content,option,callback){
		return Dialog.prompt(content,option,callback);
	},
	//=======================================
	
	
	/**
 	* 操作cookie，有三种操作方式（设置cookie，获取cookie，删除cookie）
 	* 1.当传value参数时为设置cookie操作；
 	* 2.当传入name前带有减号“-”时表示删除名称为name的cookie
 	* 	 注：要清除多个cookie时，多个带“-”的name间用“,”隔开
 	* 3.当只传name时获取名称为name的cookie值
 	* 4.不传任何参数时清除所有cookie
 	* @param name cookie名称
 	* @param value cookie名称对应的值
 	* @param days cookie保存的天数，可以为小数
 	* @author chenxiang
 	*/
	cookie:function(name,value,days){
		var set=function(name,value,days){
			var str= name + "="+ encodeURIComponent(value) + "; path=/";
			if (typeof days == 'number') {
	    		var exp  = new Date();    //new Date("December 31, 9998");
	    		exp.setTime(exp.getTime() + days*24*60*60*1000);
	    		str+="; expires=" + exp.toGMTString();
			}
	   	 document.cookie = str;
		}
	
		var get=function(name){
			var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	   		 return arr?decodeURIComponent(arr[2]):null;
		}
	
		var dels=function(){
			var arrCookie = document.cookie.split(";");
			for(var i=0,l=arrCookie.length; i<l; i++)set(arrCookie[i],"",-1);
		}

		if(!name||name=="-"){
			dels();
			return;
		}
	
		if(value)//设置cookie
		{
			set(name,value,days)
		}else if(!value && value!==undefined){
			set(name,"",-1)
		}else if(name.indexOf("-")==0)//删除cookie
		{
			var arrCookie = name.split(",");
			for(var i=0,l=arrCookie.length; i<l; i++)
				if(arrCookie[i].indexOf("-")==0)
					set(arrCookie[i].substr(1),"",-1)
		}
		else//获取cookie      
		{
	   	 return get(name)||"";
		}
	},
	
	download:function(url,param){
		if(param&&typeof param!="string"){
			param = jQuery.param(param);
		}
		var orgId = 0;
		var groupId = 0;
		if(param){
			var str = param.split("&");
			orgId = (str[0].split("="))[1];
			groupId = (str[1].split("="))[1];
		}
		var form=$("#ajaxDownloadTarget");
		if(form.length==0){
			form=$("<form id='ajaxDownloadTarget' method='get' target='ajaxDownloadTarget'><iframe name='ajaxDownloadTarget' width='0' height='0' frameborder='0'></iframe><input type='hidden' name='orgId' value="+orgId+" /><input type='hidden' name='groupId' value="+groupId+" /></form>")
			form.appendTo("body");
		}	
		form.attr("action",url);
		form.submit();
	},
	/*
	 * 判断账号是公司还是个人: org公司  person个人
	 * options={
	 * 	orgId:xxxxxx		//公司id
	 * }
	 * callback返回 {accountType:"org"|"person"}
	 */
	zzAccountType:function(options,callback){
		if(!options.orgId){
			callback();
			return;
		}
//		options.orgId="9";
		var strrr=[];
		var str=(options.orgId+"").split("");
		var r=[];
		var c=0;
		while(str.length>0){
			var temp=[];
			var preNum=0;
			for(var i=0;i<str.length;i++){
				var cNum=parseInt((preNum+str[i]),10);
				var tempR=((cNum/2)+"").split(".")[0];
				(tempR=="0"&&temp.length==0)?null:temp.push(tempR);
				preNum=(cNum%2);
				
			}
			strrr.push(temp.join(""));
			str=temp;
			r.push(preNum);
			c++;
		}
//		alert(r.reverse().join("")+"_"+c+"_"+options.orgId+"____"+strrr.join(","));
//		console.log(r.reverse().join(""));
		var type={"11":"org","10":"person"};
		r.reverse();
		callback({accountType:type[parseInt(r.slice(4,8).join("")+r.slice(0,4).join(""),2).toString(10)]});
	}
});