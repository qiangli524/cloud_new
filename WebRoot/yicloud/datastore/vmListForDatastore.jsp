<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>

<title></title>
</head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>


<body class="pop-body scrollbody">
<s:form action="VmDatastoreRelation_vmListForDatastore.do" method="post" cssClass="theForm" id="theForm">
<s:hidden id="EQ_ID" name="theForm.EQ_ID"></s:hidden>
<s:hidden id="flag" name="theForm.flag"></s:hidden>
<div class="pd-20 bgcolor-1">
	
			<table id = "theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">虚拟机名称</th>
					<th onclick="sort(theTable,1,'ip')">IP</th>
					<th onclick="sort(theTable,2,'string')">虚拟化类型</th>
					<%--<th onclick="sort(theTable,3,'string')">应用个数</th>
					<th onclick="sort(theTable,3,'string')">业务</th>
					<th onclick="sort(theTable,4,'string')">网络域</th>
					--%>
					<th onclick="sort(theTable,5,'int')">CPU(核)</th>
					<th onclick="sort(theTable,6,'int')">内存(G)</th>
					<th onclick="sort(theTable,7,'int')">存储(G)</th>
					<%--<th>性能</th>
					--%><th onclick="sort(theTable,9,'string')">虚拟机状态</th>
					<th onclick="sort(theTable,10,'string')">业务状态</th>
					<%--<th>明细</th>
			  --%></tr>
			  </thead>
			  <tbody>
			  <s:iterator value="vmList" id="theBean">
			  	
			  		<td> 
			  			<div class="hidden" title='<s:property value="#theBean.VH_NAME"/>'>
			  				<s:property value="#theBean.VH_NAME"/></a>
			  			</div> 
			  		</td>
			  		<td><s:property value="#theBean.VH_IP"/></td>
			  		<td>
			  		    <s:if test="#theBean.VH_TYPE='1'">
			  		         VMWARE
			  		    </s:if>
			  		    </td>
			  		<%--<td hostid='<s:property value="#theBean.H_ID" />'>
			  			<s:if test="#theBean.APPNUM !=0">
			  				<a href='javascript:;' name='app_list'>
			  					<s:property value="#theBean.APPNUM"/>个
			  				</a>
			  			</s:if>
			  			<s:else>
			  				<s:property value="#theBean.APPNUM"/>个
			  			</s:else>
			  		</td>
			  		--%><%--<td> 
			  			<div class="hidden" title='<s:property value="#theBean.name"/>'>
			  				<s:property value="#theBean.name" default="-"/></a>
			  			</div> 
			  		</td>
			  		<td><s:property value="#theBean.NET_NAME" default="-"/></td>
			  		--%><td><s:property value="#theBean.VH_CPU"/></td>
			  		<td>
			  			<s:if test="%{VH_MEM%1024==0}">
			  				<fmt:formatNumber type="currency" pattern="#0" value="${VH_MEM/1024}"></fmt:formatNumber>
			  			</s:if>
			  			<s:else>
			  				<fmt:formatNumber type="currency" pattern="#0.00" value="${VH_MEM/1024}"></fmt:formatNumber>
						</s:else>
			  		</td>
			  		<td>
			  		
			  				<fmt:formatNumber type="currency" pattern="#0" value="${VH_STORAGE/1024}"></fmt:formatNumber>
			  			
			  		<%--<s:property value="#theBean.VH_STORAGE"/>
			  		
			  		--%></td>
			  		<%--<td><a href='javascript:;' name='vm_motion' connect_id='<s:property value="#theBean.connectId"/>'>性能</a></td>
			  		--%><td id="stateText">
			  			<s:if test="#theBean.VH_STAT==1">
							正在运行
						</s:if>
						<s:if test="#theBean.VH_STAT==0">
							已关闭
						</s:if>
						<s:if test="#theBean.VH_STAT==2">
							挂起
						</s:if>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.BUSI_STATUS==1">
		  				  上线
		  				</s:if>
		  				<s:else>
		  				  已分配
		  				</s:else>
			  		</td><%--
			  		<td ><a href="javascript:showVMDetail('<s:property value="#theBean.VH_NAME"/>','<s:property value="#theBean.EQ_NAME"/>','<s:property value="#theBean.VH_TYPE"/>',
			  		'<s:property value="#theBean.EQ_ID"/>','<s:property value="#theBean.VH_SYSTEM"/>','<s:property value="#theBean.VH_CPU"/>','<s:property value="#theBean.VH_MEM"/>',
			  		'<s:property value="#theBean.VH_STORAGE"/>','<s:property value="#theBean.VH_NETWORK"/>','<s:property value="#theBean.VH_STAT"/>','<s:property value="#theBean.VH_UUID"/>',
			  		'<s:property value="#theBean.VH_IP"/>','<s:property value="#theBean.connectId"/>')">查看</a></td>
			  	--%></tr>
			  </s:iterator>
			  </tbody>
			</table>
		<div class="pages mgb-10">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>

		 </div>
		</div>
	</div>
</s:form>

</body>

</html>
