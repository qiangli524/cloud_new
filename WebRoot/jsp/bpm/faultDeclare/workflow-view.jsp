<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息填写</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/default.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/ui.js"></script>
<script type="">
	$(document).ready(function(){
		var entryId = $("#entryId").val();		
		getStepListById(entryId);
	})
	function getStepListById(entryId){
		 $.ajax({
		        async:false,
		        type:'post',
		        url:'<%=request.getContextPath() %>/bpm/workorder_getStepListById.do',
		        data:{"obj.entryId": entryId},
		        dataType:'json',
		        success:function (data) {
		        	$.each(data,function(i,step){		
						$("#content_step"+(step.stepId)).toggle();
		        		if(step.stepId==10){
							//渲染页面
							$("#questionType").html(step.objData.questionType);
							$("#question").html(step.objData.question);
							$("#questionDesc").html(step.objData.questionDesc);
							$("#phone").html(step.objData.phone);
							$("#email").html(step.objData.email);
						}
						if(step.stepId==20){
							$("#Custom_ispass").html(step.objData.Custom_ispass);
							$("#questionReason").html(step.objData.questionReason);
						}
					});
		        },
		        error:function (data, textStatus) {}
		 });
	}
	function save(){
		document.frm.action ="<%=request.getContextPath()%>/service/workorder_save.do"; 
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
<input type="hidden" name="obj.entryId" id="entryId" value="${obj.entryId }">
<!-- 查询类型 1我的工单 2 待审批工单 -->
<input type="hidden" name="position"  value="${position}">
<!--head end-->
<!--w-1000 star-->
<div class="w-900">
	<!--服务步骤 star-->
    <ul class="step">
    	<li><span>1</span>故障申报</li>
        <li <s:if test="obj.step.stepId==20">class="li-on"</s:if> ><span>2</span><s:if test="obj.step.stepId==20">等待处理</s:if><s:else>处理完成</s:else> </li>
    </ul>
    <!--服务步骤 end-->
    <!-- 第一步 -->
    <div id="content_step10" style="display:none">
	    <fieldset> 
		<legend>故障申报 </legend> 
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
		    </table>
	    </div>
	    </fieldset>
    </div>
   <!-- 第二步 -->
    <div id="content_step20" style="display:none">
	    <fieldset> 
			<legend>故障处理 </legend> 
			<div>
		     <table border="0" width="100%" class="table-f1 mgb-20">
		    	<tr>
		        	<th  width="15%">审核结果：</th>
		            <td id="Custom_ispass">
		            </td>
		        </tr>
				<tr>
		        	<th><span class="font-red pdr-5"></span>审核说明：</th>
		            <td id="questionReason">   
		            </td>
		        </tr>
		     </table>  
		     </div>
	     </fieldset>
     </div>
</div>
<!--w-1000 end-->
</form>
</body>
</html>
