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
					id:'sxaddWorkOrder',
					title:'添加工单',
					width:'800px',
					height:'500px',
					lock:true,
					content:'url:sxworkorder_addWorkOrder.do?oper=add'
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
						url:'sxworkorder_deleteWorkOrder.do?uuid='+uuid,
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
				var task_count = $td.attr("task_count");
				if(task_count <1 ){
					alert("该工单下无任务，请先添加任务！");
					return;
				}
				if (confirm("工单一旦执行，便不能再编辑工单下的任务，确定执行工单?")) {
					$.ajax({
						type:'post',
						url:'sxworkorder_exeWorkOrder.do?uuid='+uuid,
						success:function(msg){
							if (msg == -1) {
								alert("工单提交失败,无法执行");
							} else {
								alert("工单提交成功,等待执行");
								$("#theForm").submit();
							}
						}
					});
				}
			});
				
			
			
			$("[name='viewResource']").unbind().live("click",function(){
				var $td = $(this).parent();
				var uuid = $td.attr("uuid");
				var type = $td.attr("typer");
				var dealState = $td.attr("dealState");
				$.dialog({
					id:'viewResource',
					title:'管理工单任务',
					width: '1300px',
					height: '600px',
	    		    lock:true,
					content: 'url:sxworkorder_listResource.do?uuid='+uuid+'&type='+type+'&dealState=' +　dealState,
					close: function () {
						$("#theForm").submit();
				    }
				});
			});
		});
	  	
		function saveWorkOrder(theForm){
			$.ajax({
				type:'post',
				url:'sxworkorder_saveWorkOrder.do?' + theForm,
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
	<s:form action="sxworkorder_listWorkOrder.do" method="post" id="theForm" cssStyle="theForm">
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">工单编号:</td>
		                  		<td>
		                  			<s:textfield name="workOrderObj.uuid" id="bomc_uuid"></s:textfield> 
		                  		</td>
		                  		<td class="til">类型:</td>
		                  		<td>
		                  			<s:select list="#{'':'--请选择--','0':'申请','1':'调整','2':'回收'}" name="workOrderObj.type" id="type"></s:select>
		                  		</td>
<%--		                  		<td class="til">状态:</td>--%>
<%--		                  		<td>--%>
<%--		                  			<s:select list="#{'-1':'--请选择--','0':'未处理','1':'已处理'}" name="workOrderObj.dealState" id="dealState"></s:select>--%>
<%--		                  		</td>--%>
		                  		<td class="til">来源:</td>
		                  		<td>
		                  			<s:select list="#{'':'--请选择--','0':'BOMC','1':'云平台'}" name="workOrderObj.cameFrom" id="cameFrom"></s:select>
		                  		</td>
		                  		
		                  </tr>
		                  <tr>
		                    <td colspan="10" class="btns">
		                        <div>
									<input type = "button" class="thickbox btn-style02" value = "查询" id="searchForm" />
									<input type = "button" class="btn-style02" value = "重置" id="resetForm" />
		                        </div>
		                    </td>
		                  </tr>
	                </table>
				</div>
				
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="增加" id="addForm" /></li>
							<li><input type="button" class="thickbox btn-style02" value="删除" id="deleteForm" /></li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">选择</th>
									<th onclick="sort(theTable,1,'string')">工单编号</th>
									<th onclick="sort(theTable,2,'string')">工单类型</th>
									<th onclick="sort(theTable,3,'string')">工单状态</th>
									<th onclick="sort(theTable,4,'string')">工单来源</th>
<%--									<th onclick="sort(theTable,5,'string')">申请人</th>--%>
									<th onclick="sort(theTable,7,'string')">任务总数</th>
									<th onclick="sort(theTable,10,'string')">待处理</th>
									<th onclick="sort(theTable,11,'string')">处理中</th>
									<th onclick="sort(theTable,9,'string')">处理完成</th>
									<th onclick="sort(theTable,13,'date')">接收时间</th>
									<th onclick="sort(theTable,14,'date')">处理时间</th>
									<th onclick="sort(theTable,12,'string')">管理</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
					  				<tr>
					  					<td>
					  						<input type="checkbox" name="checkboxid" wuuid='<s:property value="#theBean.uuid"/>'
					  							wstat='<s:property value="#theBean.WSTAT"/>' camefrom='<s:property value="#theBean.cameFrom"/>'
					  							allresource='<s:property value="#theBean.ALLCOUNT" />' 
					  							/>
					  					</td>
					  					<td>
					  						<s:property value="#theBean.uuid"/>
					  					</td>
						  				<td>
						  					<s:if test="#theBean.type == 0">申请</s:if>
						  					<s:elseif test="#theBean.type == 1">调整</s:elseif>
						  					<s:elseif test="#theBean.type == 2">回收</s:elseif>
										</td>
						  				<td>
						  					<s:if test="#theBean.dealState == 0">未处理</s:if>
						  					<s:elseif test="#theBean.dealState == 1">已处理</s:elseif>
										</td>
						  				<td>
						  					<s:if test="#theBean.cameFrom == 0">BOMC</s:if>
						  					<s:elseif test="#theBean.cameFrom == 1">云平台</s:elseif>
										</td>

					  					<td >
					  						<s:property  value="#theBean.task_count"/>
					  						个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.task_wait_count"/>
					  						个
					  						
					  					</td>
					  					<td>
					  						<s:property value="#theBean.task_deal_count"/>
					  						个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.task_complete_count"/>
					  						个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.receiveTime"/>
					  					</td>
					  					<td>
					  					<s:if test="#theBean.exetime != null">
					  						<s:property value="#theBean.exetime"/>
					  						</s:if>
					  						<s:elseif test="#theBean.exetime == null">
					  						未处理
					  						</s:elseif>
					  					</td>
										<td uuid='<s:property value="#theBean.uuid"/>' states='<s:property value="#theBean.STATE"/>'
											typer = '<s:property value="#theBean.type"/>' 
											dealState = '<s:property value="#theBean.dealState"/>'   task_count='<s:property  value="#theBean.task_count"/>'>
											<s:if test="#theBean.dealState == 0">
												<a href="javascript:;" name="exeWorkOrder">执行工单</a>&nbsp;&nbsp;
												<a href="javascript:;" name="viewResource">管理任务</a>
											</s:if>
											<s:else>
												<a href="javascript:;" name="viewResource">查看任务</a>
											</s:else>
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
