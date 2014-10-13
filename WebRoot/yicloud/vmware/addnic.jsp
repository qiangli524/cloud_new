<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
		var hostCode = '<%=request.getAttribute("hostCode")%>';
		function submitRequest(theForm) {
				var vmcode = theForm.vmcode.value;
				var type = $("#type").find("option:selected").val();
				var portgroup = $("#portgroup").find("option:selected").text();
			if (confirm('确认保存虚拟网卡吗?')) {
				bar(vmcode,"正在添加网卡");
				 $.getJSON("vmw_savenic.do?vmcode="+vmcode+"&type="+type+"&portgroup="+portgroup+"&hostCode="+hostCode,{'time':new Date().toString()},function(data){
				 	if(data.result==1) {
				 		barEnd(vmcode,"成功添加网卡");
				 	} else {
				 		barEnd(vmcode,"添加网卡失败");
				 	}
				 //window.location.reload();
				 });
			 }
		}
		
		
		$.dialog.setting.zIndex = 100000;
		function bar(idstr,contents){
		$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}

	function barEnd(idstr,contents){
		$.dialog.list[idstr].content(contents,false,false);
		$.dialog.list[idstr].time(2);
	}
		
	
</script>
</head>
<body  style="width: 840px;height: 450px;margin: 10px;">
	<s:form action="vmw_savenic.do" method="post" id="theForm">
	<s:hidden id="vmcode" value="%{#request.entityId}"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize" >
			<tr>
				<td class="til" width="20%" >
					虚拟机名称：
				</td>
				<td>
					<s:property value="#request.name"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					适配器类型：
				</td>
				<td>
					<s:select list="#{'E1000':'E1000','VMXNET2':'VMXNET 2(增强型)','VMXNET3':'VMXNET 3'}" id="type"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					具有指定标签的命名网络:
				</td>
				<td>
					<s:select list="#request.list" id="portgroup" listKey="pguuid" listValue="name">
					</s:select>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
