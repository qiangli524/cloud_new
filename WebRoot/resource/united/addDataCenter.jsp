<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;

	api.button({
		id : 'Ok',
		name : '添加',
		callback : add,
		focus : true
	}, {
		id : 'cancle',
		name : '取消'
	});

	function add() {
		var name = $("#name").val();
		var parent_id = '<s:property value="parent_id" />';
		var connect_id = $("#connect_id").val();
		if (name == '') {
			alert("数据中心名称不能为空");
			return false;
		}
		
		var vtype = $("#vtype").val();
		var resBusiSytemId;
		<%--
		if (vtype == 8) {
			resBusiSytemId = $("#resBusiSytemId").val();
			if (resBusiSytemId.length == 0) {
				alert("请选择业务系统");
				return false;
			}
		} --%>
		//alert("name="+name+",parent_id="+parent_id+",vtype="+vtype+",connect_id="+connect_id+",resBusiSytemId=");
		w.saveDataCenter(name, parent_id, vtype, connect_id, resBusiSytemId);
	}

	function getpool() {
		var vtype = $("#vtype").val();
		if (vtype == 1) {
			$("#resource").show();
		} else {
			$("#resource").hide();
		}
		if (vtype == 8) {
			$("#bususys").show();
		} else {
			$("#bususys").hide();
		}
	}
</script>
</head>
<body onload="getpool()">
	<s:form action="" method="post" id="theForm">
		<table width="90%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">数据中心类型：</td>
				<td><s:select
						list="#{'8':'物理机资源','7':'空闲资源','1':'vmware','2':'xen','3':'kvm','4':'power vm','5':'网络','6':'存储'}"
						name="vtype" id="vtype" onchange="getpool()" cssClass="select-1"/></td>
			</tr>
			<tr id="resource">
				<td class="til">资源池：</td>
				<td><s:select list="resultList" listKey="resourceKey"
						listValue="resourceValue" id="connect_id" cssClass="select-1"/></td>
			</tr>
			<tr>
				<td class="til">数据中心名称<font color="red">&nbsp;*</font>：</td>
				<td><s:textfield name="name" id="name" size="30" cssClass="inpt-2 vm"/></td>
			</tr>
<%--			<tr id="bususys">--%>
<%--				<td class="til">对应业务系统：</td>--%>
<%--				<td><s:select list="busiList" listKey="id" listValue="name"  id="resBusiSytemId" name = "resBusiSytemId"></s:select></td>--%>
<%--			</tr>--%>

		</table>
	</s:form>
</body>
