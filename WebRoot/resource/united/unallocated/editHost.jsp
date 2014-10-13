<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	
	function submitRequest(){ 
		var eq_name = $("#eq_name").val();
		var eq_type = $("#eq_type").val();
		var eq_ip =  $("#eq_ip").val();
		var eq_hostname = $("#eq_hostname").val();
		var hasvertual= $("#hasvertual").val();
		var cq_id = $("#cq_id").val();
		var control = $("#control").val();
		var allocated = $("#allocated").val();
		var cpu_cl = $("#cpu_cl").val();
		var mem = $("#mem").val();
		var store = $("#store").val();
		var eq_id = $("#eq_id").val();
		var patrn = /^\d+$/;
	    if(eq_name==""){
	     alert("服务器名称不能为空！");
	     $("#eq_name").focus();
	     return false  ;
	    }   
	    if(eq_type==""){
	    	alert("服务器类型不能为空！");
	    	$("#eq_type").focus();
	     	return false  ;
	    }
	    if(eq_ip==""){
	     alert("服务器IP地址不能为空！");
	     $("#eq_ip").focus();
	     return false  ;
	    }
	    if(!ipFormat(eq_ip)){
   			alert("服务器IP输入不合法!");
   			$("#eq_ip").focus();
   			return false;
   		}
   		if(eq_hostname==""){
   			alert("服务器主机名称不能为空!");
   			$("#eq_hostname").focus();
   			return false;
   		}
   		if(hasvertual==""){
   			alert("请选择虚拟化类型!");
   			$("#hasvertual").focus();
   			return false;
   		}
   		if(cq_id==0){
   			alert("请选择机柜");
   			$("#cq_id").focus();
   			return false;
   		}
   		if(control== -1){
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
   		if(cpu_cl==""){
   			alert("CPU芯数不能为空！");
   			$("#cpu_cl").focus();
   			return false;
   		} 
   		if(!patrn.exec(cpu_cl)){
   			alert("CPU芯数必须为非负整数！");
			$("#cpu_cl").select();
			$("#cpu_cl").focus();
			return false;
		}
   		if(mem==""){
   			alert("内存不能为空！");
   			$("#mem").focus();
   			return false;
   		} 
   		if(!patrn.exec(mem)){
   			alert("内存必须为非负整数！");
			$("#mem").select();
			$("#mem").focus();
			return false;
		}
   		if(store==""){
   			alert("存储不能为空！");
   			$("#store").focus();
   			return false;
   		} 
		if(!patrn.exec(store)){
			alert("存储必须为非负整数！");
			$("#store").select();
			$("#store").focus();
			return false;
		}
   		if(allocated==''){
   			alert("请选择是否已分配！");
   			return false;
   		} 
    	var parent_id = $("#parent_id").val();
    	var url = "unitedDevice_saveHostDevice.do?"+$("#hostObj").serialize();
		w.saveHostDevice(url,parent_id);
	}
	 function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	/*判断服务器IP是否已生成*/
	function checkIpExists() {
	    var eq_ip = $("#eq_ip").val();
	    var eq_id = $("#eq_id").val();
		$("#span_key").html("");
		if (eq_ip.length>0 && ipFormat(eq_ip)) {
			var url = 'resource_checkExists.do?ip='+ eq_ip +'&eq_id='+eq_id; 
			$.getJSON(url,{'time':new Date().toString()},function(data){
				if(1==data.result){
					$("#span_key").html("服务器IP"+eq_ip+"已生成！请更改"); 
				}
				else if(0==data.result){
					$("#span_key").html("");
				}
			});
		}
	}
	/*判断服务器名称是否已生成*/
	function checkHostName() {
	  	var eq_name = $("#eq_name").val();
	  	var eq_id = $("#eq_id").val();
		$("#span_name").html("");
		if (eq_name.length>0) {
			var url = 'resource_checkNameExists.do?name='+ eq_name +'&eq_id='+eq_id; 
			$.getJSON(url,{'time':new Date().toString()},function(data){
				if(1==data.result){
					$("#span_name").html("服务器名称"+eq_name+"已生成！请更改"); 
				}else if(0==data.result){
					$("#span_name").html("");
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

</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="hostObj" >
		<s:hidden name="hostObj.eq_id" id="eq_id" />
		<s:hidden name="hostObj.flag" id="flag" />
		<s:hidden name="hostObj.hostflag" id="hostflag" />
		<s:hidden name="hostObj.HOST_POOL_ID" id="poolid" />
		<s:hidden name="obj.parent_id" id="parent_id"></s:hidden>
		<s:hidden name="obj.pratentId" id="pratentId"></s:hidden>
		<s:hidden name="obj.vtype" id="vtype"></s:hidden>
		<s:hidden name="obj.type" id="type"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						服务器名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="hostObj.eq_name"  id="eq_name" onblur="checkHostName()" />
						<span style="color:RED" id="span_name"/>    
					</td>
					<td class="til">
						服务器类型 <font color="red">*</font>
					</td>
					<td>
	                   <s:select name="hostObj.eq_type" id="eq_type" headerKey="" headerValue="请选择" list="#{'1':'IBM小机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器'}">
					  </s:select>
					</td>
				</tr>

				<tr>
				    <td class="til">
						服务器IP地址 <font color="red">*</font>
					</td>
					<td >
						<s:textfield name="hostObj.eq_ip" id="eq_ip" onblur="checkIpExists()"/>
						<span style="color:RED" id="span_key"/>    
					</td>
					<td class="til">
						服务器主机名称<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="hostObj.eq_hostname" id="eq_hostname"/>
					</td>
				</tr>
				<tr>
				    <td class="til">
						CPU芯数 <font color="red">*</font>
					</td>
					<td >
						 <s:textfield name="hostObj.cpu_cl" id="cpu_cl"/>
					</td>
					<td class="til">
						内存(G)<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="hostObj.mem" id="mem"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						存储(G) <font color="red">*</font>
					</td>
					<td >
						 <s:textfield name="hostObj.store" id="store"/>
					</td>
					<td class="til">是否已分配<font color="red">*</font>
					</td>
					<td>
						<s:select name="hostObj.allocated" headerKey="" headerValue="请选择" list="#{'0':'未分配','1':'已分配'}" id="allocated">
						</s:select>
					</td>
				</tr> 
				<tr>
				    <td class="til">
						虚拟化类型<font color="red">*</font>
					</td>
					<td>
						<s:select name="hostObj.hasvertual" id="hasvertual" headerKey="" headerValue="请选择" list="#{'0':'非虚拟化','1':'PowerVM','2':'KVM','3':'XEN','4':'VMWARE'}">
						</s:select>
					</td>
					<td class="til">
						所属机柜<font color="red">*</font>
					</td>
					<td>
                       <s:select list="hostObj.cubinetList" headerKey="0" headerValue="请选择" listKey="c_id" listValue="c_addr" name="hostObj.cq_id" id="cq_id">
	                   </s:select>
					</td>
				</tr>
				<tr>
					<td class="til">监控方式
					</td>
					<td>
						<s:select name="hostObj.PROTOCOL" headerKey="" headerValue="请选择" list="#{'ssh':'ssh','telnet':'telnet'}" id="PROTOCOL">
						</s:select>
					</td>
					<td class="til">云平台能否管控<font color="red">*</font>
					</td>
					<td>
						<s:select name="hostObj.control" headerKey="-1" headerValue="请选择" list="#{'0':'不能管控','1':'能管控'}" id="control">
						</s:select>
					</td>
				</tr> 
			</table>
	</s:form>
</body>
</html:html>
