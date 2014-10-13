<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.workflow.domain.resourceorder.WorkFlowConstant"%>
<%@ page import="com.sitech.basd.sxcloud.workflow.web.resworkflow.form.ResourceWorkflowForm" %>
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
		buttonImage : 'sxcloud/cresources/default/images/date-icon.gif',
		buttonImageOnly : true
	});
})
var count = 3;
function validateForm() {
	document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "";
	//异步方式判断 需求编码是否唯一
	var NEED_NUMBERS = document.getElementById("theForm").NEED_NUMBERS.value;
	if (NEED_NUMBERS == null || "" == NEED_NUMBERS) {
		document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "工单编号不能为空";
		return false;
	}
	var url = "resworkflow_OrderUniqueJudgement.do?NEED_NUMBERS=" + NEED_NUMBERS
			+ "&Date" + (new Date());
	$
			.getJSON(
					url,
					function(data) {
						if ("NO" == data) {
							document.getElementById("NEED_NUMBERS_SPAN").innerHTML = NEED_NUMBERS
									+ "已经存在，请更改工单编号!";
							return false;
						}
					})
	//对表单其他数据进行判断

}

//提交
function submitForm(status) {
	//对页面表单进行数据校验
	//validateForm();
	document.getElementById("theForm").COMMAND.value = status;
	theForm.action = 'resworkflow_saveOrderInfo.do'
	theForm.submit();
}
function resetForm(theForm) {
	theForm.NEED_NUMBERS.value = '';
	theForm.start_time.value = '';
}
function searchRequest() {
	theForm.submit();
}
function searchTempletRequest() {
	theForm.action = 'queryTempletInfo.do'
	theForm.submit();

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
		<s:hidden name="theForm.COMMAND" id="COMMAND" />
		<s:hidden name="theForm.TASKID"  id="TASKID"/>
		<s:hidden name="theForm.NEED_NUMBERS" id="NEED_NUMBERS" />
		<s:hidden name="theForm.FLOW_TYPE" id="FLOW_TYPE"  />
		<div class="tit-zzi">
			<div id="zi">
				工单基本信息
			</div>
			<div id="zi"></div>
		</div>
		<div>
			<table width="100%" border="0" cellpadding="0"class="pop-table nosize">
				<tr>
					<td class="til">
						工单编号：
					</td>
					<td>
						<s:text name="theForm.NEED_NUMBERS" /> 
						<span id="NEED_NUMBERS_SPAN" style="color: RED"></span>	
					</td>
				</tr>
				<tr>
				<td class="til">
						工单发起时间
					</td>
					<td>
						<s:text name="theForm.start_time" /> 
					</td>
					</tr>
				<tr>
					<td class="til">
						要求完成时间：
					</td>
					<td>
						<s:textfield name="theForm.end_time" id="datepicker1"/>
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
						<s:select name="theForm.SELECT" list="theForm.typeList" headerKey="0" headerValue="-请选择资源类别-" listKey="TYPE" listValue="TYPE_NAME" onchange="checkSelect();return false;">
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
				<s:iterator value="theForm.flowTaskList" status="index" id="theBean">
			<div class="Operation_Table">
				<div class="itemContent1">
					<div id="Info_table">
						<div id="div6_show" class="groupItem2">
							<div>
					  				<div class="tit-zzi">详细流程</div>
								<%--<s:iterator id="flowTaskBean" value="theForm.flowTaskList" status="index" ><!-- 审批详细信息开始 --> --%>	
								<div>
									<div class="tit-zzi">
										<div id="zi">第&nbsp;<s:property value="#index.index+1"/>&nbsp;环节</div>
										<div id="zi"></div>							
									</div>
									<div >
									   <table width="100%" border="0" cellpadding="0" cellspacing="0">
											
		                                    <tr >
												<th>任务编号：</th>
												<td ><s:property value="#theBean.TASK_ID"/></td>
												<th>处理人：</th>
												<td ><s:property value="#theBean.DISPOSE_MAN"/>
													<s:if test="#Bean.index==0">
														(发起人)
													</s:if>
													</td> 
											</tr>
											<tr >
												<th>任务到达时间：</th>
												<td ><s:property value="#theBean.RECEIVE_TIME"/></td>
												<th>任务处理时间：</th>
												<td ><s:property value="#theBean.DISPOSE_TIME"/></td>
											</tr>
											<s:if test="#theBean.TASK_REMARK !=null ">
											<tr>
												<th>审批意见：</th>
												<!--<td  colspan="3"><html:textarea name="flowTaskBean" property="TASK_REMARK" cols="80" rows="5"/></td>
												--><td colspan="3"><s:property value="#theBean.TASK_REMARK"/>
												   </td>
												<s:hidden name="#theBean.TASK_REMARK"></s:hidden>
											</tr>
											</s:if>
									    </table>
									</div><!-- itemContent end-->
								</div><!-- itemContent end-->
							</div>
						</div>
					</div>
			</div>
			</div>
			</s:iterator>
				<!-- itemContent1 end-->
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td colspan="4" class="btnCenter">
				<s:if test="theForm.COMMAND==@com.sitech.basd.sxcloud.workflow.domain.resourceorder.WorkFlowConstant@COMMAND_REPUBLISH" >
					<input type="button" class="btn-style02" value="提交"
						onclick="javascript:submitForm(<%=WorkFlowConstant.COMMAND_REPUBLISH%>);" />
					<input type="button" class="btn-style02" value="确定"
						onclick="javascript:submitForm(<%=WorkFlowConstant.COMMAND_RESAVE%>)" />
					<input type="button" class="btn-style02" value="返回"
						onclick="window.history.back()" />
				 </s:if>
				 <s:if test="theForm.COMMAND==@com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.WorkFlowConstant@COMMAND_BACKPUBLISH">
						  	<input type="button" class="btn-style02" value="提交" onclick="javascript:submitForm('<%=WorkFlowConstant.COMMAND_BACKPUBLISH %>');" />
						 	<input type="button" class="btn-style02" value="返回" onclick="history.go(-1)"/>
				 </s:if>	
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html:html>