<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
			var uuid = '<s:property value="uuid" />';
			var connect_id = '<s:property value="connect_id" />';
			var vtype = '<s:property value="vtype" />';
			function addHardware(){
				var parent_uuid = '<s:property value="parent_uuid" />';
				$.dialog({
					id:'addHardware',
					title:'添加硬件',
					lock:true,
					height:'450px',
					width:'800px',
					content:'url:unitedOper_addHardware.do?uuid='+uuid+"&connect_id="+connect_id+"&vtype="+vtype+"&parent_uuid="+parent_uuid
				});
			}

			function adjustSubmit(){
				$.getJSON(url,{'time':new Date().toString()},function(data){
				});
			}
			//调整存储
			function adjustStorage(){
				$.dialog({
					id:'adjustStorage',
					title:'调整存储',
					lock:true,
					height:'300px',
					width:'534px',
					content:'url:unitedOper_adjustStorage.do'
				});
			}
			//装操作系统
			function operateSystem(){
				$.dialog({
					id:'operateSystem',
					title:'操作系统',
					lock:true,
					height:'300px',
					width:'534px',
					content:'url:unitedOper_operateSystem.do?uuid='+uuid+"&connect_id="+connect_id+"&vtype="+vtype
				});
			}
			//Action执行后回调函数
			function callBack(result){
				bar('callback',result,4);
				setTimeout("refresh()", 1000)
			}
			$(function(){
				//删除网卡信息
				$(".deleteVnic").click(function(){
					var _val = $(this).attr('vnicName');
					theForm.ethernetCardName.value=_val;
					theForm.action = 'unitedOper_deleteVnic.do';
					$.dialog.confirm('你确定要删除该网卡吗？', function(){
   			 				theForm.submit();
					});
				});
				$(".reconfig").click(function(){//重新配置网卡
					var _val = $(this).attr('vnicName');
					var portgroup = $(this).prev().find('option:selected').val();
					theForm.ethernetCardName.value=_val;
					theForm.portGroup.value = portgroup;
					theForm.action = 'unitedOper_reconfigVnic.do';
					theForm.submit();
				});
				//删除一个磁盘
				$(".delDisk").click(function(){
					var _val = $(this).attr('label');
					theForm.vmdkLabel.value=_val;
					var destroy = false;
					var remove = "移除";
					if($(this).attr('id')=='destroy'){
						destroy = true;
						remove = "删除";
					}
					theForm.action='unitedOper_deleteDisk.do?destroy='+destroy;
					$.dialog.confirm('你确定要'+remove+'该磁盘吗？', function(){
   			 			theForm.submit();
					});
				});
			});
	function bar(idstr,contents,seconds){
		$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    time: seconds,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    lock:true,
		    fixed: true,
		    max:false,
		    content:contents
		});
	}
	function refresh(){
		window.location.reload();
	}
	</script>
</head>
<body  style="overflow-y:auto;">
	<s:form method="post" id="theForm" target="hidden_frame">
	<s:hidden name="virtualEthernetCardUnitedVO.ethernetCardName" id="ethernetCardName"></s:hidden>
	<s:hidden name="virtualEthernetCardUnitedVO.vType" value="%{vtype}"></s:hidden>
	<s:hidden name="virtualEthernetCardUnitedVO.vmCode" value="%{uuid}"></s:hidden>
	<s:hidden name="virtualEthernetCardUnitedVO.connectCode" value="%{connect_id}"></s:hidden>
	<s:hidden name="virtualEthernetCardUnitedVO.portGroup" id="portGroup"></s:hidden>
	<s:hidden name="virtualDiskUnitedVO.vmdkLabel" id="vmdkLabel"></s:hidden>
	<s:hidden name="virtualDiskUnitedVO.vType" value="%{vtype}"></s:hidden>
	<s:hidden name="virtualDiskUnitedVO.vmCode" value="%{uuid}"></s:hidden>
	<s:hidden name="virtualDiskUnitedVO.connectCode" value="%{connect_id}"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="30%" align="left">虚拟机名称</td>
					<td>
						<s:property value="virtualMachineUnitedVO.vmName"/>
						&nbsp;&nbsp;&nbsp;<input type="button" value="添加硬件" class="ubtn-3 vm mgl-3" onclick="addHardware();">
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
						<%-- 
						<s:textfield name="virtualMachineUnitedVO.numCPUs" id="numCPUs" cssClass="txt notnull posInt"></s:textfield>
						&nbsp;<a href="javascript:;" onclick="adjustSubmit();">确定</a>
						--%>
						<s:property value="virtualMachineUnitedVO.numCPUs"/>
					</td>
				</tr>
				<tr>
					<td class="til" width="30%" align="left"> 内存(单位：MB)</td>
					<td>
						<%-- 
						<s:textfield name="virtualMachineUnitedVO.memoryMB" id="memoryMB" cssClass="txt notnull posInt"></s:textfield>
						&nbsp;<a href="javascript:;" onclick="adjustSubmit();">确定</a>
						--%>
						<s:property value="virtualMachineUnitedVO.memoryMB"/>
					</td>
				</tr>
					<%-- <s:if test="vtype == 1">
					<tr>
						<td class="til" width="30%" align="left"> 存储(单位：MB)
							<br />
							(只能添加存储，不能减小)
						</td>
						<td>
						
							<s:textfield name="virtualMachineUnitedVO.storageSizeInMb" id="storageSizeInMb" cssClass="txt notnull posInt"></s:textfield>
							&nbsp;<a href="javascript:;" onclick="adjustStorage();">调整</a>
							
							<s:property value="virtualMachineUnitedVO.storageSizeInMb"/>
						</td>
					</tr>
				</s:if>
				--%>
				<s:iterator value="diskList" id="theBean">
					<tr>
						<td class="til">
							<s:property value="#theBean.vmdkLabel"/> (单位：MB)
						</td>
						<td>
							<s:textfield name="#theBean.capacityInMB" cssClass="txt" cssStyle="width:129px;"></s:textfield>
							<a href="javascript:;" class="delDisk" alt="注：只卸载磁盘，不在存储中删除磁盘" title="注：只卸载磁盘，不在存储中删除磁盘" label="<s:property value="#theBean.vmdkLabel" />" id="remove">卸载</a>
							<a href="javascript:;" class="delDisk"  alt="注：卸载存储，并存储中删除磁盘" title="注：卸载存储，并存储中删除磁盘" label="<s:property value="#theBean.vmdkLabel"/>" id="destroy">删除</a>
						</td>
					</tr>
				</s:iterator>
				<s:iterator value="queryList" id="theBean">
					<tr>
						<td class="til">
							<s:property value="#theBean.ethernetCardName"/>
						</td>
						<td>
							<s:select list="queryList2" listKey="name" listValue="name" name="#theBean.portGroup"></s:select>
							<a href="javascript:;" class="reconfig" vnicName='<s:property value="#theBean.ethernetCardName"/>'>确定</a>&nbsp;
							<a href="javascript:;"  class="deleteVnic" vnicName='<s:property value="#theBean.ethernetCardName"/>'>删除</a>
						</td>
					</tr>
				</s:iterator>
				<!--  
				<tr>
					<td class="til">
						操作系统 
					</td>
					<td>
						<a href="javascript:;" onclick="operateSystem();">编辑</a>
					</td>
				</tr>
				-->
			</table>
		</div>
	</s:form>
	<iframe  name="hidden_frame" id="hidden_frame" style="display:none"></iframe>
</body>
