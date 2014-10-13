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
							$("#memberType").html(step.objData.memberType);
							$("#userName").html(step.objData.userName);
							$("#userPhone").html(step.objData.userPhone);
							$("#appType").html(step.objData.appType);
							$("#business").html(step.objData.business);
							$("#website").html(step.objData.website);
							$("#sp").html(step.objData.sp);
							$("#reason").html(step.objData.reason);
						}
						if(step.stepId==20){
							$("#Custom_ispass").html(step.objData.Custom_ispass);
							$("#checkReason").html(step.objData.checkReason);
						}
						if(step.stepId==30){
							$("#vhCpu").html(step.listData[0].vhCpu+"核");
							$("#vhMem").html(step.listData[0].vhMem+"G");
							$("#bandwith").html(step.listData[0].bandwith+"M");
							$("#os").html(step.listData[0].os);
							$("#timeout").html(step.listData[0].timeout+"月");
							$("#vhAmount").html(step.listData[0].vhAmount);
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
<input type="hidden" name="obj.id"  value="${obj.id }">
<input type="hidden" name="obj.entryId" id="entryId"  value="${obj.entryId }">
<input type="hidden" name="obj.orderNo"  value="${obj.orderNo }">
<input type="hidden" name="obj.creator"  value="${obj.creator }">
<input type="hidden" name="obj.createTime"  value="<s:date name="obj.createTime" format="yyyy-MM-dd HH:mm:ss"/>">
<input type="hidden" name="obj.partUsers"  value="${obj.partUsers }">
<input type="hidden" name="obj.orderTitle"  value="公有云服务">
<input type="hidden" name="obj.orderType" value="01">
<input type="hidden" name="obj.resType" value="01">
<input type="hidden" name="obj.orderStatus" id="orderStatus"  value="1">
<input type="hidden" name="step.stepId" id="curStepId"  value="${obj.step.stepId}">
<!-- 查询类型 1我的工单 2 待审批工单 -->
<input type="hidden" name="position"  value="${position}">
<!--head end-->
<!--w-1000 star-->
<div class="w-900">
	<!--服务步骤 star-->
    <ul class="step">
    	<li><span>1</span>提交试用申请</li>
        <li <s:if test="obj.step.stepId==20">class="li-on"</s:if> ><span>2</span><s:if test="obj.step.stepId==20">审核中</s:if><s:else>审核通过</s:else> </li>
        <li <s:if test="obj.step.stepId==30">class="li-on"</s:if> ><span>3</span>选择配置</li>
        <li><span>4</span>服务开通</li>
    </ul>
    <!--服务步骤 end-->
    <!-- 第一步 -->
    <div id="content_step10" style="display:none">
	    <fieldset> 
		<legend>基本信息 </legend> 
		<div>
		    <table border="0" width="100%" class="table-f1 mgb-20">
		    	<tr>
		        	<th>会员身份：</th>
		            <td id="memberType"></td>
		        </tr>
		        <tr>
		        	<th>联系人姓名：</th>
		            <td id="userName"></td>
		        </tr>
		        <tr>
		        	<th>联系人手机：</th>
		            <td id="userPhone"></td>
		        </tr>
		        <tr>
		        	<th class="ver-top">应用类型：</th>
		            <td id="appType"></td>
		        </tr>
		        <tr>
		        	<th>主营业务：</th>
		            <td id="business"></td>
		        </tr>
		        <tr>
		        	<th>网址：</th>
		            <td  id="website">></td>
		        </tr>
		        <tr>
		        	<th>历史服务商：</th>
		            <td id="sp"></td>
		        </tr>
		        <tr>
		        	<th class="ver-top">申请理由：</th>
		            <td id="reason"></td>
		        </tr>
		    </table>
	    </div>
	    </fieldset>
    </div>
   <!-- 第二步 -->
    <div id="content_step20" style="display:none">
	    <fieldset> 
			<legend>审核信息 </legend> 
			<div>
		     <table border="0" width="100%" class="table-f1 mgb-20">
		    	<tr>
		        	<th>审核结果：</th>
		            <td id="Custom_ispass">
		            </td>
		        </tr>
				<tr>
		        	<th><span class="font-red pdr-5"></span>审核说明：</th>
		            <td id="checkReason">   
		            </td>
		        </tr>
		     </table>  
		     </div>
	     </fieldset>
     </div>
    <!-- 第三步 -->
    <div id="content_step30" style="display:none">
	    <fieldset> 
			<legend>配置信息 </legend> 
			<div>
		     <table border="0" width="100%" class="table-f1 mgb-20">
		    	<tr>
		        	<th>CPU：</th>
		            <td id="vhCpu">
		            </td>
		        </tr>
				<tr>
		        	<th><span class="font-red pdr-5"></span>内存：</th>
		            <td id="vhMem">   
		            </td>
		        </tr>
		        <tr>
		        	<th><span class="font-red pdr-5"></span>带宽：</th>
		            <td id="bandwith">   
		            </td>
		        </tr>
		        <tr>
		        	<th><span class="font-red pdr-5"></span>操作系统：</th>
		            <td id="os">   
		            </td>
		        </tr>
		        <tr>
		        	<th><span class="font-red pdr-5"></span>系统盘：</th>
		            <td id="">   
		            	免费赠送（Linux送20GB，Windows送40GB）
		            </td>
		        </tr>
		        <tr>
		        	<th><span class="font-red pdr-5"></span>购买时长：</th>
		            <td id="timeout">   
		            </td>
		        </tr>
		        <tr>
		        	<th><span class="font-red pdr-5"></span>购买数量：</th>
		            <td id="vhAmount">   
		            	
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
