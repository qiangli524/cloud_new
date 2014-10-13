<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
	})
	function resetForm(theForm){
		theForm.datepicker1.value = '';
		theForm.datepicker2.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
</script>
</head>
<body>
    <s:form action="alarmt_listDeployAlarm" method="post" cssStyle="theForm" id="theForm">
		<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								异常起止时间:从
							</td>
							<td>
							   <s:textfield name="theForm.start_time" size="10" readonly="true" cssClass="txt" id="datepicker1"></s:textfield>
							</td>
							<td class="til">
								至
							</td>
							<td>
                               <s:textfield name="theForm.end_time" size="10" readonly="true" cssClass="txt" id="datepicker2"></s:textfield>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!--query-form end -->

				<div class="blue-wrap noborder">
					<div class="table-head">
						<jsp:include page="../inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">
										应用编号
									</th>
									<th onclick="sort(theTable,1,'string')">
										主机编号
									</th>
									<th onclick="sort(theTable,2,'date')">
										告警时间
									</th>
									<th onclick="sort(theTable,3,'string')">
										操作命令
									</th>
									<th onclick="sort(theTable,4,'string')">
										操作结果
									</th>
									<th onclick="sort(theTable,5,'string')">
										类型
									</th>
								</tr>
							</thead>
							<tbody>
							        <s:iterator value="theForm.resultList" id="theBean">
										<tr>
											<td>
											    <s:text name="#theBean.APPID"/>
											</td>
											<td>
											     <s:text name="#theBean.HOSTID"/>
											</td>
											<td>
											     <s:text name="#theBean.INSERTTIME"/>
											</td>
											<td>
											     <s:text name="#theBean.COMMAND"/>
											</td>
											<td>
											     <s:text name="#theBean.RESULT"/>
											</td>
											<td>
											    <s:if test="#theBean.ALARM_LEVEL==1">
												严重告警
												</s:if>
												<s:elseif test="#theBean.ALARM_LEVEL==2">
												主要告警
												</s:elseif>
												<s:elseif test="#theBean.ALARM_LEVEL==3">
												次要告警
												</s:elseif>
												<s:elseif test="#theBean.ALARM_LEVEL==4">
												警告告警
												</s:elseif>
												<s:elseif test="#theBean.ALARM_LEVEL==5">
												不确定告警
												</s:elseif>
											</td>
										</tr>
									</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
		</div>
	</s:form>
</body>
</html:html>
