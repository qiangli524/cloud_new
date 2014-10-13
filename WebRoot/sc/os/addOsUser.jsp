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
	$(document).ready(function() {
		var api = frameElement.api;
		w = api.opener;
		
		$("#saveButton").click(function() {
			var _user_id = $("#user_id").val();
			if (_user_id == null|| _user_id == '') {
				alert('ID是必填项');
				$("#user_id").focus();
				return false;
			}		
			var _user_name = $("#user_name").val();
			if (_user_name == null|| _user_name == '') {
				alert('用户名是必填项');
				$("#user_name").focus();
				return false;
			}
			var _pass_word = $("#pass_word").val();
			if (_pass_word == null|| _pass_word == '') {
				alert('密码是必填项');
				$("#pass_word").focus();
				return false;
			}
			var _primary_group = $("#primary_group").val();
			if (_primary_group == null|| _primary_group == '') {
				alert('主组是必选项');
				$("#primary_group").focus();
				return false;
			}			
			var _home_dir = $("#home_dir").val();
			if (_home_dir != null&& _home_dir != '') {
				if(!/()^\/[a-zA-Z\/]+$/.test(_home_dir)){
					alert('家目录格式不规范');
					$("#home_dir").val("");
					$("#home_dir").focus();
					return false;
				}				
			}
			w._save_button_click_event($("#theForm").serialize());
		});
		
		$("#resetButton").click(function (){
			$("#user_id").val("");
			$("#user_name").val("");
			$("#pass_word").val("");
			$("#primary_group").val(""); 
			$("#home_dir").val(""); 
			$("#user_desc").val(""); 
		});	
		
		$("#user_name").change(function(){
			var _user_name = $("#user_name").val();
			if (_user_name != null && _user_name != '') {
				$.ajax({
					type : 'post',
					url : 'osUser_validUser.do?' + $("#theForm").serialize(),
					success : function(msg) {
						if (msg == -1) {
							$("#user_name").val("用户" + _user_name + "已存在");
							$("#user_name").css("color","red");
						}else if(msg ==-2){
							$("#user_name").val("用户" + _user_name + "已存在");
							$("#user_name").css("color","red");
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
      <h2 class="utt-1">用户配置</h2>
         <div class="bord-1 pd-10">
			<s:form action="osUser_queryUser.do" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
				<s:hidden name="theForm.os_host_id" id="os_host_id"></s:hidden>
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>							
							<td class="til">
								ID<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.user_id" id="user_id" maxlength="30" cssClass="inpt-2"/>
							</td>
						    <td class="til">
								用户名<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.user_name" id="user_name" maxlength="30" cssClass="inpt-2"/>            
							</td>
						</tr>
						<tr>
							<td class="til">
								口令<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.pass_word" id="pass_word" maxlength="30" cssClass="inpt-2"/>
							</td>
							<td class="til">
								主组<font color="red">*</font>
							</td>
							<td>
								<s:select name="theForm.primary_group" id="primary_group" cssClass="select-1"
									listValue="group_name " listKey="id" list="theForm.ogList" headerKey="" headerValue="---请选择---"/>
							</td>
						</tr>	
						<tr>
							 <td class="til">
								家目录
							</td>
							<td>
								<s:textfield name="theForm.home_dir" id="home_dir" maxlength="30" cssClass="inpt-2"/> 
							</td>
							 <td class="til">
								备注
							</td>
							<td>
								<s:textarea name="theForm.user_desc" id="user_desc" maxlength="30" cssClass="textarea-1"/> 
							</td>
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
