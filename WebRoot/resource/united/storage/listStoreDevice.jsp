<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<style type="text/css">
</style>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>

</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" id="storeDevice">
<div class="scrollbody">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<tr>
					<td class="til" width="20%" align="left">
						存储设备名称
					</td>
					<td align="left">
						<s:property value="storeDevice.name"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						CACHE容量(G)
					</td>
					<td align="left">
					   <fmt:formatNumber maxFractionDigits="2" value="${storeDevice.cache_space}"/>
					  
					</td>
				</tr> 
				<tr>
					<td class="til" width="20%" align="left">
						RAID方式
					</td>
					<td align="left">
						<s:property value="storeDevice.raid_way"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						厂商
					</td>
					<td align="left">
						<s:property value="storeDevice.supporter"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						磁盘裸容量(T)
					</td>
					<td align="left">
					     <fmt:formatNumber maxFractionDigits="2" value="${storeDevice.capacity/1024}"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						磁盘有效容量(T)
					</td>
					<td align="left">
					    <fmt:formatNumber maxFractionDigits="2" value="${storeDevice.valid_space/1024}"/>		
					</td>
				</tr>	
				<tr>
					<td class="til" align="left">
						端口数量
					</td>
					<td align="left">
					 <s:property value="storeDevice.port_num"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						磁盘已使用量(T)
					</td>
					<td align="left">
						<fmt:formatNumber maxFractionDigits="2" value="${(storeDevice.valid_space-storeDevice.storeBlockFreeSpace)/1024}"/>					 
					</td>
				</tr>	
				<tr>
					<td class="til" align="left">
						磁盘使用率（已用量/有效容量）
					</td>
					<td align="left">
						<s:if test="storeDevice.valid_space!=0">
					 		<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" value="${((storeDevice.valid_space-storeDevice.storeBlockFreeSpace)*100/storeDevice.valid_space)}"/>%
						</s:if>
						<s:else>
							-
						</s:else>
					</td>
				</tr>	
				<!-- 增加已经挂载存储块容量 -->
				<tr>
					<td class="til" align="left">
						已挂载存储容量(T)
					</td>
					<td align="left">
					 <fmt:formatNumber maxFractionDigits="2" value="${storeDevice.storeBlockTotalSize/1024 }"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						已挂载存储使用量(T)
					</td>
					<td align="left">
					 <fmt:formatNumber maxFractionDigits="2" value="${(storeDevice.storeBlockTotalSize-storeDevice.storeBlockFreeSpace)/1024 }"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						已挂载存储置备空间(T)
					</td>
					<td align="left">
					  <fmt:formatNumber maxFractionDigits="3" value="${storeDevice.purchaseSpace/1024}"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						已挂载存储使用率（挂载使用量/挂载容量）
					</td>
					<td align="left">
						<s:if test="storeDevice.storeBlockTotalSize!=0">
					 		<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" value="${((storeDevice.storeBlockTotalSize-storeDevice.storeBlockFreeSpace)*100/storeDevice.storeBlockTotalSize)}"/>%
					 	</s:if>
					 	<s:else>
					 		-
					 	</s:else>	
					</td>
				</tr>	
				<tr>
					<td class="til" align="left">
						已挂载存储分配率（置备空间/挂在容量）
					</td>
					<td align="left">
						<s:if test="storeDevice.storeBlockTotalSize!=0">
					 		<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" value="${(storeDevice.purchaseSpace*100/storeDevice.storeBlockTotalSize)}"/>%
					 	</s:if>
					 	<s:else>
					 		-
					 	</s:else>	
					</td>
				</tr>							
			</table>
		</div>
	</div><!--blue-wrap end -->
</s:form>
</body>
