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
    	<li class="li-on"><span>1</span>提交试用申请</li>
        <li><span>2</span>审核通过</li>
        <li><span>3</span>选择配置</li>
        <li><span>4</span>服务开通</li>
    </ul>
    <!--服务步骤 end-->
    <p class="mgtb-25 fs14">请填写以下资料</p>
    <table border="0" width="100%" class="table-f1 mgb-20">
    	<tr>
        	<th><span class="font-red pdr-5">*</span>会员身份：</th>
            <td>
            	<span class="pdr-15"><input type="radio" name="step.objData.memberType" value="个人" checked/> 个人 </span>
            	<span class="pdr-15"><input type="radio" name="step.objData.memberType" value="企业"/> 企业</span>
            </td>
        </tr>
        <tr>
        	<th><span class="font-red pdr-5">*</span>联系人姓名：</th>
            <td><input type="text" class="input-1c" name="step.objData.userName"/></td>
        </tr>
        <tr>
        	<th><span class="font-red pdr-5">*</span>联系人手机：</th>
            <td><input type="text" class="input-1c" name="step.objData.userPhone"/></td>
        </tr>
        <tr>
        	<th class="ver-top"><span class="font-red pdr-5">*</span>应用类型：</th>
            <td>
            	<div class="mgb-10">
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="网页游戏"/> 网页游戏</span>
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="手机游戏" /> 手机游戏</span>
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="客户端游戏" /> 客户端游戏</span>
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="门户网站" /> 门户网站</span>
                </div>
                <div class="mgb-10">
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="社区网站" /> 社区网站</span>
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="博客" /> 博客</span>
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="SNS" /> SNS</span>
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="音视频应用" /> 音视频应用</span>
                </div>
                <div class="mgb-10">
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="电商类应用" /> 电商类应用</span>
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="移动应用" /> 移动应用</span>
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="SAAS化应用" /> SAAS化应用</span>
                    <span class="check-s"><input type="checkbox"  name="step.objData.appType" value="其他" /> 其他</span>
                </div>
            </td>
        </tr>
        <tr>
        	<th><span class="font-red pdr-5">*</span>主营业务：</th>
            <td><input type="text" class="input-1c input-1c-1" name="step.objData.business"/></td>
        </tr>
        <tr>
        	<th><span class="font-red pdr-5">*</span>网址：</th>
            <td><input type="text" class="input-1c input-1c-1" name="step.objData.website" placeholder="以http://开头" value="" /></td>
        </tr>
        <tr>
        	<th><span class="font-red pdr-5">*</span>历史服务商：</th>
            <td>
            	<span class="pdr-20"><input type="checkbox" name="step.objData.sp" value="虚拟主机" /> 虚拟主机</span>
                <span class="pdr-20"><input type="checkbox" name="step.objData.sp" value="VPS"  /> VPS</span>
                <span class="pdr-20"><input type="checkbox" name="step.objData.sp" value="物理机托管"  /> 物理机托管</span>
                <span class="pdr-20"><input type="checkbox" name="step.objData.sp" value="其他云服务器"  /> 其他云服务器</span>
                <span class="pdr-20"><input type="checkbox" name="step.objData.sp" value="其他"  /> 其他</span>
            </td>
        </tr>
        <tr>
        	<th class="ver-top"><span class="font-red pdr-5">*</span>申请理由：</th>
            <td>
            	<textarea class="textarea-1"  name="step.objData.reason" ></textarea>
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
