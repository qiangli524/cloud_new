<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){
		if(thisForm.APP_NAME.value.length==0){
			alert("请输入应用名称！");
			return false;
		}  
	    thisForm.submit();
	}
	
</script>
</head>
<body class="mainbody">
<s:form action="busitree_saveBizsysApp.do" id="theForm" method="post">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						应用名称 <font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="theForm.APP_NAME" cssClass="txt" id="APP_NAME"></s:textfield>                 
					</td>
				</tr>
				<tr>
					<td class="til">
						所属系统名称<font color="red">*</font>
					</td>
					<td>
							<s:property value="#request.name"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						软件包
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td class="til">
						数据库
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td class="til">
						描述<font color="red"></font>
					</td>
					 <td colspan="3">
						<s:textarea name="theForm.APP_DESC" id="APP_DESC" cols="127" rows="4"></s:textarea>
					</td>          
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
								<span class="ubtn-green"><input type="button"  value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" /></span>
								<span class="ubtn-orange mgl-20"><input type="button"  value="重置" onclick="javascript:void(document.getElementById('theForm').reset());return false;"></span>
								<span class="ubtn-blue mgl-20"><input type="button"  value="返回" onclick="window.history.back()"/></span>							
					</td>
				</tr>
			</table>
</s:form>
</body>
