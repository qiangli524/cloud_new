<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<title></title>
<style type="text/css">
</style>
<style type="text/css">
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function() {
		$("[name='resourceDetail']").unbind().live("click", function() {
			var $td = $(this).parent();
			var wrid = $td.attr("wrid");
			w.$.dialog({
				id : 'resourceDetail',
				title : '查看工单任务详细',
				width : '600px',
				height : '500px',
				max : false,
				min : false,
				lock : true,
				content : 'url:workorder_resourceDetail.do?wrid=' + wrid
			});
		});
	});
</script>
</head>
<body>
	<s:form action="workorderAudit_listWorkOrderAudit.do" method="post"
		name="theForm" id="theForm">
		<s:hidden name="type" id="type"></s:hidden>
		<s:hidden name="uuid" id="uuid"></s:hidden>
		<s:hidden name="wstat" id="wstat"></s:hidden>
		<s:hidden name="state" id="state"></s:hidden>
		<s:hidden name="resource_type" id="resource_type"></s:hidden>
		<s:hidden name="workorderid" id="workorderid"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<caption style="color: red;margin-left: 20px;font-size: 14px">工单详情</caption>
				<tr align="left">
					<td class="til">工单标题 <font color="red">*</font></td>
					<td><s:textfield name="theForm.WORK_ORDER_TITLE"
							id="WORK_ORDER_TITLE" disabled="true"
							style="width:150px;   height:20px;" maxlength="30"></s:textfield>
					</td>
					<td class="til">工单类型 <font color="red">*</font></td>
					<td><s:select cssStyle="width:154px"
							list="#{'-1':'--请选择--','0':'申请','1':'调整','2':'回收'}"
							name="theForm.TYPE" id="TYPE" disabled="true">
						</s:select></td>
				</tr>

				<tr align="left">
					<td class="til">资源类型 <font color="red">*</font></td>
					<td><s:select cssStyle="width:154px"
							list="#{'-1':'--请选择--','1':'物理资源','2':'虚拟资源'}"
							name="theForm.RESOURCE_TYPE" id="RESOURCE_TYPE" disabled="true">
						</s:select></td>
					<td class="til">执行状态 <font color="red">*</font></td>
					<td><s:select cssStyle="width:154px"
							list="#{'-1':'--请选择--','0':'未处理','1':'已处理'}" name="theForm.WSTAT"
							id="WSTAT" disabled="true">
						</s:select></td>
				</tr>

				<tr align="left">
					<td class="til">工单备注 <font color="red">*</font></td>
					<td><s:textfield name="theForm.WORK_ORDER_COMM_MSG"
							id="WORK_ORDER_COMM_MSG" disabled="true"
							style="width:150px;   height:20px;" maxlength="30"></s:textfield>
					</td>
					<td class="til">创建时间 <font color="red">*</font></td>
					<td><s:textfield name="theForm.RECEIVETIME" id="RECEIVETIME"
							disabled="true" style="width:150px;   height:20px;"
							maxlength="30"></s:textfield></td>
				</tr>
			</table>


			<table id="theTable" width="100%" class="blue-table sorttable"
				border="0" cellspacing="0">
				<caption style="color: red;margin-top: 20px;font-size: 14px">申请详情</caption>
				<thead>
					<tr>
						<th onclick="sort(theTable,1,'string')">所属项目</th>
						<th onclick="sort(theTable,2,'string')">设备类型</th>
						<th onclick="sort(theTable,3,'string')">CPU数量</th>
						<th onclick="sort(theTable,4,'string')">内存大小</th>
						<th onclick="sort(theTable,5,'string')">存储大小</th>
						<th onclick="sort(theTable,6,'string')">申请数量</th>
						<th onclick="sort(theTable,7,'string')">处理状态</th>
						<th onclick="sort(theTable,8,'date')">处理时间</th>
						<th onclick="sort(theTable,9,'int')">处理次数</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="resultList" id="theBean">
						<tr>
							<td><s:property value="#theBean.PROJECT_NAME" /></td>
							<td><s:if test="#theBean.EQ_TYPE == 1">
												IBM小型机
											</s:if> <s:elseif test="#theBean.EQ_TYPE == 2">
												IBM X86刀片
											</s:elseif> <s:elseif test="#theBean.EQ_TYPE == 3">
												IBM PC服务器
											</s:elseif> <s:elseif test="#theBean.EQ_TYPE == 4">
												HP X86刀片
											</s:elseif> <s:elseif test="#theBean.EQ_TYPE == 5">
												机架服务器
											</s:elseif> <s:elseif test="#theBean.EQ_TYPE == 6">
												HP PC服务器
											</s:elseif></td>
							<td><s:property value="#theBean.CPU_NUM" />核</td>
							<td><s:if test="%{MEM_SIZE%1024==0}">
									<fmt:formatNumber type="currency" pattern="#0G"
										value="${MEM_SIZE/1024}"></fmt:formatNumber>
								</s:if> <s:else>
									<fmt:formatNumber type="currency" pattern="#0.00G"
										value="${MEM_SIZE/1024}"></fmt:formatNumber>
								</s:else></td>
							<td><s:if test="%{SR_SIZE%1024==0}">
									<fmt:formatNumber type="currency" pattern="#0G"
										value="${SR_SIZE/1024}"></fmt:formatNumber>
								</s:if> <s:else>
									<fmt:formatNumber type="currency" pattern="#0.00G"
										value="${SR_SIZE/1024}"></fmt:formatNumber>
								</s:else></td>
							<td><s:property value="#theBean.RESOURCE_NUM" />台</td>
							<td><s:if test="#theBean.STATE==0">
									<s:if test="#theBean.STATUS==3">处理失败</s:if>
									<s:elseif test="#theBean.STATUS==2">处理成功</s:elseif>
									<s:elseif test="#theBean.STATUS==1">处理中</s:elseif>
									<s:elseif test="#theBean.STATUS==0">待处理</s:elseif>
									<s:elseif test="#theBean.STATUS==4">未挂存储</s:elseif>
									<s:else>
										未知状态
									</s:else>
								</s:if> <s:else>
									工单数据不合法，不可处理
								</s:else></td>
							<td><s:property value="#theBean.DEALTIME" default="---" />
							</td>
							<td><s:property value="#theBean.DEAL_COUNT" />
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>

			<table id="theTable" width="100%" class="blue-table sorttable"
				border="0" cellspacing="0">
				<caption style="color: red;margin-top: 20px;font-size: 14px">审批历史</caption>
				<thead>
					<tr>
						<th onclick="sort(theTable,3,'string')">审批状态</th>
						<th onclick="sort(theTable,4,'string')">审批时间</th>
						<th onclick="sort(theTable,5,'date')">审批意见</th>
						<th onclick="sort(theTable, 6, 'string')">审批人</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><s:if test="#theBean.audit_type == 2">
							审批通过
						</s:if> <s:elseif test="#theBean.audit_type == 3">
							审批打回
						</s:elseif></td>

							<td><s:property value="#theBean.audit_date" /></td>

							<td><s:property value="#theBean.audit_info" /></td>

							<td><s:property value="#theBean.audit_user" /></td>

						</tr>
					</s:iterator>
				</tbody>
			</table>



		</div>



	</s:form>
</body>