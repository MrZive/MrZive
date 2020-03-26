
/**
 * last edit 2014-08-25 by subinghong
 * 树目录对象必要参数：
 * @param options={ 						//树目录配置参数
 * 		fId:			//（必要）树目录的ID字段名称,		
 * 		fName:			//（必要）树目录的name字段名称,	
 *  	fParentId:		//（必要）父目录ID字段名称,	
 * 		fChildren:		//（必要）子节点计数的字段名称，
 * 		subGroup:		//（必要）子节点容器的字段名称
 * 		fOrg:			//公司id的字段名称，（该字段不使用，这里暂时做保留）	
 * 		orgId:			//公司id（该字段不使用，这里暂时做保留）		
 * 		groups:			//原始分组数据（树状结构）
 * 		authList:		//原始权限列表（对于不需要权限管理的，此值不需要设置，但isAdmin必须为true），分组树的生成依赖此权限列表，格式：[{"dataRangeIdHex":"分组id1","auths":"权限名xxx1,权限名xxx2"},{"dataRangeIdHex":"分组id2","auths":"权限名xxx1,权限名xxx2"}]
 * 		authRolesMap:	//经过处理了的权限列表（对于不需要权限管理的，此值不需要设置，但isAdmin必须为true），当此参数有效时，将忽略authList参数；格式：{分组id1:{权限名xxx1:"1","权限名xxx2:"1"},分组id2:{权限名xxx1:"1","权限名xxx2:"1"}}
 * 		isAdmin:		//（必要）是否是管理员,是管理员的话就拥有所有权限(isAdmin为true时，authList和authRolesMap不设值分组树也可以正常显示)
 * 		operate:		//[true|false],true表示显示操作标签，false为不显示操作标签
 * 		systemFlag:		//判断是否是系统分组的条件,例如分组的属性type=0时时系统分组，则该参数设置为--systemFlag:"type==0".
 * 		allowVisibleAuthName:允许分组可被执行点击操作的权限名称,例如权限列表auths里的 "权限名xxx1"
 * 		sortMode:"ASC"|"DESC"|function(array){array.sort(自定义的排序方式)},	//可选的功能（不使用该功能时直接不定义此参数即可），对分组进行排序显示，集成两种排序方式：ASC根据id进行升序排序、DESC根据id进行降序排序；第三种为回调方法回传一个数组的引用，用于自定义的排序方式
 * 		operatorAuthNames:{
 *			ins:		//'增加同级分组'的权限名称,现阶段没有此权限名称，所以插入同级分组的标签只对管理员可见(该权限名称应该设置一个任意的值如："nothing")
 *			mod:		//'修改分组'的权限名称
 *			add:		//'增加子级分组'的权限名称
 *			del:		//'删除分组'的权限名称
 *		},
 * 		handler:{
 * 			add:		//"增加节点的handler",	  	//有url和回调方法两种方式，推荐使用回调方法，
 * 			modify:		//"修改节点的handler",	//有url和回调方法两种方式，推荐使用回调方法
 * 			del:		//"删除节点的handler"		//有url和回调方法两种方式，推荐使用回调方法
 * 		}		
 * }
 * 		handler回调方法范例：
 * 		handler={
 * 			add:function(groupInfo,returnId){		//groupInfo为要新增的分组详细信息，returnId为tree对象内部传出来的回调方法
 * 					var param={name:groupInfo.name,parentId:groupInfo.parentId,ownerPartyId:39358},
 * 					$.post("addURL",param,function(newGroupId){
 * 						returnId(newGroupId);		//增加成功后，向tree对象内部回传这个新增分组的id
 * 					});
 * 				},
 * 			modify:function(groupInfo,callback){		//groupInfo为修改完成后的分组详细信息，callback为tree对象内部传出来的回调方法
 * 					var param={name:groupInfo.name,parentId:groupInfo.parentId,ownerPartyId:39358},
 * 					$.put("modifyURL",param,function(newGroupId){
 * 						callback();		//修改成功，回调执行tree对象的内部方法；
 * 						//callback(false);	如果修改失败的执行方法
 * 					});
 * 				},
 * 			del:function(groupId,callback){			//groupId为要删除的分组id，callback为tree对象内部传出来的回调方法
 * 					var param={id:groupId};
 * 					$.del("delURL",param,function(result){
 * 						if(result){		
 * 							callback();		//删除成功，回调执行tree对象的内部方法；
 * 							//callback(false);	如果删除失败的执行方法
 * 						}						
 * 					});
 * 				}
 * 		}
 * @param callbackList={ 						回调函数列表
 * 		//（必要方法）节点点击事件回调,回传两个参数：groupInfo为被点击分组的详细信息，treeObject为tree对象本身的一个引用
 * 		clickEvent:function(groupInfo,treeObject){//在这里编写数据处理代码}
 * 		//(可选方法)分组树初始化后的方法回调，完成了一级分组显示后的回调函数，回传	tree对象本身的一个引用
 * 		,afterInit：function(treeObject){//在这里编写代码}
 * 		//(可选方法)第一次展开下一级分组后的回调函数(包含初始化时显示顶级分组的情况),回传两个参数：node为展开子级的节点对象，treeObject为tree对象本身的一个引用
 * 		,afterAppend:function(node,treeObject){//在这里编写代码}
 * 		//（可选方法）增加节点数据后的方法回调，回传两个参数：groupInfo为新增分组的详细信息，treeObject为tree对象本身的一个引用
 * 		,afterAdd:function(groupInfo,treeObject){//在这里编写代码}
 * 		//（可选方法）修改节点数据后的方法回调，回传两个参数：groupInfo为被修改分组的详细信息，treeObject为tree对象本身的一个引用
 * 		,afterModify:function(groupInfo,treeObject){//在这里编写代码}
 * 		//（可选方法）删除节点数据后的方法回调，回传两个参数：groupInfo为新增分组的详细信息，treeObject为tree对象本身的一个引用
 * 		,afterDelete:function(groupInfo,treeObject){//在这里编写代码}
 * 		//（可选方法）删除节点数据后的方法回调，回传两个参数：groupInfo为被修改后的分组的详细信息，treeObject为tree对象本身的一个引用
 * 		,afterModify:function(groupInfo,treeObject){}
 * 		}
 * 		
 * 	树初始化范例：
 * 		var tree=new Tree(options,callbackList);	//构造方法初始化，生成tree对象
 * 		tree.show("treeContainerId");				//将tree对象显示在id为treeContainerId的html标签内
 *  树的扩展功能：
 * 		tree.getAttr("XXX");		//该方法用于获取树内部属性，目前可用属性包括：1 "currentId"(树当前被选中的分组id值) 2 "currentSpan"(树当前被选中的分组jquery对象)
 *		tree.setCurrentId(groupId);	//设置当前分组id值
 * 		tree.getFirstEnableGroupId					//获取第一个有效分组的id值
 * 		tree.findTargetGroup(groupId);				//查找目标分组对象，最终返回目标分组的dom（Jquery）对象
 * 		tree.focusOnTargetGroup(groupId);			//将目标分组变为被选中状态（取消原先的分组选择状态，并设置目标元素的class为selected）
 * 		tree.clickFirstEnableGroup();				//点击tree对象第一个有效的分组
 * 		tree.clickTargetGroup(groupId);				//对目标分组并执行点击事件
 */
var Tree=function(options,callbackList){
	//内部私有的扩展属性
	this._privateAttr={
		currentId:null
	};   	
	this.options={fParentId:"parent_id",fId:"id",fName:"name",fChildren:"subGroupCount",subGroup:"subGroup"};
	for(var key in options||{}){
		if(key=="handler"){
			var handler=options[key];
			if(typeof(handler)=="string"){
				options[key]={get:handler};
			}
		}
		this.options[key]=options[key];
	}	
	
	this.callbackList=callbackList||{};		//回调函数列表
	if(this.options.isAdmin){
		//管理员拥有所有权限，所有分组对管理员都是可见、可操作		
		var dataInfo=this.getGroupInfoMap(this.options["groups"]);	
		this.range=dataInfo["range"];
		this.visibleGroupsInfoMap=dataInfo["groupMapForShow"];
		if(this.options["authList"]){
			//为了兼容旧代码，管理员状态下，如果有传权限列表authList，也进行权限列表处理
			this.authRolesMap=this.handleAuthRolesData(this.options["authList"]);
		}else{
			//初始化为一个空对象
			this.authRolesMap={};
		}
	}else{
		if(this.options.authRolesMap){
			
			this.authRolesMap=this.options.authRolesMap;
		}else{
			//对原权限列表进行特殊处理，转换成map形式 key为分组id，value为json对象格式的权限列表{"权限名1":"1","权限名2":"1",..."权限名n":"1"}
			this.authRolesMap=this.handleAuthRolesData(this.options["authList"]);
		}
		//获取被允许操作的分组id列表,json对象格式:{分组id1:"1",分组id2:"1",...分组id3:"1"}
		this.range=this.getEnableRange(this.authRolesMap,this.options["allowVisibleAuthName"])||{};
		this.visibleGroupsInfoMap=this.getVisibleGroupInfoMap(this.options["groups"],this.range);	//根据range获取可见分组列表
	}

}

Tree.prototype={	
	inp:'<input type="text" maxlength="24" />',
	edit:'<li class="${class}  group_tree"><div class="nohitarea"></div>${inp}</li>',
	li:'<li class="${class}" group_id=${group_id} parentRoleTypeIdHex="${parentRoleTypeIdHex}"><div class="${hit}"></div>${op}<span  title="${group_name}" class="${cla}" style="display:block;"   mark="mark" depth="${depth}" groupId="${group_id}" >${group_name}&nbsp;&nbsp;<span class="edit-img" style="display:none;"></span></span></li>',
	op:'<div class="op hide edit"><span op="ins" style="display:${ins}">插入同级分组</span><span op="add" style="display:${add}">新增子分组</span><span op="mod"  style="display:${mod}">修改</span><span op="del"  style="display:${del}">删除</span></div>',
	loading:'<span class="loading">正在加载</span>',
	
	
	/**
	 * 显示一级目录的节点
	 * @param ID 容器DOM对象或容器ID
	 */
	show:function(ID){		
		var _self=this;
		if(ID){
			_self.dom=typeof(ID)=="string"?$("#"+ID):$(ID);
			_self.dom.addClass("treeview");
		}
		var visibleGroupsInfoMap=_self.visibleGroupsInfoMap;
		var rootGroups=[];
		for(var key in visibleGroupsInfoMap){
			if(visibleGroupsInfoMap[key]["depth"]==1){
				rootGroups.push(visibleGroupsInfoMap[key]);
			}
		}
		if(_self.options.sortMode){		//分组显示顺序重排功能
			_self.sortGroups(rootGroups);
		}
		this.append(_self.dom,rootGroups,_self);		
		if(_self.callbackList["afterInit"]){
			_self.callbackList["afterInit"](_self);		//分组树一级节点显示完成后的回调函数，回传一个tree对象本身的引用
		}			
	},
	
	/*
	 * 转换权限列表格式
	 * 原格式:[{"dataRangeIdHex":x1,"auths":"[{\"name\":\"xxx1\"},{\"name\":\"xxx2\"}]"},{"dataRangeIdHex":x2,"auths":"[{\"name\":\"xxx1\"},{\"name\":\"xxx2\"}]"}]
	 * 转换后：{x1:{xxx1:"1",xxx2:"1"},x2:{xxx1:"1",xxx2:"1"},xn:{xxx1:"1",xxx2:"1"}}
	 */
	handleAuthRolesData:function(data){
		var authMap={};
		for(var i=0;i<data.length;i++){
			var authList={};
			var auths=data[i]["auths"].split(",");
			for(var j=0;j<auths.length;j++){
				if($.trim(auths[j])!==""){
					authList[auths[j]]="1";
				}			
			}
			authMap[data[i]["dataRangeIdHex"]]=authList;
			
		}
		return authMap;
	},
	
	/*
	 * 获取可见分组列表
	 * authRolesMap 权限列表，格式：{x1:{xxx1:"1",xxx2:"1"},x2:{xxx1:"1",xxx2:"1"},xn:{xxx1:"1",xxx2:"1"}}
	 * allowVisibleAuthName 允许分组可被显示的权限名称
	 * 最终返回的可见分组列表格式为：json对象格式:{分组id1:"1",分组id2:"1",...分组id3:"1"}
	 */
	getEnableRange:function(authRolesMap,allowVisibleAuthName){		
		var range={};
		for(var key in authRolesMap){
			if(authRolesMap[key][allowVisibleAuthName]){
				range[key]="1";
			}
		}	
		return range;
	},
	
	getGroupInfoMap:function(group){
		var _self=this;
		function handleIt(group,groupMapForShow,range,depth){
			if(!groupMapForShow){
				groupMapForShow={};			
			}
			if(!range){
				range={};
			}
			if(!depth){
				depth=1;
			}else{
				depth++;
			}
			var groupInfoMap={};
			for(var i=0;i<group.length;i++){
				if(!group[i]){
					continue;
				}
				group[i]["depth"]=depth;
				groupMapForShow[group[i][_self.options.fId]]=group[i];
				range[group[i][_self.options.fId]]="1";
				if(group[i][_self.options.fChildren]!=0){
					handleIt(group[i][_self.options.subGroup],groupMapForShow,range,depth);
				}else{
					group[i][_self.options.subGroup]=[];	//没有子项，给子容器初始化一个空的数组，防止后面使用的时候报错
				}
				
			}
			return {groupMapForShow:groupMapForShow,range:range};
			}
		return handleIt(group);
	},
	
	/*
 	* 处理从服务器请求的原始分组数据
 	* 调用该方法时只需要传 group,range 这两个参数即可
	* @param group			原始分组数据
 	* @param range				分组节点被允许操作的权限列表，格式为：[{"id1":"1"},{"id2":"1"},{"id3":"1"},]
 	* @param groupMapForShow	数据容器，用来储存处理完毕后的的分组数据（该参数用户递归调用，实际调用此方法时，不必传递此参数）；
	* 该方法最终返回可以被显示的分组的数据：groupMapForShow为map形式的对象集合（包含可以被显示（不一定可以被操作）的分组列表数据）
 	* 								最终格式为：{"分组id1":{"group":{实际分组的详细信息}},"分组id2":{"group":{实际分组的详细信息}},.....}；
 	* 处理完毕后原始的range数据也可能发生改变，
 	* 例如：包含在range范围内的某个分组的子分组原先并没有包含在range的范围内，处理完毕后该分组的所有子分组都将加入range
 	*/
	getVisibleGroupInfoMap:function(group,range){
		var _self=this;
		return handleIt(group,range);
		function handleIt(group,range,groupMapForShow,gDepth){
			if(!groupMapForShow){
				groupMapForShow={};
			}
			var depth=gDepth
			if(!depth){
				depth=1;
			}
			var parentIsShow=false;			//当前 这级分组 是否有可以被显示的分组的标识（只表示了可以被显示，不代表可以被操作）
			for(var i=0;i<group.length;i++){
				if(!group[i]){ //如果group[i]元素为无效值，直接进行 下一个元素的判断
					continue;
				}
				group[i]["depth"]=depth;
				var flag=false;
				
				//判断当前分组的父分组是否已经存在于被允许操作的元素列表range的范围内，其父分组在range内被允许显示，则当前分组也被允许显示，并加入range范围
				if(range[group[i][_self.options.fParentId]]){
					groupMapForShow[group[i][_self.options.fId]]=group[i];
					range[group[i][_self.options.fId]]="1";		//将此id加入允许被操作的元素列表range
					parentIsShow=flag=true;
				}else{
					//判断当前分组是否在被允许操作的权限列表range的范围内
					if(range[group[i][_self.options.fId]]){
						groupMapForShow[group[i][_self.options.fId]]=group[i];
						parentIsShow=flag=true;			
					}	
				}	
								
				if(flag){	//如果当前分组或父分组在被允许操作的权限列表range的范围内，则递归处理子分组（递归完成后其子分组都拥有被操作的权限）
					if(group[i][_self.options.fChildren]!=0){
						handleIt(group[i][_self.options.subGroup],range,groupMapForShow,depth+1);
					}else{
						group[i][_self.options.subGroup]=[];	//没有子项，给子容器初始化一个空的数组，防止后面使用的时候报错
					}
				}else{		//当前分组和其父分组都不在被允许操作的权限列表range的范围内，则递归处理子分组，查询是否有子分组在range范围内，以此来判断当前分组是否应该被显示
					if(group[i][_self.options.fChildren]!=0){
						//最后一个参数-true 表示这次的方法调用时用来处理子级分组的数据，方法调用后返回当前分组是否包含了可以被操作的子分组的标示位
						flag=handleIt(group[i][_self.options.subGroup],range,groupMapForShow,depth+1);
					}else{
						group[i][_self.options.subGroup]=[];	//没有子项，给子容器初始化一个空的数组，防止后面使用的时候报错
					}			
					if(flag==true){
						parentIsShow=true;
						//parentIsShow为true时，表示当前分组包含允许被操作的子分组，则当前分组可以被显示（加入groupMapForShow列表），
						//但不能被操作（所以不加入range的范围内）
						groupMapForShow[group[i][_self.options.fId]]=group[i];				
					}
				}
			}		
			//depth为1时表示处理完所有分组数据
			if(depth==1){
				return groupMapForShow;		//所有分组处理完则返回处理完毕的(可以被显示的)分组列表数据		
			}else{		
				//子级分组处理完，返回是否允许父分组显示的标示位
				return parentIsShow; //返回 当前这级分组 是否包含了可被显示的分组 的标示
			}	
		}
		
	},
	
	/*
	 * 权限继承,获取最终的权限列表
	 */
	inheritPermission:function(groupId,_self){
		var getParentIds=function(id,ids){
			if(!ids){
				ids=[];
			}
			ids.push(id);
			if(_self.visibleGroupsInfoMap[id]){
				getParentIds(_self.visibleGroupsInfoMap[id][_self.options.fParentId],ids);
			}
			return ids;
		};
		//递归获取分组的父id集合
		var ids=getParentIds(groupId);
		var authListFinal={};
		//级联获取父分组权限,
		for(var i=0;i<ids.length;i++){
			if(_self.authRolesMap[ids[i]]){
				for(var key in _self.authRolesMap[ids[i]]){
					authListFinal[key]="1";
				}
			}
		}
		return authListFinal;		
	},
	
	
	
	/**
	 * 展开
	 */
	expand:function(node){
		var _self=this;
		if(node.find(">.nohitarea").length>0)return;
		
		var loaded=node.has("ul").length>0?true:false;
		var p=node.parent();
		if(node.hasClass("collapsable")){
			node.removeClass("collapsable").addClass("expandable");
		}else if(node.hasClass("lastcollapsable")){
			node.removeClass("lastcollapsable").addClass("lastexpandable");
		}else if(loaded){
			node.hasClass("expandable")?node.removeClass("expandable").addClass("collapsable"):node.removeClass("lastexpandable").addClass("lastcollapsable");
		}else{
			if(!loaded){
				var groupId=node.attr("group_id");
				var children=_self.getChildrenData(groupId);
				var a=node.find("a");
				var tmp=a.text();
				a.html(this.loading);
				this.append(node,children,_self);
				a.text(tmp)
			}
			node.hasClass("expandable")?node.removeClass("expandable").addClass("collapsable"):node.removeClass("lastexpandable").addClass("lastcollapsable");
		}
		$.autoHeight(window);
	},
	
	/*
	 * 加载分组
	 * node为分组节点对象，data为node节点的子节点数据集合，_self为tree对象本身的一个引用
	 */
	append:function(node,data,_self){
		var authRolesMap=_self.authRolesMap			//map格式的权限列表
		var range=_self.range;						//允许被显示的分组id列表
		var isAdmin=_self.options.isAdmin;			//是否是管理员
		var operatorAuthNames=_self.options.operatorAuthNames;	//各个操作标签对应的权限名称列表
		var click=_self.click;
		
		node.find(">ul").remove();
		var ui=$('<ul oncontextmenu="return false" onselectstart="return false"></ul>');
		node.append(ui);
		data=data||[];
		var len=data.length-1;
		var depth = 0;
		$.each(data,function(i,item){	
			var operate = _self.options.operate;		//显示操作标签的开关	
			var op=_self.op;
			var cla = "blue-font";
			var event = "";
			if(node.html()!=null&&i==0){
				depth = node.find("span[mark='mark']").attr("depth")||0;
			}
			var count = depth;
			//判断该节点是否在range中，即判断该节点是否有权限执行点击操作
			if(range[item[_self.options.fId]]){
				cla = "green-font cursor";
			}
			
			//权限判断代码移动到这里，是为了兼容旧代码，管理员身份登录时也获取权限列表，做权限列表的处理
			if(authRolesMap){
				//继承父分组所有权限
				var authListFinal=_self.inheritPermission(item[_self.options.fId],_self);
				if(authListFinal!="{}"){
					authRolesMap[item[_self.options.fId]]=authListFinal;
				}	
			}
			
			if (isAdmin) {
				op = op.replace(/\${ins}/g, '');
				op = op.replace(/\${del}/g, '');	
				op = op.replace(/\${mod}/g, '');
				op = op.replace(/\${add}/g, '');					
			}else{
				if(operate){
					operate=false;				
					if (authRolesMap[item[_self.options.fId]]) {
						var authList=authRolesMap[item[_self.options.fId]];
						var parentAuthList=authRolesMap[item[_self.options.fParentId]];
						if(parentAuthList&&parentAuthList[operatorAuthNames['add']]){
							operate=true;
							op=op.replace(eval("/\\${ins}/g"),"");
						}else{
							op=op.replace(eval("/\\${ins}/g"),"none");
						}
						for(var key in operatorAuthNames){
							if(authList[operatorAuthNames[key]]){
								operate=true;
								op=op.replace(eval("/\\${"+key+"}/g"),"");
							}else{
								op=op.replace(eval("/\\${"+key+"}/g"),"none");
							}
						}
					}
				}
			}
			var cls=item[_self.options.fChildren]>0?(i<len?"expandable ":"lastexpandable"):(i<len?"":"last");
			var hit=item[_self.options.fChildren]>0?"hitarea":"nohitarea";
			var str=_self.li.replace(/\${class}/ig,cls)
					.replace(/\${group_id}/ig,item[_self.options.fId])
					.replace(/\${hit}/ig,hit)
					.replace(/\${group_name}/ig,item[_self.options.fName])
					.replace(/\${op}/ig,operate==true?op:"")
					.replace(/\${description}/,item.description||"")
					.replace(/\$\{cla}/ig,cla)
					.replace(/\$\{depth}/ig,++count)
					.replace(/\$\{parentRoleTypeIdHex}/ig,item[_self.options.fParentId]);
			var li=$(str);
			ui.append(li);
			_self.bind(li,item);
			
			//绑定含有cursor样式的节点的点击事件		
			li.find("span.cursor").click(function(){
				if(_self._privateAttr["currentSpan"]){
					_self._privateAttr["currentSpan"].removeClass("selected");
				}
				var currentSpan=li.find(">span");
				currentSpan.addClass("selected");
				if(_self.callbackList["clickEvent"]){
					_self.callbackList["clickEvent"](item,_self);	//回调函数，传回两个参数-被点击分组的详细信息和tree对象本身
				}else{
					$.alert("未设置分组点击事件的回调函数！<br />请在回调函数列表内设置该方法：<br />例如:<br />callbackList={'clickEvent':function(groupInfo){alert(groupInfo)}}");
				}
				_self._privateAttr["currentId"]=item[_self.options.fId];	//执行完点击方法后把当前的分组id储存起来
				_self._privateAttr["currentSpan"]=currentSpan;				//保存当前节点
			});			
			
		});
		if(_self.callbackList["afterAppend"]){
			_self.callbackList["afterAppend"](node,_self); //返回父级阶段对象和树本身的引用
		}
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
	
	/**
	 * 获取子节点数组
	 */
	getChildrenData:function(parentId){
		var _self=this;
		var subGroupData = [];
		var groups=this.options.groups;
		var visibleGroupsInfoMapTemp=this.visibleGroupsInfoMap;
		$.each(visibleGroupsInfoMapTemp,function(i,v){
			if(v[_self.options.fParentId]==parentId){
				subGroupData.push(v);
			}
		});
		if(_self.options.sortMode){		//分组显示顺序重排功能
			_self.sortGroups(subGroupData);
		}
		return subGroupData;
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
		var range=this.options["range"];
		var str=_self.edit.replace(/\${class}/ig,"last").replace(/\${group_id}/ig,tmp[_self.options.fId]).replace(/\${hit}/ig,"nohitarea").replace(/\${inp}/ig,_self.inp).replace(/\${group_name}/ig,tmp[_self.options.fName]).replace(/\${op}/ig,_self.options.operate===true?_self.op:"");
		var li=$(str);
		node.parent().find(">li").last().after(li);
		_self.flushNode(node.parent().parent());
		var input=li.find("input");
		
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
		
		input.bind('keypress',function(event){
			if(event.keyCode == "13"){
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
			}
		});
		
		//新分组添加成功后执行的函数，newGroupId为新增的分组的id
		function addSuccess(newGroupId){
			if(newGroupId){				
				var groupId=newGroupId;
				newGroupInfo[_self.options.fId]=groupId;
				newGroupInfo["depth"]=item["depth"];
				newGroupInfo[_self.options.fChildren]=0;
				newGroupInfo[_self.options.subGroup]=[];
				newGroupInfo["sourceFrom"]=item[_self.options.fId]; //添加的来源分组，这里来自同级分组
				if(_self.authRolesMap){
					_self.authRolesMap[groupId]=_self.authRolesMap[item[_self.options.fId]];	//新增加的同级分组直接继承同级分组权限列表
				}					
				_self.range[groupId]="1";		//加入range
				var parentGroup=_self.visibleGroupsInfoMap[newGroupInfo[_self.options.fParentId]];
				if(parentGroup){
					parentGroup[_self.options.fChildren]++;			//父分组的子分组计数+1
					parentGroup[_self.options.subGroup].push(newGroupInfo);	//在父分组的子分组容器里增加 新增分组的引用
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
		_self.expand(node);
		var children=node.find(">ul >li").length;
		var ul=null;
		var last=null;
		
		var cla = "green-font cursor";
		var str=_self.edit.replace(/\${class}/ig,"").replace(/\${inp}/ig,this.inp);
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
		
		input.bind('keypress',function(event){
			if(event.keyCode == "13")    
            {
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
            }
		});
		
		//新分组添加成功后执行的函数，newGroupId为新增的分组的id
		function addSuccess(newGroupId){
			if(newGroupId){						
				var groupId=newGroupId;
				newGroupInfo[_self.options.fId]=groupId;
				newGroupInfo[_self.options.subGroup]=[];
				newGroupInfo[_self.options.fChildren]=0;
				newGroupInfo["depth"]=item["depth"]+1;			//深度比父级多一级 
				newGroupInfo["sourceFrom"]=item[_self.options.fId]; //添加的来源分组，这里来自父分组
				if(_self.authRolesMap){
					_self.authRolesMap[groupId]=_self.authRolesMap[newGroupInfo[_self.options.fParentId]];	//新增加的子分组直接继承父分组权限列表
				}
				_self.range[groupId]="1";		//加入range
				_self.visibleGroupsInfoMap[groupId]=newGroupInfo;			//加入可见列表	
				_self.visibleGroupsInfoMap[newGroupInfo[_self.options.fParentId]][_self.options.subGroup].push(newGroupInfo);	////在父分组的子分组容器里增加 新增分组的引用
			    _self.visibleGroupsInfoMap[newGroupInfo[_self.options.fParentId]][_self.options.fChildren]++;					//父分组的子节点 计数 +1
				_self.append(node,_self.getChildrenData(newGroupInfo[_self.options.fParentId]),_self);	//重新加载本级分组
				if(_self.callbackList["afterAdd"]){
					_self.callbackList["afterAdd"](newGroupInfo,_self);	//回调函数，传回两个参数-新增分组的详细信息和tree对象本身
				}	
			}else{
				li.remove();
				_self.flushNode(node);
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
				array=parentGroup[_self.options.subGroup];
			}else{	
				array=_self.options.groups;	  	//被删除的分组为一级分组
			}
			for(var i=0;i<array.length;i++){	//遍历同级分组数据，从父分组的子分组容器中删除引用
				var groupInfoTemp=array[i];
					if(groupInfoTemp){
						if(groupInfoTemp[_self.options.fId]==item[_self.options.fId]){
						delete array[i];		//删除后数组索引保持不变，被删除元素的索引指向 undefined 。
						break;
					}
				}			
			}
		}
		
		if(item[_self.options.fId]>0 || (typeof item[_self.options.fId] == "string")){
			
			if(node.find("> div.hitarea").length==1)
			{
				$.alert("该分组下面存在子分组，请先删除子分组。");
			}else if(eval("item."+_self.options.systemFlag))
			{
				$.alert("系统分组不能删除")
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
			delete _self.visibleGroupsInfoMap[item[_self.options.fId]];		//从可显示的分组列表里删除该分组数据的引用
			if(!_self.options.isAdmin){									
				delete _self.authRolesMap[item[_self.options.fId]];				//删除权限信息
			}							
			if(item["depth"]!=1){
				_self.visibleGroupsInfoMap[item[_self.options.fParentId]][_self.options.fChildren]--;		//从分组列表里删除该分组数据的引用
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
	
	
	//==================================以下方法都是Tree 功能性的扩展方法，与Tree的生成、初始化、显示无关，只在Tree初始化完成后提供一些额外的便利功能
	
	/*
	 * 权限验证接口
	 * data={
	 * 		groupId:分组id
	 * 		authName:"权限名称"
	 * };
	 * 返回 true或false
	 */
	authValidate:function(data){
		var flag=false;
		if(this.options.isAdmin){
			flag=true;
		}else{
			var auth=this.options.authRolesMap[data["groupId"]];
			flag=(auth&&auth[data["authName"]])?true:false;
		}
		return flag;
	},
	
	
	//获取对象内部的私有属性值
	getAttr:function(attrName){
		var _self=this;
		if(!attrName){
			return _self._privateAttr;
		}else{
			return _self._privateAttr[attrName];
		}
		
	},
	
	//设置当前分组id值
	setCurrentId:function(currentId){
		var _self=this;
		if(_self.visibleGroupsInfoMap[currentId]){
			this._privateAttr["currentId"]=currentId;
			return currentId;
		}
		return null;
	},
	
	/*
	 * 给目标分组加上被选中样式
	 */
	focusOnTargetGroup:function(groupId){
		var _self=this;
		if(_self._privateAttr["currentSpan"]){
			_self._privateAttr["currentSpan"].removeClass("selected");
		}
		var jq=_self.findTargetGroup(groupId);
		if(jq!==null){
			_self._privateAttr["currentSpan"]=jq.addClass("selected");	//把当前的分组元素储存起来
			_self._privateAttr["currentId"]=groupId;
		}
		return jq;
	},
	
	/*
	 * 对目标分组执行点击事件
	 */
	clickTargetGroup:function(groupId){
		var _self=this;
		var jq=_self.findTargetGroup(groupId);
		if(jq!==null){
			jq.trigger("click");
		}
		return jq;
	},
	
	/*
	 * 对第一个可被点击的分组执行点击事件
	 */
	clickFirstEnableGroup:function(){
		var _self=this;
		return _self.clickTargetGroup(_self.getFirstEnableGroupId());
	},
	
	/*
	 * 获取第一个可被点击的分组的id值
	 */
	getFirstEnableGroupId:function(){
		var _self=this;
		var rangeArray=[];
		for(var key in _self.range){
			rangeArray.push(key);
		}
		var sortByNum=function(a,b){
			return parseInt(a)-parseInt(b);
			}
		rangeArray.sort(sortByNum);	
		for(var i=0;i<rangeArray.length;i++){
			if(_self.visibleGroupsInfoMap[rangeArray[i]]){
				return rangeArray[i];
			}
		}
	},
	
	//查找目标分组对象，最终返回目标分组的dom（span）对象
	findTargetGroup:function(groupId){
		if(!groupId){
			return null;
		}
		var _self=this;
		var li=_self.dom.find(">ul >li[group_id="+groupId+"]");
		if(li.length==0){
			var getParentIds=function(groupId,ids){
				if(!ids){
					ids=[];
				}
				ids.push(groupId);
				if(_self.visibleGroupsInfoMap[groupId]){
					getParentIds(_self.visibleGroupsInfoMap[groupId][_self.options.fParentId],ids);
				}
				return ids;
			};			
			//递归获取分组的父id集合
			var ids=getParentIds(groupId);
			var count=ids.length-2;
			var findLi=function(ids,count,li2){
				if(li2){
					li2=li2.find(">ul >li[group_id="+ids[count]+"]");
				}else{
					li2=_self.dom.find(">ul >li[group_id="+ids[count]+"]");
					if(li2.length===0){
						return null;
					}
				}			
				if(li2.attr("class").indexOf("expandable")>-1){
					li2.find(">div[class='hitarea']").click(); 
				}			
				count--;
				if(count>0){
					return findLi(ids,count,li2);
				}else{
					return li2.find(">ul >li[group_id="+ids[count]+"]");
				}
			}
			//递归方式触发点击事件展开分组，找到目标分组
			li=findLi(ids,count);
		}
		return li?li.find(">span[groupId="+groupId+"]"):null;		//返回分组元素对象
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
			var options={
				fId:_self.options.fId,
				fName:_self.options.fName,
				fParentId:_self.options.fParentId,			
				fChildren:_self.options.fChildren,
				subGroup:_self.options.subGroup,
				operate:false,													
				groups:_self.options.groups,		
				authRolesMap:_self.authRolesMap,
				allowVisibleAuthName:paramList["authName"],			//允许分组可被执行点击操作的权限名称
				isAdmin:_self.options.isAdmin,
				sortMode:_self.options.sortMode				
			};
			var confirmButton=$("<button align='right' class='button' >确&nbsp;定</button>");
			var callbackList={
				clickEvent:function(selectedGroupInfo,treeObject){	
					if(paramList["withConfirmButton"]){
						confirmButton.unbind("click").click(function(){	//给确定按钮绑定点击方法
							callback(selectedGroupInfo[_self.options.fId],function(){
								selector.remove();	
								objJQ.attr("showed","false");				
							});			
						});
					}else{	
						//该条件为false时，表示该选择器为第一次执行点击事件，第一次点击为初始化点击，不执行回调方法
						if(treeObject.getAttr("currentId")){	//currentId（默认值为null）是分组树的私有属性，在执行完点击事件后才会被赋值
							callback(selectedGroupInfo[_self.options.fId],function(){							
								selector.remove();							
								objJQ.attr("showed","false");
							});
						}			
						
					}
					
				}
				,afterInit:function(treeObject){			
					var offset=$(objJQ[0]).offset();
					if(paramList["clickTargetId"]){				
						treeObject.clickTargetGroup(paramList["clickTargetId"]);
					}else{
						if(_self._privateAttr["currentId"]){	//如果父Tree已经被点击过了，则定位到父Tree对象被选中的分组
							if(!treeObject.clickTargetGroup(_self._privateAttr["currentId"])){
								treeObject.clickFirstEnableGroup();
							}
						}else{									//如果父Tree未被点击过，则定位到第一个可被执行点击事件的有效分组
							treeObject.clickFirstEnableGroup();
						}						
					}
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
			var tree=new Tree(options,callbackList)    
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
	
	/*
	 * 利用当前分组对象生成一个用div构建的绝对定位窗体
	 * 窗体左边显示分组树，右边用于显示数据列表；
	 * 右边的列表数据显示处理在tree的callbackList参数中的clickEvent回调事件中处理；
	 * clickEvent需要处理的事有：
	 * 	1、拼接显示列表(多选的用checkbox，单选的用radio)，input元素必须有dataHash（数据的id值，该值应该在整个显示列表内具有唯一性）属性
	 * 	2、向窗体内部传送当前列表数据的 键值对（key为数据id字段,value为id对应的数据对象本身）,示例：treeObj.setDialogInnerObject("dataMap",dataMap);	//向dialog传送键值对数据(必要步骤)
	 * 	在完成上述两步后，窗体内部会处理input元素的点击事件，并在点击确定按钮后返回被选择的数据
	 * paramList={	//生成窗体的参数
	 * 		objJQ:$(obj)	//页面元素的Jquery对象,该对象的点击事件触发窗体的生成
	 * 		,title:title	//窗体标题
	 * 		,dialogCSS:{left:offset["left"]}	//可选参数，用于自定义窗体的css（如窗体显示位置，窗体默认的显示位置是objJQ的正对其）
	 * };
	 * callback	点击确定后的回调方法，该方法返回被选中的数据
	 * 范例：
	 * /apps/org/crm/sales/salesman/js/sales_list.js  相关方法：batchAddSalesman、initEmployeeTree
	 * /apps/org/crm/sales/business/js/add_contract.js 相关方法：selectCustomer
	 */
	loadInDialog:function(paramList,callback){
		//添加dialog框样式
		if($("#group_treeV2_css_style").length==0){
			var dialogCss=document.createElement("link");
			dialogCss.setAttribute("text","text/css");
			dialogCss.setAttribute("rel","stylesheet");
			dialogCss.setAttribute("href","/css/group_treeV2.css");
			dialogCss.setAttribute("id","group_treeV2_css_style");
			$("head")[0].appendChild(dialogCss);
		}
		var _self=this;
		var objJQ=paramList["objJQ"];
		if(objJQ.attr("showed")=="true"){		//判断是否需要加载分组选择器，showed是在分组选择器初始化时给触发事件的元素赋予的属性。选择器显示时赋予'true'，隐藏时赋予'false'
			return;
		}
		var dialog=$("<div slideDialog='dialogSelector' class='dialog-wrap'></div>");
		var topSide=$("<div class='dialog-head'><span style='position:absolute;left:10px;top:5px;'>"+(paramList["title"]||"dialog")+"</span></div>")
		var leftSide=$(
			"<div class='dialog-left'>" +
				"<p align='left'><span>分组</span></p>" +
				"<div align='left' style='overflow:auto;height:195px' treeContainer='treeContainer'></div>" +
			"</div>");
		var rightSide=$(
			"<div class='dialog-right'>" +
				"<p align='left'><span>列表</span></p>" +
				"<div id='infoListContainer' align='left' class='dialog-member-list'>" +
					"<p style='display:none;'><input type='radio'/>loading...I</p>" +
				"</div>" +
				"<div pageBar='pageBar' align='center'></div>" +
			"</div>");
		var foot=$(
			"<div class='dialog-foot'>" +
				"<span style='padding:0 5px 0 0;' ><button class='btn btn-common' ctype='confirm' >确 定</button>&nbsp;&nbsp;&nbsp;<button class='btn btn-danger' ctype='cancle'>取 消</button></span>" +
			"</div>");
		dialog.append(topSide).append(leftSide).append(rightSide).append(foot);
		$("body").append(dialog);
		var offset=objJQ.offset();
		dialog.css("position",'absolute')
				.css("left",offset["left"])
				.css("top",offset["top"]+objJQ.height())
				.css("width","640px")
				.css("display","block");
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
				if(target.closest("div[slideDialog='dialogSelector']").length == 0&&flag){
					closeDialog();
				}
		}
		$(document).mousedown(documentMouseDownFordialogSelector);	//绑定mousedown事件，自动消除分组选择器	
		var closeDialog=function(){
			dialog.remove();
			objJQ.attr("showed","false");
			//解除mousedown事件触发方法-documentMouseDownForGroupSelector
			$(document).unbind("mousedown",documentMouseDownFordialogSelector);	
		}	 
		 var dialogDataStore={
		 	selected:{count:0}
		 	,dataMap:"在获取到数据列表后,必须将数据列表转换为键值对格式,使用tree对象的setDialogInnerObject('dataMap',map)方法将键值对数据保存到dataMap"
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
		rightSide.find("#infoListContainer").click(function(event){	//动态绑定新加入infoListContainer的所有input元素的点击事件
			var dataMap=_self.getDialogInnerObject("dataMap");
			if(typeof(dataMap)==="string"){
				if(console){
					console.log(dataMap);
				}
			}
			var type=event.target.getAttribute("type");
			if(inputHandler[type]){
				inputHandler[type]($(event.target));
			}
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
		 	return _self;
		}
		if(paramList["dialogCSS"]){
			for(var key in paramList["dialogCSS"]){
				dialog.css(key,paramList["dialogCSS"][key]);
			}
		}
		dialog.show();
		(function dialogPositionFix(dialog){	//修正初始化窗口的显示位置，防止显示不全
			var offset=dialog.offset();
			var dialogHeight=dialog.height();
			var dialogWidth=dialog.width();
			var windowHeight=$(window).height();
			var windowWidth=$(window).width();
			if((offset.top+dialogHeight)>windowHeight){
				var topPosi=windowHeight-dialogHeight;
				dialog.css("top",topPosi<0?0:topPosi);
			}
			if((offset.left+dialogWidth)>windowWidth){
				var left=windowWidth-dialogWidth-10;
				dialog.css("left",left<0?0:left);
			}
		})(dialog);
		
		
		objJQ.attr("showed","true");
		_addDivDragEvent(dialog,topSide);
		return {dialog:dialog,leftSide:leftSide,rightSide:rightSide,topSide:topSide,showDialog:showUp};
	}
}

/*
 * 给div绑定一个拖拽事件
 * target为被拖拽对象，dragArea为拖拽区域，两个参数都必须是jquery的包装对象
 */
function _addDivDragEvent(target,dragArea){
	var targetWidth=target.width();
	var targetHeight=target.height();
	var windowWidth=0;
	var windowHeight=0;
	var documentJQ=$(document);
	dragArea.css("cursor","move");
	dragArea.mousedown(function(event){
		this.position_Recorder={	//鼠标按下时 
			X:event.clientX,	
			Y:event.clientY
		};
		documentJQ.mousemove(dragEvent);
		documentJQ.mouseup(removeDragEvent);
		windowWidth=$(window).width();
		windowHeight=$(window).height();
	});
	var dragEvent=function(event){
		var positionX=dragArea[0].position_Recorder.X;
		var positionY=dragArea[0].position_Recorder.Y;
		var offset=target.offset();
		var posiX=event.clientX<0?0:(event.clientX>windowWidth?windowWidth:event.clientX);
		var posiY=event.clientY<0?0:(event.clientY>windowHeight?windowHeight:event.clientY);
		
		//原位置+鼠标移动的偏移量，得到最终位置
		var	domLeft=offset["left"]+(posiX-positionX);	
		var	domTop=offset["top"]+(posiY-positionY);
		
		domLeft=domLeft<0?0:(domLeft+targetWidth>windowWidth?(windowWidth-targetWidth):domLeft);
		domTop=domTop<0?0:(domTop+targetHeight>windowHeight?(windowHeight-targetHeight):domTop);
		
		target.css("left",domLeft).css("top",domTop);
		//保存此次执行得到的结果，下次执行这个方法时用
		dragArea[0].position_Recorder.X=posiX;
		dragArea[0].position_Recorder.Y=posiY;
	}
	var removeDragEvent=function(){				//解除之前绑定的事件
		documentJQ.unbind("mousemove",dragEvent);		
		documentJQ.unbind("mouseup",removeDragEvent);
	}
}