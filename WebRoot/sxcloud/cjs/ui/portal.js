$(function(){
	
	$('.widget .item-col .caption').contextMenu("widget_menu",{shadow:true});
	$(".widget .item-col").sortable({ 
			connectWith: [".widget .item-col"] ,
			handle: ".caption",
			cursor:"move",
			revert: true ,
			placeholder:'placeholder',
	
			over:function(){
				$(".placeholder").height($(".ui-sortable-helper").height()-2);
			},
			stop:function(){
	
				$('.widget .item-col .caption .max').click(model.max);
				$('.widget .item-col .caption .min').click(model.min);	 
				$('.widget .item-col .caption .close').click(model.close);	 
			}
	});

  	$('.widget .item-col .caption .max').click(model.max);
	$('.widget .item-col .caption .min').click(model.min);
	$('.widget .item-col .caption .close').click(model.close);	 
	$("#model_local_value").trigger("click");
	//$("#model_status_value").trigger("click");
	
	$(".addWidget").click(function(){$(this).next().toggle()});
	 
})  

function getlocal(){
	var local="0";

	$(".widget .col-1 .item").each(function(i){
		local+=","+$(this).attr("id")	;	
	})
	local+=";1";

	$(".widget .col-2 .item").each(function(i){
		local+=","+$(this).attr("id");	
		
	})
	return local;
}
var model={

	max:function(){
		
			$("select").hide(); 
			
			var box = $("<div id='maskbox' style='z-index:1000;position:absolute;top:0;left:0;width:100%;height:"+$(document).height()+"px;background:#efefef'></div>");
			var obj=$(this).parents(".caption").parent();
			var clone=$(obj).clone();
			$(obj).children(".content").html("");
			$(clone).css({margin:" 10px",width:"auto"});
			if($.browser.msie && parseInt($.browser.version,10) == 6){
				$(clone).children(".content").width($(window).width()-22).height($(window).height()-50).show();
				$(clone).children(".content").find("table").width($(window).width()-40)
			}
			$(clone).find(".max,.close").remove();
			$(clone).find("select").show();
			$(clone).children(".content").height($(window).height()-50);
			//$(clone).find(".max").attr("src","../../nresources/default/images/reset.gif");
			$(clone).find(".min").click(function(){
								$(obj).children(".content").html($(clone).children(".content").html());
								$("#maskbox").remove();
								$("select").show();
								
								//$(box).parent().css("height",$(window).height());
								//$(box).parent().css("overflow","auto");
												
							
							})
			$(clone).appendTo(box);
			$(box).appendTo("body");
			//$(box).parent().css("height",$(window).height());
			//$(box).parent().css("overflow","hidden");
			
			

	},
	min:function(){
			$(this).parents(".caption").next().toggle(200);
	},
	close:function(){
		$(this).parents(".item").hide();
	}
}

