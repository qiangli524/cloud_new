<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>


</head>
  
 <body>
<div class="mainbody">
<s:form action="datastore_liststorage.do" method="post" id="theForm">
<s:hidden id="hostId" name="theForm.hostId"></s:hidden>
<s:hidden id="deviceId" name="theForm.deviceId"></s:hidden>
<div>

	 <div class="pd-20 bgcolor-1">
			
			
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">存储块名称</th>
					<th onclick="sort(theTable,1,'string')">存储块路径</th>
					<th onclick="sort(theTable,2,'string')">状态</th>
				
					<%--<th>所属存储设备</th>
					
					--%><th onclick="sort(theTable,5,'string')">存储块格式</th><%--
					<th onclick="sort(theTable,6,'int')">总容量(G)</th>
					<th onclick="sort(theTable,7,'int')">可用容量(G)</th>
					--%>
					<%--<th>使用情况</th>
					
			  --%></tr>
			  </thead>
			  <tbody>
			  <s:iterator value="dataStoreList" id="theBean">
			  	<tr>
			  		<td><s:property value="#theBean.NAME"/></td>
			  		<td align="center">
			  			<s:if test="#theBean.STORAGE_URL==null">
			  				-
			  			</s:if>
			  			<s:elseif test="#theBean.STORAGE_URL==''">
			  				-
			  			</s:elseif>
			  			<s:else>

			  				<span style="color: black;" class="font-more" title='<s:property value="#theBean.STORAGE_URL"/>'>
											<s:property value="#theBean.STORAGE_URL" />
							</span>
			  				

			  			</s:else>
			  			
			  		</td>
			  		<td align="center">
			  			<s:if test="#theBean.STATE=='connection'">
			  				<img src="sxcloud/images/virtual/accept.png" style="margin-top: 6px"/>
			  			</s:if>
			  			<s:elseif test="#theBean.STATE=='detach'">
			  				<img src="sxcloud/images/virtual/exception.png" style="margin-top: 6px"/>
			  			</s:elseif>
			  			<s:else>
			  				<img src="sxcloud/images/virtual/cancel.png" style="margin-top: 6px"/>
			  			</s:else>
			  		</td>
			  	

			  		<td><s:property value="#theBean.TYPE"/></td><%--
			  		<td><s:property value="#theBean.CAPACITY"/></td>
			  		<td><s:property value="#theBean.FREE_SPACE"/></td>
			  	
			  		
			  --%></s:iterator>
			  </tbody>
			</table>
			</div>
			<div class="pages mgb-10">
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
						</div>
		 </div>
</s:form>
 </div>
</body>
</html>
