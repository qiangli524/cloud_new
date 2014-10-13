<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">

</script>
</head>
<body class="pop-body scrollbody">
	<s:set value="#request.resultList" id="list"></s:set>
	<s:form action="datacenter_saveDataCenter.do" method="post" id="theForm">
		<table id="theTable" width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<s:if test="#list != null && #list.size()>0">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">
							状态
						</th>
						<th onclick="sort(theTable,1,'date')">
							时间
						</th>
						<th onclick="sort(theTable,2,'string')">
							备注
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.resultList" id="bean">
						<tr>
							<td>
								<s:if test="#bean.catch_status==0">
								未捕获</s:if>
								<s:elseif test="#bean.catch_status==1">正在捕获</s:elseif>
								<s:elseif test="#bean.catch_status==2">已捕获</s:elseif>
							</td>
							<td>
								<s:property value="#bean.catch_time" />
							</td>
							<td>
								<s:property value="#bean.remark" />
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</s:if>
			<s:else>无历史版本信息</s:else>
		</table>
	</s:form>
</body>
