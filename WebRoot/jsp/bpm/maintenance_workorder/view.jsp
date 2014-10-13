<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>运维处理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath%>jsp/css/alicss.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jattachment.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/dateformat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/maintenance_workorder_back.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/json.util.js"></script>

<script type="text/javascript">
$(document).ready(function(){
		var entryId = "${requestScope.obj.entryId}";
		getWorkFlowByEntryId(entryId);
		getStepListByEntryId(entryId);
		
		//保存流程
		$("#apply").click(function(){
			var params = $("form").serialize();
			//把表单的数据进行序列化
			mask('正在处理,请稍后....','0.5','50px');
			$.ajax({
				url:"<%=request.getContextPath() %>/bpm/workorder_ajaxSave.action",
				type:"POST",
				data:params,
				dataType:"json",
				success:function(data){
					if(data.result == "success"){
						removeMask();
						alert("处理成功！");	
						var path = "maintenanceWorkorder_maintenanceDone.do";
					    $("#faq_reply_form").attr("action", path).submit();
					}
				}
			});			
	    });
})
</script>
</head>

<body>
	<div id="main" class="mt20 clearfix">
		<div sytle="padding:20px;">
			<div class="uitopg">
				<!--新工单透明化 start-->
				<div class="record-review">
					<div class="record-hd cc">
						<span class="fr"> <a href="javascript:history.back()">返回列表</a>
						</span>
					</div>
					<div class="record-bd">
						<%--<div class="question-title">故障标题:<div id="orderTitle"></div>
						--%><div class="question-status">
							<span class="record-id"></span> 
							<span class="record-status"></span> 
							<span class="submitTime"></span>
						</div>
					</div>
				</div>
			</div>
			<!--新工单透明化 end-->

			<!--沟通记录start-->
			<div class="module-wrap">
				<h2 class="section-title">沟通记录</h2>
				<div class="bordersection mb20">
					<ul class="bubble-wrap">
						<li id="before_me"></li>
					</ul>
				</div>
			<!--沟通记录end-->
			<!--留言反馈表单start-->
				<div class="toolbar mt10">
					<div class="right">
						<form id="faq_reply_form" method="post">
							<input type="hidden" name="obj.id"  value="${obj.id }">
							<input type="hidden" name="obj.entryId" id="entryId" value="${obj.entryId }">
							<input type="hidden" name="obj.formId" id="formId" value="${obj.formId }">
							<input type="hidden" name="obj.resourceName" id="resourceName" value="maintenance_workorder"><%--
		                    <input class="btn-fmsubmiti y-btn y-btn-mini left" style="margin:0;min-width:100px;" type="button" id="apply" value="处理完成">
						--%></form>
					</div>
				</div>
			</form>
			<!--留言反馈表单end-->
			</div>
		</div>
	</div>
	</div>
</body>
</html>
