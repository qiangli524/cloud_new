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
		$(".step-n").addClass("step-w").removeClass("step-n");
		var _length = $(".step-w .font-w").length;
		if(_length==1){			
			$(".step-w").addClass("step-w1");
		}else if(_length==2){			
			$(".step-w").addClass("step-w2");
		} else {			
			$(".step-w").addClass("step-w3");
		}
		//alert(_length)
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
	ui_click_tab(".js_click_caption ul li");//通用tab，滑过效果
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
	var _h = $(".col-c7 .main-c1j").outerHeight();
	if(_h>400){
		$(".col-c7 .side-c1").css("height",(_h+100)+"px");
	}else{
		//$(".col-c7 .side-c1").css("height",600+"px");
		$(".col-c7 .main-c1j").css("height",675+"px");
	}	
	
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
	$(".js_more").toggle(function(){
		$(this).siblings(".operate-dl").show();
	},function(){
		$(this).siblings(".operate-dl").hide();
	})
	
	//滑过控制中心方框变色
	$(".list-item").hover(function(){
		$(this).addClass("list-item-bg");
	},function(){
		$(this).removeClass("list-item-bg");
	})
	
	//预警开关
	$(".js_switch .i-right").click(function(){		
		$(this).parents("td").find(".thick-content").show();
		$(this).children("i").css("display","block");
		$(this).parents(".span-bg").addClass("span-bg1");
		$(this).siblings(".i-left").children("i").css("display","none");
	})
	$(".js_switch .i-left").click(function(){
		$(this).children("i").css("display","block");
		$(this).parents(".span-bg").removeClass("span-bg1");
		$(this).siblings(".i-right").children("i").css("display","none");
	})
	//选择配置选择
	$(".js_more_c li .btn-n1").click(function(){
		$(this).addClass("btn-n1on").siblings(".btn-n1").removeClass("btn-n1on");
		return false;
	})
	//产品购买数量
	ui_number(".js_number",".a-addn",".a-cut",".input-w4-1");
})
//产品购买数量
function ui_number(objPos,objA,objC,objI){
	var a=$(objI).val();
	$(objPos).delegate(objA,"click",function(){
		a++;
		$(objI).val(a);
		return false;
	})
	$(objPos).delegate(objC,"click",function(){
		a--;
		$(objI).val(a);		
		if(a<=1){
			a=1;
			$(objI).val(1);		
		}
		return false;
	})
}

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
	
	selectEvent(".select-c2",true);
	selectEvent(".select-list-c1",false);
	ui_select_load(".select-list-n2 li",".select-c2");
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
		$(this).siblings().find(".option").hide();
	});
	$(document).click(function(event){
		var eo=$(event.target);
		if($(".select_box").is(":visible") && eo.attr("class")!="option" && !eo.parent(".option").length)
		$('.option').hide();							  
	});
	/*赋值给文本框*/
	$(".option").delegate("li","click", function(){
		var value=$(this).text();
		var id = $(this).attr("id");
		$(this).parent().siblings(".select_txt").find("span").text(value);
		$(this).parent().siblings(".select_txt").find("span").attr("id",id);
	})
	$(".option li").hover(function(){
		$(this).addClass("h");
	},function(){
		$(this).removeClass("h");
	})
})

//公网宽带
var _index,_offsetLeft,_posx,_left,_spanWidth,_count,_base;
$(function () {
    var x = $(".column-w1").offset();
    _spanWidth = $(".column-w1 span:first").width() / 5;
    _base = _spanWidth - 2;
    _index = 0;
    $(".column-w2").css("width", _base + "px");
    $(".drag").css("left", _base + "px");
    if (x == null) return false;
    _offsetLeft = x.left;
    	
    $(".drag").bind("mousedown", function (e) {
        _posx = e.clientX;
        _left = _posx - _offsetLeft;
        //alert(_left)
        if (_left <= 0) {
            $(".column-w2").css("width", "0px");
            $(this).css("left", "0px");
        }
        else if (_left <= 478) {
            $(".column-w2").css("width", _left + "px");
            $(this).css("left", _left + "px");
        }
        else {
            $(".column-w2").css("width", 478 + "px");
            $(this).css("left", 478 + "px");
        }
        $(document).bind({
            'mousemove': mouseMove,
            'mouseup': mouseUp
        });
        return false;
    })
    $(".uc-input").bind("keyup", function () {
        var _val = $(".uc-input").val();
        _val = _val.replace(/[^0-9]/g, '')
        $(".uc-input").val(_val)
        if (_val == "") return false;
        if (parseInt(_val) <=0) $(".uc-input").val(0);
        if (parseInt(_val) > 200) $(".uc-input").val(200);
        var _sum = ComputeSetLeft(_val)-5;
        $(".column-w2").css("width", _sum + "px");
        $(".drag").css("left", _sum + "px");
    });
})
function mouseMove(e){
	_posx = e.clientX;
	_left = _posx - _offsetLeft;
	if(_left<=0){
	  $(".column-w2").css("width","0px");
	  $(".drag").css("left","0px");
	} 
	else if(_left<=480) {
		$(".column-w2").css("width",_left+"px");
		$(".drag").css("left",_left+"px");
	}
	else{			
		$(".column-w2").css("width",478+"px");
		$(".drag").css("left",478+"px");		
	}
$(".uc-input").val(parseInt(ComputeValue(_left)));
}
function mouseUp(e){
	$(document).unbind('mousemove');
}
function ComputeValue(_setLeft) {
    if (_setLeft <= 0) return 0;
    if (_setLeft > 480) return 200;
    if (_setLeft <= 240) return _left / 48;
    if (_setLeft > 240 && _left <= 360) return (_left - 240) / 1.26315789 + 5;
    if (_setLeft > 360) return (_left - 360) / 1.2 + 100;
}
function ComputeSetLeft(val) {
    if (val <= 0) return 0;
    if (val >= 200) return 480;
    if (val <= 5) return val * 48;
    if (val > 5 && val <= 100) return 240 + (val-5) * 1.26315789;
    if (val > 100) return 360 + (val-100)*1.2
}
function RecursionCount(a,b){
	if(a-b>b){
		return 1+ RecursionCount(a-b,b);
	}else if(a-b < b && a-b>0){
		return 2;
	}else if(a>0 && a-b<0) {
		return 1;
	}else{
		return 0;
	}
}

//购买时长
var _index_m,_offsetLeft_m,_posx_m,_left_m,_spanWidth_m,_count_m,_base_m;
$(function () {
    var x = $(".column-w1-1").offset();
    _spanWidth_m = $(".column-w1-1 span:first").width() / 6;
    _base_m = _spanWidth_m - 2;
    _index_m = 0;
    $(".column-w2-2").css("width", _base_m + "px");
    $(".drag-w1").css("left", _base_m + "px");
    if (x == null) return false;
    _offsetLeft_m = x.left;
    	/*$(".column-w1-1 span").bind("click",function(e){
    		_index_m=$(this).index();
    		_posx_m = e.clientX;
    		_left_m = _posx_m - _offsetLeft_m;

    		$(".drag-w1").css("left",_left_m - 5 + "px");
    		$(".column-w2-2").css("width",_left_m + "px");
    		$(this).css("left",_left_m+"px");
    		$(".uc-input-month").val(parseInt(ComputeValue(_left_m)));
    		return false;
    	})*/
    $(".drag-w1").bind("mousedown", function (e) {
        _posx_m = e.clientX;
        _left_m = _posx_m - _offsetLeft_m;
        //alert(_left)
        if (_left <= 0) {
            $(".column-w2-2").css("width", "0px");
            $(this).css("left", "0px");
        }
        else if (_left_m <= 478) {
            $(".column-w2-2").css("width", _left_m + "px");
            $(this).css("left", _left_m + "px");
        }
        else {
            $(".column-w2-2").css("width", 478 + "px");
            $(this).css("left", 478 + "px");
        }
        $(document).bind({
            'mousemove': mouseMove_M,
            'mouseup': mouseUp_M
        });
        return false;
    })
    $(".uc-input-month").bind("keyup", function () {
        var _val = $(".uc-input-month").val();
        _val = _val.replace(/[^0-9]/g, '')
        $(".uc-input-month").val(_val)
        if (_val == "") return false;
        if (parseInt(_val) <=0) $(".uc-input-month").val(0);
        if (parseInt(_val) > 24) $(".uc-input-month").val(24);
        var _sum = ComputeSetLeft_M(_val);
        $(".column-w2-2").css("width", _sum + "px");
        $(".drag-w1").css("left", _sum + "px");
    });
})
function mouseMove_M(e){
	_posx_m = e.clientX;
	_left_m = _posx_m - _offsetLeft_m;
	if(_left_m<=0){
	  $(".column-w2-2").css("width","0px");
	  $(".drag-w1").css("left","0px");
	} 
	else if(_left_m<=480) {
		$(".column-w2-2").css("width",_left_m+"px");
		$(".drag-w1").css("left",_left_m+"px");
	}
	else{			
		$(".column-w2-2").css("width",478+"px");
		$(".drag-w1").css("left",478+"px");		
	}
$(".uc-input-month").val(parseInt(ComputeValue_M(_left_m)));
}
function mouseUp_M(e){
	$(document).unbind('mousemove');
}
function ComputeValue_M(_setLeft) {	
    if (_setLeft <= 0) return 0;
    if (_setLeft >= 480) return 24;
	var val=_setLeft/_spanWidth_m;
    return val+(_setLeft - val*_spanWidth_m>0?1:0);
}
function ComputeSetLeft_M(val) {
    if (val <= 0) return 0;
    if (val >= 24) return 480;
    return _spanWidth_m * val;
}

//支付方式选择
$(function(){
	$(".js_pay_list li input").click(function(){
	  $(this).attr("checked","checked");
	  if($(this).attr("checked","checked")){
		  $(".btn-28").addClass("btn-29");
	  }	  
	});
	$(".js_radio img").click(function(){
		$(this).parent().siblings("input").attr("checked","checked");
		$(this).addClass("border-ore");
		$(this).parent().parent().siblings().find("img").removeClass();
		if($(this).attr("class","border-ore")){
		  $(".btn-28").addClass("btn-29");
	  	}		
	})
	//支付方式tab选择
	$(".js_bank i").click(function(){
		$(this).addClass("zfbon").siblings("i").removeClass("zfbon");
		var temp_id = $(this).parent().children("i").index($(this));
		$(this).parent().next(".content-bank").children("div").css("display","none");
		$(this).parent().next(".content-bank").children("div").eq(temp_id).css("display","block");
		
	})
	//发票邮件方式
	$(".js_post em").click(function(){
		if($(this).attr("class")=="mgr-20 mail"){
			$(this).parents(".bill-t").siblings(".bill-address").show();
		} else {
			$(this).parents(".bill-t").siblings(".bill-address").hide();
		}
	})
	//弹出层
	ui_thick(".js_thick", ".js_t_close", ".thick-content");
})
//弹出层
function ui_thick(objShow,objHide,objThick) {
    var _w = $(objThick).width() / 2;
    var _h = $(objThick).height() / 2;
    $(objThick).css("margin-left", -_w + "px");
    if (!(jQuery.browser.msie && jQuery.browser.version < 7)) { // take away IE6
        $(objThick).css({
            marginTop: -_h + 'px'
        });
    }
    $(objShow).click(function () {
        $("#thick_bj").show();
        $(objThick).show();		
        return false;
    })
    $(objHide).click(function () {
        $("#thick_bj").hide();
        $(objThick).hide();
        return false;
    }) 	
}

//数据盘
var _uc_drag,_uc_x,_uc_posX,_uc_offsetLeft,_uc_left,_uc_base_left,_span_baseW1,_span_baseW2,_span_baseW3;
var _s_v1 = 512, _s_v2 = 1024, _s_v3 = 2048, _input_val = 0, _sumUC_DataCount = 0;

$(function () {
    //添加一块数据盘
    $(".a-add").bind("click", function () {
        if ($(this).hasClass("font-gary")) return false;
        var _len_data = $(".column-data").children("div").length;
        if (_len_data < 5) {
            _sumUC_DataCount++;
            var _uc_data_content = "<!--row-li star--><div class='row-li pb15 w620'><div class='fl position-rel'><div class='column-w1'><span class='block first'><div>512GB</div></span><span class='block second'><div>1024GB</div></span><span class='block second last'><div>2048GB</div></span></div><div class='column-c' style='width: 46px;'><div class='column-oer'><span class='block first'><div>512GB</div></span><span class='block second'><div>1024GB</div></span><span class='block second'><div>2048GB</div></span></div></div><a class='drag-data ^drag-data-index^'><i></i><i></i><i></i></a></div><span class='mgl-15'><input type='text' value='5' class='uc-input-data'> GB</span><a href='javascript:' class='close-a'></a><div class='clear'></div></div><!--row-li end-->";
            _uc_data_content = _uc_data_content.replace("^drag-data-index^", "drag-data" + _sumUC_DataCount);
            $(".row-li-last").before(_uc_data_content);
            uc_drag_data(".drag-data" + _sumUC_DataCount);
            if (4 - _len_data == 0) {
                $(".a-add").addClass("font-gary1");
				$(".a-add").html("不可再添加");
            }
            else {
                $(".a-add").removeClass("font-gary1");
				$(".a-add").html("添加一块");
            }
            $(".disk-number").html(4 - _len_data);
            $(".disk-over-capacity").html(uc_get_dataval("0", 5));
        }
        //移除一块数据盘
        $(".close-a").click(function () {
            //alert(1)
            var _i_val = $(this).siblings("span").find("input").val();
            $(this).parent().remove();
            var _s_h = 5 - $(".column-data").children("div").length; ;
            $(".disk-number").html(_s_h);
            $(".disk-over-capacity").html(uc_get_dataval(_i_val, 0));
            $(".disk-info").children("span:first").show();
            $(".a-add").removeClass("font-gary1");
			$(".a-add").html("添加一块");
        })
        return false;
    })

})
//获取剩余数据盘容量
function uc_get_dataval(removeVal,useVal) {
    var _sum_vals = 0;
    var _inputLists = $(".uc-input-data");
    _inputLists.each(function (i) {
        _sum_vals += parseInt("0" + $(this).val());
    })
    if (parseInt("0" + removeVal) > 0) return 8192 - _sum_vals;
    if (useVal > 0) return 8192 - _sum_vals;
    if (parseInt("0" + removeVal) == 0) return 8192;    
}
//加载数据盘拖拽效果
function uc_drag_data(objDrag){	
	_uc_drag=objDrag;
	_uc_x=$(_uc_drag).parent().offset();
	_span_w1=$(_uc_drag).siblings(".column-w1 span:first").width()/512;
	_uc_base_left=_span_w1*5;
	$(_uc_drag).css("left",_uc_base_left+"px");
	$(_uc_drag).siblings(".column-c").css("width",_uc_base_left+"px");
	if (_uc_x == null) return false;
	_uc_offsetLeft=_uc_x.left;
	_span_baseW1=$(_uc_drag).siblings(".column-w1").find("span:first").width()/512;
	_span_baseW2=$(_uc_drag).siblings(".column-w1").find("span:eq(1)").width()/512;
	_span_baseW3=$(_uc_drag).siblings(".column-w1").find("span:eq(2)").width()/1024;
	
    //绑定鼠标按下事件
	$(_uc_drag).bind("mousedown", function (e) {
	    _uc_drag = $(this);
	    _uc_posX = e.clientX;
	    _uc_left = _uc_posX - _uc_offsetLeft;
	    if (sumVal()) {
	        $(".disk-info").children("span:first").hide();
	        $(".a-add").addClass("font-gary1");
			$(".a-add").html("不可再添加");
	        //return false;
	    }
	    else {
	        $(".disk-info").children("span:first").show();
	        $(".a-add").removeClass("font-gary1");
			$(".a-add").html("添加一块");
	    }
	    if (_uc_left <= 0) {
	        $(this).siblings(".column-c").css("width", _uc_base_left + "px");
	        $(this).css("left", _uc_base_left + "px");
	        _input_val = 5;
	    }
	    else if (_uc_left <= 240) {
	        $(this).siblings(".column-c").css("width", _uc_left + "px");
	        $(this).css("left", _uc_left + "px");
	        _input_val = calculateVal(_uc_left, _span_baseW1, 0);
	    }
	    else if (_uc_left <= 360) {
	        $(this).siblings(".column-c").css("width", _uc_left + "px");
	        $(this).css("left", _uc_left + "px");
	        _input_val = calculateVal(_uc_left - 240, _span_baseW2, _s_v1);
	    }
	    else if (_uc_left <= 479) {
	        $(_uc_drag).siblings(".column-c").css("width", _uc_left + "px");
	        $(_uc_drag).css("left", _uc_left + "px");
	        _input_val = calculateVal(_uc_left - 360, _span_baseW3, _s_v2);
	    }
	    else if (_uc_left >= 479.5) {
	        $(_uc_drag).siblings(".column-c").css("width", 479.5 + "px");
	        $(_uc_drag).css("left", 479.5 + "px");
	        _input_val = 2048;
	    }

	    $(this).parent().siblings(".mgl-15").find("input").val(_input_val);
	    $(".disk-over-capacity").html(uc_get_dataval("0", _input_val));
	    $(document).bind({
	        'mousemove': mouseMove_Data,
	        'mouseup': mouseUp_Data
	    });
	    return false;
	});	    

    //拖拽事件处理
	function mouseMove_Data(e){
	  _uc_posX= e.clientX;
	  _uc_left = _uc_posX - _uc_offsetLeft;
	  //if(sumVal())return false;  
	  if (_uc_left <= 0) {
		  $(_uc_drag).siblings(".column-c").css("width", _uc_base_left+"px");
		  $(_uc_drag).css("left", _uc_base_left+"px");
		  _input_val=5;
	  }
	  else if (_uc_left <= 240) {
		  $(_uc_drag).siblings(".column-c").css("width", _uc_left + "px");
		  $(_uc_drag).css("left", _uc_left + "px");
		  _input_val=calculateVal(_uc_left,_span_baseW1,0);
	  }
	  else if(_uc_left<=360) {
		  $(_uc_drag).siblings(".column-c").css("width", _uc_left + "px");
		  $(_uc_drag).css("left", _uc_left + "px");
		  _input_val=calculateVal(_uc_left-240,_span_baseW2,_s_v1);
	  }
	  else if(_uc_left<=479){
		  $(_uc_drag).siblings(".column-c").css("width", _uc_left + "px");
		  $(_uc_drag).css("left", _uc_left + "px");
		  _input_val=calculateVal(_uc_left-360,_span_baseW3,_s_v2);
	  }
	  else if(_uc_left>=479.5){
	  	  $(_uc_drag).siblings(".column-c").css("width", 479.5 + "px");
		  $(_uc_drag).css("left", 479.5 + "px");
		  _input_val=2048;
	  }
	  $(_uc_drag).parent().siblings(".mgl-15").find("input").val(_input_val);
	  if (sumVal()) {
	      $(".disk-info").children("span:first").hide();
	      $(".a-add").addClass("font-gary1");
		  $(".a-add").html("不可再添加");
	  }
	  else {
	      $(".disk-info").children("span:first").show();
	      $(".a-add").removeClass("font-gary1");
		  $(".a-add").html("添加一块");
	  }
	  $(".disk-over-capacity").html(uc_get_dataval("0",_input_val));

}
//移除鼠标移动事件
function mouseUp_Data(e){
	$(document).unbind('mousemove');
}
//验证数据盘总容量是否超出固定值
function sumVal() {
    var _sum_val = 0, _spanW = 0;
    var _thisVal = $(_uc_drag).parent().siblings(".mgl-15").find("input").val();
    var _inputList = $(".uc-input-data");
    _inputList.each(function (i) {
        _sum_val += parseInt("0" + $(this).val());
    })
    if (_sum_val > 8192) {
        $(_uc_drag).parent().siblings(".mgl-15").find("input").val(2048 - (_sum_val - _thisVal));
        _thisVal = $(_uc_drag).parent().siblings(".mgl-15").find("input").val();
        if (_thisVal > 1024) {
            _spanW = 360 + (_thisVal - 1024) * _span_baseW3;
        }
        else if (_thisVal > 512) {
            _spanW = 240 + (_thisVal - 512) * _span_baseW2;
        }
        else if (_thisVal > 0) {
            _spanW = _thisVal * _span_baseW1;
        }
        $(_uc_drag).siblings(".column-c").css("width", _spanW + "px");
        $(_uc_drag).css("left", _spanW + "px");
        return true;
    }
	else if(_sum_val==8192){
		return true;
	}
    return false;
}
//计算拖拽时数据盘相应容量值
function  calculateVal(widthVal,baseW,cumulativeVal){
	var _val=parseInt(widthVal/baseW);
	if(cumulativeVal>0)_val=_val+cumulativeVal;
	return _val;
}
//添加数据盘
function add_uc_drag_data() {
    _sumUC_DataCount++;
    var _uc_data_content = "<!--row-li star--><div class='row-li pb15 w620'><div class='fl position-rel'><div class='column-w1'><span class='block first'><div>512GB</div></span><span class='block second'><div>1024GB</div></span><span class='block second last'><div>2048GB</div></span></div><div class='column-c' style='width: 46px;'><div class='column-oer'><span class='block first'><div>512GB</div></span><span class='block second'><div>1024GB</div></span><span class='block second'><div>2048GB</div></span></div></div><a class='drag-data ^drag-data-index^'><i></i><i></i><i></i></a></div><span class='mgl-20'><input type='text' value='5' class='uc-input-data'> GB</span><a href='javascript:' class='close-a'></a><div class='clear'></div></div><!--row-li end-->";
    _uc_data_content = _uc_data_content.replace("^drag-data-index^", "drag-data" + _sumUC_DataCount);
    $(".row-li-last").before(_uc_data_content);
    uc_drag_data(".drag-data"+_sumUC_DataCount);
}
}

//企业个人信息填写
$(function(){
	ui_radio_tab(".js_select_r",".write");
})
function ui_radio_tab(selectObj,contentObj){
    $(selectObj).find("span").click(function () {
        $(this).children("input[type=radio]").attr("checked", 'checked');
        $(this).siblings().children("input[type=radio]").attr("checked", false);
        var index = $(selectObj).find("span").index($(this));
        var selectDiv = $(contentObj).children("div");
        $(selectDiv[index]).show().siblings().hide();
    })	
}
//list-w7 li的个数
$(function(){
	$(".list-w7").delegate("li","click", function(){});
	var _lith = $(".list-w7 li").length;
	var _liW = _lith*$(".list-w7 li").width()+_lith*7;
	$(".addBut").css({left:_liW});
})
//虚拟下来菜单
$(function(){
	$(".jsMore").toggle(function(event){   
		event.stopPropagation();
		$(this).parent("li").next("li").find(".opBox").show(300);
	},function(event){   
		event.stopPropagation();
		$(this).parent("li").next("li").find(".opBox").hide(300);
	});
	
	
})	
