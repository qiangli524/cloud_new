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

<title>用户确定</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=request.getContextPath() %>/jsp/css/bubble.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/default.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jattachment.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/dateformat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/workorder_front.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/ui.js"></script>
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
					    alert("确定成功！正在跳转到评价界面！");
					    $("form").submit();
					}
				}
			});
	    });
})

</script>

</head>

<body>
<!--container star-->
	<div class="container">
		<!--col-c7 star-->
		<div class="col-c7">
			<!--left star-->
			<jsp:include page="/publicCloud/pub-page/order.jsp" />
			<!--left end-->
			<!--main-c1 star-->
			<div class="main-c1 fr">
			<!--标题 star-->
				<h2 class="title-common6">
					<a href="#" class="home"></a><img
						src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title-img2.gif"
						width="65" height="65" />工单详情
				</h2>
			<!--标题 end-->
<!-- ali css -->
	<div id="main" style="margin-top: 20px !important; ">
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

					<!--留言反馈表单start-->

								<h2 class="section-title">留言/反馈</h2>
								<form id="faq_reply_form" target="_self" action="workorder_evaluate.do?type=52"
									method="post" enctype="multipart/form-data">
									<input type="hidden" name="obj.entryId" id="entryId1"
										value="${obj.entryId }">
									<div class="textarea-autofit">
										<textarea id="reply_content" name="step.objData.question"
											class="textarea" style="width: 864px;"></textarea>
									</div>
<!-- 上传附件start -->
<!-- <br>


									<div class="mt10">
														<p>
															<a class="btn-n1 btn-n1on" id="add_attach" href="javascript:void(0)"><em>上传附件</em>
															</a>
														</p>
												<p class="line-h24 pdt-10">
												可上传<span class="font-red">3个附件</span>，每个附件的大小不的超过2M。附件支持的格式有:'jpg',
												'bmp', 'png',
												'gif','txt','rar','zip','doc','docx','ini','conf','eml'
											</p>
									</div> -->
<!-- 上传附件end -->
					<br>
					<div>
						<label><input type="radio" name="step.objData.isOk" checked value="1">已解决</label>
						<label><input type="radio" name="step.objData.isOk" value="0">未解决</label>
					</div>
					<br>
					<div class="toolbar mt10">
						<div class="left">
							<a class="btn-w2" id="apply" href="javascript:void(0);">提 交</a>
						</div>
					</div>
				</form>
				<!--留言反馈表单end-->
			</div>
		</div>
		
	</div>
<!-- ali Css end -->
</div>
           <div class="clear"></div>
		</div>
		<!--col-c7 end-->
	</div>
	<!--container end-->

<!--版权 star-->
	<div class="copy">
		<div class="copy-con ac">
			<p class="pdt-30">©2012中国电信云计算分公司版权所有 京ICP备 12022551号
				增值电信业务经营许可证A2.B1.B2-20090001</p>
		</div>
	</div>
<!--版权 end-->
</body>
</html>
