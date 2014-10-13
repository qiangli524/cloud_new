<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function submitRequest(obj){ 
	var room = document.getElementById("SOFTNAME").value;
	var type = document.getElementById("SOFTEDITION").value;
	var mech_id = document.getElementById("SUPLINUX").value;
	var capital = document.getElementById("SUPUNIX").value;
	if(room.length==0){
		alert("软件名称不能为空");
		return false;
	}
	if(type.length==0){
		alert("版本不能为空");
		return false;
	}
	if(mech_id.length==0){
		alert("Linux系统不能为空");
		return false;
	}
	if(capital.length==0){
		alert("Unix系统不能为空");
		return false;
	}
	if($(":input#SOFTLICENSE").find("option:selected").val()==''){
				alert("请选择是否需要license！");
				return false;
			}
	theForm.submit();
	}
	
  
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="version_saveVersionObj.do" method="post" id="theForm" >
		<s:hidden name="theForm.s_id" id="s_id"></s:hidden>
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						软件名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.SOFTNAME"  id="SOFTNAME"/>
					</td>
					<td class="til">
						版本<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.SOFTEDITION" id="SOFTEDITION"/>&nbsp;&nbsp;
					</td>
					
				</tr>

				<tr>
				    <td class="til">
						Linux系统<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.SUPLINUX"  id="SUPLINUX"/>	
					</td>
					<td class="til">
						Unix系统<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.SUPUNIX" id="SUPUNIX" />
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						其它系统
					</td>
					<td>
                        <s:textfield name="theForm.SUPSUNOS" id="SUPSUNOS"/>
					</td>
					 <td class="til">
						操作系统补丁
					</td>
					<td >
						<s:textfield name="theForm.SYSTEMPATCH" id="SYSTEMPATCH" />&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						操作系统服务要求
					</td>
					<td>
                        <s:textfield name="theForm.SYSTEMSERVICE" id="SYSTEMSERVICE" />
					</td>
					 <td class="til">
						依赖软件包
					</td>
					<td >
						<s:textfield name="theForm.DEPENDPACK" id="DEPENDPACK" />&nbsp;&nbsp;
					</td>
				</tr>
				
				
				<tr>
				  
					<td class="til">
						编译器要求
					</td>
					<td>
                        <s:textfield name="theForm.COMPILER" id="COMPILER" />
					</td>
					 <td class="til">
						依赖第三方
					</td>
					<td >
						<s:textfield name="theForm.OTHERPACK" id="OTHERPACK"/>&nbsp;&nbsp;
					</td>
				</tr>
				
				<tr>
				  
					<td class="til">
						软件补丁
					</td>
					<td>
                        <s:textfield name="theForm.SOFTPACK" id="SOFTPACK" />
					</td>
					 <td class="til">
						是否需要license
					</td>
					<td >
					<s:select name="theForm.SOFTLICENSE" id="SOFTLICENSE" headerKey="" headerValue="请选择" list="#{'是':'是','否':'否'}"/>
						<!--<s:textfield name="theForm.SOFTLICENSE" id="SOFTLICENSE" />&nbsp;&nbsp;-->
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


