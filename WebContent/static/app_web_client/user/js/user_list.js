define(['jquery','zzapp.datalist','jquery.lhgcalendar','zzapp.groupTree'], function($) {
return function(){



var pageOpt=null,authList;
var zzTable=null,tree=null;var auth={};
var orgId=null,areaId=null,keyword=null;
function init(op){
	pageOpt=op;
//	authList=pageOpt.initParams.authList;
	pageOpt.zzItem.operator.find('[z_b]').addClass('no-auth');
	
//	if(authList.length==0){
//		pageOpt.zzItem.tree.html('<span  style="color:gray;">没有权限</span>');
//		return;
//	}
	
//	var auths=authList[0].auths.split(",");
//	for(var i=0;i<auths.length;i++){
//		auth[auths[i]]="1";
//	}
	zzTable=initZZTable();
	zzTable.loadData({pageNum:1});
//	bindSelectorEvent();
}


function bindSelectorEvent(){
	var undef;
	pageOpt.zzItem["order_typy_selector"].on("click","a",function(){
		pageOpt.zzItem["order_typy_selector"].find("a").removeClass("current");
		zzTable.loadData({pageNum:1,keyword:keyword!=null?keyword:undef,orderType:$(this).addClass("current").attr("z_val")});
	});
	pageOpt.zzItem["state_selector"].on("click","a",function(){
		pageOpt.zzItem["state_selector"].find("a").removeClass("current");
		zzTable.loadData({pageNum:1,keyword:keyword!=null?keyword:undef,flag:$(this).addClass("current").attr("z_val")});
	});
}


/*
 * 初始化数据列表
 */
function initZZTable(){
	function handleDate(date_num){
		if(!date_num){
			return '';
		}
		function fix(num){
			return num<10?('0'+num):num;
		}
		var d = new Date(date_num);
		var month=fix(d.getMonth()+1);
		var date=fix(d.getDate());
		var h=fix(d.getHours());
		var m=fix(d.getMinutes());
		return [d.getFullYear(),month,date].join('-');
	}
	function viewDetail(params){
		pageOpt.load({
			tplFile:'tpl.html',tplName:'edit_news',js:'js/edit_news.js'
			,initParams:{
				orgId:orgId
				,item:params.item
				,id:params.item.id
				,authList:authList
				,refresh:function(){
					zzTable.loadData({pageNum:1});
				}
			}
			
		});
	}
	var attrTable=[
	    {name:"id",title:"id",width:150}
		,{name:"name",title:"名字",width:150,mainAttr:true,event:{click:viewDetail},css:{"font-weight":"bold","cursor":"pointer"}}
		,{name:"age",title:"年龄",width:150}
		,{name:"createDate",title:"创建时间",width:120,valHandler:function(data){return data.createDate?$.calendar.formatDate(new Date(data.createDate),'yyyy-MM-dd'):'';}}
	   ];
	
	var attrList=[];
	var options={
		ct:pageOpt.zzItem['data_list_container'],
		attrTable:attrTable,
		attrList:attrList,
		mode:'table',
		params:{pageSize:15,pageNum:1,orgId:orgId,orderType:1},
		getData:function(params,callback){
			params["c"]=new Date().getTime();
			var url = '/MrZive/getList';
			$.getJSON(url,params,function(json){
				callback({total:json.total,row:json.row,pageSize:params.pageSize,pageNum:params.pageNum});
				if(json.total>0&&json.row.length==0&&params.pageNum>1){
					zzTable.loadData({pageNum:1});
				}
			});
		}
	}
	return $.create_zz_table(options);
}


return {init:init};};});