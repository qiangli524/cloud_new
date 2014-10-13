<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
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
	function submitRequest(thisForm){    
	   
		if(theForm.PLATFORM_ID.value==0){
			alert("请选择所属主机名称");
			return false;
		}
		if(theForm.DEFEND_DIR.value.length==0){
			alert("请输入受控目录");
			return false;
		}
	    thisForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="defend_saveDefend" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						所属主机<font color="red">*</font>
					</td>
					<td>
					  <s:select list="theForm.hphostList" listValue="IP" listKey="PLATFORM_ID" name="theForm.PLATFORM_ID" id="PLATFORM_ID" headerKey="0" headerValue="-请选择-"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						受控目录 <font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="theForm.DEFEND_DIR" cssClass="txt" id="DEFEND_DIR"></s:textfield>           
					</td>
				</tr>
				<tr>
					    <td class="til">
						是否生效<font color="red">*</font>
					</td>
					<td>
	                   <s:select list="#{'0':'失效','1':'生效'}" name="theForm.ENABLE" id="ENABLE"></s:select>
					</td>
				</tr>
				<tr>
					 <td class="til">操作类型
					</td>
					<td>
					<s:select list="#{'0':'添加防篡改','2':'删除防篡改'}" name="theForm.DEFEND_TYPE" id="DEFEND_TYPE"></s:select>
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
