<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>测试</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/ah/calendar/css/jquery-ui.css" />
	<link rel="stylesheet" id="skin" href="<%=request.getContextPath() %>/ah/calendar/css/jquery-ui-1.9.2.custom.min14.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/ah/calendar/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/ah/calendar/js/jquery-ym-datePlugin-0.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
	<%!
	public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
	<style>
		.panel-datacenter {
			width: 300px;
			border-right: 1px dashed #ccc;
			padding: 4px 10px;
		}
		select option,div.datacenter tr td{
			color:#4F4F4F;
		}
		
		.cla,.cl{
			/*text-align:center;*/
			/*background-color: #A9DCFF;*/
			/*border-left:1px dashed #ccc;*/
			border-bottom: 1px dashed #ccc;
		}
	</style>
	<script>
		
		$("#from").css('font-size','1.1em'); //改变日期控件大小
		$.ymdateplugin.setDefaults( $.ymdateplugin.regional[ "zh-TW" ] );
		var selectTime = "";
		$(function() {
			var nowTime = '<%=request.getAttribute("nowTime")%>';
			$("#selectedDay").attr("value",nowTime);
			$( "#from" ).ymdateplugin({
				showTimePanel: true,
				showMonthAfterYear: true, // 月在年之后显示
			    changeMonth: true,     		// 允许选择月份
			    changeYear: true,      		// 允许选择年份
			    showAnim: 'drop',     		// 动画效果风格 
			    showOtherMonths: true,		// 显示其他月份的日期
			    selectOtherMonths: true 	// 允许选择其他月份的日期 
			});
			timeChange();
		});
		
		
		function timeChange(){
						 //获取日历时间
						 selectTime = $("#selectedDay").val();
						 //显示饼图
                		 $("#cpuChart").insertFusionCharts({
        	                 swfUrl: "FusionCharts/Pie2D.swf", 
        	                 dataSource: "calendarNew_showCPUResource.do?calendarObj.selectTime="+selectTime,
        	                 dataFormat: "jsonurl", 
        	                 width: "320", 
        	                 height: "200", 
        	                 id: "cpuCharts"
        	            });
                		 $("#memChart").insertFusionCharts({
        	                 swfUrl: "FusionCharts/Pie2D.swf", 
        	                 dataSource: "calendarNew_showMemResource.do?calendarObj.selectTime="+selectTime,
        	                 dataFormat: "jsonurl", 
        	                 width: "320", 
        	                 height: "200", 
        	                 id: "memCharts"
        	            });
                		 $("#storChart").insertFusionCharts({
        	                 swfUrl: "FusionCharts/Pie2D.swf", 
        	                 dataSource: "calendarNew_showStorageResource.do?calendarObj.selectTime="+selectTime,
        	                 dataFormat: "jsonurl", 
        	                 width: "320", 
        	                 height: "200", 
        	                 id: "storCharts"
        	            });
        	             $("#netChart").insertFusionCharts({
        	                 swfUrl: "FusionCharts/Pie2D.swf", 
        	                 dataSource: "calendarNew_showNetResource.do?calendarObj.selectTime="+selectTime,
        	                 dataFormat: "jsonurl", 
        	                 width: "320", 
        	                 height: "200", 
        	                 id: "netCharts"
        	            });
		}
	</script>
</head>
<body bgcolor="#FFFFFF">
	<s:form action="" method="post" id="theForm" >
	 <table width="98%" height="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr >
			<td  valign="top" class="panel-datacenter" height="100%" >
				<table border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">
				 <tr >
					<td>
						<font style="font-family:STKaiti;font-size:120%;color:#4F4F4F;">您选择的日期：</font>
					</td>
					<td>
						<input type="text" id="selectedDay" disabled="disabled" />
					</td>
				 </tr>
	       	 	</table> 
				<div id="from" align="left" />
			</td>
			<td height="100%"  >
		    	<div class="datacenter" >
		    		<table id="tabTwo"  height="100%" border="0" width="650px" cellspacing="8" >
		    			<tr  >
		    				<td width="50%" class="cla">
		    					<img src="<%=request.getContextPath()%>/sxcloud/images/virtual/cpu.gif" width="20" height="20" />&nbsp;CPU资源
		    				</td>
		    				<td width="50%" class="cl">
		    					<img src="<%=request.getContextPath()%>/sxcloud/images/virtual/mem.gif" width="20" height="20" />&nbsp;内存资源
		    				</td>
		    			</tr>
		    			<tr>
		    				<td width="50%" >
		    					<div id="cpuChart" ></div>
		    				</td>
		    				<td width="50%" >
		    					<div id="memChart" ></div>
		    				</td>
		    			</tr>
		    			<tr >
		    				<td class="cla">
		    					<img src="<%=request.getContextPath()%>/sxcloud/images/virtual/storage.png" width="20" height="20" />&nbsp;存储资源
		    				</td>
		    				<td class="cl">
		    					<img src="<%=request.getContextPath()%>/sxcloud/images/virtual/ip.png" width="20" height="20" />&nbsp;网络资源
		    				</td>
		    			</tr>
		    			<tr>
		    				<td >
		    					<div id="storChart"></div>
		    				</td>
		    				<td >
		    					<div id="netChart"></div>
		    				</td>
		    			</tr>
		    		</table>
		    	</div>  
		    	
   		 	 </td>
		</tr>
	 </table>
	</s:form>
</body>
</html>
