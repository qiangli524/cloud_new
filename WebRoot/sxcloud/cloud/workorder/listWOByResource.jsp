<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
		.font-more{ width:80px;height:20px;line-height:20px;overflow: hidden;
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
	
		$(function(){
			$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    		});
			
			$("#searchForm").click(function(){
				$("#theForm").submit();
			});
			
			$("#resetForm").click(function(){
				$("#type").val("-1");
				$("#project").val("-1");
				$("#bomc_uuid").val("");
				$("#camefrom").val("-1");
				$("#wstats").val("-1");
			});
			
			$("#addForm").click(function(){
				$.dialog({
					id:'addWorkOrder',
					title:'添加工单',
					width:'800px',
					height:'500px',
					lock:true,
					content:'url:workorder/workorder_addWorkOrder.do?oper=add'
				});
			});
			
			$("#deleteForm").click(function(){
				if ($(":checkbox:checked").length == 0) {
					alert("请选择一项进行删除");
					return false;
				}
				var uuid = "";
				var wstat;
				var camefrom;
				var allresource = "";
				$(":checkbox:checked").each(function(){
					uuid += $(this).attr("wuuid");
					wstat = $(this).attr("wstat");
					camefrom = $(this).attr("camefrom");
					allresource = $(this).attr("allresource");
				});
				
				if (confirm("你确定删除该工单吗？")) {
					if (wstat == 1) {
						alert("该工单已执行，不可删除");
						return false;
					}
					if (camefrom == 0) {
						alert("该工单来自BOMC，不可删除");
						return false;
					}
					if (allresource != 0) {
						alert("该工单下面尚有任务，请先删除任务");
						return false;
					}
					$.ajax({
						type:'post',
						url:'workorder/workorder_deleteWorkOrder.do?uuid='+uuid,
						success:function(msg){
							if (msg == -1) {
								alert("删除失败！");
							} else{
								$("#theForm").submit();
							}
						}
					});
				}
			});
			
			$("[name='exeWorkOrder']").unbind().live("click",function(){
				var $td = $(this).parent();
				var uuid = $td.attr("uuid");
				if (confirm("工单一旦执行，便不能再编辑工单下的任务，确定执行工单?")) {
					$.ajax({
						type:'post',
						url:'workorder/workorder_exeWorkOrder.do?uuid='+uuid,
						success:function(msg){
							if (msg == -1) {
								alert("执行失败");
							} else {
								alert("执行成功");
								$("#theForm").submit();
							}
						}
					});
				}
			});
				
			
			
			$("[name='viewResource']").unbind().live("click",function(){
				var $td = $(this).parent();
				var uuid = $td.attr("uuid");
				var state = $td.attr("states");
				var type = $td.attr("typer");
				var projectid = $td.attr("projectid");
				var wstat = $td.attr("wstat");
				$.dialog({
					id:'viewResource',
					title:'查看工单任务',
					width: '1000px',
					height: '600px',
					content: 'url:workorder/workorder_listResource.do?uuid='+uuid+'&state='+state+'&type='+type+'&projectid='+projectid+'&wstat='+wstat
				});
			});
		});
	  	
		function saveWorkOrder(theForm){
			$.ajax({
				type:'post',
				url:'workorder/workorder_saveWorkOrder.do?' + theForm,
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
	<s:form action="workorder/workorder_listWOByResource.do" method="post" id="theForm" cssStyle="theForm">
			<div class="clearfix filtrate-area">
				<div class="filtrate-field">
        			<label class="vm">工单编号:</label>
		                  			<s:textfield name="workOrderObj.BOMC_UUID" id="bomc_uuid"></s:textfield> 
				</div>
				<div class="filtrate-field">
        			<label class="vm">类型:</label>
		                  			<s:select list="#{'-1':'--请选择--','0':'申请','1':'调整','2':'回收'}" name="workOrderObj.TYPE" id="type"></s:select>
				</div>
				<div class="filtrate-field">
        			<label class="vm">状态:</label>
		                  			<s:select list="#{'-1':'--请选择--','0':'未处理','1':'已处理'}" name="workOrderObj.WSTAT" id="wstats"></s:select>
				</div>
				<div class="filtrate-field">
        			<label class="vm">来源:</label>
		                  			<s:select list="#{'-1':'--请选择--','0':'BOMC','1':'云平台'}" name="workOrderObj.CAMEFROM" id="camefrom"></s:select>
				</div>
				<div class="filtrate-field">
        			<label class="vm">所属项目:</label>
		           <s:select list="projectList" listKey="ID" listValue="PROJECT_NAME" name="workOrderObj.PROJECT_ID" 
		                  				id="project" headerKey="-1" headerValue="请选择"></s:select>
				</div>
				<div class="filtrate-field">
        			<span class="ubtn-1 mgl-20"><input type="button"  id="searchForm"   value="查询" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" id="resetForm" value="重置" /></span>
				</div>
			</div>
			<div class="utt-2 mgt-20">
			</div>
			
			<div class="box on">
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,1,'string')">工单编号</th>
									<th onclick="sort(theTable,2,'string')">类型</th>
									<th onclick="sort(theTable,3,'string')">状态</th>
									<th onclick="sort(theTable,4,'string')">来源</th>
									<th onclick="sort(theTable,5,'string')">任务总数</th>
									<th onclick="sort(theTable,6,'string')">成功</th>
									<th onclick="sort(theTable,7,'string')">失败</th>
									<th onclick="sort(theTable,8,'string')">待处理</th>
									<th onclick="sort(theTable,9,'string')">处理中</th>
									<th onclick="sort(theTable,10,'string')">未挂存储</th>
									<%--<th onclick="sort(theTable,12,'String')">处理信息</th>
									<th onclick="sort(theTable,13,'date')">接收时间</th>
									<th onclick="sort(theTable,14,'date')">处理时间</th>
									--%>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
					  				<tr>
					  					<%--<td>
					  						<input type="checkbox" name="checkboxid" wuuid='<s:property value="#theBean.UUID"/>'
					  							wstat='<s:property value="#theBean.WSTAT"/>' camefrom='<s:property value="#theBean.CAMEFROM"/>'
					  							allresource='<s:property value="#theBean.ALLCOUNT" />' />
					  					</td>
					  					--%><td>
					  						<s:property value="#theBean.BOMC_UUID"/>
					  					</td>
						  				<td>
						  					<s:if test="#theBean.TYPE == 0">申请</s:if>
						  					<s:elseif test="#theBean.TYPE == 1">调整</s:elseif>
						  					<s:elseif test="#theBean.TYPE == 2">回收</s:elseif>
										</td>
						  				<td>
						  					<s:if test="#theBean.WSTAT == 0">未处理</s:if>
						  					<s:elseif test="#theBean.WSTAT == 1">已处理</s:elseif>
										</td>
						  				<td>
						  					<s:if test="#theBean.CAMEFROM == 0">BOMC</s:if>
						  					<s:elseif test="#theBean.CAMEFROM == 1">云平台</s:elseif>
										</td>
					  					<td>
					  						<s:property value="#theBean.ALLCOUNT"/>个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.SUCCESS"/>个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.FALUIRE"/>个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.WAITDEAL"/>个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.DEALING"/>个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.UNMOUNTSTORE"/>个
					  					</td>
										<%--<td align="center">
											<span style="color: black;" class="font-more" title='<s:property value="#theBean.WMESSAGE"/>'>
												<s:property value="#theBean.WMESSAGE" default="无"/>
											</span>
										</td>
						  				<td>
						  					<s:property value="#theBean.RECEIVETIME" />
										</td>
										<td>
											<s:property value="#theBean.EXETIME" />
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
			</div>
	</s:form>
</body>
