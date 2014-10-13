/**
 * Filename: reset.css
 * Description: init default browser's style
 * Version: 1.0.0
 * Author: VAIO.si-tech
 * UpdateBy: kevin 13-1-9 下午1:20
 */
(function($){
    $.fn.UED_advertise = function(option){
        var defaults = {
            // default shape
            shape: "banner"
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        };
        // banner plugin
        function advertise1(o){
            var _o = o,
                   n = 0,
                   _imgs = _o.find(".scroll-img>div"),
                   _nums = _o.find(".scroll-num>li"),
                   count = _imgs.length,
                   _option = _o.data("option");
            _nums.eq(0).addClass("on");
            _imgs.eq(0).siblings().hide();
            _nums.click(function(){
                var i = $(this).index();
                n = i;
                _imgs.eq(i).fadeIn().siblings().hide();
                $(this).addClass("on").siblings().removeClass("on");
            });
            var t = setInterval(function(){
                _nums.eq(n).trigger('click');
                ++n >= count? n=0 : n=n;
            }, _option.speed || 3000);
            _o.hover(function(){clearInterval(t)}, function(){t = setInterval(function(){
                _nums.eq(n++).trigger('click');
                ++n >= count? n=0 : n=n;
            }, _option.speed || 3000);})
			_o.append('<i class="ued-icon ued-icon-cross btnClose"></i>');
			_o.find('i.btnClose').bind("click",function(){
				_o.fadeOut();
			})
        }
        // fixed advertise
        function advertise2(o){
            var _o = o,
                   _option = _o.data("option");

            if(_option.width) _o.css("width",_option.width);
            if(_option.height) _o.css("height",_option.height);
            // IE6 fixed bug
            if($.browser.msie && $.browser.version<7.0) {
                $('html').attr("style","background-image:url(about:blank);");
                var _top = _option.top!=undefined? "top:expression(documentElement.scrollTop+"+parseInt(_option.top.replace("px"))+");" : "",
                       _right = _option.right!=undefined? "right:"+_option.right+";" : "",
                       _bottom = _option.bottom!=undefined? "bottom:expression(documentElement.scrollTop+documentElement.clientHeight-this.offsetWidth-"+parseInt(_option.bottom.replace("px"))+");" : "",
                       _left = _option.left!=undefined? "left:"+_option.left+");" : "";
                _o.attr("style",_top+ _right+_bottom+ _left);
            }else{  // !IE6
                if(_option.top!=undefined) _o.css({top:_option.top});
                if(_option.right!=undefined) _o.css({right:_option.right});
                if(_option.bottom!=undefined) _o.css({bottom:_option.bottom});
                if(_option.left!=undefined) _o.css({left:_option.left});
            }
			_o.append('<i class="ued-icon ued-icon-cross btnClose" style="position:absolute; right:0;top:0"></i>');
			_o.find('i.btnClose').bind("click",function(){
				_o.fadeOut();
			})
        }

        //Interlace change color
       return this.each(function(i, n){
            var _rel =  strToJson( $(n).attr("data-rel") );
            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);

            $(n).data("option",option);
            switch(option.shape){
                case "banner":
                    advertise1($(n));
                    break;
                case "advertiseFixed":
                    advertise2($(n));
                    break;
                default:
                    break;
            }
        });
    }

    $(function(){
        $("*[data-role='ued-advertise']").UED_advertise();
    })
})(jQuery);


