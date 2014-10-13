<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提交工单</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/default.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/jsp/css/validation.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jattachment.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/workorder.constant.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
<script type="text/javascript">
//初始加载页面时
$(document).ready(function(){
	
	//初始化问题类型
	renderQuestion();
	
	//手机验证规则
	$.validator.addMethod("mobile",function(value, element, params){  
		var length = value.length;
		var mobile =  /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请填入正确的手机号码！");  
  
     var v = $("#frm").validate({
        //debug:true,
        rules: {
           'step.objData.type': {required:true},
		   'step.objData.phone': {required:true,mobile:true},
		   'step.objData.email': {required: true,email: true},
		   'step.objData.content': {required: true}
		},
        messages: {
           'step.objData.type':  "请选择问题分类",
		   'step.objData.phone': "请输入手机号码",
		   'step.objData.email': {required: "请输入Email地址",email: "请输入正确的email地址"},
		   'step.objData.content': {required: "请输入问题描述"}										
  		}
    });

	$("#apply").click(function(){
		var check = v.form();
		if(!check)return false;
		//把表单的数据进行序列化
		var params = $("form").serialize();
		
		$.ajax({
			url:"<%=request.getContextPath() %>/bpm/workorder_ajaxSave.action",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(data){
				if(data.result == "success"){
				    $("#workflowId").val(data.entryId);
				    save();
				}
			}
		});
	});
	
	
	//问题类型选择事件
	$("#questionType").change(function(){
		var qType = $(this).children('option:selected').val()
		var content = "";
		if(qType != ""){
			content += '<th><em class="font-red">*</em>选择常见问题：</th><td>';
			$.each(question,function(i,qt){
				if(qType == qt.questionType){
					$.each(qt.questions,function(i,q){
					
						if(i%2 == 0){
							content += '<div class="mgb-10">';
						}
						if(i==0){
							content += '<span class="check-s" style="width:200px"><input type="radio"	name="step.objData.question" value=' +q+' checked />'+ q +'</span>';
						}else{
							content += '<span class="check-s" style="width:200px"><input type="radio"	name="step.objData.question" value=' +q+' />'+ q +'</span>';
						}
							
						if(i%2 != 0){
							content += '</div>';
						}
					})	
				}
			})
			content += '</td>';
			$("#questionContent").html(content);
			$("#questionContent").show();
		}else{
			$("#questionContent").html("");
			//$("#questionContent").hidden();
		}
	});
	
	//附件上传例子
	var ops1 = {"operator":"upload"}
	$("#bt_upload").attachment(ops1);
});

	//初始化问题类型
	function renderQuestion(){
		var content="";
		$.each(question,function(index,qt){
		 content +=	'<option value='+qt.questionType+'>'+qt.questionType+'</option>';
		})
		$("#questionType").append(content);
	}

	function save(){
		document.frm.action ="workorder_save.action?type=52&statusType=0";
		document.frm.submit(); 
	}
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
						width="65" height="65" />提交工单
				</h2>
			<!--标题 end-->
				<form name='frm' id="frm" action="" method="post">
					<input type="hidden" name="obj.id" value="${obj.id }"/> 
					<input type="hidden" name="workflowId" id="workflowId"/> 
					<input type="hidden" name="obj.resourceName" value="workorder"/>
					<input type="hidden" name="obj.orderTitle" value="故障申报"/>
									<!-- 保存后重定向标记 --> 
					<input type="hidden" name="position"	value="${position}"/>
					<!--head end--> <!--w-1000 star-->
										<div class="w-900">
											<!--服务步骤 end-->
											
											<table border="0" width="100%" class="table-f5">
												<tr>
													<th><em class="font-red">*</em>选择问题分类：</th>
													<td><select class="select_box"	name="step.objData.type" id="questionType">
															<option value="">请选择</option>
														</select>
													</td>
												</tr>
												<tr id="questionContent">
													
												</tr>
												<tr>
													<th><em class="font-red">*</em>问题描述：</th>
													<td><textarea name="step.objData.content"
															class="textarea" id="content"></textarea>
													</td>
												</tr>
												<tr>
													<th><em class="font-red">*</em>手机号码：</th>
													<td><input type="text" name="step.objData.phone"
														class="input-1c input-1c-2" id="phone"/>
													</td>
												</tr>
												<tr>
													<th><em class="font-red">*</em>邮箱：</th>
													<td><input type="text" name="step.objData.email"
														class="input-1c input-1c-2" id="email"/>
													</td>
												</tr>
												<%-- <tr>
													<th>上传附件：</th>
													<td>
														
															<div id="downloaddiv"></div>
														
														<p>
															<a class="btn-n1 btn-n1on" id="bt_upload" href="#"><em>上传附件</em>
															</a>
														</p>
														<p class="line-h24 pdt-10">
															可上传<span class="font-red">3个附件</span>，每个附件的大小不的超过2M。附件支持的格式有:'jpg', 'bmp', 'png', 'gif','txt','rar','zip','doc','docx','ini','conf','eml'
														</p></td>
												</tr> --%>
												<tr>
													<th></th>
													<td><a class="btn-w2" id="apply" href="#">提 交</a>
													</td>
												</tr>
											</table>
										</div>
				</form>
			</div>
			<!--main-c1 end-->
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
