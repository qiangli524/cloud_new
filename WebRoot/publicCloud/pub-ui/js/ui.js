//判断浏览器分辨率，如果是1024*768的，那么整体框架的宽度是980px，否则是1200px；
$(function(){
	var _browserW = window.screen.width;
	var _W1024 = 1024;
	//alert(_browserW);
	if(_browserW == _W1024){
		$(".top").addClass("top-1000");
		$(".top-con").addClass("top-con1000");
		$(".nav").addClass("nav-1000");
		$(".nav-con").addClass("nav-con1000");
		$(".container").addClass("container1000");
		$(".footer").addClass("footer-1000");
		$(".footer-con").addClass("footer-con1000");
		$(".copy").addClass("copy-1000");
		$(".copy-con").addClass("copy-con1000");
		$(".div-bg1").addClass("div-bg11000");
		$(".div-bg2").addClass("div-bg21000");
		$(".div-bg3").addClass("div-bg31000");
		$(".div-bg4").addClass("div-bg41000");
		$(".col-c2-1").addClass("col-c2-1n");
		$(".col-c2-2").addClass("col-c2-2n");
		$(".col-c7").addClass("col-c7-1000");
	}
})
//首页banner图片轮播
$(function(){
	var n = 0,
	_imgs = $("div.scroll-img>div"),
	_nums = $(".scroll-num>li"),
	count = _imgs.length;
	_nums.eq(0).addClass("on");
	_imgs.eq(0).siblings().hide();
	_nums.mouseover(function(){
		var i = $(this).index();
		n = i;		
		_imgs.eq(i).fadeIn().siblings().hide();
		$(this).addClass("on").siblings().removeClass("on");
	});
	var m = setInterval(function(){
		_nums.eq(n).trigger('mouseover');
		++n >= count? n=0 : n=n;
	}, 5000);
})
//二级导航
$(function(){
	$(".js_sub_nav").hover(function(){		
		$(this).children(".sub-nav").css({"opacity":1,"z-index":"1"}).stop(false,true).slideDown();
		var sub_navcon = $(this).find(".sub-nav .sub-navcon");
		sub_navcon.each(function(index) {
        var _dl=$(sub_navcon[index]).find("dl");
		var _dlWidthSum=0;
		_dl.each(function(index) {
            _dlWidthSum+=$(_dl[index]).width()+50;
			//alert($(_dl[index]).width())
        });
		//alert(_dl.length)
		$(sub_navcon[index]).css({width:_dlWidthSum,"margin":"0 auto"})
    });
	},function(){
		$(this).children(".sub-nav").stop(false,true).slideUp();
	})
})
$(function(){
	//滑过资质图片边框和字体颜色
	$(".js_pic a").hover(function(){
		$(this).addClass("a-h");
	},function(){
		$(this).removeClass("a-h");
	})
	//滑过云数据中心模块，边框颜色
	$(".js_border .col-c1-1 .box-con").hover(function(){
		$(this).addClass("div-border");
	},function(){
		$(this).removeClass("div-border");
	})
	ui_common_tab(".js_show_tab .js_tab_caption ul li");//通用tab，滑过效果
	ui_click_tab(".js_click_tab .js_click_caption ul li");//通用tab，滑过效果
})
//通用tab，滑过效果
function ui_common_tab(obj){
	$(obj).hover(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var temp_id = $(this).parent().children("li").index($(this));
		$(this).parent().parent().next().children("div").css("display","none");
		$(this).parent().parent().next().children("div").eq(temp_id).css("display","block");
		
	})
}
//通用tab，点击效果
function ui_click_tab(Obj){
	$(Obj).click(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var temp_id = $(this).parent().children("li").index($(this));
		$(this).parent().parent().next().children("div").css("display","none");
		$(this).parent().parent().next().children("div").eq(temp_id).css("display","block");
		
	})
}

//合作伙伴
$(function () {	
	var n = 0,
	_index,
	_lis = $(".col-c6-con .content"),
	_nums = $(".col-btn>a"),
	count = _lis.length;
	_nums.eq(0).addClass("on");
	_nums.mouseover(function(){
		var i = $(this).index();
		n = i;	
		$(this).addClass("on").siblings().removeClass("on");
	});
	var t = setInterval(function(){
		_nums.eq(n).trigger('mouseover');
		++n >= count? n=0 : n=n;
	}, 5000);
	
	var ui_div_left = $(".col-c6-con");
	var ui_Width_div = $(".col-c6-con .content").width(); //获取焦点图的宽度（显示面积）
	var ui_div_len = $(".col-c6-con .content").length; //获取焦点图个数
	var ui_group = $(".col-btn a").length;	
	
	$(".col-btn a:eq(0)").addClass("on");
	var ui_guess_Width = ui_Width_div * ui_div_len;
	$(".col-c6-con").css("width", ui_guess_Width);
	$(".col-c6-con .content").css("width",ui_guess_Width/ui_div_len);
	if (ui_div_left.length > 0) {
		var ui_guess_ul_left = document.getElementById('move').style.marginLeft;
	}
	
	$(".col-btn a").hover(function () {
		$(this).addClass("on").siblings().removeClass("on");
		var ul_left = $(".col-btn a").index(this) * -ui_Width_div;
		ui_guess_ul_left = ul_left;
		//$(".col-c6-con").css({ left: ul_left +"px"});
		$(".col-c6-con").animate({ left: ul_left +"px"});
		return false;
	})
});

$(function(){
	$(".js_table_f1 tr:odd").addClass("tr-bink");
	$(".js_table_f1 tr").find("td:last").css({"border-right":"none","text-align":"left"});
	$(".js_table_f1 tr").find("td:first").css({"border-left":"none"});
	$(".js_table_f1 tr").find("th:last").css({"border-right":"none"});
	$(".js_table_f1 tr").find("th:first").css({"border-left":"none"});/**/
	$(".js_table_f1 tbody tr:first").find("td:eq(1)").css({"background":"#fff"});
	$(".js_table_f1 thead tr th").css({"border-top":"none"});
	
	//获取到右侧的高度，然后负值给左侧
	//var _h = $(".col-c7 .main-c1").outerHeight();
	//if(_h>400){
	//	$(".col-c7 .side-c1").css("height",_h+"px");
	//}else{
	//	$(".col-c7 .side-c1").css("height",600+"px");
	//	$(".col-c7 .main-c1").css("height",575+"px");
	//}	
	
	//滑过产品服务左侧导航
	$(".js_list_w6 li").hover(function(){
		$(this).addClass("h");
	},function(){
		$(this).removeClass("h");
	})
	
	//控制中心表格隔行变色
	$(".js_table_f4 tr:odd").addClass("tr-bink");
	$(".check-box").toggle(function(){
		$(this).addClass("check-boxs");
	},function(){
		$(this).removeClass("check-boxs");
	})
	
	//更多操作
	$(".js_more").mouseover(function(){
		$(this).addClass("more-h").siblings(".operate-dl").show();
	})
	$(".operate-dl").hover(function(){
		$(this).show();
	},function(){
		$(this).hide();
		$(this).siblings(".js_more").removeClass("more-h");
	})
	
	//滑过控制中心方框变色
	$(".list-item").hover(function(){
		$(this).addClass("list-item-bg");
	},function(){
		$(this).removeClass("list-item-bg");
	})
})

//虚拟下来框
$(function(){
	document.onclick = function(){
		$(".select-list").hide();
	};
	selectEvent(".select-n1",true);
	selectEvent(".select-list-n1",false);	
	ui_select_load(".select-list-n1 li",".select-n1");
	
	selectEvent(".select-c1",true);
	selectEvent(".select-list-c1",false);
	ui_select_load(".select-list-c1 li",".select-c1");
})
function ui_select_load(Obj,Obj1){
	$(Obj).hover(function(){
		$(this).addClass("li-hover");
	},function(){
		$(this).removeClass("li-hover");
	})	
	$(Obj).click(function(){
		var s_txt = $(this).text();
		//$(Obj1).html(s_txt);
		$(this).parent(".select-list").siblings().html(s_txt);//修改后
		$(this).parent(".select-list").hide();
	})
}
function selectEvent(slt,isTrue){
	$(slt).click(function(event){
		var e=window.event || event;
		if(e.stopPropagation){
			e.stopPropagation();
		}else{
			e.cancelBubble = true;
		}
		if(isTrue==true){
			$(slt).siblings().show();
		}
	});
}

//订单管理虚拟下来菜单
$(function(){
	$(".select_box").click(function(event){   
		event.stopPropagation();
		$(this).find(".option").toggle();
		$(this).parent().siblings().find(".option").hide();
	});
	$(document).click(function(event){
		var eo=$(event.target);
		if($(".select_box").is(":visible") && eo.attr("class")!="option" && !eo.parent(".option").length)
		$('.option').hide();									  
	});
	/*赋值给文本框*/
	$(".option li").click(function(){
		var value=$(this).text();
		$(this).parent().siblings(".select_txt").find("span").text(value);
		//$("#select_value").val(value)
	})
	$(".option li").hover(function(){
		$(this).addClass("h");
	},function(){
		$(this).removeClass("h");
	})
	
	//表格编辑功能
	$(".js_compile").hover(function () {
        $(this).addClass("position-rel").children("p").addClass("p-h");
    }, function () {
        $(this).children("p").removeClass("p-h");
    })
	$(".js_compile p").click(function () {
        $(".compile-con").css("z-index","0");
        $(this).siblings(".compile-con").css("z-index", "99");
        $(this).siblings(".compile-con").show();
        return false;
    })
	$(".js_abrogate").click(function(){
		$(this).parents(".compile-con").hide();
		return false;
	})
	//左侧菜单滑过有背景
	$(".js_current dd").hover(function(){
		$(this).addClass("current-ddhover");
	},function(){
		$(this).removeClass("current-ddhover");
	})
	//右上角操作提示框
	$(".js_p_close").click(function(){
		$(this).parents(".overall-prompt").hide();
		return false;
	})
})

$(document).click(function(){
	//$(".compile-con").hide();
})


