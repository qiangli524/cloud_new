<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script type="text/javascript">
	 var createType = '<%=request.getAttribute("type")%>';
	 var srUuid = '<%=request.getAttribute("sr_uuid")%>';
		
	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok',
	     callback:submitRequest,
	     name: '确定',
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });

	function scanPath(){
		var path1 = theForm.path1.value;
		var path2 = theForm.path2.value;
		
		var path = '';
		if(path1!=''){
			path = path1;
		}else{
			path = path2;
		}

		var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
		var host_uuid = '<%=request.getAttribute("host_uuid")%>';
		var url = "xen_getSRList.do?path="+encodeURI(encodeURI(path))+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid;
		$.ajaxSettings.async = false;
		$.getJSON(url,{"time":new Date().toString()},function(data){
			var  SelectNode = document.getElementById("osId");
			$("#osId").empty();
			SelectNode.length=0;
     		if(data != null){
     			$.each(data,function(k,v){
					$.each(v,function(key,value){
						SelectNode.appendChild(createSelect(key,value));
					});
				});
     		}
		});
		document.getElementById('srList').style.display="";
    	api.size(600,280);
	}

	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		if(srUuid!=value){
  			opt.setAttribute("disabled",true);
  		}
  		opt.appendChild(document.createTextNode(text));
  		return opt;
	}
	
	function submitRequest(){
		var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
		var host_uuid = '<%=request.getAttribute("host_uuid")%>';
		var theForm = document.getElementById("theForm");
		var srShared = '<%=request.getAttribute("srShared")%>';
		var name =theForm.NAME.value; 
		var path1 = theForm.path1.value;
		var path2 = theForm.path2.value;
		var path3 = theForm.path3.value;
		
		var path = '';
		if(path1!=''){
			path = path1;
		}else if(path2!=''){
			path = path2;
		}else{
			path=path3;
		}
		if(name==""){
			alert("名称不能为空！");
			return false;
		}
		if(path==''){
			alert("共享名称名称不能为空！");
			return false;
		}
		var username = '';
		var password = '';
		
		var iso_type = theForm.storage_iso.value;
		if(iso_type==5){
			username = $("#USERNAME").val();
			password = $("#PASSWORD").val();
		}
		var store_uuid = "";
		if(createType=='nfs'){
			store_uuid = theForm.osId.value;
			if(store_uuid==''){
				alert("请选择重新连接现有SR");
				return false;
			}
		}else{
			store_uuid=srUuid;
		}
		
		var url="xen_reConnSr.do?pool_uuid="+pool_uuid+"&host_uuid="+host_uuid+"&name="+encodeURI(encodeURI(name))
		+"&path="+path+"&share="+srShared+"&store_uuid="+store_uuid+"&username="+username+"&password="+password;
		w.reConnSr(url);
	}
	
	function getMessage(){
		var isCheck = $("#user").is(':checked');
		var storageId = $("input[name='theForm.storageId']:checked").val();
		if(isCheck && storageId==2){
			$("#username").attr("disabled",false);
			$("#password").attr("disabled",false);
			$("#USERNAME").attr("readonly",false);
			$("#PASSWORD").attr("readonly",false);
			$("#tduser").css("color","#0067ca");
			$("#tdpass").css("color","#0067ca");
		}else{
			$("#username").attr("disabled",true);
			$("#password").attr("disabled",true);
			$("#USERNAME").attr("readonly",true);
			$("#PASSWORD").attr("readonly",true);
			$("#tduser").css("color","#c0c0c0");
			$("#tdpass").css("color","#c0c0c0");
		}
	}
	function showUser(){
		var iso_type = theForm.storage_iso.value;
		if(iso_type==5){
			document.getElementById('windowsCIFS').style.display="";
			document.getElementById('isoMall').style.display="none";
			document.getElementById('show_user').style.display="";
			api.size(600,220);
		}
		else{
			document.getElementById('show_user').style.display="none";
			document.getElementById('windowsCIFS').style.display="none";
			document.getElementById('isoMall').style.display="";
			api.size(600,170);
		}
	}
	
	$(function(){
		if(createType=='iso'){
			$("#storageId2").attr("checked","2");
			$("#storageId1").attr("disabled",true);
			$("#iso").attr("style","display=''");
			$("#windowsCIFS").attr("style","display=''");
			$("#show_user").attr("style","display=''");
			$("#username").attr("disabled",true);
			$("#password").attr("disabled",true);
			$("#USERNAME").attr("readonly",true);
			$("#PASSWORD").attr("readonly",true);
			$("#tduser").css("color","#c0c0c0");
			$("#tdpass").css("color","#c0c0c0");
		}else if(createType=='nfs_iso'){
			$("#storageId2").attr("checked","2");
			$("#storageId1").attr("disabled",true);
			$("#isoMall").attr("style","display=''");
		}else if(createType=='nfs'){
			$("#storageId1").attr("checked","1");
			$("#storageId2").attr("disabled",true);
			$("#virtualDisk").attr("style","display=''");
		}
	});
	$(window).load(function(){
		if(createType=='iso'){
			api.size(600,220);
		}else if(createType=='nfs_iso'){
			api.size(600,130);
		}else if(createType=='nfs'){
			api.size(600,130);
		}
	});
	</script>
</head>
<body class="pop-body scrollbody" onload="pageOnLoad();">
	<s:form action="cluster_saveCluster.do" method="post" id="theForm" cssStyle="theForm">
	<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="15%">
						选择存储类型
					</td>
					<td>
						<input type="radio" name="theForm.storageId" id="storageId1" value="1" onclick="getDsList();"/><label for="storageId1">虚拟磁盘存储</label>
						<input type="radio" name="theForm.storageId" id="storageId2" value="2" onclick="getDsList();"/><label for="storageId2">ISO库</label>
					</td>
				</tr> 
				<tr id="iso"  style="display: none;">
				<td class="til">
					存储类型
				</td>
				<td width="100%">
					<s:select list="#{'5':'Windows 文件共享(CIFS)','6':'NFS ISO'}" name="theForm.storageId" cssStyle="width:250px;   height:25px;" id="storage_iso" onclick="showUser();"></s:select>
				</td>
			</tr>
		<tr>
			<td class="til">名称</td>
			<td>
			<s:textfield name="theForm.NAME" cssStyle="width:200px;   height:20px;" id="NAME"></s:textfield>
			</td>
		</tr>
		
		<tr>
			<td class="til">共享名称</td>
			<td id="virtualDisk" style="display:none;">
				<s:textfield name="theForm.path" id="path1" cssStyle="width:200px;   height:20px;"></s:textfield><input type="button" value="扫描" onclick="scanPath();"/>
				示例：server:/path
			</td>
			<td id="windowsCIFS" style="display:none;">
				<s:textfield name="theForm.path" id="path2" cssStyle="width:200px;   height:20px;"></s:textfield>
				示例：\\server\sharename
			</td>
			<td id="isoMall" style="display:none;">
				<s:textfield name="theForm.path" id="path3" cssStyle="width:200px;   height:20px;"></s:textfield>
				示例：server:/path
			</td>
		</tr>
		<tr id="show_user" style="display: none;">
			<td class="til">
				<input type="checkbox" onclick="getMessage();" id="user"/>使用其他用户名
			</td>
			<td>
				<table id = "userpass">
					<tr id="username" style="border: 0">
						<td style="border-width: 0 0 0 0;color: #c0c0c0" id="tduser">
							用户名:
						</td>
						<td style="border-width: 0 0 0 0">
							<s:textfield name="theForm.USERNAME" id="USERNAME"></s:textfield>
						</td>
					</tr>
					<tr id="password" style="border: 0">
						<td style="border-width: 0 0 0 0;color: #c0c0c0" id="tdpass">
							密码:
						</td>
						<td style="border-width: 0 0 0 0;">
							<s:textfield name="theForm.PASSWORD" id="PASSWORD"></s:textfield>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr id="srList" style="display: none;">
			<td class="til">
				重新连接现有SR(R):
			</td>
			<td>
				<s:select list="#{'':''}" name="theForm.osId" id="osId" multiple="multiple" size="100" cssStyle="width:270px;   height:150px;"></s:select>
			</td>
		</tr>
		</table>
		</div>
	</s:form>
</body>
