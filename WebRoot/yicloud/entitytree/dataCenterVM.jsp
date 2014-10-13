<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="yvm_dataCenterVM" method="post" id="theForm">
		<div style="position:absolute;bottom:2px;top:40px;left:5px;right:5px;overflow-y:auto;">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">名称</th>
					<!-- 	<th>状况</th>
					 	<th>状态</th> 
						<th>主机</th>
						<th>置备的空间</th>
						<th>已用空间</th> -->	
						<th onclick="sort(theTable,1,'string')">CPU(核)</th>
						<th onclick="sort(theTable,2,'string')">内存(MB)</th>
						<th onclick="sort(theTable,3,'string')">存储(GB)</th>
					
					</tr>
				</thead>    
				<tbody align="center">
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><s:property value="#theBean.VH_NAME"/></td>
						<!-- 	<td>
								<s:if test="#theBean.ifOpen==1">已打开电源</s:if>
								<s:else>已关闭电源</s:else>  
							</td>
							<td>
								<s:if test="#theBean.status==1">
									正常
								</s:if>
							</td>
							<td>
								<s:property value= "#theBean.host"/>
							</td>
							<td>
								<s:property value="#theBean.storage"/>M
							</td>
							<td>
								<s:property value="#theBean.usedStor"/>
							</td> -->
							<td>
								<s:property value="#theBean.VH_CPU"/>
							</td>
							<td>
								<s:property value="#theBean.VH_MEM"/>
							</td>
							<td>
								<s:property value="#theBean.VH_STORAGE"/>
							</td>
							
						
						</tr>
					</s:iterator>
				</tbody>               
			</table>
		</div>
</s:form>
</body>
