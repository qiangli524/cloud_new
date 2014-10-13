<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
<style type="text/css">
.blue-table .selfStyle{background-color:#EEAD0E;}
</style>
</head>
<body>
<s:form action="huaweientitytree_clusterInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
	        	<tr>
					<th colspan="4" class="selfStyle">
						基本信息
					</th>
				</tr>
				<tr>
	                <td class="til" align="left">
	                   	 名称
	                </td>
	                <td  align="left" colspan="3">
	                    <s:property value="treeForm.cluName"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	 描述
	                </td>
	                <td  align="left" colspan="3">
	                    <s:property value="treeForm.cluDesc"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	 HA
	                </td>
	                <td  align="left" colspan="3">
	                	<s:if test="treeForm.issEnableHa">
	                		启用
	                	</s:if>
	                	<s:else>
	                		未启用
	                	</s:else>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	 主机内存复用
	                </td>
	                <td  align="left" colspan="3">
	                	<s:if test="treeForm.issMemOvercommit">
	                		启用
	                	</s:if>
	                	<s:else>
	                		未启用
	                	</s:else>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	资源调度
	                </td>
	                <td  align="left" colspan="3">
	                	<s:if test="treeForm.issEnableDrs">
	                		启用
	                	</s:if>
	                	<s:else>
	                		未启用
	                	</s:else>
	                </td>
	            </tr>
	             <tr>
	                <td class="til" align="left">
	                   	自动化级别
	                </td>
	                <td  align="left" colspan="3">
	                	<s:if test="treeForm.drsLevel==1">
	                		手动
	                	</s:if>
	                	<s:elseif test="treeForm.drsLevel==3">
	                		自动
	                	</s:elseif>
	                </td>
	            </tr>
	             <tr>
	                <td class="til" align="left">
	                   	迁移阀值
	                </td>
	                <td  align="left" colspan="3">
	                	<s:if test="treeForm.drsLimen==1">
	                		保守
	                	</s:if>
	                	<s:elseif test="treeForm.drsLimen==5">
	                		中等
	                	</s:elseif>
	                	<s:elseif test="treeForm.drsLimen==9">
	                		激进
	                	</s:elseif>
	                </td>
	            </tr>
	            <tr>
					<th colspan="4" class="selfStyle">
						统计信息
					</th>
				</tr>
	            <tr>
	                <td class="til" width="20%" align="left">
						主机个数
	                </td>
	                <td align="left" colspan="3">
	                    <s:property value="treeForm.cluHostSize"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	 虚拟机个数
	                </td>
	                <td align="left" colspan="3">
	                    <s:property value="treeForm.cluVmSize"/>
	                </td>
	            </tr>
	        </table>
		</div>
</s:form>
</body>
