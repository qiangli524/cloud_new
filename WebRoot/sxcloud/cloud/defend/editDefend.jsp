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
	   
		if(theForm.ID.value==0){
			alert("请选择所属主机名称");
			this.Form.ID.focus;
			return false;
		}
		thisForm.action='defend_saveDefend.do';
	    thisForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="defend_modDefend" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">防篡改编号
					</td>
					<td>
					<s:text name="theForm.DEFEND_ID"></s:text>
					<s:hidden name="theForm.DEFEND_ID" id="DEFEND_ID"></s:hidden>
					 </td>
				</tr>
				<tr>
					<td class="til">
						所属主机名称
					</td>
					<td>
						 <s:text name="theForm.HOSTNAME"></s:text>
					</td>
				</tr>
				<tr>
				    <td class="til">
						所属主机IP地址
					</td>
					<td>
					  <%--<html:select name="theForm" property="ID">
					  <html:option value="0">-请选择-</html:option>
					  <html:optionsCollection name="theForm" property="hphostList" label="IP" value="ID"/>
					  </html:select>
					  --%>
					  <s:text name="theForm.IP"></s:text>
					  <s:hidden name="theForm.ID" id="ID"></s:hidden>
					</td>
				</tr>
				<tr>
					<td class="til">
						受控目录 <font color="red"></font>
					</td>
					<td>
<!--					    <html:text property="DEFEND_DIR" styleClass="txt" /> -->
					        <s:text name="theForm.DEFEND_DIR"></s:text>
					        <s:hidden name="theForm.DEFEND_DIR" id="DEFEND_DIR"></s:hidden> 
					</td>
				</tr>
				<tr>
					    <td class="til">
						是否生效
					</td>
					<td>
	                   <s:select list="#{'0':'失效','1':'生效'}" name="theForm.ENABLE"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">操作类型
					</td>
					<td>
						<s:select list="#{'0':'添加防篡改','2':'删除防篡改'}" name="theForm.DEFEND_TYPE"></s:select>
					</td>
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

