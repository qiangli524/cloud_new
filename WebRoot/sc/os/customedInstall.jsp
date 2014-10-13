<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/demos.css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<style>
	/**
		   .iframetab { 
		        width:100%; 
		        height:auto; 
		        border:none;
		        margin:0px; 
		        position:relative; 
		        top:0px;
		        background：none;
		    } 
		*/
	.iframetab {
		width: 100%;
		height:80%;
		border: none;
		margin:0px; 
        position:relative; 
        top:0px;
		background：none;
	}
	
	.ui-tabs-panel {
		padding: 5px !important;
	}
	
	.openout {
		float: right;
		position: relative;
		top: -28px;
		left: -5px;
	}
	</style>
<script>
	$(document).ready(function() {
		var api = frameElement.api;
		w = api.opener;
		
		var $tabs = $('#tabs').tabs({
			ajaxOptions : {
				cache : false,
				error : function(xhr, status, index, anchor) {
					$(anchor.hash).html("数据加载失败,请重试！");
				}
			}
		});

		$("a.tabref").click(function() {
			loadTabFrame($(this).attr("href"), $(this).attr("rel"));
		});

		function loadTabFrame(tab, url) {
			if ($(tab).find("iframe").length == 0) {
				var html = [];
				html.push('<div class="tabIframeWrapper">');
				html.push('<div class="openout"><a href="' + url + '"></a></div><iframe class="iframetab" frameborder="0" allowtransparency="true" src="' + url + '">Load Failed?</iframe>');
				html.push('</div>');
				$(tab).append(html.join(""));
				$(tab).find("iframe").height($(window).height() / 2.5);
			}
			return false;
		}

		$("#_save_button").click(function() {
			var _host_name = $("#host_name").val();
			if (_host_name == null|| _host_name == '') {
				alert('请填写主机名');
				$("#host_name").focus();
				return false;
			}
			var _os_template = $("#os_template").val();
			if (_os_template == null|| _os_template == '') {
				alert('请选择操作系统');
				$("#os_template").focus();
				return false;
			}
			w._save_button_click_event($("#theForm").serialize());
		});

		$("#_install_button").click(function() {
			var _host_name = $("#host_name").val();
			if (_host_name == null|| _host_name == '') {
				alert('请填写主机名');
				$("#host_name").focus();
				return false;
			};
			var _os_template = $("#os_template").val();
			if (_os_template == null|| _os_template == '') {
				alert('请选择操作系统');
				$("#os_template").focus();
				return false;
			};
			w._install_button_click_event($("#theForm").serialize());
		});

		$("#_clear_button").click(function() {
			if (confirm("确认清空配置信息")) {
				w._clear_button_click_event($("#theForm").serialize());
			}
		});
	});
</script>

</head>
<body class="overflow-y: scroll;">
	<div class="table-ct" id="body_div">
		<s:form action="autoos_updateCustomedInstall.do" method="post"
			id="theForm" cssStyle="theForm">
			<s:hidden name="theForm.eq_id" id="eq_id"></s:hidden>
			<s:hidden name="theForm.id" id="id"></s:hidden>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr align="left">
					<td class="til">主机类型</td>
					<td>
						<s:select name="theForm.eq_type" id="eq_type" disabled="true"
							list="#{'2':'IBMx86刀片','3':'IBM PC服务器','4':'HPx86刀片','6':'HP PC服务器','7':'HUAWEI PC服务器'}"/>
					</td>
					<td class="til">主机型号</td>
					<td>
						<s:textfield name="theForm.host_type_num" id="host_type_num" disabled="true"></s:textfield>
					</td>
				</tr>				
				<tr align="left">
					<td class="til">IPMI管理IP</td>
					<td>
						<s:textfield name="theForm.mge_console_ip" id="mge_console_ip" disabled="true"></s:textfield>
					</td>				
					<td class="til">序列号</td>
					<td>
						<s:textfield name="theForm.serial_num" id="serial_num" disabled="true"></s:textfield>
					</td>
				</tr>				
				<tr align="left">
					<td class="til">IPMI管理用户</td>
					<td>
						<s:textfield name="theForm.mge_console_username" id="mge_console_username" disabled="true"></s:textfield>
					</td>
					<%--
					<td class="til">IPMI管理密码</td>
					<td>
						<s:textfield type="password" name="theForm.mge_console_pass" id="mge_console_pass" disabled="true"></s:textfield>
					</td>
					 --%>
					<td class="til">主机名 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.host_name" id="host_name"></s:textfield>
					</td>
				</tr>
				<tr align="left">					
					<td class="til">
						swap设置 <font color="red">*</font>
					</td>
					<td>
						<s:select id="os_template" name="theForm.os_template"
							list="#{'1':'swap32G,1块盘（2块底层做1+0)','2':'swap32G,2块盘（4块底层做1+0)','3':'swap64G,1块盘（2块底层做1+0)','4':'swap64G,2块盘（4块底层做1+0)'}"></s:select>
					</td>
					<td class="til">安装模版<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.host_name" id="host_name"></s:textfield>
					</td>
				</tr>
			</table>
	</div>
	<div id="tabs">
		<ul>
			<li><a class="tabref" href="#tabs-1"
				rel="hostAdapter_queryHostAdapter.do?os_host_id=<s:property value="theForm.id"/>">网络配置</a>
			</li>
			<li><a class="tabref" href="#tabs-2"
				rel="osFileSystem_queryFileSystem.do?os_host_id=<s:property value="theForm.id"/>">文件系统</a>
			</li>
			<li><a class="tabref" href="#tabs-3"
				rel="osGroup_queryGroup.do?os_host_id=<s:property value="theForm.id"/>">用户组</a>
			</li>
			<li><a class="tabref" href="#tabs-4"
				rel="osUser_queryUser.do?os_host_id=<s:property value="theForm.id"/>">用户</a>
			</li>
			<li><a class="tabref" href="#tabs-5"
				rel="osGroupUser_queryGroupUser.do?os_host_id=<s:property value="theForm.id"/>">附加组</a>
			</li>
		</ul>
		<!-- tab switching -->
		<div id="tabs-1"></div>
		<div id="tabs-2"></div>
		<div id="tabs-3"></div>
		<div id="tabs-4"></div>
		<div id="tabs-5"></div>
	</div>
	<div id="_button_divs" class="_button_divs_css">
		<div>
			<span class="ubtn-1 mgl-20">
				<input type="button" id="_save_button" name="_save_button" value="保存" /> 
			</span>
			<span class="ubtn-2 mgl-20">
				<input type="button" id="_install_button" name="_install_button" value="保存并安装" /> 
			</span>
			<span class="ubtn-1 mgl-20">
				<input type="button" id="_clear_button" name="_clear_button" value="清空配置数据" />
			</span>
		</div>
	</div>
	</s:form>
</body>