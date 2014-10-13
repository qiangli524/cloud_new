/**
 * @Filename: scrollText.js
 * @Description: scrollText
 * @Version: 1.0.0
 * @Author: wangchen.si-tech
 * @UpdateBy: wanggq 20121109
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * $(".ued-scrollText").UED_scrollText({per:2});
 * 
 *
*/
(function($){
	$.fn.UED_scrollText = function(option){
		var defaults = {
            anim: "seesaw",
			speed: 600,
			interval: 2000,
			per: 1
		};
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

        var uedAnimate = {
            init:function(o){
                var _animation = $(o).data("option").anim;
                switch(_animation){
                    case "seesaw":
                        uedAnimate.seesaw(o);
                        break;
                    case "fadeToggle":
                        uedAnimate.fadeToggle(o);
                        break;
                    default :
                        break;
                }
            },

            // up and down
            seesaw: function(o){
                var  _o = $(o).children("ul"),
                        _oChild = _o.find("li"),
                        _oLiH = _oChild.eq(0).outerHeight(true),
                        _oChildL = _oChild.length,
                        _option = $(o).data("option");

                $(o).css({height: _oLiH * _option.per+"px", marginTop:"0px"});

                var timer = setInterval(function(){ _show(); }, _option.interval);
                _oChild.hover(function(){
                    _o.stop();
                    clearInterval(timer);
                },function(){
                    timer = setInterval(function(){ _show(); }, _option.interval);
                })

                function _show(){
                    _o.animate({
                        marginTop: -_oLiH * _option.per+"px"
                    }, _option.speed, function(){
                        _o.css({marginTop: "0px"}).find("li:lt("+_option.per+")").appendTo(_o);
                    });
                }
            },
            // fadeIn fadeOut
            fadeToggle:function(o){
                var  _o = $(o).children("ul"),
                    _oChild = _o.find("li"),
                    _oLiH = _oChild.eq(0).outerHeight(true),
                    _oChildL = _oChild.length,
                    _option = $(o).data("option");

                $(o).css({height: _oLiH * _option.per+"px"});
                _oChild.hide();

                var timer = setInterval(function(){ _show(); }, _option.interval);
                _oChild.hover(function(){
                    _o.stop();
                    clearInterval(timer);
                },function(){
                    timer = setInterval(function(){ _show(); }, _option.interval);
                })

                function _show(){
                    _o.find("li:lt("+_option.per+")").appendTo(_o).fadeIn(_option.speed).siblings("li").fadeOut();
                }
            }
        }

		//Interlace change color
		return this.each(function(i,n){
			var  _o = $(n).children("ul"),
                    _rel =  strToJson( $(n).attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            $(n).data("option", $.extend( true, {}, defaults, option , _rel));

            uedAnimate.init(n);
        })
	}

    $(function(){
        $("*[data-role='ued-scrollText']").UED_scrollText();
    })
})(jQuery);