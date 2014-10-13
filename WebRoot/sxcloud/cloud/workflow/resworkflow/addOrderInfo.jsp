<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page
	import="com.sitech.basd.sxcloud.workflow.domain.resourceorder.WorkFlowConstant"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
<html:html locale="true">
<head>
	<script type="text/javascript">
$(function() {
	$("#datepicker1").datepicker( {
		showOn : 'button',
		buttonImage : 'sxcloud/cresources/default/images/date-icon.gif',
		buttonImageOnly : true
	});
	$("#datepicker2").datepicker( {
		showOn : 'button',
		buttonImage : '/sxcloud/cresources/default/images/date-icon.gif',
		buttonImageOnly : true
	});
})
var count = 3;
var flag= true;
function validateForm() {

	document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "";
	//异步方式判断 需求编码是否唯一
	var NEED_NUMBERS = document.getElementById("NEED_NUMBERS").value;
	if (NEED_NUMBERS == null || "" == NEED_NUMBERS) {
		document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "工单编号不能为空";
		return false;
	}
	var url = "resworkflow_OrderUniqueJudgement.do?NEED_NUMBERS=" + NEED_NUMBERS
			+ "&Date" + (new Date());
	$.getJSON(url,function(data) {
						if("NO" == data ){
				 		document.getElementById("NEED_NUMBERS_SPAN").innerHTML = NEED_NUMBERS+"已经存在，请更改需求编号!";
				 		flag=false;
				 	}else{
				 		flag=true;
				 	}
				 })
	//对表单其他数据进行判断

}

	//提交
	function submitForm(status) {
		//对页面表单进行数据校验
		
		if(flag){
		var SELECT = theForm.SELECT.value;
		document.theForm.COMMAND.value = status;
		theForm.action='resworkflow_saveOrderInfo.do?SELECT='+SELECT;
		theForm.submit();
		}else{
			 alert("编号错误，请更改需求编号！");
		}
	
	}
	function resetForm(theForm) {
		theForm.TEM_ID.value = '';
		theForm.TEM_NAME.value = '';
		theForm.TYPE.value = '';
		theForm.CONFIG_NAME.value = '';
		theForm.CONFIG_VALUE.value = '';
	}

	function checkSelect(){
		var str= document.theForm.SELECT.value;
		if(str=='3' || str=='4'){
			document.getElementById('virtual').style.display="";
		}else {
			document.getElementById('virtual').style.display="none";
		}
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="resworkflow_saveOrderInfo.do" method="post" id="theForm">
		<s:hidden name="theForm.COMMAND" id="COMMAND"/>
		<s:hidden name="theForm.FLOW_TYPE" />
		<div class="tit-zzi">
			<div id="zi">
				工单基本信息
			</div>
			<div id="zi"></div>
		</div>
		<div>
			<table width="100%" border="0" cellpadding="0"
				class="pop-table nosize">
				<tr>
					<td class="til">
						工单编号：
					</td>
					<td>
						<s:textfield name="theForm.NEED_NUMBERS" 
							onblur="validateForm()" id="NEED_NUMBERS"/>
						<span id="NEED_NUMBERS_SPAN" style="color: RED"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						要求完成时间：
					</td>
					<td>
						<s:textfield name="theForm.end_time" 
							readonly="true"  id="datepicker1" />
					</td>
				</tr>
				<tr>

					<td class="til">
						具体内容：
					</td>
					<td colspan="3">
						<s:textarea name="theForm.content" cols="77" rows="3"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="til">
						请选择资源类别
					</td>
					<td class="til">
						<s:select name="theForm.SELECT" headerKey="0" headerValue="请选择资源类别" list="theForm.typeList" listKey="TYPE" listValue="TYPE_NAME" id="SELECT" onchange="checkSelect();return false;">
						</s:select>
					</td>
				</tr>
				<tr id=virtual style="display:none">
					<td class="til">
						请选择虚拟机
					</td>
					<td class="til">
						<s:select name="theForm.VH_ID_IBM" list="theForm.virtualList" headerKey="0" headerValue="请选择虚拟机" listKey="VH_ID_IBM" listValue="VH_NAME">
						</s:select>
					</td>
				</tr>

			</table>
		</div>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td colspan="4" class="btnCenter">

					<input type="button" class="thickbox btn-style02" value="提交"
						onclick="javascript:submitForm(<%=WorkFlowConstant.COMMAND_PUBLISTH%>);" />
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitForm(<%=WorkFlowConstant.COMMAND_SAVE%>)" />
				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<!-- 按扭背景 start -->
			<tr>
				<td class="footer_b">

				</td>
			</tr>
			<!-- 按扭背景 end -->
		</table>
	</s:form>
</body>
</html:html>