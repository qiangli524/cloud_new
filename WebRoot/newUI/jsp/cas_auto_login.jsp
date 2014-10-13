<script type="text/javascript">
	$(document).ready(function(){ 
		$('#J_Username').attr('value','<s:property value="username" />');
		$('#J_Password').attr('value','<s:property value="password" />');
		flushLoginTicket();
	});
	
	function addIframe() {
		// 验证成功后，动态创建用于提交登录的 iframe
	    $('body').append($('<iframe/>').attr({
	    	style: "display:none;width:0;height:0", 
	    	id: "ssoLoginFrame",
	    	name: "ssoLoginFrame",
	    	src: "javascript:false;"
	    }));
	}
	
	function autoSubmit() {
		addIframe();
		$('#loginForm').submit();
	}
	// 登录处理回调函数，将由 iframe 中的页同自动回调
	var feedBackUrlCallBack = function (result) {
		customLoginCallBack(result);
		deleteIFrame('#ssoLoginFrame');// 删除用完的iframe,但是一定不要在回调前删除，Firefox可能有问题的
	};
	
	// 自定义登录回调逻辑
	var customLoginCallBack = function(result){
		// 登录失败，显示错误信息
		if (result.login == 'false'){
			$('#J_ErrorMsg').fadeOut().text(result.msg).fadeIn();
			// 重新刷新 login ticket
			flushLoginTicket();
		}else{
			//该处定义登录成功后需要执行的操作,比如刷新DIV等
			//alert("登陆成功");
		}
	}

	var deleteIFrame = function (iframeName) {
		var iframe = $(iframeName); 
		if (iframe) { // 删除用完的iframe，避免页面刷新或前进、后退时，重复执行该iframe的请求
			iframe.remove()
		}
	};

	// 由于一个 login ticket 只允许使用一次, 当每次登录需要调用该函数刷新 lt
	var flushLoginTicket = function(){
		var localurl = 'http://'+'<%=request.getServerName()%>'+':' + '<%=request.getServerPort()%>' + '<%=request.getContextPath()%>';
		var _services = 'service=' + encodeURIComponent(localurl + '/newui_index.do');
		var casUrl = '<s:property value="casurl" />' + '/ajax?'+_services+'&get-lt=true&n=' + new Date().getTime();
		var result = $.ajax({
		    type: "GET",
		    url: casUrl,
		    cache: false,
		    async : false,
		    success: function(data){
		    	var data = data.split('&');
		    	$("#J_LoginTicket").val(data[0]);
		    	$("#J_Execution").val(data[1].trim());
		    	var username = $('#J_Username').val().trim();
		    	var pattern = /^e\ds\d$/g;
		    	if (username != 'error username' && pattern.test(data[1].trim())) {
		    		autoSubmit();
		    	}
		    },
		    error : function(data) {
				//alert(JSON.stringify(data));
		    }
		});
	}
</script>
<body>
	<form id="loginForm" action="<s:property value="casurl" />/ajax" method="post" target="ssoLoginFrame">
	<ul>
		<li>
			<input name="username" id="J_Username" type="hidden" style="width: 180px" />
		</li>
		<li>
			<input name="password" type="hidden"  id="J_Password" style="width: 180px" />
		</li>
		<li>
			<input type="hidden" name="isajax" value="true" />
			<input type="hidden" name="isframe" value="true" />
			<input type="hidden" name="callback" value="feedBackUrlCallBack" />
			<input type="hidden" name="lt" value="1" id="J_LoginTicket">
			<input type="hidden" name="execution" id="J_Execution" value="" />
			<input type="hidden" name="_eventId" value="submit" />
		</li>
	</ul>
</form>
</body>
