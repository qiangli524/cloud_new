<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
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
	function submitRequest(theForm){
		var name = theForm.name.value;
		if(name==null){
			alert("网络标签不能为空");
		}
		var vsName = '<s:property value="#request.NAME"/>';
		var hostName = '<%=request.getAttribute("hostName")%>'
		var vssType = '<%=request.getAttribute("vssType")%>';
		theForm.action='portgroup_savePortGroup.do?NAME='+vsName+'&hostName='+hostName +'&vssType='+vssType;
	    theForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="yvm_saveHost.do" method="post" id="theForm">
	<s:hidden name="thePGForm.flag" id="flag"></s:hidden>
	<s:hidden name="thePGForm.vSwitchId" id="vSwitchId"></s:hidden>
	<s:hidden name="thePGForm.id" id="id"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					网络标签：
				</td>
				<td>
					<s:textfield name="thePGForm.name" id="name" cssClass="txt"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					VLAN ID(可选)：
				</td>
				 <s:if test="#request.vlans!=null">
				 	<td>
						<s:select list="#request.vlans" name="thePGForm.vlanId">
						</s:select>
					</td>
				 </s:if>
				 <s:else>
					<td>
						<s:textfield name="thePGForm.vlanId" id="vlanId" cssClass="txt"/>
					</td>				 	
				 </s:else>
			</tr>	
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="window.history.back();" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
