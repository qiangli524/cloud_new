<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html:html locale="true">
	<head>
		<title></title>
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	</head>
	<body class="pop-body scrollbody">
				<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
					<tr>
			
						<td class="til">
							服务器名称 <font color="red">*</font>
						</td>
						<td>
							<s:textfield name="hostDetail.eq_name"  id="eq_name" disabled="true"/>
						</td>
						<td class="til">
							服务器类型 <font color="red">*</font>
						</td>
						<td>
		                   <s:select name="hostDetail.eq_type" id="eq_type" disabled="true" headerKey="" headerValue="请选择" list="#{'1':'IBM小机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器'}">
						  </s:select>
						</td>
					</tr>
	
					<tr>
					    <td class="til">
							服务器IP地址 <font color="red">*</font>
						</td>
						<td >
							<s:textfield name="hostDetail.eq_ip" id="eq_ip" disabled="true" />
						</td>
						<td class="til">
							服务器主机名称<font color="red">*</font>
						</td>
						<td>
	                        <s:textfield name="hostDetail.eq_hostname" id="eq_hostname" disabled="true"/>
						</td>
					</tr>
					<tr>
					    <td class="til">
							CPU芯数 <font color="red">*</font>
						</td>
						<td >
							 <s:textfield name="hostDetail.cpu_cl" id="cpu_cl" disabled="true"/>
						</td>
						<td class="til">
							内存(G)<font color="red">*</font>
						</td>
						<td>
	                        <s:textfield name="hostDetail.mem" id="mem" disabled="true"/>
						</td>
					</tr>
					<tr>
						<td class="til">
							存储(G) <font color="red">*</font>
						</td>
						<td >
							 <s:textfield name="hostDetail.store" id="store" disabled="true"/>
						</td>
						<td class="til">是否已分配<font color="red">*</font>
						</td>
						<td>
							<s:textfield name="hostDetail.allocated" id="allocated" disabled="true"/>
						</td>
					</tr> 
					<tr>
					    <td class="til">
							虚拟化类型<font color="red">*</font>
						</td>
						<td>
							<s:select name="hostObj.hasvertual" value="hostObj.hasvertual" id="hasvertual" headerKey="" headerValue="请选择" disabled="true" list="#{'0':'非虚拟化','1':'PowerVM','2':'KVM','3':'XEN','4':'VMWARE'}">
						</s:select>
						</td>
						<td class="til">
							所属机柜<font color="red">*</font>
						</td>
						<td>
							<s:textfield name="hostDetail.cubinetList" id="cubinetList" disabled="true"/>
						</td>
					</tr>
					<tr>
						<td class="til">监控方式
						</td>
						<td>
						<s:textfield name="hostDetail.PROTOCOL" id="PROTOCOL" disabled="true"/>
						</td>
						<td class="til">云平台能否管控<font color="red">*</font>
						</td>
						<td>
						<s:select name="hostObj.control" headerKey="-1" headerValue="请选择" list="#{'0':'不能管控','1':'能管控'}" id="control" disabled="true">
						</s:select>
						</td>
					</tr> 
				</table>
	</body>
</html:html>
