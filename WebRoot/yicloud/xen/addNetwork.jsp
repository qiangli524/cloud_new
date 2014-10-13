<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/xen/xenHostAddnetwork.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;
	 var poolUuid = '<%=request.getAttribute("poolUuid")%>';
	 var hostUuid = '<%=request.getAttribute("hostUuid")%>';
	 var dialogName = '<%=request.getAttribute("dialogName")%>';
	 var lastStep = '';
	 
	 $(function() {
	 	$(".lastStep").click(function(){
			if(lastStep == 'bindingPage1'){
				lastStep = '';
				$(".externalPage1").show();
 				$(".bindingPage2").hide();
				$(".lastStep").attr("disabled",true);
				$(".ok").attr("disabled",true);
				$(".nextStep").attr("disabled",false);
				$(".menuStyle1").addClass("menuStyle");
				$(".menuStyle4").removeClass("menuStyle").hide();
 				$(".topLabel").text("选择需要创建的类型");
			}
			
			if(lastStep == 'externalPage1'){
				$("#netName").val("新建网络");
				lastStep = '';
				$(".externalPage1").show();
 				$(".externalPage2").hide();
				$(".lastStep").attr("disabled",true);
				$(".menuStyle1").addClass("menuStyle");
				$(".menuStyle2").removeClass("menuStyle").hide();
				$(".topLabel").text("选择需要创建的类型");
			}
			if(lastStep == 'externalPage2'){
				lastStep = 'externalPage1';
 				$(".externalPage2").show();
 				$(".externalPage3").hide();
				$(".nextStep").attr("disabled",false);
				$(".ok").attr("disabled",true);
				$(".menuStyle2").addClass("menuStyle");
				$(".menuStyle3").removeClass("menuStyle").hide();
 				$(".topLabel").text("输入新网络的名称和说明");
			}
			if(lastStep == 'singleServicePage1'){
				$("#netName").val("新建网络");
				lastStep = '';
				$(".externalPage1").show();
 				$(".externalPage2").hide();
				$(".lastStep").attr("disabled",true);
				$(".menuStyle1").addClass("menuStyle");
				$(".menuStyle2").removeClass("menuStyle").hide();
				$(".topLabel").text("选择需要创建的类型");
			}
			if(lastStep == 'singleServicePage2'){
				lastStep = 'singleServicePage1';
 				$(".externalPage2").show();
 				$(".singleServicePage3").hide();
				$(".nextStep").attr("disabled",false);
				$(".ok").attr("disabled",true);
				$(".menuStyle2").addClass("menuStyle");
				$(".menuStyle3").removeClass("menuStyle").hide();
 				$(".topLabel").text("输入新网络的名称和说明");
			}
		});
		
	 	$(".nextStep").click(function(){
			if(lastStep==''){
				var page = $("input:checked").val();
				if(page==1){
					lastStep = 'externalPage1';
					$(".externalPage1").hide();;
	 				$(".externalPage2").show();
					$(".lastStep").attr("disabled",false);
					$(".menuStyle1").removeClass("menuStyle");
					$(".menuStyle2").show().addClass("menuStyle");
	 				$(".topLabel").text("输入新网络的名称和说明");
				}else if(page==2){
					lastStep = 'singleServicePage1';
					$(".externalPage1").hide();;
	 				$(".externalPage2").show();
					$(".lastStep").attr("disabled",false);
					$(".menuStyle1").removeClass("menuStyle");
					$(".menuStyle2").show().addClass("menuStyle");
	 				$(".topLabel").text("输入新网络的名称和说明");
				}else if(page==4){
					lastStep = 'bindingPage1';
					$(".externalPage1").hide();
	 				$(".bindingPage2").show();
					$(".lastStep").attr("disabled",false);
					$(".ok").attr("disabled",false);
					$(".nextStep").attr("disabled",true);
					$(".menuStyle1").removeClass("menuStyle");
					$(".menuStyle4").show().addClass("menuStyle");
	 				$(".topLabel").text("选择新绑定网络的成员");
				}
			}else if(lastStep=='externalPage1'){
				var netName = $("#netName").val();
				if(netName==''){
					alert("请输入网络名称！");
					return ;
				}
				lastStep = 'externalPage2';
 				$(".externalPage2").hide();
 				$(".externalPage3").show();
				$(".nextStep").attr("disabled",true);
				$(".ok").attr("disabled",false);
				$(".menuStyle2").removeClass("menuStyle");
				$(".menuStyle3").show().addClass("menuStyle");
 				$(".topLabel").text("选择网络的位置");
			}else if(lastStep=='singleServicePage1'){
				var netName = $("#netName").val();
				if(netName==''){
					alert("请输入网络名称！");
					return ;
				}
				lastStep = 'singleServicePage2';
 				$(".externalPage2").hide();
 				$(".singleServicePage3").show();
				$(".nextStep").attr("disabled",true);
				$(".ok").attr("disabled",false);
				$(".menuStyle2").removeClass("menuStyle");
				$(".menuStyle3").show().addClass("menuStyle");
 				$(".topLabel").text("选择网络的位置");
			}
		});
		
	 	$(".ok").click(function(){
	 		var creatType = $("input:checked").val();
	 		var nicUuid = '';
	 		var nicName = '';
	 		var netName = '';
	 		var netExplain = '';
	 		var autoMatic = '';
	 		var vlan = '';
	 		if(creatType==1){
				netName = $("#netName").val();
	 			netExplain = $("#netExplain").val();
	 			nicUuid = $("#nic option:selected").val();
	 			nicName = $("#nic option:selected").text();
	 			if(nicUuid==0){
	 				alert("请选择物理网络接口！");
	 				return ;
	 			}
	 			vlan = $("#vlan").val();
				var regular = /^[1-9][0-9]{0,3}$/;
				if(regular.test(vlan)){
					var slaveVlan = $("#slaveVlan").val();
					var jo = eval("(" + slaveVlan + ")");
					var svlan = jo[nicUuid];
					var vlans = svlan.split(",");
					if(vlan>4094){
						$("#judgedWrong").show();
						$("#judgedRight").hide();
						$("#judgedUsed").hide();
						return ;
					}
					for(var i = 0; i<vlans.length;i++){
						if(vlan == vlans[i]){
							$("#judgedWrong").hide();
							$("#judgedRight").hide();
							$("#judgedUsed").show();
							return ;
						}
					}
					$("#judgedWrong").hide();
					$("#judgedRight").show();
					$("#judgedUsed").hide();
				}else{
					$("#judgedWrong").show();
					$("#judgedRight").hide();
					$("#judgedUsed").hide();
					return ;
				}
	 			autoMatic = $("#externalAutoMatic").attr("checked");
			}else if(creatType==2){
				netName = $("#netName").val();
	 			netExplain = $("#netExplain").val();
	 			autoMatic = $("#singleServiceAutoMatic").attr("checked");
			}else if(creatType==4){
				
			}
	 		var url = 'xen_addNetwork.do?poolUuid='+poolUuid+"&hostUuid="+hostUuid+"&creatType="+creatType+"&nicUuid="+nicUuid
	 				  +"&nicName="+nicName+"&netName="+encodeURI(encodeURI(netName))+"&netExplain="+encodeURI(encodeURI(netExplain))
	 				  +"&autoMatic="+autoMatic+"&vlan="+vlan;
	 		w.addNetwork(url,dialogName);
		});
		
		$(".cancle").click(function(){
			w.closeDialog(dialogName);
		});
		
		$("#test").click(function(){
			var nicUuid = $("#nic option:selected").val();
			var slaveVlan = $("#slaveVlan").val();
			var vlan = $("#vlan").val();
			var jo = eval("(" + slaveVlan + ")");
			var svlan = jo[nicUuid];
			var vlans = svlan.split(",");
			var regular = /^[1-9][0-9]{0,3}$/;
			if(regular.test(vlan)){
				if(vlan>4094){
					$("#judgedWrong").show();
					$("#judgedRight").hide();
					$("#judgedUsed").hide();
					return ;
				}
				for(var i = 0; i<vlans.length;i++){
					if(vlan == vlans[i]){
						$("#judgedWrong").hide();
						$("#judgedRight").hide();
						$("#judgedUsed").show();
						return ;
					}
				}
				$("#judgedUsed").hide();
				$("#judgedWrong").hide();
				$("#judgedRight").show();
			}else{
				$("#judgedWrong").show();
				$("#judgedRight").hide();
				$("#judgedUsed").hide();
			}
		});
		
		$(".lastStep").attr("disabled","disabled");
	 	$(".ok").attr("disabled","disabled");
	 	$(".menuStyle1").attr("style","display=''").addClass("menuStyle");
	 	$(".externalPage1").attr("style","display=''");
	 });
</script>
<style type="text/css">
</style>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<s:hidden name="theForm.slaveVlan" id="slaveVlan"/>
	<div class="top">
		<img src="<%=request.getContextPath() %>/sxcloud/images/network.jpg" style="position: relative;bottom: 5px"/>
		<label class="topLabel">选择需要创建的类型</label><br/>
	</div>
	<hr/>
	<div class="left">
		<ul style="width: auto;position: relative;top: 15px;">
			<li class="menuStyle1" style="display: none"><font>选择类型</font></li>
			<li class="menuStyle2" style="display: none"><font>详细信息</font></li>
			<li class="menuStyle3" style="display: none"><font>接口</font></li>
			<li class="menuStyle4" style="display: none"><font>绑定成员</font></li>
		</ul>
	</div>
	<hr class="verticalhr"/>
	
	<div class="right">
		<table style="display: none" class="externalPage1">
			<tr>
				<td>
					<label>选择要创建的网络类型：</label>
				</td>
			</tr>
			
			<tr>
				<td>
					<br /><input type="radio" name="netType" id="netType1" checked="checked" value="1"/>
					<label class="netTypeName">外部网络</label>
				</td>
			</tr>
			<tr>
				<td>
					<label class="netTypeContent">创建通过一个VLAN传递通信量的网络</label>
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="netType" id="netType2" value="2"/>
					<label class="netTypeName">单服务器专用网络</label>
				</td>
			</tr>
			<tr>
				<td>
					<label class="netTypeContent">创建不离开每个 xenServer 主机的网络。</label>
				</td>
			</tr>
			<tr>
				<td>
					<label class="netTypeContent">该网络可用作同一主机上各 VM 之间的专用连接。</label>
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="radio" name="netType" id="netType3" value="3" disabled="disabled"/>
					<label class="netTypeName">跨服务器专用网络</label>
				</td>
			</tr>
			<tr>
				<td>
					<label class="netTypeContent">创建不离开 xenServer 池的网络。</label>
				</td>
			</tr>
			<tr>
				<td>
					<label class="netTypeContent">该网络可用作池中各 VM 之间的专用连接。</label>
				</td>
			</tr>
			<tr>
				<td>
					<label class="netTypeContent">此类型的网络要求 vSwitch 控制器处于运行状态。</label>
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="radio" name="netType" id="netType4" value="4"/>
					<label class="netTypeName">绑定网络</label>
				</td>
			</tr>
			<tr>
				<td>
					<label class="netTypeContent">创建将两个Nic绑定在一起的网络。</label>
				</td>
			</tr>
			<tr>
				<td>
					<label class="netTypeContent">这样将创建一个性能更高的通道。</label>
				</td>
			</tr>
		</table>
		
		<table class="externalPage2" style="display: none">
			<tr>
				<td>为新网络提供名称和可选说明。</td>
			</tr>
			<tr>
				<td>
					<br /><br />
					<label>名称：</label>
					<s:textfield name="theForm.netName" id="netName" value="新建网络" cssClass="txt" cssStyle="width:400px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					<label>说明：</label>
					<s:textfield name="theForm.netExplain" id="netExplain" cssClass="txt" cssStyle="width:400px;"></s:textfield>
				</td>
			</tr>
		</table>
		
		<table class="externalPage3" style="display: none">
			<tr>
				<td>新网络将映射到现有物理网络接口，并分配一个在改接口上使用的VLAN编号。可以在下面选在要使用的物理接口。</td>
			</tr>
			<tr>
				<td>
					<br /><br />
					<label>NIC：</label>
					<s:select id="nic" name="theForm.nic" list="theForm.resultList" listKey="nicUuid" listValue="nicName" headerKey="0" headerValue="     --请选择--      " cssStyle="width:250px;position: relative;left: 11px;"></s:select>
				</td>
			</tr>
			<tr>
				<td>
					<label>VLAN：</label>
					<s:textfield name="theForm.vlan" id="vlan" cssClass="txt" cssStyle="width:100px;"></s:textfield>
					<input type="button" value="检测" id="test"/>
					<label style="display: none;padding-left: 5px" id="judgedRight"><font color="green">可以使用</font></label>
					<label style="display: none;padding-left: 5px" id="judgedWrong"><font color="red">不正确，请输入正确VLAN，例如：1-4094</font></label>
					<label style="display: none;padding-left: 5px" id="judgedUsed"><font color="red">该VLAN已使用，请更换</font></label>
				</td>
			</tr>
			<tr>
				<td>
					<br /><br />
					<input type="checkbox" id="externalAutoMatic"/>
					<label style="padding-left: 5px">自动将此网络添加到新虚拟机</label>
				</td>
			</tr>
		</table>
		
		<table class="singleServicePage3" style="display: none">
			<tr>
				<td>如果要将该网络自动添加到新VM中，请选中下面的复选框。</td>
			</tr>
			<tr>
				<td>
					<br /><br />
					<input type="checkbox" id="singleServiceAutoMatic"/>
					<label style="padding-left: 5px">自动将此网络添加到新虚拟机</label>
				</td>
			</tr>
		</table>
		
		<table class="bindingPage2" style="display: none">
			<tr>
				<td>选择要在此绑定中使用的NIC以及绑定设置，并确认是否将该网络添加到新虚拟机中。</td>
			</tr>
			<tr>
				<td>
					<div style="overflow: auto;width: 540px;height: 200px">
						<table width="100%"  border="1" cellspacing="0" class="blue-table sorttable" >
							<thead>
							<tr>
								<th>选择</th>
								<th>NIC</th>
								<th>MAC</th>
								<th>链接状态</th>
								<th>速度</th>
								<th>双工</th>
								<th>供应商</th>
								<th>设备</th>
								<th>PCI总线路径</th>
							</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</td>				
			</tr>
			<tr>
				<td>
					<br /><br />
					<input type="checkbox" id="bindingaAutoMatic"/>
					<label style="padding-left: 5px">自动将此网络添加到新虚拟机</label>
				</td>
			</tr>
		</table>
	</div>
	
	<hr class="hr2"/>
	<div class="bottom">
		<input type="button" value="上一步" class="lastStep"/>
		<input type="button" value="下一步" class="nextStep" />
		<input type="button" value="完成" class="ok"/>
		<input type="button" value="取消" class="cancle"/>
	</div>
	</s:form>
</body>
