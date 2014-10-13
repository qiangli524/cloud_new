/**
 * @Filename: highLight.js
 * @Description: JavaScript text higlighting jQuery plugin
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20130117
 * Copyright (c) 2013-2014 si-tech
 *
*/
(function($){
    $.fn.UED_highLight = function (option) {
        var defaults = {
            lightClass:"ued-highlight"
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        };

        function _innerHighlight(node, option) {
            var skip = 0;
            if (node.nodeType == 3) {
                var _text = option.text.toUpperCase(),
                       pos = node.data.toUpperCase().indexOf(_text);
                if (pos >= 0) {
                    var spannode = document.createElement('span');
                    spannode.className = option.lightClass;
                    spannode.style.backgroundColor = option.bgColor;
                    var middlebit = node.splitText(pos);
                    var endbit = middlebit.splitText(_text.length);
                    var middleclone = middlebit.cloneNode(true);
                    spannode.appendChild(middleclone);
                    middlebit.parentNode.replaceChild(spannode, middlebit);
                    skip = 1;
                }
            }else if (node.nodeType == 1 && node.childNodes && !/(script|style)/i.test(node.tagName)) {
                for (var i = 0; i < node.childNodes.length; ++i) {
                    i += _innerHighlight(node.childNodes[i], option);
                }
            }
            return skip;
        }

        return this.length? this.each(function (i,n) {
            var  _o = $(n),
                    _rel =  strToJson( $(n).attr("data-rel") );
            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);

            _innerHighlight(this, option);
        }) : this;
    };

    $.fn.removeHighlight = function () {
        return this.find("span.highlight").each(function () {
            this.parentNode.firstChild.nodeName;
            with (this.parentNode) {
                replaceChild(this.firstChild, this);
                normalize();
            }
        }).end();
    };

    $(function(){
        $("*[data-role='ued-highLight']").UED_highLight();
    })
})(jQuery);

