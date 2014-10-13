<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
    api.button({
		id:'OkAnd',
		name: '确定',
		type: 'submit',
		callback:submitRequest,
		focus: true
	},
	{
    	id:'cancle',
		name: '取消'
	}); 
	
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(){ 
	    if(theForm.eq_name.value.length ==0){
	     alert("服务器名称不能为空！");
	     $("#eq_name").focus();
	     return false  ;
	    }   
	    if(theForm.eq_type.value.length ==0){
	    	alert("服务器类型不能为空！");
	    	$("#eq_type").focus();
	     	return false  ;
	    }
	    if(theForm.eq_ip.value.length ==0){
	     alert("服务器IP地址不能为空！");
	     $("#eq_ip").focus();
	     return false  ;
	    }
	    var ip=theForm.eq_ip.value;
	    if(!ipFormat(ip)){
   			alert("服务器IP输入不合法!");
   			$("#eq_ip").focus();
   			return false;
   		}
   		if(theForm.eq_hostname.value.length==0){
   			alert("服务器主机名称不能为空!");
   			$("#eq_hostname").focus();
   			return false;
   		}
   		if(theForm.cpu_cl.value.length==0){
   			alert("服务器主机CPU芯数不能为空!");
   			$("#eq_hostname").focus();
   			return false;
   		}
   		if(theForm.mem.value.length==0){
   			alert("服务器主机内存不能为空!");
   			$("#eq_hostname").focus();
   			return false;
   		}
   		if(theForm.store.value.length==0){
   			alert("服务器主机存储不能为空!");
   			$("#eq_hostname").focus();
   			return false;
   		}
   		if(theForm.hasvertual.value.length==0){
   			alert("请选择虚拟化类型!");
   			$("#hasvertual").focus();
   			return false;
   		}
   		if(theForm.cq_id.value==0){
   			alert("请选择机柜");
   			$("#cq_id").focus();
   			return false;
   		}
   		if(theForm.control.value==-1){
   			alert("请选择云平台是否可以管控");
   			return false;
   		} 
   		
   		if(Trim(document.getElementById("span_key").innerHTML).length > 0) {
   			alert("请重新输入服务器IP地址！");
   			$("#eq_ip").focus();
   			return false;
   		} 
   		if(Trim(document.getElementById("span_name").innerHTML).length > 0) {
   			alert("请重新输入服务器名称！");
   			$("#eq_name").focus();
   			return false;
   		}
   		if(theForm.allocated.value==''){
   			alert("请选择是否已分配！");
   			return false;
   		} 
   	    if (theForm.hostflag.value=='allhost'){
	    	if (theForm.flag.value=='hostpool'){
	    		var poolid=theForm.poolid.value;
	       		var url = "resource_saveHost.do?"+$("#theForm").serialize();
	       		api.get("listallhost").saveAllhost(url,poolid,'hostpool');
	    	}else{
	       		var url = "resource_saveHost.do?"+$("#theForm").serialize()+"&"+$("#snmp").serialize()+"&"+$("#protocol").serialize();
	       		w.saveAllhost1(url);
	    	}
	    }else{
	    	var url = "resource_saveHost.do?"+$("#theForm").serialize();
			w.savehost(url);
	    }
	}
	
	/* 检测输入的字符串是否符合要求 */
	function isnumber(str)
	 {
	  var number_chars = "1234567890.";
	        var i;
	        for (i=0;i<str.length;i++)
	   {
	            if (number_chars.indexOf(str.charAt(i))==-1) return false;
	        }
	        return true;
	}
	/*判断服务器IP是否已生成*/
	function checkIpExists() {
		var ip=Trim(theForm.eq_ip.value);
		var eid=theForm.eq_id.value;
		document.getElementById("span_key").innerHTML="";
		if (ip.length > 0 && ipFormat(ip)) {
			var url = 'resource_checkIpExists.do?ip='+ ip + '&eq_id=' +eid + '&flag=ip'; 
			$.getJSON(url,function(data){
				if(1==data.result){
					document.getElementById("span_key").innerHTML="服务器IP"+ip+"已生成！请更改"; 
				}
			});
		}
	}
	/*判断服务器名称是否已生成*/
	function checkHostName() {
		var name=Trim(theForm.eq_name.value);
		var eid=theForm.eq_id.value;
		document.getElementById("span_name").innerHTML="";
		if (name.length > 0) {
			var url = 'resource_checkIpExists.do?name='+ name + '&eq_id=' +eid +'&flag=name'; 
			$.getJSON(url,function(data){
				if(1==data.result){
					document.getElementById("span_name").innerHTML="服务器名称"+name+"已生成！请更改"; 
				}
			});
		}
	}
    /* 检测字符串是否为IP地址 */
	function ipFormat(str)	{
		var re=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	  	if (str.match(re)== null){
      		return false;
    	}else{
    		var stn = str.split(".");
    		var re1=/^0\d|0\d\d|00\d$/;
			for(var i=0;i<4;i++){
				if(stn[i].match(re1)!= null){
				//alert("is 0");
				return false;
				break;
				}
			}
		}
    	return true;
    }
    function showParam(){
    	var proto=$("#PROTOCOL").val();
    	if(proto!='snmp'){
    		$("#snmp").hide();
    	}else{
    		$("#snmp").show();
    	}
    }
 function pageOnLoad(){
	 showParam();
 }
</script>
</head>
<body class="pop-body scrollbody" onload="pageOnLoad()">
	<s:form action="resource_saveHost" method="post" id="theForm" >
		<s:hidden name="theForm.eq_id" id="eq_id" />
		<s:hidden name="theForm.flag" id="flag" />
		<s:hidden name="theForm.hostflag" id="hostflag" />
		<s:hidden name="theForm.HOST_POOL_ID" id="poolid" />
		<s:hidden name="theForm.h_uuid" id="h_uuid" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<div class="tit-zzi">
			<div id="zi">
				基本信息
			</div>
				<tr>
					<td class="til">
						服务器名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.eq_name"  id="eq_name" onblur="checkHostName()"/>
						<span style="color:RED" id="span_name"/>    
					</td>
					<td class="til">
						服务器类型 <font color="red">*</font>
					</td>
					<td>
	                   <s:select name="theForm.eq_type" id="eq_type" headerKey="" headerValue="请选择" list="#{'1':'IBM小机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器','6':'华为服务器'}">
								</s:select>
					</td>
				</tr>

				<tr>
				    <td class="til">
						服务器IP地址 <font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.eq_ip" id="eq_ip" onblur="checkIpExists()"/>
						<span style="color:RED" id="span_key"/>    
					</td>
					<td class="til">
						服务器主机名称<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.eq_hostname" id="eq_hostname"/>
					</td>
				</tr>
				
				<tr>
				    <td class="til">
						CPU核数 <font color="red">*</font>
					</td>
					<td >
						 <s:textfield name="theForm.cpu_cl" onkeyup="value=value.replace(/[^\d]/g,'')" id="cpu_cl"/>
					</td>
					<td class="til">
						内存(G)<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.mem" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" id="mem"/>
					</td>
				</tr>
				<tr>
				    <td class="til">
						存储(G) <font color="red">*</font>
					</td>
					<td >
						 <s:textfield name="theForm.store" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" id="store"/>
					</td>
					<td class="til">是否已分配<font color="red">*</font>
					</td>
					<td>
						<s:select name="theForm.allocated" headerKey="" headerValue="请选择" list="#{'0':'未分配','1':'已分配'}" id="allocated">
						</s:select>
					</td>
				</tr>
				
				<tr>
				    <td class="til">
						虚拟化类型<font color="red">*</font>
					</td>
					<td>
						<s:select name="theForm.hasvertual" id="hasvertual" headerKey="" headerValue="请选择" list="#{'0':'非虚拟化','1':'PowerVM','2':'KVM','3':'XEN','4':'VMWARE'}">
						</s:select>
					</td>
					<td class="til">
						所属机柜<font color="red">*</font>
					</td>
					<td>
                       <s:select list="#{'1_01_001_0005':'C12','1_01_001_0004':'C13','1_01_001_0003':'C14','1_01_001_0002':'C15','1_01_001_0001':'C16'}" headerKey="0" headerValue="请选择" name="theForm.cq_id" id="cq_id">
	                   </s:select>
					</td>
				</tr>
				<tr>
					<td class="til">监控方式
					</td>
					<td>
						<s:select name="theForm.PROTOCOL" headerKey="" headerValue="请选择" list="#{'ssh':'ssh','telnet':'telnet','snmp':'snmp'}" id="PROTOCOL" onchange="showParam()">
						</s:select>
					</td>
					<td class="til">云平台能否管控<font color="red">*</font>
					</td>
					<td>
						<s:select name="theForm.control" headerKey="-1" headerValue="请选择" list="#{'1':'能管控','0':'不能管控'}" id="control">
						</s:select>
					</td>
				</tr> 
				<tr>
					<td class="til">厂商
					</td>
					<td>
						<s:textfield name="theForm.MODEL" id="MODEL" >
						</s:textfield>
					</td>
					<td class="til">
					</td>
					<td>
						
					</td>
				</tr> 
			</table>
			</div>
			<div>
			</div>
			</s:form>
			<s:form action="resource_saveHost" method="post" id="snmp"  >
				<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<div class="tit-zzi">
					<div id="zi">
						SNMP接入参数
					</div>
						<tr>
						    <td class="til">
								版本<font color="red">*</font>
							</td>
							<td >
								<s:select list="#{'v1':'v1','v2c':'v2c','v3':'v3'}" name="snmp.version" id="version" ></s:select>
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								IP <font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.ip" id="ip"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								端口<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.port" id="port" />
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								超时时间<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.time_out" id="time_out"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								重试次数<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.retry_nums" id="retry_nums"/>
								<span style="color:RED" id="span_key"/>    
							</td>
							<!-- 
							<td class="til">
								SMM1 IP地址<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.smm1_ip" id="smm1_ip"/>
							</td>
							 -->
							 <td class="til">
								共同体名<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.community" id="community"/>
							</td>
						</tr>
						<!-- 
						    <td class="til">
								SMM2 IP地址<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.smm2_ip" id="smm2_ip" />
								<span style="color:RED" id="span_key"/>    
							</td>
						 -->
						<!-- 
						<tr>
						    <td class="til">
								安全模式<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.security_mode" id="security_mode" />
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								安全名称<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.security_name" id="security_name"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								鉴权算法<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.indentity_metic" id="indentity_metic" />
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								鉴权密码<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.indentity_password" id="indentity_password"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								加密算法<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.encrypt_metic" id="encrypt_metic"/>
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								加密密码<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.encrypt_password" id="encrypt_password"/>
							</td>
						</tr>
						 -->
					</div>
				</table>
			</s:form>
			<s:form action="resource_saveHost" method="post" id="protocol" >
				
		</s:form>	
	
</body>
</html:html>
