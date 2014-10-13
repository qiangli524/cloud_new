(function($) {  
	$.fn.slide = function(options){
		$.fn.slide.defaults = {
			values:['3M','6M','9M','12M'],
			inputName:"step.resinfos.bandwith",
			ids:{},
			proportion:1,  //每个格子代表的长度
			totalWidth:"480"    //控件宽度
		};		
		var opts = $.extend($.fn.slide.defaults, options);		
		//初始化
		init(opts, $(this));
		
		function init(opts,current){
			var id = current.attr("id");
			opts.ids.w1 = "jslide-w1-"+id;
			opts.ids.w2 = "jslide-w2-"+id;
			opts.ids.drag = "jslide-drag-"+id;
			opts.ids.oer = "jslide-oer-"+id;
			opts.ids.val = "jslide-val-"+id;
			opts.ids.unit = "jslide-unit-"+id;
			initData(opts);
			initHtml(opts,current);
			initEvent(opts,current);
		}
		
		function initData(opts){
			//console.log("初始化数据:"+opts.xx);
		}
		
		function initHtml(opts,current){
		  var htmlSpan = "";
		  var len = opts.values.length;
		  //计算span宽度
		  opts.spanWidth = opts.totalWidth/len;
		  $(opts.values).each(function(i,ele){
			  if(i==0){
				  opts.inputValue = ele.replace(/[^0-9]/g,'');
				  opts.inputUnit= ele.replace(ele.replace(/[^0-9]/g,''),'');
			  }
			  if(i==len-1){				  
				  htmlSpan = htmlSpan +	"<span class='block last' style='width:"+opts.spanWidth+"px'><div>"+ele+"</div></span>";		
			  }else{
				  htmlSpan = htmlSpan +	"<span class='block' style='width:"+opts.spanWidth+"px'><div>"+ele+"</div></span>";						  
			  }
		  });
          var html = ""+
			"<div class='column'>"+
            	"<div class='column-w1' id="+opts.ids.w1+" style='width:"+opts.totalWidth+"'>"+
            	htmlSpan +
                "</div> "+
                "<div class='column-w1 column-w2' id='"+opts.ids.w2+"'>"+
                	"<div class='column-oer'  id='"+opts.ids.oer+"'>"+
                		htmlSpan+
                    "</div>"+
                "</div>"+
                "<a class='drag'  id='"+opts.ids.drag+"'><i></i><i></i><i></i></a>"+              	
           "</div>"+
           "<span class='mgl-15'><input type='text' class='uc-input' style='height:12px' value='"+opts.inputValue+"' id='"+opts.ids.val+"' name='"+opts.inputName+"'/></span><span id="+opts.ids.unit+"> "+opts.inputUnit+"</span> ";
          current.html(html);
		}
		//宽度计算
		var _index,_offsetLeft,_posx,_left,_base;
		var x =	$("#"+opts.ids.w1).offset();
		
		_base=opts.spanWidth-2;
		_index=0;
		//初始化长度
		$("#"+opts.ids.w2).css("width",_base+"px");
		$("#"+opts.ids.drag).css("left",_base+"px");
		if(x==null)return false;
		_offsetLeft = x.left;
		
		function initEvent(opts,current){
			$("#"+opts.ids.drag).bind("mousedown",function(e){		
				_posx = e.clientX;
				_left = _posx - _offsetLeft;
				//alert(_left)
				if(_left<=0){
				  $("#"+opts.ids.w2).css("width","0px");
				  $(this).css("left","0px");
				} 
				else if(_left<=478) {
					$("#"+opts.ids.w2).css("width",_left+"px");
					$(this).css("left",_left+"px");
				}
				else{		
					$("#"+opts.ids.w2).css("width",478+"px");
					$(this).css("left",478+"px");		
				}
				//$(this).bind("mousemove",move);
				$(document).bind({
					'mousemove':mouseMove,
					'mouseup':mouseUp
				});
				return false;
			})
			
			$("#"+opts.ids.w1+" span").bind("click",function(e){
				_index=$(this).index();		
				_posx = e.clientX;
				_left = _posx - _offsetLeft;
				if(_left> opts.spanWidth*_index){
					$("#"+opts.ids.drag).css("left",opts.spanWidth*(_index+1)-2+"px");
					$("#"+opts.ids.w2).css("width",opts.spanWidth*(_index+1)+"px");
					$(this).css("left",_left+"px");
					
					var _value= $(this).children("div").html();
					var _val=_value.replace(/[^0-9]/g,'');
					var _unit=_value.replace(_value.replace(/[^0-9]/g,''),'');
					$("#"+opts.ids.val).val(_val);
					$("#"+opts.ids.unit).html(" "+_unit);
				}
				var _val= $(this).children("div").html();
				return false;
			})
			
			$("#"+opts.ids.val).bind("keyup",function(){		
				var _value=$("#"+opts.ids.val).val();
				var _val=_value.replace(/[^0-9]/g,'');
//				var _unit=_value.replace(_value.replace(/[^0-9]/g,''),'');
				$("#"+opts.ids.val).val(_val);
//				$("#"+opts.ids.unit).html(_unit);
				if(_val=="")return false;
//				if(parseInt(_val/3)>5){
//					//alert("只能在5年以内");
//					return false;
//				}
				var _sum=_base * (_val/opts.proportion);
				$("#"+opts.ids.w2).css("width",_sum+"px");
				$("#"+opts.ids.drag).css("left",_sum+"px");
			});
		}
		
		function mouseMove(e){
			_posx = e.clientX;
			_left = _posx - _offsetLeft;
			if(_left<=0){
			  $("#"+opts.ids.w2).css("width","0px");
			  $("#"+opts.ids.drag).css("left","0px");
			} 
			else if(_left<=480) {
				$("#"+opts.ids.w2).css("width",_left+"px");
				$("#"+opts.ids.drag).css("left",_left+"px");
			}
			else{			
				$("#"+opts.ids.w2).css("width",478+"px");
				$("#"+opts.ids.drag).css("left",478+"px");		
			}
			var _count=RecursionCount(_left,_base);
			if(_count>5)_count=5;
			if(_left<=opts.totalWidth && _left>=0){
				var _val = parseInt(_left/opts.spanWidth*opts.proportion);
				//TODO 临时特殊处理 
				if(_val<11){				
					$("#"+opts.ids.val).val(_val+1);	
					if(opts.values.length>12){
						$("#"+opts.ids.unit).html(" 月");
					}else{
						$("#"+opts.ids.unit).html(" M");
					}
				}else{
					if(opts.values.length>12){
						$("#"+opts.ids.val).val(_val-10);	
						$("#"+opts.ids.unit).html(" 年");
					}else{
						$("#"+opts.ids.val).val(_val+1);
						$("#"+opts.ids.unit).html(" M");
					}
				}
			}			
		}
		
		function mouseUp(e){
			$(document).unbind('mousemove');
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
	}
})(jQuery); 