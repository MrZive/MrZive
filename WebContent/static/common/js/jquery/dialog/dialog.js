var isIE = navigator.userAgent.toLowerCase().indexOf("msie") != -1;
var isIE6 = navigator.userAgent.toLowerCase().indexOf("msie 6.0") != -1;
var isGecko = navigator.userAgent.toLowerCase().indexOf("gecko") != -1;
var isQuirks = document.compatMode == "BackCompat";

function stopEvent(event){//阻止一切事件执行,包括浏览器默认的事件
	if(!event)return;
	if(isGecko){
		event.preventDefault();
		event.stopPropagation();
	}
	event.cancelBubble = true
	event.returnValue = false;
}

function getParentWindow(){
	try{
		var pw=window;
		while(pw!=pw.parent){
			pw = pw.parent;
		}
		return pw;
	}catch(e){
		alert(e);
	}
}

var dialogParentWindow=getParentWindow();

var Dialog=function(settings){
	var _self=this;
	this.zIndex=990;
	this.isIE = isIE;
	this.isIE6 = isIE6;
	this.isGecko = isGecko;
	this.isQuirks = isQuirks;
	this.pw = dialogParentWindow;
	this.onLoad = null;
	this.window = null;
	
	if(!this.pw._index) this.pw._index=1;
	this.Index=this.pw._index++;
	
	//公开的属性
	this.Width = 400;			//窗口宽度
	this.Height = 300;			//窗口高度
	this.Top = 0;				//窗口Top				
	this.Left = 0;				//窗口Left
	this.Title = "系统提示";		//窗口标题
	this.URL = null;			//窗口内容来源的URL地址
	this.HTML=null				//内嵌HTML代码
	this.WindowFlag = false;
	this.Message = null;		//窗口提示信息内容
	this.MessageTitle = null;	//窗口提示信息栏标题
	this.ShowMessageRow = false;//是否显示窗口提示信息栏
	this.ShowButtonRow = true;	//是否显示Button行
	this.AutoClose=true;		//自动关闭对话框
	this.StylePath = 'static/common/js/jquery/dialog/images/green/';//Dialog风格图片路径配置
	this.ContextPath = '';//弹出框内页面路径配置
	this.ShowOKButton=true;
	this.OKButtonText="确定";
	this.ShowCancelButton=true;
	this.CancelButtonText="取消";
	this.Data=null;				//传给子窗口(iframe)的对象
	this.state=null;		//在dialog状态栏对象
	this.Type=0;			//窗口类型，来源于Dialog.DialogType
								
	
	this.setSetting(settings);
//	if(typeof(this.pw.self.dialogList) == "undefined" || this.pw.self.dialogList == null) {
		this.pw.self.dialogList=[];
//	}
	this.pw.self.dialogList.push(this);
	
//	try{
//		if(this.isIE){
//			this.pw.document.attachEvent("onkeydown",function(event){_self.onKeyDown(event)});
//			this.pw.attachEvent('onresize',function(event){_self.setPosition(event)});
//		}else{
//			this.pw.document.addEventListener("keydown",function(event){_self.onKeyDown(event)},false);
//			this.pw.addEventListener('resize',function(event){_self.setPosition(event)},false);
//		}
//	}catch(e){}
}

Dialog.prototype.setSetting=function(settings){
	for(var key in settings||{}){
		this[key]=settings[key];
	}
};

Dialog.DialogType={
	DEFAULT:0,	//默认
	ALERT:1,	//alert
	CONFIRM:2,	//confirm
	OK:3,		//OK	扩展
	ERROR:4,	//ERROR 扩展
	PROMPT:5,	//PROMPT 扩展
	LOADING:6	//loading
};
Dialog.DialogTypeKey={
	0:"alert",
	1:"alert",
	2:"query",
	3:"info",
	4:"error",
	5:"query",
	6:"loading"
}
/**
 * 显示遮罩层背景
 * @param opacity 透明度［0-100］，默认为40
 */
Dialog.prototype.showBG=function(opacity){
	this.ID="Dialog"+this.Index;
	
	opacity=opacity||40;
	var _self=this;
	var doc = this.pw.document;
	
	this.clientLeft=this.pw.document.body.clientLeft;
	this.clientTop=this.pw.document.body.clientTop;
	var cw = this.clientWidth = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
	var ch = this.clientHeight = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;//必须考虑文本框处于页面边缘处，控件显示不全的问题
	var sl = this.scrollLeft = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var st = this.scrollTop = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
	var sw = this.scrollWidth=Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	var sh = this.scrollHeight=Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);//考虑滚动的情况
	sw=Math.max(sw,cw);
	sh=Math.max(sh,ch);
	
	if(!this.Height)this.Height = this.Width/2;
	if(this.Top==0)this.Top = (ch - this.Height - 30) / 2 + st - 8;
	if(this.Left==0)this.Left = (cw - this.Width - 12) / 2 +sl;
	if(this.ShowButtonRow)this.Top -= 18;//按钮行高36
		
	
//	var pwbody=doc.getElementsByTagName(isQuirks?"BODY":"HTML")[0];
//	pwbody.style.overflowX="hidden";//禁止出现滚动条
	
	if(this.pw.self.dialogBG){
		this.pw.self.dialogBG.style.cssText = "width:"+cw+"px;background-color:#333;position:absolute;left:0px;top:0px;opacity:" + (opacity/100) + ";filter:alpha(opacity="+opacity+");height:" + sh + "px;z-index:"+(this.zIndex+this.Index-1);
		//top.dialogBG.style.zIndex=this.zIndex+this.Index-1;
		this.pw.self.dialogBG.style.display="";
		return;
	}else{
		this.pw.self.dialogBG= this.pw.document.createElement("div");
		this.pw.self.dialogBG.id="DialogBGDiv";
		this.pw.self.dialogBG.oncontextmenu=this.pw.self.dialogBG.onselectstart=function(){return false};
		this.pw.document.body.appendChild(this.pw.self.dialogBG);
		this.pw.self.dialogBG.style.cssText = "width:"+cw+"px;background-color:#333;position:absolute;left:0px;top:0px;opacity:" + (opacity/100) + ";filter:alpha(opacity="+opacity+");height:" + sh + "px;z-index:"+(this.zIndex+this.Index-1);
	}
	if(this.isIE6){
		var bgIframeBox=this.pw.document.createElement('<div style="position:relative;width:100%;height:100%;"></div>');
		var bgIframe=this.pw.document.createElement('<iframe src="about:blank" style="filter:alpha(opacity=1);" width="100%" height="100%"></iframe>');
		var bgIframeMask=this.pw.document.createElement('<div src="about:blank" style="position:absolute;background-color:#333;filter:alpha(opacity=1);width:100%;height:100%;"></div>');
		bgIframeBox.appendChild(bgIframeMask);
		bgIframeBox.appendChild(bgIframe);
		
		this.pw.self.dialogBG.appendChild(bgIframeBox);
		
		var bgIframeDoc = bgIframe.contentWindow.document;
		bgIframeDoc.open();
		bgIframeDoc.write("<body style='background-color:#333' oncontextmenu='return false;'></body>") ;
		bgIframeDoc.close();
		delete bgIframeDoc;
		bgIframeDoc=null;
		
//		bgIframeBox.removeChild(bgIframeMask);
		delete bgIframeMask;
		bgIframeMask=null;
		
//		bgIframeBox.removeChild(bgIframe);
		delete bgIframe;
		bgIframe=null;
		
//		this.pw.self.dialogBG.removeChild(bgIframeBox);
		delete bgIframeBox;
		bgIframeBox=null;
	}
//this.pw.document.body.appendChild(top.dialogBG);
	//$(this.bg).show();
}

Dialog.prototype.show = function(){
	this.showBG();
	
	var _self=this;

	var arr = [];
	arr.push("<table name='_container' class='dialog' style='cursor:move;-moz-user-select:none;width:"+(this.Width+26)+"' border='0' cellpadding='0' cellspacing='0' width='"+(this.Width+26)+"'>");
	arr.push("  <tr style='cursor:move;' name='_draghandle'>");
	arr.push("    <td width='13' style=\"height:33px;background-image:url("+this.StylePath+"dialog_lt.png) !important;background-image: none;padding:0px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+this.StylePath+"dialog_lt.png', sizingMethod='crop');\"><div style='width:13px;'></div></td>");
	arr.push("    <td style=\"height:33px;padding:0px;background-image:url("+this.StylePath+"dialog_ct.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+this.StylePath+"dialog_ct.png', sizingMethod='crop');\"><div style=\"float:left;font-weight:bold; color:#0C0C0C; padding:9px 0 0 4px;\"><img src=\""+this.StylePath+"icon_dialog.gif\" align=\"absmiddle\">&nbsp;<span name='title'>"+this.Title+"</span></div>");
	arr.push("      <div name='_close' style=\"position: relative;cursor:pointer; float:right; margin:5px 0 0; _margin:5px 0 0;height:17px; width:28px; background-image:url("+this.StylePath+"dialog_closebtn.gif)\" onMouseOver=\"this.style.backgroundImage='url("+this.StylePath+"dialog_closebtn_over.gif)'\" onMouseOut=\"this.style.backgroundImage='url("+this.StylePath+"dialog_closebtn.gif)'\" drag='false'></div></td>");
	arr.push("    <td width='13' style=\"height:33px;padding:0px;background-image:url("+this.StylePath+"dialog_rt.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+this.StylePath+"dialog_rt.png', sizingMethod='crop');\"><div style=\"width:13px;\"></div></td>");
	arr.push("  </tr>");
	arr.push("  <tr drag='false'><td width='13' style=\"padding:0px;background-image:url("+this.StylePath+"dialog_mlm.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+this.StylePath+"dialog_mlm.png', sizingMethod='crop');\"></td>");
	arr.push("    <td align='center' valign='top' style='padding:0px;'><a hthis.pw.self='#;' name='_forTab'></a>");
	arr.push("    <table width='100%' border='0' cellpadding='0' cellspacing='0' bgcolor='#FFFFFF'>");
	arr.push("        <tr name='_MessageRow' style='display:none'>");
	arr.push("          <td height='50' valign='top' style='padding:0px;'><table name='_MessageTable' width='100%' border='0' cellspacing='0' cellpadding='8' style=\" background:#EAECE9 url("+this.StylePath+"dialog_bg.jpg) no-repeat right top;\">");
	arr.push("              <tr><td width='25' height='50' align='right'><img name='_MessageIcon' src='"+this.StylePath+"window.gif' width='32' height='32'></td>");
	arr.push("                <td align='left' style='line-height:16px;'>");
	arr.push("                <h5 class='fb' name='_MessageTitle'>&nbsp;</h5>");
	arr.push("                <div name='_Message'>&nbsp;</div></td>");
	arr.push("              </tr></table></td></tr>");
	arr.push("        <tr><td align='center' valign='top' style='padding:0px;'><div style='position:relative;width:"+this.Width+"px;height:"+this.Height+"px;'>");
	arr.push("         <div  name='_Covering' style='position:absolute; height:100%; width:100%;display:none;'>&nbsp;</div>");
	if(this.HTML){
		arr.push(this.HTML);
	}else if(this.URL){
		arr.push("          <iframe src='about:blank'");
		arr.push(" name='_DialogFrame' allowTransparency='true'  width='100%' height='100%' frameborder='0' style=\"background-color: #transparent; border:none;\"></iframe>");
	}
	arr.push("        </div></td></tr>");
	arr.push("        <tr drag='false' name='_ButtonRow'><td height='36' style='padding:0px;'>");
	arr.push("            <div style='border-top:#dadee5 1px solid; background-color:#f6f6f6;magin:0px;height:auto;'>");
	arr.push("            	<div name='_DialogButtons' style='float:right;width:180px;text-align:right; padding:8px 20px;'>");
	arr.push("           		<button name='_ButtonOK'  type='button' style='cursor:pointer;margin-left:0px;height:22px;width:54px;border:0px;color:#000;background:url("+this.StylePath+"dialog_btn.png) #f6f6f6;'>"+this.OKButtonText+"</button>&nbsp;");
	arr.push("           		<button name='_ButtonCancel'  type='button' style='cursor:pointer;margin-left:0px;height:22px;width:54px;border:0px;color:#000;background:url("+this.StylePath+"dialog_btn.png) #f6f6f6;'>"+this.CancelButtonText+"</button>");
	arr.push("            	</div>");
	arr.push("            	<div name='_DialogState' style='display:none;margin-right:155px!important;padding-top:0!important;padding-top:5px;height:36px;text-align:left;background:transparent;-moz-user-select:none;'>");
	arr.push("					<div style='display:none;'></div><ul style='display:none;'><li name='tmp'></li></ul><ul name='list'></ul>");
	arr.push("            	</div>");
	arr.push("            	<div style='clear:both;'></div>");
	arr.push("            </div>");
	arr.push("		  </td></tr>");
	arr.push("      </table></td>");
	arr.push("    <td width='13' style=\"padding:0px;background-image:url("+this.StylePath+"dialog_mrm.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+this.StylePath+"dialog_mrm.png', sizingMethod='crop');\"></td></tr>");
	arr.push("  <tr><td width='13' style=\"padding:0px;height:13px;background-image:url("+this.StylePath+"dialog_lb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+this.StylePath+"dialog_lb.png', sizingMethod='crop');\"></td>");
	arr.push("    <td style=\"padding:0px;background-image:url("+this.StylePath+"dialog_cb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+this.StylePath+"dialog_cb.png', sizingMethod='crop');\"></td>");
	arr.push("    <td width='13' style=\"height:13px;padding:0px;background-image:url("+this.StylePath+"dialog_rb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+this.StylePath+"dialog_rb.png', sizingMethod='crop');\"></td>");
	arr.push("  </tr></table>");
	
	this.DialogDiv = this.pw.document.createElement("div");
	this.DialogDiv.oncontextmenu=this.DialogDiv.onselectstart=function(){return false};
	
	this.DialogDiv.style.cssText = "position:absolute; display:block;z-index:"+(this.zIndex+this.Index)+";left:"+this.Left+"px;top:"+this.Top+"px";

	//arr.push("<div id='testDrag' style='color:red;background:#fff;'>testDrag</div>");
	this.DialogDiv.innerHTML = arr.join('\n');
	
	for(var i in arr)arr.shift();
	
	this.pw.document.body.appendChild(this.DialogDiv);
	
	var container=$(this.DialogDiv).find("table[name=_container]")[0];
	container.oncontextmenu=container.onselectstart=function(){return false};
	delete container;container=null;
	
	this.Frame=$(this.DialogDiv).find("iframe[name=_DialogFrame]")[0];
	if(this.URL){
		this.Frame.removeAttribute("src");
		if(this.isIE){
			this.Frame.onreadystatechange = function(){
				if(!_self.Frame)return;
				if(_self.Frame.readyState=="complete"){
					_self.sure=this.contentWindow.sure;
					this.contentWindow.self.dialog=_self;
					this.contentWindow.self.data=_self.Data;
					this.contentWindow.close();
				}
			}
		}else{
			this.Frame.onload=function(){
				_self.sure=this.contentWindow.sure;
				this.contentWindow.self.dialog=_self;
				this.contentWindow.self.data=_self.Data;
			}
		}
		
		var u=(this.URL.substr(0,7)=="http://" || this.URL.substr(0,1)=="/")?this.URL:(this.ContextPath+this.URL);
		if(this.param){
			u+=(u.match(/\?/) ? "&" : "?")+this.param;
		}
		
		this.Frame.src=u;
	}else{
		this.Frame=null;
	}
	
	var dragHandle=$(this.DialogDiv).find("table[name=_container]")[0];
	Drag.init(this,dragHandle,this.DialogDiv);//注册拖拽方法
	delete dragHandle;dragHandle=null;
	
	if(!isIE){
		var covering=$(this.DialogDiv).find("div[name=_Covering]")[0];
		this.DialogDiv.onDragStart = function(){covering.style.display=""}
		this.DialogDiv.onDragEnd = function(){covering.style.display="none"}
		delete covering;
		//covering=null;
	}
	
	//显示标题图片
	if(this.ShowMessageRow){
		var messageRow=$(this.DialogDiv).find("tr[name=_MessageRow]");
		messageRow.show();
		if(this.MessageTitle)
			messageRow.find("h5[name=_MessageTitle]").text(this.MessageTitle);
		else
			messageRow.find("div[name=_Message]").text(this.Message);
		delete messageRow;messageRow=null;
	}
	
	//显示按钮栏
	var buttonRow=$(this.DialogDiv).find("tr[name=_ButtonRow]");
	if(!this.ShowButtonRow){
		buttonRow.hide();
	}
	
	this.OKButton =buttonRow.find("button[name=_ButtonOK]")[0];
	if(this.ShowOKButton==false) this.OKButton.style.display="none";
	this.CancelButton = buttonRow.find("button[name=_ButtonCancel]")[0];
	if(this.ShowCancelButton==false) this.CancelButton.style.display="none";
	
	delete buttonRow;buttonRow=null;
			
	//当子页面里边有sure这个方法时，会自动调用
	this.OKButton.onclick = this.OKEvent?function(){
		//debugger;
		try{
			if(_self.sure){
				_self.sure(function(a,b,c,d,e,f,g,h){
					if(_self.OKEvent) _self.OKEvent.apply(this,arguments);
					if(_self.AutoClose==true)_self.close();
				});
			}else{
				_self.OKEvent();
				if(_self.AutoClose==true)_self.close();
			}			
		}catch(e){}
	}:this.close;
	
	//提供给其他页面调用的接口
	this.callBack=this.callback=this.call=function(a,b,c,d,e,f,g,h){
		if(_self.OKEvent) _self.OKEvent.apply(this,arguments);
		if(_self.AutoClose==true)_self.close();
	};
	
	if(this.CancelEvent){
		this.CancelButton.onclick = function(a,b,c,d,e,f,g){
			_self.CancelEvent.apply(this,arguments);
			_self.close();
		}
	}else{
		this.CancelButton.onclick=function(){_self.close()};
	}
	
	$(this.DialogDiv).find("div[name=_close]")[0].onclick=function(){_self.CancelButton.click()};
	
	if(this.ShowButtonRow==true){
		if(this.ShowOKButton==true)
			this.OKButton.focus();
		else if(this.ShowCancelButton==true)
			this.CancelButton.focus();
	}
	
	if(this.Type==Dialog.DialogType.DEFAULT&&this.ShowButtonRow){
		this.state=new State($(this.DialogDiv).find("div[name=_DialogState]"));
	}
	
	//delete _self;_self=null;
}

/**
 * Loading
 * @param opacity--背景透明度［0-100］,默认为40
 */
Dialog.loading=function(opacity){
	if(dialogParentWindow.self.loading) return dialogParentWindow.self.loading;
	
	var diag = new Dialog();
	
	diag.Type=Dialog.DialogType.LOADING;
	diag.isLoading=true;
	diag.Width = 36;
	diag.Height = 32;
	diag.Index+=10;
	
	diag.showBG(opacity);
		
	diag.DialogDiv = diag.pw.document.createElement("div");
	diag.DialogDiv.oncontextmenu=diag.DialogDiv.onselectstart=function(){return false};
	
	diag.DialogDiv.style.cssText = "position:absolute; display:block;z-index:"+(diag.zIndex+diag.Index)+";left:"+diag.Left+"px;top:"+diag.Top+"px";

	diag.DialogDiv.innerHTML = "<img src='"+diag.StylePath+"load.gif'>";
	
	diag.pw.document.body.appendChild(diag.DialogDiv);
	
//	if(diag.isIE)
//		diag.DialogDiv.focus();
//	setTimeout(function(){
//		dialogParentWindow.$("#DialogBGDiv").hide();
//		$(diag.DialogDiv).remove();
//	},5000)
	
	return diag;
}

Dialog.prototype.dialog=function(){
	
	this.show();
	
	var win=this.Frame.contentWindow;
	var doc = win.document;
	//delete win;win=null;
	doc.open();
	doc.write("<body oncontextmenu='return false;'></body>") ;
	var arr = [];
	arr.push("<table height='100%' border='0' align='center' cellpadding='10' cellspacing='0'>");
	arr.push("<tr><td align='right'><img id='Icon' src='"+this.StylePath+"icon_"+(Dialog.DialogTypeKey[this.Type]||"alert")+".gif' align='absmiddle'></td>");
	arr.push("<td align='left' id='Message' style='font-size:9pt'>"+this.message+"</td></tr></table>");
	var div1 = doc.createElement("div");
	div1.innerHTML = arr.join('');
	doc.body.appendChild(div1);
	doc.close();
	delete doc;doc=null;
	if(this.Type==Dialog.DialogType.PROMPT){
		this.OKButton.focus();
		var _self=this;
		this.OKButton.onclick=this.OKEvent?function(){
			_self.OKEvent(win.document.getElementById("prompt_content").value);
			_self.close();
		}:this.close();
	}else if(this.Type!=Dialog.DialogType.CONFIRM){
		$(this.DialogDiv).find("button[name=_ButtonOK]").hide();
		this.CancelButton.innerHTML = "确定";
		this.CancelButton.focus();
	}else{
		this.OKButton.focus();
	}
	$(this.DialogDiv).find("div[name=_DialogButtons]")[0].style.textAlign = "right";
}

Dialog.prompt=function(content,option,callback){
	if ( jQuery.isFunction( option ) ) {
		var tmp=callback;
		callback=option;
		option=tmp;
	}
	
	option=option||{};
	option.Width=option.Width||300;
	option.Height=option.Height||120;
	option.Title=option.Title||"系统提示";
	option.URL="javascript:void(0);";
	var diag = new Dialog(option);

	if ( jQuery.isFunction( callback ) )diag.OKEvent=callback;
	
	var arr=[];
	arr.push('<div><strong>内容：</strong></div>');
	arr.push('<div>');
	arr.push('<textarea rows="3" cols="20" id="prompt_content">'+(content||"")+'</textarea>');
	arr.push('</div>');
	diag.message=arr.join("");
	diag.Type=Dialog.DialogType.PROMPT;
	diag.dialog();
	diag.Frame.contentWindow.document.getElementById("prompt_content").focus();
	return diag;
}

Dialog.ok=function(msg,option,callback){
	if ( jQuery.isFunction( option ) ) {
		var tmp=callback;
		callback=option;
		option=tmp;
	}
	
	option=option||{};
	option.Width=option.Width||300;
	option.Height=option.Height||120;
	option.Title=option.Title||"系统提示";
	option.URL="javascript:void(0);";
	
	var diag = new Dialog(option);
	if ( jQuery.isFunction( callback ) )diag.CancelEvent=callback;
	
	diag.message=msg;
	diag.Type=Dialog.DialogType.OK;
	diag.dialog();
	return diag;
}

Dialog.error=function(msg,option,callback){
	if ( jQuery.isFunction( option ) ) {
		var tmp=callback;
		callback=option;
		option=tmp;
	}
	
	option=option||{};
	option.Width=option.Width||300;
	option.Height=option.Height||120;
	option.Title=option.Title||"错误提示";
	option.URL="javascript:void(0);";
	
	var diag = new Dialog(option);
	if ( jQuery.isFunction( callback ) )diag.CancelEvent=callback;
	diag.message=msg;
	diag.Type=Dialog.DialogType.ERROR;

	diag.dialog();
	return diag;
}

Dialog.alert = function(msg,option,callback){
	if ( jQuery.isFunction( option ) ) {
		var tmp=callback;
		callback=option;
		option=tmp;
	}
	
	option=option||{};
	option.Width=option.Width||300;
	option.Height=option.Height||120;
	option.Title=option.Title||"系统提示";
	option.URL="javascript:void(0);";
	
	var diag = new Dialog(option);
	if ( jQuery.isFunction( callback ) )diag.CancelEvent=callback;
	diag.message=msg;
	diag.Type=Dialog.DialogType.ALERT;
	
	diag.dialog();
	return diag;
}

Dialog.confirm = function(msg,option,callback){
	if ( jQuery.isFunction( option ) ) {
		var tmp=callback;
		callback=option;
		option=tmp;
	}
	
	option=option||{};
	option.Width=option.Width||300;
	option.Height=option.Height||120;
	option.Title=option.Title||"系统提示";
	option.URL="javascript:void(0);";
	
	var diag = new Dialog(option);
	if ( jQuery.isFunction( callback ) )diag.OKEvent=callback;
	diag.message=msg;
	diag.Type=Dialog.DialogType.CONFIRM;
	
	diag.dialog();
	return diag;
}

Dialog.prototype.close = function(){
//	try{
//		debugger;
		//this.pw.self._index--;
		try{
			for(var i=0,l=this.pw.self.dialogList.length;i<l;i++){
				if(this.pw.self.dialogList[i].Index==this.Index){
					try{
					if(this.pw.self.dialogList.length<=1)
						this.pw.self.dialogList=[];
					else
						this.pw.self.dialogList.splice(i,1);
					}catch(e){
						this.pw.self.dialogList=[];
					}
					break;
				}
			}
		}catch(e){
			
		}
		//this.pw.self.dialogList.pop();
//		debugger;
		try{
		if(typeof(this.pw.self.dialogList)=="undefined" || this.pw.self.dialogList.length<=0){
			var doc = this.pw.document;
	//		var pwbody=doc.getElementsByTagName(isQuirks?"BODY":"HTML")[0];
	//		pwbody.style.overflow="auto";//还原滚动条
			
			var d=doc.compatMode == "BackCompat"?doc.body:doc.documentElement;
			d.scrollLeft=this.scrollLeft;
			d.scrollTop=this.scrollTop;
			delete d;d=null;
			delete doc;doc=null;
	  		this.pw.self.dialogBG.style.display="none";
		}else{
			var diag=this.pw.self.dialogList[this.pw.self.dialogList.length-1];
			this.pw.self.dialogBG.style.zIndex=diag.zIndex+diag.Index-1;
			this.pw.self.dialogBG.style.display="";
		}
		}catch(e){
			
		}
//		removeChild(this.bg);
//		this.bg.innerHTML="";
//		this.pw.document.body.removeChild(this.bg);
		
		try{
			if(this.Frame) this.Frame.src="";
		
		
		//debugger;
		this.DialogDiv.style.display="none";
		$(this.DialogDiv).empty();
		
		this.pw.document.body.removeChild(this.DialogDiv);
		
		if(this.isLoading) this.pw.self.loading=null;
		
		for(var key in this){
			this[key]=null;
			eval("delete this."+key+";");
		}
		}catch(e){}
//		if(isIE&&!this.isLoading){
//			var _self=this;
//			var time=setTimeout(function(){
//				clearTimeout(time);
//				delete time;
//				time=null;
//				removeChild(_self.DialogDiv);
//				_self.DialogDiv.innerHTML="";
//				_self.pw.document.body.removeChild(_self.DialogDiv);
//				for(var key in _self){
//					_self[key]=null;
//					eval("delete _self."+key+";");
//				}
//				delete _self;_self=null;
//			},this.URL?3000:1);
//		}else{
//			this.pw.document.body.removeChild(this.DialogDiv);
//			
//			for(var key in this){
//				this[key]=null;
//				eval("delete this."+key+";");
//			}
//			delete this;
//		}
		
		if(this.isIE)CollectGarbage();   
		
//	}catch(e){
//		debugger;
//		if(this.isIE)CollectGarbage(); 
//	};
}

var State=function(state){
	this.state=state;
	this.smpList=this.state.find("ul[name=list]");
	this.tmp=this.state.find("ul li[name=tmp]");
	this.title="已选中的数据：";
	this.content=[];//数据格式为:
					//content:[{
					//			hash:String,		//唯一hash
					// 			title:String,		//标题
					// 			data:Object,		//原数据对象
					// 			callback:func(data)	//回调函数，主要用于删除时返回原始数据对象（data）
					// }]
}

State.prototype.refresh=function(){
	this.content.length>0?this.state.show():this.state.hide();
	this.smpList.html("");
	for(var i=this.content.length-1,j=1;i>=0;i--,j++){
		if(j>2) break;
		//debugger;
		var title=this.content[i].title;
		var item=this.tmp.clone();
		item.css("cursor","pointer");
		item.text(title);
		item.click(function(){alert(this.innerHTML)});
		item.appendTo(this.smpList);
	}
}

State.prototype.push=function(data){
	this.content.push(data);
	this.refresh();
}

State.prototype.remove=function(data){
	for(var i=0,l=this.content.length;i<l;i++){
		var tmp=this.content[i];
		var hash = typeof data == "object" ? data.hash : (""+data)
		if(hash==tmp.hash){
			this.content.splice(i,1);
			this.refresh();
			break;
		}
	}
}

State.prototype.setTitle=function(title){
	this.title=title;
}

State.prototype.clear=function(){
	this.content.splice(0,this.content.length);
	this.refresh();
}

var Drag={
	pw:window,
    obj:null,
    width:0,
    height:0,
    padding:20,
    clientWidth:0,
    clientHeight:0,
    clientTop:0,
    clientLeft:0,
    scrollWidth:0,
    scrollHeight:0,
    scrollTop:0,
    scrollLeft:0,
    title:null,
	init:function(diag,handle, dragBody, e){
		Drag.pw=diag.pw;
		Drag.width = Drag.clientWidth=diag.clientWidth;
	    Drag.height = Drag.clientHeight=diag.clientHeight;
	    Drag.clientTop=diag.clientTop;
	    Drag.clientLeft=diag.clientLeft;
	    Drag.scrollWidth=diag.scrollWidth;
	    Drag.scrollHeight=diag.scrollHeight;
	    Drag.scrollTop=diag.scrollTop;
	    Drag.scrollLeft=diag.scrollLeft;
	    Drag.title=$(handle).find("span[name=title]")[0];
		
		//debugger;
		handle.root = dragBody;
		if (!e) {
			handle.onmousedown=Drag.start;
		}

		if(isNaN(parseInt(handle.root.style.left)))handle.root.style.left="0px";
		if(isNaN(parseInt(handle.root.style.top)))handle.root.style.top="0px";
		handle.root.onDragStart=new Function();
		handle.root.onDragEnd=new Function();
		handle.root.onDrag=new Function();
		if (e) {
			var handle=Drag.obj=handle;
			e=Drag.fixe(e);
			var top=parseInt(handle.root.style.top);
			var left=parseInt(handle.root.style.left);
			handle.root.onDragStart(left,top,e.pageX,e.pageY);
			handle.lastMouseX=e.pageX;
			handle.lastMouseY=e.pageY;
			Drag.pw.document.onmousemove=Drag.drag;
			Drag.pw.document.onmouseup=Drag.end;
		}
	},
	start:function(e){
		var handle=Drag.obj=this;
		e=Drag.fixEvent(e);
		var top=parseInt(handle.root.style.top);
		var left=parseInt(handle.root.style.left);
		handle.root.onDragStart(left,top,e.pageX,e.pageY);
		handle.lastMouseX=e.pageX;
		handle.lastMouseY=e.pageY;
		Drag.pw.document.onmousemove=Drag.drag;
		Drag.pw.document.onmouseup=Drag.end;
		return false;
	},
	drag:function(e){
		//alert(1);
		e=Drag.fixEvent(e);
							
		var handle=Drag.obj;
		var mouseY=e.pageY;
		var mouseX=e.pageX;
		var top=parseInt(handle.root.style.top);
		var left=parseInt(handle.root.style.left);

		if(isIE){Drag.obj.setCapture();}else{e.preventDefault();};//作用是将所有鼠标事件捕获到handle对象，对于firefox，以用preventDefault来取消事件的默认动作：
		
		var sw=Math.max(Drag.clientWidth,Drag.scrollWidth);
		var sh=Math.max(Drag.clientHeight,Drag.scrollHeight);
		
		var currentLeft,currentTop;
		currentLeft=left+mouseX-handle.lastMouseX;
//		currentLeft=currentLeft<0?0:Math.min(currentLeft,sw-parseInt(handle.clientWidth)-26);
		
		
//		if(currentLeft<0)
//			currentLeft=Math.max(currentLeft,Drag.padding-parseInt(handle.clientWidth));
//		else
//			currentLeft=Math.min(currentLeft,Drag.width-Drag.padding);
		
		currentTop=top+(mouseY-handle.lastMouseY);
//		currentTop=currentTop<0?0:Math.min(mouseY,sh-parseInt(handle.clientHeight)-26);
//		if(currentTop<0)
//			currentTop=Math.max(currentTop,Drag.padding-parseInt(handle.clientHeight));
//		else
//			currentTop=Math.min(currentTop,Drag.height-Drag.padding);
		
//		Drag.title.innerText="X:"+currentLeft+",Y:"+currentTop;
		
		handle.root.style.left=currentLeft +"px";
		handle.root.style.top=currentTop+"px";
		handle.lastMouseX=mouseX;
		handle.lastMouseY=mouseY;
		handle.root.onDrag(currentLeft,currentTop,e.pageX,e.pageY);
		return false;
	},
	end:function(){
		if(isIE){Drag.obj.releaseCapture();};//取消所有鼠标事件捕获到handle对象
		Drag.pw.document.onmousemove=null;
		Drag.pw.document.onmouseup=null;
		Drag.obj.root.onDragEnd(parseInt(Drag.obj.root.style.left),parseInt(Drag.obj.root.style.top));
		Drag.obj=null;
	},
	fixEvent:function(e){//格式化事件参数对象
		var sl = Drag.scrollLeft;
		var st = Drag.scrollTop;
		if(typeof e=="undefined")e=Drag.pw.event;
		if(typeof e.layerX=="undefined")e.layerX=e.offsetX;
		if(typeof e.layerY=="undefined")e.layerY=e.offsetY;
		if(typeof e.pageX == "undefined")e.pageX = e.clientX + sl - Drag.clientLeft;
		if(typeof e.pageY == "undefined")e.pageY = e.clientY + st - Drag.clientTop;
		return e;
	}
};

var onKeyDown = function(event){
	if(!event)return;
	if(!dialogParentWindow.self.dialogList||dialogParentWindow.self.dialogList.length<=0) return;
	
	if(event.shiftKey&&event.keyCode==9){//shift键
		//stopEvent(event);
		return false;
	}
	if(event.keyCode==27){//ESC键
		dialogParentWindow.self.dialogList[dialogParentWindow.self.dialogList.length-1].close();
	}
}

var setPosition=function(){
	try{
		if(!dialogParentWindow.self.dialogList||dialogParentWindow.self.dialogList.length<=0) return;
		
		var doc = dialogParentWindow.document;
		var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
		var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;//必须考虑文本框处于页面边缘处，控件显示不全的问题
		var sl = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
		var st = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
		var sw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
		var sh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);
		delete doc;doc=null;
		sw=Math.max(sw,cw);
		sh=Math.max(sh,ch);
		dialogParentWindow.self.dialogBG.style.height= sh + "px";
		dialogParentWindow.self.dialogBG.style.width= sw + "px";
		
		for(var i=0,l=dialogParentWindow.self.dialogList.length;i<l;i++){
			var diag=dialogParentWindow.self.dialogList[i];
			diag.Top = (ch - diag.Height - 30) / 2 + st - 8;//有8像素的透明背景
			diag.Left = (cw - diag.Width - 12) / 2 +sl;
			if(diag.ShowButtonRow){//按钮行高36
				diag.Top -= 18;
			}
			
			diag.DialogDiv.style.top=diag.Top+"px";
			diag.DialogDiv.style.left=diag.Left+"px";
		}
		
	}catch(e){}
}
try{
	if(isIE){
		dialogParentWindow.document.attachEvent("onkeydown",function(event){onKeyDown(event)});
		dialogParentWindow.attachEvent('onresize',function(event){setPosition(event)});
	}else{
		dialogParentWindow.document.addEventListener("keydown",function(event){onKeyDown(event)},false);
		dialogParentWindow.addEventListener('resize',function(event){setPosition(event)},false);
	}
}catch(e){}