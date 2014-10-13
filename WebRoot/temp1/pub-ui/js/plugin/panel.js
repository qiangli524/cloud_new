/**
 * @Filename: panel.js
 * @Description: 工具面板
 * @Version: 1.0.0
 * @Author: wangyq.si-tech
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * <div class="ued-panel">
 * <h3>工具栏面板<span><a class="tool minimize"></a><a class="tool maximize"></a><a class="tool open"></a><a class="tool close"></a></span></h3>
 * <div>
 * <p>panel</p>
 * </div>
 * </div>
 *
*/
(function($){
	$.fn.UED_panel = function(option){
		var defaults = {
			open:true,
			//双击标题折叠展开
            headerCollapse: false,
            //标题和工具按钮
			header: ">h3",
			content: ".ued-content",
            min:".min",
            max:".max",
            restore:".restore",
            collapse:".collapse",
            expand:".expand",
            close:".close"
		};
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

        function _init(o){
            var _o = o;
            var option = _o.data("option");
            var header = _o.find(option.header);
            var content = _o.find(option.content);

            if( option.open != true ){
                _o.find(option.content).hide();
            }

            header.delegate(option.max,"click",function(){
                var w = $(window).width()-4,h = $(window).height()-header.height(),top = $(window).scrollTop();
                _o.css({position:"absolute",top:top,left:1,width:w,zIndex:9999});
                content.height(h);
                $(this).removeClass(option.max.substr(1)).addClass(option.restore.substr(1));
            });
            header.delegate(option.restore,"click",function(){
                _o.removeAttr("style");
                content.removeAttr("style");
                $(this).removeClass(option.restore.substr(1)).addClass(option.max.substr(1));
            });
            header.delegate(option.collapse,"click",function(){
                content.slideUp("fast");
                $(this).removeClass(option.collapse.substr(1)).addClass(option.expand.substr(1));
            });
            header.delegate(option.expand,"click",function(){
                content.slideDown("fast");
                $(this).removeClass(option.expand.substr(1)).addClass(option.collapse.substr(1));
            });
            header.delegate(option.close,"click",function(){
                _o.remove();
            });

        }

		return this.each(function(i, n){
			var  _box = $(n),
                    _rel =  strToJson( _box.attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);

            _box.data("option",option);

			_init(_box);
		})
	}
    $(function(){
        $("*[data-role='ued-panel']").UED_panel();
    })
})(jQuery);
