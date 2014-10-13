/**
 * @Filename: wordCount.js
 * @Description: The weibo input word count
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20121122
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * <textarea type="text" name="weibo" id="weibo"></textarea>
 *
 * $("#weibo").UED_wordCount();
 *
*/
(function($){
	$.fn.UED_wordCount = function(option){
		var defaults = {
			//max word length
			maxLength: 140,
			prompt1: "\u8FD8\u53EF\u4EE5\u8F93\u5165",
			prompt2: "\u8D85\u51FA",
			symbol: ""
		};
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

		//Interlace change color
		return this.each(function(i,n){
			var _Ele = $(n),
				_value = "", // value
				_surplus = 0, //还能输入字数
                _rel =  strToJson( _Ele.attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            option = $.extend( true, {}, defaults, option, _rel );

            var _tipEle = $(option.symbol);

			_Ele
			.keyup(function() {
				_value = $(this).val();
				_surplus = option.maxLength - _value.length;
				var prompt = (_surplus >= 0? option.prompt1+_surplus : option.prompt2+Math.abs(_surplus))+"\u5B57";
				if(_tipEle.length){
					_tipEle.text(prompt);
				}
			});
		})
	}
    $(function(){
        $("*[data-role='ued-wordCount']").UED_wordCount();
    })
})(jQuery);