<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
  <head>
    <title>My First chart using FusionCharts XT</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/hadoop/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
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
    	    systemLoad();
    	    
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
	    
	  //系统负载
    	function systemLoad(){
    		var hostName=$("#hostName").val();
    		var clusterName=$("#clusterName").val();
    		var potCount=$("#potCount").val();
    		var interval=$("#interval").val();
    		var isCluster=$("#isCluster").val();
    		
    		$("#systemLoad").insertFusionCharts({
                 swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                 dataSource: "hadoopHostServer_listHadoopHostSystemLoad.do?hostName="+hostName+"&clusterName="+clusterName+"&potCount="+potCount+"&interval="+interval+"&isCluster="+isCluster,
                 dataFormat: "jsonurl", 
                 width: "730", 
                 height: "270", 
                 id: "sdfsfds"
           }); 
    		
    		$("#systemCpu").insertFusionCharts({
                swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                dataSource: "hadoopHostServer_listHadoopHostSystemCpu.do?hostName="+hostName+"&clusterName="+clusterName+"&potCount="+potCount+"&interval="+interval+"&isCluster="+isCluster,
                dataFormat: "jsonurl", 
                width: "730", 
                height: "270", 
                id: "dddd"
           }); 
    		
    		
    		$("#systemMemory").insertFusionCharts({
                swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                dataSource: "hadoopHostServer_listHadoopHostSystemMemory.do?hostName="+hostName+"&clusterName="+clusterName+"&potCount="+potCount+"&interval="+interval+"&isCluster="+isCluster,
                dataFormat: "jsonurl", 
                width: "730", 
                height: "270",  
                id: "cccc"
           }); 
    		
    		$("#systemNetwork").insertFusionCharts({
                swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                dataSource: "hadoopHostServer_listHadoopHostSystemNetwork.do?hostName="+hostName+"&clusterName="+clusterName+"&potCount="+potCount+"&interval="+interval+"&isCluster="+isCluster,
                dataFormat: "jsonurl", 
                width: "730", 
                height: "270", 
                id: "fffff"
           }); 
    		
    		if(1==isCluster){
	    		$("#systemDFS").insertFusionCharts({
	                swfUrl: "FusionCharts/ScrollCombi2D.swf", 
	                dataSource: "hadoopHostServer_listClusterHadoopHostDFS.do?hostName="+hostName+"&clusterName="+clusterName+"&potCount="+potCount+"&interval="+interval+"&isCluster="+isCluster,
	                dataFormat: "jsonurl", 
	                width: "730", 
	                height: "270", 
	                id: "fffff"
	           }); 
    		}
    	};
    	
    	function selectShowType(type){
    		var hostName=$("#hostName").val();
    		var clusterName=$("#clusterName").val();
    		var potCount=$("#potCount").val();
    		var interval=$("#interval").val();
    		var isCluster=$("#isCluster").val();
    		var showCpu = $("#showCpu").val();
    		var showMem = $("#showMem").val();
    		var showSystem = $("#showSystem").val();
    		var showNetwork = $("#showNetwork").val();
    		if(type=='cpu'){
    			 $("#systemCpu").updateFusionCharts({
	   	          	 dataSource: "hadoopHostServer_listHadoopHostSystemCpu.do?hostName="+hostName+"&clusterName="+clusterName+"&potCount="+potCount+"&interval="+interval+"&isCluster="+isCluster+"&flag="+showCpu
	   	         });
    		}else if(type=='mem'){
    			$("#systemMemory").updateFusionCharts({
                	dataSource: "hadoopHostServer_listHadoopHostSystemMemory.do?hostName="+hostName+"&clusterName="+clusterName+"&potCount="+potCount+"&interval="+interval+"&isCluster="+isCluster+"&flag="+showMem
           		}); 
    		}else if(type=='system'){
    			$("#systemLoad").updateFusionCharts({
                 dataSource: "hadoopHostServer_listHadoopHostSystemLoad.do?hostName="+hostName+"&clusterName="+clusterName+"&potCount="+potCount+"&interval="+interval+"&isCluster="+isCluster+"&flag="+showSystem
           		}); 
    		}else if(type=='network'){
    			$("#systemNetwork").updateFusionCharts({
                	dataSource: "hadoopHostServer_listHadoopHostSystemNetwork.do?hostName="+hostName+"&clusterName="+clusterName+"&potCount="+potCount+"&interval="+interval+"&isCluster="+isCluster+"&flag="+showNetwork
          		}); 
    		}
    	}
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
	<div style="padding-left:1%">
		<table width="98%" border="0" cellspacing="0" cellpadding="0" >
			<tr>
				<td>
					<div class="mainbox">
     					<h2 class="datacenter dc-tt mgt-15"><span class="txt">最新数据</span></h2>
     					<div class="item" style="height:130px">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">系统CPU性能的最新数据</font></div>
							</div>
							<div class="right">
								<div>cpu核数:<span class="value"><s:property value="resultMap.cpu_num"/></span>核</div>
								<div>cpu空闲:<span class="value"><s:property value="resultMap.cpu_idle"/></span>%</div>
								<div>系统空间占用CPU:<span class="value"><s:property value="resultMap.cpu_system"/></span>%</div>
								<div>用户空间占用CPU:<span class="value"><s:property value="resultMap.cpu_user"/></span>%</div>
								<div>等待输入输出占用CPU:<span class="value"><s:property value="resultMap.cpu_wio"/></span>%</div>
     						</div>	  
     					</div>	
     					<div class="item" style="height:130px">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">系统Memory性能的最新数据</font></div>
								<div class="right">
									<div>内存总量:<span class="value"><fmt:formatNumber value="${(resultMap.mem_total/1024/1024)}" pattern="#,###.##" type="number"/></span>GB</div>
									<div>内存余量:<span class="value"><fmt:formatNumber value="${(resultMap.mem_free/1024/1024)}" pattern="#,###.##" type="number"/></span>GB</div>
									<div>缓冲的交换区总量:<span class="value"><fmt:formatNumber value="${(resultMap.mem_cached/1024/1024)}" pattern="#,###.##" type="number"/></span>GB</div>
									<div>内核缓存的内存量:<span class="value"><fmt:formatNumber value="${(resultMap.mem_buffers/1024/1024)}" pattern="#,###.##" type="number"/></span>GB</div>
									<div>交换区总量:<span class="value"><fmt:formatNumber value="${(resultMap.swap_total/1024/1024)}" pattern="#,###.##" type="number"/></span>GB</div>
								</div>								
							</div>							
						</div>	
     					<div class="item">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">系统负载的最新数据</font></div>
								<div class="right">
									<div>1分钟系统负载:<span class="value"><s:property value="resultMap.load_one"/></span> Loads</div>
									<div>5分钟系统负载:<span class="value"><s:property value="resultMap.load_five"/></span> Loads</div>
								</div>								
							</div>							
						</div>	
						<div class="item">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">系统网络读写的最新数据</font></div>
								<div class="right"  style="margin-top:5px">
									<div>网络输入:<span class="value"><fmt:formatNumber value="${(resultMap.pkts_in)}" pattern="#,###.##" type="number"/></span> Bytes/sec</div>
									<div>网络输出:<span class="value"><fmt:formatNumber value="${(resultMap.pkts_out)}" pattern="#,###.##" type="number"/></span> Bytes/sec</div>
								</div>								
							</div>							
						</div>	
					</div>	
				</td>
			</tr>
		</table>	
	</div>
	<table width="100%"  border="0" >
      	<s:if test="resultMap.cpu_idle!=0 || resultMap.cpu_system!=0 || resultMap.cpu_user!=0 || resultMap.cpu_wio!=0 ">
	      	<div class="mainbox-kpi">
		 		<div class="item">
					<div class="top">
						<div class="ml5"><font color="orange" size="3" style="width: 250px;">系统CPU性能的最新数据</font>
							<select id="showCpu" onchange="selectShowType('cpu')" style="width:120px;">
								<option value="">显示全部</option>
								<option value="cpu_idle">cpu空闲</option>
								<option value="cpu_system">系统空间占用CPU</option>
								<option value="cpu_user">用户空间占用CPU</option>
								<option value="cpu_wio">等待输入输出占用CPU</option>
							</select>
						</div>
					</div>
					<div style="height: 3px;"></div>
					<div id="systemCpu" align="center"></div>
					<div style="height: 3px;"></div>
				</div>
			</div>
		</s:if>
		<s:if test="resultMap.mem_total!=0 ">
			<div class="mainbox-kpi">
		 		<div class="item">
					<div class="top">
						<div class="ml5"><font color="orange" size="3" style="width: 250px;">系统Memory性能的最新数据</font>
							<select id="showMem" onchange="selectShowType('mem')" style="width:120px;">
								<option value="1">内存使用率</option>
							</select>
						</div>
					</div>
					<div style="height: 3px;"></div>
					<div id="systemMemory" align="center"></div>
					<div style="height: 3px;"></div>
				</div>
			</div>
		</s:if>
		<s:if test="resultMap.load_one!=0 || resultMap.pkts_out!=0">
			<div class="mainbox-kpi">
		 		<div class="item">
					<div class="top">
						<div class="ml5"><font color="orange" size="3" style="width: 250px;">系统负载的最新数据</font>
							<select id="showSystem" onchange="selectShowType('system')" style="width:120px;">
								<option value="">显示全部</option>
								<option value="load_one">1分钟系统负载</option>
								<option value="load_five">5分钟系统负载</option>
							</select>
						</div>
					</div>
					<div style="height: 3px;"></div>
					<div id="systemLoad" align="center"></div>
					<div style="height: 3px;"></div>
				</div>
			</div>
		</s:if>
		<s:if test="resultMap.pkts_in!=0 || resultMap.cpu_system!=0">
			<div class="mainbox-kpi">
		 		<div class="item">
					<div class="top">
						<div class="ml5"><font color="orange" size="3" style="width: 250px;">系统网络读写的最新数据</font>
							<select id="showNetwork" onchange="selectShowType('network')" style="width:120px;">
								<option value="">显示全部</option>
								<option value="pkts_in">网络输入</option>
								<option value="pkts_out">网络输出</option>
							</select>
						</div>
					</div>
					<div style="height: 3px;"></div>
					<div id="systemNetwork" align="center"></div>
					<div style="height: 3px;"></div>
				</div>
			</div>
		</s:if>
			<tr>
				<td colspan="2" height="20px"></td>
			</tr>
			<tr>
	 			<td style="padding-left: 2%;">
	 				<div id="systemDFS"></div>
	 			</td>
	 		</tr>
 	 </table>
  	</s:form>
  </body> 
</html>