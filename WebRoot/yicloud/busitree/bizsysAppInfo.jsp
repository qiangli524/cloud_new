<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
</head>
<body>
<s:form action="busitree_bizsysAppInfo" method="post" id="theForm">
<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til" width="10%" align="left">
					应用名称：                
				</td>
				<td>
					<s:property value="theForm.name"/>
				</td>
			</tr>
			<tr>
				<td class="til" width="10%" align="left">
					状态：    
				</td>
				<td>
					<s:if test="theForm.status==1">已注册</s:if>
					<s:elseif test="theForm.status==2">部署</s:elseif>
					<s:elseif test="theForm.status==3">已部署</s:elseif>
					<s:elseif test="theForm.status==4">注销(卸载)</s:elseif>
					<s:elseif test="theForm.status==5">已注销(已卸载)</s:elseif>
					<s:elseif test="theForm.status==6">升级</s:elseif>
					<s:elseif test="theForm.status==7">已升级</s:elseif>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					基准主机IP：                
				</td>
				<td>
					<s:property value="theForm.standardHostIP"/>
				</td>
			</tr>
<!--			<tr>-->
<!--				<td class="til" align="left">-->
<!--					应用部署路径                -->
<!--				</td>-->
<!--				<td>-->
<!--					<s:property value="theForm.deployPath"/>-->
<!--				</td>-->
<!--			</tr>-->
			<tr>
				<td class="til" align="left">
					基准主机部署路径：                
				</td>
				<td>
					<s:property value="theForm.standardPath"/>
				</td>
			</tr>
<!--			<tr>-->
<!--				<td class="til" align="left">-->
<!--					启用脚本                -->
<!--				</td>-->
<!--				<td>-->
<!--					<s:property value="theForm.startsh"/>-->
<!--				</td>-->
<!--			</tr>-->
<!--			<tr>-->
<!--				<td class="til" align="left">-->
<!--					停止脚本                              -->
<!--				</td>-->
<!--				<td>-->
<!--					<s:property value="theForm.stopsh"/>-->
<!--				</td>-->
<!--			</tr>-->
			<tr>
				<td class="til" align="left">
					备注：
				</td>
				<td>
					<s:property value="theForm.desc"/>
				</td>
			</tr>
			</table>
		</div>
</s:form>
</body>
