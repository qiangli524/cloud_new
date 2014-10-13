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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript">
	function updateCustomedInstall() {
		theForm.submit();
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="autoos_updateCustomedInstall.do" method="post"
		id="theForm" cssStyle="theForm">
		<s:hidden name="theForm.id" id="id"></s:hidden>
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr align="left">
					<td class="til">管理口IP <font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.mge_console_ip"
							id="mge_console_ip" disabled="true"
							style="width:150px;   height:20px;" maxlength="30" cssClass=" "></s:textfield>
					</td>
					<td class="til">MAC地址 <font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.mac" id="mac" disabled="true"
							style="width:150px;   height:20px;" maxlength="30"
							cssClass="required"></s:textfield></td>
				</tr>
				<tr align="left">
					<td class="til">用户名 <font color="red">*</font>
					</td>
					<td><s:textfield name="theForm.mge_console_username"
							id="mge_console_username" disabled="true"
							style="width:150px;   height:20px;" maxlength="30" cssClass=""></s:textfield>
					</td>
					<td class="til">密码 <font color="red">*</font>
					</td>
					<td><s:password type="password"
							name="theForm.mge_console_pass" id="mge_console_pass"
							disabled="true" style="width:150px;   height:20px;"
							maxlength="16" showPassword="true" cssClass=""></s:password></td>
				</tr>
				<tr align="left">
					<td class="til">安装方式</td>
					<td colspan='3'><s:select list="#{'0':'模版安装'}"
							style="width:150px;   height:100px;" id="install_type"
							disabled="true" name="theForm.install_type"></s:select></td>
				</tr>
				<tr align="left">
					<td class="til">操作系统</td>
					<td><s:select
							list="#{'Red Hat Enterprise Linux 2':'Red Hat Enterprise Linux 2','Red Hat Enterprise Linux 3 64 bit':'Red Hat Enterprise Linux 3 64 bit','Red Hat Enterprise Linux 3':'Red Hat Enterprise Linux 3','Red Hat Enterprise Linux 4 64 bit':'Red Hat Enterprise Linux 4 64 bit','Red Hat Enterprise Linux 4':'Red Hat Enterprise Linux 4','Red Hat Enterprise Linux 5':'Red Hat Enterprise Linux 5','Red Hat Enterprise Linux 6 64 bit':'Red Hat Enterprise Linux 6 64 bit','Red Hat Enterprise Linux 6':'Red Hat Enterprise Linux 6'}"
							style="width:250px;   height:100px;" id="install_os"
							name="theForm.install_os"></s:select></td>
				</tr>
			</table>
		</div>

		<div class="table-ct">
			<div>网络配置</div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr align="left">
					<td class="til">IP 地址<font color="red">*</font>
					</td>
					<td><s:property value="theForm.ip_address" /></td>
				</tr>
				<tr align="left">
					<td class="til">子网掩码<font color="red">*</font>
					</td>
					<td><s:property value="theForm.subnet" /></td>
				</tr>
				<tr align="left">
					<td class="til">默认网关<font color="red">*</font>
					</td>
					<td><s:property value="theForm.gateway" /></td>
				</tr>
			</table>
		</div>
		<div class="table-ct">
			<div>用户配置</div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr align="left">
					<td class="til">id<font color="red">*</font>
					</td>
					<td class="til">主机ID<font color="red">*</font>
					</td>
					<td class="til">用户名<font color="red">*</font>
					</td>
					<td class="til">密码<font color="red">*</font>
					</td>
				</tr>
				<s:iterator value="theForm.userList" id="theBean">
					<tr align="left">
						<td><s:property value="#theBean.id" />
						</td>
						<td><s:property value="#theBean.os_host_id" />
						</td>
						<td><s:property value="#theBean.os_user" />
						</td>
						<td><s:property value="#theBean.os_password" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div class="table-ct">
			<div>分区配置</div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr align="left">
					<td>Id</td>
					<td>序号</td>
					<td>主机ID</td>
					<td>分区名</td>
					<td>文件系统类型</td>
					<td>分区大小</td>
				</tr>
				<s:iterator value="theForm.partitionList" id="theBean">
					<tr align="left">
						<td><s:property value="#theBean.id" />
						</td>
						<td><s:property value="#theBean.order_num" />
						</td>
						<td><s:property value="#theBean.os_host_id" />
						</td>
						<td><s:property value="#theBean.part_name" />
						</td>
						<td><s:property value="#theBean.part_type" />
						</td>
						<td><s:property value="#theBean.part_size" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</s:form>
</body>