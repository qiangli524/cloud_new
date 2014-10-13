<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
 	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectService,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});

   function selectService(){
	   var serviceType = "";
	   var serviceTypeStr = "";
	   $(":checkbox:checked").each(function(){
		   var aa = $(this).attr("serviceType");
		   if (aa == 1) {
			   serviceTypeStr += "nameNode,";
		   } else if (aa == 2) {
			   serviceTypeStr += "dataNode,"
		   }  else if (aa == 3) {
			   serviceTypeStr += "journalNode,"
		   }  else if (aa == 4) {
			   serviceTypeStr += "nodeManager,"
		   }  else if (aa == 5) {
			   serviceTypeStr += "resourceManager,"
		   }  else if (aa == 6) {
			   serviceTypeStr += "hmaster,"
		   }  else if (aa == 7) {
			   serviceTypeStr += "regionServer,"
		   }  else if (aa == 8) {
			   serviceTypeStr += "hbase_thirftServer,"
		   }  else if (aa == 9) {
			   serviceTypeStr += "znode,"
		   }  else if (aa == 10) {
			   serviceTypeStr += "hive_thirftServer,"
		   } else if (aa == 11) {
			   serviceTypeStr += "impalaxx,"
		   }  else if (aa == 12) {
			   serviceTypeStr += "DFSzkFailoverController,"
		   }  
		   serviceType += aa+",";
	   });
	   api.get("addusergroup").selectService(serviceType,serviceTypeStr);
   }
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="hadoopUserGroup_selectService.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="hadoopUserGroup.cluster_id" id="clusterId"></s:hidden>
	<div class="box on">
      <div class="blue-wrap noborder">
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">服务类型</th>
						<th onclick="sort(theTable,2,'string')">实例个数</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="serviceList" id="theBean">
                		<tr >
                			<td>
                				<input type="checkbox" serviceType='<s:property value="#theBean.service_type" />'/>
                			</td>
                			<td>
                				<s:if test="#theBean.service_type == 1">nameNode</s:if>
						  		<s:elseif test="#theBean.service_type == 2">dataNode</s:elseif>
						  		<s:elseif test="#theBean.service_type == 3">journalNode</s:elseif>
						  		<s:elseif test="#theBean.service_type == 4">nodeManager</s:elseif>
						  		<s:elseif test="#theBean.service_type == 5">resourceManager</s:elseif>
						  		<s:elseif test="#theBean.service_type == 6">hmaster</s:elseif>
						  		<s:elseif test="#theBean.service_type == 7">regionServer</s:elseif>
						  		<s:elseif test="#theBean.service_type == 8">hbase_thirftServer</s:elseif>
						  		<s:elseif test="#theBean.service_type == 9">znode</s:elseif>
						  		<s:elseif test="#theBean.service_type == 10">hive_thirftServer</s:elseif>
						  		<s:elseif test="#theBean.service_type == 11">impalaxx</s:elseif>
						  		<s:elseif test="#theBean.service_type == 12">DFSzkFailoverController</s:elseif>
						  		<s:else>--</s:else>
                			</td>
                			<td>
                				<s:property value="#theBean.example_count"></s:property>个
                			</td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
       </div>
    </div>
    </s:form>
</body>