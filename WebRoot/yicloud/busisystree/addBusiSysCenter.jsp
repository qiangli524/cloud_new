<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<head>
	<title></title>
	<script type="text/javascript">
	</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="busisys_saveBusiSysCenter.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					业务中心名称：
				</td>
				<td>
					<s:textfield name="theForm.name" id="name" cssClass="txt"/>
				</td>
			</tr>
			<tr id="showValidateMess" style="display: none;">
				<td colspan="2" align="right"></td>
			</tr>
		</table>
	</s:form>
</body>
