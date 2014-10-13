<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<head>
<title></title>
<script type="text/javascript">
	
</script>
</head>
<body>
<s:form action="operationLog_listOperationLog.do" method="post" id="obj">
<div class="scrollbody">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  		<tr>
						<th onclick="sort(theTable,1,'string')">实例名称</th>
						<th onclick="sort(theTable,2,'string')">操作类型</th>
						<th onclick="sort(theTable,3,'string')">是否成功</th>
						<th onclick="sort(theTable,4,'string')">描述</th>
						<th onclick="sort(theTable,5,'string')">操作时间</th>			  			
			  		</tr>
			  </thead>
			  <tbody id="tbody_list">
			  		<s:iterator value="resultList" id="theBean">
			  			<tr>
			  				<td> <s:property value="#theBean.exampleName"/> </td>
			  				<td> 
			  					<s:if test="#theBean.operationType==0">
			  						部署
			  					</s:if>
			  					<s:elseif test="#theBean.operationType==1">
			  						上线
			  					</s:elseif>
			  					<s:else>
			  						回滚
			  					</s:else>
			  				</td>
			  				<td> 
			  					<s:if test="#theBean.isSuccess==0">
			  						成功
			  					</s:if>
			  					<s:else>
			  						失败
			  					</s:else>
			  				</td>
			  				<td> <s:property value="#theBean.description"/> </td>
			  				<td> <s:property value="#theBean.update_time"/> </td>
			  			</tr>
			  		</s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
					<jsp:include page="../../../sxcloud/inc/Pagination.jsp?formId=obj" />
			</div>
</div>
</s:form>
</body>
