<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<title></title>
<script type="text/javascript">
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
	
	function submitRequest(){
		var formData = $("#theForm").serialize();
		var dialogId = $("#dialogId").val();
		var name = $("#name").val();
		if(name=="" || name==null){
			alert('请先填写设备名称');
			return false;
		}
		var device_num = $("#device_num").val();
		if(device_num=="" || device_num==null){
			alert('请先填写设备编号');
			return false;
		}
		var supporter = $("#supporter").val();
		if(supporter=="" || supporter==null){
			alert('请先填写厂商');
			return false;
		}
		var location = $("#location").val();
		if(location=="" || location==null){
			alert('请先填写存放位置');
			return false;
		}
		var type = $("#type").val();
		if(type=="" || type==null){
			alert('请先填写类型');
			return false;
		}
		var raid_way = $("#raid_way").val();
		if(raid_way=="" || raid_way==null){
			alert('请先填写RAID方式');
			return false;
		}
		var ip = $("#ip").val();
		if(ip=="" || ip==null){
			alert('请先填写IP地址');
			return false;
		}
		var port_num = $("#port_num").val();
		if(port_num=="" || port_num==null){
			alert('请先填写端口数量');
			return false;
		}
		var cache_space = $("#cache_space").val();
		if(cache_space=="" || cache_space==null){
			alert('请先填写CACHE容量');
			return false;
		}
		var capacity = $("#capacity").val();
		if(capacity=="" || capacity==null){
			alert('请先填写磁盘裸容量');
			return false;
		}
		var valid_space = $("#valid_space").val();
		if(valid_space=="" || valid_space==null){
			alert('请先填写磁盘有效容量');
			return false;
		}
		w.saveStoreDevice(dialogId,formData);
	}
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="datastore_saveStoreDevice.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="storeDeviceObj.id" id="id"/>
<s:hidden name="dialogId" id="dialogId"/>
<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					设备名称:<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="storeDeviceObj.name" cssClass="required" id="name"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					设备编号:<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="storeDeviceObj.device_num" cssClass="required" id="device_num"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					厂商:<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="storeDeviceObj.supporter" cssClass="required" id="supporter"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					存放位置:<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="storeDeviceObj.location" cssClass="required" id="location"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					类型:<font color="red">*</font>
				</td>
				<td>
					<s:select list="#{'':'--请选择--','0':'本地存储','1':'SAN','2':'NAS'}" name="storeDeviceObj.type" id="type" cssClass="required"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					RAID方式:<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="storeDeviceObj.raid_way" cssClass="required" id="raid_way"></s:textfield>
				</td>
			</tr>
			
			<tr>
				<td class="til">
					IP地址:<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="storeDeviceObj.ip" cssClass="ipAddress" id="ip"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					端口数量:<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="storeDeviceObj.port_num" onkeyup="value=value.replace(/[^\d]/g,'')" cssClass="required" id="port_num"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					CACHE容量(G):<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="storeDeviceObj.cache_space" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" cssClass="required" id="cache_space"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					磁盘裸容量(G):<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="storeDeviceObj.capacity" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" cssClass="required" id="capacity"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					磁盘有效容量(G):<font color="red">*</font>
				</td>
				<td>
					<input name="storeDeviceObj.valid_space" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" id="valid_space" class="required" value="<s:property value="storeDeviceObj.valid_space"/>"/>
				</td>
			</tr>
		</table>
</s:form>
</body>
