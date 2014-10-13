<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script type="text/javascript">
	function submitRequest(theForm){
		if(theForm.NAME.value==""){
			alert("请输入快照名称！");
			return false;
		}
		var name = theForm.NAME.value;
		var desc = theForm.DESC.value;
		var entity_id = <%=request.getAttribute("entity_id")%>;
		var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
		var host_uuid = '<%=request.getAttribute("host_uuid")%>';
		url = "xen_createVMSnapShot.do?name="+ encodeURI(encodeURI(name)) + "&entity_id=" + entity_id + "&desc="+ encodeURI(encodeURI(desc))+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid;
		mask('正在创建快照，请稍后...','0.5');
		$.getJSON(url,{"time":new Date().toString()},function(data){
			if(data.responseCode == 1){
				alert("创建快照成功!");
				removeMask();
				window.document.location.reload();
			}else if(data.responseCode == -1 || data == 'error'){
				alert("创建快照失败!");
				window.document.location.reload();
			}
		});
	}
	</script>
</head>
<body>
<s:form action="xen_hostInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="20%" align="left"> 
						快照名称
					</td>
					<td  align="left">
						<s:textfield name="theForm.NAME"  id="NAME"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						描述
					</td>
					<td align="left">
						<s:textarea name="theForm.DESC" cols="80" rows="4" id="DESC"/>
					</td>
				</tr> 
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:resetForm(document.getElementById('theForm'));"/>
					</td>
				</tr>
			</table>
		</div>
</s:form>
</body>
