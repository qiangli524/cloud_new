<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
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

		$("#auditForm")
				.click(
						function() {
							currentEdit = $(this);
							var wuuid = '';
							var lenth = 0;
							var audit_type;
							$('[name=checkboxid]:checkbox:checked')
									.each(
											function() {
												wuuid += $(this).attr("wuuid");
												audit_type = $(this).attr(
														"audit_type");
												lenth += 1;
											});
							if (wuuid == null || wuuid == '') {
								alert("请选择需要审批的项");
								return false;
							} else if (lenth > 1) {
								alert("只能选择一条数据审批");
								return false;
							}
							if (audit_type > 1) {
								alert("该工单为已审批状态，不能再审批");
								return false;
							}
							$
									.dialog({
										id : 'updateAudit',
										title : '工单审批',
										width : '500px',
										height : '250px',
										max : false,
										min : false,
										content : 'url:workorderAudit_updateWorkOrderAudit.do?wuuid='
												+ wuuid
									});
						});

		$("[name='listWorkOrderAudit']")
				.unbind()
				.live(
						"click",
						function() {
							var $td = $(this).parent();
							var uuid = $td.attr("uuid");
							var workorderid = $td.attr("workorderid");
							$
									.dialog({
										id : 'listWorkOrderAudit',
										title : '工单详情',
										width : '750px',
										height : '400px',
										max : false,
										min : false,
										content : 'url:workorderAudit_listWorkOrderAudit.do?uuid='
												+ uuid
									});
						});
	});

	function saveWorkOrderAudit(theForm) {
		$.ajax({
			type : 'post',
			url : 'workorderAudit_saveWorkOrderAudit.do?' + theForm,
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
	<s:form action="workorderAudit_workOrderAudit.do" method="post"
		id="theForm" cssStyle="theForm">

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
						name="workOrderObj.AUDIT_TYPE" id="AUDIT_TYPE" cssClass="select-1"></s:select>
					<label class="vm">所属项目:</label>
					<s:select list="projectList" listKey="ID" listValue="PROJECT_NAME"
						name="workOrderObj.PROJECT_ID" id="project" headerKey="-1"
						headerValue="请选择" cssClass="select-1"></s:select>
					<span class="ubtn-1 mgl-20"><input type="button"
						id="searchForm" value="查询" /> </span> <span class="ubtn-2 mgl-20"><input
						type="button" id="resetForm" value="重置" /> </span>
				</div>
				<div class="utt-2 mgt-20">
					<a class="icon-add" href="javascript:void(0)" id="auditForm">审批</a>
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
							<th onclick="sort(theTable,5,'string')">资源类型</th>
							<th onclick="sort(theTable,4,'string')">执行状态</th>
							<th onclick="sort(theTable,6,'string')">审批状态</th>
							<th onclick="sort(theTable,11,'date')">创建时间</th>
							<!--									<th onclick="sort(theTable,12,'string')">管理</th>-->
						</tr>
					</thead>
					<tbody>
						<s:iterator value="resultList" id="theBean">
							<tr>
								<td><input type="checkbox" name="checkboxid"
									value="<s:property value="#theBean.UUID"/>" id="wuuid"
									wuuid='<s:property value="#theBean.UUID"/>'
									audit_type='<s:property value="#theBean.AUDIT_TYPE" />' /></td>
								<td uuid='<s:property value="#theBean.UUID" />'><a
									href="javascript:;" name="listWorkOrderAudit"> <s:property
											value="#theBean.BOMC_UUID" /> </a>
								</td>
								<td align="center">
									<div class="hidden"
										title='<s:property value="#theBean.WORK_ORDER_TITLE"/>'>
										<s:property value="#theBean.WORK_ORDER_TITLE" />
									</div>
								</td>
								<td><s:if test="#theBean.TYPE == 0">申请</s:if> <s:elseif
										test="#theBean.TYPE == 1">调整</s:elseif> <s:elseif
										test="#theBean.TYPE == 2">回收</s:elseif>
								</td>
								<td><s:if test="#theBean.RESOURCE_TYPE == 1">物理资源</s:if> <s:elseif
										test="#theBean.RESOURCE_TYPE == 2">虚拟资源</s:elseif>
								</td>
								<td><s:if test="#theBean.WSTAT == 0">未处理</s:if> <s:elseif
										test="#theBean.WSTAT == 1">已处理</s:elseif>
								</td>
								<td><s:if test="#theBean.AUDIT_TYPE == 1">待审批</s:if> <s:elseif
										test="#theBean.AUDIT_TYPE == 2">审批通过</s:elseif> <s:elseif
										test="#theBean.AUDIT_TYPE == 3">审批打回 </s:elseif> <s:elseif
										test="#theBean.AUDIT_TYPE == 0">新建</s:elseif>
								</td>
								<%-- 
										<td align="center">
											<div class="hidden"
												title='<s:property value="#theBean.WORK_ORDER_COMM_MSG"/>'>
												<s:property value="#theBean.WORK_ORDER_COMM_MSG" />
											</div>
										</td>
										--%>
								<td><s:property value="#theBean.RECEIVETIME" />
								</td>
								<%-- <td>
											<s:property value="#theBean.EXETIME" />
										</td>
										 
										<td uuid='<s:property value="#theBean.UUID"/>'
											states='<s:property value="#theBean.STATE"/>'
											typer='<s:property value="#theBean.TYPE"/>'
											wstat='<s:property value="#theBean.WSTAT"/>'
											resource_type='<s:property value="#theBean.RESOURCE_TYPE"/>'>
											<s:if test="#theBean.WSTAT == 0">
												<a href="javascript:;" name="exeWorkOrder">执行工单</a>&nbsp;&nbsp;
												<a href="javascript:;" name="viewResource">管理任务</a>
											</s:if> <s:else>
												<a href="javascript:;" name="viewResource">查看任务</a>
											</s:else>
										</td>
										--%>
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
