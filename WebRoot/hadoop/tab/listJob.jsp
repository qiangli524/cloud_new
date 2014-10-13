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
<style type="text/css">
		div.hidden{
		width:350px;
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
<head>
	<script type="text/javascript">
	    var api = frameElement.api; 
		var w = api.opener;
		$(function(){
	             $("[name='map']").click(function(){
			         currentEdit=$(this);
			         var id = $(this).attr("id");
			    		w.$.dialog({
			    			id:'task',
			    			title:'mapTask列表',
			    			width: '890px',
			    			height: '480px',
			    			max: true,
			    		    min: true,
			    		    lock:true,
	// 		    			content: 'url:hadoopTask_queryTaskListByObj.do?TaskObj.jobId='+id+'&time='+new Date()
							content: 'url:hadoopTask_queryTaskListByObj.do?TaskObj.job_id='+id+'&time='+new Date()+'&TaskObj.type='+'map'
			    			});
			     });
			     $("[name='reduce']").click(function(){
			         currentEdit=$(this);
			         var id = $(this).attr("id");
			    		w.$.dialog({
			    			id:'task',
			    			title:'reduceTask列表',
			    			width: '890px',
			    			height: '480px',
			    			max: true,
			    		    min: true,
			    		    lock:true,
	// 		    			content: 'url:hadoopTask_queryTaskListByObj.do?TaskObj.jobId='+id+'&time='+new Date()
							content: 'url:hadoopTask_queryTaskListByObj.do?TaskObj.job_id='+id+'&time='+new Date()+'&TaskObj.type='+'reduce'
			    			});
			     });
		});
	</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="applicationObj">
		<div>
			 <div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize " >
				<tr>
	  				<td style="color:#fff; background-color:#5a95ed;font-size:12px;font-weight:bold;"  width="100%" align="left" colspan="2">Job概况</td>
	  			</tr>
				<tr>
	  				<td class="til" width="30%" align="right">用户</td>
	  				<td align="left">
	  					&nbsp;&nbsp;<s:property value="jobObj.user" />
	  				</td>
	  			</tr>
				<tr>
	  				<td class="til" width="30%" align="right">名称</td>
	  				<td align="left">
	  					<div class="hidden" title='<s:property value="name"/>'>
					  		&nbsp;&nbsp;<s:property value="jobObj.name"/>
					  	</div>
					</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="right">状态</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="jobObj.state"/></td>
	  			</tr>
	  			<tr >
					<td class="til" align="right">完整情况</td>
					<td align="left">&nbsp;&nbsp;<s:property value="jobObj.uberized" /></td>
				</tr> 
	  			<tr>
	  				<td class="til" align="right">开始时间</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="jobObj.startTime" /></td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="right">完成时间</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="jobObj.finishTime" /></td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="right">启动后经过的时间</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="jobObj.elapsedTime"/></td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="right">信息</td>
	  				<td align="left">
	  					<div class="hidden" title='<s:property value="jobObj.Diagnostics"/>'>
					  		<s:property value="jobObj.Diagnostics"/>
					  	</div>
					</td>
	  			</tr>
			</table>
		</div>
		<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,1,'string')" width="5%">任务类型</th>
									<th onclick="sort(theTable,2,'string')" width="10%">总数</th>
									<th onclick="sort(theTable,3,'string')" width="5%">已完成数</th>
								</tr>
							</thead>
							<tbody>
									<tr>
										<td>
											<a href="javascript:void(0)" name="map" id='<s:property value="jobObj.job_id"/>'>Map</a>
										</td>
										<td>
					  						<s:property value="jobObj.mapsTotal"/>
										</td>
										<td>
											<s:property value="jobObj.mapsCompleted" />
										</td>
									</tr>
									<tr>
										<td>
											<a href="javascript:void(0)" name="reduce" id='<s:property value="jobObj.job_id"/>'>Reduce</a>
										</td>
										<td>
					  						<s:property value="jobObj.reducesTotal"/>
										</td>
										<td>
											<s:property value="jobObj.reducesCompleted" />
										</td>
									</tr>
							</tbody>
						</table>
					</div>
		</div>
	</s:form>
</body>
