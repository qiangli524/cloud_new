<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>控制中心</title>
</head>

<body>
 <s:set name="areaid" value="#session.areaid"></s:set>
 <div class="side-c1 fl">
        	<dl class="compose-w3 clearfix">
        		<dt><img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/telcom.gif" width="95px;" height="74px"/></dt>
                <dd>北京电信天翼云自服务平台</dd>
            </dl>
            <div class="zone-switcher">
	             <s:if test='#areaid=="1"'>
	              <a class="current-zone pek1"><span class="icon icon-zone"></span><span id="current-zone">北京东部<span class="en">(EAST)</span><em class="loading" style="display: none">切换中...</em></a>
	             </s:if>
	             <s:else>
	              <a class="current-zone gd1"><span class="icon icon-zone"></span><span id="current-zone">北京西部<span class="en">(WEST)</span><em class="loading" style="display: none">切换中...</em></a>
	             </s:else>
	             
	             <ul class="zone-switcher-select" style="display: none;">
	              <li><a data-value="pek1" areaid="1"><span class="label-current aread_icon"></span>北京东部<span class="en">(EAST)</span><span class="zone-label pek1"></span></a></li>
	              <li><a data-value="gd1" areaid="2"><span class="label-unchecked aread_icon"></span>北京西部<span class="en">(WEST)</span><span class="zone-label gd1"></span></a></li>
	             </ul>
	             
	             <form id="areaForm" action="console.do" method="post">
	              <input type="hidden" id="areaId" name="areaId" />
	             </form>
            </div>
            <div class="help-main">            
	        	<div class="y-span2 help-menu">
				    <dl class="js_current">
			        	<dt id="type_1"><i class="i-6"></i><a href="javascript:void(0);" data-ga="">云主机</a><div class="clear"></div></dt>
			        	<dd id="type_11"><i class="i-6"></i><a href="vm_listVm.do?type=11" data-ga="">主机</a><div class="clear"></div></dd>
			        	<dd id="type_12"><i class="i-14"></i><a href="disk_listDisk.do?type=12" data-ga="">磁盘</a><div class="clear"></div></dd>
						<dd id="type_13"><i class="i-7"></i><a href="snapshot_listSnapShot.do?type=13" data-ga="">快照</a><div class="clear"></div></dd>
						<dd id="type_17"><i class="i-15"></i><a href="images_listImage.do?type=17" data-ga="">镜像</a><div class="clear"></div></dd>
					</dl>
					<dl class="js_current">	
						<dt id="type_2"><i class="i-22"></i><a href="javascript:void(0);" data-ga="">云网络</a><div class="clear"></div></dt>
						<dd id="type_21"><i class="i-8"></i><a href="publicip_list.do?type=21" data-ga="">弹性IP</a><div class="clear"></div></dd>
						<dd id="type_22"><i class="i-12"></i><a href="physicalVlan_listPhysicalVlanForCustomer.do?type=22" data-ga="">虚拟网络</a><div class="clear"></div></dd>
						<dd id="type_23"><i class="i-19"></i><a href="router_listRouters.do?type=23" data-ga="">虚拟路由器</a><div class="clear"></div></dd>
						<dd id="type_24"><i class="i-10"></i><a href="firewall_listFirewall.do?type=24" data-ga="">防火墙</a><div class="clear"></div></dd>
						<dd id="type_25"><i class="i-9"></i><a href="balance_listBalance.do?type=25" data-ga="">负载均衡</a><div class="clear"></div></dd>
						
					</dl>
					<dl class="js_current">
			        	<dt id="type_3"><i class="i-18"></i><a href="javascript:void(0);" data-ga="">云监控</a></dt>
			        	<dd id="type_31"><i class="i-18"></i><a href="vmmonitor_list.do?type=31" data-ga="">云主机监控</a></dd>
			        	<dd id="type_32"><i class="i-18"></i><a href="vmalarm_list.do?type=32" data-ga="">自定义告警</a></dd>
			        	<dd id="type_33"><i class="i-18"></i><a href="subscriber_list.do?type=33" data-ga="">告警联系人</a></dd>
			        	<dd id="type_34"><i class="i-18"></i><a href="subscriberGroup_list.do?type=34" data-ga="">告警组管理</a></dd>
					</dl>
				    <dl class="js_current">
			        	<dt id="type_6"><i class="i-16"></i><a href="javascript:void(0);" data-ga="">云安全</a></dt>
					</dl>
					 <dl class="js_current">
			        	<dt id="type_7"><i class="i-17"></i><a href="javascript:void(0);" data-ga="">云存储</a></dt>
					</dl>
				    <dl class="js_current">
			        	<dt id="type_4"><i class="i-11"></i><a href="sysUserLog_logList.do?type=41" data-ga="">操作日志</a></dt>
					</dl>
				    <dl class="js_current">
			        	<dt id="type_5"><i class="i-13"></i><a href="javascript:void(0);" data-ga="">我的工单</a></dt>
						<dd id="type_51"><i class="i-21"></i><a href="workorder_apply.do?type=51" data-ga="">工单申请</a></dd>
						<dd id="type_52"><i class="i-20"></i><a href="workorder_customer_my.do?type=52" data-ga="">工单列表</a></dd>			        	
					</dl>
				</div>
            </div>
        </div>
</body>

</html>