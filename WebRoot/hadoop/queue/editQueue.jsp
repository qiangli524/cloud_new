<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		     callback:saveInfo,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function saveInfo(){ 
		var oper = $("#oper").val();
		if (oper == "add") {
			$("#namespan").empty();
			var queueName = $("#queueName").val();
			if (queueName.length == 0) {
				$("#namespan").append("<font color='red'>ERROR：请填写队列名称</font>");
				return false;
			}else{
				$.ajax({
					type:'post',
					url:'hadoopQueue_validateQueueName.do?queue_name='+queueName,
					dataType:'text',
					async:false,
					cache:false,
					success:function(data){
						if(data == -1){
							$("#namespan").append("<font color='red'>ERROR：队列名称已存在!</font>");
							return false;
						}
					}
				});
			}
			
			
			$("#tacticsspan").empty();
			var tactics = $("#tactics").val();
			if (tactics == -1) {
				$("#tacticsspan").append("<font color='red'>ERROR：请先选择一种策略</font>");
				return false;
			}else if(tactics == 1){
				$("#showconfig").hide();
			}else if(tactics == 2 || tactics == 3){
				$("#showconfig").show();
			}
			
			if(tactics!=1){
				$("#configspan").empty();
				var configId = $("#configId").val();
				if (configId.length == 0) {
					$("#configspan").append("<font color='red'>ERROR：配置文件不能为空</font>");
					return false;
				}
			}
			
			var queueType = $("#queuetype").val();
			if (queueType == 1) {
				$("#pqueuespan").empty();
				var pid = $("#pid").val();
				if (pid.length == 0) {
					$("#pqueuespan").append("<font color='red'>ERROR：父队列不能为空</font>");
					return false;
				}
			}
			
			$("#userspan").empty();
			var service_id = $("#service_id").val();
			if (service_id.length == 0) {
				$("#userspan").append("<font color='red'>ERROR：请选择用户或用户组</font>");
				return false;
			}
		} 
		
		var reg = new RegExp("^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
		
		$("#memmaxspan").empty();
		var mem_max = $("#mem_max").val();
		if (mem_max.length == 0) {
			$("#memmaxspan").append("<font color='red'>ERROR：请填写内存最大值</font>");
			return false;
		} else if (! reg.test(mem_max)) {
			$("#memmaxspan").append("<font color='red'>ERROR：请填写非负数字</font>");
			return false;
		}
		
		$("#memminspan").empty();
		var mem_min = $("#mem_min").val();
		if (mem_min.length == 0) {
			$("#memminspan").append("<font color='red'>ERROR：请填写内存最小值</font>");
			return false;
		} else if (! reg.test(mem_min)) {
			$("#memminspan").append("<font color='red'>ERROR：请填写非负数字</font>");
			return false;
		}
		
		var flag = true;
		if (tactics == 1) {
			mask('正在检测策略，请稍后','0.7','0px');
			$.ajax({
				type:'post',
				url:'hadoopQueue_checkTactics.do',
				dataType:'text',
				async:false,
				cache:false,
				success:function(msg){
					removeMask();
					if (msg == -1) {
						flag = false;
						alert("该策略已经存在队列，且该策略只能拥有一个队列，无法添加新的策略");
						return false;
					}
				}
			});
		}
		
		if (flag) {
	        w.saveQueue($("#theForm").serialize());
		}
	}
	
	function validateName(){//验证队列名字是否存在
		var oper = $("#oper").val();
		if(oper=="add"){
			$("#namespan").empty();
			var queueName = $("#queueName").val();
			if (queueName.length != 0) {
				$.ajax({
					type:'post',
					url:'hadoopQueue_validateQueueName.do?queue_name='+queueName,
					dataType:'text',
					async:false,
					cache:false,
					success:function(data){
						if(data == -1){
							$("#namespan").append("<font color='red'>ERROR：队列名称已存在!</font>");
							return false;
						}
					}
				});
			}
		}
	}
	
	function isshowConfig(){//配置文件是否展示
		var tactics = $("#tactics").val();
		if(tactics == 1){
			$("#showconfig").hide();
		}else if(tactics == 2 || tactics == 3){
			$("#showconfig").show();
		}
	}
	
	$(function(){
		var oper = $("#oper").val();
		if (oper != "add") {
			$("#showqueuetype").hide();
			$("#pqueue").hide();
			$("#showusers").hide();
			$("#showtactics").hide();
			$("#showconfig").hide();
			$("#queueName").attr("readonly",true);
		} else {
			var queueType = $("#queuetype").val();
			if (queueType == 0) {
				$("#pqueue").hide();
			}
		}
		
		$("#tactics").change(function(){
			var tactics = $("#tactics").val();
			if (tactics == -1) {
				$("#configId").val(null);
				$("#configName").val(null);
			} else {
				$.ajax({
					type:"POST",
					url:"hadoopQueue_selectConfig.do?tactics="+tactics,
					dataType:"json",
					async:false,
					cache:false,
					success:function(data){
						$("#configId").attr("value",data.id);
						$("#configName").attr("value",data.name);
					}
				});
			}
		});
		
		
		$("#selectPQueue").click(function(){
			$("#tacticsspan").empty();
			var tactics = $("#tactics").val();
			if (tactics == -1) {
				$("#tacticsspan").append("<font color='red'>ERROR：请先选择一种策略</font>");
				return false;
			}
			w.$.dialog({
				id:'selectPQueue',
				title:'选择父队列',
				width:'800px',
				height:'500px',
				lock:true,
				content:'url:hadoopQueue_list.do?hadoopQueueObj.tactics='+tactics+'&oper='+oper
			});
		});
		
		
		
		$("[name='associateUsers']").unbind().live("click",function(){
			var queue_id = $("#queueId").val();
			w.$.dialog({
				id:'users',
				title:'关联用户',
				width: '800px',
				height: '500px',
    		    lock:true,
				content: 'url:hadoopQueue_selectUsers.do?idstr='+queue_id+'&oper='+oper+'&flag=show'
			});
		});
		
	});
	
	function saveUser(serviceId){
		var array = serviceId.split(",");
		var count = array.length-1;
		$("#usercount").text("关联了"+count+"个用户");
		$("#service_id").val(serviceId);
	}
	
	function showPQueue(){
		var queueType = $("#queuetype").val();
		if (queueType == 0) {
			$("#pqueue").hide();
		} else if (queueType == 1) {
			$("#pqueue").show();
		}
	}
	
	function selectParentQueue(queueId,queueName){
		$("#pid").attr("value",queueId);
		$("#pname").attr("value",queueName);
	}
	
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="oper" id="oper"></s:hidden>
		<s:hidden name="hadoopQueueObj.id" id="queueId"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						队列名称:<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="hadoopQueueObj.queue_name" id="queueName" onblur="validateName()" readonly="false"></s:textfield>
						<span id="namespan"></span>
					</td>
				</tr>
				<tr id="showtactics">
					<td class="til">
						所属策略:<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'-1':'请选择','1':'FIFO','2':'Container','3':'Fair' }" name="hadoopQueueObj.tactics" id="tactics" onchange="isshowConfig()"></s:select>
						<span id="tacticsspan"></span>
					</td>
				</tr>
				<tr id="showconfig">
					<td class="til">
						配置文件名称：
					</td>
					<td>
						<s:hidden name="hadoopQueueObj.configId" id="configId"></s:hidden>
						<s:textfield name="hadoopQueueObj.configName" id="configName" readonly="true"></s:textfield>
						<span id="configspan"></span>
					</td>
				</tr>
				<tr id="showqueuetype">
					<td class="til">
						队列类型：
					</td>
					<td>
						<s:select list="#{'0':'父队列','1':'子队列' }" id="queuetype" name="hadoopQueueObj.type" onchange="showPQueue()"></s:select>
					</td>
				</tr>
				<tr id="pqueue">
					<td class="til">
						父队列：<font color="red">*</font>
					</td>
					<td>
						<s:hidden name="hadoopQueueObj.parent_id" id="pid"></s:hidden>
						<s:textfield name="parentQueueName" id="pname"></s:textfield>
						<input type="button" class="thickbox btn-style02" value="选择" id="selectPQueue"/>
						<span id="pqueuespan"></span>
					</td>
				</tr>
				<tr id="showusers">
					<td class="til">
						<a href="javascript:;" name="associateUsers">
							<u>关联用户:</u><font color="red">*</font>
						</a>
					</td>
					<td>
						<s:hidden name="hadoopQueueObj.service_id" id="service_id"></s:hidden>
						<label id="usercount"></label>
						<span id="userspan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">cpu最大值:</td>
					<td>
						<s:textfield name="hadoopQueueObj.cpu_max" id="cpu_max"></s:textfield>
						<span id="cpumaxspan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">cpu最小值:</td>
					<td>
						<s:textfield name="hadoopQueueObj.cpu_min" id="cpu_min"></s:textfield>
						<span id="cpuminspan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">内存最大值:<font color="red">*</font></td>
					<td>
						<s:textfield name="hadoopQueueObj.mem_max" id="mem_max"></s:textfield>
						<span id="memmaxspan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">内存最小值:<font color="red">*</font></td>
					<td>
						<s:textfield name="hadoopQueueObj.mem_min" id="mem_min"></s:textfield>
						<span id="memminspan"></span>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
</html:html>