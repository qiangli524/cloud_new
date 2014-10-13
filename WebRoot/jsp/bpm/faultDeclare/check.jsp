<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>故障处理</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/default.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jattachment.js"></script>
<script type="">
	$(document).ready(function(){
		var entryId = $("#entryId").val();		
		getStepListById(entryId);
	})
	function getStepListById(entryId){
		 $.ajax({
		        async:false,
		        type:'post',
		        url:'workorder_getStepListById.action',
		        data:{"obj.entryId": entryId},
		        dataType:'json',
		        success:function (data) {
		        	$.each(data,function(i,step){		
		        		if(step.stepId==10){
							//渲染页面
							$("#questionType").html(step.objData.questionType);
							$("#question").html(step.objData.question);
							$("#questionDesc").html(step.objData.questionDesc);
							$("#phone").html(step.objData.phone);
							$("#email").html(step.objData.email);
							if(step.objData.attachmentId){
								var ops = {"operator":"download","ids":step.objData.attachmentId}
								$("#attachId").attachment(ops);
							}
						}
					});
		        },
		        error:function (data, textStatus) {}
		 });
	}
	function save(){
		document.frm.action ="<%=request.getContextPath()%>/bpm/workorder_save.action"; 
		document.frm.submit(); 
	}
</script>
</head>
<body>
<!--head star-->
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
<form name='frm' id="frm" action="" method="post"> 
<input type="hidden" name="obj.id"  value="${obj.id }">
<input type="hidden" name="obj.entryId" id="entryId" value="${obj.entryId }">
<input type="hidden" name="obj.resourceName"  value="${obj.resourceName }">
<input type="hidden" name="obj.orderTitle"  value="故障申报">
<!-- 保存后重定向标记 -->
<input type="hidden" name="position"  value="${position}">
<!--head end-->
<!--w-1000 star-->
<div class="w-900">
	<!--服务步骤 star-->
    <ul class="step">
    	<li><span>1</span>故障申报</li>
        <li class="li-on"><span>2</span>故障处理</li>
    </ul>
    <!--服务步骤 end-->
    <fieldset> 
	<legend>基本信息 </legend> 
		<div>
	    <table border="0" width="100%" class="table-f1 mgb-20">
	    	<tr>
	        	<th width="15%"><span class="font-red pdr-5">*</span>选择问题分类：</th>
	            <td id="questionType"></td>
	        </tr>
	        <tr>
	        	<th><span class="font-red pdr-5">*</span>选择常见问题：</th>
	            <td id="question"></td>
	        </tr>
	        <tr>
	        	<th><span class="font-red pdr-5">*</span>问题描述：</th>
	            <td id="questionDesc"></td>
	        </tr>
	        <tr>
	        	<th><span class="font-red pdr-5">*</span>手机：</th>
	            <td id="phone"></td>
	        </tr>
	        <tr>
	        	<th><span class="font-red pdr-5">*</span>邮箱：</th>
	            <td id="email"></td>
	        </tr>
	        <tr>
	        	<th><span class="font-red pdr-5">*</span>附件：</th>
	            <td id="attachId"></td>
	        </tr>
	    </table>
	    </div>
    </fieldset>
	<fieldset> 
		<legend>审核信息 </legend> 
		<div>
	     <table border="0" width="100%" class="table-f1 mgb-20">
	    	<tr>
	        	<th width="15%"><span class="font-red pdr-5">*</span>处理结果：</th>
	            <td>
	            	<span class="pdr-15"><input type="radio" name="step.objData.Custom_ispass" checked value="已处理"/> 已处理 </span>
	            	<span class="pdr-15"><input type="radio" name="step.objData.Custom_ispass" value="未处理"/> 未处理</span>
	            </td>
	        </tr>
			<tr>
	        	<th><span class="font-red pdr-5"></span>故障原因：</th>
	            <td>
	            	<textarea class="textarea-1" name="step.objData.questionReason"></textarea>
	            </td>
	        </tr>
	         <tr>
	        	<th></th>
	            <td>
	            	<input type="button" class="" onclick="save()" value="提交" />
	            </td>
	        </tr>
	     </table>  
	     </div>
     </fieldset>
</div>
<!--w-1000 end-->
</form>
</body>
</html>
