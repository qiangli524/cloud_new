<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
	function submitRequest(theForm){
		if(theForm.NAME.value==""){
			alert("请输入模板名称！");
			return false;
		}
		var name = theForm.NAME.value;
		//var desc = theForm.DESC.value;
		var entity_id = <%=request.getAttribute("entity_id")%>;
		var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
		var host_uuid = '<%=request.getAttribute("host_uuid")%>';
		url = "xen_createTemByVM.do?name="+ encodeURI(encodeURI(name)) + "&entity_id=" + entity_id+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid ;
		bar(entity_id,"正在创建模板:"+name);
		$.getJSON(url,{"time":new Date().toString()},function(data){
			if(data.responseCode == 1){
				barEnd(entity_id,"创建模板成功");
				window.parent.refreshClusterNode();
				//window.document.location.reload();
			}else if(data.responseCode == -1){
				barEnd(entity_id,"创建模板失败");
				//window.document.location.reload();
			}
		});
	}
	
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
<body>
<s:form action="xen_hostInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="20%" align="left"> 
						模板名称
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
