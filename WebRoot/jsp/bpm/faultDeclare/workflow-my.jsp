<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String excel_export_jsp = path + "/excel/excel_export.jsp";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的工单-故障申报</title>
    
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
	    theForm.action = "<%=path%>/bpm/workorder_my.action?obj.resourceName=faultDeclare";
	 	theForm.submit();
	} 
 	function addWorkOrder(){
 	    window.location.href="<%=path%>/bpm/workorder_edit.action?obj.resourceName=faultDeclare&position=remy";
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
 <div class="head">
	<div class="head-con">
    	<div class="logo fl"></div>
        <ul class="nav fl">
        	<li><a href="#">产品服务</a></li>
            <li><a href="#">用户中心</a></li>
            <li><a href="#">控制台</a></li>
            <li><a href="#">论坛</a></li>
        </ul>
        <div class="clear"></div>
    </div>
</div> 
    <s:form action="/bpm/workorder_my.action?obj.resourceName=faultDeclare" method="post" cssClass="table-f1" id="theForm">
		<div class="scrollbody">
			<div class="query">
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td  width="30%">
							</td>
							<td  width="50%">
								工单编号:
								<input type="text" name="obj.orderNo" value="${obj.orderNo}"/>
								<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<input type="button" class="thickbox btn-style02" value="新建" name="edit" onclick="addWorkOrder()"/>
						<input type="button" class="thickbox btn-style02-75" value="查看进度" name="modhost" onclick="schedule()"/>
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
									<th onclick="sort(theTable,2,'string')">工单类型</th>
									<th onclick="sort(theTable,5,'date')">申报时间</th>
									<th onclick="sort(theTable,6,'string')">工单阶段</th>
									<th onclick="sort(theTable,7,'string')">当前处理人/组</th>
									<th onclick="sort(theTable,8,'date')">到达时间</th>
									<th onclick="sort(theTable,9,'String')">紧急程度</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="list" id="obj">
									<tr>
										<td>
											<input type="hidden" id="stepId_<s:property value="#obj.entryId" />" value="<s:property value="#obj.step.stepId" />">
											<input type="radio" name="entryId" value="<s:property value="#obj.entryId"/>" resourceName="<s:property value="#obj.resourceName"/>">
										</td>
										<td>
											<a href="<%=path%>/bpm/workorder_view.action?obj.id=<s:property value="#obj.id" />"><s:property value="#obj.orderNo" /></a>
										</td>
										<td>
											<s:property value="#obj.orderTitle" />
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
											<s:property value="#obj.nextUser" /> 
											<s:property value="#obj.nextGroup" /> 
										</td>	
										<td>
											<s:property value="#obj.step.startDate" />
										</td>
										<td>
											<s:if test="#obj.orderLevel==0">
												普通
											</s:if>
										</td>																	
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<jsp:include page="/jsp/inc/Pagination.jsp?formId=theForm" />
					</div>
				</div>
			</div>
		</div>
		
	</s:form>
	<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
  </body>
</html>
