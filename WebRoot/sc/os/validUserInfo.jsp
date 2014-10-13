<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript">
	$(document).ready(function() {
		var api = frameElement.api;
		w = api.opener;
		
		$("#valid_button").click(function(){
			var _id = $("#id").val();
			if (_id == null|| _id == '') {
				alert('主机ID为空，请联系管理员');
				$("#id").focus();
				return false;
			}
			var _mge_console_ip = $("#mge_console_ip").val();
			if (_mge_console_ip == null|| _mge_console_ip == '') {
				alert('IPMI管理IP为空，请联系管理员');
				$("#mge_console_ip").focus();
				return false;
			}
			var _mge_console_username = $("#mge_console_username").val();
			if (_mge_console_username == null|| _mge_console_username == '') {
				alert('IPMI管理用户为空，请联系管理员');
				$("#mge_console_username").focus();
				return false;
			} 
			var _mge_console_pass = $("#mge_console_pass").val();
			if (_mge_console_pass == null|| _mge_console_pass == '') {
				alert('IPMI管理密码是必填项');
				$("#mge_console_pass").focus();
				return false;
			}
			$.ajax({
				type : 'post',
				url : 'autoos_validUserPassWD.do?'+$("#theForm").serialize(),
				success : function(msg) {
					if (msg == 0) {//验证通过
						w.configAndInstallOs($("#id").val());
					}else if(msg == -1){//输入密码不正确
						alert("密码错误，请重新输入");
					}else if(msg == -2){//未设置管理密
						alert("主机没有设置管理密码，请联系管理员处理");
					}else{//其它问题，联系管理员处理
						alert("出现异常，请联系管理员处理");
					}
				}
			});					
		});
	});	
</script>
</head>
<body>
<div class="mainbody">
<s:form action="osTemplate_showOsTemplate.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.id" id="id"></s:hidden>
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">IPMI管理信息</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">	
				<label class="mgl-20 vm">IPMI&nbsp;&nbsp;&nbsp;&nbsp;IP：</label>	
				<s:textfield name="theForm.mge_console_ip" id="mge_console_ip" disabled="true" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>				
			</div>
			<div class="clearfix mgt-10">
				<label class="mgl-20 vm">IPMI用户：</label>
				<s:textfield name="theForm.mge_console_username" id="mge_console_username" disabled="true" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
			</div>
			<div class="clearfix mgt-10">
				<label class="mgl-20 vm">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码<font color="red">*</font>：</label>				
				<s:textfield name="theForm.mge_console_pass" id="mge_console_pass" disabled="false" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
			</div>			
			<div class="clearfix mgt-10" align="center">
				<span class="ubtn-1 mgl-20"><input type="button" id="valid_button" value="验证" /></span>
			</div>
		</div>		
</s:form>
</div>
</body>
</html:html>

