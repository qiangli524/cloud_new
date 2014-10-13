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
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>

<script type="text/javascript">
	$(function(){
		var connect_id = '<s:property value="connect_id" />';
		var uuid = '<s:property value="uuid" />';
		var type = '<s:property value="type" />';
		$("#search").click(function(){
		//	var name = $("#name").val();
			theForm.action="unitedTab_attchTask.do?connect_id="+connect_id+"&uuid="+uuid+"&type="+type;
			theForm.submit();
		});
	})
</script>
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
	<s:form id="theForm" cssStyle="theForm" method="post">
		<div class="bgcolor-1">
			<div class="bord-1 pd-10">
				<div class="clearfix filtrate-area">
					<div class="filtrate-field">
						<label class="fl">任务信息：</label>
						<s:textfield name="name" cssClass="inpt-1 fl" id="name"></s:textfield>
						<span class="ubtn-1 mgl-20"><input type="button"
							id="search" value="查询" /> </span>
					</div>
				</div>
				<div  class="mgt-20">
				<table width="100%" border="0" cellspacing="0"
					class="blue-table sorttable" id="theTable">
					<thead>
						<tr>
							<th onclick="sort(theTable,0,'string')">实体名称</th>
							<th onclick="sort(theTable,1,'string')">状态</th>
							<th onclick="sort(theTable,2,'string')">任务信息</th>
							<th onclick="sort(theTable,2,'string')">操作者</th>
							<th onclick="sort(theTable,3,'date')">开始时间</th>
							<th onclick="sort(theTable,4,'date')">完成时间</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="resultList" id="theBean">
							<tr>
								<td><s:property value="#theBean.entityName" /></td>
								<td><s:property value="#theBean.status" /></td>
								<td align="center"><span style="color: black;"
									class="font-more" title='<s:property value="#theBean.info"/>'>
										<s:property value="#theBean.info" /> </span></td>
								<td><s:property value="#theBean.user" /></td>
								<td><s:property value="#theBean.startTime" /></td>
								<td><s:property value="#theBean.completeTime" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<div class="pages mgb-10">
				</div>
			</div>
		</div>
	</s:form>
	</div>
</body>
