/**
 * @Filename: placeholder.js
 * @Description: placeholder event
 * @Version: 1.0.0
 * @Author: hanlx.si-tech
 * @UpdateBy:
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * <input type="text" class="placeholder" data-rel="" />
 * $("input.placeholder").UED_placeholder();
 *
*/
(function($){
	$.fn.UED_placeholder = function(option){
		var defaults = {
			value:"&#x8BF7;&#x8F93;&#x5165;...",//设置文本框的默认文本
			color:"#999999", //设置默认文字颜色
			fontSize:"12px", //设置默认文字大小
			fontWeight:"normal", //设置默认文字粗细
			focusBgColor:"#FCF405" //文本框获取焦点时背景颜色
		};
		function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }
		function _init(o){
			var _o = o;
            var option = _o.data("option");
			if(option.value){	
				var val = option.value;
				_o.val(val).css({"color":option.color,"font-size":option.fontSize,"font-weight":option.fontWeight});
				_o.focus(function(){
					if(_o.val()==val){
						_o.val('').css({"color":"","font-size":"","font-weight":""});	
					}
					_o.css({"background-color":option.focusBgColor});
				}).blur(function(){
					if(!_o.val()){
						_o.val(val).css({"background-color":"","color":option.color,"font-size":option.fontSize,"font-weight":option.fontWeight});
					}else{
						_o.css({"background-color":"","color":"","font-size":"","font-weight":""});	
					}
				})
			}
		}

		return this.each(function(i,n){
			var _this = $(n),
			      _rel =  strToJson(_this.attr("data-rel"));
			var options = $.extend(true, {}, defaults, option, _rel);
			_this.data("option",options);
			_init(_this);
		})
	}
	
	$(function(){
		$("*[data-role='ued-placeholder']").UED_placeholder();		   
	});
})(jQuery)