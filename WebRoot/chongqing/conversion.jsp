<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;
	 var dialogName = '<%=request.getAttribute("dialogName")%>';
	 
	 var nowPage = 'firstPage';
	 $(function() {
	 	$(".lastStep").click(function(){
			if(nowPage == 'secondPage'){
				nowPage = 'firstPage'
				$(".firstPage").show();
 				$(".secondPage").hide();
				$(".lastStep").attr("disabled",true);
				$(".menuStyle1").addClass("menuStyle");
				$(".menuStyle2").removeClass("menuStyle");
				$(".topLabel").text("源系统");
 				$(".topLabelDetail").text("选择你要迁移的源系统");
			}else if(nowPage == 'thirdPage'){
				nowPage = 'secondPage'
				$(".secondPage").show();
 				$(".thirdPage").hide();
 				$(".nextStep").attr("disabled",false);
				$(".ok").attr("disabled",true);
				$(".menuStyle2").addClass("menuStyle");
				$(".menuStyle3").removeClass("menuStyle");
				$(".topLabel").text("目标系统");
 				$(".topLabelDetail").text("选择一个新的虚拟机的主机");
			}
		});
		
	 	$(".nextStep").click(function(){
			var pattern = /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/;//IP校验
			var pattern1 = /^(192)\.(168)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/;//192.168 开头 IP校验
			var IpAdress = $("#destiIpAdress").val();
			if(nowPage=='firstPage'){
				nowPage = 'secondPage';
 				$(".firstPage").hide();
 				$(".converBox_host").hide();
 				$(".converBox_vm").hide();
 				$(".secondPage").show();
				$(".lastStep").attr("disabled",false);
				$(".menuStyle1").removeClass("menuStyle");
				$(".menuStyle2").show().addClass("menuStyle");
 				$(".topLabel").text("目标系统");
 				$(".topLabelDetail").text("");
			}else if(nowPage=='secondPage'){
				nowPage = 'thirdPage';
				initThirdPage();
				if(IpAdress!=""&&!pattern.exec(IpAdress)){
					alert("请输入正确的IP地址");
					$("#destiIpAdress").select();
					$("#destiIpAdress").focus();
					nowPage = 'secondPage';
					return false;
				}
				var converType = $("#converType").val();
				if(converType==1||converType==4){
					if(IpAdress!=""&&!pattern1.exec(IpAdress)){
						alert("IP地址要以192.168开头");
						$("#destiIpAdress").select();
						$("#destiIpAdress").focus();
						nowPage = 'secondPage';
						return false;
					}
				}
				
 				$(".secondPage").hide();
 				$(".converBox_host").hide();
 				$(".converBox_vm").hide();
 				$(".thirdPage").show();
				$(".nextStep").attr("disabled",true);
				$(".ok").attr("disabled",false);
				$(".menuStyle2").removeClass("menuStyle");
				$(".menuStyle3").show().addClass("menuStyle");
 				$(".topLabel").text("确认");
 				$(".topLabelDetail").text("请确认信息是否正确");
			}
		});
	 	
		
	 	$(".ok").click(function(){
	 		if(confirm("确认要进行迁移么？")){
	 			var convertType = $("#converType option:selected").val();
				var destiIp = $("#destiIpAdress").val();
				var sourceIp = $("#sourceIpAdress").val();
				w.saveConvert(convertType,destiIp,sourceIp);
	 		}
		});
		
		$(".cancle").click(function(){
			w.closeDialog(dialogName);
		});
		
		$(".lastStep").attr("disabled","disabled");
	 	$(".ok").attr("disabled","disabled");
	 	$(".menuStyle1").attr("style","display=''").addClass("menuStyle");
	 	$(".firstPage").attr("style","display=''");
	 });
	 
	 function initThirdPage(){
		 var convertType = $("#converType option:selected").val();
		 if(convertType==1){
			 $("#converTypeShow").text("V2P");
		 }else if(convertType==2){
			 $("#converTypeShow").text("V2V");
		 }else if(convertType==3){
			 $("#converTypeShow").text("P2V");
		 }else if(convertType==4){
			 $("#converTypeShow").text("P2P");
		 }
		 $("#sourceIpAdressShow").text($("#sourceIpAdress").val());
		 $("#sourceUserNameShow").text($("#sourceUserName").val());
		 $("#sourcePasswordShow").text($("#sourcePassword").val());
		 var osSystem = $("#OSSystem option:selected").val();
		 if(osSystem==1){
			 $("#OSSystemShow").text("Linux");
		 }else if(osSystem==2){
			 $("#OSSystemShow").text("Windows");
		 }
		 $("#destiIpAdressShow").text($("#destiIpAdress").val());
		 $("#destiUserNameShow").text($("#destiUserName").val());
		 $("#destiPasswordShow").text($("#destiPassword").val());
	 }
	 
	 $(function(){
			//查询获取IP
			$("[name='queryIpAddr']").unbind().live("click",function(){
		    	w.$.dialog({
		    			id:'queryIp',
		    			title:'选择目标主机地址',
		    			width: '950px',
		    			height: '450px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:convert_queryIpAddr.do'
		    	}); 
		   	});
			
			//查询获取源IP
			$("[name='querySourceIp']").unbind().live("click",function(){
				var converType = $("#converType").val();
				if(converType== '1' || converType =='2'){//展示虚拟机
					w.$.dialog({
		    			id:'sourceVmIp',
		    			title:'选择源虚拟机',
		    			width: '950px',
		    			height: '450px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:convert_queryVmIpAddr.do'
		    		}); 
				}else{//展示主机
					w.$.dialog({
		    			id:'sourceHostIp',
		    			title:'选择源主机',
		    			width: '950px',
		    			height: '450px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:convert_queryHostIpAddr.do'
		    		}); 
				}
		   	});
			
		});
	 
	 function getHostIpAddr(eqId,eqIp,vmNum,cpu,mem,storageNum,nicNum){
	  		$.ajax({
	  			type:"post",
	  			url:"convert_queryHostStore.do?eq_id="+eqId,
	  			dataType:"json",
	  			async:false,
	  			cache:false,
	  			success:function(data){
	  				if(data.storeAll==null){
	  					$("#store_all").text(0);
	  				}else{
	  					$("#store_all").text(data.storeAll);
	  				}
	  				
	  				if(data.storeFree==null){
	  					$("#store_free").text(0);
	  				}else{
	  					$("#store_free").text(data.storeFree);
	  				}
	  			}
	  		});
	  		
	  		$("#sourceIpAdress").attr("value",eqIp);
	  		$("#vm_num").text(vmNum);
	  		$("#cpu_num").text(cpu);
	  		$("#mem_num").text(mem);
	  		$("#store_num").text(storageNum);
	  		$("#nic_num").text(nicNum);
			$(".converBox_host").show();
			$(".converBox_vm").hide();
	  	}
	 
	 function getVmIpAddr(eqId,vhName,vhIp,vhCpu,vhMem,vhStorage,vhSystem){
	  		
	  		$("#sourceIpAdress").attr("value",vhIp);
	  		$("#vm_name").text(vhName);
	  		$("#vm_cpu").text(vhCpu);
	  		$("#vm_mem").text(vhMem);
	  		$("#vm_store").text(vhStorage);
	  		$("#vm_system").text(vhSystem);
	  		$(".converBox_vm").show();
	  		$(".converBox_host").hide();
	  	}
	 $(function(){
		$("#source_ip").text("虚拟机IP地址:");	
		$("#target_ip").text("服务器带外管理IP地址:");
		$("#source_ip_three").text("虚拟机IP地址:");
		$("#target_ip_three").text("服务器带外管理IP地址:");
		$("#target_ip_span").text("服务器带外管理IP地址");
		$("#ip_span").show();
	 });
	 function changeConverType(){
		 var converType = $("#converType").val();
		 if(converType=="1"){
			 $("#source_ip").text("虚拟机IP地址:");
			 $("#target_ip").text("服务器带外管理IP地址:");
			 $("#source_ip_three").text("虚拟机IP地址:");
			 $("#target_ip_three").text("服务器带外管理IP地址:");
			 $("#target_ip_span").text("服务器带外管理IP地址");
			 $("#ip_span").show();
		 }else if(converType=="2"){
			 $("#source_ip").text("虚拟机IP地址:");
			 $("#target_ip").text("服务器主机IP地址:");
			 $("#source_ip_three").text("虚拟机IP地址:");
			 $("#target_ip_three").text("服务器主机IP地址:");
			 $("#target_ip_span").text("服务器主机IP地址");
			 $("#ip_span").hide();
		 }else if(converType=="3"){
			 $("#source_ip").text("服务器主机IP地址:");
			 $("#target_ip").text("服务器主机IP地址:");
			 $("#source_ip_three").text("服务器主机IP地址:");
			 $("#target_ip_three").text("服务器主机IP地址:");
			 $("#target_ip_span").text("服务器主机IP地址");
			 $("#ip_span").hide();
		 }else if(converType=="4"){
			 $("#source_ip").text("服务器主机IP地址:");
			 $("#target_ip").text("服务器带外管理IP地址:");
			 $("#source_ip_three").text("服务器主机IP地址:");
			 $("#target_ip_three").text("服务器带外管理IP地址:");
			 $("#target_ip_span").text("服务器带外管理IP地址");
			 $("#ip_span").show();
		 }
		 $("#sourceIpAdress").attr("value","");
		 $("#sourceUserName").attr("value","");
		 $("#sourcePassword").attr("value","");
		 $(".converBox_vm").hide();
	  	 $(".converBox_host").hide();
	 }
	
</script>
<style type="text/css">
/* CSS Document */
#theForm{width:100%;height: 100%;position: fixed;}

.blue-table tr td{border-width: 0px 0px 0px 0px;text-align: left;}
.top{width:100%;height: 15%;background-color: #f4f7fd;border-bottom: 1px solid #ccc}
.left{width: 19.8%;height: 72%;background-color: #f4f7fd;border-right: 1px solid #ccc;}
.right{width: 80%;height: 72%;background-color: #f4f7fd;float: right;position: relative;bottom: 72% }
.bottom{width:100%;height: 12%;background-color: #f4f7fd;border-top: 1px solid #ccc;text-align: right;clear: both;position: relative;bottom: 71.7%}

input[type='button'] {width: 60px;height: 25px;margin: 11px 5px 5px 5px}

ul li {font-size: 15px;height: 25px;}
ul li font {margin-left: 35px;position: relative;top: 5px;}
.menuStyle{background-color: #4876FF;color: #ffffff}

table tr td{padding-top: 3px}
table tr td input[type='radio']{margin-left: 10px}
.netTypeName{margin-left: 5px}
.netTypeContent{margin-left: 20px}

.disable{color: #cccccc}

table {position: relative;top: 15px;left: 15px;}

.topLabel{font-size: 18px;position: relative;top: 5px;left: 30px;}
.topLabelDetail{font-size: 14px;position: relative;top: 10px;left: 50px;}

.converBox_host{
	position: absolute;
	top: 100px;
	left: 565px;
	border:1px solid #D2D9FF;
	width:220px;
}
.converBox_vm{
	position: absolute;
	top: 100px;
	left: 565px;
	border:1px solid #D2D9FF;
	width:220px;
}

.table10 tr{
	height:35px;
}

.table11 tr{
	height:22px;
}

.table10 th{
	font-weight: normal;
	text-align: left;
	padding-left:10px;
	color:blue;
}
</style>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<div class="top">
		<label class="topLabel">源系统</label><br/>
		<label class="topLabelDetail">选择你要迁移的源系统</label><br/>
	</div>
	<div class="left">
		<ul style="width: auto;position: relative;top: 15px;">
			<li class="menuStyle1" style=""><font>源系统</font></li>
			<li class="menuStyle2" style=""><font>目标系统</font></li>
			<li class="menuStyle3" style=""><font>确定</font></li>
		</ul>
	</div>
	
	<div class="right">
		<table style="display: none;width: 430px" class="firstPage table10">
			<tr>
				<th width="30%">迁移类型：</th>
				<td aling="right">					
					<s:select list="#{'1':'V2P','2':'V2V','3':'P2V','4':'P2P'}" name="theForm.converType" id="converType"
							cssStyle="width:205px;   height:25px;" value="0" onchange="changeConverType()"></s:select>
				</td>
			</tr>
			<tr>
				<th width="30%"><label id="source_ip"></label></th>
				<td>
					<s:textfield name="theForm.sourceIpAdress" id="sourceIpAdress" cssClass="txt" cssStyle="width:200px;" readonly="true"></s:textfield>
					<input type="button" style="margin:0 0 0 0" value="选择" name="querySourceIp"/>
				</td>
			</tr>
			<tr>
				<th width="30%">用户名：</th>
				<td>
					<s:textfield name="theForm.sourceUserName" id="sourceUserName" cssClass="txt" cssStyle="width:200px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<th width="30%">密码：</th>
				<td>
					<input type="password" name="theForm.sourcePassword" id="sourcePassword" style="width:200px;" cssClass="required"/>
				</td>
			</tr>
			<tr>
				<th width="30%">操作系统类型：</th>
				<td>
					<s:select list="#{'1':'Linux','2':'Windows'}" name="theForm.OSSystem" id="OSSystem"
							cssStyle="width:205px;height:25px;" value="0"></s:select>
				</td>
			</tr>
		</table>
		
		<table class="secondPage table10" style="display: none;width: 430px">
			<tr>
				<th width="33%"><label id="target_ip"></label></th>
				<td>
					<s:textfield name="theForm.destiIpAdress" id="destiIpAdress" cssClass="txt" cssStyle="width:200px;"></s:textfield>
				</td>
			</tr>
			<tr><th></th><td><span style="color:red;font-size:15px;display: none;" id="ip_span"><label id="target_ip_span"></label>起始必须是192.168</span></td></tr>
			<%--<tr>
				<th width="27%">用户名：</th>
				<td>
					<s:textfield name="theForm.destiUserName" id="destiUserName" cssClass="txt" cssStyle="width:200px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<th width="27%">密码：</th>
				<td>
					<input type="password" name="theForm.destiPassword" id="destiPassword" style="width:200px;" cssClass="required"/>
				</td>
			</tr>
		--%></table>
		
		<table class="thirdPage table11" style="display: none;margin-left:20px;width:200px">
			<tr>
				<td colspan="2">
					<label><font size="4px"  color="blue">源系统信息</font></label>
				</td>
			</tr>
			<tr>
				<td width="45%"><label>迁移类型：</label></td>
				<td><label id="converTypeShow"></label></td>
			</tr>
			<tr>
				<td><label id="source_ip_three"></label></td>
				<td><label id="sourceIpAdressShow"></label></td>
			</tr>
			<%--<tr>
				<td><label>用户名：</label></td>
				<td><label id="sourceUserNameShow"></label></td>
			</tr>
			<tr>
				<td><label>密码：</label></td>
				<td><label id="sourcePasswordShow"></label></td>
			</tr>
			--%><tr>
				<td><label>操作系统类型：</label></td>
				<td><label id="OSSystemShow"></label></td>
			</tr>
			
			<tr style="margin-top:50px">
				<td colspan="2">
					<label><font size="4px" color="blue">目标系统信息</font></label>
				</td>
			</tr>
			<tr>
				<td><label id="target_ip_three"></label></td>
				<td><label id="destiIpAdressShow"></label></td>
			</tr>
			<%--<tr>
				<td><label>用户名：</label></td>
				<td><label id="destiUserNameShow"></label></td>
			</tr>
			<tr>
				<td><label>密码：</label></td>
				<td><label id="destiPasswordShow"></label></td>
			</tr>
		--%></table>
	</div>
	
	<div class="converBox_host" style="display: none;">
		<div style="font-size:13px">虚拟机：<label id="vm_num"></label>个</div>
		<div style="font-size:13px">CPU：    <label id="cpu_num"></label>核</div>
		<div style="font-size:13px">内存：   <label id="mem_num"></label>G</div>
		<div style="font-size:13px">存储总量：   <label id="store_all"></label>G</div>
		<div style="font-size:13px">存储空闲量：<label id="store_free"></label>G</div>
		<div style="font-size:13px">网卡：   <label id="nic_num"></label>个</div>
		<div style="font-size:13px">操作系统：Red Hat Enterprise Linux 6 (64 位)</div>
	</div>
	
	<div class="converBox_vm" style="display: none;">
		<div style="font-size:13px">虚拟机名称:<label id="vm_name"></label></div>
		<div style="font-size:13px">CPU：    <label id="vm_cpu"></label>核</div>
		<div style="font-size:13px">内存：   <label id="vm_mem"></label>G</div>
		<div style="font-size:13px">存储：   <label id="vm_store"></label>G</div>
		<div style="font-size:13px">操作系统： <label id="vm_system"></label></div>
	</div>
	
	<div class="bottom">
		<input type="button" value="上一步" class="lastStep ui_state_highlight"/>
		<input type="button" value="下一步" class="nextStep ui_state_highlight" />
		<input type="button" value="完成" class="ok"/>
		<input type="button" value="取消" class="cancle"/>
	</div>
	</s:form>
</body>
