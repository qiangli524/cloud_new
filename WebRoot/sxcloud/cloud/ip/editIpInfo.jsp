<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../common/link.jsp"%>
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
	
	
	
 	function backup() {
 	    theForm.action = 'listIpInfo.do' 
		theForm.submit();
 	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="ip_saveIpInfo" method="post" cssStyle="theForm" id="theForm">
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<s:hidden name="theForm.IP_ID" id="IP_ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					  <td class="til">
						是否使用 <font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'1':'是','0':'否'}" name="theForm.ISUSED" id="ISUSED"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						是否被阻塞 <font color="red">*</font>
					</td>
						<td>
						<s:select list="#{'1':'是','0':'否'}" name="theForm.ISBLOCKED" id="ISBLOCKED"></s:select>
					</td>
				</tr>
				<tr id="singleIp" style="display:">
					<td class="til">
						IP地址 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.IPADDRESS" id="IPADDRESS" cssStyle="txt"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						IP地址类型 <font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'1':'公网IP','2':'内网IP'}" name="theForm.IP_TYPE" id="IP_TYPE"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						所属网络名称 <font color="red">*</font>
					</td>
					<td>
					<s:select list="theForm.netList" headerKey="0" headerValue="-请选择-" listKey="NET_ID" listValue="NAME"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="javascript:backup();return false;" />
							
					</td>
				</tr>
			</table>
	</s:form>
</body>

</html:html>
