<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
</script>
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
</head>
<body>
<s:form action="busitree_bizsysAppInfo" method="post" id="theForm">
<div class="scrollbody">
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table width="100%" class="pop-table nosize" border="0" cellspacing="0">
			  		<tr>
			  			<td class="til"  width="10%">
			  				基准应用名称：
			  			</td>
			  			<td>
			  				<s:property value="theForm.name"/>
			  			</td>
			  		</tr>
			  		<tr>
			  			<td class="til"  width="10%">
			  				基准机IP：
			  			</td>
			  			<td>
			  				<s:property value="theForm.standardHostIP"/>
			  			</td>
			  		</tr>
			  		<tr>
			  			<td class="til"  width="10%">
			  				基准机应用基准路径：
			  			</td>
			  			<td>
			  				<s:property value="theForm.standardPath"/>
			  			</td>
			  		</tr>
			  		<tr>
			  			<td class="til"  width="10%">
			  				部署机上线文件路径
			  			</td>
			  			<td>
			  				<s:property value="theForm.deployPath"/>
			  			</td>
			  		<tr>
			  			<td class="til"  width="10%">
			  				启动脚本：
			  			</td>
			  			<td>
			  				<s:property value="theForm.startsh"/>
			  			</td>
			  		</tr>
			  		<tr>
			  			<td class="til"  width="10%">
			  				停止脚本：
			  			</td>
			  			<td>
			  				<s:property value="theForm.stopsh"/>
			  			</td>
			  		</tr>
			</table>
		</div>
</div>
</s:form>
</body>
