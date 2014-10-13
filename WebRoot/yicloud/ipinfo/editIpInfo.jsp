<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<head>
 <s:head theme="xhtml"/>  
 <sx:head parseContent="true"/>  
<title></title>
<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})		
	
	function submitRequest(){
		theForm.action = 'ipinfo_saveIpinfo.do'
		theForm.submit();
	}
   	function searchRequest() { 
		theForm.submit();
 	} 	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="ipinfo_saveIpinfo.do" method="post" cssStyle="theForm" id="theForm">
  <s:hidden name="theForm.flag" id="flag"></s:hidden>
  <s:hidden name="theForm.IP_ID" id="IP_ID"></s:hidden>
<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					是否使用:
				</td>
				<td>
					<s:select list="theForm.usedList" listKey="CODE" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.ISUSED" id="ISUSED"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					是否阻塞:
				</td>
				<td>
					<s:select list="theForm.blockList" listKey="CODE" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.ISBLOCKED" id="ISBLOCKED"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					IP地址:
				</td>
				<td>
					<s:textfield name="theForm.IPADDRESS" cssClass="txt" id="IPADDRESS"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					网络ID:
				</td>
				<td>
					<s:textfield name="theForm.NET_ID" cssClass="txt" id="NET_ID"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					时间:
				</td>
				<td>
				<s:textfield name="theForm.INS_DATE" size="10" readonly="true" cssClass="txt" id="datepicker2"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					IP状态:
				</td>
				<td>
					<s:select list="theForm.typeList" listKey="CODE" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.IP_TYPE" id="IP_TYPE"></s:select>
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
