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
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function RequestChange(obj){
	var requestType = $(obj).val();
	if (requestType == '0') {
		$("#cpuCount").attr("disabled","true");
		$("#memory").attr("disabled","true");
		$("#storage").attr("disabled","true");
		$("#expandStorage").attr("disabled","true");
		$("#cpuCount").val('2');
		$("#memory").val('2048');
		$("#storage").val('50');
		$("#expandStorage").val('0');
<%--		$("#showcpuCount").val('2');--%>
<%--		$("#showmemory").val('2048');--%>
<%--		$("#showstorage").val('50');--%>
<%--		$("#showexpandStorage").val('0');--%>
<%--		$("#perfConf0").attr("checked","checked");--%>
<%--		$("#perfConf1").attr("checked","");--%>
<%--		$("#perfConf2").attr("checked","");--%>
	}else if(requestType == '1'){
		$("#cpuCount").attr("disabled","true");
		$("#memory").attr("disabled","true");
		$("#storage").attr("disabled","true");
		$("#expandStorage").attr("disabled","true");
		$("#cpuCount").val('4');
		$("#memory").val('4096');
		$("#storage").val('100');
		$("#expandStorage").val('30');
<%--		$("#showcpuCount").val('4');--%>
<%--		$("#showmemory").val('4096');--%>
<%--		$("#showstorage").val('100');--%>
<%--		$("#showexpandStorage").val('30');--%>
	}else if(requestType == '2'){
		$("#cpuCount").attr("disabled","true");
		$("#memory").attr("disabled","true");
		$("#storage").attr("disabled","true");
		$("#expandStorage").attr("disabled","true");
		$("#cpuCount").val('8');
		$("#memory").val('8192');
		$("#storage").val('150');
		$("#expandStorage").val('50');
<%--		$("#showcpuCount").val('8');--%>
<%--		$("#showmemory").val('8192');--%>
<%--		$("#showstorage").val('150');--%>
<%--		$("#showexpandStorage").val('50');--%>
	}else if(requestType == '3'){
		$("#cpuCount").attr("disabled",false);
		$("#memory").attr("disabled",false);
		$("#storage").attr("disabled",false);
		$("#expandStorage").attr("disabled",false);
		$("#cpuCount").val('');
		$("#memory").val('');
		$("#storage").val('');
		$("#expandStorage").val('');
<%--		$("#showcpuCount").val('8');--%>
<%--		$("#showmemory").val('8192');--%>
<%--		$("#showstorage").val('150');--%>
<%--		$("#showexpandStorage").val('50');--%>
	}
	
};

function RequestChangeNet(obj){
	var requestType = $(obj).val();
	if (requestType == '0') {
		$("#netpool2").hide();
	}else if(requestType == '1'){
		$("#netpool2").show();
	}
	
};
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

<%--		var vmname = $("#vmname").val();--%>
<%--		if (vmname.length == 0) {--%>
<%--			alert("请指定虚拟机名称");--%>
<%--			return false;--%>
<%--		}--%>
<%--		var temid = $("#temid").val();--%>
<%--		var srsize = $("#srsize").val();--%>
<%--		var cpu = $("#cpu").val();--%>
<%--		var memsize = $("#memsize").val();--%>
		var flag = true;
<%--		mask('正在检测存储大小是否满足模板要求，请稍后....','0.7','0px');--%>
<%--		$.ajax({--%>
<%--			type:'post',--%>
<%--			url:'workorder_checkStore.do?srsize='+srsize+'&temid='+temid,--%>
<%--			async:false,--%>
<%--			success:function(msg){--%>
<%--				removeMask();--%>
<%--				if (msg == 1) {--%>
<%--					flag = true;--%>
<%--				} else {--%>
<%--					alert("抱歉，存储太小，无法提交任务单");--%>
<%--				}--%>
<%--			}--%>
<%--		});--%>
<%--		--%>
<%--		if (flag) {--%>
<%--			var projectid = $("#projectid").val();--%>
<%--			mask('正在检测资源余量是否充足，请稍后....','0.7','0px');--%>
<%--			$.ajax({--%>
<%--				type:'post',--%>
<%--				url:'workorder_checkResourceLeft.do?srsize='+srsize+'&cpu='+cpu+'&memsize='+memsize+'&projectid='+projectid,--%>
<%--				async:false,--%>
<%--				success:function(msg){--%>
<%--					removeMask();--%>
<%--					if (msg == 1) {--%>
<%--						flag = true;--%>
<%--					} else {--%>
<%--						alert("很遗憾，资源余量不足，无法提交任务单");--%>
<%--					}--%>
<%--				}--%>
<%--			});--%>
<%--		}--%>
		if(flag){ 
			api.get("viewResource").saveResource($("#theForm").serialize());
		} else {
			alert("数据不合法,无法提交任务单");
			return false;
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
	  			content: 'url:workorder_selectTemMan.do?oper='+oper
			});
		});
		
		$("#domainid").change(function(){
			$("#netid").empty();
			$("#ipaddress").val(null);
			var domainid = $("#domainid").val();
			$.ajax({
				type:'post',
				dataType:"json",
				url:'workorder_selectVlan.do?domainid='+domainid,
				async:false,
				success:function(msg){
					if (msg == -1) {
						alert("该网络域下没有VLAN,请重新选择");
					} else {
						for ( var i = 0; i < msg.length; i++) {
							$("<option value="+msg[i].NET_ID+">"+msg[i].NAME+"</option>").appendTo("#netid");
						}
					}
				}
			});
		});
		
		$("#netid").change(function(){
			$("#ipaddress").val(null);
		});
		
		$("#chooseIP").click(function(){
			var netid = $("#netid").val();
			if (netid == -1) {
				alert("请先选择一个VLAN");
				return false;
			}
			var oper = $("#oper").val();
			w.$.dialog({
				id:'addIP',
	  			title:'选择IP',
	  			width: '750px',
	  			height: '470px',
	  		    lock:true,
	  			content: 'url:workorder_selectIp.do?net_id='+netid+'&oper='+oper
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
			
			var busisystemid = $("#busisystemid").val();
			var domainid = $("#domainid").val();
			var uuid = $("#uuid").val();
			mask('正在生成虚拟机名称，请稍后....','0.7','0px');
			$.ajax({
				type:'post',
				url:'workorder_randomVmName.do?temid='+temid+'&busisystemid='+busisystemid+'&ipaddress='+ipaddress+'&domainid='+domainid+'&uuid='+uuid,
				success:function(msg){
					removeMask();
					$("#vmname").val(msg);
				}
			})
		} 
	}
	
	function selectTem(temid,connectid,temtype,cpunum,memsize,storesize,temname){
		$("#temid").val(connectid+"_"+temid);
		$("#temtype").val(temtype);
		$("#cpu").val(cpunum);
		$("#memsize").val(memsize/1024);
		$("#srsize").val(storesize/1024);
		$("#temname").val(temname);
	}
	
	function selectip(ipaddress){
		$("#ipaddress").val(ipaddress);
	}
	
	function showIP(){
		var selectip = $("#selectip").val();
		if (selectip == 0) {
			document.getElementById("ipshow").style.display="none";
		} else if(selectip == 1){
			document.getElementById("ipshow").style.display="";
		}
	}
	
	$(function(){
		var domainid = $("#domainid").val();
		var vlainid = $("#vlainid").val();
		if (domainid.length > 0 && domainid != -1) {
			$.ajax({
				type:'post',
				dataType:'json',
				url:'workorder_selectVlan.do?domainid='+domainid,
				async:false,
				success:function(msg){
					for ( var i = 0; i < msg.length; i++) {
						if (msg[i].NET_ID == vlainid) {
							$("<option value="+msg[i].NET_ID+" selected='selected'>"+msg[i].NAME+"</option>").appendTo("#netid");
						} else{
							$("<option value="+msg[i].NET_ID+">"+msg[i].NAME+"</option>").appendTo("#netid");
						}
					}
				}
			});
		}
	});
	
	$(function(){
		var selectip = $("#selectip").val();
		if (selectip == 0) {
			document.getElementById("ipshow").style.display="none";
		} else if(selectip == 1){
			document.getElementById("ipshow").style.display="";
		}
	});
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="oper" id="oper"></s:hidden>
	<s:hidden name="type" id="type"></s:hidden>
	<s:hidden name="uuid" id="uuid"></s:hidden>
	<s:hidden name="orderTaskObj.uuid" id="orderTaskuuid"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td>虚拟机名称<font color="red">*</font></td>
					<td>
						<s:textfield name="orderTaskObj.name" id="name"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>虚拟机描述<font color="red">*</font></td>
					<td>
						<s:textarea name="orderTaskObj.remark" id="remark"  cols="40" rows="4" > </s:textarea>
					</td>
				</tr>
<%--				<tr>--%>
<%--					<td>虚拟机hostname<font color="red">*</font></td>--%>
<%--					<td>--%>
<%--						<s:textfield name="orderTaskObj.hostname" id="hostname"></s:textfield>--%>
<%--					</td>--%>
<%--				</tr>--%>
				<tr>
					<td>选择模板<font color="red">*</font></td>
					<td>
					<s:select list="orderTaskObj.templeteList" listKey="templateCode"  listValue="name" id="templeteId" name="orderTaskObj.templateId"
							headerKey="-1" headerValue="请选择"></s:select>  
					</td>
				</tr>
				<tr>
					<td>性能配置<font color="red">*</font></td>
					<td>
					<s:radio  list="#{'0':'低端配置','1':'中端配置','2':'高端配置' ,'3':'自定义' }" name = "orderTaskObj.perfConf"  id = "perfConf" onclick="RequestChange(this)" ></s:radio>
					</td>
				</tr>
				<tr>
					<td>CPU个数<font color="red">*</font></td>
<%--					<td>--%>
<%--					<s:hidden name="orderTaskObj.cpuCount" id="cpuCount" value= "2"></s:hidden>--%>
<%--					<s:select  disabled="true"  list="#{'2':'2颗','4':'4颗','8':'8颗' }"  id="showcpuCount" name="orderTaskObj.cpuCount"></s:select>--%>
<%--					</td>--%>
					<td>
					<s:if test="orderTaskObj.perfConf==null">
						<s:textfield size="2" disabled="true" name="orderTaskObj.cpuCount" id="cpuCount" ></s:textfield>颗
					</s:if>
					<s:elseif test="orderTaskObj.perfConf==3">
					<s:textfield size="2" name="orderTaskObj.cpuCount" id="cpuCount" ></s:textfield>颗
					</s:elseif>
					</td>
					
				</tr>
				<tr>
					<td>内存大小<font color="red">*</font></td>
<%--					<td>--%>
<%--					<s:hidden name="orderTaskObj.memory" id="memory"  value= "2048" ></s:hidden>--%>
<%--					<s:select  disabled="true"  name="orderTaskObj.memory" list="#{'2048':'2048M','4096':'4096M','8192':'8192M' }"  id="showmemory"></s:select>--%>
<%--					</td>--%>
					<td>
					<s:if test="orderTaskObj.perfConf==null">
						<s:textfield size="2" disabled="true" name="orderTaskObj.memory" id="memory" ></s:textfield>M
					</s:if>
					<s:elseif test="orderTaskObj.perfConf==3">
					<s:textfield size="2" name="orderTaskObj.memory" id="memory" ></s:textfield>M
					</s:elseif>
					</td>
				</tr>
				<tr>
					<td>存储大小<font color="red">*</font></td>
<%--					<td>--%>
<%--					<s:hidden name="orderTaskObj.storage" id="storage"  value= "50" ></s:hidden>--%>
<%--					<s:select disabled="true" name="orderTaskObj.storage" list="#{'50':'50G','100':'100G','150':'150G' }"  id="showstorage"></s:select>--%>
<%--					</td>--%>
					<td>
					<s:if test="orderTaskObj.perfConf==null">
						<s:textfield size="2" disabled="true" name="orderTaskObj.storage" id="storage" ></s:textfield>G
					</s:if>
					<s:elseif test="orderTaskObj.perfConf==3">
					<s:textfield size="2"  name="orderTaskObj.storage" id="storage" ></s:textfield>G
					</s:elseif>
					</td>
				</tr>
				<tr>
					<td>扩展存储<font color="red">*</font></td>
<%--					<td >--%>
<%--					<s:hidden name="orderTaskObj.expandStorage" id="expandStorage" value= "0" ></s:hidden>--%>
<%--					<s:select  disabled="true" name="orderTaskObj.expandStorage"  list="#{'0':'0G','30':'30G','50':'50G' }" id="showexpandStorage"></s:select>--%>
<%--					</td>--%>
					<td>
					<s:if test="orderTaskObj.perfConf==null">
						<s:textfield size="2" disabled="true" name="orderTaskObj.expandStorage" id="expandStorage" ></s:textfield>G
					</s:if>
					<s:elseif test="orderTaskObj.perfConf==3">
					<s:textfield size="2" name="orderTaskObj.expandStorage" id="expandStorage" ></s:textfield>G
					</s:elseif>
					</td>
				</tr>
				<tr>
					<td>网卡要求<font color="red">*</font></td>
					<td>
					<s:if test="orderTaskObj.uuid==null">
					<s:radio  list="#{'0':'单网卡','1':'双网卡' }" name = "netNum"  id = "netNum" value="0" onchange="RequestChangeNet(this)" ></s:radio>
					</s:if>
					<s:elseif test="orderTaskObj.uuid!=null">
					<s:if test="orderTaskObj.net2==-1">
					<s:radio  list="#{'0':'单网卡','1':'双网卡' }" name = "netNum"  id = "netNum" value="0" onchange="RequestChangeNet(this)" ></s:radio>
					</s:if>
					<s:else>
					<s:radio  list="#{'0':'单网卡','1':'双网卡' }" name = "netNum"  id = "netNum" value="1" onchange="RequestChangeNet(this)" ></s:radio>
					</s:else>
					</s:elseif>
					</td>
				</tr>
				<tr>
					<td>网卡1<font color="red">*</font></td>
					<td>
					<s:select list="orderTaskObj.netList" listKey="IBM_NET_ID" listValue="NAME" id="net1" name="orderTaskObj.net1"
							headerKey="-1" headerValue="请选择"></s:select> 
					</td>
				</tr>
				<s:if test="orderTaskObj.net2==-1||orderTaskObj.uuid==null">
				<tr id = 'netpool2' style=" display: none">
				<td>网卡2<font color="red">*</font></td>
					<td>
					<s:select list="orderTaskObj.netList" listKey="IBM_NET_ID" listValue="NAME" id="net2" name="orderTaskObj.net2"
					headerKey="-1" headerValue="请选择"></s:select> 
					<%--						<s:select list="domainList" listKey="id" listValue="name" id="domainid" name="domainid"--%>
<%--							headerKey="-1" headerValue="请选择"></s:select>  --%>
					</td>
				</tr>
				</s:if>
				<s:else>
				<tr id = 'netpool2' >
				<td>网卡2<font color="red">*</font></td>
					<td>
					<s:select list="orderTaskObj.netList" listKey="IBM_NET_ID" listValue="NAME" id="net2" name="orderTaskObj.net2"
					headerKey="-1" headerValue="请选择"></s:select> 
					</td>
				</tr>
				</s:else>
					
				<tr>
					<td>集群</td>
					<td>
					<s:select list="orderTaskObj.clusterList" listKey="c_uuid" listValue="name" id="clusterId" name="orderTaskObj.clusterId"
					headerKey="-1" headerValue="请选择"></s:select> 
					</td>
				</tr>
				<tr>
					<td>
						虚拟机个数：<font color="red">*</font>
					</td>
					<td>
					<s:select  list="#{'1':'1个','2':'2个','3':'3个','4':'4个','5':'5个' }"  id="vmNum"  name="orderTaskObj.vmNum"></s:select>
					</td>
				</tr>
				<tr>
					<td>
						回收时间：<font color="red">*</font>
					</td>
					<td>
								<s:textfield name="orderTaskObj.freeDate" id="freeDate" 
									readonly="true" class="Wdate" cssStyle="txt"
			   						onFocus="WdatePicker({maxDate:'2200-10-01',minDate:'new Date()',dateFmt:'yyyy-MM-dd'})"></s:textfield>
							</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>