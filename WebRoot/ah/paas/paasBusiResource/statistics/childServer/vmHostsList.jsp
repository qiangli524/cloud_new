<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link_export.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<%@ include file="/sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<style>
	
</style>
<head>
<title></title>
<script type="text/javascript">
	//展示物理主机的性能信息
    function performDetail(vm_id){
 		$.dialog({
	 		id:'vm_motion',
	 		title:'性能监控',
	 		width: '800px',
	 		height: '500px',
	 		lock:true,
	 		content: 'url:paasHostMonitor_highChartsTabs.do?id='+vm_id+"&eq_type=1"			
	 	 });
      }
	$(function(){
		//查询
		$("#query").click(function(){
			mask('正在查询,请稍后....','0.5','0px');
			theForm.submit();
		});
		//重置
		$("#resert").click(function(){
				   $("input[flag='text']").val("");
				});
	});
</script>
</head>
<body  class="pop-body scrollbody">
<s:form action="paasBusiStatistics_vmHostsList.do" method="post" cssClass="theForm" id="theForm">
	<s:hidden name="obj.uuid" id="uuid"></s:hidden>
	<s:hidden name="obj.server_type" id="server_type"></s:hidden>
	<div class="pd-10 bgcolor-1">
				<div class="clearfix mgt-10">
                <table width="100%"  border="0">
                  <tr>
                  	<td class="til">
								数据中心:
					</td>
					<td>
						<s:select cssClass="select-1 vm" list="obj.dataCenterList" id="center_uuidd" headerKey="" headerValue="---请选择---" listKey="uuid" listValue="name"
							name="obj.center_uuid" value="obj.center_uuid"></s:select>
					</td>
                    <td class="til">虚拟机名称:</td>
                    <td>
                    	<s:textfield name="obj.vmName" cssStyle="width:100px;" cssClass="txt" flag="text"></s:textfield>
                    </td>
                    <td class="til">IP地址:</td>
                    <td>
                    	<s:textfield name="obj.ip" cssStyle="width:100px;" cssClass="txt" flag="text"></s:textfield>
                    </td>                    
                    <td>
						<span class="ubtn-1 mgl-20"><input type="button" id="query" value="查询" /></span>
						<span class="ubtn-2 mgl-20"><input type="button" id = "resert" value="重置" /></span>
					</td>
                  </tr>
                </table>
                </div>
                <br/>
			<table id = "theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			    <tr>
					<th>虚拟机名称</th>
					<th>IP地址</th>
					<th>承载业务</th>
					<th>虚拟化类型</th>	
					<th>性能</th>			
		   	    </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="vmReportFormresultList" id="theBean">
			  	<tr>
			  		<%-- <td>
			  			<input type="checkbox" value="<s:property value='#theBean.vmId'/>" name="checkboxid" title="<s:property value='#theBean.vmName'/>" />
			  		</td> --%>
			  		<td> 
			  			<s:property value="#theBean.vmName" default="-"/>
			  		</td>
			  		<td> 
			  			<s:property value="#theBean.ip" default="-"/>
			  		</td>
			  		<td>
			  		    <s:property value="#theBean.busi" default="-"/>
                    </td>
			  		<td>
			  			<s:if test="#theBean.vmType == 1">
							VMWARE
						</s:if>
						<s:elseif test="#theBean.vmType == 3">
							XEN	
						</s:elseif>
						<s:else>
							其它
						</s:else>
                    </td>
                    <td><a href="#" onclick='performDetail("<s:property value='#theBean.vmId'/>")'>性能</a></td>
			  	</tr>
			  	</s:iterator>
			  </tbody>
			</table>
			<div class="pages">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
</div>
</s:form>
</body>
