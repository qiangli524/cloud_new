<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript">
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){ 
	    if(thisForm.NAME.value.length ==0){
	     alert("项目名称不能为空！");
	     thisForm.NAME.focus;
	     return false  ;
	    }
	    thisForm.submit();
	}
	
	 /* 检测缺醒，公用，基础中三选一*/
	function checkSelect()
	{
		var valuedefault = document.all.ISDEFAULT.options[document.all.ISDEFAULT.options.selectedIndex].value;
		var valuestating = document.all.ISSTAGING.options[document.all.ISSTAGING.options.selectedIndex].value;
		var valuepublic = document.all.ISPUBLIC.options[document.all.ISPUBLIC.options.selectedIndex].value;
		if(valuedefault=='1' && valuestating=='0' && valuepublic=='0'){
			document.all.ISSTAGING.disabled="disabled";
			document.all.ISPUBLIC.disabled="disabled";
		}else{
			document.all.ISSTAGING.disabled=false;
			document.all.ISPUBLIC.disabled=false;
			if(valuedefault=='0' && valuestating=='1' && valuepublic=='0'){
				document.all.ISDEFAULT.disabled="disabled";
				document.all.ISPUBLIC.disabled="disabled";
			}else{
				document.all.ISDEFAULT.disabled=false;
				document.all.ISPUBLIC.disabled=false;
				if(valuedefault=='0' && valuestating=='0' && valuepublic=='1'){
					document.all.ISDEFAULT.disabled="disabled";
					document.all.ISSTAGING.disabled="disabled";
				}
			}
		}		
	}
</script>
</head>
<body class="pop-body scrollbody" onload="checkSelect()">
	<s:form action="project_saveProject" method="post" id="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						项目编号 <font color="red">*</font>
					</td>
					<td>
					<s:textfield name="theForm.PROJECT_ID" id="PROJECT_ID" cssClass="txt" readonly="true"></s:textfield>
					   <font color="red" size="1"> 注:只能输入数字</font>                 
					</td>
					<td class="til">
						项目名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.NAME" id="NAME" cssClass="txt"></s:textfield>
					</td>
				</tr>
				<tr>
				    <td class="til">
						项目描述
					</td>
					<td>
						<s:textfield name="theForm.DESCRIPTION" id="DESCRIPTION" cssClass="txt"></s:textfield>
					</td>
					<td>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>
			</table>
	</s:form>
</body>
