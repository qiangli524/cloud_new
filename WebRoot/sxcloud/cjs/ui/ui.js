
$(function(){
	$(".nav-panel .layout").hide();
	$(".tree .root li a").live("click",function(){var t=this;ui.tabs.add(t)});
	//$(".overflowXhidden.r02.content dl dd a").live("click",function(){var t=this;ui.tabs.add(t)});
	$(".popContent table td a").live("click",function(){var t=this;ui.tabs.add(t)});
	$("#w4 .content dl dd ul li a").live("click",function(){var t=this;ui.tabs.add(t)});
	$(".nav li a").live("click",function(){if($(this).hasClass("homepage")){return}var t=this;ui.tabs.add(t)});
	$(".quick .quick-ct li a").live("click",function(){
		var t=this;ui.tabs.add(t)
	});
	$("#alarmNum").live("click",function(){
		var t=this;ui.tabs.add(t)
	});
	
	//bs²å¼þÎÊµÄÊ×Ò³Ñ¡Ôñ
	if($("#showBS").length>0){
		$(".nav li").eq(0).find("a").click();
	}
	
	init_autosize();
	$(window).resize(init_autosize);
	$(window).bind("resize",treetableAutosize);
	quick_lunch();
	quickAutosize("");
	delMyFav();
	table_autosize();
	sort_table();
	
	if($(".frame-ct .tabtil").length>0){
		$(".tabtil li").each(function(i){
			if(i=1) return;
			r_menu($(this));
		})
	}
	$(".tree .node.on > ul").show();
	$(".tree .node span").click(ui.tree.init);
	$(".folder-tree .node span").click(ui.tree.init);
	closeIframeTab();

	
	UI_tabs(".query .links",".scrollbody");
	UI_tabs("#w5 .tabtil","#w5 .content");
	UI_tabs("#w3 .tabtil","#w3 .content");
	$(".query .links li").bind("click",fixiescrollbug);
	
	var old_s_w=$(".sidebar").width();
	var old_s_h=$(".sidebar").height();
	var old_r_w=$(".frame-ct").width();
	var no_top_h=$(window).height()-$(".nav-panel").height()-6;
	
	
	$(".layout img").click(function(){
		var x=$(this).parent().parent().children().index($(this).parent()[0]);
		
		switch(x){
			case 0:
				$(".sidebar").hide();
				$(".head").height(53).show();
				$(".frame-ct").width("100%");
				init_autosize();
				break;
			case 1:
				$(".sidebar").show();
				$(".head").height(0).hide();
				$(".frame-ct").width("83%");
				init_autosize();
				break;
			case 2:
				$(".sidebar").hide();
				$(".head").height(0).hide();
				$(".frame-ct").width("100%");
				init_autosize();
				break;
			case 3:
				$(".sidebar").show();
				$(".head").height(53).show();
				$(".frame-ct").width("83%");
				init_autosize();
				break;
			default:
				return
		}
		
	})
	
	$(".tree .node span").hover(
		function(){
			$(this).addClass("yellow");
		},
		function(){
			$(this).removeClass("yellow");
		}
	)
	
	$(".query .title").click(function(){
		$("#nairong").val($("*").html());
		$(this).parent().next(" .box").find(".query-form table.querytable").toggle();
		if($(this).attr("src")=="sxcloud/cresources/default/images/icon02.gif"){$(this).attr("src","sxcloud/cresources/default/images/icon03.gif")}
		else $(this).attr("src","sxcloud/cresources/default/images/icon02.gif")
	})
	$(".hide img").click(function(){
		var tar=$(this).parent().parent().next();
		if(tar.css("display")=="none"){
			$(this).attr("src","sxcloud/cresources/default/images/icon02.gif");
			tar.slideDown("fast");
		}
		else{
			$(this).attr("src","sxcloud/cresources/default/images/icon03.gif");
			tar.slideUp("fast");
		}
		
	})
	$(".tree .node > ul").css("margin-top","7px");
	
	if($(".flashmap").length>0){
		var flash_w=$(".left-col").width();
		var flash_h=$(".left-col").height();
		flash_w>572?flash_w=($(".left-col").width()-25):flash_w=572;
		flash_h>580?flash_h=($(".left-col").height()-35):flash_h=580;
		$(".flashmap").attr("width",flash_w+"px");
		$(".flashmap").attr("height",flash_h+"px");
		$(".map").width(flash_w);
		var mapH=$(".col-1").height()-36;
		$(".map").css("height",mapH>580?mapH:580+"px");
		$(".map").parent().css("overflow-y","auto").css("overflow-x","auto");
	}
	
	if($(".sidebar").length>0) accordion(".UI_accordion");
	
	$(".nav li").hover(
		function(){$(this).addClass("onhover")},
		function(){$(this).removeClass("onhover")}
	);
	$(".nav li").click(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
	})
	$(".alarm a").click(function(){
		$(this).blur();
	});
	$(".jsw").each(function(){
		var w=$(this).attr("jsw");
		$(this).css("width",w+"px");
	})

	$(".blue-table tr").live("dblclick",function(){
		//$(this).parents(".blue-table").find(".bg").removeClass("bg");
		//$(this).parent().addClass("bg");
		if($(this).hasClass("bg")){
			$(this).removeClass("bg");					
		}else{
			$(this).addClass("bg");
		}
	})
})


function quickAutosize(str){
	
	$(".quick .quick-ct").each(function(){
		if($(this).find("li").length<=0){return}
		$(this).find("li").each(function(){
			$(this).css("width","auto");
		})
		$(this).css("visibility","hidden").show().width(800);
		
		if(str == ""){
			var tempWidth = $(this).find("li").eq(0).find("a")[0].clientWidth;
		}else{
			var tempWidth = str.length + 170;
		}

		$(this).width(tempWidth+80);
		$(this).hide().css("visibility","visible");
		if($(this).find("li").length<=0){$(this).width($(this).parent().find("input").eq(0).width()).height(5)}
		var ctwidth=tempWidth+80;
		$(this).find("li").each(function(){
			$(this).css("width","auto");
			$(this).width(ctwidth-10);
		})
		var totalH=$(this).find("li").length*24;
		if(totalH>400){
			$(this).height(400);
			$(this).css("overflow","auto");
			var w=$(this).width()+38;
			$(this).width(w)
		}
		else{
			$(this).height(totalH);
		}
		
		
	})
}
function quick_lunch(){
	$(".quick .quick-til").click(function(e){
		e.stopPropagation?e.stopPropagation():e.cancelBubble=true;
		var ct=$(this).parent().find(".quick-ct");
		if(ct.css("display")!="block"){
			ct.slideDown("fast");
		}
		else{
			ct.slideUp("fast");
		}
	});
	$(document).click(function(){
		$(".quick-ct").slideUp("fast");
		$(window.parent.document).find(".quick-ct").slideUp("fast");
		$(window.parent.document).find("#jqContextMenu").hide().next().hide();
	})
}
function delMyFav(){
	$(".quick-ct").each(function(){
		$(this).find("li").each(function(){
			$(this).find(" .close").click(function(e){
				e.stopPropagation?e.stopPropagation():e.cancelBubble=true;							
				var delHeight=$(this).parent().parent().height()-24;
				$(this).parent().parent().height(delHeight);
				$(this).parent().remove();
			  	$.ajax({
					type:"POST",
					url:"delShortcutConfig.do",	
					data:"FUNCID=" + $(this).parent().find("a").attr("mid"),
					success: function(msg){
						alert("ÒÑ³É¹¦É¾³ýÊÕ²Ø£¡");
				}
				}
				);
			})
		})
	})
		
}
function r_menu(t){
		t=$(t)
		t.contextMenu("widget_menu",
		{
			bindings:{
				'favorite': function(t) {			  
				  if($(".login .quick-ct li a[mid='"+$(t).attr("mid")+"']").length>0){
				  	alert("´ËÊÕ²ØÒÑ´æÔÚ");
					return
				  }
				  $(".login .quick-ct").append("<li><a href=\"javascript:void(0)\" treeurl=\""+$(t).attr("treeurl")+"\" turl=\""+$(t).attr("tabhref")+"\" where=\""+$(t).attr("where")+"\" mid="+$(t).attr("mid")+">"+$(t).find("span").html()+"</a><div class=\"close\"><img src=\"sxcloud/cresources/default/images/tabs-nav-close.gif\" title=\"É¾³ý\" /></div></li>");

				  var mould_id=$(t).attr("mid");
				  	$.ajax({
						type:"POST",
						url:"user_userShortcutConfig.do",	
						data:"FUNCSID=" + mould_id,
						success: function(msg){
							
							alert("ÒÑ³É¹¦ÊÕ²Øµ½ÓÒÉÏ½ÇµÄ¿ìËÙÈë¿Ú£¡");
							quickAutosize($(t).find("span").html());
							delMyFav();
					}
					}
					);
				  
				},
				'close': function(t) {
					if($(t).parent().find("> li").length<2) return;
					var index=$(t).parent().find("> li").index($(t)[0]);
					if($(t).hasClass("on")){
						$(t).prev().addClass("on");
						$(t).parent().next().find(".box").eq(index).prev().addClass("on");
						$(t).parent().next().find(".box").eq(index).remove();
						$(t).remove();
					}
					else{
						$(t).parent().next().find(".box").eq(index).remove();
						$(t).remove();
					}
				},
				'close_others':function(t){
					var lis=$(t).parent().find("li");
					var boxs=$(t).parent().next().find(".box");
					lis.each(function(i){
						if($(t).parent().find("> li").length<2) return;
						//else if(lis.eq(i).hasClass("on")){
						if(lis.eq(i).hasClass("on")){
							return;
						}
						else{
							lis.eq(i).remove();
							boxs.eq(i).remove();
						}
					})
					
				}
			}
		}
	)
	
}

function init_autosize(){
	$(".wrap").css("height",$(window).height()-$(".head").height()-$(".nav-panel").height()-4+"px");
	//alert($(".sidebar").width());
	//$(".frame-ct").width($(".wrap").width()-$(".sidebar").width()-1);
	
	
	if($(".tystnone").length>0){
		$(".frame-ct").width($(".wrap").width())
	}
	else{
		if($(".sidebar").css("display")!="none"){
			$(".frame-ct").width($(".wrap").width()-$(".sidebar").width()-1)
		}
		
	}
	
	if($(".map-col").length>0){
		$(".right-col").css("width",$(".wrap").width()-$(".map-col").width()-8)
	}

	
	if($(".row01").length>0){
		var r01_h=$(".right-col").height()-$(".row02").height()-$(".row03").height()-24;
		if(r01_h<173) r01_h=173;
		$(".row01").css("height",r01_h+"px");
		$(".row01").width($(".right-col").width()-5);
		$(".row01 .content").height($(".row01").height()-$(".row01 h3").height());
		$(".row02").width($(".right-col").width()-5);
		$(".row03").width($(".right-col").width()-5);
		$(".right-col")[0].scrollTop=0;
	}

	
	if($(".item-col.col-2").length>0){
		var r01=$(".item-col.col-2 .item").eq(0);
		var r02=$(".item-col.col-2 .item").eq(1);
		var r03=$(".item-col.col-2 .item").eq(2);
		var botm01,botm02,botm03;
		r01.css("margin-bottom")==null?botm01=0:botm01=parseInt(r01.css("margin-bottom"));
		r02.css("margin-bottom")==null?botm02=0:botm02=parseInt(r02.css("margin-bottom"));
		r03.css("margin-bottom")==null?botm03=0:botm03=parseInt(r03.css("margin-bottom"));
		var h=$(".wrap").height()-r02.height()-r03.height()-botm01-botm02-botm03-8;
		if(h<140) r01.find("> .content").height(117);
		else r01.find("> .content").height(h-23);
		r01.find(".content").height(r01.height()-r01.find(".caption").height()-2);
		
		if($.browser.msie && parseInt($.browser.version,10) == 6){
			r01.find(".content table").width(r01.find(".content").width()-18);
		}
		
		
		if($(".col-1 .alarm").length>0){alarmh=$(".col-1 .alarm").height()}
		else if($("#myIframe").length>0){alarmh=$("#myIframe").height()}
		if($(".sheng-left-items").length<=0){
			$("#w1 .content").height($(".wrap").height()-$("#w1 .caption").height()-$("#w3").height()-12-4-(alarmh));
		}
		else{
			$(".sheng-left-items .content").height(($(".wrap").height()-alarmh-$("#w1 .caption").height()*2-20)/2);
		}
		if($(".flashmap").length>0){
			$("#w1").height($(".item-col.col-2").height()-13-(alarmh));
		}
	}
	
	fixiescrollbug();
	ifrm_autosize();
	
		
	if($(".scrollbody").length>0){
		if($.browser.msie && parseInt($.browser.version,10) == 7 ){
			$(".query").width("98%");
			$(".query-form").width("98%");
			$(".scrollbody .blue-wrap").width("98%");
		}
		var doAutoheight=setInterval(
			function(){
				if($(window.parent.document).find(".on .ifrm").attr("loaded_h")=="true"){
					$(".scrollbody").height($(window.parent.document).find(".on .ifrm").height()-8);
					if($.browser.msie && parseInt($.browser.version,10) == 6){						
						if($(".pop-body").length>0){
							var w=$(".pop-body").width();
							$(".pop-body").width(w);
						}
						
					}				
					clearInterval(doAutoheight);				
					fixTable0Tr();
					treetableAutosize();
				}
			}
			,50
		)
		
	}
	
	
	accordion(".UI_accordion");
	if($(".alarm").length>0){
		if ($.browser.msie && parseInt($.browser.version,10) < 7){var fixIfmW=4}
		else{var fixIfmW=2}
		$(".alarm").width($(window.parent.document).find(".col-1").width()-fixIfmW);
	}
	
}

function fixTable0Tr(){
	var zeroTrTablect=$(".on .blue-wrap .table-ct");
	if(zeroTrTablect.length>0){
		if(zeroTrTablect.find(".blue-table tr").length<2){
			var sheng=$(".scrollbody").height()-$(".query").height()-10-$(".scrollbody .box.on").height();
			if(sheng>0){
				var zeroH=zeroTrTablect.height();
				zeroH+=sheng;
				zeroTrTablect.height(zeroH);
			}
		}
		
	}
}


function ifrm_autosize(){
	if($(".box.on .ifrm").length>0){
		var ifrm_h=$(window).height()-$(".head").height()-$(".nav-panel").height()-8-$(".frame-ct .tabtil").height()-$(".frame-ct .whereme").height();	
		$(".box").width($(".frame-ct").width());
		$(".box .ifrm").width($(".frame-ct").width());
		$(".box .ifrm").height(ifrm_h);
		$(".box .ifrm").attr("loaded_h","true");
	}
	

}





function table_autosize(){
	var tar=$(".table-ct");
	$(".blue-table tr").each(function(i){
		if((i+1)%2){
			$(this).addClass("odd")
		}
	})
	/*
	$(".blue-table tr").hover(
		function(){
			$(this).addClass("bg");
		},
		function(){
			$(this).removeClass("bg");
		}
	)
	*/
}
function sort_table(){
	$(".sorttable th.nobg").click(function(){
		if($(this).hasClass("bg")){return}
		else{
			if($(this).hasClass("headerSortUp")){
				$(this).removeClass("headerSortUp");
				$(this).addClass("headerSortDown");
				alert("down");
			}
			else{
				$(this).removeClass("headerSortDown");
				$(this).addClass("headerSortUp");
				alert("up");
			}
		}
	})
}

function accordion(ct){
	if($(".UI_accordion").length<=0) return;
	var sb_h=$(window).height()-$(".head").height()-$(".nav-panel").height()-4;
	var autoheight=sb_h-18-($(ct).find("dt").length*($(ct).find("dt").height()+2));
	$(ct).find("dd").css("height",autoheight+"px");
	$(ct).find("dd").width($(ct).find("dd").parent().width());
	$(ct).find("dt").click(function(){
		var click_dd=$(this).find("+dd");
		if(!(click_dd.attr("class")=="active")){
		  $(ct).find(".active").slideUp(150);
		  $(ct).find(".active").removeClass("active");
		  click_dd.slideDown(100,function(){click_dd.addClass("active");});
		}
	})
}

function UI_tabs(t, c) {
	var lis = $(t).find(">li");
	lis.live("click",function () {
		var li_index = $(this).parent().find(">li").index($(this)[0]);
		$(this).parent().find(">li.on").removeClass("on");
		$(this).addClass("on");
		var target = $(c).find(">.box");
		$(c).find(">.box.on").removeClass("on");
		target.eq(li_index).addClass("on");
	});
}

function fixiescrollbug(){
	$(".table-ct").each(function(){
		if($(this).attr("fixiebug")=="true") return;
		if($(this).height()==0) return;
		else{
			var h=$(this).height();
			$(this).height(h+17);
			$(this).attr("fixiebug","true")
		}
		
	})	
	
}

function getTree(obj,url){
	if($(".UI_accordion").length == 0){
		var url = url+"&topFlag=top";

		var str = "<div class=\"contextMenu\" id=\"widget_menu\">";
		str +="<ul>";
		str +="<li id=\"favorite\">收 藏</li>";
		str +="<li id=\"close\">关闭</li>";
		str +="<li id=\"close_others\">关闭其他页</li>";
		str +="</ul>";	
		str +="</div>";	
	}
	
	$.ajax({
	   type:"POST",
	   cache:"false",
	   url:url,
	   async:false,
	   dataType:"text",
	   success:function(msg){
   			if($(".UI_accordion").length == 0){
				
				$("#homepage").remove();
				$(".nav-panel").after(msg);
				$(".wrap-bg2").after(str);
				init_autosize();
			}else{
				
				
				$(".sidebar").html(msg);
				init_autosize();
			}
	   }
	}); 
}

function treetableAutosize(){
	$(".root").height($(".scrollbody").height()-8);
	$(".right-table").height($(".scrollbody").height()-$(".table-head").height()-22);
}

function closeIframeTab(){
	$(".frame-ct .tabtil li").each(function(){
		$(this).find("img").click(function(e){
			e.stopPropagation?e.stopPropagation():e.cancelBubble=true;
			var _li=$(this).parent();
			if(_li.parent().find("> li").length<2) return;		
			var index=_li.parent().find("> li").index(_li[0]);
			if(_li.hasClass("on")){
				if(index==0){
					_li.next().addClass("on");
					_li.parent().next().find(".box").eq(index).next().addClass("on");
				}
				else{
					_li.prev().addClass("on");
					_li.parent().next().find(".box").eq(index).prev().addClass("on");
				}

				_li.parent().next().find(".box").eq(index).remove();
				_li.remove();
			}
			else{
				_li.parent().next().find(".box").eq(index).remove();
				_li.remove();
			}
			if($(".frame-ct .tabtil li").length<=1){
				$(".frame-ct .tabtil li").find("img").hide();
			}
			$(".whereme").html($(".frame-ct .tabtil li.on").attr("where"));
		})
		
	})
}

var ui={
	tree:{
		init:function(){
			$(this).next().toggle();
			$(this).parent().toggleClass("on");
		},
		openAll:function(){
			$(".tree .node").addClass("on");

			$(".tree .node").children("span").next().show();
			
		},
		closeAll:function(){
			$(".tree .node").removeClass("on");
			$(".tree .node").children("span").next().hide();
		},
		resize:function(){
				$(".tree").height($(".tree").parent().height()-12);
				$(".tree.root").height($(".tree.root").parent().height()-$(".tree.option").height());
		}
	},
	tabs:{
		init:function(){
		    var obj=$(this)
		    var id=obj.parent().children().index(obj[0]);
		    obj.parent().children().removeClass('on');    
		    obj.addClass('on');
			$(".whereme").html(obj.parent().find(".on").attr("where"));
		    $(".tabct .box.on").removeClass('on');
		    $(".tabct .box").eq(id).addClass('on');
			
		},
		add:function(obj){
			//ÏÔÊ¾ÓÒÉÏ½ÇµÄ¿ò¼Ü±ä»¯Í¼±ê
			$(".nav-panel .layout").show();
			var text=$(obj).text();
			var text1=$(obj).text();
			if($(obj)[0].id=='alarmNum') {
				text1 = '告警管理';
			}
			var mid=$(obj).attr("mid");
			//左菜单修改--小型机监控
			if(mid == 1558){
				$(".layout img").eq(0).trigger("click");
			}else{
				$(".layout img").eq(3).trigger("click");
			}
			if($(obj).attr("menuClass") == "oneMenu" ){
					$.ajax({
						type:"POST",
						url:"ugroup_listUsergroup.do",	
						data:"MODULE_ID=" + mid,
						success: function(msg){
						text = msg;
					}
					}
					);
			}

			var target=$(obj).attr("turl");

			if($(obj).parent().parent().hasClass("nav")||$(obj).parent().parent().hasClass("quick-ct") || $(obj).parent().parent().parent().hasClass("stat-inf")||$(obj).parents().hasClass("popContent")||$(obj).parent().parent().parent().parent().hasClass("stat-inf")){
				var treeurl=$(obj).attr("treeUrl");
				getTree(obj,treeurl);
			}
			
			
			
			var lis=$(".frame-ct .tabtil li");
			var tmp=false;
			var url=$(obj).attr("ajx");
			var rfrsh=$(obj).attr("refresh");
			var where;
			
			lis.each(function(i){
				if(lis.eq(i).attr("mid")==$(obj).attr("mid")){
					tmp=true;
				}
			})

			if(tmp==true){
				lis.parent().find(".on").removeClass("on");
				$(".frame-ct .tabtil li[tabhref='"+target+"']").addClass("on");
				where=lis.parent().find(".on").attr("where");
				$(".whereme").html(where);
				
				$(".frame-ct .tabct .box.on").removeClass("on");
				$(".frame-ct .tabct .box[tabhref='"+target+"']").addClass("on");
				
				if(rfrsh=="1"){
					var src=$(".frame-ct .tabct > .on .ifrm").attr("src");
					$(".frame-ct .tabct > .on .ifrm").attr("src",src);
					return;
				}
				else{					
					return;
				}
			}
			else{
				if(lis.length>=5){
					$(".frame-ct .tabtil li").eq(0).remove();
					$(".frame-ct .tabct .box").eq(0).remove();
				}
				
				$.ajax({
				   type:"POST",
				   cache:false,
				   //async:false,
				   url:url,
				   dataType:"text",
				   success:function(msg){
						where=msg;
						$(".whereme").html(where);
						
						$(".frame-ct .tabtil li").removeClass("on");
						$(".frame-ct .tabct .box").removeClass("on");
						$(".frame-ct .tabtil").append("<li class='on' treeurl=\""+treeurl+"\" where=\""+where+"\" mid=\""+mid+"\" tabhref='"+target+"'><span>"+text1+"</span><a class='close'></a><img src=\"sxcloud/cresources/default/images/tabs-nav-close.gif\" /></li>");
						$(".frame-ct .tabct").append("<div class='box on' tabhref='"+target+"'><iframe align='top'  class='ifrm' frameborder='0' scrolling='yes' src='"+target+"'  width='100%' height='100%'  noresize ></iframe></box>");
						$(".frame-ct .tabtil li").click(ui.tabs.init);
						if($(".frame-ct .tabtil li").length<=1){
							$(".frame-ct .tabtil li").eq(0).find("img").hide();	
						}
						else if($(".frame-ct .tabtil li").length>1){
							$(".frame-ct .tabtil li").find("img").show();	
						}
						r_menu(".frame-ct .tabtil .on");
						ifrm_autosize();
						closeIframeTab()
				   }
				});
				
			}
			
			
		}
	
	}

}
