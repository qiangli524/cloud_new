<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/view.jsp"%>
<%@ include file="../../../common/link.jsp"%>	
<%@ page import="com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.WorkFlowConstant" %>
	<head>
		<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
			var flag= true;
			function validateForm(){
				document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "";
				//异步方式判断 需求编码是否唯一
				var NEED_NUMBERS = document.getElementById("NEED_NUMBERS").value;
				if(NEED_NUMBERS == null || "" == NEED_NUMBERS){
					document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "工单编号不能为空";
					flag=false;
					return false;
				}else{
					flag=true;
				}
				var url = "appworkflow_appOrderUniqueJudgement.do?NEED_NUMBERS="+NEED_NUMBERS+"&Date"+(new Date());
				 $.getJSON(url,function(data){
				 	if("NO" == data ){
				 		document.getElementById("NEED_NUMBERS_SPAN").innerHTML = NEED_NUMBERS+"已经存在，请更改需求编号!";
				 		flag=false;
				 	}else{
				 		flag=true;
				 	}
				 })
			}
			
			//提交
			function submitForm(status){
				var value = $("#theForm_theForm_APPID").find('option:selected').val();
				var needNumberValue=theForm.NEED_NUMBERS.value;
				if(needNumberValue==""){
					alert("工单编号不能为空");
					return;
				}
				if(value == 0){
					alert("请选择应用名称");
					return;
				}
				if(flag){
					document.getElementById("theForm").COMMAND.value = status;
					theForm.action = 'appworkflow_saveAppDeployOrder.do'
					theForm.submit();
				}else{
				  alert("编号错误，请更改需求编号！");
				}
			}
		</script>

	</head>
<body class="pop-body scrollbody">
<s:form action="appworkflow_addAppDeployOrder.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.COMMAND" id="COMMAND"></s:hidden>
<s:hidden name="theForm.FLOW_TYPE" id="FLOW_TYPE"></s:hidden>
	<div class="tit-zzi">
		<div id="zi">工单基本信息</div>
		<div id="zi"></div>
	</div>	
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<tr>
					<td class="til">
						工单编号：
					</td>
					<td>
						<s:textfield name="theForm.NEED_NUMBERS" cssClass="txt" onblur="validateForm()" id="NEED_NUMBERS"></s:textfield>
						<span id="NEED_NUMBERS_SPAN" style="color: RED"></span>	
					</td>	
					<td class="til">
						要求完成时间：
					</td>
					<td>
						<s:textfield name="theForm.END_DATE" size="10" readonly="true" cssClass="txt" id="datepicker2"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						具体内容：
					</td>
					<td colspan="3">
						<s:textarea name="theForm.NEED_CONT" cols="77" rows="3"></s:textarea>
					</td>
				</tr>
				</table>
			</div>
	<div class="tit-zzi">
		<div id="zi">申请的基本信息</div>
		<div id="zi"></div>
	</div>	
	<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<tr>
					<td class="til">
						应用部署个数
					</td>
					<td class="til">
						<s:textfield name="theForm.APP_SIZE" cssClass="txt"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						应用名称
					</td>
					<td>
						<s:select list="theForm.appList" listKey="ID" listValue="APPNAME" name="theForm.APPID" headerKey="0" headerValue="-请选择-"></s:select>
					</td>
				</tr>	
				</table>		
				</div>
		
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">	
			<tr>
				<td colspan="4" class="btnCenter">
					<input  type="button" class="btn-style02" value="提交" onclick="javascript:submitForm(<%=WorkFlowConstant.COMMAND_PUBLISTH %>);"/>	
			        <input  type="button" class="btn-style02" value="确定" onclick="javascript:submitForm(<%=WorkFlowConstant.COMMAND_SAVE %>)"/>
				</td>
			</tr>
		</table>   	
</s:form>
	</body>
