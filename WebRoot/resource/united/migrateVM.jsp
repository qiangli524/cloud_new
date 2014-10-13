<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=defalut"></script>
	<script type="text/javascript">
	var vtype = '<s:property value="vtype" />';
	
	var api = frameElement.api;
	var w = api.opener;
	
	$(function(){
		if(vtype == '2'){
        	var migrateState = '<s:property value="migrateState" />';
        	if(migrateState=='false'){
        		$("#text").empty().append("虚拟机所在存储不是共享存储，无法实现迁移操作！").css({"color":"red"});
	            $("#hostCode").attr("disabled","true");
	            api.button({id:'cancle',name: '取消'});
        	}else{
        		api.button({id:'Ok',name: '迁移',callback:migrate,focus: true},
				 			{id:'cancle',name: '取消'});
        	}
        }else{
        	api.button({id:'Ok',name: '迁移',callback:migrate,focus: true},
				 			{id:'cancle',name: '取消'});
        }
		function migrate(){
			var migType = $(":radio:checked").val();//判断是迁移主机还是迁移存储
			var hostCode = $("#hostCode").val();//主机
			var datastoreCode = $("#datastoreCode").val();//存储
			var state = $("#state").val();//电源状态
		 	var connect_id = '<s:property value="connect_id" />';
			var uuid = '<s:property value="uuid" />';
			var parent_uuid = null;
			var vtype = $("#vtype").val();
			var str = $("#virtualMachineUnitedVO").serialize();
			if(vtype==1){
				if(migType=="1"){
					if(hostCode==null||hostCode==""){
						alert("请选择主机");
						return false;
					}
				}else{
					if(state==1||state==3){
						if(hostCode==null||hostCode==""){
							alert("请选择主机");
							return false;
						}
					}
					if(datastoreCode==null||datastoreCode==""){
						alert("请选择存储");
						return false;
					}
				}
			}else{
				if(hostCode==null||hostCode==""){
					alert("请选择主机");
					return false;
				}
			}
			
			if(vtype==1){
				if(migType=="1"){
					parent_uuid = $("#host_uuid").val();
				}else{
					if(state==1||state==3){
						parent_uuid = $("#host_uuid").val();
					}else{
						parent_uuid = null;
					}
				}
			}else if(vtype==2){
				parent_uuid = $("#hostCode").val();
			}
			w.exeMigrateVM(vtype,connect_id,uuid,parent_uuid,str);
		}
	});
	
	function getMigSelect(radiovalue){
		if ("1" == radiovalue) {
			$("#hostCode").attr("value","");
			$("#migrateHost").show();
			$("#migrateStore1").hide();
			$("#migrateStore2").hide();
		} else{
			$("#hostCode").attr("value","");
			$("#datastoreCode").attr("value","-1");
			var state = $("#state").val();
			//state==1关机状态
			//state==2启动状态
			//state==3挂起状态
			if(state==1||state==3){
				$("#datastoreCode").empty(); 
				$("#datastoreCode").append("<option value=''>请选择</option>");
				$("#migrateHost").show();
				$("#migrateStore1").show();
				$("#migrateStore2").show();
				$("#migrateHost_vmstatus").hide();
			}else{
				$("#migrateHost").hide();
				$("#migrateHost_vmstatus").show();
				$("#migrateStore1").show();
				$("#migrateStore2").show();
			}
		}
	}
	
	
	///选择主机
	function selectHostGlobal(){
		$("#datastoreCode").attr("value","-1");
		$("#dsInfo").html("");
		var uuid = '<s:property value="uuid" />';
		var connect_id = '<s:property value="connect_id" />';
		w.$.dialog({
			id:'selectHostGlobal',
			title:'选择主机',
			height:'520px',
			width:'730px',
			lock:true,
			content:'url:united_selectHostFormigrate.do?uuid='+uuid+'&connect_id='+connect_id
		});
	}
	function getHost(hostName,host_uuid,connect_uuid){
		$("#hostCode").attr("value",hostName);
		$("#host_uuid").attr("value",host_uuid);
		$("#connect_uuid").attr("value",connect_uuid);
		var url = 'united_selectDataStore.do?parent_uuid='+host_uuid+'&connect_id='+connect_uuid;
		$.ajax({
			type:'post',
			dataType:"json",
			url: url,
			async:false,
			success:function(msg){
				$("#datastoreCode").empty(); 
				$("#datastoreCode").append("<option value=''>请选择</option>");
				for ( var i = 0; i < msg.length; i++) {
					$("<option value="+msg[i].store_uuid+">"+msg[i].NAME+"</option>").appendTo("#datastoreCode");
				}
			}
		});
	}
	//获取存储信息
	function getDSInfo() {
		
		var connect_id = '<s:property value="connect_id"/>';
		var vtype = '<s:property value="vtype"/>';
		var store_uuid = $("#datastoreCode").val();
		var state = $("#state").val();
		if(state==1||state==3){
			if(store_uuid == ""){
				$("#dsInfo").html("");
			}
		}else{
			if(store_uuid == "-1"){
				$("#dsInfo").html("");
			}
		}
		//存储名称
		$.getJSON("united_getStorageDetail.do?connect_id="+connect_id+'&store_uuid='+store_uuid+"&vtype="+vtype,{'time':new Date().toString()},function(data){
			$("#dsInfo").html('<span class="blue">总量：'+data[0].CAPACITY+'G<span class="mgl-10">余量：'+data[0].FREE_SPACE+'G</span></span>');
		});
		
	}
</script>
</head>
<body style="overflow-y:auto;" >
<s:hidden name="vtype" id="vtype"></s:hidden>
<s:hidden name="state" id="state"></s:hidden>
<s:hidden name="host_uuid" id="host_uuid"></s:hidden>
<s:hidden name="connect_uuid" id="connect_uuid"></s:hidden>
	<s:form action="united_executeMigrateVM.do" method="post" id="virtualMachineUnitedVO" cssStyle="theForm">
		<s:if test="vtype == 1">
			<div>
				<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
					<tr>
						<td class="til" align="left">选择迁移类型</td>
						<td>
							<input type="radio" name="sele"  id="migType" checked="checked" value="1" onclick="getMigSelect('1');"/><label for="migType1">&nbsp;集群下主机间迁移</label>
							<input type="radio" name="sele" id="migType" value="2" onclick="getMigSelect('2');"/><label for="migType2">迁移存储</label>
						</td>
					</tr>
					<tr id="migrateHost" style="display: ">
						<td class="til" align="left">
							选择主机
						</td>
						<td>
							<!--  
							<s:select list="resultList" headerKey="-1" headerValue="请选择" listKey="h_uuid"
							 listValue="eq_name" id="hostCode" name="virtualMachineUnitedVO.hostCode"></s:select>
							 -->
							 <s:textfield name="virtualMachineUnitedVO.hostCode" id="hostCode" readonly="true" cssClass="inpt-2 vm"></s:textfield>
							 <span class="ubtn-1 mgl-20"><input type="button" onclick="selectHostGlobal()" value="选择" /></span>
						</td>
					</tr>
					<tr id="migrateHost_vmstatus" style="display: none"> 
						<td class="til" align="left">
							&nbsp;
						</td>
						<td>
							<font color="red">（注:启动状态虚拟机不可同时更改主机及存储)</font>
						</td>
					</tr>
					<tr id="migrateStore1" style="display: none"> 
						<td class="til" align="left">
							存储
						</td>
						<td>
							<s:select list="storageList" headerKey="-1" headerValue="请选择" listKey="store_uuid"
							 listValue="NAME" id="datastoreCode" name="virtualMachineUnitedVO.datastoreCode" onchange="getDSInfo();" cssClass="select-1"></s:select>
						</td>
					</tr>
					<tr id="migrateStore2" style="display: none">
						<td class="til" align="left">资源信息</td>
						<td><div id="dsInfo">暂无</div></td>
					</tr>
				</table>
			</div>
		</s:if>
		<s:elseif test="vtype == 2">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td align="left" colspan="2">
					<span id="text">迁移过程中虚拟机会一直保持在线状态,并且过程中会消耗大量的网络和IO!</span>
				</td>
			</tr>
			<tr id="migHost1" style="display:">
				<td class="til">
					选择迁移的目标主机：
				</td>
				<td>
					<s:select list="resultList" headerKey="-1" headerValue="请选择" listKey="h_uuid"
							 listValue="eq_name" id="hostCode" name="virtualMachineUnitedVO.hostCode"></s:select>
				</td>
			</tr>
		</table>
		</s:elseif>
	</s:form>
</body>
