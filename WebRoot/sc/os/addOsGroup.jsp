<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var api = frameElement.api;
		w = api.opener;
		
		var flag = true;
		
		$("#saveButton").click(function() {
			
			var _group_id = $("#group_id").val();
			if (_group_id == null|| _group_id == '') {
				alert('组ID是必填项');
				$("#group_id").focus();
				return false;
			}else if(!/^\d+$/.test(_group_id)){
				alert('组ID必须是正整数');
				$("#group_id").val("");
				$("#group_id").focus();
				return false;
			}
			
			var _group_name = $("#group_name").val();
			if (_group_name == null|| _group_name == '') {
				alert('组名称是必填项');
				$("#group_name").focus();
				return false;
			}else if(!/^[a-zA-Z]+$/.test(_group_name)){
				alert('组名称格式不规范');
				$("#group_name").val("");
				$("#group_name").focus();
				return false; 
			}
			w._save_button_click_event($("#theForm").serialize());
		});
		
		$("#resetButton").click(function (){
			$("#group_id").val("");
			$("#group_name").val("");
			$("#group_desc").val("");
		});		
		
		$("#group_id").change(function(){
			var _group_id = $("#group_id").val();
			var _group_name = $("#group_name").val();
			if (_group_id != null && _group_id != '') {
				$.ajax({
					type : 'post',
					url : 'osGroup_validGroup.do?' + $("#theForm").serialize(),
					success : function(msg) {
						if (msg < 0) {
							flag = false;
							$("#group_id").val("组ID" + _group_id + "已存在");
							$("#group_id").css("color","red");
						}
					}
				});
			}else if (_group_name != null && _group_name != '') {
				$.ajax({
					type : 'post',
					url : 'osGroup_validGroup.do?' + $("#theForm").serialize(),
					success : function(msg) {
						if (msg < 0) {
							flag = false;
							$("#group_name").val("组名" + _group_name + "已存在");
							$("#group_name").css("color","red");
						}
					}
				});
			}
		});	
		
		$("#group_name").change(function(){
			var _group_id = $("#group_id").val();
			var _group_name = $("#group_name").val();
			if (_group_id != null && _group_id != '') {
				$.ajax({
					type : 'post',
					url : 'osGroup_validGroup.do?' + $("#theForm").serialize(),
					success : function(msg) {
						if (msg < 0) {
							flag = false;
							$("#group_id").val("组ID" + _group_id + "已存在");
							$("#group_id").css("color","red");
						}
					}
				});
			}else if (_group_name != null && _group_name != '') {
				$.ajax({
					type : 'post',
					url : 'osGroup_validGroup.do?' + $("#theForm").serialize(),
					success : function(msg) {
						if (msg < 0) {
							flag = false;
							$("#group_name").val("组名" + _group_name + "已存在");
							$("#group_name").css("color","red");
						}
					}
				});
			}
		});	
	});		
	
</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">用户组配置</h2>
         <div class="bord-1 pd-10">
			<s:form action="osGroup_saveGroup.do" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
				<s:hidden name="theForm.os_host_id" id="os_host_id"></s:hidden>
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>
						    <td class="til">
								组ID <font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.group_id" id="group_id" maxlength="30" cssClass="inpt-2"/>            
							</td>
							<td class="til">
								组名<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.group_name" id="group_name" maxlength="30" cssClass="inpt-2"/>
							</td>
						</tr>
						<tr>
							 <td class="til">
								备注
							</td>
							<td>
								<s:textarea name="theForm.group_desc" id="group_desc" maxlength="30" cssClass="textarea-1"/> 
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="4" class="btnCenter">
								<span class="ubtn-green"><input type="button"  value="确定" id="saveButton"/></span>
								<span class="ubtn-orange mgl-20"><input type="button"  value="重置" id="resetButton"/></span>
							</td>
						</tr>
					</table>
			</s:form>
	    	</div>
        </div>
    </div>
</body>

</html:html>
