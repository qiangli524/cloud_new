<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
<style type="text/css">
	.percentage { 
	vertical-align:middle;  
	display:inline-block; 
	background: url(sxcloud/images/nresources/greyp.gif) no-repeat; 
	width:73px; 
	height:12px; 
	margin-top: 4px;}
	.percentage b{ display:block; height:14px; background:url(sxcloud/images/nresources/greenp.gif) no-repeat; }
	.percentage b.red {background:url(sxcloud/images/nresources/redp.gif) no-repeat;}
</style>
<script type="text/javascript">
var api = frameElement.api;
var w = api.opener;
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
			$("#busiName").val(null);
			$("#vmName").val(null);
		});
		
		$("[name='viewVmInfo']").unbind().live("click",function(){
			var $td = $(this).parent();
			var connect_id = $td.attr("connectId");
			var entity_id = $td.attr("entityId");
			w.$.dialog({
	 			id:'vmhostConfig',
	 			title:'虚拟机配置信息',
	 			width:'600px',
	 			height:'400px',
	 			content:'url:resourceOutline_queryVMConfigInfo.do?topTargetObj.connect_uuid='+connect_id+'&topTargetObj.entity_id='+entity_id
        	});
		});
	});
</script>
</head>
<body>
	<s:form action="resourceOutline_listVmTopNDetail.do" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="resourceType" id="resourceType"></s:hidden>
			<div class="box on">
			<div class="clearfix filtrate-area">
				<div class="filtrate-field">
        			<label class="vm">业务名称:</label>
		            <s:textfield name="topTargetObj.businame" id="busiName"></s:textfield> 
				</div>
				<div class="filtrate-field">
        			<label class="vm">虚拟机名称:</label>
		            <s:textfield name="topTargetObj.name" id="vmName"></s:textfield> 
				</div>
				<div class="filtrate-field">
        			<span class="ubtn-1 mgl-20"><input type="button"  id="searchForm"   value="查询" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" id="resetForm" value="重置" /></span>
				</div>
			</div>
			<div class="utt-2 mgt-20">
			</div>
			
				<div class="blue-wrap noborder" >
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th>业务名称</th>
									<th>虚拟机名称</th>
									<th>cpu利用率</th>
									<th>内存利用率</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="globalList" id="theBean">
									<tr>
										<td>
											<s:property value="#theBean.businame"/>
										</td>
										<td entityId='<s:property value="#theBean.entity_id" />' 
											connectId='<s:property value="#theBean.connect_uuid" />'>
											<a href="javascript:;" name="viewVmInfo">
												<s:property value="#theBean.name"/>
											</a>
										</td>
										<td>
											<div class="percentage">
												<s:if test="#theBean.cpu_usage > 50">
													<b class="red" style='width: <s:property value="#theBean.cpu_usage"/>%'>
													</b>
												</s:if>
												<s:else>
													<b style='width: <s:property value="#theBean.cpu_usage"/>%'>
													</b>
												</s:else>
											</div>
											<s:property value="#theBean.cpu_usage"/>%
										</td>
										<td>
											<div class="percentage">
												<s:if test="#theBean.mem_usage > 75">
													<b class="red" style='width: <s:property value="#theBean.mem_usage"/>%'>
													</b>
												</s:if>
												<s:else>
													<b style='width: <s:property value="#theBean.mem_usage"/>%'>
													</b>
												</s:else>
											</div>
											<s:property value="#theBean.mem_usage"/>%
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