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
		$(function(){
			$check = $(":checkbox");
			$check.unbind().live("click",function(){
				$check.not(this).attr("checked",false);
			});
		});	
		
	 	function addRequest() {
	 		$.dialog({
				id : 'customed_install_v',
				title : '用户配置',
				width : '680px',
				height : '530px',
				max : false,
				min : false,
				lock : true,
				content : 'url:osUser_addUser.do?theForm.os_host_id=' + theForm.os_host_id.value
			});
	 	}
	 	
		function modRequest() { 
			var couterNum = 0;
			var checkboxids = document.getElementsByName("checkboxid");
			if(checkboxids!=null&&checkboxids.length>0){
				for(var i=0;i<checkboxids.length;i++){
					if(checkboxids[i].checked){
						couterNum = couterNum + 1 ;
						theForm.id.value = checkboxids[i].value;
					}
				}
			}
			if(couterNum==0){
				alert("请勾选一条信息");
				return false ;
			}else if(couterNum>1){
				alert("一次只能修改一条信息");
				return false ;
			}
			alert('url:osUser_updateUser.do?theForm.id=' + theForm.id.value);
			$.dialog({
				id : 'customed_install_v',
				title : '用户配置',
				width : '680px',
				height : '530px',
				max : false,
				min : false,
				lock : true,
				content : 'url:osUser_updateUser.do?theForm.id=' + theForm.id.value
			});
		}
		
		function delRequest() {
			var couterNum = 0;
		    var checkboxids = document.getElementsByName("checkboxid");
		    if(checkboxids!=null&&checkboxids.length>0){
			    for(var i=0;i<checkboxids.length;i++){
					if(checkboxids[i].checked){
						couterNum = couterNum + 1 ;
						theForm.id.value = checkboxids[i].value;
					}
			    }
		    }
		    if(couterNum==0){
			    alert("请勾选一条信息");
			    return false ;
		    }else if(couterNum>1){
			    alert("一次只能删除一条信息");
			    return false ;
		    }
			if(confirm("确定要删除?")){
				$.ajax({
				type : 'post',
				url : 'osUser_deleteUser.do?theForm.id=' + theForm.id.value,
				success : function(msg) {
					if (msg == -1) {
						alert("保存失败");
					} else {
						reflush();
					}
				}
			});
			}
		}
		
		function nextStep(theForm){
			theForm.action = "osGroupUser_queryGroupUser.do";
			theForm.submit();
		}
		
		function _save_button_click_event(argForm){
			$.ajax({
				type : 'post',
				url : 'osUser_saveUser.do?' + argForm,
				success : function(msg) {
					if (msg == -1) {
						alert("保存失败");
					} else {
						reflush();
					}
				}
			});
		}
		function _update_button_click_event(argForm){
			$.ajax({
				type : 'post',
				url : 'osUser_modifyUser.do?' + argForm,
				success : function(msg) {
					if (msg == -1) {
						alert("保存失败");
					} else {
						reflush();
					}
				}
			});
		}
		
		function reflush(){
			theForm.action = "osUser_queryUser.do";
			theForm.submit();
		}
		function installFunction(){
			$.ajax({
				type:"POST",
	            url:"autoos_customedInstall.do",
	            data:$("#theForm").serialize(),
	            async: false,
	            cache: false,
		        success: function(msg){
		        	if(msg == 'OK'){
		        		var _iframe = window.parent.parent.document.getElementById("mainIframe");
						$(_iframe).attr( "src", "autoos_listOsHost.do");
		        	}else{
		        		alert(msg);
		        	}
	           	}
			});
		}
		function confirmFunction(){
			$.ajax({
				type:"POST",
	            url:"autoos_updateCustomedInstall.do",
	            data:$("#theForm").serialize(),
	            async: false,
	            cache: false,
		        success: function(msg){
		        	if(msg > 0){
		        		var _iframe = window.parent.parent.document.getElementById("mainIframe");
						$(_iframe).attr( "src", "autoos_listOsHost.do");
		        	}else if(msg < 0){
		        		alert("确认失败：数据更新出错。");
		        	}
	           	}
			});
		}
		function clearFunctin(){
			$.ajax({
				type:"POST",
	            url:"autoos_clearOsHostConfig.do",
	            data:$("#theForm").serialize(),
	            async: false,
	            cache: false,
		        success: function(msg){
		        	if(msg == 'OK'){
		        		var _iframe = window.parent.parent.document.getElementById("mainIframe");
						$(_iframe).attr( "src", "autoos_listOsHost.do");
		        	}else{
		        		alert(msg);
		        	}
	           	}
			});
		}
		function returnFunction(){
			var _iframe = window.parent.parent.document.getElementById("mainIframe");
			$(_iframe).attr( "src", "autoos_listOsHost.do");
		}				
	</script>
</head>
<body>
<div class="mainbody">
	<s:form action="autoos_listOsHost.do" method="post" cssStyle="theForm" id="theForm">
	<s:hidden name="theForm.install_state" id="install_state"></s:hidden>
	<s:hidden name="theForm.id" id="id"></s:hidden>
	<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
	<s:hidden name="theForm.os_host_id" id="os_host_id"></s:hidden>
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">详细信息
				<%-- 
				<s:select name="theForm.os_template" id="os_template" cssClass="select-1 vm"
					listValue="templ_name " listKey="id " list="theForm.templList" disabled="true"/>
				--%>
			</h2>
			<div class="bord-1 pd-10">
				<div class="utt-2">主机配置</div>
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<tr align="left">
						<td class="til">主机序列号</td>
						<td>
							<s:textfield name="theForm.serial_num" id="mge_console_ip" style="width:150px;height:20px;" maxlength="30" disabled="true" ></s:textfield>
						</td>
						<td class="til">主机型号</td>
						<td>
							<s:textfield name="theForm.host_type_num" id="mge_console_ip" style="width:150px;height:20px;" maxlength="30" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">IPMI管理口IP</td>
						<td>
							<s:textfield name="theForm.mge_console_ip" id="mge_console_ip" style="width:150px;height:20px;" maxlength="30" disabled="true" ></s:textfield>
						</td>
						<td class="til">管理口用户 </td>
						<td>
							<s:textfield name="theForm.mge_console_username" id="mge_console_username" style="width:150px;   height:20px;" maxlength="30" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">管理口MAC</td>
						<td>
							<s:textfield name="theForm.mge_console_mac" id="mac" style="width:150px;   height:20px;" maxlength="30" disabled="true" ></s:textfield>
						</td>					
						<td class="til">管理口密码 </td>
						<td>
							<s:password name="theForm.mge_console_pass" id="mge_console_pass" style="width:150px;   height:20px;" maxlength="16" disabled="true" ></s:password>
						</td>
					</tr>
					<tr align="left">
						<td class="til">存放机房</td>
						<td>
							<s:select list="#{'1':'府青机房','2':'西区机房'}" name="theForm.stay_machroom" id="stay_machroom"  cssClass="select-1 vm" maxlength="16" disabled="true" />
						</td>
						<td class="til">主机物理位置 </td>
						<td>
							<s:textfield name="theForm.host_physical_position" id="mge_console_ip" style="width:150px;height:20px;" maxlength="30" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">所属资源池</td>
						<td>
							<s:textfield name="theForm.ownResPool" id="ownResPool" style="width:150px;   height:20px;" maxlength="16" disabled="true" ></s:textfield>
						</td>
						<td class="til">业务系统</td>
						<td>
							<s:textfield name="theForm.bussName" id="bussName" style="width:150px;   height:20px;" maxlength="17" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">主机名称</td>
						<td>
							<s:textfield name="theForm.host_name" id="host_name" style="width:150px;   height:20px;" maxlength="16" disabled="true" ></s:textfield>
						</td>
						<td class="til">安装模版</td>
						<td>
						<s:select name="theForm.os_template" id="os_template" cssClass="select-1 vm" disabled="true"
							listValue="templ_name " listKey="id " list="theForm.templList" headerKey="" headerValue="----请选择----"/>						
						</td>
					</tr>
					<%-- 
					<tr align="left">
						<td class="til">swap设置</td>
						<td colspan="3">
						<s:select id="swap_type" name="theForm.swap_type" cssClass="select-1 vm" headerValue="------请选择------" headerKey="" disabled="true"
							list="#{'1':'swap32G,1块盘（2块底层做1+0)','2':'swap32G,2块盘（4块底层做1+0)','3':'swap64G,1块盘（2块底层做1+0)','4':'swap64G,2块盘（4块底层做1+0)'}"></s:select>
						</td>
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
							<th width="10%" onclick="sort(theTable,4,'string')">自增长</th>
							<th width="10%" onclick="sort(theTable,5,'string')">描述</th>
							<th width="10%" onclick="sort(theTable,6,'string')">来源</th>	
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
							<td><s:if test="#theBean.flag==1">模版</s:if></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="75%">卷组</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">VG名称</th>
							<th width="20%" onclick="sort(theTable,1,'string')">PV编号</th>   
							<th width="20%" onclick="sort(theTable,2,'string')">容量</th>
							<th width="20%" onclick="sort(theTable,3,'string')">描述</th>	
							<th width="20%" onclick="sort(theTable,4,'string')">来源</th>						
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.vgList" id="theBean">
						<tr>
							<td><s:property value="#theBean.vg_name" /></td>
							<td><s:property value="#theBean.vg_pvno" /></td>
							<td><s:property value="#theBean.vg_pesize" />GB</td>
							<td><s:property value="#theBean.vg_desc" /></td>
							<td><s:if test="#theBean.flag==1">模版</s:if></td>
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
							<th width="10%" onclick="sort(theTable,3,'string')">容量</th>
							<th width="10%" onclick="sort(theTable,4,'string')">类型</th>
							<th width="10%" onclick="sort(theTable,5,'string')">描述</th>
							<th width="10%" onclick="sort(theTable,6,'string')">来源</th>
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
							<td><s:if test="#theBean.flag==1">模版</s:if></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>		

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="80%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="75%">用户组</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">用户组ID</th>   
							<th width="20%" onclick="sort(theTable,1,'string')">用户组</th>
							<th width="20%" onclick="sort(theTable,2,'string')">描述</th>
							<th width="20%" onclick="sort(theTable,3,'string')">来源</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.groupList" id="theBean">
						<tr>
							<td><s:property value="#theBean.group_id" /></td>
							<td><s:property value="#theBean.group_name" /></td>
							<td><s:property value="#theBean.group_desc" /></td>
							<td><s:if test="#theBean.flag==1">模版</s:if></td>
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
							<th width="10%" onclick="sort(theTable,4,'string')">描述</th>	
							<th width="10%" onclick="sort(theTable,5,'string')">来源</th>								
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
							<td><s:if test="#theBean.flag==1">模版</s:if></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="80%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="50%">附加组</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">用户</th>
							<th width="20%" onclick="sort(theTable,1,'string')">附加组</th>
							<th width="20%" onclick="sort(theTable,2,'string')">描述</th>
							<th width="20%" onclick="sort(theTable,3,'string')">来源</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.guList" id="theBean">
						<tr>
							<td><s:property value="#theBean.user_id" /></td>
							<td><s:property value="#theBean.group_id" /></td>
							<td><s:property value="#theBean.mask" /></td>
							<td><s:if test="#theBean.flag==1">模版</s:if></td>							
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="80%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="50%">软件包</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">软件名称</th>
							<th width="20%" onclick="sort(theTable,1,'string')">类型</th>
							<th width="20%" onclick="sort(theTable,2,'string')">说明</th>
							<th width="20%" onclick="sort(theTable,3,'string')">来源</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.softList" id="theBean">
						<tr>
							<td><s:property value="#theBean.soft_name" /></td>
							<td><s:property value="#theBean.soft_type" /></td>
							<td><s:property value="#theBean.soft_desc" /></td>
							<td><s:if test="#theBean.flag==1">模版</s:if></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>
				
		<div class="footbuttons mgb-20" align="center"><!-- 分页 -->
			<s:if test="theForm.install_state==4||theForm.install_state==3||theForm.install_state==0">			
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:installFunction(document.getElementById('theForm'))" value="安装" /></span>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:confirmFunction(document.getElementById('theForm'))" value="确认" /></span>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:clearFunctin(document.getElementById('theForm'))" value="清除配置" /></span>
			</s:if>
			<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:returnFunction(document.getElementById('theForm'))" value="返回" /></span>
		</div>
	</s:form>
</div>
</body>
</html:html>
