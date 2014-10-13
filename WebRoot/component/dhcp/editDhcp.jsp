<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<head>
<title></title>

<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		var ip = $("#ip");
		ip.get(0).options.add(new Option("--请先选择ip池--", "-1"));
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createdhcp,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function createdhcp(){
		var ipPool = $("#ipPool").val();
		if(ipPool == -1 ){
			alert("请选择ip池!");
			return false;
		}
		var macAddress = $("#macAddress").val();
		if (macAddress == null || macAddress == "") {
			alert("macAddress不能为空");
			return false;
		}
		
		var reg_mac=/[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}/
		if(!reg_mac.test(macAddress)){ 
			alert("mac地址格式不正确，正确格式如：00:24:21:19:BD:E4");
			return false;
		}
		w.saveDhcp($("#theForm").serialize());
	}
	function getIPList(){
		var netId = $("#ipPool").val();
		var ip = $("#ip");
		ip.empty();
		mask('IP查询中...','0.7','0');
		$.ajax({
		         type: "get",
				 async: true,
		         url: "dhcp_getIpListByNet.do?netId="+netId+"&time"+new Date(),
		         dataType: "json",
		         success : function(data){
					for(var i =0 ;i<data.length ;i++){
						var text=data[i].IPADDRESS;
						var value=data[i].IPADDRESS;
						ip.get(0).options.add(new Option(text, value));
					}
					removeMask();
		        }
		});
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="dhcp_saveDhcp.do" method="post" id="theForm">
	<s:hidden name="dhcpObj.ID"></s:hidden>
	<s:hidden name="oper" id="oper"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
		<s:if test="oper=='add'">
			    <tr>
					<td class="til">IP池<font color="red">*</font>
					</td>
					<td>
						<s:select id="ipPool" list="ipPoolList" listKey="NET_ID" listValue="NAME" headerKey="-1" headerValue="--请选择--" name="dhcpObj.NetId" onchange="getIPList()"></s:select>
					</td>
				</tr>
		</s:if>
			<tr>
				<td class="til">IP地址 <font color="red">*</font>
				</td>
				<td>
					<s:if test="oper=='add'">
						<select name="dhcpObj.IP" id="ip" ></select>
					</s:if>
					<s:elseif test="oper=='edit'">
						<s:textfield name="dhcpObj.IP" readonly="true">
						</s:textfield>
					</s:elseif>
				</td>
				</tr>
				<tr>
				<td class="til">IP状态</td>
				<td>
					<s:select list="#{'0':'未使用','1':'已使用'}" name="dhcpObj.IPSTATUS" id="ipstatus" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">macAddress<font color="red">*</font>
				</td>
				<td><s:textfield name="dhcpObj.MACADDRESS" id="macAddress"
						maxlength="50" />
				</td>
				</tr>
				<tr>
				<td class="til">描述</td>
				<td><s:textarea name="dhcpObj.DESCR" id="descr" cols="35"/>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html:html>
