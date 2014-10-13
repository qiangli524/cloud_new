<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
    <script type="text/javascript">

    var flag=0;//用于判断在提交时表单内容是否完全正确，以确定是否可以提交
   	var id= '<%=request.getAttribute("id")%>';
   	var api = frameElement.api;
		/*
		w = api.opener;
		 api.button({
		     id:'OkAnd',
		     name: '安装',
		     callback:updateTemplateInstall,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '返回'
		 });	
		 */
	function updateTemplateInstall(){
		//theForm.action="autoos_updateTemplateInstall.do";
	    theForm.submit();
	    api.close();
	}
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="autoos_updateTemplateInstall.do" method="post" id="theForm" cssStyle="theForm">
  	<s:hidden name="theForm.id" id="id"></s:hidden>
  	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til">管理口IP <font color="red">*</font></td>
					<td>
						<s:textfield name="theForm.mge_console_ip" id="mge_console_ip" disabled="true" style="width:150px;   height:20px;" maxlength="30"  cssClass="ipAddress required"></s:textfield>
					</td>
				</tr>
				<tr align="left">
					<td class="til">MAC <font color="red">*</font></td>
					<td>
						<s:textfield name="theForm.mac" id="mac" style="width:150px;   height:20px;" maxlength="30"  cssClass="required"></s:textfield>
					</td>
				</tr>
				<tr align="left">
					<td class="til">用户名 <font color="red">*</font></td>
					<td>
					<s:textfield name="theForm.mge_console_username" id="mge_console_username" disabled="true" style="width:150px;   height:20px;" maxlength="30" onblur="validateUserName()" cssClass="required"></s:textfield>
					<span id="span"><font color="red" id="username_span"></font></span>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">密码 <font color="red">*</font></td>
					<td>
					<s:password type="password" name="theForm.mge_console_pass" id="mge_console_pass" disabled="true" style="width:150px;   height:20px;" maxlength="16" showPassword="true" cssClass="required"></s:password>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">安装方式</td>
					<td>
					<s:select list="#{'0':'默认安装'}" style="width:150px;   height:20px;" id="install_type" name="theForm.install_type"></s:select>
					</td>
				</tr>
				<tr align="left">
					<td class="til">操作系统</td>
					<td>
					<s:select list="#{'Red Hat Enterprise Linux 2':'Red Hat Enterprise Linux 2','Red Hat Enterprise Linux 3 64 bit':'Red Hat Enterprise Linux 3 64 bit','Red Hat Enterprise Linux 3':'Red Hat Enterprise Linux 3','Red Hat Enterprise Linux 4 64 bit':'Red Hat Enterprise Linux 4 64 bit','Red Hat Enterprise Linux 4':'Red Hat Enterprise Linux 4','Red Hat Enterprise Linux 5':'Red Hat Enterprise Linux 5','Red Hat Enterprise Linux 6 64 bit':'Red Hat Enterprise Linux 6 64 bit','Red Hat Enterprise Linux 6':'Red Hat Enterprise Linux 6'}" style="width:250px;   height:20px;" id="install_os" name="theForm.install_os"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="thickbox btn-style02" value="安装" onclick="javascript:updateTemplateInstall();" />
					</td>
				</tr>
			</table>
		</div>
    </s:form>
</body>