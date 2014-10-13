<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<head>
<title></title>
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
		var lunId = $("#lunId").val();
		if (lunId.length == 0) {
			$("#devicespan").empty();
			var deviceId = $("#deviceId").val();
			if (deviceId == -1) {
				$("#devicespan").append("<font color='red'>ERROR：请选择存储设备</font>");
				return false;
			}
			
			$("#diskspan").empty();
			var diskGroup = $("#diskGroup").val();
			if (diskGroup == -1) {
				$("#diskspan").append("<font color='red'>ERROR：请选择磁盘组</font>");
				return false;
			}
		}
		
		$("#namespan").empty();
		var lunName = $("#lunName").val();
		if (lunName.length == 0) {
			$("#namespan").append("<font color='red'>ERROR：请填写LUN名称</font>");
			return false;
		}
		
		
		$("#depthspan").empty();
		var depth = $("#depth").val();
		if (depth == -1) {
			$("#depthspan").append("<font color='red'>ERROR：请选择分条深度</font>");
			return false;
		}
		
		
		$("#typespan").empty();
		var write = $("#lun_type").val();
		if (write == -1) {
			$("#typespan").append("<font color='red'>ERROR：请选择类型</font>");
			return false;
		}
	    w.saveLun($("#theForm").serialize());
	}
	
	$(function(){
		$("#deviceId").change(function(){
			var deviceId = $("#deviceId").val();
			$("#diskGroup").empty();
			$.ajax({
				type:'post',
				url:'lun_queryDiskGroupList.do?lunObj.deviceId='+deviceId,
				dataType:'json',
				success:function(msg){
					for ( var i = 0; i < msg.length; i++) {
						$("<option value="+msg[i].uuid+">"+msg[i].name+"</option>").appendTo("#diskGroup");
					}
				}
			});
		});
		
		$("#diskGroup").change(function(){
			var diskGroupId = $("#diskGroup").val();
			$.ajax({
				type:'post',
				dataType:'json',
				url:'lun_queryDiskInfo.do?lunObj.diskGroupId='+diskGroupId,
				async:false,
				success:function(msg){
					$("#raid").text(msg.raidLevel);
					$("#freespace").text(msg.maxSeriesFreeCapacity + "GB");
				}
			});
		});
	})
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="lunObj.uuid" id="lunId"></s:hidden>
		<s:if test="lunObj != null && lunObj.diskGroupId != null && lunObj.diskGroupId != ''">
			<s:hidden name="lunObj.diskGroupId"></s:hidden>
		</s:if>
		<div>
			<s:if test="lunObj==null || lunObj.diskGroupId == null || lunObj.diskGroupId == ''">
				<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
					<tr>
						<td align="left" colspan="10">
							选择存储设备
						</td>
					</tr>
					<tr>
						<td class="til" width="250px;">
							存储设备名称：<font color="red">*</font>
						</td>
						<td>
							<s:select cssClass="select-1" list="deviceList" listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--" id="deviceId" name="lunObj.deviceId"></s:select>
							<span id="devicespan"></span>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
					<tr>
						<td align="left" colspan="10">
							选择磁盘组
						</td>
					</tr>
					<tr>
						<td class="til" width="250px;">
							磁盘组名称：<font color="red">*</font>
						</td>
						<td>
							<select class="select-1" id="diskGroup" name="lunObj.diskGroupId">
								<option value="-1">
									--请选择--
								</option>
							</select>
							<span id="diskspan"></span>
						</td>
					</tr>
					<tr>
						<td class="til">
							RAID级别：
						</td>
						<td>
							<span id="raid"></span>
						</td>
					</tr>
				</table>
			</s:if>
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<tr>
						<td align="left" colspan="10">
							配置LUN
						</td>
					</tr>
				<tr>
					<td class="til" width="250px;">
						名称:<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="lunObj.name" id="lunName"></s:textfield>
						<span id="namespan"></span>
					</td>
				</tr>
				<%--<s:if test="lunObj==null || lunObj.uuid == null">
					<tr>
						<td class="til">
							数量:<font color="red">*</font>
						</td>
						<td>
							<s:textfield name="lunObj.num" id="num"></s:textfield>
							<span id="numspan"></span>
						</td>
					</tr>
				</s:if>--%>
				<tr>
					<td class="til">
						容量：<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="lunObj.capacity" id="capacity"></s:textfield>GB
						<span id="capacityspan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						分条深度：<font color="red">*</font>
					</td>
					<td>
						<s:select cssClass="select-1" list="#{'-1':'--请选择--','32':'32KB','64':'64KB','128':'128KB','256':'256KB','512':'512KB','1024':'1MB' }"
							name="lunObj.depth" id="depth" ></s:select>
						<span id="depthspan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						类型：<font color="red">*</font>
					</td>
					<td>
						<s:select cssClass="select-1" list="#{'-1':'--请选择--','Fibre':'Fibre','EDFA':'EDFA','DSF':'DSF'}"
							name="lunObj.lun_type" id="lun_type" ></s:select>
						<span id="typespan"></span>
					</td>
				</tr>
				<%--<tr>
					<td class="til">
						归属控制器：<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'-1':'--请选择--','1':'Controller A','2':'Controller B','3':'Controller C'}"
							name="lunObj.controller" id="controller" ></s:select>
						<span id="controllerspan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						写策略：<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'-1':'--请选择--','1':'回写','2':'不回写'}"
							name="lunObj.write_tactics" id="write" ></s:select>
						<span id="writespan"></span>
					</td>
				</tr>--%>
			</table>
		</div>
	</s:form>
</body>
</html:html>