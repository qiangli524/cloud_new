<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
<s:form action="yvm_dataCenterHost" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<thead>
					<tr>
						<th>名称</th>
					 	<th>CPU个数</th> 
						<th>CPU使用率</th>
						<th>内存使用情况</th>
					</tr>
				</thead>    
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><s:property value="#theBean.eq_name"/></td>
						 	
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
				</tbody>               
			</table>
		</div>
</s:form>
</body>
