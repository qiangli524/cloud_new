<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
	
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:submitRequest,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	function submitRequest(){ 
	var name = document.getElementById("ROOM_NAME").value;
	var code = document.getElementById("ROOM_CODE").value;
	var phone = document.getElementById("PHONE").value;
	var city = document.getElementById("ROOM_CITY").value;
	var type = document.getElementById("ROOM_TYPE").value;
	if(name.length==0){
		alert("机房名称不能为空");
		return false;
	}
	if(code.length==0){
		alert("机房编码不能为空");
		return false;
	}
	
	if(code.length<8||code.length>8)
		{
		alert("机房编码只能是8位数字");
	    return false;
	    }
	if(isNaN(code)){
		alert("机房编码只能是数字");
		return false;
	}
	if(isNaN(phone)){
		alert("电话号只能是数字");
		return false;
	}
	if(city==-1){
		alert("请选择城市");
		return false;
	}
	if(type==-1){
		alert("请选择机房性质");
		return false;
	}
  	  w.saveArea($("#theForm").serialize());
	}
  
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="room_saveRoom.do" method="post" id="theForm" >
		<s:hidden name="theForm.ROOM_ID" id="ROOM_ID" />
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						机房名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.ROOM_NAME"  id="ROOM_NAME" maxlength="12"/>
					</td>
					<td class="til">
						机房编码<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.ROOM_CODE" id="ROOM_CODE" maxlength="8"/>&nbsp;&nbsp;编码为8位数字
					</td>
					
				</tr>

				<tr>
				    <td class="til">
						所在城市<font color="red">*</font>
					</td>
					<td >
						<!--<s:textfield name="theForm.ROOM_CITY" id="ROOM_CITY"/>-->
						<s:select headerKey="-1" headerValue="--请选择--"
							list="#{'北京':'北京','上海':'上海','忻州':'忻州','太原':'太原','临汾':'临汾','运城':'运城','长治':'长治','大同':'大同','晋中':'晋中','朔州':'朔州','合肥':'合肥'}" name="theForm.ROOM_CITY" id="ROOM_CITY">
						</s:select>
					</td>
					<td class="til">
						详细地址
					</td>
					<td>
                        <s:textfield name="theForm.ROOM_ADDRESS" id="ROOM_ADDRESS" maxlength="30"/>
					</td>
				</tr>
				<tr>
				   <td class="til">
						联系人
					</td>
					<td >
						<s:textfield name="theForm.LINK_MAN" id="LINK_MAN" maxlength="12"/>
					</td>
					<td class="til">
						联系人电话
					</td>
					<td>
                        <s:textfield name="theForm.PHONE" id="PHONE" maxlength="11"/>
					</td>
				</tr>
				<tr>
					  <td class="til">
						机房性质 <font color="red">*</font>
					</td>
					<td >
					<s:select headerKey="-1" headerValue="--请选择--"
							list="#{'1':'自用','2':'租用'}" name="theForm.ROOM_TYPE" id="ROOM_TYPE">
						</s:select>
						<!-- <s:textfield name="theForm.ROOM_TYPE" id="ROOM_TYPE"/> -->
					</td>
					<td class="til">
						面积
					</td>
					<td>
                        <s:textfield name="theForm.ROOM_SIZE" id="ROOM_SIZE" maxlength="6"/>&nbsp;&nbsp;平方米
					</td>
				</tr>
			</table>
	</s:form>
</body>
</html:html>
					