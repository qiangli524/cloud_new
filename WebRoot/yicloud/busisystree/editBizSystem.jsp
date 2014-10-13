<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<head>
	<title></title>

<script type="text/javascript">
	function submitRequest(thisForm) {

		if (thisForm.SYS_NAME.value.length == 0) {
			alert("请输入应用名称！");
			return false;
		}
		thisForm.submit();
	}
</script>
</head>
<body class="mainbody">
<s:form action="bizsystem_saveBizSystem.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<s:hidden name="theForm.SYS_ID" id="SYS_ID"></s:hidden>
			<table width="350px" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						名称 <font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="theForm.name" cssClass="txt" id="name"></s:textfield>                 
					</td>
				</tr>
<%--				<tr>--%>
<%--					<td class="til">--%>
<%--						接入域<font color="red">*</font>--%>
<%--					</td>--%>
<%--					<td>--%>
<%--							<s:select list="theForm.regionList" name="theForm.REGION_ID" id="REGION_ID" listKey="REGION_ID" listValue="REGION_NAME" headerKey="" headerValue="-请选择-"></s:select>--%>
<%--					</td>--%>
<%--				</tr>--%>
				<tr>
					<td class="til">
						状态
					</td>
					<td>
						<s:select list="#{'':'-请选择-','0':'正常','1':'异常告警','2':'已停止'}" name="theForm.bizStatus" id="bizStatus"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						描述<font color="red"></font>
					</td>
					 <td colspan="3">
						<s:textarea name="theForm.bizDesc" id="bizDesc" cols="35" rows="4"></s:textarea>
					</td>          
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>
			</table>
</s:form>
</body>
