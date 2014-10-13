<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
</head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<body class="pop-body scrollbody">

<form action="datastore_listStoreHost.do?storeUuid=${storeUuid}" method="post" id="theForm">
<div>
	<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
					<th>主机名称</th>
					<th>主机IP</th>
					<th>CPU</th>
					<th>内存</th>
					<th>虚拟化类型</th>
					<th>状态</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="hostList" id="theBean">
			  	<tr>
			  		<td><s:property value="#theBean.eq_name"/></td>
			  		<td><s:property value="#theBean.eq_ip"/></td>
			  		<td><s:property value="#theBean.cpu_cl"/>核</td>
			  		<td><s:property value="#theBean.mem"/>G</td>
			  		<td>
											<s:if test="#theBean.hasvertual == 3">
												XEN
											</s:if>
											<s:elseif test="#theBean.hasvertual==4">
												VMWARE	
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==1">
												PowerVM
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==2">
												KVM
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==0">
												非虚拟化
											</s:elseif>	  		
			  		</td>
			  		<td>
						<s:if test="#theBean.STATUS == 0">
							未采集到
						</s:if>
						<s:elseif test="#theBean.STATUS == 1">
							正常启动
						</s:elseif>
						<s:elseif test="#theBean.STATUS == 2">
							关闭
						</s:elseif>
						<s:else>
							异常
						</s:else>
			  		</td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
</div>
<div class="pages mgb-10">
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
						</div>
		 </div>
</form>
</body>
