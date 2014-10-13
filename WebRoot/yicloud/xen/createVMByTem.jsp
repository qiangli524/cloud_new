<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	var parent_id = <%=request.getAttribute("parent_id")%>;
	var entity_id = <%=request.getAttribute("entity_id")%>;
	var clusterId = <%=request.getAttribute("clusterId")%>;
	var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
	var host_uuid = '<%=request.getAttribute("host_uuid")%>';
	var vdiShare = '<%=request.getAttribute("vdiShare")%>';
	var srHostUuid = '<%=request.getAttribute("srHostUuid")%>';
	var nodeId = '<%=request.getAttribute("nodeId")%>';
	
	var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok1',
	     name: '创建虚拟机',
	     callback:createVm,
	     focus: true
	 },
	 {
	     id:'cancle1',
	     name: '取消'
	 });

	
	function createVm() {
		var name =$("#NAME_EN").val();
		var cpu = $("#CPU").val();
		var currentcpu = $("#CURRENTCPU").val();
		var staticMin = $("#staticMin").val();
		var dynamicMin = $("#dynamicMin").val();
		var dynamicMax = $("#dynamicMax").val();
		var staticMax = $("#staticMax").val();
		var select_hostuuid=$("#select option:selected").val();
		var ipAddress = $("#IP option:selected").text();
		//var ipAddress = '1.1.1.1';
		if (name=='') {
			alert('虚拟机名称不能为空');
			return;
		}
		if (currentcpu=='') {
			alert('CPU期望值不能为空');
			return;
		}
		if(ipAddress=='--请选择--'){
			alert("虚拟机的IP不能为空");
			return;
		}
		var url = "xen_createVMByTem.do?name="+ encodeURI(encodeURI(name))  + "&entity_id=" + entity_id+
		"&parent_id="+parent_id+"&cpu="+cpu+"&currentcpu="+currentcpu+"&staticMin="+staticMin+"&dynamicMin="+dynamicMin
		+"&dynamicMax="+dynamicMax+"&staticMax="+staticMax+"&clusterId="+clusterId+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid+"&selectHostUuid="+select_hostuuid+"&ipAddress="+ipAddress;
		w.createVmByTem(url,nodeId);
	}
	
	function listIp(){
		var net = $("#NET").val();
		$("#IP option").remove();
		var SelectNode = $("#IP")[0];
		var url = "xen_getIpList.do?net_id="+net;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data != null){
				SelectNode.appendChild(createSelect('0','--请选择--'));
    			$.each(data,function(key,value){
					SelectNode.appendChild(createSelect(key,value));
				});
    		}
		});
	}
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		return opt;
	}

	$(function(){
    	if(vdiShare=='false'){
          $("#select").attr("disabled",true);
    		$("#select option").each(function(){
    	          var temp=$(this).attr("value");
    	          var uuids=temp.split(",");
    	          if(srHostUuid==uuids[0]){
    	               this.selected=true;
    	          }
    			});
         }
	});
	</script>
</head>
<body >
<s:form action="" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<s:if test="theForm.resultList!=null">
				<tr>
					<td class="til" align="left">
						启动服务器
					</td>
					<td align="left">
						<s:select id="select" list="theForm.resultList" listKey="uuid+','+id" listValue="name" cssStyle="width:150px;height:20px;" name="theForm.selectHostUuid">
						</s:select>
					</td>
				</tr>
				</s:if>
				<tr>
					<td class="til" width="20%" align="left"> 
						虚拟机名称
					</td>
					<td  align="left">
						<s:textfield name="theForm.NAME_EN"  id="NAME_EN" cssStyle="width:320px"/>
					</td>
				</tr> 
				<tr>
					<td class="til" width="20%" align="left"> 
						网络
					</td>
					<td  align="left">
						<s:select list="theForm.netList" listKey="NET_ID" listValue="NAME" headerKey="0" headerValue="--请选择--" name="theForm.NET" id="NET"  onchange="listIp();"></s:select>
					</td>
				</tr> 
				<tr>
					<td class="til" width="20%" align="left"> 
						IP
					</td>
					<td  align="left">
						<s:select list="theForm.ipList" listKey="IP_ID" listValue="IPADDRESS" headerKey="0" headerValue="--请选择--" name="theForm.IP" id="IP"></s:select>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						cpu最大个数
					</td>
					<td align="left">
					
						<s:textfield name="theForm.CPU" id="CPU" cssStyle="width:50px;   height:15px;"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						cpu个数
					</td>
					<td align="left">
						<s:textfield name="theForm.CURRENTCPU" id="CURRENTCPU" cssStyle="width:50px;   height:15px;"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						内存(单位:M)
					</td>
					<td align="left">
						<s:textfield name="theForm.staticMin" id="staticMin" cssStyle="width:50px;   height:15px;"/>(静态最小)
						<=<s:textfield name="theForm.dynamicMin" id="dynamicMin"  cssStyle="width:50px;   height:15px;"/>(动态最小)
						<=<s:textfield name="theForm.dynamicMax" id="dynamicMax"  cssStyle="width:50px;   height:15px;"/>(动态最大)
						<=<s:textfield name="theForm.staticMax" id="staticMax"  cssStyle="width:50px;   height:15px;"/>(静态最大)
					</td>
				</tr> 
			</table>
		</div>
		</div>
</s:form>
</body>
</html:html>
<script
	src="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.alerts.js"></script>
<link
	href="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.alerts.css"
	rel="stylesheet" type="text/css" />
