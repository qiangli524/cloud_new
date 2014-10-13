<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<head>
	<title></title>

	<script type="text/javascript">
	<%-- 
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(theForm){
		var name = theForm.NAME.value;
		if(name=="" ){
			alert("数据中心名称不能为空");
			return false;
		}
	//	$.ajax({url:'datacenter_saveDataCenter.do',type:'post',data:{name:theForm.NAME.value}});
		$.getJSON('datacenter_saveDataCenter.do?name=' + theForm.NAME.value,{'time':new Date().toString()}, function(data){
			if(data.result ==1){
				alert("添加数据中心成功");
			 //   window.returnValue = theForm.NAME.value;
		    	window.opener.receiveVallue(theForm.NAME.value);
			}else{
				alert("添加数据中心失败");
			}
		});
	}
	--%>
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="datacenter_saveDataCenter.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					数据中心名称：
				</td>
				<td>
					<s:textfield name="theForm.NAME" id="NAME" cssClass="txt"/>
				</td>
			</tr>
		<%-- 	
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
			--%>
		</table>
	</s:form>
</body>
