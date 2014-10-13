<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<html:html locale="true">
<head>
	<title></title>

	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function submitRequest(){ 
	var name = document.getElementById("AREA_NAME").value;
	var code = document.getElementById("AREA_CODE").value;
	var rname = document.getElementById("ROOM_NAME").value;
	if(name.length==0){
		alert("区域名称不能为空");
		return false;
	}
	if(code.length==0){
		alert("区域编码不能为空");
		return false;
	}
	
	if(code.length<8||code.length>8)
		{
		alert("区域编码只能是8位数字");
	    return false;
	    }
	if(isNaN(code)){
		alert("区域编码只能是数字");
		return false;
	}
	if(rname==0){
   		alert("请选择机房");
   		return false;
   		}
	
	theForm.submit();
	}
	
  
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="area_saveAreaObj.do" method="post" id="theForm" >
		<s:hidden name="theForm.AREA_ID" id="AREA_ID" />
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						区域名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.AREA_NAME"  id="AREA_NAME"/>
					</td>
					<td class="til">
						区域编码<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.AREA_CODE" id="AREA_CODE" maxlength="8"/>&nbsp;&nbsp;编码为8位数字
					</td>
					
				</tr>

				<tr>
				    <td class="til">
						所在机房<font color="red">*</font>
					</td>
					<td >
						<!-- <s:textfield name="theForm.ROOM_NAME" id="ROOM_NAME"/>-->
							<s:select list="theForm.roomList" headerKey="0" headerValue="请选择" listKey="ROOM_ID" listValue="ROOM_NAME" name="theForm.ROOM_ID" id="ROOM_NAME">
		                   </s:select>  
							
					</td>
					<td class="til">
						联系人
					</td>
					<td >
						<s:textfield name="theForm.LINK_MAN" id="LINK_MAN" maxlength="12"/>
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						联系人电话
					</td>
					<td>
                        <s:textfield name="theForm.PHONE" id="PHONE" maxlength="12"/>
					</td>
					 <td class="til">
						面积
					</td>
					<td >
						<s:textfield name="theForm.AREA_SIZE" id="AREA_SIZE" maxlength="6"/>&nbsp;&nbsp;平方米
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
</html:html>
