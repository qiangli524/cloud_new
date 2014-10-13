<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.WorkFlowConstant" %>
<%@ include file="../../../common/taglib.jsp" %>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
	<head>
		<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
			
			
			//提交
			function submitForm(status){
				
				//	alert(status);
				//	document.getElementById("theForm").COMMAND.value = status;
					theForm.COMMAND.value = status;
					theForm.action = 'appworkflow_saveAppDeployOrder.do'
					theForm.submit();
					
				//	document.getElementById("theForm").submit();
				
				
			}
			
		</script>
	</head>
<body class="pop-body scrollbody">
<s:form action="appworkflow_addAppDeployOrder.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.NEED_NUMBERS" id="NEED_NUMBERS"></s:hidden>
<s:hidden name="theForm.COMMAND" id="COMMAND"></s:hidden>
<s:hidden name="theForm.TASKID" id="TASKID"></s:hidden>
		<div class="tit-zzi">
			<div id="zi">
				工单基本信息
			</div>
			<div id="zi"></div>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						工单编号：
					</td>
					<td>
						<s:text name="theForm.NEED_NUMBERS"></s:text>
					</td>	
				</tr>
				<tr>
				<td class="til">
						要求结束时间：
					</td>
					<td>
						<s:textfield name="theForm.END_DATE" size="10" readonly="true" cssClass="txt" id="datepicker2"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						需求内容：
					</td>
					<td colspan="3">
						<s:textarea name="theForm.NEED_CONT" cols="77" rows="3"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="til">
						应用部署个数：
					</td>
					<td>
						<s:textfield name="theForm.APP_SIZE" cssClass="txt"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						应用名称
					</td>
					<td>
						<s:select list="theForm.appList" headerKey="0" headerValue="-请选择-" name="theForm.APPID" listKey="ID" listValue="APPNAME"></s:select>
					</td>
				</tr>	
			</table>
		</div>
		<s:iterator value="theForm.flowTaskList" id="theBean" status="index"><!-- 审批详细信息开始 -->	
			<div class="Operation_Table">
				<div class="itemContent1">
					<div id="Info_table">
						<div id="div6_show" class="groupItem2">
							<div>
					  				<div class="tit-zzi">详细流程</div>
								<!--<logic:iterate id="flowTaskBean" name="theForm" property="flowTaskList" indexId="index">-->
								<div>
									<div class="tit-zzi">
										<div id="zi">第&nbsp;<s:property value="#index.index+1"/>&nbsp;环节</div>
										<div id="zi"></div>							
									</div>
									<div >
									   <table width="100%" border="0" cellpadding="0" cellspacing="0">
											
		                                    <tr >
												<th>流水号：</th>
												<td>
													<s:property value="#theBean.TASK_ID"/>
												</td>
												<th>处理人：</th>
												<td >
												<s:property value="#theBean.DISPOSE_MAN"/>
													<s:if test="#index.index==0">
														(发起人)
													</s:if>
													</td>
											</tr>
											<tr >
												<th>任务到达时间：</th>
												<td>
													<s:property value="#theBean.RECEIVE_TIME"/>
												</td>
												<th>任务处理时间：</th>
												<td>
													<s:property value="#theBean.DISPOSE_TIME"/>
												</td>
											</tr>
											<s:if test="#theBean.TASK_REMARK !=null ">
											<tr>
												<th>审批意见：</th>
												<!--<td  colspan="3"><html:textarea name="flowTaskBean" property="TASK_REMARK" cols="80" rows="5"/></td>
												--><td colspan="3">
													<s:property value="#theBean.TASK_REMARK"/>
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
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">	
			<tr>
				<td colspan="4" class="btnCenter">
				<s:if test="theForm.COMMAND==@com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.WorkFlowConstant@COMMAND_REPUBLISH">
					<input  type="button" class="btn-style02" value="提交" onclick="javascript:submitForm(<%=WorkFlowConstant.COMMAND_REPUBLISH %>);"/>	
			        <input  type="button" class="btn-style02" value="确定" onclick="javascript:submitForm(<%=WorkFlowConstant.COMMAND_RESAVE %>)"/>
			        <input type="button" class="btn-style02" value="返回" onclick="window.history.back()" />
			   </s:if>
			   <s:if test="theForm.COMMAND==@com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.WorkFlowConstant@COMMAND_BACKPUBLISH">
						  	<input type="button" class="btn-style02" value="提交" onclick="javascript:submitForm('<%=WorkFlowConstant.COMMAND_BACKPUBLISH %>');" />
							<%-- <input type="button" class="btn-style02" value="确定" onclick="javascript:submitForm('<%=WorkFlowConstant.COMMAND_BACKSAVE %>');" />--%>
						 	<input type="button" class="btn-style02" value="返回" onclick="window.history.back()"/>
				</s:if>
				</td>
			</tr>
		</table>   	
	</s:form>
	</body>
