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
		        url:'workorder_getStepListById.action',
		        data:{"obj.entryId": entryId},
		        dataType:'json',
		        success:function (data) {
		        	$.each(data,function(i,step){		
		        		if(step.stepId==10){
							//渲染页面
							$("#memberType").html(step.objData.memberType);
							$("#userName").html(step.objData.userName);
							$("#userPhone").html(step.objData.userPhone);
							$("#appType").html(step.objData.appType);
							$("#business").html(step.objData.business);
							$("#website").html(step.objData.website);
							$("#sp").html(step.objData.sp);
							$("#reason").html(step.objData.reason);
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
<input type="hidden" name="obj.resourceName"  value="${obj.resourceName }">
<input type="hidden" name="obj.id"  value="${obj.id }">
<input type="hidden" name="obj.entryId" id="entryId"  value="${obj.entryId }">
<input type="hidden" name="obj.orderNo"  value="${obj.orderNo }">
<input type="hidden" name="obj.creator"  value="${obj.creator }">
<input type="hidden" name="obj.createTime"  value="<s:date name="obj.createTime" format="yyyy-MM-dd HH:mm:ss"/>">
<input type="hidden" name="obj.partUsers"  value="${obj.partUsers }">
<input type="hidden" name="obj.orderTitle"  value="公有云服务">
<input type="hidden" name="obj.orderType" value="01">
<input type="hidden" name="obj.resType" value="01">
<!-- 保存后重定向标记 -->
<input type="hidden" name="position"  value="${position}">
<!--head end-->
<!--w-1000 star-->
<div class="w-900">
	<!--服务步骤 star-->
    <ul class="step">
    	<li><span>1</span>提交试用申请</li>
        <li class="li-on"><span>2</span>审核</li>
        <li><span>3</span>选择配置</li>
        <li><span>4</span>服务开通</li>
    </ul>
    <!--服务步骤 end-->
    <fieldset> 
	<legend>基本信息 </legend> 
		<div>
	    <table border="0" width="100%" class="table-f1 mgb-20">
	    	<tr>
	        	<th><span class="font-red pdr-5">*</span>会员身份：</th>
	            <td id="memberType"></td>
	        </tr>
	        <tr>
	        	<th><span class="font-red pdr-5">*</span>联系人姓名：</th>
	            <td id="userName"></td>
	        </tr>
	        <tr>
	        	<th><span class="font-red pdr-5">*</span>联系人手机：</th>
	            <td id="userPhone"></td>
	        </tr>
	        <tr>
	        	<th class="ver-top"><span class="font-red pdr-5">*</span>应用类型：</th>
	            <td id="appType"></td>
	        </tr>
	        <tr>
	        	<th><span class="font-red pdr-5">*</span>主营业务：</th>
	            <td id="business"></td>
	        </tr>
	        <tr>
	        	<th><span class="font-red pdr-5">*</span>网址：</th>
	            <td  id="website"></td>
	        </tr>
	        <tr>
	        	<th><span class="font-red pdr-5">*</span>历史服务商：</th>
	            <td id="sp"></td>
	        </tr>
	        <tr>
	        	<th class="ver-top"><span class="font-red pdr-5">*</span>申请理由：</th>
	            <td id="reason"></td>
	        </tr>
	    </table>
	    </div>
    </fieldset>
	<fieldset> 
		<legend>审核信息 </legend> 
		<div>
	     <table border="0" width="100%" class="table-f1 mgb-20">
	    	<tr>
	        	<th><span class="font-red pdr-5">*</span>审核结果：</th>
	            <td>
	            	<span class="pdr-15"><input type="radio" name="step.objData.Custom_ispass" checked value="通过"/> 通过 </span>
	            	<span class="pdr-15"><input type="radio" name="step.objData.Custom_ispass" value="驳回"/> 驳回</span>
	            </td>
	        </tr>
			<tr>
	        	<th><span class="font-red pdr-5"></span>审核说明：</th>
	            <td>
	            	<textarea class="textarea-1" name="step.objData.checkReason"></textarea>
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
