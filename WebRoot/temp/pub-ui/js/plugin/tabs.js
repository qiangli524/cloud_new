/**
 * @Filename: tabs.js
 * @Description: tab
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20121206
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 *
 *
*/
(function($){
    $.fn.UED_tabs = function(option){
        var defaults = {
            activeClass: "active",
            activeIndex: 0,
            trigger: "click"
        };

        function _setActive(o){
            var  _o = $(o),
                    _targetConId = _o.children("a").attr("href");
			
           
			var oDiv=_o.parent('ul').parent('div.title-bg').next('div.tab-content');
			/*
			*  href="javascript:;" || href="javascript:void(0);" ||  href="#"
			*  href="#Id"
			*              {   has 'target'
			*  href= url   {
			*              {   no  'target'
			*/
			if(_targetConId==="#" || _targetConId==="javascript:;" || _targetConId==="javascript:void(0);"){
				 _o.addClass(option.activeClass).siblings("li").removeClass(option.activeClass);
				 var i=_o.index();
				 oDiv.find('div.tab-pane').eq(i).addClass(option.activeClass).siblings(".tab-pane").removeClass(option.activeClass);
			}else{
				if(oDiv.find($(_targetConId)).length==1 ){
					 _o.addClass(option.activeClass).siblings("li").removeClass(option.activeClass);
					$(_targetConId).addClass(option.activeClass).siblings(".tab-pane").removeClass(option.activeClass);
				}else if(oDiv.find($(_targetConId)).length==0){
							if(_o.children("a").attr("target")){
							}else{
								 _o.addClass(option.activeClass).siblings("li").removeClass(option.activeClass);
								 var i=_o.index();
								 oDiv.find('div.tab-pane').eq(i).addClass(option.activeClass).siblings(".tab-pane").removeClass(option.activeClass);
								 //href='#无效的字符串'
								 var reg=/^#\w{1,}$/g;
								 if(reg.test(_targetConId)){
								 }
								 else{
									$.ajax({
											url:_targetConId,
											type:'GET',
											dataType:"html",
											success:function(html){
												oDiv.find('div.tab-pane').eq(i).empty().append(html);
											},
											beforeSend: function(){
												oDiv.find('div.tab-pane').eq(i).empty().append('正在加载中...')
											},
											error:function(){
												oDiv.find('div.tab-pane').eq(i).empty().append('加载失败，请检查请求链接，或者稍后重试!')
											}
										})
								 }
							}
				}else{
					alert("页面中id为"+_targetConId+"的div存在"+oDiv.find($(_targetConId)).length+"个，页面中id是唯一的，请检查后重新配置！");
				}
			}
			
        }
        function _init(_ul, _li){
            if(_li.hasClass(option.activeClass)){
                _setActive( _ul.find("li."+option.activeClass+":first") );
            }else if( option.activeIndex >= 0 ){
                _setActive( _li.eq(option.activeIndex) );
            }
        }
        function strToJson(str){
            str = str? str:"";
             return  eval( "({" + str + "})" );
        }

        //Interlace change color
        return this.each(function(i, n){
            var  _ul = $(n).children("div.title-bg").children("ul:first"),
                    _li = _ul.children("li"),
                    _rel =  strToJson( $(n).attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            option = $.extend(true, {}, defaults, option, _rel);

            // init
            _init(_ul, _li);

            // event
            _li.bind(option.trigger?option.trigger:option.trigger, function(e){
                _setActive($(this));
                if($(this).children("a").attr("target")==undefined){
                    e.preventDefault();
                }
            })
        })
    }

    // auto init plugin
    $(function(){
        $("*[data-role='ued-tabs']").UED_tabs();
    })
})(jQuery);