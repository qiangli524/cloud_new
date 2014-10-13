<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
	function mount(uuid){
		var url = "tree_mountISO.do?vm_code="+uuid+"&path="+theForm.NAME.value;
		if(confirm("确定要挂载ISO吗?")==true){
			$.getJSON(url,{'time': new Date().toString()},function(data){
				if(data.result==1){
					alert("挂载ISO成功!");
					window.opener.receiveVallue();
				}else{
					alert("挂载ISO失败!");
					window.opener.receiveVallue();
				}
			});
		}
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="tree_mountISO.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					ISO路径
				</td>
				<td>
					<s:textfield name="theForm.NAME" id="NAME" size="35"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:mount('<%=request.getAttribute("vm_code") %>')" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
