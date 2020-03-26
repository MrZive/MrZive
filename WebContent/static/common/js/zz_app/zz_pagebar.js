(function(){
	$.extend({
		
		zz_pagebar:function(config){
			return new pagebar(config);
		}
	});
	
	/*
	 * config={
	 * 		ct:$("#pagebar"),			//显示容器
	 * 		labelNum:10,				//标签数量
	 * 		callback:function(num){}	//标签点击回调，num为被点击的页码
	 * };
	 * 
	 * refresh({total:100,pageSize:10,curPage:1})
	 */
	function pagebar(config){
		var _s=this;
		_s.config={ct:null,labelNum:10,callback:function(num){alert("点击事件未定义！")}};
		for(var key in _s.config){
			if(config[key]){
				_s.config[key]=config[key];
			}
		}
		var h=['<div class="page-bar"><a pnum="1" href="javascript:void(0);">首页</a><a ntype="prev" pnum="" href="javascript:void(0);">上一页</a>'];
		for(var i=0;i<_s.config.labelNum;i++){
			h.push('<a ntype="'+i+'" href="javascript:void(0);">...</a>');
		}
		h.push('<a ntype="next" pnum="" href="javascript:void(0);">下一页</a><a ntype="last" pnum=""  href="javascript:void(0);">尾页</a><span ntype="numtip"></span>&nbsp;共<span ntype="total"></span>条数据');
		h.push('&nbsp;<span>跳转到&nbsp;<input ntype="jumper" type="text"/>&nbsp;页&nbsp;<a ntype="jumper_confirm" href="javascript:void(0);" class="btn">确定</a></span></div>');
		
		this.jqDom=$(h.join("")).on("click","[pnum]",function(){
			var jq=$(this);
			var pageNum=parseInt(jq.attr("pnum"));
			config.callback(pageNum);
		});
		config.ct.append(this.jqDom);
		_s.pitem={numlabel:[]};
		_s.jqDom.find("[ntype]").each(function(i,v){
			var jq=$(v);
			var ntype=jq.attr("ntype");
			_s.pitem[ntype]=jq;
			var pnum=parseInt(ntype);
			pnum>=0?_s.pitem["numlabel"].push(jq):_s.pitem[ntype]=jq;
		});
		_s.pitem["jumper_confirm"].on("click",function(){
			var page=parseInt(_s.pitem["jumper"].val())||1;
			var max=parseInt(_s.pitem["last"].attr("pnum"));
			config.callback(page<1?1:page>=max?max:page);
		});
		_s.pitem["jumper"].on("keypress",function(event){
			event.keyCode==13?_s.pitem["jumper_confirm"].click():null;
		}).on("click",function(){
			$(this).select();
		});
	}
	
	pagebar.prototype={
		/*
		 * opt={
		 * 		total:数据总数
		 * 		,pageSize:每页数据数
		 * 	·	,curPage:当前页码
		 * }
		 */
		refresh:function(opt){
			var _s=this;
			if(opt.total<1){
				_s.jqDom.hide();
				return;
			}else{
				_s.jqDom.show();
			}
			var curPage=opt.curPage;
			var pitem=_s.pitem;
			var r=calculate(opt.total,opt.pageSize,opt.curPage,_s.config.labelNum);
			pitem["prev"].attr("pnum",curPage>1?(curPage-1):1);
			pitem["last"].attr("pnum",r.max);
			pitem["next"].attr("pnum",curPage<r.max?(curPage+1):r.max);
			pitem["jumper"].val(curPage);
			pitem["numtip"].text(curPage+'/'+r.max);
			pitem["total"].text(opt.total);
			$.each(pitem["numlabel"],function(i,v){
				var jq=$(v);
				var pnum=parseInt(jq.attr("ntype"))+r.first;
				pnum<=r.last?jq.show():jq.hide();
				jq.attr("pnum",pnum).text(pnum);
				curPage==pnum?jq.addClass("current"):jq.removeClass("current");
			});
		}
	}
	
	/*
	 * @param total 	数据总条数
	 * @param pageSize 	每页显示数
	 * @param curPage 	当前页码
	 * @param labelNum 	分页标签数
	 * 计算分页数据
	 * return {first:第一个标签页码,last:最后一个标签页码,max:总页数};
	 */
	function calculate(total,pageSize,curPage,labelNum){
		var f=1,l=0;
		var maxPage=Math.ceil(total/pageSize);
		maxPage=maxPage===0?1:maxPage;
		var half=labelNum/2;
		var p=Math.round(half);
		if(curPage<=p){					//首
			f=1;
			l=maxPage>labelNum?labelNum:maxPage;
		}else if(curPage>maxPage-p){	//尾
			f=maxPage-labelNum+1;
			f=f<1?1:f;
			l=maxPage;
		}else{							//中间
			f=curPage-(p==half?(p-1):--p);
			l=curPage+p;
		}
		return {first:f,last:l,max:maxPage};
	}
	
})();
