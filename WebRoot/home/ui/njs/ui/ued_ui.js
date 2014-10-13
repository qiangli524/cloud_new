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
	
	//进度条
	$("div.js_progressPer").each(function(){
		var _progress = $(this),
			_bar = _progress.children(".progress-bar"),
			_current = _progress.find(".current"),
		   num = /\d*\.?\d*(?=%)/.exec(_progress.find(".percent").html()),
		   currentW = parseInt(_bar.width()*num/100);
		_current.css("width",currentW+"px");
	})
	//修改默认checkbox样式
	$("input[type='checkbox'].inp-chb").each(function(){
		var _inpChb = $(this),
			_bChb = _inpChb.next();
		if(_inpChb.is(":checked")){
			_bChb.addClass("bon-chb");
		}else{
			if(_bChb.hasClass("bon-chb"))_bChb.removeClass("bon-chb");
		}
		_bChb.click(function(){
			_inpChb.trigger("click");
			if(_inpChb.is(":checked")){
				_bChb.addClass("bon-chb");
			}else{
				_bChb.removeClass("bon-chb");
			}
		})
	})
	//table悬停效果
	$("table.table-1").each(function(){
		$(this).find("tr").hover(function(){
			$(this).addClass("hover");								  
		},function(){
			$(this).removeClass("hover");	
		})								 
	})
	//修改默认下拉框
	$("div.js_select").each(function(){
		var _select = $(this),
			_seltt = _select.find(".js_seltt"),
			_selList = _select.find(".js_selList"),
			_selli = _selList.find("li");
		var newStr = _seltt.text().replace(/^\s*/,'').replace(/\s*$/,'')
		if(!newStr){
			_seltt.text(_selli.first().text());
		}
		_seltt.click(function(e){
			if(_selList.css("display")=="block"){
				_selList.css("display","none");	
			}else{
				_selList.css("display","block");	
			}
			e.stopPropagation();
		})
		_selli.click(function(e){
			_seltt.text($(this).text());
			_selList.css("display","none");	
			e.stopPropagation();
		}).mouseenter(function(e){
			$(this).addClass("hover").siblings().removeClass("hover");
		})
		$(document).click(function(){
			_selList.css("display","none");					
		})
	})
	//修改默认radio样式
	$("input[type='radio'].inp-radio").each(function(){
		var _inpRadio = $(this),
			_bRadio = _inpRadio.next(),
			name = _inpRadio.attr("name") || '';
		if(_inpRadio.is(":checked")){
			_bRadio.addClass("bon-radio");
		}else{
			if(_bRadio.hasClass("bon-radio"))_bRadio.removeClass("bon-radio");
		}
		_bRadio.click(function(){
			_inpRadio.trigger("click");	
			if($("input[type='radio']:checked")){
				$("input[type='radio']:checked").next().addClass("bon-radio");	
				$("input[type='radio']:not(:checked)").next().removeClass("bon-radio");
			}
			//_bRadio.parent().parent().find(".bon-radio").removeClass("bon-radio");
			//_bRadio.addClass("bon-radio");
		});
	})
	//输入框获取、失去焦点
	$("input[type='text']").each(function(){
		var _input = $(this);
		if(_input[0].value != _input[0].defaultValue){
			_input.css("color","#4f4f4f");	
		}else{
			_input.css("color","");
		}
		_input.focus(function(){
			if(_input[0].value == _input[0].defaultValue){
				_input[0].value="";
			}
			_input.css("color","#4f4f4f");	
		})
		_input.blur(function(){
			if(_input[0].value != _input[0].defaultValue){
				_input.css("color","#4f4f4f");	
			}else{
				_input.css("color","");
			}
			if(_input[0].value==""){
				_input[0].value=_input[0].defaultValue;
				_input.css("color","");
			}
		})
	})
	//超出6个字出现省略号
	$(".js_ByEllipsis").each(function(){
		var _this = $(this),
			txt = _this.text(),
			count = 0,
			showTxt = '',
			txtArr = [];
		txt = txt.replace(/^\s*/,'').replace(/\s*$/,'');
		txtArr = txt.split('');
		if(txtArr.length>6){
			for(var i=0,ln=txtArr.length;i<ln;i++){
				if(/[^\x00-\xff]/.test(txtArr[i])){
					count += 2;
				}else{
					count += 1;	
				}
				if(count>12){
					showTxt = showTxt + '...';
					break;
				}else{
					showTxt += txtArr[i];
				}
			}
		}else{
			showTxt = txt;
		}
		_this.text(showTxt);
	})
})
