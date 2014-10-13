<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>

<head>
<style type="text/css">
.blue-table .selfStyle{background-color:#EEAD0E;}
</style>
</head>
<body>
    <div class="table-ct">
        <table width="100%" border="0" cellspacing="0" class="pop-table nosize">
        	<!-- 
        	<tr>
				<th colspan="2" class="selfStyle">
					基本信息
				</th>
			</tr>
			<tr>
                <td class="til" align="left">
                   	 名称
                </td>
                <td  align="left" colspan="3">
                    <s:property value="#request.clusterCount"/>
                </td>
            </tr>
            <tr>
                <td class="til" align="left">
                   	 描述
                </td>
                <td  align="left" colspan="3">
                    <s:property value="#request.clusterCount"/>
                </td>
            </tr>
            <tr>
				<th colspan="2" class="selfStyle">
					统计信息
				</th>
			</tr>
			 -->
            <tr>
                <td class="til" align="left">
                   	 集群个数
                </td>
                <td  align="left" colspan="3">
                    <s:property value="treeForm.dcClusterSize"/>
                </td>
            </tr>
            <tr>
                <td class="til" width="20%" align="left">
					主机个数
                </td>
                <td align="left" colspan="3">
                    <s:property value="treeForm.dcHostSize"/>
                </td>
            </tr>
            <tr>
                <td class="til" align="left">
                   	 虚拟机个数
                </td>
                <td align="left" colspan="3">
                    <s:property value="treeForm.dcVmSize"/>
                </td>
            </tr>
        </table>
    </div>
</body>