<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/treeTable/stylesheets/jquery.treetable.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/treeTable/stylesheets/jquery.treetable.theme.default.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/treeTable/javascripts/src/jquery.treetable.js"></script>
  <script type="text/javascript">
  
  $(document).ready(function() {
	  $("#theTable").treetable({ expandable: true });
  });
  
  </script>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>

<script type="text/javascript">
	$(function(){
	    $("#search").click(function(){
	    	$("#theForm").submit();
	    });
	    
	    $("#resert").click(function(){
	    	$("#processname").val("");
	    	$("#processkey").val("");
	    	$("#ip").val("");
	    });
	});
</script>
</head>
<body>
<s:form action="busiareainfo_listProcess.do" method="post" cssStyle="theForm" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
			<table width="100%" class="querytable" border="0">
				<tr>
					<td class="til" >
						进程名称:
					</td>
					<td>
					    <s:textfield name="processObj.PROCESS" id="processname"></s:textfield>
					</td>
					<td class="til">
						进程标识:
					</td>
					<td>
					    <s:textfield name="processObj.PROCESS_KEY" id="processkey"></s:textfield>
					</td>
					<td class="til">
						所属主机:
					</td>
					<td>
						<s:textfield name="processObj.IP" id="ip"></s:textfield>
					</td>
				</tr>
				<tr>
					<td colspan="8" class="btns">
						<div>
							<input type="button" class="thickbox btn-style02" value="查询" id="search"/>
							<input type="button" class="btn-style02" value="重置" id="resert"/>
						</div>
					</td>
				</tr>
			</table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder" style="position:absolute;bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-head" name="operbnt">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
        <table id="theTable" width="100%"  class="blue-table sorttable" border="0" cellspacing="0" name="processtable">
				<thead>
					<tr>
					    <th name="change">
							选择
						</th>
						<th onclick="sort(theTable,2,'string')">
							进程名
						</th>
						<th onclick="sort(theTable,3,'string')">
							进程标识		
						</th>
						<th onclick="sort(theTable,4,'int')">
							进程个数		
						</th>
						<th onclick="sort(theTable,5,'string')">
							主机
						</th>
						<th name="parent-status" onclick="sort(theTable,6,'string')">
							运行状态
						</th>
						<th name="parent-status" onclick="sort(theTable,7,'string')">
							启停状态
						</th>
						<th onclick="sort(theTable,8,'string')">
							启动脚本
						</th>
						<th onclick="sort(theTable,9,'string')">
							停止脚本
						</th>
					</tr>
				</thead>
				<tbody>
				 <s:iterator value="resultList" id="theBean" status="l">
						<s:if test="#theBean.PARENT_ID==0">
		                  <tr class="expanded" align="left" data-tt-id='<s:property value="#theBean.ID"/>'>
						</s:if>
						<s:else>
						  <tr class="expanded" align="left" data-tt-id='<s:property value="#theBean.ID"/>' data-tt-parent-id='<s:property value="#theBean.PARENT_ID"/>'>
						</s:else>
						<td processid='<s:property value="#theBean.ID"/>' name="change">
							<input type="checkbox" value='<s:property value="#theBean.ID"/>' userid='<s:property value="#theBean.USER_ID"/>'/>
						</td>
					    <td processid='<s:property value="#theBean.ID"/>'>
							<s:property value="#theBean.PROCESS" default="无"/>
						</td>
						<td>
							<s:property value="#theBean.PROCESS_KEY" default="无"/>
						</td>
						<td>
							<s:property value="#theBean.PROCESS_COUNT" default="无"/>
						</td>
						<td>
						    <s:property value="#theBean.IP" default="-"/>/<s:property value="#theBean.USERNAME" default="-"/>
						</td>
						<td name="parent-status">
					        <s:if test="#theBean.PROCESS_STATE==1"><span style="color: red">意外停止<span/></s:if>
						    <s:elseif test="#theBean.PROCESS_STATE==2"><span style="color: blue">运行良好<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==0">无</s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==3"><span style="color: red">启动失败<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==4"><span style="color: red">停止失败<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==5"><span style="color: red">意外启动<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==6"><span style="color: red">个数不符<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==7"><span style="color: red">网络异常<span/></s:elseif>
						</td>
						<td name="parent-status">
							<span id="div<s:property value="#theBean.ID"/>" name="state">
							    <s:if test="#theBean.ISRUNNING==3">
									<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />检测中
								</s:if>
							    <s:elseif test="#theBean.ISRUNNING==0">
							        <s:if test="#theBean.TAST_TYPE==0">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1"/>正在启动</s:if>
									<s:if test="#theBean.TAST_TYPE==1">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />正在启动</s:if>
									<s:if test="#theBean.TAST_TYPE==2"><span state="2">已停止</span></s:if>
								</s:elseif>
								<s:elseif test="#theBean.ISRUNNING==1">
								    <s:if test="#theBean.TAST_TYPE==0">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1"/>正在停止</s:if>
									<s:if test="#theBean.TAST_TYPE==1">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />正在停止</s:if>
									<s:if test="#theBean.TAST_TYPE==2"><span state="3">运行中</span></s:if>
								</s:elseif>
								<s:elseif test="#theBean.ISRUNNING==2">
									<span state="4">部分启动</span>
								</s:elseif>
							</span>
						</td>
						<td align="center">
               			   <div class="hidden" title='<s:property value="#theBean.START_SCRIPT" default="-"/>'>
               				  <s:property value="#theBean.START_SCRIPT" default="-"/>
               			   </div>
						</td>
						<td align="center">
               			   <div class="hidden" title='<s:property value="#theBean.STOP_SCRIPT" default="-"/>'>
               				  <s:property value="#theBean.STOP_SCRIPT" default="-"/>
               			   </div>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
