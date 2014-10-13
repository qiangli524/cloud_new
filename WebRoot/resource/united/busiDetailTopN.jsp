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
    	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<style type="text/css">
		.font-more{ width:180px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
</style>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;

         $(function(){
			initData();
         });
         
         function initData(){
        	 $("#tab tr:not(:first)").remove();
			 var resourceType = $("#resourceType").val();
        	 var topnum = $("#topnum").val();
        	 var d = new Date();
        	 $.ajax({
        		 type:'post',
        		 url:'resourceOutline_showBusiDetailTopN.do?resourceType='+resourceType+'&top_num='+topnum+'&dd='+d,
        		 dataType:'json',
        		 success:function(msg){
        			 var chartXml = msg.chartXml;
        			 var chart1 = new FusionCharts("FusionCharts/Column2D.swf","ChartId1","100%","300","0","0");
 					 chart1.setJSONData(chartXml);
 					 chart1.render("chartarea");
 					 
 					 var resultList = msg.resultList;
 					 if (resultList != null) {
 						if (resourceType == 2) {
	 						 for(var i = 0;i<resultList.length;i++){
	 							$("<tr><td align=\"center\"><span style=\"color:black;\" class=\"font-more\" title='"+ resultList[i].NAME +"'>"
	 	 							 	+resultList[i].NAME+"</span></td><td>"
	 	 								+resultList[i].USER_ID+"</td><td>"
	 	 								+resultList[i].OEM+"</td><td>"
	 	 								+resultList[i].storeSize+"</td></tr>").insertAfter($("#tab tr:eq("+i+")"));
	 						 }
						} else {
	 						 for(var i = 0;i<resultList.length;i++){
	 							$("<tr><td align=\"center\"><span style=\"color:black;\" class=\"font-more\" title='"+ resultList[i].NAME +"'>"
	 	 							 	+resultList[i].NAME+"</span></td><td>"
	 	 								+resultList[i].USER_ID+"</td><td>"
	 	 								+resultList[i].OEM+"</td><td busi=\""+resultList[i].PARENT_ID+"\"><a href=\"javascript:;\" name=\"haha\">"
	 	 								+resultList[i].vmCount+"</a></td></tr>").insertAfter($("#tab tr:eq("+i+")"));
	 						 }
						}
					}
        		 }
        	 });
         }
         
         $(function(){
        	 $("[name='haha']").unbind().live("click",function(){
        		 var $td = $(this).parent();
            	 var busiId = $td.attr("busi");
            	 w.$.dialog({
    	 			id:'viesVMs',
    	 			title:'业务系统占用的虚拟机',
    	 			width:'1000px',
    	 			height:'550px',
    	 			content:'url:showvm_ah_listvm.do?busiId='+busiId
            	});
        	 });
         });
</script>
    </head>

    <body class="pop-body scrollbody" style="overflow-x:hidden">
    	<s:hidden name="resourceType" id="resourceType"></s:hidden>
    	<div align="right">
    		<table>
    			<tr>
    				<td>
    					TOP<input type="text" style="width:30px;color: red" id="topnum" value="10"/>
    					<input type="button" value="GO" class="thickbox btn-style02" onclick="initData()"/>
    				</td>
    			</tr>
    			<tr height="10px;">
    				<td></td><td></td>
    			</tr>
    		</table>
    	</div>
    	
        <div id="chartarea" align="center"></div>
        <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="tab">
				<thead>	
					<tr>
						<th>业务系统名称</th>
						<th>负责人</th>
						<th>厂商</th>
						<s:if test="resourceType==1">
							<th>虚拟机个数(个)</th>
						</s:if>
						<s:else>
							<th>存储大小(TB)</th>
						</s:else>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
    </body>
</html>
