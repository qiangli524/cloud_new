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
    $(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	})
 	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectHost,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});

   function selectHost(){
	   var host_id = "";
	   var host_name = "";
	   var connectid = "";
	   $(":checkbox:checked").each(function(){
		   host_id = $(this).attr("host_id");
		   host_name = $(this).attr("host_name");
		   connectid = $(this).attr("connectid");
	   });
	   var oper = $("#oper").val();
	   if (oper == "add") {
		   api.get("addResource").chooseHost(host_id,host_name,connectid);
		} else {
		   api.get("editResource").chooseHost(host_id,host_name,connectid);
		}
   }
   
    </script>
</head>
<body class="pop-body scrollbody">
	<div class="mainbody">
		<s:form action="workorder/workorder_selectHost.do" method="post"
			id="theForm" cssStyle="theForm">
			<s:hidden name="oper" id="oper"></s:hidden>
			<div class="pd-20 bgcolor-1">
				<div class="bord-1 pd-10">
					<div class="utt-2 mgt-20"></div>
					<table id="theTable" width="100%" border="0" cellspacing="0"
						class="blue-table sorttable">
						<thead id="table">
							<tr>
								<th>选择</th>
								<th onclick="sort(theTable,1,'string')">主机名称</th>
								<th onclick="sort(theTable,2,'string')">IP地址</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="hostList" id="theBean">
								<tr>
									<td><input type="checkbox"
										host_id='<s:property value="#theBean.h_uuid" />'
										host_name='<s:property value="#theBean.eq_name" />'
										connectid='<s:property value="#theBean.connectId" />' /></td>
									<td><s:property value="#theBean.eq_name"></s:property></td>
									<td><s:property value="#theBean.eq_ip"></s:property></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pages">
					</div>
				</div>
		</s:form>
	</div>
</body>