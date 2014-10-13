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
				$(".topLabel").text("安装方式");
 				$(".topLabelDetail").text("选择你需要的安装方式");
			}else if(nowPage == 'thirdPage'){
				nowPage = 'secondPage'
				$(".secondPage").show();
 				$(".thirdPage").hide();
 				$(".nextStep").attr("disabled",false);
				$(".ok").attr("disabled",true);
				$(".menuStyle2").addClass("menuStyle");
				$(".menuStyle3").removeClass("menuStyle");
				$(".topLabel").text("安装对象");
 				$(".topLabelDetail").text("选择需要安装软件的主机或虚拟机");
			}
		});
		
	 	$(".nextStep").click(function(){
			var pattern = /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/;//IP校验
			var pattern1 = /^(192)\.(168)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/;//192.168 开头 IP校验
			var IpAdress = $("#ip").val();
			if(nowPage=='firstPage'){
				nowPage = 'secondPage';
 				$(".firstPage").hide();
 				$(".converBox_host").hide();
 				$(".converBox_vm").hide();
 				$(".secondPage").show();
				$(".lastStep").attr("disabled",false);
				$(".menuStyle1").removeClass("menuStyle");
				$(".menuStyle2").show().addClass("menuStyle");
 				$(".topLabel").text("安装对象");
 				$(".topLabelDetail").text("");
			}else if(nowPage=='secondPage'){
				nowPage = 'thirdPage';
				initThirdPage();
				/*
				if(IpAdress!=""&&!pattern.exec(IpAdress)){
					//alert("请输入正确的IP地址");
					$("#destiIpAdress").select();
					$("#destiIpAdress").focus();
					nowPage = 'secondPage';
					return false;
				}
				var converType = $("#converType").val();
				if(converType==1||converType==4){
					if(IpAdress!=""&&!pattern1.exec(IpAdress)){
						//alert("IP地址要以192.168开头");
						$("#destiIpAdress").select();
						$("#destiIpAdress").focus();
						nowPage = 'secondPage';
						return false;
					}
				}
				*/
 				$(".secondPage").hide();
 				$(".converBox_host").hide();
 				$(".converBox_vm").hide();
 				$(".thirdPage").show();
				$(".nextStep").attr("disabled",true);
				$(".ok").attr("disabled",false);
				$(".menuStyle2").removeClass("menuStyle");
				$(".menuStyle3").show().addClass("menuStyle");
 				$(".topLabel").text("确认");
 				//$(".topLabelDetail").text("请确认信息是否正确");
			}
		});
	 	
		
	 	$(".ok").click(function(){
	 		if(confirm("确认要进行安装么？")){
	 			var url ="software_install.do?"+$("#obj").serialize();
				w.startInstall(url);
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
		 var convertType = $("input:radio[name='way']:checked").val();
		 if(convertType==0){//逐个部署
			 $("#converTypeShow").text("逐个部署");
		 }else {
			 $("#converTypeShow").text("批量部署");
		 }
		 $("#sourceIpAdressShow").text($("#ip").val());
		// $("#sourceUserNameShow").text($("#sourceUserName").val());
		// $("#sourcePasswordShow").text($("#sourcePassword").val());
		 var osSystem = $("input:radio[name='types']:checked").val();
		 if(osSystem==1){
			 $("#OSSystemShow").text("主机");
		 }else if(osSystem==2){
			 $("#OSSystemShow").text("虚拟机");
		 }
		 var soft = $("#software_type").val();
		 if(soft==1){
			 $("#soft").text("mysql 5.5");
			 $("#description").attr("value","mysql 5.5");
		 }else{
			 $("#soft").text("tomcat 6.0.37");
			 $("#description").attr("value","tomcat 6.0.37");
		 }
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
			$("[name='type']").unbind().live("click",function(){
				var converType = $("input:radio[name='types']:checked").val();
				if(converType== '2' ){//展示虚拟机
					w.$.dialog({
		    			id:'sourceVmIp',
		    			title:'选择虚拟机',
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
		    			title:'选择主机',
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
	  		
	  		$("#ip").attr("value",eqIp);
	  		$("#vm_num").text(vmNum);
	  		$("#cpu_num").text(cpu);
	  		$("#mem_num").text(mem);
	  		$("#store_num").text(storageNum);
	  		$("#nic_num").text(nicNum);
			$(".converBox_host").show();
			$(".converBox_vm").hide();
	  	}
	 
	 function getVmIpAddr(eqId,vhName,vhIp,vhCpu,vhMem,vhStorage,vhSystem){
	  		
	  		$("#ip").attr("value",vhIp);
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
		//$("#target_ip").text("服务器带外管理IP地址:");
		$("#source_ip_three").text("虚拟机IP地址:");
		//$("#target_ip_three").text("服务器带外管理IP地址:");
		//$("#target_ip_span").text("服务器带外管理IP地址");
		$("#ip_span").show();
	 });
	 /*
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
	 */
	
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
	
	<div class="top">
	<s:form action="" method="post" id="obj">
		<label class="topLabel">安装方式</label><br/>
		<label class="topLabelDetail">选择你安装软件的方式</label><br/>
	</div>
	
	<div class="left">
		<ul style="width: auto;position: relative;top: 15px;">
			<li class="menuStyle1" style=""><font>安装方式</font></li>
			<li class="menuStyle2" style=""><font>安装对象</font></li>
			<li class="menuStyle3" style=""><font>确定</font></li>
		</ul>
	</div>
	
	<div class="right">
		<table style="display: none;width: 430px" class="firstPage table10">
			<tr>
				<td width="50%" >安装方式：</td>
				<td aling="right">
					<input type="radio" value="0" name="way" style="margin-top: 50px;"/>单个安装<br /><br /><br />
					
					<input type="radio" value="1" name="way" checked="checked"/>批量安装
				</td>
			</tr>
		</table>
		
		<table class="secondPage table10" style="display: none;width: 430px">
			<tr>
				<td>安装对象</td>
				<td>
					<s:radio list="#{'1':'主机','2':'虚拟机'}" name="types" value="1"></s:radio>
				</td>
			</tr>
			<tr>
				<td>IP地址</td>
				<td><s:textfield name="obj.ip" id="ip"></s:textfield><input type="button" value="选择" name="type" /></td>
			</tr>
			<tr>
				<td>选择软件</td>
				<td><s:select list="#{'1':'mysql 5.5','2':'tomcat 6.0.37'}" id="software_type" name="obj.software_type"></s:select></td>
				
			</tr>
		</table>
		
		<table class="thirdPage table11" style="display: none;margin-left:20px;width:200px">
			<tr>
				<td colspan="2">
					<label><font size="4px"  color="blue">安装信息</font></label>
				</td>
			</tr>
			<tr>
				<td width="45%"><label>安装方式：</label></td>
				<td><label id="converTypeShow"></label></td>
			</tr>
			<tr>
				<td>IP地址：</td>
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
				<td><label>安装对象：</label></td>
				<td><label id="OSSystemShow"></label></td>
			</tr>
			<tr>
				<td>选择软件：</td>
				<td><label id="soft"></label></td>
			</tr>
			<tr>
			<!-- 
				<td width="50%">描述</td>
			 -->
				<td><s:hidden name="obj.description" id="description" ></s:hidden></td>
			</tr>
			<!--
			<tr style="margin-top:50px">
			 
				<td colspan="2">
					<label><font size="4px" color="blue">安装信息</font></label>
				</td>
				 
			</tr>
			-->
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
	<!-- 
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
	 -->
	<div class="bottom">
		<input type="button" value="上一步" class="lastStep ui_state_highlight"/>
		<input type="button" value="下一步" class="nextStep ui_state_highlight" />
		<input type="button" value="完成" class="ok"/>
		<!-- 
		<input type="button" value="取消" class="cancle"/>
		 -->
	</div>
	</s:form>
</body>
