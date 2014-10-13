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

<title>客服验证</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath%>jsp/css/alicss.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
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
			mask('正在处理,请稍后....','0.5','50px');
			if($("#toPayout").attr("checked")==true){
				$("#isPass").attr("checked")=true;
				alert("后台转赔付流程，程序继续执行");
				//转赔付流程
			}
			var params = $("form").serialize();			
			$.ajax({
				url:"<%=request.getContextPath() %>/bpm/workorder_ajaxSave.action",
				type:"POST",
				data:params,
				dataType:"json",
				success:function(data){
					if(data.result == "success"){
						removeMask();
						
						var radioObj = $('input:radio[name="step.objData.Custom_isPass"]:checked');
						//客户验证通过后才发短信和邮件通知
						//if(radioObj.val() == '1'){
						//	sendMsg();
						//}else{
							alert("处理成功！");
							history.back();
						//}
					}
				}
			});
	    });
		
		function sendMsg(){
			var path = "workorder_workDoneSendMsg.do";
		    $("#faq_reply_form").attr("action", path).submit();
		}
		
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
						<div class="question-title">问题标题:<div id="orderTitle"></div>
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

				<!--留言反馈表单start-->

				<h2 class="section-title">处理意见：</h2>
				<form id="faq_reply_form" action="" method="post">
					<input type="hidden" name="obj.id"  value="${obj.id }">
					<input type="hidden" name="obj.entryId" id="entryId1" value="${obj.entryId }">
					<input type="hidden" name="workorder.workflowId" value="${obj.entryId }">
					<input type="hidden" name="obj.resourceName"  value="${obj.resourceName }">
					<div class="textarea-autofit">
						<textarea id="reply_content" name="step.objData.answer"></textarea>
					</div>

					<p class="clearfix pt20 pb20" id="satisfy_block">
                    <label><input type="radio" name="step.objData.Custom_isPass"  value="0">未通过</label>
                    <label><input type="radio" name="step.objData.Custom_isPass"  value="1" checked>通过</label>
                    <label><input type="checkbox" name="toPayout" id="toPayout" value="1">转赔付</label>

					<div class="toolbar mt10">
						<div class="right">
							<input class="btn-fmsubmiti y-btn y-btn-mini left" style="margin:0;min-width:100px;" type="button"
								id="apply" value="提交">
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
