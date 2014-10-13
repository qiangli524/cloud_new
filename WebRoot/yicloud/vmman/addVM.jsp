<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/view.jsp"%>
<html:html locale="true">
<%@ include file="/common/link.jsp"%>
<head>
	<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
			var flag= true;
		function checkSelect(){
		var str= document.theForm.SELECT_TYPE.value;
		if(str=='IBM' || str=='KVM'){
			document.getElementById('type').style.display="";
			theForm.action = 'queryImageList.do'  
		    theForm.submit();
		}else {
			document.getElementById('type').style.display="none";
		}
	}
		</script>
</head>
<body class="pop-body scrollbody" onunload="opener.get()">
	<html:form action="saveVMService" method="post" styleId="theForm">
		<bean:define id="theForm" name="vmManagerForm" />
		<html:hidden name="theForm" property="VH_HOST" />
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
				    <td class="til">
						名称 <font color="red">*</font>
					</td>
					<td>
					    <html:text property="NAME_ZH" styleClass="txt" />              
					</td>
				</tr>	
				<tr>
					<td class="til">
						CPU
					</td>
					<td>
					    <html:text property="CPU" styleClass="txt" />              
					</td>
					</tr>
					<tr>
					<td class="til">
						内存
					</td>
					<td>
					    <html:text property="MEMORY" styleClass="txt" />              
					</td>
					<tr >
					<td class="til">选择镜像类型:</td>
                  	 <td>
						<html:select name="theForm" property="SELECT_TYPE" onchange="checkSelect();return false;">
							<html:option value="">-请选择-</html:option>
							<html:optionsCollection name="theForm" property="typeList"
								label="IM_TYPE" value="IM_TYPE" />
						</html:select>
                    </td>
				</tr>
				<tr id=virtual style="display:none">
					<td class="til">
						请选择虚拟镜像
					</td>
					<td class="til">
						<html:select name="theForm" property="SELECT_IMAGE" >
							<html:option value="0">-请选择虚拟镜像-</html:option>
							<html:optionsCollection name="theForm" property="imageList"
								label="IM_NAME" value="IM_TYPE" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="部署"
							onclick="javascript:deployRequest(document.getElementById('theForm'));return false;" />
<%--						<input type="button" class="thickbox btn-style02" value="确定"--%>
<%--							onclick="javascript:saveRequest(document.getElementById('theForm'));return false;" />--%>
						<input type="button" class="thickbox btn-style02" value="取消"
							onclick="window.history.back()" />
							
					</td>
				</tr>
			</table>
		</div>
	</html:form>
</body>
</html:html>