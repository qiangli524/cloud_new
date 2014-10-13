<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
	<style type="text/css">
		div.hidden{
		width:50px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
	</style>
</head>
<body>
<s:form action="busiareainfo_listTask.do" method="post" cssStyle="theForm" id="theForm">

<div class="scrollbody">
	<div class="box on">
	<div class="blue-wrap nobtask" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" btask="0" cellspacing="0" name="tasktable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">
							任务编号
						</th>
						<th onclick="sort(theTable,1,'string')">
							任务状态
						</th>
						<th onclick="sort(theTable,2,'string')">
							任务类型
						</th>
						<th onclick="sort(theTable,3,'int')">
							成功个数
						</th>
						<th onclick="sort(theTable,4,'int')">
							失败个数
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="resultList" id="theBean">
	                  <tr>
						<td>
							<s:property value="#theBean.TASK_ID" default="-"/>
						</td>
						<td>
						    <s:if test="#theBean.STATUS==2">待处理</s:if>
						    <s:elseif test="#theBean.STATUS==0">处理中</s:elseif>
						    <s:elseif test="#theBean.STATUS==1">处理完成</s:elseif>
						</td>
						<td>
						    <s:if test="#theBean.TASK_TYPE==0">部署任务</s:if>
						    <s:elseif test="#theBean.TASK_TYPE==1">升级任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==2">启动任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==3">停止任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==4">重启任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==5">恢复任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==6">卸载任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==7">捕获任务</s:elseif>
						</td>
						<td>
						    <s:property value="#theBean.SUCCESS_COUNT" default="-"/>
						</td>
						<td>
						    <s:property value="#theBean.FAILURE_COUNT" default="-"/>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
    </div>
</div>
</s:form>
</body>
