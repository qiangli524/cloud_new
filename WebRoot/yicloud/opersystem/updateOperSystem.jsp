<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/thickbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/contextmenu.r2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/ui16rc5.packed.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/portal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/ui.datepicker.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree_wx.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree_check.js"></script>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/jquery.uploadify-3.1.min.js"></script>
	<link href="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/uploadify.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){    
	    if(thisForm.NAME.value.length ==0){
	     alert("操作系统文件名称不能为空！");
	     thisForm.NAME.focus;
	     return false  ;
	    }
	    if(thisForm.VERSION.value.length ==0){
	     alert("操作系统文件版本不能为空！");
	     thisForm.VERSION.focus;
	     return false  ;
	    }
	    theForm.submit();
	}
	<% String str = (String)request.getAttribute("upload");
		if(str !=null && !"".equals(str)){
	%>		
		alert("<%=str%>");
	<%	} %>
	function goBack(theForm){
		theForm.action="opersystem_listOperSystemInfo.do";
		theForm.submit();
	}
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="opersystem_saveOperSystem.do" method="post" id="theForm" enctype="multipart/form-data">
	    <input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
		<s:hidden name="theForm.ID" id="ID"></s:hidden>			   
		<s:hidden name="theForm.PARTH" id="PARTH"></s:hidden>			   
		
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						操作系统名称 <font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="theForm.NAME" id="NAME" readonly="true" cssClass="txt"></s:textfield>              
					</td>
					 <td class="til">
						操作系统版本<font color="red">*</font>
					</td>
					<td>
                      <s:textfield name="theForm.VERSION" cssClass="txt" id="VERSION"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						类别
					</td>
					<td>
						<s:select list="#{'0':'物理主机','1':'虚拟机'}"  name="theForm.TYPE" id="TYPE"></s:select>
					</td>
					<td class="til">
						操作系统类型
					</td>
					<td>
						<s:select list="#{'0':'UNIX','1':'LINUX','2':'HP LINUX','3':'AIX','4':'WINDOWS'}" name="theForm.OPERTYPE" id="OPERTYPE"></s:select>
					</td>
				</tr>
				<tr>
				    <td class="til">
						备注
					</td>
					<td colspan="3">
						<s:textarea name="theForm.REMARK" id="REMARK" cols="77" rows="5"></s:textarea>
					</td>		
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="javascript:goBack(document.getElementById('theForm'));return false;" />
					</td>
				</tr>
			</table>
	</s:form>
</body>
