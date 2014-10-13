<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="./common/view.jsp"%>
<html:html locale="true">
<head>
<%=getCssTag(request, "common.css")%>
<%=getCssTag(request, "login.css")%>
<title>山西移动电子渠道云计算管控中心</title>

<script type="text/javascript" src="swfobject.js"></script>
<script type="text/javascript">
	swfobject.registerObject("myId", "9.0.0", "expressInstall.swf");
</script>

<script language="javascript" type="text/javascript">
	//登陆
	function check(obj) {
		if (obj.username.value == "") {
			alert("用户名不能为空！");
			return;
		}
		if (obj.password.value.length < 8) {
			alert("密码长度不能小于8位！");
			return;
		}
		obj.submit();
	}

	//重置
	function cz() {
		loginform.username.value = "";
		loginform.password.value = "";
	}

	if (document.addEventListener) {//如果是Firefox    
		document.addEventListener("keypress", fireFoxHandler, true);
	} else {
		document.attachEvent("onkeypress", ieHandler);
	}

	function fireFoxHandler(evt) {
		//alert("firefox");    
		if (evt.keyCode == 13) {
			check(document.getElementById('loginform'));
		}
	}
	function ieHandler(evt) {
		//alert("IE");    
		if (evt.keyCode == 13) {
			check(document.loginform);

		}
	}
</script>
<%
	String msg = request.getAttribute("msg") == null ? ""
				: (String) request.getAttribute("msg");
%>
</head>
<body onLoad="self.focus();document.loginform.username.focus()">
	<s:actionerror />
	<div class="loginPanel">

		<!-- 
		validate="true"
		-->

		<s:form id="loginform" action="login" method="post" focus="username"
			styleId="theForm">
			<div class="txta">
				<s:textfield label="username" key="username" />
				<s:password label="password" key="password" showPassword="true" />
			</div>
			<div class="btn-area">
				<input type="button" class="dl"
					onclick="javascript:check(document.getElementById('loginform'));return false;" />
				<input type="button" class="cz" onclick="javascript:cz()"
					tabindex="1" />
			</div>
			<span class="msg-area"><font color="#FF0000"><%=msg%></font>
			</span>

		</s:form>

		<!-- 
		<html:form action="login" method="post" focus="ACCOUNT" styleId="theForm">
			<bean:define id="theForm" name="loginForm"/>
			<div class="txta">
		      <input name="ACCOUNT" type="text" />
		      <input name="PASSWORD" type="password" onclick="javascript:if(event.keyCode==13){check(document.getElementById('theForm'));return false;}"/>
		    </div>
			<div class="btn-area" >
		      <input  type="button"  class="dl" onclick="javascript:check(document.getElementById('theForm'));return false;"/>
		      <input  type="button" class="cz" onclick="javascript:cz()" tabindex="1"/>
		    </div>
		   	<span class="msg-area"><font color="#FF0000"><%=msg%></font></span>
		</html:form>
		 -->

		<div class="icon"></div>
	</div>

	<div>
		<object id="myId" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			width="300" height="120">
			<param name="movie" value="index.swf" />
			<!--[if !IE]>-->
			<object type="application/x-shockwave-flash" data="index.swf"
				width="1024" height="800">
				<!--<![endif]-->
				<div>
					<h1>Alternative content</h1>
					<p>
						<a href="http://www.adobe.com/go/getflashplayer"><img
							src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
							alt="Get Adobe Flash player" />
						</a>
					</p>
				</div>
				<!--[if !IE]>-->
			</object>
			<!--<![endif]-->
		</object>
	</div>

</body>
</html:html>