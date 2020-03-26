
var CategoryTree=function(options,callbackList){
	//内部私有的扩展属性
	this._privateAttr={
		currentId:null
		,parents:{}
	};   	
	this.options={fParentId:"parent_id",fId:"id",fName:"name",fSubCount:"fSubGroupCount",fSubGroup:"fSubGroup",getChildren:""};
	for(var key in options||{}){
		this.options[key]=options[key];
	}	
	
	this.callbackList=callbackList||{};		//回调函数列表
}

CategoryTree.prototype={
	inp:'<input type="text" maxlength="24" />',
	edit:'<li class="${class}  group_tree"><div class="nohitarea"></div>${inp}</li>',
	li:'<li class="${class}" group_id=${group_id} parents="${parents}" parentRoleTypeIdHex="${parentRoleTypeIdHex}"><div class="${hit}"></div>${op}<span  class="${cla}" style="display:block;"   mark="mark" depth="${depth}" groupId="${group_id}" title="${group_name}" >${group_name}&nbsp;&nbsp;<span class="edit-img" style="display:none;"></span></span></li>',
	op:'<div class="op hide edit"><span op="ins" style="display:${ins}">插入同级分组</span><span op="add" style="display:${add}">新增子分组</span><span op="mod"  style="display:${mod}">修改</span><span op="del"  style="display:${del}">删除</span></div>',
	loading:'<span class="loading">正在加载</span>',
	
	init:function(){
		var _self=this;
		var groups=_self.options.groups;
	},
	
	/**
	 * 显示一级目录的节点
	 * @param ID 容器DOM对象或容器ID
	 */
	show:function(ID){
		var _self=this;
		var root={};
		root[_self.options.fId]-0;
		root[_self.options.fParentId]=-98394;
		root[_self.options.fName]="@ROOT";
		root[_self.options.fSubGroup]=_self.options.groups;
		_self.visibleGroupsInfoMap={
			"0":root
		};
		if(ID){
			_self.dom=typeof(ID)=="string"?$("#"+ID):$(ID);
			_self.dom.addClass("treeview");
		}
		this.append(_self.dom,_self.options.groups,_self);	
		
		if(_self.callbackList["afterAppend"]){
			_self.callbackList["afterAppend"](_self.dom,_self); //返回父级阶段对象和树本身的引用
		}	
		if(_self.callbackList["afterInit"]){
			_self.callbackList["afterInit"](_self);		//分组树一级节点显示完成后的回调函数，回传一个tree对象本身的引用
		}		
	},
	
	
	/**
	 * 展开
	 */
	expand:function(node,callback){
		var _self=this;
		if(node.find(">.nohitarea").length>0){
			callback?callback():null;
			return;
		}
		var loaded=node.has("ul").length>0?true:false;
		var p=node.parent();
		if(node.hasClass("collapsable")){
			node.removeClass("collapsable").addClass("expandable");
			callback?callback():null;
			return;
		}else if(node.hasClass("lastcollapsable")){
			node.removeClass("lastcollapsable").addClass("lastexpandable");
			callback?callback():null;
			return;
		}else if(loaded){
			node.hasClass("expandable")?node.removeClass("expandable").addClass("collapsable"):node.removeClass("lastexpandable").addClass("lastcollapsable");
			callback?callback():null;
			return;
		}
		if(!loaded){
			if(typeof(_self.options.getChildren)=="function"&&!node.originData["subLoaded"]){
				_self.options.getChildren(node.originData,function(childrenArray){
					node.originData[_self.options.fSubGroup]=childrenArray;
					node.originData["subLoaded"]=true;
					_self.expand(node);
					callback?callback():null;
				});
				return;
			}
			
			var children=node.originData[_self.options.fSubGroup];
			if(children.length===0){
				node.find(">.hitarea").removeClass("hitarea").addClass("nohitarea");
				node.attr("class",node.attr("class").replace("expandable",""));
				node.removeClass("expandable").removeClass("lastexpandable");
			}
			var a=node.find("a");
			var tmp=a.text();
			a.html(this.loading);
			this.append(node,children,_self);
			a.text(tmp);
		}
		if(node.hasClass("expandable")){
			node.removeClass("expandable").addClass("collapsable");
		}else if(node.hasClass("lastexpandable")){
			node.removeClass("lastexpandable").addClass("lastcollapsable")
		}
		if(!loaded){
			if(_self.callbackList["afterAppend"]){
				_self.callbackList["afterAppend"](node,_self); //返回父级阶段对象和树本身的引用
			}
		}
		callback?callback():null;
	},
	
	/*
	 * 加载分组
	 * node为分组节点对象，data为node节点的子节点数据集合，_self为tree对象本身的一个引用
	 */
	append:function(node,data,_self){
		node.find(">ul").remove();
		var ui=$('<ul oncontextmenu="return false" onselectstart="return false"></ul>');
		node.append(ui);
		data=data||[];
		var len=data.length-1;
		var depth = 0;
		var operate = _self.options.operate;		//显示操作标签的开关	
		var op=_self.op;
		var cla = "green-font cursor";
		var event = "";
		if(node.html()!=null){
			depth = node.find("span[mark='mark']").attr("depth")||0;
		}
		depth++;
		op = op.replace(/\${ins}/g, '');
		op = op.replace(/\${del}/g, '');
		op = op.replace(/\${mod}/g, '');
		op = op.replace(/\${add}/g, '');
		_self.visibleGroupsInfoMap=_self.visibleGroupsInfoMap?_self.visibleGroupsInfoMap:{};
		if(_self.options.sortMode){
			_self.sortGroups(data);
		}
		$.each(data,function(i,item){
			if(node.originData){
				if(node.originData["parents"]){
					item["parents"]=node.originData["parents"]+","+node.originData[_self.options.fId];
				}	
			}else{
				item["parents"]="0";
			}
			_self.visibleGroupsInfoMap[item[_self.options.fId]]=item;
			_self._privateAttr["parents"][item[_self.options.fId]]=item["parents"];
			var cls;
			var hit;
			if(typeof(_self.options.getChildren)=="function"){
				if(item["mh"]){
					if(item["mh"]==0){
						cls=(i<len?"expandable ":"lastexpandable");
					}else{
						cls=(i<len?"":"last");
					}	
				}else{
					cls=(i<len?"expandable ":"lastexpandable");
				}						
				hit="hitarea";
			}else{
				if(item[_self.options.fSubCount]>0){
					cls=(i<len?"expandable ":"lastexpandable");
					hit="hitarea";
				}else{
					cls=(i<len?"":"last");
					hit="nohitarea";
				}
//				cls=item[_self.options.fSubCount]>0?(i<len?"expandable ":"lastexpandable"):(i<len?"":"last");
//				hit=item[_self.options.fSubCount]>0?"hitarea":"nohitarea";
			}
			var strName=item[_self.options.fName];
			var str=_self.li.replace(/\${class}/ig,cls)
					.replace(/\${group_id}/ig,item[_self.options.fId])
					.replace(/\${parents}/ig,item["parents"])
					.replace(/\${hit}/ig,hit)
					.replace(/\${group_name}/ig,strName)
					.replace(/\${op}/ig,operate==true?op:"")
					.replace(/\${description}/,item.description||"")
					.replace(/\$\{cla}/ig,cla)
					.replace(/\$\{depth}/ig,depth)
					.replace(/\$\{parentRoleTypeIdHex}/ig,item[_self.options.fParentId]);
			var li=$(str);

			ui.append(li);
			li.originData=item;
			_self.bind(li,item);
			
			//绑定含有cursor样式的节点的点击事件		
			li.find("span.cursor").click(function(){
				_self.dom.find("span").removeClass("selected");
				li.find(">span").addClass("selected");
				if(_self.callbackList["clickEvent"]){
					_self.callbackList["clickEvent"](item,_self);	//回调函数，传回两个参数-被点击分组的详细信息和tree对象本身
				}else{
					$.alert("未设置分组点击事件的回调函数！<br />请在回调函数列表内设置该方法：<br />例如:<br />callbackList={'clickEvent':function(groupInfo){alert(groupInfo)}}");
				}
				_self._privateAttr["currentId"]=item[_self.options.fId];	//执行完点击方法后把当前的分组id储存起来
			});			
			
		});	
		$.autoHeight(window);
	},
	
	
	bind:function(node,item){
		var _self=this;
		node.find(">.nohitarea").unbind("click");
		node.find(">.hitarea").click(function(){_self.expand(node)});
		if(!item) return;
		node.find("span[op=ins]").click(function(){
			_self.ins(node,item);
			$(this).parent("div").addClass("hide");
		});
		node.find("span[op=add]").click(function(){
			_self.add(node,item);
			$(this).parent("div").addClass("hide");
		});
		node.find("span[op=mod]").click(function(){
			_self.mod(node,item);
			$(this).parent("div").addClass("hide");
		});
		node.find("span[op=del]").click(function(){
			_self.del(node,item);
			$(this).parent("div").addClass("hide");
		});		
		/* 鼠标 移到/离开 节点时 显示/隐藏 节点编辑图标 */
		$(".treeview li > span").hover(function(){
			if($(this).siblings().is("div.edit")){
				$(this).children("span");
				$(this).addClass("bgColor");
				$(this).children(".edit-img").css("display", "");
//				$.autoHeight(window);
			};
		},function(){
			$(this).removeClass("bgColor");
			$(this).children(".edit-img").css("display","none");
		});
		/* 鼠标 移到/离开 编辑图标时  显示/隐藏 编辑框 */
		$(".treeview li  span span.edit-img").hover(function(){
			$(".edit").addClass("hide");
			$(".treeview li").css("z-index","1");
			$(this).parent("span").siblings("div.edit").removeClass("hide");
			$(this).parents("li").css("z-index","30");
			$(this).parent("li").css("z-index","100");
		},function(){
			$(this).parent("span").siblings("div.edit").addClass("hide");
		});
		
		/* 鼠标 移到/离开 编辑框div时 显示/隐藏 节点编辑图标 */
		$(".treeview li > div.edit").hover(function(){
			$(".edit").addClass("hide");
			$(this).removeClass("hide");
			$(this).siblings("span").children(".edit-img").css("display","");
		},function(){
			$(this).addClass("hide");
			$(this).siblings("span").children(".edit-img").css("display","none");
		});
		
		/* 鼠标 移到/离开 div编辑框的span时 给span 添加/删除 hover类名 */
		$(".treeview li div.edit span").hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
		});
	},
	
		
	/*
	 * 刷新节点显示样式,node为需要刷新样式的节点。
	 */
	flushNode:function(node){
		var _self=this;
		if(node.has("ul").length==0){
			return;
		}
		var nodeClass=node.attr("class");
		var ul=node.find(">ul").first();	//子节点集合容器
		var lastLi=ul.find(">li").last();	//最后一个子节点对象
		if(lastLi.length==0){ //当前节点已经没有子节点时			
			ul.remove(); //直接移除子节点集合容器
			//移除当前节点 "展开子节点" 的 触发事件
			node.find(">.hitarea").removeClass("hitarea").addClass("nohitarea").unbind("click");
			//移除当前节点 "展开子节点" 的按钮样式
			node.attr("class",nodeClass.replace(/collapsable/g,""));
		}else{	//当前节点含有子节点时		
			var lis=ul.find(">li");		//获取子节点集合
			for(var i=0;i<lis.length;i++){
				//移除所有子节点的last样式
				$(lis[i]).attr("class",$(lis[i]).attr("class").replace("last",""));
			}
			//为最后一个子节点添加last样式
			if(lastLi.attr("class").indexOf("able")>=0){
				lastLi.attr("class","last"+lastLi.attr("class"));	//这个子节点含有子节点时(原class为 expandable 或 collapsable，最后设置为lastexpandable 或 lastcollapsable)
			}else{
				lastLi.attr("class","last")							//这个子节点不含有子节点时，直接设置为last样式
			}
			//如果当前节点不为为顶层节点，才执行当前节点本身的样式刷新
			if(nodeClass!="treeview"){
				if(node.attr("class").indexOf("last")>=0){
					node.attr("class","lastcollapsable");
				}else{
					node.attr("class","collapsable");
				}
			}			
			node.find(">.nohitarea").removeClass("nohitarea").addClass("hitarea").click(function(){_self.expand(node)});
		}
	},
	
	/**
	 * 插入同极分组
	 */
	ins:function(node,item){	
		var _self=this;	
		var tmp={}
		var str=_self.edit.replace(/\${class}/ig,"last").replace(/\${group_id}/ig,tmp[_self.options.fId]).replace(/\${hit}/ig,"nohitarea").replace(/\${inp}/ig,_self.inp).replace(/\${group_name}/ig,tmp[_self.options.fName]).replace(/\${op}/ig,_self.options.operate===true?_self.op:"");
		var li=$(str);
		node.parent().find(">li").last().after(li);
		_self.flushNode(node.parent().parent());
		var input=li.find("input");
		
		//绑定input框回车事件
		input.bind('keypress',function(event){
			if(event.keyCode == "13")    
            {
               input.blur();
            }
		});
		
		//初始化新分组基本信息
		var newGroupInfo={};
		newGroupInfo[_self.options.fParentId]=item[_self.options.fParentId];
		newGroupInfo[_self.options.fOrg]=_self.options.orgId;		
		if(typeof(dataType)!="undefined"){
			newGroupInfo["dataType"]=dataType;
		}
		
		input.focus();
		input.blur(function(){
			var cla = "green-font cursor";
			var name=$.trim(input.val());
			if(name){
				newGroupInfo[_self.options.fName] = name;	//新分组名称赋值
				if(typeof(_self.options.handler.add)=="function"){
					//如果add是一个function，则执行该function，回调方法addSuccess最终需要接收一个回传的新增的分组id
					_self.options.handler.add(newGroupInfo,addSuccess);
				}else{
					var url = _self.options.handler.add.replace(/\$?\{group_id}/g,item[_self.options.fParentId]).replace(/\$?\{org_id}/g,_self.options.orgId);
					$.post(url,newGroupInfo,function(json){
						addSuccess(json);
					});
				}					
			}else{
				li.remove();
				_self.flushNode(node.parent().parent());	
			}
		});
		
		//新分组添加成功后执行的函数，newGroupId为新增的分组的id
		function addSuccess(newGroupId){
			if(newGroupId){				
				var groupId=newGroupId;
				newGroupInfo[_self.options.fId]=groupId;
				newGroupInfo["depth"]=item["depth"];
				newGroupInfo[_self.options.fSubCount]=0;
				newGroupInfo[_self.options.fSubGroup]=[];
				newGroupInfo["sourceFrom"]=item[_self.options.fId]; //添加的来源分组，这里来自同级分组					
				var parentGroup=_self.visibleGroupsInfoMap[newGroupInfo[_self.options.fParentId]];
				if(parentGroup){
					parentGroup[_self.options.fSubCount]++;			//父分组的子分组计数+1
					parentGroup[_self.options.fSubGroup].push(newGroupInfo);	//在父分组的子分组容器里增加 新增分组的引用
				}else{	//父分组不存在，表明新增分组为第一级分组，直接加入分组列表
					_self.options.groups.push(newGroupInfo);
				}						
				_self.visibleGroupsInfoMap[groupId]=newGroupInfo;	//加入可见分组列表		
				_self.append(node.parent().parent(),_self.getChildrenData(newGroupInfo[_self.options.fParentId]),_self);  //重新加载此级分组
						
				if(_self.callbackList["afterAdd"]){
					_self.callbackList["afterAdd"](newGroupInfo,_self);	//回调函数，传回两个参数-新增分组的详细信息和tree对象本身
				}	
			}else{
				li.remove();		
			}
			_self.flushNode(node.parent().parent());
		}
		
	},
	
	
	/**
	 * 增加子分组
	 */
	add:function(node,item){
		var _self=this;
		var op = _self.op;
		var operate=_self.options.operate;
		_self.expand(node,function(){
			dd();
		});
		
		function dd(){
			
		var children=node.find(">ul >li").length;
		var ul=null;
		var last=null;
		
		var cla = "green-font cursor";
		var str=_self.edit.replace(/\${class}/ig,"").replace(/\${inp}/ig,_self.inp);
		var li=$(str);
		
		if(children==0){
			ul=$('<ul oncontextmenu="return false" onselectstart="return false"></ul>');
			ul.append(li);
			node.append(ul);
			ul=node.find(">ul");
		}else{
			ul=node.find(">ul");
			last=ul.find(">li").last();
			last.after(li);
		}
		var input=li.find("input");
		input.bind('keypress',function(event){
			if(event.keyCode == "13")    
            {
                input.blur();
            }
		});
		
		//初始化新分组基本信息
		var newGroupInfo={};
		newGroupInfo[_self.options.fParentId]=item[_self.options.fId];
		newGroupInfo[_self.options.fOrg]=_self.options.orgId;
		
		//dataType为多个应用同在一个数据库表里时用于区分的标识位
		if(typeof(dataType)!="undefined"){
			newGroupInfo["dataType"]=dataType;
		}	

		_self.flushNode(node);
		input.focus();		
		input.blur(function(){
			var name=$.trim(input.val());
			if(!name){
				li.remove();
				_self.flushNode(node);
			}else{
				newGroupInfo[_self.options.fName]=name;		//为新增分组名称赋值					
				if(typeof(_self.options.handler.add)=="function"){
					_self.options.handler.add(newGroupInfo,addSuccess);
				}else{
					var url = _self.options.handler.add.replace(/\$?\{group_id}/g,item[_self.options.fId]).replace(/\$?\{org_id}/g,_self.options.orgId);				
					$.post(url,newGroupInfo,function(json){
						addSuccess(json);
					});
				}
				
			}			
		});		
		
		//新分组添加成功后执行的函数，newGroupId为新增的分组的id
		function addSuccess(newGroupId){
			if(newGroupId){						
				var groupId=newGroupId;
				newGroupInfo[_self.options.fId]=groupId;
				newGroupInfo[_self.options.fSubGroup]=[];
				newGroupInfo[_self.options.fSubCount]=0;
				newGroupInfo["depth"]=item["depth"]+1;			//深度比父级多一级 
				newGroupInfo["sourceFrom"]=item[_self.options.fId]; //添加的来源分组，这里来自父分组
				_self.visibleGroupsInfoMap[groupId]=newGroupInfo;			//加入可见列表	
				_self.visibleGroupsInfoMap[newGroupInfo[_self.options.fParentId]][_self.options.fSubGroup].push(newGroupInfo);	////在父分组的子分组容器里增加 新增分组的引用
			    _self.visibleGroupsInfoMap[newGroupInfo[_self.options.fParentId]][_self.options.fSubCount]++;					//父分组的子节点 计数 +1
				_self.append(node,_self.getChildrenData(newGroupInfo[_self.options.fParentId]),_self);	//重新加载本级分组
				if(_self.callbackList["afterAdd"]){
					_self.callbackList["afterAdd"](newGroupInfo,_self);	//回调函数，传回两个参数-新增分组的详细信息和tree对象本身
				}	
			}else{
				li.remove();
				_self.flushNode(node);
			}
		}
		
		}
	},
	
	/**
	 * 修改分组
	 */
	mod:function(node,item){
		var _self=this;
		var span=node.children("span:first");
		span.hide();
		var inp=$(_self.inp)
		span.after(inp);
		inp.focus();
		inp.val(item[_self.options.fName]);
		inp.blur(function(){
			var name=$.trim(inp.val());
			inp.remove();
			if(!name||item[_self.options.fName]==name){
				span.show();
			}else{
				function modifySuccess(flag){	//修改名称完成后的动作
					if(flag==false){
						span.show();
						return;
					}
					item[_self.options.fName]=name;
					var cloneSpan=span.children("span").clone(true);
					span.attr("title",name);
					span.text(name).append(cloneSpan).show();
					if(_self.callbackList["afterModify"]){
						_self.callbackList["afterModify"](item,_self);
					}	
				}
				if(typeof(_self.options.handler.modify)=="function"){
					var nameTEMP=item[_self.options.fName];		//保存原分组名称
					item[_self.options.fName]=name;				//设置新分组名称
					_self.options.handler.modify(item,modifySuccess);
					item[_self.options.fName]=nameTEMP;			//还原分组名称，如果修改成功，才在modifySuccess方法里更改新的名称
				}else{
					var url = _self.options.handler.modify.replace(/\{groupId}/g,item[_self.options.fId]);
					var pars = {};
					pars[_self.options.fName]=name;
					try{
						$.put(url,pars,function(json){
							if(json){
								modifySuccess();
							}else{
								span.show();
							}
						});
					}catch(e){
						$.alert(e);
					}
				}				
			}
		});
	},
	
	/**
	 * 删除分组
	 */
	del:function(node,item){
		var _self=this;
		var ul=node.parent();
		var p=ul.parent();
		var last=ul.find(">li").last();
		var groups=this.options["groups"];
		
		function delNode(){
			var pNode=node.parent().parent();
			node.remove();
			_self.flushNode(pNode);
		}
		
		function removeFromParentSubContainer(){
			var parentGroup=_self.visibleGroupsInfoMap[item[_self.options.fParentId]];
			var array;
			if(parentGroup){
				array=parentGroup[_self.options.fSubGroup];
			}else{	
				array=_self.options.groups;	  	//被删除的分组为一级分组
			}
			for(var i=0;i<array.length;i++){	//遍历同级分组数据，从父分组的子分组容器中删除引用
				var index=-1;
				var groupInfoTemp=array[i];
				if(groupInfoTemp){
					if(groupInfoTemp[_self.options.fId]==item[_self.options.fId]){
						index=i;
//					delete array[i];		//删除后数组索引保持不变，被删除元素的索引指向 undefined 。
						break;
				}
				}			
			}
			index==-1?null:array.splice(index,1);
		}
		if(item[_self.options.fId]>0 || (typeof item[_self.options.fId] == "string")){
			if(eval("item."+_self.options.systemFlag))
			{
				$.alert("系统分组不能删除")
			}else if(node.find("> div.hitarea").length==1)
			{
				$.alert("该分组下面存在子分组，请先删除子分组。");
			}else
			{
				var fName = item[_self.options.fName]||"";
				if(fName!=""){
					$.confirm("删除分组后对应该分组下的记录也会被删除，确认要删除吗？",function(){
						if(typeof(_self.options.handler.del)=="function"){
							_self.options.handler.del(item[_self.options.fId],deleteSuccess);
						}else{
							var url=_self.options.handler.del.replace(/\{groupId}/g, item[_self.options.fId]);
							$.del(url,{"ownerPartyId":orgId},function(json){
								if(json){
									deleteSuccess();							
								}
							});
						}
						
					});
				}else{
					delNode();
				}
			}
		}else{
			delNode();
		}
		
		//删除成功后执行的操作
		function deleteSuccess(flag){
			if(flag==false){
				return;
			}				
			if(item["depth"]!=1){
				_self.visibleGroupsInfoMap[item[_self.options.fParentId]][_self.options.fSubCount]--;		//从分组列表里删除该分组数据的引用
			}								
			delNode();
			removeFromParentSubContainer();		//从父分组容器中删除该分组数据的引用,删除后 数组容器里元素索引都不发生改变，被删除的元素变成 undefined 。
			if(_self.callbackList["afterDelete"]){
				_self.callbackList["afterDelete"](item,_self);	//删除完成后的回调函数,传回已经被删除的分组信息和tree对象本身的引用
			}
		}
		
	},
	
	//可选的排序功能
	sortGroups:function(groupsArray){
		var _self=this;
		if(typeof(_self.options.sortMode)=="function"){
			_self.options.sortMode(groupsArray);
		}else{
			var sortByGroupId=function(a,b){
				if(_self.options.sortMode=="ASC"){
					return parseInt(a[_self.options.fId])-parseInt(b[_self.options.fId]);
				}else if(_self.options.sortMode=="DESC"){
					return parseInt(b[_self.options.fId])-parseInt(a[_self.options.fId]);
				}
			};
			groupsArray.sort(sortByGroupId);	
		}		
	},
	
	getChildrenData:function(groupId){
		var _self = this;
		return _self.visibleGroupsInfoMap[groupId][_self.options.fSubGroup];
	},
	
	getGroupData:function(groupId){
		var _self = this;
		return _self.visibleGroupsInfoMap[groupId];
	},
	//==================================以下方法都是Tree 功能性的扩展方法，与Tree的生成、初始化、显示无关，只在Tree初始化完成后提供一些额外的便利功能

	//获取对象内部的私有属性值
	getAttr:function(attrName){
		var _self=this;
		if(!attrName){
			return _self._privateAttr;
		}else{
			return _self._privateAttr[attrName];
		}
		
	},
	
	focusOnTargetGroup:function(groupId){
		var _self=this;
		_self.dom.find("span[groupId]").removeClass("selected");
		_self.findTargetGroup(groupId).addClass("selected");
	},
	
	/*
	 * 对目标分组执行点击事件
	 */
	clickTargetGroup:function(groupId){
		var _self=this;
		_self.findTargetGroup(groupId).trigger("click");
	},
	
	/*
	 * 对第一个可被点击的分组执行点击事件
	 */
	clickFirstEnableGroup:function(){
		var _self=this;
		var sortByNum=function(a,b){
			return parseInt(a)-parseInt(b);
			}
		rangeArray.sort(sortByNum);	
		for(var i=0;i<rangeArray.length;i++){
			if(_self.visibleGroupsInfoMap[rangeArray[i]]){
				_self.clickTargetGroup(rangeArray[i]);
				break;
			}
		}
	},
	
	//查找目标分组对象，最终返回目标分组的dom对象
	findTargetGroup:function(groupId){
		var _self=this;
		var li=_self.dom.find(">ul >li[group_id="+groupId+"]");
		if(li.length==0){			
			//递归获取分组的父id集合
			var ids=_self.getAttr("parents")[groupId].split(",");
			ids.push(groupId);
			var count=2;
			var len=ids.length;
			li=_self.dom.find(">ul >li[group_id="+ids[1]+"]");
			while(count<len){
				if(li.attr("class").indexOf("expandable")>-1){
					li.find(">div[class='hitarea']").click(); 
				}	
				li=li.find(">ul >li[group_id="+ids[count]+"]");					
				count++;
			}
		}
		return li.find(">span[groupId="+groupId+"]");		//返回分组元素对象
	},
	
	//该方法获取一个以当前tree对象数据为基础的分组选择器的生成函数，
	//参数：
	// paramList={
	//		authName:"允许执行点击操作的权限名称（必要参数）"
	//		withConfirmButton:true|false  分组选择器是否带有确定按钮
	//		clickTargetId:0 	加载分组选择器时定位到的目标id，当该值为无效值时，默认定位到原始tree对象当前被选中的id
	// }
	getGroupSelector:function(paramList){
		var _self=this;
		var groupSelector;
		//分组选择器的生成函数，包含三个参数：
		//objJQ为jquery对象,可以包含一个或者多个原始js对象，所有绑定了groupSelector函数触发事件的dom原始js对象都应该包含在objJQ里。
		//op是可选参数，目前功能是自定义分组选择器的style属性，例如：op["css"]={minWidth:500,backgroup:"#FFFFFF"};来重新定义选择器的最小宽度和背景色。
		//callback是回调方法,返回两个参数：第一个是被选中的id，第二个是关闭分组选择器的函数
		groupSelector=function groupSelectorForMove(objJQ,op,callback){
			if(typeof(op)=="function"){
				callback=op;
			}
			if(objJQ.attr("showed")=="true"){		//判断是否需要加载分组选择器，showed是在分组选择器初始化时赋予的属性。选择器显示时赋予'true'，隐藏时赋予'false'
				return;
			}
			var selector=$("<div slideDialog='groupSelector' style='text-align:left;display:none;background:#FFFFFF;border-style:solid;border-color:#808080; border-width:1px 1px 1px 1px;padding:0px 3px 3px 0px;width:auto;'></div>");
			$("body").append(selector);
//			var options={
//				fId:_self.options.fId,
//				fName:_self.options.fName,
//				fParentId:_self.options.fParentId,			
//				fSubCount:_self.options.fSubCount,
//				fSubGroup:_self.options.fSubGroup,
//				operate:false,													
//				groups:_self.options.groups,			
//				allowVisibleAuthName:paramList["authName"],			//允许分组可被执行点击操作的权限名称
//				isAdmin:_self.options.isAdmin,
//				sortMode:_self.options.sortMode				
//			};
			var options={};
			for(var key in _self.options){
				options[key]=_self.options[key];
			}
			options.operate=false;
			var confirmButton=$("<button align='right' class='button' >确&nbsp;定</button>");
			var callbackList={
				clickEvent:function(selectedGroupInfo,treeObject){	
					if(paramList["withConfirmButton"]){
						confirmButton.unbind("click").click(function(){	//给确定按钮绑定点击方法
							callback(selectedGroupInfo,function(){
								selector.remove();	
								objJQ.attr("showed","false");
							});			
						});
					}else{	
						callback(selectedGroupInfo,function(){							
							selector.remove();							
							objJQ.attr("showed","false");
						});
					}
					
				}
				,afterInit:function(treeObject){			
					var offset=$(objJQ[0]).offset();
//					if(paramList["clickTargetId"]){				
//						treeObject.clickTargetGroup(paramList["clickTargetId"]);
//					}else{
//						if(_self._privateAttr["currentId"]){	//如果父Tree已经被点击过了，则定位到父Tree对象被选中的分组
//							treeObject.clickTargetGroup(_self._privateAttr["currentId"]);
//						}else{									//如果父Tree未被点击过，则定位到第一个可被执行点击事件的有效分组
////							treeObject.clickFirstEnableGroup();
//						}						
//					}
					if(paramList["withConfirmButton"]){
						selector.append(confirmButton);
					}
					selector.css("position",'absolute');			
					if(typeof(op)!="function"){
						if(op["css"]){
							var str=[];
							for(var key in op["css"]){
								selector.css(key,op["css"][key]);
							}				
						}
					}else{
						selector.css("left",offset["left"])
								.css("top",offset["top"]+$(objJQ[0]).height())
								.css("width","auto");
					}
					selector.slideDown();
					objJQ.attr("showed","true");							
				}

			};
			var tree=new CategoryTree(options,callbackList)    
			var documentMouseDownForGroupSelector=function(event){
				var target = $(event.target);
				var flag=true;
				for(var i=0;i<objJQ.length;i++){
					if(objJQ[i]==event.target){
						flag=false;
						break;
					}
				}
				if(target.parents("div[slideDialog='groupSelector']").length == 0&&flag){
						selector.remove();
						objJQ.attr("showed","false");
						//解除mousedown事件触发方法-documentMouseDownForGroupSelector
						$(document).unbind("mousedown",documentMouseDownForGroupSelector);	
				}
			}
			$(document).mousedown(documentMouseDownForGroupSelector);	//绑定mousedown事件，自动消除分组选择器
			tree.show(selector);						//selector参数表示html页面标签，该页面标签将显示生成的tree树
		}
		return groupSelector;		//返回分组选择器的生成函数
	},
	
	loadInDialog:function(paramList,callback){
		var _self=this;
		var objJQ=paramList["objJQ"];
		if(objJQ.attr("showed")=="true"){		//判断是否需要加载分组选择器，showed是在分组选择器初始化时给触发事件的元素赋予的属性。选择器显示时赋予'true'，隐藏时赋予'false'
			return;
		}
		var dialog=$("<div slideDialog='dialogSelector' style='display:none;border-width:1px 1px 1px 1px;height:355px;border-style:solid;border-color:#808080;background:#FFFFFF;'></div>");
		var topSide=$("<div style='background:#eee;float:left;width:100%;height:20px;top:0px;border-width:0px 0px 1px 0px;border-style:solid;padding:2px 0px 2px 0px;border-color:#A9A9A9;'><span style='position:absolute;left:5px;top:5px;'>"+(paramList["title"]||"dialog")+"</span></div>")
		var leftSide=$(
			"<div style='position:absolute;width:160px;top:25px;left:5px;height:300px;border-style:solid;border-color:#808080;border-width:0px 1px 0px 0px;'>" +
				"<p align='left'><span>分组:</span></p>" +
				"<div align='left' style='overflow:auto' treeContainer='treeContainer'></div>" +
			"</div>");
		var rightSide=$(
			"<div style='float:right;top:25px;left:160px;width:430px;height:300px;'>" +
				"<p align='left'><span>列表:</span></p>" +
				"<div id='infoListContainer' align='left'>" +
					"<p><input type='radio'/>loading... A001 代码QQKAALOI</p>" +
				"</div>" +
				"<div pageBar='pageBar' align='right'>分页导航...</div>" +
			"</div>");
		var foot=$(
			"<div style='float:left;top:320px;width:100%;height:20px;border-color:#A9A9A9;border-width:1px 0px 0px 0px;border-style:solid;padding:3px 0px 2px 0px;'>" +
				"<span style='float:right;margin:0px 10px 0px 0px;' ><button ctype='confirm' >确定</button>&nbsp;<button ctype='cancle'>取消</button></span>" +
			"</div>");
		dialog.append(topSide).append(leftSide).append(rightSide).append(foot);
		$("body").append(dialog);
		var offset=objJQ.offset();
		dialog.css("position",'absolute')
				.css("left",offset["left"])
				.css("top",offset["top"]+objJQ.height())
				.css("width","600px");
		$.autoHeight(window);
		var documentMouseDownFordialogSelector=function(event){
				var target = $(event.target);
				var flag=true;
				for(var i=0;i<objJQ.length;i++){
					if(objJQ[i]==event.target){
						flag=false;
						break;
					}
				}
				if(target.parents("div[slideDialog='dialogSelector']").length == 0&&flag){
					closeDialog();
				}
		}
		$(document).mousedown(documentMouseDownFordialogSelector);	//绑定mousedown事件，自动消除分组选择器	
		function closeDialog(){
			dialog.remove();
			objJQ.attr("showed","false");
			//解除mousedown事件触发方法-documentMouseDownForGroupSelector
			$(document).unbind("mousedown",documentMouseDownFordialogSelector);	
		}	 
		 var dialogDataStore={
		 	selected:{count:0}
		 	,dataMap:"在获取到数据列表后,将数据列表转换为键值对格式使用tree对象的setDialogInnerObject('dataMap',map)方法将键值对数据保存到dataMap"
		 	,pageNumD:1
		 	,pageSizeD:10
		 	,infoListContainer:$("#infoListContainer")
		 	,pageBar:rightSide.find("div[pageBar]")
		 	,topSide:topSide
		 	};
		_self.getDialogInnerObject=function(dataName){
			if(typeof(dataName)=="string"){
				return dialogDataStore[dataName];
			}
			return dialogDataStore;
		}
		_self.setDialogInnerObject=function(dataName,value){
			if(typeof(dataName)=="string"){
				dialogDataStore[dataName]=value;
			}
		}
		foot.find("button[ctype=confirm]").click(function(){	//确认按钮点击事件
			var s=_self.getDialogInnerObject("selected");
			if(s["count"]>0){
				delete s["count"];
				callback(_self.getDialogInnerObject("selected"));
				closeDialog();
			}else{
				$.alert("请在选择后再按确定！");
			}	
			
		});
		foot.find("button[ctype=cancle]").click(function(){		//取消按钮点击事件
			closeDialog();
		});
		$("#infoListContainer input").live("click",function(){	//动态绑定新加入infoListContainer的所有input元素的点击事件
//			alert($(this).attr("dataHash"));
			var inputJQ=$(this);
			inputHandler[inputJQ.attr("type")](inputJQ);
			
		});
		var inputHandler={
			checkbox:function(inputJQ){
				var s=_self.getDialogInnerObject("selected");
				if(inputJQ.attr("checked")){
					s[inputJQ.attr("dataHash")]=_self.getDialogInnerObject("dataMap")[inputJQ.attr("dataHash")];
					s["count"]++;
				}else{
					delete s[inputJQ.attr("dataHash")];
					s["count"]--;
				}
			}
			,radio:function(inputJQ){
				var s=_self.getDialogInnerObject("selected");
				s["data"]=_self.getDialogInnerObject("dataMap")[inputJQ.attr("dataHash")];
				s["count"]=1;
			}
		}
		_self.show(leftSide.find("div[treeContainer]"));
		var showUp=function(){			//dialog的重加载函数
		 	_self.loadInDialog(paramList,callback);
		}
		dialog.show();
		objJQ.attr("showed","true");
		return {dialog:dialog,leftSide:leftSide,rightSide:rightSide,showDialog:showUp};
	}
	
}

