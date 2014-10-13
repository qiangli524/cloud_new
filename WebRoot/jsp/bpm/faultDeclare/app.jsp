<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>故障申报</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/default.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jattachment.js"></script>
<script type="">
	function save(){
		document.frm.action ="<%=request.getContextPath()%>/bpm/workorder_save.action"; 
		document.frm.submit(); 
	}
	$(document).ready(function(){
		//附件上传例子
		var ops1 = {"operator":"upload"}
		$("#bt_upload").attachment(ops1);
		//附件下载例子
		//var ops2 = {"operator":"download","ids":"95"}
		//$("#downloaddiv").attachment(ops2);
	})
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
<input type="hidden" name="obj.entryId"  id="entryId"  value="${obj.entryId }">
<input type="hidden" name="obj.resourceName"  value="${obj.resourceName }">
<input type="hidden" name="obj.orderTitle"  value="故障申报">
<!-- 保存后重定向标记 -->
<input type="hidden" name="position"  value="${position}">
<!--head end-->
<!--w-1000 star-->
<div class="w-900">
	<!--服务步骤 star-->
    <ul class="step">
    	<li class="li-on"><span>1</span>故障申报</li><li><span>2</span>故障处理</li>        
    </ul>
    <!--服务步骤 end-->
    <p class="mgtb-25 fs14">请填写以下资料</p>
    <table border="0" width="100%" class="table-f1 mgb-20">
    	<tr>
        	<th width="15%"><span class="font-red pdr-5">*</span>选择问题分类：</th>
            <td>
            	<select name="step.objData.questionType">
            		<option value="云服务器ECS相关问题">云服务器ECS相关问题</option>
            	</seletc>
            </td>
        </tr>
        <tr>
        	<th><span class="font-red pdr-5">*</span>选择常见问题：</th>
            <td>
            	<div class="mgb-10">
                    <span class="check-s" style="width:200px"><input type="radio"  name="step.objData.question" value="服务器无法远程"/> 服务器无法远程</span>
                    <span class="check-s" style="width:200px"><input type="radio"  name="step.objData.question" value="云服务器被攻击" /> 云服务器被攻击</span>
                </div> 
                <div class="mgb-10">
                    <span class="check-s" style="width:200px"><input type="radio"  name="step.objData.question" value="申请按量高配"/> 申请按量高配</span>
                    <span class="check-s" style="width:250px"><input type="radio"  name="step.objData.question" value="服务器带宽、cpu跑满/跑高" /> 服务器带宽、cpu跑满/跑高</span>
                </div>         
            </td>
        </tr>
        <tr>
        	<th><span class="font-red pdr-5">*</span>问题描述：</th>
            <td><input type="text" class="input-1c" name="step.objData.questionDesc"/></td>
        </tr>
        <tr>
        	<th><span class="font-red pdr-5">*</span>手机：</th>
            <td><input type="text" class="input-1c input-1c-1" name="step.objData.phone"/></td>
        </tr>
        <tr>
        	<th><span class="font-red pdr-5">*</span>邮箱：</th>
            <td><input type="text" class="input-1c input-1c-1" name="step.objData.email"/></td>
        </tr> 
        <tr>
        	<th><span class="font-red pdr-5">*</span>上传附件：</th>
            <td>
            	<div id="downloaddiv"></div>
            	<input id="bt_upload" type="button" value="附件上传">
            </td>
        </tr>           
        <tr>
        	<th></th>
            <td><input type="button" class="" onclick="save()" value="提交申请" /></td>
        </tr>
    </table>
</div>
<!--w-1000 end-->
</form>
</body>
</html>
