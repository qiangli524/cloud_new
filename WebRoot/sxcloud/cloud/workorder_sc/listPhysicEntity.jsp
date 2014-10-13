<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$check = $(":checkbox");
		$check.unbind().live("click", function() {
			$check.not(this).attr("checked", false);
		});
	})
	var api = frameElement.api;
	var w = api.opener;
	$(function() {
		api.button({
			id : 'OkAnd',
			name : '确定',
			callback : selectEntity,
			focus : true
		}, {
			id : 'cancle',
			name : '取消'
		});
	});

	function selectEntity() {
		var eq_id = "";
		var sn = "";
		var eq_ip = "";
		var host_name = "";
		var connectcode = "";
		$(":checkbox:checked").each(function() {
			eq_id += $(this).attr("eq_id");
			sn += $(this).attr("sn");
			eq_ip += $(this).attr("eq_ip");
			//host_name += $(this).attr("host_name");
		});
		if (eq_ip == '') {
			host_name = sn;
		} else {
			host_name = sn + "(" + eq_ip + ")";
		}
		var oper = $("#oper").val();
		if (oper == "add") {
			api.get("addResource").selectRecoverResouce(eq_id, connectcode,
					host_name);
		} else {
			api.get("editResource").selectRecoverResouce(eq_id, connectcode,
					host_name);
		}
	}
	$(function() {
		$("#searchForm").click(function() {
			$("#theForm").submit();
		});

		$("#resetForm").click(function() {
			$("#vh_name").val("");
		});
	});
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="workorder_selectRecoverResouce.do" method="post"
		id="theForm" cssStyle="theForm">
		<s:hidden name="oper" id="oper"></s:hidden>
		<s:hidden name="projectid" id="projectid"></s:hidden>
		<s:hidden name="resource_type" id="resource_type"></s:hidden>
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">回收主机选择</h2>
			<div class="bord-1 pd-10">
				<div class="clearfix mgt-10">
					<label class="vm">主机序列号:</label>
					<s:textfield name="workOrderObj.sn" id="sn"></s:textfield>
                    <label class="vm">IP地址:</label>
					<s:textfield name="workOrderObj.eq_ip" id="eq_ip"></s:textfield>
					<span class="ubtn-1 mgl-20"><input type="button"
						id="searchForm" value="查询" /> </span> <span class="ubtn-2 mgl-20"><input
						type="button" id="resetForm" value="重置" /> </span>
				</div>
				<table id="theTable" width="100%" border="0" cellspacing="0"
					class="blue-table sorttable">
					<thead id="table">
						<tr>
							<th>选择</th>
							<th onclick="sort(theTable,1,'string')">主机序列号</th>
							<th onclick="sort(theTable,2,'string')">主机名称</th>
							<th onclick="sort(theTable,3,'string')">IP地址</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="physicHostList" id="theBean">
							<tr>
								<td><input type="checkbox"
									eq_id='<s:property value="#theBean.eq_id" />'
									sn='<s:property value="#theBean.sn" />'
									host_name='<s:property value="#theBean.eq_hostname" />'
									eq_ip='<s:property value="#theBean.eq_ip" />' /></td>
								<td><s:property value="#theBean.sn"></s:property>
								</td>
								<td><s:property value="#theBean.eq_hostname"></s:property>
								</td>
								<td><s:property value="#theBean.eq_ip"></s:property></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<div class="pages">
					<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
				</div>
			</div>
		</div>
	</s:form>
</body>