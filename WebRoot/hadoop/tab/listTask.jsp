<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<style type="text/css">
	.percentage { 
	vertical-align:middle;  
	display:inline-block; 
	background: url(sxcloud/images/nresources/percentage-bg.gif) no-repeat; 
	width:148px; 
	height:14px; 
	margin-top: 4px;}
	.percentage b{ display:block; height:14px; background:url(sxcloud/images/nresources/percentage-bg4.gif) no-repeat; }
	.percentage b.red {background:url(sxcloud/images/nresources/percentage-bg3.gif) no-repeat;}
</style>
<head>
	<script type="text/javascript">
	    
		$(function(){
	             
	            /*  $("[name='queryJob']").click(function(){
		         currentEdit=$(this);
		         var id = $(this).attr("id");
// 		         var job_id = $(this).attr("job_id");
// 		         var tacking_ui = $(this).attr("tacking_ui");
	    		 $.dialog({
	    			id:'log',
	    			title:'查看task',
	    			width: '890px',
	    			height: '480px',
	    			max: true,
	    		    min: true,
	    			content: 'url:'
	    			});
	             }); */
		});
	</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="applicationObj">
		<div>
			<div class="box on">
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
								<!-- 	<th onclick="sort(theTable,1,'string')">taskID</th> -->
<!-- 									<th onclick="sort(theTable,2,'string')">名称</th> -->
<!-- 									<th onclick="sort(theTable,3,'string')">队列</th> -->
									<th onclick="sort(theTable,1,'string')">状态</th>
									<th onclick="sort(theTable,2,'string')">类型</th>
									<th onclick="sort(theTable,3,'string')">最后成功的ID</th>
									<th>进度</th>
									<th onclick="sort(theTable,5,'string')">开始时间</th>
									<th onclick="sort(theTable,6,'string')">完成时间</th>
<!-- 									<th onclick="sort(theTable,7,'string')">最终状态</th> -->
									<th onclick="sort(theTable,7,'string')">启动后经过的时间</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									<tr>
										<%-- <td>
											<s:property value="#theBean.taskId" />
										</td> --%>
										<td>
											<s:property value="#theBean.state" />
										</td>
										<td>
<!-- 											<s:if test="#theBean.type == "></s:if> -->
											<s:property value="#theBean.type"/>
										</td>
										<td>
											<s:property value="#theBean.successfulAttempt" />
										</td>
										<td>
											<div class="percentage"><b style="width:<s:property value="#theBean.progress"/>%"></b></div>
			  								<s:property value="#theBean.progress"/>%
										</td>
										<%-- <td>
											
											<s:property value="#theBean.progress" />
										</td> --%>
										<td>
											<s:property value="#theBean.startTime" />
										</td>
										<td>
											<s:property value="#theBean.finishTime"/>
										</td>
										<td>
											<s:property value="#theBean.elapsedTime"/>
										</td>
										<%-- <td>
											<s:property value="#theBean.Diagnostics"/>
										</td> --%>
										<%-- <td>
											<s:property value="#theBean.final_status"/>
										</td> --%>
										<%-- <td>
											<div class="percentage"><b style="width:<s:property value="#theBean.progress"/>%"></b></div>
			  								<s:property value="#theBean.progress"/>%
										</td> --%>
										<%-- <td>
											<a href="javascript:;" name="queryLog" id='<s:property value="#theBean.id"/>' job_id='<s:property value="#theBean.job_id"/>' tacking_ui='<s:property value="#theBean.tacking_ui"/>'>查询日志</a>
										</td> --%>
										<%-- <td>
											<a href="javascript:void(0);" name="queryTask" id='<s:property value="#theBean.id"/>' >task</a>
										</td> --%>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				<div class="table-head">
					<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=applicationObj" />
				</div>
				</div>
			</div>
		</div>
	</s:form>
</body>
