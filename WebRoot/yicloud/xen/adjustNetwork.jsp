<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Random" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script type="text/javascript">
		var poolUuid = '<%=request.getAttribute("poolUuid")%>';
	 	var hostUuid = '<%=request.getAttribute("hostUuid")%>';
	 	var networkUuid = '<%=request.getAttribute("networkUuid")%>';
	 
		 var api = frameElement.api;
		 var w = api.opener;
		
		 api.button({
		     id:'Ok1',
		     name: '调整',
		     callback:adjustNetwork,
		     focus: true
		 },
		 {
		     id:'cancle1',
		     name: '取消'
		 });
		 
		 function adjustNetwork(){
	 		var nicUuid = $("#nic option:selected").val();
	 		var nicName = $("#nic option:selected").text();
	 		var netName = netName = $("#netName").val();
	 		var netExplain = $("#netExplain").val();
	 		var autoMatic = $("#externalAutoMatic").attr("checked");
	 		var vlan = $("#vlan").val();
			var regular = /^[1-9][0-9]{0,3}$/;
			if(regular.test(vlan)){
				$("#judgedWrong").hide();
				$("#judgedRight").show();
				if(vlan>4094){
					$("#judgedWrong").show();
					$("#judgedRight").hide();
					return false;
				}
			}else{
				$("#judgedWrong").show();
				$("#judgedRight").hide();
				return false;
			}
			var url = 'xen_adjustNetwork.do?poolUuid='+poolUuid+"&hostUuid="+hostUuid+"&nicUuid="+nicUuid+"&nicName="
					+nicName+"&netName="+encodeURI(encodeURI(netName))+"&netExplain="+encodeURI(encodeURI(netExplain))
	 				  +"&autoMatic="+autoMatic+"&vlan="+vlan+"&networkUuid="+networkUuid;
	 		w.adjustNetwork(url,networkUuid);
		 }
	
		$(function(){
			$("#test").click(function(){
				var vlan = $("#vlan").val();
				var regular = /^[1-9][0-9]{0,3}$/;
				if(regular.test(vlan)){
					$("#judgedWrong").hide();
					$("#judgedRight").show();
					if(vlan>4094){
						$("#judgedWrong").show();
						$("#judgedRight").hide();
					}
				}else{
					$("#judgedWrong").show();
					$("#judgedRight").hide();
				}
			});
			
			$("#nic").bind("change",function(){
				var nicUuid = $("#nic option:selected").val();
				if(nicUuid=='0'){
					$("#test").hide();
					$("#vlan").attr("disabled",true);
				}else{
					$("#test").show();
					$("#vlan").attr("disabled",false);
				}
			});
			
			//初始化页面
			var autoMatic = theForm.autoMatic.value;
			if(autoMatic=='是'){
				$("#externalAutoMatic").attr("checked",true);
			}
			var nic = theForm.nic.value;
			if(nic=='0'){
				$("#test").hide();
				$("#vlan").attr("disabled",true);
			}
		});
	</script>
</head>
<body class="pop-body scrollbody" onload="pageOnLoad();">
	<s:form action="cluster_saveCluster.do" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="theForm.autoMatic" id="autoMatic"/>
	<s:hidden name="theForm.netType" id="netType"/>
	<div>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til" width="20%" align="left"> 
					名称：
				</td>
				<td>
					<s:textfield name="theForm.netName" id="netName" cssClass="txt" cssStyle="width:200px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" align="left"> 
					说明：
				</td>
				<td>
					<s:textfield name="theForm.netExplain" id="netExplain" cssClass="txt" cssStyle="width:200px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" align="left"> 
					NIC：
				</td>
				<td>
					<s:select id="nic" name="theForm.nic" list="theForm.resultList" listKey="nicUuid" listValue="nicName" cssStyle="width:200px;position: relative;left: 2px"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" align="left"> 
					VLAN：
				</td>
				<td>
					<s:textfield name="theForm.vlan" id="vlan" cssClass="txt" cssStyle="width:100px;"></s:textfield>
					<input type="button" value="检测" id="test"/>
					<label style="display: none;padding-left: 5px" id="judgedRight"><font color="green">可以使用</font></label>
					<label style="display: none;padding-left: 5px" id="judgedWrong"><font color="red">不正确，请输入正确VLAN，例如：1-4094</font></label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" id="externalAutoMatic"/>
					<label style="padding-left: 5px">自动将此网络添加到新虚拟机</label>
				</td>
			</tr>
			<tr style="display: none;">
				<td>
					<label>警告：应用这些设置时，网络将暂时中断</label>
				</td>
			</tr>
		</table>
		</div>
	</s:form>
</body>
