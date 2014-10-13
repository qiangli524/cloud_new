<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/view.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
  </head>
  <body  class="pop-body scrollbody">
  	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" >
			<tr>
				<td class="til" width="20%" align="left">
					交换机名称
				</td>
				<td>
					<s:property value="theForm.switch_name"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					型号
				</td>
				<td>
					<s:property value="theForm.switch_type"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					状态
				</td>
				<td>
					<s:if test="theForm.switch_status==0">unlock</s:if>
					<s:elseif test="theForm.switch_status==1">lock</s:elseif>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					管理Ip
				</td>
				<td>
					<s:property value="theForm.switch_ip"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					交换机厂商
				</td>
				<td>
					<s:property value="theForm.switch_oem"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					交换机版本
				</td>
				<td>
					<s:property value="theForm.switch_vision"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					交换机描述
				</td>
				<td>
					<s:property value="theForm.switch_des"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					登记时间
				</td>
				<td>
					<s:property value="theForm.insert_time"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					更新时间
				</td>
				<td>
					<s:property value="theForm.update_time"/>
				</td>
			</tr>
			</table>
		</div>
  </body>
</html>
