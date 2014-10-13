<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
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
		var os = $("#os").val();//操作系统
		var cpu_num = $("#cpu_num").val();//CPU大小
		var mem_size = $("#mem_size").val();//内存大小
		var local_disk_size = $("#local_disk_size").val();//本地存储总大小
		var ip = $("#ip").val();//IP地址
		//var teg = /^(([0-9]+.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		if(os==""){
			alert("请填写操作系统");
			return false;
		}
		if(cpu_num==""){
			alert("请填写CPU大小");
			return false;
		}
		if(!isnumber(cpu_num)){
			alert("CPU大小只能为大于或等于0的数字");
			return false;
		}
		if(mem_size==""){
			alert("请填写内存大小");
			return false;
		}
		if(!isnumber(mem_size)){
			alert("内存大小只能为大于或等于0的数字");
			return false;
		}
		if(local_disk_size==""){
			alert("请填写本地存储总大小");
			return false;
		}
		if(!isnumber(local_disk_size)){
			alert("本地存储总大小只能为大于或等于0的数字");
			return false;
		}
		if(ip==""){
			alert("请填写IP地址");
			return false;
		}
	    if(!ipFormat(ip)){
   			alert("IP输入不合法!");
   			return false;
   		}
       var url = "paasHostInfo_saveHostInfo.do?"+$("#hostInfoObj").serialize();
       w.savePaashost(url);
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
				return false;
				break;
				}
			}
		}
    	return true;
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
	$(function(){
		var id = $("#id").val();
		if(id!=""){
			$("#label_name").attr("disabled",true);
			$("#service").attr("disabled",true);
		}
	});
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="paasHostInfo_saveHostInfo" method="post" id="hostInfoObj" >
		<s:hidden name="hostInfoObj.id" id="id" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" align="left">
						节点名字 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="hostInfoObj.label_name" id="label_name"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						主机名字 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="hostInfoObj.host_name" id="host_name"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						主机类型 <font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'0':'物理机','2':'虚拟机'}" name="hostInfoObj.host_type" id="host_type"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						服务器类型 <font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'0':'x86服务器','1':'机架服务器','2':'vmware虚拟机','3':'xen虚拟机','4':'kvm虚拟机','5':'其它'}" name="hostInfoObj.service_type" id="service_type"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						操作系统 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="hostInfoObj.os" id="os"></s:textfield>
					</td> 
				</tr>
				<tr>
					<td class="til" align="left">
						CPU大小(单位:个) <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="hostInfoObj.cpu_num" id="cpu_num"></s:textfield>
					</td> 
				</tr>
				<tr>
					<td class="til" align="left">
						内存大小(单位:M) <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="hostInfoObj.mem_size" id="mem_size"></s:textfield>
					</td> 
				</tr>
				<tr>
					<td class="til" align="left">
						本地存储总大小 (单位:M)<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="hostInfoObj.local_disk_size" id="local_disk_size"></s:textfield>
					</td> 
				</tr>
				<tr>
					<td class="til" align="left">
						IP地址 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="hostInfoObj.ip" id="ip"></s:textfield>
					</td> 
				</tr>
			</table>
	</s:form>
</body>
</html:html>
