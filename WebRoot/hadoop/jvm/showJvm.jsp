<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
		select {margin-top:-5px;margin-left:3px;}
	</style>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	var param = queryParam();
	    	insertFusionChartData(param);
	     });

	    function queryParam(){
	    	var host_name =$("#host_name").val();//主机名字
	    	var cluster_id = $("#cluster_id").val();//集群名字
	    	var kpi_id = $("#kpi_id").val();
	    	var points = $("#points").val();
	    	var startTime = $("#starttime").val();
			var endTime = $("#endtime").val();
	    	var param = "obj.host_name="+host_name+"&obj.cluster_name="+cluster_id+"&obj.kpi_id="+kpi_id+"&obj.points="+points+"&obj.startTime="+startTime+"&obj.endTime="+endTime;
	    	return param;
	    };
	    
	    function updateFusionChartData(){
	    	var param = queryParam();
	    	$("#chartJVM").updateFusionCharts({
               dataSource: "hadoopJvm_queryHadoopJvm.do?"+param
          }); 
	    }
    	function insertFusionChartData(param){
    		 $("#chartJVM").insertFusionCharts({
                 swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                 dataSource: "hadoopJvm_queryHadoopJvm.do?"+param,
                 dataFormat: "jsonurl", 
                 width: "750", 
                 height: "350", 
                 id: "jvmChart"
           }); 
    	}
    </script>  
  </head>   
  <body class="pop-body scrollbody"> 
  	<s:hidden name="hostInfoObj.host_name" id="host_name"></s:hidden>
  	<s:hidden name="hostInfoObj.cluster_id" id="cluster_id"></s:hidden>
  	<div align="center">
  		<tr>
  			<td class="til">指标:</td>
  			<td>
  				<s:select list="#{'jvm.JvmMetrics.GcCount':'JVM进行GC的次数','jvm.JvmMetrics.GcTimeMillis':'GC花费的时间',
  					'jvm.JvmMetrics.LogError':'Log中输出ERROR的次数','jvm.JvmMetrics.LogFatal':'Log中输出FATAL的次数',
  					'jvm.JvmMetrics.MemHeapCommittedM':'JVM分配的堆大小','jvm.JvmMetrics.MemHeapUsedM':'JVM已经使用的堆大小',
  					'jvm.JvmMetrics.MemNonHeapCommittedM':'JVM分配给非堆的大小','jvm.JvmMetrics.MemNonHeapUsedM':'JVM已使用的非堆的大小',
  					'jvm.JvmMetrics.ThreadsBlocked':'处于BLOCKED状态线程数量','jvm.JvmMetrics.ThreadsTerminated':'处于TERMINATED状态线程数量',
  					'jvm.JvmMetrics.ThreadsTimedWaiting':'处于TIMED_WAITING状态线程数量'}" id="kpi_id" onchange="updateFusionChartData();" cssClass="select-1 vm"></s:select>
  			</td>
  			<td class="til">显示点数</td>
  			<td><s:select list="#{'10':'10','50':'50','100':'100','500':'500'}" id="points" onchange="updateFusionChartData();" cssClass="select-1 vm" cssStyle="width:70px;"></s:select>
  			</td>
  			<td align="center">
					时间段：从&nbsp;
					<input id="starttime" style="txt" type="text" name="startTime" size="18" class="Wdate"
					onFocus="WdatePicker({minDate:'1970-10-01',maxDate:'#F{$dp.$D(\'endtime\')||\'new Date()\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;&nbsp;
					到&nbsp;&nbsp;<input id="endtime" style="txt" type="text" name="endTime" size="18" class="Wdate"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'new Date()',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="ubtn-1 mgl-20"><input type="button" id="queryReports" onclick="updateFusionChartData()" value="查询" /></span>
			</td>
  		</tr>
  	</div>
  	<br>
  	<br>
  	<table align="center">
  		<tr>
  			<td>
  				<div id="chartJVM">JVM</div>
  			</td>
  		</tr>
  	</table>
  </body> 
</html>