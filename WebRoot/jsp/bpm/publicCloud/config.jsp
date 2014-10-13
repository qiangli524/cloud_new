<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择配置</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/default.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/plugin/jslide.js"></script>
<script>
	var cpuValue = "";
	var memValue = "";
	var os = "";
	var version = "";
	//获取cpu
	function cpu(cpu_value){
		cpuValue = cpu_value;
	}
	//获取内存
	function mem(mem_value){
		memValue = mem_value;
	}
	//获取操作系统
	function  queryOS(OS){
		os = OS;
	}
	//获取版本
	function queryVersion(versions){
		version = versions;
	}
	function save(){
		if(cpuValue==""){
			cpuValue="1";
		}
		if(memValue==""){
			memValue="0.5";
		}
		var osversion = os+version;
		var param = 'step.listData.vhCpu='+cpuValue+'&step.listData.vhMem='+memValue+'&step.listData.os='+osversion+'&step.listData.version='+version;
		document.frm.action ="<%=request.getContextPath()%>/bpm/workorder_save.action?"+param; 
		document.frm.submit(); 
	}
	
	$(document).ready(function(){
		$("#bwSliide").slide({values:['3M','6M','9M','12M'],inputName:'step.listData.bandwith',proportion:'3'});
		$("#toSlide").slide({values:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','1年','2年','3年'],inputName:'step.listData.timeout',proportion:'1'});
	});
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
	<ul class="step">
    	<li ><span>1</span>提交试用申请</li>
        <li><span>2</span>审核通过</li>
        <li class="li-on"><span>3</span>选择配置</li>
        <li><span>4</span>服务开通</li>
    </ul>
	<!--show-tab star-->
	<div class="show-tab js_show_tab">
    	<div class="tab-caption js_tab_caption">
            <ul class="list-w1 clearfix">
                <li class="on">包年包月</li>
                <li>按量付费</li>
            </ul>       
        </div>
        <!--tab-content star-->
    	<div class="tab-content border-1 pd-15">
        	<!--box star-->
        	<div class="box on">
                 <ul class="list-w2 list-w2-2 js_more_c">
                    <li>
                    	<span class="s-t1">CPU：</span><a href="#" class="btn-w1 btn-w1on" onclick="cpu('1')"><em>1核</em></a><a href="#" class="btn-w1" onclick="cpu('2')"><em>2核</em></a><a href="#" class="btn-w1" onclick="cpu('3')"><em>3核</em></a><a href="#" class="btn-w1" onclick="cpu('4')"><em>4核</em></a>
                    </li>
                    <li>
                    	<span class="s-t1">内存：</span><a href="#" class="btn-w1 btn-w1on" onclick="mem('0.5')"><em>512MB</em></a><a href="#" class="btn-w1" onclick="mem('1')"><em>1G</em></a><a href="#" class="btn-w1" onclick="mem('1.5')"><em>1.5G</em></a><a href="#" class="btn-w1" onclick="mem('2')"><em>2G</em></a><a href="#" class="btn-w1" onclick="mem('4')"><em>4G</em></a>
                        <p class="line-th30 pdl-70">为了保证良好的性能体验，512MB内存不提供windows操作系统</p>
                    </li>
                    <li>
                        <span class="s-t1">公网宽带：</span>
                        <div id="bwSliide"></div>
                    </li>
                    <!-- 
                    <li>
                    	<span class="s-t1">地域：</span><a href="#" class="btn-w1 btn-w1on"><em>青岛</em></a><a href="#" class="font-blue">查看我的产品地域	</a>
                        <p class="line-th30 pdl-70"> 建议选择最靠近您客户的节点，可减少延迟时间和提高下载速度；不同地域之间的产品内网不互通，查看<a href="#" class="font-blue">地域选择帮助</a> </p>
                    </li>
                     -->
                    <li class="clearfix">
                    	<span class="s-t1">操作系统：</span>
                        <div class="select-1c fl mgr-20">
                            <em class="select-1c-1 select-n1">选择操作系统类别</em>
                            <ul class="select-list select-list-n1">
                                <li onclick="queryOS('Red Hat Enterprise Linux ')">Red Hat Enterprise Linux </li>
                            </ul>
                        </div>
                        <div class="select-1c fl">
                            <em class="select-1c-1 select-c1">选择版本</em>
                            <ul class="select-list select-list-n1" id="version">
                            	<li onclick="queryVersion('6.2')">6.2</li>
                            </ul>
                        </div>
                    </li><%--
                    <li>
                    	<span class="s-t1">数据盘：</span><a href="#" class="font-blue a-add">增加一块</a> <span>您还可选配4块，总容量剩余2048GB可选，每块最小容量为5GB</span>
                    </li>
                    --%><li>
                    	<span class="s-t1">系统盘：</span><span class="fd">免费赠送</span><em class="font-gary">（Linux送30GB，Windows送40GB）</em>
                    </li>
                    <li>
                        <span class="s-t1">申请时长：</span>
                        <div id="toSlide"></div>
                    </li>
                    <li class="li-border mgt-25">
                    	<span class="s-t1">申请数量：</span><i class="js_number"><a href="#" class="li-al a-cut"></a><input type="text" class="input-w4 input-w4-1"  name="step.listData.vhAmount" value="1" /><a href="#" class="li-ar a-add"></a></i>
                    </li>
                </ul>
                <div class="pdl-70">
                    <div class="mgtb-25"><a class="btn-w2 mgr-10" href="#"  onclick="save()"><span>立即申请</span></a><a class="btn-w3" href="#"><span>加入产品清单</span></a></div>
                </div>
            </div>
            <!--box end-->
            <!--box star-->
        	<div class="box">
            ·	正在研发中...
            </div>
            <!--box end-->
        </div>
        <!--tab-content end-->
    </div>
    <!--show-tab end-->
</div>
</form>
<!--w-1000 end-->
</body>
</html>
