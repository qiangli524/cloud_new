<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
	
	function submitRequest(theForm){ 
		if(theForm.name.value==''){
			alert("子目录名称不能为空！");
	     	return false  ;
		}
		var name = theForm.name.value;
		var flag = <%=request.getAttribute("flag") %>;
		if(flag == 0){
			var parent_id = <%=request.getAttribute("parent_id") %>
			var url = "templettree_saveMenu.do?name="+name+"&parent_id="+parent_id+"&flag="+flag;
			$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.result==1){
				alert("创建子目录" + name +"成功");
				window.parent.asyncNode();
			}else{
				alert("创建子目录" + name +"失败");
			}	
			});
		}else{
			var url = "templettree_saveMenu.do?name="+name+"&flag="+flag;
			$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.result==1){
				alert("创建目录" + name +"成功");
				window.parent.asyncAllNode();
			}else{
				alert("创建目录" + name +"失败");
			}	
			});
		}
	}
	
	
</script>
</head>
<body class="pop-body scrollbody">

<s:form action="deviceinfo_saveDevice.do" id="theForm" method="post"
	cssClass="theForm">
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			
			<tr>
				<s:if test="%{#request.flag==1}">
					<td class="til" width="20%">
						目录名称:
					</td>
				</s:if>
				<s:else>
					<td class="til" width="20%">
						子目录名称:
					</td>
				</s:else>
				
				<td>
				    <s:textfield name="theForm.name" cssClass="txt" id="name"></s:textfield>
				</td>
			</tr>
			
			</table>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
