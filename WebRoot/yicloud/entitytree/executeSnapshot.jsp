<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
	function submitRequest(theForm){
		if(theForm.name.value==""){
			alert("请输入快照名称！");
			return false;
		}
		var memSnapshot = false;
		var isDefault = false;
		if(document.getElementById("mem").checked){
			memSnapshot = true;
		}
		if(document.getElementById("tool").checked){
			isDefault = true;
		}
		var name = theForm.name.value;
		var desc = theForm.description.value;
		var vmName = theForm.vmName.value;
		var code = theForm.code.value;
		url = "snapshot_createSnapShot.do?vmName="+ vmName+"&code="+ code + "&name=" + encodeURI(encodeURI(name)) + "&desc="+ encodeURI(encodeURI(desc))  +"&memSnapshot=" + memSnapshot + "&isDefault=" + isDefault;
		bar(code,"正在创建快照");
		$.getJSON(url,{"time":new Date().toString()},function(data){
			if(data.result == 1){
				barEnd(code,"成功创建快照");
				//window.document.location.reload();
			}else if(data.result == -1){
				barEnd(code,"创建快照失败");
				//window.document.location.reload();
			}
		});
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
<body class="pop-body scrollbody">
<s:form action="snapshot_createSnapShot" method="post" id="theForm">
<s:hidden name="theForm.vmName" id="vmName" value="%{#request.name}"></s:hidden>
<s:hidden  id="code" value="%{#request.entityId}"></s:hidden>
	<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
		<tr>
			<td class="til">
				名称
			</td>
			 <td>
				<s:textfield name="theForm.name" id="name" cssClass="txt"></s:textfield>
			</td>          
		</tr>
		<tr>
			<td class="til">
				描述
			</td>
			 <td colspan="3">
				<s:textarea name="theForm.description" id="description" cols="127" rows="4"></s:textarea>
			</td>          
		</tr>
		</table>
		<tr>
			<td><input type="checkbox" id="mem"/>生成虚拟机内存快照</td>
		</tr>
		<br/>
		<tr>
			<td><input type="checkbox" id="tool"/>使客户机文件系统处于默认状态(需要安装有VMware Tools)</td>
		</tr>
		<tr>
			<td colspan="4" class="btnCenter">
				<input type="button" class="thickbox btn-style02" value="确定"
					onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
				<input type="button" class="thickbox btn-style02" value="返回"
					onclick="window.history.back()"/>
			</td>
		</tr>
	</table>
</s:form>
</body>
