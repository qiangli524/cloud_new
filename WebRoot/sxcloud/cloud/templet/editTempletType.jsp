<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%> 
<head>
<title></title>
<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})		
	
	function submitRequest(){
		theTypeForm.action = 'templet_saveTempletType.do'
		theTypeForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="templet_saveTempletType.do" method="post" cssStyle="theForm" id="theTypeForm">
  <s:hidden name="theTypeForm.flag" id="flag"></s:hidden>
  <s:hidden name="theTypeForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
	<div>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						镜像类型名称
					</td>
					<td class="til">
						<s:textfield name="theTypeForm.NAME" cssClass="txt" id="NAME"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						镜像类型描述
					</td>
					<td colspan="3">
						<s:textarea name="theTypeForm.TYPE_DESC" cols="77" rows="3" id="TYPE_DESC"></s:textarea>
					</td>
				</tr>
			</table>
		</div>	
		
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">	
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest();return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="window.history.go(-1)" />
				</td>
			</tr>
		</table>
</div>
</s:form>
</body>
