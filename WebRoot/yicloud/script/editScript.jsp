<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(theForm){
		var name = theForm.name.value;
		if(name==null){
			alert("脚本名称不能为空");
		}
		theForm.action='templettree_saveScript.do';
	    theForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="templettree_saveScript.do" method="post" id="theForm">
	<s:hidden name="theScriptForm.flag" id="flag"></s:hidden>
	<s:hidden name="theScriptForm.id" id="id"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					名称：
				</td>
				<td>
					<s:textfield name="theScriptForm.name" id="name" cssClass="txt"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					内容：
				</td>
				<td>
					<s:textarea name="theScriptForm.content" id="content" cssClass="txt" cols="77" rows="10" style="background:black;color:white"/>
				</td>
			</tr>
			<tr>
				    <td class="til">
						描述：
					</td>
					<td colspan="3">
						<s:textarea name="theScriptForm.des" id="des" cssClass="txt" cols="77" rows="5"></s:textarea>
					</td>		
			</tr>	
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="window.history.back();" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
