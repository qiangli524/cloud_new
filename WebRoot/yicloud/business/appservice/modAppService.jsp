<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.NAME.value = '';
		theForm.SELECT_TYPE.value ='';
		theForm.SELECT_UPPER_ID.value ='';
		theForm.SELECT_BIND_DEVICE.value = '';
	}

	function submitRequest(thisForm){  
	    thisForm.submit();
	}
</script>
</head>
<body>
<s:form action="business_saveAppService.do" id="theForm" method="post"
	cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
	<div class="scrollbody">
	<div class="query">
	<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="blue-wrap noborder">
	<div class="table-ct">
	<table width="100%" class="blue-table sorttable" border="0"
		cellspacing="0">
		<thead>
			<tr>
				<td class="til">业务应用名称:</td>
				<td>
				    <s:textfield name="theForm.NAME" cssClass="txt" id="NAME"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">类型:</td>
				<td>
				    <s:select list="theForm.businessTypeList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_TYPE" id="SELECT_TYPE"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">所属上级业务:</td>
				<td>
				    <s:select list="theForm.upperList" listKey="ID" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_UPPER_ID" id="SELECT_UPPER_ID"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">绑定设备主机ID:</td>
				<td>
				    <s:select list="theForm.bineDeviceList" listKey="ID" listValue="NAME_ZH" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_BIND_DEVICE" id="SELECT_BIND_DEVICE"></s:select>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="btnCenter"><input type="button"
					class="thickbox btn-style02" value="确定"
					onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
				<input type="button" class="thickbox btn-style02" value="重置"
					onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				<input type="button" class="thickbox btn-style02" value="返回"
					onclick="window.history.back()" /></td>
			</tr>
		</thead>
	</table>
	</div>
	</div>
	<!--blue-wrap end --></div>
	</div>
	<!--box end -->
</s:form>
</body>
