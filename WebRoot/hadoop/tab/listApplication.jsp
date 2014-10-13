<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<style type="text/css">
		div.hidden{
		width:200px;
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
	             $("[name='queryJob']").click(function(){
		         currentEdit=$(this);
		         var id = $(this).attr("id");
	    		 $.dialog({
	    			id:'job',
	    			title:'Job列表',
	    			width: '890px',
	    			height: '480px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:hadoopJob_queryJobListByObj.do?jobObj.application_id='+id+'&time = '+ new Date()
	    			});
	             });
	             
	         $("#searchForm").click(function(){
	        	 $("#applicationObj").submit();
	         });
	         
	         $("#resetForm").click(function(){
	        	 $("#user").val(null);
	        	 $("#name").val(null);
	        	 $("#state").val(null);
	        	 $("#status").val(null);
	         });
		});
	</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="hadoopApplication_queryApplicationListByObj.do" method="post" id="applicationObj">
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">Job管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
						<label class="vm">用户：</label>
								<s:textfield name="applicationObj.user" id="user"></s:textfield>
							<label class="vm">名称：</label>
								<s:textfield name="applicationObj.name" id="name"></s:textfield>
							<label class="vm">状态：</label>
								<s:textfield name="applicationObj.state" id="state"></s:textfield>
							<label class="vm">最终状态：</label>
								<s:textfield name="applicationObj.final_status" id="status"></s:textfield>
								<br>
								<br/>
							 <span class="ubtn-1 mgl-20"><input id="searchForm" type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input id="resetForm" type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
			
			<br>
								<br/>
				
				
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,1,'string')" width="5%">用户</th>
									<th onclick="sort(theTable,2,'string')" width="15%">名称</th>
									<th onclick="sort(theTable,3,'string')" width="5%">队列</th>
									<th onclick="sort(theTable,4,'date')" width="10%">开始时间</th>
									<th onclick="sort(theTable,5,'date')" width="10%">完成时间</th>
									<th onclick="sort(theTable,6,'string')" width="5%">状态</th>
									<th onclick="sort(theTable,7,'string')" width="5%">最终状态</th>
									<th onclick="sort(theTable,8,'string')" width="5%">进度</th>
									<th onclick="sort(theTable,9,'string')">Tracking UI</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									<tr>
										<td>
											<s:property value="#theBean.user" />
										</td>
										<td>
											<div class="hidden" title='<s:property value="#theBean.name"/>'>
					  							<s:property value="#theBean.name"/>
					  						</div>
<!-- 											<s:property value="#theBean.name" /> -->
										</td>
										<td>
											<s:property value="#theBean.queue" />
										</td>
										<td>
											<s:property value="#theBean.start_time" />
										</td>
										<td>
											<s:property value="#theBean.finish_time"/>
										</td>
										<td>
											<s:property value="#theBean.state"/>
										</td>
										<td>
											<s:property value="#theBean.final_status"/>
										</td>
										<td>
											<div class="percentage"><b style="width:<s:property value="#theBean.progress"/>%"></b></div>
			  								<s:property value="#theBean.progress"/>%
										</td>
										<%-- <td>
											<a href="javascript:;" name="queryLog" id='<s:property value="#theBean.id"/>' job_id='<s:property value="#theBean.job_id"/>' tacking_ui='<s:property value="#theBean.tacking_ui"/>'>查询日志</a>
										</td> --%>
										<td>
											<a href="javascript:void(0);" name="queryJob" id='<s:property value="#theBean.app_id"/>' ><s:property value="#theBean.tracking_ui"/></a>
										</td>
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
