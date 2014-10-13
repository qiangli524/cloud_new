<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<style type="text/css">
	.percentage { 
	vertical-align:middle;  
	display:inline-block; 
	background: url(sxcloud/images/nresources/percentage-bg.gif) no-repeat; 
	width:148px; 
	height:14px; 
	margin-top: 4px;}
	.percentage b{ display:block; height:14px; background:url(sxcloud/images/nresources/percentage-bg4.gif) no-repeat; }
	.percentage b.red {background:url(sxcloud/images/nresources/percentage-bg3.gif) no-repeat;}
	
	.percentage2 { 
	vertical-align:middle;  
	display:inline-block; 
	background: url(sxcloud/images/nresources/percentage-bg7.gif) no-repeat; 
	width:700px; 
	height:35px; 
	margin-top: 4px;}
	.percentage2 b{ display:block; height:35px; background:url(sxcloud/images/nresources/percentage-bg8.gif) no-repeat; }
	.percentage2 b.red {background:url(sxcloud/images/nresources/percentage-bg3.gif) no-repeat;}
	
.totalResource {
    background-color: #F0F0F0;
    border: 1px solid #CECECE;
    height: 40px;
    overflow: hidden;
    width: 99%;
}
.font-more{ width:150px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;
}
</style>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript">
function resetForm(theForm){
	$("#queryName").attr("value","");
	$("#queryState").attr("value",'');
	$("#queryType").attr("value",'');
}
function searchRequest(){
	theForm.submit();
}
function gotoURL(name){
	var theForm = document.getElementById("theForm");
	//alert(name);
	theForm.action="resource_allHostList.do?store_uuid="+encodeURI(encodeURI(name));
	theForm.submit();
}
function showStoreHostDetail(uuid){
	$.dialog({
			id:'showStoreHostDetail',
			title:'明细',
			resize:false,
			width: '600px',
			height: '410px',
			lock:true,
			content: 'url:datastore_listStoreHost.do?storeUuid='+uuid
	});
}

function showVmList(uuid){
	$.dialog({
		id:'showVmList',
		title:'虚拟机列表',
		resize:false,
		width: '1100px',
		height: '600px',
		lock:true,
		content: 'url:VmDatastoreRelation_vmListForDatastore.do?vmDatastoreRelationObj.datastore_uuid='+uuid
});
	
}




</script>
</head>
<body>
<div class="mainbody">
<s:form action="datastore_liststorage.do" method="post" id="theForm">
<s:hidden id="hostId" name="theForm.hostId"></s:hidden>
<s:hidden id="deviceId" name="theForm.deviceId"></s:hidden>
<div>
<%--	<div class="totalResource">--%>
<%--		<table width="100%" border="0" style="position: relative;right: -40px">--%>
<%--			<tr>--%>
<%--				<td >--%>
<%--					存储总量:<fmt:formatNumber value="${(theForm.totalStore)}" pattern="#,###" type="number"/>G</td>	--%>
<%--				<td >--%>
<%--					已使用存储:<fmt:formatNumber value="${(theForm.totalUseStore)}" pattern="#,###" type="number"/>G</td>--%>
<%--				<td rowspan="2">--%>
<%--					<div class="percentage2" style="position: relative;left: 40px;color: blue"><b style="width:<s:property value="theForm.totalUserper"/>%"></b></div>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					可用总量:<fmt:formatNumber value="${(theForm.totalFreeStore)}" pattern="#,###" type="number"/>G</td>--%>
<%--				<td>--%>
<%--					存储分配率:<s:property value="theForm.totalUserper"/>%</td>			--%>
<%--			</tr>--%>
<%--		</table>--%>
<%--	</div>--%>
	 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">存储块管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">存储块名称：</label>
				<s:textfield name="theForm.queryName" cssClass="inpt-1 vm"
								id="queryName" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">状态：</label>
				<s:select cssClass="select-1 vm" id="queryState" name="theForm.queryState" list="#{'':'--请选择--','connection':'正常','detach':'异常','destroy':'损坏'}"></s:select>
				<label class="mgl-20 vm">存储块格式：</label>
				<s:select cssClass="select-1 vm" id="queryType" name="theForm.queryType" list="#{'':'--请选择--','NFS':'NFS','LVM':'LVM','ISO':'ISO','VMFS':'VMFS','UDEV':'UDEV','LVMOHBA':'LVMOHBA'}"></s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm()" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
			</div>
			
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">存储块名称</th>
					<th onclick="sort(theTable,1,'string')">存储块路径</th>
					<th onclick="sort(theTable,2,'string')">状态</th>
					<!-- 
					<th onclick="sort(theTable,3,'string')">连接主机数</th>
					 -->
					<th>所属存储设备</th>
					<!--
					<th>存储设备类型</th>
					-->
					<th onclick="sort(theTable,5,'string')">存储块格式</th>
					<th onclick="sort(theTable,6,'int')">总容量(G)</th>
					<th onclick="sort(theTable,7,'int')">可用容量(G)</th>
					<th>使用情况</th>
					<th>所属主机</th>
					<th>虚拟机列表</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
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
			  			
<%--			  				<span class="js_ByEllipsis" title='<s:property value="#theBean.STORAGE_URL"/>'><s:property value="#theBean.STORAGE_URL"/>
			  				</span>--%>
			  				<span style="color: black;" class="font-more" title='<s:property value="#theBean.STORAGE_URL"/>'>
											<s:property value="#theBean.STORAGE_URL" />
							</span>
			  				
<%--			  				<a style="color: black;text-decoration: none;" class="font-more" title='<s:property value="#theBean.STORAGE_URL"/>' >--%>
<%--								<s:property value="#theBean.STORAGE_URL"/>--%>
<%--							</a>--%>
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
			  		<td>
			  			<s:if test="#theBean.deviceName==null || #theBean.deviceName==''">
			  				-
			  			</s:if>
			  			<s:else>
							<s:property value="#theBean.deviceName"/>			  			
			  			</s:else>
			  		</td>
<%--			  		<td>
			  			<s:if test="#theBean.deviceType==null || #theBean.deviceType==''">
			  				-
			  			</s:if>
			  			<s:elseif test="#theBean.deviceType==0">
			  				本地存储
			  			</s:elseif>
			  			<s:elseif test="#theBean.deviceType==1">
			  				SAN
			  			</s:elseif>
			  			<s:elseif test="#theBean.deviceType==2">
			  				NAS
			  			</s:elseif>
			  		</td>--%>
			  		<!--  
			  		<td>
			  			<s:if test="#theBean.CONHOSTNUM!=0">
			  				<a href="javascript:gotoURL('<s:property value="#theBean.store_uuid"/>')"><s:property value="#theBean.CONHOSTNUM"/>个</a>
			  			</s:if>
			  			<s:else>
			  				<s:property value="#theBean.CONHOSTNUM"/>个
			  			</s:else>
			  		</td>
			  		-->
			  		<td><s:property value="#theBean.TYPE"/></td>
			  		<td><s:property value="#theBean.CAPACITY"/></td>
			  		<td><s:property value="#theBean.FREE_SPACE"/></td>
			  		<td>
			  			<div class="percentage"><b style="width:<s:property value="#theBean.use_per"/>%"></b></div>
			  			<s:property value="#theBean.use_per"/>%
			  		</td>
			  		<td><a href="javascript:showStoreHostDetail('<s:property value="#theBean.store_uuid"/>')">查看</a></td>
			  		
			  		<td><a href="javascript:showVmList('<s:property value="#theBean.store_uuid"/>')">查看</a></td>
			  	</tr>
			  </s:iterator>
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
