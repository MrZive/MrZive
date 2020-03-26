

(function(){
	var time=new Date().getTime();
	$.extend({
		/*
		 * 20161231
		 * config={
		 * 	item:$(obj),								//按钮对象
		 *  maxSize:10240,								//限制单个文件大小，单位byte(未实现！)
		 * 	accept:'.txt,.jpg',							//限制文件后缀
		 * 	getConfig:function(info){ return {};}		//返回当前上传文件附加参数的方法
		 * }
		 * 
		 * getConfig回调方法会回传一些被上传文件的信息
		 * 现在有的参数如下：
		 * 该对象包含了上传文件的信息
		 * stateObj={
		 *		state:0								//文件上传状态，0为正在排队，1为正在上传，2上传成功完毕，-1上传有错误
		 *		,listener:{
		 *			start:function(info){}			//开始上传会被调用
		 *			,complete:function(info){}		//上传完毕后会被调用
		 *			,progress:function(info){}		//上传时进度变化会被调用
		 *		}
		 *	};
		 */
		zz_html5_upload:function(config){
			
			var item=config.item;
			var input=$('<input accept="'+(config.accept?config.accept:'')+'" '+(config.multiple?'multiple':'')+' class="zzuploader" style="opacity:0;filter:progid:DXImageTransform.Microsoft.Alpha(Opacity=0)" zz_item_name="file" type="file" name="file" id="file"/>');
			input.change(function(){
				input=input.clone(true);
				item.append(input);
				this.___zz_config=config;
				addFileToUploadList(this);
			});
			
			item.css({'position':'relative',overflow:'hidden'});
			var offset=config.item.offset();
			input.css({
				'position':'absolute'
				,'top':0
				,'left':0
				,'width':item.width()*2
				,'height':item.height()*2
			});
			item.append(input);
		}
	});
	
	var isUploading=false
		,CuroXHR;
	var uploadContainer=null
		,upload_panel_con=null
		,upload_speed=null;
		
	var uploadSupported=(function(){
		var flag=false;
		try{
			flag=!!(XMLHttpRequest&&FormData);
		}catch(e){}
		return flag;
	})();
	
	function addFileToUploadList(dom){
		initUploadContainer();
		if(!uploadSupported){
			return;
		}
		$.each(dom.files,function(){
			var item=$('<div zz_item_name="list_item_temp" class="the_up'+time+' waitting'+time+'">'+
		   			'<div  file_upload_item="clear"  class="file-operator'+time+'">'+
		   				'<div style="margin:auto;width:10px;font-size:15px;">×</div>'+
		   			'</div>'+
		   			'<div class="data-wrap'+time+'">'+
			   			'<div file_upload_item="file_name" class="file-name'+time+'"></div>'+
			   			'<div file_upload_item="progress" class="progress'+time+'"></div>'+
			   			'<div file_upload_item="progress2" class="progress2'+time+'"></div>'+
		   			'</div>'+
		   		'</div>');
			var container={};
			item.find('[file_upload_item]').each(function(){
				var j=$(this);
				container[j.attr('file_upload_item')]=j;
			});
			container.file_name.text(this.name);
			container.clear.click(function(){
				if(confirm('确定清除文件？(仅从上传列表清除该项，不会删除已经上传成功文件文件)')){
					if(item.hasClass('uploading')){
						CuroXHR.abort();
						isUploading=false;
						uploadPanel2();
					}
					item.fadeOut(function(){
						item.remove();
					});
				}
			});
			item[0].__thezfile=this;//.append($(dom).hide());
			//获取上传参数，同时回传上传信息
			var stateObj={
				state:0
				,listener:{
					start:function(info){}			//开始上传
					,complete:function(info){}		//上传完毕
					,progress:function(info){}		//上传进度
				}
			};
			item[0].___the_config=dom.___zz_config.getConfig({file:this,stateObj:stateObj});
			item[0].___the_upload_state_obj=stateObj;
			upload_panel_con.append(item.removeAttr('zz_item_name'));
		});
		uploadPanel2();
	}
	
	function initUploadContainer(){
		
		/*上传窗口css*/
		var css='.uploadInput'+time+'{'+
			 'border: 1p  solid #ccc;'+
			    'border-radius: 10px;'+
			    'font-size: 14pt;'+
			    'padding: 5px 10px;'+
		'}'+
		'.uploadPanel'+time+'{'+
			'border: 1px solid #ccc;'+
		    'border-radius: 0px;'+
		    'padding: 0px 0px;'+
		    'width:500px;'+
		    'background:#fff;'+
		    
		    'position:fixed;'+
		    'right:0px;'+
		   	'bottom:0px;'+
		    'z-index:1;'+
		'}'+
		'.uploadPanel-title'+time+'{'+
			'padding: 8px 10px;'+
			'cursor:pointer;'+
			'border-bottom:1px solid #eee;'+
		'}'+

		'.uploadPanel-con'+time+'{overflow:auto; height:190px;}'+
		'.the_up'+time+'{'+
			'height:30px;'+
			'line-height:30px;'+
			'position:relative;'+
			'border:1px solid #E6E6E6;'+
			'margin-top:-1px;'+
			'overflow:hidden;'+
			'border-left:3px solid #eee;'+
		'}'+
		'.the_up'+time+'.uploading'+time+'{border-left:3px solid #AAAAAA;}'+
		'.the_up'+time+'.fail'+time+'{border-left:3px solid red;}'+
		'.the_up'+time+'.done'+time+'{border-left:3px solid green;}'+

		'.progress'+time+'{'+
			'height:30px;'+
			'background:#eee;'+
			'position:absolute;'+
			'top:0;'+
			'left:0;'+
		'}'+
		'.progress2'+time+'{'+
			'width:110px;'+
			'height:30px;'+
			'background:none;'+
			'position:absolute;'+
			'color:gray;'+
			'padding:0 5px;'+
			'right:0;'+
			'top:0;'+
		'}'+
		'.file-name'+time+'{'+
			'left: 0;'+
		    'margin-right: 120px;'+
		    'overflow: hidden;'+
		    'padding-left: 5px;'+
		    'position: relative;'+
		    'top: 0;'+
		    'z-index: 1;'+
		'}'+
		'.file-operator'+time+'{'+
			'float:right;'+
			'width:30px;'+
			'background:#D8D8D8;'+
			'cursor:pointer;'+
		'}'+
		'.file-operator'+time+':hover{'+
			'background:gray;'+
			'color:white;'+
		'}'+
		'.data-wrap'+time+'{'+
			'margin-right:30px;position:relative;'+
		'}';
		if(!uploadContainer){
			function getTopZIndex(){
				var z_index=1000;
				$('body').find('>div').each(function(){
					var _s=this;
					z_index=_s.style.zIndex>z_index?_s.style.zIndex:z_index;
				});
				return z_index;
			}
			uploadContainer=$('<div style="z-index:'+getTopZIndex()+';" id="zz_uploader_html5_only_1511" class="uploadPanel'+time+'">' +
				'<style>'+css+'</style>'+
				'<div class="uploadPanel-title'+time+'"><a href="javascript:void(0);" class="btn" style="float:right;">清除所有</a>文件上传↑&nbsp;&nbsp;<span class="xdc-upload-speed'+time+'"></span></div>' +
				'<div style="display:none;" class="uploadPanel-con'+time+'"></div>' +
			'</div>');
			$('body').append(uploadContainer);
			uploadContainer.find('.uploadPanel-title'+time).click(function(){
				upload_panel_con.slideToggle();
			});
			uploadContainer.find('.btn').click(function(){
				if(confirm('是否清除文件列表？（此操作会终止正在上传的文件，不会删除已上传的文件）')){
					if(isUploading){
						CuroXHR.abort();
					}
					upload_panel_con.hide().find('>div').remove();
					uploadContainer.hide();
					isUploading=false;
				}
				return false;
			});
			upload_panel_con=uploadContainer.find('.uploadPanel-con'+time);
			upload_speed=uploadContainer.find('.xdc-upload-speed'+time);
			upload_panel_con.slideDown();
			if(!uploadSupported){
				upload_panel_con.html('您的浏览器不支持HTML5，无法进行文件上传!' +
						'<br/>如果您使用的是国产浏览器，请切换到极速模式' +
						'<br/>推荐浏览器：<br/>最新版本的&nbsp;' +
						'<a style="font-weight:bold;" target="_blank" href="http://www.firefox.com.cn/">火狐浏览器</a>、' +
						'<a style="font-weight:bold;" target="_blank" href="https://www.baidu.com/s?wd=%e8%b0%b7%e6%ad%8c%e6%b5%8f%e8%a7%88%e5%99%a8%0d%0a">谷歌浏览器</a>。');
			}
		}else{
			uploadContainer.show();
			if(!upload_panel_con.isVisible){
				upload_panel_con.slideDown();
			}
		}
	}
	
	function uploadPanel2(){
		if(isUploading){
			return;
		}
		var fileDom=uploadContainer.find('.uploadPanel-con'+time).find('>div.waitting'+time+':first');
		if(fileDom.length==0){
			return;
		}
		var dom={};
		fileDom.find('[file_upload_item]').each(function(){
			var j=$(this);
			dom[j.attr('file_upload_item')]=j;
		});
		var zfile=fileDom[0].__thezfile;//.find('input:first');
//		if(fdom.length>0){
			var fileConfig=fileDom[0].___the_config;
			var stateObj=fileDom[0].___the_upload_state_obj;
			var fParams=fileConfig.params||{};
			var uploadURL=fileConfig.uploadURL;
			
			fileDom.removeClass('waitting'+time);
//			var file=fdom[0].files[0];
			var vFD = new FormData();
			vFD.append("saveFileName", zfile.name);
			vFD.append('__file_length',zfile.size);
			for(var key in fParams){
				vFD.append(key,fParams[key]);
			}
			if(zfile.type.indexOf('"')==0){
				//在firefox49.0.2测试时发现文件为pdf时文件的contentType="application/octet-stream,前面多了个双引号，导致后台无法正确解析类型
				//这里重新将file转成Blob，并去掉contentType里的双引号
				vFD.append('file',new Blob([zfile.slice()],{type:zfile.type.replace(/"/ig,'')}),zfile.name);
			}else{
				vFD.append('file',zfile);
			}	
			
			var oXHR = new XMLHttpRequest();
			CuroXHR=oXHR;
			var to=setInterval(function(){
				upload_speed.html(dataSizeCaculate((loaded-prevLoaded)/2)+'/s');
				prevLoaded=loaded;
			},2000);
			var prevLoaded=0,loaded=0;
			var progress=dom.progress;
			var progress2=dom.progress2;
			oXHR.upload.addEventListener('progress',function(e){
			    if (e.lengthComputable) {
			    	loaded=e.loaded;
			    	var percent=Math.round(e.loaded*100/e.total);
			    	progress.css({width:percent+'%'});
			    	progress2.attr('title',e.loaded+'/'+e.total+','+percent+'%');
			    	progress2.html(dataSizeCaculate(e.loaded)+'/'+dataSizeCaculate(e.total));
					if(stateObj.listener.progress){
						stateObj.listener.progress({percent:percent,loaded:e.loaded,total:e.total});
					}
			    } else {
			       progress.html('unable to compute');
			    }
			}, false);
			oXHR.addEventListener('load', function(){
				fileDom.removeClass('uploading'+time);
				fileDom.addClass('done'+time);
				isUploading=false;
				fileDom.attr('title','上传完毕');
				clearInterval(to);
				upload_speed.html('');
				uploadPanel2();
				
				stateObj.state=2;
				if(stateObj.listener.complete){
					stateObj.listener.complete();
				}
			}, false);
			oXHR.addEventListener('error', function(){
				fileDom.removeClass('uploading'+time);
				fileDom.addClass('fail');
				isUploading=false;
				fileDom.attr('title','上传失败');
				clearInterval(to);
				upload_speed.html('');
				uploadPanel2();
				var stateObj=fileDom[0].___the_upload_state_obj.uploadComplete;
				stateObj.state=-1;
				if(stateObj.listener.complete){
					stateObj.listener.complete();
				}
			}, false);
			oXHR.addEventListener('abort',function(){
				upload_speed.html('');
				clearInterval(to);
			}, false);
			oXHR.open('POST',uploadURL);
			oXHR.send(vFD);
			fileDom.addClass('uploading'+time);
			isUploading=true;
			
			//开始上传，回调
			stateObj.state=1;
			if(stateObj.listener.start){
				stateObj.listener.start();
			}
//		}
	}
	
	
	function dataSizeCaculate(byteSize){
		var size=byteSize;
		var unit=["b","k","m","g","t","p","e"];
		var counter=0;
		while(size>=1024){
			size=size/1024;
			++counter;
		}
		return size.toFixed(1).replace(/\.?0*$/gi,"")+unit[counter]||"??";
	}
})();
