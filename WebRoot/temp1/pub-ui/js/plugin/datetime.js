/**
 * @Filename: datetime.js
 * @Description: datetime
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20121205
 * Copyright (c) 2012-2013 si-tech
 *
 * yy: 四位年份,如:2012
 * y : 两位年份,如:12
 * mm: 两位月份,不足在前补0,如:02
 * m : 一位月份,不补0,如:02
 * dd: 月份中的日,两位表示,不足补0,如:02
 * d : 月份中的日,不足补0,如:2
 * W : 星期的全称,如:星期一
 * w : 星期的略称,如:周一
 * HH: 24小时制小时,不足两位补0,如:08,13
 * H:  24小时制,不补0,如:8,13
 * hh: 12小时制,不足两位补0并且会在加上am或pm进行区分,如01:12:20 am
 * h : 12小时制,不补0并且会在加上am或pm进行区分,如1:12:20 am
 * MM: 分钟表示,不足两位补0,如:08
 * M:  分钟表示,不补0,如:8
 * ss: 秒表示,不足两位补0,如:08
 * s:  秒表示,不补0,如:8
 * SSS:表示毫秒
 *
 * @example
 * $("#span").UED_datetime();
 *
*/
(function($) {
	$.fn.UED_datetime = function(option){
		var defaults = {
			format: "yy-mm-dd W hh:MM:ss",
			morning: "\u4E0A\u5348",
			afterNoon: "\u4E0B\u5348",
			weekNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
			weekNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
		};

        var datetime = {
            _init: function(o){
                setInterval(function(){datetime._setValue(o)}, 1);
            },
            _formatDate: function(date) {
                var dateStr = option.format;
                var showHour = dateStr.indexOf('h') != -1;
                dateStr = dateStr.replace(/yy/, date.getFullYear());
                dateStr = dateStr.replace(/y/, (date.getYear()).toString().substr(2));
                dateStr = dateStr.replace(/mm/, datetime._appendZero(date.getMonth()+1, 2));
                dateStr = dateStr.replace(/m/, date.getMonth()+1);
                dateStr = dateStr.replace(/dd/, datetime._appendZero(date.getDate(), 2));
                dateStr = dateStr.replace(/d/, date.getDate());
                dateStr = dateStr.replace(/W/, option.weekNames[date.getDay()]);
                dateStr = dateStr.replace(/w/, option.weekNamesShort[date.getDay()]);
                // 24小时制
                dateStr = dateStr.replace(/HH/, datetime._appendZero(date.getHours(), 2));
                dateStr = dateStr.replace(/H/, date.getHours());
                // 12小时制
                dateStr = dateStr.replace(/hh/, datetime._appendZero(date.getHours() > 12 ? (date.getHours() - 12) : date.getHours(), 2));
                dateStr = dateStr.replace(/h/, date.getHours() > 12 ? (date.getHours() - 12) : date.getHours());
                dateStr = dateStr.replace(/MM/, datetime._appendZero(date.getMinutes(), 2));
                dateStr = dateStr.replace(/M/, date.getMinutes());
                dateStr = dateStr.replace(/ss/, datetime._appendZero(date.getSeconds(), 2));
                dateStr = dateStr.replace(/s/, date.getSeconds());
                dateStr = dateStr.replace(/SSS/, datetime._appendZero(date.getMilliseconds(), 3));
                dateStr = dateStr.replace(/SS/, datetime._appendZero(date.getMilliseconds(), 2));
                dateStr = dateStr.replace(/S/, date.getMilliseconds());
                if (showHour) {
                    if (date.getHours() - 12 >= 0) {
                        dateStr = dateStr + option.afterNoon;
                    } else {
                        dateStr = dateStr + option.morning;
                    }
                }
                return dateStr;
            },
            _appendZero: function(value, length) {
                if (value) {
                    value = (value).toString();
                    if (value.length < length) {
                        for(var i = 0; i < length - value.length; i++) {
                            value = "0" + value;
                        }
                    }
                }
                return value;
            },
            _setValue: function(o) {
                var date = new Date();
                date = datetime._formatDate(date)
                if (o.nodeName == "INPUT" || o.nodeName == "TEXTAREA") {
                    $(o).val(date);
                } else {
                    $(o).text(date);
                }
            }
        }

        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

		return this.each(function(i, n){
			var _rel = strToJson( $(n).attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            option = $.extend(true, {}, defaults, option, _rel);

			datetime._init(n);
		})
	}

    // auto init plugin
    $(function(){
        $("*[data-role='ued-datetime']").UED_datetime();
    })
})(jQuery);