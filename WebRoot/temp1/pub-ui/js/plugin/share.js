/**
 * @Filename: share.js
 * @Description: Social  share
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20130122
 * Copyright (c) 2013-2014 si-tech
 *
*/
(function($){
    $.fn.UED_share = function(option){
        var defaults = {
            libPath:"http://v2.jiathis.com/code/jia.js",
            libHost:"http://www.jiathis.com/",
            uid:1,
            webid:"",
            url:"",
            title:""
        };
        function strToJson(str){
            str = str? str:"";
             return  eval( "({" + str + "})" );
        }

        //Interlace change color
        return this.each(function(i, n){
            var  _this = $(n)
                    _rel =  strToJson( _this.attr("data-rel") );
            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true, {}, defaults, option || {}, _rel);
            var locationPath = "";
            if(option.webid!=""){
                // http://www.jiathis.com/send/?webid=kaixin001&url=http://www.jiathis.com&title=非常棒的分享工具
                locationPath += option.libHost+"send/?webid="+option.webid+"&url="+option.url+"&title="+option.title;
                _this.bind("click",function(e){
                    window.open(locationPath);
                    e.preventDefault();
                })
            }else{
                // Single interface. eg:http://v1.jiathis.com/code/jia.js?uid=您的UID
                locationPath  += option.libPath+"?uid="+option.uid;
                var scripts = document.scripts, loadedShare;
                scripts = $.map(scripts,function(o){
                    return o.src;
                })

                for(var i= 0,l=scripts.length;i<l;i++){
                    if(scripts[i].indexOf(option.libPath)>=0){
                        loadedShare=true;
                        break;
                    }
                }
                if(!loadedShare){
                    var _script = document.createElement("script");
                    _script.src = locationPath;
                    _script.id = "uedShare";
                    document.body.appendChild(_script);
                }
            }

        })
    }

    // auto init plugin
    $(function(){
        $("*[data-role='ued-share']").UED_share();
    })
})(jQuery);