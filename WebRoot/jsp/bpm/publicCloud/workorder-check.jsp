<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String excel_export_jsp = path + "/excel/excel_export.jsp";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>待审批工单-公有云服务</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/default.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/ui.js"></script>
  </head>
  <script type="text/javascript">
 	 function searchRequest(){
	    theForm.action = "<%=path%>/bpm/workorder_my.action";
	 	theForm.submit();
	} 
	function schedule(){
 		var inputObj = $('input:radio[name="entryId"]:checked');
			$.ajax({
        		type : 'post',
        		async : false,
        		url : '<%=path%>/bpm/workorder_schedule.action',
       			data:{"obj.entryId":inputObj.val(),"obj.resourceName":inputObj.attr("resourceName")},
       			dataType: 'json',
       			success : function(data){
       				window.open(data.url);		
       			},
       			error : function(data,textStatus){
       				console.log('error:' + data);
       			}
       		});
	}
  </script>
  <body>
    <s:form action="/bpm/workorder_my.action" method="post" cssClass="table-f1" id="theForm">
		<div class="scrollbody">
			<div class="box on">
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<ul class="btns">
							<li><input type="button" class="thickbox btn-style02-75" value="查看进度" name="modhost" onclick="schedule()"/></li>
							<li><input type="button" class="thickbox btn-style02-75" value="导出全部" onclick = "listExp();" /></li>
						</ul>
						<jsp:include page="/jsp/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">工单编号</th>
									<th onclick="sort(theTable,2,'string')">工单标题</th>
									<th onclick="sort(theTable,3,'string')">工单类型</th>
									<th onclick="sort(theTable,4,'string')">资源类型</th>
									<th onclick="sort(theTable,5,'date')">创建时间</th>
									<th onclick="sort(theTable,6,'string')">工单阶段</th>
									<th onclick="sort(theTable,7,'string')">当前处理人</th>
									<th onclick="sort(theTable,8,'date')">到达时间</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="list" id="obj">
									<tr>
										<td>
											<input type="hidden" id="stepId_<s:property value="#obj.entryId" />" value="<s:property value="#obj.step.stepId" />">
											<input type="radio" name="entryId" value="<s:property value="#obj.entryId" />"  resourceName="<s:property value="#obj.resourceName"/>">
										</td>
										<td>
											<a href="<%=path%>/bpm/workorder_edit.action?obj.id=<s:property value="#obj.id" />&position=recheck"><s:property value="#obj.orderNo" /></a>
										</td>
										<td>
											<s:property value="#obj.orderTitle" />
										</td>
										<td>
											<s:if test="#obj.orderType == '01'">
												资源开通
											</s:if>
											<s:elseif test="#obj.orderType == '02'">
												资源收回
											</s:elseif>
										</td>	
										<td>
											<s:if test="#obj.resType == '01'">
												云主机
											</s:if>
											<s:elseif test="#obj.resType == '02'">
												数据库
											</s:elseif>
										</td>	
										<td>
											<s:date name="#obj.createTime" format="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>
											<s:if test="#obj.step==null">
												结束
											</s:if>
											<s:else>												
												<s:property value="#obj.step.stepName" />
											</s:else>
										</td>	
										<td>
											<s:if test="#obj.step==null">
												--
											</s:if>
											<s:else>												
												<s:property value="#obj.step.caller" />
											</s:else>
										</td>	
										<td>
											<s:property value="#obj.step.startDate" />
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
	<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
  </body>
</html>
