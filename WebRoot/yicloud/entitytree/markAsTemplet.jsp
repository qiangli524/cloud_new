<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
	
	function submitRequest(theForm){
		var id = '<%=request.getAttribute("id")%>';
		var name = '<%=request.getAttribute("name")%>';
		var parentId = '<%=request.getAttribute("parentId")%>';
		var contentParent_id = $("#CONTENTS").find('option:selected').val();
		if($("#CONTENTS").find('option:selected').val() == 0){
			alert("请选择目录");
			return false;
		}
		var url = "tree_markAsTem.do?id=" + id + "&name=" + encodeURI(encodeURI(name))+ "&parentId=" + parentId+"&contentParent_id="+contentParent_id;
		bar(id,"正在将虚拟机标记为模板:"+name);
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.result==1){
				barEnd(id,"已成功将虚拟机标记为模板:"+name);
				window.parent.refreshParentNode();
			}else{
				barEnd(id,"无法将虚拟机标记为模板:"+name);
			}	
		});
	}
	
	
</script>
<script type="text/javascript">
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
<body style="width: 840px;height: 450px;margin: 10px;">
	<s:form action="vmw_cloneVirtualMac.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize" >
<s:hidden name="theForm.NAME_ZH" id="NAME_ZH"></s:hidden>
			<tr>
				<td class="til">
					模板目录:
				</td>
				<td>
					<s:select list="theForm.resultList" listKey="parent_id" listValue="name" headerKey="" headerValue="-请选择-" id="CONTENTS"/>
				</td>
			</tr>
				
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="转化"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
