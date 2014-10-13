<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />
<meta http-equiv="expires" content="0" />
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/js/formvalidate/validate.css"  type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/home/ui/njs/ui/ued_ui.js"></script>
<script type="text/javascript">
	
	 var api = frameElement.api;
	 var w = api.opener;
	 var diskCount = 0;
	
	 api.button({
	     id:'Ok',
	     name: '创建',
	     callback:add,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	//创建虚拟机 
	 function add(){
		var name = $("#newVmName").val();
		var dns = $("#dns").val();
	 	var parent_id = '<s:property value="parent_id" />';
	 	var parent_uuid = '<s:property value="parent_uuid" />';
	 	var vtype = '<s:property value="vtype" />';
	 	var connect_id = '<s:property value="connect_id" />';
	 	var netWay = '';
	 	var net_id ='';
		var ips = '';
		//获取多网卡网络ID
		$("[id^=net_id]").each(function (i,netid) {
			net_id += netid.value+',';
		});
		//获取多网卡网络ID
		$("input:radio[name^=netType]:checked").each(function (i,nettype) {
			netWay += nettype.value+',';
		});
		//获取多网卡网络ID
		$("[id^=ip]").each(function (i,ipid) {
			ips += ipid.value+',';
		});
	 	//验证内存必须为4的倍数
	 	var mem = $("#memoryMB").val();
	 	var unit = $("#unit").val();
	 	if(unit!=2){
	 		if(mem%4!=0){
	 			alert('内存大小必须为4的倍数');
	 			return false;
	 		}
	 	}else{
	 		mem = mem*1024;
	 		if(mem%4!=0){
	 			alert('内存大小必须为4的倍数');
	 			return false;
	 		}
	 	}
	 	//暂未进行规则校验@huojla
	 	if (dns=='') {
	 		alert('虚拟机主机名称不能为空！');
	 		return false;
	 	}
	 	
	 	if(vtype==1||vtype==2){
	 		//添加IP地址校验@yanggl
		 	var checked = $('input:radio[name=netType]:checked').val();
		 	if(checked==1){
			 	if(ip==''){
			 		alert('请选择IP地址！');
			 		return false;
			 	}
		 	}
	 	}
	 
	 	var str = $("#virtualMachineUnitedVO").serialize()+"&netWay="+netWay+"&net_id="+net_id+"&memUnit="+unit+"&ips="+ips;
	 	///
		var mark = validateName(name,vtype);
		if(mark==true){
			w.saveVM(parent_id,parent_uuid,vtype,connect_id,str);
		}else{
			return false;
		}
		
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
	 
	//获取模板信息
	function getTemInfo(temid,name,cpunum,memsize,storesize,nic_count){
		$("#vmCode").attr("value",temid);
		$("#showVMName").attr("value",name);
		$("#numCPUs").attr("value",cpunum);
		$("#memoryMB").attr("value",memsize);
		var storeG =storesize/1024 +"";
		var store = "";
		if(storeG.indexOf(".")!= -1){
			store = storeG.substring(0,storeG.indexOf("."));
		}else{
			store =storeG; 
		}
		$("#storageSizeInMb").attr("value",store);
		//清空已追加的Dom
		emptyAppendDom();
		diskCount = 0;
		
		$("#vmware_table").append(initNetHtml(nic_count));
		//$("#vmware_table").append(initDataDiskButton());
		for ( var i = 1; i <= nic_count; i++) {
			$("#vm_nic"+i).unbind().live("click",function(){
				var nicnum = $(this).attr("id").substring(6);
				var netType=$("input[name='netType"+nicnum+"']:checked").val();
				for (var i=1;i<=nic_count;i++ ) {
					if (i == nicnum) {
						if(netType==1){
							$("#net_auto"+nicnum).show();
							$("#net"+nicnum).show();
							$("#vlan"+nicnum).show();
							$("#IP"+nicnum).show();
						} else {
							$("#net_auto"+nicnum).show();
							$("#net"+nicnum).show();
							$("#vlan"+nicnum).show();
							$("#IP"+nicnum).hide();
						}
					} else {
						$("#net_auto"+i).hide();
						$("#net"+i).hide();
						$("#vlan"+i).hide();
						$("#IP"+i).hide();
					}
				}
			});
			
			$("#netType"+i+1).unbind().live("click",function(){
				var num =  $(this).attr("name").substring(7);
				var netType=$("input[name='netType"+num+"']:checked").val();
				if(netType==1){
					$("#IP"+num).show();
					$("#net"+num).show();
					$("#vlan"+num).show();
				}else{
					$("#net"+num).show();
					$("#vlan"+num).show();
					$("#IP"+num).hide();
				}
				$("#ip"+num).attr('value','');
			});
			$("#netType"+i+2).unbind().live("click",function(){
				var num =  $(this).attr("name").substring(7);
				var netType=$("input[name='netType"+num+"']:checked").val();
				if(netType==1){
					$("#IP"+num).show();
					$("#net"+num).show();
					$("#vlan"+num).show();
				}else{
					$("#net"+num).show();
					$("#vlan"+num).show();
					$("#IP"+num).hide();
				}
				$("#ip"+num).attr('value','');
			});
			
			$("#selectip"+i).unbind().live("click",function(){
				var num =  $(this).attr("id").substring(8);
				var vlan_id = $("#net_id"+num).val();
				w.$.dialog({
					id:'addIP',
		  			title:'选择IP',
		  			width: '750px',
		  			height: '470px',
		  		    lock:true,
		  			content: 'url:united_selectIpByVlan.do?uuid='+vlan_id+"&name="+'addVM'+"&vm_num="+num
				});
			});
			
			$("#subdomain"+i).bind('change', function(){
				var num =  $(this).attr("id").substring(9);
				var domainid = $("#subdomain"+num).val();
				$.ajax({
					type:'GET',
					dataType:"json",
					url:'workorder/workorder_selectVlan.do?domainid='+domainid,
					async:false,
					cache:false,
					success:function(msg){
						if (msg == -1) {
							alert("该网络域下没有VLAN,请重新选择");
						} else {
							$("#net_id"+num).empty();
							for ( var i = 0; i < msg.length; i++) {
								$("<option value="+msg[i].NET_ID+">"+msg[i].NAME+"</option>").appendTo("#net_id"+num);
							}
						}
					}
				});
				 
            });
			
		}
		getNicNetSelect(nic_count,1);
		initSubDomain(nic_count);
		initVlan(nic_count);
	}
	
	//获取存储信息
	function getDSInfo() {
		var connect_id = '<s:property value="connect_id"/>';
		var vtype = '<s:property value="vtype"/>';
		var store_uuid = $("#datastoreCode").val();
		//存储名称
		$.getJSON("united_getStorageDetail.do?connect_id="+connect_id+'&store_uuid='+store_uuid+"&vtype="+vtype,{'time':new Date().toString()},function(data){
			$("#dsinfo").html( '<span class="blue">总量：'+data[0].CAPACITY+'G<span class="mgl-10">余量：'+data[0].FREE_SPACE+'G</span></span>');
		});
		
	}	
	
		//获取网络信息
		function getNETSelect(num){
			var netType=$("input[name='netType"+num+"']:checked").val();
			if(netType==1){
				$("#IP"+num).show();
				$("#net"+num).show();
				$("#vlan"+num).show();
			}else{
				$("#net"+num).show();
				$("#vlan"+num).show();
				$("#IP"+num).hide();
			}
			$("#ip"+num).attr('value','');
		}
		
		//进入界面实例vlan列表
		function initVlan(nic_count) {
			for ( var i = 1; i <= nic_count; i++) {
				getSubDomain(i);
			}
		}
		
		///根据子网路域获取对应的vlan
		function getSubDomain(num){
			var domainid = $("#subdomain"+num).val();
			$.ajax({
				type:'GET',
				dataType:"json",
				url:'workorder/workorder_selectVlan.do?domainid='+domainid,
				async:false,
				cache:false,
				success:function(msg){
					if (msg == -1) {
						alert("该网络域下没有VLAN,请重新选择");
					} else {
						$("#net_id"+num).empty();
						for ( var i = 0; i < msg.length; i++) {
							$("<option value="+msg[i].NET_ID+">"+msg[i].NAME+"</option>").appendTo("#net_id"+num);
						}
					}
				}
			});
	   }
		///通过vlan获取对应的IP地址列表
		function selectIPByVlan(num){
			var vlan_id = $("#net_id"+num).val();
			w.$.dialog({
				id:'addIP',
	  			title:'选择IP',
	  			width: '750px',
	  			height: '470px',
	  		    lock:true,
	  			content: 'url:united_selectIpByVlan.do?uuid='+vlan_id+"&name="+'addVM'+"&vm_num="+num
			});
		}
		//选择IP地址
		function selectip(ip,vm_num){
			$("#ip"+vm_num).attr("value",ip);
		}
		/*
		*显示不同网卡网络配置界面
		*/
		function getNicNetSelect(nic_count,nicnum) {
			var netType=$("input[name='netType"+nicnum+"']:checked").val();
			for (var i=1;i<=nic_count;i++ ) {
				if (i == nicnum) {
					if(netType==1){
						$("#net_auto"+nicnum).show();
						$("#net"+nicnum).show();
						$("#vlan"+nicnum).show();
						$("#IP"+nicnum).show();
					} else {
						$("#net_auto"+nicnum).show();
						$("#net"+nicnum).show();
						$("#vlan"+nicnum).show();
						$("#IP"+nicnum).hide();
					}
				} else {
					$("#net_auto"+i).hide();
					$("#net"+i).hide();
					$("#vlan"+i).hide();
					$("#IP"+i).hide();
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
					nic_radio = '<input type="radio" name="vm_nic" id="vm_nic'+(i+1)+'" checked="checked" value="'+(i+1)+'"/><label for="vm_nic'+(i+1)+'">网卡'+(i+1)+'</label>'
				} else {
					nic_radio = '<input type="radio" name="vm_nic" id="vm_nic'+(i+1)+'" value="'+(i+1)+'"/><label for="vm_nic'+(i+1)+'">网卡'+(i+1)+'</label>'
				}
				nic_list = nic_list + nic_radio;
			}
			nic_list=nic_list	+ '</td>' +'</tr>';
			for (var i=0;i<nic_count;i++) {
				nic_list=nic_list+initSelectNetHtml(i);
			}
			return nic_list;
		}
		
		function initSelectNetHtml(num) {
			num = num+1;
			var netdata = 
				'<tr id="net_auto'+num+'">'
				+	'<td class="til" width="15%">'
				+		'网络分配方式'
				+	'</td>'
				+	'<td>'
				+		'<input type="radio" name="netType'+num+'" id="netType'+num+'1" checked="checked" value="1" /><label for="netType'+num+'1">手动</label>'
				+		'<input type="radio" name="netType'+num+'" id="netType'+num+'2" value="2" /><label for="netType'+num+'2">自动</label>'
				+	'</td>'
				+'</tr>' 
				+'<tr id="net'+num+'">'
				+	'<td class="til">'
				+		'网络域'
				+	'</td>'
				+	'<td>'
				+       '<select  id="subdomain'+num+'" class="select-1">'
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
				+		'<s:textfield id="ip'+num+'" cssClass="ipAddress select-1" readonly="true"></s:textfield><input type="button" id="selectip'+num+'" value="选择" class="ubtn-3 vm mgl-3"/>'
				+	'</td>'
				+'</tr>';
			return netdata;
		}
		
		function emptyAppendDom() {
			$("tr[id^=net_auto]").remove();
			$("tr[id^=net]").remove();
			$("tr[id^=vlan]").remove();
			$("tr[id^=IP]").remove();
			$("tr[id^=nic_count]").remove();
		}
		
		//选择GB Or MB时更新相应数值
		function changeGbOrMb() {
			var mem = $("#memoryMB").val();
			var unit = $("#unit").val();
			
			if (unit == 2) {
				$("#memoryMB").attr("value", mem / 1024.0);
			} else {
				$("#memoryMB").attr("value", mem * 1024.0);
			}
		}
		//实例数据存储盘Html
		function initDataDiskHtml() {
			var disk_list = 
				'<tr id="data_disk">'
				+	'<td class="til" width="15%">'
				+		'数据盘清单'
				+	'</td>'
				+	'<td id="data_disk_num">';
				
				diskCount = 1;
				disk_list = disk_list + initDataDiskNum(diskCount);
				disk_list=disk_list	+ '</td>' +'</tr>';
			/*for (var i=0;i<nic_count;i++) {
				disk_list=disk_list+initSelectNetHtml(i);
			}*/
			disk_list=disk_list+initSelectDiskHtml(diskCount);
			return disk_list;
		}
		
		function addDataDiskFun() {
			if (confirm("确认添加数据盘吗？")) {
				if (diskCount == 0) {
					$("#vmware_table").append(initDataDiskHtml());
				} else {
					diskCount = diskCount + 1;
					$("#data_disk_num").append(initDataDiskNum(diskCount));
					$("#vmware_table").append(initSelectDiskHtml(diskCount));
				}
			}
		}
		
		function initDataDiskButton() {
			var disk_list = 
				'<tr id="data_disk_button">'
				+	'<td class="til" width="15%">'
				+		'&nbsp;'
				+	'</td>'
				+	'<td>';
				
				disk_list = disk_list + '<input type="button" value="添加数据盘" id="addDataDisk" class="ubtn-3 vm mgl-3" onclick="addDataDiskFun()"/>';
				disk_list=disk_list	+ '</td>' +'</tr>';
			return disk_list;
		}
		
		function initDataDiskNum(i) {
			var diskNumStr = '';
			diskNumStr += '<input type="radio" name="vm_disk" id="vm_disk'+(i)+'" checked="checked" value="'+(i)+'" onclick="getDiskDomSelect('+diskCount+','+i+')"/><label for="vm_disk'+(i)+'">数据磁盘'+(i)+'</label>';
			return diskNumStr;
		}
		
		function initSelectDiskHtml(num) {
			var diskdata = 
			'<tr id="diskType'+num+'">'
				+'<td class="til">'
				+	'磁盘类型:'
				+'</td>'
				+'<td>'
				+	'<input type="radio" name="type'+num+'" value="new" checked="checked"/>创建新的虚拟机磁盘<br/>'
				+	'<input type="radio" name="type'+num+'" value="now" />使用现有磁盘 <br/>'
				+	'(重新使用以前配置的磁盘)<br />'
				+	'<input type="radio" "type'+num+'" value="san" disabled="disabled"/>裸机映射 <br/>'
				+	'(让虚拟机直接访问SAN.该选项允许您使用现有SAN命令管理存储器并继续使用数据存储访问该存储器)'
				+'</td>'
			+'</tr>'
			+'<tr id="diskSize'+num+'">'
			+	'<td class="til">'
			+	'<font color="red">*</font>磁盘大小:'
			+	'</td>'
			+	'<td>'
			+		'<s:textfield cssClass="txt" cssStyle="width:100px;" name="virtualDiskUnitedVO.capacityInMB" id="capacity"></s:textfield>MB'
			+	'</td>'
			+'</tr>'
			+'<tr  id="diskPath'+num+'">'
			+	'<td class="til">'
			+		'<font color="red">*</font>存储路径:'
			+	'</td>'
			+	'<td>'
			+	'	<s:textfield cssClass="txt" cssStyle="width:400px;" name="virtualDiskUnitedVO.vmdkPath" id="vmdkPath"></s:textfield>'
			+	'</td>'
			+'</tr>'
			+'<tr  id="diskType1'+num+'">'
			+	'<td class="til">'
			+		'类型'
			+	'</td>'
			+	'<td>'
			+		'<select  id="net_id'+num+'" class="select-1"  name="virtualDiskUnitedVO.diskType">'
			+			'<option value="thin">精简模式</option>'
			+			'<option value="thick">厚置备延迟置零</option>'
			+			'<option value="eagerzeroedthick">厚置备置零</option>'
			+		'</select>'
			+	'</td>'
			+'</tr>'
			+'<tr  id="diskMode'+num+'">'
			+	'<td class="til">'
			+	'	模式:'
			+	'</td>'
			+	'<td>'
			+		'<input type="checkbox" value="independent" id="independence"/>独立<br />'
			+		'&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="virtualDiskUnitedVO.diskMode" disabled="disabled" value="independent_persistent" class="model"/>持久<br />'
			+		'&nbsp;&nbsp;&nbsp;&nbsp;(更改会立即永久性的写入磁盘)<br/>'
			+		'&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="virtualDiskUnitedVO.diskMode" disabled="disabled" value="independent_nonpersistent" class="model"/>非持久<br />'
			+		'&nbsp;&nbsp;&nbsp;&nbsp;(当关闭电源或恢复快照时，对该磁盘的更改会被丢弃)<br />'
			+	'</td>'
			+'</tr>'
			
			+'<tr  id="diskLocation'+num+'">'
			+	'<td class="til">'
			+		'位置:'
			+	'</td>'
			+	'<td>'
			+		'<input type="radio" name="diskPath" id="diskPath1" checked="checked" value="1" onclick="dealDatastore();"/><label for="diskPath1">与虚拟机存储在同一目录</label>'
			+	    '<input type="radio" name="diskPath" id="diskPath2" value="2" onclick="dealDatastore();"/><label for="diskPath1">指定数据存储</label><br/>'
			+	'</td>'
			+'</tr>'
			
			+'<tr  id="diskDatastore'+num+'">'
			+	'<td class="til">'
			+		'存储清单:'
			+	'</td>'
			+	'<td>'
			+	'</td>'
			+'</tr>';
			return diskdata;
		}
		
		/*
		*显示不同网卡网络配置界面
		*/
		function getDiskDomSelect(nic_count,disknum) {
			var netType=$("input[name='vm_disk"+disknum+"']:checked").val();
			for (var i=1;i<=nic_count;i++ ) {
				if (i == disknum) {
					$("#diskType"+disknum).show();
					$("#diskSize"+disknum).show();
					$("#diskPath"+disknum).show();
					$("#diskType1"+disknum).show();
					$("#diskMode"+disknum).show();
					$("#diskLocation"+disknum).show();
					$("#diskDatastore"+disknum).show();
				} else {
					$("#diskType"+i).hide();
					$("#diskSize"+i).hide();
					$("#diskPath"+i).hide();
					$("#diskType1"+i).hide();
					$("#diskMode"+i).hide();
					$("#diskLocation"+i).hide();
					$("#diskDatastore"+i).hide();
				}
			}
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
	/* 	getTemInfo();//获取模板信息 */
		//getNETSelect();//获取网络信息
		getDSInfo();
		//getSubDomain();
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
					$("#span_vmName1").empty();
					$("#span_vmName2").empty();
					
				}else{
					if(vtype==1){
						$("#span_vmName1").html('虚拟机名称已经存在，请更改');
					}
					else if(vtype==2){
						$("#span_vmName2").html('虚拟机名称已经存在，请更改');
					}
				}
			}
		});	
		return flag;
	}
	
	$(function(){
		var connect_id = '<s:property value="connect_id" />';
		var vtype = '<s:property value="vtype" />';
		$("#tem").click(function(){
			w.$.dialog({
				id:'chooseTem',
	  			title:'选择模板',
	  			width: '800px',
	  			height: '400px',
	  		    //lock:true,
	  			content: 'url:united_chooseTemMan.do?vtype='+vtype+'&connect_id='+connect_id
			});
		});
	})
	
	</script>
	
</head>
<body onload="pageOnLoad()" style="overflow-y:auto;">
	<s:form action="" method="post" id="virtualMachineUnitedVO">
		
		<s:if test="vtype==1">
		
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize" id="vmware_table">
				<tr id="temList" >
					<td class="til" align="left">
						选择模板
					</td>
					<td align="left">
						<%-- <s:select list="resultList" listKey="templateCode" listValue="name"
							cssStyle="width:160px;   height:25px;" onchange="getTemInfo();" id="vmCode" name="virtualMachineUnitedVO.vmCode" cssClass="required"></s:select> --%>
							<s:textfield cssClass="required inpt-2 vm" id="showVMName"></s:textfield>
							<s:hidden name="virtualMachineUnitedVO.vmCode" id="vmCode"></s:hidden>
							<input type="button" value="选择" id="tem" class="ubtn-3 vm mgl-3"/>
					</td>
				</tr>
				<tr>
					<td class="til" >
						虚拟机名称
						<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="virtualMachineUnitedVO.newVmName" id="newVmName" value="新建虚拟机" cssClass="inpt-2 vm"></s:textfield>
						<span style="color:RED" id="span_vmName1"/>
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
						<s:textfield  id="numCPUs" name="virtualMachineUnitedVO.numCPUs" cssStyle="width:50px;" cssClass="inpt-2 vm"></s:textfield>个
					</td>
				</tr>
				<tr>
					<td class="til" >
						内存
						<font color="red">*</font>
					</td>
						<td>
						<s:textfield name="virtualMachineUnitedVO.memoryMB" id="memoryMB"  cssStyle="width:50px;" cssClass="inpt-2 vm"></s:textfield>
						<s:select list="#{'1':'M','2':'G'}" 
							name="memUnit" id="unit" cssStyle="width:50px;" cssClass="select-1" onchange="changeGbOrMb()"></s:select>
						</td>
				</tr>
				<tr>
					<td class="til">存储
						<font color="red">*</font>
					</td>
					<td>
						<s:if test="vtype==1">
							<s:select list="storageList" listKey="store_uuid" listValue="NAME"  id="datastoreCode" name="virtualMachineUnitedVO.datastoreCode" onchange="getDSInfo()" cssClass="select-1"></s:select>
						</s:if>
						<s:else>
							<s:select list="storageList" listKey="store_uuid" listValue="name"  id="datastoreCode" name="virtualMachineUnitedVO.datastoreCode" onchange="getDSInfo()" cssClass="select-1"></s:select>
						</s:else>
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
						<s:textfield  id="storageSizeInMb" cssStyle="width:50px;" name="virtualMachineUnitedVO.storageSizeInMb"></s:textfield>G
						<font color="red">存储只能增加，不能减少</font>
					</td>
				</tr>
				<!-- 
				<tr id="SUBNET">
					<td class="til">
						子网掩码
					</td>
					<td>
						<s:textfield  name="subnet" id="subnet" cssClass="netMask"></s:textfield>
					</td>
				</tr>
				<tr id="GATEWAY">
					<td class="til">
						网关
					</td>
					<td>
						<s:textfield id="gateway" name="gateway" cssClass="ipAddress"></s:textfield>
					</td>
				</tr>
				
				<tr id="DNS">
					<td class="til">
						DNS服务器
					</td>
					<td>
						<s:textfield  name="dns" id="dns" cssClass="ipAddress" ></s:textfield>
					</td>
				</tr>
				 -->
			</table> 
		</div>

</s:if>
<s:elseif test="vtype==2">
	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr id="temList" >
					<td class="til" align="left">
						选择模板
					</td>
					<td align="left">
						<%-- <s:select list="resultList" listKey="templateCode" listValue="name"
							cssStyle="width:160px;   height:25px;" onchange="getTemInfo();" id="vmCode" name="virtualMachineUnitedVO.vmCode" cssClass="required"></s:select> --%>
							<s:textfield cssClass="required inpt-2 vm" id="showVMName"></s:textfield>
							<s:hidden name="virtualMachineUnitedVO.vmCode" id="vmCode"></s:hidden>
							<input type="button" value="选择" id="tem"  class="ubtn-3 vm mgl-3"/>
					</td>
				</tr>
				<tr>
					<td class="til" >
						虚拟机名称
						<font color="red">*</font>
					</td>
					<td align="left">
						<s:textfield name="virtualMachineUnitedVO.newVmName" id="newVmName" value="新建虚拟机" cssClass="inpt-2 vm"></s:textfield>
						<span style="color:RED" id="span_vmName2"/>
					</td>
				</tr>
				<%-- <tr>
					<td class="til" >
						HOST_NAME
					</td>
					<td>
						<s:textfield name="virtualMachineUnitedVO.dns" id="dns" ></s:textfield>
						<span style="color:RED" id="span_vmName1"/>
					</td>
				</tr> --%>
				<tr>
					<td class="til" >
						CPU个数
						<font color="red">*</font>
					</td>
					<td align="left">
						<s:textfield  id="numCPUs" name="virtualMachineUnitedVO.numCPUs" cssStyle="width:50px;"  cssClass="inpt-2 vm"></s:textfield>个
					</td>
				</tr>
				<tr>
					<td class="til" >
						内存
						<font color="red">*</font>  
					</td>
						<td align="left">
						<s:textfield name="virtualMachineUnitedVO.memoryMB" id="memoryMB"  cssStyle="width:50px;"  cssClass="inpt-2 vm"></s:textfield>
						<s:select list="#{'1':'M','2':'G'}" 
							name="memUnit" id="unit" cssStyle="width:50px;" cssClass="select-1"></s:select>
						</td>
				</tr>
				<tr>
					<td class="til">存储
						<font color="red">*</font>
					</td> 
					<td align="left">
						<s:if test="vtype==1">
							<s:select list="storageList"  cssClass="select-1" listKey="store_uuid" listValue="NAME"  id="datastoreCode" name="virtualMachineUnitedVO.datastoreCode" onchange="getDSInfo()"></s:select>
						</s:if>
						<s:else>
							<s:select list="storageList" cssClass="select-1" listKey="store_uuid" listValue="name"  id="datastoreCode" name="virtualMachineUnitedVO.datastoreCode" onchange="getDSInfo()"></s:select>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						存储使用情况
					</td>
					<td align="left">
						<div id="dsinfo"></div>
					</td>
				</tr>
				<tr>
					<td class="til">存储大小
						<font color="red">*</font>
					</td>
					<td align="left">
						<s:textfield  id="storageSizeInMb" cssStyle="width:50px;" name="virtualMachineUnitedVO.storageSizeInMb" cssClass="inpt-2 vm"></s:textfield>G
						<font color="red">存储只能增加，不能减少</font>
					</td>
				</tr>
			</table>
		</div>
		</div>
</s:elseif>
</s:form>
</body>
</html:html>

