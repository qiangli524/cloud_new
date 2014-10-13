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
div.hidden {
	width: 50px;
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
	width: 100px;
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

	var api = frameElement.api;
	var w = api.opener;

	$(function() {
		$("[name='dealbyhand']").unbind().live(
				"click",
				function() {
					var $td = $(this).parent();
					var wrid = $td.attr("wrid");
					var resource_type = $("#resource_type").val();
					//var vmname =  $td.attr("vmname");
					//if(vmname.length == 0){
					//	alert("请修改虚拟机名称");
					//	return;
					//}
					$.ajax({
						type : 'post',
						url : 'workorder_dealByHand.do?wrid=' + wrid
								+ '&resource_type=' + resource_type,
						success : function(msg) {
							if (msg == -1) {
								alert("执行失败");
							} else {
								alert("执行成功");
								$("#theForm").submit();
							}
						}
					});
				});

		$("[name='deploy']").unbind().live("click", function() {
			var $td = $(this).parent();
			var wrid = $td.attr("wrid");
			if (confirm("你确定该虚拟机已经挂载存储并能够交付使用了吗？")) {
				$.ajax({
					type : 'post',
					url : 'workorder_deployVM.do?wrid=' + wrid,
					success : function(msg) {
						if (msg == -1) {
							alert("很遗憾，该虚拟机交付失败！");
						} else {
							alert("恭喜，该虚拟机交付成功");
							$("#theForm").submit();
						}
					}
				});
			}
		});

		$("#addForm")
				.click(
						function() {
							var type = $("#type").val();
							var uuid = $("#uuid").val();
							var resource_type = $("#resource_type").val();
							w.$
									.dialog({
										id : 'addResource',
										title : '添加任务',
										width : '500px',
										height : '450px',
										max : false,
										min : false,
										content : 'url:workorder_addResource.do?operFrom=addResource&oper=add&type='
												+ type
												+ '&uuid='
												+ uuid
												+ '&resource_type='
												+ resource_type
									});
						});

		$("#editForm")
				.click(
						function() {
							if ($(":checkbox:checked").length == 0) {
								alert("请选择一项任务进行修改");
								return false;
							}
							var wrid = "";
							var status = "";
							$(":checkbox:checked").each(function() {
								wrid += $(this).attr("value");
								status = $(this).attr("status");
							});
							if (status == 0 || status == 3) {
								var type = $("#type").val();
								var uuid = $("#uuid").val();
								var resource_type = $("#resource_type").val();
								if (type == 2 && resource_type == 1) {
									alert("不能修物理资源回收任务，请直接删除后新建");
									return false;
								}
								w.$
										.dialog({
											id : 'editResource',
											title : '编辑任务',
											width : '550px',
											height : '450px',
											max : false,
											min : false,
											content : 'url:workorder_editResource.do?operFrom=editResource&wrid='
													+ wrid
													+ '&oper=edit&type='
													+ type
													+ '&uuid='
													+ uuid
													+ '&resource_type='
													+ resource_type
										});
							} else {
								alert("处理中/处理成功,不准许修改");
								return false;
							}

						});

		$("#deleteForm").click(function() {
			if ($(":checkbox:checked").length == 0) {
				alert("请选择一项进行删除");
				return false;
			}
			var wrid = "";
			$(":checkbox:checked").each(function() {
				wrid += $(this).attr("value");
			});
			if (confirm("你确定要进行删除操作吗？")) {
				$.ajax({
					type : 'post',
					url : 'workorder_deleteResource.do?wrid=' + wrid,
					success : function(msg) {
						if (msg == -1) {
							alert("删除失败");
						} else {
							$("#theForm").submit();
						}
					}
				});
			}
		});

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

	$(function() {
		var wstat = $("#wstat").val();
		if (wstat == 1) {
			document.getElementById("addForm").style.display = "none";
			//document.getElementById("editForm").style.display="none";
			document.getElementById("deleteForm").style.display = "none";
		}
	});

	function saveResource(theForm) {
		$.ajax({
			type : 'post',
			url : 'workorder_saveResource.do',
			data : theForm,
			success : function(msg) {
				if (msg == -1) {
					alert("保存失败");
				} else if (msg == -2) {
					alert("处理中\处理成功,不准许修改");
				} else {
					$("#theForm").submit();
				}
			}
		});
	}
</script>
</head>
<body>
	<s:form action="workorder_listResource.do" method="post" id="theForm"
		cssStyle="theForm">
		<s:hidden name="type" id="type"></s:hidden>
		<s:hidden name="uuid" id="uuid"></s:hidden>
		<s:hidden name="wstat" id="wstat"></s:hidden>
		<s:hidden name="state" id="state"></s:hidden>
		<s:hidden name="resource_type" id="resource_type"></s:hidden>

		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">工单任务管理</h2>
				<div class="utt-2 mgt-20">
					<a class="icon-add" href="javascript:void(0)" id="addForm">新增</a>
					<a class="icon-modify" href="javascript:void(0)" id="editForm">编辑</a>
					<a class="icon-del" href="javascript:void(0)" id="deleteForm">删除</a>
					<a class="icon-occupy" href="javascript:void(0)"
						onclick="viewResourceClose()">返回</a> <a class="icon-release"
						href="javascript:void(0)" onclick="refresh()">刷新</a>
				</div>

				<table id="theTable" width="100%" class="blue-table sorttable"
					border="0" cellspacing="0">
					<thead>
						<tr>
							<th>选择</th>
							<%-- <th onclick="sort(theTable,1,'string')">任务编号</th> --%>
							<th onclick="sort(theTable,1,'string')">主机名</th>
							<th onclick="sort(theTable,2,'string')">项目名称</th>
							<th onclick="sort(theTable,3,'string')">CPU数量</th>
							<th onclick="sort(theTable,4,'string')">内存大小</th>
							<th onclick="sort(theTable,5,'string')">存储大小</th>
							<!--  
									<th onclick="sort(theTable,6,'string')">网络域</th>
									<th onclick="sort(theTable,7,'string')">IP地址</th>-->
							<th onclick="sort(theTable,8,'string')">模板名称</th>
							<th onclick="sort(theTable,9,'string')">模板类型</th>
							<th onclick="sort(theTable,10,'string')">处理状态</th>
							<th onclick="sort(theTable,11,'date')">处理时间</th>
							<th onclick="sort(theTable,12,'int')">处理次数</th>
							<th onclick="sort(theTable,13,'string')">人工处理</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="resultList" id="theBean">
							<tr>
								<td><input type="checkbox" name="checkboxid"
									value="<s:property value='#theBean.ID'/>"
									status='<s:property value="#theBean.STATUS" />' /></td>
								<%--  
					  					<td>
					  						<span style="color: black;" class="font-more" title='<s:property value="#theBean.ID"/>'>
												<s:property value="#theBean.ID"/>
											</span>
					  					</td>
					  					--%>
								<td><s:property value="#theBean.VM_NAME" /></td>
								<td><s:property value="#theBean.PROJECT_NAME" /></td>
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
								<!-- 
						  				<td><s:property value="#theBean.NETWORK_NAME" />
													</td>
						  				<td><s:property value="#theBean.IPADDRESS" />
													</td> -->
								<td><s:property value="#theBean.TEMPLATENAME" /></td>
								<td><s:if test="#theBean.TEMP_TYPE==1">
											VMWARE
											</s:if> <s:if test="#theBean.TEMP_TYPE==2">
											XEN
											</s:if> <s:else>
											物理机
											</s:else> <!--  <s:if test="#theBean.TEMP_TYPE==4">
											物理机
											</s:if> --></td>
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
								<td wrid='<s:property value="#theBean.ID"/>'><s:if
										test="state==0">
										<s:if test="#theBean.STATUS==3">
											<a href="javascript:;" name="dealbyhand">处理</a>
										</s:if>
										<s:elseif test="#theBean.STATUS==2">
											<a href="javascript:;" name="deploy">交付</a>
										</s:elseif>
									</s:if> &nbsp;&nbsp;&nbsp; <a href="javascript:;"
									name="resourceDetail">查看</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
		</div>
		</div>
	</s:form>
</body>
