<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function(){
		var size ='<%=request.getAttribute("size")%>';
		var div = window.parent.document.getElementById("deployAlarm");
		var h =  105+size*30;
		$(div).attr("style","height: "+h+"px");
	});
	$(function(){
		var id = '<%=request.getAttribute("id")%>';
		theForm.action = "busitree_listDeployAlarm.do?id="+id;
	});
</script>
</head>
<body>
    <s:form action="busitree_listDeployAlarm" method="post" cssStyle="theForm" id="theForm">
    	<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<div class="scrollbody">
			<div class="box on">
				<div class="blue-wrap noborder">
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0" id="theTable"
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
									<th onclick="sort(theTable,5,'int')">
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
					<div class="table-head">
						<jsp:include page="../../sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
		</div>
	</s:form>
</body>
</html:html>
