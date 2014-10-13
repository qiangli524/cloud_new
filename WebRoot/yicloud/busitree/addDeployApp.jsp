<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript">
		
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){
	    if(thisForm.HOSTID.value==0){
	     	alert("部署主机不能为空！");
	     	return false  ;
	    }
	    thisForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
    <s:form action="busitree_deployApp" method="post" cssStyle="theForm" id="theForm">
    	<s:hidden name="theForm.appId"  id="appId" value="%{#request.id}"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
		        <tr>
		        	<td class="til">
		        		应用名称 
		        	</td>
		        	<td>
		        	 	<s:property value="#request.name"/>
		        	</td>
				    <td class="til">
						所属Vlan:<font color="red">*</font>
					</td>
					<td>
					        <s:select list="#{'1':'Default VLAN','2':'www_web','3':'inside_web'}" name="theForm.VLAN" id="VLAN"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						中间件:<font color="red">*</font>
					</td>
					<td>
					    <s:select list="#{'0':'中间件1','1':'中间件2'}" name="theForm.KEYNAME" id="KEYNAME"></s:select>
					</td>
				    <td class="til">
						部署主机:<font color="red">*</font>
					</td>
					<td>
					    <s:select id="ID" list="theForm.resultList" listKey="ID" name="theForm.id" id="HOSTID" listValue="IP" headerKey="0" headerValue="-请选择-"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
							<span class="ubtn-green"><input type="button"  value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" /></span>
								<span class="ubtn-orange mgl-20"><input type="button"  value="重置" onclick="javascript:void(document.getElementById('theForm').reset());return false;"></span>	
					</td>
				</tr>

			</table>
	</s:form>
</body>
