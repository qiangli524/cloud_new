<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
  <style type="text/css">
		div.hidden{
		width:170px;
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="busiareainfo_listOrder.do" method="post" cssStyle="theForm" id="theForm">
		<div class="scrollbody">
			<div class="box on">
				<div class="blue-wrap noborder" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0" name="ordertable">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">
										订单编号
									</th>
									<th onclick="sort(theTable,1,'date')">
										订单有效时间
									</th>
									<th onclick="sort(theTable,2,'int')">
										任务总数
									</th>
									<th onclick="sort(theTable,3,'string')">
										待处理
									</th>
									<th onclick="sort(theTable,4,'string')">
										处理中
									</th>
									<th onclick="sort(theTable,5,'string')">
										处理完成
									</th>
									<th onclick="sort(theTable,6,'date')">
										创建时间
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
				                  <tr>
									<td>
										<s:property value="#theBean.ORDER_ID" default="-"/>
									</td>
									<td>
									   <s:property value="#theBean.EFFECT_TIME" default="-"/>
									</td>
									<td name="taskcount" orderid='<s:property value="#theBean.ID"/>'>
									  <s:property value="#theBean.TASK_COUNT" default="-"/>
									</td>
									<td>
									  <s:property value="#theBean.TASK_WAIT_COUNT" default="-"/>
									</td>
									<td>
									  <s:property value="#theBean.TASK_DEAL_COUNT" default="-"/>
									</td>
									<td>
									  <s:property value="#theBean.TASK_COMPLETE_COUNT" default="-"/>
									</td>
									<td>
									    <s:property value="#theBean.ADD_TIME" default="-"/>
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
