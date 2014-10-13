<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="yvm_dataCenterVM" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">名称</th>
					 	<th onclick="sort(theTable,1,'string')">状态</th>
					 	<th onclick="sort(theTable,2,'string')">CPU个数</th> 
						<th onclick="sort(theTable,3,'string')">CPU使用率</th>
						<th onclick="sort(theTable,4,'string')">内存使用情况</th>
					</tr>
				</thead>    
				<tbody>
					<s:iterator value="theForm.hostResultList" id="theBean">
						<tr>
							<td><s:property value="#theBean.eq_name"/>  <span style="font-weight: bold;">(主机)</span></td>
							<td>
								运行
							</td>
							<td>
								<s:property value= "#theBean.cpu_cl"/>
							</td>
							<td>
							<s:property value= "#theBean.cpu_fq"/>%
							</td>
							<td>
								<s:property value="#theBean.memUsage"/>M/<s:property value="#theBean.mem"/>M
							</td>
						</tr>
					</s:iterator>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><s:property value="#theBean.VH_NAME"/></td>
						 	<td>
								<s:if test="#theBean.VH_STATUS==1">运行</s:if>
								<s:else>关闭</s:else>  
							</td>
							<td>
								<s:if test="#theBean.VH_MIN_CPU==null ||#theBean.VH_MIN_CPU==''">--</s:if>
								<s:else><s:property value= "#theBean.VH_MIN_CPU"/></s:else>
							</td>
							<td>
							<s:if test="#theBean.VH_CPU==null || #theBean.VH_CPU==''">--</s:if>
							<s:else><s:property value= "#theBean.VH_CPU"/>%</s:else>
							</td>
							<td>
							<s:if test="#theBean.VH_MIN_MEM==null || #theBean.VH_MIN_MEM==''">--</s:if>
							<s:else>
								<s:property value="#theBean.VH_MIN_MEM"/>M/<s:property value="#theBean.VH_MEM"/>M
							</s:else>
							</td>
						</tr>
					</s:iterator>
				</tbody>               
			</table>
		</div>
</s:form>
</body>
