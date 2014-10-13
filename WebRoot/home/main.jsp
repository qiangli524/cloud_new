<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sitech.basd.yicloud.util.CfgUtil" %>
<%!
public String getString(String str) {
	return CfgUtil.getString(str);
}%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
  	<%--<title>重庆移动云资源池管理</title>--%>
  	<title>云计算管理平台</title>
  </head>
  <body>
    <%@include file="/home/head.jsp"%>
	<iframe width="100%" height="1000" id="mainIframe"  scrolling="no" name="aa" frameborder="0" src="<%=path%>/home_home.do"></iframe>
	</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="http://<%=getString("node.js.ip")%>:<%=getString("node.js.port")%>/socket.io/socket.io.js"></script>
<script language="javascript" type="text/javascript">
 
    /* 扩展窗口外部方法 */ 
$.dialog.notice = function( options ) 
{ 
    var opts = options || {}, 
        api, aConfig, hide, wrap, top, 
        duration = opts.duration || 800; 
         
    var config = { 
        left: '100%', 
        top: '100%', 
        fixed: true, 
        drag: false, 
        resize: false, 
        init: function(here){ 
            api = this; 
            aConfig = api.config; 
            wrap = api.DOM.wrap; 
            top = parseInt(wrap[0].style.top); 
            hide = top + wrap[0].offsetHeight; 
                         
            wrap.css('top', hide + 'px') 
            .animate({top: top + 'px'}, duration, function(){ 
                opts.init && opts.init.call(api, here); 
            }); 
        }, 
        close: function(here){ 
            wrap.animate({top: hide + 'px'}, duration, function(){ 
                opts.close && opts.close.call(this, here); 
                aConfig.close = $.noop; 
                api.close(); 
            }); 
                         
            return false; 
        } 
    }; 
         
    for(var i in opts) 
    { 
        if( config[i] === undefined ) config[i] = opts[i]; 
    } 
         
    return $.dialog( config ); 
}; 
    
    //飘窗
    function bar(contents){
		$.dialog.notice({ 
		max: false, 
    	min: false,
   		title: '消息', 
    	width: 220,  /*必须指定一个像素宽度值或者百分比，否则浏览器窗口改变可能导致lhgDialog收缩 */ 
    	height: 100,
    	content: contents 
    	//time: 5 //取消时间
		});
	}
	
	//消息推送
	
    	$(function () {
            var onMessage = function (data) {
               // Do something with the message data
               //收到的为所有登录人的信息,只对任务发起人进行广播
               var uuid = data.id;//任务uuid
               var params = {"uuid":uuid};
               var message = data.content;
               $.ajax({
					type:"GET",
					url:"globaltask_ifCreater.do",
            		cache: false,
            		data:  params,
					dataType:"json",
					success:function(resultData){
						var result = resultData.resultList;
						if(result.length>0){
							bar(message);
						}
					}
				});
            };
 			var ProMessage = function(data){
 				var uuid = data.id;
             	id = '#'+ uuid;
             		//$(id).attr("process",data.progress);
             		$(id).html(data.content + ', 已完成' + data.progress + '%');
 			}
 			
 			var startMessage = function(data){
 				startList();
 			}
 			
            var connectToServer = function () {
            	if(io){
            		var socket = io.connect('http://<%=getString("node.js.ip")%>',{port:<%=getString("node.js.port")%>});//连接推送服务器
               		socket.on('message-name', onMessage);//处理完成的任务,飘窗
               		//socket.on('message-process', ProMessage);//处理正在进行的任务,更新任务百分比
               		//socket.on('message-start', startMessage);//开始执行任务
            	}
            };
 
            connectToServer();
 
         });
       
         $(function(){
        	$("a[name='task']").live("click",function(){
        		$.dialog({
    				id:'task',
    				title:'所有任务',
    				width: 800,
    				height: 450,
    				content: 'url:globaltask_listGlobalTask.do'
    			});
        	});
        });
 </script>
</html>
