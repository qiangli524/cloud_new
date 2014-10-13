<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<head>
<style type="text/css">
.blue-table .selfStyle{background-color:#EEAD0E;}
</style>
</head>
<body>
<s:form action="huaweientitytree_hostInfo" method="post" id="theForm">
		<div  align="left">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
	        	<tr>
					<th colspan="4" class="selfStyle">
						基本信息
					</th>
				</tr>
				<tr>
	                <td class="til" width="20%" align="left">
	                   	 名称
	                </td>
	                <td  align="left" colspan="3">
	                    <s:property value="treeForm.hostName"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	 描述
	                </td>
	                <td  align="left" colspan="3">
	                    <s:property value="treeForm.hostDesc"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	 状态
	                </td>
	                <td  align="left" colspan="3">
	                	<s:if test="treeForm.hostStatus=='rebooting'">
	                		重启中
	                	</s:if>
	                	<s:elseif test="treeForm.hostStatus=='normal'">
	                		正常
	                	</s:elseif>
	                	<s:elseif test="treeForm.hostStatus=='fault'">
	                		故障
	                	</s:elseif>
	                	<s:elseif test="treeForm.hostStatus=='initial'">
	                		初始化
	                	</s:elseif>
	                	<s:elseif test="treeForm.hostStatus=='unknown'">
	                		未知
	                	</s:elseif>
	                	<s:elseif test="treeForm.hostStatus=='poweroff'">
						   	离线
	                	</s:elseif>
	                	<s:elseif test="treeForm.hostStatus=='booting'">
						   	上电中
	                	</s:elseif>
	                	<s:elseif test="treeForm.hostStatus=='shutdowning'">
						  	 下电中
	                	</s:elseif>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	 是否维护状态
	                </td>
	                <td  align="left" colspan="3">
	                	<s:if test="treeForm.hostIsMaintaining">
	                		是
	                	</s:if>
	                	<s:else>
	                		否
	                	</s:else>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	所属集群
	                </td>
	                <td  align="left" colspan="3">
	                	<s:property value="treeForm.hostClusterName"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	主机IP
	                </td>
	                <td  align="left" colspan="3">
	                	<s:property value="treeForm.hostIp"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	BMC IP
	                </td>
	                <td  align="left" colspan="3">
	                	<s:property value="treeForm.hostBmcIp"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                	 挂载光驱虚拟机
	                </td>
	                <td  align="left" colspan="3">
	                	<s:property value="treeForm.hostAttachedISOVM"/>
	                </td>
	            </tr>
	            <tr>
					<th colspan="4" class="selfStyle">
						统计信息
					</th>
				</tr>
	            <tr>
	                <td class="til" align="left">
	                   	 虚拟机个数
	                </td>
	                <td align="left" colspan="3">
	                    <s:property value="treeForm.hostVmSize"/>
	                </td>
	            </tr>
	        </table>
		</div>
</s:form>
</body>
