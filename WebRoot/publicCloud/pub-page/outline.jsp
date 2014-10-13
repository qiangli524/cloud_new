<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>控制中心</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
<script type="text/javascript">
$(function(){
	
	<%
	  String areaid= (String)session.getAttribute("areaid");
	 %>
	 var areaid= '<%=areaid%>' ;
	//各种点击事件
	$("#vlan").click(function(){
		window.location.assign("physicalVlan_listPhysicalVlanForCustomer.do?type=22");
	})
	$("#image").click(function(){
		window.location.assign("images_listImage.do?type=17");
	})
	$("#vm").click(function(){
		window.location.assign("vm_listVm.do?type=11");
	})
	$("#disk").click(function(){
		window.location.assign("disk_listDisk.do?type=12");
	})
	$("#publicip").click(function(){
		window.location.assign("publicip_list.do?type=21&ipObj.region_id="+areaid);
	})
	$("#snapshot").click(function(){
		window.location.assign("snapshot_listSnapShot.do?type=13");
	})
	$("#firewall").click(function(){
		window.location.assign("firewall_listFirewall.do?type=24");
	})
	$("#loadbalance").click(function(){
		window.location.assign("balance_listBalance.do?type=25");
	})
	
})
</script>
</head>
<body>
<!--container star-->
<div class="container">
	<!--col-c7 star-->
	<div class="col-c7">
    	<!--left star-->
       		<jsp:include page="order.jsp" />
        <!--left end-->
        <!--main-c1 star-->
        <div class="main-c1 fr">
        	<!--标题 star-->
            <h2 class="title-common6"><a href="#" class="home"></a><img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title_console.gif" width="65" height="65" />概况</h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <s:set name="areaid" value="#session.areaid"></s:set>
             <p class="p-con4">
                 <s:if test='#areaid=="1"'>
                     北京东部<span class="en">(EAST)</span>
                 </s:if>
                 <s:else>
                     北京西部<span class="en">(WEST)</span>
                 </s:else>

                 <a> 资源配额</a>  帐户余额:0.0044元<a>[消费记录]</a></p>
            <!--文字介绍 end-->
            <!--列表 star-->
            <div class="list-div clearfix">
            	<div class="list-item" id="vm">
                	<div class="fl item-left">
                    	<span class="icon1"></span>
                    	<p>云主机</p>
                    </div>
                    <strong class="strong-s">${requestScope.vm_count}</strong>
                </div>
                <div class="list-item" id="disk">
                	<div class="fl item-left">
                    	<span class="icon-ci"></span>
                    	<p>磁盘</p>
                    </div>
                    <strong class="strong-s">${requestScope.disk_count}</strong>
                </div>
                <div class="list-item" id="snapshot">
                	<div class="fl item-left">
                    	<span class="icon2"></span>
                    	<p>快照</p>
                    </div>
                    <strong class="strong-s">${requestScope.snapshot_count}</strong>
                </div>
                
                <div class="list-item" id="vlan">
                	<div class="fl item-left">
                    	<span class="i-12"></span>
                    	<p>子网</p>
                    </div>
                    <strong class="strong-s">${requestScope.vlan_count}</strong>
                </div>
                <div class="list-item" id="publicip">
                	<div class="fl item-left">
                    	<span class="icon3"></span>
                    	<p>弹性IP</p>
                    </div>
                    <strong class="strong-s">${requestScope.public_ip_count}</strong>
                </div>
                <div class="list-item" id="loadbalance">
                	<div class="fl item-left">
                    	<span class="icon4"></span>
                    	<p>负载均衡器</p>
                    </div>
                    <strong class="strong-s">${requestScope.lb_count}</strong>
                </div>
                <div class="list-item" id="firewall">
                	<div class="fl item-left">
                    	<span class="icon5"></span>
                    	<p>防火墙</p>
                    </div>
                    <strong class="strong-s">${requestScope.firewall_count}</strong>
                </div>
                 <div class="list-item" id="image">
                	<div class="fl item-left">
                    	<span class="icon5"></span>
                    	<p>镜像</p>
                    </div>
                    <strong class="strong-s">${requestScope.image_count}</strong>
                </div>
                <%-- <div class="list-item">
                	<div class="fl item-left">
                    	<span class="icon6"></span>
                    	<p>操作日志</p>
                    </div>
                    <strong class="strong-s">0</strong>
                </div>
                <div class="list-item">
                	<div class="fl item-left">
                    	<span class="icon7"></span>
                    	<p>消费记录</p>
                    </div>
                    <strong class="strong-s">0</strong>
                </div> 
                <div class="list-item" >
                	<div class="fl item-left">
                    	<span class="icon8"></span>
                    	<p>工单</p>
                    </div>
                    <strong class="strong-s">0</strong>
                </div>--%>
               
                 
                 
               
            </div>
            <!--列表 end-->
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
		<p class="pdt-30">©2012中国电信云计算分公司版权所有 京ICP备 12022551号  增值电信业务经营许可证A2.B1.B2-20090001</p>
	</div>
</div>
<!--版权 end-->
</body>
</html>
