<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>查看</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath%>jsp/css/alicss.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jattachment.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/dateformat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/workorder_back.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		var entryId = "${requestScope.obj.entryId}";
		//渲染页面
		getWorkFlowByEntryId(entryId);
		getStepListByEntryId(entryId);
		
		//保存流程
		$("#apply").click(function(){
			//把表单的数据进行序列化
			var params = $("form").serialize();
			
			$.ajax({
				url:"<%=request.getContextPath() %>/bpm/workorder_ajaxSave.action",
				type:"POST",
				data:params,
				dataType:"json",
				success:function(data){
					if(data.result == "success"){
					    alert("反馈成功！");
					    history.back();
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
						<div class="question-title"><div id="orderTitle"></div>
						<div class="question-status">
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
			</div>
		</div>
	</div>
	</div>

</body>
</html>
