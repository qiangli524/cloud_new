<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Random" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script type="text/javascript">
	 var createType = '<%=request.getAttribute("type")%>';
		
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
		
		function getDsList(){
			var storageType=$("input[name='theForm.storageId']:checked").val();
			if(storageType==1){
				document.getElementById('virtual').style.display="" ; 
				document.getElementById('iso').style.display="none" ; 
				document.getElementById('virtualDisk').style.display="";
				document.getElementById('isoMall').style.display="none";
				document.getElementById('createStyle').style.display="";
				document.getElementById('show_user').style.display="none";
				document.getElementById('username').style.display="none";
				document.getElementById('password').style.display="none";
				
				var createStyle = $("input[name='theForm.createStyle']:checked").val();
				if(createStyle==1){
					api.size(600,300);
					document.getElementById('hostList').style.display="";
				}else{
					api.size(600,250);
				}
				
			}else{
				document.getElementById('virtual').style.display="none" ; 
				document.getElementById('iso').style.display="" ; 
				document.getElementById('virtualDisk').style.display="none";
				document.getElementById('isoMall').style.display="";
				document.getElementById('createStyle').style.display="none";
				document.getElementById('show_user').style.display="";
				document.getElementById('srList').style.display="none";
				document.getElementById('hostList').style.display="none";
				getMessage();
				
			}
		}
		
	function pageOnLoad() {
		 getDsList();
		 getHostList(0);
		 //showUser();
		 getMessage();
	}
	<%!
	Random random = new Random();
	%>
	
	function scanPath(){
		var number = <%=random.nextInt(1000)%>;
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
		var url = "xen_getSRList.do?path="+encodeURI(encodeURI(path))+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid+"&number="+number;
		$.ajaxSettings.async = false;
		$.getJSON(url,function(data){
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
    	api.size(600,460);
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
  		/*if(check == value){
    		opt.selected=true;
  		}*/
  		return opt;
	}
	
	function submitRequest(){
	//172.21.3.58:/var/test-nfs/xen
	//\\172.21.43.177\YiCloud\ISO
		var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
		var host_uuid = '<%=request.getAttribute("host_uuid")%>';
		var host_id = '<%=request.getAttribute("host_id")%>';
		var nodeId = '<%=request.getAttribute("nodeId")%>';
		var theForm = document.getElementById("theForm");
		var name =theForm.NAME.value; 
		var share = theForm.share.value;
		var path1 = theForm.path1.value;
		var path2 = theForm.path2.value;
		
		var path = '';
		if(path1!=''){
			path = path1;
		}else{
			path = path2;
		}
		var desc = theForm.desc.value;
		var storageId = $("input[name='theForm.storageId']:checked").val();
		var createStyle='';
		var iso_type =''; 
		var username = '';
		var password = '';
		if(storageId==1){
			createStyle = $("input[name='theForm.createStyle']:checked").val();
		}else{
			createStyle='';
			iso_type = theForm.storage_iso.value;
			if(iso_type==5){
				username = theForm.USERNAME.value;
				password = theForm.PASSWORD.value;
			}
		}
		var store_uuid = theForm.osId.value;
		var bind_uuid = theForm.uuid.value;
		var url="xen_createSR.do?pool_uuid="+pool_uuid+"&host_uuid="
		+host_uuid+"&name="+encodeURI(encodeURI(name))+"&path="+path+"&desc="+encodeURI(encodeURI(desc))+"&host_id="+host_id+"&storageId="
		+storageId+"&createStyle="+createStyle+"&share="+share+"&store_uuid="+store_uuid+"&bind_uuid="+bind_uuid
		+"&iso_type="+iso_type+"&username="+username+"&password="+password;
		w.addNewStore(url,nodeId);
	}
	
	function getHostList(style){
		if(style==1){
			document.getElementById('hostList').style.display="";
			document.getElementById('isoMall').style.display="none";
			document.getElementById('virtualDisk').style.display="";
			api.size(600,300);
		}else{
			document.getElementById('hostList').style.display="none";
			document.getElementById('isoMall').style.display="";
			document.getElementById('virtualDisk').style.display="none";
			document.getElementById('srList').style.display="none";
			api.size(600,250);
			
		}
	}
	
	function getMessage(){
		var isCheck = $("#user").is(':checked');
		var storageId = $("input[name='theForm.storageId']:checked").val();
		if(isCheck && storageId==2){
			document.getElementById('username').style.display="";
			document.getElementById('password').style.display="";
			api.size(600,380);
		}else{
			document.getElementById('username').style.display="none";
			document.getElementById('password').style.display="none";
			api.size(600,330);
		}
		
	}
	function showUser(){
		iso_type = theForm.storage_iso.value;
		if(iso_type==5){
			document.getElementById('show_user').style.display="";
		}
		else{
			document.getElementById('show_user').style.display="none";
		}
	
		
	}
	$(function(){
		if(createType=='iso'){
			$("#storageId2").attr("checked","2");
		}
	});
	</script>

</head>
<body class="pop-body scrollbody" onload="pageOnLoad();">
	<s:form action="cluster_saveCluster.do" method="post" id="theForm" cssStyle="theForm">
	<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til" width="15%">
						选择存储类型
					</td>
					<td>
						<input type="radio" name="theForm.storageId" id="storageId1" checked="checked" value="1" onclick="getDsList();"/><label for="storageId1">虚拟磁盘存储</label>
						<input type="radio" name="theForm.storageId" id="storageId2" value="2" onclick="getDsList();"/><label for="storageId2">ISO库</label>
					</td>
				</tr> 
				<tr id="virtual" style="display: none;">
					<td class="til" >
						存储类型
					</td>
					<td width="60%">
						<s:select list="#{'1':'NFS VHD','2':'软件 iSCSI','3':'硬件 HBA','4':'StorageLink 技术'}"
							name="theForm.storageId" id="storageId" cssStyle="width:250px;   height:25px;" ></s:select>
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
			<td class="til">描述</td>
			<td >
			<s:textfield name="theForm.DESC" cssStyle="width:200px;   height:20px;" id="desc"></s:textfield>
			</td>
		</tr>
		<tr>
			<td class="til">与所在集群其他主机共享</td>
			<td >
			<s:select list="#{'true':'是','false':'否'}" cssStyle="width:100px;   height:20px;" name="theForm.share" id="share"></s:select>
			</td>
		</tr>
		<tr id="createStyle" style="display:none;">
			<td class="til" width="15%">
				创建方式
			</td>
			<td>
				<input type="radio" name="theForm.createStyle" id="createStyle1" checked="checked" value="0" onclick="getHostList(0);"/><label>创建新 SR</label>
				<input type="radio" name="theForm.createStyle" id="createStyle2" value="1" onclick="getHostList(1);"/><label>重新连接已有 SR</label>
			</td>
		</tr> 
		<tr>
			<td class="til">共享名称</td>
			<td id="virtualDisk" style="display:none;">
			<s:textfield name="theForm.path" id="path1" cssStyle="width:200px;   height:20px;"></s:textfield><input type="button" value="扫描" onclick="scanPath();"/>
			示例：server:/path
			</td>
			<td id="isoMall" style="display:none;">
			<s:textfield name="theForm.path" id="path2" cssStyle="width:200px;   height:20px;"></s:textfield>
			示例：server:/path
			</td>
		</tr>
		<tr id="show_user" style="display: none;">
			<td class="til">
			<input type="checkbox" onclick="getMessage();" id="user"/>使用其他用户名
			</td>
		</tr>
		<tr id="username" style="display: none;">
			<td class="til">
				用户名
			</td>
			<td >
				<s:textfield name="theForm.USERNAME" id="USERNAME"></s:textfield>
			</td>
		</tr>
		<tr id="password" style="display: none;">
			<td class="til">
				密码
			</td>
			<td >
				<s:textfield name="theForm.PASSWORD" id="PASSWORD"></s:textfield>
			</td>
		</tr>
		
		<tr id="hostList" style="display: none;">
			<td class="til">
				选择需要挂载的主机
			</td>
			<td>
				<s:select list="theForm.resultList" name="theForm.HOST_ID" id="uuid" listKey="uuid" listValue="name"  cssStyle="width:200px;   height:20px;"></s:select>
			</td>
		</tr>
		<tr id="srList" style="display: none;">
			<td class="til">
				选择已存在的SR重新挂载至主机
			</td>
			<td>
				<s:select list="#{'':''}" name="theForm.osId" id="osId" multiple="multiple" size="100" cssStyle="width:270px;   height:150px;"></s:select>
			</td>
		</tr>
		</table>
		</div>
	</s:form>
</body>
