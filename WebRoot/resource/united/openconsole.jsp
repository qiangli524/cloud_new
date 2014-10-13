<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>


<head>
	<script type="text/javascript">
	
		var vmrc = null;
		var ticket = null;
		var vmNameTooltip = null;
		var tooltipTimeout = null;
		var resizeTimeout = null;
		var removeSpinnerCb = null;
		var SCROLLBAR_WIDTH = null;
		var title = '<s:property value="uuid" />';
		var vmName = '<s:property value="uuid" />';
		var connect_id = '<s:property value="connect_id" />';
		var vtype = '<s:property value="vtype" />';
		// VMRC EVENTS
		var GS_GRABBED        = 1;
		var GS_UNGRABBED_HARD = 2;
		var GS_UNGRABBED_SOFT = 3;
		
		var MT_HINT           = 0;
		var MT_INFO           = 1;
		var MT_WARN           = 2;
		var MT_ERROR          = 3;
		var vmId = null;
		var CS_DISCONNECTED   = 0;
		var CS_CONNECTED      = 2;
		vmrc = document.getElementById("vmrc");
		if (window.console == undefined || window.console.debug == undefined) {
  			window.console = {
      			debug: function(msg){},
      			info: function(msg){},
      			warn: function(msg){},
      			error: function(msg){}
   			}
   		}	
   		function hideSpinner() {
  			$("#spinner").hide();
   			//removeSpinnerCb();
  			$("#glassPane").hide();
		}
		function hideTooltip() {
   			tooltipTimeout = setTimeout('$("#vmNameTooltip").fadeOut("slow")', 600);
		}
		function onWindowResized(event) {
  			clearTimeout(resizeTimeout);
   			resizeTimeout = setTimeout(resizeContainer, 300);
		}
		function resizeContainer() {
   			// set height
  		 	$("#container").height( $(window).height() - $("#container").offset().top );
   			// set width
   			$("#container").width( $(window).width() );
   			// align console
   			alignConsole();
		}
		function alignConsole() {
  			var cw = $("#container").width();
   			var vw = $(vmrc).width();
   			var ch = $("#container").height();
   			var vh = $(vmrc).height();
   			var offX = 1;
   			var offY = $("#container").offset().top;

   /* Force add/remove scrollbars (FF issue) */

   			var xScrollOn, yScrollOn = false;
   			var xDiff, yDiff = 0;
	
   			yDiff = ch - vh;
   			xDiff = cw - vw;

			yScrollOn = yDiff <= 0;
   			xScrollOn = xDiff <= 0;
			
   			if (SCROLLBAR_WIDTH == null) {
     			SCROLLBAR_WIDTH = scrollbarWidth();
   			}
   // Enabling one scrollbar could trigger the other dimension to be clipped...
  			if (yScrollOn && xDiff < SCROLLBAR_WIDTH) {
      			xScrollOn = true;
   			}
   			if (xScrollOn && yDiff < SCROLLBAR_WIDTH) {
      			yScrollOn = true;
   			}

   // Compensate for scroll bars
   			if (yScrollOn) { xDiff -= SCROLLBAR_WIDTH; }
   			if (xScrollOn) { yDiff -= SCROLLBAR_WIDTH; }

   // Set the CSS directives
   			var xVal = xScrollOn ? "scroll" : "hidden";
   			var yVal = yScrollOn ? "scroll" : "hidden";

   /* Position the console */

   			if (cw > vw) {
      			offX += Math.floor(xDiff / 2);
   			}
   			if (ch > vh) {
     			offY += Math.ceil(yDiff / 3);
   			}

			//XXX this offset and overflow instruction "kills" vmrc !?@$!!
			//   $(vmrc).offset({top: offY, left: offX});
			//   $("#container").css("overflow-x", xVal);
			//   $("#container").css("overflow-y", yVal);

			//XXX Whereas this doesn't seem harmful
   			$(vmrc).css("margin-left", offX + "px");

			//XXX UE question: do we want to have console aligned vertically as well?
			//   $(vmrc).css("margin-top", offY + "px");
		}
		function scrollbarWidth() {
   			var div = $('<div style="width:50px;height:50px;overflow:hidden;position:abso' +
               'lute;top:-200px;left:-200px;"><div style="height:100px;"></div>');
   			// Append div, do calculation and then remove it
   			$("body").append(div);
   			var w1 = $("div", div).innerWidth();
   			div.css('overflow-y', 'scroll');
   			var w2 = $("div", div).innerWidth();
   			$(div).remove();
   			return (w1 - w2);
		}
		function mouseOutHandler(e) {
   			var target = getTarget(e);
   			if (target == null) {
      			return;
   			}
   			target.children("div").removeClass('box_hover').removeClass('box_pressed');
		}

		function mouseOverHandler(e) {
   			var target = getTarget(e);
   			if (target == null) {
      			return;
   			}
   			target.children("div").addClass('box_hover');
		}

		function mouseDownHandler(e) {
   			var target = getTarget(e);
   			if (target == null) {
      			return;
   			}
   			target.children("div").removeClass('box_hover').addClass('box_pressed');
		}

		function mouseUpHandler(e) {
   			var target = getTarget(e);
   			if (target == null) {
      			return;
   			}
   			target.children("div").removeClass('box_pressed').addClass('box_hover');
		}
		function selectStartHandler(e) {
   			return false;
		}
		function dragStartHandler(e) {
   			return false;
		}
		function registerButton(buttonId, clickHandler) {
  			var button = $("#" + buttonId);
  			button.click(clickHandler);
   			button.mouseout(mouseOutHandler);
  			button.mouseover(mouseOverHandler);
  			button.mousedown(mouseDownHandler);
   			button.mouseup(mouseUpHandler);
   			button.bind('selectstart', selectStartHandler);
   			button.bind('dragstart', dragStartHandler);

   			// mark the top level DOM element as a "button" class
   			button.addClass("button");
		}
		function showTooltip() {
   			if (tooltipTimeout != null) {
      			clearTimeout(tooltipTimeout);
      			tooltipTimeout = null;
   			}
   			$("#vmNameTooltip").show();
		}
		function showSpinner() {
   			// Use Raphael's Spinner --> raphael.min.js
   			// constructor args: holderid, R1, R2, count, stroke_width, colour
   			//removeSpinnerCb = spinner("spinner", 16, 24, 12, 5, "#fff");
   			$("#glassPane").fadeTo(0, 0.3);
   			$("#spinner").show();
		}
		function justifyButtonWidths() {
  			$("#buttonBar").children().each( function() {
      			var button = $(this);
      			var r = $(button.children(".buttonR")[0]).width();
      			var c = $(button.children(".buttonC")[0]).width();
      			var l = $(button.children(".buttonL")[0]).width();
      			button.width(l + c + r + 1);
   			});
		}
		$(function(){
			$("#download").unbind().live("click",function(){
				var url = 'vm_downloadPlugin.do';
				location.href = url;
			})
		})
		// INITIALIZE
		$(document).ready(function(){
  			try {
				// Show spinner overlay
   				showSpinner();
				// Start up VMRC
   				vmrc = document.getElementById("vmrc");
   				vmNameTooltip = $("#vmNameTooltip");
   				var vmTitle = $("#vmTitle");

   				// set the title strings on the page
			   	document.title = title;
   				//vmTitle.text(title);
   				//vmNameTooltip.text(title);

   				// Attach event listeners
   				$(window).resize(onWindowResized);
   					registerButton("fullScreenButton", fullScreen);
   					registerButton("cadButton", sendCAD);

   				// Register tool tip
   				$("#vmName").hover(showTooltip, hideTooltip);
   				$("#vmNameTooltip").hover(showTooltip, hideTooltip);

  				// Adjust CSS geometry
   				$("#buttonBar").height( $("#container").offset().top );
   				// This fixes IE7's inability to assign correct widths
   				justifyButtonWidths();
   				initVmrc();
   			} catch (error) {
   				hideSpinner();
  				 console.error(error);

   				// Only alert if the plugin is installed
   				if (vmrc != null && vmrc.isReadyToStart != null) {
      				alert(error);
      				alert();
      				$("#hint").text("请您在IE或者FireFox浏览器中打开控制台");
   				} else {
     				$("#hint").text("请您在IE或者FireFox浏览器中打开控制台,并且安装vmware-plug-in。如已经安装，请忽略此提示");
     				//$("#hint").html("<br/><a href='javascript:;' onclick='download()'>xiaaa</a>");
     			$("#hint").append('<a href="javascript:;"  id="download">'+'点击此链接下载vmware-plug-in'+'</a>');
   				}
			}
		});
		
		function onSizeChanged(width, height) {
   			console.debug("VMRC: size changed: " + width + "x" + height);
   			$(vmrc).height(height);
   			$(vmrc).width(width);

			alignConsole();
		}
		function onGrabStateChanged(state) {
   			console.debug("VMRC: grab state changed: " + state);
   			// Only show "Ctrl-Alt" hint when the mouse is grabbed
   			var hint = $("#hint");

   			//XXX bug: IE has integer (enum), FF has string as "state" parameter
   			if (state == GS_GRABBED || state == "grabbed") {
      			//hint.text("123456");
      			hint.fadeTo("fast", 1.0);
   			} else if (state == GS_UNGRABBED_SOFT || state == GS_UNGRABBED_HARD ||
         		state == "ungrabbedHard" || state == "ungrabbedSoft") {
      			hint.fadeTo("slow", 0.5);
   			}
		}
		function backToClient() {
   			var url =
     		window.location.protocol + "//" + window.location.host + "/vsphere-client/?"
      			+ "#extensionId=vsphere.core.vm.summaryView;context=com.vmware.core.model%3A%3A"
      			+ "ServerObjectRef~%3A%3A"
      			+ "vm-383";
			if (window.opener) {
      			window.opener.location = url;
   			} else {
      			window.open(url);
   			}
		}
		
		function onMessage(type, msg) {
   			var prefix = isNaN(type) ? type + ": " : "";
   			alert(prefix + msg);
		}
		function onWindowStateChanged(state) {
   			console.debug("VMRC: window state changed: " + state);
		}
		function getTarget(event) {
 			return $(event.target).parents(".button");
		}
		function onConnectionStateChanged(cs, host, vmId, userRequested, reason) {
   			console.debug("VMRC: connection state changed: " + cs);

   			if (cs == CS_CONNECTED) {
     			hideSpinner();
      			//$("#hint").text("123456");
      			$("#hint").fadeTo("fast", 0.5);
   			} else {
      		setTimeout(function() {
         		vmrc.shutdown();
         		if (reason != null && reason.length > 0) {
            		alert(reason);
         		} else {
            		alert("123456");
         	}
         		window.opener = self;
         		window.close();
      			}, 0);
   			}
		}
		function initVmrc() {
   			// Keep retrying as long as VMRC is not ready
   			if (!isVmrcReady()) {
      			setTimeout(initVmrc, 100);
     			console.debug("VMRC: initVmrc to retry " + new Date().getTime());
	   			return;
  			}
   			console.debug("VMRC: is ready to start.");
   			if ($.browser.msie) {
 	    	 	vmrc.attachEvent("onSizeChange", onSizeChanged);
 	  		   	vmrc.attachEvent("onConnectionStateChange", onConnectionStateChanged);
 	  		   	vmrc.attachEvent("onGrabStateChange", onGrabStateChanged);
 		   		vmrc.attachEvent("onMessage", onMessage);
   		 		vmrc.attachEvent("onWindowStateChange", onWindowStateChanged);
   			} else if ($.browser.mozilla) {
     			vmrc.onSizeChange = onSizeChanged;
      			vmrc.onConnectionStateChange = onConnectionStateChanged;
      			vmrc.onGrabStateChange = onGrabStateChanged;
     			vmrc.onMessage = onMessage;
      			vmrc.onWindowStateChange = onWindowStateChanged;
   			}
   			var startupId = vmrc.startup(1, 1, false, "");
			   $.ajax({
     				url: "united_getticket.do?uuid=" + vmName+"&connect_id="+connect_id+"&vtype="+vtype,
      				async: false,
      				cache: false,
      				success: function(data) {
      					///var json = eval("("+ data+")");
        				ticket = data;
        				vmId = '<s:property value="uuid" />';
      				}
   				});
   			if (startupId) {
      			console.info("VMRC startup: " + startupId);
      			var ipAddress = '<%=request.getAttribute("ip")%>';
     			var connected = vmrc.connect(ipAddress, ticket, "", "", vmId, "", "");
     			if (connected) {
         			console.info("VMRC connected successfully.");
      			} else {
        	 		console.error("VMRC failed to connect.");
      			}
   			} else {
      			console.error("VMRC failed to startup.");
   			}
		}
		function isVmrcReady() {
      		return vmrc != null && vmrc.isReadyToStart();
		}
		function fullScreen() {
   			if (vmrc != null) {
      		vmrc.setFullscreen(true);
   			}
		}

		function sendCAD() {
   			if (vmrc != null) {
      		vmrc.sendCAD();
   			}
		}
		// UNLOAD
$(document).unload(function(){
   if (vmrc != null) {
      vmrc.disconnect();
      vmrc = null;
   }
});
	</script>
</head>
<body> 
	<div id="buttonBar">
	 	<div id="fullScreenButton" class="buttonT">
        	<div class="buttonR">&nbsp;</div>
        	<div class="buttonC"></div>
        	<div class="buttonL">&nbsp;</div>
      	</div>

     	<div id="cadButton" class="buttonT">
        	<div class="buttonR">&nbsp;</div>
        	<div class="buttonC"></div>
         	<div class="buttonL">&nbsp;</div>
      	</div>
   	</div>
   <div id="vmName">
      <span id="vmTitle"><!-- filled programmatically --></span>
   </div>
   <div id="vmNameTooltip"><!-- filled programmatically --></div>

   	<div id="hint"></div>
	<div id="container">
      	<!--[if IE]>
      		<object id="vmrc" classid="CLSID:291BA977-564B-4626-B868-A968FB8D4591">
      		</object>
      	<![endif]-->
      	<!--[if !IE]><!-->
      		<object id="vmrc" type="application/x-vmware-remote-console-2011">
      		</object>
      	<!--<![endif]-->
   </div>
   <div id="glassPane"></div>
   <div id="spinner"></div>
</body>
