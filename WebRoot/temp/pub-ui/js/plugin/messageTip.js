/**
 * @Filename: placeholder.js
 * @Description: placeholder event
 * @Version: 1.0.0
 * @Author: hanlx.si-tech
 * @UpdateBy: ******
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * <div class="messageTip" data-rel="content:'固定提示信息',auto:true">页面加载时，信息提示框默认在左上方展开</div>
 * $("div.messageTip").UED_messageTip();
 *
*/
(function($){
	$.fn.UED_messageTip = function(option){
		var defaults = {
			content:"",             //设置提示信息
			width:"auto",			//设置提示框的宽度
			height:"auto",			//设置提示框的高度
			x:'left',				//提示框相对于当前对象的水平位置
			y:'top',				//提示框相对于当前对象的垂直位置
			distanceX:0,			//提示框相对于当前对象的水平偏移量
        	distanceY:0,			//提示框相对于当前对象的垂直偏移量
			auto:false,             //提示信息是否自动加载，如果不是，那么：鼠标经过时显示，移开时关闭,并且当content为空时自动读取attr[title]
			removeTitle: true		//设置提示框的高度
		};
		function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }
		function _mouseHandler(o,p){
			p.content = p.content || o.attr("title");
			if(p.removeTitle){
				o.removeAttr("title");
			}
			//如果不是自动模式：鼠标经过时显示，移开时关闭
			if (!p.auto){
				
				function over(o,p){
					if(!o.tip){
						_tip(o,p);
					}	
				}
				function out(o,p){
					if(o.tip){
						o.tip.remove();
					}
				}
				o.live("mouseover",function (){
					_tip(o,p);
					_closeTip(o)
					
				}).live("mouseout",function(){
					setTimeout(function(){o.tip.remove();console.log(o.tip);},500);	
				})
			}else{
				_tip(o,p);
				_closeTip(o);
			}
		}
		function _position(o,p){
			if(p.x == "left"){
				p.valX = (o.offset().left + (p.distanceX || 0)) || 0;
			}else if(p.x == "right"){
				p.valX  = (o.width() + o.offset().left + (p.distanceX || 0)) || 0;
			}
			if(p.y == "top"){
				p.valY = (o.offset().top + (p.distanceY || 0)) || 0;
			}else if(p.y == "bottom"){
				p.valY = (o.height() + o.offset().top + (p.distanceY || 0)) || 0; 	
			}
		}
		function _closeTip(o){
			$(".l-verify-tip-close").click(function(){
				$(this).parents(".l-verify-tip").remove();
				o.unbind("mouseover mouseout");
			})
		}
		function _tip(o,p){
			if(p.content){
				var tip = $('<div class="l-verify-tip"><div class="l-verify-tip-corner"></div><div class="l-verify-tip-box"><div class="l-verify-tip-content">'+p.content+'</div><div class="l-verify-tip-close"></div></div></div>');
				tip.appendTo("body");
				o.tip = tip;
				_position(o,p);
				tip.css({'left':p.valX+"px",'top':p.valY+"px"});
				if(p.y == "top" && p.distanceY<-o.height()+8){
					o.tip.find(".l-verify-tip-corner").addClass("l-verify-tip-cornerT").css({"top":o.height(),"left":o.width()/2});
				}else if(p.y == "bottom" && p.distanceY>=0){
					o.tip.find(".l-verify-tip-corner").addClass("l-verify-tip-cornerB").css({"left":o.width()/2});
				}
			}
		}
		return this.each(function(i,n){
			var _this = $(n);
			var _rel =  strToJson(_this.attr("data-rel"));
			var option = $.extend(true,{},defaults,option,_rel);
			_this.data("option",option);
			_mouseHandler(_this,option);
		})
		
	}
	
	$(function(){
		$("*[data-role='ued-messageTip']").UED_messageTip();		   
	});
})(jQuery)