<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
<title></title>
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript">
	var flag = 0;//用于判断在提交时表单内容是否完全正确，以确定是否可以提交
	var api = frameElement.api;
	var w = api.opener;
	$(function() {
		api.button({
			id : 'OkAnd',
			name : '确定',
			callback : updateTemplateInstall,
			focus : true
		}, {
			id : 'cancle',
			name : '取消'
		});
	});

	function updateTemplateInstall() {
		var _mge_console_ip = $("#mge_console_ip").val();
		if (_mge_console_ip == null || _mge_console_ip == '') {
			alert('请填写管理口IP');
			return false;
		}
		var _mge_console_username = $("#mge_console_username").val();
		if (_mge_console_username == null || _mge_console_username == '') {
			alert('请填写用户名');
			return false;
		}
		var _mge_console_pass = $("#mge_console_pass").val();
		if (_mge_console_pass == null || _mge_console_pass == '') {
			alert('请填写口令');
			return false;
		}
		var _cpu_cl = $("#cpu_cl").val();
		if (_cpu_cl == null || _cpu_cl == '') {
			alert('请填写主机CPU');
			return false;
		}
		var _memory = $("#memory").val();
		if (_memory == null || _memory == '') {
			alert('请填写主机内存');
			return false;
		}
		var _storage_num = $("#storage_num").val();
		if (_storage_num == null || _storage_num == '') {
			alert('请填写主机存储');
			return false;
		}
		var _nic_num = $("#nic_num").val();
		if (_nic_num == null || _nic_num == '') {
			alert('请填写网卡');
			return false;
		}
		var _eq_type = $("#eq_type").val();
		if (_eq_type == null || _eq_type == '') {
			alert('请填写主机类型');
			return false;
		}
		var _os_versions = $("#os_versions").val();
		if (_os_versions == null || _os_versions == '') {
			alert('请填写操作系统版本');
			return false;
		}
		var _os_digits = $("#os_digits").val();
		if (_os_digits == null || _os_digits == '') {
			alert('请填写操作系统位数');
			return false;
		}
		var _raid_information = $("#raid_information").val();
		if (_raid_information == null || _raid_information == '') {
			alert('请填写Raid信息');
			return false;
		}
		var _bond_information = $("#bond_information").val();
		if (_bond_information == null || _bond_information == '') {
			alert('请填写Bond信息');
			return false;
		}
		var _fibercard = $("#fibercard").val();
		if (_fibercard == null || _fibercard == '') {
			alert('请填写光纤卡');
			return false;
		}
		var _shared_storage = $("#shared_storage").val();
		if (_shared_storage == null || _shared_storage == '') {
			alert('请填写共享存储');
			return false;
		}
		var _blade_groove = $("#blade_groove").val();
		if (_blade_groove == null || _blade_groove == '') {
			alert('请填写刀片槽位');
			return false;
		}
		w.saveHostInfo($("#theForm").serialize());
		//theForm.submit();
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="autoos_insertOsHost.do" method="post" id="theForm"
		cssStyle="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr align="left">
					<td class="til">主机序列号 <font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.serial_num" id="mge_console_ip"
							style="width:150px;height:20px;" maxlength="30"></s:textfield></td>

					<td class="til">主机类型 <font color="red">*</font>
					</td>
					<td>
						<!--  <s:select
							list="#{'':'','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器'}"
							name="theForm.eq_type" id="eq_type"
							style="width:150px;   height:20px;" maxlength="16" /> --> <s:select
							list="#{'':'','2':'IBMx86刀片','3':'IBM PC服务器','4':'HPx86刀片','6':'HP PC服务器','7':'HUAWEI PC服务器'}"
							name="theForm.eq_type" id="eq_type"
							style="width:150px;   height:20px;" maxlength="16" /></td>
				</tr>
				<tr align="left">
					<td class="til">主机型号 <font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.host_type_num"
							id="mge_console_ip" style="width:150px;height:20px;"
							maxlength="30"></s:textfield></td>
					<td class="til">主机CPU个数<font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.cpu_cl" id="cpu_cl"
							style="width:150px;   height:20px;" maxlength="16"></s:textfield>
					</td>
				</tr>
				<tr align="left">

					<td class="til">主机内存 <font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.memory" id="memory"
							style="width:150px;   height:20px;" maxlength="16"></s:textfield>G
					</td>
					<td class="til">主机存储大小 <font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.store" id="storage_num"
							style="width:150px;   height:20px;" maxlength="16"></s:textfield>G
					</td>
				</tr>
				<tr align="left">
					<td class="til">主机存储块数 <font color="red">*</font>
					</td>
					<td><s:select
							list="#{'':'','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6'}"
							name="theForm.storage_num" id="storage_num"
							style="width:150px;   height:20px;" maxlength="16" />块</td>
					<td class="til">网卡个数 <font color="red">*</font>
					</td>
					<td><s:select
							list="#{'':'','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6'}"
							name="theForm.nic_num" id="nic_num"
							style="width:150px;   height:20px;" maxlength="16" />个</td>
				</tr>
				<tr align="left">
					<td class="til">IPMI管理口IP <font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.mge_console_ip"
							id="mge_console_ip" style="width:150px;height:20px;"
							maxlength="30"></s:textfield></td>
					<td class="til">管理口用户 <font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.mge_console_username"
							id="mge_console_username" style="width:150px;   height:20px;"
							maxlength="30"></s:textfield></td>
				</tr>
				<tr align="left">
					<td class="til">管理口密码 <font color="red">*</font>
					</td>
					<td><s:password name="theForm.mge_console_pass"
							id="mge_console_pass" style="width:150px;   height:20px;"
							maxlength="16"></s:password></td>
					<td class="til">管理口MAC</td>
					<td><s:textfield name="theForm.mge_console_mac" id="mac"
							style="width:150px;   height:20px;" maxlength="30"></s:textfield>
					</td>

				</tr>
				<tr align="left">
					<td class="til">操作系统版本<font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.os_versions" id="os_versions"
							style="width:150px;   height:20px;" maxlength="17"></s:textfield>
					</td>
					<td class="til">操作系统位数<font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.os_digits" id="os_digits"
							style="width:150px;   height:20px;" maxlength="17"></s:textfield>
					</td>
				</tr>
				<tr align="left">
					<td class="til">raid信息<font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.raid_information"
							id="raid_information" style="width:150px;   height:20px;"
							maxlength="17"></s:textfield>
					</td>

					<td class="til">光纤卡<font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.fibercard" id="fibercard"
							style="width:150px;   height:20px;" maxlength="17"></s:textfield>
					</td>

				</tr>
				<tr align="left">

					<td class="til">共享存储<font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.shared_storage"
							id="shared_storage" style="width:150px;   height:20px;"
							maxlength="17"></s:textfield>
					</td>
					<td class="til">bond信息<font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.bond_information"
							id="bond_information" style="width:150px;   height:20px;"
							maxlength="17"></s:textfield>
					</td>
				</tr>
				<tr align="left">
					<td class="til">存放机房</td>
					<td><s:select list="#{'1':'府青机房','2':'西区机房','2':'上海电信'}"
							name="theForm.stay_machroom" id="stay_machroom"
							style="width:150px;   height:20px;" maxlength="16" />
					</td>
					<td class="til">主机物理位置 <font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.host_physical_position"
							id="mge_console_ip" style="width:150px;height:20px;"
							maxlength="30"></s:textfield></td>
				</tr>
				<tr align="left">
					<td class="til">刀片槽位<font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.blade_groove" id="blade_groove"
							style="width:150px;   height:20px;" maxlength="16"></s:textfield>
					</td>
					<td class="til">描述</td>
					<td><s:textfield name="theForm.install_desc" id="install_desc"
							style="width:150px;   height:20px;" maxlength="17"></s:textfield>
					</td>


				</tr>

				<!--  <tr>
					<td colspan="2" align="right"><input type="button"
						class="thickbox btn-style02" value="保存"
						onclick="javascript:updateTemplateInstall();" /></td>
				</tr> -->
			</table>
		</div>
	</s:form>
</body>