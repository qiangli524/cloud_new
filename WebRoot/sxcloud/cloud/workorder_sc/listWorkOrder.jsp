<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<title></title>
<style type="text/css">
div.hidden {
	width: 200px;
	height: 30px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	text-overflow: ellipsis; /* IE/Safari */
	-ms-text-overflow: ellipsis;
	-o-text-overflow: ellipsis; /* Opera */
	-moz-binding: url("ellipsis.xml#ellipsis"); /*FireFox*/
}
</style>
<style type="text/css">
.font-more {
	width: 80px;
	height: 20px;
	line-height: 20px;
	overflow: hidden;
	white-space: nowrap;
	display: block;
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
}
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function() {
		$check = $(":checkbox");
		$check.unbind().live("click", function() {
			$check.not(this).attr("checked", false);
		});
	});

	$(function() {
		$(".query").click(function() {
			if ($(".query-form").is(":visible")) {
				$(".query-form").slideUp("slow");
			} else {
				$(".query-form").slideDown("slow");
			}
		});

		$("#searchForm").click(function() {
			$("#theForm").submit();
		});

		$("#resetForm").click(function() {
			$("#type").val("-1");
			$("#project").val("-1");
			$("#bomc_uuid").val("");
			$("#camefrom").val("-1");
			$("#wstats").val("-1");
		});

		$("#addForm").click(function() {
			$.dialog({
				id : 'addWorkOrder',
				title : '添加工单',
				width : '400px',
				height : '300px',
				max : false,
				min : false,
				lock : true,
				content : 'url:workorder/workorder_addWorkOrder.do?oper=add'
			});
		});

		$("#sendAudit").click(function() {
			if ($(":checkbox:checked").length == 0) {
				alert("请选择一条工单发送审批");
				return false;
			}
			var uuid = "";
			var audit_type = "";
			var allresource = "";
			$(":checkbox:checked").each(function() {
				uuid += $(this).attr("wuuid");
				audit_type = $(this).attr("audit_type");
				allresource = $(this).attr("allresource");
			});
			if (allresource == 0) {
				alert("还未给该工单配置任务，不能发送审批");
				return false;
			}
			if (audit_type > 0) {
				alert("只有新建状态的工单才能发送审批");
				return false;
			}
			if (confirm("你确定将该工单发送审批吗？")) {
				$.ajax({
					type : 'post',
					url : 'workorder_sendAuditWorkOrder.do?uuid=' + uuid,
					success : function(msg) {
						if (msg == -1) {
							alert("发送审批失败！");
						} else {
							$("#theForm").submit();
						}
					}
				});
			}
		});

		$("#deleteForm").click(function() {
			if ($(":checkbox:checked").length == 0) {
				alert("请选择一项进行删除");
				return false;
			}
			var uuid = "";
			var wstat;
			var camefrom;
			var allresource = "";
			var audit_type = "";
			$(":checkbox:checked").each(function() {
				uuid += $(this).attr("wuuid");
				wstat = $(this).attr("wstat");
				camefrom = $(this).attr("camefrom");
				allresource = $(this).attr("allresource");
				audit_type = $(this).attr("audit_type");
			});
			if (audit_type == 1 || audit_type == 2) {
				alert("该工单为待审批或审批通过状态，不可删除");
				return false;
			}
			if (camefrom == 0) {
				alert("该工单来自BOMC，不可删除");
				return false;
			}
			if (confirm("你确定删除该工单吗？")) {
				$.ajax({
					type : 'post',
					url : 'workorder_deleteWorkOrder.do?uuid=' + uuid,
					success : function(msg) {
						if (msg == -1) {
							alert("删除失败！");
						} else {
							$("#theForm").submit();
						}
					}
				});
			}
		});

		$("[name='exeWorkOrder']").unbind().live(
				"click",
				function() {
					var $td = $(this).parent();
					var uuid = $td.attr("uuid");
					var resource_type = $td.attr("resource_type");
					if (confirm("工单一旦执行，便不能再编辑工单下的任务，确定执行工单?")) {
						$.ajax({
							type : 'post',
							url : 'workorder_exeWorkOrder.do?uuid=' + uuid
									+ '&resource_type=' + resource_type,
							success : function(msg) {
								if (msg == -2) {
									alert("存在虚拟机名称为空的资源，请修改后执行");
								} else if (msg == -1) {
									alert("工单提交失败,无法执行");
								} else {
									alert("工单提交成功,等待执行");
									$("#theForm").submit();
								}
							}
						});
					}
				});

		$("[name='viewResource']").unbind().live(
				"click",
				function() {
					var $td = $(this).parent();
					var uuid = $td.attr("uuid");
					var state = $td.attr("states");
					var type = $td.attr("typer");
					var wstat = $td.attr("wstat");
					var resource_type = $td.attr("resource_type");
					$.dialog({
						id : 'viewResource',
						title : '查看工单任务',
						width : '900px',
						height : '400px',
						max : false,
						min : false,
						content : 'url:workorder_listResource.do?uuid=' + uuid
								+ '&state=' + state + '&type=' + type
								+ '&wstat=' + wstat + '&resource_type='
								+ resource_type
					});
				});
	});

	function refresh() {
		window.location.reload();
	}

	function saveWorkOrder(theForm) {
		$.ajax({
			type : 'post',
			url : 'workorder_saveWorkOrder.do?' + theForm,
			success : function(msg) {
				if (msg == -1) {
					alert("保存失败");
				} else {
					$("#theForm").submit();
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="mainbody">
		<s:form action="workorder_listWorkOrder.do" method="post" id="theForm"
			cssStyle="theForm">
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">工单管理</h2>
				<div class="bord-1 pd-10">
					<div class="clearfix mgt-10">
						<label class="vm">工单编号:</label>
						<s:textfield name="workOrderObj.BOMC_UUID" id="bomc_uuid"></s:textfield>
						<label class="vm">工单类型:</label>
						<s:select list="#{'-1':'--请选择--','0':'申请','1':'调整','2':'回收'}"
							name="workOrderObj.TYPE" id="type" cssClass="select-1"></s:select>
						<label class="vm">执行状态:</label>
						<s:select list="#{'-1':'--请选择--','0':'未处理','1':'已处理'}"
							name="workOrderObj.WSTAT" id="wstats" cssClass="select-1"></s:select>
						<label class="vm">审批状态:</label>
						<s:select list="#{'-1':'--请选择--','1':'待审批','2':'审批通过','3':'审批打回'}"
							name="workOrderObj.AUDIT_TYPE" id="AUDIT_TYPE"
							cssClass="select-1"></s:select>
						<label class="vm">所属项目:</label>
						<s:select list="projectList" listKey="ID" listValue="PROJECT_NAME"
							name="workOrderObj.PROJECT_ID" id="project" headerKey="-1"
							headerValue="请选择" cssClass="select-1"></s:select>
						<span class="ubtn-1 mgl-20"><input type="button"
							id="searchForm" value="查询" /> </span> <span class="ubtn-2 mgl-20"><input
							type="button" id="resetForm" value="重置" /> </span>
					</div>
					<div class="utt-2 mgt-20">
						<a class="icon-add" href="javascript:void(0)" id="addForm">新增</a>
						<a class="icon-del" href="javascript:void(0)" id="deleteForm">删除</a>
						<a class="icon-add" href="javascript:void(0)" id="sendAudit">发送审批</a>
						<a class="icon-release" href="javascript:void(0)"
							onclick="refresh()">刷新</a>
					</div>
					<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<th onclick="sort(theTable,0,'string')">选择</th>
								<th onclick="sort(theTable,1,'string')">工单号</th>
								<th onclick="sort(theTable,2,'string')">工单标题</th>
								<!-- <th onclick="sort(theTable,1,'string')">工单号</th> -->
								<th onclick="sort(theTable,3,'string')">类型</th>
								<th onclick="sort(theTable,4,'string')">资源类型</th>
								<th onclick="sort(theTable,5,'string')">执行状态</th>
								<th onclick="sort(theTable,6,'string')">审批状态</th>
								<!-- 
									<th onclick="sort(theTable,5,'string')" width="5%">来源</th> -->
								<!-- <th onclick="sort(theTable,5,'string')">任务总数(个)</th> -->
								<!--<th onclick="sort(theTable,6,'string')" width="5%">成功(个)</th>
									<th onclick="sort(theTable,7,'string')" width="5%">失败(个)</th>
									<!-- <th onclick="sort(theTable,8,'string')">待处理(个)</th> -->
								<!--<th onclick="sort(theTable,8,'string')" width="5%">处理中(个)</th>
									<th onclick="sort(theTable,9,'string')" width="5%">未挂存储(个)</th>-->
								<%--<th onclick="sort(theTable,10,'string')">备注</th>
									<%--<th onclick="sort(theTable,12,'String')">处理信息</th>--%>
								<th onclick="sort(theTable,11,'date')">创建时间</th>
								<%--<th onclick="sort(theTable,14,'date')">处理时间</th>--%>
								<th onclick="sort(theTable,12,'string')">管理</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="resultList" id="theBean">
								<tr>
									<td><input type="checkbox" name="checkboxid"
										wuuid='<s:property value="#theBean.UUID"/>'
										wstat='<s:property value="#theBean.WSTAT"/>'
										camefrom='<s:property value="#theBean.CAMEFROM"/>'
										allresource='<s:property value="#theBean.ALLCOUNT" />'
										audit_type='<s:property value="#theBean.AUDIT_TYPE" />' />
									</td>
									<td><s:property value="#theBean.BOMC_UUID" /></td>
									<td align="center">
										<div class="hidden"
											title='<s:property value="#theBean.WORK_ORDER_TITLE"/>'>
											<s:property value="#theBean.WORK_ORDER_TITLE" />
										</div></td>
									<td><s:if test="#theBean.TYPE == 0">申请</s:if> <s:elseif
											test="#theBean.TYPE == 1">调整</s:elseif> <s:elseif
											test="#theBean.TYPE == 2">回收</s:elseif></td>
									<td><s:if test="#theBean.RESOURCE_TYPE == 1">物理资源</s:if> <s:elseif
											test="#theBean.RESOURCE_TYPE == 2">虚拟资源</s:elseif></td>
									<td><s:if test="#theBean.WSTAT == 0">未处理</s:if> <s:elseif
											test="#theBean.WSTAT == 1">已处理</s:elseif></td>
									<td><s:if test="#theBean.AUDIT_TYPE == 1">待审批</s:if> <s:elseif
											test="#theBean.AUDIT_TYPE == 2">审批通过</s:elseif> <s:elseif
											test="#theBean.AUDIT_TYPE == 3">审批打回 </s:elseif> <s:elseif
											test="#theBean.AUDIT_TYPE == 0">新建</s:elseif></td>
									<%--
						  				<td>
						  					<s:if test="#theBean.CAMEFROM == 0">BOMC</s:if>
						  					<s:elseif test="#theBean.CAMEFROM == 1">云平台</s:elseif>
										</td>
					  					<td>
					  						<s:property value="#theBean.SUCCESS"/>/<s:property value="#theBean.ALLCOUNT"/>
					  					</td>
					  					<td>
					  						<s:property value="#theBean.FALUIRE"/>/<s:property value="#theBean.ALLCOUNT"/>
					  					</td>
					  					<td>
					  						<s:property value="#theBean.WAITDEAL"/>
					  					</td> 
					  					<td>
					  						<s:property value="#theBean.DEALING"/>/<s:property value="#theBean.ALLCOUNT"/>
					  					</td>
					  					<td>
					  						<s:property value="#theBean.UNMOUNTSTORE"/>/<s:property value="#theBean.ALLCOUNT"/>
					  					</td>--%>
									<%--<td align="center">
											<span style="color: black;" class="font-more" title='<s:property value="#theBean.WMESSAGE"/>'>
												<s:property value="#theBean.WMESSAGE" default="无"/>
											</span>
										</td>--%>
									<!--  
										<td align="center">
											<div class="hidden"
												title='<s:property value="#theBean.WORK_ORDER_COMM_MSG"/>'>
												<s:property value="#theBean.WORK_ORDER_COMM_MSG" />
											</div>
										</td>-->
									<td><s:property value="#theBean.RECEIVETIME" /></td>
									<%-- <td>
											<s:property value="#theBean.EXETIME" />
										</td>
										 --%>
									<td uuid='<s:property value="#theBean.UUID"/>'
										states='<s:property value="#theBean.STATE"/>'
										typer='<s:property value="#theBean.TYPE"/>'
										wstat='<s:property value="#theBean.WSTAT"/>'
										resource_type='<s:property value="#theBean.RESOURCE_TYPE"/>'>
										<s:if test="#theBean.AUDIT_TYPE == 0">
											<!-- <a href="javascript:;" name="exeWorkOrder">执行工单</a>&nbsp;&nbsp;  -->
											<a href="javascript:;" name="viewResource">管理任务</a>
										</s:if> <s:elseif test="#theBean.AUDIT_TYPE == 3">
											<!-- <a href="javascript:;" name="exeWorkOrder">执行工单</a>&nbsp;&nbsp;  -->
											<a href="javascript:;" name="viewResource">管理任务</a>
										</s:elseif> <s:elseif test="#theBean.AUDIT_TYPE == 1">
											<a href="javascript:;" name="viewResource">查看任务</a>
										</s:elseif> <s:elseif test="#theBean.AUDIT_TYPE == 2">
											<a href="javascript:;" name="viewResource">查看任务</a>
										</s:elseif></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pages">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
				</div>
		</s:form>
	</div>
</body>
