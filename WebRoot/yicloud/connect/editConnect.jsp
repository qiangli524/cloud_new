<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){    
		thisForm.action='connectinfo_saveConnect.do';
	    thisForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="connectinfo_modConnect.do" method="post" id="theForm"
	cssStyle="theForm">
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					A端外围接口:
				</td>
				<td>
					<s:select list="theForm.interfaceList" listKey="ID" listValue="INTERFACE_A_NAME" headerKey="0" headerValue="-请选择-" name="theForm.INTERFACE_A" id="INTERFACE_A"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					Z端外围接口:
				</td>
				<td>
					<s:select list="theForm.interfaceList" listKey="ID" listValue="INTERFACE_A_NAME" headerKey="0" headerValue="-请选择-" name="theForm.INTERFACE_Z" id="INTERFACE_Z"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					连通状态:
				</td>
				<td>
					<s:select list="theForm.connectstatusList" listKey="CODE" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.STATUS" id="STATUS"></s:select>
				</td>
			</tr>

			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="window.history.back()" />
				</td>
			</tr>
		</table>
	</s:form>
</body>

