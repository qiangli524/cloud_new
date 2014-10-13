<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.config.Constant"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />
<meta http-equiv="expires" content="0" />
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/js/formvalidate/validate.css"  type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dataformat/jquery.formDataOper.js"></script>
<script type="text/javascript">
	
	 
	var api = frameElement.api;
	var w = api.opener;

	api.button({
		id : 'Ok',
		name : '创建',
		callback : add,
		focus : true
	}, {
		id : 'cancle',
		name : '取消'
	});

	var storageOld;
	$(function() {
		// 把存储的值放到隐藏域中 ,判断 修改后的值是否小于初始值。小于创建失败@yanggl
		$("#storage").attr("value", $("#storageSizeInMb").val());
		storageOld = $("#storage").val();
	});
	//创建虚拟机 
	function add() {
		var flag = false;
		var name = $("#newVmName").val();
		var parent_uuid = '<s:property value="parent_uuid" />';
		var dns = $("#dns").val();
		var vtype = '<s:property value="vtype" />';
		var connect_id = '<s:property value="connect_id" />';
		var storageNew = $("#storageSizeInMb").val();
		var srUuid = $("#srUuid").val();//存储

		var netWay = '';
		var net_id = '';
		var ips = '';
		//获取多网卡网络ID
		$("[id^=net_id]").each(function(i, netid) {
			net_id += netid.value + ',';
		});
		//获取多网卡网络ID
		$("input:radio[name^=netType]:checked").each(function(i, nettype) {
			netWay += nettype.value + ',';
		});
		//获取多网卡网络ID
		$("[id^=ip]").each(function(i, ipid) {
			ips += ipid.value + ',';
		});

		if (vtype == 1) {
			//添加IP地址和主机名称必填校验@yanggl
			if (dns == '') {
				alert('虚拟机主机名称不能为空！');
				return false;
			}
			//验证内存必须为4的倍数
			var mem = $("#memoryMB").val();
			var unit = $("#unit").val();
			if (unit != 2) {
				if (mem % 4 != 0) {
					alert('内存大小必须为4的倍数');
					return false;
				}
			} else {
				mem = mem * 1024;
				if (mem % 4 != 0) {
					alert('内存大小必须为4的倍数');
					return false;
				}
			}
			//存储只能增加，不能减小@yanggl
			if (storageOld > storageNew) {
				alert("存储只能增加，不能减小");
				$("#storageSizeInMb").attr("value", storageOld);
				return false;
			}

			var checked = $('input:radio[name=netType]:checked').val();
			if (checked == 1) {
				if (ip == '') {
					alert('请选择IP地址！');
					return false;
				}
			}
			var str = $("#virtualMachineUnitedVO").serialize() + "&memUnit="
					+ unit + "&netWay=" + netWay + "&net_id=" + net_id
					+ "&ips=" + ips;
		} else {
			if (name == "") {
				alert("虚拟机名字不能为空");
				return false;
			}
			if (srUuid == "") {
				alert("请选择存储");
				return false;
			}
			var str = $("#virtualMachineUnitedVO").serialize();
		}
		
		var mark = validateName(name, vtype);
		if (mark == true) {
			w.cloneVM(parent_uuid, vtype, connect_id, str);
		} else {
			return false;
		}
	}
	//获取存储信息
	function getDSInfo() {
		var connect_id = '<s:property value="connect_id"/>';
		var vtype = '<s:property value="vtype"/>';
		var store_uuid = $("#datastoreCode").val();
		//存储名称
		///var dsname = $("#datastoreName").find("option:selected").text();
		$.getJSON("united_getStorageDetail.do?connect_id=" + connect_id + '&store_uuid=' + store_uuid + "&vtype=" + vtype, 
	    {'time' : new Date().toString()}, 
	    function(data) {
			$("#dsinfo").html(
					 '<span class="blue">总量：'+data[0].CAPACITY+'G<span class="mgl-10">余量：'+data[0].FREE_SPACE+'G</span></span>');
		});
	}

	//获取网络信息
	function getNETSelect(num) {
		var netType = $("input[name='netType" + num + "']:checked").val();
		if (netType == 1) {
			$("#IP" + num).show();
			$("#net" + num).show();
			$("#vlan" + num).show();
		} else {
			$("#net" + num).show();
			$("#vlan" + num).show();
			$("#IP" + num).hide();
		}
		$("#ip" + num).attr('value', '');
	}

	//进入界面实例vlan列表
	function initVlan(nic_count) {
		for ( var i = 1; i <= nic_count; i++) {
			getSubDomain(i);
		}
	}

	//根据主机获取对应的存储列表
	function createXmlHttp() {
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	var check = '';
	function createSelect(value, text) {
		var opt = document.createElement("option");
		opt.setAttribute("value", value);
		opt.appendChild(document.createTextNode(text));
		if (check == value) {
			opt.selected = true;
		}
		return opt;
	}
	function getHostStorageList() {
		createXmlHttp();
		var hostCode = $("#hostCode").val();
		xmlHttp.open("GET", "vmw_getHostStorageList.do?HOST_ID=" + hostCode,
				false);
		xmlHttp.setRequestHeader("If-Modified-Since", "0");
		xmlHttp.send(null);
		if (xmlHttp.readyState == 4) {
			var pageInfo = eval("(" + xmlHttp.responseText + ")");
			var SelectNode = document.all.datastoreCode;
			SelectNode.length = 0;
			for ( var o in pageInfo) {
				SelectNode.appendChild(createSelect(o, pageInfo[o]));
			}
		}
	}

	///根据子网路域获取对应的vlan
	function getSubDomain(num) {
		var domainid = $("#subdomain" + num).val();
		$.ajax({
			type : 'GET',
			dataType : "json",
			url : 'workorder/workorder_selectVlan.do?domainid=' + domainid,
			async : false,
			cache : false,
			success : function(msg) {
				if (msg == -1) {
					alert("该网络域下没有VLAN,请重新选择");
				} else {
					$("#net_id" + num).empty();
					for ( var i = 0; i < msg.length; i++) {
						$(
								"<option value="+msg[i].NET_ID+">"
										+ msg[i].NAME + "</option>").appendTo(
								"#net_id" + num);
					}
				}
			}
		});
	}
	///通过vlan获取对应的IP地址列表
	function selectIPByVlan(num) {
		var vlan_id = $("#net_id" + num).val();
		w.$.dialog({
			id : 'addIP1',
			title : '选择IP',
			width : '750px',
			height : '470px',
			max : false,
			min : false,
			lock : true,
			content : 'url:united_selectIpByVlan.do?uuid=' + vlan_id + "&name="
					+ 'cloneVM' + "&vm_num=" + num
		});
	}
	//选择IP地址
	function selectip(ip, vm_num) {
		$("#ip" + vm_num).attr("value", ip);
	}
	/*
	 *显示不同网卡网络配置界面
	 */
	function getNicNetSelect(nic_count, nicnum) {
		var netType = $("input[name='netType" + nicnum + "']:checked").val();
		for ( var i = 1; i <= nic_count; i++) {
			if (i == nicnum) {
				if (netType == 1) {
					$("#net_auto" + nicnum).show();
					$("#net" + nicnum).show();
					$("#vlan" + nicnum).show();
					$("#IP" + nicnum).show();
				} else {
					$("#net_auto" + nicnum).show();
					$("#net" + nicnum).show();
					$("#vlan" + nicnum).show();
					$("#IP" + nicnum).hide();
				}
			} else {
				$("#net_auto" + i).hide();
				$("#net" + i).hide();
				$("#vlan" + i).hide();
				$("#IP" + i).hide();
			}
		}
	}

	function initNetHtml(nic_count) {
		var nic_list = 
			'<tr id="nic_count">'
			+	'<td class="til" width="15%">'
			+		'网卡清单'
			+	'</td>'
			+	'<td>';
		for (var i=0;i<nic_count;i++) {
			var nic_radio = '';
			if (i == 0) {
				nic_radio = '<input type="radio" name="vm_nic" id="vm_nic'+(i+1)+'" checked="checked" value="'+(i+1)+'" onclick="getNicNetSelect('+nic_count+','+(i+1)+');"/><label for="vm_nic'+(i+1)+'">网卡'+(i+1)+'</label>'
			} else {
				nic_radio = '<input type="radio" name="vm_nic" id="vm_nic'+(i+1)+'" value="'+(i+1)+'" onclick="getNicNetSelect('+nic_count+','+(i+1)+');"/><label for="vm_nic'+(i+1)+'">网卡'+(i+1)+'</label>'
			}
			nic_list = nic_list + nic_radio;
		}
		nic_list=nic_list	+ '</td>' +'</tr>';
		for (var i=0;i<nic_count;i++) {
			nic_list=nic_list+initSelectNetHtml(i);
		}
		return nic_list;
	}
	
	//实例网络子域下拉框信息
	function initSubDomain(nic_count) {
		$.ajax({
			type:'GET',
			dataType:"json",
			url:'united_getSubDomainList.do',
			async:false,
			cache:false,
			success:function(msg){
				if (msg == -1) {
					alert("该网络域下没有VLAN,请重新选择");
				} else {
					for (var j=1;j<=nic_count;j++ ) {
						$("#subdomain"+j).empty();
						for ( var i = 0; i < msg.length; i++) {
							$("<option value="+msg[i].id+">"+msg[i].name+"</option>").appendTo("#subdomain"+j);
						}
					}
				}
			}
		});
	}
	
	function initSelectNetHtml(num) {
		num = num+1;
		var netdata = 
			'<tr id="net_auto'+num+'">'
			+	'<td class="til" width="15%">'
			+		'网络分配方式'
			+	'</td>'
			+	'<td>'
			+		'<input type="radio" name="netType'+num+'" id="netType'+num+'1" checked="checked" value="1" onclick="getNETSelect('+num+');"/><label for="netType'+num+'1">手动</label>'
			+		'<input type="radio" name="netType'+num+'" id="netType'+num+'2" value="2" onclick="getNETSelect('+num+');"/><label for="netType'+num+'2">自动</label>'
			+	'</td>'
			+'</tr>' 
			+'<tr id="net'+num+'">'
			+	'<td class="til">'
			+		'网络域'
			+	'</td>'
			+	'<td>'
			+       '<select  id="subdomain'+num+'" onchange="getSubDomain('+num+')" class="select-1">'
			+			'<option value="-1">请选择</option>'
			+		'</select>'
			+	'</td>'
			+'</tr>'
			+'<tr id="vlan'+num+'">'
			+	'<td class="til">'
			+		'VLAN'
			+	'</td>'
			+	'<td>'
			+		'<select  id="net_id'+num+'" class="select-1">'
			+			'<option value="-1">请选择</option>'
			+		'</select>'
			+	'</td>'
			+'</tr>'
			+'<tr id="IP'+num+'">'
			+	'<td class="til">'
			+		'IP地址<font color="red">*</font>'
			+	'</td>'
			+	'<td>'
			+		'<s:textfield id="ip'+num+'" cssClass="ipAddress select-1" readonly="true"></s:textfield><input type="button" value="选择" onclick="selectIPByVlan('+num+');"  class="ubtn-3 vm mgl-3"/>'
			+	'</td>'
			+'</tr>';
		return netdata;
	}
</script>
	
	<script type="text/javascript">
		function bar(idstr,contents){
		$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}

	function barEnd(idstr,contents){
		$.dialog.list[idstr].content(contents,false,false);
		$.dialog.list[idstr].time(2);
	}
	
	
	function pageOnLoad(){
		getNETSelect();//获取网络信息
		getHostStorageList();//获取主机对应的存储列表
		getDSInfo();
		getSubDomain();
		
		var nic_count = '<s:property value="vm_nic_count" />';
		$("#vmware_table").append(initNetHtml(nic_count));
		getNicNetSelect(nic_count,1);
		initSubDomain(nic_count);
		initVlan(nic_count);
	}
	//校验虚拟机名字是否存在
	function validateName(name,vtype){
		var param = "united_validateName.do?name="+encodeURI(encodeURI(name))+"&vtype="+vtype;
		var flag = false;
		$.ajax({
			type : "POST",
			url : param,
			dataType : "json",
			cache:false,
			async: false,
			success : function(data){
				var count = data.count;
				if(count==0){
					flag = true;
					$("#span_name1").empty();
					$("#span_name2").empty();
					
				}else{
					if(vtype==1){
						$("#span_name1").html('虚拟机名称已经存在，请更改');
					}
					else if(vtype==2){
						$("#span_name2").html('虚拟机名称已经存在，请更改');
					}
				}
			}
		});	
		return flag;
	}
	</script>
	
</head>
<body onload="pageOnLoad()" style="overflow-y:auto;">
	<s:form action="" method="post" id="virtualMachineUnitedVO">
	<s:hidden name="virtualMachineUnitedVO.vmCode" id="vmCode"></s:hidden>
	<s:hidden name="storage" id="storage"></s:hidden>
<s:if test="vtype==1">
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize"  id="vmware_table">
				<tr id="temList">
					<td class="til" align="left">
						选择主机
					</td>
					<td>
						<s:select list="resultList" listKey="uuid" listValue="name" 
							 id="hostCode" name="virtualMachineUnitedVO.hostCode" onchange="getHostStorageList()" cssClass="select-1"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" >
						虚拟机名称
						<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="virtualMachineUnitedVO.newVmName" id="newVmName" value="新建虚拟机" cssClass="inpt-2 vm" ></s:textfield>
						<span style="color:RED" id="span_name1"/>
					</td>
				</tr>
				<tr>
					<td class="til" >
						HOST_NAME<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="virtualMachineUnitedVO.dns" id="dns" cssClass="inpt-2 vm"></s:textfield>
						<span style="color:RED" id="span_vmName1"/>
					</td>
				</tr>
				<tr>
					<td class="til" >
						CPU个数
						<font color="red">*</font>
					</td>
					<td>
						<s:textfield
							 id="numCPUs" name="virtualMachineUnitedVO.numCPUs"
							cssStyle="width:50px;" cssClass="inpt-2 vm"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" >
						内存
						<font color="red">*</font>
					</td>
						<td>
						<s:textfield name="virtualMachineUnitedVO.memoryMB" id="memoryMB" cssClass="inpt-2 vm" cssStyle="width:50px;"></s:textfield>
						<s:select list="#{'1':'M','2':'G'}" 
							cssStyle="width:50px;   height:30px;" name="memUnit" id="unit" cssClass="select-1"></s:select>
						</td>
				</tr>
				<tr>
					<td class="til">存储
						<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'':''}" listKey="store_uuid" listValue="NAME"  id="datastoreCode" name="virtualMachineUnitedVO.datastoreCode" onchange="getDSInfo()"
						cssClass="select-1"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						存储使用情况
					</td>
					<td>
						<div id="dsinfo"></div>
					</td>
				</tr>
				<tr>
					<td class="til">存储大小
						<font color="red">*</font>
					</td>
					<td>
						<s:textfield  id="storageSizeInMb" cssStyle="width:50px;" name="virtualMachineUnitedVO.storageSizeInMb" cssClass="inpt-2 vm"></s:textfield>G
						<font color="red">存储只能增加，不能减少</font>
					</td>
				</tr>
				<!--  
				<tr id="SUBNET">
					<td class="til">
						子网掩码
					</td>
					<td>
						<s:textfield  name="subnet" id="subnet" cssClass="required ip"></s:textfield>
					</td>
				</tr>
				<tr id="GATEWAY">
					<td class="til">
						网关
					</td>
					<td>
						<s:textfield id="gateway" name="gateway" cssClass="required ip"></s:textfield>
					</td>
				</tr>
				<tr id="DNS">
					<td class="til">
						DNS服务器
					</td>
					<td>
						<s:textfield  name="dns" id="dns" cssClass="required ip" ></s:textfield>
					</td>
				</tr>
				-->
			</table> 
		</div>
</s:if>
<s:elseif test="vtype==2">
	 <table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til">虚拟机名称<font color="red">*</font></td>
					<td>
					    <s:textfield name="virtualMachineUnitedVO.newVmName" id="newVmName" value="新建虚拟机" cssClass="txt"></s:textfield>
						<span style="color:RED" id="span_name2"/></td>
				</tr>
				<tr>
					<td class="til">虚拟机描述</td>
					<td><s:textfield name="virtualMachineUnitedVO.vmDesc" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="til"> 
						选择存储<font color="red">*</font>
					</td>
					<td>
						<s:select list="storageList" name="virtualMachineUnitedVO.srUuid" listKey="store_uuid" listValue="name" headerKey="" headerValue="--请选择--" id="srUuid"/>
					</td>
				</tr>
			</table>
</s:elseif>
</s:form>
</body>
</html:html>

