<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	var api = frameElement.api;
	 var w = api.opener;
	
	 api.button({
	     id:'Ok1',
	     name: '确定',
	     callback:saveSecurityConfig,
	     focus: true
	 },
	 {
	     id:'cancle1',
	     name: '取消'
	 }
	 );
	 
	 function resetForm(){
		 document.getElementById('theForm').reset();
	 }
	function saveSecurityConfig(theForm){
		var secObj = $("#theForm").serialize(); 
		w.saveSecurityConfigInParent(secObj);
	}

</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="user_saveSecurityConfig" method="post" cssStyle="theForm" id="theForm">
		<%-- 
		<s:hidden name="securityConfigObj.id" id="id"></s:hidden>
		--%>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						类型 <font color="red">*</font>
					</td>
					<td>
					<s:select list="#{'':'-请选择-','0':'-IP鉴权-','2':'-错误登录次数-'}"  name="securityConfigObj.type" id="type" cssClass="required"></s:select>
					</td>
				</tr>
				<tr>
				    <td class="til">
						状态<font color="red">*</font>
					</td>
					<td>
					<s:select list="#{'':'-请选择-','0':'-失效-','1':'-生效-'}" name="securityConfigObj.status" id="status" cssClass="required"></s:select>
					</td>
				</tr>
				<tr>
				    <td class="til">
						配置值<font color="red">*</font>
					</td>
					<td>
					<s:textarea name="securityConfigObj.value" cssStyle="txt" id="value" rows="3" cols="40" maxlength="300" cssClass="required"/>
					</td>
				</tr>
				
				<tr>
				    <td class="til">
						描述
					</td>
					<td colspan="3">
						<s:textarea name="securityConfigObj.desc" cssStyle="txt" rows="4" cols="60" maxlength="300"></s:textarea>
					</td>
				</tr>
			</table>
	</s:form>
</body>

</html:html>
