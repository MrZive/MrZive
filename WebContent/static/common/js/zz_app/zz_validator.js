(function(){
	$.extend({
		zz_validator:function(config){
			return new validator(config);
		}
	});
	
	/*
	 * input值验证器
	 * 初始化参数：config={
	 * 	wrapper:$('#xxxxx')									//被验证input的包裹容器—jquery对象,所要验证的input元素需要加上属性zz_validate=xxx
	 * 	,validateFlagAttr:'zz_validate'
	 * 	//验证元素设置列表
	 * 	,validate:{
	 * 		'title':['notNull','amount','numberOnly']
	 * 		'money':['notNull','amount','myValidateMethod']
	 *	}
	 * 	//验证器默认有几个验证方法，如果默认的验证方法不能满足要求，可以自己定义验证方法
	 * 	,validateHandler:{									//自定义验证方法
	 * 		//验证方法示例,在这里定义了验证函数后，可以在validate列表中被使用，如 validata:{'money':['myValidateMethod']}
	 * 		//参数op={val:被验证的值,cb:回调方法返回验证结果}；验证结果全部使用回调返回的原因是为了兼容异步验证和同步验证，可以将值用ajax发送到远程服务端进行验证
	 * 		myValidateMethod:function(op){					
	 * 			var val=$.trim(op.val);
	 * 			//发送异步请求，从服务器获取验证结果
	 * 			$.getJSON('/isThatEnougtForYou',{val:val},funtion(result){
	 * 				if(result==true){
	 * 					//f表示验证结果boolean，通过true，不通过false；tip表示验证结果提示信息
	 * 					cb({f:true,tip:''});
	 * 				}else{
	 * 					//f表示验证结果boolean，通过true，不通过false；tip表示验证结果提示信息
	 * 					cb({f:false,tip:'No!you need to give me more money'});
	 * 				}
	 * 			});
	 * 		}
	 * 	}
	 * ,autoValidate:true|false 			//是否input的blur事件触发验证方法，默认true
	 * ,keyCodeValidate:{keyCode:13} 		//keyCode触发验证事件，keyCode=13是回车事件
	 * 	//自定义的验证完毕提示处理函数，使用方式请参考 默认的内部验证完毕提示处理函数_tipShow
	 * 	//op={
	 * 	//	element:被验证的input元素，type=text时，element是一个jquery对象；type=radio或checkbox时，element是一个数组
	 * 	//	,result:{f:表示验证通过或不通过 boolean,name:当前被验证的元素名称,detail:当前元素验证结果详细列表_数组}	
	 * 	//}
	 * 	,showTip:function(op){}
	 * 	
	 * 	}
	 * }
	 * 
	 * 其它杂项说明:暂无
	 * 
	 * //验证器使用示例，验证input的值是非空并且纯数字：
	 * 
	 * <body>
	 * 	<input zz_validate="theNumber" name="theNumber"/>
	 * </body>
	 * 
	 * var config={
	 * 	wrapper:$('body'),
	 * 	validate:{title:['notNull','numberOnly']}
	 * };
	 * var validator=$.zz_validator(config);
	 * //result={f:表示验证通过或不通过_boolean,detail:所有被验证参数的验证结果_数组,inputVal:被验证参数值集合_对象}
	 * validator.exc(function(result){
	 * 		
	 * });
	 * 		
	 */
	function validator(config){
		var _self=this;
		_self.config=config;
		config.showTip?_self._tipShow=config.showTip:null;	
		config.autoValidate=config.autoValidate===false?false:true;
		
		config.validateHandler=config.validateHandler?config.validateHandler:{};		
		this._getItems=function(){
			var items={};
			//记录验证元素总个数，供最后的exc方法判断是否验证完所有元素，以判断何时调用回调函数返回验证结果
			_self.__vc=0;
			this.config.wrapper.find("[zz_validate]").each(function(){
				var jq=$(this);
				var n=jq.attr("zz_validate");
				
				var t=jq.attr("type");
				if(t=="radio"||t=="checkbox"){
					if(!items[n]){
						items[n]=[];
						++_self.__vc;
					}
					items[n].push(jq);
				}else{
					items[n]=jq;
					++_self.__vc;
				}
			});
			return items;
		}
		this._bindExtEvent();
		
	}
	
	validator.prototype={
		//执行全部验证
		exc:function(callback){
			var _self=this;
			var dom=_self._getItems();
			var r=[];				//验证结果详情列表
			var f=true;				//最终验证结果，只要有单个验证不通过，则false
			for(var key in dom){
				_self._valid(dom[key],key,function(result){
					_self._tipShow({element:dom[result.name],result:result})
					//收集每个验证结果详情
					r.push(result);
					f?f=result.f:null;
					//执行完每个验证后，执行回调函数返回验证结果
					if(r.length==_self.__vc){
						callback?callback({f:f,detail:r,inputVal:_self._getAllVal()}):null;
					}
				});
			}
		}
		//执行单个验证，element被验证的元素，name被验证的元素zz_validate属性名，done回调函数，_valid执行完之后向done函数回传结果数据r={name:name,f:true,detail:[]}
		,_valid:function(element,name,done){
			var _self=this;
			var val=_self.__extractValFromItem(element);
			var r={name:name,f:true,detail:[]};
			if(!_self.config.validate[name]){
				return done?done(r):null;
			}
			var counter=_self.config.validate[name].length;
			$.each(_self.config.validate[name],function(){
				var n=this+"";
				var m=_self.config.validateHandler[n]||validateHandler[n];
				//result={f:验证结果,tip:'验证结果提示信息',fixVal:对被验证元素进行设置新值}
				var c=function(result){
					result.fixVal?element.val(result.fixVal+''):null;
					result['__handlerName']=n;					r.detail.push(result);
					r.f=r.f?result.f:false;
					if(!r.f||(--counter==0&&done)){
						done?done(r):null;
						done=null;
					}
				}
				m?m({val:val,cb:c}):--counter;
				return r.f;
			});
		}
		//获取所有需要验证的值
		,_getAllVal:function(){
			var _self=this;
			var data={};
			var it=_self._getItems();
			for(var key in it){
				data[key]=_self.__extractValFromItem(it[key]);
			}
			return data;
		}
		//获取单个需要验证的值
//		,_getVal:function(name){
//			var _self=this;
//			var v=_self._getItems()[name];
//			return _self.__extractValFromItem(v);
//		}
		,__extractValFromItem:function(item){
			if(item instanceof Array){
				var val=[];
				$.each(item,function(){
					this[0].checked?val.push(this.val()):null;
				});
				return val;
			}else{
				return item.val();
			}
		}
		//绑定失焦验证事件
		,_bindExtEvent:function(){
			var _self=this;
			if(_self.config.autoValidate){
				_self.config.wrapper.on("blur","[zz_validate]",function(){
					var jq=$(this);
					var n=jq.attr("zz_validate");
					_self._valid(jq,n,function(result){
						_self._tipShow({element:jq,result:result});
					});
				});
			}
			if(_self.config.keyCodeValidate){
				var keyCode=config.keyCodeValidate.keyCode||[];
				keyCode=(keyCode instanceof Array)?keyCode:[keyCode];
				_self.config.wrapper.on("keyup","[zz_validate]",function(event){
					var jq=$(this);
					$.each(keyCode,function(){
						if(event.keyCode==this){
							var n=jq.attr("zz_validate");
							_self._valid(jq,n,function(result){
								_self._tipShow({element:jq,result:result});
							});
							return false;
						}
					});
				});
			}
		}
		/*
		 * 默认的内部验证完毕提示处理函数
		 * op={
		 * 	element:被验证的input元素，type=text时，element是一个jquery对象；type=radio或checkbox时，element是一个数组
		 *  result:{f:表示验证通过或不通过 boolean,name:被验证的元素名称,detail:验证结果详细列表_数组}
		 *  
		 * }
		 */
		,_tipShow:function(op){
			var jq=op.element;
			var result=op.result;
			var _self=this;
			var n=result.name;
			_self.tipDom=_self.tipDom?_self.tipDom:{};
			if(!_self.tipDom[n]){
				_self.tipDom[n]=$("<span style='display:inline-block;color:red;'></span>");
				if(jq instanceof Array){
					var p=jq[0].parents("td:first");
					if(p.length==0){
						p=jq[0].parent();
					}
					p.append("&nbsp;&nbsp;").append(_self.tipDom[n]);
				}else{
					jq.after(_self.tipDom[n]);
				}
			}
			var tip=null;
			$.each(result.detail,function(){
				tip=this.f?null:this.tip;
			});
			result.f?_self.tipDom[n].hide():_self.tipDom[n].text(tip).show();
		}
	}
	
	function zztrim(val){
		if(typeof val =="string"){
			return $.trim(val);
		}
		return val;
	}
	
	function regValid(op){
		var val=zztrim(op.val);
		var f=val.length==0||op.reg.test(val);
		op.cb({f:f,tip:f?op.msg.s:op.msg.f});
	}
	
	var validateHandler={
		notNull:function(op){
			var f=zztrim(op.val).length>0;
			op.cb({f:f,tip:f?"":"该项为必"+(op.val instanceof Array?"选":"填")+"项!"});
		}
		//金额格式验证
		,amount:function(op){
			regValid({reg:/^[0-9]*[.]{0,1}[0-9]*$/,val:op.val,cb:op.cb,msg:{f:"输入有误，格式如：88.88",s:""}});
		}
		//邮编格式验证
		,postcode:function(op){
			regValid({reg:/^[1-9]\d{5}$/,val:op.val,cb:op.cb,msg:{f:"邮编格式有误",s:""}});
		}
		//网址格式验证
		,website:function(op){
			regValid({reg:/^([A-Za-z]+:\/\/){0,1}([A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*){0,1}$/,val:op.val,cb:op.cb,msg:{f:"网址格式有误",s:""}});
		}
		//固定（传真）电话号码格式验证
		,tel:function(op){
			regValid({reg:/^[0-9]{7,12}$/,val:op.val,cb:op.cb,msg:{f:"号码格式有误",s:""}});
		}
		//email格式验证
		,email:function(op){
			regValid({reg:/^[A-Za-z0-9]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/,val:op.val,cb:op.cb,msg:{f:"email格式有误",s:""}});
		}
		//手机号码格式验证
		,mobile:function(op){
			regValid({reg:/^[0-9]{5,13}$/,val:op.val,cb:op.cb,msg:{f:"手机格式有误",s:""}});
		}
		//qq号码格式验证
		,qq:function(op){
			regValid({reg:/^[0-9]{4,15}$/,val:op.val,cb:op.cb,msg:{f:"qq格式有误",s:""}});
		}
		//msn号码格式验证
		,msn:function(op){
			regValid({reg:/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/,val:op.val,cb:op.cb,msg:{f:"msn格式有误",s:""}});
		}
		,numberOnly:function(op){
			regValid({reg:/^[0-9]*$/,val:op.val,cb:op.cb,msg:{f:"必须为纯数字",s:""}});
		}
		//判断密码不能头尾
		,password:function(op){
			regValid({reg:/^\s*((?:[\S\s]*\S)?)\s*$/,val:op.val,cb:op.cb,msg:{f:"首尾不能带空格",s:""}});
		}
	};
	
})();
