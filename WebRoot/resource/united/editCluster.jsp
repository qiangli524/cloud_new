<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
$(function(){
	var api = frameElement.api;
	var w = api.opener;
	
	api.button({
	    id:'Ok',
	    name: '添加',
	    callback:add,
	    focus: true
	},{
	    id:'cancle',
	    name: '取消'
	});
	
	function add(){
		var uuid='<s:property value="uuid" />';
		var connect_id = '<s:property value="connect_id" />';
		var vtype = '<s:property value="vtype" />';
		var id = '<s:property value="id" />';
		var ha = $("#hastate").attr("checked");
		if(ha){
			$("#hastate").attr("value",true);
		}else{
		 	$("#hastate").attr("checked", true);
			$("#hastate").attr("value",false);
		}
		
		var drs = $("#drsstate").attr("checked");
		if(drs){
			$("#drsstate").attr("value",true);
		}else{
			$("#drsstate").attr("checked", true);
			$("#drsstate").attr("value",false);
		}
		
		var hm = $("#hostMonitoring").attr("checked");
		if(hm){
			$("#hostMonitoring").attr("value",true);
		}else{
			$("#hostMonitoring").attr("checked", true);
			$("#hostMonitoring").attr("value",false);
		}
		var str = $("#clusterUnitedVO").serialize();
		//alert("str:"+str);
		w.editCluster(uuid,connect_id,vtype,id,str);
	}
	
	function isha(){
		if($("#hastate").attr("checked")){
			for(i=1;i<4;i++){
				$("#tr_ha"+i).show();
			}
		}else{
			for(i=1;i<4;i++){
				$("#tr_ha"+i).hide();
			}
		}
	}
	function isdrs(){
		if($("#drsstate").attr("checked")){
			for(i=1;i<4;i++){
				$("#tr_drs"+i).show();
			}
		}else{
			for(i=1;i<4;i++){
				$("#tr_drs"+i).hide();
			}
		}
	}
	function isPolicy(){
		if($("#radio_1").attr("checked")){
			$("#numFailover").attr("disabled",false);
			$("#cpuFailoverPercent").attr("disabled",true);
			$("#memoryFailoverPercent").attr("disabled",true);
			$("#failoverHosts").attr("disabled",true);
		}else if($("#radio_2").attr("checked")){
			$("#numFailover").attr("disabled",true);
			$("#cpuFailoverPercent").attr("disabled",false);
			$("#memoryFailoverPercent").attr("disabled",false);
			$("#failoverHosts").attr("disabled",true);
		}else if($("#radio_3").attr("checked")){
			$("#numFailover").attr("disabled",true);
			$("#cpuFailoverPercent").attr("disabled",true);
			$("#memoryFailoverPercent").attr("disabled",true);
			$("#failoverHosts").attr("disabled",false);
		}
	}
	$("#enableAdmissionControl_i").click(function() {
		$("#numFailover").attr("disabled",false);
		$("#cpuFailoverPercent").attr("disabled",false);
		$("#memoryFailoverPercent").attr("disabled",false);
		$("#failoverHosts").attr("disabled",false);
		for(i=1;i<4;i++){
			$("#radio_"+i).attr("disabled",false);
		}
	});
	$("#enableAdmissionControl_j").click(function() {
		$("#numFailover").attr("disabled",true);
		$("#cpuFailoverPercent").attr("disabled",true);
		$("#memoryFailoverPercent").attr("disabled",true);
		$("#failoverHosts").attr("disabled",true);
		for(i=1;i<4;i++){
			$("#radio_"+i).attr("disabled",true);
		}
		
	});
	$("#radio_1").click(function() {
		isPolicy();
	});
	$("#radio_2").click(function() {
		isPolicy();
	});
	$("#radio_3").click(function() {
		isPolicy();
	});
	$("#hastate").click(function() {
		isha();
	});
	$("#drsstate").click(function() {
		isdrs();
	});
	
	$(window).load(function () {
	  isha();
	  isdrs();
	  var iscontrol = '<s:property value="clusterUnitedVO.haVO.enableAdmissionControl" />';
	  if(!iscontrol){
	  	$("#numFailover").attr("disabled",true);
		$("#cpuFailoverPercent").attr("disabled",true);
		$("#memoryFailoverPercent").attr("disabled",true);
		$("#failoverHosts").attr("disabled",true);
		for(i=1;i<4;i++){
			$("#radio_"+i).attr("disabled",true);
		}
	  }
	  isPolicy();
	});
});
</script>
</head>
<body class="pop-body scrollbody">

<s:form action="" id="clusterUnitedVO" method="post"
	cssClass="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
				<tr>
					<td>
						<input type="checkbox" id="hastate" name="clusterUnitedVO.hastate" <s:if test="clusterUnitedVO.hastate==true"> checked="checked"</s:if>	/>	打开 Vsphere HA	
					</td>
					<td>vSphere HA功能用于检测故障,对集群中运行的虚拟机提供快速恢复功能。核心功能包括主机监控和虚拟机监控功能,用于在检测不到信号时最大程度的缩短停机时间。<br />
						必须打开vSphere HA，才能使用Fault Tolerance。
					</td>
				</tr>
				<tr id="tr_ha1">
					<td>主机监控状态</td>
					<td>
						<input type="checkbox" id="hostMonitoring" name="clusterUnitedVO.haVO.hostMonitoring"	<s:if test="clusterUnitedVO.haVO.hostMonitoring==true">checked="checked"</s:if> />  启用主机监控
					</td>
				</tr>
				<tr id="tr_ha2">
					<td>接入控制</td>
					<td>
						<input type="radio" id="enableAdmissionControl_i" name="clusterUnitedVO.haVO.enableAdmissionControl" value="true" <s:if test="clusterUnitedVO.haVO.enableAdmissionControl==true">checked="checked"</s:if> />    启用：不允许违反可用性限制的虚拟机电源打开操作	<br />
						<input type="radio" id="enableAdmissionControl_j" name="clusterUnitedVO.haVO.enableAdmissionControl" value="false" <s:if test="clusterUnitedVO.haVO.enableAdmissionControl==false">checked="checked"</s:if> />  不启用：允许违反可用性限制的虚拟机电源打开操作"
					</td>
				</tr>
				<tr id="tr_ha3">
					<td>接入控制策略</td>
					<td>
						<input type="radio" id="radio_1" name="radio_a" />集群允许的主机故障数目
						<s:textfield name="clusterUnitedVO.haVO.numFailover" id="numFailover" cssClass="txt notnull posInt"></s:textfield>
						<br/>
						<input type="radio" id="radio_2" name="radio_a" />作为故障切换空间容量保留的集群资源的百分比：<br />
						<s:textfield name="clusterUnitedVO.haVO.cpuFailoverPercent" id="cpuFailoverPercent" cssClass="txt notnull posInt"></s:textfield>% CPU   
						<s:textfield name="clusterUnitedVO.haVO.memoryFailoverPercent" id="memoryFailoverPercent" cssClass="txt notnull posInt"></s:textfield>% 内存<br />
						<input type="radio" id="radio_3" name="radio_a" />指定故障切换主机：
						<!--  
						<s:textfield name="clusterUnitedVO.haVO.failoverHosts" id="failoverHosts" cssClass="txt notnull posInt"></s:textfield>
						-->
						<s:select id="failoverHosts" name="clusterUnitedVO.haVO.failoverHosts" list="resultList" listKey="h_uuid" listValue="eq_name"></s:select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" id="drsstate" name="clusterUnitedVO.drsstate" <s:if test="clusterUnitedVO.drsstate==true">checked="checked"</s:if>/>	打开 Vsphere DRS	
					</td>
					<td>
						vSphere DRS 使vCenter Server能够将主机作为资源的聚合池进行管理。群集资源可以根据用户、组和虚拟机划分为更小的资源池。<br />
						vSphere DRS还使vCenter Server能够自动管理虚拟机到主机的分配，在虚拟机打开电源时给出放置位置建议，以及为平衡负载和强制执行资源分配策略而对运行的虚拟机进行迁移。<br />
						应当在集群中启用vSphere DRS和VMvare EVC，以允许在负载平衡期间放置和迁移已打开Fault Tolerance的虚拟机。
					</td>
				</tr>
				<tr id="tr_drs1">
					<td>自动化级别</td>
					<td>
						<input type="radio" id="vmBehavior" name="clusterUnitedVO.drsVO.vmBehavior" value="manual"/>手动：<br />
						<input type="radio" id="vmBehavior" name="clusterUnitedVO.drsVO.vmBehavior" value="partiallyAutomated" checked="checked"/>半自动：<br />
						<input type="radio" id="vmBehavior" name="clusterUnitedVO.drsVO.vmBehavior" value="fullyAutomated"/>全自动：<br />
						迁移阀值: <s:select id="vmotionRate" name="clusterUnitedVO.drsVO.vmotionRate" list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" ></s:select>
					</td>
				</tr>
				<!-- 
				<tr id="tr_drs2">
					<td>虚拟机DRS组</td>
					<td>
						迁移阀值<s:textfield name="virtualMachineUnitedVO.numCPUs" id="numCPUs" cssClass="txt notnull posInt"></s:textfield>
					</td>
				</tr>
				<tr id="tr_drs3">
					<td>主机DRS组</td>
					<td>
						迁移阀值<s:textfield name="virtualMachineUnitedVO.numCPUs" id="numCPUs" cssClass="txt notnull posInt"></s:textfield>
					</td>
				</tr>
				 -->
		</table>
	</s:form>
</body>
