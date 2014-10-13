<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />
<meta http-equiv="expires" content="0" />
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/js/formvalidate/validate.css"  type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript">
	
	 var api = frameElement.api;
	 var w = api.opener;
	
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
	 	theForm.operationSystemName.value=$("#osId").find('option:selected').text();
	 	theForm.submit();
	 	bar('add','虚拟机创建中...');
	 }
	//获取存储信息
	function getDSInfo() {
		var connect_id = '<s:property value="connect_id"/>';
		var vtype = '<s:property value="vtype"/>';
		var store_uuid = $("#datastoreCode").val();
		//存储名称
		if(vtype==1){
		///var dsname = $("#datastoreName").find("option:selected").text();
		$.getJSON("united_getStorageDetail.do?connect_id="+connect_id+'&store_uuid='+store_uuid+"&vtype="+vtype,{'time':new Date().toString()},function(data){
			$("#dsinfo").html('总量:'+data[0].CAPACITY+'G'+'&nbsp;&nbsp;&nbsp;'+'余量:'+data[0].FREE_SPACE+'G');
		});
		}
	}
	function bar(idstr,contents){
		w.$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    time:   2,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}
	function pageOnLoad(){
		getOSSelect();
		getDSInfo();
	}
	//校验虚拟机名字是否存在
	function validateName(){
		var name = $("#newVmName").val();
		var vtype = '<s:property value="vtype"/>';
		var url = "united_validateName.do";
		var param = "name="+encodeURI(encodeURI(name))+"&vtype="+vtype;
		$.ajax({
			type : "POST",
			url : url,
			data: param,
			dataType: "json",
			success : function(data){
				if(data.count>0){
					$("#span_vmName1").html("名字已存在");
				}else{
					$("#span_vmName1").html("");
				}
				//span_vmName2 不存在。yanggl修改
				//if(vtype==1){
				//	$("#span_vmName1").html(data);
				//}
				//else if(vtype==2){
				//	$("#span_vmName2").html(data);
				//}
			}
		});	
	}
	//选择网卡
	function chooseVnic(){
		var num = $("[type='vnic']").find("option:selected").val();
		if(num=='1'){
			$("#vnic1").show();
			$("#vnic2,#vnic3,#vnic4").hide();
		}else if(num=='2'){
			$("#vnic1,#vnic2").show();
			$("#vnic3,#vnic4").hide();
		}else if(num=='3'){
			$("#vnic1,#vnic2,#vnic3").show();
			$("#vnic4").hide();
		}else if(num=='4'){
			$("#vnic1,#vnic2,#vnic3,#vnic4").show();
		}
	}
	//获取OS下拉框数据
	function getOSSelect() {
		createXmlHttp();
    	var osType=$("input[class='osType']:checked").val();
    	xmlHttp.open("GET", "vmw_getOsTypeList.do?osType="+osType, false);   
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
     		if (xmlHttp.readyState == 4) {
    		var pageInfo = eval("("+xmlHttp.responseText+")");     
			var  SelectNode = document.all.osId;
     		SelectNode.length=0;
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(o,pageInfo[o]));
      		}
      		
    	}
	}
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
	}
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		return opt;
	}
	</script>
	
</head>
<body onload="pageOnLoad()" style="overflow-y:auto;">
	<s:form method="post" id="theForm" target="hidden_frame" action="unitedOper_createOperate">
		<s:hidden name="uuid"></s:hidden>
		<s:hidden name="connect_id"></s:hidden>
		<s:hidden name="vtype"></s:hidden>
		<s:hidden name="parent_uuid"></s:hidden>
		<s:hidden name="virtualMachineUnitedVO.operationSystemName" id="operationSystemName"></s:hidden>
		<s:if test="vtype==1">
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til" width="15%">
						客户机操作系统
					</td>
					<td>
						<input type="radio" class="osType" name="osType" id="osType1" checked="checked" value="1" onclick="getOSSelect();"/><label for="osType1">Windows</label>
						<input type="radio" class="osType" name="osType" id="osType2" value="2" onclick="getOSSelect();"/><label for="osType2">Linux</label>
						<input type="radio" class="osType" name="osType" id="osType3" value="3" onclick="getOSSelect();"/><label for="osType3">其他</label>
					</td>
				</tr> 
				<tr>
					<td class="til">
						版本
					</td>
					<td>
						<s:select list="#{'':''}"
							name="virtualMachineUnitedVO.operationSystemId" id="osId" cssStyle="width:350px;   height:25px;"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" >
						虚拟机名称
					</td>
					<td>
						<s:textfield name="virtualMachineUnitedVO.newVmName" id="newVmName" value="新建虚拟机" cssClass="txt" onblur="validateName()"></s:textfield>
						<span style="color:RED" id="span_vmName1"/>
					</td>
				</tr>
				<tr>
					<td class="til" >
						CPU个数
					</td>
					<td>
						<s:select
							list="#{'8':'8','7':'7','6':'6','5':'5','4':'4','3':'3','2':'2','1':'1'}"
							 id="numCPUs" name="virtualMachineUnitedVO.numCPUs"
							cssStyle="width:40px;   height:25px;" value="2"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" >
						内存
					</td>
						<td>
						<s:textfield name="virtualMachineUnitedVO.memoryMB" id="memoryMB" cssClass="txt" cssStyle="width:50px;" value="2048"></s:textfield>
						<s:select list="#{'1':'M','2':'G'}" 
							cssStyle="width:40px;   height:25px;" name="memUnit" id="unit"></s:select>
						</td>
				</tr>
				<tr>
					<td class="til">存储</td>
					<td>
						<s:select list="queryList" listKey="store_uuid" listValue="NAME"  id="datastoreCode" name="virtualMachineUnitedVO.datastoreCode" onchange="getDSInfo()"></s:select>
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
					<td class="til">存储大小</td>
					<td>
						<s:textfield  id="storageSize" value="10240" cssClass="txt" cssStyle="width:50px;" name="virtualMachineUnitedVO.storageSizeInMb"></s:textfield>M
					</td>
				</tr>
				<tr>
					<td class="til">
						存储模式
					</td>
					<td>
						<s:select list="#{'persistent':'厚置备延迟置零','nonpersistent':'厚置备置零','undoable':'精简置备'}" name="virtualMachineUnitedVO.diskMode"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						网络
					</td>
					<td>
						<s:select list="#{'1':'1','2':'2','3':'3','4':'4'}" onchange="chooseVnic();" name="virtualMachineUnitedVO.vnicNum" type="vnic"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						网卡 1
						<s:hidden name="virtualMachineUnitedVO.ethernetCardList[0].ethernetCardName" value="网络适配器 1"></s:hidden>
					</td>
					<td>
						<s:select list="queryList2" listKey="name" listValue="name" name="virtualMachineUnitedVO.ethernetCardList[0].portGroup"></s:select>
						&nbsp;&nbsp;类型：
						<s:select list="#{'E1000':'E1000','VMXNET2':'VMXNET2','VMXNET3':'VMXNET3'}" name="virtualMachineUnitedVO.ethernetCardList[0].nicType"></s:select>
					</td>
				</tr>
				<tr id="vnic2" style="display:none;">
					<td class="til">
						网卡 2
						<s:hidden name="virtualMachineUnitedVO.ethernetCardList[1].ethernetCardName" value="网络适配器 2"></s:hidden>
					</td>
					<td>
						<s:select list="queryList2" listKey="name" listValue="name" name="virtualMachineUnitedVO.ethernetCardList[1].portGroup"></s:select>
						&nbsp;&nbsp;类型：
						<s:select list="#{'E1000':'E1000','VMXNET2':'VMXNET2','VMXNET3':'VMXNET3'}" name="virtualMachineUnitedVO.ethernetCardList[1].nicType"></s:select>
					</td>
				</tr>
				<tr id="vnic3" style="display:none;">
					<td class="til">
						网卡 3
						<s:hidden name="virtualMachineUnitedVO.ethernetCardList[2].ethernetCardName" value="网络适配器 3"></s:hidden>
					</td>
					<td>
						<s:select list="queryList2" listKey="name" listValue="name" name="virtualMachineUnitedVO.ethernetCardList[2].portGroup"></s:select>
						&nbsp;&nbsp;类型：
						<s:select list="#{'E1000':'E1000','VMXNET2':'VMXNET2','VMXNET3':'VMXNET3'}" name="virtualMachineUnitedVO.ethernetCardList[2].nicType"></s:select>
					</td>
				</tr>
				<tr id="vnic4" style="display:none;">
					<td class="til">
						网卡 4
						<s:hidden name="virtualMachineUnitedVO.ethernetCardList[3].ethernetCardName" value="网络适配器 4"></s:hidden>
					</td>
					<td>
						<s:select list="queryList2" listKey="name" listValue="name" name="virtualMachineUnitedVO.ethernetCardList[3].portGroup"></s:select>
						&nbsp;&nbsp;类型：
						<s:select list="#{'E1000':'E1000','VMXNET2':'VMXNET2','VMXNET3':'VMXNET3'}" name="virtualMachineUnitedVO.ethernetCardList[3].nicType"></s:select>
					</td>
				</tr>
			</table> 
		</div>
</s:if>
<iframe  name="hidden_frame" id="hidden_frame" style="display:none"></iframe>
</s:form>
</body>
</html:html>

