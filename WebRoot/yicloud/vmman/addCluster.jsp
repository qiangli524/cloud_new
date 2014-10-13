<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<html:html locale="true">
<head>
	<title></title>

	<script type="text/javascript">
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(theForm){ 
	if(theForm.CLUSTER_TYPE.value==0){
		alert("集群类型不能为空");
	}
	if(theForm.NAME.value==0){
		alert("集群名称不能为空");
	}
	  var ID = <%=request.getAttribute("ID")%>;
	  var TYPE =<%=request.getAttribute("TYPE")%>;
		theForm.action='yvm_saveCluster.do?ID='+ID+'&TYPE='+TYPE;  
	    theForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="yvm_saveCluster.do" method="post" id="theForm" cssStyle="theForm">
	<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
		<tr>
			<td class="til">集群类型</td>
			<td>
			<s:select list="#{'1':'VMWARE','2':'KVM'}" headerKey="0" headerValue="--请选择--" name="theForm.CLUSTER_TYPE" id="CLUSTER_TYPE"></s:select>
			</td>
		</tr>
		<tr>
			<td class="til">集群名称</td>
			<td><s:textfield name="theForm.NAME" id="NAME"/></td>
		</tr>
		
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
		</table>
		</div>
	</s:form>
</body>

</html:html>
