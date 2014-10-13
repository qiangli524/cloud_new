<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="yvm_clusterResource" method="post" id="theForm">
		<div>
			<div>
				<ul>			      
				   <li>
     					<p align="left">CPU</p> 
          				<div>
            				<p align="left">        总容量： <s:property value="theForm.cloudType"/> </p>
            				<p align="left">        预留的容量：<s:property value="theForm.version"/> </p>
            				<p align="left">        可用容量：<s:property value="theForm.username"/> </p>
           				</div>
           			</li>
    			</ul>
    		</div>
  		</div>
  		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">预留(MHz)</th>
						<th onclick="sort(theTable,1,'string')">限制(MHz)</th>
						<th onclick="sort(theTable,2,'string')">份额</th>
						<th onclick="sort(theTable,3,'string')">份额值</th>
						<th onclick="sort(theTable,4,'string')">份额(%)</th>
						<th onclick="sort(theTable,5,'string')">最坏情况分配</th>
						<th onclick="sort(theTable,6,'string')">类型</th>
					</tr>
				</thead>    
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</s:iterator>
				</tbody>               
			</table>
		</div>
</s:form>
</body>
