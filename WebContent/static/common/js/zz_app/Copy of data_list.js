

$(function(){
	
	$.extend({
		create_zz_table:function(options){
			return new zzTable(options);
		}
	});
	
	
	/*
	 * 初始化参数
	 * options={
	 * 		ct:数据显示容器
	 * 		,attrTable:[{name:'name',title:'标题',valHandler:function(data){},asyncHandler:function(callback){callback(['','',''])}},{},{}]
	 * 		,attrList:[[{}],[{},{}],[{},{}]]
	 * 		,params:{pageSize:10(每页数据，必须),pageNum:1(页码，必须),...其他参数}
	 * 		,getData:function(params,callback){   	//获取数据的方法,必须
	 * 			//调用callback向表格传送结果数据
	 * 			callback({total:数据总数,row:数据数组,pageSize:每页显示数,pageNum:当前页码});
	 * 		}
	 * 		,dataLoaded:function(data){				//数据显示完毕后的回调
	 * 			data={oriData:原始数据数组,};
	 * 		}
	 * }
	 * 
	 * attrTable|attrList 说明：
	 * attrTable为一个一维数组，数组的每一个对象是表格每一列的配置参数;
	 * attrTable为一个二维数组，第一级数组的每一个元素为一行,第二级数组是每一行显示的数据
	 * 	，例如：attrList:[[{title:编号}],[{title:名称},{title:日期}],[{title:备注}]]，会显示成-
	 * 			--------------------------------------------
	 * 			编号：xxx
	 * 			名称：xxx				日期：xxxx
	 * 			备注：xxx
	 * 			--------------------------------------------
	 * 
	 * name 定义名称，对应原始数据的字段名称
	 * title 对于表格，是定义表格头部名称；对于列表，是定义前缀名称
	 * mainAttr 定义是否是主要属性，设置为true时，表示其不能被取消显示
	 * valHandler 自定义处理数据的函数，每一行数据都会执行一次这个回调方法，参数data是原始的行数据，处理该行的数据，然后return处理后的值
	 * asyncHandler	异步数据获取函数,当需要显示的数据中不是单纯的基础数据，而是包含一些比较耗资源的查询数据时，利用该方法可以先显示基础数据，再显示其他数据，
	 * 	比如当有些列的数据是做统计显示，会耗费比较长的时间，我们可以先把基础数据快速的显示出来，然后再发起另外一个请求去获取统计数据，之后更新该列的显示数据，
	 * 	asyncHandler是一个回调函数，从内部回传一个callback方法，你必须调用该callback向内部传送一个数组，该数组的每一个元素对应每一行的数据，
	 *  假设数据有三行，方法定义实例：
	 * 	function(callback){
	 * 		callback(['第一行异步数据','第二行异步数据','第三行异步数据']);
	 * 	}
	 * visible:true|false 设置是否显示，默认值true
	 * event:{click:function(data){}}	定义事件处理方法，目前只有click事件可用，内部向函数传回一个对象，该对象为 {obj:被点击的元素,item:被点击的行数据,util:{htmlEncode:防xss攻击转义函数}}
	 * 
	 * attrList专有：
	 * multiLine 设置运行多行显示，当其单独占一行时有效
	 * 
	 * 公共方法：
	 * loadData(params): 执行数据加载
	 * 	调用该方法后，内部会根据params参数重置options.params里的值，最后调用options.getData并将被重置后的options.params传给getData
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	function zzTable(options){
		this.opt=options;
		var _self=this;
		_self.getData=_self.opt.getData;
		_self.dataLoaded=_self.opt.dataLoaded||function(){};
		_self.__table_ori_data=null; //保存当前数据数组
		_self.__xhr=[];
		_self["innerObj"]={selectAll:$("<input "+(_self.opt.selectType=="single"?"style='display:none'":"")+" type='checkbox' />")};
		$(_self.opt.ct).append(_self._initSettingPanel()).append(_self._initSettingOperator()).append(_self._initTable()).find("[zz_t_item]").each(function(i,v){
			_self["innerObj"][$(v).attr("zz_t_item")]=$(v);
			
		});
		_self["innerObj"]["mode_switch_panel"].find("[mode_switch="+_self.opt.mode+"]").css({"background":"white","color":""});
		_self._reloadHeader();
		_self._bindDataListEvent();
		_self["innerObj"]["label_visible_num"].text(_self.innerObj.settingOperator.find('[oname="list_setting"]:first input:checked').length);
		
		//绑定数据列表的全选按钮和选择按钮点击事件
		_self["innerObj"]["data_list"].on("click","input[type=checkbox]",function(){
			var jq=$(this).parents("tr:first");
			if(this.checked){
				jq.addClass("selected");
			}else{
				jq.removeClass("selected");
				_self["innerObj"]["selectAll"].attr("checked",false);
			}
		});
		_self["innerObj"]["selectAll"].on("click",function(){
			var subitem=_self["innerObj"]["data_list"].find("input[type=checkbox]");
			if(this.checked){
				subitem.each(function(i,v){
					v.checked=true;
					$(v).parents("tr:first").addClass("selected");
				});
			}else{
				subitem.each(function(i,v){
					v.checked=false;
					$(v).parents("tr:first").removeClass("selected");
				});
			}
		});
		
	}
	zzTable.prototype={
		loadData:function(params){
			var _self=this;
			$.each(_self.__xhr,function(){
				this.abort();
			});
			_self.__xhr=[];
			if(params){
				for(var key in params){
					_self.opt.params[key]=params[key];
				}
			}
			_self["innerObj"]["selectAll"].attr("checked",false);
			_self["innerObj"]["data_list"].html("<tr><td colspan='"+_self["innerObj"]["header"].attr("header_ths")+"'>正在加载...</td></tr>");
			var xhr=_self.getData(_self.opt.params,function(result){
				_self._createDataList(result.row);
				_self._createPagebar(result.total,result.pageSize,result.pageNum,10);
			});
			xhr&&xhr.abort?_self.__xhr.push(xhr):null;
		}
		,getSelectedItem:function(){
			var _self=this;
			var selected=[];
			var ori=_self.__table_ori_data;
			_self["innerObj"]["data_list"].find("input:checked").each(function(){
				selected.push(ori[$(this).attr("z_n")]);
			});
			return selected;
		}
		//绑定数据列表的事件处理
		,_bindDataListEvent:function(){
			var _self=this;
			var tableEvents={};
			var listEvents={};
			$.each(_self.opt.attrTable,function(i,v){
				v.event?(tableEvents[v.name]=v.event):null;
			});
			$.each(_self.opt.attrTable,function(i,v){
				v.event?(listEvents[v.name]=v.event):null;
			});
			_self["innerObj"]["data_list"].on("click","[z_f_l]",function(event){
				var jq=$(event.target);
				var e=listEvents[jq.attr("z_f_l")];
				e?e["click"]?e["click"]({obj:this,event:event,item:_self.__table_ori_data[jq.parents("tr:first").attr("z_n")],util:{htmlEncode:htmlEncode}}):null:null;
				
			}).on("click","[z_f_t]",function(event){
				var jq=$(event.target);
				var e=tableEvents[jq.attr("z_f_t")];
				e?e["click"]?e["click"]({obj:this,event:event,item:_self.__table_ori_data[jq.parents("tr:first").attr("z_n")],util:{htmlEncode:htmlEncode}}):null:null;
			});
		}
		,_initTable:function(){
			var _self=this;
			var html=["<table class='zz-table'><thead><tr zz_t_item='header'>"];
			html.push("</tr></thead><tbody zz_t_item='data_list'></tbody><tfoot><tr><td zz_t_item='pagebar' colspan='100'></td></tr></tfoot></table>");
			var jq=$(html.join(""));
			jq.find("th:first").append(_self["innerObj"]["selectAll"]);			
			return jq;
		}
		,_reloadHeader:function(){
			var _self=this;
			_self["innerObj"]["data_list"].html("");
			var html=["<th></th>"];
			if(_self.opt.mode=="list"){
				html.push("<th _zz_main_attr='true'></th>");
			}else{
				$.each(_self.opt.attrTable,function(i,v){
					if(v.visible!==false){
						html.push("<th "+(v.mainAttr?"_zz_main_attr='true'":"")+">"+v.title+"</th>");
					}
				});
			}
			var ths=$(html.join(""));
			ths.eq(0).append(_self["innerObj"]["selectAll"]);
			_self["innerObj"]["header"].html(ths).attr("header_ths",ths.length);
			_self["innerObj"]["pagebar"].attr("colspan",ths.length);
			tableDragEvent(_self["innerObj"]["header"]);
		}
		,_initSettingPanel:function(){
			var _self=this;
			var modeStyle="style='display:inline-block;*display:inline;padding:0 5px;background:gray;cursor:pointer;color:white;'";
			var settingPanel=$('<div zz_t_item="settingPanel" o-end="" style="background:#eee;padding:2px;border:1px dotted #dbcfcf;border-bottom-width:0;">'+
				'<div zz_t_item="mode_switch_panel" style="display:inline-block;float:right;background:white;">'+
					'<div mode_switch="table" '+modeStyle+'>表格</div>'+
					'<div mode_switch="list" '+modeStyle+'>列表</div>'+
				'</div>'+
				'<div>'+
					'设置：'+
					'<a setting_button="pageSize_setting" style="margin-right:15px;" href="javascript:void(0);">每页显示(<span zz_t_item="label_page_size">'+_self.opt.params.pageSize+'</span>)</a>'+
					'<a setting_button="list_setting" style="'+(_self.opt.mode=='table'?'':'display:none;')+'margin-right:15px;" href="javascript:void(0);">列(<span zz_t_item="label_visible_num"></span>/'+_self.opt.attrTable.length+')</a>'+
					'<a setting_button="list_setting_2" style="display:none;margin-right:15px;" href="javascript:void(0);">列(<span zz_t_item="label_visible_num2"></span>/'+_self.opt.attrList.length+')</a>'+
				'</div>'+
			'</div>');
			
			settingPanel.on("click","[setting_button]",function(){
				var jq=$(this);
				_self["innerObj"]["settingOperator"].find(">[oname]").each(function(i,v){
					_self["innerObj"]["settingOperator"].show();
					if($(v).attr("oname")==jq.attr("setting_button")){
						$(v).show();
					}else{
						$(v).hide();
					}
				});
			}).on("click","[mode_switch]",function(){
				var jq=$(this);
				jq.parent().find("[mode_switch]").css({"background":"gray","color":"white"});
				jq.css({"background":"white","color":""});
				var button=jq.parent().parent().find("[setting_button]");
				var mode=jq.attr("mode_switch");
				if(mode=="table"){
					button.each(function(i,v){
						var attr=$(this).attr("setting_button");
						if(attr=="pageSize_setting"||attr=="list_setting"){
							$(this).show();
						}else{
							$(this).hide();
						}
					});
				}else{
					button.each(function(i,v){
						var attr=$(this).attr("setting_button");
						if(attr=="pageSize_setting"){
							$(this).show();
						}else{
							$(this).hide();
						}
					});
				}
				_self.opt.mode=mode;
				_self._reloadHeader();
				_self.loadData();
			});
			return settingPanel;
		}
		,_initSettingOperator:function(){
			var _self=this;
			var settingOperator=['<div zz_t_item="settingOperator" style="padding:5px;display:none;background:#eee1;border:1px dotted #dbcfcf;border-top-width:0;"><div oname="pageSize_setting">'+
				'每页显示数据&nbsp;<input style="border:1px solid #C0C0C0;position:relative;top:-2px;width:40px;" type="text"/>&nbsp;行&nbsp;'+
				'<br /><br />'+
				'<a zz_button="pageSize_setting" href="javascript:void(0);" class="btn">确定</a> <a zz_button="cancel" href="javascript:void(0);" class="btn">关闭</a>'+
			'</div><div oname="list_setting">'];
			$.each(_self.opt.attrTable,function(i,v){
				settingOperator.push('<div style="display:inline-block;margin-right:15px;"><input '+(v.mainAttr?'disabled':'')+' '+(v.visible==false?"":"checked")+' type="checkbox"/>&nbsp;'+v.title+'</div>');
			});
			settingOperator.push('<br /><br /><a zz_button="list_setting" href="javascript:void(0);" class="btn">确定</a> <a zz_button="cancel" href="javascript:void(0);" class="btn">关闭</a></div></div>');
			var op=$(settingOperator.join(""));
			op.on("click","[zz_button]",function(){
				var jq=$(this);
				var bname=jq.attr("zz_button");
				if(bname=="cancel"){
					_self["innerObj"]["settingOperator"].hide();
					return;
				}
				var m={pageSize_setting:pageSize_setting,list_setting:list_setting};
				m[bname]?m[bname]():null;
				function pageSize_setting(){
					var pageSize=parseInt(jq.parents("[oname]:first").find("input:first").val());
					if(pageSize>0){
						_self.loadData({pageNum:1,pageSize:pageSize});
						_self["innerObj"]["label_page_size"].text(pageSize);
					}
				}
				function list_setting(){
					var count=0;
					jq.parents("[oname]:first").find("input[type=checkbox]").each(function(i,v){
						_self.opt.attrTable[i].visible=v.checked;
						v.checked?++count:0;
					});
					_self["innerObj"]["label_visible_num"].text(count);
					_self._reloadHeader();
					_self.loadData();
				}
			});
			return op;
		}
		,_createDataList:function(row){
			var _self=this;
			var h=[];
			var tableOriData=row;
			function getStyle(css){
				var s=[];
				for(var key in css){
					s.push(key+":"+css[key]);
				}
				return s.length>0?("style='"+s.join(";")+"'"):"";
			}
			var it=_self.opt.selectType!="single"?'type="checkbox"':('type="radio" name="'+new Date().getTime()+'"');
			var asyncF={};
			if(_self.opt.mode=="list"){
				var per=[];
				$.each(row,function(i,v){
					h.push('<tr z_n="'+i+'"><td><input z_n="'+i+'" '+it+' /></td><td>');
					$.each(_self.opt.attrList,function(i2,v2){
						if(per.length<=i2){
							per.push(100/v2.length);
						}
						h.push("<p "+(v2.length==1&&v2[0].multiLine==true?"class='multi-line'":"")+" style='line-height: 18px'>");
						$.each(v2,function(i3,v3){
							if(v3.visible!==false){
								v3["asyncHandler"]?asyncF[v3['name']]={h:v3["valHandler"],ah:v3["asyncHandler"]}:null;
								var val=htmlEncode((v3["valHandler"]?v3["valHandler"](v):v[v3['name']])||'');
								h.push('<span style="display:inline-block;*display:inline;width:'+per[i2]+'%">'+
									v3['title']+'&nbsp;:&nbsp;<span title="'+(val)+'" '+getStyle(v3["css"])+' z_f_l="'+v3['name']+'">'+val+'</span></span>');
							}
						});
						h.push("</p>");
					});
					h.push('</td></tr>');
				});
			}else{
				$.each(row,function(i,v){
					h.push('<tr z_n="'+i+'"><td><input z_n="'+i+'" '+it+' /></td>');
					$.each(_self.opt.attrTable,function(i2,v2){
						if(v2.visible!==false){
							v2["asyncHandler"]?asyncF[v2['name']]={h:v2["valHandler"],ah:v2["asyncHandler"]}:null;
							var val=htmlEncode((v2["valHandler"]?v2["valHandler"](v):v[v2['name']])||'');
							h.push('<td title="'+(val)+'" '+getStyle(v2["css"])+' z_f_t="'+v2['name']+'">'+val+'</td>');
						}
					});
					h.push('</tr>');
				});
			}
			_self.__table_ori_data=tableOriData;
			_self["innerObj"]["data_list"].html(h.length>0?h.join(""):("<tr><td colspan='"+_self["innerObj"]["header"].attr("header_ths")+"'>无数据</td></tr>"));
			$.autoHeight();
			for(var key in asyncF){
				(function(key){
					var xhr=asyncF[key]['ah'](function(result){
						var tds=null;
						if(_self.opt.mode=="list"){
							tds=_self["innerObj"]["data_list"].find("[z_f_l='"+key+"']");
						}else{
							tds=_self["innerObj"]["data_list"].find(">tr >td[z_f_t='"+key+"']");
						}
						if(tds.length==result.data.length){
							tds.each(function(i){
								_self.__table_ori_data[i]["__async"]?null:_self.__table_ori_data[i]["__async"]={};
								_self.__table_ori_data[i]["__async"][key]=result.data[i];
								var val=htmlEncode(asyncF[key]['h']?asyncF[key]['h'](_self.__table_ori_data[i]):_self.__table_ori_data[i]);
								$(this).html(val);
							});
						}else{
							tds.text('数据错误???');
						}
					},{oriDataList:_self.__table_ori_data});
					xhr&&xhr.abort?_self.__xhr.push(xhr):null;
				})(key);
			}
		}
		,getData:null
		,_createPagebar:function(total,pageSize,curPage,labelNum){
			var _self=this;
			if(!_self.__pagebar){
				if($.zz_pagebar){
					var config={
						ct:_self["innerObj"]["pagebar"],labelNum:10
						,callback:function(num){
							_self.loadData({pageNum:num});
						}
					};
					_self.__pagebar=$.zz_pagebar(config);
				}else{
					_self["innerObj"]["pagebar"].html("分页不可用！(缺少$.zz_pagebar)");
				}
			}
			_self.__pagebar.refresh({total:total,pageSize:pageSize,curPage:curPage});
		}
		
	}
	
	/*
	 * 字符串编码防止xss攻击
	 */
	function htmlEncode(str){
       var div = document.createElement("div");
       div.appendChild(document.createTextNode(str));
       return div.innerHTML;
  	}
	
	/*
	 * 拖拽调整表格的列宽度
	 */
	function tableDragEvent(tr){
		
		var ths=tr.find("th");
		if(ths.length==0){
			return;
		}
		var widthPer=parseInt((100/ths.length).toFixed(0));
		ths.css("width",widthPer+"%");
		var sum=0,fixDom=null,trWidth=tr.width();
		ths.each(function(i,v){
			var jq=$(v),ieFix=window.ActiveXObject?"background-color:#eee;":"",per=widthPer;
		 	var d=$('<div zz_table_drager=1 style="'+ieFix+'border:0px solid red;right:0;top:0;cursor:e-resize;width:5px;height:100%;position:absolute;"></div>');
			//设置全选按钮所在列宽度
			if(jq.find("input[type=checkbox]").length>0){
				var oriHTML=$("<div style='margin-left:5px;'></div>").append(jq.find("input[type=checkbox]"));
				jq.css("text-align","left").html("").append(oriHTML);
				per=parseInt((2500/trWidth+0.4).toFixed(0));
			}
			jq.css("position","relative").append(d);
			sum+=per;
			if(fixDom==null&&jq.attr("_zz_main_attr")=="true"){
				fixDom=jq;
			}
			jq.attr("zz_per_w",per)
		});
		fixDom=fixDom?fixDom:ths.eq(1);
		var fixper=100-sum+parseInt(fixDom.attr("zz_per_w"));
		fixDom.attr("zz_per_w",fixper);
		ths.each(function(i,v){
			var jq=$(v);
			jq.css("width",jq.attr("zz_per_w")+"%");
		});
		tr.attr('zz_event_binded')?null:tr.on("mousedown","[zz_table_drager]",dragermouseDown).attr('zz_event_binded',true);
		function dragermouseDown(event){
			function getWidth(o){
				return o.width();
			}
			var o=this;
			var td=$(o).parents("th:first");
			var ntd=td.next();
			if(ntd.length==0){return;}
			var d=$(document);
			var lenLimit=getWidth(td)+getWidth(ntd);
			var mx=event.clientX;
			if(o.setCapture){
				o.setCapture();
			}else if(window.captureEvents){
				window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
			}
			var ol=	parseInt(td.attr("zz_per_w"))+parseInt(ntd.attr("zz_per_w"));
			function clear(){
				d.unbind("mousemove",move);
				d.unbind("mouseup",clear);
				if(o.releaseCapture) 
					o.releaseCapture(); 
				else if(window.captureEvents) 
					window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
				var fwp=(td.width()*ol/lenLimit).toFixed(0);
				td.css("width",fwp+"%").attr("zz_per_w",fwp);
				ntd.css("width",(ol-fwp)+"%").attr("zz_per_w",(ol-fwp));
			}
			function move(event){
				window.getSelection?window.getSelection().removeAllRanges():document.selection.empty();
				var nmx=event.clientX;
				var cx=mx-nmx;
				var len=getWidth(td)-cx;
				var len2=getWidth(ntd)+cx;
				if(len>=lenLimit-20){
					len=lenLimit-20;
				}else if(len<20){
					len=20;
				}
				var nlen=lenLimit-len;
				td.css("width",len);
				ntd.css("width",nlen);
				mx=nmx;
			}
			d.mousemove(move);
			d.mouseup(clear);
		}
	}
});