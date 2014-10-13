<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<head>
<%
String excel_export_jsp = request.getContextPath() + "/excel/excel_export.jsp";
String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<title></title>
	<style type="text/css">
		div.hidden{
		width:200px;
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
<style type="text/css">
		.font-more{ width:80px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
  <script type="text/javascript">
 	 function searchRequest(){
	    theForm.action = "<%=path%>/bpm/workorder_getAllWorkFlowList.action";
	 	theForm.submit();
	}
	function creset(){
		$("#orderNo").val("");
		$("#orderTitle").val("");
		$("#orderType").val("");
		$("#resType").val("");
	}
	//导出
	function listExp(){
		var orderno = theForm.orderNo.value;
		var ordertitle = theForm.orderTitle.value;
		var ordertype = theForm.orderType.value;
		var restype = theForm.resType.value;
		
		var url="<%=excel_export_jsp%>?key=workorderentitry";
		if (orderno!='') {
		    	url = url + "&orderno=" + orderno;
		}
	    if (ordertitle!='') {
	    	url = url + "&ordertitle=" + ordertitle;
	    }
	    if (ordertype!='') {
		    	url = url + "&ordertype=" + ordertype;
		}
	    if (restype!='') {
	    	url = url + "&restype=" + restype;
	    }
	    alert("url:"+url);
	    exportForm.action =url;
	    exportForm.submit();
	}
  </script>
  </head>
  <body>
    <s:form action="/bpm/workorder_getAllWorkFlowList.action" method="post" id="theForm" cssStyle="theForm">
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">工单编号:</td>
							<td><s:textfield name="obj.orderNo" id="orderNo" cssClass="txt"></s:textfield>
							</td>
							<td class="til">工单标题:</td>
							<td><s:textfield name="obj.orderTitle" id="orderTitle" cssClass="txt"></s:textfield>
							</td>
							<td class="til">工单类型：</td>
							<td><s:select list="#{'':'---请选择--','03':'故障申报','01':'资源开通','02':'资源收回'}" id="orderType" name="obj.orderType"></s:select>
							</td>
							<td class="til">资源类型：</td>
							<td><s:select list="#{'':'---请选择--','03':'故障申报','01':'云主机','02':'数据库'}" id="resType" name="obj.resType"></s:select>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询" onclick="javascript:searchRequest()" />
									<input type = "button" class="btn-style02" value = "重置" id="resetForm" onclick="creset()" />
									<input type="button" class="thickbox btn-style02" value="导出"  onclick = "listExp();" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<jsp:include page="/jsp/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,1,'string')">工单编号</th>
									<th onclick="sort(theTable,2,'string')">工单标题</th>
									<th onclick="sort(theTable,3,'string')">工单类型</th>
									<th onclick="sort(theTable,4,'string')">资源类型</th>
									<th onclick="sort(theTable,5,'date')">创建时间</th>
									<th onclick="sort(theTable,6,'string')">工单阶段</th>
									<th onclick="sort(theTable,7,'string')">当前处理人</th>
									<th onclick="sort(theTable,8,'date')">到达时间</th>
									<th onclick="sort(theTable,9,'String')">紧急程度</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="list" id="obj">
									<tr>
										<td>
											<a href="<%=path%>/bpm/workorder_view.action?obj.id=<s:property value="#obj.id" />&position=my"><s:property value="#obj.orderNo" /></a>
										</td>
										<td>
											<s:property value="#obj.orderTitle" />
										</td>
										<td>
											<s:if test="#obj.orderType == '01'">
												资源开通
											</s:if>
											<s:elseif test="#obj.orderType == '02'">
												资源收回
											</s:elseif>
										</td>	
										<td>
											<s:if test="#obj.resType == '01'">
												云主机
											</s:if>
											<s:elseif test="#obj.resType == '02'">
												数据库
											</s:elseif>
										</td>	
										<td>
											<s:date name="#obj.createTime" format="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>
											<s:if test="#obj.step==null">
												结束
											</s:if>
											<s:else>												
												<s:property value="#obj.step.stepName" />
											</s:else>
										</td>	
										<td>
											<s:if test="#obj.step==null">
												--
											</s:if>
											<s:else>												
													<s:property value="#obj.user.get(\"userName\")" /> 
											</s:else>
										</td>	
										<td>
											<s:property value="#obj.step.startDate" />
										</td>
										<td>
											<s:if test="#obj.orderLevel==0">
												普通
											</s:if>
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
	<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
  </body>
</html>
