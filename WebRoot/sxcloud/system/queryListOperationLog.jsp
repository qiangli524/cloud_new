<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
	<title></title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<body>
	<s:form action="log_listOperationLogList" method="post" cssStyle="theForm"
		id="theForm">
		<s:hidden name="theForm.ID" id="ID" />
		<div class="scrollbody">
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
										用户账号
									</th>
									<th onclick="sort(theTable,1,'string')">
										用户名称
									</th>
									<th onclick="sort(theTable,2,'string')"> 
										模块ID
									</th>
									<th onclick="sort(theTable,3,'string')">
										模块名称
									</th>
									<th onclick="sort(theTable,4,'string')">
										操作类别
									</th>
									<th onclick="sort(theTable,5,'string')">
										操作信息
									</th>
									<th onclick="sort(theTable,6,'string')">
										操作结果
									</th>
									<th onclick="sort(theTable,7,'string')">
										备注
									</th>
									<th onclick="sort(theTable,8,'date')">
										操作时间
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
										<tr>
											<td>
												<s:text name="#theBean.ACCOUNT" />
											</td>
											<td>
											<s:text name="#theBean.NAME" />
											</td>
											<td>
											<s:text name="#theBean.FUNCID_STR" />
											</td>
											<td>
											<s:text name="#theBean.FUNNAME" />
											</td>
											<td>
												<s:if test="#theBean.OPERTYPE==1">
												增加
												</s:if>
												<s:elseif test="#theBean.OPERTYPE==2">
													删除
												</s:elseif>
												<s:elseif test="#theBean.OPERTYPE==3">
												修改
												</s:elseif>
												<s:elseif test="#theBean.OPERTYPE==4">
												查询
												</s:elseif>
											</td>
											<td>
											<s:text name="#theBean.MESSAGE" />
											</td>
											<td>
												<s:if test="#theBean.RESULT==0">
												失败
											</s:if>
											<s:elseif test="#theBean.RESULT==1">
												成功
											</s:elseif>
											</td>
											<td>
											<s:if test="theBean.REMARK!=null">
											<s:text name="#theBean.REMARK" />
											</s:if>
											<s:else>
												无
											</s:else>
											</td>
											<td><s:text name="#theBean.OPERTIME" />
											</td>
										</tr>
									</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<!--blue-wrap end -->
		</div>
	</s:form>
</body>
</html:html>
