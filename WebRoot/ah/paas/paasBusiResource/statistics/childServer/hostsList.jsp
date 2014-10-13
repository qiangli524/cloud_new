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
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<%@ include file="/sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title></title>
<script type="text/javascript">
	//展示物理主机的性能信息
    function performDetail(eq_ip,sn){
      	var ext_eq_id=eq_ip+"_"+sn;
 		$.dialog({
	 		id:'vm_motion',
	 		title:'性能监控',
	 		width: '800px',
	 		height: '500px',
	 		lock:true,
	 		content: 'url:paasHostMonitor_highChartsTabs.do?id='+ext_eq_id+"&eq_type=2"			
	 	 });
      }
 	$(function(){
	 	//查询		
	   	$("#search").click(function(){
	   		mask('正在查询,请稍后....','0.5','0px');
			theForm.submit();
	   	});
	   	//重置
	   	$("#resert").click(function(){
	   		theForm.eq_name.value = "";
			theForm.eq_ip.value = "";
	   	});
 	});
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="paasBusiStatistics_hostsList.do" method="post" cssClass="theForm" id="theForm">
	<s:hidden name="obj.uuid" id="uuid"></s:hidden>
	<s:hidden name="obj.server_type" id="server_type"></s:hidden>
	 <div class="pd-10 bgcolor-1">
				<div class="clearfix mgt-10">
					<table width="100%"  border="0">
						<tr>
							<td class="til">主机名称:</td>
							<td><s:textfield name="obj.eq_name" cssClass="txt" id="eq_name"></s:textfield></td>
							<td class="til">主机IP地址:</td>
							<td><s:textfield name="obj.eq_ip" cssClass="txt" id="eq_ip"></s:textfield></td>
							<td>
								<span class="ubtn-1 mgl-20"><input type="button" id="search" value="查询" /></span>
								<span class="ubtn-2 mgl-20"><input type="button" id = "resert" value="重置" /></span>
							</td>
						</tr>
					</table>
				</div>
				<br/>
			<table id = "theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th>主机名称</th>
					<th>IP地址</th>
					<th>主机类型</th>
					<th>状态</th>
					<th>性能</th>				
		    </tr>
			  </thead>
			  <tbody>
				  <s:iterator value="hostInforesultList" id="theBean">
					<tr>
						<td>
							<s:property value="#theBean.eq_name" /></a>
						</td>	
						<td>
							<s:property value="#theBean.eq_ip" />
						</td>										
						<td>
							<s:if test="#theBean.eq_type == 1">
								IBM小型机
							</s:if>
							<s:elseif test="#theBean.eq_type == 2">
								IBM刀片
							</s:elseif>
							<s:elseif test="#theBean.eq_type == 3">
								IBM普通刀片
							</s:elseif>
							<s:elseif test="#theBean.eq_type == 4">
								HPx86刀片
							</s:elseif>
							<s:elseif test="#theBean.eq_type == 5">
								机架服务器
							</s:elseif>
						</td>
						<td>
							<s:if test="#theBean.status == 0">
								未采集到
							</s:if>
							<s:elseif test="#theBean.status == 1">
								正常启动
							</s:elseif>
							<s:elseif test="#theBean.status == 2">
								关闭
							</s:elseif>
							<s:else>
								异常
							</s:else>
							</td>
						<td><a href="#" onclick='performDetail("<s:property value='#theBean.eq_ip'/>","<s:property value='#theBean.sn'/>")'>性能</a></td>
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
