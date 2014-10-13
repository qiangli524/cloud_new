<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
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
		var name = $("#name").val();
		var type = $("#type1").val();
		var ip = $("#ip").val();
		var pattern = /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/;
		
		if(name==""){
			$("#name").attr("class","required");
			$("#name").focus();
			return false;
		}
		if(type==''){
			$("#type1").attr("class","required");
			$("#type1").focus();
			return false;
		}
		if(!pattern.exec(ip)){
			$("#ip").attr("class","ipAddress");
			$("#ip").select();
			$("#ip").focus();
			return false;
		}
		var parentId = $("#parent_id").val();
		var url = "unitedDevice_saveStorageDevice.do?"+$("#storeObj").serialize();
		w.saveStorageDevice(url,parentId);
	}
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="datastore_saveStoreDevice.do" method="post" cssStyle="storeObj" id="storeObj">
<s:hidden name="storeObj.id" id="id"/>
<s:hidden name="obj.parent_id" id="parent_id"></s:hidden>
<s:hidden name="obj.pratentId" id="pratentId"></s:hidden>
<s:hidden name="obj.vtype" id="vtype"></s:hidden>
<s:hidden name="obj.type" id="type"></s:hidden>
<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					名称:
				</td>
				<td>
					<s:textfield name="storeObj.name" cssClass="required" id="name"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					类型:
				</td>
				<td>
					<s:select list="#{'':'--请选择--','0':'本地存储','1':'SAN','2':'NAS'}" name="storeObj.type" id="type1" cssClass="required"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					IP地址:
				</td>
				<td>
					<s:textfield name="storeObj.ip" cssClass="ipAddress" id="ip"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					CACHE容量(G):
				</td>
				<td>
					<s:textfield name="storeObj.cache_space" cssClass="required" id="cache_space"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					RAID方式:
				</td>
				<td>
					<s:textfield name="storeObj.raid_way" cssClass="required" id="raid_way"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					厂商:
				</td>
				<td>
					<s:textfield name="storeObj.supporter" cssClass="required" id="supporter"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					端口数量:
				</td>
				<td>
					<s:textfield name="storeObj.port_num" cssClass="required" id="port_num"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					磁盘裸容量(G):
				</td>
				<td>
					<s:textfield name="storeObj.capacity" cssClass="required" id="capacity"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					磁盘有效容量(G):
				</td>
				<td>
					<input name="storeObj.valid_space" id="valid_space" class="required" value="<s:property value="storeObj.valid_space"/>"/>
				</td>
			</tr>
		</table>
</s:form>
</body>
