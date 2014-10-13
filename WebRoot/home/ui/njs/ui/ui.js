$(function(){	
	//menu
	$("div.nav").each(function(){
		var _nav = $(this);
		_nav.find("div.thrNav").each(function(){
			$(this).prev("a").addClass("hasthr");
		});
		_nav.find(">ul>li").hover(function(){
			var _this = $(this),
				thisL = _this.offset().left,
				subW = _this.children(".subNav").outerWidth(),
				screenW = document.documentElement.clientWidth;
			if(subW>screenW-thisL+10){
				_this.children(".subNav").css({"left":"auto","right":"0"});
			}
			_this.children(".subNav").show();
			_this.addClass("on");
			_this.siblings().children(".subNav").hide();
		},function(){
			$(this).children(".subNav").hide();
			$(this).removeClass("on");	
		})
		_nav.find(".subNav>ul>li").click(function(e){
			$(this).parent().parent().hide();	
				e.stopPropagation();						  
		})
		_nav.find(".subNav>ul>li").hover(function(){
			var _this = $(this),
				thisL = _this.offset().left,
				subW = _this.closest(".subNav").outerWidth(),
				thrW = _this.children("div.thrNav").outerWidth(),
				screenW = document.documentElement.clientWidth;
			if(thrW>screenW-thisL-subW+10){
				_this.children("div.thrNav").css({"left":"auto","right":subW+"px"});
			}
			_this.addClass("on").children("div.thrNav").show();  
		},function(){
			$(this).removeClass("on").children("div.thrNav").hide();
		});
	})
	
	//tab
	$("div.js_tab").each(function(){
		var _tab = $(this),
			_tabTt = _tab.children(".tab-tt"),
			_tabCont = _tab.children("div.tab-cont"),
			cur = 0;
		if(_tabTt.children("li.on") && _tabTt.children("li.on").length){
			cur = _tabTt.children("li.on").index();
		}else{
			_tabTt.children("li:first").addClass("on");	
		}
		_tabCont.children("div.box").eq(cur).addClass("box-on").siblings().removeClass("box-on");
		_tabTt.children("li").click(function(){
			var _li = $(this);
			cur = _li.index();
			_li.addClass("on").siblings().removeClass("on");
			_tabCont.children("div.box").eq(cur).addClass("box-on").siblings().removeClass("box-on");

		})
	})
	
	//½ø¶ÈÌõ
	$("div.js_progressPer").each(function(){
		var _progress = $(this),
			_bar = _progress.children(".progress-bar"),
			_current = _progress.find(".current"),
		   num = /\d*(?=%)/.exec(_progress.find(".percent").html()),
		   currentW = parseInt(_bar.width()*num/100);
		_current.css("width",currentW+"px");
		_progress.find(".percent").css({"width":Math.abs(currentW-10)+"px"});
	})
})