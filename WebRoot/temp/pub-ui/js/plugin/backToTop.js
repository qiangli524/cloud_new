/**
 * @Filename: backToTop.js
 * @Description: backToTop
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20121124
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * 引入此文件即可
 * 默认：当屏幕滚动超过一屏的时候出现“返回头部”
 *       文字标示
 *       如果是图片按钮,请传入容器
 *
*/
(function($){
	$.fn.UED_backToTop = function(option){
		var defaults = {
			speed: 600
		};
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        };

        var $backToTopEle = $(this),
               _rel =  strToJson( $backToTopEle.attr("data-rel")),
		       /* 合并默认参数和用户自定义参数 */
               options = $.extend(true,{},defaults,option,_rel);

        $backToTopEle.appendTo($("body"))
					.attr("title", options.title)
					.click(function() {
						$("html, body").animate({ scrollTop: 0 }, options.speed);
					});
		$backToTopFun = function() {
			var st = $(document).scrollTop(), 
				winH = $(window).height();
			(st > winH)? $backToTopEle.fadeIn(): $backToTopEle.fadeOut();    
			//IE6下的定位
			if (!window.XMLHttpRequest) {
				$backToTopEle.css("top", st + winH - $backToTopEle.height()-100 );    
			}
		};
		$(window).bind("scroll", $backToTopFun);
	}

	$(function(){
        $("*[data-role='ued-backtoTop']").UED_backToTop();
	});
})(jQuery);