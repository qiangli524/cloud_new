<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<title></title>
<style type="text/css">
html,body,div ul{margin:0;padding:0;border:0;font-size:100%;background:transparent;}
ul{list-style:none;}
a{text-decoration:none;}
body{background:#f2f2f2;font:12px microsoft yahei,Verdana,Tahoma,Lucida Grande,Arial,sans-serif;color:#000;}
#gg{position:fixed;bottom:0;background:#000;width:100%;height:23px;line-height:23px;z-index:9999;opacity:.60;filter:alpha(opacity=60);_bottom:auto;_width:100%;_position:absolute;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0)));}
#gg a{color:#fff;font-size:12px;letter-spacing:2px;}
.close a{float:right;margin:0 10px 0 0;padding:0 10px 0 10px;}
.bulletin{float:left;height:23px;color:#fff;margin:0 0 0 20px;background:url(<%=request.getContextPath()%>/toolbar/bulletin.gif) no-repeat;min-height:23px;overflow:hidden;}
.bulletin li{height:23px;padding-left:25px;}

.bnav3{height:25px;width:16px;line-height:25px; margin:0 1%; padding-right:6px;border-bottom:none;z-index:999;position:fixed;bottom:0;right:0;_position:absolute;/* for IE6 */_top: expression(documentElement.scrollTop + documentElement.clientHeight-this.offsetHeight); /* for IE6 */ overflow:visible;}
.bnav3 a{background:url(toolbar/open.gif) no-repeat center; display:block;height:25px;width:16px; text-indent:-5000px;}
</style>
<!--[if IE 6]>
<style type="text/css">
/* 修正IE6振动bug */
html body{background-image:url(about:blank);background-attachment:fixed;}
</style>
<![endif]-->
<script type="text/javascript">
function showNav(){
	$(".openClose").toggle();
	$('#gg').slideDown('slow');
}
function hideNav(){
	$(".openClose").toggle();
	$('#gg').slideUp('slow');
}
$(function(){
	$.fn.extend({
		ScrollBar:function(opt,callback){
		if(!opt) var opt={};
		var _this=this.eq(0).find("ul:first");
		var lineH=_this.find("li:first").height(),
		line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10),
		speed=opt.speed?parseInt(opt.speed,10):7000,//卷动速度，数值越大，速度越慢（毫秒）
		timer=opt.timer?parseInt(opt.timer,10):7000;//滚动的时间间隔（毫秒）
			if(line==0) line=1;
			var upHeight=0-line*lineH;
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
		_this.hover(function(){
			clearInterval(timerID);
		},function(){
			timerID=setInterval("scrollUp()",timer);
		}).mouseout();
	}
	})
});
var startList;
$(document).ready(function(){
	startList = function(){
			$.ajax({
			type:"GET",
			url:"globaltask_listProccessing.do",
            cache: false,
			dataType:"json",
			success:function(data){
				var html = '';
				var result = data.resultList;
				if(result == null || result == ''){
					html += '<li><a href="#" name="task">暂无最新任务</a></li>';
				}
				if(result !=null && result != ''){
					for(var i=0;i<result.length;i++){
					//taskjson += result[i].id + '",process:"' + result[i].process + '",';
						html += '<li><a href="#" name="task" id="' +result[i].id + '" process="'+ result[i].progress +'">' + result[i].content + ', 已完成' + result[i].progress + '%</a></li>';
					}
				}
				$("#proccessing").empty().append(html);
			}
			});
		}
	startList();
	$(".bulletin").ScrollBar({line:1,speed:1000,timer:5000});//修改此数字调整滚动时间
});

</script>
</head>
<body>
<div id="gg">
<div class="close"><a href="javascript:void(0)"  onclick="hideNav()" title="隐藏">隐藏×</a></div>
<div class="bulletin">
<ul id="proccessing">
</ul>
</div>
</div>
<div class="bnav3 openClose" style="display:none;"><a href="javascript:void(0)" onclick="showNav()" title="打开">打开</a></div>
</body>
</html>