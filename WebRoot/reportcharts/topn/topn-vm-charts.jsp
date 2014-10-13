<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <title>TopN</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
        <script type="text/javascript">
        var type = '';
        var topName = '';
        $(document).ready(function(){
            /*var fusioncharts = 'fusioncharts';
               $("#chartContainer").bind(fusioncharts + "beforedataupdate", function (e, args) {
        	   var type =$(this).attr("name");
               var topName = $(this).children("span").text();
        	   getChartsEntity(type,topName);
           });*/
           $("#topn").change(function(){
        	   type =$(this).val();
               topName = $(this).children("span").text();
        	   getTopN();
           });
           $("#topn:eq(0)").trigger("change");
         });
         //获取报表中实体列表
         function getChartsEntity(type,topname) {
           $("#tab tr:not(:first)").remove();   //首先删除，除第一行之外的所有表格
           var num = $("#num").val();
      	   var id = $("#id").val();
      	   if (id.length == 0) {
				id = $("#treeid").val();
		   }
      	   var params = "top_num="+num+"&type="+type+"&id="+id;
    	   if (topname != undefined) {
    		 params = params + "&topName="+topname;
    	   }
      	   if (type == 'cpu') {
      		//插入报表数据源
          	   $("#chartContainer").insertFusionCharts({
                     swfUrl: "FusionCharts/Column2D.swf", 
                     dataSource: "entityTopN_queryVmTopN.do?" + params, 
                     dataFormat: "jsonurl", 
                     width: "99%", 
                     height: "400px", 
                     id: "topnVirtualMachineCharts"
               });
      	   } else {
      		 $("#chartContainer").updateFusionCharts({
   	          dataSource: "entityTopN_queryVmTopN.do?" + params
   	         });
      	   }
      	   var url = "entityTopN_queryVmTopNList.do?" + params;
      	   $.getJSON(url,{'time':new Date().toString()},function(data){
      		   	if (data != null) {
	                 for(var i = 0; i < data.length ;i++){
	                	 if (typeof(data[i].cpu_usage)=="undefined"){
	                		 data[i].cpu_usage = 0;
	                	 }
	                	 if (typeof(data[i].mem_usage)=="undefined"){
	                		 data[i].mem_usage = 0;
	                	 }
	                  $("<tr><td>"+data[i].name+"</td><td>"
	                   			 +data[i].ip+"</td><td>"
	                   			 +data[i].net_name+"</td><td>"
	                   			 +data[i].businame+"</td><td>"
	                   			 +data[i].cpu_usage+"%</td><td>"
	                   			 +data[i].mem_usage+"%</td></tr>").insertAfter($("#tab tr:eq("+i+")"));
	                 }
      		   	}
		   });
         }
         function getTopN() {
             getChartsEntity(type,topName);
         }
        </script>
    </head>

    <body >
    	<s:hidden name="id" id="id"></s:hidden>
		<div id="time">
			<table style="width: 100%;height: 20px;border: 0" >
				<tr>
					<td style="text-align: right;">
						<s:if test="id == null || id == ''">
								<s:select list="treeList" id="treeid" listKey="id" listValue="name" cssClass="select-1 vm"></s:select>
						</s:if>
					<s:select list="#{'cpu':'CPU使用率','mem':'内存使用率','storeikbps':'存储写kbps','storeokbps':'存储读kbps','networkokbps':'网络读kbps','networkikbps':'网络写kbps'}" id="topn" cssClass="select-1 vm" />
					TOP<input type="text"  id="num" value="10" class="vm"  style="height:29px;line-height:19px;border:solid 1px #c3c3c3;width:30px;"/>
					<input type="button" value="GO" id="set" onclick="getTopN();" class="ubtn-3 vm mgl-3"/></td>
						<td width="20px"></td>
				</tr>
			</table>
		</div>
        <div id="chartContainer">加载宿主机TopN</div>
        <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="tab">
				<thead>	
					<tr>
						<th>虚拟机名称</th>
						<th>IP地址</th>
						<th>VLAN</th>
						<th>所属业务</th>
						<th>CPU使用率</th>
						<th>内存使用率</th>
						<!-- <th>存储使用率</th> -->
					</tr>
				</thead>
				<tbody>
					<s:iterator id="list" value="resultList">
						<tr>
							<td><s:property value="#list.name"/></td>
							<td><s:property value="#list.ip"/></td>
							<td><s:property value="#list.net_name"/></td>
							<td><s:property value="#list.businame"/></td>
							<td><s:property value="#list.cpu_uage"/>%</td>
							<td><s:property value="#list.mem_usage"/>%</td>
							<%-- <td><s:property value="#list.store_usage"/>%</td> --%>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
    </body>
</html>
