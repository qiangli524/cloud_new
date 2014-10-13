<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<html>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<head>
    <title>明细</title>
    <style type="text/css">
        div.hidden {
            width: 170px;
            height: 30px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            text-overflow: ellipsis; /* IE/Safari */
            -ms-text-overflow: ellipsis;
            -o-text-overflow: ellipsis; /* Opera */
            -moz-binding: url("ellipsis.xml#ellipsis"); /*FireFox*/
        }
    </style>
</head>
<body >
<div class="mainbody">
            <table id="theTable" width="100%" class="blue-table sorttable imgTableCenter" border="0" cellspacing="0">

                
                <tbody>
                <s:iterator value="#request.vminfoList" id="vminfo">
                <thead>
                	<tr>
                    	<th onclick="sort(theTable,1,'string')">属性</th>
                    	<th onclick="sort(theTable,2,'string')">内容</th>
                	</tr>
                </thead>
                	<tr>
 						<td>订单ID</td>
                     	<td><s:property value="#vminfo.OrderId" /></td>
                    </tr>
                    <tr>
 						<td>用戶ID</td>
                     	<td><s:property value="#vminfo.user_id" /></td>
                    </tr>
                    <tr>
 						<td>登陆账号</td>
                     	<td><s:property value="#vminfo.user_Name" /></td>
                    </tr>
                    <tr>
 						<td>用户电话</td>
                     	<td><s:property value="#vminfo.mobile" /></td>
                    </tr>
                    <tr>
 						<td>用户邮箱</td>
                     	<td><s:property value="#vminfo.email" /></td>
                    </tr>
                    <tr>
 						<td>用户类型</td>
                     	<td><s:property value="#vminfo.user_Type" /></td>
                    </tr>
                    <tr>
 						<td> 付费方式</td>
                     	<td>
                     		<s:if test="#vminfo.payment_type == 1">后付费</s:if><s:else>预付费</s:else>
                     	</td>
                    </tr>
                    <tr>
 						<td> 购买方式</td>
                     	<td>
                     		<s:if test="#vminfo.duration_unit == 1">按小时</s:if><s:else>包月</s:else>
                     	</td>
                    </tr>
                    <tr>
 						<td>主机订购关系ID</td>
                     	<td><s:property value="#vminfo.vmProductInstanceId" /></td>
                    </tr>
                    <tr>
 						<td>操作系统</td>
                     	<td><s:property value="#vminfo.oper_system" /></td>
                    </tr>
                    <tr>
 						<td>虚拟机ID</td>
                     	<td><s:property value="#vminfo.vmCode" /></td>
                    </tr>                 
                    <tr>
 						<td>connectCode</td>
                     	<td><s:property value="#vminfo.connectCode" /></td>
                    </tr>                    
                    <tr>
 						<td>地域</td>
                     	<td><s:property value="#vminfo.area_id" /></td>
                    </tr>                   
                    <tr>
 						<td>CPU</td>
                     	<td><s:property value="#vminfo.cpu" /></td>
                    </tr>
                    <tr>
 						<td>CPU订购关系ID</td>
                     	<td><s:property value="#vminfo.cpuProductInstanceId" /></td>
                    </tr>
                    <tr>
 						<td>内存</td>
                     	<td><s:property value="#vminfo.memoryInMb" /></td>
                    </tr>
                    <tr>
 						<td>内存订购关系ID</td>
                     	<td><s:property value="#vminfo.memoryProductInstanceId" /></td>
                    </tr>
                    <tr>
 						<td>磁盘信息</td>
                     	<td>
                     		<table id="theTable" width="100%" class="blue-table sorttable imgTableCenter" border="0" cellspacing="0">
                     			<thead>
                					<tr>
                    					<th>磁盘属性</th>
                    					<th>属性值</th>
                					</tr>
                				</thead>
                				<s:iterator value="#vminfo.vmdkList" id="diskinfo">
                					<tr>
 										<td>大小</td>
                     					<td><s:property value="#diskinfo.capacityInMB" /></td>
                    				</tr>
                    				<tr>
 										<td>磁盘订购关系ID</td>
                     					<td><s:property value="#diskinfo.vmdkProduceInstaceId" /></td>
                    				</tr>
                				</s:iterator>
                     		</table>
                     	</td>
                    </tr>
                    <tr>
 						<td>IP个数</td>
                     	<td><s:property value="#vminfo.ip_sum" /></td>
                    </tr>
                    <tr>
 						<td>IP订购关系ID</td>
                     	<td><s:property value="#vminfo.ipProductInstanceId" /></td>
                    </tr>
                    <tr>
 						<td>带宽</td>
                     	<td><s:property value="#vminfo.band_width" /></td>
                    </tr>
                    <tr>
 						<td>VLANID</td>
                     	<td><s:property value="#vminfo.vlan_id" /></td>
                    </tr>
                    <tr>
 						<td>带宽订购关系ID</td>
                     	<td><s:property value="#vminfo.vLanProductInstanceId" /></td>
                    </tr>                    
                    <tr>
 						<td>负载均衡ID</td>
                     	<td><s:property value="#vminfo.loadId" /></td>
                    </tr>
                     <tr>
 						<td>负载均衡订购关系ID</td>
                     	<td><s:property value="#vminfo.loadProductInstanceId" /></td>
                    </tr> 
                </s:iterator>
                </tbody>
            </table>
</div>
</body>
</html>