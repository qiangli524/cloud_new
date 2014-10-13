/**
 * @Filename: ui.js
 * @Description: adWindow
 * @Version: 1.0.0
 * @Author: wangchen.si-tech
 * @UpdateBy: wangchen 20121105
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 *$(".ued-adWindow").UED_adWindow({width:256,height:185});
 *
*/

(function($){
	$.fn.UED_adWindow = function(option){
		var defaults = {
			width:256,
			height:187
		};
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        };

		//Interlace change color
		return this.each(function(i,n){
			var elem = $(n),
                    _rel =  strToJson( elem.attr("data-rel"));

            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);
			
			setPos();
			$(window).resize(function(){
				setPos();
			})
			 $(window).scroll(function() {
				setPos();
			})
			function setPos(){
				var TopY=0,//初始化元素距父元素的距离
					   _initY = $(window).scrollTop() + $(window).height();
				//elem.css({width:option.width+"px", height:option.height+"px", top:_initY-option.height+"px"});
				elem.css({width:option.width+"px", height:option.height+"px"});
				elem.slideDown();
	
				elem.find(".adWindow-close").click(function() {
					 if(TopY==0){
						 elem.slideUp();
					 }else{
						  $(".ued-adWindow").animate({top: TopY+height}, "slow", function() { $(".ued-adWindow").hide(); });
					}
				 });
				 
				
				 if($.browser.msie && $.browser.version < 7.0){
					 $("html").css("_background","url(about:blank)");
					 elem.css("top", $(window).scrollTop() + $(window).height() - $(".ued-adWindow").outerHeight());
					// elem.css("bottom", 0);
				 }
			
			 
			}
		})
	}
    // auto init
    $(function(){
        $("*[data-role='ued-adWindow']").UED_adWindow({width:256,height:185});
    })
})(jQuery);