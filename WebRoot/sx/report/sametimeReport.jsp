<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <title></title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
    	    	insertData();
        })
        	function insertData(){
        		$("#chartContainer").insertFusionCharts({
	                 swfUrl: "FusionCharts/ZoomLine.swf",
	                // dataSource: "sxreport_sametimeData.do", 
	                 dataFormat: "jsonurl", 
	                 width: "100%", 
	                 height: "90%", 
	                 id: "sametimeReport"
	           });
        	}
        	
        	$(function(){
        		$("#queryReports").click(function(){
        			var ids ='';
        			$(":input:checked").each(function(){
        				ids += $(this).val()+",";
        			});
        			var kpi = $("[name='changeType']").find('option:selected').val();
        			var starttime = $("#starttime").val();
        			var endtime = $('#endtime').val();
        			var type= $('#type').val();
        			$("#chartContainer").updateFusionCharts({
	                 	dataSource: "sxreport_sametimeData.do?kpi="+kpi+"&starttime="+starttime+"&endtime="+endtime+"&type="+type+"&ids="+ids
	           		});
        		})
        	})
        </script>
    </head>
    <body class="pop-body scrollbody" style="overflow-x:hidden">
    	<s:hidden id="type" value="%{search.type}"></s:hidden>
    	<div style="float:left;margin:5,30,5,5px;">
    		<s:iterator value="entityList" id="theBean">
    			<input type="checkbox" value='<s:property value="#theBean.id"/>' />
    			<s:property value="#theBean.name"/></br>
    		</s:iterator>
    	</div>
    	
    	 <div>
    	 	<div style="margin:10,5,20,5px;"">
    	 	<table>
    	 		<tr>
				<td align="center">
					指标类型：<s:select list="#{'1':'CPU', '2':'内存','3':'光纤卡读写','4':'网卡读写'}" id="changeType" name="changeType"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;
					时间段：从&nbsp;
					<input id="starttime" style="txt" type="text" name="startTime" size="20" class="Wdate"
					onFocus="WdatePicker({minDate:'1970-10-01',maxDate:'#F{$dp.$D(\'endtime\')||\'new Date()\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;&nbsp;
					到&nbsp;&nbsp;<input id="endtime" style="txt" type="text" name="endTime" size="20" class="Wdate"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'new Date()',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type = "button" class="thickbox btn-style02" value = "查询" id="queryReports" />
				</td>
				</tr>
			</table>
			</div>
			<div id="chartContainer"></div>
    	 </div>
    </body>
</html>
