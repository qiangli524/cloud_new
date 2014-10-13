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
           $("#time a").click(function(){
        	   type =$(this).attr("name");
               topName = $(this).children("span").text();
        	   getTopN();
           });
           $("#time a:eq(0)").trigger("click");
         });
         //获取报表中实体列表
         function getChartsEntity(type,topname) {
           $("#tab tr:not(:first)").remove();   //首先删除，除第一行之外的所有表格
           var num = $("#num").val();
      	   var uuid = $("#uuid").val();
      	   var connect_id = $("#connect_id").val();
      	   var params = "top_num="+num+"&type="+type+"&id="+$("#id").val();
    	   if (topname != undefined) {
    		 params = params + "&topName="+topname;
    	   }
      	   if (type == 'cpu') {
      		//插入报表数据源
          	   $("#chartContainer").insertFusionCharts({
                     swfUrl: "FusionCharts/Column3D.swf", 
                     dataSource: "bmanager_queryVmTopN.do?" + params, 
                     dataFormat: "jsonurl", 
                     width: "100%", 
                     height: "80%", 
                     id: "topnBusiVMCharts"
               });
      	   } else {
      		 $("#chartContainer").updateFusionCharts({
   	          dataSource: "bmanager_queryVmTopN.do?" + params
   	         });
      	   }
      	   var url = "bmanager_queryVmTopNList.do?" + params;
      	   $.getJSON(url,{'time':new Date().toString()},function(data){
      		   	if (data != null) {
	                 for(var i = 0; i < data.length ;i++){
	                  $("<tr><td>"+data[i].name+"</td><td>"
	                   			 +data[i].ip+"</td><td>"
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

    <body class="pop-body scrollbody">
    	<s:hidden name="id" id="id"></s:hidden>
		<div id="time">
			<table style="width: 100%;height: 20px;border: 0" >
				<tr>
				    <td style="width: 64%"></td>
					<td><a href="javascript:;" name="cpu" class="aalink" ><span style="color: blue">CPU使用率TOPN</span></a></td>
					<td><a href="javascript:;" name="mem" class="aalink"><span style="color: blue">内存使用率TOPN</span></a></td>
<%-- 				<td><a href="javascript:;" name="store" class="aalink"><span style="color: blue">存储使用率TOPN</span></a></td> --%>					
					<td style="font-size:15px;">TOP<input type="text" style="width:20px;color: red" id="num" value="10"/><input type="button" value="GO" id="set" onclick="getTopN();"/></td>
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
						<th>CPU利用率</th>
						<th>内存利用率</th>
						<!-- <th>存储利用率</th> -->
					</tr>
				</thead>
				<tbody>
					<s:iterator id="list" value="resultList">
						<tr>
							<td><s:property value="#list.name"/></td>
							<td><s:property value="#list.ip"/></td>
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
