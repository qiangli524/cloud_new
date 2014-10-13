$(function(){
	common_tab(".show-area .tab-caption ul li");//通用TAB
	common_tab_has_scroll();//点击,内容滚动的TAB
	common_tips();//通用提示
	common_select();//通用模拟下拉select
	accordion();//层叠面板
	about_show_area_2();//show-area-2相关效果
	common_tab(".show-tab .tab-caption ul li");//通用TAB
	common_tab(".show-tab-4 .tab-caption ul li");//通用TAB
	//common_tab(".show-tab-3 .tab-caption ul li");
	common_tab(".selfservice-tab .tab-caption ul li");//通用TAB
	common_tab(".selfservice-tab-vertical .tab-caption ul li");//通用TAB
	about_icon_box();//切换样式
	customer_panel_effect();//用户选择号码，切换当前号码
	selfservice_form_pop_panel();//自助服务，我的产品信息弹出star
	common_tab_vertical();//通用竖向tab，点击效果
	about_q_and_a();//常见问题解答
	about_daily_download();//常见下载相关函数
	//about_menu();//判断主导航中哪个导航为高亮
	about_collect();//加入收藏
	about_show_area_3();//show-area-3相关函数
	switch_tab();//组合TAB
	showFAV();
	write_active_package_for_select();//IE6下启动该函数，解决IE6下拉框遮住DIV的问题
	//addclass();//弹出层
	autoScroll();
	
	$("input.text-style-1").hover(function(){$(this).next().next().show();},function(){$(this).next().next().hide();});
})

//给查询页面-2添加权限标签，去掉其点击功能和收藏功能start 
function disabled_switch_tab(ary){
	//var aa=".switch-tab li[tar="+ary+"]";
	//$(aa).addClass("disabled");
	//$(aa).attr("title","抱歉，您没有权限");
	//$(aa +" .favorite").remove();
		var aa=".tab-caption li[tar="+ary+"]";
		$(aa).addClass("disabled");
		$(aa).attr("title","抱歉，您没有权限");
		$(aa +" .favorite").remove();
		
		$("<div style='display:none;'></div>").insertAfter(".tab-content #"+ary);
}

//给查询页面-2添加权限标签，去掉其点击功能和收藏功能start



function showFAV(){
	
		$(".switch-tab li a").hover(
									
			function(){
					$(this).find("span").show();
			},
			function(){
				$(this).find("span").hide()
			}
		)
		$(".switch-tab li a span").click(function(e){e.stopPropagation?e.stopPropagation():e.cancelBubble=true;})
	
}


function about_head(){//头部效果，选择城市
	$(".head .text").hover(function(){
		$(this).children(".pop-menu").show();
	},function(){
		$(this).children(".pop-menu").hide();	
	})
}

function about_nav(){//主导航
	$(".nav-panel li").hover(function(){
		$(this).children("ul").show();
	},function(){
		$(this).children("ul").hide();
	})
}

function about_compose_1(){//组合样式1效果
	$(".compose-1 li").hover(function(){
		$(this).children(".pop-content").show();							  
	},function(){
		$(this).children(".pop-content").hide();
	})
}

function common_tab(obj){//通用tab，点击效果
	$(obj).click(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var temp_id = $(this).parent().children("li").index($(this));
		$(this).parent().parent().next().children("div").css("display","none");
		$(this).parent().parent().next().children("div").eq(temp_id).css("display","block");
		
	})
}

function common_tab_2(obj){//通用tab，点击效果，有缓冲效果
	$(obj).click(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var temp_id = $(this).parent().children("li").index($(this));
		$(this).parent().parent().next().children("div").hide();
		$(this).parent().parent().next().children("div").eq(temp_id).fadeIn(500);
	})
}

function common_tab_has_scroll(){//点击,内容滚动的TAB
	$(".common-tab-mouseover-hoverpanel .tab-caption ul li").mouseover(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var temp_id = $(this).parent().children().index($(this));
		var top_y= temp_id*(-160);
		$(this).parent().parent().next().children(".div-container").animate({marginTop:top_y},500);
	})
}

//通用提示start
function common_tips(){


	  //$(".common-tips").each(function(){
			//var til=$(this).find(".tips-title");
			//var ct=$(this).find(".tips-content");
			$(".common-tips .tips-title").hover(
				function(){
					//alert(ct.length);
					var ct=$(this).parent().find(".tips-content");
					ct.css("visibility","hidden");
					ct.css("display","block");;
					
					$(this).mousemove(function(e){
						if(e.clientY<400){
							ct.css({"left":e.pageX+10,"top":e.pageY-10,"display":"block"});
						}
						else{
							//ct.css({"left":e.pageX+10,"top":e.pageY-200,"display":"block"});
							ct.css({"left":e.pageX+10,"top":e.pageY-10,"display":"block"});
						}
					})
				  //alert(ct.height());
				  var h=ct.height();
				  ct.height(h);
				  setTimeout(function(){ct.css("visibility","visible")},200)
				},
				function(){
				  var ct=$(this).parent().find(".tips-content");
				  var animate_timer=setTimeout(function(){ct.hide()},1);
				  ct.hover(
					function(){
					  clearTimeout(animate_timer);
					},
					function(){
					  ct.hide("fast")
					}
				  )
				}
			)
	  //})
	  


}
//通用提示end

function common_select(){//通用模拟下拉select
	$(".common-select .input").click(function(e){
		e.stopPropagation?e.stopPropagation():e.cancelBubble=true;			
		if($(this).next().find("ul li").length>7){
			$(this).next(".option").css("height",125+"px");
		}
		$(".common-select .option").hide();
		$(this).next().slideToggle(100);
	})
	$(".common-select .option li").click(function(){
		var temp_value=$(this).children().html();
		$(this).parent().parent().parent().find(".text").html(temp_value);
		$(this).parent().parent().hide();
	})
	$(document).click(function(){
		$(".common-select .option").hide(0);
	})
}

//announce start
function about_announce(){//滚动公告
    $("#previous").click(function(){
        sPrevious();
        $("#play-pause").removeClass("btn-pause");
    })
    $("#next").click(function(){
        sNext();
        $("#play-pause").removeClass("btn-pause");
    })
    $("#play-pause").click(function(){
        $(this).addClass("btn-pause");
        pausePlay();
     })
}
var count=0
var index = 0;
var t;
var tDelay;
var textScroll = '';
var links = '';
var scrollList = new Array();
function timedCount()
{
    if(index == links.length) index = 0;
    textScroll = scrollList[index];
    objScroller = $('#scroll-anchor')[0];
    objScroller.innerHTML = textScroll ;//textScroll.substring(0,count);
	try{
    objScroller.href = links[index].href ;
    }catch(e){
    //怪异，不知道为什么报 links[index] is undefined 的错，但不影响使用，这里给catch一下
    }
    $(objScroller).css({position:"absolute",top:"-20px"});
    $(objScroller).animate({top:"0"}, "2000");
     try{    
        hbxName = links[index].name;
        hbxName = hbxName.substring(hbxName.indexOf('=')+1,hbxName.length);
     }
    catch(e){hbxName = '';}
    hbxName = 'javascript:_hbLink("'+hbxName+'");';
    objScroller.onclick =  new Function(hbxName);
/*     count=count+1;
     if(count > textScroll.length+1){
        waitTime(2);
        count = 0;
        if( index > scrollList.length-1)
        index = 0;
        tDelay = setTimeout("cancelDelay()",3000)
        return;
    } */
    //t=setTimeout("timedCount()",5000); 
        waitTime(2);
        count = 0;
        if( index > scrollList.length-1)
        index = 0;
        tDelay = setTimeout("cancelDelay()",3000)
        return;}
 
function sNext(){
    stopCount();
    index++;
    if( index > scrollList.length-1)
        index = 0; 
    count = 0;
    timedCount();
}
function sPrevious(){
    stopCount();
    index--;
    if( index == -1)
    index = scrollList.length-1;
    count = 0;
    
    timedCount();
}
var date;
function waitTime(mills)
{
    stopCount();
    date = new Date();
    date.setMinutes(mills + date.getMinutes());

}
function cancelDelay()
{
    
    var curDate = new Date();
    if(curDate <= date)
    {
        clearTimeout(tDelay);
        t=setTimeout("timedCount()",50);
        index = index +1;
    }
    else
    {
        tDelay = setTimeout("cancelDelay()",3000)
    }
}

function stopCount()
{
    clearTimeout(t);
    clearTimeout(tDelay);
}

function pausePlay()
{
    stopCount();
}

function startScroller()
{
    links = $("#scrollerLinks a");
    for(var i = 0;i < links.length ;i++)
    {
        scrollList[scrollList.length] = links[i].innerHTML;
    }

    if(scrollList.length > 0)
        timedCount();
}
function seturl()
{
    var obj = document.getElementById('verizon_prods')
    _hbLink(obj.options[obj.selectedIndex].getAttribute('hbx'));
    document.location.href = obj.options[obj.selectedIndex].getAttribute('url')

}
//announce end


function accordion(){//层叠面板
	$(".accordion h1").eq(0).addClass("on");
	$(".accordion .content").eq(0).css("display","block");//默认第一个为显示
	var temp_num=$(".accordion h1").length;//计算模块个数
	var temp_height=$(".accordion").height()-1;//获取整个面板高度
	var temp_content=temp_height-(temp_num*29)-13;
	$(".accordion .content").height(temp_content);
		
	$(".accordion h1").click(function(){
		if($(this).hasClass("on")){
			$(this).next().hide();
			$(this).removeClass("on");
			$(".accordion").height("auto");
			return;
		}				  									  
		$(".accordion h1").removeClass("on");
		$(this).toggleClass("on"); 
		
		$(".accordion .content").hide();
		$(this).next().show();
	})
}

//滚动图片start
function advFlash(x,t){
	var t =8000;
	var _t=this;
	var i=0;
	var interAuto;
	var len;
	_t.auto=function(t){
		i=$(".scrolls-nums ul li").index($(".scrolls-nums ul li.on")[0]);
		//alert(i);
		interAuto=setInterval(function(){
			_t.showjQueryFlash(i);
			_t.switchNum(i);
			i++;
			if(i>(len-1)){i=0}
		}
		,parseInt(t));
	}
	this.switchNum=function(i){
		$(".scrolls-nums ul li.on").removeClass("on");
		$(".scrolls-nums ul li").eq(i).addClass("on");
	}
	this.clickchange=function(){
		clearInterval(interAuto);
		$(this).addClass("on").siblings().removeClass("on");
		i=$(".scrolls-nums ul li").index($(".scrolls-nums ul li.on")[0]);
		_t.showjQueryFlash(i);
	}
	this.showjQueryFlash=function(i){
		var tmpli=$(".scrolls-nums ul li").eq(0);
		$(".scrolls .scrolls-imgs li").eq(i).animate({opacity: 1},1000).css({"z-index": "1"}).siblings().animate({opacity: 0},1000).css({"z-index": "0"});
		//$("#flow").animate({ right: i*tmpli.width()+8+10 +"px"}, 700 ); //滑块滑动	
	}
	_t.init=function(t){
		var _ul=$("<ul class=\"scrolls-imgs\"></ul>");
		var _ul2=$("<div class=\"scrolls-nums\"><ul></ul></div>");
		for(var y=0;y<imgary.length;y++){
			//var img=$(imgary[y]);
			var tmp="<a target=\"blank\" href=\""+imgurl[y]+"\"><img src=\""+imgary[y]+"\" /></a>";
			//var tmp=url.append(img)
			if(y==0){
				var _li="<li class=\"first\">"+tmp+"</li>";
				var _li2="<li class=\"on\">"+title[y]+"</li>";
			}
			else{
				var _li="<li>"+tmp+"</li>";
				var _li2="<li>"+title[y]+"</li>";
			}
			_ul.append(_li);			
			_ul2.find("ul").append(_li2);
			
		}
		var advflash=$("<div style=\"width:100%; height:100%;\" class=\"scrolls\"></div>");
		advflash.append(_ul).append(_ul2);
		$(x).append(advflash);
		//alert(_ul.html());
		len=$(".scrolls .scrolls-imgs li").length;
		_t.auto(t);
		$(".scrolls .scrolls-imgs li img").hover(
			function(){
				clearInterval(interAuto);
			},
			function(){
				_t.auto(t);
			}
		);
		$(".scrolls-nums ul li").bind("click",_t.clickchange);
	}
	_t.init(t);
}
//滚动图片end

//show-area-2 start
function about_show_area_2(){//show-area-2相关效果
	common_tab_2(".show-area-2 .tab-caption ul li");//通用TAB,带缓冲效果
	$(".show-area-2 .title-common-3 .more-link").click(function(){
		$(this).parent().next().children(".tab-content").slideToggle(500);
		if($(this).html()=='&gt;收起'){
			$(this).html("&gt;展开");
		}
		else{
		$(this).html("&gt;收起");
		}
	})
}
//show-area-2 end

function about_icon_box(){//切换样式
	$(".icon-box").click(function(){
		$(".icon-box").removeClass("icon-box-select");
		$(this).toggleClass("icon-box-select");
		//2012-03-06 phone's color choice
		$("#productColorDisPlay").text($(this).text());
	})
}

//切换号码start
function customer_panel_effect(){//用户选择号码，切换当前号码
	$(".customer-panel .tab-user").click(function(){
		$(".customer-panel .pop").show();
		category_hide();
	})
	$(".customer-panel .close-btn").click(function(){
		$(".customer-panel .pop").slideUp("fast");
		category_show()
	})
	$(document).click(function(){
		$(".customer-panel .pop").slideUp("middle");
		category_show()
	})
	$(".customer-panel").click(function(e){
		e=e||window.event;
		e.stopPropagation?e.stopPropagation():e.cancelBubble=true;	
	})
	$(".customer-panel tr").click(function(){
		
		var temp_id = $(this).parent().children().index($(this));
		if(temp_id!="0"){
			$(".customer-panel .pop").slideUp("fast");
			$(this).children().children("input:radio").attr("checked","checked");
			var temp_number=$(this).children().children("span").eq(0).html();
			$(".customer-panel .current-number").html(temp_number);
			category_show();
		}
	})
}
function category_show(){
		if($(".mcdropdown").length>0){
			$(".mcdropdown").show();
		}
}
function category_hide(){
		if($(".mcdropdown").length>0){
			$(".mcdropdown").hide();	
		}									  
}
//切换号码end


//自助服务，我的产品信息弹出start
function selfservice_form_pop_panel(){//自助服务，我的产品信息弹出start
	$(".selfservice-form .show-selfservice-form-pop-panel").click(function(){
		$(".on .selfservice-form-pop-panel").slideDown(500);
	})
	$(".selfservice-form-pop-panel .hide-selfservice-form-pop-panel").click(function(){
		$(".on .selfservice-form-pop-panel").slideUp(500);
	})
}
//自助服务，我的产品信息弹出end

//权限标签start
function hide_tab(ary){//指定哪个TAB为隐藏并将其删除，需要延时执行。不可以直接执行
		//$(".flag_common_tab .tab-caption #"+ary).hide();
		//$(".flag_common_tab .tab-content #"+ary+"content").hide();
		$(".tab-caption #"+ary).hide();
		$(".tab-content #"+ary+"content").hide();
		$(".new-tab-caption #"+ary).hide();
		$(".new-tab-content #"+ary+"content").hide();
}
/*function flag_common_tab(id){//此函数与后台发生交互，通过后台返回的变量，判断哪个TAB高亮
	var temp_id=$("#"+id).parent().children("li").index($("#"+id));
	
	$(".flag_common_tab .tab-nav ul>li").removeClass("on");
	$(".flag_common_tab .tab-content>.box").removeClass("on");

	$(".flag_common_tab .tab-nav ul>li").eq(temp_id).addClass("on");
	$(".flag_common_tab .tab-content>.box").eq(temp_id).addClass("on");
}*/
function flag_common_tab(id){//此函数与后台发生交互，通过后台返回的变量，判断哪个TAB高亮，适用tab里嵌套tab
				var temp_id=$("#"+id).parent().children("li").index($("#"+id));
				$("#"+id).parent().find(".on").removeClass("on");
				$("#"+id).addClass("on");
				//var temp_id = $(this).parent().children("li").index($(this));
				$("#"+id).parent().parent().next().children("div").removeClass("on");
				$("#"+id).parent().parent().next().children("div").eq(temp_id).addClass("on");
			}
function flag_common_tab_2(id){//此函数与后台发生交互，通过后台返回的变量，判断哪个TAB高亮，适用单层tab
				var temp_id=$("#"+id).parent().children("li").index($("#"+id));
				$("#"+id).parent().find(".on").removeClass("on");
				$("#"+id).addClass("on");
				//var temp_id = $(this).parent().children("li").index($(this));
				$("#"+id).parent().next().children("div").removeClass("on");
				$("#"+id).parent().next().children("div").eq(temp_id).addClass("on");
			}
//权限标签end

//费用查询二次确认start
function start_confirm_second(){
	$(".confirm-second").hide(0,function(){$(".detail-bill").show()});
}
//费用查询二次确认end

//通用竖向tab，点击效果start
function common_tab_vertical(){//通用竖向tab，点击效果
	$(".selfservice-tab-vertical .tab-caption-vertical ul li").click(function(){
		$(this).parent().find(".on1").removeClass("on1");
		$(this).addClass("on1");
		var temp_id = $(this).parent().children().index($(this));
		$(this).parent().parent().next().children("div").removeClass("on1");
		$(this).parent().parent().next().children("div").eq(temp_id).addClass("on1");
		
	})
}
//通用竖向tab，点击效果end

//弹出等待层start

function open_pop_waiting(){
	//写入弹出层 
		var temp_html='<div class="pop-waiting" ><a href="javascript:void(0);" class="close-pop-waiting" title="关闭"></a> </div>';
		$("body").append(temp_html);
		
	$.blockUI({
           message:$('.pop-waiting'),       /* 信息框中的内容 */
        	css : {
                border: 'none',
                background: 'none',
				cursor:'default'
			}, /* 框内样式  */
            overlayCSS: {cursor:'default',backgroundColor: '#000',border:'none'}/* 框外样式  */
		}		  
	);
	//自动关闭弹出层 
	//setTimeout(close_pop_waiting(),4000)
	$(".close-pop-waiting").click(function(){//点击关闭按钮取消弹出
		shut_all_unblockUI();
	})
	
	$(".blockOverlay").click(function(){//点击蒙版取消弹出
		shut_all_unblockUI();
	})
	
}
	
//弹出等待层end
//弹出等待层start

function open_pop_ad(){
	//写入弹出层 
		var temp_html='<div class="pop-waiting-ad" > <img src="nresources/ads/pop-ad.jpg" /><div class="close-pop-waiting"><a href="javascript:void(0);"  title="关闭"><img src="nresources/ads/pop-close.gif" /></a></div> </div>';
		$("body").append(temp_html);
		
	$.blockUI({
           message:$('.pop-waiting-ad'),       /* 信息框中的内容 */
        	css : {
                border: 'none',
                background: 'none',
				cursor:'default'
			}, /* 框内样式  */
            overlayCSS: {cursor:'default',backgroundColor: '#000',border:'none'}/* 框外样式  */
		}		  
	);
	//自动关闭弹出层 
	//setTimeout(close_pop_waiting(),4000)
	$(".close-pop-waiting").click(function(){//点击关闭按钮取消弹出
		shut_all_unblockUI();
	})
	
	$(".blockOverlay").click(function(){//点击蒙版取消弹出
		shut_all_unblockUI();
	})
	
}
	
function shut_all_unblockUI(){
	$.unblockUI();
}
//弹出等待层end

//投票start
function about_vote(idNum,percentNum,colorType){
	$("#"+idNum).css("width",percentNum*2+40+"px").attr("title",percentNum+"%");
	$("#"+idNum).children("p").css("width",percentNum*2+"px").attr("class",colorType);
	$("#"+idNum).children("span").html(percentNum+'%');
}
//投票end

//常见问题解答start
function about_q_and_a(){
	$(".q-a .question").click(function(){
		$(this).next().toggle(0);
	})
}
//常见问题解答end

//常用下载start
function about_daily_download(){//常见下载相关函数
	$(".daily-download .link-detail").click(function(){
		$(this).toggleClass("up");
		$(this).parent().next().toggle();
	})
}
//常用下载end

//判断主导航中哪个导航为高亮start
function about_menu(){//判断主导航中哪个导航为高亮
	var temp_id=$("#about_menu").attr("value");
	//var obj=$(".nav-panel > ul > li").eq(temp_id);
	var obj=$(".nav > ul > li").eq(temp_id);
	//$(".menu > ul > li").removeClass("on");
	//$(".menu > ul > li").removeClass("tmpon");
	$(".nav > ul > li").removeClass("on");
	$(".nav > ul > li").removeClass("tmpon");
	$(obj).addClass("on");
	$(obj).addClass("tmpon");
}
//判断主导航中哪个导航为高亮end


//加入收藏start----update 2012-03-13 by kevin
function about_collect(){//加入收藏
	$("div.tab-content-4 dl dd").hover(function(){
		$(this).children("a.btn-collect-2").css("float","right").show();
	},function(){
		$(this).children("a.btn-collect-2").hide();	
	})	
	$("div.show-tab-4-3 div.tab-caption-2 ul li").hover(function(){
		$(this).find("span.favorite").show();
	},function(){
		$(this).find("span.favorite").hide();	
	})	
}
//加入收藏end

//判断左侧菜单高亮start
/*function about_menu_4(e){//判断左侧菜单高亮
	$(".menu-4 li").removeClass("on");
	$(".menu-4 li").eq(e-1).addClass("on");
}*/
//判断左侧菜单高亮end


//show-area-3相关函数 start
function about_show_area_3(){//show-area-3相关函数
	$(".show-area-3 .more-link").click(function(){
		if($(this).html()=="&gt;展开"){
			$(this).html("&gt;收起");
			$(this).parent().next().slideDown();
		}
		else{
			$(this).html("&gt;展开");
			$(this).parent().next().slideUp();
		}
	})
}
//show-area-3相关函数 end

//switch tab start
function switch_tab(){//组合TAB
	$(".switch-tab li").live("click",function(){
		if($(this).attr("class")!="disabled"){									  
											  
			if($(this).hasClass("on")){
			$(".switch-tab .on").removeClass("on");
			$(".switch-tab-content .box.on").removeClass("on");
		}
		else{
			var tbIndex=$(this).parent().find("li").index($(this)[0]);
			var ulIndex=$(".switch-tab").index($(this).parent()[0]);
			$(".switch-tab .on").removeClass("on");
			$(this).addClass("on");
			$(".switch-tab-content .box.on").removeClass("on");
			//$("."+$(this).parent().attr("ctTarget")).find(".box").eq(tbIndex).addClass("on");
			$("#"+$(this).attr("tar")).addClass("on");
			
			var _clone=$(this).parent().clone();
			$(".switch-tab:last").after(_clone);
			$(this).parent().remove();
			showFAV();
		}
	
		}
	})
}
function about_switch_tab_default(e){//让哪个li高亮
	$(".switch-tab li").removeClass("on");
	var aa=".switch-tab li[tar="+e+"]";
	$(aa).click();
}
//switch tab end
//判断公告层是否显示start
function show_hide_announce(e){//0为隐藏，非0为显示
	if(e=="0"){
		$(".announce").hide();
	}
	else{
		 $(".announce").show();
		 $(".nav-panel").css("margin-bottom","0");
	}
}
//判断公告层是否显示end

//主导航菜单下拉时隐藏下拉列表start
function package_for_select(){//主导航
	$(".nav-panel li").hover(function(){
		$(this).children("iframe").show();
	},function(){
		$(this).children("iframe").hide();
	})
}
function write_active_package_for_select(){//写入JS代码
	var temp_code='<!--[if lte IE 6]>';
	temp_code+='<script type="text/javascript">';
	temp_code+='package_for_select();';
	temp_code+='</script>';
	temp_code+='<![endif]-->';
	$("html").append(temp_code);
}
//主导航菜单下拉时隐藏下拉列表end
//showiframe-Start
/*function addclass(){
	$(".mypanel").parent().addClass("showpanel");
}*/
function showiframe(){
	$(".mypanel").css("left","240px");
	$(".mypanel").css("top","210px");
	$(".mypanel").addClass("show-on");
}
function disshow(){
	$(".mypanel").removeClass("show-on");
}

$(function logintab(){
	var $li=$(".test li");
	$li.click(function(){
	$(this).addClass("on").siblings().removeClass("on");
	var index=$li.index(this);
		
	$(".tab_content > div.tab_box").eq(index).show().siblings().hide();
		})
	})
	
	$(function logt_inner(){
		$li_in=$(".show-tab-inner ul.tab-caption-inner li");
	$li_in.click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var index_inner=$li_in.index(this);
	    $(".tab-content-inner > div").eq(index_inner).show().siblings().hide();
		})	
		
		
		})
	$(function logintab(){
	var $li=$(".test li");
	$li.click(function(){
	$(this).addClass("on").siblings().removeClass("on");
	var index=$li.index(this);
		
	$(".tab_content-3 > div.tab_box").eq(index).show().siblings().hide();
		})
	})
	
	$(function logt_inner(){
		$li_in=$(".show-tab-inner ul.tab-caption-inner li");
	$li_in.click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var index_inner=$li_in.index(this);
	    $(".tab-content-inner > div").eq(index_inner).show().siblings().hide();
		})	
		
		
		})

//showiframe-End
//纵向tab-start
$(function(){
	    $(".vertical-tab .vertical-tab-nav li").click(verticaltab);
		$(".vertical-tab-2 .vertical-tab-nav li").click(verticaltab);
	    
})
function verticaltab(){
	    var obj=$(this)
	    var id=$(obj).parent().children().index($(obj));
	    $(obj).parent().next().children().addClass('on');
	    $(obj).parent().children().removeClass('on');    
	    $(obj).addClass('on');
	    $(obj).parent().parent().next().children().removeClass('on');
	    $(obj).parent().parent().next().children().eq(id).addClass('on');
	    
}
//关闭弹出层
function parent_tb_remove() {	
	var p=$(window.parent.document);
	p.find("#TB_imageOff").unbind("click");
	p.find("#TB_closeWindowButton").unbind("click");
	p.find("#TB_window").fadeOut("fast",function(){p.find('#TB_window,#TB_overlay,#TB_HideSelect').trigger("unload").unbind().remove();});
	p.find("#TB_load").remove();
	p.find(".thickboxonshow").removeClass("thickboxonshow");
	if (typeof document.body.style.maxHeight == "undefined") {//if IE 6
		p.find("body","html").css({height: "auto", width: "auto"});
		p.find("html").css("overflow","");
	}
	p.onkeydown = "";
	p.onkeyup = "";
	return false;
}
function autoScroll(){
	var tar=$("#w1 .content .scrollContainer");
	var h=tar.find("ul").eq(0).height();
	var clone=$("#w1 .content .scrollContainer ul").clone();
	tar.append(clone);
	var ss;
	var _t=this;
	this.goscroll=function(){
		ss=setInterval(function(){
			var t=parseInt(tar.parent().scrollTop());
			if(t>=h+h-tar.parent().height()){
				t=h-tar.parent().height();
			}
	
			t+=1;
			tar.parent().scrollTop(t);
	
			
		},25)
	},
	
	$("#w1 .content").mouseover(function(){
		clearInterval(ss);
	})
	$("#w1 .content").mouseout(function(){
		_t.goscroll();
	})
	_t.goscroll();
}
/*//信息修改
$(function(){
	    $(".table-form td .more-link-4").click(textmodify);
	    
})
function textmodify(){
	var obj = $(this);
	var txt = $(obj).parent().find("span");
	if(txt.html()=='<input type="text">'){
	}else{
	var addtype = $("<input type='text'/>");//添加表单类型	
	addtype.val(txt.html());//赋值
	txt.html("");//清空td
	addtype.appendTo(txt);//插入表单元素
	}
}*/
$(function(){
	    $(".showprotocol").click(showprotocol);
	    
})
function showprotocol(){
	$(".protocol-panel").toggle();
}
//滚动~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
(function($){
$.fn.extend({
   Scroll:function(opt,callback){
    //参数初始化
    if(!opt) var opt={};
    var _this=this.eq(0).find("ul:first");
    var lineH=_this.find("li:first").height(), //获取行高
     line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
     speed=opt.speed?parseInt(opt.speed,10):500, //卷动速度，数值越大，速度越慢（毫秒）
     timer=opt.timer?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
     if(line==0) line=1;
     var upHeight=0-line*lineH;
     //滚动函数
     scrollUp=function(){
      _this.animate({
       marginTop:upHeight
      },speed,function(){
       for(i=1;i<=line;i++){
        _this.find("li:first").appendTo(_this);
       }
       _this.css({marginTop:0});
      });
     }
     //鼠标事件绑定
     var timerID;
     timerID=setInterval("scrollUp()",timer);
     _this.mouseover(function(){
      clearInterval(timerID);   
     }).mouseout(function(){
      timerID=setInterval("scrollUp()",timer);
     });
   }
})
})(jQuery);


//tab change
$(function(){
	$(".new-tab-caption ul li").each(function(index) {
        $(this).click(function(){
			$(this).addClass('on').siblings().removeClass('on');
			$("div.new-tab-content>div").eq(index).addClass('box-show').siblings().removeClass('box-show');
			//$(".new-box"+index).addClass('box-show').siblings().removeClass('box-show');
		})
    });	   
})

$(function(){
	$(".table-window td").hover(
		function(){
			$(this).find(".window").show();
			$()	
				},
		function(){
				$(this).find(".window").hide();
			})
		})

//tjpage/biz/main/商务领航 tab change && 20111212 by Kevin
$(function(){
	var tabCon = $("div.new-wrap>div.new-wrap-box"),
		tabLabel = $("div.new-tab-caption-1>ul>li"),
		tabLabelW = 0,
		$kv = $kh = 0;
	
	for(var i=0; i<tabLabel.length; i++){
		tabLabelW += tabLabel.eq(i).outerWidth(true);
		if(tabLabelW>680){
			//alert(i);
			tabLabel.eq(i).hide();
			$("div.new-tab-1 .new-tab-caption-1 .next-btnDis").attr("class", "next-btn");
		}
 	}
	
	tabLabel.bind("click", function(){
		var $index = $(this).index();
		$(this).addClass("on").siblings().removeClass("on");
		tabCon.eq($index).addClass("show").siblings().removeClass("show");
	})
	
	$kv = $("div.new-tab-caption-1>ul>li:visible").index();
	$kh = $("div.new-tab-caption-1>ul>li:hidden").index();
	$("div.new-tab-caption-1>span").bind("click", function(){
		var $class = $(this).attr("class");
		switch($class){
			case "pre-btnDis" ://leftDis
				break;
			case "next-btnDis" ://rightDis
				break;
			case "pre-btn" ://left
				$("div.new-tab-caption-1>span").eq(1).attr("class","next-btn");
				tabLabel.eq($kv).show();
				tabLabel.eq($kh).hide();
				$kv--;
				$kh--;
				if($kv <= 0){
					//console.log("pre-btnDis");
					$(this).attr("class", "pre-btnDis");
					$kh = $("div.new-tab-caption-1>ul>li:hidden").index();
					$kv = 0;
				};
				break;
			default ://right
				$("div.new-tab-caption-1>span").eq(0).attr("class","pre-btn");
				tabLabel.eq($kh).show();
				tabLabel.eq($kv).hide();
				$kv++;
				$kh++;
				if($kh >= tabLabel.length){
					$(this).attr("class","next-btnDis");
					$kh=tabLabel.length-1;
					$kv--;
				}
				break;
		}
	})
})

//点击输入框，验证码放大
$(function(){
	$(".yzm").focus(
	 function(){
		$("#code img").addClass("img-enlarge") 
		 })	
	$(".yzm").blur(
		function(){
		$("#code img").removeClass("img-enlarge") 	
			})
		   });


$(function(){
	$(".tabshow .tabcaption a").mouseover(
		function(){
			$(this).addClass("on").siblings().removeClass("on");
			var a_index=$(this).index();
			$(this).parents(".tabcaption").next(".tabcont").find(".tabbox").eq(a_index).show().siblings().hide();
			}								  
										  )	   
		   })
