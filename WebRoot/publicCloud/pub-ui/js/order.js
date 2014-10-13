//判断浏览器分辨率，如果是1024*768的，那么整体框架的宽度是980px，否则是1200px；
$(function(){
	 //地域点击事件
	  $('.zone-switcher a').live('click',function(){
	  		$('.zone-switcher-select').toggle();
	  });	
	  //dt点击事件
	  $('.y-span2 dl dt').live('click',function(){
	  		var dl=$(this).parent('dl');
	  		if(dl.hasClass('current')){
	  			dl.removeClass('current');
	  			$(this).removeClass('current');
	  		}else{  			
		  		$('.y-span2 dl.current').removeClass('current');
		  		dl.addClass('current');
		  		
		  		$('.y-span2 dt.current').removeClass('current');
		  		$(this).addClass('current');
	  		}
	  });
	  //dd点击事件
	  $('.y-span2 dl dd').live('click',function(){
	  		$('.y-span2 dd.current').removeClass('current');
	  		$(this).addClass('current');
	  });
	  
	  //更改按钮的可用状态
		$('.code').unbind().live('click', function() {
			var num = $('input:checkbox[name="bb"]:checked').size();
			if(num>=1){
				$('.aa').removeClass("gray_button").addClass("blue_button");
			}else{
				$('.aa').removeClass("blue_button").addClass("gray_button");
			}
		});
		//地域选择事件
		$('.zone-switcher-select a').live('click',function(){
			var areaid = $(this).attr('areaid');
			if(areaid=='1'){
				$('#current-zone').html('北京东部<span class="en">(EAST)');
			}else{				
				$('#current-zone').html('北京西部<span class="en">(WEST)');
			}
			$('.label-current').removeClass('label-current').addClass('label-unchecked');
			$(this).children('.aread_icon').removeClass('label-unchecked').addClass('label-current');
			
			//为了隐藏ＵＲＬ参数才这样做的
			$("#areaId").val(areaid);
			$("#areaForm").submit();
			//window.location.assign("console.do?areaId="+areaid);
		});
		//菜单根据url默认选择
		var type =  GetQueryString('type');
		if(type != null && type.length==2){
			$('#type_'+type).addClass('current');
			var parentType = type.substring(0,1);
			$('#type_'+parentType).addClass('current');		
			$('#type_'+type).parent('dl').addClass('current');
		}
		
		//获取参数
		function GetQueryString(name) {
		   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");	
		   var r = window.location.search.substr(1).match(reg);	
		   if (r!=null) return unescape(r[2]); return null;		
		}   
})


