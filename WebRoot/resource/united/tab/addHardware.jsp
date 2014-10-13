<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>


	<script type="text/javascript">

	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok',
	     name: '添加',
	     callback:confirm,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	
	function confirm(){
		var _val = $("#hardware").find('option:selected').val();
		if(_val=='vnic'){
			theForm.action="unitedOper_addVnic.do";
		}else if(_val=='disk'){
			var validate = validatePath();
			if(validatePath()){
				alert(validate);
				return false;
			}
			theForm.action="unitedOper_createDisk.do"//创建或新加磁盘
		}
		theForm.submit();
		return false;
	}
	function validatePath(){
		var _val= $('[name="type"]:checked').val();
		if(_val=='now'){
			var vmdkPath = $.trim($("#vmdkPath").val());
			if(vmdkPath == null || vmdkPath == ''){
				return "磁盘路径不能为空！";
			}
		}else if(_val=='new'){
			var capacity = $.trim($("#capacity").val());
			if(capacity ==null||capacity==''){
				return "磁盘大小不能为空！";
			}
		} 
		return false;
	}
	//添加网卡回调函数
	function addCallback(result){
		bar('addnic',result,4);
		//api.close();//关闭对话窗
		setTimeout("refresh()", 1000)
	}
	function refresh(){
		w.refresh();
	}
	function bar(idstr,contents,seconds){
		w.$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    time: seconds,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}
	
	function dealDatastore() {
		var datastore = $("input[name='diskPath']:checked").val();
		if (datastore == 1) {
			$("#datastore").hide();
		} else {
			$("#datastore").show();
		}
	}
	$(function(){
		$("[type='hardware']").change(function(){
			var type = $(this).find('option:selected').val();
			if(type=='vnic'){
				$(".vnic").show();
				$(".disk").hide();
				$(".location").hide();
				$(".datastore").hide();
			}else if(type="disk"){
				$(".vnic").hide();
				$(".disk").not("#path").show();
				$(".location").show();
				var datastore = $("input[name='diskPath']:checked").val();
				if (datastore == 1) {
					$("#datastore").hide();
				} else {
					$("#datastore").show();
				}
			}
		});
		$("#independence").click(function(){
			if(this.checked){
				$('.model').attr('disabled',false);
			}else{
				$('.model').attr('disabled',true);
			}
		});
		$('[name="type"]').click(function(){
			var typeVal = $(this).val();
			if(typeVal=='new'){
				$(".disk").not("#path").show();
				$("#path").hide();
			}else if(typeVal='now'){
				$(".disk").not("#size").show();
				$("#size").hide();
			}else if(typeVal='san'){
				//$(".disk").not("#path").show();
			}
		});
		dealDatastore();
	});
</script>
</head>
<body class="pop-body scrollbody">
	<s:form method="post" id="theForm" target="hidden_frame">
	<s:hidden name="virtualEthernetCardUnitedVO.vType" value="%{vtype}"></s:hidden>
	<s:hidden name="virtualEthernetCardUnitedVO.vmCode" value="%{uuid}"></s:hidden>
	<s:hidden name="virtualEthernetCardUnitedVO.connectCode" value="%{connect_id}"></s:hidden>
	
	<s:hidden name="virtualDiskUnitedVO.vType" value="%{vtype}"></s:hidden>
	<s:hidden name="virtualDiskUnitedVO.vmCode" value="%{uuid}"></s:hidden>
	<s:hidden name="virtualDiskUnitedVO.connectCode" value="%{connect_id}"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			
			<tr>
				<td class="til" width="20%">
					类型：
				</td>
				<td>
					<s:select list="#{'vnic':'网卡','disk':'硬盘'}" type="hardware" id="hardware"></s:select>
				</td>
			</tr>
			<tr class="vnic">
				<td class="til">
					适配器类型:
				</td>
				<td>
					<s:select list="#{'E1000':'E1000','VMXNET2':'VMXNET2','VMXNET3':'VMXNET3'}" name="virtualEthernetCardUnitedVO.nicType"></s:select>
				</td>
			</tr>
			<tr class="vnic">
				<td class="til">
					网络连接:
				</td>
				<td>
					<s:select list="queryList" listKey="name" listValue="name" name="virtualEthernetCardUnitedVO.portGroup"></s:select>
				</td>
			</tr>
			<tr class="vnic">
				<td class="til">
					设备状态：
				</td>
				<td>
					<s:checkbox name="status"  checked="checked" disabled="true"></s:checkbox>&nbsp;打开电源时连接
				</td>
			</tr>
			<tr class="disk" style="display:none">
				<td class="til">
					磁盘类型:
				</td>
				<td>
					<input type="radio" name="type" value="new" checked="checked"/>创建新的虚拟机磁盘<br/>
					<input type="radio" name="type" value="now" />使用现有磁盘 <br/>
					(重新使用以前配置的磁盘)<br />
					<input type="radio" name="type" value="san" disabled="disabled"/>裸机映射 <br/>
					(让虚拟机直接访问SAN.该选项允许您使用现有SAN命令管理存储器并继续使用数据存储访问该存储器)
				</td>
			</tr>
			<tr class="disk" style="display:none" id="size">
				<td class="til">
				<font color="red">*</font>磁盘大小:
				</td>
				<td>
					<s:textfield cssClass="txt" cssStyle="width:100px;" name="virtualDiskUnitedVO.capacityInMB" id="capacity"></s:textfield>MB
				</td>
			</tr>
			<tr class="disk" style="display:none" id="path">
				<td class="til">
					<font color="red">*</font>存储路径:
				</td>
				<td>
					<s:textfield cssClass="txt" cssStyle="width:400px;" name="virtualDiskUnitedVO.vmdkPath" id="vmdkPath"></s:textfield>
				</td>
			</tr>
			<tr class="disk" style="display:none">
				<td class="til">
					类型
				</td>
				<td>
					<s:select list="#{'thin':'精简模式','thick':'厚置备延迟置零','eagerzeroedthick':'厚置备置零'}" name="virtualDiskUnitedVO.diskType"></s:select>
				</td>
			</tr>
			<tr class="disk" style="display:none">
				<td class="til">
					模式:
				</td>
				<td>
					<input type="checkbox" value="independent" id="independence"/>独立<br />
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="virtualDiskUnitedVO.diskMode" disabled="disabled" value="independent_persistent" class="model"/>持久<br />
					&nbsp;&nbsp;&nbsp;&nbsp;(更改会立即永久性的写入磁盘)<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="virtualDiskUnitedVO.diskMode" disabled="disabled" value="independent_nonpersistent" class="model"/>非持久<br />
					&nbsp;&nbsp;&nbsp;&nbsp;(当关闭电源或恢复快照时，对该磁盘的更改会被丢弃)<br />
				</td>
			</tr>
			
			<tr class="location" style="display:none">
				<td class="til">
					位置:
				</td>
				<td>
					<input type="radio" name="diskPath" id="diskPath1" checked="checked" value="1" onclick="dealDatastore();"/><label for="diskPath1">与虚拟机存储在同一目录</label>
				    <input type="radio" name="diskPath" id="diskPath2" value="2" onclick="dealDatastore();"/><label for="diskPath1">指定数据存储</label><br/>
				</td>
			</tr>
			
			<tr class="datastore" style="display:none" id="datastore">
				<td class="til">
					存储清单:
				</td>
				<td>
					<s:select list="datastoreList" headerKey="-1" headerValue="请选择" listKey="NAME" listValue="NAME" name="virtualDiskUnitedVO.datastoreCode" id="datastoreCode"></s:select>
				</td>
			</tr>
		</table>
	</s:form>
	<iframe name="hidden_frame" id="hidden_frame" style="display:none;"></iframe>
</body>
