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
	    thisForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="interfacesinfo_saveInterface.do" id="theForm"
	method="post" cssClass="theForm">
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
	    <s:hidden name="theForm.ID" id="ID"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					设备主机ID:
				</td>
				<td>
					<s:select list="theForm.deviceList" listKey="ID" listValue="NAME_ZH" headerKey="0" headerValue="-请选择-" name="theForm.DEVICE_ID" id="DEVICE_ID"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					设备外围编号:
				</td>
				<td>
					<s:textfield name="theForm.CODE" cssClass="txt" id="CODE"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					端口状态:
				</td>
				<td>
					<s:select list="theForm.statusList" listKey="CODE" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.STATUS" id="STATUS"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					外围接口类型:
				</td>
				<td>
					<s:select list="theForm.typeList" listKey="CODE" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.TYPE" id="TYPE"></s:select>
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
