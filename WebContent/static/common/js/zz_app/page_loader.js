/**
 * 页面加载工具by subinghong
 * 集成requirejs做模块化处理20161109
 */
(function($){
	$.extend({
		initZzApp:function(opt){
			new zzApp(opt);
		}
		//引入requirejs模块的初始化方法
		,initApp:function(opt){
			opt.amdMode=true;
			new zzApp(opt);
		}
	});
	
	var tempCache={};
	var jsCache={};
	var isCacheInMem=false;
	

	function GetTemplate2(URI,appVer,callback){
		var template=null;
		if(tempCache[URI]){
			callback(tempCache[URI]);
			return;
		}
		$.ajax({url:URI,success:function(str){
			template={};
			str=str.replace(/[\n\r]/g,"").replace(/>[ ]+</g,"><");
			var re=/<template id="(\w+)">(.*?)<\/template>/g;
			var m=null;
			while(m=re.exec(str)){
				template[m[1]]=m[2];			
			};
			isCacheInMem?tempCache[URI]=template:null;
			callback(template);
		},dataType:"text",data:{c:appVer()},error:function(e){alert(e)}});
	}
	
	var requirejsLoadRecord={};
	function getJs(js,appVer,callback,amdMode){
		if(amdMode){
			appVer=isCacheInMem?appVer:function(){ return new Date().getTime();};
			var jsUrl=js+'?ver='+appVer();
			//requirejs模块动态加载
			require([jsUrl], function(lib){
				callback(lib());
			});
			if(!isCacheInMem){
				if(requirejsLoadRecord[js]){
					//在不缓存的情况下，移除之前加入的同一个脚本文件的标签，防止页面内script标签无限增多(非必要，只是为了页面脚本标签列表整洁，不做处理也没有问题)
					$('head').find('script[data-requiremodule="'+requirejsLoadRecord[js]+'"]').remove();
				}
				requirejsLoadRecord[js]=jsUrl;
			}
		}else{
			//eval动态加载
			var js_method=jsCache[js];
			if(js_method){
				callback(js_method());
			}else{
				$.get(js,{c:appVer()},function(text){
					js_method=eval("(false||function(){return (function(){"+text+"; var references=references||null; return {init:init,references:references||null};})();})");
					isCacheInMem?jsCache[js]=js_method:null;
					callback(js_method());
				},'text');
			}
		}
	}
	
	var appVerCache={};
	function getAppVerFromAjax(basicPath,cb){
		if(isCacheInMem){
			if(appVerCache[basicPath]){
				cb(appVerCache[basicPath]);
				return;
			}
		}
		$.get(basicPath+'zz_app_ver.js',{c:new Date().getTime()},function(ver){
			if(isCacheInMem){
				appVerCache[basicPath]=ver;
			}
			cb(ver);
		});
	}
	
	/*
	 * opt={mainJQ:$('#main'),basicPath:'',tplFile:'',tplName:'',js:''}
	 */
	function zzApp(opt){
		var _self=this;
		this.opt=opt;
		var op={};
		for(var key in opt){
			op[key]=opt[key];
		}
		if(opt.basicPath){
			opt.basicPath+=opt.basicPath.substring(opt.basicPath.length-1)=="/"?"":"/";
		}else{
			opt.basicPath="";
		}
		op.containerJQ=opt.mainJQ;
		if(!op.appVer){
			getAppVerFromAjax(opt.basicPath,function(ver){
				op.appVer=ver;
				_self.loadZzPage(op);
			});
		}else{
			_self.loadZzPage(op);
		}		
	}
	zzApp.prototype={
		loadZzPage:function(options){
			var _self=this;
			var appVer=options.appVer;
			if(typeof appVer!=='function'){
				appVer=(function(appVer){
					return function(){return appVer;};
				})(appVer);
			}
			
			var loadtime=new Date().getTime();
			options["containerJQ"]=options["containerJQ"]?options["containerJQ"]:_self.opt.mainJQ;
			options["containerJQ"][0].__loadtime=loadtime;
			var container=options["containerJQ"][0];
			var offset=options["containerJQ"].offset();
			var width=options["containerJQ"].width();
			
			
			if(options["cover"]){
				container["subPage"]=null;
				options["containerJQ"].html("");
				
			}
			var newPage=$("<div style='display:none;'></div>");
			if(!container["subPage"]){	//如果未定义，则初始化该值
				container["subPage"]=[options["containerJQ"].find(">div")[0]];		//subPage用于储存页面内容数组
				container["flag"]=0;					//flag代表当前页面在subPage数组里的索引值
			}
			
			var htmlTpl=null;
			if(options.tplFile.indexOf("/")!=0){
				options.tplFile=_self.opt.basicPath+options.tplFile;
			}
			GetTemplate2(options.tplFile,appVer,function(tpl){
				htmlTpl=tpl[options.tplName];
				pageInit();
			});
			var js_method_init=null;
			
			if(options.js.indexOf("/")!=0){
				options.js=_self.opt.basicPath+options.js;
			}
			getJs(options.js,appVer,function(method){
				js_method_init=method.init;
				var js_method_references=method.references;
				if(typeof js_method_references == 'function'){
					if(!options.___private){
						options.___private={
							reference:location.pathname
						};
					}
					var f=false;
					$.each(js_method_references()||[],function(){
						var str=this.indexOf("/")==0?this:(_self.opt.basicPath+this)
						f=options.___private.reference==str;
						return !f;
					});
					f?pageInit():alert('loadPage denied :file ( '+options.___private.reference+' ) must defined in the references of ('+options.js+')!');
				}else{
					pageInit();
				}
			},options.amdMode);
			function pageInit(){
				if(js_method_init&&htmlTpl&&options["containerJQ"][0].__loadtime==loadtime){
					
					var container=options["containerJQ"][0];
					var index=container["flag"]+1;				//新加载页面的索引值为 当前页面索引值+1
					if(container["subPage"].length>index){		//如果subPage容器的长度大于index，subPage还有空闲的位置，则将新页面直接放入subPage下标为index的位置
						container["subPage"][index]=newPage;		
					}else{									//现subPage长度小于或等于index，subPage没有空闲的位置，则将新页面push进subPage。
						container["subPage"].push(newPage);
					}	
					$(container).append(newPage);				//将新页面容器pageObj加入到页面；
					
					var zzItem={type:{}};
					newPage.html(htmlTpl).show().find("[zz_item_name]").each(function(i,v){
						var jq=$(v);
						var name=jq.attr("zz_item_name");
						var type=jq.attr("zz_item_type");
						if(type){
							var typeC=zzItem["type"][type];
							if(!zzItem["type"][type]){
								zzItem["type"][type]={};
							}
							zzItem["type"][type][name]=jq;
						}else{
							zzItem[name]=jq;
						}
					});
					if(typeof $.autoHeight=="function"){
						$.autoHeight();
					}
					var prevDiv=$(container["subPage"][container["flag"]]).hide();	//加载完新页面的文档内容后隐藏前一个页面的内容
					container["flag"]=index;				//重新设定subPage容器当前页的索引值
					var pageInitOption={
						zzItem:zzItem,
						pageObj:newPage,
						loadPage:function(opt){
							if(!opt.containerJQ){
								opt.containerJQ=_self.mainJQ;
								opt.cover=false;
							}
							opt.appVer=appVer;
							opt.___private={
								reference:options.js
							};
							_self.loadZzPage(opt);
						},
						initZzApp:function(opt){
							opt.___private={
								reference:options.js
							};
							new zzApp(opt);
						},
						//requireJs加载
						initApp:function(opt){
							opt.___private={
								reference:options.js
							};
							opt.amdMode=true;
							new zzApp(opt);
						},
						//requireJs加载
						load:function(opt){
							opt.amdMode=true;
							if(!opt.containerJQ){
								opt.containerJQ=_self.mainJQ;
								opt.cover=false;
							}
							opt.appVer=appVer;
							opt.___private={
								reference:options.js
							};
							_self.loadZzPage(opt);
						},
						initParams:options.initParams,
						back:function(){			//页面返回函数
							newPage.hide().remove().html("");
							prevDiv.show();
							delete container["subPage"][container["flag"]--];			//重新设定subPage容器当前页的索引值		
							if(typeof $.autoHeight=="function"){
								$.autoHeight();
							}
							this.back=function(){};
							return prevDiv;						//返回前一个页面的Jquery对象
						},
						getMainContainer:function(){
							
							return _self.opt.mainJQ;
						}
					};
					js_method_init(pageInitOption);
					
				}
			}
		}
	}
})(jQuery);

