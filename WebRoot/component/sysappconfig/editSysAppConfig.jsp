<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<head>
<title></title>
<script type="text/javascript">
		var api = frameElement.api;
		var w = api.opener;
	    $(function(){
	    	api.button({
	    			id:'OkAnd',
	    			name:'确定',
	    			callback:createForm,
	    			focus:true
	    		},{
	    			id:'cancel',
	    			name:'取消'
	    	});
	    });
	    function createForm(){
	    	var sys_order =$("#sys_order").val(); 
			var jdk_order = $("#jdk_order").val();
			var user_order = $("#user_order").val();
			var middleware_order = $("#middleware_order").val();
			var cpuuse_order = $("#cpuuse_order").val();
			var memoryuse_order = $("#memoryuse_order").val();
			var heapuse_order = $("#heapuse_order").val();
			var port_order = $("#port_order").val();
			var server_order = $("#server_order").val();
			var config_order = $("#config_order").val();
			var heapleft_order = $("#heapleft_order").val();
			var pathauthority = $("#pathauthority").val();
			var netio_order = $("#netio_order").val();
			
			if(sys_order==null || sys_order==''){
				alert('操作系统不能为空');
					return false;
			}
			if(jdk_order==null || jdk_order ==''){
				alert('jdk不能为空');
					return false;
			}
			if(user_order==null || user_order ==''){
				alert('用户不能为空');
					return false;
			}
			if(middleware_order==null || middleware_order ==''){
				alert('中间件不能为空');
					return false;
			}
			
			var reg_use= /^(?:[1-9]|[1-9][0-9]?)$/;
			if(!reg_use.test(cpuuse_order)){ 
				alert("cpu利用率只能介于0-100之间");
				return false;
			} 
			if(!reg_use.test(memoryuse_order)){ 
				alert("内存利用率只能介于0-100之间");
				return false;
			} 
			if(!reg_use.test(heapuse_order)){ 
				alert("存储利用率只能介于0-100之间");
				return false;
			} 
			if(port_order==null || port_order ==''){
				alert('端口要求不能为空');
					return false;
			}
			if(server_order==null || server_order ==''){
				alert('服务要求不能为空');
					return false;
			}
			if(config_order==null || config_order ==''){
				alert('配置要求不能为空');
					return false;
			}
			if(heapleft_order==null || heapleft_order ==''){
				alert('剩余存储不能为空');
					return false;
			}
			if(pathauthority==null || pathauthority ==''){
				alert('部署路径权限要求不能为空');
					return false;
			}
			if(netio_order==null || netio_order ==''){
				alert('网络IO要求不能为空');
					return false;
			}
	    	w.saveTheForm($("#theForm").serialize());
	    }
	</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<s:hidden name="oper" id="oper"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til" colspan="1">
					基准应用名称
					<font color="red">*</font>
				</td>
				<td colspan="3">
					<s:if test="oper == 'add'">
						<s:select list="appList" listKey="id" listValue="appname" name="sysAppConfigObj.CONFIG_ID" id="appid"></s:select>
					</s:if>
					<s:elseif test="oper == 'edit'">
						<s:hidden name="sysAppConfigObj.CONFIG_ID"></s:hidden>
						<s:textfield name="sysAppConfigObj.APPNAME" disabled="true"></s:textfield>
					</s:elseif>
				</td>
			</tr>
			<tr>
				<td class="til">
					操作系统要求
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="sysAppConfigObj.SYS_ORDER" id="sys_order" ></s:textfield>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.SYS_ORDER_FLAG" id="sysorder_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					JDK要求
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="sysAppConfigObj.JDK_ORDER" id="jdk_order"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.JDK_ORDER_FLAG" id="jdk_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					用户要求
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="sysAppConfigObj.USER_ORDER" id="user_order"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.USER_ORDER_FLAG" id="user_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					中间件要求
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="sysAppConfigObj.MIDDLEWARE_ORDER" id="middleware_order" />
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.MIDDLEWARE_ORDER_FLAG" id="middle_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					CPU利用率要求(低于)
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="sysAppConfigObj.CPUUSE_ORDER" id="cpuuse_order"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.CPUUSE_ORDER_FLAG" id="cpuuse_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					内存利用率要求(低于)
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="sysAppConfigObj.MEMORYUSE_ORDER" id="memoryuse_order"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.MEMORYUSE_ORDER_FLAG" id="memo_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					存储利用率要求(低于)
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="sysAppConfigObj.HEAPUSE_ORDER" id="heapuse_order"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.HEAPUSE_ORDER_FLAG" id="heapuse_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">端口要求<font color="red">*</font></td>
				<td><s:textfield name="sysAppConfigObj.PORT_ORDER" id="port_order"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.PORT_FLAG" id="port_flag" ></s:select>
				</td>
			</tr>
				
			<tr>
				<td class="til">服务要求<font color="red">*</font></td>
				<td><s:textfield name="sysAppConfigObj.SERVER_ORDER" id="server_order"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.SERVER_FLAG" id="server_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">部署配置要求<font color="red">*</font></td>
				<td><s:textfield name="sysAppConfigObj.CONFIG_ORDER" id="config_order"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.CONFIG_FLAG" id="config_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">剩余存储大小<font color="red">*</font></td>
				<td><s:textfield name="sysAppConfigObj.HEAPLEFT_ORDER" id="heapleft_order"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.HEAPLEFT_FLAG" id="heapleft_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">部署路径权限检测<font color="red">*</font></td>
				<td><s:textfield name="sysAppConfigObj.PATHAUTHORITY" id="pathauthority"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.PATH_FLAG" id="path_flag" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">部署机网络IO要求<font color="red">*</font></td>
				<td><s:textfield name="sysAppConfigObj.NETIO_ORDER" id="netio_order"/>
				</td>
				<td class="til">
					部署前是否检测
				</td>
				<td>
					<s:select list="#{'1':'检测','0':'不检测'}" name="sysAppConfigObj.NET_FLAG" id="net_flag" ></s:select>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html:html>