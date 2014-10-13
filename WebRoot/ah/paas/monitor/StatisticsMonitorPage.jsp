<%@ page language="java" import="java.util.*,java.lang.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <title>统计监控</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
        <script type="text/javascript">
        var threadInfoJo = '<%=request.getAttribute("threadInfoJo") %>';
        var connInfoJo = '<%=request.getAttribute("connInfoJo") %>';
        var reqInfoJo = '<%=request.getAttribute("reqInfoJo") %>';
        var totalByteInfoJo = '<%=request.getAttribute("totalByteInfoJo") %>';
        var storeInfoJo = '<%=request.getAttribute("storeInfoJo") %>';
        var objInfoJo = '<%=request.getAttribute("objInfoJo") %>';
        
        var threadInfoLst = '<%=request.getAttribute("threadInfoLst") %>';
        threadInfoLst = eval(threadInfoLst);
        var connInfoLst = '<%=request.getAttribute("connInfoLst") %>';
        connInfoLst = eval(connInfoLst);
        var reqInfoLst = '<%=request.getAttribute("reqInfoLst") %>';
        reqInfoLst = eval(reqInfoLst);
        var totalByteInfoInfoLst = '<%=request.getAttribute("totalByteInfoInfoLst") %>';
        totalByteInfoInfoLst = eval(totalByteInfoInfoLst);
        var storeInfoLst = '<%=request.getAttribute("storeInfoLst") %>';
        storeInfoLst = eval(storeInfoLst);
        var objInfoLst = '<%=request.getAttribute("objInfoLst") %>';
        objInfoLst = eval(objInfoLst);
        
        $(document).ready(function(){
         	
            $("#chartContainer").insertFusionCharts({
	             swfUrl: "FusionCharts/Column2D.swf", 
	             width: "100%", 
	           	 height: "70%",
	             id: "myChartId",
	             dataFormat: "json", 
	             dataSource: threadInfoJo
    		 });
    	});
        function changeMess(){
        	var mess = $("#mess").val();
        	if(mess == "threadInfo"){
        		$("#chartContainer").updateFusionCharts({
                	dataSource: threadInfoJo
                });
        	   $("#tab tr:not(:first)").remove(); 
        	   for(var i = 0; i < threadInfoLst.length ;i++){
     		   		$("<tr><td>"+threadInfoLst[i].kpiName+"</td><td>"+threadInfoLst[i].kpiDec+"</td><td>"
     					+threadInfoLst[i].kpiValue+"</td></tr>").insertAfter($("#tab tr:eq("+i+")"));
     		   }
        	}else if(mess == "connInfo"){
        		$("#chartContainer").updateFusionCharts({
                	dataSource: connInfoJo
                });
        	   $("#tab tr:not(:first)").remove(); 
        	   for(var i = 0; i < connInfoLst.length ;i++){
     		   		$("<tr><td>"+connInfoLst[i].kpiName+"</td><td>"+connInfoLst[i].kpiDec+"</td><td>"
     					+connInfoLst[i].kpiValue+"</td></tr>").insertAfter($("#tab tr:eq("+i+")"));
     		   }
        	}else if(mess == "reqInfo"){
        		$("#chartContainer").updateFusionCharts({
                	dataSource: reqInfoJo
                });
        	   $("#tab tr:not(:first)").remove(); 
        	   for(var i = 0; i < reqInfoLst.length ;i++){
     		   		$("<tr><td>"+reqInfoLst[i].kpiName+"</td><td>"+reqInfoLst[i].kpiDec+"</td><td>"
     					+reqInfoLst[i].kpiValue+"</td></tr>").insertAfter($("#tab tr:eq("+i+")"));
     		   }
        	}else if(mess == "totalByteInfo"){
        		$("#chartContainer").updateFusionCharts({
                	dataSource: totalByteInfoJo
                });
        	   $("#tab tr:not(:first)").remove(); 
        	   for(var i = 0; i < connInfoLst.length ;i++){
     		   		$("<tr><td>"+connInfoLst[i].kpiName+"</td><td>"+connInfoLst[i].kpiDec+"</td><td>"
     					+connInfoLst[i].kpiValue+"</td></tr>").insertAfter($("#tab tr:eq("+i+")"));
     		   }    		
			}else if(mess == "storeInfo"){
				$("#chartContainer").updateFusionCharts({
                	dataSource: storeInfoJo
                });
        	   $("#tab tr:not(:first)").remove(); 
        	   for(var i = 0; i < storeInfoLst.length ;i++){
     		   		$("<tr><td>"+storeInfoLst[i].kpiName+"</td><td>"+storeInfoLst[i].kpiDec+"</td><td>"
     					+storeInfoLst[i].kpiValue+"</td></tr>").insertAfter($("#tab tr:eq("+i+")"));
     		   }
			}else if(mess == "objInfo"){
				$("#chartContainer").updateFusionCharts({
                	dataSource: objInfoJo
                });
        	   $("#tab tr:not(:first)").remove(); 
        	   for(var i = 0; i < objInfoLst.length ;i++){
     		   		$("<tr><td>"+objInfoLst[i].kpiName+"</td><td>"+objInfoLst[i].kpiDec+"</td><td>"
     					+objInfoLst[i].kpiValue+"</td></tr>").insertAfter($("#tab tr:eq("+i+")"));
     		   }
			}
        }
     	
        </script>
    </head>

    <body class="pop-body scrollbody" style="overflow-x:hidden">
    	<s:hidden name="id" id="id"></s:hidden>
		<div id="time">
			<table style="width: 100%;height: 20px;border: 0" >
				<tr>
					<td style="text-align: left;">
						<a href="javascript:;" name="cpu" class="aalink" style="margin-left: 10px"><span style="color: blue">全部信息</span></a>
						<a href="javascript:;" name="cpu" class="aalink" style="margin-left: 10px"><span style="color: blue">刷新页面</span></a>
					</td>
					<td style="text-align: right;">
					<font size="2px" style="margin-right: 5px">选择:</font>
					<s:select list="#{'threadInfo':'进程信息','connInfo':'连接信息','reqInfo':'请求信息','totalByteInfo':'内存信息','storeInfo':'存储信息','objInfo':'过期对象信息'}" id="mess" onchange="changeMess();"/>
					</td>
					
				</tr>
			</table>
		</div>
        <div id="chartContainer">加载宿主机TopN</div>
         <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="tab">
				<thead>	
					<tr>
						<th>参数</th>
						<th>描述</th>
						<th>值</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="obj" value="threadInfoLst">
						<tr>
							<td><s:property value="#obj.kpiName"/></td>
							<td><s:property value="#obj.kpiDec"/></td>
							<td><s:property value="#obj.kpiValue"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		
    </body>
</html>
