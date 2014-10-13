<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../toolbar/toolbar.jsp"%>
<%@ include file="cas_auto_login.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sitech.basd.yicloud.util.CfgUtil" %>
<%!
public String getString(String str) {
	return CfgUtil.getString(str);
}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
    <title>重庆移动云资源池管理</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/newUI/newUI/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/newUI/newUI/css/jquery.contextMenu.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/newUI/newUI/css/index.css" />
    <!--[if lt IE 8]>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/newUI/newUI/css/index-hack.css" />
    <![endif]-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/plugin/correctPNG.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/plugin/modernizr-min.2.6.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/plugin/jquery.hotkeys.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/plugin/jquery.contextMenu.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/function.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/index.js"></script>
    
    <script type="text/javascript" src="http://<%=getString("node.js.ip")%>:<%=getString("node.js.port")%>/socket.io/socket.io.js"></script>

    <script language="JavaScript" src="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.hiAlerts-min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/thickbox.js"></script>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jquery.hiAlerts.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jbox.css" rel="stylesheet" type="text/css"></link>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css"></link>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css"></link>
    <% HashMap information=request.getAttribute("information")==null?null:(HashMap)request.getAttribute("information"); 
    	
       String INFORMATION_FLAG=(String)  request.getAttribute("INFORMATION_FLAG");
    %>
    <!--[if IE 6]>
    <script type="text/javascript">
        window.attachEvent("onload", correctPNG);
    </script>
    <![endif]-->
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
	/*
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
               		socket.on('message-process', ProMessage);//处理正在进行的任务,更新任务百分比
               		socket.on('message-start', startMessage);//开始执行任务
            	}
            };
 
            connectToServer();
 
         });*/
       
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
        <%-- 以上暂定, duangh --%>
        
         
         
    	$(function(){
    		$("button").click(function(){
    			var module_name=$("#module_name").val();
    			$("form").submit();
    		});
    	});
    	function login_out(){
    	  var localurl = 'http://'+'<%=request.getServerName()%>'+':' + '<%=request.getServerPort()%>' + '<%=request.getContextPath()%>';
    	  $.ajax({
			type: "POST",
			cache: "false",
			async: true,
			dataType: "text",
			url: "<%=request.getContextPath()%>/login_loginout.do",
			success: function(data) {
				//实现单点登出
				 $.ajax({
						type: "GET",
						cache: "false",
						async: true,
						dataType: "text",
						url: '<s:property value="casurl" />' + '/logout?service='+localurl,
						success: function(data) {
							
						}
				 });
				window.location=localurl;
			}
		  });
    	}
    	function ff(o){
             var _o = $(o);
			_o.click(function(e){
		        e.preventDefault();
		            var _url = $(this).attr("ss");
		            if(_url == "" || _url == "#"){
		                return false;
		            }else{
		                $("div.wrap").css("background-image", "url("+_url+")");
		               // $.cookie("background-image-url", _url, {expires: 10});
		               //setCookie("url",_url);
		            }
		    })
    	}
    	   function cutString(text, canvasWidth) {
	 	var suffix = "...";
	    var tempText = text.substring(0, canvasWidth);
	    if(canvasWidth >= text.length) return text;
	    return tempText+suffix
	}
	function myhelp(){
	  UID_dailog_3('帮助','/cloud/newUI/jsp/help.jsp',500,true,function (){
	           return ;
		      });
	}
	
	function pageInit() {
	var _url='';
		$("div.wrap").css("background-image", "url("+'<%=request.getContextPath()%>/newUI/newUI/images/index-bg.jpg'+")");
<%--		setCookie("url",_url);--%>
	}
	function openHref(url,name) {
		//url = window.location.protocol + "//" + window.location.host + "/" +url ;
		//id不要相同，相同的id，对话框无法弹出
		//设置zIndex参数，要不其他的弹出框都被这个覆盖了。
		//zIndex为全剧参数其他地方以此累加，其他地方不要加
		var x = document.body.scrollWidth;
		var y = document.body.scrollHeight - 45;
		$.dialog({
    		id:url,
    		title:name,
    		width: x,
    		height: y,
    		fixed: true,
    		zIndex:1000,
    		content: 'url:'+url+''
    	});
	}
    </script>
</head>

<body onload="pageInit()">
<div class="wrap">
    <div class="top">
        <h1 class="fl"><img src="<%=request.getContextPath()%>/newUI/newUI/images/index_logo.png" title="云管理平台" /></h1>
        <div class="option">
        	
            <div class="search">
            <form action="newui_index.do" method="post">
            	<input type="text" placeholder="请输入应用名称" name="module_name" id="module_name" /><button title="搜索" ></button>
            </form>
            </div>
            <a href="javascript:void(0)" class="bulletin">公告栏</a>
            <a href="javascript:void(0)" class="set">设置</a>
            <a href="javascript:void(0)" onclick="myhelp()"  class="help">帮助</a>
            <a href="javascript:void(0)" class="exit" onclick="login_out()">退出</a>
        </div>
    </div>
    <div class="main fl clear">
        <div id="main" class="none">
        	<%
        	List<Map<String,String>> moduleList=(List<Map<String,String>>)request.getAttribute("displayModule");
        	  int page_num=0;
        	//一页展示9个图标
        		for(int i=0;i<moduleList.size();i++){
        			if(i%9==0){
        				if(page_num!=0){
        				%>
        				</div>
        				<%
        				}
        				%>
        				<div class="pane">
        			<!--  	 <a href="#" class="indexAdd"><img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_10.png" alt="添加"><span>添加<s></s></span></a>-->
        			<%
        				page_num++;
        				
        			}
        			Map<String,String> map = moduleList.get(i);
        			String module_path = map.get("FUNCREQUEST");
        			String module_name = map.get("FUNNAME");
        			String pic_path = map.get("PIC_PATH");
        			String module_id = map.get("FUNCID");
        			String flag = map.get("THREE");
        			String iconSrc=request.getContextPath()+pic_path;
					if(iconSrc.equals("null")){
						iconSrc=request.getContextPath()+"/newUI/images/icon_15.png";
					}
        			
        			if("one".equals(flag)){
        			%>
        			  <a href="javascript:openHref('<%=module_path%>','<%=module_name%>')" class="indexIconNew" id="<%=module_id %>"><img src=<%=request.getContextPath()+pic_path%> alt=<%=module_name%>/><span><%=module_name%><s></s></span></a>
        			<%
                    }else if("three".equals(flag)){
                    	if(module_name.length()>=5){
                    	}
        			%>
        			  <a href="javascript:openHref('<%=module_path%>','<%=module_name%>')" class="indexIcon" id="<%=module_id %>"><img src=<%=request.getContextPath()+pic_path%> alt=<%=module_name%>/><span title="<%=module_name%>"><script type="text/javascript">document.write(cutString('<%=module_name%>', 5))</script><s></s></span></a>
        			<%
        			}
        		}
        		request.setAttribute("page",page_num);
        	 %>
        	 <a href="#" class="indexAdd"><img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_10.png" alt="添加"/><span>添加<s></s></span></a>
        </div>
    </div>
    <a href="#" class="arrow-l" id="arrow-l" title="上一屏"><img src="<%=request.getContextPath()%>/newUI/newUI/images/arrow_l.png" alt=""/></a>
    <a href="#" class="arrow-r" id="arrow-r" title="下一屏"><img src="<%=request.getContextPath()%>/newUI/newUI/images/arrow_r.png" alt=""/></a>
    <div class="indexTip">
        <ul class="clearfix" id="numTip">
        	 <%int num=(Integer)request.getAttribute("page"); 
        	for(int i=0;i<num;i++){
        	%>
        		 <li><%=i+1%></li>
        	<%
        	}%>
        </ul>
    </div>
</div>
<div class="setSkin">
    <h2 class="fn">请选择您喜欢的背景 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <span onclick="ff(this)" style="cursor:pointer" ss="<%=request.getContextPath()%>/newUI/newUI/images/index-bg.jpg">恢复默认</span>
     <a href="javascript:void(0)" class="close fr" title="关闭">&nbsp;</a>
    </h2>
    <a href="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/index-bg-1.jpg"><img src="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/skin-bg01.jpg" alt=""/></a>
    <a href="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/index-bg-2.jpg"><img src="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/skin-bg02.jpg" alt=""/></a>
    <a href="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/index-bg-3.jpg"><img src="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/skin-bg03.jpg" alt=""/></a>
    <a href="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/index-bg-4.jpg"><img src="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/skin-bg04.jpg" alt=""/></a>
    <a href="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/index-bg-5.jpg"><img src="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/skin-bg05.jpg" alt=""/></a>
    <!-- <a href="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/index-bg-6.jpg"><img src="<%=request.getContextPath()%>/newUI/newUI/images/pic-publish/skin-bg06.jpg" alt=""></a>  -->
    <a href="<%=request.getContextPath()%>/newUI/newUI/images/index-bg.jpg"><img src="<%=request.getContextPath()%>/newUI/newUI/images/index-bg.jpg" alt=""/></a>
</div>
<div class="bullBoard">
	<h2 class="fn bullClose"><a href="javascript:void(0)" class="close fr" title="关闭">&nbsp;</a></h2>
  	<div class="webtake mt40">
    	<h2 class="fn ml10">&bull;今日信息：
    	   <% if("true".equals(INFORMATION_FLAG)){ %>
    	  <a href="javascript:void(0)" class="construction fr" title="新建">&nbsp;</a>
    	  <%} %>
    	</h2>
        <p class="pad5">
        <%if(information==null){
        %>
       	 无公告</p>
        <% } else{
        %>
        <%=information.get("CONTENT")==null?"": information.get("CONTENT")%>
         			<div style="position:absolute;bottom:30px;left:30px;">
         			<ul>
         			<li>
				   	<s:if test="%{beatMap['rest']==null || beatMap['rest']==''}">
						<img src="<%=request.getContextPath()%>/heartbeat/ico/noheart.gif" width="40" height="40"/>
					</s:if>
					<s:else>
						<img src="<%=request.getContextPath()%>/heartbeat/ico/beat.gif" width="40" height="40"/>
					</s:else>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="%{beatMap['daemon']==null || beatMap['daemon']==''}">
						<img src="<%=request.getContextPath()%>/heartbeat/ico/noheart.gif" width="40" height="40"/>
					</s:if>
					<s:else>
						<img src="<%=request.getContextPath()%>/heartbeat/ico/beat.gif" width="40" height="40" />
					</s:else>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="%{beatMap['monitor']==null || beatMap['monitor']==''}">
						<img src="<%=request.getContextPath()%>/heartbeat/ico/noheart.gif" width="40" height="40"/>
					</s:if>
					<s:else>
						<img src="<%=request.getContextPath()%>/heartbeat/ico/beat.gif" width="40" height="40"/>
					</s:else>
					</li>
					<li>
					&nbsp;&nbsp;接口&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					后台&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					监控
					</li>
					</ul>
					</div>
        </p><%--
        <a href="/information.do" class="viewMore">查看更多>></a>
        --%><% }%>
    </div>
</div>
</body>
</html>