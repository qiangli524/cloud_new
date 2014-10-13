<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript">
	
	function resetForm(theForm){
		$("#host_name").attr("value","");
		$("#os_template").attr("value","");
		$("#swap_type").attr("value","");
	}

	function nextStep(thisForm) { 
		var _host_name = $("#host_name").val();
		if (_host_name == null|| _host_name == '') {
			alert('主机名称是必填项');
			$("#host_name").focus();
			return false;
		}
		var _os_template = $("#os_template").val();
		if (_os_template == null|| _os_template == '') {
			alert('系统模版是必填项');
			$("#os_template").focus();
			return false;
		}
		/** 增加Part，VG配置，取消SWAP选择
		var _swap_type = $("#swap_type").val();
		if (_swap_type == null|| _swap_type == '') {
			alert('SWAP是必填项');
			$("#swap_type").focus();
			return false;
		}*/
		$.ajax({
			type : 'post',
			url : 'autoos_saveConfigOsBase.do?'+$("#theForm").serialize(),
			success : function(msg) {
				if (msg == -1) {
					alert("信息保存失败！");
				} else {
					thisForm.action = "hostAdapter_queryHostAdapter.do";
					thisForm.submit();
				}
			}
		});
	}

</script>
</head>
<body>
<div class="mainbody">
<s:form action="osTemplate_showOsTemplate.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.id" id="id"></s:hidden>
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">基础配置</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="mgl-20 vm">序 列 号：</label>				
				<s:textfield name="theForm.serial_num" id="serial_num" disabled="true" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<label class="mgl-20 vm">物理位置：</label>				
				<s:textfield name="theForm.host_physical_position" id="host_physical_position" disabled="true" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>							
				<label class="mgl-20 vm">IPMI IP：</label>	
				<s:textfield name="theForm.mge_console_ip" id="mge_console_ip" disabled="true" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>				
				<label class="mgl-20 vm">IPMI用户：</label>
				<s:textfield name="theForm.mge_console_username" id="mge_console_username" disabled="true" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
			</div>
			<div class="clearfix mgt-10">
				<label class="mgl-20 vm">主机名称 <font color="red">*</font></label>
				<s:textfield name="theForm.host_name" id="host_name" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<label class="mgl-20 vm">系统安装模版 <font color="red">*</font></label>
				<s:select name="theForm.os_template" id="os_template" cssClass="select-1"
					listValue="templ_name " listKey="id " list="theForm.templList" headerKey="" headerValue="----请选择----"/>
				<%-- 
				<label class="mgl-20 vm">swap设置<font color="red">*</font></label>
				<s:select id="swap_type" name="theForm.swap_type" cssClass="select-1 vm" headerValue="------请选择------" headerKey=""
					list="#{'1':'swap32G,1块盘（2块底层做1+0)','2':'swap32G,2块盘（4块底层做1+0)','3':'swap64G,1块盘（2块底层做1+0)','4':'swap64G,2块盘（4块底层做1+0)'}"></s:select>
				--%>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:nextStep(document.getElementById('theForm'))" value="下一步" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
		</div>
		
</s:form>
</div>
</body>
</html:html>

