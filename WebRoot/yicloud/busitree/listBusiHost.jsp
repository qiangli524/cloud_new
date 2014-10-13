<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
   var id = '<%=request.getAttribute("id")%>';
	$("#checkButton").unbind().live("click",function(){
 		var hostId = $(this).attr("hostId");
		var IP = $(this).attr("IP");
		$("#"+hostId+" input").attr("disabled",true);
		var url = 'busitree_checkStatus.do?IP='+IP+'&hostId='+hostId;
		$.getJSON(url,{"time":new Date().toString()},function(data){
			$("#"+hostId+" input").attr("disabled",false);
			if(data.result == 1){
				$("#"+hostId+" :eq(3)").text("正在运行");
				alert("该主机的运行状态为：正在运行");
			}else{
				$("#"+hostId+" :eq(3)").text("已停止");
				alert("该主机的运行状态为：已停止");
			}
		});
	});
</script>
</head>
<body>
	 <s:form action="busitree_listAppDeployHost"  method="post" cssStyle="theForm" id="theForm">
	 <s:hidden name="theForm.id" id="id"></s:hidden>
		<div class="scrollbody">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
<%--									<th onclick="sort(theTable,0,'string')">主机名称</th>--%>
									<th onclick="sort(theTable,1,'string')">管理IP</th>
<%--									<th>VLAN</th>--%>
									<th onclick="sort(theTable,2,'string')">服务IP</th>
									<th onclick="sort(theTable,3,'string')">运行状态</th>
							<%-- 		<th>状态</th>  --%>
									<th>运行状态检测</th>
								</tr>
							</thead>
							<tbody>
							        <s:iterator value="theForm.resultList" id="theBean">
										<tr id="<s:property value="#theBean.ID"/>">
<%--											<td>--%>
<%--											    <s:text name="#theBean.HOSTNAME"/>--%>
<%--											</td>--%>
											<td>
											     <s:text name="#theBean.IP"/>
											</td>
<%--											<td>--%>
<%--											     <s:text name="#theBean.VLAN"/>--%>
<%--											</td>--%>
											<td>
											     <s:text name="#theBean.VLANIP"/>
											</td>
											<td>
												<s:if test="#theBean.WORKSTATUS==0">
													暂无
												</s:if>
												<s:elseif test="#theBean.WORKSTATUS==1">
													正在运行
												</s:elseif>
												<s:elseif test="#theBean.WORKSTATUS==2">
													已停止
												</s:elseif>
												<s:elseif test="#theBean.WORKSTATUS==3">
													异常
												</s:elseif>
											</td>
											<!-- 
											<td>
												<s:if test="#theBean.STATUS==1">
												空闲
												</s:if>
												<s:elseif test="#theBean.STATUS==2">
												非空闲
												</s:elseif>
											</td>
											 -->
											<td>
												<a id="checkButton"  href="javascript:void(0)" hostId='<s:property value="#theBean.ID"/>' IP='<s:property value="#theBean.IP"/>'>
                								检测
                								</a>
											</td>
										</tr>
									</s:iterator>
							</tbody>
						</table>
					</div>
					<div class="pages mgb-10">
						<jsp:include page="../../sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
	</s:form>
</body>
</html:html>
