<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../common/taglib.jsp"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createResource,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function createResource(){
		var fl = $("#self").val();//用于判断走哪个保存方法
		if(fl == 2){
			//走原先的保存方法
			var vmname = $("#vmname").val();
		if (vmname.length == 0) {
			alert("请指定虚拟机名称");
			return false;
		}
		var temid = $("#temid").val();
		var srsize = $("#srsize").val();
		var cpu = $("#cpu").val();
		var memsize = $("#memsize").val();
		var flag = false;
		
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
		/*
		mask('正在检测存储大小是否满足模板要求，请稍后....','0.7','0px');
		$.ajax({
			type:'post',
			url:'workorder/workorder_checkStore.do?srsize='+srsize+'&temid='+temid,
			async:false,
			success:function(msg){
				removeMask();
				if (msg == 1) {
					flag = true;
				} else {
					alert("抱歉，存储太小，无法提交任务单");
				}
			}
		});
		*/
		//if (flag) {
			var oper = $("#oper").val();
			var wrid = "";
			if ('edit' == oper) {
				wrid = $("#wrid").val();
			} 
			var projectid = $("#projectid").val();
			mask('正在检测资源余量是否充足，请稍后....','0.7','0px');
			$.ajax({
				type:'post',
				url:'workorder/workorder_checkResourceLeft.do?srsize='+srsize+'&cpu='+cpu+'&memsize='+memsize+'&projectid='+projectid+'&wrid='+wrid,
				async:false,
				success:function(msg){
					removeMask();
					if (msg == 1) {
						flag = true;
					} else {
						flag = false;
						alert("很遗憾，资源余量不足，无法提交任务单");
					}
				}
			});
			//}
			if(flag){
				api.get("viewResource").saveResource($("#theForm").serialize()+"&netWay="+netWay+"&net_id="+net_id+"&ips="+ips);
			} else {
				alert("数据不合法,无法提交任务单");
				return false;
			}
		}else{
			//走新修改方法
			var os = $("#operos").val();
			if(os == null || os == ""){
				alert("操作系统不能为空!");
			}else{
				api.get("viewResource").saveEntityUser(os);
			}
		}
	}
	
	$(function(){
		$("#chooseTem").click(function(){
			var oper = $("#oper").val();
			w.$.dialog({
				id:'addtem',
	  			title:'选择模板',
	  			width: '1100px',
	  			height: '550px',
	  		    lock:true,
	  			content: 'url:workorder/workorder_selectTemMan.do?oper='+oper
			});
		});
		
		$("#selectClu").click(function(){
			var connectid = $("#connectid").val();
			var oper = $("#oper").val();
			w.$.dialog({
				id:'addclu',
	  			title:'选择集群',
	  			width: '1100px',
	  			height: '550px',
	  		    lock:true,
	  			content: 'url:workorder/workorder_selectCluster.do?oper='+oper+'&connectId='+connectid
			});
		});
		
		$("#selectHost").click(function(){
			var cluid = $("#clusterid").val();
			var connectid = $("#connectid").val();
			if (cluid.length == 0) {
				alert("请先选择集群");
				return false;
			}
			var oper = $("#oper").val();
			w.$.dialog({
				id:'addhost',
	  			title:'选择主机',
	  			width: '1100px',
	  			height: '550px',
	  		    lock:true,
	  			content: 'url:workorder/workorder_selectHost.do?oper='+oper+'&domainid='+cluid+'&vlainid='+connectid
			});
		});
		
		$("#choosePRO").click(function(){
			var openerId =  $("#openerId").val();
			w.$.dialog({
				id:'addpro',
	  			title:'选择项目',
	  			width: '900px',
	  			height: '470px',
	  		    lock:true,
	  			content: 'url:workorder/workorder_selectProject.do?operFrom='+openerId
			});
		});
		
		
		$("#chooseBusi").click(function(){
			
			var openerId =  $("#openerId").val();
			w.$.dialog({
				id:'addbusi',
	  			title:'选择业务系统',
	  			width: '900px',
	  			height: '470px',
	  		    lock:true,
	  			content: 'url:workorder/workorder_selectBusiSystem.do?operFrom='+openerId
			});
		});
		
		$("#chooseSubBusi").click(function(){
			var resParentBusiSystemiId = $("#resParentBusiSystemiId").val();
			if (resParentBusiSystemiId.length == 0) {
				alert("请先选择一个业务系统");
				return false;
			}
			var openerId =  $("#openerId").val();
			
			w.$.dialog({
				id:'addSubBusi',
				title:'选择业务子系统',
				width:'900px',
				height:'470px',
				lock:true,
				content:'url:workorder/workorder_selectBusiSystem.do?busisystemid='+resParentBusiSystemiId+'&operFrom='+openerId
			});
		});
		
		
	});
	
	function randomVmName(){
		
		var vmname = $("#vmname").val();
		if (vmname.length == 0) {
			var temid = $("#temid").val();
			if (temid.length == 0) {
				alert("请选择一个模板");
				return false;
			}
			var cpu = $("#cpu").val();
			if (cpu.length == 0) {
				alert("cpu个数不能为空");
				return false;
			}
			var memsize = $("#memsize").val();
			if (memsize.length == 0) {
				alert("内存大小不能为空");
				return false;
			}
			var srsize = $("#srsize").val();
			if (srsize.length == 0) {
				alert("存储大小不能为空");
				return false;
			}
			var netid = $("#netid").val();
			if (netid == -1) {
				alert("请选择一个VLAN");
				return false;
			}
			var selectip = $("#selectip").val();
			var ipaddress = "";
			if (selectip == 1) {
				ipaddress = $("#ipaddress").val();
				if (ipaddress.length == 0) {
					alert("请指定一个ip地址");
					return false;
				}
			}
			
			////////
			var projectid = $("#projectname").val();
			if (projectid.length == 0) {
				alert("请选择一个项目");
				return false;
			}
			var resBusiSytemId = $("#resBusiSytemId").val();
			if (resBusiSytemId.length == 0) {
				alert("请选择业务系统");
				return false;
			}
			
//			var resAppDir = $("#resAppDir").val();
//			if (resAppDir.length == 0) {
//				alert("请录入业务资源目录");
//				return false;
//			}
//			var resAppSize = $("#resAppSize").val();
//			if (resAppSize.length == 0 ) {
//				alert("请输入业务资源目录空间大小");
//				return false;
//			}
			///////
			
			var busisystemid = $("#resBusiSytemId").val();
			var domainid = $("#domainid").val();
			var uuid = $("#uuid").val();
			mask('正在生成虚拟机名称，请稍后....','0.7','0px');
			$.ajax({
				type:'post',
				dataType:'text',
				url:'workorder/workorder_randomVmName.do?temid='+temid+'&busisystemid='+busisystemid+'&ipaddress='+ipaddress+'&domainid='+domainid+'&uuid='+uuid,
				success:function(msg){
					removeMask();
					$("#vmname").val(msg);
				}
			})
		} 
	}
	
	function selectTem(temid,connectid,temtype,cpunum,memsize,storesize,temname,nic_count){
		$("#connectid").val(connectid);
		$("#temid").val(connectid+"_"+temid);
		$("#temtype").val(temtype);
		$("#cpu").val(cpunum);
		$("#memsize").val(memsize/1024);
		$("#srsize").val(storesize/1024);
		$("#temname").val(temname);
		//处理多网卡信息
		$("#vmware_table").append(initNetHtml(nic_count));
		
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
			
			$("#ipselect"+i).unbind().live("click",function(){
				var num =  $(this).attr("id").substring(8);
				var vlan_id = $("#net_id"+num).val();
				w.$.dialog({
					id:'addIP',
		  			title:'选择IP',
		  			width: '750px',
		  			height: '470px',
		  		    lock:true,
		  			content: 'url:united_selectIpByVlan.do?uuid='+vlan_id+"&name="+'addResource'+"&vm_num="+num
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
  			content: 'url:united_selectIpByVlan.do?uuid='+vlan_id+"&name="+'addResource'+"&vm_num="+num
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
			+		'<s:textfield id="ip'+num+'" cssClass="ipAddress select-1" readonly="true"></s:textfield><input type="button" id="ipselect'+num+'" value="选择" class="ubtn-3 vm mgl-3"/>'
			+	'</td>'
			+'</tr>';
		return netdata;
	}
	
	
	
	function referHost(){
		var selecthost = $("#referhost").val();
		if (selecthost == 0) {
			$("#clushow").hide();
			$("#host_id").val(null);
			$("#host_name").val(null);
			$("#connectid").val(null);
			$("#clusterid").val(null);
			$("#clustername").val(null);
		} else if (selecthost == 1) {
			$("#clushow").show();
		}
	}
	
	function selectBusi(busiid,businame,busisystemid){
		if (busisystemid.length == 0) {//业务系统
			$("#resParentBusiSystemiId").val(busiid);
			$("#resParentBusiSystemName").val(businame);
			$("#resBusiSytemId").val("");
			$("#resBusiSystemName").val("");
		} else {//子系统
			$("#resBusiSytemId").val(busiid);
			$("#resBusiSystemName").val(businame);
		}
	}
	
	function chooseCluster(cluname,connectid,cluid){
		$("#clustername").val(cluname);
		$("#connectid").val(connectid);
		$("#clusterid").val(cluid);
	}
	
	function chooseHost(host_id,host_name,connectid){
		$("#host_id").val(host_id+"_"+connectid);
		$("#host_name").val(host_name);
	}
	
	function showBusi(){
		var typer = $("#typer").val();
		if (typer == 2) {
			$("#showBusi").hide();
		} else if(typer == 0){
			$("#showBusi").show();
		}
	}
	function selectPro(proid,proleader,projectname,projectusername){
		$("#projectid").val(proid);
		$("#projectuserid").val(proleader);
		$("#projectname").val(projectname);
		$("#projectusername").val(projectusername);
	}
	
	$(function(){
		var selecthost = $("#referhost").val();
		if (selecthost == 0) {
			$("#clushow").hide();
		} else if (selecthost == 1) {
			$("#clushow").show();
		}
	});
	/*add by qism */
	function sel(){
		var val = $("#self").val();
		if(val == 1){
			$("#hideTab1").hide();
			$("#hideTab2").show();
		}else if(val == 2){
			$("#hideTab1").show();
			$("#hideTab2").hide();
		}else{
			$("#hideTab1").hide();
			$("#hideTab2").hide();
		}
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssClass="pdt-10">
	<s:hidden name="oper" id="oper"></s:hidden>
	<s:hidden name="workOrderObj.ID" id="wrid"></s:hidden>
	<s:hidden name="type" id="type"></s:hidden>
	<s:hidden name="uuid" id="uuid"></s:hidden>
	<s:hidden name="vlainid" id="vlainid"></s:hidden>
	<s:hidden name="workOrderObj.TEMPLATE_ID" id="temid"></s:hidden>
	<s:hidden name="operFrom" id="openerId"></s:hidden>
	
		<div>
			<div style="border-bottom:1px dashed #ccc;" class="pd-10">
			&nbsp;&nbsp;资源类型:<s:select onchange="sel();" cssClass="select-1" id="self" list="#{'0':'--请选择--','1':'主机','2':'虚拟机'}"></s:select>
			</div>
			<div id="hideTab1" style="display: none;">
				<table width="100%" border="0" cellspacing="0" class="pop-table nosize" id="vmware_table">
				<%--<tr>
					<td>虚拟机名称<font color="red">*</font></td>
					<td>
						<s:textfield name="workOrderObj.VM_NAME" id="vmname"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>主机名称<font color="red">*</font></td>
					<td>
						<s:textfield name="workOrderObj.HOST_NAME" id="hostname"></s:textfield>
					</td>
				</tr>
				--%>
					<tr>
						<td class="til">选择模板<font color="red">*</font></td>
						<td>
							<s:textfield name="workOrderObj.TEMPLATENAME" id="temname" readonly="true" cssClass="inpt-2 vm"></s:textfield>
							<input type="button" class="ubtn-3 vm mgl-3" value="选择" id="chooseTem"/>
						</td>
						<td class="til">模板类型</td>
						<td>
							<s:textfield name="workOrderObj.TEMPLATE_TYPE" id="temtype" readonly="true" cssClass="inpt-2 vm"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="til">CPU个数<font color="red">*</font></td>
						<td>
							<s:textfield name="workOrderObj.CPU_NUM" id="cpu" cssClass="inpt-2 vm"></s:textfield>
						</td>
						<td class="til">内存大小<font color="red">*</font></td>
						<td>
							<s:textfield name="workOrderObj.MEM_SIZE" id="memsize" cssClass="inpt-2 vm"></s:textfield>G
						</td>
					</tr>
					<tr>
						<td class="til">存储大小<font color="red">*</font></td>
						<td>
							<s:textfield name="workOrderObj.SR_SIZE" id="srsize" cssClass="inpt-2 vm"></s:textfield>G
						</td>
						<td class="til">
							是否指定主机
						</td>
						<td>
							<s:select list="#{'1':'是','0':'否'}" id="referhost" onchange="referHost()" name="workOrderObj.ISREFERHOST" cssClass="select-1"></s:select>
						</td>
					</tr>
					<tr id="clushow">
						<td class="til">集群<font color="red">*</font></td>
						<td>
							<s:hidden name="workOrderObj.CLUSTERID" id="clusterid"></s:hidden>
							<s:hidden name="workOrderObj.CONNECT_ID" id="connectid"></s:hidden>
							<s:textfield readonly="true" name="workOrderObj.CLUSTERNAME" id="clustername" cssClass="inpt-2 vm"></s:textfield>
							<input type="button" class="ubtn-3 vm mgl-3" value="选择" id="selectClu"/>
						</td>
						<td class="til">主机<font color="red">*</font></td>
						<td>
							<s:hidden name="workOrderObj.HOST_ID" id="host_id"></s:hidden>
							<s:textfield readonly="true" name="workOrderObj.HOST_NAME" id="host_name" cssClass="inpt-2 vm"></s:textfield>
							<input type="button" class="ubtn-3 vm mgl-3" value="选择" id="selectHost"/>
						</td>
					</tr>
					<!-- ----------------------------------------- -->
					<tr>
						<td class="til">
							选择项目：<font color="red">*</font>
						</td>
						<td>
							<s:hidden name="workOrderObj.PROJECT_ID" id="projectid"></s:hidden>
							<s:textfield name="workOrderObj.PROJECT_NAME" readonly="true" id="projectname" cssClass="inpt-2 vm"></s:textfield>
							<input type="button" class="ubtn-3 vm mgl-3" value="选择" id="choosePRO"/>
						</td>
						<td class="til">
							项目责任人:
						</td>
						<td>
							<s:hidden name="workOrderObj.PROJECT_USER_ID" id="projectuserid"></s:hidden>
							<s:textfield name="workOrderObj.PROJECT_USER_NAME" readonly="true" id="projectusername" cssClass="inpt-2 vm"></s:textfield>
						</td>
					</tr>
					<!-- ----------------------------------------- -->
					<tr id="showBusi">
						<td class="til">
							业务系统：<font color="red">*</font>
						</td>
						<td>
							<s:hidden name="workOrderObj.resParentBusiSystemiId" id="resParentBusiSystemiId"></s:hidden>
							<s:textfield name="workOrderObj.resParentBusiSystemName" id="resParentBusiSystemName" disabled="true" cssClass="inpt-2 vm"></s:textfield>
							<input type="button" class="ubtn-3 vm mgl-3" value="选择" id="chooseBusi"/>
						</td>
						<td class="til">
							子业务系统：<font color="red">*</font>
						</td>
						<td>
							<s:hidden name="workOrderObj.resBusiSytemId" id="resBusiSytemId"></s:hidden>
							<s:textfield name="workOrderObj.resBusiSystemName" id="resBusiSystemName" disabled="true" cssClass="inpt-2 vm"></s:textfield>
							<input type="button" class="ubtn-3 vm mgl-3" value="选择" id="chooseSubBusi"/>
						</td>				
					</tr>
					<tr>
						<td class="til">
							资源目录：
						</td>
						<td>
							<s:textfield name="workOrderObj.resAppDir" id="resAppDir" cssClass="inpt-2 vm"></s:textfield>
						</td>
						<td class="til">
							资源目录大小(G)：
						</td>
						<td>
							<s:textfield name="workOrderObj.resAppSize" id="resAppSize" cssClass="inpt-2 vm"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="til">
							虚拟机名称：<font color="red">*</font>
						</td>
						<td>
							<s:textfield name="workOrderObj.VM_NAME" id="vmname" onfocus="randomVmName()" cssStyle="width:350px;" cssClass="inpt-2 vm"></s:textfield>
							<br/><font color="red">虚拟机名称须符合规范性要求，请慎重命名</font>
						</td>
					</tr>
				</table>
			</div>
			<div id="hideTab2" style="display: none;" class="pd-10">
				&nbsp;&nbsp;操作系统:<s:select  cssClass="select-1" id="operos" list="#{'0':'--请选择--','1':'windows server2008','2':'redhat6.2','3':'centos'}"></s:select>
			</div>
		</div>
	</s:form>
</body>