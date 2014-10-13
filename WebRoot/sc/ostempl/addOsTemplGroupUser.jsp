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
		function submitRequest(thisForm){		
			var _group_id = $("#group_id").val();
			if(_group_id == null || _group_id == ''){
				alert("组是必选项");
				$("#group_id").focus();
				return false;
			}
			var _user_id = $("#user_id").val();
			if(_user_id == null || _user_id == ''){
				alert("用户是必选项");
				$("#user_id").focus();
				return false;
			}
			$.ajax({
				type : 'post',
				url : 'osTemplComp_validOsTemplateGroupUser.do?' + $("#theForm").serialize(),
				success : function(msg) {
					if (msg == -1) {
						alert("用户" + _user_id + ",已添加组" + _group_id);
					} else {
						thisForm.submit();
					}
				}
			});
		}
	</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">附加组配置</h2>
         <div class="bord-1 pd-10">
			<s:form action="osTemplComp_saveOsTemplateGroupUser.do" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.id" id="id"></s:hidden>
				<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>
							<td class="til">
								用户名<font color="red">*</font>
							</td>
							<td>
								<s:select name="theForm.user_id" id="user_id" cssClass="select-1"
									listValue="user_name" listKey="id" list="theForm.userList" headerKey="" headerValue="---请选择---"/>
							
							</td>
						    <td class="til">
								附加组<font color="red">*</font>
							</td>
							<td>
								<s:select name="theForm.group_id" id="group_id" cssClass="select-1"
									listValue="group_name " listKey="id " list="theForm.groupList" headerKey="" headerValue="---请选择---"/>
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
								<span class="ubtn-green"><input type="button"  value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" /></span>
								<span class="ubtn-orange mgl-20"><input type="button"  value="重置" onclick="javascript:void(document.getElementById('theForm').reset());return false;"></span>
								<span class="ubtn-blue mgl-20"><input type="button"  value="返回" onclick="window.history.back()"/></span>
							</td>
						</tr>
					</table>
			</s:form>
	    	</div>
        </div>
    </div>
</body>

</html:html>
