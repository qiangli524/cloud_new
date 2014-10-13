/**
 * @Filename: ui.js
 * @Description: dropdown
 * @Version: 1.0.0
 * @Author: wangchen.si-tech
 * @UpdateBy: wanggq 20130125
 * Copyright (c) 2013-2014 si-tech
 *
 * @example
 *$("a").UED_dropdown();
 *
*/

(function($){
    $.fn.UED_dropdown = function(){
        var defaults = {
            activeClass: "on"
        }
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

        return this.each(function(i,n){
            var  _this = $(n),
                    _rel = strToJson( $(n).attr("data-rel") );
            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);
			
            _this.children("li").each(function(){
				$(this).children('a').click(function(e){
					if($(this).next(".ued-dropdown-menu").css('display') == 'block'){
						$(this).parent("li").removeClass(option.activeClass);
						$(this).next(".ued-dropdown-menu").hide();
					}else{
						$(this).parent("li").addClass(option.activeClass);
						$(this).next(".ued-dropdown-menu").show();
					}
					var e=window.event || e;
					if(e.stopPropagation){
						e.stopPropagation();
					}else{
						e.cancelBubble = true;
					}
				})
			})
			$(document).click(function(e){
				$(".ued-dropdown-menu").hide();
			})
				
		})
       
    }
    $(function(){
        $("*[data-role='dropdown']").UED_dropdown();
    })
})(jQuery);