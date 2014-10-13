<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>My First chart using FusionCharts XT</title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    <style type="text/css">
  	    html,body,form
     {
      margin:0px;
      height:100%;
     }
  	</style>
    <script type="text/javascript">
    	
	    $(function(){
    	    
	    	$("[name='monitor']").click(function(){
	    		var kpiUuid=$(this).attr("kpiUuid");
	    		var hostName=$("#hostName").val();
	    		var clusterName=$("#clusterName").val();
	    		var potCount=$("#potCount").val();
	    		var interval=$("#interval").val();
				$.dialog({
	    			id:'add',
	    			title:'详细信息',
	    			width: '830px',
	    			height: '500px',
	    		    lock:true,
	    			content: "url:hadoopHostServer_getKpiIdChart.do?hostName="+hostName+"&clusterName="+clusterName+"&kpiUuid="+kpiUuid+"&potCount="+potCount+"&interval="+interval
		    	});
	    	});
	    	
			$("#resert").click(function(){
				   $("input[type='text']").val("");
			});
			
			var isHide=$("#isHideKpiList").val();
			 if(isHide!=null&&isHide==1){
				 $("[name='kpi']").hide();
			 }
			 
			 $("#search").click(function(){
	        	  $("#theForm").submit();
			 });
			 
			
	    });
    </script>  
  </head>   
  <body> 
  <s:form action="" id="theForm">
    <s:hidden name="hostName" id="hostName" ></s:hidden>
    <s:hidden name="clusterName" id="clusterName"></s:hidden>
    <s:hidden name="potCount" id="potCount"></s:hidden>
    <s:hidden name="interval" id="interval"></s:hidden>
    <s:hidden name="isCluster" id="isCluster"></s:hidden>
    <s:hidden name="isHideKpiList" id="isHideKpiList"></s:hidden>
<div class="scrollbody">
	<div class="pd-20 bgcolor-1">
		<h2 class="utt-1">指标监控</h2>
       	<div class="bord-1 pd-10">
			<table width="100%"  border="0">
				<tr>
					<td class="til">监控指标名称:</td>
					<td>
						<s:textfield name="kpiId" id="kpiId" size="20"></s:textfield>
					</td>
					<td class="til">监控指标描述:</td>
					<td>
						<s:textfield name="kpiDesc" id="kpiDesc" size="20"></s:textfield>
					</td>
					<td colspan="10" class="pd-10">
						<div align="center">
							<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="search" /></span>
							<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="resert" /></span>
						</div>
					</td>
				</tr>
			</table>
 			 <table id="theTable" width="100%"  class="blue-table sorttable" border="0" cellspacing="0" name="kpi"> 
					<thead>
						<tr>
							<th>
								指标名称
							</th>
							<th>
								指标描述
							</th>
							<th>
								最新数据
							</th>
							<th>
								单位
							</th>
							<th>
								级别
							</th>
							<th>
								图表
							</th>
						</tr>
					</thead>
					<tbody>
					 <s:iterator value="kpiIds" id="theBean">
		               <tr>
							<td>
								<s:property value="#theBean.kpi_id" default="无"/>
							</td>
							<td >
	               				   <s:property value="#theBean.description" default="无"/>
							</td>
							<td>
								<s:property value="#theBean.lastData" default="-"/> 
							</td>
							<td>
								<s:property value="#theBean.unit" default="-"/> 
							</td>
							<td>
								<s:if test="#theBean.level==0">
								  高
								</s:if>
							    <s:elseif test="#theBean.shape==1">
							              中
							    </s:elseif>
							    <s:else>
							             低
							    </s:else>
							</td>
							<td>
							    <a href="javascript:;" kpiUuid='<s:property value="#theBean.id"/>' name="monitor">图表</a>
							</td>
						</tr>
					  </s:iterator>
					</tbody>
				</table>
				<div class="pages mgb-10"><!-- 分页 -->
					<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
				</div>
			</div>
		</div>
	</div>
  	</s:form>
  </body> 
</html>