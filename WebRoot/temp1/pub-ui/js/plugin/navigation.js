/**
 * @Filename: navigation.js
 * @Description: navigation event
 * @Version: 1.0.0
 * @Author: dujj.si-tech
 * @UpdateBy: wanggq 20121106
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * $("*[data-role='ued-nav']").UED_nav();
 *
*/
(function($){
	$.fn.UED_nav = function(option){
		var defaults = {
			//child menu element
			childMenu: "ul",
			//current navigation's class
			activeClass: "on"
		};
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

		/* 合并默认参数和用户自定义参数 */
		var option = $.extend(true,{},defaults,option);
		//Interlace change color
		return this.each(function(i,n){
			var  _this = $(n),
                    _rel = strToJson( _this.attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);

			_this.find(">ul>li").hover(function(){
				$(this).addClass(option.activeClass);
			},function(){
				$(this).removeClass(option.activeClass);
			});
		})
	}

    $(function(){
        $("*[data-role='ued-nav']").UED_nav();
    })
})(jQuery);