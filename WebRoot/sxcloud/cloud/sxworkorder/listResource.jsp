<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<title></title>
	<style type="text/css">
		div.hidden{
		width:50px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
</style>
<style type="text/css">
		.font-more{ width:10px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
  <script type="text/javascript">
	  	$(function(){
			$check = $(":checkbox");
			$check.unbind().live("click",function(){
				$check.not(this).attr("checked",false);
			});
		});	
  	
	  	var api = frameElement.api;
	  	var w = api.opener;
	  	
		$(function(){
			$("[name='dealbyhand']").unbind().live("click",function(){
				var $td = $(this).parent();
				var wrid = $td.attr("wrid");
				var uuid = $td.attr("uuid");
				$.ajax({
					type:'post',
					url:'sxworkorder_dealByHand.do?wrid='+wrid+'&uuid='+uuid,
					success:function(msg){
						if(msg==-1){
							alert("执行失败");
			        	}else{
			        		alert("执行成功");
			        		$("#theForm").submit();
			        	}
					}
				});
			});
			
			
			$("#addForm").click(function(){
				var type = $("#type").val();
				var uuid = $("#uuid").val();
				var workOrderId = $("#workOrderId").val();
				if($("#dealState").val() == 1){
					alert('工单已执行，不能添加任务！');
					return;
				}
				w.$.dialog({
					id:'addResource',
					title:'添加VMWARE任务',
					width:'600px',
					height:'500px',
					lock:true,
					content:'url:sxworkorder_addResource.do?oper=add&uuid='+uuid+'&workOrderId='+workOrderId+'&type='+type
				});
			});
			
			$("#addSceForm").click(function(){
				var type = $("#type").val();
				var uuid = $("#uuid").val();
				var workOrderId = $("#workOrderId").val();
				if($("#dealState").val() == 1){
					alert('工单已执行，不能添加任务！');
					return;
				}
				w.$.dialog({
					id:'addResource',
					title:'添加SCE任务',
					width:'600px',
					height:'500px',
					lock:true,
					content:'url:sxworkorder_addSceResource.do?oper=add&uuid='+uuid+'&workOrderId='+workOrderId+'&type='+type
				});
			});
			
			$("#editForm").click(function(){
				if ($(":checkbox:checked").length == 0) {
					alert("请选择一项任务进行修改");
					return false;
				}
				var wrid = "";
				var orderTaskType = "";
				$(":checkbox:checked").each(function(){
					wrid += $(this).attr("value");
					orderTaskType += $(this).attr("typer");
				});
				var type = $("#type").val();
				var uuid = $("#uuid").val();
				if($("#dealState").val() == 1){
					alert('工单已执行，不能修改任务！');
					return;
				}
				w.$.dialog({
					id:'editResource',
					title:'编辑任务',
					width:'600px',
					height:'500px',
					lock:true,
					content:'url:sxworkorder_editResource.do?wrid='+wrid+'&oper=edit&orderTaskType='+orderTaskType+'&type='+type+'&uuid='+uuid
				});
			});
			
			$("#deleteForm").click(function(){
				if($("#dealState").val() == 1){
					alert('工单已执行，不能修改任务！');
					return;
				}
				if($(":checkbox:checked").length == 0){
					alert("请选择一项进行删除");
					return false;
				}
				var wrid = "";
				$(":checkbox:checked").each(function(){
					wrid += $(this).attr("value");
				});
				if (confirm("你确定要进行删除操作吗？")) {
					$.ajax({
						type:'post',
						url:'sxworkorder_deleteResource.do?wrid='+wrid,
						success:function(msg){
							if (msg == -1) {
								alert("删除失败");
							} else {
								$("#theForm").submit();
							}
						}
					});
				}
			});
			
		});
		
		$(function(){
			var wstat = $("#wstat").val();
			if (wstat == 1) {
				document.getElementById("addForm").style.display="none";
				document.getElementById("editForm").style.display="none";
				document.getElementById("deleteForm").style.display="none";
			}
		});
		
		function saveResource(theForm){
			$.ajax({
				type:'post',
				url:'sxworkorder_saveResource.do',
				data:theForm,
				success:function(msg){
					if (msg == -1) {
						alert("保存失败");
					} else {
						$("#theForm").submit();
					}
				}
			});
		}
		
		function saveSceResource(theForm){
			$.ajax({
				type:'post',
				url:'sxworkorder_saveSceResource.do',
				data:theForm,
				success:function(msg){
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
	<s:form action="sxworkorder_listResource.do" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="type" id="type"></s:hidden>
	<s:hidden name="uuid" id="uuid"></s:hidden>
	<s:hidden name="dealState" id="dealState"></s:hidden>
<%--	<s:hidden name="projectid" id="projectid"></s:hidden>--%>
<%--	<s:hidden name="wstat" id="wstat"></s:hidden>--%>
<%--	<s:hidden name="state" id="state"></s:hidden>--%>
<%--	<s:hidden name="busisystemid" id="busisystemid"></s:hidden>--%>
		<div class="scrollbody">
			<div class="box on">
				<div class="blue-wrap noborder" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="增加x86" id="addForm" /></li>
							<li><input type="button" class="thickbox btn-style02" value="增加ibm" id="addSceForm" /></li>
							<li><input type="button" class="thickbox btn-style02" value="修改" id="editForm" /></li>
							<li><input type="button" class="thickbox btn-style02" value="删除" id="deleteForm" /></li>
						</ul>
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th>选择</th>
<%--									<th onclick="sort(theTable,1,'string')">任务编号</th>--%>
									<th onclick="sort(theTable,2,'string')">任务类型</th>
									<th onclick="sort(theTable,3,'string')">虚拟机名称</th>
									<th onclick="sort(theTable,4,'string')">CPU数量(颗)</th>
									<th onclick="sort(theTable,4,'string')">分布物理cpu(颗)</th>
									<th onclick="sort(theTable,5,'string')">内存大小(M)</th>
									<th onclick="sort(theTable,6,'string')">存储大小(G)</th>
									<th onclick="sort(theTable,6,'string')">扩展存储(G)</th>
									<th onclick="sort(theTable,7,'string')">网络域1</th>
									<th onclick="sort(theTable,8,'string')">网络域2</th>
									<th onclick="sort(theTable,8,'string')">集群名称</th>
									<th onclick="sort(theTable,9,'string')">模板名称</th>
									<th onclick="sort(theTable,9,'string')">资源释放日期</th>
									<th onclick="sort(theTable,8,'string')">虚拟机个数</th>
									<th onclick="sort(theTable,8,'string')">成功个数</th>
									<th onclick="sort(theTable,8,'string')">失败个数</th>
									<th onclick="sort(theTable,8,'string')">待处理个数</th>
									<th onclick="sort(theTable,10,'string')">处理状态</th>
									<th onclick="sort(theTable,11,'string')">人工处理</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="orderTaskList" id="theBean">
					  				<tr>
					  					<td>
					  						<input type="checkbox" name="checkboxid" typer="<s:property value='#theBean.type'/>" value="<s:property value='#theBean.uuid'/>" />
					  					</td>
<%--					  					<td>--%>
<%--					  						<span style="color: black;" class="font-more" title='<s:property value="#theBean.uuid"/>'>--%>
<%--												<s:property value="#theBean.uuid"/>--%>
<%--											</span>--%>
<%--					  					</td>--%>
					  					<td>
					  					<s:if test="#theBean.type==1">vmware</s:if>
											<s:elseif test="#theBean.type==0">sce</s:elseif>
					  					</td>
						  				<td><s:property value="#theBean.name" /></td>
						  				<td>
						  					<s:property value="#theBean.cpuCount" />
										</td>
						  				<td><s:if test="#theBean.type==1">--</s:if>
											<s:elseif test="#theBean.type==0"><s:property value="#theBean.cpushared" /></s:elseif>
						  					
										</td>
						  				<td>
						  					<s:property value="#theBean.memory" />
										</td>
						  				<td><s:property value="#theBean.storage" />
													</td>
						  				<td>
						  				<s:if test="#theBean.type==1"><s:property value="#theBean.expandStorage" /></s:if>
											<s:elseif test="#theBean.type==0">--</s:elseif>
													</td>
						  				<td><s:property value="#theBean.net1name" />
													</td>
										<td>
										<s:if test="#theBean.net2==-1" >
											--</s:if><s:else><s:property value="#theBean.net2name"/></s:else>
										</td>
										<td>
										<s:if test="#theBean.type==1"><s:property value="#theBean.clusterName" /></s:if>
											<s:elseif test="#theBean.type==0">--</s:elseif>
										</td>
										<td><s:property value="#theBean.templateName" />
													</td>
										<td><s:property value="#theBean.freeDate" />
													</td>
													
										<td><s:property value="#theBean.vmNum" />
													</td>
										<td><s:property value="#theBean.success" />
													</td>
										<td><s:property value="#theBean.failNum" />
													</td>
										<td><s:property value="#theBean.waiting" />
													</td>
										<td><s:if test="#theBean.failNum>0&&#theBean.vmNum>#theBean.success">处理失败</s:if>
											<s:elseif test="#theBean.failNum==0&&#theBean.success==0">待处理</s:elseif>
											<s:elseif test="#theBean.vmNum>#theBean.success">处理中</s:elseif>
											<s:elseif test="#theBean.vmNum<=#theBean.success">处理成功</s:elseif>
													</td>
										<td wrid='<s:property value="#theBean.uuid"/>' uuid='<s:property value="uuid"/>'>
<%--											<s:if test="state==0">--%>
												<s:if test="#theBean.waiting<#theBean.vmNum&&#theBean.failNum>0&&#theBean.vmNum>#theBean.success">
													<a href="javascript:;" name="dealbyhand">处理</a>
												</s:if>
												<s:else>
												无需人工
<%--													工单处理中或待处理或处理成功--%>
												</s:else>
<%--											</s:if>--%>
<%--											<s:else>--%>
<%--												工单数据不合法，不可处理--%>
<%--											</s:else>--%>
										</td>
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
