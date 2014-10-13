<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
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
		var diskId = $("#diskId").val();
		if (diskId.length == 0) {
			$("#devicespan").empty();
			var deviceId = $("#deviceId").val();
			if (deviceId == -1) {
				$("#devicespan").append("<font color='red'>ERROR：请选择存储设备</font>");
				return false;
			}
		}
		
		$("#namespan").empty();
		var diskName = $("#diskName").val();
		if (diskName.length == 0) {
			$("#namespan").append("<font color='red'>ERROR：请填写磁盘组名称</font>");
			return false;
		}
		
		$("#raidspan").empty();
		var raidLevel = $("#raidLevel").val();
		if (raidLevel == -1) {
			$("#raidspan").append("<font color='red'>ERROR：请选择RAID级别</font>");
			return false;
		}
		
	<%--	var reg = new RegExp("^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
		
		$("#spacespan").empty();
		var space = $("#space").val();
		if (space.length == 0) {
			$("#spacespan").append("<font color='red'>ERROR：请填写最大连续空闲空间</font>");
			return false;
		} else if (! reg.test(space)) {
			$("#spacespan").append("<font color='red'>ERROR：请填写非负数字</font>");
			return false;
		}--%>
		
	    w.saveDiskGroup($("#theForm").serialize());
	}
	
	$(function(){
		var flag = $("#flag").val();
		$("[name='selectObj_id']").unbind().live("click",function(){
				w.$.dialog({
	    			id:'addDisk',
	    			title:'选择磁盘',
	    			width: '800px',
	    			height: '500px',
	    		    lock:true,
	    			content: 'url:diskgroup_addDisk.do?flag='+flag
		    		});
			 });
	});

	function listDiskInfo(diskId,diskNumber,diskSeq,disk_name){
		$("#disk_id").attr("value",diskId);
		$("#disk_number").attr("value",diskNumber);
		$("#disk_seq").attr("value",diskSeq);
		$("#disk_name").attr("value",disk_name);
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="diskGroupObj.uuid" id="diskId"></s:hidden>
		<s:hidden name="flag" id="flag"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<s:if test="diskGroupObj==null||diskGroupObj.uuid==null">
					<tr>
						<td class="til">
							存储设备：<font color="red">*</font>
						</td>
						<td>
							<s:select cssClass="select-1" list="deviceList" listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--" id="deviceId" name="diskGroupObj.storeDeviceId"></s:select>
							<span id="devicespan"></span>
						</td>
					</tr>
				</s:if>
				<tr>
					<td class="til">
						磁盘组名称:<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="diskGroupObj.name" id="diskName"></s:textfield>
						<span id="namespan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						RAID级别:<font color="red">*</font>
					</td>
					<td>
						<s:select cssClass="select-1" list="#{'-1':'--请选择--','0':'RAID 0','1':'RAID 1','2':'RAID 2','3':'RAID 3','4':'RAID 4','5':'RAID 5',
							'10':'RAID 10'}" name="diskGroupObj.raidLevel" id="raidLevel"></s:select>
						<span id="raidspan"></span>
					</td>
				</tr>
				<tr>
				<td class="til">
					磁盘<font color="red">*</font>
				</td>
				<td>
					<s:hidden name="diskGroupObj.disk_id" id="disk_id"></s:hidden>
					<s:hidden name="diskGroupObj.disk_number" id="disk_number"></s:hidden>
					<s:hidden name="diskGroupObj.disk_seq" id="disk_seq"></s:hidden>
					<s:textarea name="diskGroupObj.disk_name" id="disk_name" disabled="true" cssClass="vm"></s:textarea>
					<span class="ubtn-2 mgl-20"><input type="button"  value="选择" name="selectObj_id"/></span>
				</td>
			</tr>
			</table>
		</div>
	</s:form>
</body>
</html:html>