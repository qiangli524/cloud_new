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
		var eq_type = "";
		var cpu_cl = "";
		var mem = "";
		var store = "";
		var maxCounts = "";
		$(":checkbox:checked").each(function() {
			eq_type += $(this).attr("eq_type");
			cpu_cl += $(this).attr("cpu_cl");
			mem += $(this).attr("mem");
			store += $(this).attr("store");
			maxCounts += $(this).attr("count");
		});
		//var oper = $("#oper").val();
		//alert(oper);
		//if (oper == "add") {
			api.get("addResource").selectTem(eq_type,cpu_cl,mem,store,maxCounts);
		//} else {
		//	api.get("editResource").selectTem(eq_type,cpu_cl,mem,store,maxCounts);
		//}
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
	<s:form action="workorder_listResourceType.do" method="post"
		id="theForm" cssStyle="theForm">
		<div class="box on">
			<div class="query-form">
				<table width="100%" class="querytable" border="0">
					<tr>
						<td class="til">主机类型:</td>
						<td><s:select
								list="#{'-1':'--请选择--','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器','6':'HP PC服务器'}"
								name="hostInfoObj.eq_type" id="type"></s:select></td>
						<td class="til">CPU个数:</td>
						<td><s:textfield name="hostInfoObj.cpu_cl" cssClass="txt"
								id="cpu_cl"></s:textfield>
						</td>
						<td class="til">内存大小:</td>
						<td><s:textfield name="hostInfoObj.mem" cssClass="txt" id="mem"></s:textfield>
						</td>
						<td class="til">存储大小:</td>
						<td><s:textfield name="hostInfoObj.store" cssClass="txt"
								id="store"></s:textfield>
						</td>
					</tr>
					<tr>
						<td colspan="10" class="btns">
							<div>
								<input type="button" class="thickbox btn-style02" value="查询"
									id="searchForm" /> <input type="button" class="btn-style02"
									value="重置" id="resetForm" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="blue-wrap noborder">
				<div class="table-ct">
					<table id="theTable" width="100%" border="0" cellspacing="0"
						class="blue-table sorttable">
						<thead id="table">
							<tr>
								<th>选择</th>
								<th onclick="sort(theTable,1,'string')">主机类型</th>
								<th onclick="sort(theTable,2,'string')">CPU个数</th>
								<th onclick="sort(theTable,3,'string')">内存大小</th>
								<th onclick="sort(theTable,4,'string')">存储大小</th>
								<th onclick="sort(theTable,5,'string')">可分配个数</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="physicHostList" id="theBean">
								<tr>
									<td><input type="checkbox"
										eq_type='<s:property value="#theBean.eq_type" />'
										cpu_cl='<s:property value="#theBean.cpu_cl" />'
										mem='<s:property value="#theBean.mem" />'
										store='<s:property value="#theBean.store" />'
										count='<s:property value="#theBean.COUNT" />' />
									</td>
									<td><s:if test="#theBean.eq_type == 1">
												IBM小型机
											</s:if> <s:elseif test="#theBean.eq_type == 2">
												IBM X86刀片
											</s:elseif> <s:elseif test="#theBean.eq_type == 3">
												IBM PC服务器
											</s:elseif> <s:elseif test="#theBean.eq_type == 4">
												HP X86刀片
											</s:elseif> <s:elseif test="#theBean.eq_type == 5">
												机架服务器
											</s:elseif> <s:elseif test="#theBean.eq_type == 6">
												HP PC服务器
											</s:elseif></td>
									<td><s:property value="#theBean.cpu_cl" /> 个</td>
									<td><s:if test='#theBean.mem != null'>
											<s:property
												value="@java.lang.Math@round(#theBean.mem/1024*100) / 100" />
												G
											</s:if></td>
									<td><s:if test='#theBean.store != null'>
											<s:property
												value="@java.lang.Math@round(#theBean.store/1024*100) / 100" />
												G
											</s:if>
									</td>
									<td><s:property value="#theBean.COUNT"></s:property>台
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</s:form>
</body>