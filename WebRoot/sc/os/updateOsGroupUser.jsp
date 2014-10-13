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
		
		$("#updateButton").click(function() {
			w._update_button_click_event($("#theForm").serialize());
		});
		
		$("#resetButton").click(function (){
			$("#group_name").val("");
			$("#user_name").val(""); 
			$("#mask").val("");  
		});		
	});	

</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">附加组配置</h2>
         <div class="bord-1 pd-10">
			<s:form action="osGroupUser_modifyGroupUser.do" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.id" id="id"></s:hidden>
				<s:hidden name="theForm.os_host_id" id="os_host_id"></s:hidden>
				<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>
							<td class="til">
								用户<font color="red">*</font>
							</td>
							<td>
								<s:select name="theForm.user_id" id="user_id" cssClass="select-1"
									listValue="user_name" listKey="id" list="theForm.ouList"  headerKey="" headerValue="---请选择---"/>
							
							</td>
						    <td class="til">
								附加组<font color="red">*</font>
							</td>
							<td>
								<s:select name="theForm.group_id" id="group_id" cssClass="select-1"
									listValue="group_name " listKey="id" list="theForm.ogList"  headerKey="" headerValue="---请选择---"/>
							</td>
						</tr>
						<tr>
							 <td class="til">
								备注
							</td>
							<td>
								<s:textarea name="theForm.mask" id="mask" maxlength="30" cssClass="textarea-1"/>
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="4" class="btnCenter">
								<span class="ubtn-green"><input type="button"  value="确定" id="updateButton"/></span>
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
