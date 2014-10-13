<%@ page language="java" import="java.util.*,java.lang.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <title>数据监控</title>
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
		<style type="text/css">
			.black-table th,.black-table td,.pop-table th,.pop-table td,.creat-table th,.creat-table td{border:1px solid ; empty-cells:show; color:#000000; border-bottom:none; border-left:none; height:23px; line-height:23px; padding:0 3px 0 0; text-align: center;}
			.black-table td{text-align: center;}
			.black-table td span{text-align: center;font-size: 14px;}
		</style>
        <script type="text/javascript">
        	function slabChanged(){
        		var entity_id = $("#slab").val();
	        	 $.ajax({
				    type:"get",
		            url:'paasDataMonitor_getSlabsInfo.do?entity_id='+entity_id,
     				dataType: "json",
     		        async: true,
		            cache: false,
			        success: function(data){
			        	$("#slabs tr:not('#not')").remove();
			        	var resultList = data.slabkpi;
			        	if(resultList!=null&&resultList!=""){
			        		for(var i=0;i<resultList.length;i++){
	     						if(resultList[i].desc_kpi =='west'){
	     							$("<tr><td colspan='2'><span>"+resultList[i].kpi_name+"</span></td><td><span>"
	     							+resultList[i].kpi_value_last+"</span></td></tr>").insertAfter($("#slabs tr:eq("+(i+2)+")"));
	     						}else {
		     						$("<tr><td><span>"+resultList[i].kpi_name+"</span></td><td><span>"+resultList[i].desc_kpi+"</span></td><td><span>"
		     						+resultList[i].kpi_value_last+"</span></td></tr>").insertAfter($("#slabs tr:eq("+(i+2)+")"));
	     						}
	     					}
			        	}
			        }
				}); 
        }
        </script>
    </head>

    <body class="pop-body scrollbody" style="overflow-x:hidden" >
    	<s:hidden name="entity_id" id="id"></s:hidden>
		<table width="700px"  border="1px" class="black-table">
			<tr><td colspan="3" bgcolor="#b5d4f3" align="center"><font size="4px">全局参数</font></td></tr>
			<tr>
				<td width="200px" height="30px" bgcolor="#b5d4f3" style="text-align: center">
					<font size="3px" color="#00000">参数</font>
				</td>
				<td width="350px" height="30px" bgcolor="#b5d4f3" style="text-align: center">
					<font size="3px" color="#00000">描述</font>
				</td>
				<td width="150px" height="30px" bgcolor="#b5d4f3" style="text-align: center">
					<font size="3px" color="#00000">值</font>
				</td>
			</tr>
			<tr height="30px" >
				<td><span>curr_items</span></td>
				<td><span>当前存储的数据总数</span></td>
				<td><span><s:property value="dataMonitor.curr_items" default="-"/></span></td>
			</tr>
			<tr height="30px" >
				<td><span>bytes</span></td>
				<td><span>当前存储占用的字节数</span></td>
				<td><span><s:property value="dataMonitor.bytes" default="-"/></span></td>
			</tr>
			<tr height="30px" >
				<td><span>actives_slabs</span></td>
				<td><span>slab数量</span></td>
				<td><span><s:property value="dataMonitor.actives_slabs" default="-"/></span></td>
			</tr>
			<tr height="30px" >
				<td><span>total_malloced</span></td>
				<td><span>总内存数量</span></td>
				<td><span><s:property value="dataMonitor.total_malloced" default="-"/></span></td>
			</tr>
		</table><br>
		<br>
		<table width="700px" border="1px" class="black-table" id="slabs">
			<tr id="not"><td colspan="3" bgcolor="#b5d4f3" align="center"><font size="4px" color="#00000">SLAB参数</font></td></tr>
			<tr id="not"><td colspan="3" bgcolor="#b5d4f3" style="clear:both;" align="right">
				<span>选择内存区块 ：</span><s:select id="slab" name="slab" list="list" listKey="kpiValue" listValue="kpiName" onchange="slabChanged();" cssStyle="width:150px"></s:select></td>
			</tr>
			<tr id="not">
				<td width="200px" height="30px" bgcolor="#b5d4f3" style="text-align: center">
					<font size="3px" color="#00000">参数</font>
				</td>
				<td width="350px" height="30px" bgcolor="#b5d4f3" style="text-align: center">
					<font size="3px" color="#00000">描述</font>
				</td>
				<td width="150px" height="30px" bgcolor="#b5d4f3" style="text-align: center">
					<font size="3px" color="#00000">值</font>
				</td>
			</tr>
			<s:iterator id="obj" value="kpiList">
						<s:if test="#obj.desc_kpi == 'west'">
							<tr>
								<td colspan="2"><span><s:property value="#obj.kpi_name"/></span></td>
								<td><span><s:property value="#obj.kpi_value_last"/></span></td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td><span><s:property value="#obj.kpi_name"/></span></td>
								<td><span><s:property value="#obj.desc_kpi"/></span></td>
								<td><span><s:property value="#obj.kpi_value_last"/></span></td>
							</tr>
						</s:else>
			</s:iterator>
		</table>
    </body>
</html>
