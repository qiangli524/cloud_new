<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#copyButton").click(function() {
			var _templ_name = $("#templ_name").val();
			if (_templ_name == null|| _templ_name == '') {
				alert('模版名称是必填项');
				$("#templ_name").focus();
				return false;
			}else{
				$.ajax({
					type : 'post',
					url : 'osTemplate_validateOsTemplateName.do?templ_name='+encodeURI(encodeURI(_templ_name)),
					success : function(msg) {
						if(msg == 'OK'){
							theForm.submit();
						}else if(msg == 'EXIT'){
							$("#templ_name").val("");
							$("#templ_name").focus();
							alert("模版【" + _templ_name + "】已存在");
						}else{
							alert(msg);
						}
					}
				});			
			}
		});
				
	});
	
	function returnOsTemplate(){
		theForm.action = "osTemplate_showOsTemplate.do";
		theForm.submit();
	}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="osTemplate_saveCopyOsTemplate.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.id" id="id"></s:hidden>
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">复制模版</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">模版名称<font color="red">*</font></label>
				<s:textfield name="theForm.templ_name" id="templ_name" cssClass="inpt-2" maxlength="30"></s:textfield>
				<font color="grey">（模版名称不能重复）</font>
				<span class="ubtn-orange mgl-20"><input type="button" id="copyButton" value="复制" /></span>
				<span class="ubtn-green mgl-20"><input type="button"  value="返回" onclick="javascript:returnOsTemplate();"/></span>
			</div>
		</div>
</s:form>
</div>
</body>
</html:html>
