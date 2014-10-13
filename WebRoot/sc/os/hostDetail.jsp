<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>

<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
		var flag = 0;//用于判断在提交时表单内容是否完全正确，以确定是否可以提交
		var api = frameElement.api;
		var w = api.opener;
		$(function() {
			api.button({
				id : 'cancle',
				name : '关闭',
				focus : true
			});
		});			
	</script>
</head>
<body>
<div class="mainbody">
	<s:form action="osUser_queryUser.do" method="post" cssStyle="theForm" id="theForm">
	<s:hidden name="theForm.id" id="id"></s:hidden>
	<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
	<s:hidden name="theForm.os_host_id" id="os_host_id"></s:hidden>
		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<div class="utt-2">主机配置</div>
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<tr align="left">
						<td class="til">主机序列号</td>
						<td>
							<s:textfield name="theForm.serial_num" id="mge_console_ip" cssClass="input-2" maxlength="30" disabled="true" ></s:textfield>
						</td>
						<td class="til">主机类型</td>
						<td>
							<s:select list="#{'':'','2':'IBMx86刀片','3':'IBM PC服务器','4':'HPx86刀片','6':'HP PC服务器'}" name="theForm.eq_type" id="eq_type" cssClass="select-1" maxlength="16" disabled="true" />
						</td>
					</tr>
					<tr align="left">
						<td class="til">主机型号</td>
						<td>
							<s:textfield name="theForm.host_type_num" id="mge_console_ip" cssClass="input-2" maxlength="30" disabled="true" ></s:textfield></td>
						<td class="til">主机CPU个数</td>
						<td>
							<s:textfield name="theForm.cpu_cl" id="cpu_cl" cssClass="input-2" maxlength="16" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">主机内存</td>
						<td>
							<s:textfield name="theForm.memory" id="memory" cssClass="input-2" maxlength="16" disabled="true" ></s:textfield>G
						</td>
						<td class="til">主机存储大小</td>
						<td>
							<s:textfield name="theForm.store" id="storage_num" cssClass="input-2" maxlength="16" disabled="true" ></s:textfield>G
						</td>
					</tr>
					<tr align="left">
						<td class="til">主机存储块数</td>
						<td>
							<s:select list="#{'':'','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6'}" name="theForm.storage_num" id="storage_num" cssClass="select-1" maxlength="16" disabled="true"  />块
						</td>
						<td class="til">网卡个数</td>
						<td>
							<s:select list="#{'':'','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6'}" name="theForm.nic_num" id="nic_num" cssClass="select-1" maxlength="16" disabled="true" />个
						</td>
					</tr>
					<tr align="left">
						<td class="til">IPMI管理口IP</td>
						<td>
							<s:textfield name="theForm.mge_console_ip" id="mge_console_ip" cssClass="input-2" maxlength="30" disabled="true" ></s:textfield>
						</td>
						<td class="til">管理口用户 </td>
						<td>
							<s:textfield name="theForm.mge_console_username" id="mge_console_username" cssClass="input-2" maxlength="30" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">管理口密码 </td>
						<td>
							<s:password name="theForm.mge_console_pass" id="mge_console_pass" disabled="true" ></s:password>
						</td>
						<td class="til">管理口MAC</td>
						<td>
							<s:textfield name="theForm.mge_console_mac" id="mac" cssClass="input-2" maxlength="30" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">操作系统版本</td>
						<td>
							<s:textfield name="theForm.os_versions" id="os_versions" cssClass="input-2" maxlength="17" disabled="true" ></s:textfield>
						</td>
						<td class="til">操作系统位数</td>
						<td>
							<s:textfield name="theForm.os_digits" id="os_digits" cssClass="input-2" maxlength="17" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">raid信息</td>
						<td>
							<s:textfield name="theForm.raid_information" id="raid_information" cssClass="input-2" maxlength="17" disabled="true" ></s:textfield>
						</td>
						<td class="til">光纤卡</td>
						<td>
							<s:textfield name="theForm.fibercard" id="fibercard" cssClass="input-2" maxlength="17" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">共享存储</td>
						<td>
							<s:textfield name="theForm.shared_storage" id="shared_storage" cssClass="input-2" maxlength="17" disabled="true" ></s:textfield>
						</td>
						<td class="til">bond信息</td>
						<td>
							<s:textfield name="theForm.bond_information" id="bond_information" cssClass="input-2" maxlength="17" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">存放机房</td>
						<td>
							<s:select list="#{'1':'府青机房','2':'西区机房'}" name="theForm.stay_machroom" id="stay_machroom" cssClass="select-1" maxlength="16" disabled="true" />
						</td>
						<td class="til">主机物理位置 </td>
						<td>
							<s:textfield name="theForm.host_physical_position" id="mge_console_ip" cssClass="input-2" maxlength="30" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">刀片槽位 </td>
						<td>
							<s:textfield name="theForm.blade_groove" id="blade_groove" cssClass="input-2" maxlength="16" disabled="true" ></s:textfield>
						</td>
						<td class="til">描述</td>
						<td>
							<s:textfield name="theForm.install_desc" id="install_desc" cssClass="input-2" maxlength="17" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">所属资源池</td>
						<td>
							<s:textfield name="theForm.ownResPool" id="ownResPool" cssClass="input-2" maxlength="16" disabled="true" ></s:textfield>
						</td>
						<td class="til">业务系统</td>
						<td>
							<s:textfield name="theForm.bussName" id="bussName" cssClass="input-2" maxlength="17" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">主机名称</td>
						<td>
							<s:textfield name="theForm.host_name" id="host_name" cssClass="input-2" maxlength="16" disabled="true" ></s:textfield>
						</td>
						<td class="til">安装模版</td>
						<td>
						<s:select name="theForm.os_template" id="os_template" cssClass="select-1" disabled="true"
							listValue="templ_name " listKey="id " list="theForm.templList" headerKey="" headerValue="----请选择----"/>						
						</td>
					</tr>
					<%-- 
					<tr align="left">
						<td class="til">swap设置</td>
						<td>
						<s:select id="swap_type" name="theForm.swap_type" cssClass="select-1" headerValue="------请选择------" headerKey="" disabled="true"
							list="#{'1':'swap32G,1块盘（2块底层做1+0)','2':'swap32G,2块盘（4块底层做1+0)','3':'swap64G,1块盘（2块底层做1+0)','4':'swap64G,2块盘（4块底层做1+0)'}"></s:select>
						</td>
						<td></td>
						<td></td>
					</tr>
					--%>						
				</table>
			</div>			
		</div>
		
		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<div class="utt-2">网络</div>
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<thead>
						<tr>
							<th width="10%" onclick="sort(theTable,0,'string')">序号</th>
							<th width="20%" onclick="sort(theTable,1,'string')">MAC</th>
							<th width="20%" onclick="sort(theTable,2,'string')">IP地址</th>
							<th width="20%" onclick="sort(theTable,3,'string')">子网掩码</th>
							<th width="20%" onclick="sort(theTable,4,'string')">默认网关</th>
							<th width="10%" onclick="sort(theTable,5,'string')">备注</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.adptList" id="theBean">
						<tr>
							<td><s:property value="#theBean.nic_order" /></td>
							<td><s:property value="#theBean.mac" /></td>
							<td><s:property value="#theBean.ip_address" /></td>
							<td><s:property value="#theBean.subnet_mask" /></td>
							<td><s:property value="#theBean.default_gateway" /></td>
							<td><s:property value="#theBean.purpose" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="75%">Part</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">名称</th>
							<th width="20%" onclick="sort(theTable,1,'string')">类型</th>
							<th width="20%" onclick="sort(theTable,2,'string')">DISK</th>   
							<th width="10%" onclick="sort(theTable,3,'string')">大小</th>
							<th width="10%" onclick="sort(theTable,5,'string')">自增长</th>
							<th width="20%" onclick="sort(theTable,4,'string')">描述</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.partList" id="theBean">
						<tr>
							<td><s:property value="#theBean.part_name" /></td>							
							<td><s:property value="#theBean.part_fstype" /></td>
							<td><s:property value="#theBean.part_ondisk" /></td>
							<td><s:property value="#theBean.part_size" />GB</td>
							<td><s:if test="#theBean.part_grow==1">是</s:if><s:else>否</s:else></td>
							<td><s:property value="#theBean.part_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="80%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="75%">卷组</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">VG名称</th>
							<th width="20%" onclick="sort(theTable,1,'string')">PV编号</th>   
							<th width="20%" onclick="sort(theTable,2,'string')">容量</th>
							<th width="20%" onclick="sort(theTable,3,'string')">描述</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.vgList" id="theBean">
						<tr>
							<td><s:property value="#theBean.vg_name" /></td>
							<td><s:property value="#theBean.vg_pvno" /></td>
							<td><s:property value="#theBean.vg_pesize" />GB</td>
							<td><s:property value="#theBean.vg_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>	
								
		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="75%">文件系统</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">文件系统</th>
							<th width="20%" onclick="sort(theTable,1,'string')">卷组</th>
							<th width="20%" onclick="sort(theTable,2,'string')">逻辑卷</th>   
							<th width="20%" onclick="sort(theTable,3,'string')">容量</th>
							<th width="10%" onclick="sort(theTable,4,'string')">类型</th>
							<th width="10%" onclick="sort(theTable,5,'string')">描述</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.fsList" id="theBean">
						<tr>
							<td><s:property value="#theBean.filesys_name" /></td>
							<td><s:property value="#theBean.volume_group" /></td>
							<td><s:property value="#theBean.logical_volume" /></td>
							<td><s:property value="#theBean.filesys_size" />GB</td>
							<td><s:property value="#theBean.filesys_type" /></td>
							<td><s:property value="#theBean.filesys_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>		

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="60%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="75%">用户组</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">用户组ID</th>   
							<th width="20%" onclick="sort(theTable,1,'string')">用户组</th>
							<th width="20%" onclick="sort(theTable,2,'string')">描述</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.groupList" id="theBean">
						<tr>
							<td><s:property value="#theBean.group_id" /></td>
							<td><s:property value="#theBean.group_name" /></td>
							<td><s:property value="#theBean.group_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="100%">用户</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">ID</th>   
							<th width="20%" onclick="sort(theTable,1,'string')">用户名</th>
							<th width="20%" onclick="sort(theTable,2,'string')">主组</th>
							<th width="20%" onclick="sort(theTable,3,'string')">家目录</th>
							<th width="20%" onclick="sort(theTable,4,'string')">描述</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.userList" id="theBean">
						<tr>
							<td><s:property value="#theBean.user_id" /></td>
							<td><s:property value="#theBean.user_name" /></td>
							<td><s:property value="#theBean.primary_group" /></td>
							<td><s:property value="#theBean.home_dir" /></td>
							<td><s:property value="#theBean.user_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="60%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="50%">附加组</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">用户</th>
							<th width="20%" onclick="sort(theTable,1,'string')">附加组</th>
							<th width="20%" onclick="sort(theTable,2,'string')">描述</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.guList" id="theBean">
						<tr>
							<td><s:property value="#theBean.user_id" /></td>
							<td><s:property value="#theBean.group_id" /></td>
							<td><s:property value="#theBean.mask" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="60%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="50%">软件包</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">软件名称</th>
							<th width="20%" onclick="sort(theTable,1,'string')">类型</th>
							<th width="20%" onclick="sort(theTable,2,'string')">说明</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.softList" id="theBean">
						<tr>
							<td><s:property value="#theBean.soft_name" /></td>
							<td><s:property value="#theBean.soft_type" /></td>
							<td><s:property value="#theBean.soft_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>		
	</s:form>
</div>
</body>
</html:html>