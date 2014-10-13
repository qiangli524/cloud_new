<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
$(function(){
	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok',
	     name: '添加',
	     callback:add,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	
	function add(){
		var id = '<s:property value="id" />';
		var uuid='<s:property value="uuid" />';
		var connect_id = '<s:property value="connect_id" />';
		var vtype = '<s:property value="vtype" />';
		
		
		var nfs = $("#NFSStore").attr("checked");
		var lun = $("#LUNStore").attr("checked");
		if(nfs){
			var host = $("#remoteHost").val();
			var path = $("#remotePath").val();
			var mode = $('input[name="datastoreUnitedVO.accessMode"]:checked').val();
			var name = $("#datastoreName").val();
			
			if(host==""){
			alert("服务器不能为空!");
			$("#remoteHost").focus();
			return false;
			}
			if(path==""){
				alert("文件夹不能为空!");
				$("#remotePath").focus();
				return false;
			}
			if(name==""){
				alert("数据存储名字不能为空!");
				$("#datastoreName").focus();
				return false;
			}
			
			var str = "&datastoreUnitedVO.remoteHost="+host
					+"&datastoreUnitedVO.remotePath="+path
					+"&datastoreUnitedVO.accessMode="+mode
					+"&datastoreUnitedVO.datastoreName="+name
					+"&datastoreUnitedVO.datastoreType=NFS";
			w.saveStore(id,uuid,connect_id,vtype,str);
		}
		if(lun){
			//alert("");
		}
	
	}
	function isLUN(){
		if($("#LUNStore").attr("checked")){
			for(i=1;i<5;i++){
				$("#tr_lun"+i).show();
			}
		}else{
			for(i=1;i<5;i++){
				$("#tr_lun"+i).hide();
			}
		}
	}
	function isNFS(){
		if($("#NFSStore").attr("checked")){
			for(i=1;i<5;i++){
				$("#tr_nfs"+i).show();
			}
		}else{
			for(i=1;i<5;i++){
				$("#tr_nfs"+i).hide();
			}
		}
	}
	$("#LUNStore").click(function() {
		isLUN();
		isNFS();
	});
	$("#NFSStore").click(function() {
		isLUN();
		isNFS();
	});
	$("#maxSize").click(function() {
		$("#customSizeNum").attr("disabled",true);
	});
	$("#customSize").click(function() {
		$("#customSizeNum").attr("disabled",false);
	});
	$(window).load(function () {
	  $("#NFSStore").attr("checked", true);
	  $("#maxSize").attr("checked", true);
	  $("#customSizeNum").attr("disabled",true);
	  $("#accessModeRO").attr("checked", true);
	  isLUN();
	  isNFS();
	});
});
</script>
</head>
<body class="pop-body scrollbody" >
	<s:form action="" method="post" id="datastoreUnitedVO">
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td colspan="2">
				<input type="radio" id="LUNStore" name="radioStore" />&nbsp;磁盘/LUN <br /> 
					&nbsp;&nbsp;&nbsp;&nbsp;(在光纤通道、iSCSI或本地SCSI磁盘上创建数据存储，或挂载现有VMFS卷。)<br />
				<input type="radio" id="NFSStore" name="radioStore" />&nbsp;网络文件系统<br /> 
					&nbsp;&nbsp;&nbsp;&nbsp;(如果要创建网络文件系统，请选择此选项。)</td>
			</tr>
			<tr id="tr_nfs1">
				<td class="til" width="25%">服务器：</td>
				<td><s:textfield name="datastoreUnitedVO.remoteHost" id="remoteHost" cssClass="txt"/></td>
			</tr>
			<tr id="tr_nfs2">
				<td class="til" width="25%">文件夹：</td>
				<td><s:textfield name="datastoreUnitedVO.remotePath" id="remotePath" cssClass="txt"/></td>
			</tr>
			<tr id="tr_nfs3">
				<td class="til"  width="25%">访问模式：</td>
				<td>
					<input type="radio" id="accessModeRO" name="datastoreUnitedVO.accessMode" value="readWrite" />&nbsp;读写 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="accessModeRW" name="datastoreUnitedVO.accessMode" value="readOnly" />&nbsp;只读
				</td>
			</tr>
			<tr id="tr_nfs4">
				<td class="til"  width="25%">数据存储名称：</td>
				<td><s:textfield name="datastoreUnitedVO.datastoreName" id="datastoreName" cssClass="txt"/></td>
			</tr>
			
			<tr id="tr_lun1">
				<td class="til"  width="25%">选择磁盘/LUN：</td>
				<td><s:select id="vmotionRate" name="clusterUnitedVO.drsVO.vmotionRate" list="#{'--请选择--':'--请选择--'}" ></s:select></td>
			</tr>
			<tr id="tr_lun2">
				<td class="til"  width="25%">文件系统版本：</td>
				<td>
					<input type="radio" id="Version" name="Version" />&nbsp;VMFS-5：    选此项可启用附加功能，例如支持2TB以上大小。<br />
					<input type="radio" id="Version" name="Version" />&nbsp;VMFS-3 ：    ESX版本低于5.0主机访问此数据存储，请选此项。</td>
				</td>
			</tr>
			<tr id="tr_lun3">
				<td class="til"  width="25%">数据存储名称</td>
				<td><s:textfield name="datastoreUnitedVO.datastoreName" id="datastoreName" cssClass="txt"/></td>
			</tr>
			<tr id="tr_lun4">
				<td class="til"  width="25%">数据存储容量：</td>
				<td>
					<input type="radio" id="maxSize" name="storeSize" />&nbsp;最大可用空间<br />
					<input type="radio" id="customSize" name="storeSize" />&nbsp;自定义空间<s:textfield name="datastoreUnitedVO.datastoreName" id="customSizeNum" cssClass="txt"/>GB(共341GB可用空间)
				</td>
			</tr>
		</table>
	</s:form>
</body>
