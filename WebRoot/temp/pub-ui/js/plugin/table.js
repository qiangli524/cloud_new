/**
 * @Filename: table.js
 * @Description: table interlaced color change
 * @Version: 1.0.0
 * @Author: dujj.si-tech
 * @UpdateBy: wanggq 20121105
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * $("table").UED_tableInterlace();
 *
*/
(function($){
	$.fn.UED_tableInterlace = function(option){
		var defaults = {
			//tr odd class
			oddClass: "ued-tr-odd",
			//tr even class
			evenClass: "ued-tr-even",
			//tr hover class
			trHover: "ued-tr-hover",
			//tr click event
			trClick: function(){}
		};
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        };

		//Interlace change color
		return this.each(function(i,n){
			var _tbody = $(n).children("tbody").length>0?$(n).children("tbody"):$(n),
				_tr = _tbody.children("tr"),
                _rel =  strToJson( $(n).attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            option = $.extend(true,{},defaults,option , _rel);

			for(var i=0; i<_tr.length; i++){
                var _class;
				if(i%2==0)_class = option.evenClass;
				else _class = option.oddClass;
				_tr.eq(i).addClass(_class);
			}
			//tr hover event
			_tr
			.hover(function(){
				$(this).addClass(option.trHover);
			},function(){
				$(this).removeClass(option.trHover);
			})
			.click(option.trClick);
		})
	}
    $(function(){
        $("*[data-role='ued-tableInterlace']").UED_tableInterlace();
    })
})(jQuery);