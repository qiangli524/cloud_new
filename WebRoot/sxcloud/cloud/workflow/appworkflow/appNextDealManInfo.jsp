<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
	<head>
		<title></title>
		<script type="text/javascript">
		   //显示详细信息
		   function searchRequest(){ 
					    var theForm = document.getElementById('theForm');
					    var flowType = theForm.FLOW_TYPE.value;
					    if(flowType == 'FLOW_TYPE_TODOLIST'){
					   		theForm.action = 'appworkflow_listWaitAppDeploy.do';
					   	}else if(flowType == 'FLOW_TYPE_ALREADYDO'){
					   		theForm.action = 'appworkflow_alreadyDealApp.do';
					   	}else if(flowType == 'FLOW_TYPE_PLAYEDBACK'){
					   		theForm.action = 'appworkflow_rejectAppDeploy.do';
					   	}else if(flowType == 'FLOW_TYPE_HUNGLIST'){
					   		theForm.action = 'appworkflow_addAppDeployOrder.do';
					   	}
						theForm.submit();
		   }
		</script>
	</head>
	<%
		String nextDealManInfo = (String)request.getAttribute("NextDealMan"); 
	%>
	<body>
	<s:form action="appworkflow" method="post" cssClass="theForm" id="theForm">
		<s:hidden name="theForm.FLOW_TYPE" id="FLOW_TYPE"></s:hidden>
			<div class="scrollbody"> 
				<div class="box on">
					<div class="query-form">
						<table width="100%" class="querytable" border="0">
						   <tr> 
								<td align='center'>
									<%=nextDealManInfo %>
								</td>  
							</tr>
							<tr>
								<td colspan="2" class="btns">
									<div>
										<input type="button" class="thickbox btn-style02" value="确定" onclick="javascript:searchRequest()" />
									</div>
								</td>
							</tr>
						</table>
					</div>   
				</div>
			</div>
		</s:form>
	</body>
