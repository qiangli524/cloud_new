<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var api = frameElement.api;
			var w = api.opener;
			api.button({
			     id:'Ok',
			     name: '编辑',
			     callback:edit,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
			$("#capacityInMB").attr("value",$("input[name='#disk.capacityInMB']").val());
			var capacityOld = $("#capacityInMB").val();
			function edit(){
				var parent_id = '<s:property value="parent_id" />';
			 	var parent_uuid = '<s:property value="parent_uuid" />';
			 	var vtype = '<s:property value="vtype" />';
			 	var state = '<s:property value="state" />';
			 	var connect_id = '<s:property value="connect_id" />';
			 	var type = '<s:property value="type" />';
				var uuid = '<s:property value="uuid" />';
				var str = $("#virtualMachineUnitedVO").serialize();
				var mem = $("#memoryMB").val();
				var capacityNew = $("input[name='#disk.capacityInMB']").val();
				var patrn = /^[1-9]\d*$/;
				if(vtype == 1){
					if(!patrn.exec(capacityNew)){
						alert("请输入正确的存储大小");
						return false;
					}
					//存储只能增加，不能减小@yanggl
					if(capacityOld>capacityNew){
						alert("存储只能增加，不能减小");
						$("input[name='#disk.capacityInMB']").attr("value",capacityOld);
						return false;
					}
					
					if(mem%4!=0){
						alert('内存必须为4的倍数');
						return false;
					}
					
					var labels= '';
					var capacitys = '';
					$("input[name='#disk.capacityInMB']").each(function(){
						currentEdit=$(this);
						var lab = currentEdit.next().next().next().attr("lab");
		        		labels +=lab +",";
		        		var capa = $(this).val();
		        		capacitys +=capa+",";
		        	 });
				}else if(vtype == 2){
					var xenCpu = $("#xenNumCPUs").val();
					var xenMem = $("#xenMemoryMB").val();
					if(!patrn.exec(xenCpu)){
						alert('cpu必须大于0的整数');
						return false;
					}
					if(!patrn.exec(xenMem)){
						alert('内存必须大于0的整数');
						return false;
					}
				}
			 	w.saveVMInfo(parent_id,parent_uuid,vtype,connect_id,type,uuid,state,str,labels,capacitys);
			}
		});
	</script>
</head>
<body  style="overflow-y:auto;">
	<s:form action="" method="post" id="virtualMachineUnitedVO" >
	<s:hidden name="virtualMachineUnitedVO.vmName" id="vmName"></s:hidden>
	<s:hidden name="virtualMachineUnitedVO.ip" id="ip"></s:hidden>
	<s:hidden name="capacityInMB" id="capacityInMB"></s:hidden>
		<div>
		 <s:if test="vtype==1">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="30%" align="left">虚拟机名称</td>
					<td>
						<s:property value="virtualMachineUnitedVO.vmName"/>
					</td>
				</tr>
				<tr>
					<td class="til" width="30%" align="left"> IP地址</td>
					<td>
						<s:property value="virtualMachineUnitedVO.ip"/>
					</td>
				</tr>
				<tr>
					<td class="til" width="30%" align="left"> CPU(单位：个)</td>
					<td>
						<s:textfield name="virtualMachineUnitedVO.numCPUs" id="numCPUs" cssClass="txt notnull posInt"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" width="30%" align="left"> 内存(单位：M)</td>
					<td>
						<s:textfield name="virtualMachineUnitedVO.memoryMB" id="memoryMB" cssClass="txt notnull posInt"></s:textfield><br/>
						<font color="red">内存须为4的倍数,且在开机状态下不能减小内存</font>
					</td>
				</tr>
				<!--
				<s:if test="vtype == 1">
					<tr>
						<td class="til" width="30%" align="left"> 存储(单位：G)
						</td>
						<td>
							<s:textfield name="virtualMachineUnitedVO.storageSizeInMb" id="storageSizeInMb" cssClass="txt notnull posInt"></s:textfield>
						</td>
					</tr>
				</s:if>
				  -->
				<s:iterator value="resultList" id="disk">
					<tr>
						<td class="til" align="left" >
							<s:property  value="#disk.vmdkLabel"/>(单位：G)
						</td>
						<td>
							<s:textfield   name="#disk.capacityInMB" ></s:textfield><br/>
							<font color="red">存储只能增加，不能减小</font>
							<input type="hidden" name="capacity" lab='<s:property  value='#disk.vmdkLabel'/>' />
						</td>
					</tr>
				</s:iterator>
			</table>
			</s:if>
			<s:elseif test="vtype == 2">
				<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="30%" align="left">虚拟机名称</td>
					<td>
						<s:property value="virtualMachineUnitedVO.vmName"/>
					</td>
				</tr>
				<tr>
					<td class="til" width="30%" align="left">操作系统 </td>
					<td>
						<s:if test="virtualMachineUnitedVO.ip == '' || virtualMachineUnitedVO.ip ==null">
							暂无
						</s:if>
						<s:property value="virtualMachineUnitedVO.ip"/>
					</td>
				</tr>
				<tr>
					<td class="til" width="30%" align="left"> CPU(单位：个)</td>
					<td>
						<s:textfield name="virtualMachineUnitedVO.numCPUs" id="xenNumCPUs" cssClass="txt notnull posInt"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" width="30%" align="left"> 内存(单位：M)</td>
					<td>
						<s:textfield name="virtualMachineUnitedVO.memoryMB" id="xenMemoryMB" cssClass="txt notnull posInt"></s:textfield><br/>
					</td>
				</tr>
			</table>
			</s:elseif>
		</div>
	</s:form>
</body>
