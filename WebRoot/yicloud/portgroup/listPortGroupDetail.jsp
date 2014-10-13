<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ page import="com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.WorkFlowConstant" %>
	<head>
		<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
		</script>
	</head>
<body class="pop-body scrollbody">
<s:form action="#" method="post" cssStyle="theForm" id="theForm">
	<div class="tit-zzi">
		<div id="zi">端口属性</div>
		<div id="zi"></div>
	</div>	
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<tr>
					<td class="til">
						网络标签：
					</td>
					<td>
					</td>	
				</tr>
				<tr>
					<td class="til">
						VLAN ID：
					</td>
					<td >
					</td>
				</tr>
				<tr>
					<td class="til">
						vMotion：
					</td>
					<td >
					</td>
				</tr>
				<tr>
					<td class="til">
						Fault Tolerance 日志记录：
					</td>
					<td >
					</td>
				</tr>
				<tr>
					<td class="til">
						流量管理：
					</td>
					<td >
					</td>
				</tr>
				<tr>
					<td class="til">
						iSCSI端口绑定：
					</td>
					<td >
					</td>
				</tr>
				</table>
			</div>
	<div class="tit-zzi">
		<div id="zi">网卡设置</div>
		<div id="zi"></div>
	</div>	
	<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<tr>
					<td class="til">
						MAC 地址：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						MTU：
					</td>
					<td >
					</td>
				</tr>	
				</table>		
	</div>
		
	<div class="tit-zzi">
		<div id="zi">IP设置</div>
		<div id="zi"></div>
	</div>	
	<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<tr>
					<td class="til">
						IP 地址：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						子网掩码：
					</td>
					<td >
					</td>
				</tr>	
				</table>		
	</div>
	
	<div class="tit-zzi">
		<div id="zi">有效策略</div>
		<div id="zi"></div>
	</div>	
	<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<tr>
					<td  style="font-size:16px">安全</td>
				</tr>
				<tr>
					<td class="til">
						混杂模式：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						MAC 地址更改：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						伪信号：
					</td>
					<td >
					</td>
				</tr>
					
				<tr>
					<td  style="font-size:16px">流量调整</td>
				</tr>
				<tr>
					<td class="til">
						平均宽带：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						宽带峰值：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						突发大小：
					</td>
					<td >
					</td>
				</tr>
				
				<tr>
					<td  style="font-size:16px">故障切换和负载平衡</td>
				</tr>	
				<tr>
					<td class="til">
						负载平衡：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						网络故障检测：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						通知交换机：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						故障恢复：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						活动适配器：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						待机适配器：
					</td>
					<td >
					</td>
				</tr>	
				<tr>
					<td class="til">
						未用适配器：
					</td>
					<td >
					</td>
				</tr>	
				</table>		
	</div>	 	
</s:form>
	</body>
