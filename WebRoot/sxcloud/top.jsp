<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%@ page import="com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj"%>
<%@ page import="com.sitech.basd.sxcloud.config.Constant"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj"%>

<head>

	<script language="javascript">
var h=document.body.offsetHeight-135;
 $(".frame-ct").find(".tabct").height(h);
$(window).resize(function(){
	var h=document.body.offsetHeight-135;
    $(".frame-ct").find(".tabct").height(h);
});

</script>
	<%@ include file="common/taglib.jsp"%>
	<script language="JavaScript" src="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.hiAlerts-min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/thickbox.js"></script>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jquery.hiAlerts.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jbox.css" rel="stylesheet" type="text/css"></link>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css"></link>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css"></link>
	<script type="text/javascript">    
	window.onbeforeunload = function() {
		if (window.event.clientY < 0) {
    		$.getJSON("login_loginout.do",{'time':new Date().toString()}, function(data){
    		});
		}
	}
</script> 
	<script type="text/javascript">    
	window.onbeforeunload = function() {
		if (window.event.clientY < 0) {
    		$.getJSON("login_loginout.do",{'time':new Date().toString()}, function(data){
    		});
		}
	}
	
	var timer;
	function changeAlarmState() {
		var _src = $("#alarmImg")[0].src;
		if(_src.indexOf('sound-off')>0) {//开启警报
			$("#alarmImg")[0].src = "<%=request.getContextPath()%>/publicCloud/pub-ui/images/sound-on.png";
			
			refreshAlarm();
			timer = window.setInterval("refreshAlarm()", 10000);
		}else {//关闭警报
			$("#alarmImg")[0].src = "<%=request.getContextPath()%>/publicCloud/pub-ui/images/sound-off.png";
			if(timer) {
				window.clearInterval(timer);
			}
		}
	}
	
	function refreshAlarm() {
		$.ajax({
			type:'post',
			async: false,
			dataType:'text',
			url:"alarm_getTodayAlarmNum.do",
			success:function(msg){
				$("#alarmNum")[0].innerHTML=msg;
				if(msg>0) {
					//$("#alarmAudio")[0].play();
					var embedSrc = '<embed id="alarmAudio" height="100" width="100" src="<%=request.getContextPath()%>/publicCloud/pub-ui/audio/ALARM1.wav" autostart="true" style="display:none;"/>'
					$("#alarmAudio")[0].innerHTML=embedSrc;
					
				}
			}
		});
	}
	
	function getTodayAlarmNum() {
		$.ajax({
			type:'post',
			async: false,
			dataType:'text',
			url:"alarm_getTodayAlarmNum.do",
			success:function(msg){
				$("#alarmNum")[0].innerHTML=msg;
				//document.getElementById("alarmNum").innerHTML=msg;
			}
		});
	}
	
	function hasAlarmPermission() {
		$.ajax({
			type:'post',
			async: false,
			dataType:'text',
			url:"alarm_hasAlarmPermission.do",
			success:function(msg){
				if(msg==1) {//如果有权限，则让告警图标显示
					$("#alarmPic")[0].style.display='block';
					$("#alarmRemind")[0].style.display='block';
					getTodayAlarmNum();
				}else {
					document.getElementById('quickDiv').style.width=140;
				}
			}
		});
	}
	
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
</script> 
</head>
<div class="head">
	<div class="logo">
		<%=ImageUtil.getImageTag(request, "logo-bg.jpg")%>
	</div>

	<div class="login">

		<div class="login-out">
			<%
				Boolean leader = (Boolean)request.getSession().getAttribute(Constant.LEADER_SESSION_KEY);
				if(null != leader && leader){
			%>
			<s:url action="gotoLeaderPage" >后退</s:url>
			<%--<html:link action="gotoLeaderPage" target="_parent">后退</html:link>--%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%
				}
			%>
<%--			<s:a href="login_gobackDemoindex.do" value="回到主页">回到主页</s:a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
			<s:a value="退出" href="#" onclick="login_out()">退出</s:a>
		</div>

		<div class="navall">
			
		</div>
		<div class="navall">
		
		</div>

		<div class="quick" id="quickDiv">
		<%String userinfoObj = (String)request.getSession().getAttribute("name"); %>
			<span><%=userinfoObj%>：您好</span>
			<span style="padding-left:12px;display:none;" id="alarmPic">
				<img id="alarmImg" style="cursor:pointer;" src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/sound-off.png"  width="23" height="23" alt="告警提示" onclick="changeAlarmState();"/>
				<!-- 
				<embed id="alarmAudio" height="100" width="100" src="<%=request.getContextPath()%>/publicCloud/pub-ui/audio/ALARM1.wav" autostart="false" style="display:none;"/>
				-->
			</span>
			<span id="alarmAudio" style="display:none;"></span> 
			<span style="padding-left:5px;color:red;display:none;" id="alarmRemind">
			告警次数：
		<%
        List functionsOneList = (List) request.getAttribute("FUNCTIONS_ONE");
        if(functionsOneList!=null&&functionsOneList.size()>0){
	        for(int i=0;i<functionsOneList.size();i++){
		        TbSysFunctionsObj obj = (TbSysFunctionsObj)functionsOneList.get(i);
		        if("告警管理".equals(obj.getFUNNAME())) {
			        String subFuncid = obj.getFUNCID().substring(0,2)+"01000000";
			        String single = String.valueOf(obj.getFUNCREQUEST()).contains("?") ? "&" : "?";
			        String subUrl ;
			        if(obj.getFUNCREQUEST().contains("http")){
			        	subUrl = obj.getFUNCREQUEST();
			        }else{
			         	subUrl = request.getContextPath()+"/"+String.valueOf(obj.getFUNCREQUEST()) + single + "FUNCID="+subFuncid+"&FUNCSID="+obj.getID() ;
			        }
		%>
			<a  href="javascript:void(0)" turl="<%=subUrl%>"
				ajx="login_getTitle.do?FUNCID=<%=subFuncid%>"
				treeUrl="login_leftTree.do?FUNCID=<%=subFuncid%>" mid="<%=obj.getID()%>" id="alarmNum" style="color:red;font-weight:500;text-decoration:underline;"><%=obj.getFUNNAME()%>
			</a>
		<%
				}
			}
		}
		%>
			</span>
			 
			
		</div>

	</div>
</div>
<div class="nav-panel">
	<ul class="nav">
		<%
        //List functionsOneList = (List) request.getAttribute("FUNCTIONS_ONE");
        if(functionsOneList!=null&&functionsOneList.size()>0){
        for(int i=0;i<functionsOneList.size();i++){
        TbSysFunctionsObj obj = (TbSysFunctionsObj)functionsOneList.get(i);
        String subFuncid = obj.getFUNCID().substring(0,2)+"01000000";
        String single = String.valueOf(obj.getFUNCREQUEST()).contains("?") ? "&" : "?";
        String subUrl ;
        if(obj.getFUNCREQUEST().contains("http")){
        	subUrl = obj.getFUNCREQUEST();
        }else{
         	subUrl = request.getContextPath()+"/"+String.valueOf(obj.getFUNCREQUEST()) + single + "FUNCID="+subFuncid+"&FUNCSID="+obj.getID() ;
        }
		%>
		<li>
			<a menuClass="oneMenu" href="javascript:void(0)" turl="<%=subUrl%>"
				ajx="login_getTitle.do?FUNCID=<%=subFuncid%>"
				treeUrl="login_leftTree.do?FUNCID=<%=subFuncid%>" mid="<%=obj.getID()%>"><%=obj.getFUNNAME()%>
			</a>
		</li>
		<%
				}
			}
		%>
	</ul>
	<ul class="layout">
		<li>
			<%=ImageUtil.getImageTag(request, "layout01.gif")%>
		</li>
		<li>
			<%=ImageUtil.getImageTag(request, "layout02.gif")%>
		</li>
		<li>
			<%=ImageUtil.getImageTag(request, "layout03.gif")%>
		</li>
		<li>
			<%=ImageUtil.getImageTag(request, "layout04.gif")%>
		</li>
	</ul>
</div>
<!--nav-panel end -->
<script type="text/javascript">
hasAlarmPermission();

</script>