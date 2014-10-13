<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link_export.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/yicloud/xen/js/common.js"></script>
<head>
<title></title>
<style type="text/css">
.font-more {
	width: 170px;
	height: 20px;
	line-height: 20px;
	overflow: hidden;
	white-space: nowrap;
	display: block;
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
}
</style>
</head>
<body class="mainbody">
	<s:form cssStyle="theForm">
		<div class="bgcolor-1">
			<div class="bord-1 pd-10">
				<s:if test="vtype == 2">
					<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<th onclick="sort(theTable,0,'string')">网络
								</th>
								<th onclick="sort(theTable,1,'string')">MAC
								</th>
								<th onclick="sort(theTable,2,'string')">限制
								</th>
								<th onclick="sort(theTable,3,'string')">IP地址
								</th>
								<th onclick="sort(theTable,4,'string')">活动
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="resultList" id="theBean">
								<tr>
									<td align="center"><s:property
											value="#theBean.netWorkName" /></td>
									<td align="center"><s:property value="#theBean.mac"
											default="-" /></td>
									<td align="center"><s:property value="" default="-" /></td>
									<td align="center"><s:property value="#theBean.ip"
											default="-" /></td>
									<td align="center"><s:if
											test="#theBean.currentlyAttached == true">
					  						激活
					  					</s:if> <s:else>
					  						未激活
					  					</s:else></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</s:if>
				<!-- vmware的网络情况 -->
				<s:elseif test="vtype == 1">
					<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<th onclick="sort(theTable,0,'string')">网卡名称
								</th>
								<th onclick="sort(theTable,1,'string')">网卡MAC地址
								</th>
								<th onclick="sort(theTable,2,'string')">网卡所属VLAN
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="resultList" id="theBean">
								<tr>
									<td align="center"><s:property
											value="#theBean.ethernetCardName" default="-" /></td>
									<td align="center"><s:property
											value="#theBean.ethernetCardMac" default="-" /></td>
									<td align="center"><s:property
											value="#theBean.ethernetCardVlan" default="-" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</s:elseif>
				<div class="pages mgb-10">
				</div>
			</div>
		</div>
	</s:form>
	</div>
</body>
