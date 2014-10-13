<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/view.jsp"%>
<%@ include file="../../../common/link.jsp"%>	
<html:html locale="true">
	<head>
		<title></title>
		<script type="text/javascript">
		   //显示详细信息
		   function searchRequest(){ 
				var theForm = document.getElementById('theForm');
				var flowType = theForm.FLOW_TYPE.value;
				if(flowType == 'FLOW_TYPE_TODOLIST'){
					theForm.action = 'resworkflow_listWaitResourceOrderInfo.do';
				}else if(flowType == 'FLOW_TYPE_ALREADYDO'){
					theForm.action = 'resworkflow_alreadyResourceOrder.do';
				}else if(flowType == 'FLOW_TYPE_PLAYEDBACK'){
					theForm.action = 'resworkflow_rejectResourceOrder.do';
				}else if(flowType == 'FLOW_TYPE_HUNGLIST'){
					   		theForm.action = 'resworkflow_addOrderInfo.do';
					   	}
				theForm.submit();
		   }
		</script>
	</head>
	<%
		String nextDealManInfo = (String)request.getAttribute("NextDealMan"); 
	%>
	<body>
		<s:form action="resworkflow_getNextDealManInfo.do" method="post" id="theForm"> 
			<s:hidden name="theForm.FLOW_TYPE" id="FLOW_TYPE"/> 
			
			<div class="scrollbody"> 
				<div class="box on">
					<div class="query-form">
						<table width="100%" class="querytable" border="0">
						   <tr> 
								<td align='center'>
									<%=nextDealManInfo%>
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
</html:html>
