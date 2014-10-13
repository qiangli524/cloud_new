<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <title>虚拟服务器详细信息</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    </head>

    <input id="hyId" type="hidden" value="<s:property value="hyId"/>">  
    <input id="type" type="hidden" value="<s:property value="type"/>">  
    <body class="pop-body scrollbody" style="overflow-x:hidden">
    	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til" width="25%">所属数据中心</td>
			 		<td>
						<s:property value="vmHostDetailInfoVO.blgCenterName"/>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">所属主机名</td>
					<td>
						<s:property value="vmHostDetailInfoVO.blgHostName"/>
					</td>
				</tr> 
				<tr align="left">
					<td class="til" width="25%">虚拟机名称</td>
			 		<td>
						<s:property value="vmHostDetailInfoVO.vmHostName"/>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">虚拟机IP</td>
					<td>
						<s:property value="vmHostDetailInfoVO.vmHostIp"/>
					</td>
				</tr> 
				<tr align="left">
					<td class="til" width="25%">承载业务系统</td>
			 		<td>
			 			<s:iterator value="vmHostDetailInfoVO.busiSysList"  id="busiSyss" status="busiSys">
			 				<s:property value="#busiSyss.busiSysName1"/>
			 				<s:property value="#busiSyss.busiSysName2"/>
			 				<s:property value="#busiSyss.busiSysName3"/>
			 				<s:property value="#busiSyss.busiSysName4"/>
			 				<s:property value="#busiSyss.busiSysName5"/>
			 				<p>
			 			</s:iterator>
					</td>
				</tr> 
			</table>
		</div>
		<hr>
		<div id="chartContainer"></div>
     </body>
     
    <script type="text/javascript">
        $(document).ready(function(){
        	//getChartsEntity();
        });
        setTimeout("getChartsEntity()",2000);
        var hyId = $("#hyId").val();
        var type = $("#type").val();
        
        //alert(hyId);
        //alert(type);
        //获取报表中实体列表
        function getChartsEntity() {
     		//插入报表数据源
         	  $("#chartContainer").insertFusionCharts({
                 swfUrl: "FusionCharts/MSColumn2D.swf", 
                 dataSource: "homepagereport_vmHostDetailInfoData.do?hyId="+hyId+"&type="+type, 
                 dataFormat: "jsonurl", 
                 width: "100%", 
                 height: "270"
           });
        }
     </script>
     
</html>
