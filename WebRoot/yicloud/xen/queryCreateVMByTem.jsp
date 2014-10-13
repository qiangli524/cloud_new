<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<link
		href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
		rel="stylesheet" type="text/css" />
	<link
		href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
		rel="stylesheet" type="text/css" />
	<link
		href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css"
		rel="stylesheet" type="text/css" />
	<!-- 进度条 -->
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/jquery-1.7.2.js"></script>
	<script type="text/javascript"
		src="sxcloud/js/progressbar/js/jquery.progressbar.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
				$("#spaceused1").progressBar({boxImage	:' <%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbar.gif', barImage: '<%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbg_green.gif',showText:false,height:7,width:150} );
				$("#spaceused4").progressBar({ boxImage	:' <%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbar.gif', barImage: '<%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbg_green.gif',showText:false,height:7,width:150} );
	});
	
	</script>

	<!-- 部署时的进度条--jquery ui -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.progressbar.js"></script>
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/demos.css" />
	<style>
	.ui-progressbar .ui-progressbar-value {
		background-image:
			url(sxcloud/js/jqueryui/themes/base/images/pbar-ani.gif);
	}
	</style>
	<script>
	var val;
	$(function() {
		$( "#progressbar" ).progressbar({
			value: 0
		});
	});
	updateProgressbarValue();
	function updateProgressbarValue() {
		setTimeout(updateProgressbarValue, 1000);
		$.getJSON("xen_getProgressbarVal.do",{'time':new Date().toString()},function(data){
			val = data.val;
		});
		if (val == 100) {
			$("#progressbar").progressbar( "value", val);
			return;
		}
		var current = $("#progressbar").progressbar("value");
		if (current >= val) {
			return;
		}
		var value = $("#progressbar").progressbar("value")+15;
		$("#progressbar").progressbar( "value", value);
	}
	</script>
	<script type="text/javascript">
	function deployVMRequest(theForm) {
		var name = theForm.NAME_EN.value;
		//var unit_name = $("#UNIT").find("option:selected").text();
		var parent_id = <%=request.getAttribute("parent_id")%>;
		var entity_id = <%=request.getAttribute("entity_id")%>;
		var clusterId = <%=request.getAttribute("clusterId")%>;
		var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
		var host_uuid = '<%=request.getAttribute("host_uuid")%>';
		
		if (name=='') {
			alert('虚拟机名称不能为空');
			return;
		}
		content = '虚拟机名称：' + name + '<br>' ;
		jConfirm(content,'确认信息', function(r){
		if(r==false){
			return false;
		}
		if(r==true){
		url = "xen_queryCreateVMByTem.do?name="+ encodeURI(encodeURI(name))  + "&entity_id=" + entity_id+
		"&parent_id="+parent_id+"&clusterId="+clusterId+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid;
		show();
		$.getJSON(url,{"time":new Date().toString()},function(data){
			if(data.responseCode == 1){
				alert("创建虚拟机成功!");
				window.parent.refreshParentNode();
				window.location.reload();
			}else if(data.responseCode == -1){
				alert("创建虚拟机失败!");
				window.location.reload();
			}
		});
		}
		})
	}
	
	function pageOnLoad() {
	}
	function show(){
        document.all.ly.style.display="block";  
     	document.all.ly.style.width=document.body.scrollWidth;//网页正文全文宽
     	document.all.ly.style.height=document.body.scrollHeight;//网页正文全文高
        document.all.divTest.style.display='block'; 
       
        document.getElementById("divTest").style.visibility="visible";
        /*var ss = document.getElementById("progressbar");
        ss.innerHTML= "<img src='sxcloud/js/progressbar/images/progressbg_yellow.gif'/>";*/
    }
	</script>
</head>
<body onload="pageOnLoad()">
<s:form action="xen_hostInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="20%" align="left"> 
						虚拟机名称
					</td>
					<td  align="left">
						<s:textfield name="theForm.NAME_EN"  id="NAME_EN"/>
					</td>
				</tr> 
				
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="提交"
							id="confirm"
							onclick="javascript:deployVMRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:resetForm(document.getElementById('theForm'))" />
					</td>

				</tr>
			</table>
		</div>
		<!-- 创建虚拟机进度条 -->
		<div id="divTest"
			style="position: absolute; z-index: 10; width: 310; height: 30px; background-color: Yellow; display: none; top: 15%; left: 30%;">
			<table border="1" width="50%" cellspacing="0" cellpadding="0"
				style="border-collapse: collapse" bgcolor="#FFFFEC" height="50"
				align="center">
				<tr>
					<td bgcolor="#3399FF" style="font-size: 12px; color: #ffffff"
						height=5>
						虚拟机创建中...
					</td>
				</tr>
				<tr>
					<td style="" align="center">
						<div id="progressbar"></div>
					</td>
				</tr>
			</table>
		</div>
		<div id="ly"
			style="position: absolute; top: 0px; filter: alpha(opacity =   10); background-color: #777; z-index: 2; left: 0px; display: none;">

		</div>
		<div id="main" style="background-color: #FFFFFF; height: 500px;">
		</div>
</s:form>
</body>
</html:html>
<script
	src="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.alerts.js"></script>
<link
	href="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.alerts.css"
	rel="stylesheet" type="text/css" />
