<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>

<head>
</head>
<body>
    <div class="table-ct">
        <table width="100%" border="0" cellspacing="0" class="pop-table nosize">
            <tr>
                <td class="til" align="left">
                   	 集群个数
                </td>
                <td  align="left" colspan="3">
                    <s:property value="#request.clusterCount"/>
                </td>
            </tr>
            <tr>
                <td class="til" width="20%" align="left">
					主机个数
                </td>
                <td align="left" colspan="3">
                    <s:property value="#request.hostCount"/>
                </td>
            </tr>
            <tr>
                <td class="til" align="left">
                    虚拟机个数
                </td>
                <td align="left" colspan="3">
                    <s:property value="#request.vmCount"/>
                </td>
            </tr>
<%--            <tr>
            	<td class="til" align="left" rowspan="3">
                   	虚拟机状态统计
                </td>
                <td align="left" width="150px">
					正在运行个数
                </td>
                <td align="left">
                    0
                </td>
                <tr>
                	<td align="left" width="150px">
						已停止个数
	                </td>
	                <td align="left">
	                    0
	                </td>
                </tr>
                <tr>
                	<td align="left" width="150px">
						挂起个数
	                </td>
	                <td align="left">
	                    0
	                </td>
	                
                </tr>
            </tr>
--%>
       <%--      <tr>
                <td class="til" align="left">
                    网络个数
                </td>
                <td align="left">
                    <s:property value="#request.netCount"/>
                </td>
            </tr> --%>
            <tr>
                <td class="til" align="left">
                    存储个数
                </td>
                <td align="left" colspan="3">
                    <s:property value="#request.dsCount"/>
                </td>
            </tr>
        </table>
    </div>
</body>