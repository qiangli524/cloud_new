<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/chongqing/directoryType/js/directoryType.js"></script>
</head>

<body class="pop-body scrollbody" onload="document.theForm.name.focus()">
	<s:form action="" method="post" id="theForm">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">名称 <font color="red">*</font></td>
					<td>
						<input type="hidden" name="id" value="${typeObj.id }"/>
						<input type="text" id="name" value="${typeObj.name }"  maxlength="32" size="32" name="name" />
					</td>
				</tr>
				<tr>
					<td class="til">编码 <font color="red">*</font></td>
					<td>
						<input type="text" id="code" value="${typeObj.code }" maxlength="128" size="64" name="code"/>
					</td>
				</tr>
				<tr>
					<td class="til">描述</td>
					<td>
						<textarea rows="5" cols="110" cssStyle="txt" id="description"  name="description">${typeObj.description }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="saveEdit()" />
						 <input type="button" class="thickbox btn-style02" value="重置"
							onclick="_resetInfo()" /> 
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>
			</table>
	</s:form>
	<div style="display: none">
	<input type="hidden" id="name_bak" value="${typeObj.name }" />
	<input type="hidden" id="code_bak" value="${typeObj.code }" />
	<input type="hidden" id="description_bak" value="${typeObj.description }" />
	</div>
</body>

