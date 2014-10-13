<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<style type="text/css">
.font-more{ width:170px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
.font-more-percent{ width:50px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>
<title></title>
<script type="text/javascript">
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="busitree_bizsysView" method="post" id="theForm">
<s:hidden name="theForm.id" id="id"></s:hidden>
<div class="scrollbody">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">部署机IP</th>
				   <th onclick="sort(theTable,1,'string')">业务系统</th>
				   <th onclick="sort(theTable,2,'string')">应用</th>
				   <th onclick="sort(theTable,3,'string')">上线路径</th>
				   <th onclick="sort(theTable,4,'string')">部署状态</th>
                   <th onclick="sort(theTable,5,'string')">服务状态</th>
                   <th onclick="sort(theTable,6,'date')">部署时间</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator value="theForm.resultList" id="theBean">
						<tr>
						    <td> <s:property value="#theBean.IP"/></td>
						    <td><s:property value="#theBean.SYS_NAME"/></td>
							<td><s:property value="#theBean.APPNAME"/></td>
							<td  style="width: 170px">
							<div  class="font-more" title='<s:property value="#theBean.DEPLOYPATH"/>' >
								<s:property value="#theBean.DEPLOYPATH"/>
								</div>
							</td>
							<td>
							  <span id="div<s:text name="#theBean.ID"/>">
							   <a  class="font-more-percent" title='<s:property value="#theBean.DEPLOY_PERCENT"/>' >
							    <s:if test="#theBean.DEPLOY_FLAG==0">
								    未部署
								</s:if>
								<s:elseif test="#theBean.DEPLOY_FLAG==1">
								  <img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在部署
								</s:elseif>
								<s:elseif test="#theBean.DEPLOY_FLAG==2">
								   已部署
								</s:elseif>
								<s:elseif test="#theBean.DEPLOY_FLAG==11">
								   部署失败
								</s:elseif>
								</a>
							  </span>
							</td>
							<td>
							   <s:if test="#theBean.serveState==0">
									停止
								</s:if>
								<s:elseif test="#theBean.serveState==1">
									运行
								</s:elseif>
								<s:else>
									异常
								</s:else>
							</td>
							<td >
								<s:if test="#theBean.DEPLOYEENDTIME!=null">
									<s:property value="#theBean.DEPLOYEENDTIME"/>
								</s:if>
								<s:else>
									未部署
								</s:else>
							</td>
						</tr>
				</s:iterator>
			  </tbody>
			</table>
		<div class="pages mgb-10">
			<jsp:include page="../../sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
</div>
</s:form>
</body>
