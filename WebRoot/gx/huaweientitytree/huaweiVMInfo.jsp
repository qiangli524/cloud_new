<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="huaweientitytree_vmInfo" method="post" id="theForm">
		<div  align="left">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
	                <td class="til" width="20%" align="left">
	                   	 名称
	                </td>
	                <td  align="left" colspan="3">
	                    <s:property value="treeForm.vmname"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	 ID
	                </td>
	                <td  align="left" colspan="3">
	                    <s:property value="treeForm.vmcode"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	 描述
	                </td>
	                <td  align="left" colspan="3">
	                    <s:property value="treeForm.vmdesc"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="til" align="left">
	                   	 状态
	                </td>
	                <td  align="left" colspan="3">
	                	<s:if test="treeForm.vmstatus=='running'">
	                		运行中
	                	</s:if>
	                	<s:elseif test="treeForm.vmstatus=='stopped'">
	                		已停止
	                	</s:elseif>
	                	<s:elseif test="treeForm.vmstatus=='unknown'">
	                		不明确
	                	</s:elseif>
	                	<s:elseif test="treeForm.vmstatus=='hibernated'">
	                		已休眠
	                	</s:elseif>
	                	<s:elseif test="treeForm.vmstatus=='creating'">
	                		创建中或模板正在部署虚拟机或正在导入模板
	                	</s:elseif>
	                	<s:elseif test="treeForm.vmstatus=='shutting-down'">
						   	删除中
	                	</s:elseif>
	                	<s:elseif test="treeForm.vmstatus=='migrating'">
						   	迁移
	                	</s:elseif>
	                	<s:elseif test="treeForm.vmstatus=='fault-resuming'">
						  	 故障恢复中
	                	</s:elseif>
	                	<s:elseif test="treeForm.vmstatus=='starting'">
						  	 启动中
	                	</s:elseif>
	                	<s:elseif test="treeForm.vmstatus=='stopping'">
						  	 停止中
	                	</s:elseif>
	                	<s:elseif test="treeForm.vmstatus=='hibernating'">
						  	休眠中
	                	</s:elseif>
	                </td>
	            </tr>
	            <s:if test="#request.type==4">
	            	<tr>
		                <td class="til" align="left">
		                   	 tools状态
		                </td>
		                <td  align="left" colspan="3">
		                	<s:if test="treeForm.vmpvDriverStatus=='starting'">
		                		启动中
		                	</s:if>
		                	<s:elseif test="treeForm.vmpvDriverStatus=='notRunning'">
		                		未运行
		                	</s:elseif>
		                	<s:elseif test="treeForm.vmpvDriverStatus=='running'">
		                		正运行
		                	</s:elseif>
		                </td>
		            </tr>
	            </s:if>
	            <tr>
	                <td class="til" align="left">
	                   	所属集群/主机
	                </td>
	                <td  align="left" colspan="3">
	                	<s:property value="treeForm.vmlocationName"/>
	                </td>
	            </tr>
	            <s:if test="#request.type==4">
	            	<tr>
		                <td class="til" align="left">
		                   	运行主机
		                </td>
		                <td  align="left" colspan="3">
		                	<s:property value="treeForm.runningHostName"/>
		                </td>
		            </tr>
	            </s:if>
	            <tr>
	                <td class="til" align="left">
	                   	创建时间
	                </td>
	                <td  align="left" colspan="3">
	                	<s:property value="treeForm.vmcreateTime"/>
	                </td>
	            </tr>
	            <s:if test="#request.type==4">
	            	<tr>
		                <td class="til" align="left">
		                	 是否与主机绑定
		                </td>
		                <td  align="left" colspan="3">
		                	<s:if test="treeForm.vmisBindHost">是</s:if>
		                	<s:else>否</s:else>
		                </td>
		            </tr>
	            </s:if>
	        </table>
		</div>
</s:form>
</body>
